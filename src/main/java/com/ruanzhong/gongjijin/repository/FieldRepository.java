package com.ruanzhong.gongjijin.repository;

import com.ruanzhong.gongjijin.domain.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FieldRepository extends JpaRepository<Field, Integer> {
    @Query(value = "select f.*\n" +
            "from field f,table_field tf\n" +
            "where tf.field_id = f.id and tf.table_id = ?1 order by f.sequence",nativeQuery = true)
    List<Field> getFieldsByTableId(Integer tableId);


    @Query(value = "select * from field\n" +
            "where comments  like %?1%  \n" +
            "and comments not like %?2%  \n" +
            "and comments not like %?3% \n" +
            "and comments not like %?4% \n" +
            "and comments not like %?5% \n" +
            "and comments not like %?6% ",nativeQuery = true)
    List<Field> findLikeString(String 用, String 用户, String 商用, String 已用, String 领用, String 费用);
}
