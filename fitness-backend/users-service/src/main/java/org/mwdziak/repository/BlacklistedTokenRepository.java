package org.mwdziak.repository;

import org.mwdziak.domain.BlacklistedToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlacklistedTokenRepository extends JpaRepository<BlacklistedToken, Long> {
    public boolean existsByToken(String token);
}
