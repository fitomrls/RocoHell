package modell.factory;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modell.beans.Video;
import view.Main;
import view.Vista;
import view.VistaListener;

public class DataVideo {
	
	final File folder = new File("/home/you/Desktop");
	private String currentDirectory;
	private String workingDirectory;

	public List<Video> getVideosXorden(String order) {
		Connection conn = null;
		ResultSet resultSet = null;
		List<Video> myList = new ArrayList<Video>();
		try {			
			conn = ConnectionFactory.getInstance().getConnection();
			Statement stmt = conn.createStatement();
			resultSet = stmt.executeQuery("SELECT * FROM VIDEO order by "+order);

			while (resultSet.next()) {
				Video video = new Video();
				video.setId(resultSet.getInt("id"));
				video.setAutor(resultSet.getString("autor"));
				video.setTitulo(resultSet.getString("titulo"));
				video.setDirectory(resultSet.getString("directory"));
				video.setFile(resultSet.getString("file"));
				myList.add(video);
			}
			resultSet.close();
			// stmt.close();
			conn.close();
		} catch (Exception e) {
			System.err.println("DataVideo.getAllVideos:"
					+ e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return myList;
	}
	
	public List<Video> getAllVideos() {
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement prepareStt = null;
		List<Video> myList = new ArrayList<Video>();
		try {
			conn = ConnectionFactory.getInstance().getConnection();
			// Statement stmt = conn.createStatement();
			// resultSet = stmt.executeQuery("SELECT * FROM USUARIO;");
			prepareStt = conn
					.prepareStatement("SELECT * FROM VIDEO WHERE year=?");
			prepareStt.setInt(1, 2013);
			resultSet = prepareStt.executeQuery();
			while (resultSet.next()) {
				Video video = new Video();
				video.setId(resultSet.getInt("id"));
				video.setAutor(resultSet.getString("autor"));
				video.setTitulo(resultSet.getString("titulo"));
				video.setYear(resultSet.getInt("year"));
				myList.add(video);
			}
			resultSet.close();
			// stmt.close();
			conn.close();
		} catch (Exception e) {
			System.err.println("DataVideo.getAllVideos:"
					+ e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return myList;
	}

	public boolean insertVideo(Video nuevoVideo) throws Exception {

		PreparedStatement stmt = null;
		Connection conn = null;
		boolean res = false;

		try {
			conn = ConnectionFactory.getInstance().getConnection();
			stmt = conn
					.prepareStatement("INSERT INTO video(file,autor,titulo,directory,typeFile)"
							+ "values(?,?,?,?,?)");
			stmt.setString(1, nuevoVideo.getFile());
			stmt.setString(2, nuevoVideo.getAutor().trim());
			stmt.setString(3, nuevoVideo.getTitulo().trim());
			stmt.setString(4, nuevoVideo.getDirectory());
			stmt.setString(5, nuevoVideo.getTypeFile());
			stmt.executeUpdate();
			res = true;
		} finally { // cleanup
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException ex) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					System.out.println("DataVideo: " + ex
							+ ", insertVideo fail"); // log and
				}
			}
		}
		return res;
	}

	public void scanInfoVideos(String directory){
		File file = new File(".");
		workingDirectory = file.getAbsolutePath();
		currentDirectory = directory;
		//System.setProperty("user.dir", directory);
//		fileFolder = new File(file.getAbsolutePath());
		saveListFilesForFolder(new File(directory));
	}
	
	private void saveListFilesForFolder(final File folder) {
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				saveListFilesForFolder(fileEntry);
			} else {
				String fileVideo = fileEntry.getName();
				// System.out.println("\""+fileVideo+"\""+"," );
				String[] split = fileVideo.split("-");
				Video video = new Video();
				if ( split.length==2) {
					video.setAutor(split[0]);
					video.setTitulo(split[1].replaceFirst("[.][^.]+$", ""));
					video.setTypeFile(split[1].substring(split[1].lastIndexOf(".")));
					video.setFile(fileVideo);
					video.setDirectory(currentDirectory);
					
				}else{
					video.setAutor(fileVideo);
					video.setTitulo(fileVideo.replaceFirst("[.][^.]+$", ""));
					video.setTypeFile(fileVideo.substring(fileVideo.lastIndexOf(".")));
					video.setFile(fileVideo);
					video.setDirectory(currentDirectory);
				}
				try {
					insertVideo(video);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public String[] getFormatString(String fileName) {
		String[] split = fileName.split("-");
		if (split.length == 2) {
			return split;
		} else {
			if(split.length>2){
				System.out.println("Nombre de archivo sin formato:  Autor - Titulo.xxx");
			}else{
				System.out.println("Autor o Titulo ausentes");
			}
			return null;
		}
	}

	public void getVideos(Main main, String order) {
		Connection conn = null;
		ResultSet resultSet = null;

		try {			
			conn = ConnectionFactory.getInstance().getConnection();
			Statement stmt = conn.createStatement();
			resultSet = stmt.executeQuery("SELECT * FROM VIDEO order by "+order);
//			Random randomGenerator = new Random();
//			int numer = 1;
			while (resultSet.next()) {
				Video video = new Video();
				video.setId(resultSet.getInt("id"));
				video.setAutor(resultSet.getString("autor"));
				video.setTitulo(resultSet.getString("titulo"));
				video.setDirectory(resultSet.getString("directory"));
				video.setFile(resultSet.getString("file"));
				video.setThumb(resultSet.getString("thumb"));
//				int randomInt = randomGenerator.nextInt(9);
				//System.out.print("randomInt:"+randomInt);
				Vista vista = new Vista(video.getThumb(),0,0,
						main.getCARD_DIMENSION_ANCHO(),main.getCARD_DIMENSION_ALTO(),
						-1, main);
				vista.setVideo(video);
				VistaListener listener = new VistaListener(main, vista);
				vista.addMouseListener((MouseListener) listener);
				vista.addMouseMotionListener((MouseMotionListener) listener);
//				vista.addMouseListener((MouseListener) main.getListener());
//				vista.initGUI();
				main.addVista(vista);
//				numer++;
			}
			resultSet.close();
			// stmt.close();
			conn.close();
		} catch (Exception e) {
			System.err.println("DataVideo.getVideos:"
					+ e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	
	public Object[][] getFilasVideos() {
		Connection conn = null;
		ResultSet resultSet = null;
		Object[][] filas = {};
		int nfilas = getNumeroFilas();
		try {
			conn = ConnectionFactory.getInstance().getConnection();
			Statement stmt = conn.createStatement();
			resultSet = stmt.executeQuery(" SELECT * FROM VIDEO ");
			int iContador = 0;
			
			filas = new Object[nfilas][8];

			String[] columnas_Name = { "###", "autor", "titulo",
					"categoria", "duracion", "directorio", "archivo",
					"snap" };
			
			while (resultSet.next()) {
				filas[iContador][0] = iContador + 1;
				filas[iContador][1] = resultSet.getString("autor");
				filas[iContador][2] = resultSet.getString("titulo");
				if(resultSet.getString("genero")==null ){
					filas[iContador][3] = "";
				}else{
					filas[iContador][3] = resultSet.getString("genero");
				}
				if(resultSet.getString("duracion")==null ){
					filas[iContador][4] = "";
				}else{
					filas[iContador][4] = resultSet.getString("duracion");
				}
				
				filas[iContador][5] = resultSet.getString("directory")+"";
				filas[iContador][6] = resultSet.getString("file");
				if(resultSet.getString("thumb")==null ){
					filas[iContador][7] = "";
				}else{
					filas[iContador][7] = resultSet.getString("thumb");
				}
				
				iContador++;
			}

			resultSet.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.err.println("DataVideo.getFilasVideos:"
					+ e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return filas;
	}
		
	private int getNumeroFilas(){
		Connection conn = null;
		ResultSet resultSet = null;
		int filas = 0;
		try {
			conn = ConnectionFactory.getInstance().getConnection();
			Statement stmt = conn.createStatement();
			resultSet = stmt.executeQuery("SELECT count(id) as filas FROM VIDEO ");

			if(resultSet.next()) {
				filas = resultSet.getInt("filas");
			}

			resultSet.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.err.println("DataVideo.getFilasVideos:"
					+ e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return filas;
	}

	public Object[][] buscarVideos(String keyword) {
		
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet resultSet = null;
		Object[][] filas = {};
		try {
			conn = ConnectionFactory.getInstance().getConnection();
			Statement stmt = conn.createStatement();
			ptmt = conn.prepareStatement(" SELECT * FROM VIDEO WHERE  autor like'%"+keyword+"%' or  titulo like'%"+keyword+"%'");
			resultSet = ptmt.executeQuery();
			int iContador = 0;
			int nfilas = getNumeroFilasBusqueda(keyword);
			filas = new Object[nfilas][9];

			while (resultSet.next()) {
				filas[iContador][0] = iContador + 1;
				filas[iContador][1] = resultSet.getString("autor");
				filas[iContador][2] = resultSet.getString("titulo");
				if(resultSet.getString("genero")==null ){
					filas[iContador][3] = "";
				}else{
					filas[iContador][3] = resultSet.getString("genero");
				}
				if(resultSet.getString("duracion")==null ){
					filas[iContador][4] = "";
				}else{
					filas[iContador][4] = resultSet.getString("duracion");
				}
				
				filas[iContador][5] = resultSet.getString("directory")+"";
				filas[iContador][6] = resultSet.getString("file");
				if(resultSet.getString("thumb")==null ){
					filas[iContador][7] = "";
				}else{
					filas[iContador][7] = resultSet.getString("thumb");
				}
				iContador++;
			}

			resultSet.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.err.println("DataVideo.buscarVideos:"
					+ e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return filas;

	}

	private int getNumeroFilasBusqueda(String keyword){
		Connection conn = null;
		ResultSet resultSet = null;
		int filas = 0;
		try {
			conn = ConnectionFactory.getInstance().getConnection();
			Statement stmt = conn.createStatement();
			resultSet = stmt.executeQuery(" SELECT count(id) as filas FROM VIDEO WHERE  autor like'%"+keyword+"%' or  titulo like'%"+keyword+"%'");
			if(resultSet.next()) {
				filas = resultSet.getInt("filas");
			}

			resultSet.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.err.println("DataVideo.getFilasVideosBusqueda:"
					+ e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return filas;
	}

	
}
