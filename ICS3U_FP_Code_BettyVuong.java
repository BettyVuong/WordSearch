package FinalProject;

/**
 * Betty Vuong 
 * ICS3U 
 * 06/21/21 
 * Program is a word search with multiple themes, "game instructions" within the menu will go in depth about the game.
 */
import java.util.*;

public class ICS3U_FP_Code_BettyVuong {

	public static void main(String[] args) {
		int correctTotal = 0, correctPerWord = 0, round = 0, play = 0, gamePlay = 0;
		String word;
		String[] correctWordsFixed = new String[8];
		String[] correctWordsUpdates = new String[8];
		System.out.println("------------------Word Search-----------------");

		// game's initiated until user wants to exit
		do {
			// menu is prompted from the method call, unless the user enters '2' within the
			// menu() method, loop is exited
			do {
				play = menu();
			} while (play == 2);

			// game plays if user enters 1 within the menu(); method
			while (play == 1) {
				gamePlay++;
				int theme = theme();
				System.out.println("\n----------------------------------------------\n\nWelcome to Word Search!\n");
				themeIndicator(theme);
				char[][] search = mainArray(theme); // the method does the 2D array's elements. They are assigned with random letters, then some 
													// elements are reassigned with new letters
				correctWordsFixed = correctWordsFixed(search, theme);
				correctWordsUpdates = correctWordsUpdates(theme, correctWordsFixed);

				// plays the game and prompts/displays the word search until user enters all the
				// words
				do {

					if (correctPerWord == 1 || round == 0) {
						word = mainPrintArray(search);
						correctPerWord = wordChecker(word, search, correctWordsFixed, correctWordsUpdates, theme);
						// if user enters 3 within the game, the game round is exited immediately
						if (correctPerWord == 8) {
							correctTotal = 8;
						} else {
							correctTotal += correctPerWord; // if user enters correct word, it'll be added. variables make sure
															// that the users correct words only count once
						}
					} else { // pauses printing the 2D array and makes user enter words until they enter a correct one
						word = userInteraction();
						correctPerWord = wordChecker(word, search, correctWordsFixed, correctWordsUpdates, theme);
						// if user enters 3 within the game, the game round is exited immediately
						if (correctPerWord == 8) {
							correctTotal = 8;
						} else {
							correctTotal += correctPerWord; // if user enters correct word, it'll be added. variables make sure
															// that the users correct words only count once
						}
					}

					round++;
				} while (correctTotal < 8); // the game will stop once all 8 words are entered
				round = 0;// "resets/null" the variable in case the game is replayed to ensure the do-while loop above works

				// if structure prints 2D array if the game round is completed and prints remaining words if game round is not completed
				if (!word.equals("3")) {
					// prints 2d array using loop one last time after the game ends
					System.out.println("      Word Search      ");
					for (int rowCount = 0; rowCount < search.length; rowCount++) {
						for (int column = 0; column < search[rowCount].length; column++) {
							System.out.print(search[rowCount][column] + " ");
						}
						System.out.println();
					}
				} else {
					// prints the remaining words if game wasn't completed
					System.out.print("\nRemaining words: ");
					for (int index = 0; index < correctWordsUpdates.length; index++) {
						if (!correctWordsUpdates[index].equals(" ")) {
							if (index != 7) {
								System.out.print("\n- " + correctWordsUpdates[index]);
							} else {
								System.out.print("\n- " + correctWordsUpdates[index] + "\n");
							}
						}
					}
				}
				System.out
						.println("\n-----------------------------------------------\n\n               Game round ended"
								+ "\n\n-----------------------------------------------");
				play = 0; // "resets/null" to exit the loop
			}
			correctTotal = 0;

		} while (play != 3);

	}

