package com.octaviocenteno.technicaltest.model.client.transactions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class OBPTransaction {

    private String id;
    @JsonProperty("this_account")
    private ThisAccount thisAccount;
    @JsonProperty("other_account")
    private OtherAccount otherAccount;
    private TransactionDetails details;
    private AccountMetadata metadata;
}
