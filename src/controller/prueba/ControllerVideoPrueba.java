package controller.prueba;

import java.util.List;

import modell.beans.Video;
import modell.factory.DataVideo;

public class ControllerVideoPrueba {

	public void mostrarVideos(){
		DataVideo dataVideo = new DataVideo();
		List<Video> lista = dataVideo.getAllVideos();
		
		for (Video video : lista) {
			System.out.println("Video: "+video.getAutor()+" - "+video.getTitulo()+"("+video.getYear()+")");
		}
	}
	
}
