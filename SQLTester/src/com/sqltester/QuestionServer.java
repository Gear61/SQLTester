package com.sqltester;

import java.util.ArrayList;
import java.util.Arrays;


// This class contains the questions our app contains
public class QuestionServer
{
    // Our singleton
    public static QuestionServer instance = null;

    private boolean isSingleType;

    // now this is all that needs to be called to determine which tables to use
    public void setType (boolean single) {
        isSingleType=single;

        if (isSingleType) {

           questions = new ArrayList<String> (Arrays.asList(singleTableQuestions));
            questionTablePairings = singleTableQuestionPairings;
            allQuestions = new ArrayList<Question> (Arrays.asList(allSinglQs));
        }
        else {
            questions = new ArrayList<String> (Arrays.asList(multiTableQuestions));
            questionTablePairings = multiTableQuestionPairings;
            allQuestions = new ArrayList<Question> (Arrays.asList(allMultiQs));
        }

        AnswerServer.setType(isSingleType);


    }

    private static int [][] questionTablePairings;
    private static ArrayList<String> questions;
	// Hooray for strange 0 indexing.
	// Question 1 -> Table 2 = {0, 1}, so the first element of this array would be 1
	private static int[][] singleTableQuestionPairings =
           {{1},
            {1},
            {1},
            {1},
            {1},
            {1},
            {1},
            {1},
            {1},
            {1},
            {1},
            {1},
            {2},
            {3},
            {5}
           };

        private static int [][] multiTableQuestionPairings = {
         {2, 3},
         {2, 3},
         {4,5},
         {4,5},
         {1,5}
         };



	// Questions stored here in this ghetto hard-coded array
	private static String[] singleTableQuestions =
           {"Write a query that outputs all of the contents of the table.",
            "Write a query that returns the names of all the professors in the table.",
            "Write a query that returns all departments in the table (no duplicates).",
            "Write a query that returns the number of professors whose salary is greater than 150000.",
            "Write a query that returns all departments and their aggregate salaries (in this column order).",
            "Write a query that returns the name and salary (in this column order) of the professor with the " +
                    "highest salary.",
            "Write a query that returns the name and salary (in this column order) of the professor with the " +
                    "highest salary in the \"Computer Science\" department.",
            "Write a query that returns the name and salaries (in this column order) of the Top 5 highest earning" +
                    " professors.",
            "Write a query that returns the name and salary (in this column order) of the lowest earning professor.",
            "Write a query that returns the department Professor \"Zaniolo\" works in.",
            "Write a query that returns all the professor names that begin with the letter 'C'.",
            "Write a query that returns the third highest salary in the table.",
            "Write a query that returns all people who share their last name with somebody else.",


            "Write a query that returns all books whose author's name contains the word 'Crew'.",
            "Query to obtain the teacher who teaches the most classes (name and how many he teaches)"
            };
          /// multi table based questions

    private static String[] multiTableQuestions = {
         "Write a query that returns the first and last name of all people who have checked out a book by Terry Crews.",
         "Write a query that returns all the books that haven't been checked out.",

         "Query to obtain the student with the most classes enrolled",
         "Query to return student with no classes enrolled",
         "Query to return all Professors that are not teaching any classes"
        };

    private ArrayList<Question> allQuestions;

    private Question[] allMultiQs = new Question[multiTableQuestions.length];
    private Question[] allSinglQs = new Question[singleTableQuestions.length];



    private QuestionServer ()
    {
        for (int i = 0; i < multiTableQuestions.length; i++)
        {
            allMultiQs[i] = new Question(multiTableQuestions[i], multiTableQuestionPairings[i]);
        }

        for (int i = 0; i < singleTableQuestions.length; i++)
        {
            allSinglQs[i] = new Question(singleTableQuestions[i], singleTableQuestionPairings[i]);
        }
        instance = this;
    }

    public static QuestionServer getQuestionServer()
    {
        if (instance == null)
        {
            instance = new QuestionServer();
        }
        return instance;
    }
	
	public static int getNumQuestions()
	{
		return questions.size();
	}
	
	public Question getQuestion(int position)
	{
		return allQuestions.get(position);
	}
}
