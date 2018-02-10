package frameWork;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import main.Handler;





public class KeyListener extends KeyAdapter{
	
	Handler handler;
	
	private boolean[] keyDown = new boolean[4];
	
	public KeyListener(Handler handler){
		this.handler = handler;
		
		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
	}

	
	
	
	
	
	public void keyPressed(KeyEvent e){
		//key presses get changed to asky code they = an int
		int key = e.getKeyCode();
		
		//cicle though all game objects looking for player then exicute following code.
		for(int i = 0; i < handler.object.size(); i++){
			
			//make a tempary holding cell for objects that = the id in i
			GameObject tempObject = handler.object.get(i);
			
			//find player then exicutes following code for object
			if(tempObject.getId() == ObjectId.Player){
				//hendler movement right
				if(key == KeyEvent.VK_D) {tempObject.setVelX(5); keyDown[0] = true;}
				if(key == KeyEvent.VK_RIGHT) {tempObject.setVelX(5); keyDown[2] = true;}
				
				//handlers left movement
				if(key == KeyEvent.VK_A) {tempObject.setVelX(-5); keyDown[1] = true;}
				if(key == KeyEvent.VK_LEFT) {tempObject.setVelX(-5); keyDown[3] = true;}
				
				//handles jumping 
				
			}
			
			
		}
		
		
		
		
		if(key == KeyEvent.VK_ESCAPE){
			System.exit(1);
		}
		
		
	}
	
	public void keyReleased(KeyEvent e){
		//key presses get changed to asky code they = an int
		int key = e.getKeyCode();
		
		//cicle though all game objects looking for player then exicute following code.
		for(int i = 0; i < handler.object.size(); i++){
			
			//make a tempary holding cell for objects that = the id in i
			GameObject tempObject = handler.object.get(i);
			
			//find player then exicutes following code for object
			if(tempObject.getId() == ObjectId.Player){
				//hendler movement right
				if(key == KeyEvent.VK_D) keyDown[0] = false;//tempObject.setVelX(0);
				if(key == KeyEvent.VK_RIGHT) keyDown[2] = false; //tempObject.setVelX(0);
				
				//handlers left movement
				if(key == KeyEvent.VK_A) keyDown[1] = false;//tempObject.setVelX(0);
				if(key == KeyEvent.VK_LEFT) keyDown[3] = false;//tempObject.setVelX(0);
				
				//handles jumping 
				if(key == KeyEvent.VK_SPACE  && !tempObject.isJumping()){
					tempObject.setJumping(true);
					tempObject.setVelY(-15);
				}
				if(key == KeyEvent.VK_UP && !tempObject.isJumping()){
					tempObject.setJumping(true);
					tempObject.setVelY(-15);
				}
				if(key == KeyEvent.VK_W && !tempObject.isJumping()){
					tempObject.setJumping(true);
					tempObject.setVelY(-15);
				}
				
				if(!keyDown[1] && !keyDown[0] && !keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
				
			}

		}
	
	}
}
