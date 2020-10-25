package com.capgemini.indiastatecensusanalyser;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StateCensusAnalyserTest {

	private static final String CENSUS_DATA_PATH = "C:\\Users\\shashi7\\eclipse-workspace\\indiastatecensusanalyser\\src\\main\\java\\com\\capgemini\\resources\\\\IndiaStateCensusData.csv";
	private static final String CENSUS_DATA_PATH_INCORRECT_DELIMITER = ".\\src\\main\\java\\com\\capgemini\\resources\\IndiaStateCensusDataIncorrectDelimiter.csv";
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

	@Test
	public void givenIncorrectCSVFilePath_ThrowsCustomException(){
		try {
			stateCensusAnalyser.loadCensusData(CENSUS_DATA_PATH+"123");
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.ExceptionType.INVALID_FILE_PATH, e.type);
		}
	}

	@Test
	public void givenIncorrectDelimiter_ThrowsCustomException(){
		try {
			System.out.println(stateCensusAnalyser.loadCensusData(CENSUS_DATA_PATH_INCORRECT_DELIMITER));
		} catch (CensusAnalyserException e) {
			System.out.println(e.getMessage());
			assertEquals(CensusAnalyserException.ExceptionType.INVALID_DELIMITER, e.type);
		}
	}

}