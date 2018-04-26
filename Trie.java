class newtrie {
    Node root;
    int current;

    public newtrie (int size){
	current = 0;
	Byte phrase = (byte)0;
	root = new Node(phrase, current);
    }

    public void initialise(){
	Byte reset = (byte)0;
	current++;
	root.add(reset,current);
	for(int i = 0; i<256; i++){
	    Byte b = (byte)i;
	    current++;
	    root.add(b, current);
	}	
    }

    public void print(){
	System.err.print("Root: ");
	root.printNode();
	System.err.print("Reset: ");
	root.children[0].printNode();
	for(int i = 1; i<root.children.length; i++){
	    if(root.children[i] != null)
		root.children[i].printNode();
	}
    }

    public Boolean contains(Byte find, Node parent){
	for(int i = 0; i<parent.children.length; i++){
	    if(parent.children[i].phrase == find){
		return true;
	    }
	}
	return false;
    }

    public int add(Node parent, Byte item){
	parent.add(item, current);
	current++;
	return parent.number;
    }

    public int getCurrent() { return current; }
    public void setCurrent(int value) { current = value; }

    
    /*
    public Node getNodeFromChain(Node[] chain){
	Node parent = root;
	Node last = null;
	int chaincounter = 0;
	for(int i = 0; i< chain.length; i++){
	    if(parent.children[i] == null)
		last = parent;
	    if(parent.children[i].phrase == chain[chaincounter]){
		chaincounter ++;
		parent = parent.children[i];
	    }
	}
hh	return last;
	}*/

    //Finds a node from a given parent
    //searches alll children of the parent until the phrase matches
    //Returns a reference to that child node
    //else if its not there then return null
    public Node findChild(Node previous, Byte item){
	for(int i = 0; i<previous.children.length; i++){
	    if(previous.children[i].phrase == item)
	        return previous.children[i];
	}
	System.err.println("IVE FOUND NOTHING");
	Byte b = (byte)0;
	Node blank = new Node(b, 0);
	return blank;	
    }

    /*public Node findChild(Node parent, Byte find){
	for(int i = 0; i<
	}*/
}
