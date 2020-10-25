package com.capgemini.indiastatecensusanalyser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyser {
	public int loadCensusData(String censusDataPath) {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(censusDataPath));
			CsvToBeanBuilder<CSVStates> csvToBeanBuilder = new CsvToBeanBuilder<CSVStates>(reader);
			csvToBeanBuilder.withType(CSVStates.class);
			CsvToBean<CSVStates> csvToBean = csvToBeanBuilder.build();
			Iterator<CSVStates> censusIterator = csvToBean.iterator();
			int noOfEntries = 0;
			while (censusIterator.hasNext()) {
				noOfEntries++;
				CSVStates censusData = censusIterator.next();
			}
			return noOfEntries;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
}