package Clases;
// Generated 13/11/2012 06:11:39 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 * Tiponivel generated by hbm2java
 */
public class Tiponivel  implements java.io.Serializable {


     private Integer idTiponivel;
     private String nombre;
     private Set<Nivel> nivels = new HashSet<Nivel>(0);

    public Tiponivel() {
    }

    public Tiponivel(String nombre, Set<Nivel> nivels) {
       this.nombre = nombre;
       this.nivels = nivels;
    }
    
    @Override
    public String toString() {
        return nombre;
    }
   
    public Integer getIdTiponivel() {
        return this.idTiponivel;
    }
    
    public void setIdTiponivel(Integer idTiponivel) {
        this.idTiponivel = idTiponivel;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Set<Nivel> getNivels() {
        return this.nivels;
    }
    
    public void setNivels(Set<Nivel> nivels) {
        this.nivels = nivels;
    }

//// GENERADO POR GONZALEZ FERNANDO
    
    public void guardarTiponivel(Tiponivel unTiponivel){
        Controlador.getPERSISTENCIA().insert(this);

        JOptionPane.showMessageDialog(null,"El Nivel "+ 
                String.valueOf(unTiponivel.getIdTiponivel()) +
                " se guardo correctamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void eliminarTiponivel(Tiponivel unTiponivel){
        Iterator it=nivels.iterator();
        if(it.hasNext()){
            JOptionPane.showMessageDialog(null,"El nivel no se puede eliminar porque esta relacionada con otras declaraciones juradas","Mensaje",JOptionPane.INFORMATION_MESSAGE);
        }else{
        Controlador.getPERSISTENCIA().delete(this);
        JOptionPane.showMessageDialog(null,"El Cargo "+ 
                String.valueOf(unTiponivel.getIdTiponivel()) +
                " se elimino correctamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
        }
    }


}

