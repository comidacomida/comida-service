package com.comida.client;

import com.comida.exceptions.RemoteServiceErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Component
public class RemoteClient {

  private static final Logger LOGGER = LoggerFactory.getLogger(RemoteClient.class);

  @Autowired
  private RestTemplate restTemplate;

  public <T> ResponseEntity<T> getForObject(
      final String url,
      final HttpHeaders headers,
      final Class<T> expectedResponseType,
      final String remoteServiceName,
      final Object... uriVariables) {

    return doRequest(
        url, HttpMethod.GET, headers, null, expectedResponseType, remoteServiceName, uriVariables);
  }

  private <T> ResponseEntity<T> doRequest(
      final String url,
      final HttpMethod method,
      final HttpHeaders headers,
      final Object payload,
      final Class<T> expectedResponseType,
      final String remoteServiceName,
      final Object... uriVariables) {

    try {
      return restTemplate.exchange(
          url, method, new HttpEntity<>(payload, headers), expectedResponseType, uriVariables);

    } catch (final HttpStatusCodeException e) {
      LOGGER.error(e.getMessage(), e);
      throw RemoteServiceErrorException.errorRequestingRemoteService(
          remoteServiceName, e.getMessage(), e);

    } catch (final Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw RemoteServiceErrorException.unexpectedErrorRequestingRemoteService(
          remoteServiceName, e.getMessage(), e);
    }
  }
}
