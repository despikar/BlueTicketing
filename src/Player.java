import java.util.*;
import java.io.*;

// Player class implements the User interface
class Player implements User {
    private String userName; // Stores the player's username
    private String password; // Stores the player's password
    private int userType;    // Stores the type of user

    // Constructor to initialize Player object with username, password, and userType
    public Player(String userName, String password, int userType) {
        this.userName = userName;
        this.password = password;
        this.userType = userType;
    }

    // Method to perform actions specific to a player
    @Override
    public void performActions() {
        // Implement player actions
        int option = 0; // Stores the user's selected option
        Scanner input = new Scanner(System.in); // Scanner for user input
        try {
            System.out.println("Welcome " + userName); // Welcome message
            int submitagain = 0; // Flag to control ticket submission loop
            System.out.println("What would you like to do?"); // Menu options
            System.out.println("1. Submit a ticket");
            System.out.println("2. Ask a question");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: "); // Prompt user for choice
            while(submitagain != 2) { // Loop until user chooses to exit ticket submission
                if(input.hasNextInt()) { // Check if user input is an integer
                    option = input.nextInt(); // Read the option
                    switch (option) { // Switch based on user's choice
                        case 1: // Submit a ticket
                            System.out.println("What type of ticket do you want to submit?");
                            System.out.println("1. Customer Support issues");
                            System.out.println("2. Technical issues");
                            System.out.print("Enter your choice: "); // Prompt for ticket type
                            int ticket = input.nextInt(); // Read ticket type
                            if(ticket == 1) { // Customer support ticket
                                // Prompt for issue type
                                System.out.println("Select a type of issue for the ticket:");
                                System.out.println("1. Reporting a Player");
                                System.out.println("2. Billing and Payment Issues");
                                System.out.println("3. Account Recovery");
                                System.out.print("Enter your choice: ");
                                int select = input.nextInt(); // Read issue type
                                if(select == 1) { // Reporting a player issue
                                    try {
                                        Scanner scan = new Scanner(System.in);
                                        System.out.println("Enter a description for the ticket:");
                                        String description = scan.nextLine(); // Read ticket description
                                        String explanation = ("Reporting a Player: " + description); // Construct ticket explanation
                                        File customersupport = new File("customersupport.txt"); // Customer support ticket file
                                        FileWriter writer = new FileWriter(customersupport,true); // FileWriter for appending to file
                                        int resolved = 0; // Initial ticket resolution status
                                        writer.write(userName + "," + explanation + "," + resolved + "\n"); // Write ticket details to file
                                        writer.close(); // Close FileWriter
                                        System.out.println("Ticket submitted successfully!"); // Confirmation message
                                    } catch (IOException e) {
                                        e.printStackTrace(); // Handle IOException
                                    }
                                }
                                // Similar blocks for other issue types...
                            } else if(ticket == 2) { // Technical issue ticket
                                // Similar block for technical issues...
                            }
                            break; // Exit switch statement

                        case 2: // Ask a general question
                            System.out.println("General question portal."); // Information message
                            try {
                                Scanner scan = new Scanner(System.in);
                                System.out.println("Enter a description for the question:");
                                String description = scan.nextLine(); // Read question description
                                String explanation = ("General question: " + description); // Construct question explanation
                                File questions = new File("questions.txt"); // Question file
                                FileWriter writer = new FileWriter(questions,true); // FileWriter for appending to file
                                int resolved = 0; // Initial question resolution status
                                writer.write(userName + "," + explanation + "," + resolved + "\n"); // Write question details to file
                                writer.close(); // Close FileWriter
                                System.out.println("Ticket submitted successfully!"); // Confirmation message
                            } catch (IOException e) {
                                e.printStackTrace(); // Handle IOException
                            }
                            break; // Exit switch statement

                        case 3: // Exit option
                            System.out.println("Exiting..."); // Exit message
                            break; // Exit switch statement

                        default: // Invalid option
                            System.out.println("Invalid option."); // Error message for invalid option
                    }
                } else { // Invalid input
                    System.out.println("There was an error. Please try again later!"); // Error message for invalid input
                }
                Scanner newticket = new Scanner(System.in); // Scanner for new ticket submission
                System.out.print("Would you like to submit a new ticket? (1. Yes / 2. No): "); // Prompt for new ticket submission
                submitagain = newticket.nextInt(); // Read user choice
                // Validate user choice and prompt again if invalid
                if(submitagain == 1) {
                    System.out.println("What would you like to do?");
                    System.out.println("Option 1: Submit a ticket");
                    System.out.println("Option 2: Ask a question");
                    System.out.println("Option 3: Exit");
                    System.out.print("Enter your choice: ");
                } else if(submitagain != 1 && submitagain != 2) {
                    System.out.println("Error: Wrong input...");
                    System.out.print("Would you like to submit a new ticket? (1. Yes / 2. No): ");
                    submitagain = newticket.nextInt();
                }
            }
        } catch (InputMismatchException e) { // Handle input mismatch exception
            System.out.println("Invalid input. Please enter a number."); // Error message for invalid input
        }
        System.out.print("Would you like to give feedback? (1. Yes / 2. No): "); // Prompt for feedback
        Scanner feed = new Scanner(System.in); // Scanner for feedback input
        int feedback = feed.nextInt(); // Read feedback choice
        try {
            if(feedback == 1) { // If user wants to give feedback
                File fb = new File("feedback.txt"); // Feedback file
                FileWriter responses = new FileWriter("feedback.txt", true); // FileWriter for appending to file
                System.out.println("How satisfied were you with the ticketing process?"); // Prompt for satisfaction level
                System.out.println("   a) Very satisfied");
                System.out.println("   b) Somewhat satisfied");
                System.out.println("   c) Neutral");
                System.out.println("   d) Somewhat dissatisfied");
                System.out.println("   e) Very dissatisfied");
                Scanner newscan = new Scanner(System.in);
                System.out.print("Enter your response: "); // Prompt for response
                String response = newscan.nextLine(); // Read response
                // Write feedback response to file
                if(response.equalsIgnoreCase("a")) {
                    String answer = ("Very satisfied");
                    responses.write("\n" + userName + "," + answer);
                    responses.close(); // Close FileWriter
                    System.out.println("Thank you for completing the survey!"); // Confirmation message
                }
                // Similar blocks for other satisfaction levels...
            } else if(feedback == 2) { // If user does not want to give feedback
                System.out.println("Ok! Exiting..."); // Exit message
            } else { // Invalid feedback choice
                System.out.println("Error: Wrong input. Exiting..."); // Error message for invalid input
            }
        } catch(IOException e) { // Handle IOException
            e.printStackTrace(); // Print stack trace for IOException
        }
    }
}
