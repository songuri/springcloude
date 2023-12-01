package com.example.userservicemsp;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.boot.autoconfigure.domain.EntityScan;


/**
 * h2 database 테스트로 사용하려면 임의로 이렇게 더미 디비를 하나 만들어야함. 아니면 자체적으로 실행을 안시킴
 */
@Entity
public class dummy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
