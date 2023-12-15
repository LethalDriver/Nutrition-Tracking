package org.mwdziak.domain;

import jakarta.persistence.*;

public class BlacklistedToken {
    @GeneratedValue
    @Id
    private Integer id;
    private String token;
}
