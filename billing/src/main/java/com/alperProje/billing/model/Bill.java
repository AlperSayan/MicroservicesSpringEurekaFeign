package com.alperProje.billing.model;

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
public class Bill {
    @Id
    @SequenceGenerator(name = "bill_id_generator", sequenceName = "bill_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bill_id_sequence")
    public Integer id;
    public Integer SaleId;
    public Integer totalAmount;
    public boolean isComplete;

}
