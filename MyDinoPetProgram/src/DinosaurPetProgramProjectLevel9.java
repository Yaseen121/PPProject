 // AUTHOR: Yaseen Sultan
 // Student ID: 160389076
 // Date: 09/10/2016
 // This program is intended to make as many interactive dinosuar pets as the user wants.
 // The program will then generate random values for thirst, hunger and irritability that 
 // all add up to anger. The user will be given an option to sort pets by anger and then
 // choose a pet to take care of. The user will also be given the option to end early which 
 // will save a text file that can be reloaded next time.
 // To win, all dinosaurs need to enter a state of serenity. 
 // If any pet gets too angry then the user will lose.
 // If user loses they will be given the option to go back up to 5 rounds.

import java.util.Scanner;
import java.io.*;
public class DinosaurPetProgramProjectLevel9
{
	// Sets up constants to be used 
	public static final int maxNumberOfSaves = 5;
	public static final int maxValueOfStateOfMindFields = 10;
	public static final int minValueOfStateOfMindFields = 0;
	public static final int upperLimitForSereneMood = 10;
	public static final int lowerLimitForGrouchyMood = 11;
	public static final int upperLimitForGrouchyMood = 20;
	public static final int lowerLimitForDangerousMood = 21;
	public static final int upperLimitForDangerousMood = 25;
	public static final int lowerLimitForPutDownMood = 26;

	// Main method that calls the method askToReadFile to check if there is a save file and 
	// to ask if they'd like to load it.
	// It then creates a scanner and tells user what the program is for.
	// Then it calls a method to ask how many pets they want and uses that number to create
	// an array of the right size.
	// Then the array is initialised and the method calls whatActionLoop which is the main 
	// loop for the program.
	public static void main (String[] param) throws IOException 
	{
		askToReadFile();
		Scanner scanner = new Scanner(System.in);
		whatIsThisProgramFor ();
		int numberOfPets = howManyPets(scanner);
		PetDetails[] petMenagerie = createArray(numberOfPets);
		setInitialDetailsOfPets(petMenagerie, scanner);
		whatActionLoop(petMenagerie, scanner);
		System.exit(0);
	}
	
	// Method to tell user what the program is for
	public static void whatIsThisProgramFor () 
	{
		System.out.println("\nThis program is intended to make as many interactive dinosuar pets as the user wants.");
		System.out.println("The program will then generate random values for thirst, hunger and irritability that all add up to anger.");
		System.out.println("The user will be given an option to sort pets by anger and then choose a pet to take care of.");
		System.out.println("The user will also be given the option to end early which will save a file that can be reloaded next time.");
		System.out.println("To win, all dinosaurs need to enter a state of serenity. If any pet gets too angry then the user will lose.");
		System.out.println("If user loses they will be given the option to go back up to 5 rounds.");
	}
	
	// This method asks the user how many pets they would like to create and returns it 
	// into a variable in main
	public static int howManyPets(Scanner scanner)
	{
		System.out.println("\nHow many pets would you like have?");
		int number = Integer.parseInt(scanner.nextLine());
		return number;
	}
	
	// This uses the variable in main to create an array of PetDetails of length depending 
	// on the variable, it also initialises the array
	public static PetDetails[] createArray (int size)
	{
		PetDetails[] p = new PetDetails[size];
		for (int i = 0; i <= (size - 1); i++)
		{
			p[i] = new PetDetails();
		}
		return p;
	}
	
	// This method is a loop that asks for the name and species of each pet and passes it
	// into the add pet method, it then calls a method 
	// To print a happy birthday message
	public static void setInitialDetailsOfPets (PetDetails[] pet, Scanner scanner)
	{
		for (int i = 0; i <= (pet.length - 1); i ++)
		{
			System.out.println("\nWhat would you like to name your dinosaur pet " + (i+1) +"?");
			String name = scanner.nextLine();
			System.out.println("\nWhat is the species of your dinosaur pet " + (i+1) +"?");
			String species = scanner.nextLine();
			addPet(pet[i], name, species);
			printHappyBirthday(pet[i]);
		}
	}
	
	// This method calls all the setter methods for each field of the pet
	public static void addPet (PetDetails pet, String name, String species)
	{
		pet = setNameField(pet, name);
		pet = setSpeciesField(pet, species);
		pet = setThirstField(pet, randomNumber());
		pet = setHungerField(pet, randomNumber());
		pet = setIrritabilityField(pet, randomNumber());
		pet = setAngerMoodField(pet);
	}
	
