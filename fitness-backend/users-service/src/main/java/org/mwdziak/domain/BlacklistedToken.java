package org.mwdziak.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="_blacklisted_tokens")
@NoArgsConstructor
public class BlacklistedToken {
    @GeneratedValue
    @Id
    private Integer id;
    private String token;

    public BlacklistedToken(String token) {
        this.token = token;
    }

}
