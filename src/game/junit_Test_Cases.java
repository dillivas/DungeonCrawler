package game;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import org.junit.jupiter.api.Test;

class junit_Test_Cases {

public Game g = new Game();  
	
	BufferedImage b = new BufferedImage(5,5,5);
	SpriteSheet ss = new SpriteSheet(b);
	Handler handler = new Handler();
	
	Player P = new Player(g.WIDTH*32, g.HEIGHT*32, ID.Player,ss, handler);
	BasicEnemy E = new BasicEnemy(g.WIDTH*32, g.HEIGHT*32, ID.Enemy,ss, handler);
	Ghost Gh = new Ghost(g.WIDTH*32, g.HEIGHT*32, ID.Enemy,ss);
	
	Fireball f = new Fireball(1,1,0,-5,ID.Attack,ss, handler);
	GameObject B = new Fireball(1000,1000,0,-5,ID.Block,ss, handler);
	GameObject L = new Fireball(0,0,0,0,ID.Lava,ss, handler);;
	GameObject tempObject;

	
	@Test
	void fireball_path_test() {
	
		Rectangle R = new Rectangle(1,-4,16,16);
	//	f.handler.object.add(B);
		
		//move fireball one pace
		f.tick();
		
		//make sure fireball is on right path
		assertEquals(R, f.getBounds());
		
		
	}
	
	@Test
	void Player_initializer_test() {
		
		Rectangle R = new Rectangle(g.WIDTH*32,g.HEIGHT*32,32,48);
		
		//assert first key press
		assertEquals('d', P.lastKey);
		//assert player location
		assertEquals(R , P.getBounds());
		
		
		
	}
	
	@Test
	void Player_Move_test() {
		
		//new player location, one pixel right
		Rectangle R = new Rectangle(P.x+1,g.HEIGHT*32,32,48);
		
		P.speedX=1;
		P.tick();
		//player should move right by one pixel
		assertEquals(R , P.getBounds());
		
		Rectangle T = new Rectangle(P.x+5,g.HEIGHT*32,32,48);
		
		P.tick();
		P.tick();
		P.tick();
		P.tick();
		P.tick();
		
		//player should move right by 5 pixels
		assertEquals(R , P.getBounds());
	}
	
	@Test
	void Player_Render_Test() {
		
		
	try {
		//test down render
		g.handler.setDown(true);
		g.render();
		g.handler.setDown(false);
		
		//test up render
		g.handler.setUp(true);
		g.render();
		g.handler.setUp(false);
		
		//test left render
		g.handler.setLeft(true);
		g.render();
		g.handler.setLeft(false);
		
		//test right render 
		g.handler.setRight(true);
		g.render();
		g.handler.setRight(false);
	}
	catch(NullPointerException e) {
		
		fail("Player was not rendered correctly");
		
	}
		
		//last key should be d
		assertEquals('d',P.lastKey);
		
	}
	
	@Test 
	void Player_Enemy_Collision_test(){
		
		Player P = new Player(0, 0, ID.Player,ss, handler);
		BasicEnemy E = new BasicEnemy(0, 0, ID.Enemy, ss, handler);
		
		for(int i = 0; i < 100; i++) {
			P.handler.object.add(E);
			P.tick();
			
			
		}
		
		
		//player health should be zero or less
		assert(HUD.HEALTH<0);
		
	}
	
	@Test
	void BasicEnemy_initializer_test() {
		
		Rectangle R = new Rectangle(g.WIDTH*32,g.HEIGHT*32,32,48);
		
		//assert full health
		assertEquals(100, E.hp);
		//assert player location
		assertEquals(R , P.getBounds());
		
	}
	
