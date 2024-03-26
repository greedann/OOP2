package com.example.symulacja.zwierze;

import com.example.symulacja.Zwierze;
import com.example.symulacja.World;
import com.example.symulacja.Organizm;

import java.awt.*;

public class Wilk extends Zwierze{
    public Wilk(World world, int x, int y) {
        super(world, x, y);
        this.sila = 9;
        this.inicjatywa = 5;
        this.color = Color.darkGray;
    }

    @Override
    public Organizm copy(int x, int y) {
        return new Wilk(this.world, x, y);
    }

    @Override
    public String to_str() {
        return "Wilk";
    }
}
