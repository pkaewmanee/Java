package Ex9;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.sound.sampled.*;     // for sounds

public class NemoFrame extends JFrame
{    
    // components
    private JPanel           contentpane;
    private JLabel           drawpane;
    private JComboBox        combo;
    private JToggleButton    []tb;
    private ButtonGroup      bgroup;
    private JButton          swimButton, stopButton, moreButton;
    private JTextField       scoreText;
    private MyImageIcon      backgroundImg;    
    private MySoundEffect    themeSound;
    
    private NemoLabel        nemoLabel;
    private NemoFrame        currentFrame;

    // working variables - adjust the values as you want
    private int frameWidth = 1200, frameHeight = 650;
    private int score;

    public static void main(String[] args) 
    {
        new NemoFrame();
    }    

    //////////////////////////////////////////////////////////////////////////
    public NemoFrame()
    {   
        setTitle("Nemo Game");
        setBounds(50, 50, frameWidth, frameHeight);
        setResizable(false);
	setVisible(true);
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        currentFrame = this;

        // (1) Add WindowListener (anonymous class)
        //     - Stop everything. Show total score


	contentpane = (JPanel)getContentPane();
	contentpane.setLayout( new BorderLayout() );        
        AddComponents();
    }

    //////////////////////////////////////////////////////////////////////////
    public void AddComponents()
    {
        String path = "src/main/java/Ex9/resources/"; 
        
	backgroundImg  = new MyImageIcon(path + "seabackground.jpeg").resize(frameWidth, frameHeight);
	drawpane = new JLabel();
	drawpane.setIcon(backgroundImg);
        drawpane.setLayout(null);

	themeSound = new MySoundEffect(path + "theme.wav"); 
        themeSound.playLoop(); themeSound.setVolume(0.4f);
        
        nemoLabel = new NemoLabel(currentFrame);
        drawpane.add(nemoLabel);
        
        
        // (2) Add ActionListener (anonymous class) to swimButton
        //     - If Nemo isn't swiming, create nemoThread to make him swim
	swimButton = new JButton("Swim");  
        
        
        // (3) Add ActionListener (anonymous class) to stopButton
        //     - Stop nemoThread, i.e. make it return from method run
	stopButton = new JButton("Stop");
   
        
	// (4) Add ItemListener (anonymouse class) to combo 
        //     - Set Nemo's speed, i.e. sleeping time for nemoThread
        String[] speed = { "fast", "medium", "slow"};
        combo = new JComboBox(speed);
	combo.setSelectedIndex(1);

        
	// (5) Add ItemListener (anonymouse class) to tb[i]
        //     - Make Nemo turn left/right
        tb = new JToggleButton[2];
        bgroup = new ButtonGroup();      
        tb[0] = new JRadioButton("Left");   tb[0].setName("Left");
        tb[1] = new JRadioButton("Right");  tb[1].setName("Right"); 
	tb[0].setSelected(true);

        for (int i=0; i < 2; i++)
        {
            bgroup.add( tb[i] );     
        }
        
        
        // (6) Add ActionListener (anonymous class) to moreButton
        //     - Create a new itemThread to control each item
	moreButton = new JButton("More item");
		

	scoreText = new JTextField("0", 5);		
	scoreText.setEditable(false);

        JPanel control  = new JPanel();
        control.setBounds(0,0,1000,50);
	control.add(new JLabel("Nemo Control : "));
        control.add(swimButton);
        control.add(stopButton);
        control.add(combo);
        control.add(tb[0]);
        control.add(tb[1]);
	control.add(new JLabel("                 "));
	control.add(new JLabel("Item Control : "));
	control.add(moreButton);
	control.add(new JLabel("                 "));
	control.add(new JLabel("Score : "));
	control.add(scoreText);
        contentpane.add(control, BorderLayout.NORTH);
        contentpane.add(drawpane, BorderLayout.CENTER);      
        validate();       
    }
    
    ////////////////////////////////////////////////////////////////////////////
    public void setNemoThread()
    {
        Thread nemoThread = new Thread() {
            public void run()
            {
                while (nemoLabel.isSwim())
                {
                    nemoLabel.updateLocation();
                }          
            } // end run
        }; // end thread creation
        nemoThread.start();
    }
    ////////////////////////////////////////////////////////////////////////////
    public void setItemThread()
    {
        Thread itemThread = new Thread() {
            public void run()
            {
                // (7) Create a new ItemLabel, add it to drawpane
                //     - Keep updating its location 
                //     - Check whether it collides with Nemo. If it does, play hit sound
                //       and update score
                //     - Once reaching the top/bottom of drawpane or colliding with Nemo, 
                //       remove the item from drawpane and end this thread                
            } // end run
        }; // end thread creation
        itemThread.start();
    }
    ////////////////////////////////////////////////////////////////////////////
    public void updateScore(int hp)
    {
        // (8) Score update must be synchronized since it can be done by >1 itemThreads
    }
} // end outer class NemoGame


