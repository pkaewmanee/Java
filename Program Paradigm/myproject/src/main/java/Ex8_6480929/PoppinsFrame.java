/**
 * @author
 * Jawit Poopradit      6480087
 * Phakkhapon Kaewmanee 6480929
 */

package Ex8_6480929;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class PoppinsFrame extends JFrame implements KeyListener{ //implements KeyListener
    private JLabel       contentpane;
    private MyImageIcon  sunny_background, rainy_background;
    private ToggleLabel  poppinsLabel, activeLabel;
    private DragLabel    umbrellaLabel;
    
    // adjust frame's size as you want
    private int frameWidth = 800, frameHeight = 500;

    public PoppinsFrame() {
        String path = "src/main/java/Ex8_6480929/resources/";
                
	setBounds(50, 50, frameWidth, frameHeight);
	setVisible(true);
	setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );

	// set background image by using JLabel as contentpane
	setContentPane(contentpane = new JLabel());
	sunny_background = new MyImageIcon(path + "sunny_background.jpg").resize(frameWidth, frameHeight);
        rainy_background = new MyImageIcon(path + "rainy_background.jpg").resize(frameWidth, frameHeight);
	contentpane.setLayout( null );
        setSunny();

	poppinsLabel = new ToggleLabel(this); 
        contentpane.add(poppinsLabel);
        activeLabel = poppinsLabel;
                            
        umbrellaLabel  = new DragLabel(this);  
        contentpane.add(umbrellaLabel);
        
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

	repaint();
    }
    
    public void setSunny() {
        contentpane.setIcon(sunny_background);
        setTitle("Sunny");
        if (activeLabel != null) {
            activeLabel.setLocation(activeLabel.getX(), activeLabel.getGroundY());
        }
    }
    
    public void setRainy() {
        contentpane.setIcon(rainy_background);
        setTitle("Rainy");
    }
    
    public ToggleLabel getActiveLabel() {
        return activeLabel;
    }
    
    public DragLabel getUmbrellaLabel(){
        return umbrellaLabel;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        
        switch (keyCode) {
            case KeyEvent.VK_S:
                setSunny();
                activeLabel.setWalk();
                break;
            case KeyEvent.VK_R:
                setRainy();
                activeLabel.setFly();
                break;
            case KeyEvent.VK_LEFT:
                activeLabel.moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                activeLabel.moveRight();
                break;
            case KeyEvent.VK_UP:
                activeLabel.moveUp();
                break;
            case KeyEvent.VK_DOWN:
                activeLabel.moveDown();
                break;
            default:
                break;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) { }
    
    @Override
    public void keyTyped(KeyEvent e) { }
        
    public static void main(String[] args) {
	new PoppinsFrame();
    }	
}

////////////////////////////////////////////////////////////////////////////////
class MoveLabel extends JLabel {
    protected int             curX, curY, width, height;
    protected boolean         horizontalMove, verticalMove;
    protected PoppinsFrame    parentFrame;    
    
    public MoveLabel(PoppinsFrame pf) {
        parentFrame = pf;       
        setHorizontalAlignment(JLabel.CENTER);
        setIcon(null);
    }
    
    public void setMoveConditions(boolean hm, boolean vm) {
        horizontalMove = hm; verticalMove = vm;
    }   
    
    public void updateLocation() {
        /* override in child class */ 
    }
}

////////////////////////////////////////////////////////////////////////////////
class ToggleLabel extends MoveLabel {
    protected MyImageIcon walkIcon, flyIcon;  
    private int groundY; // keeping ground location

    public ToggleLabel(PoppinsFrame pf) { 
        super(pf);    
        
        curX = 100;
        curY = groundY = 200;
        width = 130;
        height = 200;
        
        String path = "src/main/java/Ex8_6480929/resources/";
        walkIcon = new MyImageIcon(path + "poppins_walk.png").resize(width, height);
        flyIcon  = new MyImageIcon(path + "poppins_fly.png").resize(width, height);  
        setBounds(curX, groundY, width, height);
        setWalk();
    }
    
     public int getGroundY() {
        return groundY;
    }

    public void setWalk() {
        setIcon(walkIcon);
        setMoveConditions(true, false);
        curY = groundY;
        updateLocation();
    }

    public void setFly() {
        setIcon(flyIcon);
        setMoveConditions(true, true);
        updateLocation();
    }

    public void moveUp() {
        if (verticalMove) {
            curY = Math.max(curY - 10, 0);
            updateLocation();
        }
    }

    public void moveDown() {
        if (verticalMove) {
            int maxY = getParent().getHeight() - height;
            curY = Math.min(curY + 10, maxY);
            updateLocation();
        }
    }

    public void moveLeft() {
        curX = (curX - 10 < -width) ? parentFrame.getWidth() : curX - 10;
        updateLocation();
    }

    public void moveRight() {
        curX = (curX + 10 > parentFrame.getWidth()) ? -width : curX + 10;
        updateLocation();
    }

    @Override
    public void updateLocation() {
        setBounds(curX, curY, width, height);
    }
}

////////////////////////////////////////////////////////////////////////////////
class DragLabel extends MoveLabel implements MouseMotionListener, MouseListener{ //implements MouseMotionListener
    protected MyImageIcon itemIcon;
    private boolean drag = false;
    
    public DragLabel(PoppinsFrame pf) { 
        super(pf);

        curX = 40;
        curY = 40;
        width = 80;
        height = 80;
        
        String path = "src/main/java/Ex8_6480929/resources/";
        itemIcon = new MyImageIcon(path + "umbrella.png").resize(width, height);     
        setIcon(itemIcon);
        setBounds(curX, curY, width, height);
        
        addMouseMotionListener(this);
        addMouseListener(this);
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        if (drag) {
            int mouseX = e.getX();
            int mouseY = e.getY();

            curX += mouseX;
            curY += mouseY;

            Container p = getParent();
            if (curX < 0) curX = 0;
            if (curY < 0) curY = 0;
            if (curX + width > p.getWidth()) curX = p.getWidth() - width;
            if (curY + height > p.getHeight()) curY = p.getHeight() - height;

            setLocation(curX, curY);

            if (getBounds().intersects(parentFrame.getActiveLabel().getBounds())) {
                parentFrame.getActiveLabel().setFly();
            }
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        drag = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        drag = false;
    }

    @Override
    public void mouseMoved(MouseEvent e) { }

    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }
    
    @Override
    public void updateLocation() {
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