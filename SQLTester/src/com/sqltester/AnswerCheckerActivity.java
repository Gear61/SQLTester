package com.sqltester;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sqltester.R;

// Evaluates the answer that the user gave from QuestionActivity
public class AnswerCheckerActivity extends Activity
{
	final Context context = this;
	int questionNum;
	String userQuery;
	
	// Evaluation XML views
	TextView verdict;
	TextView their_answers;
	Button advance_forward;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		// Grab relevant data needed to evaluate answers from Question Activity
		Intent intent = getIntent();
		questionNum = intent.getIntExtra("QUESTION_NUM", 0);
		userQuery = intent.getStringExtra("USER_QUERY");
		
		// Grab an evaluation of user's answer and display it
		AnswerChecker mrAnswer = new AnswerChecker(context);
		setContentView(R.layout.evaluation);
		
		verdict = (TextView) findViewById(R.id.verdict);
		their_answers = (TextView) findViewById(R.id.their_answers);
		advance_forward = (Button) findViewById(R.id.advance_forward);
		
		displayResponse(mrAnswer.checkAnswer(questionNum, userQuery));
	}
	
	private void displayResponse(ResponseBundle score)
	{
		if (score.getWasCorrect())
		{
			verdict.setText("Congratulations! You got the correct answer!");
			advance_forward.setVisibility(View.VISIBLE);
		}
		else
		{
			verdict.setText("Your query was incorrect. Please try again.");
		}
		if (score.userResults() == null)
		{
			their_answers.setText("Your query wasn't a valid query.");
		}
		else if (score.userResults().length == 0)
		{
			their_answers.setText("Your query didn't return anything.");
		}
		else
		{
			their_answers.setText("Here is what your query returned.");
		}
	}
	
	public void advanceToNextQuestion(View view)
	{
		AnswerCheckerActivity.this.finish();
		Intent intent = new Intent(context, QuestionActivity.class);
	    intent.putExtra("QUESTION_NUM", questionNum + 1);
	    context.startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
