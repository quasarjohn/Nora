package com.berstek.nora.matrix;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

public class Matrix {

  int rows, cols;
  double[][] data;


  public Matrix(int rows, int cols) {
    data = new double[rows][cols];

    this.rows = rows;
    this.cols = cols;
  }

  public Matrix(double[] input) {
    this.rows = input.length;
    this.cols = 1;

    data = new double[rows][cols];

    for (int i = 0; i < rows; i++) {
      data[i][0] = input[i];
    }
  }

  //passed
  public void randomize() {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        data[i][j] =  ThreadLocalRandom.current().nextDouble(0, 1);
      }
    }
  }

  //passed
  public void printValue() {
    System.out.println("Matrix " + rows + " : " + cols);

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        System.out.print(data[i][j] + ", ");
      }
      System.out.println();
    }
  }

  //passed
  public void mapFunction(Function<Double, Double> fun) {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        data[i][j] = fun.apply(data[i][j]);
      }
    }
  }

  //pass
  public static Matrix mapFunction(Matrix matrix, Function<Double, Double> fun) {
    Matrix copy = matrix.copy();

    for (int i = 0; i < copy.rows; i++) {
      for (int j = 0; j < copy.cols; j++) {
        copy.data[i][j] = fun.apply(copy.data[i][j]);
      }
    }

    return copy;
  }

  //pass
  public static Matrix addScalar(Matrix m, double n) {
    Matrix result = new Matrix(m.rows, m.cols);

    for (int i = 0; i < m.rows; i++) {
      for (int j = 0; j < m.cols; j++) {
        result.data[i][j] = m.data[i][j] + n;
      }
    }

    return result;
  }

  //pass
  public static Matrix addElementWise(Matrix l, Matrix m) {
    Matrix result = new Matrix(l.rows, l.cols);

    for (int i = 0; i < l.rows; i++) {
      for (int j = 0; j < l.cols; j++) {
        result.data[i][j] = l.data[i][j] + m.data[i][j];
      }
    }
    return result;
  }

  //pass
  public static Matrix subtractElementWise(Matrix l, Matrix m) {
    Matrix result = new Matrix(l.rows, l.cols);

    for (int i = 0; i < l.rows; i++) {
      for (int j = 0; j < l.cols; j++) {
        result.data[i][j] = l.data[i][j] - m.data[i][j];
      }
    }
    return result;
  }

  //pass
  public static Matrix multScalar(Matrix m, double n) {
    Matrix result = new Matrix(m.rows, m.cols);

    for (int i = 0; i < m.rows; i++) {
      for (int j = 0; j < m.cols; j++) {
        result.data[i][j] = m.data[i][j] * n;
      }
    }

    return result;
  }

  //pass
  public static Matrix multElementWise(Matrix l, Matrix m) {
    Matrix result = new Matrix(l.rows, l.cols);
    for (int i = 0; i < l.rows; i++) {
      for (int j = 0; j < l.cols; j++) {
        result.data[i][j] = l.data[i][j] * m.data[i][j];
      }
    }
    return result;
  }

  //pass
  public static Matrix multDotProduct(Matrix l, Matrix m) {
    Matrix result = null;

    if (l.cols == m.rows) {
      result = new Matrix(l.rows, m.cols);

      for (int i = 0; i < result.rows; i++) {
        for (int j = 0; j < result.cols; j++) {
          double sum = 0;
          for (int k = 0; k < l.cols; k++) {
            sum += (l.data[i][k]) * (m.data[k][j]);
          }
          result.data[i][j] = sum;
        }
      }
    } else {
      System.out.println("Column or row mismatch");
    }

    return result;
  }

  //pass
  public static Matrix transpose(Matrix m) {
    Matrix result = new Matrix(m.cols, m.rows);

    for (int i = 0; i < result.rows; i++) {
      for (int j = 0; j < result.cols; j++) {
        result.data[i][j] = m.data[j][i];
      }
    }

    return result;

  }

  //passed
  public Matrix copy() {
    Matrix m = new Matrix(rows, cols);

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        m.data[i][j] = data[i][j];
      }
    }

    return m;
  }
}
