// Implements Dictionary using Doubly Linked List (DLL)
// Implement the following functions using the specifications provided in the class List

public class A1List extends List {

    private A1List  next; // Next Node
    private A1List prev;  // Previous Node 

    public A1List(int address, int size, int key) { 
        super(address, size, key);
    }
    
    public A1List(){
        super(-1,-1,-1);
        // This acts as a head Sentinel

        A1List tailSentinel = new A1List(-1,-1,-1); // Intiate the tail sentinel
        
        this.next = tailSentinel;
        tailSentinel.prev = this;
    }

    public A1List Insert(int address, int size, int key)
    {
        if(this.next != null) {
            // Current node is not the tail sentinel
            // Create new node
            A1List N = new A1List(address, size, key);
            // Insert after current node
            N.next = this.next;
            N.prev = this;
            N.next.prev = N;
            this.next = N;
            return N;
        }
        else {
            // current node is the tail sentinel, so no insertion
            return null;
        }    
    }

    public boolean Delete(Dictionary d) 
    {
        if(d == null) {
            // Invalid list element
            return false;
        }
        else {
            // d != null
            A1List temp = this;
            // Search forwards
            while(temp != null) {
                if(temp.key != d.key) {
                    temp = temp.next;
                    continue;
                }
                else {
                    if(temp.address == d.address && temp.size == d.size) {
                        if(temp.next == null || temp.prev == null) {
                            // temp is sentinel, so invalid deletion
                            return false;
                        }
                        else {
                            // Perform deletion
                            temp.next.prev = temp.prev;
                            temp.prev.next = temp.next;
                            temp.next = null;
                            temp.prev = null;
                            return true;
                        }    
                    }
                    else {
                        temp = temp.next;
                        continue;
                    }
                }    
            }
            // temp = null
            temp = this;
            // Search backwards
            while(temp != null) {
                if(temp.key != d.key) {
                    temp = temp.prev;
                    continue;
                }
                else {
                    if(temp.address == d.address && temp.size == d.size) {
                        if(temp.next == null || temp.prev == null) {
                            // temp is sentinel, so invalid deletion
                            return false;
                        }
                        else {
                            // Perform deletion
                            temp.next.prev = temp.prev;
                            temp.prev.next = temp.next;
                            temp.next = null;
                            temp.prev = null;
                            return true;
                        }    
                    }
                    else {
                        temp = temp.prev;
                        continue;
                    }
                }    
            }
            // temp = null, so element not found in either direction
            return false;
        }
    }

    public A1List Find(int k, boolean exact)
    { 
        A1List temp = this.getFirst();
        // temp points to the first element of the dictionary dll (maybe null)
        if(exact) {
            // Find exact key
            while(temp != null && temp.key != k) {
                temp = temp.next;
            }    
            // temp == null || temp.key == k
            if(temp == null) {
                // Element not found
                return null;
            }
            else {
                // temp.key == k, i.e. element found
                if(temp.prev == null || temp.next == null) {
                    // temp is a sentinel, and therefore not an element of the dll
                    return null;
                }
                else {
                    return temp;
                }
            }
        }
        else {
            // exact is false
            // Find approximate element
            while(temp != null && temp.key < k) {
                temp = temp.next;
            }
            // temp == null || temp.key >= k
            if(temp == null) {
                // Element not found
                return null;
            }
            else {
                // temp.key >= k, i.e. element found
                if(temp.prev == null || temp.next == null) {
                    // temp is a sentinel, and therefore, not an element of the dll
                    return null;
                }
                else {
                    return temp;
                }
            }
        }
    }

    public A1List getFirst()
    {
        // this may point to a sentinel node
        A1List temp = this;
        while(temp.prev != null) {
            temp = temp.prev;
        }
        // temp.prev == null, i.e. temp is the head sentinel node
        temp = temp.next;
        if(temp != null && temp.next != null) {
            //temp is the first element of the dictionary
            return temp;
        }
        else {
            // temp is either null or is the tail sentinel node, i.e. the dictionary is empty
            return null;
        }
    }
    
