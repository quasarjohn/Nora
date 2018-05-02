package com.berstek.nora.test;

import com.berstek.nora.nn.NeuralNet;

import java.util.concurrent.ThreadLocalRandom;

public class Test {

  public static void main(String[] args) {

    NeuralNet nn = new NeuralNet(2, 2, 1);


    double[][] training_data = new double[][]{
        {0, 0},
        {0, 1},
        {1, 0},
        {1, 1},
    };

    double[][] target = new double[][]{
        {0},
        {1},
        {1},
        {0}
    };

    for (int i = 0; i < 1000000; i++) {
      int rand = ThreadLocalRandom.current().nextInt(0, 4);
      nn.train(training_data[rand], target[rand]);
    }

    nn.feedForward(new double[]{1, 0}).printValue();
    nn.feedForward(new double[]{1, 1}).printValue();
    nn.feedForward(new double[]{0, 0}).printValue();
    nn.feedForward(new double[]{1, 0}).printValue();


  }
}
