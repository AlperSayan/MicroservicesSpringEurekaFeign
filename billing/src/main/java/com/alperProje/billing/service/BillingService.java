package com.alperProje.billing.service;

import com.alperProje.billing.dto.SaleDTO;
import com.alperProje.billing.dto.SaleItem;
import com.alperProje.billing.feign.ProductServiceFeignClient;
import com.alperProje.billing.feign.SaleServiceFeignClient;
import com.alperProje.billing.model.Bill;
import com.alperProje.billing.repository.BillingRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
@Slf4j
public class BillingService {
    private final BillingRepository billingRepository;
    private final ProductServiceFeignClient productServiceFeignClient;
    private final SaleServiceFeignClient saleServiceFeignClient;

    public void createBillBySaleId(Integer saleId){
        SaleDTO saleDTO = saleServiceFeignClient.getSaleBySessionId(String.valueOf(saleId));
        List<Integer> modelIds = saleDTO.getSaleItems().stream().map(SaleItem::getModelId).collect(Collectors.toList());
        List<Integer> saleQuantities = saleDTO.getSaleItems().stream().map(SaleItem::getModelQuantity).collect(Collectors.toList());
        List<Integer> modelPrices = productServiceFeignClient.getProductPricesByProductIds(modelIds.toString().substring(1, modelIds.toString().length() - 1));
        Integer priceOfSale = getTotalPrice(saleQuantities, modelPrices);
        Bill bill = Bill.builder().SaleId(saleId).isComplete(false).totalAmount(priceOfSale).build();
        billingRepository.save(bill);
    }

    public List<Bill> findAll(){
        return billingRepository.findAll();
    }

    private Integer getTotalPrice(List<Integer> quantities, List<Integer> prices){
        return IntStream.range(0, quantities.size()).
                map(i-> quantities.get(i) * prices.get(i)).
                sum();
    }


}
