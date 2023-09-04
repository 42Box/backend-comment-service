package com.practice.boxcommentservice.client.board_client;

import com.practice.boxcommentservice.entity.comments.ScriptBoardsCommentsEntity;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * UserClient.
 *
 * @author : middlefitting
 * @since : 2023/08/31
 */


@Component
@AllArgsConstructor
@Slf4j
public class ScriptBoardClient implements BoardsClient<ScriptBoardsCommentsEntity> {

  private final DiscoveryClient discoveryClient;

  public HttpStatus increaseComment(long boardId) {
    return getStatus(boardId, HttpMethod.POST);
  }

  public HttpStatus decreaseComment(long boardId) {
    return getStatus(boardId, HttpMethod.DELETE);
  }

  private HttpStatus getStatus(long boardId, HttpMethod method) {
    try {
      RestTemplate restTemplate = new RestTemplate();
      String userServiceUrl = getServiceUrl("board-service");
      String endpoint = userServiceUrl + "/private/script-boards/" + boardId + "/comments";
      restTemplate.exchange(endpoint, method, null, Void.class);
    } catch (HttpClientErrorException e) {
      if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
        return HttpStatus.NOT_FOUND;
      }
    } catch (Exception e) {
      return HttpStatus.INTERNAL_SERVER_ERROR;
    }
    return HttpStatus.OK;
  }

  private String getServiceUrl(String serviceName) {
    List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
    if (instances != null && !instances.isEmpty()) {
      return instances.get(0).getUri().toString();
    }
    throw new RuntimeException("No service instance available for " + serviceName);
  }
}
