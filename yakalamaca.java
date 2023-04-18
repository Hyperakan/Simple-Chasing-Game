import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class yakalamaca implements KeyListener {
    JFrame frame;
    JLabel mLabel;
    ArrayList<JLabel> mLabelList = new ArrayList<JLabel>();
    JLabel cLabel = new JLabel();

    yakalamaca() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.addKeyListener(this);
        new myCharacter();

    }

    class myCharacter {
        private int cx;
        private int cy;

        myCharacter() {
            this.setCx(250);
            this.setCy(250);
            cLabel.setBounds(cx, cy, 20, 20);
            cLabel.setBackground(Color.green);
            cLabel.setOpaque(true);
            frame.add(cLabel);

        }

        public int getCy() {
            return cy;
        }

        public void setCy(int cy) {
            this.cy = cy;
        }

        public int getCx() {
            return cx;
        }

        public void setCx(int cx) {
            this.cx = cx;
        }

    }

    class Monster extends Thread { // extends JLabel
        private int x;
        private int y;
        private JLabel ownLabel;

        Monster(int x, int y) {
            mLabel = new JLabel();
            this.setOwnLabel(mLabel);
            mLabelList.add(ownLabel);
            if (x <= 480 && y <= 480 && x > 0 && y > 0) {
                this.setX(x);
                this.setY(y);
            } else {
                Random rand = new Random();
                this.setX(Math.abs(rand.nextInt() % 500));
                this.setY(Math.abs(rand.nextInt() % 500));
            }
            ownLabel.setBounds(x, y, 20, 20);
            ownLabel.setBackground(Color.blue);
            ownLabel.setOpaque(true);
            frame.add(ownLabel);
            frame.setVisible(true);

        }

        public JLabel getOwnLabel() {
            return ownLabel;
        }

        public void setOwnLabel(JLabel ownLabel) {
            this.ownLabel = ownLabel;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public boolean isCatch() {
            int cx = cLabel.getX();
            int cy = cLabel.getY();
            for (int tempX = x; tempX < x + 20; tempX++) {
                for (int tempY = y; tempY < y + 20; tempY++) {
                    if (tempX > cx && tempX < cx + 20 && tempY > cy && tempY < cy + 20) {
                        setX(cLabel.getX());
                        setY(cLabel.getY());
                        System.out.println("game over");

                        System.exit(0);
                        return true;
                    }

                }
            }

            return false;
        }

        public boolean icerideMi(int k) {
            if (k >= 0 && k <= 480)
                return true;

            return false;
        }

        public void yaklas() {
            if (!isCatch()) {
                int c_x = cLabel.getX();
                int c_y = cLabel.getY();
                int fark_x = c_x - x;
                int fark_y = c_y - y;
                Random rand = new Random();
                // System.out.println(Thread.currentThread().getName());
                if (fark_x < 0 && fark_y < 0) {
                    int temp = rand.nextInt(2);
                    if (temp == 0) {
                        if (icerideMi(getX() - 10))
                            setX(getX() - 10);

                    } else {
                        if (icerideMi(getY() - 10))
                            setY(getY() - 10);

                    }
                } else if (fark_x < 0 && fark_y > 0) {
                    int temp = rand.nextInt(2);
                    if (temp == 0) {
                        if (icerideMi(getX() - 10))
                            setX(getX() - 10);

                    } else {
                        if (icerideMi(getY() + 10))
                            setY(getY() + 10);

                    }
                } else if (fark_x > 0 && fark_y < 0) {
                    int temp = rand.nextInt(2);
                    if (temp == 0) {
                        if (icerideMi(getX() + 10))
                            setX(getX() + 10);
                    } else {
                        if (icerideMi(getY() - 10))
                            setY(getY() - 10);
                    }
                } else if (fark_x > 0 && fark_y > 0) {
                    int temp = rand.nextInt(2);
                    if (temp == 0) {
                        if (icerideMi(getX() + 10))
                            setX(getX() + 10);
                    } else {
                        if (icerideMi(getY() + 10))
                            setY(getY() + 10);

                    }
                } else if (fark_x < 0 && fark_y == 0) {
                    if (icerideMi(getX() - 10))
                        setX(getX() - 10);
                } else if (fark_x > 0 && fark_y == 0) {
                    if (icerideMi(getX() + 10))
                        setX(getX() + 10);

                } else if (fark_x == 0 && fark_y < 0) {
                    if (icerideMi(getY() - 10))
                        setY(getY() - 10);
                } else if (fark_x == 0 && fark_y > 0) {
                    if (icerideMi(getY() + 10))
                        setY(getY() + 10);
                }

            }
            isCatch();

            /*
             * else {
             * setX(cLabel.getX());
             * setY(cLabel.getY());
             * System.out.println("game over");
             * System.exit(0);
             * }
             */
            ownLabel.setBounds(x, y, 20, 20);

        }

        @Override
        public void run() {

            yaklas();

            try {
                Monster.sleep(1000);
                // System.out.println(Thread.currentThread().getName()+" x: "+x+" y: "+y);
                run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // System.out.println(Monster.currentThread().getName()+": x->"+x+" y->"+y);

        }

    }

    public static void main(String[] args) {
        // bunu unutma!!!
        // int number_of_monsters = Integer.parseInt(args[0]);
        int number_of_monsters = 10;
        System.out.println(number_of_monsters);
        yakalamaca m = new yakalamaca();

        yakalamaca.Monster[] monsters = new yakalamaca.Monster[number_of_monsters];

        Random r = new Random();

        for (int i = 0; i < number_of_monsters; i++) {
            monsters[i] = m.new Monster(Math.abs(r.nextInt() % 500), Math.abs(r.nextInt() % 500));
        }

        for (int i = 0; i < number_of_monsters; i++)
            monsters[i].start();

        try {
            for (int i = 0; i < number_of_monsters; i++)
                monsters[i].join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void moveEnemy() {
        for (JLabel l : mLabelList) {
            Random r = new Random();
            int temp = r.nextInt(4);
            if (temp == 0)
                l.setBounds(l.getX() + 10, l.getY(), 20, 20);
            if (temp == 1)
                l.setBounds(l.getX(), l.getY() + 10, 20, 20);
            if (temp == 2)
                l.setBounds(l.getX() - 10, l.getY(), 20, 20);
            if (temp == 3)
                l.setBounds(l.getX(), l.getY() - 10, 20, 20);

        }

    }

    public boolean sinirdaMi(int k, int m) {
        if (k >= 0 && k <= 480 && m >= 0 && m <= 450)
            return true;

        return false;
    }

    public boolean icindenGeciyoMu() {
        for (JLabel l : mLabelList) {
            int cx = cLabel.getX();
            int cy = cLabel.getY();
            for (int tempX = mLabel.getX(); tempX < mLabel.getX() + 20; tempX++) {
                for (int tempY = mLabel.getY(); tempY < mLabel.getY() + 20; tempY++) {
                    if (tempX >= cx && tempX <= cx + 20 && tempY >= cy && tempY <= cy + 20) {
                        return true;
                    }

                }
            }

            return false;
        }
        return false;

    }
    public boolean carptinMi(int cx,int cy){
        for (JLabel l : mLabelList){
            int tempX = l.getX();
            int tempY = l.getY();
            if (tempX >= cx && tempX <= cx + 25 && tempY >= cy && tempY <= cy + 25) {
                System.out.println("carptin!!!");
                return true;
            }
        }
        return false;
    }

    // iclerinden gecebilyorum onu cozmem lazim

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'a':
                if ((!icindenGeciyoMu()) && sinirdaMi(cLabel.getX() - 10, cLabel.getY())&& !carptinMi(cLabel.getX() - 10, cLabel.getY()))
                    cLabel.setLocation(cLabel.getX() - 10, cLabel.getY());
                break;
            case 'w':
                if ((!icindenGeciyoMu()) && sinirdaMi(cLabel.getX(), cLabel.getY() - 10)&& !carptinMi(cLabel.getX(), cLabel.getY() - 10))
                    cLabel.setLocation(cLabel.getX(), cLabel.getY() - 10);

                break;
            case 's':
                if ((!icindenGeciyoMu()) && sinirdaMi(cLabel.getX(), cLabel.getY() + 10)&& !carptinMi(cLabel.getX(), cLabel.getY() + 10))
                    cLabel.setLocation(cLabel.getX(), cLabel.getY() + 10);

                break;
            case 'd':
                if ((!icindenGeciyoMu()) && sinirdaMi(cLabel.getX() + 10, cLabel.getY())&& !carptinMi(cLabel.getX()+10, cLabel.getY()))
                    cLabel.setLocation(cLabel.getX() + 10, cLabel.getY());

                break;
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

}
