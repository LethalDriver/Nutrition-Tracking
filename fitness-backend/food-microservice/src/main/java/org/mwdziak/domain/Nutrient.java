package org.mwdziak.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@RequiredArgsConstructor
public class Nutrient {
    @Id
    private Long id;
    private String name;
    private String unitName;
}
