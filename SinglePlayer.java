package data;

import static helpers.Leveler.LoadMap;

import entities.Player;
import tile.TileGrid;

public class SinglePlayer {

	private TileGrid grid;
	protected Player player;
	
	public final int DIRECTION_NEUTRAL = 0;
	public final int ADD_MAX_RIGHT = 1;
	public final int ADD_MAX_LEFT = 2;

	public SinglePlayer() {
		//Map, name is map.tdm
		this.grid = LoadMap("map.tdm");
		//Setup Player with coords 2,4 (tiles not pixels). speed of 3.5. 4 animation states, 2 animation length. And base name of smooth. res/ anState 
		//and .png are autoadded. provides the tile grid
		this.player = new Player(2,4,3.5f,4,2,"smooth",ADD_MAX_LEFT,grid);
	}
	
	public void update() {
		grid.draw();
		player.update();
	}
}