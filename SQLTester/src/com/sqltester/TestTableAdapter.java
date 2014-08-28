package com.sqltester;
import com.example.sqltester.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TestTableAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<String> allDecriptions = SchemaServer.getSchemaServer().serveAllDescriptions();



    public TestTableAdapter(Context context) {
        this.context = context;
    }

    public int getCount()
    {
        return allDecriptions.size();
    }

    public Object getItem(int position)
    {
        return allDecriptions.get(position);
    }
    public long getItemId(int position)
    {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v = convertView;
        TextView item;
        if (v == null)
        {
            LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.table_item, null);
            item = (TextView) v.findViewById(R.id.table_block);
            v.setTag(item);
        }
        else
        {
            item = (TextView) v.getTag();
        }
        final String desc = allDecriptions.get(position);
        if (desc != null)
        {
            item.setText(desc);
        }
        return v;
    }

}
