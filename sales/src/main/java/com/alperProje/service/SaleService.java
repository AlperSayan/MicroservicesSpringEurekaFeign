package com.alperProje.service;

import com.alperProje.model.Sale;
import com.alperProje.model.SaleItem;
import com.alperProje.repository.SaleItemRepository;
import com.alperProje.repository.SaleRepository;
import com.alperProje.request.SaleRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@AllArgsConstructor
public class SaleService {
    private final SaleRepository saleRepository;
    private final SaleItemRepository saleItemRepository;
    private final RestTemplate saleConfig;


    public void createSale(SaleRequest saleRequest){
        int random = new Random().nextInt(1000000000); // Bu olmaz bunu biliyorum :D
        Sale sale = Sale.builder().
                sessionId(random).
                soldTo(saleRequest.getSoldTo()).
                build();
        saleRepository.save(sale);
        for (int i=0; i < saleRequest.getItemIds().size();i++){
            Integer saleId = saleRequest.getItemIds().get(i);
            Integer saleQuantity = saleRequest.getItemQuantities().get(i);
            // Burada bu kadar ürün var mı ya da ürün var mı diye kontrol yapılabilir
            SaleItem saleItem = SaleItem.builder().
                    sessionId(random).
                    modelId(saleId).
                    modelQuantity(saleQuantity).
                    build();
            saleItemRepository.save(saleItem);
        }
    }

    public HashMap<Integer, Object> getAllSales(){
        HashMap<Integer, Object> allMap = new HashMap<Integer, Object>();
        HashMap<String, Object> map = new HashMap<String, Object>();
        List<Integer> sessionIds = saleRepository.getAllSessionId();
        List<String> soldTos = saleRepository.getAllSoldTo();
        for(int i=0; i < sessionIds.size(); i++){
            Integer sessionId = sessionIds.get(i);
            String soldTo = soldTos.get(i);
            List<SaleItem> saleItems = saleItemRepository.getSaleItemBySessionId(sessionId);
            map.put("sessionID", sessionId);
            map.put("saleItems", saleItems);
            map.put("soldTo", soldTo);
            allMap.put(i, map);
        }


        return allMap;
    }

    public HashMap<String, Object> getSaleById(Integer sessionId){
        String soldTo = saleRepository.getSoldToBySessionId(sessionId);
        List<SaleItem> saleItems = saleItemRepository.getSaleItemBySessionId(sessionId);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("saleItems", saleItems);
        map.put("soldTo", soldTo);
        return map;
    }
}
