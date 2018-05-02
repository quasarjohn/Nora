package com.berstek.nora.test;

import com.berstek.nora.matrix.Matrix;
import com.berstek.nora.nn.NeuralNet;


public class Test2 {
  public static void main(String[] args) {
    Matrix m = new Matrix(2,3);
    m.randomize();
    m.printValue();

    System.out.println();
    Matrix.mapFunction(m, new NeuralNet(2,2,1) :: activation);
    m.printValue();
  }
}
