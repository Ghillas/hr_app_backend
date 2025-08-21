package com.hr.api.domain.projectworkers;

import java.util.List;

public interface ProjectWorkersFetcher {

    public List<ProjectWorkers> findAll();

    public void saveProjectWorkers(ProjectWorkers projectWorkers);

    public void deleteProjectWorkers(ProjectWorkers projectWorkers);

    public List<ProjectWorkers> findByEmployeeId(Long employeeId);

    public List<ProjectWorkers> findByProjectId(Long projectId);

    public void deleteByEmployeeIdAndProjectId(Long employeeId, Long projectId);

}
