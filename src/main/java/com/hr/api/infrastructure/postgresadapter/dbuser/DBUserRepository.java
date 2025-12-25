package com.hr.api.infrastructure.postgresadapter.dbuser;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DBUserRepository extends JpaRepository<DBUser, Long>{

    public DBUser findByUsername(String username);
    
}
