package com.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

public class ExcelUtil {

    public static Object[][] getTestData(String fileName) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(fileName));
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);
        int rowCount = sheet.getPhysicalNumberOfRows();
        Row firstRow = sheet.getRow(0);
        int colCount = firstRow.getPhysicalNumberOfCells();
        Object[][] data = new Object[rowCount - 1][colCount-1];

        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next();
        int i = 0;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            for (int j = 0; j < colCount -1; j++) {
                data[i][j] = row.getCell(j).getStringCellValue();
            }
            i++;
        }

        return data;
    }

    public static void updateResult(String fileName, String testCase, String result) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(fileName));
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            String excelTestCase = row.getCell(0).getStringCellValue();
            if (excelTestCase.equals(testCase)) {
                Cell cell = row.createCell(3, CellType.STRING);
                cell.setCellValue(result);
                break;
            }
        }

        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        workbook.close();
        fileInputStream.close();
    }
}
