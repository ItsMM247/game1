package com.example.game1;

        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;
        import android.widget.TextView;

        import java.util.Random;

public class scoreborad extends AppCompatActivity {

    TextView player1;
    TextView player2;
    TextView player3;
    TextView player4;
    int score,score1,score2,score3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreborad);
        player1=(TextView)findViewById(R.id.p3);
        player2=(TextView)findViewById(R.id.p);
        player3=(TextView)findViewById(R.id.p1);
        player4=(TextView)findViewById(R.id.p2);
        score=getIntent().getExtras().getInt("score");
        int player=random_generator(2,4);
        if(player==2)
        {int a=random_generator(1,100);
            player1.setText("Your Score"+score);
            while(a %10!=0)
            {
                a =random_generator(10,100);
                score1=a;
            }
            player2.setText("@anonymous"+score1);
        }
        else if (player==3)
        {

        }
        else if(player==4)
        {

        }
    }

    public int random_generator(int min,int max)
    {
        final int random = new Random().nextInt((max - min) + 1) + min;
        return random;
    }
}
