package entities;

import org.lwjgl.input.Keyboard;
import helpers.Artist;
import helpers.Clock;
import tile.TileGrid;

public class Player extends Character {

	private TileGrid grid;
	public boolean lr=false;
	private boolean ll=false;
	private boolean lu=false;
	private boolean ld=false;
	
	public Player(float x, float y, float speed, int anMax, int anLength, String anLead, TileGrid grid_) {
		super(x,y,speed,anMax,anLength,anLead);
		grid = grid_;
	}
	
	public Player(float x, float y, float speed, int anMax, int anLength, String anLead, int dirMode, TileGrid grid_) {
		super(x,y,speed,anMax,anLength,anLead,dirMode);
		grid = grid_;
	}
	
	@Override
	public void update() {
		super.update();
		if(Keyboard.isKeyDown(Keyboard.KEY_W) && !ld  && !lu) {
			if(Artist.debug || (grid.getTile(Math.round(x+0.4f),Math.round(y+0.4f)-1).getType().passable &&
					grid.getTile(Math.round(x-0.4f),Math.round(y+0.4f)-1).getType().passable)) {
				if(locked) yo -= Clock.Delta() * speed;
				else y -= Clock.Delta() * speed;
				if(System.currentTimeMillis()%(anLength*100)<Clock.Delta()*1000) anInd++;
				if(locked) {
					lu=true;
					y--;
					yo++;
				}
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A) && !lr && !ll) {
			if(Artist.debug || (grid.getTile(Math.round(x+0.4f)-1,Math.round(y+0.4f)).getType().passable && 
					grid.getTile(Math.round(x+0.4f)-1,Math.round(y-0.4f)).getType().passable)) {
				if(locked) xo -= Clock.Delta() * speed;
				else x -= Clock.Delta() * speed;
				left = true;
				right = false;
				if(System.currentTimeMillis()%(anLength*100)<Clock.Delta()*1000) anInd++;
				if(locked) {
					ll=true;
					x--;
					xo++;
				}
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S) && !ld && !lu) {
			if(Artist.debug || (grid.getTile(Math.round(x+0.4f),Math.round(y-0.4f)+1).getType().passable && 
					grid.getTile(Math.round(x-0.4f),Math.round(y-0.4f)+1).getType().passable)) {
				if(locked) yo += Clock.Delta() * speed;
				else y += Clock.Delta() * speed;
				if(System.currentTimeMillis()%(anLength*100)<Clock.Delta()*1000) anInd++;
				if(locked) {
					ld=true;
					y++;
					yo--;
				}
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D) && !lr && !ll) {
			if(Artist.debug || (grid.getTile(Math.round(x-0.4f)+1,Math.round(y+0.4f)).getType().passable && 
					grid.getTile(Math.round(x-0.4f)+1,Math.round(y-0.4f)).getType().passable)) {
				if(locked) xo += Clock.Delta() * speed;
				else x += Clock.Delta() * speed;
				left = false;
				right = true;
				if(System.currentTimeMillis()%(anLength*100)<Clock.Delta()*1000) anInd++;
				if(locked) {
					lr=true;
					x++;
					xo--;
				}
			}
		}
		boolean l = true;
		if(lr) {
			if(!locked) lr = false;
			if(xo <= 0f) {
				xo += speed * Clock.Delta();
				l = false;
			}
			else {
				xo = 0f;
				lr = false;
			}
			if(System.currentTimeMillis()%(anLength*100)<Clock.Delta()*1000) anInd++;
		}
		if(ll) {
			if(!locked) ll = false;
			if(xo >= 0f) {
				xo -= speed * Clock.Delta();
				l = false;
			}
			else {
				xo = 0f;
				ll = false;
			}
			if(System.currentTimeMillis()%(anLength*100)<Clock.Delta()*1000) anInd++;
		}
		if(ld) {
			if(!locked) ld = false;
			if(yo <= 0f) {
				yo += speed * Clock.Delta();
				l = false;
			}
			else {
				yo = 0f;
				ld = false;
			}
			if(System.currentTimeMillis()%(anLength*100)<Clock.Delta()*1000) anInd++;
		}
		if(lu) {
			if(!locked) lu = false;
			if(yo >= 0f) {
				yo -= speed * Clock.Delta();
				l = false;
			}
			else {
				yo = 0f;
				lu = false;
			}
			if(System.currentTimeMillis()%(anLength*100)<Clock.Delta()*1000) anInd++;
		}
		if(l) {
			while(xo>=1) {
				xo--;
				x++;
			}
			while(xo<0) {
				xo++;
				x--;
			}
			while(yo>=1) {
				yo--;
				y++;
			}
			while(yo<0) {
				yo++;
				y--;
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) && Keyboard.next()) super.locked = !super.locked;
	}
	
}
