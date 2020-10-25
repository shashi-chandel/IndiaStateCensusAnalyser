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

	public int loadCensusData(String censusDataPath) throws CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(censusDataPath));) {
			Iterator<IndiaStateCensus> censusIterator = new OpenCSVBuilder().getCSVFileIterator(reader, IndiaStateCensus.class);
			int noOfEntries = new OpenCSVBuilder().getCount(censusIterator);
			BufferedReader br = new BufferedReader(new FileReader(censusDataPath));
			String line = "";
			int ctr = 0;
			while ((line = br.readLine()) != null) {
				if (!line.contains(","))
					throw new CensusAnalyserException("Invalid delimiter",
							CensusAnalyserException.ExceptionType.INVALID_DELIMITER);
				if (ctr == 0) {
					String[] headers = line.split(",");
					if (!(headers[0].equals("State") && headers[1].equals("Population")
							&& headers[2].equals("AreaInSqKm") && headers[3].equals("DensityPerSqKm")))
						throw new CensusAnalyserException("Invalid headers",
								CensusAnalyserException.ExceptionType.INVALID_HEADER);
					ctr++;
				}
			}
			br.close();
			return noOfEntries;
		} catch (IOException e) {
			throw new CensusAnalyserException("Invalid file location",
					CensusAnalyserException.ExceptionType.INVALID_FILE_PATH);
		}
	}

	public int loadCodeData(String codeDataPath) throws CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(codeDataPath));) {
			Iterator<CSVStates> censusIterator = new OpenCSVBuilder().getCSVFileIterator(reader, CSVStates.class);
			int noOfEntries = new OpenCSVBuilder().getCount(censusIterator);
			BufferedReader br = new BufferedReader(new FileReader(codeDataPath));
			String line = "";
			int ctr = 0;
			while ((line = br.readLine()) != null) {
				if (!line.contains(","))
					throw new CensusAnalyserException("Invalid delimiter For Code Data",
							CensusAnalyserException.ExceptionType.INVALID_DELIMITER);
				if (ctr == 0) {
					String[] headers = line.split(",");
					if (!(headers[0].equals("SrNo") && headers[1].equals("State Name") && headers[2].equals("TIN")
							&& headers[3].equals("StateCode")))
						throw new CensusAnalyserException("Invalid header(s) For Code Data",
								CensusAnalyserException.ExceptionType.INVALID_HEADER);
					ctr++;
				}
			}
			br.close();
			return noOfEntries;
		} catch (IOException e) {
			throw new CensusAnalyserException("Invalid File Path For Code Data",
					CensusAnalyserException.ExceptionType.INVALID_FILE_PATH);
		}
	}
}