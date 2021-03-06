package Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import frameWork.GameObject;
import frameWork.ObjectId;

public class LevelMarker extends GameObject{
	
	public LevelMarker(float x, float y, ObjectId id) {
		super(x, y, id);
	}

	
	
	public void render(Graphics g){
		g.setColor(Color.GREEN);
		g.fillRect((int) x, (int) y, 32, 32);
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	
	public void tick(LinkedList<GameObject> object) {
		
		
	}

}
