package Ex8;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class PoppinsFrame extends JFrame implements KeyListener {

    private JLabel contentpane;
    private MyImageIcon sunny_background, rainy_background;
    private ToggleLabel poppinsLabel, activeLabel;
    private DragLabel umbrellaLabel;

    // adjust frame's size as you want
    private int frameWidth = 800, frameHeight = 500;

    public PoppinsFrame() {
        String path = "src/main/java/Ex8/resources/";

        setBounds(50, 50, frameWidth, frameHeight);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // set background image by using JLabel as contentpane
        setContentPane(contentpane = new JLabel());
        sunny_background = new MyImageIcon(path + "sunny_background.jpg").resize(frameWidth, frameHeight);
        rainy_background = new MyImageIcon(path + "rainy_background.jpg").resize(frameWidth, frameHeight);
        contentpane.setLayout(null);
        setSunny();

        poppinsLabel = new ToggleLabel(this);
        contentpane.add(poppinsLabel);
        activeLabel = poppinsLabel;

        umbrellaLabel = new DragLabel(this);
        contentpane.add(umbrellaLabel);

        repaint();
    }

    public void setSunny() {
        contentpane.setIcon(sunny_background);
        setTitle("Sunny");
    }

    public ToggleLabel getActiveLabel() {
        return activeLabel;
    }

    public static void main(String[] args) {
        new PoppinsFrame();
    }
    
    public void setRainy() {
        contentpane.setIcon(rainy_background);
        setTitle("Rainy");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_R: { setRainy(); break; }
            case KeyEvent.VK_S: { setSunny(); break; }
            case KeyEvent.VK_LEFT: { activeLabel.moveLeft(); activeLabel.setWalk(); break; }
            case KeyEvent.VK_RIGHT: { activeLabel.moveRight(); activeLabel.setWalk();break; }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

////////////////////////////////////////////////////////////////////////////////
class MoveLabel extends JLabel {

    protected int curX, curY, width, height;
    protected boolean horizontalMove, verticalMove;
    protected PoppinsFrame parentFrame;

    public MoveLabel(PoppinsFrame pf) {
        parentFrame = pf;
        setHorizontalAlignment(JLabel.CENTER);
        setIcon(null);
    }

    public void setMoveConditions(boolean hm, boolean vm) {
        horizontalMove = hm;
        verticalMove = vm;
    }

    public void updateLocation() {
        /* override in child class */ }
    
        public void moveLeft(){ 
        curX = curX - 20; 
        if (curX < 0) curX = parentFrame.getWidth() - width;
        setBounds(curX, curY, width, height);
    }
    
    public void moveRight(){ 
        curX = curX + 20;       
        if(curX + width > parentFrame.getWidth()) curX = 0;
        setBounds(curX, curY, width, height);
    }
}
////////////////////////////////////////////////////////////////////////////////

class ToggleLabel extends MoveLabel {

    protected MyImageIcon walkIcon, flyIcon;
    private int groundY;                    // keeping ground location

    public ToggleLabel(PoppinsFrame pf) {
        super(pf);

        curX = 100;
        curY = groundY = 200;
        width = 130;
        height = 200;
        String path = "src/main/java/Ex8/resources/";
        walkIcon = new MyImageIcon(path + "poppins_walk.png").resize(width, height);
        flyIcon = new MyImageIcon(path + "poppins_fly.png").resize(width, height);
        setBounds(curX, groundY, width, height);
        setWalk();
    }

    public void setWalk() {
        setIcon(walkIcon);
    }
}

////////////////////////////////////////////////////////////////////////////////
class DragLabel extends MoveLabel //implements MouseMotionListener
{

    protected MyImageIcon itemIcon;

    public DragLabel(PoppinsFrame pf) {
        super(pf);

        curX = 40;
        curY = 40;
        width = 80;
        height = 80;
        String path = "src/main/java/Ex8/resources/";
        itemIcon = new MyImageIcon(path + "umbrella.png").resize(width, height);
        setIcon(itemIcon);
        setBounds(curX, curY, width, height);
    }
}

////////////////////////////////////////////////////////////////////////////////
// auxiliary class to resize image
class MyImageIcon extends ImageIcon {

    public MyImageIcon(String fname) {
        super(fname);
    }

    public MyImageIcon(Image image) {
        super(image);
    }

    public MyImageIcon resize(int width, int height) {
        Image oldimg = this.getImage();
        Image newimg = oldimg.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return new MyImageIcon(newimg);
    }
};
