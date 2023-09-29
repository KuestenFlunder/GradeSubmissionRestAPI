package com.ltp.gradesubmission.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(Long userId){

                super(String.format("The user with id %d does not exist,",userId));

    };
}
