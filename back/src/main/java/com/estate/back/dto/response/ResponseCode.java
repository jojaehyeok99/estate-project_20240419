package com.estate.back.dto.response;

// Response의 공통된 code 값
public interface ResponseCode {
  
  // 200
  String SUCCESS = "SU";

  // 400
  String VALIDATION_FAILED = "VF";
  String DUPLICATED_ID = "DI";
  String DUPLICATED_EMAIL = "DE";
  String NO_EXIST_BOARD = "NB";
  String WRITTEN_COMMENT = "WC";

  // 401
  String SIGN_IN_FAILED = "SF";
  String AUTHENTICATION_FAILED = "AF";

  // 500
  String TOKEN_CREATION_FAILED = "TF";
  String DATABASE_ERROR = "DBE";
  String MAIL_SEND_FAILED = "MF";
}
