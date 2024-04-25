package com.estate.back.service.implementation;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.estate.back.common.util.EmailAuthNumberUtil;
import com.estate.back.dto.request.auth.EmailAuthCheckRequestDto;
import com.estate.back.dto.request.auth.EmailAuthRequestDto;
import com.estate.back.dto.request.auth.IdCheckRequestDto;
import com.estate.back.dto.request.auth.SignInRequestDto;
import com.estate.back.dto.request.auth.SignUpRequestDto;
import com.estate.back.dto.response.ResponseDto;
import com.estate.back.dto.response.auth.SignInResponseDto;
import com.estate.back.entity.EmailAuthNumberEntity;
import com.estate.back.provider.MailProvider;
import com.estate.back.repository.EmailAuthNumberRepository;
import com.estate.back.repository.UserRepository;
import com.estate.back.service.AuthService;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

// Auth 모듈의 비즈니스 로직 구현체
@Service
@RequiredArgsConstructor
public class AuthServiceImplementation implements AuthService{

  private final UserRepository userRepository;
  private final EmailAuthNumberRepository emailAuthNumberRepository;
  private final MailProvider mailProvider;

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

  @Override
    public ResponseEntity<ResponseDto> emailAuth(EmailAuthRequestDto dto) {
        
        try {

            String userEmail = dto.getUserEmail();

            boolean existedEmail = userRepository.existsByUserEmail(userEmail);
            if (existedEmail) return ResponseDto.duplicatedEmail();

            String authNumber = EmailAuthNumberUtil.createNumber();

            EmailAuthNumberEntity emailAuthNumberEntity = new EmailAuthNumberEntity(userEmail, authNumber);

            emailAuthNumberRepository.save(emailAuthNumberEntity);

            mailProvider.mailAuthSend(null, null);

        } catch (MessagingException messagingException) {
            messagingException.printStackTrace();
            return ResponseDto.mailSendFailed();


        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        try {
            mailProvider.mailAuthSend(null, null);
            

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.mailSendFailed();
        }

        return ResponseDto.success();
        
    }

  @Override
  public ResponseEntity<ResponseDto> emailAuthCheck(EmailAuthCheckRequestDto dto) {
    
    throw new UnsupportedOperationException("Unimplemented method 'emailAuthCheck'");
  }

  @Override
  public ResponseEntity<ResponseDto> signUp(SignUpRequestDto dto) {
    
    throw new UnsupportedOperationException("Unimplemented method 'signUp'");
  }

  @Override
  public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'signIn'");
  }
}
