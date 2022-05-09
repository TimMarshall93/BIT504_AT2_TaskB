import java.awt.Color;

public class Paddle extends Sprite
{
	private static final int paddleWidth = 10;
	private static final int paddleHeight = 100;
	private static final Color paddleColor = Color.WHITE;
	private static final int distanceFromEdge = 40;
	
	public Paddle(Player player, int panelWidth, int panelHeight)
	{
		setWidth(paddleWidth);
		setHeight(paddleHeight);
		setballAndPaddleColor(paddleColor);
		
		int xPos;
        if(player == Player.One) {
            xPos = distanceFromEdge;
        } else {
            xPos = panelWidth - distanceFromEdge - getWidth();
        }
        setInitialPosition(xPos, panelHeight / 2 - (getHeight() / 2));
        resetToInitialPosition();
    
	}

}
