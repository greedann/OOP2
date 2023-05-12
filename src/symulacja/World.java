package symulacja;

import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import symulacja.rosliny.*;
import symulacja.zwierze.*;

public class World extends JFrame {
    private int width;
    private int height;
    private int turn;
    private ArrayList<Organizm> organizmy = new ArrayList<Organizm>();
    private Czlowiek czlowiek;

    public World(int width, int height) {
        super("Pavel Harelik 196766");
        this.width = width;
        this.height = height;
        this.turn = 0;
        this.czlowiek = new Czlowiek(this, 0, 0);
        for (int i = 0; i < width * height / 15; i++) {
            AddRandomOrganizm();
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width * 20, height * 22 + 100);

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    int x = e.getX();
                    int y = e.getY();
                    x = (int) x / 20;
                    y = (int) (y-40) / 20;
                    if (x >= 0 && x < width && y >= 0 && y < height) {
                        select_and_add(x, y);
                    }
                }
            }
        });
        KeyListener keyListener = new KeyListener() {
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                int dx = 0, dy = 0;
                switch (keyCode) {
                    case KeyEvent.VK_UP -> {
                        if (czlowiek.getUmiejetnosc() > 0) moveCzlowiek(0, -2);
                        else moveCzlowiek(0, -1);
                    }
                    case KeyEvent.VK_DOWN -> {
                        if (czlowiek.getUmiejetnosc() > 0) moveCzlowiek(0, 2);
                        else moveCzlowiek(0, 1);
                    }
                    case KeyEvent.VK_LEFT -> {
                        if (czlowiek.getUmiejetnosc() > 0) moveCzlowiek(-2, 0);
                        else moveCzlowiek(-1, 0);
                    }
                    case KeyEvent.VK_RIGHT -> {
                        if (czlowiek.getUmiejetnosc() > 0) moveCzlowiek(2, 0);
                        else moveCzlowiek(1, 0);
                    }
                    case KeyEvent.VK_U -> czlowiek.setUmiejetnosc(5);
                    case KeyEvent.VK_SPACE -> {
                        nextTurn();
                        System.out.println("Turn: " + turn);
                    }
                    case KeyEvent.VK_S -> fileWork(true);
                    case KeyEvent.VK_L -> fileWork(false);
                }
            }
            public void keyReleased(KeyEvent keyEvent) {
            }
            public void keyTyped(KeyEvent keyEvent) {
            }
        };
        JButton button = new JButton("Next Turn");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nextTurn();
                System.out.println("Turn: " + turn);
            }
        });
        button.addKeyListener(keyListener);
        button.setBounds(0, height * 20+5, width * 10, 50);
        add(button);

        addKeyListener(keyListener);

        JButton button2 = new JButton("Save to file");
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                requestFocusInWindow();
                fileWork(true);
            }
        });
        button2.addKeyListener(keyListener);
        button2.setBounds(width * 10, height * 20+55, width * 10, 50);
        add(button2);

        JButton button3 = new JButton("Load from file");
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                requestFocusInWindow();
                fileWork(false);
            }
        });
        button3.addKeyListener(keyListener);
        button3.setBounds(0, height * 20+55, width * 10, 50);
        add(button3);

        JButton button4 = new JButton("Show hint");
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                requestFocusInWindow();
                show_hint();
            }
        });
        button4.addKeyListener(keyListener);
        button4.setBounds(width * 10, height * 20+5, width * 10, 50);
        add(button4);

        setResizable(false);
        setLayout(null);
        setVisible(true);
        requestFocusInWindow();
        this.repaint();
    }

    private void show_hint() {
        JFrame frame = new JFrame("Hint Window");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 350);
        frame.setLocationRelativeTo(null);
        String hint = "<html>Controls:<br>SPACE to next turn<br>U to use special ability<br>Arrows to move<br>" +
                "S to save to file<br>L to load from file<br><br>Types of organisms and colors: <br>Antylopa - yellow<br>" +
                "Barberry - pink<br>Guarana - red<br>Fox - orange<br>Milk - white<br>Sheep - cyan<br>Grass - green<br>" +
                "Wolf - black<br>Wolfberry - blue<br>Turtle - gray</html>";
        JLabel label = new JLabel(hint);
        JButton button = new JButton("OK");
        button.setBounds(100, 280, 100, 30);

        JPanel panel = new JPanel(new FlowLayout());
        panel.add(label);
        frame.add(button);
        frame.add(panel);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        frame.setVisible(true);


        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        frame.setVisible(true);
    }

    private void fileWork(boolean write) {
        JFrame frame = new JFrame("File Input Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 100);
        frame.setLocationRelativeTo(null);

        JLabel label = new JLabel("Enter the name of the file:");
        JTextField textField = new JTextField(20);
        JButton button = new JButton("OK");

        JPanel panel = new JPanel(new FlowLayout());
        panel.add(label);
        panel.add(textField);
        panel.add(button);
        frame.add(panel);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fileName = textField.getText();
                if (write) saveToFile(fileName);
                else readFromFile(fileName);
                frame.dispose();
            }
        });
        frame.setVisible(true);
    }

    private void select_and_add(int x, int y) {
        JFrame frame = new JFrame("Zwierze select Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 150);
        frame.setLocationRelativeTo(null);

        JRadioButton radioButton1 = new JRadioButton("Antylopa");
        JRadioButton radioButton2 = new JRadioButton("Lis");
        JRadioButton radioButton3 = new JRadioButton("Owca");
        JRadioButton radioButton4 = new JRadioButton("Wilk");
        JRadioButton radioButton5 = new JRadioButton("Zolw");
        JRadioButton radioButton6 = new JRadioButton("Trawa");
        JRadioButton radioButton7 = new JRadioButton("Wilcze jagody");
        JRadioButton radioButton8 = new JRadioButton("Guarana");
        JRadioButton radioButton9 = new JRadioButton("Mlecz");
        JRadioButton radioButton10 = new JRadioButton("Barszcz Sosnowskiego");
        JButton button = new JButton("OK");

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        buttonGroup.add(radioButton3);
        buttonGroup.add(radioButton4);
        buttonGroup.add(radioButton5);
        buttonGroup.add(radioButton6);
        buttonGroup.add(radioButton7);
        buttonGroup.add(radioButton8);
        buttonGroup.add(radioButton9);
        buttonGroup.add(radioButton10);

        JPanel panel = new JPanel(new FlowLayout());
        panel.add(radioButton1);
        panel.add(radioButton2);
        panel.add(radioButton3);
        panel.add(radioButton4);
        panel.add(radioButton5);
        panel.add(radioButton6);
        panel.add(radioButton7);
        panel.add(radioButton8);
        panel.add(radioButton9);
        panel.add(radioButton10);
        panel.add(button);
        frame.add(panel);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (radioButton1.isSelected()) addOrganizm(new Antylopa(World.this, x, y));
                else if (radioButton2.isSelected()) addOrganizm(new Lis(World.this, x, y));
                else if (radioButton3.isSelected()) addOrganizm(new Owca(World.this, x, y));
                else if (radioButton4.isSelected()) addOrganizm(new Wilk(World.this, x, y));
                else if (radioButton5.isSelected()) addOrganizm(new Zolw(World.this, x, y));
                else if (radioButton6.isSelected()) addOrganizm(new Trawa(World.this, x, y));
                else if (radioButton7.isSelected()) addOrganizm(new WilczeJagody(World.this, x, y));
                else if (radioButton8.isSelected()) addOrganizm(new Guarana(World.this, x, y));
                else if (radioButton9.isSelected()) addOrganizm(new Mlecz(World.this, x, y));
                else if (radioButton10.isSelected()) addOrganizm(new BarszczSosnowskiego(World.this, x, y));
                frame.dispose();
                repaint();
            }
        });
        frame.setVisible(true);
    }
    private void moveCzlowiek(int dx, int dy) {
        // перемещаем текущий квадрат
        int newX = czlowiek.getX() + dx;
        int newY = czlowiek.getY() + dy;
        if (newX < 0 || newY < 0 || newX >= width || newY >= height) return;
        this.czlowiek.setX(this.czlowiek.getX() + dx);
        this.czlowiek.setY(this.czlowiek.getY() + dy);
        nextTurn();
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawRect(czlowiek.getX() * 20, (czlowiek.getY() + 2) * 20, 20, 20);
        g.setColor(Color.RED);
        g.fillRect(czlowiek.getX() * 20, (czlowiek.getY() + 2) * 20, 20, 20);

        for (Organizm organizm : organizmy) {
            g.setColor(organizm.getColor());
            g.drawRect(organizm.getX() * 20, (organizm.getY() + 2) * 20, 20, 20);
            g.fillRect(organizm.getX() * 20, (organizm.getY() + 2) * 20, 20, 20);
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
        for (int i = 0; i < organizmy.size(); i++) {
            organizmy.get(i).akcja(1, false);
        }
        this.repaint();
        this.czlowiek.akcja(1, false);
    }

    public Organizm getOrganizm(int x, int y) {
        for (Organizm organizm : organizmy) {
            if (organizm.getX() == x && organizm.getY() == y) return organizm;
        }
        if (czlowiek.getX() == x && czlowiek.getY() == y) return czlowiek;
        return null;
    }

    public void AddRandomOrganizm() {
        int x = (int) (Math.random() * width);
        int y = (int) (Math.random() * height);
        int dice = (int) (Math.random() * 10);
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
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (x + i >= 0 && x + i < width && y + j >= 0 && y + j < height) {
                    if (!isOccupied(x + i, y + j)) {
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
        if (czlowiek.getX() == x && czlowiek.getY() == y) return true;
        for (Organizm organizm : organizmy) {
            if (organizm.getX() == x && organizm.getY() == y) return true;
        }
        return false;
    }

    public int getBlockWidth() {
        return width;
    }

    public int getBlockHeight() {
        return height;
    }

    public void killZwierzeNeardy(int x, int y) {
        for (Organizm organizm : organizmy) {
            if (Math.abs(organizm.getX() - x) + Math.abs(organizm.getY() - y) <= 1) {
                if (organizm instanceof Zwierze) {
                    organizmy.remove(organizm);
                    System.out.println(organizm.getClass().getSimpleName() + " zginął od barzczu sosnowskiego");
                    return;
                }
            }
        }
    }

    public void saveToFile(String filename) {
        try {
            FileWriter fileWriter = new FileWriter("./src/symulacja/saves/" + filename);
            fileWriter.write(String.valueOf(width));
            fileWriter.write(" ");
            fileWriter.write(String.valueOf(height));
            fileWriter.write("\n");
            fileWriter.write(String.valueOf(turn));
            fileWriter.write("\n");
            fileWriter.write(String.valueOf(czlowiek.getX()));
            fileWriter.write(" ");
            fileWriter.write(String.valueOf(czlowiek.getY()));
            fileWriter.write(" ");
            fileWriter.write(String.valueOf(czlowiek.getUmiejetnosc()));
            fileWriter.write(" ");
            fileWriter.write(String.valueOf(czlowiek.getWiek()));
            fileWriter.write("\n");
            fileWriter.write(String.valueOf(organizmy.size()));
            fileWriter.write("\n");
            for (Organizm organizm : organizmy) {
                fileWriter.write(organizm.getClass().getSimpleName());
                fileWriter.write(" ");
                fileWriter.write(String.valueOf(organizm.getX()));
                fileWriter.write(" ");
                fileWriter.write(String.valueOf(organizm.getY()));
                fileWriter.write(" ");
                fileWriter.write(String.valueOf(organizm.getWiek()));
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromFile(String filename) {
        try {
            organizmy.clear();
            FileReader fileReader = new FileReader("./src/symulacja/saves/" + filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            String[] dimensions = line.split(" ");
            width = Integer.parseInt(dimensions[0]);
            height = Integer.parseInt(dimensions[1]);
            line = bufferedReader.readLine();
            turn = Integer.parseInt(line);
            line = bufferedReader.readLine();
            String[] czlowiekData = line.split(" ");
            int x = Integer.parseInt(czlowiekData[0]);
            int y = Integer.parseInt(czlowiekData[1]);
            int umiejetnosc = Integer.parseInt(czlowiekData[2]);
            int wiek = Integer.parseInt(czlowiekData[3]);
            czlowiek = new Czlowiek(this, x, y);
            czlowiek.setUmiejetnosc(umiejetnosc);
            czlowiek.setWiek(wiek);
            line = bufferedReader.readLine();
            int iloscOrganizmow = Integer.parseInt(line);
            for (int i = 0; i < iloscOrganizmow; i++) {
                line = bufferedReader.readLine();
                String[] organizmData = line.split(" ");
                String className = organizmData[0];
                x = Integer.parseInt(organizmData[1]);
                y = Integer.parseInt(organizmData[2]);
                wiek = Integer.parseInt(organizmData[3]);
                switch (className) {
                    case "Wilk" -> organizmy.add(new Wilk(this, x, y));
                    case "Owca" -> organizmy.add(new Owca(this, x, y));
                    case "Lis" -> organizmy.add(new Lis(this, x, y));
                    case "Zolw" -> organizmy.add(new Zolw(this, x, y));
                    case "Antylopa" -> organizmy.add(new Antylopa(this, x, y));
                    case "Trawa" -> organizmy.add(new Trawa(this, x, y));
                    case "Mlecz" -> organizmy.add(new Mlecz(this, x, y));
                    case "Guarana" -> organizmy.add(new Guarana(this, x, y));
                    case "WilczeJagody" -> organizmy.add(new WilczeJagody(this, x, y));
                    case "BarszczSosnowskiego" -> organizmy.add(new BarszczSosnowskiego(this, x, y));
                }
            }
            repaint();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
