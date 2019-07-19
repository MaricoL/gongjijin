package com.ruanzhong.gongjijin;

import com.ruanzhong.gongjijin.domain.Field;
import com.ruanzhong.gongjijin.domain.Project;
import com.ruanzhong.gongjijin.domain.SubProject;
import com.ruanzhong.gongjijin.domain.Table;
import com.ruanzhong.gongjijin.repository.FieldRepository;
import com.ruanzhong.gongjijin.repository.ProjectRepository;
import com.ruanzhong.gongjijin.repository.SubProjectRepository;
import com.ruanzhong.gongjijin.repository.TableRepository;
import jdk.nashorn.internal.runtime.options.Option;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.Example;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

@SpringBootApplication
public class GongjijinApplication {


    public static void main(String[] args) throws Exception {

        SpringApplication.run(GongjijinApplication.class, args);
//
//        ConfigurableApplicationContext context = new SpringApplicationBuilder(GongjijinApplication.class)
//                .web(WebApplicationType.SERVLET)
//                .run(args);


//        readExcel(context);

//        context.close();

    }

    private static void readExcel(ConfigurableApplicationContext context) throws Exception {
        ProjectRepository projectRepository = context.getBean(ProjectRepository.class);
        SubProjectRepository subProjectRepository = context.getBean(SubProjectRepository.class);
        TableRepository tableRepository = context.getBean(TableRepository.class);
        FieldRepository fieldRepository = context.getBean(FieldRepository.class);

        //excel文件路径
        String directoryPath = "C:\\Users\\Lin\\Desktop\\1";
        File directory = new File(directoryPath);
        File[] xlsFiles = directory.listFiles((dir, name) -> name.endsWith(".xls"));
        for (File xlsFile : xlsFiles) {

            Workbook wb;
            if (xlsFile.isFile() && xlsFile.exists()) {   //判断文件是否存在
                FileInputStream fis = new FileInputStream(xlsFile);   //文件流对象
                wb = new HSSFWorkbook(fis);

                //开始解析
                for (int i = 0; i < wb.getNumberOfSheets(); i++) {
                    Sheet sheet = wb.getSheetAt(i);
                    // 表名
                    String tableName = sheet.getSheetName();

                    // 第 5 行，第 5 列
                    Cell descriptionCell = sheet.getRow(4).getCell(4);
                    // 表描述信息
                    String description = descriptionCell.getStringCellValue();
                    // 第 3 行，第 5 列
                    Cell cell = sheet.getRow(2).getCell(4);
                    // 获得项目名称
                    String projectName = cell.getStringCellValue();
                    // 第 4 行，第 5 列
                    Cell subCell = sheet.getRow(3).getCell(4);
                    // 获得子项目名称
                    String subProjectName = subCell.getStringCellValue();

                    // 第 4 行，第 6 列
                    Cell  recordDateCell = sheet.getRow(3).getCell(10);
                    // 获取填写日期
//                    String recordDate = recordDateCell.getStringCellValue();
                    Date recordDate = recordDateCell.getDateCellValue();


                    // 第 3 行，第 6 列
                    Cell  managerCell = sheet.getRow(2).getCell(10);
                    String manager = managerCell.getStringCellValue();

                    // 根据projectName获取该projectId
                    Project project = projectRepository.findByProjectName(projectName);

                    if (project == null) {
                        project = new Project(projectName);
                        projectRepository.save(project);
                    }

                    SubProject subProject  = subProjectRepository.findBySubProjectNameAndProjectId(subProjectName,project.getId());

                    if (subProject == null && !"".equals(subProjectName.trim())) {
                        subProject = new SubProject(subProjectName, project);
                        subProjectRepository.save(subProject);
                    }

                    Table table = new Table(tableName, description, subProject,manager,recordDate);
                    //
                    tableRepository.save(table);

                    // 字段插入
//                    insertFields(wb,sheet,tableRepository);
                    // 判断是否为“同XXXX”
                    String firstInfo = sheet.getRow(6).getCell(0).getStringCellValue();
                    System.out.println(firstInfo);
                    while (firstInfo.trim().startsWith("同")) {
                        if (firstInfo.trim().substring(1).equals(sheet.getSheetName())) {
                            break;
                        } else {
                            String likeTableName = firstInfo.substring(1).trim();
                            System.out.println(likeTableName);
                            sheet = wb.getSheet(likeTableName);
                            firstInfo = sheet.getRow(6).getCell(0).getStringCellValue();
                        }
                    }

                    int physicalNumberOfCells = sheet.getRow(0).getPhysicalNumberOfCells();
                    int j = 7;
                    Row row = sheet.getRow(j);
                    while (row != null) {
                        Cell firstCellOfRow = row.getCell(0);
                        if (firstCellOfRow == null) {
                            break;
                        }

                        // 创建Field对象
                        Field field = new Field();
                        field.setSequence((int) row.getCell(0).getNumericCellValue());
                        field.setFieldName(row.getCell(1).getStringCellValue());
                        if (row.getCell(2).getCellType() == 0) {
                            field.setDigits(String.valueOf(row.getCell(2).getNumericCellValue()));
                        }else{
                            field.setDigits(row.getCell(2).getStringCellValue());
                        }
                        field.setLength(String.valueOf(row.getCell(3).getCellType()));
                        if (row.getCell(4).getCellType() == 0) {
                            field.setDigits(String.valueOf(row.getCell(4).getNumericCellValue()));
                        }else{
                            field.setDigits(row.getCell(4).getStringCellValue());
                        }
                        field.setKeyType(row.getCell(5).getStringCellValue());
                        if (row.getCell(6).getCellType() == 0) {
                            field.setDigits(String.valueOf(row.getCell(6).getNumericCellValue()));
                        }else{
                            field.setDigits(row.getCell(6).getStringCellValue());
                        }
                        if (row.getCell(7) == null) {
                            field.setIsNull("");
                        }else{
                            field.setIsNull(row.getCell(7).getStringCellValue());
                        }

                        if (row.getCell(8) == null) {
                            field.setDictionaryName("");
                        }else{
                            field.setDictionaryName(row.getCell(8).getStringCellValue());
                        }
                        if (row.getCell(9) == null) {
                            field.setDescription("");
                        }else{
                            field.setDescription(row.getCell(9).getStringCellValue());
                        }


                        field.setComments(row.getCell(10).getStringCellValue());

                        // 先从 field表 中查找是否有该字段
                        Example<Field> fieldExample = Example.of(field);
                        Optional<Field> fieldOpt = fieldRepository.findOne(fieldExample);
                        if (fieldOpt.isPresent()) {
                            fieldOpt.get().getTables().add(table);
                            fieldRepository.save(fieldOpt.get());
                        }else{
                            field.setTables(Collections.singletonList(table));
                            fieldRepository.save(field);
                        }

                        j++;
                        row = sheet.getRow(j);
                    }

                    System.out.println("插入" + tableName + " 成功");


                }
            }
        }
    }

}
