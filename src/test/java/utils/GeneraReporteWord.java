package utils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GeneraReporteWord {

    private static String lastScenarioName = "";
 

    public static void generateReport(String scenarioName, String stepName, byte[] screenshotBytes, String result, int stepNumber, XWPFDocument document) {
        try {
            // Agregar información del escenario solo una vez
            if ( !scenarioName.equals(lastScenarioName)) {
                XWPFParagraph casePara = document.createParagraph();
                XWPFRun caseRun = casePara.createRun();
                caseRun.setBold(true);
                caseRun.setFontSize(16);
                caseRun.setText("Escenario: " + scenarioName);

                lastScenarioName = scenarioName;
            }
    
            lastScenarioName = scenarioName;
            XWPFParagraph stepPara = document.createParagraph();
            XWPFRun stepRun = stepPara.createRun();
            stepRun.setText("Paso " + stepNumber + ": " + stepName);
            stepRun.setFontSize(14);
            stepRun.addBreak();
            stepRun.setText("Resultado: " + result);
            stepRun.setFontSize(12);
           
            if (screenshotBytes != null) {
            try (InputStream inputStream = new ByteArrayInputStream(screenshotBytes)) {
                XWPFParagraph imgPara = document.createParagraph();
                XWPFRun imgRun = imgPara.createRun();
                imgRun.addPicture(inputStream,
                        XWPFDocument.PICTURE_TYPE_PNG,
                        "screenshot.png",
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
        
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        XWPFParagraph title = document.createParagraph();
        XWPFRun titleRun = title.createRun();
        String nameReport = "Reporte de pruebas" + " " + timestamp;
        titleRun.setText(nameReport);
        titleRun.setBold(true);
        titleRun.setFontSize(20);
    }
    
    private static String executionFolder = "target/execution_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

    public static void saveDocument(XWPFDocument document, String scenarioName) {
        try {
            // Crear la carpeta de la ejecución si no existe
            Path folderPath = Paths.get(executionFolder);
            if (!Files.exists(folderPath)) {
                Files.createDirectories(folderPath);
            }
    
            // Guardar el reporte dentro de la carpeta de ejecución
            String filePath = executionFolder + "/" + scenarioName + ".docx";
            try (FileOutputStream out = new FileOutputStream(filePath)) {
                document.write(out);
                System.out.println("Reporte guardado en: " + filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
