package com.filopl.softblue.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Created by T. Filo Zegarlicki on 10.06.2023
 **/

@Getter
@Setter
public class ErrorResponse {

    private int status;
    private String message;
    private LocalDateTime timestamp;

}
