import java.io.*;
import java.util.*;

public class Decoder {

    public HashMap<Integer, String> dictionary;
    public int dictSize = 258;
    public int currword;
    public int priorword;

    String S;
    String C = "";

    public void init(){
	dictSize = 258;
	dictionary = new HashMap<>();

        for (int i = 2; i < dictSize; i++) {
	    int j = i - 2;
            dictionary.put(i, Character.toString((char) j));
        }
    }

    
    public void LZW_Decompress() throws IOException {
	init();

	DataOutputStream out = new DataOutputStream(System.out);
	Scanner in = new Scanner(System.in);

        try {
	    priorword = Integer.parseInt(in.next());
	    out.writeBytes(dictionary.get(priorword));

            while (in.hasNext()) {

		currword = Integer.parseInt(in.next());

                if (!dictionary.containsKey(currword)) {

		    S = dictionary.get(priorword);
		    S = S + C;

                } else if (currword == 1){
		    init();
		} else {

		    S = dictionary.get(currword);

                }

		out.writeBytes(S);
		C = S.substring(0, 1);

		dictionary.put(dictSize++, dictionary.get(priorword) + C);
		priorword = currword;
            }
	    out.flush();
        } catch (EOFException e) {
            //TO-DO: Error handling
        }
    }

    public static void main(String[] args) throws IOException {
        try {

	    Decoder lzw = new Decoder();
	    lzw.LZW_Decompress();

        } catch (FileNotFoundException e) {
            System.out.println("File was not found!");
        }
    }
}
