package com.octaviocenteno.technicaltest.model.client.transactions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Holder {

    private String name;
    @JsonProperty("is_alias")
    private boolean isAlias;
}
