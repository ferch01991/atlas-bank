package com.faherrera2.atlas_bank.transaction.validation;

import com.faherrera2.atlas_bank.transaction.dtos.TransferRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DifferentAccountValidator implements ConstraintValidator<DifferentAccounts, TransferRequest> {
    @Override
    public boolean isValid(TransferRequest transferRequest, ConstraintValidatorContext context) {
        if (transferRequest.getFromId() == null || transferRequest.getToId() == null){
            return true;
        }
        return !transferRequest.getFromId().equals(transferRequest.getToId());
    }
}
