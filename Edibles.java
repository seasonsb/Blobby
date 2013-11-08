import java.awt.Graphics;

import javax.swing.JPanel;


public class Edibles {
	int x, y = -50;
	boolean markedForDeletion = false;
	Edibles(int xPos){
		x = xPos;
	}
	public void drawSelf(Graphics g, JPanel panel)
	{
		g.fillRect(x, y, 50, 50);
	}
	public int timeUpdate(int time){
		return time;
	}
	public int scoreUpdate(int OScore){
		return OScore + 10;
	}
	public void update(int pX, int pY){
		if(y < 700)
		{
			y+=20;
		}
		else
		{
			markedForDeletion = true;
		}
	}
}