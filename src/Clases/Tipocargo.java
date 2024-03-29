package Clases;
// Generated 13/11/2012 06:11:39 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 * Tipocargo generated by hbm2java
 */
public class Tipocargo  implements java.io.Serializable {


     private Integer idTipocargo;
     private String nombre;
     private Set<Cargo> cargos = new HashSet<Cargo>(0);

    public Tipocargo() {
    }

    public Tipocargo(String nombre, Set<Cargo> cargos) {
       this.nombre = nombre;
       this.cargos = cargos;
    }
    
    @Override
    public String toString() {
        return nombre;
    } 
   
    public Integer getIdTipocargo() {
        return this.idTipocargo;
    }
    
    public void setIdTipocargo(Integer idTipocargo) {
        this.idTipocargo = idTipocargo;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Set<Cargo> getCargos() {
        return this.cargos;
    }
    
    public void setCargos(Set<Cargo> cargos) {
        this.cargos = cargos;
    }

/// GENERADO POR GONZALEZ FERNANDO
    
    
    public void guardarTipocargo(Tipocargo unTipocargo){
        Controlador.getPERSISTENCIA().insert(this);

        JOptionPane.showMessageDialog(null,"El tipo de Cargo "+ 
                unTipocargo.getNombre() +
                " se guardo correctamente","Registrar tipo de cargo",JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void eliminarTipocargo(Tipocargo unTipocargo){
        Iterator it=cargos.iterator();
        if(it.hasNext()){
            JOptionPane.showMessageDialog(null,"El tipo de cargo no se puede eliminar porque esta relacionada con otras declaraciones juradas","Eliminar Cargo",JOptionPane.INFORMATION_MESSAGE);
        }else{
        Controlador.getPERSISTENCIA().delete(this);
        JOptionPane.showMessageDialog(null,"El Cargo se elimino correctamente","Eliminar Cargo",JOptionPane.INFORMATION_MESSAGE);
        }
    }


}


