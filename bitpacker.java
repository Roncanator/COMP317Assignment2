/*******************************************************
 * 
 *          Alexander Bakx - 1283648
 *          Riley Cochrane - 1218251
 *
 *******************************************************/

import java.io.InputStreamReader;
import java.io.BufferedReader;

class bitpacker {

    public static void main(String[] args) {
	try{
	    if(args.length != 0){
		System.err.println("Program does not use any arguments");
		return;
		}
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String outputLine = "";
	    String line = br.readLine();
	    int number;
	    //The number of bits to read at a time
	    int highestBits = 9;
	    //highest value will be 2^9 = 512 initially
	    //more than 2^8 (256) bytes initialised
	    int highestBinary = (int)Math.pow(2, highestBits);	    
	    while(line != null){		
		number = Integer.parseInt(line);
		if(number > highestBinary){		    
		    //change the highest binary value
		    highestBinary = number;
		    //change highest Bits value
		    highestBits = (Integer.toBinaryString(number)).length();    
		}
		String binary = Integer.toBinaryString(number);
		//If less than the greatest
		if(binary.length() < highestBits) {
		    //lengthen out to the number of highest bits
		    binary = lengthen(binary, highestBits);	    
		}
		//Now need to pack bits into integers
		//put line into outputLine
		outputLine = outputLine + binary;
		if(outputLine.length() > 8){
		    outputLine = outputByte(outputLine);
		}
		line = br.readLine();
		}
	    //Now need to output the rest
	    outputEnd(outputLine);
	}
	catch(Exception e){
	    System.err.println("Bitpacker " + e.getLocalizedMessage());
	}
    }
    
    //Lengthens a string to the current maximum number of bits
    //This is to lengthen then to all the same size
    //EG initially values need to be 9 bits in length
    //so anything less needs to have 00s concatenated to the front
    public static String lengthen(String binary, int highestBits){
	String result;
	//Create a stringbuilder
	StringBuilder sb = new StringBuilder();
	//for the maximum amount of bits create a string that long
	for(int i = 0; i< highestBits; i++){
	    sb.append("0");
	}
	//Make it a string
	String unedited = sb.toString();
	//recude the size of the unedited bytes
	//and concatenate the original bits on
	result = unedited.substring(binary.length()) + binary;
	return result;	
    }

    //Output 8 Bits at a time
    //And removes the 8 bits off the outputLine
    public static String outputByte(String outputLine){
        String result;
	//get the next 8 bits to output
	String output = outputLine.substring(0, 8);
	//shift the outputted bits out of string
	result = outputLine.substring(8);
        int byteint = Integer.parseInt(output, 2);
	System.out.print(Integer.toString(byteint) + " ");
	//Return the lesser string now
	return result;
    }

    //If the output is not a multiple of 8 there will still be bits to print out
    //So print out the remaining bits in the string
    public static void outputEnd(String outputLine){
	int byteint = Integer.parseInt(outputLine, 2);
	System.out.print(Integer.toString(byteint));
	//put it all on one line of output
	System.out.println();
    }
}
