package com.alperProje.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
@Entity
public class SaleItem {
    @Id
    @SequenceGenerator(name = "sale_item_id_generator", sequenceName = "sale_item_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sale_item_id_sequence")
    private Integer id;
    private Integer sessionId;
    private Integer modelId;
    private Integer modelQuantity;
}
