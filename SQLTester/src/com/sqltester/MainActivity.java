package com.sqltester;

import com.example.sqltester.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

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
		QuestionAdapter questionAdapter = new QuestionAdapter(context);
		questionList.setAdapter(questionAdapter);
		questionList.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, final View view, int position, long id)
			throws IllegalArgumentException, IllegalStateException
			{
				Intent intent = new Intent(context, QuestionActivity.class);
			    intent.putExtra("QUESTION_NUM", position);
			    MainActivity.this.finish();
			    context.startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
