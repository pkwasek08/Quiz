package pl.project.Result.Export;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import pl.project.Result.Result;

import java.io.*;
import java.util.ArrayList;

import static java.util.Objects.nonNull;

public class ResultDocGenerator {
    public static ByteArrayInputStream docxGenerator(ArrayList<Result> resultArrayList) throws IOException {
        XWPFDocument document = new XWPFDocument();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        XWPFTable table = document.createTable();
        table.setWidth("100%");

        XWPFTableRow tableRowOne = table.getRow(0);
        tableRowOne.getCell(0).setText("#");
        tableRowOne.addNewTableCell().setText("Imie");
        tableRowOne.addNewTableCell().setText("Nazwisko");
        tableRowOne.addNewTableCell().setText("Test");
        tableRowOne.addNewTableCell().setText("Punkty");
        tableRowOne.addNewTableCell().setText("Ocena");

        Integer resultCount = 1;
        for (Result result : resultArrayList) {
            XWPFTableRow row = table.createRow();
            row.getCell(0).setText(resultCount.toString());
            row.getCell(1).setText(result.getUser().getName());
            row.getCell(2).setText(result.getUser().getLastname());
            row.getCell(3).setText(result.getGenerateTest().getTest().getName());
            row.getCell(4).setText(result.getPoints().toString());
            Double mark = result.getMark();
            if (nonNull(mark)) {
                row.getCell(5).setText(result.getMark().toString());
                row.getCell(5).setColor("2bad1a");
                if (mark < 3) {
                    row.getCell(5).setColor("d42222");
                }
            }
            resultCount++;
        }

        document.write(out);
        return new ByteArrayInputStream(out.toByteArray());
    }

}
