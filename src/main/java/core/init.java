package core;

import core.ann.classifier.Dataset;
import core.ann.classifier.MatrixExceptionHandler;
import core.ann.classifier.NeuralNetwork;

import java.io.IOException;


public class init {
    public static void main(String[] args) throws MatrixExceptionHandler, IOException {
        NeuralNetwork ann = new NeuralNetwork(43, 5, 1, 2, "adam", "categorical", 300);
        Dataset x = new Dataset();
        ann.setLogs(true);
        ann.setMetrices(false);
        ann.set_l2_regularizer(5e-4, 5e-4);
        ann.fit(x.X_train(), x.y_train());
        NeuralNetwork.SaveObject(ann);
        //ann.predict(new Matrix(1,2,new double[][]{{0.0978738, 0.3729515}}), new Matrix(1,3,new double[][]{{1.0,2.0,0.0}}).transpose());

    }
}
