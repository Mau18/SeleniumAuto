package steps;

import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import pages.BasePage;

import utils.GeneraReporteWord;
import utils.ScreenshotUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hooks extends BasePage implements ConcurrentEventListener {

    public Hooks() {
        super(driver);
    }

    private static List<String> stepDescriptions = new ArrayList<>();
    private static List<String> stepResults = new ArrayList<>();  
    private static final Map<String, XWPFDocument> scenarioDocuments = new HashMap<>();
    private int stepCounter = 0; 

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestStepStarted.class, this::captureStep);
        publisher.registerHandlerFor(TestStepFinished.class, this::captureStepResult);
    }
    
    @Before
    public void beforeScenario(Scenario scenario) {
        stepDescriptions.clear();
        stepResults.clear();
        stepCounter = 0;
    }
    
    private void captureStep(TestStepStarted event) {
        TestStep step = event.getTestStep();
        if (step instanceof PickleStepTestStep) {
            PickleStepTestStep pickleStep = (PickleStepTestStep) step;
            stepDescriptions.add(pickleStep.getStep().getText());
            System.out.println("Paso registrado: " + pickleStep.getStep().getText());
        }
    }

    private void captureStepResult(TestStepFinished event) {
        Result result = event.getResult();
        String status = result.getStatus().toString();
    
        // ⚠️ Validación extra para evitar el error de índice negativo
        if (stepDescriptions.isEmpty()) {
            System.err.println("⚠️ No hay pasos registrados, ignorando resultado del paso.");
            return;
        }
    
        // Asegurar que stepResults tenga el mismo tamaño que stepDescriptions
        while (stepResults.size() < stepDescriptions.size()) {
            stepResults.add("Omitido");
        }
    
        int lastIndex = stepResults.size() - 1; // Última posición válida
    
        if (status.equalsIgnoreCase("PASSED")) {
            stepResults.set(lastIndex, "Exitoso");
        } else if (status.equalsIgnoreCase("FAILED")) {
            stepResults.set(lastIndex, "Fallido");
        } else if (status.equalsIgnoreCase("SKIPPED")) {
            stepResults.set(lastIndex, "Omitido");
        } else {
            stepResults.set(lastIndex, "Desconocido (" + status + ")");
        }
    
        System.out.println("✅ Estado real del paso: " + status);
    }

    @AfterStep
    public void afterStep(Scenario scenario) throws IOException {
        if (stepDescriptions.isEmpty() || stepResults.isEmpty()) {
            System.err.println("No hay pasos registrados para este escenario todavía.");
            return;
        }

        stepCounter++;

        if (stepCounter > stepDescriptions.size()) {
            System.err.println("No se encontró descripción para el paso " + stepCounter);
            return;
        }

        String stepDescription = stepDescriptions.get(stepCounter - 1);
        String stepResult = (stepResults.size() >= stepCounter) ? stepResults.get(stepCounter - 1) : "Omitido";

        byte[] screenshotBytes = ScreenshotUtil.takeScreenshot(driver);

        String scenarioName = scenario.getName().replaceAll(" ", "_");
        XWPFDocument document = scenarioDocuments.computeIfAbsent(scenarioName, k -> {
            XWPFDocument newDoc = new XWPFDocument();
            try {
                GeneraReporteWord.createReportHeader(scenarioName, newDoc);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return newDoc;
        });

        GeneraReporteWord.generateReport(scenario.getName(), stepDescription, screenshotBytes, stepResult, stepCounter, document);
    }

    @AfterAll
    public static void saveAllDocuments() {
        for (Map.Entry<String, XWPFDocument> entry : scenarioDocuments.entrySet()) {
            GeneraReporteWord.saveDocument(entry.getValue(), entry.getKey());
        }
        System.out.println("✅ Todos los reportes han sido guardados.");
    }
}