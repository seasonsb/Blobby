import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class Cookie extends Edibles{

	Cookie(int xPos) {
		super(xPos);
	}
	public void drawSelf(Graphics g, JPanel panel)
	{
		g.setColor(new Color(142,108,39));
		g.fillRect(x, y, 50, 50);
	}
	public int scoreUpdate(int OScore){
		return OScore + 20;
	}
}
