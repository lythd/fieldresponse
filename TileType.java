package tile;

public enum TileType {

	//Passable
	Grass("grass", true),
	Soil("dirt2", true),
	Path("stone2", true),
	Ice("ice", true),
	Wood("wood", true),
	TrimmedGrass("grass2", true),
	Dirt("dirt", true),
	Stone("stone", true),
	Sand("sand", true),
	Snow("snow", true),
	//Not Passable
	NormalBrick("brickNormal", false),
	CementBrick("brickCement", false),
	DirtBrick("dirtBrick", false),
	StoneBrick("stoneBrick", false),
	BrickCrate("brickCrate", false),
	DirtCrate("dirtCrate", false),
	StoneCrate("stoneCrate", false),
	Water("water", false),
	TallGrass("grassTall", false),
	GravellyDirt("dirtGravel", false),
	GravellyStone("stoneGravel", false),
	NULL("null", false);
	
	
	String textureName;
	public boolean passable;
	
	TileType(String textureName, boolean passable) {
		this.textureName = textureName;
		this.passable = passable;
	}
	
}
