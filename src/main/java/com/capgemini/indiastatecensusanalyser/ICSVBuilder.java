package com.capgemini.indiastatecensusanalyser;

import java.io.Reader;
import java.util.Iterator;

public interface ICSVBuilder {
	public<E> Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws CensusAnalyserException;
}