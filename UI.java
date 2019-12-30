package UI;

import java.awt.Font;
import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.TrueTypeFont;

import static helpers.Artist.*;

public class UI {

	private ArrayList<Button> buttonList;
	private ArrayList<Menu> menuList;
	private TrueTypeFont font, conFont;
	private Font awtFont, conAwtFont;
	
	public UI() {
		buttonList = new ArrayList<Button>();
		menuList = new ArrayList<Menu>();
		awtFont = new Font("Times New Roman", Font.BOLD, 24);
		conAwtFont = new Font("Times New Roman", Font.BOLD, 12);
		font = new TrueTypeFont(awtFont, false);
		conFont = new TrueTypeFont(conAwtFont, false);
	}
	
	public void drawString(int x, int y, String text) {
		font.drawString(x, y, text);
	}
	
	public void print(int x, int y, String text) {
		conFont.drawString(x, y, text);
	}
	
	public boolean isButtonClicked(String buttonName) {
		Button b = getButton(buttonName);
		float mouseY = Height - Mouse.getY() - 1;
		if (Mouse.getX() > b.getX() && Mouse.getX() < b.getX() + b.getWidth() &&
				mouseY > b.getY() && mouseY < b.getY() + b.getHeight()) {
			return true;
		}
		return false;
	}
	
	private Button getButton(String buttonName) {
		for (Button b: buttonList) {
			if (b.getName().equals(buttonName)) {
				return b;
			}
		}
		return null;
	}
	
	public void addButton(String name, String textureName, int x, int y) {
		buttonList.add(new Button(name, QuickLoad(textureName), x, y));
	}
	
	public void createMenu(String name, int x, int y, int width, int height, int optionsWidth, int optionsHeight) {
		menuList.add(new Menu(name, x, y, width, height, optionsWidth, optionsHeight));
	}
	
	public void createMenu(String name, int x, int y, int width, int height, int buttonWidth, int buttonHeight, int optionsWidth, int optionsHeight) {
		menuList.add(new Menu(name, x, y, width, height, buttonWidth, buttonHeight, optionsWidth, optionsHeight));
	}
	
	public Menu getMenu(String name) {
		for(Menu m: menuList) if(name.equals(m.getName())) return m;
		return null;
	}
	
	public void draw() {
		for (Button b: buttonList) b.draw();;
		for (Menu m: menuList) m.draw();
	}
	
	public class Menu {
		
		private ArrayList<Button> menuButtons;
		private int x, y, width, height, buttonWidth, buttonHeight, buttonAmount, optionsWidth, optionsHeight, xPadding, yPadding;
		private boolean useBWH;
		String name, text;
		
		public Menu(String name, int x, int y, int width, int height, int optionsWidth, int optionsHeight) {
			this.name = name;
			this.text = "";
			this.x = x;
			this.y = y;
			this.setWidth(width);
			this.setHeight(height);
			this.optionsWidth = optionsWidth;
			this.setOptionsHeight(optionsHeight);
			this.xPadding = (width - (optionsWidth * buttonWidth)) / (optionsWidth + 1);
			if(optionsHeight != 0) this.setyPadding((height - (optionsHeight * buttonHeight)) / (optionsHeight + 1));
			else this.setyPadding(buttonHeight/4);
			this.buttonAmount = 0;
			this.menuButtons = new ArrayList<Button>();
			this.buttonWidth = TileWidth;
			this.buttonHeight = TileHeight;
		}
		
		public Menu(String name, String text, int x, int y, int width, int height, int optionsWidth, int optionsHeight) {
			this.name = name;
			this.text = text;
			this.x = x;
			this.y = y;
			this.setWidth(width);
			this.setHeight(height);
			this.optionsWidth = optionsWidth;
			this.setOptionsHeight(optionsHeight);
			this.xPadding = (width - (optionsWidth * buttonWidth)) / (optionsWidth + 1);
			if(optionsHeight != 0) this.setyPadding((height - (optionsHeight * buttonHeight)) / (optionsHeight + 1));
			else this.setyPadding(buttonHeight/4);
			this.buttonAmount = 0;
			this.menuButtons = new ArrayList<Button>();
			this.buttonWidth = TileWidth;
			this.buttonHeight = TileHeight;
		}
		
