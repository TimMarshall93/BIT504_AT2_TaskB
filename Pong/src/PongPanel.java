import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;

public class PongPanel extends JPanel implements ActionListener, KeyListener
{

	private static final Color backgroundColor = (Color.BLACK);
	private static final int timerDelay = 5;
	Ball ball;
	GameState gameState = GameState.Initialising;
	Paddle paddle1, paddle2;
	
	public void createObjects()
	{
		ball = new Ball(getWidth(), getHeight());
		paddle1 = new Paddle(Player.One, getWidth(), getHeight());
		paddle2 = new Paddle(Player.Two, getWidth(), getHeight());
		
	}
	

	public PongPanel()
	{
		setBackground(backgroundColor);
		Timer timer = new Timer(timerDelay, this);
		timer.start();
	}

	@Override
	public void keyTyped(KeyEvent event) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent event) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent event) 
	{
		// TODO Auto-generated method stub
		
	}
	private void update()
	{
		switch(gameState)
		{
		    case Initialising:
		    {
		    	createObjects();
				gameState = GameState.Playing;
				break;
		    }
		    case Playing:
		    {
		    	break;
		    }
		    case GameOver:
		    {
		    	break;
		    }
		
			
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) 
	{
		update();
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) 
	{
	    super.paintComponent(g);
	    paintDottedLine(g);
	    if (gameState != GameState.Initialising)
	    {
	    	paintSprite(g, ball);
	    	paintSprite(g, paddle1);
	    	paintSprite(g, paddle2);
	    }
	}
	private void paintDottedLine(Graphics g) 
	{
	    Graphics2D g2d = (Graphics2D) g.create();
	    Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
	    g2d.setStroke(dashed);
	    g2d.setPaint(Color.WHITE);
	    g2d.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
	    g2d.dispose();
	}
	
	private void paintSprite(Graphics g, Sprite sprite)
	{
		g.setColor(sprite.getballAndPaddleColor());
		g.fillRect(sprite.getxPosition(), sprite.getyPosition(), sprite.getWidth(), sprite.getHeight());
	}
}