	@Test 
	void BasicEnemy_Attack_test() {
		
		
		//hit enemy multiple times with fireball
		for(int i = 0; i <1000 ; i ++) {
			
			Fireball f = new Fireball(E.x,E.y,0,0,ID.Attack,ss, handler);
			E.handler.object.add(f);
			E.tick();
			
		}
		
			
		//enemy hp should be less than zero
		assert(E.hp<=0);
		
	}
	
	
	@Test
	void BasicEnemy_Block_test() {
		
		try {
		for(int i = 0; i <1000 ; i ++) {
		
			E.handler.object.add(B);
			E.tick();
			
		}
		}
		catch(Exception e) {
			
			fail("Enemy passed over boundry.");
			
		}
		
		
	}

	
	
	@Test
	void Ghost_initializer_test() {
		Rectangle R = new Rectangle(20486,16387,32,32);
		
		//move ghost to various parts of the map
		Gh.tick();
		
		Gh.getBounds();
		
		Gh.steps=1200;
		
		Gh.tick();
		
		Gh.steps=1000;
		
		Gh.tick();
		
		Gh.steps=33333;
		
		Gh.tick();
		
		Gh.steps=2000;
		
		Gh.tick();
		
		//make sure ghost is at right location
		assertEquals(R, Gh.getBounds());
		
	}
	
	@Test 
	void keyInput_test() {
		
		
		try {
		KeyInput k = new KeyInput(handler, ss);
		
		k.handler.object.add(P);
		
		//check up key
		KeyEvent key = new KeyEvent(g, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_UP,'Z');
	    g.getKeyListeners()[0].keyPressed(key);
		
		k.keyPressed(key);
		
		k.keyReleased(key);
		
		//check down key
		key = new KeyEvent(g, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_DOWN,'Z');
	    g.getKeyListeners()[0].keyPressed(key);
		
	    k.keyPressed(key);
	    
	    k.keyReleased(key);
		
	    //check left key
		key = new KeyEvent(g, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_LEFT,'Z');
	    g.getKeyListeners()[0].keyPressed(key);
		
	    k.keyPressed(key);
	    
	    k.keyReleased(key);
		
	    //check right key
		key = new KeyEvent(g, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_RIGHT,'Z');
	    g.getKeyListeners()[0].keyPressed(key);
		
	    k.keyPressed(key);
	    
	    k.keyReleased(key);
	    
	    //check space key
		key = new KeyEvent(g, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_SPACE,'Z');
	    g.getKeyListeners()[0].keyPressed(key);
		
	    k.keyPressed(key);
	    
	    k.keyReleased(key);
		
	    //check w key
		key = new KeyEvent(g, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_W,'Z');
	    g.getKeyListeners()[0].keyPressed(key);
		
	    k.keyPressed(key);
	    
	    //check a key
		key = new KeyEvent(g, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_A,'Z');
	    g.getKeyListeners()[0].keyPressed(key);
		
	    k.keyPressed(key);
	    
	    //check s key
		key = new KeyEvent(g, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_S,'Z');
	    g.getKeyListeners()[0].keyPressed(key);
		
	    k.keyPressed(key);
	    
	    //check d key
		key = new KeyEvent(g, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_D,'Z');
	    g.getKeyListeners()[0].keyPressed(key);
		
	    k.keyPressed(key);
	    
	    //check q key
		key = new KeyEvent(g, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_Q,'Z');
	    g.getKeyListeners()[0].keyPressed(key);
		
	    k.keyPressed(key);
	    
	    k.keyPressed(key);
		}
		catch(NullPointerException e) {
			
			fail("A key did not register correctly.");
			
		}
	    
	}
	
	@Test
	void HW_initializer_test() {
		
		//make sure base values are applied
		assertEquals(512,g.HEIGHT);
		assertEquals(640,g.WIDTH);
	}
	
	@Test
	void game_clamp_test() {
		
		//see if right arithmetic is being done for clamps
		int x = g.clamp(1, 1, 10);	
		
		assertEquals(1, x);
		
		
		
	}

    @Test
    void load_test() {
    	
    	
    	Render r = new Render();
    	
    	//try to load in false image 
    	try {
    	r.loadImage("ABCD");
    	}
    	catch(Exception e) {
    		
    		return;
    		
    	}
    	
    	fail("An Image should not have been loaded in.");
    	
    }


}
