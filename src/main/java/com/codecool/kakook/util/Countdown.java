package com.codecool.kakook.util;

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
            System.out.println(date.getTime());
            timer.stop();
        }
    });

}