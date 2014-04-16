package com.sqltester;

// Contains:
// 1. Boolean indicating whether or not the user got the correct answer
// 2. 2D String array containing the USER'S answers (NULL if bad query)
// 3. 2D String array containing the CORRECY answers

public class ResponseBundle
{
	private Boolean wasCorrect;
	private String[][] userResults;
	private String[][] correctAnswers;
	
	public ResponseBundle (Boolean wasCorrect, String[][] userResults, String[][] correctAnswers)
	{
		this.wasCorrect = wasCorrect;
		this.userResults = userResults;
		this.correctAnswers = correctAnswers;
	}
	
	public Boolean getWasCorrect()
	{
		return wasCorrect;
	}
	
	public String[][] userResults()
	{
		return userResults;
	}
	
	public String[][] correctAnswers()
	{
		return correctAnswers;
	}
}
