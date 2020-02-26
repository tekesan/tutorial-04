package com.apap.tu04.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.tu04.model.FlightModel;
import com.apap.tu04.model.PilotModel;
import com.apap.tu04.service.FlightService;
import com.apap.tu04.service.PilotService;

@Controller
public class FlightController {
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private PilotService pilotService;

	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.GET)
	private String add(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		FlightModel flight = new FlightModel();
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		flight.setPilot(pilot);
		model.addAttribute("flight", flight);
		return "addFlight";
	}
	
	@RequestMapping(value = "/flight/add", method = RequestMethod.POST)
	private String addFlightSubmit(@ModelAttribute FlightModel flight) {
		flightService.addFlight(flight);
		return "add";
	}
	
	@GetMapping(value = "/flight/edit/{id}")
    private String edit(@PathVariable(value = "id") Long id, ModelMap model){

        FlightModel flightModel = flightService.getById(id);

        model.addAttribute("flight", flightModel);

        return "editFlight";

    }

    @PostMapping(value = "/flight/edit")
    private String submitedit(@ModelAttribute FlightModel flightModel){

        flightService.addFlight(flightModel);
        return "redirect:/pilot/view/"+flightModel.getPilot().getLicenseNumber();

    }
	
	 @GetMapping(value = "/flight/delete/{id}")
	    private String delete(@PathVariable(value = "id") Long id, ModelMap model){

	        FlightModel flightModel = flightService.getById(id);

	        model.addAttribute("flight", flightModel);

	        return "deleteFlight";

	    }

	    @PostMapping(value = "/flight/delete")
	    private String submit(@ModelAttribute FlightModel flightModel){

	        String license = flightModel.getPilot().getLicenseNumber();
	        flightService.deleteFlight(flightModel);

	        return "redirect:/pilot/view/"+license;
	    }
}
