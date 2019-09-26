package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText player1name;
    EditText player2name;
    int player1score = 0, player2score = 0;
    Button start_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player1name = (EditText)findViewById(R.id.player1name);
        player2name = (EditText)findViewById(R.id.player2name);
        start_button = (Button)findViewById(R.id.start_button);

        start_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, GamePage.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("player1name",player1name.getText().toString());
                        bundle.putString("player2name",player2name.getText().toString());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }
        );
    }

}
