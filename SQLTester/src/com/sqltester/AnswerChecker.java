package com.sqltester;

import java.util.HashSet;

import android.content.Context;

public class AnswerChecker
{
	public MisterDataSource dataSource;

	public AnswerChecker(Context context)
	{
		dataSource = new MisterDataSource(context);
	}

	public boolean checkAnswer(int questionNumber, String userQuery)
	{
		String[][] userResult = dataSource.getData(userQuery);
		
		// HashSet to store the rows fetched by the users
		HashSet <String> UserAnswers = new HashSet <String>();
		for (int i = 0; i < userResult.length; i++)
		{
			// Have flying pie delimiters to separate our columns
			String currentRow = "~~|}";
			for (int j = 0; j < userResult[0].length; j++)
			{
				currentRow += (userResult[i][j] + "~~|}");
			}
			UserAnswers.add(currentRow);
		}
		
		String[][] correctAnswers = dataSource.getData(AnswerServer.getAnswer(questionNumber));
		if (userResult.length != correctAnswers.length)
		{
			return false;
		}
		
		for (int i = 0; i < correctAnswers.length; i++)
		{
			String currentRow = "~~|}";
			for (int j = 0; j < correctAnswers[0].length; j++)
			{
				currentRow += (correctAnswers[i][j] + "~~|}");
			}
			if (!UserAnswers.contains(currentRow))
			{
				return false;
			}
		}
		return true;
	}
}