////////////////////////////////////////////////////////////////////////////////
class NemoLabel extends JLabel 
{
    private MyImageIcon   leftImg, rightImg;
    private NemoFrame     parentFrame;
        
    String [] imageFiles = {"src/main/java/Ex9/resources/nemo_left.png", 
                            "src/main/java/Ex9/resources/nemo_right.png"};
        
    // working variables - adjust the values as you want
    private int width = 180, height = 120; 
    private int curX  = 700, curY   = 200;   
    private int speed = 500;
    private boolean left = true, swim = false;        
        
    public NemoLabel(NemoFrame pf)
    {
        parentFrame = pf;
            
        leftImg  = new MyImageIcon(imageFiles[0]).resize(width, height);
        rightImg = new MyImageIcon(imageFiles[1]).resize(width, height);
        setIcon(leftImg);
        setBounds(curX, curY, width, height);
    }
        
    public void setSpeed(int s)     { speed = s; }
    public void turnLeft()          { setIcon(leftImg);  left = true; }
    public void turnRight()         { setIcon(rightImg); left = false; }
    public void setSwim(boolean w)  { swim = w; }
    public boolean isSwim()         { return swim; }
        
    public void updateLocation()
    {
        if (left)
        {   
            curX = curX - 50;
            if (curX < -100) { curX = parentFrame.getWidth(); } 			
        }
        else
        {
            curX = curX + 50;
            if (curX > parentFrame.getWidth()-100) { curX = 0; }			
        }
        setLocation(curX, curY);
        repaint();             
        try { Thread.sleep(speed); } 
        catch (InterruptedException e) { e.printStackTrace(); }            
    }
} // end class NemoLabel
    
    
class ItemLabel extends JLabel 
{
    private static boolean  allMoving = true;    // controlling all items at once    
    private int            type;                 // 0 = bad item (falling down), 1 = good item (floating up)
    private MyImageIcon    itemImg;
    private MySoundEffect  hitSound;
    private NemoFrame      parentFrame;
    
    String [] imageFiles = {"src/main/java/Ex9/resources/hook.png", 
                            "src/main/java/Ex9/resources/plankton.png"};
        
    String [] soundFiles = {"src/main/java/Ex9/resources/punch.wav",
                            "src/main/java/Ex9/resources/coin.wav"};
        
    // working variables - adjust the values as you want
    private int width = 40, height = 60;
    private int curX, curY;
    private int speed = 400;

    public ItemLabel(NemoFrame pf)
    {
        parentFrame = pf;
        
        curX = (int)(Math.random() * 5555) % (parentFrame.getWidth()-100);
        if (curX % 2 == 0) 
        { 
            type = 0;                              // bad item
            curY = 0;                              // starting at the top & falling down
        }
        else               
        { 
            type = 1;                              // good item
            curY = parentFrame.getHeight()-60;     // starting at the bottom & floating up
        }
          
        itemImg  = new MyImageIcon(imageFiles[type]).resize(width, height);
        hitSound = new MySoundEffect(soundFiles[type]);
        setIcon(itemImg);
        setBounds(curX, curY, width, height);
    }
        
    public void playHitSound()              { hitSound.playOnce(); }
    public int  getHitPoints()              { if (type == 0) return -1; else return 1; }

    // for all items in the program
    public static void    setAllStop()      { allMoving = false; }
    public static boolean isAllMoving()     { return allMoving; }    

} // end class ItemLabel


////////////////////////////////////////////////////////////////////////////////
// Auxiliary class to resize image
class MyImageIcon extends ImageIcon
{
    public MyImageIcon(String fname)  { super(fname); }
    public MyImageIcon(Image image)   { super(image); }

    public MyImageIcon resize(int width, int height)
    {
	Image oldimg = this.getImage();
	Image newimg = oldimg.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
	return new MyImageIcon(newimg);
    }
};


// Auxiliary class to play sound effect (support .wav or .mid file)
class MySoundEffect
{
    private Clip         clip;
    private FloatControl gainControl;         

    public MySoundEffect(String filename)
    {
	try
	{
            java.io.File file = new java.io.File(filename);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);            
            gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
	}
	catch (Exception e) { e.printStackTrace(); }
    }
    public void playOnce()             { clip.setMicrosecondPosition(0); clip.start(); }
    public void playLoop()             { clip.loop(Clip.LOOP_CONTINUOUSLY); }
    public void stop()                 { clip.stop(); }
    public void setVolume(float gain)
    {
        if (gain < 0.0f)  gain = 0.0f;
        if (gain > 1.0f)  gain = 1.0f;
        float dB = (float)(Math.log(gain) / Math.log(10.0) * 20.0);
        gainControl.setValue(dB);
    }
}