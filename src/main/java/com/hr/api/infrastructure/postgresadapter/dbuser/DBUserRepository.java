package com.hr.api.infrastructure.postgresadapter.dbuser;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DBUserRepository extends JpaRepository<DBUser, Long>{

    public Optional<DBUser> findByUsername(String username);
    
}
