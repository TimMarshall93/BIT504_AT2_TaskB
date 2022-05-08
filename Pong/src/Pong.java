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
    	add(new PongPanel());
    	setVisible(true);
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) 
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable() 
		{
	        public void run() 
	        {
		        new Pong();
	        }
		});

	}

}
