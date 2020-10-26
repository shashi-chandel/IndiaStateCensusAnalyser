package com.capgemini.indiastatecensusanalyser;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import com.google.gson.Gson;

public class CensusAnalyserTest {
	private static final String CENSUS_DATA_PATH = "C:\\Users\\shashi7\\eclipse-workspace\\indiastatecensusanalyser\\src\\main\\java\\com\\capgemini\\resources\\IndiaStateCensusData.csv";
	private static final String CENSUS_DATA_PATH_INCORRECT_DELIMITER = ".\\src\\main\\java\\com\\capgemini\\resources\\IndiaStateCensusDataIncorrectDelimiter.csv";
	private static final String CENSUS_DATA_PATH_INCORRECT_HEADER = ".\\src\\main\\java\\com\\capgemini\\resources\\IndiaStateCensusDataIncorrectHeader.csv";
	private static final String STATE_CODE_DATA_PATH = ".\\src\\main\\java\\com\\capgemini\\resources\\IndiaStateCode.csv";
	private static final String STATE_CODE_DATA_PATH_INCORRECT_DELIMITER = ".\\src\\main\\java\\com\\capgemini\\resources\\IndiaStateCodeIncorrectDelimiter.csv";
	private static final String STATE_CODE_DATA_PATH_INCORRECT_HEADER = ".\\src\\main\\java\\com\\capgemini\\resources\\IndiaStateCodeIncorrectHeader.csv";

	private StateCensusAnalyser stateCensusAnalyser;

	@Before
	public void init() {
		stateCensusAnalyser = new StateCensusAnalyser();
	}
	
	@Test
	public void givenIncorrectCSVFilePath_ThrowsCensusAnalyserExceptionOfTypeInvalidFilePath() {
		try {
			stateCensusAnalyser.loadCensusData(CENSUS_DATA_PATH + "123");
		} catch (CensusAnalyserException e) {
			System.out.println(e.getMessage());
			assertEquals(CensusAnalyserException.ExceptionType.INVALID_FILE_PATH, e.type);
		}
	}
	
	@Test
	public void givenIncorrectHeader_ThrowsCensusAnalyserExceptionOfTypeInvalidHeader(){
		try {
			System.out.println(stateCensusAnalyser.loadCensusData(CENSUS_DATA_PATH_INCORRECT_HEADER));
		} catch (CensusAnalyserException e) {
			System.out.println(e.getMessage());
			assertEquals(CensusAnalyserException.ExceptionType.INVALID_HEADER, e.type);
		}
	}
	
	@Test
	public void givenCodeCSVFile_ReturnsCorrectNoOfEntries() throws CensusAnalyserException {
		int noOfEntries = stateCensusAnalyser.loadCodeData(STATE_CODE_DATA_PATH);
		assertEquals(37, noOfEntries);
	}
	
	@Test
	public void givenIncorrectStateCodeCSVFilePath_ThrowsCodeAnalyserExceptionOfTypeInvalidFilePath(){
		try {
			stateCensusAnalyser.loadCodeData(STATE_CODE_DATA_PATH+"123");
		} catch (CensusAnalyserException e) {
			System.out.println(e.getMessage());
			assertEquals(CensusAnalyserException.ExceptionType.INVALID_FILE_PATH, e.type);
		}
	}
	
	@Test 
	public void givenIncorrectStateCodeCSVClassType_ThrowsCodeAnalyserExceptionOfTypeInvalidClassType() {
		try {
			stateCensusAnalyser.loadCodeData(STATE_CODE_DATA_PATH);
		} catch (CensusAnalyserException e) {
			System.out.println(e.getMessage());
			assertEquals(CensusAnalyserException.ExceptionType.INVALID_CLASS_TYPE, e.type);
		}
	}
	
	@Test
	public void givenIncorrectStateCodeCSVHeader_ThrowsCodeAnalyserExceptionOfTypeInvalidHeader(){
		try {
			System.out.println(stateCensusAnalyser.loadCodeData(STATE_CODE_DATA_PATH_INCORRECT_HEADER));
		} catch (CensusAnalyserException e) {
			System.out.println(e.getMessage());
			assertEquals(CensusAnalyserException.ExceptionType.INVALID_HEADER, e.type);
		}
	}
	
	@Test
	public void givenIndianCensusData_WhenSortedOnState_ShouldReturnSortedResult() throws CensusAnalyserException {
		String sortedCensusData = "";
		stateCensusAnalyser.loadCensusData(CENSUS_DATA_PATH);
		sortedCensusData = stateCensusAnalyser.getStateWiseSortedCensusData();
		IndiaStateCensus[] censusData = new Gson().fromJson(sortedCensusData, IndiaStateCensus[].class);
		assertEquals("Andhra Pradesh", censusData[0].getStateName());
	}
}