package com.comida.client;

import com.comida.client.response.ifood.IfoodItemResponse;
import com.comida.helper.ApiServicesHelper;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class IfoodClient {

  private static final String REMOTE_SERVICE_NAME = "ifood";

  @Value("${ifood.url}")
  private String url;

  @Autowired
  private RemoteClient remoteClient;

  @Async("asyncExecutor")
  public CompletableFuture<IfoodItemResponse> getItems(final String authorization) {

    final HttpHeaders headers =
        ApiServicesHelper.createHeader(authorization);
    final ResponseEntity<IfoodItemResponse> responseEntity =
        remoteClient.getForObject(
            url,
            headers,
            IfoodItemResponse.class,
            REMOTE_SERVICE_NAME);

    return CompletableFuture.completedFuture(responseEntity.getBody());
  }

}
