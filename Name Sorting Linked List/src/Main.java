import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		try {
			NameLinkedList list = new NameLinkedList(); //new linked list object
			list.init(); //initialize the list
			Scanner input = new Scanner(System.in);
			System.out.println("Welcome to my Name Sorter Java application!"); 
			System.out.print("Enter the name of the text file you would like to read names from, without the extension: "); //prompt
			String f = input.nextLine(); //name of the file (without .txt) is in var f
			int numOfLines = Files.readAllLines(Paths.get(f + ".txt")).size(); //var numOfLines is equal to the number of lines in the file inputted
			//The file to read names from is the user input plus .txt at the end
			System.out.println("There are " + numOfLines + " lines in the file " + f + ".txt."); 
			//number to check against to make sure an equal amount of nodes are created when lengthOfList() is called (before any nodes are deleted)
			for (int i = 0; i < numOfLines; i++) { //for every line in the file
				if (i == 0) { //if we're at the front of the list
					list.front = list.makeNameNode(Files.readAllLines(Paths.get(f + ".txt")).get(i)); //create a node at the front with the name at the first line
				}
				else { //if we're at the interior or back
					NameNode node = list.makeNameNode(Files.readAllLines(Paths.get(f + ".txt")).get(i)); //make a node with the name in the current line
					if (node.nameCode < list.front.nameCode) { //if the node's name is numerialphabetically (proabably not a word) less than the front node of the list
						list.addFront(node); //it becomes the new front
					}
					else if (node.nameCode > list.findTail().nameCode) { //if the node's name is numerialphabetically more than the last node of the list
						list.addBack(node); //it becomes the new back
					}
					else { //if it's not the front or the back
						list.addInterior(node); //put it in the middle
					}
				}
			}
			System.out.println("The list has been built."); //message
			String options = "Here's the options:\n1 - Display the contents of the list, 2 - Get the length of the list, \n3 - Delete a name from the list, 4 - Display the contents of a section of the list, \n5 - Get the length of a section of the list, 6 - Quit the program";
			//Console operation options
			System.out.println(options); //print the options
		while (1 == 1) { //dummy dum loop so the break label statements work. Iterate on the same list forever until you want to quit
		start : { //come back to the prompt, take more input
			System.out.print("Enter a number for the task you want to complete (Enter 7 to display the options again): "); //prompt
			int choice = input.nextInt(); //a certain number input does a certain operation
			switch(choice) {
				case 1: //if 1 is entered
					list.showList(); //display the list 
					break start; //back to the prompt for more input
				case 2: //if 2 is entered
					list.lengthOfList(); //get the length of the list
					break start;
				case 3: //if 3 is entered
					list.deleteNode(); //delete a single name from the list
					break start;
				case 4: //if 4 is entered
					list.getSection(); //print out a section of the list
					break start;
				case 5: //if 5 is entered
					list.getSectionLength(); //get the length of a section of the list
					break start;
				case 6: //if 6 is entered
					System.out.println("I hope you had a great experience using this application!"); //thanks for playing
					System.exit(0); //terminate the program
				case 7: //if 7 is entered
					System.out.println(options); //print the console options
					break start;
			}
		}
		}
		} catch (IOException poof) {
			System.out.println("The file given doesn't exist or is inaccessible " + poof);
			//catch if a file is entered that doesn't exist
		}
	}
}