	// This method asks and sets the name of the pet into a record
	public static PetDetails setNameField (PetDetails pet, String name)
	{
		pet.nameField = name;
		return pet;
	}
	
	// This method gets the name of the pet from the record
	public static String getNameField (PetDetails pet)
	{
		return pet.nameField;
	}
	
	// This method asks and sets the species of the pet into a record
	public static PetDetails setSpeciesField (PetDetails pet, String species)
	{
		pet.speciesField = species;
		return pet;
	}

	// This method gets the species of the pet from the record
	public static String getSpeciesField (PetDetails pet)
	{
		return pet.speciesField;
	}

	// Method that prints out a happy birthday message for your pet giving its 
	// name and species 
	public static void printHappyBirthday (PetDetails pet) 
	{
		System.out.println("\nHappy 0th Birthday " + getNameField(pet) + " the " + getSpeciesField(pet) + ".");
	}
	
	// Creates random number between 0 - 10
	public static int randomNumber()
	{
		return (int)(Math.random()*11); 
	}
		
	// This randomises the number until it is lower than what was passed in unless 0 
	// was passed in
	public static int lowerNumber (int currentLevel)
	{
		int newLevel = 0;
		if (!(currentLevel == minValueOfStateOfMindFields))
		{
			do 
			{
				newLevel = randomNumber(); 
			} while (newLevel >= currentLevel);
			currentLevel = newLevel;
			return currentLevel;
		}
		else 
		{
			return currentLevel;
		}
	}
		
	// This randomises the number until it is higher than what was passed in unless 10 
	// was passed in
	public static int raiseNumber (int currentLevel)
	{
		int newLevel = 0;
		if (!(currentLevel == maxValueOfStateOfMindFields))
		{
			do 
			{
				newLevel = randomNumber();
			} while (newLevel <= currentLevel);
			currentLevel = newLevel;
			return currentLevel;
		}
		else 
		{
			return currentLevel;
		}
	}
	
	// This method sets the thirst level of the pet
	public static PetDetails setThirstField (PetDetails pet, int thirstLevel)
	{
		pet.thirstField = thirstLevel;
		return pet;
	}
	
	// This method gets the value stored in the record for thirst level
	public static int getThirstField (PetDetails pet)
	{
		return pet.thirstField;
	}

	// This method sets the hunger level of the pet
	public static PetDetails setHungerField (PetDetails pet, int hungerLevel)
	{
		pet.hungerField = hungerLevel;
		return pet;
	}
	
	// This method gets the value stored in the record for hunger level
	public static int getHungerField (PetDetails pet)
	{
		return pet.hungerField;
	}
	
	// This method sets the irritability level of the pet
	public static PetDetails setIrritabilityField (PetDetails pet, int irritabilityLevel)
	{
		pet.irritabilityField = irritabilityLevel;
		return pet;
	}

	// This method gets the value stored in the record for irritability level
	public static int getIrritabilityField (PetDetails pet)
	{
		return pet.irritabilityField;
	}
	
	// Prints the name of the pet and its thirst level 
	public static void printThirstLevel (PetDetails pet)
	{
		System.out.println("\n" + getNameField(pet) + "'s thirst level is " + getThirstField(pet) + "/10.");
	}
	
	// Prints the name of the pet and its hunger level 
	public static void printHungerLevel (PetDetails pet)
	{
		System.out.println(getNameField(pet) + "'s hunger level is " + getHungerField(pet) + "/10.");
	}
	
	// Prints the name of the pet and its irritability level 
	public static void printIrritabilityLevel (PetDetails pet)
	{
		System.out.println(getNameField(pet) + "'s irritability level is " + getIrritabilityField(pet) + "/10.");
	}
	
	// Gets total sum of anger fields
	public static int getTotalAngerScore(PetDetails pet)
	{
		return (getThirstField(pet) + getHungerField(pet) + getIrritabilityField(pet));
	}
	
	// This method sets the anger mood of the pet using the thirst, hunger 
	// and irritability levels
	public static PetDetails setAngerMoodField (PetDetails pet)
	{
		int angerLevel = getThirstField(pet) + getHungerField(pet) + getIrritabilityField(pet);
		if (angerLevel <= upperLimitForSereneMood)
		{
			pet.angerMoodField = "serene";
		}
		
		else if (angerLevel >= lowerLimitForGrouchyMood && angerLevel <= upperLimitForGrouchyMood)
		{
			pet.angerMoodField = "grouchy";
		}
		else if (angerLevel >= lowerLimitForDangerousMood && angerLevel <= upperLimitForDangerousMood)
		{
			pet.angerMoodField = "dangerous";
		}
		else if (angerLevel >=lowerLimitForPutDownMood)
		{
			pet.angerMoodField = "putdown";
		}
		return pet;
	}
	
