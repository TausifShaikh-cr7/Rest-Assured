package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.endpoints.UserEndpointsthroughproperties;
import api.payloads.User;
import io.restassured.response.Response;

public class UserTestsThroughproperties {
	
	Faker faker;
	User userPayload;
	public Logger logger;
	
	@BeforeClass
	public void setup()
	{
		faker=new Faker();
		userPayload=new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		//logs
		
		logger=LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("-----creating user----");
		Response response=UserEndpointsthroughproperties.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=2)
	public void testGetUserByname() {
		
		Response response=UserEndpointsthroughproperties.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority=3)
	public void testUpdateByName() {
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		Response response=UserEndpointsthroughproperties.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().body().statusCode(200);   //one more type to valiudate status code instead of assertion
		
		//checking if response is updated so for that will again call read user
		
		Response updatedresponse=UserEndpointsthroughproperties.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(updatedresponse.getStatusCode(), 200);
	}
	
	@Test(priority=4)
	public void testDeleteUserByname() {
		
		Response response=UserEndpointsthroughproperties.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
