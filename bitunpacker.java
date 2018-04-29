/*******************************************************
 * 
 *          Alexander Bakx - 1283648
 *          Riley Cochrane - 1218251
 *
 *******************************************************/

import java.io.*;
import java.io.IOException;

class bitunpacker {

    //Get the number of bits needed to encode an integer
    public static int getNumBits(int value) {
	int count = 0;
	while (value > 0) {
	    count++;
	    //Shift by 1
	    value = value >> 1;
	}
	return count;
    }

    public void unpack() throws IOException {
	//Size of dictionary to keep track of how many bits are needed to encode a phrase number
	int dictSize = 255;

	//Keep track of how many bits left in the output
	int numPhraseBits = 0;
	int inputBytePiece = 0;
	int inputByte = 0;
	//Keep track of how many bits left in the next byte
	int remainingBits = 0;
	//Output phrase number
	int phraseNumber = 0;

	try{
	    //While not end of stream
	    while(inputByte != -1) {		
		//Read in new byte from standard input
		inputByte = System.in.read();
		//Number of bits remaining to be output
		remainingBits = 8;

		//While we have bits from previous byte
		while (remainingBits > 0){
		    //0 free bits in the phrase, eg new phrase number
		    if(numPhraseBits == 0){
			//Get number of bits needed to encode the biggest possible phrase number
			numPhraseBits = getNumBits(dictSize);
		    }
		    //The remaining bits all fit into the current phrase
		    if(numPhraseBits < remainingBits){
			//Bit in the second byte left shifted into the most significant bit
			inputBytePiece = inputByte >> (8 - numPhraseBits);
			//AND operation
			inputByte &= (int)(Math.pow(2, 8 - numPhraseBits) - 1);
			//Left shift it by number of free bits
			phraseNumber <<= numPhraseBits;
			//Or it with the first byte to get integer
			phraseNumber |= inputBytePiece;
			//Set remaining bits to be the number of bits that didn't fit in the phrase
			remainingBits -= numPhraseBits;
			numPhraseBits = 0;
		    } else {
			// Load the read bits into the output phrase
			phraseNumber <<= remainingBits;
			//OR operation with first byte to get integer
			phraseNumber |= inputByte;
			//Reduce number of free bits in the phrase by the remaining bits from the last byte
			numPhraseBits -= remainingBits;
			//All of the bits from the remaining byte have been output, so set number of remaining bits to 0
			remainingBits = 0;
		    }

		    //Once the output phrase has no empty bits
		    if(numPhraseBits == 0){
			//Output the phrase
			System.out.println(phraseNumber);
			//If the phrase number is the RESET symbol
			if(phraseNumber == 1){
			    //Reset the dictionary
			    dictSize = 257;
			} else {
			    //Increase the dictionary
			    dictSize++;
			}
			//Reset the phrase number
			phraseNumber = 0;
		    }
		}	
	    }
	} catch (IOException e){
	    //Error handling
	    System.err.println("Error reading from input stream");
	}
    }

    public static void main(String[] args) throws IOException {
        try {
	    //Create new unpacker
	    bitunpacker lzw = new bitunpacker();
	    lzw.unpack();

        } catch (IOException e) {
	    //Error handling
            System.out.println("Error running this program.");
        }
    }
}
