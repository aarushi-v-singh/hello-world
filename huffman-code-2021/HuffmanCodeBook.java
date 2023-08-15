/**
 * The Huffman CodeBook class represents the “codebook” of the Huffman coding process, that is,
 * it tells us, for each letter – what is the correct binary sequence.
 */
public class HuffmanCodeBook {
    private char[] chars;
    private BinarySequence[] binarySeqs;
    private int size;

    /**
     * constructor
     * initilizes size
     * creates 2 new arrays to track chars and associated binary sequences
     */
    public HuffmanCodeBook(){
        size = 0;
        this.chars = new char[size];
        this.binarySeqs = new BinarySequence[size];
    }

    /**
     * add in character and it's associated binary sequence to HuffmanCodeBook
     * @param c character (letter)
     * @param seq binary sequence
     */
    public void addSequence(char c, BinarySequence seq){
        resize(size+1);

        for(int i = 0; i < size; i++){
            if(c < chars[i]){
                shiftBack(i);
                chars[i] = c;
                binarySeqs[i] = seq;
                break;
            }
            if(i == (size-1)){
                chars[i] = c;
                binarySeqs[i] = seq;
            }
        }
    }

    /**
     * helper function
     * resizes keys and values array
     */
    public void resize(int newSize) {
        char[] newChars = new char[newSize];
        BinarySequence[] newBinarySeqs = new BinarySequence[newSize];
        if(size != 0){
            for (int i = 0; i < size; i++) {
                newChars[i] = chars[i];
                newBinarySeqs[i] = binarySeqs[i];
            }
        }
        size = newSize;
        chars = newChars;
        binarySeqs = newBinarySeqs;
    }

    /**
     * helper function
     * shifts back elements after the point you will add in c,
     * so that by adding c you dont erase the element that was originally in c's position
     */
    public void shiftBack(int i){
        for(int j = size-2; j >= i; j--){
            chars[j+1] = chars[j];
            binarySeqs[j+1] = binarySeqs[j];
        }
    }

    /**
     * checks if letter is in HuffmanCodeBook
     * @param letter
     * @return true if and only if a previous call to addSequence
     *         has added this letter
     */
    public boolean contains(char letter){
        int low = 0;
        int high = size -1;

        while(low <= high){
            int mid = (low + high)/2;
            if (chars[mid] < letter) {
                low = mid + 1;
            } else if(chars[mid] > letter){
                high = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * @param letters
     * @return return true if and only if every letter in the input
     *         string is contained in the codebook
     */
    public boolean containsAll(String letters){
        for(int i = 0; i < letters.length(); i++){
            //char letter = letters.charAt(i);
            if(!contains(letters.charAt(i))){
                return false;
            }
        }
        return true;
    }

    public int getSize(){
        return size;
    }

    public char getChar(int index){
        return chars[index];
    }

    /**
     * get the binary sequence associated with the given letter
     * @param c
     * @return return associated binary sequence if letter is in HuffmanBookCode
     *         otherwise return null if its not in it
     */
    public BinarySequence getSequence(char c){
        if(!contains(c)){
            return null;
        }

        int low = 0;
        int high = size -1;
        while(low <= high){
            int mid = (low + high)/2;
            if (chars[mid] < c) {
                low = mid + 1;
            } else if(chars[mid] > c){
                high = mid - 1;
            } else {
                return binarySeqs[mid];
            }
        }
        return null;
    }

    /**
     * get the binary sequence at index i
     * @param i is index
     * @return binary sequence at provided index
     */
    public BinarySequence getSequence(int i){
        return binarySeqs[i];
    }

    /**
     * encode the input string into a binary sequence
     * @param s
     * @return
     */
    //will not have to test for cases where chars in s arent in codebook; choose to crash if it doesnt exist
    public BinarySequence encode(String s){
        BinarySequence retVal = new BinarySequence();
        for(int i = 0; i < s.length(); i++){
            retVal.append(getSequence(s.charAt(i)));
        }
        return retVal;
    }

}