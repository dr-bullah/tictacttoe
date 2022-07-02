package com.example.titato;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class gameActivity extends AppCompatActivity{

    public void onBackPressed() {
        finish();
    }
    public void goBackHS(View view){
        finish();
    }

    boolean gameActive = true;
    private TextView playerOneScore, playerTwoScore, playerStatus, turnStatus;
    private int p1scoreCount, p2scoreCount,turnCounter;
    int activePlayer, previousWinner; //o-> 0, x->1, 2-> empty
    int [] gameState = {2,2,2,2,2,2,2,2,2};
    int [][] winState = {
            {0,1,2}, {3,4,5}, {6,7,8}, //ALl row IDs
            {0,3,6}, {1,4,7}, {2,5,8}, //All column IDs
            {0,4,8}, {2,4,6} //Diagonal IDs
    };

    public void fullReset(View view){
        gameActive=true;
        activePlayer = 0;
        for (int i = 0; i<gameState.length;i++){
            gameState[i]=2;
        }
        ((ImageView)findViewById(R.id.tito0)).setImageResource(0);
        ((ImageView)findViewById(R.id.tito1)).setImageResource(0);
        ((ImageView)findViewById(R.id.tito2)).setImageResource(0);
        ((ImageView)findViewById(R.id.tito3)).setImageResource(0);
        ((ImageView)findViewById(R.id.tito4)).setImageResource(0);
        ((ImageView)findViewById(R.id.tito5)).setImageResource(0);
        ((ImageView)findViewById(R.id.tito6)).setImageResource(0);
        ((ImageView)findViewById(R.id.tito7)).setImageResource(0);
        ((ImageView)findViewById(R.id.tito8)).setImageResource(0);
        p1scoreCount=0;
        p2scoreCount=0;
        updatePlayerScore();
        turnStatus.setText("Player One's Turn");
        playerStatus.setText("");
    }

    public void gameReset(View view){
        gameActive=true;
        activePlayer = previousWinner;
        for (int i = 0; i<gameState.length;i++){
            gameState[i]=2;
        }
        ((ImageView)findViewById(R.id.tito0)).setImageResource(0);
        ((ImageView)findViewById(R.id.tito1)).setImageResource(0);
        ((ImageView)findViewById(R.id.tito2)).setImageResource(0);
        ((ImageView)findViewById(R.id.tito3)).setImageResource(0);
        ((ImageView)findViewById(R.id.tito4)).setImageResource(0);
        ((ImageView)findViewById(R.id.tito5)).setImageResource(0);
        ((ImageView)findViewById(R.id.tito6)).setImageResource(0);
        ((ImageView)findViewById(R.id.tito7)).setImageResource(0);
        ((ImageView)findViewById(R.id.tito8)).setImageResource(0);
        turnStatus.setText("");
        if(p1scoreCount > p2scoreCount){
            String temp1 = "Player One is Winning";
            playerStatus.setText(temp1);
        } else if (p2scoreCount > p1scoreCount){
            String temp2 = "Player Two is Winning";
            playerStatus.setText(temp2);
        } else {
            playerStatus.setText("");
        }
        if(previousWinner==0){
            turnStatus.setText("Player One's Turn");
        }else{
            turnStatus.setText("Player Two's Turn");
        }
    }
    public void updatePlayerScore(){
        String temp1 = Integer.toString(p1scoreCount);
        String temp2 = Integer.toString(p2scoreCount);
        playerOneScore.setText(temp1);
        playerTwoScore.setText(temp2);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        if(getSupportActionBar() != null){ //To handle the null pointer exception shown in logs without this, from google
            getSupportActionBar().hide(); //If the function is not returning a null value, then it proceeds
        }
        playerOneScore = (TextView) findViewById(R.id.player_one_score);
        playerTwoScore = (TextView) findViewById(R.id.player_two_score); //R->Resources and findViewById is a method name
        playerStatus = (TextView) findViewById(R.id.playerStatus);
        turnStatus = (TextView) findViewById(R.id.turnStatus);
        turnStatus.setText("Player One's Turn");
        p1scoreCount=0;
        p2scoreCount=0;
    }
    public void userTap(View view){
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        turnCounter++;
        if(!gameActive){
            gameReset(view);
        }
        if(gameState[tappedImage] == 2 && gameActive){
            gameState[tappedImage]=activePlayer;
            img.setTranslationY(-1000f);
            if(activePlayer == 0){
                img.setImageResource(R.drawable.tito_cross);
                activePlayer = 1;
                turnStatus.setText("Player Two's Turn");
            }else{
                img.setImageResource(R.drawable.tito_zero);
                activePlayer=0;
                turnStatus.setText("Player One's Turn");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        if(p1scoreCount > p2scoreCount){
            String temp1 = "Player One is Winning";
            playerStatus.setText(temp1);
        } else if (p2scoreCount > p1scoreCount){
            String temp2 = "Player Two is Winning";
            playerStatus.setText(temp2);
        } else {
            playerStatus.setText("");
        }
        for(int[] winPosition : winState){
            if(gameState[winPosition[0]] == gameState[winPosition[1]]
                    && gameState[winPosition[1]] == gameState[winPosition[2]]
                        && gameState[winPosition[0]]!=2){
                gameActive=false;
                if(gameState[winPosition[0]] == 0){
                    p1scoreCount++;
                    previousWinner=0;
                    gameReset(view);
                }else {
                    p2scoreCount++;
                    previousWinner=1;
                    gameReset(view);
                }
            }
        }
        updatePlayerScore();
        if(turnCounter==9){
            gameReset(view);
            turnCounter=0;
        }
    }
}
