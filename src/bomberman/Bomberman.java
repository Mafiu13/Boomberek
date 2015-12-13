/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.awt.Canvas;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Rectangle;
//import javax.swing.Timer;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.*;

/**
 *
 * @author Jedi
 */
public class Bomberman extends Canvas implements Board, KeyListener { // canvas przenosi elementy typu graphics na ekran, board plansza

    public long usedTime;
    public BufferStrategy strategy; // pobieranie elementow na ekran, itd
    private SpriteCache spriteCache;
    private ArrayList<Actors> actors;
//    private ArrayList bombs;
    private Bomb bomb1, bomb2;
    private Bomber b1;
    private Bomber b2;
    private int player;
//    private Bomb bomb;
    private boolean startTimer;
    private boolean startTimer2;

    private boolean setB;
    private double CriticalRange = 70;
    private double newRange = CriticalRange;
    private boolean dead;

    long startProgram = System.currentTimeMillis();

    public Bomberman(int player) {
        this.player = player;
        spriteCache = new SpriteCache();

        JFrame window = new JFrame("BOMBERMAN");
        JPanel panel = (JPanel) window.getContentPane();
        setBounds(0, 0, Board.WIDTH, Board.HEIGHT);
        panel.setPreferredSize(new Dimension(Board.WIDTH, Board.HEIGHT));
        panel.setLayout(null);
        panel.add(this);

        window.setBounds(0, 0, Board.WIDTH, Board.HEIGHT);
        window.setVisible(true);
        window.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                //System.exit(0);
                dead = true;
            }
        });

        window.setResizable(false);

        createBufferStrategy(2);
        strategy = getBufferStrategy();
        requestFocus();
        addKeyListener(this);

    }

    public class Reminder {

        Timer timer;

        public Reminder(int seconds) {
            timer = new Timer();
            timer.schedule(new RemindTask(), seconds * 1000);

        }

        class RemindTask extends TimerTask {

            public void run() {
                checkRange();
                {
                    bomb1.setX(44444);
                }

                timer.cancel(); //Wyłączamy taska

            }
        }

    }

    public class Reminder2 {

        Timer timer;

        public Reminder2(int seconds) {
            timer = new Timer();
            timer.schedule(new RemindTask2(), seconds * 1000);

        }

        class RemindTask2 extends TimerTask {

            public void run() {
                checkRange();

                {
                    bomb2.setX(44444);
                }

                timer.cancel(); //Wyłączamy taska

            }
        }

    }

    public class ReminderBeep {
//  Toolkit toolkit;

        Timer timer2;

        public ReminderBeep(int seconds) {
            // toolkit = Toolkit.getDefaultToolkit();
            timer2 = new Timer();
            timer2.schedule(new RemindTask(), seconds * 1000);
        }

        class RemindTask extends TimerTask {

            public void run() {
                CriticalRange = CriticalRange + 20;

                timer2.cancel();
                startTimer2 = true;
                System.out.println("POLE RAŻEINA ZOSTAŁO ZWIĘKSZONE" + CriticalRange);

            }
        }

    }

