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
		    //System.out.println("New highest: " + Integer.toString(number) + " with " + Integer.toString(highestBits) + " set bits");
		    
		}
		String binary = Integer.toBinaryString(number);
		//System.out.println("line: " + line + " int: " + binary);
		//If less than the greatest
		if(binary.length() < highestBits) {
		    //lengthen out to the number of highest bits
		    //System.out.println("need to lengthen");
		    binary = lengthen(binary, highestBits);
		    //System.err.println("Lengthed: " + binary);	    
		}
		//Now need to pack bits into integers
		//put line into outputLine
		outputLine = outputLine + binary;
		//System.out.println("outputLine: " + outputLine);
		if(outputLine.length() > 8){
		    //System.out.println("CAN OUTPUT the 8 bits");
		    outputLine = outputByte(outputLine);
		    //System.out.println("new outputLine: " + outputLine.toString());
		}
		line = br.readLine();
		}
	    //Now need to output the rest
	    outputEnd(outputLine);
	}
	catch(Exception e){
	    System.err.println(e.getMessage());
	}
    }

    public static String lengthen(String binary, int highestBits){
	String result;
	StringBuilder sb = new StringBuilder();
	for(int i = 0; i< highestBits; i++){
	    sb.append("0");
	}
	String unedited = sb.toString();
	result = unedited.substring(binary.length()) + binary;
	return result;	
    }
    public static String outputByte(String outputLine){
        String result;
	String output = outputLine.substring(0, 8);
	//System.out.println("time to output: " + output);
	//shift the outputted bits out of string
	result = outputLine.substring(8);
	//print out byte 1 char at a time
	for(int i = 0; i<output.length(); i++){
	    System.out.print(output.charAt(i));
	}
	//Return the lesser string now
	return result;
    }
    public static void outputEnd(String outputLine){
	for(int i = 0; i< outputLine.length(); i++){	    
	    System.out.print(outputLine.charAt(i));
	}
	System.out.println();
    }
}
