package symulacja;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import symulacja.rosliny.*;
import symulacja.zwierze.*;

public class World extends JFrame {
    int width;
    int height;
    public int turn;
    ArrayList<Organizm> organizmy = new ArrayList<Organizm>();
    Czlowiek czlowiek;
    public World(int width,int height) {
        super("Pavel Harelik 196766");
        this.width = width;
        this.height = height;
        this.turn = 0;
        this.czlowiek = new Czlowiek(this, 0, 0);
        for (int i = 0; i < width * height / 15; i++)
        {
            AddRandomOrganizm();
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width*20,height*22);
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch(keyCode) {
                    case KeyEvent.VK_UP:
                        moveCzlowiek(0, -1);
                        break;
                    case KeyEvent.VK_DOWN:
                        moveCzlowiek(0, 1);
                        break;
                    case KeyEvent.VK_LEFT:
                        moveCzlowiek(-1, 0);
                        break;
                    case KeyEvent.VK_RIGHT:
                        moveCzlowiek(1, 0);
                        break;
                    case KeyEvent.VK_U:
                        System.out.println("U");
                        czlowiek.setUmiejetnosc(5);
                        break;
                    case KeyEvent.VK_SPACE:
                        System.out.println("SPACE");
                        nextTurn();
                        System.out.println("Turn: "+turn);
                        break;
                }
            }
        });
        setVisible(true);
        this.repaint();
    }

    private void moveCzlowiek(int dx, int dy) {
        // перемещаем текущий квадрат
        int newX = czlowiek.getX()+dx;
        int newY = czlowiek.getY()+dy;
        if(newX<0 || newY<0 || newX>=width || newY>=height) return;
        this.czlowiek.setX(this.czlowiek.getX()+dx);
        this.czlowiek.setY(this.czlowiek.getY()+dy);
        nextTurn();
    }




    public void paint(Graphics g) {
        super.paint(g);
        g.drawRect(czlowiek.getX()*20,(czlowiek.getY()+2)*20,20,20);
        g.setColor(Color.RED);
        g.fillRect(czlowiek.getX()*20,(czlowiek.getY()+2)*20,20,20);

        for (Organizm organizm : organizmy) {
            g.setColor(organizm.getColor());
            g.drawRect(organizm.getX()*20,(organizm.getY()+2)*20,20,20);
            g.fillRect(organizm.getX()*20,(organizm.getY()+2)*20,20,20);
        }
    }

    public void addOrganizm(Organizm organizm) {
        this.organizmy.add(organizm);
    }

    public void removeOrganizm(Organizm organizm) {
        this.organizmy.remove(organizm);
    }

    public void nextTurn() {
        this.turn++;
        for (int i=0;i<organizmy.size();i++) {
            organizmy.get(i).akcja(1,false);
        }
        this.repaint();
        this.czlowiek.akcja(1,false);
    }

    public Organizm getOrganizm(int x, int y) {
        for (Organizm organizm : organizmy) {
            if(organizm.getX()==x && organizm.getY()==y) return organizm;
        }
        if (czlowiek.getX()==x && czlowiek.getY()==y) return czlowiek;
        return null;
    }

    public void AddRandomOrganizm() {
        int x = (int)(Math.random()*width);
        int y = (int)(Math.random()*height);
        int dice = (int)(Math.random()*10);
        switch (dice) {
            case 0 -> organizmy.add(new Wilk(this, x, y));
            case 1 -> organizmy.add(new Owca(this, x, y));
            case 2 -> organizmy.add(new Lis(this, x, y));
            case 3 -> organizmy.add(new Zolw(this, x, y));
            case 4 -> organizmy.add(new Antylopa(this, x, y));
            case 5 -> organizmy.add(new Trawa(this, x, y));
            case 6 -> organizmy.add(new Mlecz(this, x, y));
            case 7 -> organizmy.add(new Guarana(this, x, y));
            case 8 -> organizmy.add(new WilczeJagody(this, x, y));
            case 9 -> organizmy.add(new BarszczSosnowskiego(this, x, y));
        }
    }

    public int[] getFree(int x, int y) {
        int[] free = new int[2];
        free[0] = -1;
        free[1] = -1;
        for (int i = -1; i <= 1; i++)
        {
            for (int j = -1; j <= 1; j++)
            {
                if (x + i >= 0 && x + i < width && y + j >= 0 && y + j < height)
                {
                    if (!isOccupied(x + i, y + j))
                    {
                        free[0] = x + i;
                        free[1] = y + j;
                        return free;
                    }
                }
            }
        }
        return free;
    }
    public boolean isOccupied(int x, int y) {
        if (czlowiek.getX()==x && czlowiek.getY()==y) return true;
        for (Organizm organizm : organizmy) {
            if(organizm.getX()==x && organizm.getY()==y) return true;
        }
        return false;
    }

    public void killZwierzeNeardy(int x,int y) {
        for (Organizm organizm : organizmy) {
            if(Math.abs(organizm.getX()-x) + Math.abs(organizm.getY()-y) <= 1) {
                if(organizm instanceof Zwierze) {
                    organizmy.remove(organizm);
                    System.out.println(organizm.getClass().getSimpleName()+ " zginął od barzczu sosnowskiego");
                    return;
                }
            }
        }
    }
}
