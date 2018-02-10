package frameWork;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class GameObject {
	
	
	// because its abstract used protected so that the child objects could use varables
	protected float x,y;
	
	//created by enum to hold objects in the game.
	protected ObjectId id;
	
	//velacity for the game objects
	protected float velX = 0, velY = 0;
	
	protected boolean jumping = true;
	protected boolean falling = false;
	
	
	
	
	public GameObject(float x, float y, ObjectId id){
		this.x = x;
		this.y = y;
		this.id = id;
		
	}

	public abstract void tick(LinkedList<GameObject> object);
	public abstract void render(Graphics g);
	
	//get bounds for the player object
	public abstract Rectangle getBounds();
	
	
	// gitters and setters
	public float getY(){ 
		return y;
	}
	public float getX(){
		return x;
	}
	
	public void setX(float x){
		this.x = x;
	}
	public  void SetY(float y){
		this.y = y;
	}
	
	public  float getVelX(){
		return velX;
	}
	public  float getVelY(){
		return velY;
	}
	
	public  void setVelX(float velX){
		this.velX = velX;
	}
	public  void setVelY(float velY){
		this.velY = velY;
	}
	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public ObjectId getId(){
		return id;
	}
	
	
	
}
