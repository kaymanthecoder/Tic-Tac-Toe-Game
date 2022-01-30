import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TicTacToe implements ActionListener{
	
	//---------------------------------------
	//For Game
	JFrame game = new JFrame();
	JPanel title_panel = new JPanel();
	JPanel button_panel = new JPanel();
	JLabel text_field = new JLabel();
	JLabel name = new JLabel();
	JButton resetGame = new JButton("Reset");
	//[ ] is used because it is an array  
	JButton[ ] buttons = new JButton[9];
	boolean playerTurn;
	
	String player1;
	String player2;
	
	//---------------------------------------
	//For Main menu
	JLabel gameName = new JLabel("Tic-Tac-Toe game");
	JLabel player1Label = new JLabel("Player 1 Name: ");
	JLabel player2Label = new JLabel("Player 2 Name: ");
	JButton theButton = new JButton("Login");
	JTextField player1Field = new JTextField();
	JTextField player2Field = new JTextField();
	JFrame mainMenu = new JFrame();
	JButton resetButton = new JButton("Reset");
	
	//---------------------------------------
	
	//Creating the constructor of this class
	TicTacToe(){
		//--------------------------------------------------------------------
		//Main Menu
		
		// This sets the dimensions (x, y, width, height)
		gameName.setBounds(35,-15, 2200, 220);
		gameName.setFont(new Font("Microsoft JhengHei", Font.BOLD, 60));
		
		// I put 2200 because it would show up as "Nam....." instead of "Name: "
		player1Label.setBounds(50,100, 2200, 220);
		player1Label.setFont(new Font("Microsoft JhengHei", Font.BOLD, 25));
		
		player2Label.setBounds(50, 170, 2200, 220);
		player2Label.setFont(new Font("Microsoft JhengHei", Font.BOLD, 25));
		
		// This is where the players enter their names
		player1Field.setBounds(250, 189, 300, 50);
		player2Field.setBounds(250, 259, 300, 50);
		
		//The font for the text that is entered in the text field
		player1Field.setFont(new Font("Microsoft JhengHei", Font.LAYOUT_LEFT_TO_RIGHT, 25));
		player2Field.setFont(new Font("Microsoft JhengHei", Font.LAYOUT_LEFT_TO_RIGHT, 25));

		
		// Login Button
		// This will submit the information when the button is clicked
		theButton.setBounds(250, 330, 140, 60);
	    // This removes the border around the words that is inside the button
		theButton.setFocusable(false);
		// This is the action listener for the login button
		theButton.addActionListener(this);
		// Reset Button
		// This will submit the information when the button is clicked
	    resetButton.setBounds(410, 330, 140, 60);
	    resetButton.setFocusable(false);
		// This is the action listener for the reset button
		resetButton.addActionListener(this);
		//This displays the application
		mainMenu.add(gameName);
		mainMenu.add(player1Label);
		mainMenu.add(player2Label);
		mainMenu.add(player1Field);
		mainMenu.add(player2Field);
		mainMenu.add(theButton);
		mainMenu.add(resetButton);
		mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainMenu.setSize(600, 600);
		mainMenu.setLayout(null);
		mainMenu.setVisible(true);
		
		//---------------------------------------------------------------------
		
		//The game
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setSize(600, 600);
		game.getContentPane().setBackground(new Color(50, 50, 50));
		game.setLayout(new BorderLayout());
		game.setVisible(false);
		
		//The text field
		text_field.setBackground(new Color(25, 25, 25));
		text_field.setForeground(new Color(25, 255, 0));
		text_field.setFont(new Font("Ink Free", Font.BOLD, 75));
		text_field.setHorizontalAlignment(JLabel.CENTER); //Border Layout
		text_field.setOpaque(true);
		
		//The title panel
		title_panel.setLayout(new BorderLayout());
		title_panel.setBounds(0, 0, 800, 100);
		
		//Button panel
		button_panel.setLayout(new GridLayout(3, 3));
		button_panel.setBackground(new Color(150, 150, 150));
		
		//Reset Game
		resetGame.setBounds(0, 0, 140, 60);
	    resetGame.setFocusable(false);
		// This is the action listener for the reset button
		resetGame.addActionListener(this);
		
		//X or O that is entered in the panel
		for(int i=0; i<9; i++) {
			buttons[i] = new JButton();
			button_panel.add(buttons[i]);
			buttons[i].setFont(new Font("MV boli", Font.BOLD, 120));
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this);
		}
		
		//Add text field to the title panel
		title_panel.add(text_field);
		//Used border layout to make title panel to stick to the top
		game.add(title_panel, BorderLayout.NORTH);
		game.add(button_panel);
		game.add(resetGame, BorderLayout.SOUTH);
		
		firstTurn();
	}
	
	//Need to utilize the action performed method because of action listener
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// When the reset button is clicked on, every text in the user name and password will be erased
		if(e.getSource()==resetButton) {
			player1Field.setText("");
			player2Field.setText("");
		}
		
		// When the login button is clicked on
		if(e.getSource()==theButton) {
			player1 = player1Field.getText();
			player2 = player2Field.getText();
			
			//Getting rid of the main menu once login has been entered
		    mainMenu.setVisible(false);
		    //Making the game appear
		    game.setVisible(true);
		}
		
		//When the reset button during the game has been clicked, it will reset
		if(e.getSource()==resetGame) {
			
			//This clears all of the panels
			for(int i=0; i<9; i++) {
				buttons[i].setText("");
				buttons[i].setEnabled(true);
				firstTurn();
				
			}
			
		}
		
		//This will run 9 times whenever a button is clicked
		for(int i=0; i<9; i++) {
			//Check each of the nine buttons
			if(e.getSource()==buttons[i]) {
				//X's turn
				if(playerTurn) {
					//Checking if there is words in button
					if(buttons[i].getText()=="") {
						buttons[i].setForeground(Color.RED);
						buttons[i].setText("X");
						//Change player's turn
						playerTurn = false;
						//Make it O's turn
						text_field.setText(player2 + "'s turn");
						text_field.setForeground(Color.BLUE);
						check();
					}
					
				}
				//O's turn
				else {
					if(buttons[i].getText()=="") {
						buttons[i].setForeground(new Color(0, 0, 255));
						buttons[i].setText("O");
						//Change player's turn
						playerTurn = true;
						//Make it X's turn
						text_field.setText(player1 +"'s turn");
						text_field.setForeground(Color.RED);
						check();
						}
					}
				}
			}
		}
	
	//To determine who will go first. The player or the computer
	public void firstTurn() {
		
		Random goesFirst = new Random();
		if(goesFirst.nextInt(2)==0) {
			playerTurn = true;
			text_field.setText("player 1 go first");
		}
		else {
			playerTurn = false;
			text_field.setText("player 2 go first");
		}
	}
	
	//Check winning conditions or who won.
	public void check() {
		//Check X win conditions
		
		//3 in 1st row
		if((buttons[0].getText()=="X") && 
		   (buttons[1].getText()=="X") &&
		   (buttons[2].getText()=="X")) {
			//This is the winning combinations
			xWins(0, 1, 2);
		}
		
		//3 in 2nd row
		if((buttons[3].getText()=="X") && 
		   (buttons[4].getText()=="X") &&
		   (buttons[5].getText()=="X")) {
			//This is the winning combinations
			xWins(3, 4, 5);
		}
		
		//3 in 3rd row
		if((buttons[6].getText()=="X") && 
		   (buttons[7].getText()=="X") &&
		   (buttons[8].getText()=="X")) {
			//This is the winning combinations
			xWins(6, 7, 8);
			}
		
		//3 in 1st column
		if((buttons[0].getText()=="X") && 
		   (buttons[3].getText()=="X") &&
		   (buttons[6].getText()=="X")) {
			//This is the winning combinations
			xWins(0, 3, 6);
		}
		
		//3 in 2nd column
		if((buttons[1].getText()=="X") && 
		   (buttons[4].getText()=="X") &&
		   (buttons[7].getText()=="X")) {
			//This is the winning combinations
			xWins(1, 4, 7);
		}
		
		//3 in 3rd column
		if((buttons[2].getText()=="X") && 
		   (buttons[5].getText()=="X") &&
		   (buttons[8].getText()=="X")) {
			//This is the winning combinations
			xWins(2, 5, 8);
			}
		
		//3 from top left to bottom right
		if((buttons[0].getText()=="X") && 
		   (buttons[4].getText()=="X") &&
		   (buttons[8].getText()=="X")) {
			//This is the winning combinations
			xWins(0, 4, 8);
		}
		
		//3 from top right to bottom left
		if((buttons[2].getText()=="X") && 
		   (buttons[4].getText()=="X") &&
		   (buttons[6].getText()=="X")) {
			//This is the winning combinations
			xWins(2, 4, 6);
			}
		
//----------------------------------------------------------//
		
		//Check O win conditions
		
		//3 in 1st row
		if((buttons[0].getText()=="O") && 
		   (buttons[1].getText()=="O") &&
		   (buttons[2].getText()=="O")) {
			//This is the winning combinations
			oWins(0, 1, 2);
		}
		
		//3 in 2nd row
		if((buttons[3].getText()=="O") && 
		   (buttons[4].getText()=="O") &&
		   (buttons[5].getText()=="O")) {
			//This is the winning combinations
			oWins(3, 4, 5);
		}
		
		//3 in 3rd row
		if((buttons[6].getText()=="O") && 
		   (buttons[7].getText()=="O") &&
		   (buttons[8].getText()=="O")) {
			//This is the winning combinations
			oWins(6, 7, 8);
			}
		
		//3 in 1st column
		if((buttons[0].getText()=="O") && 
		   (buttons[3].getText()=="O") &&
		   (buttons[6].getText()=="O")) {
			//This is the winning combinations
			oWins(0, 3, 6);
		}
		
		//3 in 2nd column
		if((buttons[1].getText()=="O") && 
		   (buttons[4].getText()=="O") &&
		   (buttons[7].getText()=="O")) {
			//This is the winning combinations
			oWins(1, 4, 7);
		}
		
		//3 in 3rd column
		if((buttons[2].getText()=="O") && 
		   (buttons[5].getText()=="O") &&
		   (buttons[8].getText()=="O")) {
			//This is the winning combinations
			oWins(2, 5, 8);
			}
		
		//3 from top left to bottom right
		if((buttons[0].getText()=="O") && 
		   (buttons[4].getText()=="O") &&
		   (buttons[8].getText()=="O")) {
			//This is the winning combinations
			oWins(0, 4, 8);
		}
		
		//3 from top right to bottom left
		if((buttons[2].getText()=="O") && 
		   (buttons[4].getText()=="O") &&
		   (buttons[6].getText()=="O")) {
			//This is the winning combinations
			oWins(2, 4, 6);
			}
	}
	
	//a, b, & c is the winning combination
	//It changes the background of the combination
	public void xWins(int a, int b, int c) {
		//So that the player cannot continue to select buttons
		for(int i=0; i<9; i++) {
			buttons[i].setEnabled(false);
		}
		text_field.setText(player1 + ": X wins!!!");
		text_field.setForeground(Color.RED);
	}
	
    public void oWins(int a, int b, int c) {
		//So that the player cannot continue to select buttons
		for(int i=0; i<9; i++) {
			buttons[i].setEnabled(false);
		}
		text_field.setText(player2 + ": O wins!!!");
		text_field.setForeground(Color.BLUE);
	}
}
