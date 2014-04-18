package com.sqltester;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class MisterDataSource
{
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
	
	public boolean addAnswer(int qNumi) {
		open();
		try {
			long ret = -1;
			String qNum = Integer.toString(qNumi);
			String query = "SELECT COUNT(Question_Number) FROM COMPLETION_STATUS WHERE Question_Number = \""+ qNum +"\";";
			Cursor cursor = database.rawQuery(query, null);
			cursor.moveToNext();
			if (cursor.getInt(0) == 0) {
				query = "INSERT INTO COMPLETION_STATUS VALUES ('" + qNum + "');";
				ContentValues cv = new ContentValues(1);
				cv.put("Question_Number", qNum);
				ret = database.insertOrThrow("COMPLETION_STATUS", null, cv);
				System.out.println("successfully inserted query!");
				cursor.close();
			} else {
				cursor.close();
				System.out.println("answer was answered correctly before");
			}
			close();
			if (ret >= 0)
				return true;
			else
				return false;
		} catch (SQLiteException e) {
			close();
			return false;
		}
	}
	
	public boolean checkAnswer(int qNumi) {
		open();
		try {
			String qNum = Integer.toString(qNumi);
			String query = "SELECT COUNT(Question_Number) FROM COMPLETION_STATUS WHERE Question_Number = \""+ qNum +"\";";
			Cursor cursor = database.rawQuery(query, null);
			cursor.moveToNext();
			if(cursor.getInt(0) > 0) {
				cursor.close();
				close();
				return true;
			} else {
				cursor.close();
				close();
				return false;
			}
		} catch (SQLiteException e) {
			close();
			return false;
		}
	}
	
	public void printTable() {
		System.out.println("inside printTable()");
		open();
		String query = "SELECT * FROM COMPLETION_STATUS;";
		Cursor cursor = database.rawQuery(query, null);
		int row = cursor.getCount();
		while (cursor.moveToNext()) {
			for (int i = 0; i < row; i++) {
				System.out.println(cursor.getString(0));
			}
		}
		cursor.close();
		close();
	}

	public ResultSet getData(String queryString)
	{
		open();
		try
		{
    		Cursor cursor = database.rawQuery(queryString, null);
    		int row = cursor.getCount(), col = cursor.getColumnCount();
    		String columns[] = new String[col];
    		for (int i = 0; i < col; i++)	
    		{
    			columns[i] = cursor.getColumnName(i);
    		}
    		
    		// If no data was gotten, return null
    		if (row == 0)
    		{
    			String[][] empty = {};
    			return new ResultSet(columns, empty);
    		}
    		
    		String[][] ourData = new String[row][col];
    		int[] typeDict = new int[col];
    
    		cursor.moveToNext();
    		for (int i = 0; i < col; i++)
    		{
    			typeDict[i] = cursor.getType(i);
    		}
    		cursor.moveToPrevious();
    
    		int eye = 0;
    		while (cursor.moveToNext())
    		{
    			for (int i = 0; i < col; i++)
    			{
    				switch (typeDict[i])
    				{
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
    		return new ResultSet(columns, ourData);
    	}
		catch (SQLiteException e)
		{
			return new ResultSet(null, null);
		}
	}
	
}
