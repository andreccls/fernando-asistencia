/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Clases;

import Persistencia.persistencia;
import java.util.Date;
import javax.swing.JOptionPane;
/**
 *
 * @author fer
 */
public class Controlador {
    public static persistencia PERSISTENCIA;

    private Colegios col;

    public Controlador() {
        PERSISTENCIA = new persistencia();
    }
    public static persistencia getPERSISTENCIA() {
        return PERSISTENCIA;
    }

//    public void CrearPersonal(String reg, String dni, String apell, String nom, String cuil, String ciud,String dir, String pais, String prov, String correo, String sex, String depto, String estciv,Colegio colegio){
//        col=colegio;
//        col.crearPersonal(reg, dni, apell, nom, cuil, ciud, dir, pais, prov, correo, sex, depto, estciv, col);
//    }

    public Colegios getColegio() {

        Colegios cole = (Colegios) Controlador.getPERSISTENCIA().getColegios().get(0);
        this.col = cole;
        return cole;

    }
}
