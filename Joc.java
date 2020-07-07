import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


public class Joc implements KeyListener{
	Finestra f;
	int nvius,i;
	int N=20;
	Enemic[] Enmcs = new Enemic[N];
	Jugador J;
	int ample, alt, sep, D, C, T;
	ArrayList<Bala> bales= new ArrayList<Bala>();
	

	Joc(Finestra f){
		this.f=f;
		ample=40;
		alt=30;
		sep=25;
		C=0;
		D=1;
		T=20;
	}
	int executa() {
		f.addKeyListener(this);
		inicialitza();
		while(true) {
			T++;
			calculaMoviments();
			comprovaXocs();
			repintaPantalla();
			if(nvius==0){
				bales.clear();
				return 1;
			}
			if(J.HP==0){
				bales.clear();
				return 2;
			}
			
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	void inicialitza() {
		Font myFont = new Font("Impact", Font.PLAIN, 24);
		f.g.setFont(myFont);
		for(int i=0; i<N; i++){
			Enmcs[i]=new Enemic(50+(i%5)*(sep+ample),100+i/5*(sep+alt),ample,alt,2);
		}
		J=new Jugador(300,500,50,50,7);
	}
	void calculaMoviments() {
		int s,x,v;
		Bala b;
		s=1;
		x=Enmcs[0].x;
		v=Enmcs[0].v;
		
		boolean vora1, vora2;
		{
		vora1=x<50;
		vora2=x>4*(sep+ample)-sep;
		}
		
		if(vora1||vora2){
			if(C<20){
				C++;
				for(i=0; i<N; i++){
					Enmcs[i].y+=v;
					if((Enmcs[i].y+Enmcs[i].ample>f.AMPLE)&&(Enmcs[i].HP>0)){
						System.out.println("qdasdsad");
						J.HP=0;
					}
				}
			} else {
				C=0;
				if(vora1){
					D=1;
				} else {
					D=-1;
				}
				for(i=0; i<N; i++){
					Enmcs[i].x+= D*v;
				}
			}
			
		}
		if((50<=x)&&(4*(sep+ample)-sep>=x)){
			for(i=0; i<N; i++){
				Enmcs[i].x+=D*v;
			}
		}
		for(i=0; i<bales.size(); i++){
			b=bales.get(i);
			b.y+=b.v;
			if(b.y+b.alt<0){
				bales.remove(b);
			}
		}
		nvius=0;
		for(i=0; i<N; i++){
			if(Enmcs[i].HP>0){
				nvius++;
			}
		}
		if(nvius<=5){
			for(i=0; i<N; i++){
				Enmcs[i].v=4;
			}
		}
	}
	
	void comprovaXocs() {
		int i,j; 
		double r;
		Bala b;
		ArrayList<Enemic> PF;
		for(j=0; j<bales.size(); j++){
			b=bales.get(j);
			for(i=0; i<N; i++){
				if(b.R.intersects(Enmcs[i].R)&&(Enmcs[i].HP>0)){
					Enmcs[i].HP=Math.max(Enmcs[i].HP-1,0);
					bales.remove(b);
				}
			}
			if(J.P.intersects(b.R)){
				J.HP=Math.max(J.HP-1, 0);
				bales.remove(b);
			}
		}
		for(i=0; i<N; i++){
			if((J.P).intersects(Enmcs[i].R)){
				J.HP=0;
			}
		}
		PF=PrimeraFila(Enmcs);
		for(Enemic E:PF){
			r=Math.random();
			if(r<0.013){
				b=E.dispara();
				bales.add(b);
			}
		}
		PF.clear();
	}
	void repintaPantalla() {
		f.g.clearRect(0,0,f.AMPLE,f.ALT);
		f.g.setColor(Color.BLACK);
		//f.g.fillRect(0, 0, f.AMPLE, f.ALT);
		f.g.drawImage(f.fondo,0, 0, f.AMPLE, f.ALT,null);
		
		f.g.setColor(Color.YELLOW);
		f.g.drawString("HP: "+J.HP, 20, 580);
		for (int i=0; i<N; i++) {
			Enmcs[i].pinta(f.g);
		}
		J.pinta(f.g);
		for(Bala b:bales){
			b.pinta(f.g);
		}
		f.repaint();
	}
	public void keyPressed(KeyEvent e) {
		/*esquerra: 37
		dreta: 39
		espai: 32*/
		int s;
		s=e.getKeyCode();
		if(s==39){
			if(J.x+J.ample<=550){
				J.x+=J.v;
			}
		}
		if(s==37){
			if(J.x>=50){
				J.x-=J.v;
			}
		}
		if(s==32){
			if(T>=20){
				bales.add(J.dispara());
				T=0;
			}
		}
	}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	
	ArrayList<Enemic> PrimeraFila(Enemic Es[]){
		// 4 files, 5 columnes
		int i,j; 
		ArrayList<Enemic> PF = new ArrayList<Enemic>();
		
		for(j=1; j<=5; j++){
			for(i=4; i>=1; i--){
				if (Es[(i-1)*5+j-1].HP != 0){
					PF.add(Es[(i-1)*5+j-1]);
					break;
				}
			}
		}
		return PF;
	}
	
	
}	
