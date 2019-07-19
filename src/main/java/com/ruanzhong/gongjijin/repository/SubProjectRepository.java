package com.ruanzhong.gongjijin.repository;

import com.ruanzhong.gongjijin.domain.SubProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SubProjectRepository extends JpaRepository<SubProject, Integer> {
    @Query(value = "select * from subproject s where s.sub_project_name = ?1 and s.project_id =?2", nativeQuery = true)
    SubProject findBySubProjectNameAndProjectId(String subProjectName, Integer projectId);
}
