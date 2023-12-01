package com.example.apigatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApigatewayServiceApplication {
    // API GATEWAY를 실행하게 되면
    // Netty started on port 8000 <- Netty라는 비동기 내장 서버가 작동 됨.
    // ZULL은 동기 방식으로 톰캣서버를 기동 했는데 Spring Cloud API GateWay 서비스를 사용하면 비동기 방식으로 서비스를 실행한다.

    public static void main(String[] args) {
        SpringApplication.run(ApigatewayServiceApplication.class, args);
    }

}
