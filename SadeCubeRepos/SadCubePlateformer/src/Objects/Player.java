package Objects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import frameWork.GameObject;
import frameWork.ObjectId;
import main.Camera;
import main.Handler;

import java.awt.Color;

public class Player extends GameObject {
	
	private float width = 64, height = 64;
	private float gravity = 0.5f;
	private Handler handler;
	private Camera camera;
	
	//max falling speed
	private final float MAX_FALLING_SPEED = 10;
	
	//made in game init
	public Player(float x, float y, Handler handler, Camera camera, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		this.camera = camera;
	}

	
	public void tick(LinkedList<GameObject> object) {
		
		 
		x += velX;
		y += velY;
		
		//adds up gravity while falling to vely
		if(falling || jumping){
			velY += gravity;
			
			if(velY > MAX_FALLING_SPEED){
				velY = MAX_FALLING_SPEED;
			}
			
		}
		
		collision(object);
	}

	
	public void render(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect((int) x, (int) y, (int) width, (int) height);
		
		Graphics2D g2d = (Graphics2D) g;
		
		//draws bounding boxes
		g.setColor(Color.red);
		g2d.draw(getBounds());
		g.setColor(Color.blue);
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsRight());
		g2d.draw(getBoundsTop());
	}
	
	
	//handlers collision 
	private void collision(LinkedList<GameObject> object){
		for(int i = 0; i < handler.object.size(); i++ ){
			
			GameObject tempObject = handler.object.get(i);
			
			
			
			
			//handles collision
			if(tempObject.getId() == ObjectId.Block)
			{
				//top collision
				if(getBoundsTop().intersects(tempObject.getBounds())){
					y = tempObject.getY() + (height/2);
					velY = 0;
				}
				
				//Right collision
				if(getBoundsRight().intersects(tempObject.getBounds())){
					x = tempObject.getX() - width;
				
				}
				
				//Left collision
				if(getBoundsLeft().intersects(tempObject.getBounds())){
					x = tempObject.getX() + (width - 30);
				
				}
				
				//bottem collision
				if(getBounds().intersects(tempObject.getBounds())){
					y = tempObject.getY() - height;
					velY = 0;
					jumping = false;
					falling = false;
				}else{
					falling = true;
				}
				
			
				
				
					
					
					
				}else if(tempObject.getId() == ObjectId.LevelMarker){
					//Swithing levels
					if (getBounds().intersects(tempObject.getBounds())){
						handler.switchLevel();
						
					}
			}
		}
	}

	
	public Rectangle getBounds() {
		return new Rectangle((int) x + 10, (int) y + 32 , (int) width - 20, (int) height/2);
	}public Rectangle getBoundsLeft() {
		return new Rectangle((int) x, (int) y + 10, (int) width - 54, (int) height - 20);
	}public Rectangle getBoundsRight() {
		return new Rectangle((int) x + 54, (int) y + 10, (int) width - 54, (int) height - 20);
	}public Rectangle getBoundsTop() {
		return new Rectangle((int) x + 10, (int) y, (int) width - 20, (int) height/2);
	}

}
