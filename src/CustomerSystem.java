
// Throughout this project, the use of data structures are not permitted such as methods like .split and .toCharArray



import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
// More packages may be imported in the space below

class CustomerSystem{
    public static void main(String[] args){
        // Please do not edit any of these variables
        Scanner reader = new Scanner(System.in);
        String userInput, enterCustomerOption, generateCustomerOption, exitCondition;
        enterCustomerOption = "1";
        generateCustomerOption = "2";
        exitCondition = "9";

        // More variables for the main may be declared in the space below


        do{
            printMenu();                                    // Printing out the main menu
            userInput = reader.nextLine();                  // User selection from the menu

            if (userInput.equals(enterCustomerOption)){
                // Only the line below may be editted based on the parameter list and how you design the method return
		        // Any necessary variables may be added to this if section, but nowhere else in the code
                enterCustomerInfo();
            }
            else if (userInput.equals(generateCustomerOption)) {
                // Only the line below may be editted based on the parameter list and how you design the method return
                generateCustomerDataFile();
            }
            else{
                System.out.println("Please type in a valid option (A number from 1-9)");
            }

        } while (!userInput.equals(exitCondition));         // Exits once the user types 
        
        reader.close();
        System.out.println("Program Terminated");
    }
    public static void printMenu(){
        System.out.println("Customer and Sales System\n"
        .concat("1. Enter Customer Information\n")
        .concat("2. Generate Customer data file\n")
        .concat("3. Report on total Sales (Not done in this part)\n")
        .concat("4. Check for fraud in sales data (Not done in this part)\n")
        .concat("9. Quit\n")
        .concat("Enter menu option (1-9)")
        );
    }
    /*
    * This method may be edited to achieve the task however you like.
    * The method may not nesessarily be a void return type
    * This method may also be broken down further depending on your algorithm
    */
    public static void enterCustomerInfo() {
        Scanner reader = new Scanner(System.in);
        
        System.out.println("What is your first name?");
        String FirstName = reader.nextLine();

        System.out.println("What is your Last Name?");
        String LastName = reader.nextLine();

        System.out.println("What is the city you live on?");
        String city = reader.nextLine();
    }
   /**
     * Validates a postal code - Kalen
     * @param postalCode the queried postal code.
     * @return true if it is a valid postal code, false otherwise.
     */
    public static boolean validatePostalCode(String postalCode) {
        if (postalCode.length() != 3) return false; // Check length.
        Scanner postalScanner = null;
        try {
            postalScanner = new Scanner(new File("src/postal_codes.csv"));
        } catch(FileNotFoundException s)  {
            System.out.println("postal_codes.csv does not exist. Please insure postal_codes.csv is inside the src folder.");
        }
        if (postalScanner != null) { // Check if the scanner is properly initialized.
            postalScanner.nextLine(); // Skip the first line since it isn't valid data.
            while (postalScanner.hasNextLine()) { // If there are more lines.
                // We already know that the postal code is the first 3 digits of every line, so compare those 3 digits to the queried postal code.
                // Don't need to use delimiter as that will be a waste of iterations since we already know that it will ALWAYS be the first 3 of each line. Doing it line by line will be faster.
                if ((postalScanner.nextLine().substring(0, 3)).equals(postalCode)) return true;
            }
        }
        return false;
    }
    /*
    * This method may be edited to achieve the task however you like.
    * The method may not nesessarily be a void return type
    * This method may also be broken down further depending on your algorithm
    */
    public static void validateCreditCard(){
    }
    /*
    * This method may be edited to achieve the task however you like.
    * The method may not nesessarily be a void return type
    * This method may also be broken down further depending on your algorithm
    */
    public static void generateCustomerDataFile(){
    }
    /*******************************************************************
    *       ADDITIONAL METHODS MAY BE ADDED BELOW IF NECESSARY         *
    *******************************************************************/
}
