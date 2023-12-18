package org.mwdziak.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="_blacklisted_tokens")
public class BlacklistedToken {
    @GeneratedValue
    @Id
    private Integer id;
    private String token;
}
