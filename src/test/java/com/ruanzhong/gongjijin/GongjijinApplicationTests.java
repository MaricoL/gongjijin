package com.ruanzhong.gongjijin;

import com.ruanzhong.gongjijin.domain.Field;
import com.ruanzhong.gongjijin.domain.Table;
import com.ruanzhong.gongjijin.repository.FieldRepository;
import com.ruanzhong.gongjijin.repository.TableRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GongjijinApplicationTests {


    @Autowired
    private FieldRepository fieldRepository;

    @Autowired
    private TableRepository tableRepository;

    @Test
    public void contextLoads() {

        Table table = tableRepository.findById(1).get();
        Field field = new Field();
        field.setId(88);
        field.setTables(Arrays.asList(table));
        fieldRepository.save(field);

    }

}
