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


public class TestAnswerActivity extends Activity {

    final Context context = this;

    String userQuery;


    TextView their_answers, user_query;

    Button retry, give_up;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Grab relevant data needed to evaluate answers from Question Activity
        Intent intent = getIntent();

        userQuery = intent.getStringExtra("USER_QUERY");

        // Grab an evaluation of user's answer and display it
        MisterDataSource mrAnswer = new MisterDataSource(context);
        mrAnswer.refreshTables();

        setContentView(R.layout.query_content);

        user_query = (TextView) findViewById(R.id.user_query);
        user_query.setText(userQuery); //shows what the user had put in the textbox previously

        their_answers = (TextView) findViewById(R.id.their_answers);

        retry = (Button) findViewById(R.id.retry_question);
        give_up = (Button) findViewById(R.id.return_main);

        //get query result
        ResultSet result = mrAnswer.getData(userQuery);


        displayResult (result);
    }


    public void displayResult (ResultSet userQuery)
    {
        if (userQuery.getData() == null)
        {
            their_answers.setText("The query entered was invalid; nothing returned");
        }
        else if (userQuery.getData().length == 0)
        {
            their_answers.setText("The query returned no rows");
        }
        else {
            their_answers.setText("The following was returned from the query:");

            createTable((TableLayout) findViewById(R.id.their_answers_table),
                    userQuery.getColumns(), userQuery.getData());
        }


    }

    public void returnToMain (View view)
    {
        Intent intent = new Intent(context, MainActivity.class);
        TestAnswerActivity.this.finish();
        context.startActivity(intent);
    }


    public void retryQuery (View view)
    {
        Intent intent = new Intent(context, TestBedActivity.class);
        intent.putExtra("USER_QUERY", userQuery);
        TestAnswerActivity.this.finish();
        context.startActivity(intent);
    }


    // i know i should have inherited. If anything this should be in util or something
    // since this could be used anywhere
    public void createTable(TableLayout table, String[] columns, String[][] data)
    {
        TableLayout.LayoutParams params1 = new TableLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 0.1f);

        LinearLayout topRow = new LinearLayout(this);
        for (int i = 0; i < columns.length; i++)
        {
            TextView text = new TextView(this);
            text.setText(columns[i]);
            text.setLayoutParams(params1);
            text.setTypeface(null, Typeface.BOLD);
            text.setTextColor(getResources().getColor(R.color.Green_GreenYellow));
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
                text.setTextColor(getResources().getColor(R.color.Green_GreenYellow));
                tuple.addView(text);
            }
            tuple.setOrientation(LinearLayout.HORIZONTAL);
            table.addView(tuple);
        }
    }
}
