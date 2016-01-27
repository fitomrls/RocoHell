package view.admin;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;



import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.headless.*;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import view.admin.Admin;



public class ScanTool extends JFrame{
	
	static JButton myButton;
	static JProgressBar myProgressBar;
	static JLabel myUpdate;
	static JTextArea myTextArea;
	static HeadlessMediaPlayer mediaPlayer;
    static MediaPlayerFactory mediaPlayerFactory;
    static  BufferedImage bufImg;
    static File file4 ;
    static JFrame myFrame;


	static final String vlcAppPath = "C:\\Program Files (x86)\\VideoLAN\\VLC\\";
	//static final String vlcAppPath = "/Applications/VLC.app/Contents/MacOS/lib";
	static final String videosFolderPath= "D:\\Videos\\listo";
	//static final String videosFolderPath= "Users/nativuks/Movies/";
	//static final String snapsFolderPath =  "Users/nativuks/Movies/Snaps";
	static final String snapsFolderPath =  "D:\\snaps";


	
	public ScanTool(){
		
		 myFrame = new JFrame("Load videos..");
		

	      myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      Container myPane = myFrame.getContentPane();
	      myPane.setLayout(new GridBagLayout());
	      GridBagConstraints c = new GridBagConstraints();
	      setMyConstraints(c,0,0,GridBagConstraints.CENTER);
	      myPane.add(getFieldPanel(),c);
	      setMyConstraints(c,0,1,GridBagConstraints.CENTER);
	      myPane.add(getButtonPanel(),c);

	       myFrame.setLocationRelativeTo(null);

	      myFrame.pack();
	      myFrame.setVisible(true);
		
	}

   
   private static JPanel getFieldPanel() {
	      JPanel p = new JPanel(new GridBagLayout());
	      p.setBorder(BorderFactory.createTitledBorder(""));
	      GridBagConstraints c = new GridBagConstraints();
	      setMyConstraints(c,0,0,GridBagConstraints.EAST);
	      p.add(new JLabel("Progress:"),c);
	      setMyConstraints(c,1,0,GridBagConstraints.WEST);
	      myProgressBar = new JProgressBar(0, 100);
	      p.add(myProgressBar,c);
	      setMyConstraints(c,0,1,GridBagConstraints.EAST);
	      p.add(new JLabel("Last number:"),c);
	      setMyConstraints(c,1,1,GridBagConstraints.WEST);
	      myUpdate = new JLabel("");
	      p.add(myUpdate,c);
	      setMyConstraints(c,0,2,GridBagConstraints.EAST);
	      p.add(new JLabel("All numbers:"),c);
	      setMyConstraints(c,1,2,GridBagConstraints.WEST);
	      myTextArea = new JTextArea(10,40);
	      myTextArea.setLineWrap(true);
	      myTextArea.setFont(new Font("Courier",Font.PLAIN, 12));
	     // p.add(myTextArea,c);
	      return p;
	   }
	   private static JPanel getButtonPanel() {
	      JPanel p = new JPanel(new GridBagLayout());
	      myButton = new JButton("Run");
	      myButton.addActionListener(new MyButtonListener());
	      p.add(myButton);
	      return p;
	   }
	   private static void setMyConstraints(GridBagConstraints c,
	      int gridx, int gridy, int anchor) {
	      c.gridx = gridx;
	      c.gridy = gridy;
	      c.anchor = anchor;
	   }
   
 
   static class MyButtonListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         myButton.setText("Wait");
         myButton.setEnabled(false);
         myTextArea.setText("");
         MySwingWorker worker = new MySwingWorker();
         worker.addPropertyChangeListener(new MyProgressListener());
         worker.execute();
      }
   }

// My SwingWorker class to perform the task
   static class MySwingWorker
      extends SwingWorker<Integer[], Integer> {
	   
	  File file = new File(videosFolderPath);
	  File[] listOfFiles = file.listFiles();
      int total = listOfFiles.length;//;1000;
      
   
      int wait = 100;
      protected Integer[] doInBackground() {
         Integer[] l = new Integer[total];
         Random r = new Random();
         try {
            for (int i=0; i<total; i++) {
               Thread.sleep(wait);
               //task
               if(listOfFiles[i].isFile()){
                 getSnapshot(listOfFiles[i].getAbsolutePath());

                 publish(i);
                 System.out.println(listOfFiles[i].getName());
                setProgress((100*(i+1))/total);
               }
            }
            System.out.println("TotalFiles"+total);
         } catch (Exception e) {
            e.printStackTrace();
         }
         return l;
      }
      protected void process(java.util.List<Integer> v) {
         myUpdate.setText(""+(Integer)v.get(v.size()-1));
      }
      protected void done() {
         try {
            Integer[] r = get();
            String s = "";
            for (int i=0; i<r.length; i++) {
               s += " "+r[i];
            }
            //myTextArea.setText(s);
            myButton.setText("Run");
            myButton.setEnabled(true);
            myFrame.setVisible(false);

			//.setVisible(false);
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
   }

// My PropertyChangeListener class catch progress property changes
   static class MyProgressListener implements PropertyChangeListener {
      public void propertyChange(PropertyChangeEvent e) {
         if ("progress".equals(e.getPropertyName())) {
            myProgressBar.setValue((Integer)e.getNewValue());
         }
      }
   }
   
   static void getSnapshot(String mrl) throws IOException{
	   
	   
	   
	    NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), vlcAppPath);
	    Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
	    file4 = new File(mrl);

	    // Configure player factory.
       String[] VLC_ARGS = {
               "--intf", "dummy",          // no interface
               "--vout", "dummy",          // we don't want video (output)
               "--no-audio",               // we don't want audio (decoding)
               "--no-video-title-show",    // nor the filename displayed
               "--no-stats",               // no stats
               "--no-sub-autodetect-file", // we don't want subtitles
              
               "--no-disable-screensaver", // we don't want interfaces
                 // no blending in dummy vout
       };
       mediaPlayerFactory= new MediaPlayerFactory(VLC_ARGS);
       System.out.println("trace");

	   
	    mediaPlayer = mediaPlayerFactory.newHeadlessMediaPlayer();
	    mediaPlayer.setSnapshotDirectory(snapsFolderPath);
	    
	
	    mediaPlayer.enableMarquee(true);
	    mediaPlayer.startMedia(mrl);
	    mediaPlayer.setMarqueeSize(30);
	    mediaPlayer.setLogoLocation(100, 100);
	    mediaPlayer.setMarqueeText("Test");
	    mediaPlayer.setPosition(0.0010f);

	    try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    mediaPlayer.getSnapshot();
	    mediaPlayer.saveSnapshot();
	    mediaPlayer.stop();
	    mediaPlayer.release();
	    mediaPlayerFactory.release();
	

   }
   
   
	

}
