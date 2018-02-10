package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import Objects.Block;
import Objects.LevelMarker;
import Objects.Player;
import frameWork.GameObject;
import frameWork.ObjectId;

public class Handler {
	
	private Camera camera;
	private BufferedImage level2 = null;

	// crate a linked list that holds gameObjects
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	
	//used for temparary objects for passing to the tick method.
	private GameObject tempObject;
	
	public Handler(Camera camera){
		this.camera = camera;
		
		BufferedImageLoader loader = new BufferedImageLoader();
	
		level2 = loader.loadImage("/level2.png");
	}
	
	//tick all objects in game
	public void tick(){
		
		//going trough all abjects and ticking them
		for(int i = 0; i < object.size(); i++){
			
							//object = id in linked list
			tempObject = object.get(i);
			
			
			//passes in the object with that id to be ticked
			tempObject.tick(object);
			
		}
		
	}
	
	
	//renders all objects in game
	public void render(Graphics g){
		
		for(int i = 0; i < object.size(); i++){
			
			tempObject = object.get(i);
			
			
			tempObject.render(g);
			
		}
		
	}
	
	private void clearLevel(){
		object.clear();
	}
	
	public void switchLevel(){
		clearLevel();
		camera.setX(0);
		
		switch(Game.gameLevel){
		
		case 1:
			LoadImageLevel(level2);
		}
		
		Game.gameLevel++;
		
	}
	
	public void LoadImageLevel(BufferedImage image){
		int w = image.getWidth();
		int h = image.getHeight();
		
		System.out.println("Hight: " + h + " Width: " + w);
		
		for(int xx = 0; xx < h; xx++){
			for(int yy = 0; yy < w; yy++){
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red == 255 && green == 255 && blue == 255) addObject(new Block(xx*32, yy*32, ObjectId.Block));
				if(red == 0 && green == 255 && blue == 0) addObject(new LevelMarker(xx*32, yy*32, ObjectId.LevelMarker));
				//if(red == 0 && green == 0 && blue == 255) addObject(new Player(xx*32, yy*32, handler, ObjectId.Block));
			}
		}
		
				//creates player object
				addObject(new Player(100, 100, this, camera, ObjectId.Player));
	}
	
	/*creates level for game.
	public void createLevel(){
		for(int i = 0; i < Game.WIDTH*2; i += 32){
			addObject(new Block(i, Game.HEIGHT - 32, ObjectId.Block));
		}
		for(int i = 0; i < Game.HEIGHT; i += 32){
			addObject(new Block((Game.WIDTH*2) - 32, i, ObjectId.Block));
		}
		for(int i = 0; i < Game.HEIGHT; i += 32){
			addObject(new Block(0, i, ObjectId.Block));
		}
		for(int i = 500; i < 1000; i += 32){
			addObject(new Block(i, Game.HEIGHT - 164, ObjectId.Block));
		}
	}*/
	
	
	//adds objects to the linkedList that get passed in
	public void addObject(GameObject object){
		this.object.add(object);
	}
	
	//remove object from linkedlist
	public void removeObject(GameObject object){
		this.object.remove(object);
	}
	
}
