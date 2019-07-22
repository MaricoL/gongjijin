package com.ruanzhong.gongjijin.repository;

import com.ruanzhong.gongjijin.domain.Field;
import com.ruanzhong.gongjijin.domain.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FieldRepository extends JpaRepository<Field, Integer> {
    @Query(value = "select f.*\n" +
            "from field f,table_field tf\n" +
            "where tf.field_id = f.id and tf.table_id = ?1 order by f.sequence",nativeQuery = true)
    List<Field> getFieldsByTableId(Integer tableId);
}
