package com.example.symulacja.zwierze;

import com.example.symulacja.Zwierze;
import com.example.symulacja.World;
import com.example.symulacja.Organizm;

public class Owca extends Zwierze {
    public Owca(World world, int x, int y) {
        super(world, x, y);
        this.sila = 4;
        this.inicjatywa = 4;
        this.color = java.awt.Color.WHITE;
    }

    @Override
    public Organizm copy(int x, int y) {
        return new Owca(this.world, x, y);
    }

    @Override
    public String to_str() {
        return "Owca";
    }
}
