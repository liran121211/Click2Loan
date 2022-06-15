package com.example.demo;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.beans.PropertyChangeSupport;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;


public class ThreadWorker_3 extends Service<Void> {
    private final PropertyChangeSupport notifier;

    private static final String ANN_URL_FILE = "http://207.154.201.63/pre_trained_ann.bin";
    private static final String ANN_FILE_NAME = "pre_trained_ann.bin";

    public ThreadWorker_3() {
        this.notifier = new PropertyChangeSupport(this);

        setOnSucceeded(success -> notifier.firePropertyChange("DOWNLOAD_STATUS", null, "SUCCESS"));
        setOnFailed(fail -> notifier.firePropertyChange("DOWNLOAD_STATUS", null, "FAILED"));
        setOnCancelled(cancelled -> notifier.firePropertyChange("DOWNLOAD_STATUS", null, "CANCELED"));
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
                URL url;
                URLConnection con;
                DataInputStream dis;
                FileOutputStream fos;
                byte[] fileData;
                File ann_file = new File("src\\main\\java\\core\\bin\\metrics", ANN_FILE_NAME);

                try {
                    if (!ann_file.exists()) { //skip download if file exists.
                        url = new URL(ANN_URL_FILE); //File Location goes here
                        con = url.openConnection(); // open the url connection.
                        dis = new DataInputStream(con.getInputStream());
                        fileData = new byte[con.getContentLength()];
                        for (int q = 0; q < fileData.length; q++)
                            fileData[q] = dis.readByte();

                        dis.close(); // close the data input stream
                        fos = new FileOutputStream(ann_file);
                        fos.write(fileData);  // write out the file we want to save.
                        fos.close(); // close the output stream writer
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
    }

    public PropertyChangeSupport getNotifier() {
        return notifier;
    }
}


