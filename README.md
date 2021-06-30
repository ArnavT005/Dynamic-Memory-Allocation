# Dynamic Memory Allocation (using Doubly-Linked Lists, Binary Search Trees and AVL Trees)
## Assignment of course COL106, Fall 2020

---
### Files and Folders: 
This repository consists of a pdf and a folder at the top-most level: 
 - The pdf document describes the problem statement and code expectations in detail. 
 - The folder *Code* contains all the code that was used in completing the assignment. (Written 100% in `Java`) 
 
### How to Run:
1. Change to *Code* folder.
2. Open terminal and execute the code: `javac *.java`. This compiles all java files and generates the bytecode.
3. Run the *driver* program: `java Driver`. Below, I describe the input format of this program: 
	- The first line of input consists of a single integer **t**, the number of test cases.
	- The first line of each test case consists of two integers **size** and **dict_type**, denoting the size of the memory and type of the  dictionary to be used. (1 for DLL, 2 for BST and AVL Tree for other values). *Dictionary* data structure is used by the code to store  the free/allocated memory blocks.
	- The second line of each test case consists of a single integer **m**, the number of commands to be processed by the memory allocator.
	- The next *m* lines consist of a single command each. Each command can be any of the following types:
		- `Allocate <block_size>`, where **<block_size>** is an integer denoting the size of memory block that needs to be allocated.
		- `Free <block_addr>`, where **<block_addr>** is an integer denoting the starting address of the memory block that needs to be deallocated/freed.
		- `Defragment 0`, to defragment the memory by combining contiguous blocks of *free* memory.
4. To clean up after a successful run, execute the command: `del *.class` (Windows) or `rm *.class` (Linux/MacOS). This removes all java class files from the directory.

### Output Format:
This section describes the output of the Memory Allocator to different input commands:
 - Command Type - *Allocate*. The allocator returns the starting address (int) of the allocated block on successful allocation and **-1** on unsuccessful allocation. Memory addresses start from *0*. The value returned is then printed on the terminal.
 - Command Type - *Free*. The allocator returns **0** on successful deallocation and **-1** on an unsuccessful attempt. The value returned is then printed on the terminal.
 - Command Type - *Defragment*. The allocator does not return anything and nothing is printed on the terminal. You can check if defragmentation took place using subsequent *Allocate* and *Free* commands.