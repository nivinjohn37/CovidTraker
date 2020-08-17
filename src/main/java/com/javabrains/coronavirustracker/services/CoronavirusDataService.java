package com.javabrains.coronavirustracker.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.javabrains.coronavirustracker.models.LocationStats;

@Service
public class CoronavirusDataService {
	//this csv is obatined from : https://github.com/CSSEGISandData/COVID-19/tree/master/csse_covid_19_data
	private static String virus_data_url = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";
	List<LocationStats> allStats = new ArrayList<LocationStats>();

	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void fetchVirusData() throws URISyntaxException {
		List<LocationStats> newStats = new ArrayList<LocationStats>();
		RestTemplate restTemplate = new RestTemplate();
		URI uri = new URI(virus_data_url);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		try {
			Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader()
					.parse(new StringReader(result.getBody()));
			for (CSVRecord record : records) {
				LocationStats locationStats = new LocationStats();
				locationStats.setState(record.get("Province/State"));
				locationStats.setCountry(record.get("Country/Region"));
				locationStats.setLatestTotalCases(Integer.parseInt(record.get(record.size() - 1)));
				locationStats.setDeltaPrev(Integer.parseInt(record.get(record.size() - 1))
						- Integer.parseInt(record.get(record.size() - 2)));
				newStats.add(locationStats);
			}
			this.allStats = newStats;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @return the allStats
	 */
	public List<LocationStats> getAllStats() {
		return allStats;
	}

}
