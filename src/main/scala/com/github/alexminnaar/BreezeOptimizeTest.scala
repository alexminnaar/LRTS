package com.github.alexminnaar

import breeze.linalg.{DenseMatrix, norm, DenseVector}
import breeze.optimize.DiffFunction


//case class Params(weights:DenseVector[Double],
//                  inputs:DenseMatrix[Double],
//                  outputs: DenseVector[Double],
//                  priorMean:DenseVector[Double],
//                  priorCovariance:DenseMatrix[Double])
//
//object BreezeOptimizeTest extends App{
//
//  val f = new DiffFunction[DenseVector[Double]] {
//
//    def calculate(x:DenseVector[Double])={
//      //returns value at x and gradient at x
//      (norm((x-3d) :^ 2d,1d),(x*2d)-6d)
//    }
//
//  }
//
//
//  println( f.valueAt(DenseVector(3,3,3)))
//  println(f.gradientAt(DenseVector(3,0,1)))
//
//
//  val fu = new DiffFunction[Params] {
//    def calculate(x:Params)={
//
//      val fVal = -0.5*(x.weights-x.priorMean)*x.priorCovariance*(x.weights - x.priorMean)
//
//
//      ()
//    }
//
//  }
//
//}
