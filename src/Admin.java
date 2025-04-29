import java.util.*;
import java.io.*;

// Admin class implements the User interface
class Admin implements User {
    private Player player;
    private developer developer;
    private customersupport customersupport;

    // Constructor to initialize instances of Player, Developer, and CustomerSupport
    public Admin(String userName, String password, int userType) {
        this.player = new Player(userName, password, userType);
        this.developer = new developer(userName, password, userType);
        this.customersupport = new customersupport(userName, password, userType);
    }
	
	@Override
	// Method for the admins to perform actions
	public void performActions(){
		System.out.println("What would you like to do?");
		System.out.println("1. Check on the other user classes");
		System.out.println("2. Answer questions");
		System.out.println("3. Check feedback ");
		Scanner scan = new Scanner(System.in);
		int adminChoise = scan.nextInt();
		if(adminChoise == 1){
			// Check on other user classes
			System.out.println("What would you like to check?");
			System.out.println("1. The Player user class");
			System.out.println("2. The Customer Support user class");
			System.out.println("3. The Developer user class");
			Scanner input = new Scanner(System.in);
			int check = input.nextInt();
			if(check == 1){
				player.performActions();
			}
			else if(check == 2){
				customersupport.performActions();
			}
			else if(check == 3){
				developer.performActions();
			}
		}
		else if(adminChoise == 2){
			try{
				// Read the entire file into memory
				List<String> lines = new ArrayList<>();
				try (BufferedReader br = new BufferedReader(new FileReader("questions.txt"))) {
					String line;
					while ((line = br.readLine()) != null) {
						lines.add(line);
					}
				}
				
				boolean foundTickets = false;
				// Process each line and append comments
				Scanner input = new Scanner(System.in);
				for (int i = 0; i < lines.size(); i++) {
					String line = lines.get(i);
					if (line.endsWith(",0")) {
						foundTickets = true;
						System.out.println(line);
						String[] splits = line.split(",");
						splits[2] = "1";
						String newLine = String.join(",", splits);
						
						// Prompt user for comments
						System.out.println("Please input comments for this ticket:");
						String comments = input.nextLine();

						// Append comments to the line
						newLine += ", Answer:" + comments;
						lines.set(i, newLine);

						// Prompt user for further action
						System.out.printf("If you want to answer more questions press 1, if not press 2 ");
						int end = input.nextInt();
						input.nextLine(); // Consume newline
						if (end == 2) {
							System.out.println("Ok, ending correcting process");
							break;
						} else if (end != 1) {
							System.out.println("Wrong input, exiting...");
							break;
						}
					}
					int count = lines.size() - i;
					if(count == 1){
						foundTickets = false;
					}
				}
				if(!foundTickets){
					System.out.println("No questions to answer to");
				}

				// Write the modified lines back to the file
				try (PrintWriter writer = new PrintWriter(new FileWriter("questions.txt"))) {
					for (String line : lines) {
						writer.println(line);
					}
				}

				System.out.println("Exiting...");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(adminChoise == 3){
			try{
				// Variables to count feedback responses
				int a=0;
				int b=0;
				int c=0;
				int d=0;
				int e=0;
				List<String> newlines = new ArrayList<>();
				try (BufferedReader feedback = new BufferedReader(new FileReader("feedback.txt"))) {
					String newline;
					while ((newline = feedback.readLine()) != null) {
						newlines.add(newline);
					}
				}
				// Loop through feedback responses and count each category
				for(int i=0;i<newlines.size();i++){
					String newline = newlines.get(i);
					String[] splits = newline.split(",");
					if(splits[1].equalsIgnoreCase("Very satisfied")){
						a++;
					}
					else if(splits[1].equalsIgnoreCase("Somewhat satisfied")){
						b++;
					}
					else if(splits[1].equalsIgnoreCase("Neutral")){
						c++;
					}
					else if(splits[1].equalsIgnoreCase("Somewhat dissatisfied")){
						d++;
					}
					else if(splits[1].equalsIgnoreCase("Very dissatisfied")){
						e++;
					}
				}
				// Display feedback results
				System.out.printf("The results of the feedback check are:");
				System.out.printf("\nVery satisfied: %d which is %.2f%% of the total answers", a, ((double) a / newlines.size()) * 100);
				System.out.printf("\nSomewhat satisfied: %d which is %.2f%% of the total answers", b, ((double) b / newlines.size()) * 100);
				System.out.printf("\nNeutral: %d which is %.2f%% of the total answers", c, ((double) c / newlines.size()) * 100);
				System.out.printf("\nSomewhat dissatisfied: %d which is %.2f%% of the total answers", d, ((double) d / newlines.size()) * 100);
				System.out.printf("\nVery dissatisfied: %d which is %.2f%% of the total answers", e, ((double) e / newlines.size()) * 100);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			System.out.printf("Wrong input, exiting...");
		}
	}
}
