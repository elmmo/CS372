package com.example.yahtzee;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.content.Context;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    final int DICE_NUM = 5;
    final int DICE_SIDES = 6;
    protected int[] images;
    protected ImageView[] dice;
    YahtzeeRoll[] rolls;
    Button rollButton;
    ImageView img;
    final String TAG = "ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        images = new int[DICE_SIDES];

        populate();
        setUpListener();
    }

    public void setUpListener() {
        rollButton = (Button) findViewById(R.id.roll_button);

        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launch();
            }
        });

    }

    private void populate() {
        rolls = new YahtzeeRoll[DICE_NUM];
        dice = new ImageView[DICE_NUM];
        for (int i = 0; i < DICE_SIDES; i++) {
            Context context = this;
            int num = i+1;

            // populates the array of images with their id references
            String name = "face_" + num;
            int id = context.getResources().getIdentifier(name, "drawable", context.getPackageName());
            images[i] = id;

            if (i < DICE_NUM) {
                // populates the array of imageviews
                name = "die" + num;
                ImageView view = (ImageView)findViewById(getResources().getIdentifier(name, "id", this.getPackageName()));
                dice[i] = view;

                rolls[i] = new YahtzeeRoll(this, dice[i]);
            }
        }
    }

    public void launch() {
        for (int i = 0; i < DICE_NUM; i++) {
            Thread t = new Thread(rolls[i]);
            t.start();
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
