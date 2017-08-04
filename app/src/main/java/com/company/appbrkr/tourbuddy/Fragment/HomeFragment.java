package com.company.appbrkr.tourbuddy.Fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.company.appbrkr.tourbuddy.Class.EventDatabase;
import com.company.appbrkr.tourbuddy.Class.EventDatabaseOpenHelper;
import com.company.appbrkr.tourbuddy.Class.EventModel;
import com.company.appbrkr.tourbuddy.R;

import java.util.ArrayList;

/**
 * Created by Safkat on 6/23/2017.
 */

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private String eventName,eventDesti,eventDate,eventTime,eventDes,eventBudget,delId;
    private ArrayList<EventModel> eventList=new ArrayList<>();

    public HomeFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeList();
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.home_frag,container, false);

        recyclerView=(RecyclerView) rootView.findViewById(R.id.cardViewForHome);


        //Setting the Ui parameters of the Recycle View
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        if(eventList.size()>0 && recyclerView != null) {
            //Setting the Adapter of the recycle view
            recyclerView.setAdapter(new MyAdapter(eventList));
        }

        recyclerView.setLayoutManager(layoutManager);

        return rootView;


    }

    //Custom adapter class

    private static class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        private ArrayList<EventModel> list;

        //constructor to populate the list
        public MyAdapter(ArrayList<EventModel> data) {
            this.list=data;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //Setting up the ui

            View v=LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.event_item_for_home_frag,parent,false);
            //calling the custom view holder class

            MyViewHolder holder=new MyViewHolder(v);

            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

            //setting the parameters for the views

          //  holder.eName.setText(list.get(position).getEventName()); //using the EventModel class to get the values of ArrayList
            holder.eDesti.setText(list.get(position).getEventDesti());
            holder.eDate.setText(list.get(position).getEventDate());
            holder.eTime.setText(list.get(position).getEventTime());

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

    }

    private static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView eName,eDesti,eDate,eTime;

        public MyViewHolder(View v) {
            super(v);

            //initializing the views
           // eName=(TextView)v.findViewById(R.id.setENameHome);
            eDesti=(TextView)v.findViewById(R.id.setEDestinationHome);
            eDate=(TextView)v.findViewById(R.id.setEDateHome);
            eTime=(TextView)v.findViewById(R.id.setETimeHome);
        }
    }



    //initialize the list
    private void initializeList() {
        //getting data from the database and populating the list

        EventDatabaseOpenHelper mDbhelper=new EventDatabaseOpenHelper(getContext());
        SQLiteDatabase db=mDbhelper.getReadableDatabase();
        String query = "SELECT * FROM " + EventDatabase.DefineTable.TABLE_NAME;

        Cursor res = db.rawQuery(query,null);

        while (res.moveToNext()) {
            eventName=res.getString(0);
            eventDesti=res.getString(1);
            eventDate=res.getString(2);
            eventTime=res.getString(3);
            eventDes=res.getString(4);
            eventBudget=res.getString(5);
            delId=res.getString(6);
            eventList.add(new EventModel(eventName,eventDesti,eventDate,eventTime,eventDes,eventBudget,delId));
        }


    }

}
