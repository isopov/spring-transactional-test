package com.sopovs.moradanen;

import java.io.File;
import java.io.IOException;

import javax.sql.DataSource;

import org.hsqldb.jdbc.JDBCDataSource;

public final class Utils {

	private Utils() {
	}

	public static DataSource createDataSource() {
		File dbFile;
		try {
			dbFile = File.createTempFile("hsqldb", "tmp");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		dbFile.deleteOnExit();
		JDBCDataSource result = new JDBCDataSource();
		result.setUrl("jdbc:hsqldb:file:" + dbFile.getAbsolutePath());
		result.setUser("test");
		return result;
	}

}
