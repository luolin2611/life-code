package cn.lifecode.frameworkcore.util;

import com.alibaba.fastjson.JSON;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Excel操作类
 * 1.import org.apache.poi.ss.usermodel.Workbook,对应Excel文档；
 * <p>
 * 　　2.import org.apache.poi.hssf.usermodel.HSSFWorkbook，对应xls格式的Excel文档；
 * <p>
 * 　　3.import org.apache.poi.xssf.usermodel.XSSFWorkbook，对应xlsx格式的Excel文档；
 * <p>
 * 　　4.import org.apache.poi.ss.usermodel.Sheet，对应Excel文档中的一个sheet；
 * <p>
 * 　　5.import org.apache.poi.ss.usermodel.Row，对应一个sheet中的一行；
 * <p>
 * 　　6.import org.apache.poi.ss.usermodel.Cell，对应一个单元格。
 *
 * @author luolin
 * @date 2021-01-18 16:09:53
 */
public class ExcelPoiUtil {
    /**
     * 返回的格式为 list [ map {  }  ]
     * [
     * {
     * "sheet1": [
     * {"colName1": "value"},
     * {"colName2": "value"},
     * {"colName3": "value"},
     * ]
     * },
     * {
     * "sheet2": [
     * {"colName1": "value"},
     * {"colName2": "value"},
     * {"colName3": "value"},
     * ]
     * }
     * ]
     *
     * @param filePath 文件路径
     * @param columns  列名
     * @return
     */
    public static List<Map<String, List<Map<String, String>>>> getExcelToList(String filePath, String[] columns) {
        Workbook excel = readExcel(filePath);
        //最外层List 存放sheet
        List<Map<String, List<Map<String, String>>>> outerList = null;
        // sheetMap对象
        Map<String, List<Map<String, String>>> sheetMap = null;
        if (excel != null) {
            outerList = new ArrayList<>();
            Sheet sheet;
            //内存list存放每个sheet下的map对象
            List<Map<String, String>> list;
            Row row;
            String cellData;
            sheetMap = new HashMap<>();
            //遍历每个sheet
            for (int i = 0; i < excel.getNumberOfSheets(); i++) {
                //获取sheet
                sheet = excel.getSheetAt(i);
                //用来存放表中数据
                list = new ArrayList<>();
                //获取最大行数
                int rownum = sheet.getPhysicalNumberOfRows();
                //获取第一行
                row = sheet.getRow(0);
                //获取最大列数
                int colnum = row.getPhysicalNumberOfCells();
                //遍历每行
                for (int j = 1; j < rownum; j++) {
                    Map<String, String> map = new LinkedHashMap<String, String>();
                    row = sheet.getRow(j);
                    if (row != null) {
                        //遍历每个单元格
                        for (int k = 0; k < colnum; k++) {
                            cellData = (String) getCellFormatValue(row.getCell(k));
                            map.put(columns[k], cellData);
                        }
                    } else {
                        break;
                    }
                    list.add(map);
                }
                sheetMap.put(sheet.getSheetName(), list);
            }
            outerList.add(sheetMap);
        }
        return outerList;
    }


    /**
     * 读取excel
     */
    public static Workbook readExcel(String filePath) {
        Workbook wb = null;
        if (filePath == null) {
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if (".xls".equals(extString)) {
                return new HSSFWorkbook(is);
            } else if (".xlsx".equals(extString)) {
                return new XSSFWorkbook(is);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static Object getCellFormatValue(Cell cell) {
        Object cellValue = null;
        if (cell != null) {
            //判断cell类型
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC: {
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                }
                case Cell.CELL_TYPE_FORMULA: {
                    //判断cell是否为日期格式
                    if (cell instanceof Date) {
                        //转换为日期格式YYYY-mm-dd
                        cellValue = cell.getDateCellValue();
                    } else {
                        //数字
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case Cell.CELL_TYPE_STRING: {
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        } else {
            cellValue = "";
        }
        return cellValue;
    }

    public static void main(String[] args) {
        String[] colums = new String[]{"时间", "大类", "金额", "币种", "备注"};
        List list = getExcelToList("/Users/luolin/Downloads/网易有钱记账数据的副本.xlsx", colums);
        String str = JSON.toJSONString(list);
        System.out.println(str);
    }
}
