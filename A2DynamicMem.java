// Class: A2DynamicMem
// Implements Degragment in A2. No other changes should be needed for other functions.

public class A2DynamicMem extends A1DynamicMem {
      
    public A2DynamicMem() {  super(); }

    public A2DynamicMem(int size) { super(size); }

    public A2DynamicMem(int size, int dict_type) { super(size, dict_type); }

    // In A2, you need to test your implementation using BSTrees and AVLTrees. 
    // No changes should be required in the A1DynamicMem functions. 
    // They should work seamlessly with the newly supplied implementation of BSTrees and AVLTrees
    // For A2, implement the Defragment function for the class A2DynamicMem and test using BSTrees and AVLTrees. 

    public void Defragment() {
        AVLTree auxiliary = new AVLTree();
        // auxiliary is indexed by address rather than size
        Dictionary temp = this.freeBlk.getFirst();
        while(temp != null) {
            auxiliary.Insert(temp.address, temp.size, temp.address);
            temp = temp.getNext();
        }
        // all elements inserted
        temp = auxiliary.getFirst();
        if(temp == null) {
            return;
        }
        else{
            // temp != null
            Dictionary y = temp.getNext();
            while(temp != null && y != null) {
                if(temp.address + temp.size == y.address) {
                    // contiguous memory location
                    int n1 = temp.address, n2 = temp.size, n3 = y.address, n4 = y.size;
                    Dictionary d1 = new AVLTree(n1, n2, n1); // del from aux
                    Dictionary d2 = new AVLTree(n1, n2, n2); // del from freeBlk
                    Dictionary d3 = new AVLTree(n3, n4, n3); // del from aux
                    Dictionary d4 = new AVLTree(n3, n4, n4); // del from freeBlk
                    auxiliary.Delete(d1);
                    auxiliary.Delete(d3);
                    this.freeBlk.Delete(d2);
                    this.freeBlk.Delete(d4);
                    this.freeBlk.Insert(n1, n2 + n4, n2 + n4);
                    temp = auxiliary.Insert(n1, n2 + n4, n1);
                    y = temp.getNext();
                }
                else {
                    // not contiguous, no merging
                    temp = y;
                    y = y.getNext();
                }
            }
            // temp = null || y = null, defragmentation done
            return;
        }    
    }
}