package com.sqltester;

// Represents a schema (collection of columns + names)
// Knows how to create and populate itself
public class Schema
{
	private Column[] columns;
	private String tableName;
	private String[][] rows;
	private String insertionTemplate;
	
	public Schema (String tableName, Column[] columns, String[][] rows)
	{
		this.columns = columns;
		this.tableName = tableName;
		this.rows = rows;
		
		// Initialize insertionTemplate base of "INSERT INTO TABLE_NAME (COLUMNS) VALUES )"
		insertionTemplate = "INSERT INTO " + tableName + " (";
		for (int i = 0; i < columns.length; i++)
		{
			insertionTemplate += columns[i].getRowName();
			if (i != columns.length - 1)
			{
				insertionTemplate += ", "; 
			}
		}
		insertionTemplate += ") VALUES (";
	}
	
	public String getName()
	{
		return tableName;
	}
	
	public Column[] getColumns()
	{
		return columns;
	}
	
	// Returns a string describing itself to the app user
	// Example: SALARIES (Professor_Name (TEXT), Department (TEXT), SALARY (INT))
	public String description()
	{
		String description = tableName + " (";
		for (int i = 0; i < columns.length; i++)
		{
			description += columns[i].getRowName() + " (" + columns[i].getDataType() + ")";
			if (i != columns.length - 1)
			{
				description += ", ";
			}
		}
		description += ")";
		return description;
	}
	
	// Returns statement to create this schema in DB
	public String creationStatement()
	{
		// Database creation sql statements
		String creationStatement = "CREATE TABLE " + tableName + "(";
		for (int i = 0; i < columns.length; i++)
		{
			if (i == columns.length - 1)
			{
				creationStatement += columns[i].getRowName() + " " + columns[i].getDataType() + ");";
			}
			else
			{
				creationStatement += columns[i].getRowName() + " " + columns[i].getDataType() + ", ";
			}
		}
		return creationStatement;
	}
	
	// Returns an array of insertion statements to populate itself
	public String[] insertStatements()
	{
		if (rows.length == 0)
		{
			return null;
		}
		
		// Create array equal to # of rows we have
		String[] inserts = new String[rows.length];
		
		// For each row...
		for (int i = 0; i < rows.length; i++)
		{
			// Start with the template "INSERT INTO TABLE_NAME (COLUMNS) VALUES ("
			inserts[i] = insertionTemplate;
			for (int j = 0; j < columns.length; j++)
			{
				if (columns[j].getDataType().equals("TEXT"))
				{
					inserts[i] += "\"";
				}
				inserts[i] += rows[i][j];
				if (columns[j].getDataType().equals("TEXT"))
				{
					inserts[i] += "\"";
				}
				
				if (j != columns.length - 1)
				{
					inserts[i] += ", "; 
				}
			}
			inserts[i] += ");";
		}
		return inserts;
	}
}
