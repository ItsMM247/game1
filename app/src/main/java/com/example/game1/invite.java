package com.example.game1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class invite extends AppCompatActivity {
    Button btn_solo,btn_team,btn_create;
    Boolean flags_solo,flags_team,flags_create;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);
        btn_solo=(Button)findViewById(R.id.solo);
        btn_solo=(Button)findViewById(R.id.solo);
        btn_team=(Button)findViewById(R.id.teamup);
        btn_create=(Button)findViewById(R.id.create);
        btn_solo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flags_solo=true;
                Intent intent= new Intent(getApplicationContext(),selection.class);
                intent.putExtra("flags_solo",flags_solo);
                startActivity(intent);
            }
        });
        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flags_create=true;
                Intent intent= new Intent(getApplicationContext(),selection.class);
                intent.putExtra("flags_create",flags_create);
                startActivity(intent);
            }
        });
        btn_team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flags_team=true;
                Intent intent= new Intent(getApplicationContext(),selection.class);
                intent.putExtra("flags_team",flags_team);
                startActivity(intent);
            }
        });
    }
}
