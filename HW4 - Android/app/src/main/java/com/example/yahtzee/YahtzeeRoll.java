package com.example.yahtzee;

import android.widget.ImageView;

public class YahtzeeRoll implements Runnable {
    MainActivity master;
    ImageView obj;

    YahtzeeRoll(MainActivity master, ImageView obj) {
        this.master = master;
        this.obj = obj;
    }

    public void run() {
        int start = 0;
        int stop = (int)(Math.random()*100+10); // the random index to stop scrolling at
        int i; // iterator that also stores the value of the dice
        for (i = 0; start <= stop; i++) {
            final int val = i;
            master.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    obj.setImageResource(master.images[val]);
                }
            });

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // resets the looping through the numbers if it reaches its max
            if (i+1 == 6) i = -1;
            start++;
        }
    }
}