	// outputs a menu and prompts user with options
	// post: returns an int value from 1-3
	public static int menu() {
		System.out.print("\nMenu:\n1. Play game\n2. Game instructions\n3. Exit\nEnter an option: ");
		Scanner input = new Scanner(System.in);
		int option = input.nextInt();

		// if user does not enter one of the valid options, they are prompted to
		// re-enter an option until they do
		while (option < 1 || option > 3) {
			System.out.print(
					"\nPlease enter a valid option.\nMenu:\n1. Play game\n2. Game instructions\n3. Exit\nEnter an option: ");
			option = input.nextInt();
		}

		// option #2 and #3
		if (option == 2) {
			System.out.println(
					"\n-----------------------------------------------\n\nINSTRUCTIONS\n\nTo start the game, enter \"1\"; to learn more about \nthe"
							+ " game and instructions, enter \" 2\"; to exit, \nenter \"3\". Once the game round starts, you will \nbe prompted with a theme"
							+ " to select, enter the \ntheme you want. After selecting a theme, you will \nbe able to play. Look for words that fit"
							+ " the theme \nwithin the word search and enter in a word you \nfind; you will get a comment if you are incorrect\nand will be "
							+ "able to retry until you are correct. \nIf you are correct, the word search will be updated \nand cross out the word you"
							+ " entered. Once you have \nfound all of the words, the menu will reappear. \n*Note that if at any point you want to "
							+ "exit the \ngame when playing a round, enter \"3\".*\n\nRULES\n\n1. You cannot retype a correct word, \n   it"
							+ " will be considered incorrect.\n\n2. If you type in more than one word, \n   it'll be considered incorrect.\n"
							+ "\n-----------------------------------------------");
		} else if (option == 3) {
			System.out.println(
					"\n-----------------------------------------------\n\n     Thank you for playing Word Search!"
							+ "\n\n-----------------------------------------------\n");
		}

		// returns an option from 1-3 that corresponds to what option user chooses
		return (option);
	}

	// displays themes user can choose and selects
	// post: returns an int value that correlates with a theme
	public static int theme() {
		Scanner input = new Scanner(System.in);
		int themeChoice = 0;
		System.out.print("\n----------------------------------------------\n\nPick a theme:\n"
				+ "1 - The Shadowhunters Novels (book realm)\n2 - Hairstyling And Theory\n"
				+ "3 - Lana Del Rey\nEnter an option: ");
		themeChoice = input.nextInt();

		// if user does not enter one of the valid options, they are prompted to
		// re-enter an option until they do
		while (themeChoice < 1 || themeChoice > 3) {
			System.out.print("Please Enter a valid option: ");
			themeChoice = input.nextInt();
		}

		// returns 'themeChoice' which has a value of 1-3 that corresponds to what theme the user chooses
		return (themeChoice);

	}

	// prints theme to indicate user
	// pre: int 'theme'
	public static void themeIndicator(int theme) {
		if (theme == 1) {
			System.out.print("Theme: The Shadowhunters Novels (book realm)\n\n");
		} else if (theme == 2) {
			System.out.print("Theme: Hairstyling And Theory\n\n");
		} else if (theme == 3) {
			System.out.print("Theme: Lana Del Rey\n\n");
		}
	}

