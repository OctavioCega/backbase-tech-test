package com.octaviocenteno.technicaltest.model.client.transactions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public abstract class BaseAccount {

    private String id;
    private String number;
    private String kind;
    @JsonProperty("IBAN")
    private String iban;
    @JsonProperty("swift_bic")
    private String swiftBic;
    private Bank bank;
}
