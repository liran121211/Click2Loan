package com.example.demo;

import core.ann.classifier.NeuralNetwork;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.beans.PropertyChangeSupport;

public class ThreadWorker_1 extends Service<NeuralNetwork> {
    private final PropertyChangeSupport notifier;

    private static final String ANN_FILE = "src\\main\\java\\core\\bin\\metrics\\pre_trained_ann.bin";

    public ThreadWorker_1() {
        this.notifier = new PropertyChangeSupport(this);

        setOnSucceeded(success -> notifier.firePropertyChange("LOADER_STATUS", null, "SUCCESS"));
        setOnFailed(fail -> notifier.firePropertyChange("LOADER_STATUS", null, "FAILED"));
        setOnCancelled(cancelled -> notifier.firePropertyChange("LOADER_STATUS", null, "CANCELED"));
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
    protected Task<NeuralNetwork> createTask() {
        return new Task<>() {
            @Override
            protected NeuralNetwork call() throws Exception {
                //create neural network object and load pre-trained values
                while (!LoanManager.is_file_downloaded) {
                    System.out.print("");
                }

                return NeuralNetwork.LoadObject(ANN_FILE);
            }
        };
    }

    public PropertyChangeSupport getNotifier() {
        return notifier;
    }
}


