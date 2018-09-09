package cn.file.xlsFile;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XLSFileUtils {
    private static final Logger logger = LoggerFactory.getLogger(XLSFileUtils.class);

    public XLSBo readExcel(File file) {
        XLSBo xlsBo = new XLSBo();
        Map<String, List<String>> mapDATA = new HashMap<String, List<String>>();
        List<Map<String, List<String>>> listConMap = new ArrayList<Map<String, List<String>>>();
        int column = 0;
        List<Label> labelList = new ArrayList<Label>();
        Workbook wb = null;
        try {
            // 创建输入流，读取Excel
            InputStream is = new FileInputStream(file.getAbsolutePath());
            // jxl提供的Workbook类
            wb = Workbook.getWorkbook(is);
            // Excel的页签数量
            int sheet_size = wb.getNumberOfSheets();
            for (int index = 0; index < sheet_size; index++) {
                // 每个页签创建一个Sheet对象
                Sheet sheet = wb.getSheet(index);
                // sheet.getRows()返回该页的总行数
                column = sheet.getColumns();
                List<String> keyList = new ArrayList<String>();
                System.out.println(column);
                for (int i = 0; i < sheet.getRows(); i++) {
                    // sheet.getColumns()返回该页的总列数
                    for (int j = 0; j < sheet.getColumns(); j++) {
                        String cellinfo = sheet.getCell(j, i).getContents();
                        if (i == 0) {
                            keyList.add(cellinfo);
//                            System.out.println(key);cellinfo
                            mapDATA.put(keyList.get(j), new ArrayList<String>());
                        } else {
                            System.out.println(cellinfo);
                            List valueList = mapDATA.get(keyList.get(j));
                            valueList.add(cellinfo);
                            mapDATA.put(keyList.get(j), valueList);
                        }
                        Label label = new Label(i, j ,cellinfo);
                        labelList.add(label);
                    }

                }
            }
            xlsBo.setColumn(column);
            listConMap.add(mapDATA);
            xlsBo.setMapDATA(listConMap);
            xlsBo.setLabelList(labelList);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (wb != null) {wb.close();}
        }
        return xlsBo;
    }
    public void writeXLSFile(String file, String sheetName, int sheetNum, XLSBo xlsBo) {
        if (xlsBo == null || file == null) {
            return;
        }
        WritableWorkbook book = null;
        try {
            // 打开文件
            book = Workbook.createWorkbook(new File(file));
            // 生成名为“sheet1”的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet(sheetName, sheetNum);
            // 在Label对象的构造子中指名单元格位置是第一列第一行(0,0),单元格内容为string
//            Label label = new Label(0, 0, "string");
            // 将定义好的单元格添加到工作表中
//            sheet.addCell(label);
            // 生成一个保存数字的单元格,单元格位置是第二列，第一行，单元格的内容为1234.5
//            jxl.write.Number number = new jxl.write.Number(1, 0, 1234.5);
//            sheet.addCell(number);
            // 生成一个保存日期的单元格，单元格位置是第三列，第一行，单元格的内容为当前日期
//            DateTime dtime = new DateTime(2, 0, new Date());
//            sheet.addCell(dtime);
            for (Label label:
                xlsBo.getLabelList()) {
                sheet.addCell(label);
            }
            // 写入数据并关闭文件

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (book != null) {
                try {
                    book.write();
                    book.close();
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }

            }
        }
    }
}
