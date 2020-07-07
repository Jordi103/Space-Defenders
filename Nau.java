import java.awt.Color;
import java.awt.Graphics;

public class Nau extends ObjecteEspacial {
	int ample; int alt; int HP;
	Nau(int x, int y, int ample, int alt, int v) {
		super(x,y,v);
		this.ample=ample;
		this.alt=alt;
		this.HP=1;
	}
	
}
