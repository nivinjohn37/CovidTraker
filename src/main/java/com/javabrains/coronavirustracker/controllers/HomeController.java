package com.javabrains.coronavirustracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.javabrains.coronavirustracker.models.LocationStats;
import com.javabrains.coronavirustracker.services.CoronavirusDataService;

@Controller
public class HomeController {
	@Autowired
	CoronavirusDataService coronavirusDataService;

	@GetMapping("/")
	public String home(Model model) {
		//Adding a comment for github test
		List<LocationStats> locationStatList = coronavirusDataService.getAllStats();
		//int sum =locationStatList.stream().map(locationStat->locationStat.getLatestTotalCases()).reduce(0,Integer::sum);
		int totalCases = locationStatList.stream().mapToInt(locationStat->locationStat.getLatestTotalCases()).sum();
		int totalNewCases = locationStatList.stream().mapToInt(locationStat->locationStat.getDeltaPrev()).sum();
		model.addAttribute("totalReportedCases", totalCases);
		model.addAttribute("totalNewCases", totalNewCases);
		model.addAttribute("locationStats", locationStatList);
		return "home";
	}
}
