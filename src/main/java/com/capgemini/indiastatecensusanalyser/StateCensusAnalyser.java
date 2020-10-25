package com.capgemini.indiastatecensusanalyser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import com.capgemini.indiastatecensusanalyser.CensusAnalyserException.ExceptionType;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyser {
	public int loadCensusData(String censusDataPath) throws CensusAnalyserException{
		try {
			Reader reader = Files.newBufferedReader(Paths.get(censusDataPath));
			CsvToBeanBuilder<CSVStates> csvToBeanBuilder = new CsvToBeanBuilder<CSVStates>(reader);
			csvToBeanBuilder.withType(CSVStates.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<CSVStates> csvToBean = csvToBeanBuilder.build();
			BufferedReader br = new BufferedReader(new FileReader(censusDataPath));
			String line = "";
			while ((line = br.readLine()) != null) {
				if (!line.contains(","))
					throw new CensusAnalyserException("Invalid delimiter in csv file", ExceptionType.INVALID_DELIMITER);
			}
			br.close();
			Iterator<CSVStates> censusIterator = csvToBean.iterator();
			int noOfEntries = 0;
			while (censusIterator.hasNext()) {
				noOfEntries++;
				CSVStates censusData = censusIterator.next();
				System.out.println(censusData);
			}
			return noOfEntries;
		} catch (IOException e) {
			throw new CensusAnalyserException("Invalid CSV file location", ExceptionType.INVALID_FILE_PATH);
		} catch (IllegalStateException e) {
			throw new CensusAnalyserException("Invalid type", ExceptionType.INVALID_CLASS_TYPE);
		}
	}
}