package com.octaviocenteno.technicaltest.model.client.transactions;

import lombok.Getter;

import java.util.List;

@Getter
public class TransactionMetadata {

    private String narrative;
    private List<String> comments;
    private List<String> tags;
    private List<String> images;
    private String where;
}
