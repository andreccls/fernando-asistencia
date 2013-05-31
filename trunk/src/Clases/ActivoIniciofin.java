package Clases;
// Generated 17/10/2012 08:54:15 by Hibernate Tools 3.2.1.GA


import java.util.Date;
import javax.swing.JOptionPane;

/**
 * ActivoIniciofin generated by hbm2java
 */
public class ActivoIniciofin  implements java.io.Serializable {


     private Integer idActivoiniciofin;
     private Activo activo;
     private Date inicio;
     private Date fin;

    public ActivoIniciofin() {
    }

	
    public ActivoIniciofin(Activo activo) {
        this.activo = activo;
    }
    public ActivoIniciofin(Activo activo, Date inicio, Date fin) {
       this.activo = activo;
       this.inicio = inicio;
       this.fin = fin;
    }
   
    public Integer getIdActivoiniciofin() {
        return this.idActivoiniciofin;
    }
    
    public void setIdActivoiniciofin(Integer idActivoiniciofin) {
        this.idActivoiniciofin = idActivoiniciofin;
    }
    public Activo getActivo() {
        return this.activo;
    }
    
    public void setActivo(Activo activo) {
        this.activo = activo;
    }
    public Date getInicio() {
        return this.inicio;
    }
    
    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }
    public Date getFin() {
        return this.fin;
    }
    
    public void setFin(Date fin) {
        this.fin = fin;
    }

 
    //// GENERADO POR GONZALEZ FERNANDO
    
    
    public void guardarActivoiniciofin(ActivoIniciofin unActivoiniciofin){
        Controlador.getPERSISTENCIA().insert(this);

        JOptionPane.showMessageDialog(null,"El activo se guardo correctamente","Registrar Activo",JOptionPane.INFORMATION_MESSAGE);
    }

    public void eliminarActivoiniciofin(ActivoIniciofin unActivoiniciofin){
        Controlador.getPERSISTENCIA().delete(this);

//        JOptionPane.showMessageDialog(null,"El activo inicio-fin "+ 
//                String.valueOf(unActivoiniciofin.getIdActivoiniciofin()) +
//                " se guardo correctamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
    }

}


