import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            // Prompt the user for action
            System.out.println("Welcome! What would you like to do?");
            System.out.println("1. Login");
            System.out.println("2. Register a new account");

            // Read user input for action choice
            int account = scanner.nextInt();

            if (account == 1) {
                // Login Process
                Scanner log = new Scanner(System.in);
                System.out.println("Welcome to the Login Portal!");
                System.out.printf("Enter your User name: ");
                String userName = log.nextLine();
                System.out.printf("Enter your Password: ");
                String password = log.nextLine();
                int userType = 0;

                try {
                    File login = new File("Userdata.txt");
                    Scanner inp = new Scanner(login);

                    boolean loggedIn = false;

                    // Check if the credentials match with stored data
                    while (!loggedIn & inp.hasNextLine()) {
                        String line = inp.nextLine();
                        String[] splits = line.split(",");
                        if (userName.equals(splits[0]) && password.equals(splits[1])) {
                            userType = Integer.parseInt(splits[2]);
                            loggedIn = true;
                            User user = null;

                            // Initialize user object based on userType
                            switch (userType) {
                                case 1:
                                    user = new Admin(userName, password, userType);
                                    break;
                                case 2:
                                    user = new customersupport(userName, password, userType);
                                    break;
                                case 3:
                                    user = new developer(userName, password, userType);
                                    break;
                                case 4:
                                    user = new Player(userName, password, userType);
                                    break;
                                default:
                                    System.out.println("User not initialized. Exiting...");
                                    break;
                            }

                            // Perform actions based on user type
                            if (user != null) {
                                user.performActions();
                            } else {
                                System.out.printf("\n");
                            }
                        }
                    }
                    //inp.close();

                    // If login fails, prompt for registration or retry
                    if (!loggedIn) {
                        Scanner reg = new Scanner(System.in);
                        System.out.println("Login failed. Invalid credentials.");
                        System.out.printf("Would you like to create a new account? (yes/no): ");
                        String createAccount = reg.nextLine();

                        if (createAccount.equalsIgnoreCase("yes")) {
                            System.out.printf("Enter username: ");
                            userName = reg.nextLine();
                            System.out.printf("Enter your Password: ");
                            password = reg.nextLine();

                            // Register a new player
                            registerNewPlayer(userName, password);

                            Scanner choise = new Scanner(System.in);
                            System.out.println("Do you want to log in?");
                            System.out.printf("If yes, press 1: ");
                            int chose = choise.nextInt();

                            if (chose == 1) {
                                Scanner newinp = new Scanner(login);
                                loggedIn = false;

                                // Perform login again after registration
                                while (newinp.hasNextLine()) {
                                    String line2 = newinp.nextLine();
                                    String[] newsplits = line2.split(",");
                                    if (userName.equals(newsplits[0]) && password.equals(newsplits[1])) {
                                        userType = Integer.parseInt(newsplits[2]);
                                        System.out.println("You have logged in");
                                        loggedIn = true;
                                        User user = null;

                                        // Initialize user object based on userType
                                        switch (userType) {
                                            case 1:
                                                user = new Admin(userName, password, userType);
                                                break;
                                            case 2:
                                                user = new customersupport(userName, password, userType);
                                                break;
                                            case 3:
                                                user = new developer(userName, password, userType);
                                                break;
                                            case 4:
                                                user = new Player(userName, password, userType);
                                                break;
                                            default:
                                                System.out.println("User not initialized. Exiting...");
                                                break;
                                        }
                                        // Perform actions based on user type
                                        if (user != null) {
                                            user.performActions();
                                        } else {
                                            System.out.println("\n");
                                        }
                                    }
                                }
                                //newinp.close();
                            } else {
                                System.out.println("Ok");
                            }
                        } else {
                            System.out.printf("Do you want to try to login again? (yes/no): ");
                            Scanner tryagain = new Scanner(System.in);
                            String newtry = tryagain.nextLine();

                            if (newtry.equalsIgnoreCase("yes")) {
                                Scanner newlog = new Scanner(System.in);
                                System.out.println("Welcome to the Login Portal!");
                                System.out.printf("Enter your User name: ");
                                userName = newlog.nextLine();
                                System.out.printf("Enter your Password: ");
                                password = newlog.nextLine();
                                userType = 0;

                                try {
                                    File newlogin = new File("Userdata.txt");
                                    Scanner newinp = new Scanner(login);
                                    boolean newloggedIn = false;

                                    // Perform login with new credentials
                                    while (!newloggedIn & newinp.hasNextLine()) {
                                        String newline = newinp.nextLine();
                                        String[] splitsagain = newline.split(",");
                                        if (userName.equals(splitsagain[0]) && password.equals(splitsagain[1])) {
                                            userType = Integer.parseInt(splitsagain[2]);
                                            newloggedIn = true;
                                            User user = null;

                                            // Initialize user object based on userType
                                            switch (userType) {
                                                case 1:
                                                    user = new Admin(userName, password, userType);
                                                    break;
                                                case 2:
                                                    user = new customersupport(userName, password, userType);
                                                    break;
                                                case 3:
                                                    user = new developer(userName, password, userType);
                                                    break;
                                                case 4:
                                                    user = new Player(userName, password, userType);
                                                    break;
                                                default:
                                                    System.out.println("User not initialized. Exiting...");
                                                    break;
                                            }

                                            // Perform actions based on user type
                                            if (user != null) {
                                                user.performActions();
                                            } else {
                                                System.out.println("\n");
                                            }
                                        }
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            } else {
                                System.out.println("Ok! Exiting...");
                                return;
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (account == 2) {
                Scanner newacc = new Scanner(System.in);
                System.out.printf("Enter username: ");
                String userName = newacc.nextLine();
                System.out.printf("Enter your Password: ");
                String password = newacc.nextLine();
                registerNewPlayer(userName, password);
                Scanner choise = new Scanner(System.in);
                System.out.println("Do you want to log in?");
                System.out.printf("If yes, press 1: ");
                int chose = choise.nextInt();
                if (chose == 1) {
                    File login = new File("Userdata.txt");
                    Scanner newinp = new Scanner(login);
                    boolean loggedIn = false;
                    while (newinp.hasNextLine()) {
                        String line2 = newinp.nextLine();
                        String[] newsplits = line2.split(",");
                        if (userName.equals(newsplits[0]) && password.equals(newsplits[1])) {
                            int userType = Integer.parseInt(newsplits[2]);
                            System.out.println("You have logged in");
                            loggedIn = true;
                            User user = null;
                            switch (userType) {
                                case 1:
                                    user = new Admin(userName, password, userType);
                                    break;
                                case 2:
                                    user = new customersupport(userName, password, userType);
                                    break;
                                case 3:
                                    user = new developer(userName, password, userType);
                                    break;
                                case 4:
                                    user = new Player(userName, password, userType);
                                    break;
                                default:
                                    System.out.println("User not initialized. Exiting...");
                            }
                            if (user != null) {
                                user.performActions();
                            } else {
                                System.out.println("\n");
                            }
                        }
                    }
                    newinp.close();
                } else {
                    System.out.println("Ok! Exiting...");
                }
            } else {
                System.out.printf("Wrong input. Exiting...");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Method to register a new player
    private static void registerNewPlayer(String userName, String password) {
        try {
            OTP newotp = new OTP();
            if (!newotp.ROTP()) {
                System.out.println("Registration failed");
            } else {
                File file = new File("Userdata.txt");
                FileWriter writer = new FileWriter(file, true);
                writer.write(userName + "," + password + ",4\n"); // Assuming userType 4 for players
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