	// assigns char values to elements for the themes
	// post: returns char 2D array
	public static char[][] mainArray(int themeChoice) {
		Random rand = new Random();
		char[][] search = new char[12][12];
		int count = 0, rowCount = 0, column = 0;

		// filling 2d array with random letters
		for (rowCount = 0; rowCount < search.length; rowCount++) {
			for (column = 0; column < search[rowCount].length; column++) {
				search[rowCount][column] = (char) (rand.nextInt(26) + 'a'); // random generator of char between a-z
			}
		}

		String[] words = correctWordsFixed(search, themeChoice);
		if (themeChoice == 1) { // reassigns for shadowhunters theme
			for (count = 0; count < words.length; count++) {
				char[] word1 = words[count].toCharArray();

				if (count == 0) { // the word "rune" will be updated in the 2d array
					// allocates words in the 1d array into the 2d array
					for (int rounds = 0, row = 2; rounds < word1.length; rounds++, row++) {
						search[row][11] = word1[rounds];
					}
				} else if (count == 1) { // the word "stele" will be updated in the 2d array
					for (int rounds = 0, columnNum = 1; rounds < word1.length; rounds++, columnNum++) {
						search[10][columnNum] = word1[rounds];
					}
				} else if (count == 2) { // the word "angel" will be updated in the 2d array
					for (int rounds = 0, columnNum = 2; rounds < word1.length; rounds++, columnNum++) {
						search[4][columnNum] = word1[rounds];
					}
				} else if (count == 3) { // the word "blade" will be updated in the 2d array
					for (int rounds = 0, row = 2; rounds < word1.length; rounds++, row++) {
						search[row][1] = word1[rounds];
					}
				} else if (count == 4) { // the word "institute" will be updated in the 2d array
					for (int rounds = 0, columnNum = 0; rounds < word1.length; rounds++, columnNum++) {
						search[8][columnNum] = word1[rounds];
					}
				} else if (count == 5) { // the word "idris" will be updated in the 2d array
					for (int rounds = 0, row = 2; rounds < word1.length; rounds++, row++) {
						search[row][7] = word1[rounds];
					}
				} else if (count == 6) { // the word "herondale" will be updated in the 2d array
					for (int rounds = 0, columnNum = 1; rounds < word1.length; rounds++, columnNum++) {
						search[1][columnNum] = word1[rounds];
					}
				} else if (count == 7) { // the word "pandemonium" will be updated in the 2d array
					for (int rounds = 0, row = 1; rounds < word1.length; rounds++, row++) {
						search[row][10] = word1[rounds];
					}
				}
			}
		} else if (themeChoice == 2) { // reassigns for hairstyling and theory theme
			for (count = 0; count < words.length; count++) {
				char[] word1 = words[count].toCharArray();

				if (count == 0) { // the word "cortex" will be updated in the 2d array
					// allocates words in the 1d array into the 2d array
					for (int rounds = 0, columnNum = 6; rounds < word1.length; rounds++, columnNum++) {
						search[1][columnNum] = word1[rounds];
					}
				} else if (count == 1) { // the word "braid" will be updated in the 2d array
					for (int rounds = 0, row = 2, columnNum = 4; rounds < word1.length; rounds++, columnNum++) {
						search[row][columnNum] = word1[rounds];
					}
				} else if (count == 2) { // the word "balayage" will be updated in the 2d array
					for (int rounds = 0, row = 2; rounds < word1.length; rounds++, row++) {
						search[row][2] = word1[rounds];
					}
				} else if (count == 3) { // the word "bonds" will be updated in the 2d array
					for (int rounds = 0, columnNum = 6; rounds < word1.length; rounds++, columnNum--) {
						search[11][columnNum] = word1[rounds];
					}
				} else if (count == 4) { // the word "developer" will be updated in the 2d array
					for (int rounds = 0, columnNum = 1; rounds < word1.length; rounds++, columnNum++) {
						search[0][columnNum] = word1[rounds];
					}
				} else if (count == 5) { // the word "straightener" will be updated in the 2d array
					for (int rounds = 0, row = 0; rounds < word1.length; rounds++, row++) {
						search[row][0] = word1[rounds];
					}
				} else if (count == 6) { // the word "blowout" will be updated in the 2d array
					for (int rounds = 0, columnNum = 3; rounds < word1.length; rounds++, columnNum++) {
						search[8][columnNum] = word1[rounds];
					}
				} else if (count == 7) { // the word "babylight" will be updated in the 2d array
					for (int rounds = 0, row = 2; rounds < word1.length; rounds++, row++) {
						search[row][11] = word1[rounds];
					}
				}
			}
		} else if (themeChoice == 3) {
			for (count = 0; count < words.length; count++) {
				char[] word1 = words[count].toCharArray();

				if (count == 0) { // the word "drugs" will be updated in the 2d array
					// allocates words in the 1d array into the 2d array
					for (int rounds = 0, row = 1; rounds < word1.length; rounds++, row++) {
						search[row][10] = word1[rounds];
					}
				} else if (count == 1) { // the word "coke" will be updated in the 2d array
					for (int rounds = 0, columnNum = 0; rounds < word1.length; rounds++, columnNum++) {
						search[1][columnNum] = word1[rounds];
					}
				} else if (count == 2) { // the word "poem" will be updated in the 2d array
					for (int rounds = 0, columnNum = 8; rounds < word1.length; rounds++, columnNum--) {
						search[4][columnNum] = word1[rounds];
					}
				} else if (count == 3) { // the word "honeymoon" will be updated in the 2d array
					for (int rounds = 0, row = 1; rounds < word1.length; rounds++, row++) {
						search[row][4] = word1[rounds];
					}
				} else if (count == 4) { // the word "salvatore" will be updated in the 2d array
					for (int rounds = 0, columnNum = 2; rounds < word1.length; rounds++, columnNum++) {
						search[10][columnNum] = word1[rounds];
					}
				} else if (count == 5) { // the word "lolita" will be updated in the 2d array
					for (int rounds = 0, columnNum = 6; rounds < word1.length; rounds++, columnNum++) {
						search[7][columnNum] = word1[rounds];
					}
				} else if (count == 6) { // the word "whiney" will be updated in the 2d array
					for (int rounds = 0, row = 3; rounds < word1.length; rounds++, row++) {
						search[row][1] = word1[rounds];
					}
				} else if (count == 7) { // the word "picsart" will be updated in the 2d array
					for (int rounds = 0, columnNum = 0; rounds < word1.length; rounds++, columnNum++) {
						search[11][columnNum] = word1[rounds];
					}
				}
			}
		}

		// returns the 2D array that is now used throughout the game round
		return (search);
	}

