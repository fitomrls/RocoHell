package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.EventListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

import modell.beans.AgenteCentro;
import modell.beans.AgenteCentroDerecha;
import modell.beans.AgenteCentroIzquierda;
import modell.beans.AgenteDerechaVacio;
import modell.beans.AgenteDerechoCentro;
import modell.beans.AgenteIzquierdaVacio;
import modell.beans.AgenteIzquierdoCentro;
import modell.beans.AgenteIzquierdoDerecha;
import modell.beans.AgenteVolverDerIzq;
import modell.beans.AgenteVolverIzqDer;
import modell.beans.Video;
import modell.factory.ListaCircular;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import view.player.CapaPlayer;
import view.player.Player;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import controller.ControllerVideo;

//public class Main extends JFrame implements Runnable {
public class Main extends JWindow {

    private static final long serialVersionUID = 1L;
    private final RootPane panelRoot;
    private final JLayeredPane panelVistas;
    private final JButton okButton;
    ListaCircular lista;
    final int CARD_DIMENSION_ANCHO = 600;
    final int CARD_DIMENSION_ALTO = 300;
    final Toolkit toolkit = Toolkit.getDefaultToolkit();
    final Dimension screenSize = toolkit.getScreenSize();
    final int ANCHORES = screenSize.width;
    final int ALTORES = screenSize.height;
    // (screenSize.height / 2) + 100;
    final int IZQX = 26;
    final int IZQY = 160;

    final int CENTROX = 328;
    final int CENTROY = 80;

    final int DERX = 930;
    final int DERY = 160;

    private Vista uno, selected;
    private EventListener listener;
    private final String[] args;
    private final Mensaje mensaje;
    private final CapaPlayer capaPlayer;
    boolean released = true;
    boolean avance = true;

