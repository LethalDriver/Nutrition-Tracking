package org.mwdziak.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.List;

import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@RequiredArgsConstructor
public class Food {
    @Id
    private Long fdc_id;
    private String description;
    @OneToMany
    private List<Nutrient> nutrients;

}
