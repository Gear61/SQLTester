package com.sqltester;

public class AnswerServer
{
		// Answers stored here in this ghetto hard-coded array
		private static String[] answers =
              { "SELECT * FROM SALARIES;",
                "SELECT Professor_Name FROM SALARIES;",
                "SELECT DISTINCT Department FROM SALARIES;",
                "SELECT COUNT(Professor_Name) FROM SALARIES WHERE Salary > 150000;",
                "SELECT DEPARTMENT, SUM(Salary) FROM SALARIES GROUP BY DEPARTMENT;",
                "SELECT Professor_Name, Salary FROM SALARIES WHERE Salary = (SELECT MAX(Salary) FROM SALARIES);",
                "SELECT Professor_Name, Salary FROM SALARIES WHERE Department " +
                        "= \"Computer Science\" ORDER BY Salary DESC LIMIT 1;",
                "SELECT Professor_Name, Salary FROM SALARIES ORDER BY Salary DESC LIMIT 5;",
                "SELECT Professor_Name, Salary FROM SALARIES ORDER BY Salary ASC LIMIT 1;",
                "SELECT Department FROM SALARIES WHERE Professor_Name = \"Zaniolo\";",
                "SELECT Professor_Name FROM SALARIES WHERE Professor_Name LIKE 'C%';",
                "SELECT DISTINCT Salary from SALARIES S1 where 2 = " +
                        "(SELECT COUNT (DISTINCT SALARY) from SALARIES S2 where S1.SALARY < S2.SALARY);",
                "SELECT DISTINCT a.First_Name, a.Last_Name from CHECKED_OUT a, CHECKED_OUT b where a.Last_Name = b.Last_Name " +
                        "AND a.First_Name != b.First_Name;",
                "SELECT DISTINCT First_Name, Last_Name FROM CHECKED_OUT INNER JOIN BOOKS " +
                        "ON CHECKED_OUT.Book_ID = BOOKS.Book_ID WHERE Author = \"Terry Crews\";",
                "SELECT b.* from BOOKS b LEFT JOIN CHECKED_OUT c on b.Book_ID = c.Book_ID where c.Book_ID is null;" , //was just from books


                        //      "SELECT b.* from SALARIES s, CHECKED_OUT b where s.Professor_Name like CONCAT(\"%\", b.Last_Name, \"%\");",

                "SELECT * from BOOKS where Author like \"%Crew%\";",

                    /*     "SELECT s.*, t.nClasses from SALARIES s inner join " +
                                 "(Select Professor_Name, COUNT(CID) as nClasses from CLASSES GROUP BY Professor_Name) as t " +
                                 "on s.Professor_Name = t.Professor_Name ORDER BY t.nClasses" +
                                 "DESC LIMIT 1;" ,
                     */

                "SELECT Teacher, Max(nClasses) FROM (SELECT Teacher, COUNT(CID) as nClasses from CLASSES GROUP BY Teacher);",

                 "SELECT s.*, COUNT(t.CID) as nClasses from STUDENTS s inner join CLASSES t" +
                     " ON s.SID = t.SID group by s.SID ORDER BY nClasses DESC LIMIT 1;",

                 "SELECT s.* from STUDENTS s left join CLASSES c on s.SID = c.SID where c.SID is null;",

                  "SELECT s.* from SALARIES s left join CLASSES c on s.Professor_Name = c.Teacher " +
                          "where c.Teacher is null;"
               };
                //"MINUS " +
                //"(SELECT BOOKS.Book_ID, Book_Name, Author " +
                //"FROM BOOKS INNER JOIN CHECKED_OUT ON CHECKED_OUT.Book_ID = BOOKS.Book_ID);"};

		public static String getAnswer(int position)
		{
			return answers[position];
		}
}