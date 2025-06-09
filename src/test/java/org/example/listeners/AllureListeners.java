package org.example.listeners;

import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.TestResult;
import org.example.utilities.FileManager;
import org.example.utilities.Logs;

public class AllureListeners implements TestLifecycleListener {

    @Override
    public void beforeTestStop(TestResult result){
        Logs.debug("Before test stop de allure");
        Status resultType = result.getStatus();

        switch(resultType){
            case BROKEN, FAILED -> {
                FileManager.getScreenshot();
                FileManager.getPageSource();
            }
        }
    }
}
