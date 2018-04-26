import java.io.DataInputStream;

class test {

    public static void main(String[] args){
	try{
	    //check there is only 1 argument passed
	    if(args.length != 1){
		System.err.println("USAGE ERROR:" + 
                  "Integer needed for maximum number of bits to encode");
		return;
	    }
	    //if there are no bytes to read from System.in, return
	    if(System.in.available() == 0){
		System.err.println("No input bytes detected");
		return;
	    }
	    int maximum = Integer.parseInt(args[0]);
	    //Answer to the question on moodle MUST CHANGE!!
	    if(maximum  == 0){
		System.err.println("Number of bits must be less than 1 byte");
		return;
	    }
	    
	    DataInputStream dis = new DataInputStream(System.in);
	    newtrie trie = new newtrie(5);
	    trie.initialise();
	    trie.print();
	    //need current?
	    int current = trie.getCurrent() + 1;
	    //dont need this chain stuff
	    int tracker = 0;
	    Byte[] chain = new Byte[50];
	    //finalised
	    int read;
	    read = dis.read();
	    System.err.println("First Read: " + Integer.toString(read));
	    Byte item = (byte)read;
	    Byte further = item;
	    Node previous;
	    Node latest = trie.root.findChild(item);
	    try{
		while(read != -1){
		    System.err.println("1 while");
		    item = (byte)read;
		    System.out.print("Latest: " );
		    latest.printNode();
		    previous = latest;
		    try{
			while(latest != null){
			    System.err.println("2 while");
			    read = dis.read();
			    item = (byte)read;
			    latest = previous.findChild(item);
			    System.out.print("Latest: " );
			    latest.printNode();
			}
		    }
		    catch(Exception e){
			System.err.println(e.getMessage());
		    }
		    int output = trie.add(previous, item);
		    System.out.println(Integer.toString(output));
		    read = dis.read();
		    System.err.println("Next Read: " + Integer.toString(read));
		}
	    }
	    catch(Exception e){
		System.err.println(e.getMessage());
	    }
	    		
		/*
		//Found the initial root child that read matches
		try{
		    while(latest != null){
			//get the next read element
			read = dis.read();
			item = (byte)read;
			//search the latest node root
			try{
			    if(read != -1){
				System.err.println("can read another");
				previous = latest;
				
				latest = previous.findChild(item);
				/*if(latest == null){
				  System.err.println("Ive found null");
				  }	
				
				System.err.print("Latest: ");
				latest.printNode();
				System.err.println("Now to add");
			    }
			    else{
				//its the end of file - special case
			    }
			}
			catch(Exception e){
			    System.err.println(e.getMessage());
			}
	
			//Need to add item to previous' children
			//& output previous number
			int previousNumber = trie.add(previous, item);
			System.err.println("Added to previous");
			System.err.println("Previous Number : " + Integer.toString(previousNumber));
			
			System.err.println("LOOPING");
		    }
		}
		catch(Exception e){
		    System.err.println(e.getMessage());
		}
		System.err.println("NO MATCHING CHILD");
		read = dis.read();
		
		}*/
	    
	    /*
	    Byte tofind = (byte)245;
	    Node found = trie.findRootChild(tofind);
	    found.printNode();*/
	}
	catch(Exception e){
	    System.err.println(e.getMessage());
	}
    }
}
