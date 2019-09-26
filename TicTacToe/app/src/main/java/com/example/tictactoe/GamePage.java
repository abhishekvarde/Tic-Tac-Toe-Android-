package com.example.tictactoe;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Timer;
import java.util.TimerTask;

public class GamePage extends AppCompatActivity {

    Handler delay;
    Runnable startDelay;

    TextView player1;
    TextView player2;
    TextView player1score;
    TextView player2score;
    String player1name;
    String player2name;
    int[] array = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    int[] arrayForX = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    int[] arrayForO = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    int counter = 0, player1win = 0, player2win = 0;
    int[][] winningStates = {{0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 4, 8}, {2, 4, 6}};
    int alternatePlayers = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);

        delay = new Handler();

        player1 = (TextView) findViewById(R.id.player1);
        player2 = (TextView) findViewById(R.id.player2);
        player1score = (TextView) findViewById(R.id.player1score);
        player2score = (TextView) findViewById(R.id.player2score);
        player1score.setText(String.valueOf(player1win));
        player2score.setText(String.valueOf(player2win));

        Bundle bundle = getIntent().getExtras();
        player1name = bundle.getString("player1name");
        player2name = bundle.getString("player2name");
        player1.setText(player1name+"*");
        player2.setText(player2name);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void newAction(View view) {

        int position = ((view.getId()) - 5) % 10;


        if (array[position] == 0) {

            array[position] = 1;

            ImageView view1 = (ImageView) view;

            if (player2name == player2.getText().toString()) {
                view1.setImageResource(R.drawable.newx);
                arrayForX[position] = 1;
                player2.setText(player2name+"*");
                player1.setText(player1name);
            } else {
                view1.setImageResource(R.drawable.newo);
                arrayForO[position] = 1;
                player1.setText(player1name+"*");
                player2.setText(player2name);
            }

            if (ifDraw()) {
                Toast.makeText(GamePage.this, "Match Draw!", Toast.LENGTH_LONG).show();
                reset();
            }

            if (checkWinner(arrayForX) || checkWinner(arrayForO)) {
                win_updatation(counter);
                counter = (++alternatePlayers)%2;
                if ((counter) % 2 != 0) {
                    player2.setText(player2name + "*");
                    player1.setText(player1name);
                }
                else {
                    player1.setText(player1name + "*");
                    player2.setText(player2name);
                }

                return;
            }

            counter++;
        }
    }

    public void win_updatation( int counter ){

        if (counter % 2 == 0) {
            Toast.makeText(GamePage.this, player1name + " Wins", Toast.LENGTH_LONG).show();
            player1score.setText(String.valueOf(++player1win));
        }
        else {
            Toast.makeText(GamePage.this, player2name + " Wins", Toast.LENGTH_LONG).show();
            player2score.setText(String.valueOf(++player2win));
        }
        reset();
    }

    public boolean checkWinner(int[] checker) {
        for (int[] a : winningStates) {
            if (checker[a[0]] == 1 && checker[a[1]] == 1 && checker[a[2]] == 1) {
                return true;
            }
        }
        return false;
    }

    public boolean ifDraw() {
        for (int i : array)
            if (i == 0)
                return false;

        return true;
    }

    public void reset() {

        startDelay = new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 9; i++) {
                    array[i] = 0;
                    arrayForX[i] = 0;
                    arrayForO[i] = 0;
                }

                ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
                ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
                ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
                ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
                ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
                ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
                ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
                ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);
                ((ImageView) findViewById(R.id.imageView9)).setImageResource(0);

            }
        };
        delay.postDelayed(startDelay,2000);


    }
}