	// This method get the value stored in the record for anger mood 
	public static String getAngerMoodField (PetDetails pet)
	{
		return pet.angerMoodField;
	}

	// Uses anger mood to print an appropriate reaction for the user to take
	public static void printAngerReaction (PetDetails pet)
	{
		if (getAngerMoodField(pet).equals("serene"))
		{
			System.out.println("\n" + getNameField(pet) + " is looking " + getAngerMoodField(pet) + ", you can approach it without worry.");
		}
		
		else if (getAngerMoodField(pet).equals("grouchy"))
		{
			System.out.println("\n" + getNameField(pet) + " is looking " + getAngerMoodField(pet) + ", you can approach but be careful.");
		}
		
		else if (getAngerMoodField(pet).equals("dangerous"))
		{
			System.out.println("\n" + getNameField(pet) + " is looking " + getAngerMoodField(pet) + ", you should get away from it while you still can.");
		}
		else if (getAngerMoodField(pet).equals("putdown"))
		{
			System.out.println("\n" + getNameField(pet) + " was put down due to excessive anger.");
		}
	}

	// This method calls the getPetDetails method which prints all the details 
	// of the pet. It does so in a loop passing each pet so that it prints all
	// the details of all the pets
	public static void printDetailsOfAll(PetDetails[] pet)
	{
		for (int i = 0; i <= (pet.length -1); i++)
		{
			getPetDetails(pet[i]);
		}
	}

	// This method calls all the other print details methods for state of mind 
	// and prints them all out
	public static void getPetDetails(PetDetails pet)
	{
		printThirstLevel(pet);
		printHungerLevel(pet);
		printIrritabilityLevel(pet);
		setAngerMoodField(pet);
		printAngerReaction(pet);	
	}
	
	// This lowers the thirst field of the pet
	public static void waterPet (PetDetails pet)
	{
		setThirstField(pet, lowerNumber(getThirstField(pet)));
	}
	
	// This lowers the hunger field of the pet
	public static void feedPet (PetDetails pet)
	{
		setHungerField(pet, lowerNumber(getHungerField(pet)));
	}
	
	// This lowers the irritability field of the pet
	public static void singPet (PetDetails pet)
	{
		setIrritabilityField(pet, lowerNumber(getIrritabilityField(pet)));
	}
	
	// This raises all fields of the pet by a random amount
	public static void ignorePet (PetDetails pet)
	{
		setThirstField(pet, raiseNumber(getThirstField(pet)));
		setHungerField(pet, raiseNumber(getHungerField(pet)));
		setIrritabilityField(pet, raiseNumber(getIrritabilityField(pet)));
	}
	
	// This creates and initialises an array of an array of petdetails 
	public static PetDetails[][] createPetSaves(PetDetails[] pet)
	{
		PetDetails[][] petSaves = new PetDetails[maxNumberOfSaves][pet.length];
		for (int i = 0; i <= (maxNumberOfSaves - 1); i++)
		{
			for (int j = 0; j <= (pet.length-1); j++)
			{
				petSaves[i][j] = new PetDetails();
			}
		}
		return petSaves;
	}
	
	// This checks if any of the pets need to be putdown
	public static String checkIfPutdown(PetDetails[] pet)
	{
		for (int i = 0; i <= (pet.length - 1); i ++)
		{
			if (getAngerMoodField(pet[i]).equals("putdown"))
			{
				return "putdown";
			}
		}
		return "null";
	}
	
	// This checks if all the pets are serene 
	public static String checkIfAllSerene(PetDetails[] pet)
	{
		int serenePets = 0;
		for (int i = 0; i <= (pet.length - 1); i ++)
		{
			if (getAngerMoodField(pet[i]).equals("serene"))
			{
				serenePets = serenePets + 1;
			}
		}
		if (serenePets == pet.length)
		{
			return "serene";
		}
		else
		{
			return "null";
		}
	}

	// This copies the current values of pet to petCopy
	public static PetDetails[] copyPetToPetCopy(PetDetails[] pet, PetDetails[] petCopy)
	{
		for (int i = 0; i <= (pet.length - 1); i++)
		{
			setNameField(petCopy[i], getNameField(pet[i]));
			setSpeciesField(petCopy[i], getSpeciesField(pet[i]));
			setThirstField(petCopy[i], getThirstField(pet[i]));
			setHungerField(petCopy[i], getHungerField(pet[i]));
			setIrritabilityField(petCopy[i], getIrritabilityField(pet[i]));
		}
		return petCopy;
	}
	
