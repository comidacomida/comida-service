package com.comida.exceptions;

import java.util.IllegalFormatException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum IssueEnum {
  MALFORMED_REQUEST(1, "Malformed Request"),
  REMOTE_SERVICE_ERROR(101, "Error when calling %s. Original message: %s."),
  REMOTE_SERVICE_UNEXPECTED_ERROR(
      102, "Unexpected error returned when calling %s. Original message: %s");


  private final int code;
  private final String message;

  IssueEnum(final int code, final String message) {

    this.code = code;
    this.message = message;
  }

  public int getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  public String getFormattedMessage(final Object... args) {

    if (message == null) {
      return "";
    }

    try {
      return String.format(message, args);
    } catch (final IllegalFormatException e) {
      log.error(e.getMessage(), e);
      return message.replace("%s", "");
    }
  }

}
