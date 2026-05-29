package com.faherrera2.atlas_bank.shared.exception;

import com.faherrera2.atlas_bank.account.exception.AccountNotFoundException;
import com.faherrera2.atlas_bank.transaction.exception.AccountNotActiveException;
import com.faherrera2.atlas_bank.transaction.exception.InsufficientFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // create a class that catch all exception that show up in the system
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    public ProblemDetail handlerAccountNotFound(AccountNotFoundException ex){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );
        problem.setTitle("Account not found");
        return problem;
    }

    @ExceptionHandler(InsufficientFoundException.class)
    public ProblemDetail handlerInsufficientFoundException(InsufficientFoundException ex){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatusCode.valueOf(424),
                ex.getMessage()
        );
        problem.setTitle("Insufficient funds");
        return problem;
    }

    @ExceptionHandler(AccountNotActiveException.class)
    public ProblemDetail handlerAccountNotActiveException(AccountNotActiveException ex){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.UNPROCESSABLE_CONTENT,
                ex.getMessage()
        );
        problem.setTitle("Account is not active");
        return problem;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handlerException(Exception ex){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal server error"
        );
        problem.setTitle("Internal error");
        return problem;
    }
}
