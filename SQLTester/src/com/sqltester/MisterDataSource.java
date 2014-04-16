package com.sqltester;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class MisterDataSource {
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;

	// Constructor
	public MisterDataSource(Context context)
	{
		dbHelper = new MySQLiteHelper(context);
	}

	// Open connection to database
	private void open() throws SQLException
	{
		database = dbHelper.getWritableDatabase();
	}

	// Terminate connection to database
	private void close()
	{
		dbHelper.close();
	}
	
	public String[][] getData(String queryString) {
		open();
		System.out.println("connection opened");
		Cursor cursor = database.rawQuery(queryString, null);
		System.out.println("query successful");
		int row = cursor.getCount(), col = cursor.getColumnCount();
		System.out.println("row is " + row);
		System.out.println("col is " + col);
		String[][] ourData = new String[row][col];
		int[] typeDict = new int[col];
		
		cursor.moveToNext();
		for (int i = 0; i < col; i++) {
			typeDict[i] = cursor.getType(i);
		}
		cursor.moveToPrevious();
		
		int eye = 0;
		while(cursor.moveToNext()) {
			for (int i = 0; i < col; i++) {
				switch (typeDict[i]) {
				case Cursor.FIELD_TYPE_STRING:
					ourData[eye][i] = cursor.getString(i);
					break;
					
				case Cursor.FIELD_TYPE_INTEGER:
					ourData[eye][i] = String.valueOf(cursor.getInt(i));
					break;
				
				case Cursor.FIELD_TYPE_FLOAT:
					ourData[eye][i] = String.valueOf(cursor.getFloat(i));
					break;
				
				case Cursor.FIELD_TYPE_NULL:
					ourData[eye][i] = null;
					break;
					
				case Cursor.FIELD_TYPE_BLOB:
					break;
				}
			}
			eye++;
		}
		cursor.close();
		close();
		return ourData;
	}
}
