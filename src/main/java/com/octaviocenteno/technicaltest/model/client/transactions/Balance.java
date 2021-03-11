package com.octaviocenteno.technicaltest.model.client.transactions;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class Balance {

    private String currency;
    private BigDecimal amount;
}
