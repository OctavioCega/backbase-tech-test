package com.octaviocenteno.technicaltest.model.client.transactions;

import lombok.Getter;

import java.util.List;

@Getter
public class ThisAccount extends BaseAccount {

    private List<Holder> holders;
}
