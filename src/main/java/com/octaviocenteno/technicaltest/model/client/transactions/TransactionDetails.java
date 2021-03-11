package com.octaviocenteno.technicaltest.model.client.transactions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
public class TransactionDetails {

    private String type;
    private String description;
    private OffsetDateTime posted;
    private OffsetDateTime completed;
    @JsonProperty("new_balance")
    private Balance newBalance;
    private Balance value;

}
