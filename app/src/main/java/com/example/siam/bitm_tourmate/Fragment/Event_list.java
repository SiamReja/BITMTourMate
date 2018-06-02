package com.example.siam.bitm_tourmate.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.siam.bitm_tourmate.Adapter.Event_listview;
import com.example.siam.bitm_tourmate.ModelClass.Event_Pojo;
import com.example.siam.bitm_tourmate.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class Event_list extends Fragment {
    TextView add_event_TV;
    ListView Event_list_View;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String user_id;
    FirebaseAuth firebaseAuth;
    FirebaseUser  firebaseUser;
    String date="";

    String TAG = "            Event_Pojo list ";

    ArrayList<Event_Pojo> event_pojos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.event_list, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        add_event_TV = view.findViewById(R.id.add_event_TV);
        Event_list_View = view.findViewById(R.id.event_listView);

        event_pojos=new ArrayList<>();

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        user_id=firebaseUser.getUid();

        Log.e(TAG, "onViewCreated: "+user_id );

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                event_pojos.clear();
                Event_Pojo event_pojo=null;

                for (DataSnapshot snapshot:dataSnapshot.child("Event List").getChildren()){

                        event_pojo=snapshot.child("Jun 2, 2018 9:09:38 PM").getValue(Event_Pojo.class);
                        Log.e(TAG, "onDataChange: "+event_pojo.getBudget());
                        event_pojos.add(event_pojo);

                }
                Event_listview event_list_view=new Event_listview(getContext(),event_pojos);
                Event_list_View.setAdapter(event_list_view);
                if (!event_pojos.isEmpty()){
                    add_event_TV.setVisibility(View.GONE);
                    Event_list_View.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        add_event_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Alert();
            }
        });
    }

    public void Alert() {


        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        final LayoutInflater inflater = this.getLayoutInflater();


        final View dialogView;


        dialogView = inflater.inflate(R.layout.customdialogwithinput, null);
        dialogBuilder.setView(dialogView);
        final EditText event_Name_et = dialogView.findViewById(R.id.event_Name_ET_ID);
        final EditText starting_location_et = dialogView.findViewById(R.id.location_ET_ID);
        final EditText destination_et = dialogView.findViewById(R.id.destination_ET_ID);
        final EditText journey_date_et = dialogView.findViewById(R.id.journey_Date_ET_ID);
        final EditText budget_et = dialogView.findViewById(R.id.budget_ET_ID);


//        circleImageView = (CircleImageView) dialogView.findViewById(R.id.circle_image_view);

//set Action on Image view

//        circleImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setType("image/*")
//                        .setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "select image"), profile_image_Code);
//                select_img_ET.setVisibility(View.GONE);
//            }
//        });
        dialogBuilder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                submit_Data_To_Database(event_Name_et.getText().toString(), starting_location_et.getText().toString(),
                        destination_et.getText().toString(), journey_date_et.getText().toString(), budget_et.getText().toString());
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        }).setCancelable(false).create();

        dialogBuilder.show();
    }

    private void submit_Data_To_Database(String s, String s1, String s2, String s3, String s4) {
        Log.e(TAG, "submit_Data_To_Database: " + s);

        String date = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        Event_Pojo eventPojo = new Event_Pojo(s, s1, s2, s3, s4);
        databaseReference.child("Event List").child(user_id).child(date).setValue(eventPojo);


    }
}
