package Clases;
// Generated 25/10/2012 04:33:33 by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 * Tipodoc generated by hbm2java
 */
public class Tipodoc  implements java.io.Serializable {

     private Integer idTipodoc;
     private String tipodoc;
     private Set<Personal> personals = new HashSet<Personal>(0);

    public Tipodoc() {
    }

    public Tipodoc(String tipodoc, Set<Personal> personals) {
       this.tipodoc = tipodoc;
       this.personals = personals;
    }
   
    @Override
    public String toString() {
        return tipodoc;
    }
    
    public Integer getIdTipodoc() {
        return this.idTipodoc;
    }
    
    public void setIdTipodoc(Integer idTipodoc) {
        this.idTipodoc = idTipodoc;
    }
    public String getTipodoc() {
        return this.tipodoc;
    }
    
    public void setTipodoc(String tipodoc) {
        this.tipodoc = tipodoc;
    }
    public Set<Personal> getPersonals() {
        return this.personals;
    }
    
    public void setPersonals(Set<Personal> personals) {
        this.personals = personals;
    }

//// GENERADO POR GONZALEZ FERNANDO
    
    
    public void guardarTipodoc(Tipodoc unTipodoc){
        Controlador.getPERSISTENCIA().insert(this);

        JOptionPane.showMessageDialog(null,"El Tipodoc "+ 
                String.valueOf(unTipodoc.getIdTipodoc()) +
                " se guardo correctamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void eliminarTipodoc(Tipodoc unTipodoc){
        Iterator it=personals.iterator();
        if(it.hasNext()){
            JOptionPane.showMessageDialog(null,"El Tipo de documento no se puede eliminar porque esta relacionada con otros personales","Mensaje",JOptionPane.INFORMATION_MESSAGE);
        }else{
        Controlador.getPERSISTENCIA().delete(this);
        JOptionPane.showMessageDialog(null,"El Tipodoc "+ 
                String.valueOf(unTipodoc.getIdTipodoc()) +
                " se elimino correctamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
        }
    }


}


