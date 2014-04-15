package com.sqltester;

// This class contains the questions our app contains

public class QuestionServer
{
	// Hooray for strange 0 indexing.
	// Question 1 -> Table 1 = {0,0}, so the first element of this array would be 0
	private static int[] questionTablePairings = {1, 1, 1};
	
	// Questions stored here in this ghetto hard-coded array
	private static String[] questions =
		{"Write a query that returns the names of all the professors in the table.",
		 "Write a query that returns all departments in the table (no duplicates).",
		 "Write a query that returns the number of professors whose salary is more than 150,000."};
	
	// Returns the index of the table that the question of interest uses
	public static int getTableUsed (int questionNum)
	{
		return questionTablePairings[questionNum];
	}
	
	public static int getNumQuestions()
	{
		return questions.length;
	}
	
	public static String getQuestion(int position)
	{
		return questions[position];
	}
}
