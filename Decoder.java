/*******************************************************
 * 
 *          Alexander Bakx - 1283648
 *          Riley Cochrane - 1218251
 *
 *******************************************************/

import java.io.*;
import java.util.*;

public class Decoder {

    //String table
    public HashMap<Integer, String> dictionary;
    //Size of dictionary
    public int dictSize = 258;
    public int currword;
    public int priorword;
    String S;
    String C;

    //Initialize the string table
    public void init(){
	//Reset dictionary for when it encounters RESET symbol
	dictSize = 258;
	dictionary = new HashMap<>();

	//Sets string table with initial 256 byte values from 2 - 257
	//1 is used for RESET symbol
        for (int i = 2; i < dictSize; i++) {
	    int j = i - 2;
            dictionary.put(i, Character.toString((char) j));
        }
    }

    //Decodes from standard input
    public void decode() throws IOException {
	//Initialize dictionary
	init();

	//Write stream of bytes to standard output
	DataOutputStream out = new DataOutputStream(System.out);
	//Read from standard input
	Scanner in = new Scanner(System.in);

        try {
	    //Get first input code
	    priorword = Integer.parseInt(in.next());
	    //Find it in the dictionary and output it
	    out.writeBytes(dictionary.get(priorword));
	    //While there is more data in the input stream
            while (in.hasNext()) {
		//Get the next input code
		currword = Integer.parseInt(in.next());
		//Check if it's not in the dictionary
                if (!dictionary.containsKey(currword)) {
		    //Find the previous code in the dictionary and get string
		    S = dictionary.get(priorword);
		    //Append C
		    if (C != null){
			S = S + C;
		    }
		//If the code is 1, which is the RESET symbol
                } else if (currword == 1){
		    //Reset the dictionary
		    init();
		} else {
		    //If it's in the dictionary, find the corresponding string
		    S = dictionary.get(currword);

                }
		//Output the string
		out.writeBytes(S);
		//Set C to the first character of S
		C = S.substring(0, 1);
		//Add the old string and C to the dictionary at the next free spot
		dictionary.put(dictSize++, dictionary.get(priorword) + C);
		//Set the old code to the new code
		priorword = currword;
            }
	    //To print
	    out.flush();
        } catch (EOFException e) {
            //Error handling
	    System.err.println("Error: EOFException");
        }
    }

    public static void main(String[] args) throws IOException {
        try {
	    //Create new decoder
	    Decoder lzw = new Decoder();
	    lzw.decode();

        } catch (FileNotFoundException e) {
	    //Error handling
            System.out.println("File was not found!");
        }
    }
}
