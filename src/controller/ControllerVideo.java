package controller;

import java.util.List;

import view.Main;

import modell.beans.Video;
import modell.factory.DataVideo;
import modell.factory.ListaCircular;

public class ControllerVideo {

	DataVideo dataVideo = new DataVideo();
	
	public void setDataVideosDirectory(){
//		dataVideo.scanInfoVideos("D:/Videos/Musical/");

	}

	public List<Video> getVideosXAutor() {
		return dataVideo.getVideosXorden("autor asc");
	}

	public void getVideos(Main main,  String order) {
		dataVideo.getVideos(main, order);
	}
	
	public Object[][] getFilasVideos(){
		return dataVideo.getFilasVideos(); 
	}
	
	public Object[][] getResultadosBusqueda(String keyword){
		return dataVideo.buscarVideos(keyword);
	}
}
