package view.admin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;

import modell.beans.User;
import util.FormatoTabla;
import view.ModeloTabla;
import controller.ControllerVideo;

public class Admin extends JFrame {

	private static final long serialVersionUID = 1L;
	private User usuario;
	private JTabbedPane tabVideos;
	private JPanel panTool;
	private JTextField fBuscar;
	private JLabel lBuscar;
	private JPanel pVideos;
	private JButton bSalir;
	private JPanel panelRoot;
	private JScrollPane scrollProds;
	final Toolkit toolkit = Toolkit.getDefaultToolkit();
	final Dimension screenSize = toolkit.getScreenSize();
	private JButton updateBT;
	final int ANCHORES = screenSize.width;
	final int ALTORES = screenSize.height; 

	
	public Admin(User usuario) {

		this.usuario = usuario;
		// setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);// ojo
		panelRoot = new JPanel();
		getContentPane().add(panelRoot, BorderLayout.CENTER);
		panelRoot.setLayout(null);
		panelRoot.setPreferredSize(new java.awt.Dimension(ANCHORES, ALTORES-60));
		panelRoot.setBackground(new java.awt.Color(192, 192, 192));
		panelRoot.add(getTabVideos());
		
		setTitle("Administracion");
		//setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
//		setUndecorated(true);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		pack();
		
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	public JScrollPane getTablaVideos(Object[][] datitos) {
		String[] columnas_Name = { "###", "autor", "titulo",
				"categoria", "duracion", "directorio", "archivo",
				"snap" };

		ModeloTabla modeloTabla = new ModeloTabla(columnas_Name, datitos);
		modeloTabla.setContenido();
		
		FormatoTabla formato = new FormatoTabla();
		final JTable table = new JTable();	
		table.setDefaultRenderer(Integer.class, formato);
		table.setDefaultRenderer(String.class, formato); 
		/**table.setDefaultRenderer(Double.class, formato); 
		table.setDefaultRenderer(String.class, formato); 
		table.setDefaultRenderer(Integer.class, formato);
		table.setDefaultRenderer(Float.class, formato);*/

		table.setModel(modeloTabla);
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		table.getColumnModel().getColumn(0).setMinWidth(50);
		table.getColumnModel().getColumn(0).setMaxWidth(50);
		table.getColumnModel().getColumn(3).setMinWidth(100);
		table.getColumnModel().getColumn(3).setMaxWidth(100);
		table.getColumnModel().getColumn(4).setMinWidth(60);
		table.getColumnModel().getColumn(4).setMaxWidth(60);


//		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		table.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseClicked(MouseEvent e)	{
				if (e.getClickCount() == 2) {
					/*String nombre = table.getValueAt(table.getSelectedRow(), 1)
							.toString();
					String cantidad = table.getValueAt(table.getSelectedRow(),
							2).toString();
					String precioCompra = table.getValueAt(
							table.getSelectedRow(), 3).toString();
					String precioVenta = table.getValueAt(
							table.getSelectedRow(), 4).toString();
					String fechaVenci = table.getValueAt(
							table.getSelectedRow(), 5).toString();
					String fechaReg = table.getValueAt(table.getSelectedRow(),
							6).toString();
					String codigo = table.getValueAt(table.getSelectedRow(), 7)
							.toString();
					String categoria = table.getValueAt(table.getSelectedRow(),
							8).toString();*/
				}
			}
		});
		scrollProds = new JScrollPane(table);
		//packColumn(table, 8, 2);
		
		return scrollProds;
	}
	
	public void packColumn(JTable table, int vColIndex, int margin) {
       /* TableModel model = table.getModel();
        DefaultTableColumnModel colModel = (DefaultTableColumnModel)table.getColumnModel();
        TableColumn col = colModel.getColumn(vColIndex);
        int width = 0;            
        TableCellRenderer renderer = col.getHeaderRenderer();
        if (renderer == null) {
            renderer = table.getTableHeader().getDefaultRenderer();
        }
        Component comp = renderer.getTableCellRendererComponent(table, col.getHeaderValue(), false, false, 0, 0);
        width = comp.getPreferredSize().width;            
       	for (int r=0; r<table.getRowCount(); r++) {
            renderer = table.getCellRenderer(r, vColIndex);
            comp = renderer.getTableCellRendererComponent(table, table.getValueAt(r, vColIndex), false, false, r,vColIndex);
            width = Math.max(width, comp.getPreferredSize().width);
        }            
       	width += 2*margin;
       	
        col.setPreferredWidth(width); */
	}
	

	public void actualizarTabla() {
		pVideos.remove(scrollProds);
//		scrollProds = getTablaProductos(productoController.getDatosProducto());
		pVideos.add(scrollProds);
		scrollProds.setBounds(13, 40, 655, 355);
	}


	private void filtrarTabla() { // ojo2
		pVideos.remove(scrollProds);
		pVideos.updateUI();
		ControllerVideo controllerVideo = new ControllerVideo();
		scrollProds = getTablaVideos(controllerVideo .getResultadosBusqueda(fBuscar.getText().trim()));
		scrollProds.setBounds(2, 2, ANCHORES - 20, ALTORES - 200);
		pVideos.add(scrollProds);
	}

	private JPanel getPanTool() {
		if (panTool == null) {
			panTool = new JPanel();
			panTool.setLayout(null);
			panTool.setBounds(1, 3, ANCHORES-10, 42);
			panTool.setBackground(new java.awt.Color(128,128,128));
			{
				bSalir = new JButton();
				panTool.add(bSalir);
				panTool.add(getUpdateBT());
				bSalir.setText("Salir");
				bSalir.setBounds(ANCHORES-100, 3, 86, 35);
				bSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						Object[] options = {"Si", "No"};
						int n = JOptionPane.showOptionDialog(null,"Esta seguro de salir de la Aplicacion?", "Saliendo de la Aplicacion...",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE,
								null,     //do not use a custom Icon
								options,  //the titles of buttons
								options[1]); //default button title
						if(n==0){
							setVisible(false);
							//System.exit(0);
						}
					}
				});
			}
		}
		return panTool;
	}


	private JTabbedPane getTabVideos() {
		if (tabVideos == null) {
			tabVideos = new JTabbedPane();
			tabVideos.setBounds(2, 50, ANCHORES-10, ALTORES-125);
			tabVideos.setBackground(new java.awt.Color(192,192,192));
			// tabVideos.addTab("jLabel1", null, getJLabel1(), null);

			pVideos = new JPanel();
			pVideos.setLayout(null);
			pVideos.setBounds(2, 2, ANCHORES-30, ALTORES-385);
			pVideos.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 1,
					false));
