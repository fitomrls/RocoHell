package modell.factory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import modell.beans.Video;

public class FileManager {

	final File folder = new File("/home/you/Desktop");
	private String currentDirectory;
	private String workingDirectory;
	private File fileFolder;

	public FileManager(String directory) {
		File file = new File(".");
		workingDirectory = file.getAbsolutePath();
		currentDirectory = directory;
		// System.out.println("Current Working Directory: " +
		// file.getAbsolutePath());
		System.setProperty("user.dir", directory);// "D:/Videos/Musical/"
		// System.out.println("New Current Working Directory: " +
		// file.getAbsolutePath());
		fileFolder = new File(file.getAbsolutePath());
		 listFilesForFolder(new File(file.getAbsolutePath()));
	}

	public void listFilesForFolder(final File folder) {
		List<Video> listDir = null;
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				listDir = new ArrayList<Video>();

				Video video = new Video();
				video.setAutor("x");
//				String fileVideo = fileEntry.getName();
				if(fileEntry.isFile()&&fileEntry!=null){
					System.out.println("File: "+fileEntry.getName());	
				}
				
				/*String[] split = getFormatString(fileVideo);
				split[0] = split[0].trim();
				split[1] = split[1].trim();
				video.setAutor(split[0]);
				video.setTitulo(split[1].replaceFirst("[.][^.]+$", ""));
				video.setTypeFile(split[1].substring(split[1].lastIndexOf(".")));*/
				listDir.add(video);
			}
		}
		//return listDir;
	}

	public String[] getFormatString(String fileName) {
		return fileName.split("-");
	}

	// Alan Parsons Proyect - Time.mp4

	public String getCurrentDirectory() {
		return currentDirectory;
	}

	public void setCurrentDirectory(String currentDirectory) {
		this.currentDirectory = currentDirectory;
	}

	public String getWorkingDirectory() {
		return workingDirectory;
	}

	public void setWorkingDirectory(String workingDirectory) {
		this.workingDirectory = workingDirectory;
	}

	public File getFileFolder() {
		return fileFolder;
	}

	public void setFileFolder(File fileFolder) {
		this.fileFolder = fileFolder;
	}
}
