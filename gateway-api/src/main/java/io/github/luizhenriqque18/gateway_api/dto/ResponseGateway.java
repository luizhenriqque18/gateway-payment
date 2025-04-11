package io.github.luizhenriqque18.gateway_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResponseGateway<T> {
    private String message;
    private T data;    
}
