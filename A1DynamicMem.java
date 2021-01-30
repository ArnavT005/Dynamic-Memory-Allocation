// Class: A1DynamicMem
// Implements DynamicMem
// Does not implement defragment (which is for A2).

public class A1DynamicMem extends DynamicMem {
      
    public A1DynamicMem() {
        super();
    }

    public A1DynamicMem(int size) {
        super(size);
    }

    public A1DynamicMem(int size, int dict_type) {
        super(size, dict_type);
    }

    public void Defragment() {
        return ;
    }

    // In A1, you need to implement the Allocate and Free functions for the class A1DynamicMem
    // Test your memory allocator thoroughly using Doubly Linked lists only (A1List.java).

    public int Allocate(int blockSize) {
        if(blockSize <= 0) {
            // Invalid block size
            return -1;
        }
        else {
            // blockSize > 0
            Dictionary temp = this.freeBlk.Find(blockSize, false);
            if(temp == null) {
                // memory not available
                return -1;
            }
            else {
                // memory is available, temp != null
                int t1 = temp.address, t2 = temp.size;
                if(temp.size > blockSize) {
                    // splitting is possible
                    this.allocBlk.Insert(t1, blockSize, t1);
                    this.freeBlk.Delete(temp);
                    this.freeBlk.Insert(t1 + blockSize, t2 - blockSize, t2 - blockSize);
                }
                else {
                    // splitting not possible, temp.size = blockSize
                    this.allocBlk.Insert(t1, blockSize, t1);
                    this.freeBlk.Delete(temp);
                }
                // return starting address of allocated memory block
                return t1;
            }
        }
    } 
    
    public int Free(int startAddr) {
        if(startAddr < 0) {
            // invalid starting address
            return -1;
        }
        else {
            // startAddr >= 0
            Dictionary temp = this.allocBlk.Find(startAddr, true);
            if(temp == null) {
                // memory block not found
                return -1;
            }
            else {
                // memory block to be freed is present
                this.freeBlk.Insert(temp.address, temp.size, temp.size);
                this.allocBlk.Delete(temp);
                // Successful deletion
                return 0;
            }
        }
    }
}