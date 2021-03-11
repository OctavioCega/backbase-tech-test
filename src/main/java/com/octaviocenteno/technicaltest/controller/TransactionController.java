package com.octaviocenteno.technicaltest.controller;

import com.octaviocenteno.technicaltest.client.OBPTransactionsClient;
import com.octaviocenteno.technicaltest.mapper.TransactionMapper;
import com.octaviocenteno.technicaltest.model.rest.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@RequestMapping("transactions")
@RestController
public class TransactionController {

    private final OBPTransactionsClient obpTransactionsClient;
    private final TransactionMapper transactionMapper;
    /**
     * Retrieves the requested transactions
     * @return
     */
    @GetMapping()
    public List<Transaction> getTransactions(@RequestParam(required = false) String transactionType){
        return obpTransactionsClient.getTransactions("rbs", "savings-kids-john", "public")
                        .getTransactions().stream()
                .filter(obpTransaction -> transactionType == null ||
                        obpTransaction.getDetails()!=null &&
                                obpTransaction.getDetails().getType().equals(transactionType))
                .map(transactionMapper::toTransaction)
                .collect(Collectors.toList());
    }

    @GetMapping("total")
    public BigDecimal getTotalAmountByTransactionType(@RequestParam(required = true) String transactionType){
        return obpTransactionsClient.getTransactions("rbs", "savings-kids-john", "public")
                .getTransactions().stream()
                .filter(obpTransaction ->
                        obpTransaction.getDetails()!=null &&
                                obpTransaction.getDetails().getType().equals(transactionType))
                .map(obpTransaction -> obpTransaction.getDetails().getValue().getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