	// list of correct words within each theme
	// pre: char 2D array and int 'themeChoice'
	// post: returns a String 1D array of correct words according to theme
	public static String[] correctWordsFixed(char[][] search, int themeChoice) {
		String[] words = new String[8];
		String[] shadowhuntersWords = { "rune", "stele", "angel", "blade", "institute", "idris", "herondale",
				"pandemonium" };
		String[] hairstylingWords = { "cortex", "braid", "balayage", "bonds", "developer", "straightener", "blowout",
				"babylight" };
		String[] lana = { "drugs", "coke", "poem", "honeymoon", "salvatore", "lolita", "whiney", "picsart" };

		// assigns array with correct words of a theme array according to theme
		if (themeChoice == 1) {
			words = shadowhuntersWords;
		} else if (themeChoice == 2) {
			words = hairstylingWords;
		} else if (themeChoice == 3) {
			words = lana;
		}
		
		// returns 'words' with the array of "permanent/correct" words 
		return (words);
	}

	// list of correct words that are corrected for checking purposes
	// pre: int 'themeChoice'
	// post: returns a String 1D array of correct words according to theme
	public static String[] correctWordsUpdates(int themeChoice, String[] correctWordsFixed) {
		String[] words = new String[8];
		String[] shadowhuntersWords = { "rune", "stele", "angel", "blade", "institute", "idris", "herondale",
				"pandemonium" };
		String[] hairstylingWords = { "cortex", "braid", "balayage", "bonds", "developer", "straightener", "blowout",
				"babylight" };
		String[] lana = { "drugs", "coke", "poem", "honeymoon", "salvatore", "lolita", "whiney", "picsart" };

		// assigns array with correct words of a theme array according to theme
		if (themeChoice == 1) {
			words = shadowhuntersWords;
		} else if (themeChoice == 2) {
			words = hairstylingWords;
		} else if (themeChoice == 3) {
			words = lana;
		}
		
		// returns 'words' with the array of "permanent/correct" words but is modified depending on what user enters
		return (words);
	}

	// Prints char 2D array along with returning user string input
	// pre: char 2D array
	// post: returns user String input
	public static String mainPrintArray(char[][] search) {
		System.out.println("      Word Search      ");
		// prints 2d array using loop
		for (int rowCount = 0; rowCount < search.length; rowCount++) {
			for (int column = 0; column < search[rowCount].length; column++) {
				System.out.print(search[rowCount][column] + " ");
			}
			System.out.println();
		}

		// user interaction method call
		String userWord = userInteraction();
		
		//returns 'userWord' which is what the user enters if they possibly find a word on the 2D array
		return (userWord);
	}

	// allows user to input word found in the word search
	// post: returns string 'userword' user entered
	public static String userInteraction() {
		int spaceCount = 0;
		Scanner input = new Scanner(System.in);
		System.out.print("Enter word: ");
		String userWord = input.nextLine();

		// checking to see if user enters more than one word
		spaceCount = 0;
		for (int i = 0; i < userWord.length(); i++) {
			char c = userWord.charAt(i);
			if (c == 32) { // if it finds a space
				spaceCount++; // counts number of spaces user enters
			}
		}
		userWord = userWord.trim();

		//returns 'userWord' which is what the user enters if they possibly find a word on the 2D array
		return (userWord);
	}

