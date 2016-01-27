package view;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.SwingUtilities;

/**
 * @author Adolf
 */
public class VistaListener implements MouseListener, MouseMotionListener {

    private final Main main;
    private Vista vista;
    private int xPress = 0;
    boolean pressed = false;

    public VistaListener(Main main, Vista vista) {
        this.main = main;
        this.vista = vista;
    }

    @Override
    public void mousePressed(MouseEvent event) {
        ///if (!SwingUtilities.isRightMouseButton(event)) {
        if (vista.getMain().isReleased()) {
            xPress = event.getX();
        }
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        //System.out.println("Xpress:" + xPress + ", Dragged:" + event.getX());
        if (!SwingUtilities.isRightMouseButton(event)) {
            if (main.isReleased()) {
                if (vista.getLugar() == 2) {
                    if (xPress > 0) {
                        Point point = event.getPoint();
                        System.out.println("Dragged");
                        //System.out.println("left");
                        //main.draggingLeft(vista);
                    }
                    //System.out.println("MouseDragged("+vista.getLugar()+")");
                }
                /*if (vista.getEjeX() == 328) {
                 if (xPress >= 328 && xPress <= 928) {
                 if(event.getX()<=928&&event.getY()>=328){
                 if (event.getX() < xPress) {
                 //main.dragging(-1, vista);// izquierda
                 System.out.println("left");
                 } else {
                 // main.dragging(1, vista);// derecha
                 System.out.println("rigth");
                 }
                 }

                 }
                 }*/
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        if (!SwingUtilities.isRightMouseButton(event)) {
            if (main.isReleased()) {
                if (vista.main.isAvance()) {
                    if (!SwingUtilities.isRightMouseButton(event)) {
                        System.out.println("<Pressed>(" + event.getX() + ")");
                        //System.out.println("<MousePressed>("+vista.getLugar()+")");
                        if (vista.getLugar() == 1) { //if (vista.getEjeX() == 26) {
                            main.noPlay();
                            main.setAvance(false);
                            main.moverIzquierdoAderecha(vista);
                        } else {
                            if (vista.getLugar() == 2) { //if (vista.getEjeX() == 328) {
                                main.setSelectedVideo(vista);
                                main.ponelePlay(vista);
                            } else {
                                if (vista.getLugar() == 3) { //if (vista.getEjeX() == 930) {
                                    main.noPlay();
                                    //main.moverDerechaAizquierda(vista);
                                    main.setAvance(false);
                                    main.moverIzquierdoVacio(vista);
                                }
                            }
                        }
                    } else {
                        System.out.println("positionX:" + vista.getEjeX());
                    }
                }
                /*if (event.getX() < xPress) {
                 System.out.println("fuiste por la izquierda");
                 }
                 if (event.getX() > xPress) {
                 System.out.println("fuiste por la derecha");
                 }*/
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        if (SwingUtilities.isRightMouseButton(event)) {
            System.out.println("ejeX:" + vista.getEjeX() + ", lugar:" + vista.getLugar());
        }
    }

    @Override
    public void mouseEntered(MouseEvent event) {
    }

    @Override
    public void mouseExited(MouseEvent event) {
    }

    @Override
    public void mouseMoved(MouseEvent event) {

    }

    public Vista getVista() {
        return vista;
    }

    public void setVista(Vista vista) {
        this.vista = vista;
    }
}
