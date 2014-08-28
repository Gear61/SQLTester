package com.sqltester;

import com.example.sqltester.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity
{

    final Context context = this;
    Button test_bed, single, multi;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        QuestionServer.getQuestionServer(); // instantiate them before they need to be used
        SchemaServer.getSchemaServer();


        test_bed =      (Button) findViewById(R.id.test_bed);
        single = (Button) findViewById(R.id.single_type);
        multi = (Button) findViewById(R.id.multi_type);
    }


    public void goToTestBed (View view)
    {
        Intent intent = new Intent(context, TestBedActivity.class);
        MainActivity.this.finish();
        context.startActivity(intent);
    }

    public void goToSingles (View view)
    {
        Intent intent = new Intent(context, QuestionListActivity.class);
        intent.putExtra("TYPE", "SINGLE");
        MainActivity.this.finish();
        context.startActivity(intent);
    }

    public void goToMultis (View view)
    {
        Intent intent = new Intent(context, QuestionListActivity.class);
        intent.putExtra("TYPE", "MULTI");
        MainActivity.this.finish();
        context.startActivity(intent);
    }

}
