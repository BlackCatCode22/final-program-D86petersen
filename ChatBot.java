
// import gui stuff
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
 //import colors
import java.awt.Color;
// import key listener
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
//import math library
import java.lang.Math;
// created class that implements the gui and key listener
public class ChatBot extends JFrame implements KeyListener{

	// created gui objects

	// create a panel
	JPanel p = new JPanel();
	// create a text area for the dialog
	JTextArea dialog = new JTextArea(20,50);
	// create a text area for the input
	JTextArea input = new JTextArea(1,50);
	// create a scroll pane for the dialog
	JScrollPane scroll = new JScrollPane(
		dialog,
		// create a scroll bar for the dialog
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
	);
	// create a 2d array of strings to hold the chat-bot questions and responses
	String[][] chatBot = {
		//standard greetings
		{"hi","hello","hola","ola","howdy"},
		{"hi","hello","hey"},
		//question greetings
		{"how are you","how r you","how r u","how are u"},
		{"good","doing well"},
		//yes
		{"yes"},
		{"no","NO","NO!!!!!!!"},
		//default
		{"Please repeat that","What did you say?","try again","huh?",
		"(Chatty does not understand!)"}
	};

	// created main method to run the program and create the chat-bot object
	public static void main(String[] args){

		new ChatBot();
	}

	// chat-bot object
	public ChatBot() {
		// set the title of the window
		super("Chatty the Chat Bot");
		// set the size of the window
		setSize(600,400);
		// set the window to not be resizable
		setResizable(false);
		// set the default close operation
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// set the dialog to not be editable
		dialog.setEditable(false);
		// set a key listener for the input
		input.addKeyListener(this);
		// add the scroll pane and input to the panel
		p.add(scroll);
		// add the input to the panel
		p.add(input);
		// set the background color for the panel
		p.setBackground(new Color(0, 187, 255));
		// add the panel to the window
		add(p);
		// add a default greeting to the dialog so when the program is run it will greet the user
		addText("-->Chatty the chat bot: Welcome to the Chat, my name is Chatty\n");
		// set the window to be visible when the program is run
		setVisible(true);

	}

	// method to handle the key pressed event
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			input.setEditable(false);
			//-----grab quote-----------
			// used a getter to grab the text from the input field
			String quote = input.getText();
			// set the input field to be blank after the user presses enter
			input.setText("");
			// add the quote to the dialog
			addText("-->You:\t" + quote);
			// make sure that any white spaces are removed from the quote
			quote.trim();

			while(
				quote.charAt(quote.length() - 1) == '!' ||
				quote.charAt(quote.length() - 1) == '.' ||
				quote.charAt(quote.length() - 1) == '?'
			){

				quote = quote.substring(0,quote.length() - 1);
			}
			quote.trim();
			// set the response to 0
			byte response = 0;

			//-----check for matches----
			int j = 0;//which group we're checking
			// while loop to keep checking for a match until one is found
			while(response == 0){
				// if the quote is in the chatBot array then set the response to 2
				if(inArray(quote.toLowerCase(),chatBot[j * 2])){
					response = 2;
					// get a random number to pick a response from the chatBot array
					int r = (int)Math.floor(Math.random() * chatBot[(j * 2) + 1].length);
					// add the response to the dialog
					addText("\n-->Chatty\t" + chatBot[(j * 2) + 1][r]);
				}
				// increment j
				j++;
				// if j is equal to the length of the chatBot array and the response is still 0 then set the response to 1
				if(j * 2 == chatBot.length - 1 && response == 0){
					response = 1;
				}
			}
			
			//-----default--------------
			if(response == 1){
				// get a random number to pick a response from the chatBot array
				int r = (int)Math.floor(Math.random() * chatBot[chatBot.length - 1].length);
				addText("\n-->Chatty\t"+chatBot[chatBot.length - 1][r]);
			}
			// add a new line to the dialog
			addText("\n");
		}
	}

	// method to handle the key released event
	public void keyReleased(KeyEvent e){
		// if the enter key is released then set the input field to be editable
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			input.setEditable(true);
		}
	}

	// method to handle the key typed event
	public void keyTyped(KeyEvent e){}
	// method to add text to the dialog
	// used a setter to add text to the dialog and getter to get the text from the input field
	public void addText(String str){

		dialog.setText(dialog.getText() + str);
	}

	// method to check if the quote is in the chatBot array
	public boolean inArray(String in,String[] str) {
		// set match to false
		boolean match = false;
		// for loop to check if the quote is in the chatBot array
		for(int i = 0; i < str.length; i++){
			// if the quote is in the chatBot array then set match to true
			if (str[i].equals(in))
			{
				match = true;
				// break out of the loop
				break;
			}
		}
		// return the match
		return match;
	}
}