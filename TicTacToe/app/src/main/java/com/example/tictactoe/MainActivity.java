package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    boolean isNeo = true;
    String [] board = {"None", "None", "None", "None", "None", "None", "None", "None", "None"};
    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    public void onClickPlay(View view){


        int tag = -1; //tags on the board are between 0-9
        String played = "";
        ImageView play = (ImageView) view;
        if (board[Integer.parseInt(play.getTag().toString())] == "None") {
            if (isNeo) {
                play.setImageResource(R.drawable.neo);
                played = "neo";
                isNeo = false;
            } else {
                play.setImageResource(R.drawable.mehrdad);
                played = "mehrdad";
                isNeo = true;
            }
            //animatedly bring down the relative image on the board
            play.setTranslationY(-1500);
            play.animate().translationYBy(1500).setDuration(500);
            //update the board
            tag = Integer.parseInt(play.getTag().toString());
            board[tag] = played;
            for (int i = 0; i < 8; i++) {
                int idx0 = winningPositions[i][0];
                int idx1 = winningPositions[i][1];
                int idx2 = winningPositions[i][2];
                if (board[idx0] != "None" && board[idx0] == board[idx1] && board[idx1] == board[idx2]) {
                    Toast.makeText(this, "Winner is " + board[idx0], Toast.LENGTH_SHORT).show();
                    resetBoard();
                }
                if (!Arrays.stream(board).anyMatch("None"::equals)){
                    Toast.makeText(this, "No winners! Play again", Toast.LENGTH_SHORT).show();
                    resetBoard();
                }
            }
        }

    }

    public void resetBoard(){
        for (int player=0; player<9; player++) {
            board[player] = "None";
        }

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        ImageView grid;
        for (int g=0; g<9; g++){
            grid = (ImageView) gridLayout.getChildAt(g);
            grid.setImageDrawable(null);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}