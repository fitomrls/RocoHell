package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Objects;
import javax.swing.*;
import modell.beans.Video;

public class Vista extends JPanel {

    private static final long serialVersionUID = 5417200999431760031L;
    private String img;
    private int ejeX = 0, ejeY = 0;
    private int ancho = 1, alto = 1;
    private Vista ant, sig;
    private int profundidad = -1;
    private boolean estado = false;
    private Video video;
    Main main;
    BufferedImage backImage = null;
    int xInicio = 0, xFin = 0;
    private int lugar = 0;
    private ImageIcon imageIcon;
    private Image image;

    public Vista(String img, int ejeX, int ejeY, int ancho, int alto,
            int profundidad, Main main) {
        this.ejeX = ejeX;
        this.ejeY = ejeY;
        this.ancho = ancho;
        this.alto = alto;
        this.profundidad = profundidad;
        estado = false;
        this.img = img;
        this.main = main;
        initGUI();
    }

    public Vista() {
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(ancho, alto);
    }

    public void setAlpha(float alpha) {
        // this.alpha = alpha;
        repaint();
    }

    public void initGUI() {
        this.setLayout(null);
        /*try {
         File file = new File("bin/images/" + img);
         backImage = ImageIO.read(file);
         } catch (IOException e) {
         e.printStackTrace();
         }*/
        imageIcon = new ImageIcon(getClass().getClassLoader().getResource("images/" + img));
         image = imageIcon.getImage();
        // Image.SCALE_SMOOTH);
//		this.setBackground(Color.red);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//		setForeground(new Color(0, 0, 0, 0));
        Graphics2D g2 = (Graphics2D) g;
        g2.drawString(video.getAutor() + " - " + video.getTitulo(), 10, 15);
        /*		if (backImage != null) {
         g2.drawImage(backImage, 0, 20, this.getAncho(), this.getAlto(),	this);
         }*/
        if (image!= null) {
            g2.drawImage(image, 0, 20, this.getAncho(), this.getAlto(), this);
        }
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getEjeX() {
        return ejeX;
    }

    public void setEjeX(int ejeX) {
        this.ejeX = ejeX;
    }

    public int getEjeY() {
        return ejeY;
    }

    public void setEjeY(int ejeY) {
        this.ejeY = ejeY;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
        // setImageScale();
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
        // setImageScale();
    }

    public Vista getAnt() {
        return ant;
    }

    public void setAnt(Vista ant) {
        this.ant = ant;
    }

    public Vista getSig() {
        return sig;
    }

    public void setSig(Vista sig) {
        this.sig = sig;
    }

    @Override
    public boolean equals(Object obj) {
        boolean response = false;
        if (obj instanceof Vista) {
            String objImg = ((Vista) obj).img;
            int objX = ((Vista) obj).ejeX;
            int objY = ((Vista) obj).ejeY;
            int objAncho = ((Vista) obj).ancho;
            int objAlto = ((Vista) obj).alto;
            int objLugar = ((Vista) obj).profundidad;

            response = objX == ejeX && objY == ejeY && objAncho == ancho
                    && objAlto == alto && objLugar == profundidad
                    && objImg.equals(img);
        }

        return response;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.ejeX;
        hash = 29 * hash + this.ejeY;
        hash = 29 * hash + this.ancho;
        hash = 29 * hash + this.alto;
        hash = 29 * hash + this.profundidad;
        hash = 67 * hash + Objects.hashCode(this.img);
        return hash;
    }

    public int getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(int profundidad) {
        this.profundidad = profundidad;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void opacarLabel() {

    }

    public void setHalfScaleIcon() {
        ancho = main.getCARD_DIMENSION_ANCHO() / 2;
        alto = main.getCARD_DIMENSION_ALTO() / 2;
        this.setBounds(ejeX, ejeY, ancho, alto);
        repaint();
    }

    public void setNormalScaleIcon() {
        ancho = main.getCARD_DIMENSION_ANCHO();
        alto = main.getCARD_DIMENSION_ALTO();
        this.setBounds(ejeX, ejeY, ancho, alto);
        repaint();
    }

    public void moverAtras() {
        main.moverAtras(this);
    }

    public void moverIzquierdoAderecha() {
        main.moverIzquierdoAderecha(this);
    }

    public void moverCenter() {
        main.moveCenter(this);
    }

    public void moverDerechaAizquierda() {
        main.moverDerechaAizquierda(this);
    }

    public void setLugar(int lugar) {
        this.lugar = lugar;
    }

    public int getLugar() {
        return lugar;
    }}
