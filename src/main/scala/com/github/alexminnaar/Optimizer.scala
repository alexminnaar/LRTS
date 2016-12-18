package com.github.alexminnaar

import breeze.linalg._
import breeze.numerics.{log, sigmoid}
import breeze.optimize.DiffFunction


case class Params(gradient: DenseVector[Double],
                  weights: DenseVector[Double],
                  mean: DenseVector[Double],
                  inverseCovariance: DenseMatrix[Double],
                  inputs: Seq[DenseVector[Double]],
                  outputs: Seq[Double])


object Optimizer extends App {


  val blrDiffFunction = new DiffFunction[Params] {

    def calculate(params: Params) = {

     val lossFunctionTerm1: Double = -(params.weights - params.mean).t * params.inverseCovariance * (params.weights - params.weights)

      //println(-(params.weights - params.mean).t * params.inverseCovariance*(params.weights - params.weights))
      val lossFunctionTerm2: Double = params.inputs
        .zip(params.outputs)
        .map { case (x, y) =>
        println(params.weights.t * x)
        val prediction: Double = sigmoid(params.weights.t * x)
        y * log(prediction) + (1 - y * log(prediction))
      }.sum

      val lossFunction: Double = lossFunctionTerm1 + lossFunctionTerm2

      val gradientTerm1: DenseVector[Double] = params.inputs
        .zip(params.outputs)
        .map { case (x, y) =>
        val prediction: Double = sigmoid(params.weights.t * x)
        (prediction - y) :* x
      }.reduce(_ + _)

      val gradient= (params.weights- params.mean).t*params.inverseCovariance + gradientTerm1.t

      (lossFunction, params.copy(gradient = gradient.t))


      (0.5*lossFunction,params)
    }


  }

  val g = DenseVector(0.0, 0.0)
  val w = DenseVector(0.1, 0.2)
  val m = DenseVector(0.02, 0.3)
  val ic = DenseMatrix((0.01, 0.08), (0.004, 0.001))
  val i = Seq(DenseVector(1.0, 2.0), DenseVector(6.0, 3.0))
  val o = Seq(1.0, 0.0)

  val p = new Params(gradient = g, weights = w, mean = m, inverseCovariance = ic, inputs = i, outputs = o)

  println("hello world")

  val balh = blrDiffFunction.valueAt(p)
  println(balh)


}
