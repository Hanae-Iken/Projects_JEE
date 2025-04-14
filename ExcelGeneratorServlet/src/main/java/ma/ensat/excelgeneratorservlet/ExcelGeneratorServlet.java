package ma.ensat.excelgeneratorservlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@WebServlet("/generate-real-excel")
public class ExcelGeneratorServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Créer un vrai classeur Excel
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Rapport");

        // 2. Ajouter des données (comme dans le PowerPoint mais en version réelle)
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Produit");
        headerRow.createCell(1).setCellValue("Q1");
        headerRow.createCell(2).setCellValue("Q2");
        headerRow.createCell(3).setCellValue("Q3");
        headerRow.createCell(4).setCellValue("Q4");
        headerRow.createCell(5).setCellValue("Total");

        // Ligne 1 : Apples (comme dans le PPT)
        Row row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("Apples");
        row1.createCell(1).setCellValue(78);
        row1.createCell(2).setCellValue(87);
        row1.createCell(3).setCellValue(92);
        row1.createCell(4).setCellValue(29);
        row1.createCell(5).setCellFormula("SUM(B2:E2)");

        // Ligne 2 : Oranges (comme dans le PPT)
        Row row2 = sheet.createRow(2);
        row2.createCell(0).setCellValue("Oranges");
        row2.createCell(1).setCellValue(77);
        row2.createCell(2).setCellValue(86);
        row2.createCell(3).setCellValue(93);
        row2.createCell(4).setCellValue(30);
        row2.createCell(5).setCellFormula("SUM(B3:E3)");

        // 3. Configurer la réponse HTTP
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=rapport.xlsx");

        // 4. Écrire le fichier Excel dans le flux de réponse
        try (OutputStream out = response.getOutputStream()) {
            workbook.write(out);
        } finally {
            workbook.close();
        }
    }
}