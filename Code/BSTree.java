// Class: Implementation of BST in A2
// Implement the following functions according to the specifications provided in Tree.java

public class BSTree extends Tree {

    private BSTree left, right;     // Children.
    private BSTree parent;          // Parent pointer.
        
    public BSTree(){  
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node!.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
    }    

    public BSTree(int address, int size, int key){
        super(address, size, key); 
    }

    public BSTree Insert(int address, int size, int key) 
    { 
        // Create node
        BSTree N = new BSTree(address, size, key);
        BSTree temp = this;
        while(temp.parent != null) {
            temp = temp.parent;
        }
        // temp.parent == null, so temp is the sentinel
        BSTree y = temp;
        // from convention, the actual root is the right child of the sentinel.`
        temp = temp.right;
        while(temp != null) {
            y = temp;
            if(temp.key < key) {
                temp = temp.right;
            }
            else {
                // key <= temp.key
                if(key < temp.key) {
                    temp = temp.left;
                }
                else {
                    // key == temp.key
                    if(address > temp.address) {
                        temp = temp.right;
                    }
                    else {
                        // address <= temp.address
                        temp = temp.left;
                    }
                }    
            }
        }
        // temp = null and temp is y's child
        if(y != null && y.parent != null) {
            // y is not the root sentinel
            if(key > y.key) {
                y.right = N;
            }
            else {
                // key <= y.key
                if(key < y.key) {
                    y.left = N;
                }
                else {
                    // key == y.key
                    if(address > y.address) {
                        y.right = N;
                    }
                    else {
                        // address <= y.address
                        y.left = N;
                    }
                }    
            }
            N.parent = y;
            // return node
            return N;
        }
        else {
            // y = null || y.parent = null
            if(y == null) {
                // invalid bst, no sentinel
                return null;
            }
            else {
                //y is the root sentinel, so insertion is done on the right side (convention)
                y.right = N;
                N.parent = y;
                return N;
            }
        }
    }


