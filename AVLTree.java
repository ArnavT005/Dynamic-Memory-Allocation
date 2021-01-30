// Class: Height balanced AVL Tree
// Binary Search Tree

public class AVLTree extends BSTree {
    
    private AVLTree left, right;     // Children. 
    private AVLTree parent;          // Parent pointer. 
    private int height;  // The height of the subtree
        
    public AVLTree() { 
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node !.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
        
    }

    public AVLTree(int address, int size, int key) { 
        super(address, size, key);
        this.height = 0;
    }

    // Implement the following functions for AVL Trees.
    // You need not implement all the functions. 
    // Some of the functions may be directly inherited from the BSTree class and nothing needs to be done for those.
    // Remove the functions, to not override the inherited functions.
    
    public AVLTree Insert(int address, int size, int key) 
    { 
        // Create node
        AVLTree N = new AVLTree(address, size, key);
        AVLTree temp = this;
        while(temp.parent != null) {
            temp = temp.parent;
        }
        // temp.parent == null, so temp is the sentinel
        AVLTree y = temp;
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
                // since, tree is avl, y.left.height is atmost 0
                if(y.left == null) {
                    // adjust y's height
                    y.height = 1;
                }
                else {/*y's height is consistent*/;}
            }
            else {
                // key <= y.key
                if(key < y.key) {
                    y.left = N;
                    // since, tree is avl, y.right.height is atmost 0
                    if(y.right == null) {
                        // adjust y's height
                        y.height = 1;
                    }
                    else {/*y's height is consistent*/;}
                }
                else {
                    // key == y.key
                    if(address > y.address) {
                        y.right = N;
                        // since, tree is avl, y.left.height is atmost 0
                        if(y.left == null) {
                            // adjust y's height
                            y.height = 1;
                        }
                        else {/*y's height is consistent*/;}
                    }
                    else {
                        // address <= y.address
                        y.left = N;
                        // since, tree is avl, y.right.height is atmost 0
                        if(y.right == null) {
                            // adjust y's height
                            y.height = 1;
                        }
                        else {/*y's height is consistent*/;}
                    }
                }    
            }
            N.parent = y;
        }
        else {
            // y = null || y.parent = null
            if(y == null) {
                // invalid avl, no sentinel
                return null;
            }
            else {
                //y is the root sentinel, so insertion is done on the right side (convention)
                y.right = N;
                N.parent = y;
                // return node
                return N;
            }
        }
        // y is not the sentinel, hence, its parent will exist
        AVLTree x = N, z = y.parent;
        // z maybe the root sentinel
        while(z.parent != null) {
            // z is parent of y
            if(z.left == y) {
                if(z.right == null) {
                    // z has both a child and a grandchild, therefore, z is unbalanced.
                    if(y.left == x) {
                        // left-left rotation
                        if(y.right == null) {
                            y.right = z;
                            y.parent = z.parent;
                            z.parent = y;
                            z.left = null;
                            if(y.parent.parent == null) {
                                // y.parent is the root sentinel, therefore
                                y.parent.right = y;
                            }
                            else {
                                // y.parent is not the sentinel
                                if(y.key > y.parent.key) {
                                    y.parent.right = y;
                                }
                                else {
                                    // y.key <= y.parent.key
                                    if(y.key < y.parent.key) {
                                        y.parent.left = y;
                                    }
                                    else {
                                        // y.key = y.parent.key
                                        if(y.address > y.parent.address) {
                                            y.parent.right = y;
                                        }
                                        else {
                                            y.parent.left = y;
                                        }
                                    }
                                }
                            }
                            // adjust heights
                            z.height = x.height; // 0
                        }
                        else {
                            // y.right != null
                            z.left = y.right;
                            y.right.parent = z;
                            y.right = z;
                            y.parent = z.parent;
                            z.parent = y;
                            if(y.parent.parent == null) {
                                // y.parent is the sentinel
                                y.parent.right = y;
                            }
                            else {
                                // y.parent is not the sentinel
                                if(y.key > y.parent.key) {
                                    y.parent.right = y;
                                }
                                else {
                                    // y.key <= y.parent.key
                                    if(y.key < y.parent.key) {
                                        y.parent.left = y;
                                    }
                                    else {
                                        // y.key = y.parent.key
                                        if(y.address > y.parent.address) {
                                            y.parent.right = y;
                                        }
                                        else {
                                            y.parent.left = y;
                                        }
                                    }
                                }
                            }
                            // adjust height
                            z.height = x.height;
                        }
                    }
                    else {
                        // y.right = x
                        // left-right rotation
                        y.right = x.left;
                        if(x.left != null) {
                            x.left.parent = y;
                        }
                        else {;}
                        y.parent = x;
                        x.left = y;
                        x.parent = z.parent;
                        z.left = x.right;
                        if(x.right != null) {
                            x.right.parent = z;
                        }
                        else {;}
                        x.right = z;
                        z.parent = x;
                        if(x.parent.parent == null) {
                            // x.parent is the sentinel
                            x.parent.right = x;
                        }
                        else {
                            // x.parent is not the sentinel
                            if(x.key > x.parent.key) {
                                x.parent.right = x;
                            }
                            else {
                                // x.key <= x.parent.key
                                if(x.key < x.parent.key) {
                                    x.parent.left = x;
                                }
                                else {
                                    // x.key = x.parent.key
                                    if(x.address > x.parent.address) {
                                        x.parent.right = x;
                                    }
                                    else {
                                        x.parent.left = x;
                                    }
                                }
                            }
                        }
                        // adjust height
                        x.height = x.height + 1;
                        y.height = y.height - 1;
                        z.height = y.height;
                    }
                    // rotation done, so break
                    break;
                }
                else {
                    // z.right != null
                    int diff = AVLTree.abs_diff(y.height, z.right.height);
                    if(diff > 1) {
                        // z is unbalanced, so perform rotation
                        if(y.left == x) {
                            // left-left rotation
                            if(y.right == null) {
                                y.right = z;
                                y.parent = z.parent;
                                z.parent = y;
                                z.left = null;
                                if(y.parent.parent == null) {
                                    // y.parent is the root sentinel, therefore
                                    y.parent.right = y;
                                }
                                else {
                                    // y.parent is not the sentinel
                                    if(y.key > y.parent.key) {
                                        y.parent.right = y;
                                    }
                                    else {
                                        // y.key <= y.parent.key
                                        if(y.key < y.parent.key) {
                                            y.parent.left = y;
                                        }
                                        else {
                                            // y.key = y.parent.key
                                            if(y.address > y.parent.address) {
                                                y.parent.right = y;
                                            }
                                            else {
                                                y.parent.left = y;
                                            }
                                        }
                                    }
                                }
                                // adjust heights
                                z.height = x.height;
                            }
                            else {
                                // y.right != null
                                z.left = y.right;
                                y.right.parent = z;
                                y.right = z;
                                y.parent = z.parent;
                                z.parent = y;
                                if(y.parent.parent == null) {
                                    // y.parent is the sentinel
                                    y.parent.right = y;
                                }
                                else {
                                    // y.parent is not the sentinel
                                    if(y.key > y.parent.key) {
                                        y.parent.right = y;
                                    }
                                    else {
                                        // y.key <= y.parent.key
                                        if(y.key < y.parent.key) {
                                            y.parent.left = y;
                                        }
                                        else {
                                            // y.key = y.parent.key
                                            if(y.address > y.parent.address) {
                                                y.parent.right = y;
                                            }
                                            else {
                                                y.parent.left = y;
                                            }
                                        }
                                    }
                                }
                                // adjust height
                                z.height = x.height;
                            }
                        }
                        else {
                            // y.right = x
                            // left-right rotation
                            y.right = x.left;
                            if(x.left != null) {
                                x.left.parent = y;
                            }
                            else {;}
                            y.parent = x;
                            x.left = y;
                            x.parent = z.parent;
                            z.left = x.right;
                            if(x.right != null) {
                                x.right.parent = z;
                            }
                            else {;}
                            x.right = z;
                            z.parent = x;
                            if(x.parent.parent == null) {
                                // x.parent is the sentinel
                                x.parent.right = x;
                            }
                            else {
                                // x.parent is not the sentinel
                                if(x.key > x.parent.key) {
                                    x.parent.right = x;
                                }
                                else {
                                    // x.key <= x.parent.key
                                    if(x.key < x.parent.key) {
                                        x.parent.left = x;
                                    }
                                    else {
                                        // x.key = x.parent.key
                                        if(x.address > x.parent.address) {
                                            x.parent.right = x;
                                        }
                                        else {
                                            x.parent.left = x;
                                        }
                                    }
                                }
                            }
                            // adjust height
                            x.height = x.height + 1;
                            y.height = y.height - 1;
                            z.height = y.height;
                        }
                        // rotation done, so break
                        break;                        
                    }
                    else {
                        // diff <= 1, so tree is balanced at z
                        if(y.height > z.right.height) {
                            z.height = y.height + 1;
                            // move upwards
                            x = y;
                            y = z;
                            z = z.parent;
                        }
                        else {
                            // z's height remains unchanged, so no need to move upwards
                            break;
                        }
                    }    
                }
            }
            else {
                // z.right == y
                if(z.left == null) {
                    // z has both a child and a grandchild, therefore, z is unbalanced.
                    if(y.right == x) {
                        // right-right rotation
                        if(y.left == null) {
                            y.left = z;
                            y.parent = z.parent;
                            z.parent = y;
                            z.right = null;
                            if(y.parent.parent == null) {
                                // y.parent is the root sentinel, therefore
                                y.parent.right = y;
                            }
                            else {
                                // y.parent is not the sentinel
                                if(y.key > y.parent.key) {
                                    y.parent.right = y;
                                }
                                else {
                                    // y.key <= y.parent.key
                                    if(y.key < y.parent.key) {
                                        y.parent.left = y;
                                    }
                                    else {
                                        // y.key = y.parent.key
                                        if(y.address > y.parent.address) {
                                            y.parent.right = y;
                                        }
                                        else {
                                            y.parent.left = y;
                                        }
                                    }
                                }
                            }
                            // adjust heights
                            z.height = x.height; // 0
                        }
                        else {
                            // y.left != null
                            z.right = y.left;
                            y.left.parent = z;
                            y.left = z;
                            y.parent = z.parent;
                            z.parent = y;
                            if(y.parent.parent == null) {
                                // y.parent is the sentinel
                                y.parent.right = y;
                            }
                            else {
                                // y.parent is not the sentinel
                                if(y.key > y.parent.key) {
                                    y.parent.right = y;
                                }
                                else {
                                    // y.key <= y.parent.key
                                    if(y.key < y.parent.key) {
                                        y.parent.left = y;
                                    }
                                    else {
                                        // y.key = y.parent.key
                                        if(y.address > y.parent.address) {
                                            y.parent.right = y;
                                        }
                                        else {
                                            y.parent.left = y;
                                        }
                                    }
                                }
                            }
                            // adjust height
                            z.height = x.height;
                        }
                    }
                    else {
                        // y.left = x
                        // right-left rotation
                        y.left = x.right;
                        if(x.right != null) {
                            x.right.parent = y;
                        }
                        else {;}
                        y.parent = x;
                        x.right = y;
                        x.parent = z.parent;
                        z.right = x.left;
                        if(x.left != null) {
                            x.left.parent = z;
                        }
                        else {;}
                        x.left = z;
                        z.parent = x;
                        if(x.parent.parent == null) {
                            // x.parent is the sentinel
                            x.parent.right = x;
                        }
                        else {
                            // x.parent is not the sentinel
                            if(x.key > x.parent.key) {
                                x.parent.right = x;
                            }
                            else {
                                // x.key <= x.parent.key
                                if(x.key < x.parent.key) {
                                    x.parent.left = x;
                                }
                                else {
                                    // x.key = x.parent.key
                                    if(x.address > x.parent.address) {
                                        x.parent.right = x;
                                    }
                                    else {
                                        x.parent.left = x;
                                    }
                                }
                            }
                        }
                        // adjust height
                        x.height = x.height + 1;
                        y.height = y.height - 1;
                        z.height = y.height;
                    }
                    // rotation done, so break
                    break;
                }
                else {
                    // z.left != null
                    int diff = AVLTree.abs_diff(z.left.height, y.height);
                    if(diff > 1) {
                        // z is unbalanced, so perform rotation
                        if(y.right == x) {
                            // right-right rotation
                            if(y.left == null) {
                                y.left = z;
                                y.parent = z.parent;
                                z.parent = y;
                                z.right = null;
                                if(y.parent.parent == null) {
                                    // y.parent is the root sentinel, therefore
                                    y.parent.right = y;
                                }
                                else {
                                    // y.parent is not the sentinel
                                    if(y.key > y.parent.key) {
                                        y.parent.right = y;
                                    }
                                    else {
                                        // y.key <= y.parent.key
                                        if(y.key < y.parent.key) {
                                            y.parent.left = y;
                                        }
                                        else {
                                            // y.key = y.parent.key
                                            if(y.address > y.parent.address) {
                                                y.parent.right = y;
                                            }
                                            else {
                                                y.parent.left = y;
                                            }
                                        }
                                    }
                                }
                                // adjust heights
                                z.height = x.height;
                            }
                            else {
                                // y.left != null
                                z.right = y.left;
                                y.left.parent = z;
                                y.left = z;
                                y.parent = z.parent;
                                z.parent = y;
                                if(y.parent.parent == null) {
                                    // y.parent is the sentinel
                                    y.parent.right = y;
                                }
                                else {
                                    // y.parent is not the sentinel
                                    if(y.key > y.parent.key) {
                                        y.parent.right = y;
                                    }
                                    else {
                                        // y.key <= y.parent.key
                                        if(y.key < y.parent.key) {
                                            y.parent.left = y;
                                        }
                                        else {
                                            // y.key = y.parent.key
                                            if(y.address > y.parent.address) {
                                                y.parent.right = y;
                                            }
                                            else {
                                                y.parent.left = y;
                                            }
                                        }
                                    }
                                }
                                // adjust height
                                z.height = x.height;
                            }
                        }
                        else {
                            // y.left = x
                            // right-left rotation
                            y.left = x.right;
                            if(x.right != null) {
                                x.right.parent = y;
                            }
                            else {;}
                            y.parent = x;
                            x.right = y;
                            x.parent = z.parent;
                            z.right = x.left;
                            if(x.left != null) {
                                x.left.parent = z;
                            }
                            else {;}
                            x.left = z;
                            z.parent = x;
                            if(x.parent.parent == null) {
                                // x.parent is the sentinel
                                x.parent.right = x;
                            }
                            else {
                                // x.parent is not the sentinel
                                if(x.key > x.parent.key) {
                                    x.parent.right = x;
                                }
                                else {
                                    // x.key <= x.parent.key
                                    if(x.key < x.parent.key) {
                                        x.parent.left = x;
                                    }
                                    else {
                                        // x.key = x.parent.key
                                        if(x.address > x.parent.address) {
                                            x.parent.right = x;
                                        }
                                        else {
                                            x.parent.left = x;
                                        }
                                    }
                                }
                            }
                            // adjust height
                            x.height = x.height + 1;
                            y.height = y.height - 1;
                            z.height = y.height;
                        }
                        // rotation done, so break
                        break;
                    }
                    else {
                        // diff <= 1, so tree is balanced at z
                        if(y.height > z.left.height) {
                            z.height = y.height + 1;
                            // move upwards
                            x = y;
                            y = z;
                            z = z.parent;
                        }
                        else {
                            // z's height remains unchanged, so no need to move upwards
                            break;
                        }
                    }
                }
            }
        }
        // z.parent = null, so z is the sentinel || rotation performed. Hence, necessary rotations have been done.
        // return node
        return N;
    }

    public boolean Delete(Dictionary e)
    {
        if(e == null) {
            // Invalid node deletion
            return false;
        }
        else {
            // e != null
            AVLTree temp = this;
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
                            AVLTree y = temp.parent;
                            // y may or may not be the sentinel
                            if(y.parent == null) {
                                // y is the sentinel, so temp is its right child whatever its key value may be
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
                            y.del_balance();
                        }    
                        else {
                            // temp has atleast one child
                            // Case 2: temp has a single child (and that child is a leaf (AVL))
                            if(temp.right == null) {
                                // temp.left != null
                                // copy contents of the leaf
                                temp.key = temp.left.key;
                                temp.address = temp.left.address;
                                temp.size = temp.left.size;
                                temp.height = temp.left.height; // 0
                                temp.left.parent = null;
                                temp.left = null;
                                AVLTree y = temp.parent;
                                y.del_balance();
                            }    
                            else {
                                // temp.right != null
                                if(temp.left == null) {
                                    // copy contents of the leaf
                                    temp.key = temp.right.key;
                                    temp.address = temp.right.address;
                                    temp.size = temp.right.size;
                                    temp.height = temp.right.height; // 0
                                    temp.right.parent = null;
                                    temp.right = null;
                                    AVLTree y = temp.parent;
                                    y.del_balance();
                                }    
                                else {
                                    // Case 3: temp has two children
                                    AVLTree Right = temp.getNext();
                                    // Right is the successor of temp, so Right.left is null as Right is the minimum element of the right subtree of temp
                                    // Temporarily store the contents of Right
                                    int n1 = Right.key, n2 = Right.address, n3 = Right.size;
                                    // Delete Right (atmost one child { which is the right child })
                                    AVLTree y = Right.parent;
                                    if(Right.right != null) {
                                        // copy contents
                                        Right.key = Right.right.key;
                                        Right.address = Right.right.address;
                                        Right.size = Right.right.size;
                                        Right.height = Right.right.height; // 0
                                        Right.right.parent = null;
                                        Right.right = null;
                                    }
                                    else {
                                        // Right.right = null
                                        if(Right.key > y.key) {
                                            y.right = null;
                                        }
                                        else {
                                            // Right.key <= y.key
                                            if(Right.key < y.key) {
                                                y.left = null;
                                            }
                                            else {
                                                // Right.key = y.key
                                                if(Right.address > y.address) {
                                                    y.right = null;
                                                }
                                                else {
                                                    y.left = null;
                                                }
                                            }
                                        }
                                        Right.parent = null;
                                    }
                                    // Successor is deleted
                                    // Overwrite contents into temp
                                    temp.key = n1;
                                    temp.address = n2;
                                    temp.size = n3;
                                    // node deleted, successor restored
                                    y.del_balance();
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
        
    public AVLTree Find(int k, boolean exact)
    { 
        AVLTree temp = this;
        while(temp.parent != null) {
            temp = temp.parent;
        }
        // temp.parent = null, so temp is the sentinel
        temp = temp.right;
        // temp is the main root (convention)
        if(exact) {
            AVLTree y = null;
            while(temp != null) {
                if(k > temp.key) {
                    temp = temp.right;
                }
                else {
                    // k <= temp.key
                    if(temp.key == k) {
                        // temp is a possible find, but I need to minimize address
                        y = temp;
                        // still keep looking
                        temp = temp.left;
                    }
                    else {
                        // k < temp.key
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
            AVLTree y = null;
            while(temp != null) {
                if(temp.key < k) {
                    temp = temp.right;
                }
                else {
                    // temp.key >= k, so temp is a possible find, but I need to minimize key value
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

    public AVLTree getFirst()
    { 
        AVLTree temp = this;
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

    public AVLTree getNext()
    {
        AVLTree temp = this;
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
                AVLTree y = temp.parent;
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
        AVLTree temp1 = this, temp2 = this;
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
        AVLTree temp = this;
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
                            boolean check2 = temp.isBST(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
                            if(check2) {
                                return temp.isBalanced();
                            }
                            else {
                                // not a bst
                                return false;
                            }
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

    // Balances tree upon deletion
    private void del_balance() {
        AVLTree temp = this;
        while(temp.parent != null) {
            if(temp.left == null && temp.right == null) {
                // temp is balanced, adjust height
                temp.height = 0;
                temp = temp.parent; // move upwards
            }
            else {
                // temp has atleast one child
                if(temp.left == null) {
                    // temp.right != null
                    if(temp.right.height == 0) {
                        // temp is balanced, adjust height
                        if(temp.height != 1) {
                            temp.height = 1;
                            temp = temp.parent; // move upwards
                        }
                        else {
                            // temp's height is unchanged, so no need to move upwards
                            break;
                        }
                    }
                    else {
                        // temp is unbalanced, perform necessary rotation
                        AVLTree z = temp, y = temp.right, x;
                        if(y.right != null) {
                            if(y.left != null) {
                                if(y.right.height >= y.left.height) {
                                    x = y.right;
                                    z.right = y.left;
                                    y.left.parent = z;
                                    y.parent = z.parent;
                                    y.left = z;
                                    z.parent = y;
                                    if(y.parent.parent == null) {
                                        // y.parent is the sentinel
                                        y.parent.right = y;
                                    }
                                    else {
                                        if(y.key > y.parent.key) {
                                            y.parent.right = y;
                                        }
                                        else {
                                            // y.key <= y.parent.key
                                            if(y.key < y.parent.key) {
                                                y.parent.left = y;
                                            }
                                            else {
                                                // y.key = y.parent.key
                                                if(y.address > y.parent.address) {
                                                    y.parent.right = y;
                                                }
                                                else {
                                                    y.parent.left = y;
                                                }
                                            }
                                        }
                                    }
                                    // adjust heights
                                    int n1 = z.height;
                                    z.height = z.right.height + 1;
                                    int max = AVLTree.maximum(z.height, x.height);
                                    y.height = max + 1;
                                    if(y.height < n1) {
                                        // move upwards
                                        temp = y.parent;
                                    }
                                    else {
                                        // height of subtree is unchanged, so break
                                        break;
                                    }
                                }
                                else {
                                    // y.right.height < y.left.height
                                    x = y.left;
                                    y.left = x.right;
                                    if(x.right != null) {
                                        x.right.parent = y;
                                    }
                                    else {;}
                                    x.right = y;
                                    y.parent = x;
                                    x.parent = z.parent;
                                    z.right = x.left;
                                    if(x.left != null) {
                                        x.left.parent = z;
                                    }
                                    else {;}
                                    x.left = z;
                                    z.parent = x;
                                    if(x.parent.parent == null) {
                                        // x.parent is the sentinel
                                        x.parent.right = x;
                                    }
                                    else {
                                        if(x.key > x.parent.key) {
                                            x.parent.right = x;
                                        }
                                        else {
                                            // x.key <= x.parent.key
                                            if(x.key < x.parent.key) {
                                                x.parent.left = x;
                                            }
                                            else {
                                                // x.key = x.parent.key
                                                if(x.address > x.parent.address) {
                                                    x.parent.right = x;
                                                }
                                                else {
                                                    x.parent.left = x;
                                                }
                                            }
                                        }
                                    }
                                    // adjust heights
                                    y.height = y.height - 1;
                                    if(z.right != null) {
                                        z.height = z.right.height + 1;
                                    }
                                    else {
                                        z.height = 0;
                                    }
                                    x.height = y.height + 1;
                                    temp = x.parent;
                                }
                            }
                            else {
                                // y.left = null
                                x = y.right;
                                z.right = null;
                                y.parent = z.parent;
                                y.left = z;
                                z.parent = y;
                                if(y.parent.parent == null) {
                                    // y.parent is the sentinel
                                    y.parent.right = y;
                                }
                                else {
                                    if(y.key > y.parent.key) {
                                        y.parent.right = y;
                                    }
                                    else {
                                        // y.key <= y.parent.key
                                        if(y.key < y.parent.key) {
                                            y.parent.left = y;
                                        }
                                        else {
                                            // y.key = y.parent.key
                                            if(y.address > y.parent.address) {
                                                y.parent.right = y;
                                            }
                                            else {
                                                y.parent.left = y;
                                            }
                                        }
                                    }
                                }
                                // adjust heights
                                z.height = 0;
                                int max = AVLTree.maximum(z.height, x.height);
                                y.height = max + 1;
                                temp = y.parent;
                            }
                        }
                        else {
                            // y.right = null
                            x = y.left;
                            y.left = x.right;
                            if(x.right != null) {
                                x.right.parent = y;
                            }
                            else {;}
                            x.right = y;
                            y.parent = x;
                            x.parent = z.parent;
                            z.right = x.left;
                            if(x.left != null) {
                                x.left.parent = z;
                            }
                            else {;}
                            x.left = z;
                            z.parent = x;
                            if(x.parent.parent == null) {
                                // x.parent is the sentinel
                                x.parent.right = x;
                            }
                            else {
                                if(x.key > x.parent.key) {
                                    x.parent.right = x;
                                }
                                else {
                                    // x.key <= x.parent.key
                                    if(x.key < x.parent.key) {
                                        x.parent.left = x;
                                    }
                                    else {
                                        // x.key = x.parent.key
                                        if(x.address > x.parent.address) {
                                            x.parent.right = x;
                                        }
                                        else {
                                            x.parent.left = x;
                                        }
                                    }
                                }
                            }
                            // adjust heights
                            if(y.left != null) {
                                y.height = y.left.height + 1;
                            }
                            else {
                                y.height = 0;
                            }
                            if(z.right != null) {
                                z.height = z.right.height + 1;
                            }
                            else {
                                z.height = 0;
                            }
                            int max = AVLTree.maximum(z.height, y.height);
                            x.height = max + 1;
                            temp = x.parent;
                        }
                    }
                }
                else {
                    // temp.left != null
                    if(temp.right == null) {
                        if(temp.left.height == 0) {
                            // temp is balanced, adjust height
                            if(temp.height != 1) {
                                temp.height = 1;
                                temp = temp.parent; // move upwards
                            }
                            else {
                                // temp's height is unchanged, so break
                                break;
                            }    
                        }
                        else {
                            // temp is unbalanced, perform necessary rotation
                            AVLTree z = temp, y = temp.left, x;
                            if(y.left != null) {
                                if(y.right != null) {
                                    if(y.left.height >= y.right.height) {
                                        x = y.left;
                                        z.left = y.right;
                                        y.right.parent = z;
                                        y.parent = z.parent;
                                        y.right = z;
                                        z.parent = y;
                                        if(y.parent.parent == null) {
                                            // y.parent is the sentinel
                                            y.parent.right = y;
                                        }
                                        else {
                                            if(y.key > y.parent.key) {
                                                y.parent.right = y;
                                            }
                                            else {
                                                // y.key <= y.parent.key
                                                if(y.key < y.parent.key) {
                                                    y.parent.left = y;
                                                }
                                                else {
                                                    // y.key = y.parent.key
                                                    if(y.address > y.parent.address) {
                                                        y.parent.right = y;
                                                    }
                                                    else {
                                                        y.parent.left = y;
                                                    }
                                                }
                                            }
                                        }
                                        // adjust heights
                                        int n1 = z.height;
                                        z.height = z.left.height + 1;
                                        int max = AVLTree.maximum(z.height, x.height);
                                        y.height = max + 1;
                                        if(y.height < n1) {
                                            temp = y.parent; // move upwards
                                        }
                                        else {
                                            // subtree's height is unchanged
                                            break;
                                        }
                                    }
                                    else {
                                        // y.left.height < y.right.height
                                        x = y.right;
                                        y.right = x.left;
                                        if(x.left != null) {
                                            x.left.parent = y;
                                        }
                                        else {;}
                                        x.left = y;
                                        y.parent = x;
                                        x.parent = z.parent;
                                        z.left = x.right;
                                        if(x.right != null) {
                                            x.right.parent = z;
                                        }
                                        else {;}
                                        x.right = z;
                                        z.parent = x;
                                        if(x.parent.parent == null) {
                                            // x.parent is the sentinel
                                            x.parent.right = x;
                                        }
                                        else {
                                            if(x.key > x.parent.key) {
                                                x.parent.right = x;
                                            }
                                            else {
                                                // x.key <= x.parent.key
                                                if(x.key < x.parent.key) {
                                                    x.parent.left = x;
                                                }
                                                else {
                                                    // x.key = x.parent.key
                                                    if(x.address > x.parent.address) {
                                                        x.parent.right = x;
                                                    }
                                                    else {
                                                        x.parent.left = x;
                                                    }
                                                }
                                            }
                                        }
                                        // adjust heights
                                        y.height = y.height - 1;
                                        if(z.left != null) {
                                            z.height = z.left.height + 1;
                                        }
                                        else {
                                            z.height = 0;
                                        }
                                        x.height = y.height + 1;
                                        temp = x.parent;
                                    }
                                }
                                else {
                                    // y.right = null
                                    x = y.left;
                                    z.left = null;
                                    y.parent = z.parent;
                                    y.right = z;
                                    z.parent = y;
                                    if(y.parent.parent == null) {
                                        // y.parent is the sentinel
                                        y.parent.right = y;
                                    }
                                    else {
                                        if(y.key > y.parent.key) {
                                            y.parent.right = y;
                                        }
                                        else {
                                            // y.key <= y.parent.key
                                            if(y.key < y.parent.key) {
                                                y.parent.left = y;
                                            }
                                            else {
                                                // y.key = y.parent.key
                                                if(y.address > y.parent.address) {
                                                    y.parent.right = y;
                                                }
                                                else {
                                                    y.parent.left = y;
                                                }
                                            }
                                        }
                                    }
                                    // adjust heights
                                    z.height = 0;
                                    int max = AVLTree.maximum(z.height, x.height);
                                    y.height = max + 1;
                                    temp = y.parent;
                                }
                            }
                            else {
                                // y.left = null
                                x = y.right;
                                y.right = x.left;
                                if(x.left != null) {
                                    x.left.parent = y;
                                }
                                else {;}
                                x.left = y;
                                y.parent = x;
                                x.parent = z.parent;
                                z.left = x.right;
                                if(x.right != null) {
                                    x.right.parent = z;
                                }
                                else {;}
                                x.right = z;
                                z.parent = x;
                                if(x.parent.parent == null) {
                                    // x.parent is the sentinel
                                    x.parent.right = x;
                                }
                                else {
                                    if(x.key > x.parent.key) {
                                        x.parent.right = x;
                                    }
                                    else {
                                        // x.key <= x.parent.key
                                        if(x.key < x.parent.key) {
                                            x.parent.left = x;
                                        }
                                        else {
                                            // x.key = x.parent.key
                                            if(x.address > x.parent.address) {
                                                x.parent.right = x;
                                            }
                                            else {
                                                x.parent.left = x;
                                            }
                                        }
                                    }
                                }
                                // adjust heights
                                if(y.right != null) {
                                    y.height = y.right.height + 1;
                                }   
                                else {
                                    y.height = 0;
                                }   
                                if(z.left != null) {
                                    z.height = z.left.height + 1;
                                }
                                else {
                                    z.height = 0;
                                }
                                int max = AVLTree.maximum(z.height, y.height);
                                x.height = max + 1;
                                temp = x.parent;
                            }
                        }
                    }
                    else {
                        // temp has 2 children
                        int diff = AVLTree.abs_diff(temp.left.height, temp.right.height);
                        if(diff <= 1) {
                            // temp is balanced, adjust height
                            int n1 = temp.height;
                            int max = AVLTree.maximum(temp.left.height, temp.right.height);
                            temp.height = max + 1;
                            if(temp.height < n1) {
                                temp = temp.parent; // move upwards
                            }
                            else {
                                // temp's height is unchanged, so break
                                break;
                            }    
                        }
                        else {
                            // diff > 1, temp is unbalanced, perform necessary rotation
                            AVLTree z = temp, y, x;
                            if(z.left.height < z.right.height) {
                                y = z.right;
                                if(y.right != null) {
                                    if(y.left != null) {
                                        if(y.right.height >= y.left.height) {
                                            x = y.right;
                                            z.right = y.left;
                                            y.left.parent = z;
                                            y.parent = z.parent;
                                            y.left = z;
                                            z.parent = y;
                                            if(y.parent.parent == null) {
                                                // y.parent is the sentinel
                                                y.parent.right = y;
                                            }
                                            else {
                                                if(y.key > y.parent.key) {
                                                    y.parent.right = y;
                                                }
                                                else {
                                                    // y.key <= y.parent.key
                                                    if(y.key < y.parent.key) {
                                                        y.parent.left = y;
                                                    }
                                                    else {
                                                        // y.key = y.parent.key
                                                        if(y.address > y.parent.address) {
                                                            y.parent.right = y;
                                                        }
                                                        else {
                                                            y.parent.left = y;
                                                        }
                                                    }
                                                }
                                            }
                                            // adjust heights
                                            int n1 = z.height;
                                            z.height = z.right.height + 1;
                                            int max = AVLTree.maximum(z.height, x.height);
                                            y.height = max + 1;
                                            if(y.height < n1) {
                                                temp = y.parent; //move upwards
                                            }
                                            else {
                                                // subtree's height is unchanged, so break
                                                break;
                                            }    
                                        }
                                        else {
                                            // y.right.height < y.left.height
                                            x = y.left;
                                            y.left = x.right;
                                            if(x.right != null) {
                                                x.right.parent = y;
                                            }
                                            else {;}
                                            x.right = y;
                                            y.parent = x;
                                            x.parent = z.parent;
                                            z.right = x.left;
                                            if(x.left != null) {
                                                x.left.parent = z;
                                            }
                                            else {;}
                                            x.left = z;
                                            z.parent = x;
                                            if(x.parent.parent == null) {
                                                // x.parent is the sentinel
                                                x.parent.right = x;
                                            }
                                            else {
                                                if(x.key > x.parent.key) {
                                                    x.parent.right = x;
                                                }
                                                else {
                                                    // x.key <= x.parent.key
                                                    if(x.key < x.parent.key) {
                                                        x.parent.left = x;
                                                    }
                                                    else {
                                                        // x.key = x.parent.key
                                                        if(x.address > x.parent.address) {
                                                            x.parent.right = x;
                                                        }
                                                        else {
                                                            x.parent.left = x;
                                                        }
                                                    }
                                                }
                                            }   
                                            // adjust heights
                                            y.height = y.height - 1;
                                            if(z.right != null) {
                                                int max = AVLTree.maximum(z.left.height, z.right.height);
                                                z.height = max + 1;
                                            }
                                            else {
                                                z.height = z.left.height + 1;
                                            }
                                            x.height = AVLTree.maximum(y.height, z.height) + 1;
                                            temp = x.parent;
                                        }
                                    }
                                    else {
                                        // y.left = null
                                        x = y.right;
                                        z.right = null;
                                        y.parent = z.parent;
                                        y.left = z;
                                        z.parent = y;
                                        if(y.parent.parent == null) {
                                            // y.parent is the sentinel
                                            y.parent.right = y;
                                        }
                                        else {
                                            if(y.key > y.parent.key) {
                                                y.parent.right = y;
                                            }
                                            else {
                                                // y.key <= y.parent.key
                                                if(y.key < y.parent.key) {
                                                    y.parent.left = y;
                                                }
                                                else {
                                                    // y.key = y.parent.key
                                                    if(y.address > y.parent.address) {
                                                        y.parent.right = y;
                                                    }
                                                    else {
                                                        y.parent.left = y;
                                                    }
                                                }
                                            }
                                        }
                                        // adjust heights
                                        z.height = z.left.height + 1;
                                        int max = AVLTree.maximum(z.height, x.height);
                                        y.height = max + 1;
                                        temp = y.parent;
                                    }
                                }
                                else {
                                    // y.right = null
                                    x = y.left;
                                    y.left = x.right;
                                    if(x.right != null) {
                                        x.right.parent = y;
                                    }
                                    else {;}
                                    x.right = y;
                                    y.parent = x;
                                    x.parent = z.parent;
                                    z.right = x.left;
                                    if(x.left != null) {
                                        x.left.parent = z;
                                    }
                                    else {;}
                                    x.left = z;
                                    z.parent = x;
                                    if(x.parent.parent == null) {
                                        // x.parent is the sentinel
                                        x.parent.right = x;
                                    }
                                    else {
                                        if(x.key > x.parent.key) {
                                            x.parent.right = x;
                                        }
                                        else {
                                            // x.key <= x.parent.key
                                            if(x.key < x.parent.key) {
                                                x.parent.left = x;
                                        }
                                            else {
                                                // x.key = x.parent.key
                                                if(x.address > x.parent.address) {
                                                    x.parent.right = x;
                                                }
                                                else {
                                                    x.parent.left = x;
                                                }
                                            }
                                        }
                                    }
                                    // adjust heights
                                    if(y.left != null) {
                                        y.height = y.left.height + 1;
                                    }
                                    else {
                                        y.height = 0;
                                    }
                                    if(z.right != null) {
                                        z.height = AVLTree.maximum(z.right.height, z.left.height) + 1;
                                    }
                                    else {
                                        z.height = z.left.height + 1;
                                    }
                                    int max = AVLTree.maximum(z.height, y.height);
                                    x.height = max + 1;
                                    temp = x.parent;
                                }
                            }
                            else {
                                // z.left.height > z.right.height
                                y = temp.left;
                                if(y.left != null) {
                                    if(y.right != null) {
                                        if(y.left.height >= y.right.height) {
                                            x = y.left;
                                            z.left = y.right;
                                            y.right.parent = z;
                                            y.parent = z.parent;
                                            y.right = z;
                                            z.parent = y;
                                            if(y.parent.parent == null) {
                                                // y.parent is the sentinel
                                                y.parent.right = y;
                                            }
                                            else {
                                                if(y.key > y.parent.key) {
                                                    y.parent.right = y;
                                                }
                                                else {
                                                    // y.key <= y.parent.key
                                                    if(y.key < y.parent.key) {
                                                        y.parent.left = y;
                                                    }
                                                    else {
                                                        // y.key = y.parent.key
                                                        if(y.address > y.parent.address) {
                                                            y.parent.right = y;
                                                        }
                                                        else {
                                                            y.parent.left = y;
                                                        }
                                                    }
                                                }
                                            }
                                            // adjust heights
                                            int n1 = z.height;
                                            z.height = z.left.height + 1;
                                            int max = AVLTree.maximum(z.height, x.height);
                                            y.height = max + 1;
                                            if(y.height < n1) {
                                                temp = y.parent; // move upwards
                                            }
                                            else {
                                                // subtree's height is unchanged, so break
                                                break;
                                            }
                                        }
                                        else {
                                            // y.left.height < y.right.height
                                            x = y.right;
                                            y.right = x.left;
                                            if(x.left != null) {
                                                x.left.parent = y;
                                            }
                                            else {;}
                                            x.left = y;
                                            y.parent = x;
                                            x.parent = z.parent;
                                            z.left = x.right;
                                            if(x.right != null) {
                                                x.right.parent = z;
                                            }
                                            else {;}
                                            x.right = z;
                                            z.parent = x;
                                            if(x.parent.parent == null) {
                                                // x.parent is the sentinel
                                                x.parent.right = x;
                                            }   
                                            else {
                                                if(x.key > x.parent.key) {
                                                    x.parent.right = x;
                                                }
                                                else {
                                                    // x.key <= x.parent.key
                                                    if(x.key < x.parent.key) {
                                                        x.parent.left = x;
                                                    }
                                                    else {
                                                        // x.key = x.parent.key
                                                        if(x.address > x.parent.address) {
                                                            x.parent.right = x;
                                                        }
                                                        else {
                                                            x.parent.left = x;
                                                        }
                                                    }
                                                }
                                            }
                                            // adjust heights
                                            y.height = y.height - 1;
                                            if(z.left != null) {
                                                z.height = AVLTree.maximum(z.left.height, z.right.height) + 1;
                                            }
                                            else {
                                                z.height = z.right.height + 1;
                                            }
                                            x.height = y.height + 1;
                                            temp = x.parent;
                                        }
                                    }
                                    else {
                                        // y.right = null
                                        x = y.left;
                                        z.left = null;
                                        y.parent = z.parent;
                                        y.right = z;
                                        z.parent = y;
                                        if(y.parent.parent == null) {
                                            // y.parent is the sentinel
                                            y.parent.right = y;
                                        }
                                        else {
                                            if(y.key > y.parent.key) {
                                                y.parent.right = y;
                                            }
                                            else {
                                                // y.key <= y.parent.key
                                                if(y.key < y.parent.key) {
                                                    y.parent.left = y;
                                                }
                                                else {
                                                    // y.key = y.parent.key
                                                    if(y.address > y.parent.address) {
                                                        y.parent.right = y;
                                                    }
                                                    else {
                                                        y.parent.left = y;
                                                    }
                                                }
                                            }
                                        }
                                        // adjust heights
                                        z.height = z.right.height + 1;
                                        int max = AVLTree.maximum(z.height, x.height);
                                        y.height = max + 1;
                                        temp = y.parent;
                                    }
                                }
                                else {
                                    // y.left = null
                                    x = y.right;
                                    y.right = x.left;
                                    if(x.left != null) {
                                        x.left.parent = y;
                                    }
                                    else {;}
                                    x.left = y;
                                    y.parent = x;
                                    x.parent = z.parent;
                                    z.left = x.right;
                                    if(x.right != null) {
                                        x.right.parent = z;
                                    }
                                    else {;}
                                    x.right = z;
                                    z.parent = x;
                                    if(x.parent.parent == null) {
                                        // x.parent is the sentinel
                                        x.parent.right = x;
                                    }
                                    else {
                                        if(x.key > x.parent.key) {
                                            x.parent.right = x;
                                        }
                                        else {
                                            // x.key <= x.parent.key
                                            if(x.key < x.parent.key) {
                                                x.parent.left = x;
                                            }
                                            else {
                                                // x.key = x.parent.key
                                                if(x.address > x.parent.address) {
                                                    x.parent.right = x;
                                                }
                                                else {
                                                    x.parent.left = x;
                                                }
                                            }
                                        }
                                    }
                                    // adjust heights
                                    if(y.right != null) {
                                        y.height = y.right.height + 1;
                                    }   
                                    else {
                                        y.height = 0;
                                    }          
                                    if(z.left != null) {
                                        z.height = AVLTree.maximum(z.left.height, z.right.height) + 1;
                                    }
                                    else {
                                        z.height = z.right.height + 1;
                                    }
                                    int max = AVLTree.maximum(z.height, y.height);
                                    x.height = max + 1;
                                    temp = x.parent;
                                } 
                            }
                        }
                    }
                }
            }
        }
        // temp.parent = null or subtree balanced, balancing done
        return;
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

    // checks if this is balanced or not
    private boolean isBalanced() {
        // Case 1: this has no child
        if(this.left == null && this.right == null) {
            if(this.height != 0) {
                // insane
                return false;
            }
            else {
                // height is consistent as I assume height of a leaf to be 0
                return true;
            }
        }
        else {
            // Case 2: this has exactly one child
            if(this.left == null) {
                // this.right != null
                if(this.right.height > 0 || this.right.height < 0) {
                    // balance property violated at this
                    return false;
                }
                else {
                    // balance property is satisfied, check consistency of height
                    if(this.height != this.right.height + 1) {
                        // height inconsistent
                        return false;
                    }
                    else {
                        // all good at this, check this.right
                        return this.right.isBalanced();
                    }
                }
            }
            else {
                // this.left != null
                if(this.right == null) {
                    if(this.left.height > 0 || this.left.height < 0) {
                        // balance property violated at this
                        return false;
                    }
                    else {
                        // balance property is satisfied, check consistency of height
                        if(this.height != this.left.height + 1) {
                            // height inconsistent
                            return false;
                        }
                        else {
                            // all good at this, check this.left
                            return this.left.isBalanced();
                        }
                    }
                }
                else {
                    // Case 3: this has two children
                    // this.left != null && this.right != null
                    if(this.left.height < 0 || this.right.height < 0) {
                        // heights are invalid
                        return false;
                    }
                    else {
                        // valid heights
                        int diff = AVLTree.abs_diff(this.left.height, this.right.height);
                        if(diff > 1) {
                            // tree is unbalanced at this
                            return false;
                        }
                        else {
                            // diff <= 1
                            int max = AVLTree.maximum(this.left.height, this.right.height);
                            if(this.height != max + 1) {
                                // height inconsistent
                                return false;
                            }
                            else {
                                // height consistent at this, check its children
                                boolean check1 = this.left.isBalanced();
                                if(check1) {
                                    return this.right.isBalanced();
                                }
                                else {
                                    // left subtree is unbalanced
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // absolute difference between two integers
    private static int abs_diff(int n1, int n2) {
        if(n1 >= n2) {
            return n1 - n2;
        }
        else {
            return n2 - n1;
        }
    }

    // maximum of two numbers
    private static int maximum(int n1, int n2) {
        if(n1 >= n2) {
            return n1;
        }
        else {
            return n2;
        }
    }
}


