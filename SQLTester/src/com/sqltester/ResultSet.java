package com.sqltester;

public class ResultSet
{
	private String[] columns;
	private String[][] data;
	
	public ResultSet (String[] columns, String [][] data)
	{
		this.columns = columns;
		this.data = data;
	}
	
	public String[] getColumns()
	{
		return columns;
	}
	
	public String[][] getData()
	{
		return data;
	}
}
