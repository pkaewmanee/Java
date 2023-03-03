package Lab_Ch6;

class MyDeadThread extends Thread
{
    private boolean killed;

    MyDeadThread(String name) 
    { 
	super(name); 
	killed = false;
	start(); 
    }

    public void kill()		{ killed = true; }

    @Override
    public void run()
    {
        int i = 1;
        
        // ----- (1) prepare for unexpected death
        /*
	try 
	{            
            while (i <= 900)
                {  System.out.printf("%4d ", i); i++; }
	}
	catch (ThreadDeath e)
	{
            System.out.println("\n\n***** clean up task (ThreadDeath)");
            throw e;
	}
        */

        
        // ----- (2) use program logic
        /*
        while (i <= 900 && !killed)
            {  System.out.printf("%4d ", i); i++; }
        */
        
        
        if (!killed)
            System.out.println("\n\n***** " + getName() + " dies normally");
        else
            System.out.println("\n\n***** clean up task (program logic)");
    }
};

//////////////////////////////////////////////////////////////////////////////////////////////////
class w6_3_Death
{
    public static void main(String[] args) 
    {
	MyDeadThread T1 = new MyDeadThread("T1");

	try { Thread.sleep(20); }  catch (InterruptedException e) { }

	// ----- (1) stop thread (deprecated method)
	//T1.stop();

	// ----- (2) stop thread without using explicit stop() --> safer
        //T1.kill();

        // ----- (3) busy waiting (not good)
        while (T1.isAlive()) { }
            System.out.println("\n\n***** T1 is dead");	
    }
}
