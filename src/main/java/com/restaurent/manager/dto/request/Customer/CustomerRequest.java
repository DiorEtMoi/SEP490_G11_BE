package com.restaurent.manager.dto.request.Customer;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class CustomerRequest {

     @Valid
     @NotNull(message = "INVALID_PHONENUMBER")
     @NotBlank(message = "INVALID_PHONENUMBER")
     String phoneNumber;

     @NotNull(message = "INVALID_CUSTOMER_NAME")
     @NotBlank(message = "INVALID_CUSTOMER_NAME")
     String name;

     String address;
     @NotNull(message = "NOT_NULL")
     Long restaurantId;
}
