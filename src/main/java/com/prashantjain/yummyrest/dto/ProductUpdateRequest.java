package com.prashantjain.yummyrest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public record ProductUpdateRequest(
        @JsonProperty("name")
        String name,

        @JsonProperty("price")
        String price
) {
}
