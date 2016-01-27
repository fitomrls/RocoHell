package view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Mensaje extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -95115943247847712L;
	private JLabel titulo;
	private JButton cancelBT;
	private JButton okBT;
	private JLabel titulo1;
	private Main main;

	public Mensaje(Main main) {
		this.main = main;
		initGUI();

	}

	private void initGUI() {

		// setBackground(new java.awt.Color(0, 0, 0));
		setOpaque(false);
		// setBackground(new Color(0,0,0,200));
		setPreferredSize(new java.awt.Dimension(600, 300));
		setLayout(null);
		setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 1, false));

		titulo = new JLabel();
		add(titulo);
		titulo.setText("Confirma que desea repdroducir ");
		titulo.setBounds(143, 45, 315, 56);
		titulo.setForeground(Color.white);
		titulo.setBackground(Color.black);
		titulo.setFont(new java.awt.Font("Tahoma", 1, 19));
		titulo.setOpaque(true);

		titulo1 = new JLabel();
		add(titulo1);
		titulo1.setText("este video?");
		titulo1.setBounds(240, 100, 110, 45);
		titulo1.setForeground(Color.white);
		titulo1.setBackground(Color.black);
		titulo1.setFont(new java.awt.Font("Tahoma", 1, 18));
		titulo1.setOpaque(true);

		okBT = new JButton();
		add(okBT);
		okBT.setText("SI");
		okBT.setBounds(178, 204, 90, 35);
		okBT.setFont(new java.awt.Font("Tahoma", 1, 16));
		okBT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrar();
				main.play();
			}
		});

		cancelBT = new JButton();
		add(cancelBT);
		cancelBT.setText("NO");
		cancelBT.setFont(new java.awt.Font("Tahoma", 1, 16));
		cancelBT.setBounds(321, 204, 90, 35);
		cancelBT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrar();
			}
		});
	}

	public void cerrar() {
		main.volverVistas(true);
		setVisible(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				0.4f));
		/*
		 * GradientPaint redtowhite = new GradientPaint(0,0,Color.red,600, 0,
		 * Color.white); g2d.setPaint(redtowhite); g2d.fill(new
		 * RoundRectangle2D.Double(0, 0, 600, 300, 10, 10));
		 */
		g2d.setPaint(Color.BLACK);
		g2d.fill(new Rectangle2D.Double(0, 0, 600, 300));
//		g2d.setColor(Color.yellow);
//		g2d.drawString("Confirma que desea repdroducir ", 153, 49);
		titulo.updateUI();
		titulo1.updateUI();
		okBT.updateUI();
		cancelBT.updateUI();
	}
}
