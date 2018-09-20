package com.codecool.kakook.util;

import com.codecool.kakook.game.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class Countdown {

    public static Date date = null;

    public static Timer timer = new javax.swing.Timer(30000, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (date == null) {
                date = new Date();
            }
            Game.getInstance().sendAnswer();
            System.out.println(date.getTime());
            timer.stop();
        }
    });

}
