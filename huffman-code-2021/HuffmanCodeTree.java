/**
 * The HuffmanCodeTree class builds and maintains a binary tree that represents a collection of Huffman
 * codes for various letters. It can also be used to decode encoded text file.
 */

import java.lang.StringBuilder;

public class HuffmanCodeTree {
    private HuffmanNode root;

    /**
     * constructor
     * initlizes nodes root with provided parameter root
     */
    public HuffmanCodeTree(HuffmanNode root){
        this.root = root;
    }

    /**
     * constructor
     * creates a tree with provided parameter Huffman codebook
     */
    public HuffmanCodeTree(HuffmanCodeBook codebook){
        this.root = new HuffmanNode(null,null);
        for(int i = 0; i < codebook.getSize(); i++){
            char letter = codebook.getChar(i);
            BinarySequence seq = codebook.getSequence(i);
            put(seq,letter);
        }
    }

    /**
     * check if the tree formed by the root node and it’s descendants is a valid Huffman code tree
     * @return if tree is valid or not
     */
    public boolean isValid(){
        return root.isValid();
    }

    /**
     * modify the binary tree structure so that the node “addressed” by the binary sequence stores the given char
     * @param seq
     * @param letter
     */
    public void put(BinarySequence seq, char letter){
        HuffmanNode currentNode = this.root;
        //for(boolean digit: seq){
        for(int i = 0; i < seq.size(); i++){
            if(seq.get(i)){ //true -> one/1
                if(currentNode.getOne() == null){
                    HuffmanNode one = new HuffmanNode(null, null);
                    currentNode.setOne(one);
                }
                currentNode = currentNode.getOne();
            } else {
                if(currentNode.getZero() == null){
                    HuffmanNode zero = new HuffmanNode(null, null);
                    currentNode.setZero(zero);
                }
                currentNode = currentNode.getZero();
            }
        }
        currentNode.setData(letter);
    }

    /**
     * decode a BinarySequence into a string
     * @param s
     * @return decoded binary sequence (should return text)
     */
    public String decode(BinarySequence s){
        StringBuilder sBuilder = new StringBuilder();
        HuffmanNode currentNode = this.root;
        for(boolean digit: s){
            if(digit){
                currentNode = currentNode.getOne();
            } else { //else if
                currentNode = currentNode.getZero();
                //to go back to thr root you just set the currentNode to  this.root
            }
            if(currentNode.isLeaf()) {
                sBuilder.append(currentNode.getData());
                currentNode = this.root;
            }
        }

        return sBuilder + "";
    }
}
