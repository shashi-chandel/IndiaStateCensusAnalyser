package com.capgemini.indiastatecensusanalyser;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class StateCensusAnalyserTest {
	private static final String CENSUS_DATA_PATH = "C:\\Users\\shashi7\\eclipse-workspace\\indiastatecensusanalyser\\src\\main\\java\\com\\capgemini\\resources\\IndianStateCodes.csv";
	private StateCensusAnalyser stateCensusAnalyser;

	@Before
	public void init() {
		stateCensusAnalyser = new StateCensusAnalyser();
	}

	@Test
	public void givenCensusCSVFile_ReturnsCorrectNoOfEntries() {
		int noOfEntries = stateCensusAnalyser.loadCensusData(CENSUS_DATA_PATH);
		assertEquals(29, noOfEntries);
	}
}
