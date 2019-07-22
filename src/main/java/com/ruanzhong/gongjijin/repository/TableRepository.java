package com.ruanzhong.gongjijin.repository;

import com.ruanzhong.gongjijin.domain.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TableRepository extends JpaRepository<Table, Integer> {
    @Query(value = "select * from _table where table_name = ?1 and sub_project_id=?2",nativeQuery = true)
    Table findByTableName(String tableName,Integer subProjectId);
}
