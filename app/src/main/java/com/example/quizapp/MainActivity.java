package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity extends AppCompatActivity {
    private final String SCORE_KEY="SCORE";
    private final String INDEX_KEY="INDEX";
    private TextView mTxtQuestion;
    private Button btnF;
    private Button btnT;
    private int mQuestionIndex;
    private int mQuizQuestion;
    private ProgressBar mProgressBar;
    private TextView mQuizstatsTextView;
    private int mUserScore;


    private QuizModel[] questionCollection = new QuizModel[]{
            new QuizModel(R.string.q1,true),
            new QuizModel(R.string.q2,false),
            new QuizModel(R.string.q3,true),
            new QuizModel(R.string.q4,true),
            new QuizModel(R.string.q5,true),
            new QuizModel(R.string.q6,true),
            new QuizModel(R.string.q7,true),
            new QuizModel(R.string.q8,false),
            new QuizModel(R.string.q9,true),
            new QuizModel(R.string.q10,false),


    };
    final int USER_PROGRESS=(int)Math.ceil(100.0/questionCollection.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        if(savedInstanceState != null){
//            mUserScore=savedInstanceState.getInt(SCORE_KEY);
//            mQuestionIndex=savedInstanceState.getInt(INDEX_KEY);
//            mQuizstatsTextView.setText(mUserScore+"");
//        }{
//            mUserScore=0;
//            mQuestionIndex=0;
//        mQuizstatsTextView.setText(mUserScore+"");
//        }

        //first lifecycle method
//        Toast.makeText(getApplicationContext(),"On Create method is Called",Toast.LENGTH_LONG).show();
        mTxtQuestion= findViewById(R.id.txtQuestion);
        QuizModel q1=questionCollection[mQuestionIndex];
        mQuizQuestion = q1.getmQuestion();
        mTxtQuestion.setText(mQuizQuestion);
        mProgressBar = findViewById(R.id.quizPB);
        mQuizstatsTextView = findViewById(R.id.txtQuizStats);



        btnT = findViewById(R.id.btnTrue);
        btnT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                evaluateUserAnswer(true);
                changeQuestionOnButtonClick();

            }
        });

        btnF=findViewById(R.id.btnFalse);
        btnF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                evaluateUserAnswer(false);
                changeQuestionOnButtonClick();

            }
        });

    }
    private void changeQuestionOnButtonClick(){
        mQuestionIndex=(mQuestionIndex+1)%10;
        if(mQuestionIndex==0){
            AlertDialog.Builder quizAlert = new AlertDialog.Builder(this);
            quizAlert.setCancelable(false);
            quizAlert.setTitle("Quiz is Finished");
            quizAlert.setMessage("Your Score is "+mUserScore);
            quizAlert.setPositiveButton("Finish the Quiz", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            quizAlert.show();
        }
        mQuizQuestion = questionCollection[mQuestionIndex].getmQuestion();
        mTxtQuestion.setText(mQuizQuestion);
        mProgressBar.incrementProgressBy(USER_PROGRESS);
        mQuizstatsTextView.setText(mUserScore+""+"/ 10");
    }
    private void evaluateUserAnswer(boolean userGuess){
        boolean currentQuestionAnswer = questionCollection[mQuestionIndex].ismAnswer();
        if(currentQuestionAnswer== userGuess){
          FancyToast.makeText(getApplicationContext(),"Sahi Jawab Kya Kahne!", FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();
          mUserScore=mUserScore+1;
        }
        else{
            FancyToast.makeText(getApplicationContext(),"Galat Hai Answer!",FancyToast.LENGTH_SHORT,FancyToast.CONFUSING,true).show();
        }
    }

//    //Lifecycle Methods
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        Toast.makeText(getApplicationContext(),"On Start method is Called",Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        Toast.makeText(getApplicationContext(),"On Resume method is Called",Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        Toast.makeText(getApplicationContext(),"On Pause method is Called",Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        Toast.makeText(getApplicationContext(),"On Stop method is Called",Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Toast.makeText(getApplicationContext(),"On Destroy method is Called",Toast.LENGTH_LONG).show();
//    }
//
////    @Override
////    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
////        super.onSaveInstanceState(outState, outPersistentState);
////        //computer --a robot
////        outState.putInt(SCORE_KEY,mUserScore);
////        outState.putInt(INDEX_KEY,mQuestionIndex);
////    }
}