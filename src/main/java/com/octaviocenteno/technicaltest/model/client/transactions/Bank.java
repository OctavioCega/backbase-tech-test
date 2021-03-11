package com.octaviocenteno.technicaltest.model.client.transactions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Bank {

    @JsonProperty("national_identifier")
    private String nationalIdentifier;
    private String name;
}
