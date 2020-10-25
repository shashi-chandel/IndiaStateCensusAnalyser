package com.capgemini.indiastatecensusanalyser;

public class CSVBuilderFactory {

	public static ICSVBuilder createCSVBuilder() {
		return new OpenCSVBuilder();
	}

}
