package com.craft.demo.load.test
import io.gatling.http.Predef._
object MyDataLoadTestConfig {
  val httpProtocol = http.baseURL("http://craft-demo-app:8585/mydata")
  val RampUp_Users_putAPI = 100
  val RampUp_Duration_putAPI = 10
  val RampUp_Users_getAPI = 26
  val RampUp_Duration_getAPI = 4
}