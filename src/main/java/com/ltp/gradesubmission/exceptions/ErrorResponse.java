package com.ltp.gradesubmission.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

   @Setter
   @Getter
public class ErrorResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDate localDate;
    private List<String> message;

    public ErrorResponse(List<String> message) {
        this.localDate = LocalDate.now();
        this.message = message;
    }



}