	// This method outputs a message and returns an input Int
	public static int inputInt(String message)
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println(message);
		return Integer.parseInt(scanner.nextLine());
	}
	
	// This method outputs a message and returns an input Int
	public static String inputString(String message)
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println(message);
		return scanner.nextLine();
	}
	
	// This method creates a pet save array that holds 5 pet details arrays.
	// It loops asking which pet to take care of and how to take care of it.
	// Also asks if they'd like to sort pets by anger to see which needs 
	// caring most of all.  Loops until game is won or user selects end.
	// If user loses it gives an option to go back a certain number of rounds.
	public static void whatActionLoop(PetDetails[] pet, Scanner scanner) throws IOException
	{
		String actionToTake = "null";
		int stepsBack;
		int counter = 0;
		int petNumber = 0;
		PetDetails[][] petSaves = createPetSaves(pet);
		do 
		{
			PetDetails[] petCopy = createArray(pet.length);
			petCopy = copyPetToPetCopy(pet, petCopy);
			printDetailsOfAll(pet);
			checkIfWinOrLose(counter, pet);
			askToSort(pet);
			if (!checkIfPutdown(pet).equals("putdown"))
			{
				petNumber = inputInt("Which pet would you like to take care of (please input the number, 1 being the pet at the top)?") - 1;
				System.out.println("\nYou have choosen to take care of " + getNameField(pet[petNumber]) + ".");
				actionToTake = inputString("\nWould you like to feed, water, sing, ignore or end (and save current state)?");
			}
			petActionTake(pet, petNumber, actionToTake);
			if (counter <=4)
			{
				if (!checkIfPutdown(pet).equals("putdown"))
				{
					petSaves[counter] = petCopy; 
					counter = counter + 1;
				}
				else if (checkIfPutdown(pet).equals("putdown"))
				{
					stepsBack = inputInt("Game over. \nYou can take a maximum of " + counter + " steps back.");
					if (stepsBack >= counter + 1 || stepsBack == 0)
					{
						System.out.println("You did not enter a valid input. \nSystem has crashed.");
						System.exit(0);
					}
					pet = petSaves[counter - stepsBack];
					counter = 0;
				}
			}
			else if (counter >=5)
			{
				if (!checkIfPutdown(pet).equals("putdown"))
				{
					petSaves[0] = petSaves[1];
					petSaves[1] = petSaves[2];
					petSaves[2] = petSaves[3];
					petSaves[3] = petSaves[4];
					petSaves[4] = petCopy;
				}
				else if (checkIfPutdown(pet).equals("putdown"))
				{
					stepsBack = inputInt("Game over. \nYou can take a maximum of 5 steps back.");
					if (stepsBack >= 6 || stepsBack == 0)
					{
						System.out.println("You did not enter a valid input. \nSystem has crashed.");
						System.exit(0);
					}
					pet = petSaves[5 - stepsBack];
					counter = 0;
				}
			}
		} while (!(actionToTake.equalsIgnoreCase("end")));
	}
	
	// This method takes the action and sets whta should be done to the
	// pet details
	public static void petActionTake(PetDetails[] pet, int petNumber, String actionToTake) throws IOException
	{
		if (actionToTake.equalsIgnoreCase("water"))
		{
			waterPet(pet[petNumber]);
		}
		else if (actionToTake.equalsIgnoreCase("feed"))
		{
			feedPet(pet[petNumber]);
		}
		else if (actionToTake.equalsIgnoreCase("sing"))
		{
			singPet(pet[petNumber]);
		}
		else if (actionToTake.equalsIgnoreCase("end"))
		{
			System.out.println("You have chosen to end the program. \nA save file will be made and system will then close.");
			savePetDetailsInFile(pet);
		}
		else if (actionToTake.equalsIgnoreCase("ignore"))
		{
			ignorePet(pet[petNumber]);
		}
		else
		{
			System.out.println("You did not enter a valid input. \nSystem has crashed.");
			System.exit(0);
		}
	}
	
	// This method prints a message for when the user wins and 
	// exits program
	public static void printWinMessage()
	{
		System.out.println("You win!");
		System.out.println("All dinosaur pets are serene and so you have reached a state of Dinosaur Nirvana!");
		System.exit(0);
	}

	// This method checks if the user has lost or won and outputs message
	public static void checkIfWinOrLose(int counter, PetDetails[] pet)
	{
		if (counter == 0 && checkIfPutdown(pet).equals("putdown"))
		{
			System.out.println("Game over. You have lost at the start of the progam!");
			System.out.println("This means you have to restart as you have no saves to go back to.");
			System.exit(0);
		}
		else if (checkIfAllSerene(pet).equalsIgnoreCase("serene"))
		{
			printWinMessage();
		}
	}

	// This method asks the user if they'd like to sort the pets by anger.
	// If answer is Y then it calls bubblesort method that sorts the 
	// array putting angriest at end of array.
	// It then re prints the newly sorted array
	public static void askToSort(PetDetails[] pet)
	{
		Scanner scanner = new Scanner(System.in);
		String ans;
		System.out.println("Would you like sort the pets by anger with angriest at the bottom (Y/N).");
		ans = scanner.nextLine();
		if (ans.equalsIgnoreCase("N"))
		{
			return;
		}
		else if (ans.equalsIgnoreCase("Y"))
		{
			bubbleSort(pet);

			printDetailsOfAll(pet);
			return;
		}
	}
	
	// This method is a standard bubble sort algorithm that calls the 
	// swap method to swap positions. 
	// It orders it so that those with the highest total anger are 
	// put at the end of the array.
	public static void bubbleSort(PetDetails[] pet)
	{
		for (int pass = 1; pass <= (pet.length -1); pass++)
		{
			for (int i = 0; i < (pet.length -(pass+1)); i++)
			{
				if (getTotalAngerScore(pet[i]) > getTotalAngerScore(pet[i+1]))
				{
					singleSwapArrayPositions(pet, i, i+1);
				}
			}
		}
	}
	
	// This method creates a temp pet details in order to swap the details
	// in two positions of the array
	public static void singleSwapArrayPositions(PetDetails[] pet, int pos1, int pos2)
	{
		PetDetails petTemp = new PetDetails();
		petTemp = pet[pos2];
		pet[pos2] = pet[pos1];
		pet[pos1] = petTemp;
	}

	// This method prints the number of pets, then prints all of the fields 
	// of each pet into a save file in form .txt
	public static void savePetDetailsInFile(PetDetails[] pet) throws IOException
	{
		PrintWriter outputStream = new PrintWriter(new FileWriter("PetDetailsSave.txt"));
		outputStream.println(pet.length);
		for (int i=0; i <= (pet.length -1); i++)
		{
			outputStream.println(getNameField(pet[i]));
			outputStream.println(getSpeciesField(pet[i]));
			outputStream.println(getThirstField(pet[i]));
			outputStream.println(getHungerField(pet[i]));
			outputStream.println(getIrritabilityField(pet[i]));
		}
		outputStream.close();
		System.exit(0);
	}

	// This method reads in pet details from the save file into pet
	// First line shows how many pets are there were and this is used 
	// to create the pet array
	public static PetDetails[] readFile() throws IOException
	{
		BufferedReader inStream = new BufferedReader (new FileReader("PetDetailsSave.txt"));
		int numberOfPets = Integer.parseInt(inStream.readLine());
		PetDetails[] pet = createArray(numberOfPets);
		for (int i = 0;i <= (pet.length -1); i++)
		{
			setNameField(pet[i], inStream.readLine());
			setSpeciesField(pet[i], inStream.readLine());
			setThirstField(pet[i], Integer.parseInt(inStream.readLine()));
			setHungerField(pet[i], Integer.parseInt(inStream.readLine()));
			setIrritabilityField(pet[i], Integer.parseInt(inStream.readLine()));
			setAngerMoodField(pet[i]);
		}
		inStream.close();
		return pet;
	}
	
	// This method checks if it can find the save file and then asks if they
	// would like to load it
	public static void askToReadFile() throws IOException
	{
		File saveFile = new File("PetDetailsSave.txt");
		Scanner scanner = new Scanner(System.in);
		if (saveFile.exists())
		{
			String ans = inputString("A previous save file was found, would you like to load it (Y/N).");
			if (ans.equalsIgnoreCase("Y"))
			{
				System.out.println("Save file will be loaded.");
				PetDetails[] pet = readFile();
				whatActionLoop(pet, scanner);
			}
			else
			{
				System.out.println("Program shall commence as normal.");
			}
		}
		else
		{
			System.out.println("No previous save file was found, program shall commence as normal.");
		}
	}
}

class PetDetails
{
	String nameField;
	String speciesField;
	int thirstField;
	int hungerField;
	int irritabilityField;
	String angerMoodField;
}