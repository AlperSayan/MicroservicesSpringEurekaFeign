package com.alperProje.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class SaleRequest {
    @JsonProperty("soldTo")
    private String soldTo;
    @JsonProperty("soldItemIds")
    private List<Integer> itemIds;
    @JsonProperty("soldItemQuantities")
    private List<Integer> itemQuantities;
}
