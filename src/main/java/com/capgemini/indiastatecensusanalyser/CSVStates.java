package com.capgemini.indiastatecensusanalyser;

import com.opencsv.bean.CsvBindByName;

public class CSVStates {

	@CsvBindByName(column = "Sl.No")
	private int serialNo;

	@CsvBindByName(column = "State Name")
	private String stateName;

	@CsvBindByName(column = "StateCode")
	private String stateCode;

	@Override
	public String toString() {
		return "CSVStates [serialNo=" + serialNo + ", stateName=" + stateName + ", stateCode=" + stateCode + "]";
	}
}
