package com.example;
import javax.swing.JFrame;

import com.example.symulacja.World;

public class Main extends JFrame{
    public static void main(String[] args) {
        World world = new World(25,25);
    }
}