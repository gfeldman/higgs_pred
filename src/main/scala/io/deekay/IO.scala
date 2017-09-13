package io.deekay

import org.apache.spark.rdd.RDD
import org.apache.spark.SparkContext
import org.apache.spark.mllib.linalg.{Vector,Vectors}
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.sql.{SQLContext, SparkSession}

/**
  * Created by gfeldman on 9/13/17.
  */
object IO {
  def loadData(num_records: Int,sc: SparkContext): RDD[LabeledPoint]  ={

    val rawData = sc.textFile("data/HIGGS.csv")
                    .zipWithIndex()
                    .filter( x => x._2 < num_records)
                    .map( x => x._1)

    val data = rawData.map( line =>
        line.split(",").map(_.toDouble)
    )

    val response: RDD[Int] = data.map(row => row(0).toInt)
    val features: RDD[Vector] = data.map( line => {
      Vectors.dense(line.slice(1, line.size))
    })

    val higgs = response.zip(features).map{
      case (response,features) => LabeledPoint(response,features)
    }

    higgs.setName("higgs").cache()

    return(higgs)
  }

}
