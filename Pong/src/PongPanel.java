import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Font;
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
	private static final int ballMovementSpeed = 5;
	private final static int pointsToWin = 11;
	int player1Score = 0, player2Score = 0;
	String winner = "WIN!";
	Player gameWinner;
	private final static int scoreTextX = 100;
    private final static int scoreTextY = 100;
    private final static int scoreFontSize = 50;
    private final static String scoreFontFamily = "Serif";
    private final static int winnerTextX = 200;
    private final static int winnerTextY = 200;
    
	public PongPanel()
	{
		setBackground(backgroundColor);
		Timer timer = new Timer(timerDelay, this);
		timer.start();
		addKeyListener(this);
		setFocusable(true);
	}

	@Override
	public void keyTyped(KeyEvent event) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent event) 
	{
		if(event.getKeyCode() == KeyEvent.VK_W) 
		{
            paddle1.setyVelocity(-5);
        } 
		else if(event.getKeyCode() == KeyEvent.VK_S) 
		{
            paddle1.setyVelocity(5);
		}
		
		if(event.getKeyCode() == KeyEvent.VK_UP) 
		{
            paddle2.setyVelocity(-5);
        } 
		else if(event.getKeyCode() == KeyEvent.VK_DOWN) 
		{
            paddle2.setyVelocity(5);
		}
	}

	@Override
	public void keyReleased(KeyEvent event) 
	{
		if(event.getKeyCode() == KeyEvent.VK_W || event.getKeyCode() == KeyEvent.VK_S) 
		{
            paddle1.setyVelocity(0);
        }
		if(event.getKeyCode() == KeyEvent.VK_UP || event.getKeyCode() == KeyEvent.VK_DOWN) 
		{
            paddle2.setyVelocity(0);
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
	    	paintScores(g);
	    	displayWin(g);
	    }
	}
	
	private void update()
	{
		switch(gameState)
		{
		    case Initialising:
		    {
		    	createObjects();
				gameState = GameState.Playing;
				ball.setyVelocity(ballMovementSpeed);
				ball.setxVelocity(ballMovementSpeed);
				break;
		    }
		    case Playing:
		    {
		    	moveObject(paddle1);
		    	moveObject(paddle2);
		    	moveObject(ball);
		    	checkWallBounce();
		    	checkPaddleBounce();
		    	checkWin();
		    	break;
		    }
		    case GameOver:
		    {
		    	break;
		    }
		}
	}

	public void createObjects()
	{
		ball = new Ball(getWidth(), getHeight());
		paddle1 = new Paddle(Player.One, getWidth(), getHeight());
		paddle2 = new Paddle(Player.Two, getWidth(), getHeight());
		
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
	
	private void moveObject(Sprite obj)
	{
		obj.setxPosition(obj.getxPosition() + obj.getxVelocity(), getWidth());
		obj.setyPosition(obj.getyPosition() + obj.getyVelocity(), getHeight());
	}
	
	private void checkWallBounce()
	{
		if (ball.getyPosition() <= 0 || ball.getyPosition() >= getHeight() - ball.getHeight())
		{
			ball.setyVelocity(-ball.getyVelocity());
		}
		
		if(ball.getxPosition() <= 0) 
		{
	        // Hit left side of screen
	        ball.setxVelocity(-ball.getxVelocity());
	        addScore(Player.Two);
	        resetBall();
	    } 
		else if(ball.getxPosition() >= getWidth() - ball.getWidth()) 
		{
	        // Hit right side of screen
	        ball.setxVelocity(-ball.getxVelocity());
	        addScore(Player.One);
	        resetBall();
	    }
	}
	
	private void resetBall()
	{
		ball.resetToInitialPosition();
	}
	
	private void checkPaddleBounce()
	{
		if(ball.getxVelocity() < 0 && ball.getRectangle().intersects(paddle1.getRectangle())) 
		{
	        ball.setxVelocity(ballMovementSpeed);
	    }
	    if(ball.getxVelocity() > 0 && ball.getRectangle().intersects(paddle2.getRectangle())) 
	    {
	        ball.setxVelocity(-ballMovementSpeed);
	    }
	}
	
	private void addScore(Player player)
	{
		if (player == Player.One)
		{
			player1Score ++;
		}
		else if (player == Player.Two)
		{
			player2Score ++;
		}
	}
	
	private void checkWin()
	{
		if (player1Score >= pointsToWin)
		{
			gameWinner = Player.One;
			gameState = GameState.GameOver;
		}
		else if (player2Score >= pointsToWin)
		{
			gameWinner = Player.Two;
			gameState = GameState.GameOver;
		}
	}
	
	private void paintScores(Graphics g)
	{
         Font scoreFont = new Font(scoreFontFamily, Font.BOLD, scoreFontSize);
         String leftScore = Integer.toString(player1Score);
         String rightScore = Integer.toString(player2Score);
         g.setFont(scoreFont);
         g.drawString(leftScore, scoreTextX, scoreTextY);
         g.drawString(rightScore, getWidth()-scoreTextX, scoreTextY);
	}
	
	private void displayWin (Graphics g)
	{
		if (gameWinner != null)
		{
        Font winnerFont = new Font(scoreFontFamily, Font.BOLD, scoreFontSize);
        g.setFont(winnerFont);
        int xPosition = getWidth() / 2;
        if (gameWinner == Player.One)
        {
        	xPosition -= winnerTextX;
        }
        
        else if (gameWinner == Player.Two)
        {
        	xPosition += winnerTextX;
        }
        g.drawString(winner, xPosition, winnerTextY);
		}
	}
}
