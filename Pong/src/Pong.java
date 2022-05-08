/**
 * @author tmtim
 */
import javax.swing.JFrame;

public class Pong extends JFrame 
{
	private static final String windowTitle = "Pong";
	private static final Integer windowWidth = 800;
	private static final Integer windowHeight = 600;
	
	public Pong() 
	{
    	setTitle(windowTitle);
	    setSize(windowWidth, windowHeight);
    	setResizable(false);
    	setVisible(true);
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new Pong();

	}

}
