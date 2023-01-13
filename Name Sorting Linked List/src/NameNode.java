
import java.util.Scanner;
class NameNode { //node for the list that holds a 
	String name; //name
	int nameCode; //a name code based on that name 
	NameNode next; //and a reference to the next node
}
class NameLinkedList { //defines a list object
	Scanner input = new Scanner(System.in);
	NameNode front = new NameNode(); //the first namenode in the list
	void init() { //initialize the list
		front = null;
	}
	NameNode[] buildIndex(NameNode[] indexArray) { //build an index for each letter
		NameNode current;
		current = front; //start at the front of the list
		indexArray = new NameNode[26]; //array of namenodes to load with the index is the parameter
		char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
				'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'}; //alphabet
		try {
			for (int i = 0; i < 26; i++) { //for each letter in the alphabet
				if (current.name.charAt(0) == letters[i]) {  //if the first letter of the current namenode is the letter at the current index of the alphabet array (starting at a)
					indexArray[i] = current; //load the index array with the first node corresponding to the current letter
				}
				else {
					indexArray[i] = null; //there isn't a name starting with the letter in the list, so it points to null
				}
				while (current.name.charAt(0) == letters[i]) { 
					current = current.next;
					//only want to get a link to the first name that corresponds to the current letter, so skip past
					//any other names starting with the current letter until it hits the next letter, which will be
					//the next letter in the alphabet
				}
			}
		} catch (NullPointerException n) {
		
		}
	 /*	System.out.println("-------------Did it load into buildIndex?----------------");
		for (int i = 0; i < indexArray.length; i++) {
			if (indexArray[i] == null) {
				System.out.println("No names starting with " + getNumChar(i) + ", this index is null.");
			}
			else {
				System.out.println(indexArray[i].name + " - " + indexArray[i].nameCode + 
						" is the first node with a name starting with " + getNumChar(i) + ".");
			}
		} */
		return indexArray; //return an array of namenode indexes
	} 
	void getSection() { //get a section of the list by 1 or more letters
		NameNode current; 
		current = front; //start at the front
		if (front == null) { //if you deleted all the namenodes with the delete function specified here later
			System.out.println("The list is empty, there's no section to retrieve.");
			return; //don't continue with the rest of the method
		}
		NameNode[] indices = new NameNode[26]; //new array to hold indexes
		System.out.print("Enter a letter or letters to retrieve a section by: "); //prompt
		String first = input.nextLine().toLowerCase(); //take in user input string, convert to lowercase in case a uppercase letter is inputted
		try {
			indices = buildIndex(indices); //build an index with the array from earlier
			current = indices[getCharNum(first.charAt(0))]; 
			//with the first character of the input, get the number corresponding to it with getCharNum(),
			//that number corresponds to an index of the index array indices (0 = a, 1 = b, etc.). Start the
			//search at the namenode at that index.
			if (current == null) { //if the index points to null (there isn't a name starting with the first letter of the input in the list)
				System.out.println("There are no names starting with " + first + "."); //message
				return; //don't continue with rest of the method
			}
			if (first.length() == 1) { //if user input is only one character
				System.out.println("Here's all the nodes with names starting with " + first.charAt(0) + "...."); //message
				while (current.name.charAt(0) == first.charAt(0)) { //while the first character of the node's name in the list is the input (or just == first)
					System.out.println(current.name + " - " + current.nameCode); //print that node's name and its name code
					current = current.next; //keep going until the loop hits a node with a name that doesn't start with the input
					//the list is in alphabetical order so all the names starting with the input should be printed 
				}
			}
			else { //if the user input is longer than one character
				boolean sectionFound = false; //false until it is located
				while (current != null) { //go through the whole list
					if (current.name.length() >= first.length()) { 
						//check that the current name is as long or longer than the search parameter, filter out any
						//names that aren't. This bypasses string index out of bounds exception if the search 
						//encounters a name that is smaller than first.length() - 1
						if (current.name.substring(0, first.length()).equals((first))) { //Starting at the first character, if the current name contains the user input
							sectionFound = true; //the section of the list the user searched for was found
							break; //found a starting point of the section, don't need to continue the loop
						}
					}
					current = current.next; //going through the whole list
				}
				if (sectionFound == false) { //the section of the list the user searched for wasn't found
					System.out.println("There is no section of the list with names beginning with " + first + "."); //message
					return; //don't continue with the rest of the method
				}
				System.out.println("Here's all the nodes with names starting with " + first + "...."); //message
				while (current.name.substring(0, first.length()).equals(first)) {  
					System.out.println(current.name + " - " + current.nameCode);
					current = current.next; //found the start of the section earlier (marked by current), now print the name and name code of
											//each node in the section until there aren't any more
				}
			}
		} catch (NullPointerException n) {
			//I sleep
		}
	}
	void getSectionLength() { //get the length of a section of the length by 1 or more letters
		NameNode current; 
		current = front; //start at front of the list
		if (front == null) { //if all nodes were deleted
			System.out.println("The list is empty, there's no section length to retrieve."); //message
			return; //don't continue
		}
		NameNode[] indices = new NameNode[26]; //array to load indexes into
		System.out.print("Enter a letter or letters to get a section of the list by: "); //message
		String first = input.nextLine().toLowerCase(); //get the user string input, convert to lowercase
		try {
			indices = buildIndex(indices); //indices holds indexes of the list
			current = indices[getCharNum(first.charAt(0))]; 
			//with the first character of the input, get the number corresponding to it with getCharNum(),
			//that number corresponds to an index of the index array indices (0 = a, 1 = b, etc.). Start the
			//search at the namenode at that index.
			if (current == null) { //if the index points to null (there isn't a name starting with the first letter of the input in the list)
				System.out.println("There's no section of the list with names starting with " + first + "."); //message
				return; //don't continue
			}
			int length = 0; //initialize length var
			if (first.length() == 1) { //if user input is only one character
				while (current.name.charAt(0) == first.charAt(0)) { //while the first character of the node's name in the list is the input 
					length++; //increment the length count by 1, for each node in the section
					current = current.next; //keep going until the loop hits a node with a name that doesn't start with the input
				}
				System.out.println("The section of the list with names starting with " + first + " is " + length + " nodes long."); //message
			}
			else { //if the user input is longer than one character
				boolean sectionFound = false; //false until it is located
				while (current != null) { //go through the whole list
					if (current.name.length() >= first.length()) {
						//check that the current name is as long or longer than the search parameter, filter out any
						//names that aren't. This bypasses string index out of bounds exception if the search 
						//encounters a name that is smaller than first.length() - 1
						if (current.name.substring(0, first.length()).equals((first))) { //Starting at the first character, if the current name contains the user input
							sectionFound = true; //the section of the list the user searched for was found
							break; //found a starting point of the section, don't need to continue the loop
						}
					//	current = current.next;
				    }
					current = current.next; //going through the whole list
				}
				if (sectionFound == false) { //the section of the list the user searched for wasn't found
					System.out.println("There is no section of the list with names beginning with " + first + "."); //message
					return; //don't continue
				}
				while (current.name.substring(0, first.length()).equals(first)) { 
					length++;
					current = current.next;
					//found the start of the section earlier (marked by current), now increment the count of length
					//by one for each node in the section until there aren't any more
				}
				System.out.println("The section of the list with names starting with " + first + " is " + length + " nodes long.");
				//print the final length
			}
		} catch (NullPointerException n) {
			//I still sleep
		}
	}
	int getCharNum(char ch) { //input a character, get a number associated with it (a = 0, b = 1, etc...)
		int number;
		switch(ch) {
			case 'a':
				number = 0;
				break;
			case 'b':
				number = 1;
				break;
			case 'c':
				number = 2;
				break;
			case 'd':
				number = 3;
				break;
			case 'e':
				number = 4;
				break;
			case 'f':
				number = 5;
				break;
			case 'g':
				number = 6;
				break;
			case 'h':
				number = 7;
				break;
			case 'i':
				number = 8;
				break;
			case 'j':
				number = 9;
				break;
			case 'k':
				number = 10;
				break;
			case 'l':
				number = 11;
				break;
			case 'm':
				number = 12;
				break;
			case 'n':
				number = 13;
				break;
			case 'o':
				number = 14;
				break;
			case 'p':
				number = 15;
				break;
			case 'q':
				number = 16;
				break;
			case 'r':
				number = 17;
				break;
			case 's':
				number = 18;
				break;
			case 't':
				number = 19;
				break;
			case 'u':
				number = 20;
				break;
			case 'v':
				number = 21;
				break;
			case 'w':
				number = 22;
				break;
			case 'x':
				number = 23;
				break;
			case 'y':
				number = 24;
				break;
			default: //z
				number = 25;
				break;
		}
		return number;
	}
	String getNumChar(int num) { //input a number, get a character associated with it (0 = a, 1 = b, etc...)
		String ch;
		switch(num) {
			case 0:
				ch = "a";
				break;
			case 1:
				ch = "b";
				break;
			case 2:
				ch = "c";
				break;
			case 3:
				ch = "d";
				break;
			case 4:
				ch = "e";
				break;
			case 5:
				ch = "f";
				break;
			case 6:
				ch = "g";
				break;
			case 7:
				ch = "h";
				break;
			case 8:
				ch = "i";
				break;
			case 9:
				ch = "j";
				break;
			case 10:
				ch = "k";
				break;
			case 11:
				ch = "l";
				break;
			case 12:
				ch = "m";
				break;
			case 13:
				ch = "n";
				break;
			case 14:
				ch = "o";
				break;
			case 15:
				ch = "p";
				break;
			case 16:
				ch = "q";
				break;
			case 17:
				ch = "r";
				break;
			case 18:
				ch = "s";
				break;
			case 19:
				ch = "t";
				break;
			case 20:
				ch = "u";
				break;
			case 21:
				ch = "v";
				break;
			case 22:
				ch = "w";
				break;
			case 23:
				ch = "x";
				break;
			case 24:
				ch = "y";
				break;
			default: //25
				ch = "z";
				break;
		}
		return ch;
	}
	NameNode makeNameNode(String name) { //create a name node
		NameNode node = new NameNode(); //new node
		node.name = name; //its name is the argument entered
		node.nameCode = (int) ((getCharNum(name.charAt(0)) * Math.pow(26, 2)) + 
				(getCharNum(name.charAt(1)) * Math.pow(26, 1)) + 
				(getCharNum(name.charAt(2)) * Math.pow(26, 0)));
		//generate nameCode attribute based on the numerical value of the name attribute
		node.next = null; //reference to next namenode is null, until it's placed in the list
		return node; //return the node created
	}
	void addFront(NameNode in) { //add a node to the front
		in.next = front;
		front = in;
	}
	void addBack(NameNode in) { //add a node to the back
		NameNode tail;
		tail = findTail();
		tail.next = in;
	}
	NameNode findSpot(NameNode in) { //find a spot to use to add or delete a node in the interior
		NameNode current;
		NameNode previous;
		current = front;
		previous = front;
		while (current.nameCode < in.nameCode) {
			previous = current;
			current = current.next;
		}
		return previous;
	}
	void addInterior(NameNode in) { //add a node in the interior based on the result from findSpot()
		NameNode spot;
		spot = findSpot(in);
		in.next = spot.next;
		spot.next = in;
	}
	void deleteNode() { //delete a node
		NameNode current;
		current = front; //start at the front of the list
		if (front == null) { //if all namenodes were deleted
			System.out.println("The list is empty, there's nothing to delete."); //message
			return; //don't continue
		}
		System.out.print("What is the name you want to delete? "); //prompt
		String out = input.nextLine().toLowerCase(); //take input, convert to lowercase in case it has uppercase letters
		boolean nameFound = false; //nameFound is initially false until user input is found
		while (current != null) { //go through the whole list
			if (current.name.equals(out)) { //if the current node's name equals the user input
				nameFound = true; //the name was found
				break; //don't need to go through the rest of the loop
			}
			current = current.next; //go to the next node
		}
		if (nameFound == false) { //if user input didn't equal any node's name in the list
			System.out.println("The name " + out + " wasn't found in the list."); //message
			return; //don't continue
		}
		System.out.println("Any last words, " + out + "?"); //message
		while (current != null) { //go through the whole list
			if (current.name.equals(out)) { //if the current node's name equals the user input
				if (current == front) { //if that node is at the front of the list
					NameNode node = front;
					front = node.next;
					node.next = null;
					break; //delete from front and stop going through nodes
				}
				else { //if that node is in the interior or back
					NameNode findSpotFor = current; 
					NameNode spot = findSpot(findSpotFor);
					NameNode node = spot.next;
					spot.next = node.next;
					break; //delete from interior/back and stop going through nodes
				}
			} 
			current = current.next; //go through the whole list
		}
		System.out.println(out + " is no longer with us."); //message
		NameNode[] indices = new NameNode[26];
		indices = buildIndex(indices); //rebuild the index in case an index namenode was deleted
	}
	NameNode findTail() { //find the last node of the list
		NameNode current;
		current = front;
		while (current.next != null) {
			current = current.next;
		}
		return current;
	}
	void showList() { //print the contents of every namenode in the list 
		NameNode current;
		current = front;
		if (front == null) { //if list is empty
			System.out.println("The list is empty, there's nothing to show."); //message
			return; //don't continue
		}
		System.out.println("Here's the list...."); //message
		while (current != null) {
			System.out.println(current.name + ", " + current.nameCode); //print for every namenode
			current = current.next;
		}
	}
	void lengthOfList() { //get the length of the list
		int length = 0;
		NameNode current;
		current = front;
		if (front == null) { //if the list is empty
			System.out.println("The list is empty, it has no length."); //message
			return; //don't continue
		}
		while (current != null) {
			length++; //increment by one for every node in the list
			current = current.next;
		}
		System.out.println("The length of the list is " + length + " nodes."); //message
	}
}