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
    private Card firstCard=null;
    private Card secondCard=null;
    private Integer moves=0;
    private Integer scores=0;
    private List<Button> cardButton;
    private boolean isBusy;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout cardGridLayout=findViewById(R.id.cardGridLayout);
        TextView scoreView=findViewById(R.id.score);
        TextView moveView=findViewById(R.id.move);
        ArrayList<Card> cards= new ArrayList<>();
        for(int i=0;i<8;i++){
           cards.add(new Card(i));
           cards.add(new Card((i)));

        }
        Collections.shuffle(cards);
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
                    if(isBusy){
                        return;
                    }
                    Card selectCard=cards.get(finalI);
                    if(selectCard.isMatch()||selectCard.isFlipped()){
                        return;
                    }


                   String value=String.valueOf(selectCard.getNumber());
                   button.setText(value);
                   selectCard.setFlipped(true);

                   if(firstCard==null) {
                       firstCard= cards.get(finalI);
                    }
                   else{
                       secondCard=cards.get(finalI);
                       moves++;
                       isBusy=true;

                       new Handler().postDelayed(new Runnable() {
                           @Override
                           public void run() {
                               if(firstCard.getNumber()==secondCard.getNumber()){
                                   scores++;
                                   firstCard.setMatch(true);
                                   secondCard.setMatch(true);
                                   scoreView.setText("Score:"+String.valueOf(scores));
                                   moveView.setText("Move"+String.valueOf(moves));
                               }

                               else{
                                   int firstCardIndex=cards.indexOf(firstCard);
                                   Button prevButton = cardButton.get(firstCardIndex);
                                   prevButton.setText("?");
                                   button.setText("?");
                                   firstCard.setFlipped(false);
                                   secondCard.setFlipped(false);
                               }
                           }
                       },3000);



                          isBusy=false;
                          firstCard=null;
                          secondCard=null;


                       }




                   }



            });
            cardGridLayout.addView(button);
        }
    }
}
