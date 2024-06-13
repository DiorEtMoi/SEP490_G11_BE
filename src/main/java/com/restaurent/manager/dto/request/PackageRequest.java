package com.restaurent.manager.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PackageRequest {
    @Valid
    @NotNull(message = "INVALID")
    private String packName;
    private Set<Long> permissions;
}
