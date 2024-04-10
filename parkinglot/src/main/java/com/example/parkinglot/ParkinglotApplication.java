package com.example.parkinglot;

import com.example.parkinglot.controllers.ParkingLotController;
import com.example.parkinglot.controllers.TicketController;
import com.example.parkinglot.dtos.*;
import com.example.parkinglot.repositories.ParkingLotRepository;
import com.example.parkinglot.repositories.TicketRepository;
import com.example.parkinglot.services.ParkingLotService;
import com.example.parkinglot.services.TicketService;
import com.example.parkinglot.services.factories.FeesCalculationStrategyFactory;
import com.example.parkinglot.services.strategies.spotassignmentstrategy.RandomSpotAssignmentStrategy;
import com.example.parkinglot.services.strategies.spotassignmentstrategy.SpotAssignmentStrategy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ParkinglotApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkinglotApplication.class, args);
		ObjectRegistry.put("parkingLotRepository", new ParkingLotRepository());
		ObjectRegistry.put("parkingLotService", new ParkingLotService(
				(ParkingLotRepository) ObjectRegistry.get("parkingLotRepository")
		));
		ObjectRegistry.put("parkingLotController", new ParkingLotController(
				(ParkingLotService) ObjectRegistry.get("parkingLotService")
		));
		ObjectRegistry.put("ticketRepository", new TicketRepository());
		ObjectRegistry.put("spotAssignmentStrategy", new RandomSpotAssignmentStrategy());
		ObjectRegistry.put("ticketService", new TicketService(
				(TicketRepository) ObjectRegistry.get("ticketRepository"),
				(SpotAssignmentStrategy) ObjectRegistry.get("spotAssignmentStrategy"),
				(ParkingLotRepository) ObjectRegistry.get("parkingLotRepository"),
				(FeesCalculationStrategyFactory) ObjectRegistry.get("feesCalculationStrategyFactory")
		));
		ObjectRegistry.put("ticketController", new TicketController(
				(TicketService) ObjectRegistry.get("ticketService")
		));


		ParkingLotController parkingLotController = (ParkingLotController) ObjectRegistry.get("parkingLotController");
		CreateParkingLotRequestDto request = new CreateParkingLotRequestDto();
		request.setAddress("Delhi Airport");
		request.setNumberOfFloors(4);

		CreateParkingLotResponseDto response =
				parkingLotController.createParkingLot(request);

		if (response.getResponseStatus().equals(ResponseStatusDto.FAILURE)) {
			System.out.println("Something is wrong");
		} else {
			System.out.println(response.getParkingLot());
		}

		UpdateParkingLotRequestDto updateParkingLotRequest = new UpdateParkingLotRequestDto();
		updateParkingLotRequest.setParkingLotId(1L);
		updateParkingLotRequest.setAddress("Noida");

		UpdateParkingLotResponseDto responseDto = parkingLotController.updateAddress(updateParkingLotRequest);

		System.out.println(responseDto);
	}

}
