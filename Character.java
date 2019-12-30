package entities;

import org.newdawn.slick.opengl.Texture;

import helpers.Artist;

public abstract class Character implements Entity {


	public float x,y,xo,yo,speed;
	
	protected boolean overDraw = false;
	protected int anMax,anInd,anLength;
	protected Texture[] textures;
	protected int dirMode;
	protected boolean left = false, right = false;
	
	public boolean locked = true;
	
	protected String anLead;
	
	private boolean toTexture = false;
	
	public Character(float x_, float y_, int anMax_, String anLead_) {
		x = x_;
		y = y_;
		speed = 0;
		anInd = 0;
		anMax = anMax_;
		anLength = 0;
		textures = new Texture[anMax];
		anLead = anLead_;
		dirMode = 0;
		xo = 0f;
		yo = 0f;
		toTexture = true;
	}
	
	public Character(float x_, float y_, float speed_, int anMax_, int anLength_, String anLead_) {
		x = x_;
		y = y_;
		speed = speed_;
		anInd = 0;
		anMax = anMax_;
		anLength = anLength_;
		textures = new Texture[anMax];
		anLead = anLead_;
		for (int i = 0; i < anMax; i++) textures[i] = Artist.QuickLoad(anLead + i);
		dirMode = 0;
		xo = 0f;
		yo = 0f;
	}
	
	public Character(float x_, float y_, float speed_, int anMax_, int anLength_, String anLead_, int dirMode_) {
		x = x_;
		y = y_;
		speed = speed_;
		anInd = 0;
		anMax = anMax_;
		anLength = anLength_;
		anLead = anLead_;
		if(dirMode_ == 0) textures = new Texture[anMax];
		if(dirMode_ == 1 || dirMode_ == 2) textures = new Texture[anMax*2];
		for (int i = 0; i < textures.length; i++) textures[i] = Artist.QuickLoad(anLead + i);
		dirMode = dirMode_;
	}
	
	public void anLead(String nAnLead) {
		if(anLead.equals(nAnLead)) return;
		anLead = nAnLead;
		toTexture = true;
	}
	
	public void anMax(int nAnMax) {
		if(anMax == nAnMax) return;
		anMax = nAnMax;
		textures = new Texture[anMax];
		toTexture = true;
	}
	
	@Override
	public float getX() {
		return x;
	}

	@Override
	public float getY() {
		return y;
	}

	@Override
	public int getWidth() {
		return Artist.TileWidth;
	}

	@Override
	public int getHeight() {
		return Artist.TileHeight;
	}

	@Override
	public void setX(float x) {
		this.x = x;
	}

	@Override
	public void setY(float y) {
		this.y = y;
	}

	@Override
	public void setWidth(int width) {
		//nothing
	}

	@Override
	public void setHeight(int height) {
		//nothing
	}

	@Override
	public void update() {
		if(toTexture) for (int i = 0; i < anMax; i++) textures[i]=Artist.QuickLoad(anLead + i);
		toTexture = false;
		if(anInd >= anMax) anInd = 0;
		if(locked) {
			x = Math.round(x);
			y = Math.round(y);
		} else if(xo != 0 || yo != 0) {
			x += xo;
			y += yo;
			xo = 0;
			yo = 0;
		}
		if(!overDraw) draw(xo,yo);
	}

	@Override
	public void draw(float xoff, float yoff) {
		if(dirMode == 0) Artist.DrawQuadTex(textures[anInd],(x+xoff)*Artist.TileWidth,(y+yoff)*Artist.TileHeight,
				Artist.TileWidth,Artist.TileHeight);
		else if(dirMode == 1) {
			if(right) Artist.DrawQuadTex(textures[anInd+anMax],(x+xoff)*Artist.TileWidth,(y+yoff)*Artist.TileHeight,
					Artist.TileWidth,Artist.TileHeight);
			else Artist.DrawQuadTex(textures[anInd],(x+xoff)*Artist.TileWidth,(y+yoff)*Artist.TileHeight,Artist.TileWidth,
					Artist.TileHeight);
		}
		else if(dirMode == 2) {
			if(left) Artist.DrawQuadTex(textures[anInd+anMax],(x+xoff)*Artist.TileWidth,(y+yoff)*Artist.TileHeight,
					Artist.TileWidth,Artist.TileHeight);
			else Artist.DrawQuadTex(textures[anInd],(x+xoff)*Artist.TileWidth,(y+yoff)*Artist.TileHeight,
					Artist.TileWidth,Artist.TileHeight);
		}
	}
	
	public void draw(int ind) {
		if(ind >= textures.length || ind < 0) ind = 0;
		Artist.DrawQuadTex(textures[ind],(x+xo)*Artist.TileWidth,(y+yo)*Artist.TileHeight,
				Artist.TileWidth,Artist.TileHeight);
	}
	
}
