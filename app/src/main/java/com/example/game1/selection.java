package com.example.game1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class selection extends AppCompatActivity {
    int  time = 59;
    DatabaseReference reference;
    TextView textView;
    EditText editText;
    Button play;
    CheckBox name,place,thing,animal,bird,fruit_Veg,movie,song;
    Boolean f_name,f_place,f_thing,f_animal,f_bird,f_fruit,f_movie,f_song;
    Boolean flags_solo,flags_team,flags_create;
    List<String> id_solo=new ArrayList<>();
    List<String> id_create=new ArrayList<>();
    List<String> id_team=new ArrayList<>();
    TextView coin;
    TextView hinthere;
    TextView coinhere;
    Database mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        flags_create=getIntent().getExtras().getBoolean("flags_create");
        flags_solo=getIntent().getExtras().getBoolean("flags_solo");
        flags_team=getIntent().getExtras().getBoolean("flags_team");
        name=(CheckBox)findViewById(R.id.Name);
        coinhere=(TextView) findViewById(R.id.hint);
        hinthere=(TextView) findViewById(R.id.coin);
        place=(CheckBox)findViewById(R.id.Place);
        thing=(CheckBox)findViewById(R.id.Things);
        animal=(CheckBox)findViewById(R.id.Animals);
        bird=(CheckBox)findViewById(R.id.Birds);
        fruit_Veg=(CheckBox)findViewById(R.id.Fruits);
        movie=(CheckBox)findViewById(R.id.Movies);
        song=(CheckBox)findViewById(R.id.Songs);
        play=(Button)findViewById(R.id.Play);
        coin=(TextView)findViewById(R.id.coin);
        editText=(EditText)findViewById(R.id.editText);
        reference = FirebaseDatabase.getInstance().getReference();
        reference.child("solo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
          //          id_solo.add(postSnapshot.child("player_total").getValue().toString());
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        /*
        reference.child("team").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    id_team.add(postSnapshot.child("id").getValue().toString());
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        reference.child("create").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    id_create.add(postSnapshot.child("id").getValue().toString());
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });*/
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {time=59;
                if(flags_team==true)
                { set_visibility();
                        Intent intent=new Intent(selection.this,MainActivity.class);
                        intent.putExtra("flags_team",flags_team);
                        intent.putExtra("f_name",f_name);
                        intent.putExtra("f_animal",f_animal);
                        intent.putExtra("f_bird",f_bird);
                        intent.putExtra("f_fruit",f_fruit);
                        intent.putExtra("f_movie",f_movie);
                        intent.putExtra("f_place",f_place);
                        intent.putExtra("f_song",f_song);
                        intent.putExtra("f_thing",f_thing);
                        startActivity(intent);
                        //finish();
                    //  setuserdata_forteam_mode();
                }
                if(flags_create==true)
                { set_visibility();
                    Intent intent=new Intent(selection.this,MainActivity.class);
                    intent.putExtra("flags_create",flags_create);
                    intent.putExtra("f_name",f_name);
                    intent.putExtra("f_animal",f_animal);
                    intent.putExtra("f_bird",f_bird);
                    intent.putExtra("f_fruit",f_fruit);
                    intent.putExtra("f_movie",f_movie);
                    intent.putExtra("f_place",f_place);
                    intent.putExtra("f_song",f_song);
                    intent.putExtra("f_thing",f_thing);
                    startActivity(intent);
                    //finish();
                    //setuserdata_forcreate_mode();
                }
                if(flags_solo==true)
                {final AlertDialog.Builder builder = new AlertDialog.Builder(selection.this);
                    builder.setTitle("Fetching For Players");
                    builder.setMessage("start time");
                    builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    final AlertDialog alert = builder.create();
                    alert.show();
                    new CountDownTimer(60000, 1000) {
                        @Override
                        public void onTick(long l) {
                            alert.setMessage("Remaining Time : "+checkDigit(time));
                            time--;
                            if(time==random_generator(1,60))
                            {
                                alert.setMessage("Player Found");
                                Intent intent=new Intent(selection.this,MainActivity.class);
                                intent.putExtra("flags_solo",flags_solo);
                                intent.putExtra("f_name",f_name);
                                intent.putExtra("f_animal",f_animal);
                                intent.putExtra("f_bird",f_bird);
                                intent.putExtra("f_fruit",f_fruit);
                                intent.putExtra("f_movie",f_movie);
                                intent.putExtra("f_place",f_place);
                                intent.putExtra("f_song",f_song);
                                intent.putExtra("f_thing",f_thing);
                                startActivity(intent);
                            }
                        }
                        @Override
                        public void onFinish() {
                            alert.setMessage("No Player Found");
                        }
                    }.start();
                    set_visibility();
                }

            }
        });
        if(flags_team==true)
        {
            name.setVisibility(View.INVISIBLE);
            movie.setVisibility(View.INVISIBLE);
            song.setVisibility(View.INVISIBLE);
            editText.setVisibility(View.INVISIBLE);

        }
        else if(flags_solo==true)
        {
            movie.setVisibility(View.INVISIBLE);
            name.setVisibility(View.INVISIBLE);
            song.setVisibility(View.INVISIBLE);
            editText.setVisibility(View.INVISIBLE);
        }
        else if(flags_create==true)
        {
            movie.setVisibility(View.VISIBLE);
            name.setVisibility(View.VISIBLE);
            song.setVisibility(View.VISIBLE);
            editText.setVisibility(View.VISIBLE);
        }
    }
    public int random_generator(int min,int max)
    {
        final int random = new Random().nextInt((max - min) + 1) + min;
        return random;
    }
    public void setuserdata_forteam_mode()
    {
        // if(id_team==null)
        //reference.child("team").push().setValue(random_generator());
    }
    public void setuserdata_forcreate_mode()
    {
        /*if(!editText.getText().equals(null))
            reference.child("create").push().setValue(editText.getText().toString());*/
    }
    public void setuserdata_forsolo_mode()
    {
        /*if(id_solo==null) {
            dataInsert obj = new dataInsert();
            obj.setPlayer("1");
            obj.setPlayer_total("1");
            obj.setRoom_id("" + random_generator());
            reference.child("solo").push().setValue(obj);
        }
        else {
            for(Integer i=0;i<id_solo.size();i++)
            {
                int myNum = Integer.parseInt(id_solo.get(i));
                if(myNum<5)
                {
                    reference.child("solo").child("player_total").setValue("2");
                }
            }
        }*/
    }
    private void set_visibility() {
        if(name.isChecked())
        {
            f_name=true;
        }
        if(animal.isChecked())
        {
            f_animal=true;
        }
        if(place.isChecked())
        {
            f_place=true;
        }
        if(bird.isChecked())
        {
            f_bird=true;

        }
        if(song.isChecked())
        {
            f_song=true;
        }
        if(movie.isChecked())
        {
            f_movie=true;

        }if(thing.isChecked())
        {
            f_thing=true;
        }
        if(fruit_Veg.isChecked())
        {
            f_fruit=true;
        }
    }
    public void show(){
        Cursor res=mydb.getData();
        if(res.getCount()==0){
        show("0");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            buffer.append("COINS :"+res.getString(1));
            buffer.append("HINTS :"+res.getString(2));
            show(buffer.toString());
        }
    }
    public void show(String Message){
        hinthere.setText("message");
        coinhere.setText(Message.toString());
    }
    public void search_Player() {

    }
    public String checkDigit(int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }
}
