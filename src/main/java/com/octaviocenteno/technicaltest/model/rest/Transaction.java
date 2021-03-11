package com.octaviocenteno.technicaltest.model.rest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {

    private String id;
    private String accountId;
    private String counterpartyAccount;
    private String counterpartyName;
    private String counterPartyLogoPath;
    private String instructedAmount;
    private String instructedCurrency;
    private String transactionAmount;
    private String transactionCurrency;
    private String transactionType;
    private String description;
}
