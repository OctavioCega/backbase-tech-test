package com.octaviocenteno.technicaltest.model.client.transactions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class AccountMetadata {

    @JsonProperty("public_alias")
    private String publicAlias;

    @JsonProperty("private_alias")
    private String privateAlias;

    @JsonProperty("more_info")
    private String moreInfo;

    @JsonProperty("URL")
    private String url;

    @JsonProperty("image_URL")
    private String imageUrl;

    @JsonProperty("open_corporates_URL")
    private String openCorporatesUrl;

    @JsonProperty("corporate_location")
    private String corporateLocation;

    @JsonProperty("physical_location")
    private String physicalLocation;
}
