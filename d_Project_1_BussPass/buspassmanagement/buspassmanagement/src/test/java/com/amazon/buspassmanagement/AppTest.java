package com.amazon.buspassmanagement;

import java.sql.ResultSet;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

import com.amazon.buspassmanagement.controller.AuthenticationService;
import com.amazon.buspassmanagement.controller.ValidityCheckService;
import com.amazon.buspassmanagement.db.DB;
import com.amazon.buspassmanagement.db.VehicleDAO;
import com.amazon.buspassmanagement.model.BusPass;
import com.amazon.buspassmanagement.model.Feedback;
import com.amazon.buspassmanagement.model.Route;
import com.amazon.buspassmanagement.model.Stop;
import com.amazon.buspassmanagement.model.User;
import com.amazon.buspassmanagement.model.Vehicle;

// Reference Link to Use JUnit as Testing Tool in your Project
// https://maven.apache.org/surefire/maven-surefire-plugin/examples/junit.html

public class AppTest {

	ValidityCheckService vService = ValidityCheckService.getInstance();
	DB db = DB.getInstance();

	@Test
	public void testValidBusPass() {
		BusPass passes = new BusPass();
		passes.id = 1;
		passes.validTill = "2024-04-11 03:50:48.000";
		boolean result = vService.passValidity(passes);
		Assert.assertEquals(true, result);
	}

	@Test
	public void testVehicleAtRoute() {
		Vehicle vehicle = new Vehicle();
		vehicle.routeId = 1;
		boolean result = vService.vehicalAtRoute(vehicle);
		Assert.assertEquals(true, result);
	}

	@Test
	public void testStopAtRoute() {
		Stop stop = new Stop();
		stop.routeId = 1;
		boolean result = vService.stopAtRoute(stop);
		Assert.assertEquals(true, result);
	}

	@Test
	public void testtwoStopAtRoute() {
		Stop stop = new Stop();
		stop.routeId = 1;
		boolean result = vService.stopAtRoute(stop);
		Assert.assertEquals(true, result);
	}

	@Test
	public void testUserComplaint() {
		Feedback feedback = new Feedback();
		feedback.userId = 8;
		feedback.type = 2;
		boolean result = vService.complaintCheck(feedback);
		Assert.assertEquals(true, result);
	}

}

