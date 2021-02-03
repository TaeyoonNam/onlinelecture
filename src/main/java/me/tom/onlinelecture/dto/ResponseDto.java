package me.tom.onlinelecture.dto;

import lombok.Getter;

@Getter
public class ResponseDto {

  private boolean success;
  private String message;
  private Object data;

  public ResponseDto(boolean success, String message) {
    this.success = success;
    this.message = message;
  }
}
