package com.octaviocenteno.technicaltest.client;

import com.octaviocenteno.technicaltest.model.client.transactions.OBPTransactions;
import feign.Headers;
import feign.Param;
import feign.RequestLine;


@Headers("Accept: application/json")
public interface OBPTransactionsClient {

    @RequestLine("GET /banks/{bankId}/accounts/{account-id}/{view-id}/transactions")
    OBPTransactions getTransactions(@Param("bankId") String bankId,
                                    @Param("account-id") String accountId, @Param("view-id") String viewId);
}
