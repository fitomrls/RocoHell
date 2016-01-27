package view.admin;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Editar extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = -87794518192104642L;
	private JPanel panelRoot;
	private JLabel archivo;
	private JLabel autor;
	private JTextField anhoTF;
	private JLabel jLabel1;
	private JButton cancelBT;
	private JButton okBT;
	private JTextField calidadTF;
	private JTextField tipoTF;
	private JTextField directorioTF;
	private JTextField snapTF;
	private JTextField archivoTF;
	private JTextField duracionTF;
	private JTextField categoriaTF;
	private JTextField tituloTF;
	private JLabel titulo;
	private JLabel categoria;
	private JTextField autorTF;
	private JLabel tipo;
	private JLabel directorio;
	private JLabel anho;
	private JLabel calidad;
	private JLabel duracion;
	private JLabel snapshot;

	public Editar(){
		setModal(true);
		initGUI();
		setVisible(true);
	}
	
	private void initGUI() {
		try {
			{
				panelRoot = new JPanel();
				getContentPane().add(panelRoot, BorderLayout.CENTER);
				panelRoot.setPreferredSize(new java.awt.Dimension(707, 258));
				panelRoot.setLayout(null);
				panelRoot.setBackground(new java.awt.Color(128,128,128));
				{
					archivo = new JLabel();
					panelRoot.add(archivo);
					archivo.setText("Archivo:");
					archivo.setBounds(338, 50, 84, 14);
					archivo.setForeground(new java.awt.Color(255,255,255));
				}
				{
					snapshot = new JLabel();
					panelRoot.add(snapshot);
					snapshot.setText("Snapshot:");
					snapshot.setBounds(338, 77, 84, 14);
					snapshot.setForeground(new java.awt.Color(255,255,255));
				}
				{
					autor = new JLabel();
					panelRoot.add(autor);
					autor.setText("Autor:");
					autor.setBounds(27, 50, 74, 14);
					autor.setForeground(new java.awt.Color(255,255,255));
				}
				{
					titulo = new JLabel();
					panelRoot.add(titulo);
					titulo.setText("Titulo:");
					titulo.setBounds(27, 76, 74, 14);
					titulo.setForeground(new java.awt.Color(255,255,255));
				}
				{
					categoria = new JLabel();
					panelRoot.add(categoria);
					categoria.setText("Categoria:");
					categoria.setBounds(27, 102, 75, 14);
					categoria.setForeground(new java.awt.Color(255,255,255));
				}
				{
					duracion = new JLabel();
					panelRoot.add(duracion);
					duracion.setText("Duracion:");
					duracion.setBounds(27, 157, 74, 14);
					duracion.setForeground(new java.awt.Color(255,255,255));
				}
				{
					calidad = new JLabel();
					panelRoot.add(calidad);
					calidad.setText("Calidad:");
					calidad.setBounds(338, 156, 77, 14);
					calidad.setForeground(new java.awt.Color(255,255,255));
				}
				{
					anho = new JLabel();
					panelRoot.add(anho);
					anho.setText("Año:");
					anho.setBounds(27, 129, 74, 14);
					anho.setForeground(new java.awt.Color(255,255,255));
				}
				{
					directorio = new JLabel();
					panelRoot.add(directorio);
					directorio.setText("Directorio:");
					directorio.setBounds(338, 104, 84, 14);
					directorio.setForeground(new java.awt.Color(255,255,255));
				}
				{
					tipo = new JLabel();
					panelRoot.add(tipo);
					tipo.setText("Tipo Video:");
					tipo.setBounds(338, 128, 84, 14);
					tipo.setForeground(new java.awt.Color(255,255,255));
				}
				{
					autorTF = new JTextField();
					panelRoot.add(autorTF);
					autorTF.setBounds(113, 47, 180, 21);
				}
				{
					tituloTF = new JTextField();
					panelRoot.add(tituloTF);
					tituloTF.setBounds(113, 73, 180, 21);
				}
				{
					categoriaTF = new JTextField();
					panelRoot.add(categoriaTF);
					categoriaTF.setBounds(113, 99, 180, 21);
				}
				{
					anhoTF = new JTextField();
					panelRoot.add(anhoTF);
					anhoTF.setBounds(113, 125, 180, 21);
				}
				{
					duracionTF = new JTextField();
					panelRoot.add(duracionTF);
					duracionTF.setBounds(113, 151, 180, 21);
				}
				{
					archivoTF = new JTextField();
					panelRoot.add(archivoTF);
					archivoTF.setBounds(440, 47, 238, 21);
				}
				{
					snapTF = new JTextField();
					panelRoot.add(snapTF);
					snapTF.setBounds(440, 73, 238, 21);
				}
				{
					directorioTF = new JTextField();
					panelRoot.add(directorioTF);
					directorioTF.setBounds(440, 99, 238, 21);
				}
				{
					tipoTF = new JTextField();
					panelRoot.add(tipoTF);
					tipoTF.setBounds(440, 125, 180, 21);
				}
				{
					calidadTF = new JTextField();
					panelRoot.add(calidadTF);
					calidadTF.setBounds(440, 151, 180, 21);
				}
				{
					okBT = new JButton();
					panelRoot.add(okBT);
					okBT.setText("Registrar");
					okBT.setBounds(470, 225, 100, 21);
				}
				{
					cancelBT = new JButton();
					panelRoot.add(cancelBT);
					cancelBT.setText("Cancelar");
					cancelBT.setBounds(580, 225, 100, 21);
					cancelBT.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							setVisible(false);
						}
					});
				}
				{
					jLabel1 = new JLabel();
					panelRoot.add(jLabel1);
					jLabel1.setText("Registro Nuevo Video");
					jLabel1.setBounds(24, 16, 230, 24);
					jLabel1.setFont(new java.awt.Font("Tahoma",1,20));
					jLabel1.setForeground(new java.awt.Color(255,255,255));
				}
			}
			pack();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
