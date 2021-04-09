
// Throughout this project, the use of data structures are not permitted such as methods like .split and .toCharArray



import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
// More packages may be imported in the space below

class CustomerSystem{
    
    // sum of the odd number
    static int sum1 = 0;
    
    private static String userFirstName, userLastName, userCity, userPostalCode, userCreditCard;
    private static int userId = 0;

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
    * Stores Customers information @- Jeffrey Lin
    *
    * @param - none, The methods are used for storing information
    * @ return - FirstName, LastName, city, postalcode
    */
    public static void enterCustomerInfo() {
        Scanner reader = new Scanner(System.in);

        System.out.println("What is your first name?");
        userFirstName = reader.nextLine(); // prompt user input First name 

        System.out.println("What is your Last Name?");
        userLastName = reader.nextLine(); // prompt user input Last name

        System.out.println("What is the city you live in?");
        userCity = reader.nextLine(); // prompt user input the city

        System.out.println("What is your postal code?");
        userPostalCode = reader.nextLine(); // Prompt user input PostalCode

        boolean ValidateNumber = false; // initi boolean ValidateNumber variable

        while (ValidateNumber == false){ // SET my condition into loop everytime when boolean value is false
            System.out.println("What is your credit card number? (At least 9 digits)");
            String userCreditCard = reader.nextLine(); // prompt the user for CreditCard Number
            int CreditCardLength = userCreditCard.length(); // Store the integer value of the length of the CreditCard Number

            if(CreditCardLength >=9){ // IF CONITION: Checks the CreditcardLength if is greater or equal 9
                validateCreditCard(userCreditCard); // The length of the CreditCard is pass and becomes the String Value param of validateCreditCard
                ValidateNumber = true; // Boolean = true; While loop Stop
            }    
            // ELSE CONDITION: if the length of the creditcard number is less than 9 
            else{
                System.out.println("The credit card number you insert is less than 9 digits, Please entered it again"); // Tell the user to reinput the credit card again
                ValidateNumber = false; // Boolean = false; While loop repeats
            }

        }
        reader.close();

        userId++;
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
                if ((postalScanner.nextLine().substring(0, 3)).equals(postalCode)){
                    return true;
                }
            }
        }
        return false;
    }
    /*
    * Description: Reverse the number and also adds up the odd digits sum
    * @ Jeffrey Lin
    *
    * @param - String value of from CustomerInformation Methods
    * @Returns - The Reverse value 
    * @Returns - the OddNumber Sum
    */
    public static void validateCreditCard(String Number){
        System.out.println("CreditCard Number: " + Number);

        
        String reverse = ""; // Converting Char to String
        // i should be the length() -1 of Number; i decrease each loop
        for(int i = Number.length() -1; i>=0; i--){
                    
            reverse = reverse + Number.charAt(i); // Converting char to String
        }
        System.out.println("Reversed CredutCard Number is: " + reverse); // prints the Reverse number
                
        int len = reverse.length(); // Stores the reverse number length in a valables called "len"
        // i should always be less than len
        for (int i = 0; i < len; i++){
            if(i%2 ==0){// Whenever i%2 =0, means the oddnumber
                char dig = reverse.charAt(i);
                String str = "";
                str = str + dig; // convert char to String
                int OddNumber = Integer.valueOf(str); //String to char
                sum1 += OddNumber; // Finally they can be add up together
            }
                    
        }
        System.out.println(sum1); //prints the sum1

        EvenNumber(reverse); // reverse string value become the param of the EvenNumber
              
    }
    /**
     * Generates the customer's data file - Kalen.
     */
    public static void generateCustomerDataFile(){
        Scanner customerScanner = new Scanner(System.in);
        String fileName, fileLocation;
        System.out.println("|| CUSTOMER DATA GENERATION ||");
        if (userId == 0){ // There isn't input yet.
            System.out.println("PLEASE ENTER YOUR USER DATA FIRST");
            return;
        }
        System.out.printf("%s: ", "Please provide the output file name");
        fileName = customerScanner.nextLine();
        System.out.printf("%s: ", "Please provide the output location");
        fileLocation = customerScanner.nextLine();
        try{
            String exportLocation = String.format("%s\\%s.csv", fileLocation, fileName);
            PrintWriter dataWriter = new PrintWriter(new File(exportLocation));
            StringBuilder stringBuilder = new StringBuilder(); // Use a string builder.
            stringBuilder.append("Id,First Name,Last Name,City,Postal Code,Credit Card\n"); // Append the header.
            stringBuilder.append(String.format("%d,%s,%s,%s,%s,%s", userId, userFirstName, userLastName, userCity, userPostalCode, userCreditCard));
            dataWriter.write(stringBuilder.toString()); // Write to file.
            dataWriter.close(); // Close the reader.
            System.out.printf("|| DONE DATA EXPORT TO LOCATION: %s ||\n", exportLocation);
        } catch (FileNotFoundException e) { // Catch the exception
            System.out.println("DATA EXPORT HAS FAILED: THIS COULD BE BECAUSE THE FILE ALREADY EXISTS OR THAT THE LOCATION IS INVALID");
        }
    }
    /*******************************************************************
     *       ADDITIONAL METHODS MAY BE ADDED BELOW IF NECESSARY         *
     *******************************************************************/
    /*
     * This method is used for calculates the even number sum in the Luhn-Algorithm 
     * @ Jeffrey Lin
     * @ param - The reverse number from the above methods
     * @ return - sum of the evennumber = sum2
    */
    public static void EvenNumber(String ReverseNumber){
    
        int len = ReverseNumber.length(); // Stores the length number into variable called len
        
        System.out.print("Even digits: \t\t"); // the number below should connects into this line, in order to make "visual affects"

        // i should be always be less than len
        for (int i = 1; i < len; i+=2){
            if(i+2>=len){ // Checks everytime if the i+2 has greater than len value; Basically prints the last digits
                System.out.println(ReverseNumber.charAt(i)); // prints the even order of the digits; and should indicates the last even order digits
            }
            // if i+2 is in the range of len, prints the even order digits
            else{
                System.out.print(ReverseNumber.charAt(i)+", "); // prints the even order digits 
            }
        }

        System.out.print("Double digits: \t\t"); // the number below should connects into this line, in order to make "visual affects"

        // i should be always be less than len
        for (int i = 1; i < len; i+=2){
            if(i+2>=len){ // Checks everytime if the i+2 has greater than len value; Basically doubles 2 the last digits
                System.out.println(Character.getNumericValue(ReverseNumber.charAt(i))*2); // First the convert char value into integer, enable for mulitple by 2
            }
            // if i+2 is in the range of len, doubles the even order digits
            else{
                System.out.print(Character.getNumericValue(ReverseNumber.charAt(i))*2 +", "); // Convert char value into integer, enable for mulitple by 2
            }
        }

        System.out.print("Sum the digits>9: \t"); // The number below should connects into this line, in order to make "visual affects"

        // i should be always be less than len
        for (int i = 1; i < len; i+=2){
            if(i+2>=len){ // IF CONDITION: Checks everytime if the i+2 has greater than len value; Basically doubles 2 the last digits
                if(Character.getNumericValue(ReverseNumber.charAt(i))*2>9){ // After doubles the even order digits; IF CONDITION: checks whether is pass 9 or not.
                    int num=Character.getNumericValue(ReverseNumber.charAt(i))*2; 
                    System.out.println(num%10+num/10); // add the number first digits by second digits
                }

                // ELSE CONDITION: if the double even digits is less 9, then leave it as the same
                else{
                    System.out.println(Character.getNumericValue(ReverseNumber.charAt(i))*2);
                }
            }

            // ELSE CONDITION: if i+2 is in the range of len
            else{
                if(Character.getNumericValue(ReverseNumber.charAt(i))*2>9){ // After doubles the even order digits; IF CONDITION: checks whether is pass 9 or not.
                    int num = Character.getNumericValue(ReverseNumber.charAt(i))*2;
                    System.out.print(num%10+num/10+", "); // add the number first digits by second digits
                }

                // ELSE CONDITION: if the double even digits is less 9, then leave it as the same
                else{
                    System.out.print(Character.getNumericValue(ReverseNumber.charAt(i))*2+", ");
                }
            }
        }

        System.out.print("Sum the new digits: \t"); // the number below should connects into this line

        int sum2=0; //initi of sum

        // i should be always be less than len
        for (int i = 1; i < len; i+=2){
            if(i+2>=len){ // IF CONDITION: Checks everytime if the i+2 has greater than len value; Basically doubles 2 the last digits
                if(Character.getNumericValue(ReverseNumber.charAt(i))*2>9){
                    int num=Character.getNumericValue(ReverseNumber.charAt(i))*2;
                    System.out.print(num%10+num/10);
                    sum2+=num%10+num/10; // add up all the new digits together
                }

                // ELSE CONDITION: leave the same number if the digits is not pass 9
                else{
                    System.out.print(Character.getNumericValue(ReverseNumber.charAt(i))*2);
                    sum2+=Character.getNumericValue(ReverseNumber.charAt(i))*2; // add up all the digits that is not greater than 9
                }
            }

            else{
                if(Character.getNumericValue(ReverseNumber.charAt(i))*2>9){ // IF CONDITION: Checks if the value is greater than 9 or not
                    int num=Character.getNumericValue(ReverseNumber.charAt(i))*2;
                    System.out.print(num%10+num/10+" +"); // prints all the new digits all the time and + sign after it
                    sum2+=num%10+num/10; // adds up all the new digits
                }
                
                // ELSE CONDITION: if the double even digits is less 9, then leave it as the same
                else{
                    System.out.print(Character.getNumericValue(ReverseNumber.charAt(i))*2+" +"); // prints all the digits that are less than 9
                    sum2+=Character.getNumericValue(ReverseNumber.charAt(i))*2; // add up the digits
                }
            }
        }
        System.out.println("="+sum2+"=sum2"); // Finally prints the sum2 

        System.out.println("sum1 +sum2 ="+(sum1+sum2)); // In here the oddnumber sum + the new even digits sum

        // IF CONDITION: checks if the overall sum last digits end up with 0 or not.
        if((sum1+sum2)%10==0){
            returnValidity(true); // if the last digits is end up with 0, the boolean value will be true and represents as the param returnValidity methods
        }else{
            returnValidity(false); // if the last digits is not end up with 0, the boolean value will be false and represents as the param returnValidity methods
        }
    }

        /*
         * Description: Checks the Validity of the creditcard
         * @ Jeffrey Lin
         * 
         * @ param - the boolean value was assign in EvenNumber methods
         * @ Return - the results of whether is valid or not valid (flag).
        */
    public static boolean returnValidity(boolean flag){
        if (flag == true){ // IF CONDITION: if the flag is true
            System.out.println("Vaild as the sum ends with zero(0)"); //CreditCard Number valid
        }

        //ELSE CONDITION: if the flag is false
        else{
            System.out.println("Not vaild as the sum does not end with a zero (0)"); // CreditCard Number is not valid
            System.out.println();
        }
        return flag;
    }
}
