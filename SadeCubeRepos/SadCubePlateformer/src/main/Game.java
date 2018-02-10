package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import Objects.Block;
import Objects.LevelMarker;
import Objects.Player;
import frameWork.KeyListener;
import frameWork.ObjectId;

public class Game extends Canvas implements Runnable{
	
	
	String test = "test";
	
	//started in this class start method. used to tell the program that the game has started
	private boolean running = false;
	private Thread thread;
	
	public static int gameLevel = 1;
	
	
	public static int WIDTH, HEIGHT;
	
	public BufferedImage level = null;
	
	Handler handler;
	Camera camera;
	
	
	//Main method builds window and makes the game object.
	
			public static void main(String[] args){
				//makes window object, passes width, height, title, then creats a new Game object that names after it is passed.
				Window window = new Window(1600,1200,"He is a sad cube",new Game());
			}

	
	
	private void init(){
		
		WIDTH = getWidth();
		HEIGHT = getHeight();
		
		//loads levels
		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/level1.png");

		
		
		camera = new Camera(0,0);
		handler = new Handler(camera);
		
		
		
		handler.LoadImageLevel(level);
		
		
		
		
		//add new objects to the game such as blocks and the player.
		//handler.createLevel();
		
		this.addKeyListener(new KeyListener(handler));
		
	}
	
	
	
	public void run() {
		
		init();
		this.requestFocus();
	 
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		
		
		
		
		while(running){
			//set now = to system time in nano secounds.
			long now = System.nanoTime();
			
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
		
	}// End of run
	
	private void tick(){
		handler.tick();
		
		for(int i = 0; i < handler.object.size(); i++){
			if(handler.object.get(i).getId() == ObjectId.Player){
				camera.tick(handler.object.get(i));
			}
		}
		
		
	}//end of tick
	
	private void render(){
		
		
		//this cuts the fps and redrects the proccesing power to premake otherframes.
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null){
									//how many extra frame you want to process ahead.
			this.createBufferStrategy(3);
			return;
			
		}
		
		//make a graphics object called g
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		
		
		///////////////////////////////////////////
		//place to draw to screen.
		
		//make a black rectangle and then fill the set the size to fill the screen
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		//begining of camera movement, all objects inbetween effected
		g2d.translate(camera.getX(), camera.getY());
		
		handler.render(g);
		
		//end of camera movement
		g2d.translate(-camera.getX(), -camera.getY());
		
		//end of place to draw to screen.
		///////////////////////////////////////////
		
		
		//You only need to dispose of Graphics when you actually create it yourself.
		//disoses of graphics object so it can be remade
		g.dispose();
		//show premade screen
		bs.show();
		
	}//end of render
	
	public synchronized void start(){
		
		//check to make sure game does not start another instence on the off chance that this method gets called agian.
		if (running)
			return;
					
		running = true;
		thread = new Thread(this);
		thread.start();
		
		
	}

}
