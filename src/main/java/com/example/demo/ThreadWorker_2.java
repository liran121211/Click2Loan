package com.example.demo;

import core.python.extender.PythonInterpreter;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import java.beans.PropertyChangeSupport;

public class ThreadWorker_2 extends Service<Void> {
    private final PropertyChangeSupport notifier;

    public ThreadWorker_2() {
        this.notifier = new PropertyChangeSupport(this);

        setOnSucceeded(success -> notifier.firePropertyChange("PYTHON_STATUS", null, "SUCCESS"));
        setOnFailed(fail -> notifier.firePropertyChange("PYTHON_STATUS", null, "FAILED"));
        setOnCancelled(cancelled -> notifier.firePropertyChange("PYTHON_STATUS", null, "CANCELED"));
    }

    /**
     * This method starts the Service
     */
    public void startTheService() {
        if (!isRunning()) {
            //...
            reset();
            start();
        }

    }

    @Override
    protected Task<Void> createTask() {
        return new Task<>() {
            @Override
            protected Void call() {
                //create neural network object and load pre-trained values
                PythonInterpreter.exec("normalize_data.py");
                return null;
            }
        };
    }

    public PropertyChangeSupport getNotifier() {
        return notifier;
    }
}


