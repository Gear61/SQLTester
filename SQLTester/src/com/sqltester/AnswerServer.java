package com.sqltester;

public class AnswerServer
{
		// Ansewrs stored here in this ghetto hard-coded array
		private static String[] answers =
			{"SELECT Professor_Name FROM SALARIES;",
			 "SELECT DISTINCT Department FROM SALARIES;",
			 "SELECT COUNT(Professor_Name) FROM SALARIES WHERE Salary > 150000;"};
		
		public static String getAnswer(int position)
		{
			return answers[position];
		}
}