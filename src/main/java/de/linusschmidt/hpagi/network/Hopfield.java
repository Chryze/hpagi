package de.linusschmidt.hpagi.network;

import java.util.ArrayList;
import java.util.List;

public class Hopfield {

    private int neurons;

    private double[] storage;

    private double[][] weights;

    private List<double[]> data;

    public Hopfield(int neurons) {
        this.neurons = neurons;

        this.storage = new double[neurons];

        this.weights = new double[neurons][neurons];

        this.data = new ArrayList<>();
    }

    public void addData(double[] data) {
        this.data.add(data);
    }

    public double[] recreate(double[] input, int maxIter) {
        double[] output = new double[input.length];
        for(int i = 0; i < maxIter; i++) {
            for(int j = 0; j < output.length; j++) {
                double value = this.activate(input, j);
                if(value > 0.0D) {
                    output[j] = 1.0D;
                } else {
                    output[j] = -1.0D;
                }
            }
        }
        return output;
    }

    public void train() {
        for(int i = 1; i < this.neurons; i++) {
            for(int j = 0; j < i; j++) {
                for(double[] data : this.data) {
                    double value = this.hopfieldValue(data[i]);
                    double target = this.hopfieldValue(data[j]);
                    int temp = (int) (target * value);
                    this.weights[j][i] = this.weights[i][j] = temp;
                }
            }
        }
        for(int i = 0; i < this.neurons; i++) {
            this.storage[i] = 0.0D;
            for(int j = 0; j < i; j++) {
                this.storage[i] += this.weights[i][j];
            }
        }
    }

    private double activate(double[] X, int idx) {
        double sum = 0.0D;
        for(int i = 0; i < this.neurons; i++) {
            if(i != idx) {
                sum += this.weights[idx][i] * X[idx];
            }
        }
        return 2.0D * sum - this.storage[idx];
    }

    private double hopfieldValue(double x) {
        return x == 0 ? -1.0D : 1.0D;
    }
}