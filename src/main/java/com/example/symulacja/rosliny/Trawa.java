package com.example.symulacja.rosliny;

import com.example.symulacja.Roslina;
import com.example.symulacja.World;
import com.example.symulacja.Organizm;

public class Trawa extends Roslina {
    public Trawa(World world, int x, int y) {
        super(world, x, y);
        this.sila = 0;
        this.color = java.awt.Color.GREEN;
    }

    @Override
    public Organizm copy(int x, int y) {
        return new Trawa(this.world, x, y);
    }

    @Override
    public String to_str() {
        return "Trawa";
    }

    @Override
    public void kolizja(Organizm org) {
        System.out.println(org.to_str() + " zjadl trawe");
        super.kolizja(org);
    }

}
