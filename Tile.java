package tile;

import static helpers.Artist.DrawQuadTex;
import static helpers.Artist.QuickLoad;
import static helpers.Artist.TileWidth;
import static helpers.Artist.TileHeight;

import org.newdawn.slick.opengl.Texture;

public class Tile {

	private float x, y;
	private String texture;
	private Texture loadTex;
	private TileType type;
	private boolean loaded;
	
	public Tile(float x, float y, TileType type) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.texture = type.textureName;
	}
	
	public void draw() {
		if(!loaded) {
			loadTex = QuickLoad(texture);
			loaded = true;
		}
		DrawQuadTex(loadTex, x, y, TileWidth, TileHeight);
	}

	public int getXPlace() {
		return (int) x / TileWidth;
	}
	
	public float getX() {
		return x;
	}

	public int getYPlace() {
		return (int) y / TileHeight;
	}
	
	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public Texture getTexture() {
		return loadTex;
	}

	public void setTexture(Texture tex) {
		loadTex = tex;
	}

	public String getTextureName() {
		return texture;
	}

	public void setTextureName(String texture) {
		this.texture = texture;
	}

	public TileType getType() {
		return type;
	}

	public void setType(TileType type) {
		this.type = type;
	}
}
