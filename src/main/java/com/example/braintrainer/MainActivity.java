package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //declaring different fields
    Button goButton;
    TextView scoreTextView;
    TextView rightwrongTextView;
    TextView ans0;
    TextView ans1;
    TextView ans2;
    TextView ans3;
    TextView quesTextView;
    TextView timerTextView;
    Button playAgainButton;
    GridLayout gridLayout;

    ArrayList<Integer> answers = new ArrayList<Integer>();
    int rno;
    int score=0;
    int noOfQuestions=0;

    public void playAgain(View view){

        score=0;
        noOfQuestions=0;
        timerTextView.setText("0:30");
        scoreTextView.setText("0/0");
        rightwrongTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);

        newQuestion();

        new CountDownTimer(30100, 1000) {
            public void onTick(long msleft) {
                if (msleft / 1000 < 10) {
                    timerTextView.setText("0:0" + msleft / 1000);
                } else {
                    timerTextView.setText("0:" + msleft / 1000);
                }
            }

            public void onFinish() {
                rightwrongTextView.setText("TIME UP!");
                playAgainButton.setVisibility(View.VISIBLE);

                //my own extra feature
                //when the timer stops, no option can be clicked
                ans0.setClickable(false);
                ans1.setClickable(false);
                ans2.setClickable(false);
                ans3.setClickable(false);
            }
        }.start();

        //when the game starts again, options can be clicked
        ans0.setClickable(true);
        ans1.setClickable(true);
        ans2.setClickable(true);
        ans3.setClickable(true);


    }

    public void startGame(View view) {
        goButton.setVisibility(View.INVISIBLE);

        timerTextView.setVisibility(View.VISIBLE);
            quesTextView.setVisibility(View.VISIBLE);
            scoreTextView.setVisibility(View.VISIBLE);
            gridLayout.setVisibility(View.VISIBLE);

            playAgain(findViewById(R.id.playAgainButton));

    }

    public void guessAnswer(View view) {
        rightwrongTextView.setVisibility(View.VISIBLE);

        if (view.getTag().toString().equals(Integer.toString(rno))) {
            score++;
            rightwrongTextView.setText("Correct!");
        } else {
            rightwrongTextView.setText("Wrong :(");
        }

        noOfQuestions++;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(noOfQuestions));

        newQuestion();
    }

    public void newQuestion() {

        Random r=new Random();
        int x = r.nextInt(21);
        int y = r.nextInt(21);

        quesTextView.setText(Integer.toString(x) + "+" + Integer.toString(y));

        rno = r.nextInt(4);

        answers.clear();  //cleans the whole arrayList everytime to add new values

        int wrongAnswer;

        for (int i = 0; i < 4; i++) {
            if (i == rno) {
                answers.add(x + y);
            } else {
                 wrongAnswer= r.nextInt(41);

                 while (wrongAnswer == x + y) {
                    wrongAnswer = r.nextInt(41);
                 }

                answers.add(wrongAnswer);
            }
        }

        ans0.setText(Integer.toString(answers.get(0)));
        ans1.setText(Integer.toString(answers.get(1)));
        ans2.setText(Integer.toString(answers.get(2)));
        ans3.setText(Integer.toString(answers.get(3)));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.goButton);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        quesTextView = (TextView) findViewById(R.id.quesTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        rightwrongTextView = (TextView) findViewById(R.id.rightwrongTextView);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        ans0 = (TextView) findViewById(R.id.no1TextView);
        ans1 = (TextView) findViewById(R.id.no2TextView);
        ans2 = (TextView) findViewById(R.id.no3TextView);
        ans3 = (TextView) findViewById(R.id.no4TextView);
        gridLayout = findViewById(R.id.gridLayout);

        //setting the initial visibilty mode to Invisible
        timerTextView.setVisibility(View.INVISIBLE);
        quesTextView.setVisibility(View.INVISIBLE);
        scoreTextView.setVisibility(View.INVISIBLE);
        rightwrongTextView.setVisibility(View.INVISIBLE);
        gridLayout.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);
        
    }
}