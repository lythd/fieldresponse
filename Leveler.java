package helpers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import tile.Tile;
import tile.TileGrid;
import tile.TileType;

public class Leveler {

	private static String main;
	private static String extra;
	private static int slide, i, j;
	private static TileGrid grid;
	
	public static void SaveMap(String mapName, TileGrid grid) {
		try {
			File file = new File(mapName);
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(main);
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static TileGrid LoadMap(String mapName) {
		grid = new TileGrid();
		try {
			BufferedReader br = new BufferedReader(new FileReader(mapName));
			main = br.readLine();
			extra = main.substring(grid.getTilesWide() * grid.getTilesHigh());
			for (i = 0; i < grid.getTilesWide(); i++) {
				for (j = 0; j < grid.getTilesHigh(); j++) {
					grid.setTile(i, j, getTileType(main.substring(i * grid.getTilesHigh() + j, i * grid.getTilesHigh() + j + 1)));
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return grid;
	}
	
	public static String getTileID(Tile t) {
		String ID = "ÿ";
		switch (t.getType()) {
		case Grass:
			ID = "0";
			break;
		case Soil:
			ID = "1";
			break;
		case Path:
			ID = "2";
			break;
		case Ice:
			ID = "3";
			break;
		case Wood:
			ID = "4";
			break;
		case TrimmedGrass:
			ID = "5";
			break;
		case Dirt:
			ID = "6";
			break;
		case Stone:
			ID = "7";
			break;
		case Sand:
			ID = "8";
			break;
		case Snow:
			ID = "9";
			break;
		case NormalBrick:
			ID = "a";
			break;
		case CementBrick:
			ID = "b";
			break;
		case DirtBrick:
			ID = "c";
			break;
		case StoneBrick:
			ID = "d";
			break;
		case BrickCrate:
			ID = "e";
			break;
		case DirtCrate:
			ID = "f";
			break;
		case StoneCrate:
			ID = "g";
			break;
		case Water:
			ID = "h";
			break;
		case TallGrass:
			ID = "i";
			break;
		case GravellyDirt:
			ID = "j";
			break;
		case GravellyStone:
			ID = "k";
			break;
		case NULL:
			ID = "ÿ";
			break;
		}
		return ID;
	}
	
	public static TileType getTileType(String ID) {
		TileType type = TileType.NULL;
		switch (ID) {
		case "0":
			type = TileType.Grass;
			break;
		case "1":
			type = TileType.Soil;
			break;
		case "2":
			type = TileType.Path;
			break;
		case "3":
			type = TileType.Ice;
			break;
		case "4":
			type = TileType.Wood;
			break;
		case "5":
			type = TileType.TrimmedGrass;
			break;
		case "6":
			type = TileType.Dirt;
			break;
		case "7":
			type = TileType.Stone;
			break;
		case "8":
			type = TileType.Sand;
			break;
		case "9":
			type = TileType.Snow;
			break;
		case "a":
			type = TileType.NormalBrick;
			break;
		case "b":
			type = TileType.CementBrick;
			break;
		case "c":
			type = TileType.DirtBrick;
			break;
		case "d":
			type = TileType.StoneBrick;
			break;
		case "e":
			type = TileType.BrickCrate;
			break;
		case "f":
			type = TileType.DirtCrate;
			break;
		case "g":
			type = TileType.StoneCrate;
			break;
		case "h":
			type = TileType.Water;
			break;
		case "i":
			type = TileType.TallGrass;
			break;
		case "j":
			type = TileType.GravellyDirt;
			break;
		case "k":
			type = TileType.GravellyStone;
			break;
		case ">":
			type = getTileType(extra.substring(slide, slide + 1));
			slide += 1;
			break;
		case "ÿ":
			type = TileType.NULL;
			break;
		}
		return type;
	}
}
