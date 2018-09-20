package com.codecool.kakook.util;

import com.codecool.kakook.game.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Countdown {

    public static Timer timer = new javax.swing.Timer(20000, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            Game.getInstance().sendAnswer();
            timer.stop();
        }
    });
}
