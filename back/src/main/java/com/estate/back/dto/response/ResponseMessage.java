package com.estate.back.dto.response;

// Response의 공통된 message 값
public interface ResponseMessage {

  // 200
  String SUCCESS = "Success.";

  // 400
  String VALIDATION_FAILED = "Validation Failed.";
  String DUPLICATED_ID = "Duplicated Id.";
  String DUPLICATED_EMAIL = "Duplicated Email.";
  String NO_EXIST_BOARD = "No Exist Board.";
  String WRITTEN_COMMENT = "Written Comment.";

  // 401
  String SIGN_IN_FAILED = "Sign in failed.";
  String AUTHENTICATION_FAILED = "Authentication Failed.";

  // 403
  String AUTHORIZATION_FAILED = "Authorization Failed.";

  String NOT_FOUND = "Not Found.";

  // 500
  String TOKEN_CREATION_FAILED = "Token creation Failed.";
  String DATABASE_ERROR = "Database Error.";
  String MAIL_SEND_FAILED = "Mail send Failed.";
}
