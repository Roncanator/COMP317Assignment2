/*******************************************************
 * 
 *          Alexander Bakx - 1283648
 *          Riley Cochrane - 1218251
 *
 *******************************************************/

class Node {

    int phrase;
    int number;
    Node[] children;
    int filled;

    //Public Constructor
    //Children of a node are an array of Nodes - expands when it gets full
    public Node(int p, int n){
	phrase = p;
	number = n;
        children = new Node[30];
	filled = 0;
    }

    //Adds a byte phrase and phrase number to the Node
    public void add(int phrase, int number){
	//create a new Node with the given phrase and number
	Node newnode = new Node(phrase, number);
	//If the the children array is full resize it
	if(filled >= children.length){
	    resize();
	}
	//loop through the array and enter it into the first empty slot
	for(int i = 0; i< children.length; i++){
	    //if its null its free
	    if(children[i] == null){
		//so add the node
		children[i] = newnode;
		//increment fill so its not behind
		filled++;
		//break cause the loop has found the latest element to fill
		break;
	    }
	}
    }
    
    //Resize the children array by 1.2 when it is fill
    //The root Node will need to resize - needs 256 Nodes
    //But hopefully no other nodes need to resize much beyond the initial 30
    private void resize(){
	int larger = children.length;
	//Multiply the current size by 1.2 - random value
	larger = (int)(larger * 1.2);
	//create a new Node array with the larger size
	Node[] result = new Node[larger];
	//copy the old children array to the larger one
	for(int i = 0; i<children.length; i++)
	    result[i] = children[i];
	//rerefence the children array as the bigger array
	children = result;
    }
    
    //Argument: item - the given byte to find
    //loops through the child array and compares the item to the current phrase
    //Returns a reference to that node if they are the same
    //A node will only have unique children so no chance of double ups
    public Node findChild(int item){
	for(int i = 0; i< children.length; i++){
	    //if looped past all the children values
	    //then its not in children so return null value
	    if(children[i] == null){
		return null;
	    }
	    //if the phrase and item match
	    //return reference to that child Node
	    if(children[i].phrase == item){
		return children[i];
	    }
	}
	//else found nothing so return null
	return null;
    }
    //Prints the Phrase and Number for a given Node
    //Used for error checking
    public void printNode(){
	if(this == null)
	    System.err.println("I AM NULL");
	else{
	    String line = "Phrase: " + Integer.toString(phrase) + ", Number: " + Integer.toString(number);
	    System.err.println(line);
	}
    }

    //Prints all the children for the Node
    //Printed in the order they are in the array 0 -> length-1
    //Used for error checking
    public void printChildren(){
	for(int i = 0; i< children.length; i++)
	    //if its not null
	    if(children[i] != null)
		//print the Node
		this.children[i].printNode();
    }   
}
