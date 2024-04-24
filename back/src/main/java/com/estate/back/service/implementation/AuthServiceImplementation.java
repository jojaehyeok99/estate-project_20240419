package com.estate.back.service.implementation;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.estate.back.dto.request.auth.IdCheckRequestDto;
import com.estate.back.dto.response.ResponseDto;
import com.estate.back.repository.UserRepository;
import com.estate.back.service.AuthService;

import lombok.RequiredArgsConstructor;

// Auth 모듈의 비즈니스 로직 구현체
@Service
@RequiredArgsConstructor
public class AuthServiceImplementation implements AuthService{

  private final UserRepository userRepository;

  @Override
  public ResponseEntity<ResponseDto> idCheck(IdCheckRequestDto dto) {
    
    try{

      // 2. 데이터베이스의 user 테이블에서 해당하는 (userId)를 가지고 있는 유저가 있는지 확인(boolean)
      String userId = dto.getUserId();
      boolean existedUser = userRepository.existsByUserId(userId);
      // 2-1. 만약 가지고 있는 유저가 존재하면 'DI' 응답 처리
      if (existedUser) return ResponseDto.duplicatedId();

    } catch (Exception exception){
        // 2-2. 만약 데이트베이스 작업 중 에러가 발생하면 'DBE' 응답 처리
        exception.printStackTrace();
        return ResponseDto.databaseError();
    }
    // 중복된 (userId)가 없다면 'SU' 응답 처리
    return ResponseDto.success();
  }
}
