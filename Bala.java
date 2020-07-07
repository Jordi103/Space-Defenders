import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Bala extends ObjecteEspacial{
	int ample; int alt; Rectangle R;
	Bala(int x, int y, int v) {
		super(x,y,v);
		this.ample=2;
		this.alt=5;
		R=new Rectangle(x,y,ample,alt);
	}
	
	void pinta(Graphics g){
		g.setColor(Color.WHITE);
		R.setBounds(x,y,ample,alt);
		g.fillRect(x,y,ample,alt);
	}
}
