import java.io.DataInputStream;

class Encoder {

    public static void main(String[] args){
	try{
	    //check there is only 1 argument passed
	    if(args.length != 1){
		System.err.println("USAGE ERROR:" + 
                  "1 Integer needed for maximum number of bits to encode");
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
	    //Create a DataInputStream to read Bytes from System.in
	    DataInputStream dis = new DataInputStream(System.in);
	    Trie trie = new Trie(5);
	    //Initilaise the trie with RESET and initial byte values
	    trie.initialise();
	    //read a byte in
	    int read = dis.read();
	    int item = read;
	    //keep track of the previous item
	    int past = item;
	    //Find the root Child the read item is
	    Node latest = trie.rootFind(item);
	    //keep atrack of the previous Node
	    Node previous = latest;
	    try{
		//While not the end of the Byte Stream
		while(read != -1){
		    //loop trying to find next phrase in children
		    while(latest != null && read!= -1){
			//keep track of the last values
			previous = latest;
			past = item;
			//read the next value in
			read = dis.read();		
			item = read;
			//Check the next value is in a child of previous Node
			latest = previous.findChild(past);
			
		    }
		    //There has been a mismatch - latest is null
		    //If the item read is the end of file
		    if(item == -1){
			//break dont want to add to file at all
			break;
		    }
		    //get the mismatched parent's number to output
		    int no = trie.add(previous, item);
		    System.out.println(Integer.toString(no));
		    //Keep going with the mismatch character
		    latest = trie.rootFind(item);
		}
	    }
	    catch(Exception e){
		System.err.println(e.getMessage());
	    }
	}
	catch(Exception e){
	    System.err.println(e.getMessage());
	}
    }
}
