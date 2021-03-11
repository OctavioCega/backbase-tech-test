package com.octaviocenteno.technicaltest.model.client.transactions;

import lombok.Getter;

@Getter
public class OtherAccount extends BaseAccount {

    private Holder holder;
    private AccountMetadata metadata;
}