	// checks if user enters a word within the word search or not and updates the 2D array
	// pre: String userWord, String 1D array correctWordsFixed and correctWordsUpdates
	// post: returns int value depending on what the string value is and updates the 2D array "search"
	public static int wordChecker(String userWord, char[][] search, String[] correctWordsFixed,
			String[] correctWordsUpdates, int theme) {
		
		// if-else structure is for if user enters "3" the game will exit immediately
		if (!userWord.equals("3")) {
			// updates the array and "blocks" out the word entered on the array if word
			// entered is correct
			if (theme == 1) {
				if (userWord.equalsIgnoreCase("rune")) { // updates if user inputed "rune"
					for (int rounds = 0, row = 2; rounds < userWord.length(); rounds++, row++) {
						search[row][11] = ' ';
					}
				} else if (userWord.equalsIgnoreCase("stele")) { // updates if user inputed "stele"
					for (int rounds = 0, columnNum = 1; rounds < userWord.length(); rounds++, columnNum++) {
						search[10][columnNum] = ' ';
					}
				} else if (userWord.equalsIgnoreCase("angel")) { // updates if user inputed "angel"
					for (int rounds = 0, columnNum = 2; rounds < userWord.length(); rounds++, columnNum++) {
						search[4][columnNum] = ' ';
					}
				} else if (userWord.equalsIgnoreCase("blade")) { // updates if user inputed "blade"
					for (int rounds = 0, row = 2; rounds < userWord.length(); rounds++, row++) {
						search[row][1] = ' ';
					}
				} else if (userWord.equalsIgnoreCase("institute")) { // updates if user inputed "institute"
					for (int rounds = 0, columnNum = 0; rounds < userWord.length(); rounds++, columnNum++) {
						search[8][columnNum] = ' ';
					}
				} else if (userWord.equalsIgnoreCase("idris")) { // updates if user inputed "idris"
					for (int rounds = 0, row = 2; rounds < userWord.length(); rounds++, row++) {
						search[row][7] = ' ';
					}
				} else if (userWord.equalsIgnoreCase("herondale")) { // updates if user inputed "herondale"
					for (int rounds = 0, columnNum = 1; rounds < userWord.length(); rounds++, columnNum++) {
						search[1][columnNum] = ' ';
					}
				} else if (userWord.equalsIgnoreCase("pandemonium")) { // updates if user inputed "pandemonium"
					for (int rounds = 0, row = 1; rounds < userWord.length(); rounds++, row++) {
						search[row][10] = ' ';
					}
				}
			} else if (theme == 2) {
				if (userWord.equalsIgnoreCase("cortex")) { // updates if user inputed "cortex"
					// allocates words in the 1d array into the 2d array
					for (int rounds = 0, columnNum = 6; rounds < userWord.length(); rounds++, columnNum++) {
						search[1][columnNum] = ' ';
					}
				} else if (userWord.equalsIgnoreCase("braid")) { // updates if user inputed "braid"
					for (int rounds = 0, row = 2, columnNum = 4; rounds < userWord.length(); rounds++, columnNum++) {
						search[row][columnNum] = ' ';
					}
				} else if (userWord.equalsIgnoreCase("balayage")) { // updates if user inputed "balayage"
					for (int rounds = 0, row = 2; rounds < userWord.length(); rounds++, row++) {
						search[row][2] = ' ';
					}
				} else if (userWord.equalsIgnoreCase("bonds")) { // updates if user inputed "bonds"
					for (int rounds = 0, columnNum = 6; rounds < userWord.length(); rounds++, columnNum--) {
						search[11][columnNum] = ' ';
					}
				} else if (userWord.equalsIgnoreCase("developer")) { // updates if user inputed "developer"
					for (int rounds = 0, columnNum = 1; rounds < userWord.length(); rounds++, columnNum++) {
						search[0][columnNum] = ' ';
					}
				} else if (userWord.equalsIgnoreCase("straightener")) { // updates if user inputed "straightener"
					for (int rounds = 0, row = 0; rounds < userWord.length(); rounds++, row++) {
						search[row][0] = ' ';
					}
				} else if (userWord.equalsIgnoreCase("blowout")) { // updates if user inputed "blowout"
					for (int rounds = 0, columnNum = 3; rounds < userWord.length(); rounds++, columnNum++) {
						search[8][columnNum] = ' ';
					}
				} else if (userWord.equalsIgnoreCase("babylight")) { // updates if user inputed "babylight"
					for (int rounds = 0, row = 2; rounds < userWord.length(); rounds++, row++) {
						search[row][11] = ' ';
					}
				}
			} else if (theme == 3) {
				if (userWord.equalsIgnoreCase("drugs")) { // updates if user inputed "drugs"
					// allocates words in the 1d array into the 2d array
					for (int rounds = 0, row = 1; rounds < userWord.length(); rounds++, row++) {
						search[row][10] = ' ';
					}
				} else if (userWord.equalsIgnoreCase("coke")) { // updates if user inputed "coke"
					for (int rounds = 0, columnNum = 0; rounds < userWord.length(); rounds++, columnNum++) {
						search[1][columnNum] = ' ';
					}
				} else if (userWord.equalsIgnoreCase("poem")) { // updates if user inputed "poem"
					for (int rounds = 0, columnNum = 8; rounds < userWord.length(); rounds++, columnNum--) {
						search[4][columnNum] = ' ';
					}
				} else if (userWord.equalsIgnoreCase("honeymoon")) { // updates if user inputed "honeymoon"
					for (int rounds = 0, row = 1; rounds < userWord.length(); rounds++, row++) {
						search[row][4] = ' ';
					}
				} else if (userWord.equalsIgnoreCase("salvatore")) { // updates if user inputed "salvatore"
					for (int rounds = 0, columnNum = 2; rounds < userWord.length(); rounds++, columnNum++) {
						search[10][columnNum] = ' ';
					}
				} else if (userWord.equalsIgnoreCase("lolita")) { // updates if user inputed "lolita"
					for (int rounds = 0, columnNum = 6; rounds < userWord.length(); rounds++, columnNum++) {
						search[7][columnNum] = ' ';
					}
				} else if (userWord.equalsIgnoreCase("whiney")) { // updates if user inputed "whiney"
					for (int rounds = 0, row = 3; rounds < userWord.length(); rounds++, row++) {
						search[row][1] = ' ';
					}
				} else if (userWord.equalsIgnoreCase("picsart")) { // updates if user inputed "picsart"
					for (int rounds = 0, columnNum = 0; rounds < userWord.length(); rounds++, columnNum++) {
						search[11][columnNum] = ' ';
					}
				}
			}

			// returns a value of 1-3 that will be used as an indicator of sorts in a
			// different method
			return (repeatOrIncorrect(userWord, correctWordsFixed, correctWordsUpdates));
		} else {
			// returns an int value of 8 to exit the loop from the main method from running to exit the game round
			return (8);
		}

	}

