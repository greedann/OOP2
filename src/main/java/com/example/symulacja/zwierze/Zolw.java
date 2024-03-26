package com.example.symulacja.zwierze;

import com.example.symulacja.Organizm;
import com.example.symulacja.Zwierze;
import com.example.symulacja.World;

import java.awt.*;

public class Zolw extends Zwierze{
    public Zolw(World world, int x, int y) {
        super(world, x, y);
        this.sila = 2;
        this.inicjatywa = 1;
        this.color = Color.GRAY;
    }

    @Override
    public Organizm copy(int x, int y) {
        return new Zolw(this.world, x, y);
    }

    @Override
    public String to_str() {
        return "Zolw";
    }

    @Override
    public void kolizja(Organizm organizm) {
        if (organizm.getSila() < 5) {
            System.out.println(this.to_str() + " odbil atak " + organizm.to_str());
        }
        else {
            super.kolizja(organizm);
        }
    }
}
