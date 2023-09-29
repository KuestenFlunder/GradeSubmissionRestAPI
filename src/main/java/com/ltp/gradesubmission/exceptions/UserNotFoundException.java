package com.ltp.gradesubmission.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long userId) {
        super(String.format("The user with id %d does not exist,", userId));
    }

    public UserNotFoundException(String username) {
        super(String.format("The user with the name %s does not exist,", username));
    }
}
