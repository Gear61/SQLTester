package com.sqltester;

import com.example.sqltester.R;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class QuestionAdapter extends BaseAdapter
{
	private Context context;
	private ListView questionListView;
	private String[] questionList = new String[QuestionServer.getNumQuestions()];
	
	// Creates the "Question 1, Question 2, etc..." list
	public QuestionAdapter(Context context, ListView questionListView)
	{
		this.context = context;
		
		// Set up ListView
		this.questionListView = questionListView;
		setClickListener();
		
		populateList();
	}
	
	// Starts new activity, passing question number
	private void setClickListener()
	{
		this.questionListView.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, final View view, int position, long id)
			throws IllegalArgumentException, IllegalStateException
			{
				Intent intent = new Intent(context, QuestionActivity.class);
			    intent.putExtra("QUESTION_NUM", position);
			    context.startActivity(intent);
			}
		});
	}
	
	// Fills in "Question 1, Question 2, etc..." list
	private void populateList()
	{
		for (int i = 1; i <= QuestionServer.getNumQuestions(); i++)
		{
			this.questionList[i-1] = "Question " + String.valueOf(i);
		}
	}

	public int getCount()
	{
		return questionList.length;
	}

	public Object getItem(int position)
	{
		return questionList[position];
	}

	public long getItemId(int position)
	{
		return position;
	}

	public static class ViewHolder
    {
        public ImageView item1;
        public TextView item2;
    }
    
	// Renders the ListView item that the user has scrolled to or is about to scroll to
    public View getView(int position, View convertView, ViewGroup parent)
	{
    	View v = convertView;
        final ViewHolder holder;
        if (v == null)
        {
            LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.question_item, null);
            holder = new ViewHolder();
            holder.item1 = (ImageView) v.findViewById(R.id.completion_status);
            holder.item2 = (TextView) v.findViewById(R.id.question_number);
            v.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)v.getTag();
        }
 
        final String question = questionList[position];
        if (question != null)
        {
        	// Set the check/x-mark. TODO: Need to update with database logic
        	holder.item1.setImageResource(R.drawable.x_mark_red);
        	
        	// Load in "Question X"
        	holder.item2.setText(question);
        }
        return v;
    }
}