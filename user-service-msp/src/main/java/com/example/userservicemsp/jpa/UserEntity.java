package com.example.userservicemsp.jpa;


import jakarta.persistence.*;
import lombok.Data;

/**
 * Entity class 는 data base로 만들어져야 하는 클래스다.
 */

@Data
@Entity
@Table(name = "users")
public class UserEntity { // 내용이 없을떄 오류는 기본키가 없기 때문에.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String email;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, unique = true)
    private String userId;
    @Column(nullable = false, length = 50)
    private String encryptedPwd;

}
