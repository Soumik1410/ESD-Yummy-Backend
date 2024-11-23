package com.prashantjain.yummyrest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public record CustomerDetailsUpdateRequest(
        @NotNull(message = "Access Token should be present")
        @NotEmpty(message = "Access Token should be present")
        @NotBlank(message = "Access Token should be present")
        @JsonProperty("access_token")
        String access_token,

        @JsonProperty("first_name")
        String firstName,

        @JsonProperty("last_name")
        String lastName,

        @JsonProperty("address")
        String address,

        @JsonProperty("city")
        String city,

        @JsonProperty("pincode")
        String pincode
) {
}
