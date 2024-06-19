package com.restaurent.manager.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(500, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(400, "Uncategorized error", HttpStatus.BAD_REQUEST),
    USER_EXISTED(400, "Phone number or email is exist in system", HttpStatus.BAD_REQUEST),
    EMAIL_EXIST(400, "Email existed", HttpStatus.BAD_REQUEST),
    PHONENUMBER_EXIST(400, "Phone number existed", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(400, "Username must be at least {min} characters", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(400, "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(404, "User not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(401, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(403, "You do not have permission", HttpStatus.FORBIDDEN),
    INVALID_DOB(400, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),
    ROLE_EXISTED(400,"Role existed",HttpStatus.BAD_REQUEST),
    ROLE_NOT_EXISTED(400,"Role not existed",HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(400,"Invalid code",HttpStatus.BAD_REQUEST),
    INVALID_USERNAME(400,"username can not empty",HttpStatus.BAD_REQUEST),
    INVALID_PHONENUMBER(400,"invalid phone number",HttpStatus.BAD_REQUEST),
    INVALID_EMAIL(400,"invalid email",HttpStatus.BAD_REQUEST),
    PASSWORD_INCORRECT(400,"Incorrect password",HttpStatus.BAD_REQUEST),
    DISH_CATEGORY_EXIST(400,"Dish category name is existed ",HttpStatus.BAD_REQUEST),
    RESTAURANT_NOT_EXISTED(404, "Restaurant not existed", HttpStatus.NOT_FOUND),
    LIMITED_RESTAURANT(400,"You can only create one restaurant ",HttpStatus.BAD_REQUEST),
    RESTAURANT_NAME_EXISTED(400,"Restaurant name is exist ",HttpStatus.BAD_REQUEST)
    ;
    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
    private int code;
    private String message;
    private HttpStatusCode statusCode;
}
