class Trie {
    Node root;
    int current;

    //Public constructor
    //SIZE ARGUMENT ??
    //Creates a root Node for the trie with phrase 0, number 0;
    public Trie (int size){
	current = 0;
	int phrase = 0;
	root = new Node(phrase, current);
    }

    //Initialise the Trie with the RESET number and all 255 byte values
    public void initialise(){
	int reset = 0;
	current++;
	//add reset with number = 1 directly to root
	root.add(reset, current);
	//loop for all byte values
	for(int i = 0; i<256; i++){
	    current++;
	    //add to root
	    root.add(i, current);
	}
	current++;
    }

    //Prints the initalised root values
    //Used for error checking
    public void print(){
	//print root itself
	System.err.print("Root: ");
	root.printNode();
	//print the reset Node
	System.err.print("Reset: ");
	root.children[0].printNode();
	//print out all the byte values
	for(int i = 1; i<root.children.length; i++){
	    if(root.children[i] != null)
		root.children[i].printNode();
	}
    }

    //Adds and item to a given Node parent
    //Links with the Node.add() function for a given Node
    //Need this to keep atrack of the current phrase number counter of the trie
    //Returns an int = parent's phrase number to output to system.out
    public int add(Node parent, int item){
	//add the item to the parent's children Node array with the current phrase number value
	parent.add(item, current);
	//increment it so no double up
	current++;
	//return the parent's phrase number
	return parent.number;
    }    

    //FindChild method specifically for the root of Trie
    //Need to skip the RESET node - cant do that in the Node findChild
    //Returns the Node of the child that matches the given phrase item
    //If not found returns null
    //NON ROOT ITEMS -- use Node.FindChild() method
    public Node rootFind(int item){
	//loop through all root's children, excluding the first RESET node
	for(int i = 1; i< root.children.length; i++){
	    //if the item we are searching for matches the current child's phrase
	    if(root.children[i].phrase == item)
		//return that Node reference
		return root.children[i];
	}
	//Otherwise it was not found so return null
	return null;
    }

    //RESETS the node when it past its maximum size
    public void reset(){
	//loses all the root's children - to reset
	root = null;
	//sets the current count to zero
	setCurrent(0);
	//create a new clear root Node to 
	root = new Node(0, current);
	//reinitialises reset value and byte values
	initialise();
    }
    
    //Getter and Setter for the Current Values
    //Used for when Trie is RESET
    public int getCurrent() { return current; }
    public void setCurrent(int value) { current = value; }
    //returns an integer of the RESET value
    public int getResetValue() { return root.children[0].number; }
}
