package com.cg.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.jwt.entity.UserInfo;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findByName(String username);

}
