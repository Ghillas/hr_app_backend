package com.hr.api.infrastructure.postgresadapter.projectworkers;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hr.api.domain.projectworkers.ProjectWorkers;
import com.hr.api.domain.projectworkers.ProjectWorkersFetcher;

@Component
public class ProjectWorkersAdapter implements ProjectWorkersFetcher{

    @Autowired
    private ProjectWorkersRepository projectWorkersRepository;

    @Override
    public List<ProjectWorkers> findAll() {
        return StreamSupport
            .stream(projectWorkersRepository.findAll().spliterator(), false)
            .map(ProjectWorkersMapper::fromEntityToDomain)
            .toList();
    }

    @Override
    public void saveProjectWorkers(ProjectWorkers projectWorkers) {
        projectWorkersRepository
            .save(
                ProjectWorkersMapper.fromDomainToEntity(projectWorkers)
            );
    }

    @Override
    public void deleteProjectWorkers(ProjectWorkers projectWorkers) {
        projectWorkersRepository
        .delete(
            ProjectWorkersMapper.fromDomainToEntity(projectWorkers)
        );
    }

    @Override
    public List<ProjectWorkers> findByEmployeeId(Long employeeId) {
        return projectWorkersRepository
            .findByEmployeeId(employeeId)
            .stream()
            .map(ProjectWorkersMapper::fromEntityToDomain)
            .toList();
    }

    @Override
    public List<ProjectWorkers> findByProjectId(Long projectId) {
        return projectWorkersRepository
            .findByProjectId(projectId)
            .stream()
            .map(ProjectWorkersMapper::fromEntityToDomain)
            .toList();
    }

    @Override
    public void deleteByEmployeeIdAndProjectId(Long employeeId, Long projectId) {
        List<ProjectWorkers> projectWorkers = this
            .findAll()
            .stream()
            .filter(item -> item.getEmployee().getId() == employeeId)
            .filter(item -> item.getProject().getId() == projectId)
            .toList();
        if (projectWorkers.size() != 1) {
            System.out.println("Error deleting ProkectWorkes : " + projectWorkers.size());
        } else {
            //projectWorkersRepository.deleteByEmployeeIdAndProjectId(employeeId, projectId);
            projectWorkersRepository.deleteById(projectWorkers.get(0).getId());
        }
    }

}