// naciśnięcie klawisza skutkuje ruchem b1, coś podobnego dla b2 tylko z drugiego watku od klienta trzeba pobierac info
    public void keyPressed(KeyEvent e) {
        //dodac wsyłanie do socketa
        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                makeMove(this.player, Move.down);
                break;
            case KeyEvent.VK_UP:
                makeMove(this.player, Move.up);
                break;
            case KeyEvent.VK_LEFT:
                makeMove(this.player, Move.left);
                break;
            case KeyEvent.VK_RIGHT:
                makeMove(this.player, Move.right);
                break;
            case KeyEvent.VK_SPACE:
                makeMove(this.player, Move.bomb);
                break;

        }
    }
    
    
    
    public void makeMove(int player, Move move) {
        if (player == 1) {
            b1.makeMove(move);
        }
        else if (player == 2) {
            b2.makeMove(move);
        }
        
        // wyslij ze gracz wykonal ten ruch
    }
    
    public void stopMove(int player, Move move) {
        if (player == 1) {
            b1.stopMove(move);
        }
        else if (player == 2) {
            b2.stopMove(move);
        }
        
        // wyslij ze gracz przerwal ten ruch
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                stopMove(this.player, Move.down);
                break;
            case KeyEvent.VK_UP:
                stopMove(this.player, Move.up);
                break;
            case KeyEvent.VK_LEFT:
                stopMove(this.player, Move.left);
                break;
            case KeyEvent.VK_RIGHT:
                stopMove(this.player, Move.right);
                break;
            case KeyEvent.VK_SPACE:
                stopMove(this.player, Move.bomb);
                break;

        }

        
    }

    public void keyTyped(KeyEvent e) {
    }

    public void initWorld() {

        startTimer2 = true;
        int brick_size = 50;
        
        b1 = new B1(this);
        b1.setX(0);
        b1.setY(0);

        b2 = new B2(this);
        b2.setX((int) Board.WIDTH - 40);
        b2.setY((int) Board.HEIGHT - 90);
        
        

        actors = new ArrayList();
        //        bombs=new ArrayList();

        bomb1 = new Bomb(this);
        bomb2 = new Bomb(this);

        //      bomb=new Bomb(this);
        bomb1.setX((int) Board.WIDTH + 40);
        bomb1.setY((int) Board.HEIGHT + 90);

        bomb2.setX((int) Board.WIDTH + 40);
        bomb2.setY((int) Board.HEIGHT + 90);

        for (int i = 0; i < 7; i++) {

            for (int j = 0; j < 7; j++) {
                Brick b = new Brick(this);
                b.setX(brick_size * 2 * i + brick_size);
                b.setY(brick_size * 2 * j);
                actors.add(b);
            }

        }

        for (int i = 0; i < 4; i++) {
            Brick b = new Brick(this);
            b.setX(brick_size * i + brick_size);
            b.setY(brick_size * i + brick_size);
            actors.add(b);

        }
        for (int i = 0; i < 5; i++) {
            Brick b = new Brick(this);
            b.setX(brick_size * i + 10 * brick_size);
            b.setY(brick_size * i);
            actors.add(b);

        }

        for (int i = 0; i < 5; i++) {
            Brick b = new Brick(this);
            b.setX(brick_size * i + 7 * brick_size);
            b.setY(brick_size * i + 3 * brick_size);
            actors.add(b);

        }

        for (int i = 0; i < 5; i++) {
            Brick b = new Brick(this);
            b.setX(brick_size * i);
            b.setY(brick_size * i + 6 * brick_size);
            actors.add(b);

        }
        for (int i = 0; i < 5; i++) {
            Brick b = new Brick(this);
            b.setX(brick_size * 6);
            b.setY(brick_size * i + 5 * brick_size);
            actors.add(b);

        }
        for (int i = 0; i < 5; i++) {
            Brick b = new Brick(this);
            b.setX(brick_size * 14);
            b.setY(brick_size * i + 6 * brick_size);
            actors.add(b);

        }
        for (int i = 0; i < 1; i++) {
            Brick b = new Brick(this);
            b.setX(brick_size * 12);
            b.setY(brick_size * 5);
            actors.add(b);

        }

    }

    public void checkRange() {

        System.out.println(CriticalRange);
        System.out.println(System.currentTimeMillis());
        Rectangle bombBounds = bomb1.getBounds();
        Rectangle bombBounds2 = bomb2.getBounds();
        Rectangle playerBounds = b1.getBounds();
        Rectangle playerBounds2 = b2.getBounds();
        for (int i = 0; i < actors.size(); i++) {
            //         for(int j=0; j<bombs.size();j++)
            {
                Actors a1 = (Actors) actors.get(i);
                //       Actors b = (Actors)bombs.get(j);

                Rectangle r1 = a1.getBounds();
                //      Rectangle r5= b.getBounds();

                double x1 = r1.getCenterX();
                double y1 = r1.getCenterY();

                double x2 = bombBounds.getCenterX();
                double y2 = bombBounds.getCenterY();

                double x3 = playerBounds.getCenterX();
                double y3 = playerBounds.getCenterY();

                double x4 = playerBounds2.getCenterX();
                double y4 = playerBounds2.getCenterY();

                double x5 = bombBounds2.getCenterX();
                double y5 = bombBounds2.getCenterY();
                //       double x5=r5.getCenterX();
                //       double y5=r5.getCenterY();

                double range = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
                double range2 = Math.sqrt((x2 - x3) * (x2 - x3) + (y2 - y3) * (y2 - y3));
                double range3 = Math.sqrt((x2 - x4) * (x2 - x4) + (y2 - y4) * (y2 - y4));

                double range1_2 = Math.sqrt((x5 - x1) * (x5 - x1) + (y5 - y1) * (y5 - y1));
                double range2_2 = Math.sqrt((x5 - x3) * (x5 - x3) + (y5 - y3) * (y5 - y3));
                double range3_2 = Math.sqrt((x5 - x4) * (x5 - x4) + (y5 - y4) * (y5 - y4));

                if (range <= CriticalRange || range1_2 <= CriticalRange) {
                    System.out.println("giniemy");
                    actors.remove(a1);
                    // a1.setX(1000);
                    System.out.println("range1 " + range);

                }
                if (range2 <= CriticalRange || range2_2 <= CriticalRange) {
                    System.out.println("zginal b1");
                    b1.markedForRemoval = true;
                    System.out.println(range2);

                }
                if (range3 <= CriticalRange || range3_2 <= CriticalRange) {
                    System.out.println("zginal b2");
                    b2.markedForRemoval = true;
                    System.out.println(range3);
                }

            }
        }
    }

    public void checkCollisions() {
        //kolizja gracz 1

        Rectangle playerBounds = b1.getBounds();
        Rectangle playerBounds2 = b2.getBounds();
        for (int i = 0; i < actors.size(); i++) {
            Actors a1 = (Actors) actors.get(i);
            Rectangle r1 = a1.getBounds();
            if (r1.intersects(playerBounds)) {
                b1.collision(a1);
                a1.collision(b1);
            }
            if (playerBounds2.intersects(playerBounds)) {
                b1.collision(b2);
                b2.collision(b1);
            }
            if (r1.intersects(playerBounds2)) {
                b2.collision(a1);
                a1.collision(b2);
            }

        }

    }

    public void paintWorld() {

        Graphics2D g = (Graphics2D) strategy.getDrawGraphics();

        paint_info(g);
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());

        for (int i = 0; i < actors.size(); i++) { // wczytywanie wszystkich aktorów
            Actors o = (Actors) actors.get(i);
            o.paint(g);
        }

        //        for(int i=0; i<bombs.size();i++){ // wczytywanie wszystkich aktorów
        //      Actors b=(Actors)bombs.get(i);
        //tutaj zrobie b1.getB a bylo bomb1.getB
        if (b2.getB() == true) {

            bomb1.setX(b2.x);
            bomb1.setY(b2.y);

            setB = false;
            //bylo bomb1.setB
            b2.setB(setB);
            startTimer = bomb1.getTimer();
        }
        if (b1.getB() == true) {

            bomb2.setX(b1.x);
            bomb2.setY(b1.y);

            setB = false;
            //bylo bomb1.setB
            b1.setB(setB);
            startTimer = bomb2.getTimer();
        }

        bomb1.paint(g);
        bomb2.paint(g);

        b1.paint(g);
        b2.paint(g);

        //ogolnie jesli ma byc wiecej bomb to wtedy lista
        {
            if (startTimer) {
                //    Thread.sleep(5000);
                new Reminder(2);
                System.out.format("Task scheduled.%n");
                // bomb.setX(1000);
                // bomb.setY(1000);
                startTimer = false;

                //        for(int j=0;j<bombs.size();j++)
                {
                    bomb1.setTimer(startTimer);
                    bomb2.setTimer(startTimer);

                }
            }
        }
        if (startTimer2) {
            new ReminderBeep(5);
            startTimer2 = false;
        }

        if (b1.markedForRemoval == true) {
            g.setColor(Color.black);
            g.fillRect(0, 0, getWidth(), getHeight());

            // g.drawString("GAME OVER", WIDTH/2, WIDTH/2);
            String s = "GAME OVER";
            Font font = new Font("SansSerif", Font.BOLD, 50);

            g.setFont(font);
            g.setColor(Color.white);
            g.drawString(s, Board.WIDTH / 2 - 120, Board.HEIGHT / 2 - 100);
            g.drawString("B2 WIN", Board.WIDTH / 2 - 70, Board.HEIGHT / 2);
        }

        if (b2.markedForRemoval == true) {
            g.setColor(Color.black);
            g.fillRect(0, 0, getWidth(), getHeight());

            // g.drawString("GAME OVER", WIDTH/2, WIDTH/2);
            String s = "GAME OVER";
            Font font = new Font("SansSerif", Font.BOLD, 50);

            g.setFont(font);
            g.setColor(Color.white);
            g.drawString(s, Board.WIDTH / 2 - 120, Board.HEIGHT / 2 - 100);
            g.drawString("B1 WIN", Board.WIDTH / 2 - 70, Board.HEIGHT / 2);
        }

        if (b2.markedForRemoval == true && b1.markedForRemoval == true) {
            g.setColor(Color.black);
            g.fillRect(0, 0, getWidth(), getHeight());

            // g.drawString("GAME OVER", WIDTH/2, WIDTH/2);
            String s = "GAME OVER";
            Font font = new Font("SansSerif", Font.BOLD, 50);

            g.setFont(font);
            g.setColor(Color.white);
            g.drawString(s, Board.WIDTH / 2 - 120, Board.HEIGHT / 2 - 100);
            g.drawString("REMIS", Board.WIDTH / 2 - 70, Board.HEIGHT / 2);
        }

        strategy.show();

    }

    public void updateWorld() {

        for (int i = 0; i < actors.size(); i++) {
            Actors o = (Actors) actors.get(i);
            o.act();

        }
//          for (int i = 0; i < bombs.size(); i++) {
        //       Actors b = (Actors)bombs.get(i);
        //        b.act();
        bomb1.act();
        bomb2.act();
        //       }
        b1.act();
        b2.act();
        //    bomb.act();

        // tutaj kolizje itd
        checkCollisions();

    }

    public SpriteCache getSpriteCache() {
        return spriteCache;
    }

    public void game() {
        
        
        
        //nowy watek ktory bedzie wczytywal iformacje od programu i wukonywal make/stop mow
        //    usedTime=1000;
        initWorld();
        dead = false;
        while (isVisible() && !dead) {
            updateWorld();
            paintWorld();
            try {
                Thread.sleep(Board.SPEED);
            } catch (InterruptedException e) {
            }

        }

    }

    public void paint_info(Graphics2D g) {

        if (startTimer2 == false) {
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.setPaint(Color.green);
            g.drawString("WARTOŚĆ POLA RAŻENIA WYNOSI " + CriticalRange, 0, 50);

        }

    }

