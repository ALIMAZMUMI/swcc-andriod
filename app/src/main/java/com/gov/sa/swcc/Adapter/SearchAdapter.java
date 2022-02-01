package com.gov.sa.swcc.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gov.sa.swcc.R;
import com.gov.sa.swcc.model.LeaveItems;
import com.gov.sa.swcc.model.SearchItem;

import java.util.List;

public class SearchAdapter extends ArrayAdapter<SearchItem> {

    private final Activity context;
    List<SearchItem> Titem;


    public SearchAdapter(Activity context, List<SearchItem> Titem) {
        // TODO Auto-generated constructor stub
      super(context,R.layout.activity_leave,Titem);
        this.context=context;
        this.Titem=Titem;


    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();

        SearchItem T=Titem.get(position);
        View rowView;


            rowView= inflater.inflate(R.layout.listitem, null, true);
            TextView Name = (TextView) rowView.findViewById(R.id.EmpName);
            TextView City = (TextView) rowView.findViewById(R.id.city);


        Name.setText( T.getName());
            City.setText(T.getCity());

        return rowView;

    }


}