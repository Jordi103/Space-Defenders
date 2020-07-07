import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;


public class Jugador extends Nau{
	Polygon P;
	int[] xs = {x-ample/2,x,x+ample/2};
	int[] ys = {y+alt, y, y+alt};
	Jugador(int x, int y, int ample, int alt, int v) {
		super(x,y,ample,alt,v);
		this.HP=3;
		P =new Polygon(xs,ys,3);
	}
	
	void pinta(Graphics g){
		g.setColor(Color.YELLOW);
		int[] xs = {x-ample/2,x,x+ample/2};
		int[] ys = {y+alt, y, y+alt};
		P =new Polygon(xs,ys,3);
		if(this.HP>0){
			g.fillPolygon(P);
		}
		
	}
	
	Bala dispara(){
		Bala b=new Bala(this.x,this.y-5,-10);
		return b;
	}
}
