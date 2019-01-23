package com.example.yahtzee;

import android.widget.ImageView;

/**
 * For rolling each individual die
 * @author Elizabeth Min
 */
public class YahtzeeRoll implements Runnable {
    MainActivity master;
    ImageView obj;

    /**
     * Stores the class from which it was called and the gui object to manipulate
     * @param master    the class that Yahtzee Roll was called from
     * @param obj       the ImageView to change
     */
    YahtzeeRoll(MainActivity master, ImageView obj) {
        this.master = master;
        this.obj = obj;
    }

    /**
     * Picks a random number to stop at and scrolls through the faces of the die until it reaches that number
     */
    public void run() {
        int start = 0;
        int stop = (int)(Math.random()*100+10); // the random index to stop scrolling at
        int i; // iterator that also stores the value of the dice
        for (i = 0; start <= stop; i++) {
            final int val = i;
            // must run in the scope that the view was created in
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
