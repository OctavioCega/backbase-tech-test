package com.octaviocenteno.technicaltest.model.client.transactions;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class OBPTransactions {

    private List<OBPTransaction> transactions;
}