    public A1List getNext() 
    {
        // this may point to a sentinel node
        if(this.next == null) {
            // this is the tail sentinel node
            return null;
        }
        else {
            // this is not the tail sentinel
            A1List temp = this;
            temp = temp.next;
            if(temp != null && temp.next != null) {
                // temp is an internal node
                return temp;
            }
            else {
                // temp is either null or is the tail sentinel node
                return null;
            }
        }
    }

    public boolean sanity()
    {
        // this may point to a sentinel node
        // Checking for loops in the list
        A1List temp1 = this, temp2 = this;
        int met = 0;
        boolean check1 = true;
        //Checking in forward direction
        while(temp1 != null && temp2 != null && temp2.next != null) {
            if(met == 0) {
                // first iteration
                temp1 = temp1.next;
                temp2 = temp2.next.next;
                met = 1;
            }
            else {
                // some non trivial iteration
                if(temp1 == temp2) {
                    // cycle is present
                    check1 = false;
                    break;
                }
                else {
                    // continue;
                    temp1 = temp1.next;
                    temp2 = temp2.next.next;
                }
            }
        }
        // temp1 == null || temp2 == null || temp2.next == null || check1 == false
        if(!check1) {
            // cycle is present, i.e. insane
            return false;
        }
        else {
            // Checking in backward direction
            temp1 = this; 
            temp2 = this;
            met = 0;
            check1 = true;
            while(temp1 != null && temp2 != null && temp2.prev != null) {
                if(met == 0) {
                    // first iteration
                    temp1 = temp1.prev;
                    temp2 = temp2.prev.prev;
                    met = 1;
                }
                else {
                    // some non trivial iteration
                    if(temp1 == temp2) {
                        // cycle is present
                        check1 = false;
                        break;
                    }
                    else {
                        // continue;
                        temp1 = temp1.prev;
                        temp2 = temp2.prev.prev;
                    }
                }
            }
            // temp1 == null || temp2 == null || temp2.prev == null || check1 == false
            if(!check1) {
                // cycle is present, i.e. insane
                return false;
            }
            else {
                // No cycles/loops present in the DLL
                A1List temp = this;
                while(temp.prev != null) {
                    temp = temp.prev;
                }
                // the loop will terminate as there are no loops
                // temp.prev == null, so it should be head sentinel for sanity
                if(temp.key != -1 || temp.address != -1 || temp.size != -1) {
                    // temp is not the sentinel, i.e. insane
                    return false;
                }
                else {
                    if(temp.next == null) {
                        // temp may be the head, but there is no next element in the list, not even tail, i.e. insane
                        return false;
                    }
                    else {
                        // temp.next != null
                        if(temp.next.prev != temp) {
                            // DLL structure is not preserved, i.e. insane
                            return false;
                        }
                        else {
                            // head is sane, so go to next element
                            temp = temp.next;
                            while(temp.next != null) {
                                // temp.next != null => temp is not the tail
                                // Check for sanity of each node
                                if(temp.prev == null) {
                                    // node is insane as it is not the head
                                    return false;
                                }
                                else {
                                    // temp.prev != null
                                    if(temp.next.prev != temp || temp.prev.next != temp) {
                                        // Structure of DLL is not preserved, i.e. insane
                                        return false;
                                    }
                                    else {
                                        // temp is sane, so move on to next node
                                        temp = temp.next;
                                    }
                                }
                            }
                            // temp.next == null, so it should be the tail
                            // Check for sanity of tail
                            if(temp.key != -1 || temp.address != -1 || temp.size != -1) {
                                // temp is not the sentinel, i.e. insane
                                return false;
                            }
                            else {
                                if(temp.prev == null || temp.prev.next != temp) {
                                    // DLL structure is not preserved, i.e. insane
                                    return false;
                                }
                                else {
                                    // All nodes are sane and no loops/cycles
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
    }   
}


