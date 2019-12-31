package data;

import static helpers.Leveler.LoadMap;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import entities.OtherPlayer;
import entities.ServerPlayer;
import tile.TileGrid;

public class MultiPlayer {
	
	private boolean sk = false;

	private TileGrid grid;
	private Socket s;
	private ServerPlayer player;
	
	protected ArrayList<OtherPlayer> players;
	
	public PrintWriter sender;
	
	public final int DIRECTION_NEUTRAL = 0;
	public final int ADD_MAX_RIGHT = 1;
	public final int ADD_MAX_LEFT = 2;
	
	public int ci;
	public String hn;
	public int p;

	public MultiPlayer() {
		//Map, name is map.tdm
		grid = LoadMap("map.tdm");
		//Player List
		players = new ArrayList<OtherPlayer>();
	}
	
	public void update() {
		if(!sk) {
			sk = true;
			//Socket Stuff
			ci = Integer.parseInt(JOptionPane.showInputDialog(null,"Client ID"));
			hn = JOptionPane.showInputDialog(null,"Host Name");
			p = Integer.parseInt(JOptionPane.showInputDialog(null,"Port"));
			try {
				s = new Socket(hn,p);
				
				ServerConnection reciever = new ServerConnection(s,this);
				new Thread(reciever).start();
				
				sender = new PrintWriter(s.getOutputStream(),true);
				
				sender.println("get");
			} catch (IOException e) {
				e.printStackTrace();
			}
			//Setup Player with coords 2,4 (tiles not pixels). speed of 3.5. 4 animation states, 2 animation length. And base name of smooth. res/ anState 
			//and .png are autoadded. provides the tile grid
			player = new ServerPlayer(sender,ci,2,4,3.5f,4,2,"smooth",ADD_MAX_LEFT,grid);
		}
		grid.draw();
		player.update();
		for(OtherPlayer p : players) p.update();
	}
	
	public void say(String str) {
		sender.println("say " + str);
	}
	
	public void shutdown() {
		if(!sk) return;
		sender.println("stp");
		try {
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
