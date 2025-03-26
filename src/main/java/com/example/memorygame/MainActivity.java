package com.example.memorygame;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Integer firstCardIndex=null;
    private Integer secondCardIndex=null;
    private Integer firstCard=null;
    private Integer secondCard=null;
    private Integer moves=0;
    private Integer scores=0;
    private List<Button> cardButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout cardGridLayout=findViewById(R.id.cardGridLayout);
        TextView scoreView=findViewById(R.id.score);
        TextView moveView=findViewById(R.id.move);
        ArrayList<Integer> arr=new ArrayList<Integer>();
        for(int i=0;i<8;i++){
           arr.add(i);
           arr.add(i);

        }
        Collections.shuffle(arr);
        cardButton=new ArrayList<>();

        for(int i=0;i<16;i++){
            Button button=new Button(this);
            button.setText("?");
            button.setLayoutParams(new GridLayout.LayoutParams());
            cardButton.add(button);
            final int finalI=i;

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   String value=String.valueOf(arr.get(finalI));
                   button.setText(value);

                   if(firstCard==null) {
                       firstCardIndex=finalI;
                       firstCard= arr.get(finalI);
                    }
                   else{
                       secondCard=arr.get(finalI);
                       secondCardIndex=finalI;
                       moves++;
                       new Handler().postDelayed(new Runnable() {
                           @Override
                           public void run() {
                               if(firstCard==secondCard){
                                   scores++;
                               }

                               else{
                                   if(firstCardIndex!=null) {
                                       Button prevButton = cardButton.get(firstCardIndex);
                                       prevButton.setText("?");
                                       button.setText("?");
                                   }
                               }
                           }
                       },3000);

                       if(firstCard==secondCard){
                           scores++;
                       }
                       else{
                           new Handler().postDelayed(new Runnable() {
                               @Override
                               public void run() {
                                   if(firstCardIndex!=null) {
                                       Button prevButton = cardButton.get(firstCardIndex);
                                       prevButton.setText("?");
                                       button.setText("?");
                                   }
                                   
                                   firstCard=null;
                                   secondCard=null;
                                   firstCardIndex=null;
                                   secondCardIndex=null;
                               }
                           },1000);



                       }



                       scoreView.setText("Score:"+String.valueOf(scores));
                       moveView.setText("Move"+String.valueOf(moves));
                   }


                }
            });
            cardGridLayout.addView(button);
        }
    }
}
