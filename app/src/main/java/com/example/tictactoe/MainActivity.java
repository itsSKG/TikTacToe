package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // no player = 2, circle = 0, cross = 1;
    int currentPlayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive = true;
    Button playAgainButton = (Button) findViewById(R.id.button);
    TextView winnerName = (TextView) findViewById(R.id.textView);


    public void pressed(View view){

        ImageView box = (ImageView) view;
        Log.d("tag", box.getTag().toString());
        int tappedBox = Integer.parseInt(box.getTag().toString());

        if(gameState[tappedBox] == 2 && gameActive) {
            box.setTranslationY(-1500);
            gameState[tappedBox] = currentPlayer;

            if (currentPlayer == 0) {
                currentPlayer = 1;
                box.setImageResource(R.drawable.cross);
            } else {
                currentPlayer = 0;
                box.setImageResource(R.drawable.circle);
            }
         box.animate().translationYBy(1500).rotation(3600).setDuration(3000);
        for(int[] winningPosition : winningPositions ){
            if(gameState[winningPosition[0]] !=2 && gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]]){
                gameActive = false;
                 String winner = "";
                if(currentPlayer == 0) {
                    winner = "circle";
                }
                else {
                    winner = "cross";
                }
            Toast.makeText(this, "winner is" + winner, Toast.LENGTH_SHORT).show();

                winnerName.setText(winner + "has won!");
                playAgainButton.setVisibility(View.VISIBLE);
                winnerName.setVisibility(View.VISIBLE);
            }
        }
        }
    }
    public void playAgain(View view){
        playAgainButton.setVisibility(View.INVISIBLE);
        winnerName.setVisibility(View.INVISIBLE);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount(); i++){
            ImageView box = (ImageView) gridLayout.getChildAt(i);
            box.setImageDrawable(null);
        }
        for(int j=0;j<gameState.length; j++){
            gameState[j] = 2;
        }
        currentPlayer = 0;
        gameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
