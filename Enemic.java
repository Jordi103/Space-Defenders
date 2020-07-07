import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Enemic extends Nau{
	Rectangle R;
	Enemic(int x, int y, int ample, int alt, int v) {
		super(x,y,ample,alt,v);
		this.HP=1;
		R=new Rectangle(x,y,ample,alt);
	}
	void pinta(Graphics g){
		if(HP>0){
			g.setColor(Color.RED);
			R.setBounds(x,y,ample,alt);
			g.fillRect(x,y,ample,alt);
		}
	}
	Bala dispara(){
		Bala b=new Bala(this.x+this.ample/2,this.y+this.alt,10);
		return b;
	}
}
