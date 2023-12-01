package com.example.secondservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RequestMapping("/") 일떄 http://localhostL8082/welcom <- 이렇게 해야 열림.
//@RequestMapping("/") 일때 apiGateway에서는  http://localhostL8082/first-service/welcome
// 이렇게 요청하고 있느니까 404가 발생
// @RequestMapping("/") -> @RequestMapping("/first-service/") 로 수정
@RestController
@RequestMapping("/second-service")
@Slf4j
public class SecondServiceController {
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the First service.";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("second-request") String header) {
        log.info(header);
        return "Hello World in Second Service";
    }

    @GetMapping("/check")
    public String check() {
        return "Hi, there. THis is a message from Second Service";
    }
}
