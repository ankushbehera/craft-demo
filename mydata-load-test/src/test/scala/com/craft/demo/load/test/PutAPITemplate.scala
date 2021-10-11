package com.craft.demo.load.test
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object PutAPITemplate {
  val feeder = csv("put_mydata.csv")
  val putAPITemplate =
    feed(feeder)
      .exec(http("PutAPITemplate")
        .put("")
        .body(StringBody("""[{"id": "${ID}","attributes": ${ATTRIBUTES},"timestamp": ${TIMESTAMP}}] """))
        .header("Content-Type","application/json")
        .check(status.is(200))
      )
}