package view.player;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JWindow;

import modell.beans.AgentePlayer;
import modell.beans.Video;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.headless.HeadlessMediaPlayer;
import view.Main;

public class Player extends JWindow {

	private static final long serialVersionUID = 7910905308384682871L;
	private JPanel panelPlayer;
	private JPanel panelRoot;
	private JButton closeBT;
	private Canvas canvas;
	private Main main;
	private EmbeddedMediaPlayer media;
	
	public Player(Main main,String[] args, Video video, int ancho, int alto){
		super();
		this.main = main;
		
		panelRoot = new JPanel();
		getContentPane().add(panelRoot, BorderLayout.CENTER);
		panelRoot.setLayout(null);
		panelRoot.setPreferredSize(new java.awt.Dimension(ancho, alto));
		panelRoot.setBackground(Color.DARK_GRAY);
		
		closeBT = new JButton("Close");
		closeBT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				media.stop();
				terminado();
			}
		});
		
		closeBT.setBounds(0, 0, 80, 50);
		
        canvas = new Canvas();
        canvas.setBackground(Color.black);
        panelPlayer = new JPanel();
        panelPlayer.setLayout(new BorderLayout());
        panelPlayer.add(canvas, BorderLayout.CENTER);
        panelPlayer.setBounds(0,50, ancho, alto);
        panelRoot.add(panelPlayer, BorderLayout.CENTER);
        
        panelRoot.add(closeBT);
        
        main.setVisible(false);
        this.pack();
//        this.setAlwaysOnTop(true);
        this.setVisible(true);

        MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
        EmbeddedMediaPlayerComponent mediaPlayer = new EmbeddedMediaPlayerComponent();
        media = mediaPlayer.getMediaPlayer();
        media.setVideoSurface(mediaPlayerFactory.newVideoSurface(canvas));

        
        HeadlessMediaPlayer HeadMediaPlayer = mediaPlayerFactory.newHeadlessMediaPlayer();
        String mrl = video.getDirectory()+video.getFile();
        HeadMediaPlayer.startMedia(mrl);
        
        long time = HeadMediaPlayer.getLength();
        System.out.println("time1: "+time );
        HeadMediaPlayer.stop();
        
        
        media.addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
            @Override
            public void finished(MediaPlayer mediaPlayer) {
                System.out.println("fin!!!!!!!!!!!!!");
            }
        });
        AgentePlayer agentePlayer = new AgentePlayer( (int)time, this );
        //HeadMediaPlayer.startMedia(mrl);
        media.playMedia(video.getDirectory()+video.getFile(),args);
        agentePlayer.start();
        System.out.println("time2: "+media.getLength());
        
//        terminado();
                
	}
	
	public void terminado(){
		main.setVisible(true);
		main.volverVistas(true);
		this.setVisible(false);
	}
}
