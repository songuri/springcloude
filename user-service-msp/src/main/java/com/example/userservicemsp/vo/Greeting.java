package com.example.userservicemsp.vo;


//vo 란 Value Object의 약자.


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
@Slf4j
public class Greeting {

    @Value("${greeting.message}")
    private String message;

}
