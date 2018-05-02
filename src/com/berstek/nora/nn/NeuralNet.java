package com.berstek.nora.nn;

import com.berstek.nora.matrix.Matrix;

public class NeuralNet {

  private double learning_rate = 0.01;

  //weights from input to hidden
  private Matrix weights_ih;

  //weights from hidden to output
  private Matrix weights_ho;

  //biases from input to hidden
  private Matrix bias_ih;

  //biases from hidden to output
  private Matrix bias_ho;


  private Matrix output_hidden, output_final;

  public NeuralNet(int input_c, int hidden_c, int output_c) {
    weights_ih = new Matrix(hidden_c, input_c);
    weights_ih.randomize();
    weights_ho = new Matrix(output_c, hidden_c);
    weights_ho.randomize();
    bias_ih = new Matrix(hidden_c, 1);
    bias_ih.randomize();
    bias_ho = new Matrix(output_c, 1);
    bias_ho.randomize();
  }

  public double activation(double x) {
    return 1 / (1 + Math.exp(-x));
  }

  private double dActivation(double x) {
    return x * (1 - x);
  }

  public Matrix feedForward(double[] input_arr) {
    Matrix input = new Matrix(input_arr);

    //output_hidden = sigmoid(weights_ih * input + bias_ih)
    output_hidden = Matrix.multDotProduct(weights_ih, input);
    output_hidden = Matrix.addElementWise(output_hidden, bias_ih);
    output_hidden = Matrix.mapFunction(output_hidden, this::activation);

    //output_final = sigmoid(weights_ho * output_hidden + bias_ho)
    //this operation uses the output from the hidden layer as its input
    output_final = Matrix.multDotProduct(weights_ho, output_hidden);
    output_final = Matrix.addElementWise(output_final, bias_ho);
    output_final = Matrix.mapFunction(output_final, this::activation);
    return output_final;
  }

  public void train(double[] input_arr, double[] target_arr) {
    Matrix input = new Matrix(input_arr);
    Matrix target = new Matrix(target_arr);
    feedForward(input_arr);

    Matrix error_output = Matrix.subtractElementWise(target, output_final);
    Matrix gradient_output = Matrix.mapFunction(output_final, this::dActivation);
    gradient_output = Matrix.multElementWise(gradient_output, error_output);
    gradient_output = Matrix.multScalar(gradient_output, learning_rate);
    Matrix delta_weight_ho = Matrix.multDotProduct(gradient_output, Matrix.transpose(output_hidden));

    Matrix error_hidden = Matrix.multDotProduct(Matrix.transpose(weights_ho), error_output);
    Matrix gradient_hidden = Matrix.mapFunction(output_hidden, this::dActivation);
    gradient_hidden = Matrix.multElementWise(gradient_hidden, error_hidden);
    gradient_hidden = Matrix.multScalar(gradient_hidden, learning_rate);
    Matrix delta_weight_ih = Matrix.multDotProduct(gradient_hidden, Matrix.transpose(input));

    weights_ho = Matrix.addElementWise(weights_ho, delta_weight_ho);
    weights_ih = Matrix.addElementWise(weights_ih, delta_weight_ih);
    bias_ho = Matrix.addElementWise(bias_ho, gradient_output);
    bias_ih = Matrix.addElementWise(bias_ih, gradient_hidden);

  }

}
