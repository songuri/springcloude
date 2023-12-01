package com.example.userservicemsp.service;


import com.example.userservicemsp.dto.UserDto;
import com.example.userservicemsp.jpa.UserEntity;
import com.example.userservicemsp.jpa.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 유저 서비스 impl은 그냥 일반적인 자바 클래스로 등록하지 말고
 * 데이터값이 빈으로서 등록되게끔 해야 한다.
 */


/**
 *    단계         Front End <--------> Controller/Service/Repository   <--------> Database
 *    사용객체              [RequestUser]      [DTO]                       [Entity]
 *    각 단계에서 사용되는 객체가 다른데 이런거는 각각의 위치에서 쓰는 객체들은 변환하면서 사용해야함.
 *    이런건 좀 편하게 해주는 클래스가 MapperClass
 *
 *         <dependency>
 *             <groupId>org.modelmapper</groupId>
 *             <artifactId>modelmapper</artifactId>
 *             <version>2.3.8</version>
 *         </dependency>
 */

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        /**
         * @Autowired 라는 것은 사용자, 개발자에 의해서 인스턴스가 만들어지는 것이 아닌 Spring에서 컨테스트가 기동이 되면서 자동으로
         * 등록할 수 있는 Bean들을 찾아서 메모리에 객체를 생성/등록해주는 과정임.
         *
         * UserServiceImpl 이 빈에 등록될려면 매개변수인  userRepository , bCryptPasswordEncoder 도 둘다 생성이 되어야 하는데
         * userRepository 객체는 이미 빈에 등록이 되어 있음
         * 근데 BCryptPasswordEncoder는 Bean에 등록이 되어 있지 않고 생성도 되어 있지 않은 상태라 오류 발생함.
         *
         * 그래서 UserServiceMspApplication 에 초기 기동에 필요한 클래스를 우리가 제어할 수 있으므로 저기에 생성자 만들어 주면 됨
         */
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity userEntity = mapper.map(userDto, UserEntity.class);
        userEntity.setEncryptedPwd(bCryptPasswordEncoder.encode(userDto.getPwd()));

        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>Entity   " + userEntity.toString());
        userRepository.save(userEntity);

        UserDto returnUserDto = mapper.map(userEntity, UserDto.class);

        return returnUserDto;
    }
}