    public boolean Delete(Dictionary e)
    { 
        if(e == null) {
            // Invalid node deletion
            return false;
        }
        else {
            // e != null
            BSTree temp = this;
            while(temp.parent != null) {
                temp = temp.parent;
            }
            // temp.parent = null, so it is the sentinel
            temp = temp.right;
            // temp is the root of the actual tree (from convention)
            // temp maybe null
            while(temp != null) {
                if(temp.key != e.key) {
                    if(temp.key < e.key) {
                        temp = temp.right;
                    }
                    else {
                        // temp.key > e.key
                        temp = temp.left;
                    }
                }
                else {
                    // temp.key == e.key
                    if(temp.address == e.address && temp.size == e.size) {
                        // element found
                        // temp is not the sentinel as the loop started with the right child of the sentinel, and we are only moving down the tree
                        // i.e. temp has a parent
                        // Case 1: temp has no children
                        if(temp.right == null && temp.left == null) {
                            BSTree y = temp.parent;
                            // y may or may not be the sentinel
                            if(y.parent == null) {
                                // y is the sentinel, so temp is its right child whatever its key value maybe
                                y.right = null;
                                temp.parent = null;
                                // node deleted
                            }
                            else {
                                // y is not the sentinel
                                if(temp.key > y.key) {
                                    y.right = null;
                                }
                                else {
                                    // temp.key <= y.key
                                    if(temp.key < y.key) {
                                        y.left = null;
                                    }
                                    else {
                                        // temp.key = y.key
                                        if(temp.address > y.address) {
                                            y.right = null;
                                        }
                                        else {
                                            // temp.address <= y.address
                                            y.left = null;
                                        }
                                    }    
                                }
                                temp.parent = null;
                                // node deleted
                            }
                        }    
                        else {
                            // temp has atleast one child
                            // Case 2: temp has a single child
                            if(temp.right == null) {
                                // temp.left != null
                                BSTree y = temp.parent;
                                // y may or may not be the sentinel
                                if(y.parent == null) {
                                    // y is the sentinel, therefore, temp is its right child no matter what its key value is
                                    y.right = temp.left;
                                    temp.left.parent = y;
                                    temp.parent = null;
                                    temp.left = null;
                                    // node deleted
                                }
                                else {
                                    // y is not the sentinel
                                    if(temp.key > y.key) {
                                        y.right = temp.left;
                                        temp.left.parent = y;
                                    }
                                    else {
                                        // temp.key <= y.key 
                                        if(temp.key < y.key) {
                                            y.left = temp.left;
                                            temp.left.parent = y;
                                        }
                                        else {
                                            // temp.key = y.key
                                            if(temp.address <= y.address) {
                                                y.left = temp.left;
                                                temp.left.parent = y;
                                            }
                                            else {
                                                // temp.address > y.address
                                                y.right = temp.left;
                                                temp.left.parent = y;
                                            }
                                        }       
                                    }
                                    temp.left = null; 
                                    temp.parent = null;
                                    // node deleted
                                }
                            }    
                            else {
                                // temp.right != null
                                if(temp.left == null) {
                                    BSTree y = temp.parent;
                                    // y may or may not be the sentinel
                                    if(y.parent == null) {
                                        // y is the sentinel, therefore, temp is its right child no matter what its key value is
                                        y.right = temp.right;
                                        temp.right.parent = y;
                                        temp.parent = null;
                                        temp.right = null;
                                        // node deleted
                                    }
                                    else {
                                        // y is not the sentinel
                                        if(temp.key > y.key) {
                                            y.right = temp.right;
                                            temp.right.parent = y;
                                        }
                                        else {
                                            // temp.key <= y.key
                                            if(temp.key < y.key) {
                                                y.left = temp.right;
                                                temp.right.parent = y;
                                            }
                                            else {
                                                // temp.key = y.key
                                                if(temp.address > y.address) {
                                                    y.right = temp.right;
                                                    temp.right.parent = y;
                                                }
                                                else {
                                                    // temp.address <= y.address
                                                    y.left = temp.right;
                                                    temp.right.parent = y;
                                                }
                                            }    
                                        }
                                        temp.right = null;
                                        temp.parent = null;
                                        // node deleted
                                    }
                                }    
                                else {
                                    // Case 3: temp has two children
                                    BSTree Right = temp.getNext();
                                    // Right is the successor of temp, so Right.left is null as Right is the minimum element of the right subtree of temp
                                    // Temporarily store the contents of Right
                                    int n1 = Right.key, n2 = Right.address, n3 = Right.size;
                                    // Delete Right (atmost one child { which is the right child })
                                    BSTree Right_parent = Right.parent;
                                    // Right_parent may be temp itself
                                    if(Right.key > Right_parent.key) {
                                        Right_parent.right = Right.right;
                                    }
                                    else {
                                        // Right.key <= Right_parent.key
                                        if(Right.key < Right_parent.key) {
                                            Right_parent.left = Right.right;
                                        }
                                        else {
                                            // Right.key = Right_parent.key
                                            if(Right.address <= Right_parent.address) {
                                                Right_parent.left = Right.right;
                                            }
                                            else {
                                                // Right.address > Right_parent.address
                                                Right_parent.right = Right.right;
                                            }
                                        }
                                    }
                                    if(Right.right != null) {
                                        Right.right.parent = Right_parent;
                                    }
                                    else{;}
                                    Right.right = null;
                                    Right.parent = null;
                                    // Successor is deleted
                                    // Overwrite contents into temp
                                    temp.key = n1;
                                    temp.address = n2;
                                    temp.size = n3;
                                    // node deleted, successor restored
                                }
                            }
                        }
                        // element deleted
                        return true;
                    }
                    else {
                        // temp.address != e.address || temp.size != e.size
                        if(temp.address < e.address) {
                            temp = temp.right;
                        }
                        else {
                            // temp.address >= e.address
                            temp = temp.left;
                        }
                    }
                }    
            }
            // temp == null, i.e. element not found
            return false;
        }
    }
        
