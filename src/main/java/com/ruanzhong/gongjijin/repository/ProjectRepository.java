package com.ruanzhong.gongjijin.repository;

import com.ruanzhong.gongjijin.domain.Project;
import com.ruanzhong.gongjijin.domain.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    @Query(value = "select * from project where project_name= ?1",nativeQuery = true)
    Project findByProjectName(String projectName);
}
