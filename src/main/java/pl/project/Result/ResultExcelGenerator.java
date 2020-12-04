package pl.project.Result;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pl.project.User.User;

import static java.util.Objects.isNull;

public class ResultExcelGenerator {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Result> listResult;

    public ResultExcelGenerator(List<Result> listResult) {
        this.listResult = listResult;
        workbook = new XSSFWorkbook();
    }

    private void writeTitleLine(){
        sheet = workbook.createSheet("Wyniki testu");
        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:E1"));
        CellStyle style = initCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(22);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        Row rowTitle = sheet.createRow(0);
        createCell(rowTitle, 0, listResult.get(0).getGenerateTest().getTest().getName(), style);
    }

    private void writeHeaderLine() {
        CellStyle style = initCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        Row row = sheet.createRow(1);
        createCell(row, 0, "#", style);
        createCell(row, 1, "Imię", style);
        createCell(row, 2, "Nazwisko", style);
        createCell(row, 3, "Punkty", style);
        createCell(row, 4, "Ocena", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 2;

        CellStyle style = initCellStyle();

        for (Result result : listResult) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            User user = result.getUser();
            createCell(row, columnCount++, rowCount - 2, style);
            createCell(row, columnCount++, user.getName(), style);
            createCell(row, columnCount++, user.getLastname(), style);
            createCell(row, columnCount++, result.getPoints().toString(), style);
            createMarkCell(row, columnCount++, result.getMark(), style);

        }
    }

    private CellStyle initCellStyle() {
        CellStyle style = workbook.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        return style;
    }

    private void createMarkCell(Row row, int columnCount, Object value, CellStyle style) {
        CellStyle styleCellMark = initCellStyle();
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        styleCellMark.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        if (isNull(value) || (Double) value < 3) {
            styleCellMark.setFillForegroundColor(IndexedColors.RED.getIndex());
        }
        styleCellMark.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        if (value instanceof Double) {
            cell.setCellValue((Double) value);
        }
        cell.setCellStyle(styleCellMark);
    }

    public void export(HttpServletResponse response) throws IOException {
        writeTitleLine();
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}

