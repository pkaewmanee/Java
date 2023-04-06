// A class to represent a human
class Human {
    private String name;
    public Human(String n) { 
        name = n; 
    }
    public String getName() { 
        return name; 
    }
}

// A class that represents a soul which can run
class MySoul extends Human implements Runnable {
    public MySoul(String n) { 
        super(n); 
    }
    public String activity() { 
        return ("runnable activity"); 
    }

    @Override
    public void run() {
        // Access the current thread, sleep, and print a message
        Thread me = Thread.currentThread();
        try { 
            me.sleep(200); 
        } catch (InterruptedException e) { 
        }
        System.out.printf("Thread = %-8s   runnable = %-4s   -->   run in %s \n\n", 
            me.getName(), getName(), getClass().getName());
    }
}

// A class that represents a body which can be run as a thread
class MyBody extends Thread  {
    private MySoul soul;	
    public MyBody(MySoul s, String n) { 
        super(s, n); 
        soul = s; 
    }

    @Override
    public void run()  {
        // Print a message and call a method from the associated soul object
        System.out.printf("Thread = %-8s   runnable = %-4s   -->   run in %s ", 
            getName(), soul.getName(), getClass().getName());
        System.out.printf("   -->   call %s \n\n", soul.activity());
    }
}

// A class that represents a body of a different gender which can be run as a thread
class HerBody extends Thread {
    private MySoul soul;
    public HerBody(MySoul s, String n) { 
        super(s, n); 
        soul = s; 
    }
}

// The main class that creates threads and runs them
class HumanThread {
    public static void main(String[] args) {
        // Create and start the threads
        Thread tbody  = new Thread(new MySoul("TSoul"), "Thread"); 
        MyBody mbody  = new MyBody(new MySoul("MSoul"), "MyBody");
        HerBody hbody = new HerBody(new MySoul("HSoul"), "HerBody");
        
        tbody.start();  
        mbody.start();  
        hbody.start();
    }
}

