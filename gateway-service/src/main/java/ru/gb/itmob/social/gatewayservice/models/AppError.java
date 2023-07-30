package ru.gb.itmob.social.gatewayservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppError {
    private Integer httpStatusCode;
    private String errorMsg;
}
