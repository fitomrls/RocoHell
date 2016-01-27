package view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class RootPane extends JPanel{
	
	BufferedImage backImage = null;
	private static final long serialVersionUID = -1771315483980525458L;
    ImageIcon image;

	public RootPane(){
//		this.setLayout(null);
        image = new ImageIcon(getClass().getClassLoader().getResource("images/bgmetal1.jpg"));

		/*try {
			File file = new File("bin/images/bgmetal1.jpg");
			backImage = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
//		setForeground(new Color(0, 0, 0, 0));
		Graphics2D g2 = (Graphics2D) g;
		//if (backImage != null) {
		//	g2.drawImage(backImage, 0, 0, this.getWidth(), this.getHeight(),this);
		//}
        g2.drawImage(image.getImage(), 0, 0, this.getWidth(), this.getHeight(),this);        		
	}
	
	
}
