package com.testing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class GetDataFromFile {
	static Properties getProp = new Properties();
	static FileReader file;

	public static String getDataFromDataFile(String key) {
		try {
			file = new FileReader("allData.properties");
			getProp.load(file);

		} catch (FileNotFoundException e) {
			System.out.println("Data file is not found");
		} catch (IOException e) {
			System.out.println("Data file is not loaded");
		}
		return getProp.getProperty(key);

	}

	public static String getDataFrompathFile(String key) {
		try {
			file = new FileReader("allpaths.properties");
			getProp.load(file);

		} catch (FileNotFoundException e) {
			System.out.println("allpaths file is not found");
		} catch (IOException e) {
			System.out.println("allpaths file is not loaded");
		}
		return getProp.getProperty(key);

	}
}
