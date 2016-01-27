package view.player;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.SwingUtilities;

import view.Main;

/**
 * 
 * @author Adolf
 */
public class PlayListener implements MouseListener {

	private Main main;

	public PlayListener(Main main) {
		this.main = main;
	}

	@Override
	public void mousePressed(MouseEvent event) {
	}

	@Override
	public void mouseReleased(MouseEvent event) {
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		if (!SwingUtilities.isRightMouseButton(event)) {
			main.reproducir();
		}
	}

	@Override
	public void mouseEntered(MouseEvent event) {
	}

	@Override
	public void mouseExited(MouseEvent event) {
	}

}
