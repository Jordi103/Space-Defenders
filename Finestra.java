import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Finestra extends Frame implements KeyListener, WindowListener{
	
	int AMPLE=600;
	int ALT=600;
	Joc joc;
	int mode, resultat; 
	Font titol,subtitol; 
	Image img, fondo; 
	Graphics g;
	
	public static void main(String[] args) {
		new Finestra();
	}
	Finestra() {
		this.setSize(AMPLE, ALT);
		this.setVisible(true);
		this.setFocusable(true);
		this.addKeyListener(this);
		this.addWindowListener(this);
		mode=0;
		titol=new Font("Impact", Font.BOLD, 45);
		subtitol=new Font("Impact", Font.BOLD, 25);
		
		try
		{
		    fondo = ImageIO.read(new File("fondo2.png"));
		}
		catch (IOException e)
		{
		    String workingDir = System.getProperty("user.dir");
		    System.out.println("Current working directory : " + workingDir);
		    e.printStackTrace();
		}
		
		
		img = createImage(AMPLE, ALT);
		g=img.getGraphics();
		
		
		
		Joc joc=new Joc(this);
		resultat=0;
		while(true){
			//System.out.println(mode);
			switch(resultat){
			case 0: pintaMenu();
					break;
			case 1: pintaVictoria();
					break;
			case 2: pintaDerrota();
					break;
			}
			if(mode==1){
				if(resultat==0){
					resultat=joc.executa();
				} else {
					resultat=0;
				}
				mode=0;
			}
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update(Graphics g) {
		paint(g);
	}
	public void paint(Graphics g) {
		g.drawImage(img,0,0,null);
	}
	void pintaMenu(){
		g.clearRect(0,0,AMPLE,ALT);;
		g.drawImage(fondo,0, 0, AMPLE, ALT,null);
		g.setFont(titol);
		g.setColor(Color.WHITE);
		g.drawString("SPACE INVADERS",140,120);
		g.setFont(subtitol);
		g.drawString("Premeu Enter", 220, 550);
		this.repaint();
	}
	
	void pintaVictoria(){
		g.setFont(titol);
		g.setColor(Color.BLACK);
		g.drawRect(160, 270, 290, 60);
		g.setColor(Color.YELLOW);
		g.fillRect(160, 270, 290, 60);
		g.setColor(Color.BLACK);
		g.drawString("HAS GUANYAT!", 170, 320);
		g.setColor(Color.WHITE);
		g.setFont(subtitol);
		g.drawString("Premeu Enter", 220, 550);
		this.repaint();
	}
	void pintaDerrota(){
		g.setFont(titol);
		g.setColor(Color.BLACK);
		g.drawRect(170, 270, 260, 60);
		g.setColor(Color.RED);
		g.fillRect(170, 270, 260, 60);
		g.setColor(Color.BLACK);
		g.drawString("HAS PERDUT.", 180, 320);
		g.setColor(Color.WHITE);
		g.setFont(subtitol);
		g.drawString("Premeu Enter", 220, 550);
		this.repaint();
	}
	
	public void keyPressed(KeyEvent e) {
		int s=e.getKeyCode();
		if(s==10){
			mode=1;
		}
	}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent arg0) {}
	
	public void windowClosing(WindowEvent arg0) {
		System.exit(0);
	}
	public void windowActivated(WindowEvent arg0) {}
	public void windowClosed(WindowEvent arg0) {}
	public void windowDeactivated(WindowEvent arg0) {}
	public void windowDeiconified(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
	public void windowOpened(WindowEvent arg0) {}
}
