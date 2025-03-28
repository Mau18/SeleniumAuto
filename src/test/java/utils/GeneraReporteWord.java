package utils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GeneraReporteWord {

    public static void generateReport(String scenarioName, String stepName, String screenshotPath, String result, int stepNumber, XWPFDocument document) {
        try {
            // Agregar información del escenario solo una vez
            if (stepNumber == 1) {
                XWPFParagraph casePara = document.createParagraph();
                XWPFRun caseRun = casePara.createRun();
                caseRun.setBold(true);
                caseRun.setFontSize(16);
                caseRun.setText("Escenario: " + scenarioName);
            }
    
            // Agregar información del paso
            XWPFParagraph stepPara = document.createParagraph();
            XWPFRun stepRun = stepPara.createRun();
            stepRun.setText("Paso " + stepNumber + ": " + stepName);
            stepRun.setFontSize(14);
            stepRun.addBreak();
            stepRun.setText("Resultado: " + result);
            stepRun.setFontSize(12);
    
            // Agregar screenshot
            Path imgPath = Paths.get(screenshotPath);
            if (Files.exists(imgPath)) {
                try (InputStream inputStream = Files.newInputStream(imgPath)) {
                    XWPFParagraph imgPara = document.createParagraph();
                    XWPFRun imgRun = imgPara.createRun();
                    imgRun.addPicture(inputStream,
                        XWPFDocument.PICTURE_TYPE_PNG,
                        imgPath.getFileName().toString(),
                        Units.toEMU(500), Units.toEMU(300));
                } catch (InvalidFormatException e) {
                    System.err.println("⚠️ Error de formato en la imagen: " + e.getMessage());
                    e.printStackTrace();
                }
            }
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void createReportHeader(String scenarioName, XWPFDocument document) throws IOException {
        // Agregar título
        XWPFParagraph title = document.createParagraph();
        XWPFRun titleRun = title.createRun();
        titleRun.setText("Reporte de Pruebas");
        titleRun.setBold(true);
        titleRun.setFontSize(20);
    }

    public static void saveDocument(XWPFDocument document, String scenarioName) {
        try (FileOutputStream out = new FileOutputStream("target/" + scenarioName + ".docx")) {
            document.write(out);
            System.out.println("Reporte guardado en: " + "target/" + scenarioName + ".docx");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
