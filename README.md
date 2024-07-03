# Hangman Game

This is a Hangman game implemented in Java Swing using Maven 3.8.1 and Java 18. The game allows players to guess letters to uncover a hidden word within a certain number of attempts.

## Installation

To run the Hangman game, make sure you have Maven 3.8.1 and Java 18 installed on your system. Then, follow these steps:

1. Clone the repository to your local machine.
2. Open a terminal or command prompt and navigate to the project directory.
3. Run the following command to build the project:
   
   mvn clean install
   
4. Once the build is successful, run the following command to start the game:
   
   mvn exec:java -Dexec.mainClass="com.dang.man.Main"
   

## Usage

The Hangman game consists of the following classes:

1. Hangman.java: This class contains the main user interface and provides the main methods that handle the game's functionality.

2. Constants.java: This class holds constant values such as colors and dimensions used throughout the game.

3. Words.java: This class is responsible for reading a text file and setting the word for the game.

4. CustomUtil.java: This class contains basic methods used in the game, such as loading the word and reading the images.

5. Main.java: This class initiates the graphical user interface (GUI) for the game.

To play the game, follow the on-screen instructions. The main UI will display the hangman image, category label, hidden word label, result label, and letter buttons. Guess letters by clicking on the corresponding buttons. The hangman image will update based on the number of incorrect guesses. The game will end when the word is guessed correctly or when the hangman is fully displayed.
