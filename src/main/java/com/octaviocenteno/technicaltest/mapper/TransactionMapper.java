package com.octaviocenteno.technicaltest.mapper;

import com.octaviocenteno.technicaltest.model.client.transactions.OBPTransaction;
import com.octaviocenteno.technicaltest.model.rest.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(target = "accountId", source = "thisAccount.id")
    @Mapping(target = "counterpartyAccount", source = "otherAccount.number")
    @Mapping(target = "counterpartyName", source = "otherAccount.holder.name")
    @Mapping(target = "counterPartyLogoPath", source = "otherAccount.metadata.imageUrl")
    @Mapping(target = "instructedAmount", source = "details.value.amount")
    @Mapping(target = "instructedCurrency", source = "details.value.currency")
    @Mapping(target = "transactionAmount", source = "details.value.amount")
    @Mapping(target = "transactionCurrency", source = "details.value.currency")
    @Mapping(target = "transactionType", source = "details.type")
    @Mapping(target = "description", source = "details.description")
    Transaction toTransaction(OBPTransaction obpTransaction);
}
