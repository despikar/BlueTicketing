import java.util.*;
import java.io.*;

class developer implements User {
    private String userName;
    private String password;
    private int userType;
	
    // Constructor
    public developer(String userName, String password, int userType) {
        this.userName = userName;
        this.password = password;
        this.userType = userType;
    }

    @Override
    public void performActions() {
        try{
            // Read the entire file into memory
            List<String> lines = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader("developer.txt"))) {
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
					int amount = lines.size()-i;
					System.out.println("There are " + amount +" ticket(s) to respond to");
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
                    System.out.printf("If you want to answer more tickets press 1, if not press 2: ");
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
				System.out.println("No tickets to respond to");
			}

            // Write the modified lines back to the file
            try (PrintWriter writer = new PrintWriter(new FileWriter("developer.txt"))) {
                for (String line : lines) {
                    writer.println(line);
                }
            }

            System.out.println("Exiting...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
