//Warit Poopradit 6381053

package Ex8_6381053;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class CatAndBirdFrame extends JFrame implements KeyListener
{
    private JLabel      contentpane;
    private MoveLabel   catLabel, birdLabel, activeLabel;
    private DragLabel   hatLabel;
    
    // adjust frame's size as you want
    private int frameWidth = 800, frameHeight = 500;
    private int fenceY = 120, groundY = 300;

    public CatAndBirdFrame()
    {
        String path = "src/main/java/Ex8_6381053/";
                
        setBounds(50, 50, frameWidth, frameHeight);
        setVisible(true);
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );

        // set background image by using JLabel as contentpane
        setContentPane(contentpane = new JLabel());
        MyImageIcon background = new MyImageIcon(path + "background.jpg");
        contentpane.setIcon( background.resize(frameWidth, frameHeight) );
        contentpane.setLayout( null );

        catLabel = new MoveLabel(path + "cat.png", 170, 140, this); 
        catLabel.setMoveConditions(200, 300, true, false);
        contentpane.add(catLabel);
        
        birdLabel = new MoveLabel(path + "bird.png", 170, 140, this);
        birdLabel.setMoveConditions(400, 50, true, true);
        contentpane.add(birdLabel);
                
        hatLabel  = new DragLabel(path + "hat.png", 80, 80, this); 
        hatLabel.setMoveConditions(40, 40, true, true);
        contentpane.add(hatLabel);
        
        addKeyListener(this);
        setCat();
        repaint();
    }
    
    public int       getFence()              { return fenceY; }
    public int       getGround()             { return groundY; }
    public MoveLabel getTargetLabel()        { return activeLabel; }    
    public void      setCat()                { activeLabel = catLabel; setTitle("Cat Moves"); hatLabel.setActiveLabel(catLabel); }
    public void      setBird()               { activeLabel = birdLabel; setTitle("Bird Moves"); hatLabel.setActiveLabel(birdLabel); }
    public int       getWidth()              { return frameWidth; }
    
    public static void main(String[] args) {
        new CatAndBirdFrame();
    } 

    @Override
    public void keyTyped ( KeyEvent e ) { }                                                                          
    
    @Override
    public void keyPressed ( KeyEvent e ){                                      
        switch(e.getKeyCode()){
            case KeyEvent.VK_C: { setCat(); break; }
            case KeyEvent.VK_B: { setBird(); break; }
            case KeyEvent.VK_UP: { activeLabel.moveUp(); break; }
            case KeyEvent.VK_DOWN: { activeLabel.moveDown(); break; }
            case KeyEvent.VK_LEFT: { activeLabel.moveLeft(); break; }
            case KeyEvent.VK_RIGHT: { activeLabel.moveRight(); break; }
        }
        
    }
    
    @Override
    public void keyReleased ( KeyEvent e) {}                                    
}

class MoveLabel extends JLabel 
{
    protected MyImageIcon        moveIcon;
    protected int                curX, curY, width, height;
    protected boolean            horizontalMove, verticalMove;
    protected CatAndBirdFrame    parentFrame;

    public MoveLabel() { }
    public MoveLabel(String file, int w, int h, CatAndBirdFrame pf)    
    { 
        width = w; height = h;
        moveIcon = new MyImageIcon(file).resize(width, height);
        setIcon(moveIcon);
        setHorizontalAlignment(JLabel.CENTER);        
        parentFrame = pf;
    }
        
    public void setMoveConditions(int x, int y, boolean hm, boolean vm) {
        curX = x; curY = y;
        setBounds(curX, curY, width, height);
        horizontalMove = hm; verticalMove = vm;
    }
    
    public void disappear()     { setIcon(null); }
    public void appear()        { setIcon(moveIcon); }
    
    public void moveUp(){
        if(verticalMove == false){
            if(curY == parentFrame.getGround()) curY = parentFrame.getFence();
            if(curY < parentFrame.getFence()) curY = parentFrame.getFence();
            setBounds(curX, curY, width, height);
        }
        if(verticalMove == true){
            if(curY == parentFrame.getGround()) curY = parentFrame.getFence();
            curY = curY - 20;
            if(curY < 0) curY = 0;
            setBounds(curX, curY, width, height);
        }
    }
    
    public void moveDown(){
        if(verticalMove == false){
            if(curY > parentFrame.getGround()) curY = parentFrame.getGround(); 
            if(curY == parentFrame.getFence()) curY = parentFrame.getGround();  
            setBounds(curX, curY, width, height);
        }
        if(verticalMove == true){
            curY = curY + 20;
            if(curY == parentFrame.getFence()) curY = parentFrame.getGround();  
            if(curY > parentFrame.getGround()) curY = parentFrame.getGround();  
            setBounds(curX, curY, width, height);
        }
    }
    
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

class DragLabel extends MoveLabel implements MouseMotionListener
{
    private MoveLabel activeLabel;
    
    public DragLabel(String file, int w, int h, CatAndBirdFrame pf)    
    { 
        super(file, w, h, pf);
        addMouseMotionListener(this);
    }    

    public void setActiveLabel(MoveLabel la)   { activeLabel = la; } 
    
    public void mousePressed( MouseEvent e ){ }
    public void mouseReleased( MouseEvent e ){ }
    public void mouseMoved( MouseEvent e ){ }
    public void mouseClicked( MouseEvent e ){ }
    public void mouseEntered( MouseEvent e ){ } 
    public void mouseExited( MouseEvent e ){ }
    
    @Override
    public void mouseDragged( MouseEvent e ) 
    { 

        curX = curX + e.getX();
        curY = curY + e.getY();
            
        Container par = getParent();
        if (curX < 0)  curX = 0;
        if (curY < 0)  curY = 0;
        if (curX + width  > par.getWidth())   curX = par.getWidth() - width;
        if (curY + height > par.getHeight())  curY = par.getHeight() - height;
        
        if ( this.getBounds().intersects(activeLabel.getBounds()) ){
            activeLabel.disappear();
        }
        else{
            activeLabel.appear();
        }
        setLocation(curX, curY);
    }
}

// auxiliary class to resize image
class MyImageIcon extends ImageIcon{ 
    
    public MyImageIcon(String fname)  { super(fname); }
    public MyImageIcon(Image image)   { super(image); }

    public MyImageIcon resize(int width, int height) {
        
        Image oldimg = this.getImage();
        Image newimg = oldimg.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return new MyImageIcon(newimg);
    }
}