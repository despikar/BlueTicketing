# BlueTicketing 🎫

**BlueTicketing** is a Java-based ticketing system designed for an FPS game. Developed as a group project for a Software Engineering course, it allows players to submit feedback, developers to manage issues, and support teams to assist users efficiently.

## 🧩 Features

- 🔐 OTP-based login system  
- 👤 Role-based user access: Admin, Developer, Player, Customer Support  
- 📝 Feedback and questions submission  
- 📁 Text-file based data storage for lightweight deployments  
- 🖥️ Command-line interaction

## 📂 Project Structure

BlueTicketing/
├── src/                 # Java source files and text data  
│   ├── Admin.java  
│   ├── customersupport.java  
│   ├── developer.java  
│   ├── OTP.java  
│   ├── Player.java  
│   ├── User.java  
│   ├── main.java  
│   ├── *.txt            # Includes feedback, questions, and user data  
├── executable/  
│   └── BlueTicketing.jar  
└── README.md

## 🚀 How to Run

1. Navigate to the `executable/` folder in your terminal.  
2. Run the following command:

   java -jar BlueTicketing.jar

> Make sure you have **Java 21 or higher** installed. You can check using `java -version`.

## 👨‍💻 Authors

- Despina Karatziou  
- Aggelos Michalopoulos 

## 📄 License

This project is intended for educational purposes only.  

## 🧠 Future Improvements

- Migrate from plain text files to a database (e.g., SQLite or MySQL)  
- Add a user-friendly GUI using JavaFX or Swing  
- Implement ticket status tracking and filtering  
- Add unit testing using JUnit  
- Encrypt stored data for better security

Feel free to fork the repository or open an issue if you have suggestions!
