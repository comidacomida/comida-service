package com.comida.helper;

import org.springframework.http.HttpHeaders;

public class ApiServicesHelper {

  /**
   * Creates a header to call API.
   *
   * @param jwt
   * @return HttpHeaders
   */
  public static HttpHeaders createHeader(final String jwt) {
    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.AUTHORIZATION, jwt);
    return headers;
  }

}
