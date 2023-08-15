/**
 * The Huffman CodeBook class serves as a component of the HuffmanCodeTree class.
 * Structure is similar to a binary node, with 2 nodes representing zero and one.
 */
public class HuffmanNode {

    private HuffmanNode zero; //left node
    private HuffmanNode one; //right node //null node is a leaf
    private Character data;

    /**
     * constructor
     * initlizes nodes zero and one with provided parameters zero and one
     */
    public HuffmanNode(HuffmanNode zero, HuffmanNode one){
        this.zero = zero;
        this.one = one;
        //this.data = null;
    }

    /**
     * constructor
     * initlizes nodes zero and one as null
     * intilizes data with provided parameter data
     */
    public HuffmanNode(char data){
        this.zero = null;
        this.one = null;
        this.data = data;
    }

    /**
     * getters
     */
    public HuffmanNode getZero() {
        return zero;
    }

    public HuffmanNode getOne() {
        return one;
    }

    public Character getData() {
        return data;
    }

    /**
     * setters
     * @param zero, one, data. the parameters is the new value/item/thing youre setting
     *              one of the private global variables to
     */
    public void setZero(HuffmanNode zero) {
        this.zero = zero;
    }

    public void setOne(HuffmanNode one) {
        this.one = one;
    }

    public void setData(char data) {
        this.data = (Character) data;
    }

    /**
     *
     * @return
     */
    public boolean isLeaf(){
        return data != null && zero == null && one == null;
    }

    /**
     *
     * @return
     */
    public boolean isInternal(){
        return data == null && zero != null && one != null;
    }

    /**
     *
     * @return
     */
    public boolean isValid(){
        if(isLeaf()){
            return true;
        } else if (isInternal()){
            return zero.isValid() && one.isValid();
        } else {
            return false;
        }
    }
}
