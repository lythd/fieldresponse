package entities;

public class OtherPlayer extends Character {

	private int ind = 0;
	private int id = 0;
	
	public OtherPlayer(int id_, float x, float y, float xo, float yo, int anMax, int ind_, String anLead) {
		super(x, y, anMax, anLead);
		super.overDraw = true;
		super.xo = xo;
		super.yo = yo;
		ind = ind_;
		id = id_;
	}
	
	@Override
	public void update() {
		super.update();
		super.draw(ind);
	}
	
	public void recieve(float x_, float y_, float xo_, float yo_, int am, int ind_, String ld) {
		x = x_;
		y = y_;
		xo = xo_;
		yo = yo_;
		ind = ind_;
		super.anLead(ld);
		super.anMax(am);
	}
	
	public int id() {
		return id;
	}
	
}
