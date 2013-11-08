import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class Broccoli extends Edibles{

	Broccoli(int xPos) {
		super(xPos);
		// TODO Auto-generated constructor stub
	}
	public void drawSelf(Graphics g, JPanel panel)
	{
		g.setColor(Color.GREEN);
		g.fillRect(x, y, 50, 50);
	}
	public int timeUpdate(int time){
		int newTime = time - 200;
		return newTime;
	}
}