	// determines if string user entered is correct, been entered, or incorrect and outputs the appropriate comment
	// pre: String userWord, String 1D array correctWordsFixed and correctWordsUpdates
	// post: returns int value depending on what the string value is
	public static int repeatOrIncorrect(String userWord, String[] correctWordsFixed, String[] correctWordsUpdates) {
		// each numerical value is equivalent to a different comment in the main method along with other conditions of the program
		// legend: 1 = correct word, 2 = repeated correct word entered, and 3 = incorrect word entered
		int indication = 0;

		// determines if string user entered is correct, been entered, or incorrect
		for (int round = 0; round < correctWordsFixed.length; round++) {

			if (userWord.equalsIgnoreCase(correctWordsUpdates[round])) {
				correctWordsUpdates[round] = " ";
				if (userWord.equalsIgnoreCase(correctWordsFixed[round])) {
					indication = 1;
				}
			} else if (!userWord.equalsIgnoreCase(correctWordsUpdates[round])) {
				if (userWord.equalsIgnoreCase(correctWordsFixed[round])) {
					indication = 2;
				}
			} else if (!userWord.equalsIgnoreCase(correctWordsUpdates[round])) {
				if (!userWord.equalsIgnoreCase(correctWordsFixed[round])) {
					indication = 3;
				}
			}
		}

		// prints the appropriate comment and reassigns "indication" for usage in the main method legend for variable 
		// "indication": 1 = correct word, 2 = either incorrect or correct but user has entered previously
		if (indication == 1) {
			System.out.println("Correct!\n");
			indication = 1;
		} else if (indication == 2) {
			System.out.println("Already entered this correct word, try again!\n");
			indication = 0;
		} else {
			System.out.println("Incorrect\n");
			indication = 0;
		}
		
		//returns 'indication' which has the value of either 1 or 2 that indicates to another method if the word user entered is correct or incorrect
		return (indication);
	}
}