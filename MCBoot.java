package data;

import static helpers.Artist.BeginSession;
import static helpers.Artist.UpdateDisplay;

import org.lwjgl.opengl.Display;
public class MCBoot {
	
	public MCBoot() {
		//Makes screen and sets it up
		BeginSession("Field Response");
		StateManager.init();
		//Update everything repeatedly until you close the window
		while(!Display.isCloseRequested()) {
			StateManager.update();
			UpdateDisplay();
		}
		StateManager.mp.shutdown();
		Display.destroy();
	}
	
	public static void main(String[] args) {
		new MCBoot();
	}
}