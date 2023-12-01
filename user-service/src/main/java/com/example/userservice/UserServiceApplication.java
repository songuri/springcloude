package com.example.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceApplication {
    /**
     *  서버를 기동시키는 4가지 방법
     *  1. Inteli J Run 옆에 인스턴스 변경 > Edit Configuration > UserServiceApplication > Add vm option -Dserver.port=9002
     *  2. 명령어로 실행  mvn spring-boot:run `-Dspring-boot.run.jvmArguments='-Dserver.port=9003'
     *  3. jar 파일 생성후 실행
     *  mvn clean complie package
     *  java -jar -Dserver.port=9004 ./target/user-service-0.0.1-SNAPSHOT.jar
     *
     *  4. 그냥 런때려 버리기
     *
     * --> 근데 이렇게 사람이 포트 하나씩 부여 하면서 하는거 졸라 불편함  spring boot에서 자동(랜덤)으로 부여하도록 해보자!
     *
     *  자동 부여 (랜덤 부여)
     *  application.yml   에서 설정  port: 0
     *  mvn spring-boot:run
     */

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

}


