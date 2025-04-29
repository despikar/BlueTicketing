import java.util.*;


class OTP{
	private String generateOTP(int length){
		Random random = new Random();
		StringBuilder OTP = new StringBuilder();
		for(int i = 0;i<length;i++){
			OTP.append(random.nextInt(10));
		}
		return OTP.toString();
	}
	public boolean validateOTP(String enteredOTP, String generatedOTP) {
            return enteredOTP.equals(generatedOTP);
        }
	public boolean ROTP(){
		Scanner input = new Scanner(System.in);
		// Example usage
        String otp = generateOTP(6);
		System.out.println("Your OTP for account registration is: " + otp);

		// Prompt user to enter OTP
		System.out.print("Enter OTP received: ");
		String enteredOTP = input.nextLine();
		
		// Validate OTP
		if (validateOTP(enteredOTP,otp)) {
			System.out.println("OTP verification successful. Account registered!");
			// Additional code to proceed with account registration
		} else {
			boolean verdic = false;
			int count = 0;
			while(!verdic && count <4){
				System.out.println("Invalid OTP. Registration failed, try again");
				System.out.println("Your OTP for account registration is: " + otp);
				System.out.print("Enter OTP received: ");
				enteredOTP = input.nextLine();
				if(enteredOTP.equals(otp)){
					System.out.println("OTP verification successful. Account registered!");
					verdic = true;
				}
				else{
					int remain = 3-count;
					System.out.println("you have "+remain+" amount of tries left");
				}
				count++;
			}
		}

		//input.close();
		return validateOTP(enteredOTP,otp);
	}
}