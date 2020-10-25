package com.capgemini.indiastatecensusanalyser;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StateCensusAnalyserTest {

	private static final String censusDataPath = "C:\\Users\\shashi7\\eclipse-workspace\\indiastatecensusanalyser\\src\\main\\java\\com\\capgemini\\resources\\\\IndiaStateCensusData.csv";
	private StateCensusAnalyser stateCensusAnalyser;

	@Before
	public void init() {
		stateCensusAnalyser = new StateCensusAnalyser();
	}

	@Test
	public void givenCensusCSVFile_ReturnsCorrectNoOfEntries() {
		int noOfEntries = stateCensusAnalyser.loadCensusData(censusDataPath);
		assertEquals(7, noOfEntries);
	}

}
