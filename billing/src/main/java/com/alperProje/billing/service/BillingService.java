package com.alperProje.billing.service;

import com.alperProje.billing.Config.BillingConfig;
import com.alperProje.billing.model.Bill;
import com.alperProje.billing.repository.BillingRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
@Slf4j
public class BillingService {
    private final BillingRepository billingRepository;
    private final BillingConfig billingConfig;

    public void createBillBySaleId(Integer saleId) throws JsonProcessingException {
        String urlForSaleItems = "http://localhost:8081/api/v1/sales/getSaleBySessionId/"
                + saleId; // First I should ask sale Service in order to obtain sessionId
        ResponseEntity<String> response = billingConfig.restTemplate().getForEntity(urlForSaleItems, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode saleItems = root.path("saleItems");
        HashMap<Integer, Integer> modelIdsAndQuantities = new HashMap<>();
        for(int i=0; i< saleItems.size(); i++){
            modelIdsAndQuantities.put(saleItems.get(i).get("modelId").intValue(),
                    saleItems.get(i).get("modelQuantity").intValue());
        }
        String urlForItemPrices = "http://localhost:8080/api/v1/products/getPricesByProductIds/"
                + modelIdsAndQuantities.keySet().toString().substring(1, modelIdsAndQuantities.keySet().toString().length() - 1); // First I should ask sale Service in order to obtain sessionId
        log.info("URL for item prices" + modelIdsAndQuantities.keySet().toString().substring(1, modelIdsAndQuantities.keySet().toString().length() - 1));
        ResponseEntity<String> responsePrice = billingConfig.restTemplate().getForEntity(urlForItemPrices, String.class);
        log.info("item prices: " + responsePrice.getBody());
        List listPrices = new ObjectMapper().readValue(responsePrice.getBody(), List.class); // list of object
        List<Integer> integerList = new ArrayList<Integer>(listPrices);
        // now we have model ids we can ask for prices
        Integer priceOfSale = getTotalPrice(new ArrayList<Integer>(modelIdsAndQuantities.values()), integerList);
        log.info("Calculated price:" + priceOfSale);
        //
        Bill bill = Bill.builder().SaleId(saleId).isComplete(false).totalAmount(priceOfSale).build();
        billingRepository.save(bill);
    }

    public List<Bill> findAll(){
        return billingRepository.findAll();
    }

    private Integer getTotalPrice(List<Integer> quantities, List<Integer> prices){
        return IntStream.range(0, quantities.size()).map(i-> quantities.get(i) * prices.get(i)).sum();
    }


}
