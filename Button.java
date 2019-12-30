package UI;

import static helpers.Artist.DrawQuadTex;

import java.awt.Font;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;

public class Button {

	private String name, text;
	private Texture texture;
	private int x, y, width, height;
	private TrueTypeFont font;
	private Font awtFont;
	
	public Button(String name, Texture texture, int x, int y, int width, int height) {
		this.name = name;
		this.text = "";
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height= height;
		awtFont = new Font("Times New Roman", Font.BOLD, 24);
		font = new TrueTypeFont(awtFont, false);
	}
	
	public Button(String name, String text, Texture texture, int x, int y, int width, int height) {
		this.name = name;
		this.text = text;
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height= height;
		awtFont = new Font("Times New Roman", Font.BOLD, 24);
		font = new TrueTypeFont(awtFont, false);
	}
	
	public Button(String name, Texture texture, int x, int y) {
		this.name = name;
		this.text = "";
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.width = texture.getImageWidth();
		this.height = texture.getImageHeight();
		awtFont = new Font("Times New Roman", Font.BOLD, 24);
		font = new TrueTypeFont(awtFont, false);
	}

	public Button(String name, String text, Texture texture, int x, int y) {
		this.name = name;
		this.text = text;
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.width = texture.getImageWidth();
		this.height = texture.getImageHeight();
		awtFont = new Font("Times New Roman", Font.BOLD, 24);
		font = new TrueTypeFont(awtFont, false);
	}
	
	public void drawString(int x, int y, String text) {
		font.drawString(x, y, text);
	}
	
	public void draw() {
		DrawQuadTex(texture, x, y, width, height);
		drawString(x, y, text);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	
	
}
