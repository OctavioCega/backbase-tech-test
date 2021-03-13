package com.octaviocenteno.technicaltest.controller;

import com.octaviocenteno.technicaltest.model.rest.Transaction;
import com.octaviocenteno.technicaltest.model.rest.TransactionsTotal;
import com.octaviocenteno.technicaltest.service.TransactionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("banks")
@RestController
public class TransactionController {

    private final TransactionService transactionService;

    @ApiOperation(value = "Retrieves all public transactions from requested bankId and accountId. Empty array if" +
            "no transaction was found. Can filter by transactionType")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK response"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 500, message = "Unknown internal error")
    })
    @GetMapping("{bankId}/accounts/{accountId}/transactions")
    public List<Transaction> getTransactions(
            @ApiParam(value = "Bank id to search the account")
            @PathVariable(required = false) String bankId,
            @ApiParam(value = "AccountId to search public transactions")
            @PathVariable(required = false) String accountId,
            @ApiParam(required = false, value = "Optional query param to filter transactions by type")
            @RequestParam(required = false) String transactionType){

        return transactionService.getTransactions(bankId, accountId, transactionType);
    }

    @ApiOperation(value = "Retrieves all transactions from requested BANK_ID , ACCOUNT_ID and TYPE_ID. Empty array if" +
            "no transaction was found")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK response"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 500, message = "Unknown internal error")
    })
    @GetMapping("{bankId}/accounts/{accountId}/transactions/type/{transactionType}/total")
    public TransactionsTotal getTotalAmountByTransactionType(
            @ApiParam(value = "Bank id to search the account")
            @PathVariable(required = true) String bankId,
            @ApiParam(value = "AccountId to search public transactions")
            @PathVariable(required = true) String accountId,
            @ApiParam(required = true, value = "Required query param to filter transactions by type")
            @PathVariable(required = true) String transactionType){
        return transactionService.getTotalAmountByTransactionType(bankId, accountId, transactionType);

    }

}
