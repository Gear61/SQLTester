package com.sqltester;

public class AnswerServer
{
		// Ansewrs stored here in this ghetto hard-coded array
		private static String[] answers =
			{"SELECT * FROM SALARIES;",
			 "SELECT Professor_Name FROM SALARIES;",
			 "SELECT DISTINCT Department FROM SALARIES;",
			 "SELECT COUNT(Professor_Name) FROM SALARIES WHERE Salary > 150000;",
			 "SELECT DEPARTMENT, SUM(Salary) FROM SALARIES GROUP BY DEPARTMENT;",
			 "SELECT Professor_Name, Salary FROM SALARIES WHERE Salary = (SELECT MAX(Salary) FROM SALARIES);",
			 "SELECT Professor_Name, Salary FROM SALARIES WHERE Department " +
			 "= \"Computer Science\" ORDER BY Salary DESC LIMIT 1;",
			 "SELECT Professor_Name, Salary FROM SALARIES ORDER BY Salary DESC LIMIT 5;",
			 "SELECT Professor_Name, Salary FROM SALARIES ORDER BY Salary ASC LIMIT 1;",
			 "SELECT Department FROM SALARIES WHERE Professor_Name = \"Zaniolo\";"};
		
		public static String getAnswer(int position)
		{
			return answers[position];
		}
}