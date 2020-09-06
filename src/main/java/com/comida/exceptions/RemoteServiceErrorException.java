package com.comida.exceptions;

import org.springframework.web.client.HttpStatusCodeException;

public class RemoteServiceErrorException extends GlobalException {

  private static final long serialVersionUID = 1L;

  private RemoteServiceErrorException(final Issue issue, final Exception exception) {
    super(issue);
  }

  public static RemoteServiceErrorException errorRequestingRemoteService(
      final String remoteServiceName,
      final String originalErrorMessage,
      final HttpStatusCodeException exception) {

    return new RemoteServiceErrorException(
        new Issue(IssueEnum.REMOTE_SERVICE_ERROR, remoteServiceName, originalErrorMessage),
        exception);
  }

  public static RemoteServiceErrorException unexpectedErrorRequestingRemoteService(
      final String remoteServiceName, final String originalMessage, final Exception exception) {

    return new RemoteServiceErrorException(
        new Issue(IssueEnum.REMOTE_SERVICE_UNEXPECTED_ERROR, remoteServiceName, originalMessage),
        exception);
  }
}