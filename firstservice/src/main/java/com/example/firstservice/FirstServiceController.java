package com.example.firstservice;


import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/first-service")
public class FirstServiceController {

    Environment environment; // application.yml 파일에 등록된 환경설정 정보를 가져오는 방법중 하나. 가장 전통적인 방식

    //@Autowired 를 사용하는게 가장 일반적이지만 intelij는 생성자를 통해서 하는걸 추천함.
    public FirstServiceController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the First service.";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("first-request") String header) {
        log.info(header);
        return "Hello World in First Service";
    }

    @GetMapping("/check")
    public String check(HttpServletRequest request) {
        log.info("Server Port= {}", request.getServerPort());
        return String.format("Hi, there. THis is a message from First Service on PORT %s",
                environment.getProperty("local.server.port"));

    }
}
