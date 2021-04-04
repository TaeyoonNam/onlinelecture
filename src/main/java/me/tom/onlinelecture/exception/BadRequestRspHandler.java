package me.tom.onlinelecture.exception;

import me.tom.onlinelecture.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Lombok을 통해 타입 검증 에러 처리
 */
@RestControllerAdvice
public class BadRequestRspHandler {

  @ExceptionHandler({MethodArgumentNotValidException.class})
  public ResponseEntity<ResponseDto> validException(MethodArgumentNotValidException ex) {
    ResponseDto responseDto =
        new ResponseDto(
            false,
            ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());

    return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
  }
}