//    public static void main(String[] args) 
//    {
//        Bomberman bomberman= new Bomberman();
//        bomberman.game();
//    }
    /*
    
    co ja potzrbuję dosstać:
    X i Y playera
    
    czyli
    
    public void GetPosition(int x,int y,bool setB)
    {
    b1.setX(x);
    b1.setY(y);
    b1.getB()=setB;
    }
    oraz czy bomba została zasetowana- mam jakąś wartość boolową dla każdego z bombermanów setB();
    jak dostanę true to mam pozycję bomby- jest taka sama jak pozycja bombermana który ją zasetował
    dołożyłam timer związany z range- to już działa
    bomber2 ma też już inny kolor- dwie klasy dziedziczące po Bomber
    zrobiłam też obiekt bomba1 który jest zwiazany z konkretnym bomberem, będzie można 1 ustawiać- muszę jeszcze
    zabezpieczyć przed odpalenniem wcześniejszym niż wyłączenie timera- jak będę miała czas
    
    jutro nie mogę już nic podziałać, jeśli możesz ogarnij tylko jakiekolwiek przesyłanie między klient-serwer
    wydaje mi się że to co napisałam na fb ma sens, może nie jest zajebiste ale ma szanse zadziałać
    w sensie łączymy program kliencki z konkretnym bombermanem, np b2 a serwer to b1
    
    
    
    }
    
    
     */
}
