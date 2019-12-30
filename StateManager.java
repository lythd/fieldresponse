package data;

import static helpers.Artist.debug;
import static helpers.Artist.hideUI;
import static helpers.Artist.hideChatUI;

import org.lwjgl.input.Keyboard;

import UI.UI;
import helpers.Artist;
import helpers.Clock;

public class StateManager {

	public static int state;

	protected static MainMenu mm;
	protected static SinglePlayer sp;
	protected static MultiPlayer mp;
	
	public static final int MAINMENU = 0, SINGLEPLAYER = 1, MULTIPLAYER = 2;
	
	private static UI infoUI;
	private static UI chatUI;
	private static int curY;
	private static String[] chats = new String[15];
	private static int chatnum = 0;
	
	public static long nextSecond = System.currentTimeMillis() + 1000;
	public static int framesInLastSecond = 0;
	public static int framesInCurrentSecond = 0;
	
	public static void init() {
		state = MAINMENU;
		setupUI();
		mm = new MainMenu();
		sp = new SinglePlayer();
		mp = new MultiPlayer();
	}
	
	public static void update() {
		//update subs
		Clock.update();
		if(state == MAINMENU) mm.update();
		else if(state == SINGLEPLAYER) sp.update();
		else if(state == MULTIPLAYER) mp.update();
		
		//Time Calculations
		
		long currentTime = System.currentTimeMillis();
		if (currentTime > nextSecond) {
			nextSecond += 1000;
			framesInLastSecond = framesInCurrentSecond;
			framesInCurrentSecond = 0;
		}
		
		framesInCurrentSecond++;
		// Handle Keyboard Input
		
		while (Keyboard.next()) {
			if (Keyboard.getEventKey() == Keyboard.KEY_LCONTROL && Keyboard.getEventKeyState()) debug = !debug;
			if (Keyboard.getEventKey() == Keyboard.KEY_F3 && Keyboard.getEventKeyState()) hideUI = !hideUI;
			if (Keyboard.getEventKey() == Keyboard.KEY_C && Keyboard.getEventKeyState()) hideChatUI = !hideChatUI;
			if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE && Keyboard.getEventKeyState()) mm.choose();
			if (Keyboard.getEventKey() == Keyboard.KEY_P && Keyboard.getEventKeyState()) {
				Artist.l++;
				if(Artist.l>4) Artist.l=0;
			}
		}
		
		updateUI();
	}
	
	public static void setupUI() {
		infoUI = new UI(); 
		chatUI = new UI(); 
	}
	
	private static void updateUI() {
		if(!hideUI) {
			curY = 0;
			infoUI.drawString(0, curY, framesInLastSecond + " FPS");
			curY += 60;
			if(state == SINGLEPLAYER) {
				infoUI.drawString(0, curY, "Locked: " + sp.player.locked);
				curY += 30;
				infoUI.drawString(0, curY, "X: " + sp.player.x + " Y: " + sp.player.y +
						" XO: " + sp.player.xo + " YO: " + sp.player.yo);
				curY += 30;
			}
			infoUI.drawString(0, curY, "Controls:");
			curY += 30;
			if(state == SINGLEPLAYER || state == MULTIPLAYER) {
				infoUI.drawString(0, curY, "WASD: Move.");
				curY += 30;
			}
			infoUI.drawString(0, curY, "P: Change max framerate.");
			curY += 30;
			if(state == MULTIPLAYER) {
				infoUI.drawString(0, curY, "C: Toggle Chat.");
				curY += 30;
			}
			if(state != MAINMENU) {
				infoUI.drawString(0, curY, "ESC: Go to main menu.");
				curY += 30;
			}
			infoUI.drawString(0, curY, "F3: Toggle this menu.");
		}
		if(!hideChatUI && state == MULTIPLAYER) {
			curY = 0;
			chatUI.drawString(Artist.Width * 2/3, curY, "CHAT");
			curY += 60;
			for(String s : chats) {
				if(s != null && s.length() > 0) {
					chatUI.drawString(Artist.Width * 2/3, curY, s);
					curY += 30;
				}
			}
		}
	}
	
	public static void addChat(String str) {
		if(chatnum >= 15) {
			for(int i = 1; i < chats.length; i++) chats[i-1] = chats[i];
			chats[15] = str;
		}
		else {
			chats[chatnum] = str;
		}
		chatnum++;
	}
}
