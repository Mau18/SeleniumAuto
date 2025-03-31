package steps;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.*;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import pages.BasePage;

import utils.GeneraReporteWord;
import utils.ScreenshotUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Hooks extends BasePage implements EventListener {

    public Hooks() {
        super(driver);
    }

    private static List<String> stepDescriptions = new ArrayList<>();
    private static List<String> stepResults = new ArrayList<>();  // Lista para almacenar los resultados de cada paso

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestStepStarted.class, this::captureStep);
        publisher.registerHandlerFor(TestStepFinished.class, this::captureStepResult);
    }

    
    private void captureStep(TestStepStarted event) {
        TestStep step = event.getTestStep();
        if (step instanceof PickleStepTestStep) {
            PickleStepTestStep pickleStep = (PickleStepTestStep) step;
            stepDescriptions.add(pickleStep.getStep().getText());
        }
    }

    private void captureStepResult(TestStepFinished event) {
        Result result = event.getResult();
        String status = result.getStatus().toString();
    
        // Asegurar que stepResults y stepDescriptions tienen el mismo tamaño
        while (stepResults.size() < stepDescriptions.size()) {
            stepResults.add("Omitido");
        }
    
        if (status.equalsIgnoreCase("PASSED")) {
            stepResults.set(stepResults.size() - 1, "Exitoso");
        } else if (status.equalsIgnoreCase("FAILED")) {
            stepResults.set(stepResults.size() - 1, "Fallido");
        } else if (status.equalsIgnoreCase("SKIPPED")) {
            stepResults.set(stepResults.size() - 1, "Omitido");
        } else {
            stepResults.set(stepResults.size() - 1, "Desconocido (" + status + ")");
        }
    
        System.out.println("✅ Estado real del paso: " + status);
    }


    // Este método se ejecutará después de cada paso
    @AfterStep
    public void afterStep(Scenario scenario) throws IOException {
        int stepNumber = stepResults.size();

        if (stepResults.size() < stepNumber) {
            System.err.println("⚠️ No se encontró resultado para el paso " + stepNumber + ". Asignando 'Omitido'.");
            stepResults.add("Omitido");
        }

        String stepDescription = stepDescriptions.get(stepNumber - 1);  // Obtener la descripción del paso actual
        String stepResult = stepResults.get(stepNumber - 1);  // Obtener el resultado del paso actual

        // Captura la pantalla después de cada paso
        String screenshotPath = ScreenshotUtil.takeScreenshot(driver, scenario.getName() + "_step" + stepNumber);
        
        // Crear o abrir el documento
        String scenarioName = scenario.getName().replaceAll(" ", "_");
        XWPFDocument document = new XWPFDocument();
        if (Files.exists(Paths.get("target/" + scenarioName + ".docx"))) {
            try (FileInputStream fis = new FileInputStream("target/" + scenarioName + ".docx")) {
                document = new XWPFDocument(fis);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Crear el encabezado solo una vez para cada escenario
            GeneraReporteWord.createReportHeader(scenarioName, document);
        }

        // Genera el reporte para el paso actual
        GeneraReporteWord.generateReport(scenario.getName(), stepDescription, screenshotPath, stepResult, stepNumber, document);
        // Guardar el documento final con todos los pasos
        GeneraReporteWord.saveDocument(document, scenarioName);
    }

   
}
