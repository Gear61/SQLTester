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
import android.widget.ListView;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;


public class TestBedActivity extends Activity {
// this is the workbench page. all tables are shown
 // only the answer of the query is returned

    Context context = this;
    SchemaServer m_SS = SchemaServer.getSchemaServer();

    String queryPreSet;

    // Question form views


    ListView tableList;
    AutoCompleteTextView queryHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // Since TestBedActivity layout (tedbed_form) doesn not use all of the ids as Question Activity
        // must use Activity's oncreate. Thus this is more customizable, but still able to inherit all
        // fields of QuestionActivity


        super.onCreate(savedInstanceState);
        setContentView(R.layout.testbed_form);

        Intent intent = getIntent();


        tableList = (ListView) findViewById(R.id.tableList);
        queryHelper = (AutoCompleteTextView) findViewById(R.id.query_entry);
        queryPreSet = intent.getStringExtra("USER_QUERY");

        setUpSchemaDisplay();
    }

    private void setUpSchemaDisplay() //similar to setUpQuestion
    {


        TestTableAdapter tt = new TestTableAdapter(context);
        tableList.setAdapter(tt);

        SchemaServer ss = SchemaServer.getSchemaServer();

        Schema[] relevantTables = ss.allValidTables();

        // Set up Auto Complete
        QueryACAdapter adapter = new QueryACAdapter(context, android.R.layout.simple_dropdown_item_1line,
                relevantTables, queryHelper);
        queryHelper.setAdapter(adapter);
        if (queryPreSet != null)
            queryHelper.setText(queryPreSet);
    }



    public void answerQuery (View view)
    {
        if (Util.validSELECT(queryHelper.getText().toString()))
        {
            Intent intent = new Intent(context, TestAnswerActivity.class);

            intent.putExtra("USER_QUERY", queryHelper.getText().toString());
            TestBedActivity.this.finish();
            context.startActivity(intent);
        }
        else
        {
            Util.showDialog("Please enter a SELECT statement.", context, "");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    public boolean onOptionsItemSelected (MenuItem item)
    {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(context, MainActivity.class);
            TestBedActivity.this.finish();
            context.startActivity(intent);
        }

        return super.onOptionsItemSelected(item);

    }


}
