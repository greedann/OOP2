package com.example.symulacja.zwierze;

import com.example.symulacja.Organizm;
import com.example.symulacja.World;
import com.example.symulacja.Zwierze;

public class Lis extends Zwierze {
    public Lis(World world, int x, int y) {
        super(world, x, y);
        this.sila = 3;
        this.inicjatywa = 7;
        this.color = java.awt.Color.ORANGE;
    }

    @Override
    public Organizm copy(int x, int y) {
        return new Lis(this.world, x, y);
    }

    @Override
    public void akcja(int zasieg,boolean wech) {
        super.akcja(1, true);
    }

    @Override
    public String to_str() {
        return "Lis";
    }

}
