package main;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window {

	
	//window for game
	public Window(int wedth,int height,String title, Game game){
		
		
		game.setPreferredSize(new Dimension(wedth, height));
		game.setMaximumSize(new Dimension(wedth, height));
		game.setMinimumSize(new Dimension(wedth, height));
		
		JFrame frame = new JFrame(title);
		
		frame.add(game);
		//pack let the program handle fitting item on the screen.
		frame.pack();
		//closes window when the exit button is hit.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//place window in the middle of the screen.
		frame.setLocationRelativeTo(null);
		//makes it not resizable.
		frame.setResizable(false);
		//make the window visible it is not by defult dont ask me why.
		frame.setVisible(true);
		
		//calls the start method from the game object
		game.start();
		
		
	}
	
	
}
