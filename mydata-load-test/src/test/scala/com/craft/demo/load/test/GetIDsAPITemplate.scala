package com.craft.demo.load.test
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object GetIDsAPITemplate {
  val feeder = csv("get_mydata.csv")
  val getIDsAPITemplate =
    feed(feeder)
      .exec(http("GetIDsAPITemplate")
        .get("/${TIMESTAMP}/${KEY}/${VALUE}")
        .header("Content-Type","application/json")
        .check(status.is(200))
      )
}