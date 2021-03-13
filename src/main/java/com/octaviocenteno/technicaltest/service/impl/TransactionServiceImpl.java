package com.octaviocenteno.technicaltest.service.impl;

import com.octaviocenteno.technicaltest.client.OBPTransactionsClient;
import com.octaviocenteno.technicaltest.mapper.TransactionMapper;
import com.octaviocenteno.technicaltest.model.rest.Transaction;
import com.octaviocenteno.technicaltest.model.rest.TransactionsTotal;
import com.octaviocenteno.technicaltest.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {

    private final OBPTransactionsClient obpTransactionsClient;
    private final TransactionMapper transactionMapper;

    private static final String DEFAULT_VIEW_ID = "public";
    @Override
    public List<Transaction> getTransactions(String bankId, String accountId, String transactionType) {

        return obpTransactionsClient.getTransactions(bankId, accountId, DEFAULT_VIEW_ID)
                        .getTransactions().stream()
                .filter(obpTransaction -> transactionType == null ||
                        obpTransaction.getDetails()!=null &&
                                obpTransaction.getDetails().getType().equals(transactionType))
                .map(transactionMapper::toTransaction)
                .collect(Collectors.toList());
    }

    @Override
    public TransactionsTotal getTotalAmountByTransactionType(String bankId, String accountId, String transactionType) {
        log.info("Getting info for: %s, %s, %s",bankId, accountId, transactionType);
        TransactionsTotal transactionsTotal = new TransactionsTotal();
        transactionsTotal.setTotal(obpTransactionsClient.getTransactions(bankId, accountId, DEFAULT_VIEW_ID)
                .getTransactions().stream()
                .filter(obpTransaction ->
                        obpTransaction.getDetails() != null &&
                                obpTransaction.getDetails().getType().equals(transactionType))
                .map(obpTransaction -> obpTransaction.getDetails().getValue().getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        return transactionsTotal;

    }
}
