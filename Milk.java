import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class Milk extends Edibles{

	Milk(int xPos) {
		super(xPos);
		// TODO Auto-generated constructor stub
	}
	public void drawSelf(Graphics g, JPanel panel)
	{
		g.setColor(Color.GRAY);
		g.fillRect(x, y, 50, 50);
	}
	public int timeUpdate(int time){
		int newTime = time + 40;
		return newTime;
	}
}
