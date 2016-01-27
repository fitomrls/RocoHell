package view.player;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import view.Main;

public class CapaPlayer extends JPanel {

	private static final long serialVersionUID = -2497537181610968399L;
	private JLabel titulo;
	private JButton playBT;
	private Main main;

	public CapaPlayer(Main main) {
		this.main = main;
		initGUI();
	}

	private void initGUI() {

		setOpaque(false);
		setPreferredSize(new java.awt.Dimension(600, 300));
		setLayout(null);
		setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 1, false));

		titulo = new JLabel();
//		add(titulo);
		titulo.setBounds(143, 45, 315, 56);
		titulo.setForeground(Color.white);
		titulo.setBackground(Color.black);
		titulo.setFont(new java.awt.Font("Tahoma", 1, 19));
		titulo.setOpaque(true);

		playBT = new JButton();
//		playBT.setOpaque(false);
		add(playBT);
		playBT.setIcon(new ImageIcon(getClass().getClassLoader()
				.getResource("images/play.png")));
		playBT.setBounds(260, 120, 97, 68);
		playBT.setFont(new java.awt.Font("Tahoma", 1, 16));
		playBT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.reproducir();
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				0.4f));

		GradientPaint redtowhite = new GradientPaint(0, 0, Color.red, 600, 0,
				Color.white);
		g2d.setPaint(redtowhite);
		g2d.fill(new RoundRectangle2D.Double(0, 0, 600, 300, 10, 10));
		/*
		 * g2d.setPaint(Color.BLACK); g2d.fill(new Rectangle2D.Double(0, 0, 600,
		 * 300));
		 */
		// titulo.updateUI();
		playBT.updateUI();
	}
}
