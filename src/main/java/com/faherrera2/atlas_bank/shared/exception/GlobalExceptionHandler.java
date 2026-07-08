package com.faherrera2.atlas_bank.shared.exception;

import com.faherrera2.atlas_bank.account.exception.AccountNotFoundException;
import com.faherrera2.atlas_bank.transaction.exception.AccountNotActiveException;
import com.faherrera2.atlas_bank.transaction.exception.InsufficientFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice // create a class that catch all exception that show up in the system
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    public ProblemDetail handleAccountNotFound(AccountNotFoundException ex){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );
        problem.setTitle("Account not found");
        return problem;
    }

    @ExceptionHandler(InsufficientFoundException.class)
    public ProblemDetail handleInsufficientFoundException(InsufficientFoundException ex){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatusCode.valueOf(424),
                ex.getMessage()
        );
        problem.setTitle("Insufficient funds");
        return problem;
    }

    @ExceptionHandler(AccountNotActiveException.class)
    public ProblemDetail handleAccountNotActiveException(AccountNotActiveException ex){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.UNPROCESSABLE_CONTENT,
                ex.getMessage()
        );
        problem.setTitle("Account is not active");
        return problem;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleException(Exception ex){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal server error"
        );
        problem.setTitle("Internal error");
        return problem;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidation(MethodArgumentNotValidException ex){
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setTitle("Validation Error");

        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors()
                .forEach( error -> errors.add(error.getField() + ": "+ error.getDefaultMessage()));

        ex.getBindingResult().getGlobalErrors()
                        .forEach(error -> errors.add(error.getDefaultMessage()));
        problem.setProperty("errors", errors);

        return problem;
    }
}
