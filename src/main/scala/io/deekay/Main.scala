package io.deekay

/**
  * Created by gfeldman on 9/13/17.
  */

import org.apache.spark.sql.SparkSession
import org.apache.log4j.Logger
import org.apache.log4j.Level

import org.apache.spark.h2o.H2OContext

object Main extends App {

  /*
    * Clear spark logging info, so we can see results.
  */
  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)


  /*
    * Setup spark session.
  */
  val spark = SparkSession.builder()
                          .master("local[*]")
                          .appName("HiggsPrediction")
                          .getOrCreate()

  val sc = spark.sparkContext
  val sqlContext = spark.sqlContext

  /**
    * Load the data.
    * The loadData function returns an RDD of LabeledPoints
    */

  val num_records = 100000
  val data = IO.loadData(num_records,sc)
  data.take(5).foreach(x => println(x))

  /**
    * create train test split
    */

  val trainTestSplit = data.randomSplit(Array(0.8,0.2))
  val (trainingData,testData) = (trainTestSplit(0),trainTestSplit(1))

  trainingData.take(5).foreach(x => println(x))
  testData.take(5).foreach(x => println(x))
}
