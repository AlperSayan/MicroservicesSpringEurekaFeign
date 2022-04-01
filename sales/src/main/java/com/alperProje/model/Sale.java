package com.alperProje.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
@Entity
public class Sale {
    @Id
    @SequenceGenerator(name = "sale_id_generator", sequenceName = "sale_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sale_id_sequence")
    public Integer id;
    public Integer sessionId;
    public String soldTo;
}
