import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainGame extends JPanel implements ActionListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4612118976568207700L;
	JFrame frame;
	Timer timer;
	int x, y = 625, counter = 2400, stage = -1, itemspawn = 0, score;
	boolean goRight, goLeft, start = false;
	ArrayList<Edibles> drops = new ArrayList<Edibles>();
	MainGame(){ //complete, makes game happen
		super();
		this.addKeyListener(this);
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		timer = new Timer(25, this);
		this.setFocusable(true);
		setPreferredSize(new Dimension(400,700));
		frame.getContentPane().add(this);
		frame.setVisible(true);
		frame.pack();
		requestFocus();
	}
	public void paintComponent(Graphics g){ //incomplete
		if(stage == -1)
		{
			g.clearRect(0, 0, this.getWidth(), this.getHeight()); //clears screen every frame
			g.setFont(new Font(Font.SANS_SERIF, 0, 20));
			g.drawString("Press any key to start", 100, 200);
		}
		if(stage == 0){
			g.clearRect(0, 0, this.getWidth(), this.getHeight()); //clears screen every frame
			g.setColor(Color.DARK_GRAY);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.setColor(Color.RED); //sets color to red
			g.fillRect(x, y, 50, 50); //draws red rectangle(this is you)
			for(Edibles i: drops)
			{
				g.setColor(Color.black);
				i.drawSelf(g, this);
			}
			g.setColor(Color.PINK); //sets color to black
			Integer tempstr = score;
			String tempst = tempstr.toString();
			g.drawString(tempst, 350, 10);
			Integer temp = counter/40; //shows how much time you have left
			String temps = temp.toString(); //makes the secs left into a temp string
			g.drawString(temps, 0, 10); //displays the time limit
		}
		if(stage == 1)
		{
			g.clearRect(0, 0, getWidth(), getHeight());
			Integer temp = score; //shows how much time you have left
			String temps = temp.toString(); //makes the secs left into a temp string
			g.setFont(new Font(Font.SANS_SERIF, 0, 40));
			g.drawString("Score: ", 120 , 50);
			g.drawString(temps, 120, 100);
			timer.stop();
		}
	}
	public static void main(String[] args) { //complete, starts game
		new MainGame();
	}

	@Override
	public void keyReleased(KeyEvent key) {
		if(stage == 0) //if you are in game
		{
			if(key.getKeyCode() == KeyEvent.VK_LEFT)
			{
				goLeft = false; //goLeft stops once key is released
			}
			else if(key.getKeyCode() == KeyEvent.VK_RIGHT)
			{
				goRight = false; //goRight stops once key is released
			}
		}
	}
	@Override
	public void keyTyped(KeyEvent s){}
	public void keyPressed(KeyEvent key) {
	if(stage == -1)
	{
		timer.start();
		stage++;
	}
		if(stage == 0)
		{
			if(key.getKeyCode() == KeyEvent.VK_RIGHT)
			{
				if(x < 350){
					goRight = true; //makes you go rightward, stops leftward motion
					goLeft = false;
				}
			}
			else if(key.getKeyCode() == KeyEvent.VK_LEFT)
			{
				if(x > 0)
				{
					goLeft = true; //moves you left, stops rightward movement
					goRight = false;
				}
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) { //complete, updates game
		update(); //updates info
		repaint(); //redraws
	}
	public void update(){ //incomplete, updates info
		if(stage == 0)
		{
			if(itemspawn % 20 == 0) //every 20 frames, spawns item
			{
				//			System.out.println("spawning");
				int spawnType = (int)(Math.random()*10);
				int spawnX = (int)(Math.random() * 350);
				if(spawnType <= 1) //makes milk 20% chance
				{
					drops.add(new Milk(spawnX));
				}
				else if(spawnType <= 4) //makes broccoli 30% chance
				{
					drops.add(new Broccoli(spawnX));
				}
				else if(spawnType <= 9) //makes cookie 50% chance
				{
					drops.add(new Cookie(spawnX));
				}
			}
			itemspawn++;
			int temp = 0;
			if(drops.size() > 0)
			{
				while(temp < drops.size())
				{
					if(drops.get(temp).markedForDeletion == true)
						drops.remove(temp);
					else
						temp++;
				}
			}
			for(Edibles i: drops)
			{
				i.update(x, y);
				if(Math.abs(i.x - x) < 50 && Math.abs(i.y - y) < 50)
				{
					score = i.scoreUpdate(score);
					counter = i.timeUpdate(counter);
					i.markedForDeletion = true;
				}
			}
			if(counter <= 0) //once time runs out, you go to the next stage
			{
				stage++;
			}
			else
			{
				counter--;
			}
			if(goRight && x < 350) //move you but stops you from going offscreen
				x+=10;
			else if(goLeft && x > 0)
				x-=10;
		}
	}
}