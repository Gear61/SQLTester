package com.sqltester;

import com.example.sqltester.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.widget.ListView;

public class MainActivity extends Activity
{
	final Context context = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		// Populate the list, attach adapter to it
		setContentView(R.layout.question_list);
		final ListView questionList = (ListView) findViewById(R.id.questionList);
		QuestionAdapter questionAdapter = new QuestionAdapter(context, questionList);
		questionList.setAdapter(questionAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
