package com.octaviocenteno.technicaltest;

import com.octaviocenteno.technicaltest.client.OBPTransactionsClient;
import com.octaviocenteno.technicaltest.mapper.TransactionMapper;
import com.octaviocenteno.technicaltest.mapper.TransactionMapperImpl;
import com.octaviocenteno.technicaltest.model.client.transactions.Balance;
import com.octaviocenteno.technicaltest.model.client.transactions.OBPTransaction;
import com.octaviocenteno.technicaltest.model.client.transactions.OBPTransactions;
import com.octaviocenteno.technicaltest.model.client.transactions.TransactionDetails;
import com.octaviocenteno.technicaltest.model.rest.Transaction;
import com.octaviocenteno.technicaltest.model.rest.TransactionsTotal;
import com.octaviocenteno.technicaltest.service.TransactionService;
import com.octaviocenteno.technicaltest.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    @Spy
    private TransactionMapper transactionMapper = Mappers.getMapper(TransactionMapper.class);

    @Mock
    private OBPTransactionsClient obpTransactionsClient;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    private static final String BANK_ID = "bankId";
    private static final String ACCOUNT_ID = "accountId";
    private static final String TYPE_ID = "typeId";
    private static final BigDecimal VALUE = BigDecimal.ONE;
    @Test
    void getTransactions_validRequest(){
        Mockito.when(
                obpTransactionsClient
                        .getTransactions(Mockito.anyString(),Mockito.anyString(),Mockito.anyString()))
                .thenReturn(myValidTransactionResponse());

        List<Transaction> transactions = transactionService.getTransactions(BANK_ID, ACCOUNT_ID, TYPE_ID);

        Assertions.assertEquals(1,transactions.size());
    }

    @Test
    void getTransactionsTotal_validRequest(){
        Mockito.when(
                obpTransactionsClient
                        .getTransactions(Mockito.anyString(),Mockito.anyString(),Mockito.anyString()))
                .thenReturn(myValidTransactionResponse());

        TransactionsTotal transactionsTotal = transactionService.getTotalAmountByTransactionType(BANK_ID, ACCOUNT_ID, TYPE_ID);

        Assertions.assertEquals(VALUE, transactionsTotal.getTotal());
    }

    private OBPTransactions myValidTransactionResponse(){
        OBPTransactions obpTransactions = new OBPTransactions();
        List<OBPTransaction> obpTransactionList = new ArrayList<>();
        OBPTransaction obpTransaction = new OBPTransaction();
        TransactionDetails transactionDetails = new TransactionDetails();
        transactionDetails.setType(TYPE_ID);
        Balance balance = new Balance();
        balance.setAmount(VALUE);

        transactionDetails.setValue(balance);
        obpTransaction.setDetails(transactionDetails);
        obpTransactionList.add(obpTransaction);
        obpTransactions.setTransactions(obpTransactionList);

        return obpTransactions;
    }
}
