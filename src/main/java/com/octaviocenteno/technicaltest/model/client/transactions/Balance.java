package com.octaviocenteno.technicaltest.model.client.transactions;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Balance {

    private String currency;
    private BigDecimal amount;
}
