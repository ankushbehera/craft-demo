package com.craft.demo.load.test
import io.gatling.core.Predef._

class MyDataLoadTestUseCase2 extends Simulation {

		val scn= scenario("Load test for Get IDs from document")
    		.exec(GetIDsAPITemplate.getIDsAPITemplate)

  setUp(scn.inject(rampUsers(MyDataLoadTestConfig.RampUp_Users_getAPI) over (MyDataLoadTestConfig.RampUp_Duration_getAPI))).protocols(MyDataLoadTestConfig.httpProtocol)
}
