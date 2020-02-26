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
public class PilotController {
	@Autowired
	private PilotService pilotService;
	private FlightService flightService;

	@RequestMapping("/")
	private String home(){
		return "home";
	}
	
	@RequestMapping(value = "/pilot/add", method = RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("pilot", new PilotModel());
		return "addPilot";
	}
	
	@GetMapping(value = "/pilot/view/{licensenumber}")
	private String view(@PathVariable(value = "licensenumber") String license, Model model) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(license);
		model.addAttribute("pilot", pilot);
		model.addAttribute("flights", pilot.getPilotFlight());
		return "view-pilot";
	}

	@RequestMapping(value = "/pilot/add", method = RequestMethod.POST)
	private String addPilotSubmit(@ModelAttribute PilotModel pilot) {
		pilotService.addPilot(pilot);
		return "add";
	}
	
	 @GetMapping(value = "/pilot/edit/{id}")
	    private String edit(@PathVariable(value = "id") Long id, ModelMap model){

	        PilotModel pilotModel = pilotService.getPilotDetailByID(id);

	        model.addAttribute("pilot", pilotModel);

	        return "editPilot";

	    }
	
	@PostMapping(value = "/pilot/edit")
    private String submitedit(@ModelAttribute PilotModel pilotModel){

        pilotService.addPilot(pilotModel);
        return "redirect:/pilot/view/"+pilotModel.getLicenseNumber();

    }

    @GetMapping(value = "/pilot/delete/{id}")
    private String delete(@PathVariable(value = "id") Long id, ModelMap model){

        PilotModel pilotModel = pilotService.getPilotDetailByID(id);

        model.addAttribute("pilot", pilotModel);

        return "deletePilot";

    }

    @PostMapping(value = "/pilot/delete")
    private String submitdelete(@ModelAttribute PilotModel pilotModel){

        pilotModel = pilotService.getPilotDetailByID(pilotModel.getId());

        for(FlightModel flight : pilotModel.getPilotFlight()){
            flightService.deleteFlight(flight);
        }

        pilotService.delete(pilotModel);

        return "home";
    }
}
