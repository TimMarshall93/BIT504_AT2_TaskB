import java.awt.Color;

public class Ball extends Sprite
{
	private static final Color ballColor = Color.WHITE;
	private static final int ballHeight = 25;
	private static final int ballWidth = 25;
	
	public Ball (int panelWidth, int panelHeight)
	{
		setballAndPaddleColor(ballColor);
		setHeight(ballHeight);
		setWidth(ballWidth);
		setInitialPosition(panelWidth / 2 - (getWidth() / 2), panelHeight / 2 - (getHeight() / 2));
	    resetToInitialPosition();
	}

	

}