package com.w.volunteer.pojo.importAndExport;

import com.w.volunteer.pojo.common.School;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.ss.usermodel.Cell;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author wcy
 * @Date 2022/4/10 下午3:41
 **/
@Slf4j
public class ExcelUtils {

        /**
         * excel2007及以上版本
         *
         * @param file
         * @return
         * @throws IOException
         */
        static List<School> xlsxImportExcel(MultipartFile file) throws IOException {
            log.info("excel2007及以上版本");
            //获取excel工作簿
            XSSFWorkbook xwb = new XSSFWorkbook(file.getInputStream());
            //获取excel的sheet
            XSSFSheet xssfSheet = xwb.getSheetAt(0);

            if (xssfSheet == null) {
                return null;
            }

            List<School> list = new ArrayList<School>();

            //循环获取excel每一行
            for (int rowNum = 2; rowNum < xssfSheet.getLastRowNum() + 1; rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow == null) {
                    continue;
                }
                School school = new School();
                //循环获取excel每一行的每一列
                for (int cellNum = 0; cellNum < xssfRow.getLastCellNum(); cellNum++) {
                    XSSFCell xssCell = xssfRow.getCell(cellNum);
                    if (xssCell == null) {
                        continue;
                    }

                    switch (cellNum) {
                        case 0:
                            school.setSchoolName(String.valueOf(getXSSFValue(xssCell)));
                            break;
                        case 1:
                            school.setAreaName(String.valueOf(getXSSFValue(xssCell)));
                            break;
                        case 2:
                            //Integer类型需要自行处理
                            school.setVolunteerCode(String.valueOf(getXSSFValue(xssCell)));
                            break;
                            // Double xssfValue = (Double) getXSSFValue(xssCell);
                            // school.setAge(xssfValue.intValue());
                            // break;
                    }
                    log.info("内容：" + getXSSFValue(xssCell));

                }
                //将excel每一行的数据封装到对象,并将对象添加到list
                list.add(school);
            }
            return list;
        }

        /**
         * @param file
         * @return excel2003版本
         * @throws IOException
         */
        static List<School> xlsImportExcel(MultipartFile file) throws IOException {
            log.info("excel2003版本");

            //获取excel工作簿
            Workbook wb = new HSSFWorkbook(file.getInputStream());
            //获取excel的sheet
            Sheet sheet = wb.getSheetAt(0);

            if (sheet == null) {
                return null;
            }

            List<School> list = new ArrayList<School>();

            //循环获取excel每一行
            for (int rowNum = 2; rowNum < sheet.getLastRowNum() + 1; rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row == null) {
                    continue;
                }

                School school = new School();

                //循环获取excel每一行的每一列
                for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
                    Cell cell = row.getCell(cellNum);
                    if (cell == null) {
                        continue;
                    }

                    switch (cellNum) {
                        case 0:
                            school.setSchoolName(String.valueOf(getValue(cell)));
                            break;
                        case 1:
                            school.setAreaName(String.valueOf(getValue(cell)));
                            break;
                        case 2:
                            school.setVolunteerCode(String.valueOf(getValue(cell)));
                            break;
                            // //Integer类型需要自行处理
                            // Double xssfValue = (Double) getValue(cell);
                            // user.setAge(xssfValue.intValue());
                            // break;
                    }
                    log.info("内容：" + getValue(cell));

                }
                list.add(school);    //将excel每一行的数据封装到user对象,并将user对象添加到list
            }
            return list;
        }

        public static List<School> importExcel(MultipartFile file) throws IOException {

            String fileName = file.getOriginalFilename();  //获得上传的excel文件名
            String fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1);  //获取上传的excel文件名后缀
            List<School> schools = null;
            if ("xlsx".equals(fileSuffix)) {
                schools = xlsxImportExcel(file);
            } else if ("xls".equals(fileSuffix)) {
                schools = xlsImportExcel(file);
            }
            return schools;
        }


        /**
         * excel值处理
         *
         * @param hssfCell
         * @return
         */
        public static Object getXSSFValue(XSSFCell hssfCell) {
            Object result = null;
            CellType cellType = hssfCell.getCellType();
            switch (hssfCell.getCellType()) {
                case NUMERIC: //数字
                    result = hssfCell.getNumericCellValue();
                    break;
                case BOOLEAN: //Boolean
                    result = hssfCell.getBooleanCellValue();
                    break;
                case ERROR: //故障
                    result = hssfCell.getErrorCellValue();
                    break;
                case FORMULA: //公式
                    result = hssfCell.getCellFormula();
                    break;
                case BLANK: //空值
                    result = "";
                    break;
                default: //字符串
                    result = hssfCell.getStringCellValue();
            }
            return result;
        }

        /**
         * excel值处理
         *
         * @param hssfCell
         * @return
         */
        public static Object getValue(Cell hssfCell) {
            Object result = null;

            switch (hssfCell.getCellType()) {
                case NUMERIC: //数字
                    result = hssfCell.getNumericCellValue();
                    break;
                case BOOLEAN: //Boolean
                    result = hssfCell.getBooleanCellValue();
                    break;
                case ERROR: //故障
                    result = hssfCell.getErrorCellValue();
                    break;
                case FORMULA: //公式
                    result = hssfCell.getCellFormula();
                    break;
                case BLANK: //空值
                    result = "";
                    break;
                default: //字符串
                    result = hssfCell.getStringCellValue();
            }
            return result;
        }
}