//			pVideos.setPreferredSize(new java.awt.Dimension(526, 112));


			tabVideos.addTab("Videos Registrados", null, pVideos, null);
			pVideos.setBackground(new java.awt.Color(0,0,0));

			panelRoot.add(getPanTool());

			ControllerVideo videoController = new ControllerVideo();
			
			scrollProds = getTablaVideos(videoController.getFilasVideos() );
			pVideos.add(scrollProds);
			scrollProds.setBounds(2, 2, ANCHORES-20, ALTORES-200);

			lBuscar = new JLabel();
			pVideos.add(lBuscar);
			lBuscar.setText("Buscar :");
			lBuscar.setBounds(ANCHORES-320, ALTORES-195, 59, 16);
			lBuscar.setForeground(new java.awt.Color(255,255,255));

			fBuscar = new JTextField();
			pVideos.add(fBuscar);
			fBuscar.setBounds(ANCHORES-235, ALTORES-195, 206, 23);
			fBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyTyped(java.awt.event.KeyEvent evt) {
					filtrarTabla();
				}
			});

		}
		return tabVideos;
	}
	
	private JButton getUpdateBT() {
		if(updateBT == null) {
			updateBT = new JButton();
			updateBT.setText("Actualizar");
			updateBT.setBounds(12, 10, 94, 21);
			updateBT.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {

					ScanTool scan = new ScanTool();
					scan.setLocationRelativeTo(null);
					//scan.setVisible(true);
					//
				}
			});
		}
		return updateBT;
	}

}// fin