    public BSTree Find(int key, boolean exact)
    {   
        BSTree temp = this;
        while(temp.parent != null) {
            temp = temp.parent;
        }
        // temp.parent = null, so temp is the sentinel
        temp = temp.right;
        // temp is the main root (convention)
        if(exact) {
            BSTree y = null;
            while(temp != null) {
                if(key > temp.key) {
                    temp = temp.right;
                }
                else {
                    // key <= temp.key
                    if(temp.key == key) {
                        // temp is a possible find, but I need to minimize address
                        y = temp;
                        // still keep looking
                        temp = temp.left;
                    }
                    else {
                        // key < temp.key
                        temp = temp.left;
                    }
                }
            }
            // temp == null && y points to required element
            if(y == null) {
                // element not found
                return null;
            }
            else {
                // y is the desired element. Also y is not the sentinel, as temp started from sentinel's right child and we are only moving downwards
                return y;
            }
        }    
        else {
            // exact is false, find an approximate element
            BSTree y = null;
            while(temp != null) {
                if(temp.key < key) {
                    temp = temp.right;
                }
                else {
                    // temp.key >= key, so temp is a possible find, but I need to minimize key value
                    y = temp;
                    temp = temp.left;
                }
            }
            // temp = null && y is the desired element
            if(y == null) {
                // element not found
                return null;
            }
            else {
                // y is the desired element. Also y is not the sentinel, as temp started from sentinel's right child and we are only moving downwards
                return y;
            }
        }    
    }

    public BSTree getFirst()
    { 
        BSTree temp = this;
        while(temp.parent != null) {
            temp = temp.parent;
        }
        // temp.parent == null, i.e. temp is the root sentinel
        temp = temp.right;
        // temp is the actual root of the tree (convention)
        if(temp == null) {
            // no element in the tree
            return null;
        }
        else {
            // temp != null
            while(temp.left != null) {
                temp = temp.left;
            }
            // temp.left == null, so temp is the smallest element in the tree
            return temp;
        }
    }

    public BSTree getNext()
    { 
        BSTree temp = this;
        if(temp.parent == null) {
            // temp is the sentinel, so return null
            return null;
        }
        else {
            // temp is not the sentinel
            if(temp.right != null) {
                temp = temp.right;
                while(temp.left != null) {
                    temp = temp.left;
                }
                // temp.left = null, so temp is the successor of this
                return temp;
            }
            else {
                // temp.right = null
                BSTree y = temp.parent;
                // y may or may not be the sentinel
                while(y != null && y.right == temp) {
                    temp = y;
                    y = y.parent;
                }
                // y = null || y.right != temp => y.left = temp
                if(y == null) {
                    // successor not found
                    return null;
                }
                else {
                    // y.left = temp, so y is the successor of this
                    if(y.parent == null) {
                        // y is the sentinel, so not really a successor
                        return null;
                    }
                    else {
                        // y is not the sentinel
                        return y;
                    }
                }
            }
        }
    }    

