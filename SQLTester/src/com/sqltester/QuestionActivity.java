package com.sqltester;

import com.example.sqltester.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

// Loads questions for users to answer
public class QuestionActivity extends Activity
{
	final Context context = this;
	SchemaServer m_SS = SchemaServer.getSchemaServer();
	int currentQuestion;
	String queryPreSet;

	// Question form views
	TextView questionNumber;
	TextView tableDesign;
	TextView questionPrompt;
	AutoCompleteTextView queryHelper;

	// Menu items, don't want to find multiple times
	MenuItem backward;
	MenuItem forward;

	public boolean killKeyboard()
	{
		View view = this.getWindow().getDecorView().findViewById(android.R.id.content);
		Rect r = new Rect();
		view.getWindowVisibleDisplayFrame(r);

		int heightDiff = view.getRootView().getHeight() - (r.bottom - r.top);
		if (heightDiff > 100)
		{
			InputMethodManager inputManager = (InputMethodManager) context
					.getSystemService(Context.INPUT_METHOD_SERVICE);
			inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),
					InputMethodManager.HIDE_NOT_ALWAYS);
		}
		return false;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question_form);

		Intent intent = getIntent();
		currentQuestion = intent.getIntExtra("QUESTION_NUM", 0);

		questionNumber = (TextView) findViewById(R.id.question_number);
		tableDesign = (TextView) findViewById(R.id.table_design);
		questionPrompt = (TextView) findViewById(R.id.problem);
		queryHelper = (AutoCompleteTextView) findViewById(R.id.query_entry);
		queryPreSet = intent.getStringExtra("USER_QUERY");

		setUpQuestion();
	}

	public void checkAnswer(View view)
	{
		if (Util.validSELECT(queryHelper.getText().toString()))
		{
    		Intent intent = new Intent(context, AnswerCheckerActivity.class);
    		intent.putExtra("QUESTION_NUM", currentQuestion);
    		intent.putExtra("USER_QUERY", queryHelper.getText().toString());
    		QuestionActivity.this.finish();
    		context.startActivity(intent);
		}
		else
		{
			Util.showDialog("Please enter a SELECT statement.", context, "");
		}
	}

	// Sets up a question given the number
	private void setUpQuestion()
	{
		// Clear contents if we're coming here from a different question
        queryHelper.setText("");

		// Set up simple title
		questionNumber.setText("Question #" + String.valueOf(currentQuestion + 1));

		// Get description of table we're supposed to use.
		tableDesign.setText(m_SS.serveTable(QuestionServer.getTableUsed(currentQuestion))
				.description());

		// Load the problem
		questionPrompt.setText(QuestionServer.getQuestion(currentQuestion));

		// Set up Auto Complete
		QueryACAdapter adapter = new QueryACAdapter(context,
				android.R.layout.simple_dropdown_item_1line, m_SS.serveTable(QuestionServer
						.getTableUsed(currentQuestion)), queryHelper);
		queryHelper.setAdapter(adapter);
		if (queryPreSet != null)
			queryHelper.setText(queryPreSet);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.question_menu, menu);
		backward = menu.findItem(R.id.backward);
		forward = menu.findItem(R.id.forward);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item)
	{
		killKeyboard();
        int numQuestions = QuestionServer.getNumQuestions();
		switch (item.getItemId())
		{
			case android.R.id.home:
				Intent intent = new Intent(context, MainActivity.class);
			    QuestionActivity.this.finish();
			    context.startActivity(intent);
				break;
			case R.id.backward:
				currentQuestion--;
                if (currentQuestion < 0)
                {
                    currentQuestion = numQuestions - 1;
                }
				setUpQuestion();
				break;
			case R.id.forward:
				currentQuestion++;
                if (currentQuestion == numQuestions)
                {
                    currentQuestion = 0;
                }
				setUpQuestion();
				break;
			default:
				break;
		}
		invalidateOptionsMenu();
		return super.onOptionsItemSelected(item);
	}
}