    public Main(String[] args) {
        this.args = args;
		// setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        // setUndecorated(true);

        panelRoot = new RootPane();
        getContentPane().add(panelRoot, BorderLayout.CENTER);
//		panelRoot.setLayout(null);
        panelRoot.setPreferredSize(new java.awt.Dimension(ANCHORES, ALTORES));
        panelRoot.setBackground(Color.gray);

        panelVistas = new JLayeredPane();
//		panelVistas.setBounds(10, 55, ANCHORES - 100, 420);
        panelVistas.setPreferredSize(new java.awt.Dimension(1204, 420));
		// panelVistas.setBorder(new LineBorder(Color.gray ));

        /*
         * listener = new VistaListener(this);
         * panelVistas.addMouseListener((MouseListener) listener);
         * panelVistas.addMouseMotionListener((MouseMotionListener) listener);
         */
        setListaVistas();
        setFirst();

        okButton = new JButton("close");
//		okButton.setBounds(0, 0, 90, 35);
        okButton.setPreferredSize(new java.awt.Dimension(90, 35));
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				// ControllerVideo ctlrlVideo = new ControllerVideo();
                // ctlrlVideo.setDataVideosDirectory();
                // setFirst();
                System.exit(0);
            }
        });

        mensaje = new Mensaje(this);
        capaPlayer = new CapaPlayer(this);

        setMensaje();
        setCapaPLayer();

        panelRoot.add(panelVistas, BorderLayout.CENTER);
        panelRoot.add(okButton, BorderLayout.NORTH);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        pack();

    }

    private void setListaVistas() {
        lista = new ListaCircular();
        ControllerVideo contVideo = new ControllerVideo();
        contVideo.getVideos(this, "autor");
    }

    public void addVista(Vista vista) {
        lista.insertarUltimo(vista, this);
    }

    private void setFirst() {
        uno = lista.getRaiz();
        uno.setEjeX(IZQX);
        uno.setEjeY(IZQY);
        uno.setHalfScaleIcon();
        uno.setLugar(1);
        panelVistas.add(uno, new Integer(uno.getProfundidad()));

        uno.getSig().setEjeX(CENTROX);
        uno.getSig().setEjeY(CENTROY);
        uno.getSig().setNormalScaleIcon();
        uno.getSig().setProfundidad(0);
        uno.getSig().setLugar(2);
        panelVistas.add(uno.getSig(),
                new Integer(uno.getSig().getProfundidad()));
        selected = uno.getSig();

        uno.getSig().getSig().setEjeX(DERX);
        uno.getSig().getSig().setEjeY(DERY);
        uno.getSig().getSig().setHalfScaleIcon();
        uno.getSig().getSig().setLugar(3);
        panelVistas.add(uno.getSig().getSig(), new Integer(uno.getSig()
                .getSig().getProfundidad()));

    }

    public void addVistas() {
        ControllerVideo contVideo = new ControllerVideo();
        lista = new ListaCircular();
        Random randomGenerator = new Random();
        int randomInt = 0;
        int contador = 0;
        int tabx = IZQX;
        for (Video video : contVideo.getVideosXAutor()) {
            randomInt = randomGenerator.nextInt(9);
            if (randomInt == 0) {
                randomInt++;
            }
            Vista vista;
            System.out.println("tabx: " + tabx);
            if (contador == 1) {
                System.out.println("central!");
                vista = new Vista(randomInt + ".jpg", tabx, IZQY,
                        CARD_DIMENSION_ANCHO, CARD_DIMENSION_ALTO, 0, this);
                tabx = tabx + CARD_DIMENSION_ANCHO - 50;
            } else {
                vista = new Vista(randomInt + ".jpg", tabx, IZQY,
                        CARD_DIMENSION_ANCHO / 2, CARD_DIMENSION_ALTO / 2, -1,
                        this);
                tabx = tabx + (CARD_DIMENSION_ANCHO / 2) - 50;
            }
            vista.setVideo(video);
            // lista.insertarUltimo(vista);
            if (contador == 0 || contador == 1 || contador == 2) {
                vista.setBounds(vista.getEjeX(), vista.getEjeY(), vista
                        .getAncho(), vista.getAlto());
                panelVistas.add(vista, new Integer(vista.getProfundidad()));
                vista.setEstado(true);
            } else {
                vista.setEstado(false);
            }

            contador++;
        }
    }

    public static void main(final String[] args) {
        final String vlcDir = "C:\\Program Files (x86)\\VideoLAN\\VLC\\";
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), vlcDir);
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main main = new Main(args);
//				 main.setAlwaysOnTop(true);
                main.setVisible(true);
            }
        });

    }

    public void moverFrente(Vista vista) {
        panelVistas.setPosition(vista, 0);
    }

    public void moverAtras(Vista vista) {
        vista.setProfundidad(-1);
        // panelVistas.setPosition(vista,vista.getProfundidad());
        panelVistas.setLayer(vista, vista.getProfundidad());
        panelVistas.updateUI();
    }

    public void moveCenter(Vista vista) {
        AgenteIzquierdaVacio avacio = new AgenteIzquierdaVacio(vista.getAnt());
        avacio.start();
    }

    public void agregarVista(Vista vista) {
        // vista.setNormalScaleIcon();
        System.out.println("lugar:"+vista.getLugar());
        System.out.println("profundidad:"+vista.getProfundidad());
        System.out.println("ejeX:"+vista.getEjeX());
        System.out.println("ejeY:"+vista.getEjeY());
        System.out.println("video:"+vista.getVideo().getAutor()+"-"+vista.getVideo().getTitulo());
        panelVistas.add(vista, new Integer(vista.getProfundidad()));
        vista.setEstado(true);
    }

    public boolean componenteEsta(Vista vista) {
        Component componets[] = panelVistas.getComponents();
        for (Component component : componets) {
            if (component == vista) {
                return true;
            }
        }
        return false;
    }

    public void moverDerechaAizquierda(Vista vista) {
        AgenteIzquierdaVacio agenteIzquierdaVacio = new AgenteIzquierdaVacio(
             vista.getAnt().getAnt());
        agenteIzquierdaVacio.start();
        avance = false;
    }

    private void cambiarLugarVista(Vista vista) {
        vista.setEjeX(vista.getEjeX() - 3);
        vista.setEjeY(IZQY);
        vista.setBounds(vista.getEjeX(), vista.getEjeY(), vista.getAncho(),
                vista.getAlto());
    }

    public void moverAnteriorAlCentro(Vista vista) {
        AgenteCentro agenteCentro = new AgenteCentro(vista);
        agenteCentro.start();
    }

    public void moverIzquierdoAderecha(Vista vista) {
        AgenteIzquierdoDerecha agenteIzq = new AgenteIzquierdoDerecha(vista
                .getSig().getSig());
        agenteIzq.start();
    }

    public void moverIzquierdoAlCentro(Vista vista) {
        AgenteIzquierdoCentro agenteIzcentro = new AgenteIzquierdoCentro(vista);
        // AgenteDerecho agenteDerecho = new AgenteDerecho(vista);
        agenteIzcentro.start();
    }

    public void agregarAlaDerechaOmostrar(Vista vista) {
        vista.setEjeX(DERX);
        vista.setEjeY(DERY);
        vista.setHalfScaleIcon();
        if (!vista.getEstado()) {
            if (vista != lista.getRaiz()) {
                if (componenteEsta(vista)) {
                    vista.setVisible(true);
                } else {
                    System.out.println("ADD");
                    agregarVista(vista);
                }
            } else {
                System.out.println("ROOT");
                vista.setVisible(true);
            }
        } else {
            vista.setVisible(true);
        }
        vista.setLugar(3);
    }

    public void agregarAlaIzquierdaOmostrar(Vista vista) {

        vista.setEjeX(IZQX);
        vista.setEjeY(IZQY);
        vista.setHalfScaleIcon();
        if (!vista.getEstado()) {
            if (vista != lista.getRaiz()) {
                if (componenteEsta(vista)) {
                    vista.setVisible(true);
                } else {
                    System.out.println("ADD");
                    agregarVista(vista);
                }
            } else {
                System.out.println("ROOT");
                vista.setVisible(true);
            }
        } else {
            vista.setVisible(true);
        }
        vista.setLugar(1);
    }

    public int getCARD_DIMENSION_ANCHO() {
        return CARD_DIMENSION_ANCHO;
    }

    public int getCARD_DIMENSION_ALTO() {
        return CARD_DIMENSION_ALTO;
    }

    public int getIZQX() {
        return IZQX;
    }

    public int getIZQY() {
        return IZQY;
    }

    public int getCENTROX() {
        return CENTROX;
    }

    public int getCENTROY() {
        return CENTROY;
    }

    public int getDERX() {
        return DERX;
    }

    public int getDERY() {
        return DERY;
    }

    public void moverCentroIzquierda(Vista vista) {
        AgenteCentroIzquierda agenteCentroIzquierda = new AgenteCentroIzquierda(
                vista);
        agenteCentroIzquierda.start();
    }

    public void moverDerechoAcentro(Vista vista) {
        AgenteDerechoCentro agenteDerechoCentro = new AgenteDerechoCentro(vista);
        agenteDerechoCentro.start();
    }

    public Vista getUno() {
        return uno;
    }

    public void setUno(Vista uno) {
        this.uno = uno;
    }

    public EventListener getListener() {
        return listener;
    }

    public void moverLibre(int x) {

    }

    public Vista getVistaLugar(int lugar) {
        Vista vista = null;
        if (!lista.vacia()) {
            Vista reco = lista.getRaiz();
            do {
                if (reco.getLugar() == lugar) {
                    vista = reco;
                    return vista;
                }
                reco = reco.getSig();
            } while (reco != lista.getRaiz());
            System.out.println();
        }
        return vista;
    }

    public void moverCentroDerecha(Vista vista) {
        AgenteCentroIzquierda agenteCentroIzq = new AgenteCentroIzquierda(vista);
        agenteCentroIzq.start();
    }

    private void volverTresDerechaIzquierda(Vista vista) {
        AgenteVolverDerIzq agenteVolverDerIzq = new AgenteVolverDerIzq(vista);
        agenteVolverDerIzq.setAlcance(936);
        agenteVolverDerIzq.start();
    }

    private void volverTresIzquierdaDerecha(Vista vista) {
        AgenteVolverIzqDer agenteVolverIzqDer = new AgenteVolverIzqDer(vista);
        agenteVolverIzqDer.setAlcance(924);
        agenteVolverIzqDer.start();
    }

    private void volverDosIzquierdaDerecha(Vista vista) {
        AgenteVolverIzqDer agenteVolverIzqDer = new AgenteVolverIzqDer(vista);
        agenteVolverIzqDer.setAlcance(322);
        agenteVolverIzqDer.start();
    }

    private void volverDosDerechoIzquierda(Vista vista) {
        AgenteVolverDerIzq agenteVolverDerIzq = new AgenteVolverDerIzq(vista);

        agenteVolverDerIzq.setAlcance(334);
        agenteVolverDerIzq.start();
    }

    public void moverDerechaAvacio(Vista vista) {
        AgenteDerechaVacio agenteDerechaVacio = new AgenteDerechaVacio(vista);
        agenteDerechaVacio.start();
    }

    public void moverCentroAderecha(Vista vista) {
        AgenteCentroDerecha agenteCentroDerecha = new AgenteCentroDerecha(vista);
        agenteCentroDerecha.start();
    }

    public void cambiarProfundidad(Vista vista, int prof) {
        vista.setProfundidad(prof);
        panelVistas.setLayer(vista, vista.getProfundidad());
    }

    public void draggingLeft(Vista vista) {
        capaPlayer.setVisible(false);
        vista.setEjeX(vista.getEjeX() - 10);
    }

    public void draggingRigth(Vista vista) {
        capaPlayer.setVisible(false);
        vista.setEjeX(vista.getEjeX() + 10);
    }

    public void dragging(int ind, Vista vista) {
        capaPlayer.setVisible(false);
        if (ind == -1) { // izquierda
			/*if (vista.getLugar() == 1) {
             System.out.println("Drag#1");
             if (vista.getEjeX() > -150) {
             vista.setEjeX(vista.getEjeX() - 6);
             }
             }*/
            if (vista.getLugar() == 2) {
                if (vista.getEjeX() > 270) {
                    vista.getAnt().setProfundidad(-1);
                    panelVistas.setLayer(vista.getAnt(), vista.getAnt()
                            .getProfundidad());
                    vista.setProfundidad(0);
                    panelVistas.setLayer(vista, vista.getProfundidad());
                    vista.setEjeX(vista.getEjeX() - 6);
                }
            }
            /*if (vista.getLugar() == 3) {
             vista.getAnt().setProfundidad(-1);
             panelVistas.setLayer(vista.getAnt(), vista.getAnt()
             .getProfundidad());
             vista.setProfundidad(0);
             panelVistas.setLayer(vista, vista.getProfundidad());
             if (vista.getEjeX() > 875) {
             vista.setEjeX(vista.getEjeX() - 6);
             }
             }*/

        } else { // derecha
			/*if (vista.getLugar() == 1) {
             if (vista.getEjeX() < 85) {// no mover mas alla
             vista.getSig().setProfundidad(-1);
             panelVistas.setLayer(vista.getSig(), vista.getSig()
             .getProfundidad());
             vista.setProfundidad(0);
             panelVistas.setLayer(vista, vista.getProfundidad());
             vista.setEjeX(vista.getEjeX() + 6);
             }
             }*/
            if (vista.getLugar() == 2) {
                if (vista.getEjeX() < 380) {// no mover mas alla
                    vista.setEjeX(vista.getEjeX() + 6);
                }
            }
            /*if (vista.getLugar() == 3) {
             if (vista.getEjeX() < 1060) {// no mover mas alla
             vista.setEjeX(vista.getEjeX() + 6);
             }
             }*/
        }
        vista.setBounds(vista.getEjeX(), vista.getEjeY(), vista.getAncho(),
                vista.getAlto());
        // System.out.println("ejeX:"+vista.getEjeX());
    }

    public void released(Vista vista) {
		// noPlay();
		/*if (vista.getLugar() == 1) {
         if (vista.getEjeX() <= -40) {
         moverCentroDerecha(vista.getSig());
         vista.setLugar(-1);
         vista.setVisible(false);
         } else {
         volverUnoOrigenIzquierdaDer(vista);
         }
         if (vista.getEjeX() >= 68) {
         moverIzquierdoAderecha(vista);
         } else {
         volverUnoOrigenDerIzquierda(vista);
         }
         }*/
        if (vista.getLugar() == 2) {
            if (vista.getEjeX() <= 280) {
                moveCenter(vista);
            } else {
                volverDosIzquierdaDerecha(vista);
            }
            if (vista.getEjeX() >= 375) {
                moverCentroAderecha(vista.getSig());
            } else {
                volverDosDerechoIzquierda(vista);
            }
        }
        /*if (vista.getLugar() == 3) {
         if (vista.getEjeX() <= 900) {
         moverDerechaAizquierda(vista);
         } else {
         volverTresIzquierdaDerecha(vista);
         }
         if (vista.getEjeX() >= 960) {
         moverDerechaAvacio(vista);
         } else {
         volverTresDerechaIzquierda(vista);
         }
         }*/
    }

    public void devolverAlsitio25(Vista vista) {
        if (vista.getLugar() == 1 || vista.getLugar() == 3) {
            vista.setProfundidad(-1);
            panelVistas.setLayer(vista, vista.getProfundidad());
        } else {
            if (vista.getLugar() == 2) {
                vista.setProfundidad(1);
                panelVistas.setLayer(vista, vista.getProfundidad());
            }
        }
    }

    public Vista getSelected() {
        return this.selected;
    }

    public void volverUnoOrigenDerIzquierda(Vista vista) {
        AgenteVolverDerIzq agenteVolverDerIzq = new AgenteVolverDerIzq(vista);
        agenteVolverDerIzq.setAlcance(32);
        agenteVolverDerIzq.start();
    }

    public void volverUnoOrigenIzquierdaDer(Vista vista) {
        AgenteVolverIzqDer agenteVolverIzqDer = new AgenteVolverIzqDer(vista);
        agenteVolverIzqDer.setAlcance(26);
        agenteVolverIzqDer.start();
    }

    public void ponelePlay(Vista vista) {
//		playButton.setEnabled(true);
//		playButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/play.png")));
        capaPlayer.setVisible(true);

    }

    public void noPlay() {
        selected = null;
//		playButton.setEnabled(false);
//		playButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/playdef.png")));
        capaPlayer.setVisible(false);
    }

    public void setSelectedVideo(Vista vista) {
        selected = vista;
    }

    public void reproducir() {
        if (selected != null) {
            volverVistas(false);
            capaPlayer.setVisible(false);
            mensaje.setVisible(true);
        }
    }

    public void play() {
        if (selected != null) {
            Player player = new Player(this, this.args, selected.getVideo(),
                    ANCHORES, ALTORES);
        }
    }

    private void setMensaje() {
        panelVistas.add(mensaje, new Integer(3));
        mensaje.setBounds(CENTROX, CENTROY, CARD_DIMENSION_ANCHO,
                CARD_DIMENSION_ALTO);
        mensaje.setVisible(false);
    }

    private void setCapaPLayer() {
        panelVistas.add(capaPlayer, new Integer(3));
        capaPlayer.setBounds(CENTROX, CENTROY, CARD_DIMENSION_ANCHO,
                CARD_DIMENSION_ALTO);
        capaPlayer.setVisible(false);
    }

    public void volverVistas(boolean log) {
        released = log;
        capaPlayer.setVisible(!log);
    }

    public boolean isReleased() {
        return released;
    }

    public void setReleased(boolean released) {
        this.released = released;
    }

    public boolean isAvance() {
        return avance;
    }

    public void setAvance(boolean avance) {
        this.avance = avance;
    }

    public void moverIzquierdoVacio(Vista vista) {
        AgenteIzquierdaVacio agenteIzquierdaVacio = new AgenteIzquierdaVacio(vista.getAnt().getAnt());
        agenteIzquierdaVacio.start();
    }
    
    

}// fin
