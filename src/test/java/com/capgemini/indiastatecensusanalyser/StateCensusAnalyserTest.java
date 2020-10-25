package com.capgemini.indiastatecensusanalyser;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StateCensusAnalyserTest {
		private static final String CENSUS_DATA_PATH = ".\\src\\main\\java\\com\\capgemini\\resources\\IndiaStateCensusData.csv";
		private static final String CENSUS_DATA_PATH_INCORRECT_DELIMITER = ".\\src\\main\\java\\com\\capgemini\\resources\\IndiaStateCensusDataIncorrectDelimiter.csv";
		private static final String CENSUS_DATA_PATH_INCORRECT_HEADER = ".\\src\\main\\java\\com\\capgemini\\resources\\IndiaStateCensusDataIncorrectHeader.csv";
		private StateCensusAnalyser stateCensusAnalyser;

		@Before
		public void init() {
			stateCensusAnalyser = new StateCensusAnalyser();
		}

		@Test
		public void givenCensusCSVFile_ReturnsCorrectNoOfEntries() throws CensusAnalyserException {
			int noOfEntries = stateCensusAnalyser.loadCensusData(CENSUS_DATA_PATH);
			assertEquals(29, noOfEntries);
		}
		
		@Test
		public void givenIncorrectCSVFilePath_ThrowsCustomExceptionOfTypeInvalidFilePath(){
			try {
				stateCensusAnalyser.loadCensusData(CENSUS_DATA_PATH+"123");
			} catch (CensusAnalyserException e) {
				System.out.println(e.getMessage());
				assertEquals(CensusAnalyserException.ExceptionType.INVALID_FILE_PATH, e.type);
			}
		}
		
		@Test
		public void givenIncorrectDelimiter_ThrowsCustomExceptionOfTypeInalidDelimiter(){
			try {
				System.out.println(stateCensusAnalyser.loadCensusData(CENSUS_DATA_PATH_INCORRECT_DELIMITER));
			} catch (CensusAnalyserException e) {
				System.out.println(e.getMessage());
				assertEquals(CensusAnalyserException.ExceptionType.INVALID_DELIMITER, e.type);
			}
		}
		
		@Test
		public void givenIncorrectHeader_ThrowsCustomExceptionOfTypeInvalidHeader(){
			try {
				System.out.println(stateCensusAnalyser.loadCensusData(CENSUS_DATA_PATH_INCORRECT_HEADER));
			} catch (CensusAnalyserException e) {
				System.out.println(e.getMessage());
				assertEquals(CensusAnalyserException.ExceptionType.INVALID_HEADER, e.type);
			}
		}
	}
