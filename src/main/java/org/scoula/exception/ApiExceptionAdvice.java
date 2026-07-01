package org.scoula.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice(basePackages = "org.scoula")   // API 컨트롤러 예외를 JSON으로 처리
@Log4j2
public class ApiExceptionAdvice {

    // 데이터를 못 찾음 → 404
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNotFound(NoSuchElementException ex) {
        log.error("404: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("요청한 데이터를 찾을 수 없습니다.");
    }

    // 잘못된 요청 (파라미터 오류 등) → 400
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBadRequest(IllegalArgumentException ex) {
        log.error("400: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("잘못된 요청입니다: " + ex.getMessage());
    }

    // 그 외 모든 예외 → 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleServerError(Exception ex) {
        log.error("500: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("서버 오류가 발생했습니다.");
    }
}