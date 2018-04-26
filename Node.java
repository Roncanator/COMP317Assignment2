class Node {

    Byte phrase;
    int number;
    Node[] children;
    
    public Node(Byte p, int n){
	phrase = p;
	number = n;
        children = new Node[300];
    }

    //Handy to return the parent phrase number?
    public void add(Byte phrase, int number){
	Node newnode = new Node(phrase, number);
	for(int i = 0; i< children.length; i++){
	    if(children[i] == null){
		children[i] = newnode;
		break;
	    }
	}
    }
    //Finds the
    public Node findChild(Byte item){
	for(int i = 0; i< children.length; i++){
	    if(children[i] == null){
		return null;
	    }
	    if(children[i].phrase == item){
		return children[i];
	    }
	}
	return null;
    }

    public void printNode(){
	if(this == null)
	    System.err.println("I AM NULL");
	else{
	    String line = "Phrase: " + printAsByte() + ", Number: " + Integer.toString(number);
	    System.err.println(line);
	}
    }
    
    public String printAsByte(){
	String result;
	int value = phrase;
	result = Integer.toBinaryString(value);
	if(result.length() > 7)
	    result = result.substring(24);
	else{
	    String unpad = result;
	    result = "00000000".substring(unpad.length()) + unpad;
	}
	return result;
    }

    
}
