package com.octaviocenteno.technicaltest.service;

import com.octaviocenteno.technicaltest.model.rest.Transaction;
import com.octaviocenteno.technicaltest.model.rest.TransactionsTotal;

import java.util.List;

public interface TransactionService {

    /**
     * Retrieves a list of public transactions of an accountId by bank. Optional filtering by transactionType
     * @param bankId            the bankId where the account belongs
     * @param accountId         the accountId to search public transactions
     * @param transactionType   the optional transactionType to filter by
     * @return                  a {@link List<Transaction>}. Can be empty if no transaction found.
     */
    List<Transaction> getTransactions(String bankId, String accountId, String transactionType);

    /**
     * Retrieves the total amount by adding the value from the public transactions of an accountId by bank.
     * Requires the transactionType to operate.
     * @param bankId            the bankId where the account belongs
     * @param accountId         the accountId to search public transactions
     * @param transactionType   the transactionType to filter by
     * @return                  a {@link TransactionsTotal}. Wrapping the total.
     */
    TransactionsTotal getTotalAmountByTransactionType(String bankId, String accountId, String transactionType);
}