		public Menu(String name, int x, int y, int width, int height, int buttonWidth, int buttonHeight, int optionsWidth, int optionsHeight) {
			this.name = name;
			this.text = "";
			this.x = x;
			this.y = y;
			this.setWidth(width);
			this.setHeight(height);
			this.optionsWidth = optionsWidth;
			this.setOptionsHeight(optionsHeight);
			this.xPadding = (width - (optionsWidth * buttonWidth)) / (optionsWidth + 1);
			if(optionsHeight != 0) this.setyPadding((height - (optionsHeight * buttonHeight)) / (optionsHeight + 1));
			else this.setyPadding(buttonHeight/4);
			this.buttonAmount = 0;
			this.menuButtons = new ArrayList<Button>();
			this.buttonWidth = buttonWidth;
			this.buttonHeight = buttonHeight;
			useBWH = true;
		}
		
		public Menu(String name, String text, int x, int y, int width, int height, int buttonWidth, int buttonHeight, int optionsWidth, int optionsHeight) {
			this.name = name;
			this.text = text;
			this.x = x;
			this.y = y;
			this.setWidth(width);
			this.setHeight(height);
			this.optionsWidth = optionsWidth;
			this.setOptionsHeight(optionsHeight);
			this.xPadding = (width - (optionsWidth * buttonWidth)) / (optionsWidth + 1);
			if(optionsHeight != 0) this.setyPadding((height - (optionsHeight * buttonHeight)) / (optionsHeight + 1));
			else this.setyPadding(buttonHeight/4);
			this.buttonAmount = 0;
			this.menuButtons = new ArrayList<Button>();
			this.buttonWidth = buttonWidth;
			this.buttonHeight = buttonHeight;
			useBWH = true;
		}
		
		public void addButton(Button b) {
			setButton(b);
		}
		
		public void quickAdd(String bName, String tName) {
			if(useBWH) {
				Button b = new Button(bName, QuickLoad(tName), 0, 0, buttonWidth, buttonHeight);
				setButton(b);
			} else {
			Button b = new Button(bName, QuickLoad(tName), 0, 0);
			setButton(b);
			}
		}
		
		public void quickAdd(String bName, String bText, String tName) {
			if(useBWH) {
				Button b = new Button(bName, bText, QuickLoad(tName), 0, 0, buttonWidth, buttonHeight);
				setButton(b);
			} else {
			Button b = new Button(bName, bText, QuickLoad(tName), 0, 0);
			setButton(b);
			}
		}
		
		private void setButton(Button b) {
			//if(optionsHeight != 0) b.setY(y + (buttonAmount / optionsHeight) * buttonHeight);
			b.setY(y + (buttonAmount / optionsWidth) * (yPadding + buttonHeight) + yPadding);
			b.setX(x + (buttonAmount % optionsWidth) * (xPadding + buttonWidth) + xPadding);
			buttonAmount++;
			menuButtons.add(b);
		}
		
		public void draw() {
			for (Button b: menuButtons) b.draw();
		}
		
		public String getName() {
			return name;
		}
		
		public boolean isButtonClicked(String buttonName) {
			Button b = getButton(buttonName);
			float mouseY = Height - Mouse.getY() - 1;
			if (Mouse.getX() > b.getX() && Mouse.getX() < b.getX() + b.getWidth() &&
					mouseY > b.getY() && mouseY < b.getY() + b.getHeight()) {
				return true;
			}
			return false;
		}
		
		private Button getButton(String buttonName) {
			for (Button b: menuButtons) {
				if (b.getName().equals(buttonName)) {
					return b;
				}
			}
			return null;
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
		public int getOptionsHeight() {
			return optionsHeight;
		}
		public void setOptionsHeight(int optionsHeight) {
			this.optionsHeight = optionsHeight;
		}
		public int getyPadding() {
			return yPadding;
		}
		public void setyPadding(int yPadding) {
			this.yPadding = yPadding;
		}
	}
	
}
