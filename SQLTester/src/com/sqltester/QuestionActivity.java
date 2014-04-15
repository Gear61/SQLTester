package com.sqltester;

import com.example.sqltester.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

public class QuestionActivity extends Activity
{
	final Context context = this;
	SchemaServer m_SS = SchemaServer.getSchemaServer();
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question_form);
		
		Intent intent = getIntent();
		int questionNum = intent.getIntExtra("QUESTION_NUM", 0);
		setUpQuestion(questionNum);
	}
	
	// Sets up a question given the number
	private void setUpQuestion(int questionNum)
	{
		// Get description of table we're supposed to use.
		TextView tableDesign = (TextView) findViewById(R.id.table_design);
		tableDesign.setText(m_SS.serveTable(QuestionServer.getTableUsed(questionNum)).description());
				
		// Load the problem
		TextView questionPrompt = (TextView) findViewById(R.id.problem);
		questionPrompt.setText(QuestionServer.getQuestion(questionNum));
				
		// Set up Auto Complete 
		AutoCompleteTextView queryHelper = (AutoCompleteTextView)findViewById(R.id.query_entry);
		QueryACAdapter adapter = new QueryACAdapter(context, android.R.layout.simple_dropdown_item_1line,
													m_SS.serveTable(QuestionServer.getTableUsed(questionNum)),
													queryHelper);
		queryHelper.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
