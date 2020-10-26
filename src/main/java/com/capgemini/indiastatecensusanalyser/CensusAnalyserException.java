package com.capgemini.indiastatecensusanalyser;

public class CensusAnalyserException extends Exception {
	public enum ExceptionType{
		INVALID_FILE_PATH,INVALID_CLASS_TYPE, INVALID_DELIMITER, INVALID_HEADER, NO_CENSUS_DATA
	}
	public ExceptionType type;
	
	public CensusAnalyserException(String message,ExceptionType type) {
		super(message);
		this.type = type;
	}
}
