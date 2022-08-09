package org.grube.userdetailstryout.users.error;

import org.grube.userdetailstryout.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@ControllerAdvice
@Configuration
//@PropertySource("classpath:exceptions.yml")
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // "global" exceptions (uncaught, default, fallthrough)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception exception, WebRequest request) {
        logger.error("Global Exception" + exception.toString());
        ApiException apiException = new ApiException(exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiException);
    }
}
