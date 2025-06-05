package org.example.listeners;

import org.example.utilities.FileManager;
import org.example.utilities.Logs;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListeners implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        Logs.info("Comenzando el test: %s", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Logs.info("Test exitoso: %s", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Logs.info("Test fallido: %s", result.getName());
        FileManager.getScreenshot(result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Logs.info("Test ignorando: %s", result.getName());
    }
}
