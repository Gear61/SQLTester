package com.sqltester;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.example.sqltester.R;

public class AnswerCheckerActivity extends Activity
{
	final Context context = this;
	int questionNum;
	String userQuery;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		questionNum = intent.getIntExtra("QUESTION_NUM", 0);
		userQuery = intent.getStringExtra("USER_QUERY");
		AnswerChecker mrAnswer = new AnswerChecker(context);
		mrAnswer.checkAnswer(questionNum, userQuery);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
