package com.example.userservicemsp.controller;


import com.example.userservicemsp.dto.UserDto;
import com.example.userservicemsp.service.UserService;
import com.example.userservicemsp.vo.Greeting;
import com.example.userservicemsp.vo.RequestUser;
import com.example.userservicemsp.vo.ResponseUser;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/") // ("/") <- 이건 쓰지 않았던 거랑 동일한 표현.
public class UserController {
    /**
     * application.yml 파일에 존재하는 데이터를 가져오는 2가지 방법
     * 1. Environment 사용.
     * 2. @Value 사용.
     */
    private final Environment env;
    private final Greeting greeting;

    private UserService userService;
    public UserController(Environment env, Greeting greeting, UserService userService ) {
        this.env = env;
        this.greeting = greeting;
        this.userService = userService;
    }


    @PostMapping("/users/v1")
    public String createUserV1(@RequestBody RequestUser user) { //requestUserType은 requestBody 형태로 넘어오기 때문에 annotation, request, body 잊지 말고 써주시기 바랍니다.

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto = mapper.map(user, UserDto.class);
        userService.createUser(userDto);

        return "Create User Method Is Called";  // 이 상태로만 쓰면 Respond Code 200 이 나옴.
    }

    @PostMapping("/users/v2")
    public ResponseEntity createUserV2(@RequestBody RequestUser user) { //requestUserType은 requestBody 형태로 넘어오기 때문에 annotation, request, body 잊지 말고 써주시기 바랍니다.

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto = mapper.map(user, UserDto.class);
        userService.createUser(userDto);

        return new ResponseEntity(HttpStatus.CREATED);  // 이 상태로만 쓰면 Respond Code 201 이 나옴.
    }

    @PostMapping("/users/v3")
    public ResponseEntity<ResponseUser> createUserV3(@RequestBody RequestUser user) {
        /**
         * 사용자에게 정상적으로 해당 기능이 수행됬고,
         * 또 어떤 데이터들이 저장됐는지 까지 정보를 주는 방법
         * ReponseUser라는 클래스를 생성해서 사용함.
         */

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto = mapper.map(user, UserDto.class);
        userService.createUser(userDto);

        ResponseUser responseUser = mapper.map(userDto, ResponseUser.class);


        return  ResponseEntity.status(HttpStatus.CREATED).body(responseUser); // 생성된 일부 값을 같이 넘겨줌.
    }




    @GetMapping("/welcome_env")
    public String welcome() {
        return env.getProperty("greeting.message");
    }

    @GetMapping("/welcome_value")
    public String welcome_v2() {
        return greeting.getMessage();
    }

    @GetMapping("/health_check")
    public String status() {
        return "It's working well in user service msp";
    }



}