    public boolean sanity()
    { 
        // check if parent pointers form a cycle or not
        BSTree temp1 = this, temp2 = this;
        int met = 0;
        while(temp1 != null && temp2 != null && temp2.parent != null) {
            if(met == 0) {
                // first iteration
                temp1 = temp1.parent;
                temp2 = temp2.parent.parent;
                met = 1;
            }
            else {
                // non-trivial iteration
                if(temp1 == temp2) {
                    // cycle exists, not a tree
                    return false;
                }
                else {
                    temp1 = temp1.parent;
                    temp2 = temp2.parent.parent;
                }
            }
        }
        // temp1 = null || temp2 = null || temp2.parent = null, either way, parents do not form a cycle
        BSTree temp = this;
        while(temp.parent != null) {
            temp = temp.parent;
        }
        // termination of this loop is guaranteed, as parents do not form a cycle, therefore, temp.parent = null
        // temp should be the root sentinel
        if(temp.address != -1 || temp.key != -1 || temp.size != -1) {
            // not the sentinel
            return false;
        }
        else {
            if(temp.left != null) {
                // convention violated
                return false;
            }
            else {
                // temp.left = null && temp.parent = null
                if(temp.right != null) {
                    if(temp.right.parent != temp) {
                        // Tree structure not preserved
                        return false;
                    }
                    else {
                        // sentinel is sane, so, check sanity of right sub-tree
                        temp = temp.right;
                        boolean check1 = temp.isTree();
                        if(check1) {
                            // check for search property
                            return temp.isBST(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
                        }
                        else {
                            // not a tree
                            return false;
                        }
                    }
                }
                else {
                    // sentinel is sane and is the only node in the entire tree, so tree is sane
                    return true;
                }
            }
        }
    }

    // checks if this is a tree
    private boolean isTree() {
        // check sanity of parent
        if(this.parent == null) {;}
        else {
            // this.parent != null
            if(this.parent.right == this) {
                if(this.parent.left == this || this.parent.parent == this) {
                    // insane
                    return false;
                }
                else {;}
            }
            else {
                // this.parent.right != this
                if(this.parent.left != this || this.parent.parent == this) {
                    // insane
                    return false;
                }
                else {;}
            }
        } 
        // check sanity of children        
        if(this.left == null) {
            if(this.right == null) {
                // Node with sane parent, so it is a tree
                return true;
            }
            else {
                // this.right != null
                if(this.right == this.parent) {
                    // same child, same parent
                    return false;
                }
                else {
                    // distinct pointers
                    if(this.right.parent != this || this.right.left == this || this.right.right == this) {
                        // insane
                        return false;
                    }
                    else {
                        // all good at this, let's check its right child
                        return this.right.isTree(); 
                    }
                }    
            }
        }
        else {
            // this.left != null
            if(this.right == null) {
                if(this.left == this.parent) {
                    // same child, same parent
                    return false;
                }
                else {
                    // distinct pointers
                    if(this.left.parent != this || this.left.left == this || this.left.right == this) {
                        // insane
                        return false;
                    }
                    else {
                        // all good at this, let's check its left child
                        return this.left.isTree();
                    }
                }    
            }
            else {
                // both children are not null
                if(this.left == this.parent || this.right == this.parent || this.right == this.left) {
                    // same pointers
                    return false;
                }
                else {
                    // distinct pointers
                    if(this.left.parent != this || this.left.left == this || this.left.right == this) {
                        // insane
                        return false;
                    }
                    else {
                        // this.left.parent = this, and other pointers of left child are distinct
                        if(this.right.parent != this || this.right.left == this || this.right.right == this) {
                            // insane
                            return false;
                        }
                        else {
                            // all good at this
                            boolean check1 = this.left.isTree();
                            if(check1) {
                                return this.right.isTree();
                            }
                            else {
                                return false;
                            }
                        }
                    }
                }    
            }
        }
    }

    // checks for search property in the binary tree "this"
    private boolean isBST(int kmin, int amin, int kmax, int amax) {
        if(this.key < kmax) {
            if(this.key > kmin) {;}
            else {
                // this.key <= kmin
                if(this.key < kmin) {
                    // search property violated
                    return false;
                }
                else {
                    // this.key = kmin
                    if(this.address <= amin) {
                        // search property violated
                        return false;
                    } 
                    else {;}
                }
            }
        }
        else {
            // this.key >= kmax
            if(this.key > kmax) {
                // search property violated
                return false;
            }
            else {
                // this.key = kmax
                if(this.address > amax) {
                    // search property violated
                    return false;
                }
                else {;}
            }
        }
        // this is fine, let's check its children
        if(this.left == null) {
            if(this.right == null) {
                // this has no children, so its a bst
                return true;
            }
            else {
                // this.right != null
                // check its right child
                return this.right.isBST(this.key, this.address, kmax, amax);
            }
        }
        else {
            // this.left != null
            if(this.right == null) {
                return this.left.isBST(kmin, amin, this.key, this.address);
            }
            else {
                // both children are not null
                boolean check1 = this.left.isBST(kmin, amin, this.key, this.address);
                if(check1) {
                    return this.right.isBST(this.key, this.address, kmax, amax);
                }
                else {
                    // left subtree is not a bst
                    return false;
                }
            }
        }
    }
}


 


