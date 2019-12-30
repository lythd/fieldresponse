package data;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import UI.UI;

import static helpers.Artist.*;


public class MainMenu {

	private Texture background;
	private UI menuUI;
	private int menu;
	
	public MainMenu() {
		choose();
		menuUI = new UI();
		menuUI.addButton("Single Player", "buttonSP", Width / 2 - 128, (int) (Height * 0.45));
		menuUI.addButton("Multi Player", "buttonMP", Width / 2 - 128, (int) (Height * 0.55f));
		menuUI.addButton("Quit", "buttonQuit", Width / 2 - 128, (int) (Height * 0.65f));
	}
	
	private void updateButtons() {
		if (Mouse.isButtonDown(0)) {
			if (menuUI.isButtonClicked("Single Player"))
				StateManager.state = StateManager.SINGLEPLAYER;
			if(menuUI.isButtonClicked("Multi Player"))
				StateManager.state = StateManager.MULTIPLAYER;
			if (menuUI.isButtonClicked("Quit"))
				System.exit(0);
		}
	}
	
	public void update() {
		DrawQuadTex(background, 0, 0, TileWidth * 32, TileHeight * 16);
		menuUI.draw();
		updateButtons();
	}
	
	public void choose() {
		StateManager.state = StateManager.MAINMENU;
		menu = (int) Math.round(Math.random() * 2);
		background = QuickLoad("mainmenu" + menu);
	}
}
