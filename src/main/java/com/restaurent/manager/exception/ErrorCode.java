package com.restaurent.manager.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(500, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(400, "Uncategorized error", HttpStatus.BAD_REQUEST),
    USER_EXISTED(400, "user already exist in system", HttpStatus.BAD_REQUEST),
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
    RESTAURANT_NAME_EXISTED(400,"Restaurant name is exist ",HttpStatus.BAD_REQUEST),
    FIELD_EMPTY(400,"this field can not empty",HttpStatus.BAD_REQUEST),
    NUMBER_CHAIRS_TOO_FEW(400,"number chairs must be greater than {min}", HttpStatus.BAD_REQUEST),
    NOT_EXIST(404,"id not exist ",HttpStatus.BAD_REQUEST),
    GREATER_NUMBER(400,"can not set field equal 0 or less than 0",HttpStatus.BAD_REQUEST),
    DISH_NOT_FOUND(404,"Dish not existed" , HttpStatus.NOT_FOUND ),
    INVALID_COMBO_PRICE(400, "Combo price must be number and greater than 0", HttpStatus.BAD_REQUEST),
    INVALID_COMBO_DESCRIPTION(400, "Combo description can not be empty", HttpStatus.BAD_REQUEST),
    INVALID_CUSTOMER_NAME(400, "Customer name can not be empty", HttpStatus.BAD_REQUEST),
    COMBO_NOT_EXISTED(400, "Combo not existed", HttpStatus.NOT_FOUND),
    INVALID_COMBO_NAME(400, "Combo name can not empty", HttpStatus.BAD_REQUEST),
    PACKAGE_NOT_EXIST(404,"Pack is not exist",HttpStatus.BAD_REQUEST),
    TABLE_NAME_EXISTED(400,"Table name is existed",HttpStatus.BAD_REQUEST),
    TABLE_TYPE_NULL(400,"Table type can not null field",HttpStatus.BAD_REQUEST),
    AREA_NULL(400,"Area can not null field",HttpStatus.BAD_REQUEST),
    NAME_NULL(400,"name can not null",HttpStatus.BAD_REQUEST),
    EMPLOYEE_NOT_EXIST(404,"Employee not exist",HttpStatus.BAD_REQUEST),
    CUSTOMER_NOT_EXIST(404,"Customer not exist",HttpStatus.BAD_REQUEST),
    INVALID_VAT_CODE(400,"Invalid vat code", HttpStatus.BAD_REQUEST),
    NOT_EMPTY(400,"Field can not empty",HttpStatus.BAD_REQUEST),
    EMAIL_NOT_EXIST(400, "Email not exist",HttpStatus.BAD_REQUEST),
    INVALID_TAX_VALUE(400,"Tax value must be between {min} to {max}",HttpStatus.BAD_REQUEST),
    MAX_AREA(400,"Bạn đã đạt giới hạn khu vực, vui lòng nâng cấp gói để có thể thêm", HttpStatus.BAD_REQUEST),
    RESTAURANT_ID_NULL(400,"restaurant id can be not null", HttpStatus.BAD_REQUEST),
    MAX_TABLE(400, "Bạn không thể tạo thêm bàn vì đã vượt qua giới hạn cho phép của gói, nâng cấp gói để tạo bàn.", HttpStatus.BAD_REQUEST),
    BOOKED_TABLE(400, "Bàn đã được đặt trước, vui lòng đặt bàn khác", HttpStatus.BAD_REQUEST),
    BOOKED_DATE_INVALID(400, "Vui lòng chọn ngày lớn hơn ngày hiện tại", HttpStatus.BAD_REQUEST),
    TIME_INVALID(400, "Vui lòng chọn thời gian lớn hơn thời gian hiện tại", HttpStatus.BAD_REQUEST)
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
