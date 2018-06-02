package com.example.siam.bitm_tourmate.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.siam.bitm_tourmate.ModelClass.Event_Pojo;
import com.example.siam.bitm_tourmate.R;

import java.util.ArrayList;

public class Event_listview extends BaseAdapter {

    Context context;

    ArrayList<Event_Pojo> event_pojos;
    LayoutInflater layoutInflater;

    public Event_listview(Context context, ArrayList<Event_Pojo> event_pojos) {
        this.context = context;
        this.event_pojos = event_pojos;

        layoutInflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return event_pojos.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View view1=layoutInflater.inflate(R.layout.custom__event_list_layout,null);
        TextView event_name=view1.findViewById(R.id.event_name_TV);
        TextView budget_name=view1.findViewById(R.id.budget_name_TV);
        TextView start_date=view1.findViewById(R.id.start_date_name_TV);
        Event_Pojo event_pojo=event_pojos.get(i);
        event_name.setText(event_pojo.getEvent_name());
        budget_name.setText(event_pojo.getBudget());
        start_date.setText(event_pojo.getStart_Location());
        return view1;
    }
}
