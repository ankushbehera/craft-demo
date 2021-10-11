package com.craft.demo.load.test
import io.gatling.core.Predef._

class MyDataLoadTestUseCase1 extends Simulation {
	val scn = scenario("Load Test for insert document")
        .exec(PutAPITemplate.putAPITemplate)
  setUp(scn.inject(rampUsers(MyDataLoadTestConfig.RampUp_Users_putAPI) over (MyDataLoadTestConfig.RampUp_Duration_putAPI))).protocols(MyDataLoadTestConfig.httpProtocol)
}
