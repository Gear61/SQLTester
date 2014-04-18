package com.sqltester;

public class AnswerServer
{
		// Ansewrs stored here in this ghetto hard-coded array
		private static String[] answers =
			{"SELECT Professor_Name FROM SALARIES;",
			 "SELECT DISTINCT Department FROM SALARIES;",
			 "SELECT COUNT(Professor_Name) FROM SALARIES WHERE Salary > 150000;",
			 "SELECT DEPARTMENT, SUM(SALARY) FROM SALARIES GROUP BY DEPARTMENT;",
			 "SELECT Professor_Name, Salary FROM SALARIES WHERE Salary = (SELECT MAX(Salary) FROM SALARIES);",
			 "SELECT Professor_Name, Salary FROM SALARIES WHERE Department " +
			 "= \"Computer Science\" ORDER BY Salary DESC LIMIT 1;"};
		
		public static String getAnswer(int position)
		{
			return answers[position];
		}
}