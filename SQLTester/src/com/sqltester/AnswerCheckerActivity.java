package com.sqltester;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
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
	Button retry;
	
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
		retry = (Button) findViewById(R.id.retry_question);
		
		displayResponse(mrAnswer.checkAnswer(questionNum, userQuery));
	}
	
	private void displayResponse(ResponseBundle score)
	{
		if (score.getWasCorrect())
		{
			verdict.setText("Congratulations! You got the correct answer!");
			if (questionNum != QuestionServer.getNumQuestions() - 1)
			{
				advance_forward.setVisibility(View.VISIBLE);
			}
		}
		else
		{
			retry.setVisibility(View.VISIBLE);
			verdict.setText("Your query was incorrect. Please try again.");
		}
		
		// They got it wrong
		if (score.userResults().getData() == null)
		{
			their_answers.setText("Your query wasn't a valid query.");
		}
		else if (score.userResults().getData().length == 0)
		{
			their_answers.setText("Your query didn't return anything.");
		}
		else
		{
			their_answers.setText("Here is what your query returned.");
			// Logic to display their table
			createTable((TableLayout) findViewById(R.id.their_answers_table),
						score.userResults().getColumns(), score.userResults().getData());
		}
		// Logic to display our answers table
		createTable((TableLayout) findViewById(R.id.correct_answers_table),
				score.correctAnswers().getColumns(), score.correctAnswers().getData());
	}
	
	public void createTable(TableLayout table, String[] columns, String[][] data)
	{
		TableLayout.LayoutParams params1 = new TableLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 0.1f);
		
		LinearLayout topRow = new LinearLayout(this);
		for (int i = 0; i < columns.length; i++)
		{
			TextView text = new TextView(this);
	        text.setText(columns[i].replace("_", " "));
	        text.setLayoutParams(params1);
	        text.setTypeface(null, Typeface.BOLD);
	        topRow.addView(text);
		}
		topRow.setOrientation(LinearLayout.HORIZONTAL);
		
		// add the TableRow to the TableLayout
		table.addView(topRow);
		
		for (int i = 0; i < data.length; i++)
		{
			LinearLayout tuple = new LinearLayout(this);
			for (int j = 0; j < columns.length; j++)
			{
				TextView text = new TextView(this);
		        text.setText(data[i][j]);
		        text.setLayoutParams(params1);
		        tuple.addView(text);
			}
			tuple.setOrientation(LinearLayout.HORIZONTAL);
			table.addView(tuple);
		}
	}
	
	public void retryQuestion(View view)
	{
		Intent intent = new Intent(context, QuestionActivity.class);
	    intent.putExtra("QUESTION_NUM", questionNum);
	    AnswerCheckerActivity.this.finish();
	    context.startActivity(intent);
	}
	
	public void advanceToNextQuestion(View view)
	{
		Intent intent = new Intent(context, QuestionActivity.class);
	    intent.putExtra("QUESTION_NUM", questionNum + 1);
	    AnswerCheckerActivity.this.finish();
	    context.startActivity(intent);
	}
	
	public void returnToQuestionList(View view)
	{
		Intent intent = new Intent(context, MainActivity.class);
	    AnswerCheckerActivity.this.finish();
	    context.startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu);
		getActionBar().setDisplayHomeAsUpEnabled(false);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case android.R.id.home:
				Intent intent = new Intent(context, QuestionActivity.class);
			    intent.putExtra("QUESTION_NUM", questionNum);
			    AnswerCheckerActivity.this.finish();
			    context.startActivity(intent);
				break;
			default:
				break;
		}
		invalidateOptionsMenu();
		return super.onOptionsItemSelected(item);
	}
}
