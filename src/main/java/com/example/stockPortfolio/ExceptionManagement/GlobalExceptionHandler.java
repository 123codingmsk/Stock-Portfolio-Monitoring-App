package com.example.stockPortfolio.ExceptionManagement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    //custom exception for resource notfound exception
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorClass> handleResourceNotFound(ResourceNotFoundException ex) {
        String errMsg = ex.getMessage();
        ErrorClass error = new ErrorClass(
                HttpStatus.BAD_REQUEST.value(),
                errMsg,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    //usernotfound exception gets handled here
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorClass> handleUserNotFoundException(UserNotFoundException ex){
        String errMsg = ex.getMessage();
        ErrorClass error = new ErrorClass(
                HttpStatus.BAD_REQUEST.value(),
                errMsg,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    //MethodArgumentNotValidException, pre-defined exception, @Valid will handle this exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorClass> handleValidationException(MethodArgumentNotValidException ex){

        //creates a user-friendly message, so he understands
        String errMsg = ex.getBindingResult().getFieldErrors().stream().
                map(fieldError -> fieldError.getField()+": "+fieldError.getDefaultMessage())
                .collect(Collectors.joining(", "));

        //this takes the parameters, to show the output or the error occurred in a good way
        ErrorClass error = new ErrorClass(
                HttpStatus.BAD_REQUEST.value(),
                errMsg,
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(HoldingNotFoundException.class)
    public ResponseEntity<String> handleHoldingNotFound(HoldingNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(StockDataUnavailableException.class)
    public ResponseEntity<String> handleStockDataUnavailable(StockDataUnavailableException ex) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneric(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
    }
}
