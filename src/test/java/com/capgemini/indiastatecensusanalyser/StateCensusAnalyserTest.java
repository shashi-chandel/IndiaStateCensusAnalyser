package com.capgemini.indiastatecensusanalyser;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class StateCensusAnalyserTest {
	private static final String CENSUS_DATA_PATH = "C:\\Users\\shashi7\\eclipse-workspace\\indiastatecensusanalyser\\src\\main\\java\\com\\capgemini\\resources\\IndianStateCodes.csv";
	public static final String WRONG_CSV_FILE_PATH = "C:\\Users\\shashi7\\eclipse-workspace\\indiastatecensusanalyser\\src\\main\\java\\com\\capgemini\\resources\\aab.csv";
	public static final String WRONG_CSV_FILE_TYPE = "C:\\\\Users\\\\shashi7\\\\eclipse-workspace\\\\indiastatecensusanalyser\\\\src\\\\main\\\\java\\\\com\\\\capgemini\\\\resources\\\\IndianStateCodesWithIncorrectType.csv";
	public static final String CSV_FILE_WITH_INVALID_DELIMITER = "C:\\Users\\shashi7\\eclipse-workspace\\indiastatecensusanalyser\\src\\main\\java\\com\\capgemini\\resources\\IndianStateCodesWithInvalidDelimiter.csv";
	public static final String CSV_FILE_WITH_INVALID_HEADER = "C:\\Users\\shashi7\\eclipse-workspace\\indiastatecensusanalyser\\src\\main\\java\\com\\capgemini\\resources\\IndianStateCodesWithInvalidHeader.csv";
	public StateCensusAnalyser stateCensusAnalyser;

	@Before
	public void init() {
		stateCensusAnalyser = new StateCensusAnalyser();
	}

	@Test
	public void givenCensusCSVFile_ReturnsCorrectNoOfEntries() throws CensusAnalyserException {
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

	@Test
	public void givenCSVFile_WithInvalidDelimiter_ShouldThrowCensusAnalyserException() {
		try {
			System.out.println(stateCensusAnalyser.loadCensusData(CSV_FILE_WITH_INVALID_DELIMITER));
		} catch (CensusAnalyserException e) {
			System.out.println(e.getMessage());
			assertEquals(CensusAnalyserException.ExceptionType.INVALID_DELIMITER, e.type);
		}
	}

	@Test
	public void givenCSVFile_WithInvalidHeader_ShouldThrowCensusAnalyserException() {
		try {
			System.out.println(stateCensusAnalyser.loadCensusData(CSV_FILE_WITH_INVALID_HEADER));
		} catch (CensusAnalyserException e) {
			System.out.println(e.getMessage());
			assertEquals(CensusAnalyserException.ExceptionType.INVALID_HEADER, e.type);
		}
	}

}
