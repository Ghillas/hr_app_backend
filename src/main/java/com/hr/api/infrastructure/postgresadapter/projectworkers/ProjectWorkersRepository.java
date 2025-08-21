package com.hr.api.infrastructure.postgresadapter.projectworkers;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectWorkersRepository extends JpaRepository<ProjectWorkersEntity, Long>{

    public void deleteByEmployeeIdAndProjectId(Long employeeId, Long projectId);

    public List<ProjectWorkersEntity> findByEmployeeId(Long employeeId);

    public List<ProjectWorkersEntity> findByProjectId(Long projectId);
    
}
