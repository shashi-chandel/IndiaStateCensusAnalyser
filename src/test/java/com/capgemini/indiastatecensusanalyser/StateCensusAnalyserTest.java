package com.capgemini.indiastatecensusanalyser;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class StateCensusAnalyserTest {
	private static final String CENSUS_DATA_PATH = "C:\\Users\\shashi7\\eclipse-workspace\\indiastatecensusanalyser\\src\\main\\java\\com\\capgemini\\resources\\IndianStateCodes.csv";
	public static final String WRONG_CSV_FILE_PATH = "C:\\Users\\shashi7\\eclipse-workspace\\indiastatecensusanalyser\\src\\main\\java\\com\\capgemini\\resources\\aab.csv";
	public static final String WRONG_CSV_FILE_TYPE = "C:\\\\Users\\\\shashi7\\\\eclipse-workspace\\\\indiastatecensusanalyser\\\\src\\\\main\\\\java\\\\com\\\\capgemini\\\\resources\\\\IndianStateCodesWithIncorrectType.csv";
	private StateCensusAnalyser stateCensusAnalyser;

	@Before
	public void init() {
		stateCensusAnalyser = new StateCensusAnalyser();
	}

	@Test
	public void givenCensusCSVFile_ReturnsCorrectNoOfEntries() throws CensusAnalyserException{
		int noOfEntries = stateCensusAnalyser.loadCensusData(CENSUS_DATA_PATH);
		assertEquals(29, noOfEntries);
	}
	
	@Test(expected = CensusAnalyserException.class)
	public void givenIncorrectCensusCSVFile_ShouldThrowCensusAnalyserException() throws CensusAnalyserException {
		stateCensusAnalyser.loadCensusData(WRONG_CSV_FILE_PATH);
	}
	
	@Test(expected = CensusAnalyserException.class)
	public void givenIncorrectCSVFileType_ShouldThrowCensusAnalyserException() throws CensusAnalyserException {
		stateCensusAnalyser.loadCensusData(WRONG_CSV_FILE_TYPE);
	}
	
}
