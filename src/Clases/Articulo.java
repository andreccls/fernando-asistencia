package Clases;
// Generated 11-may-2013 1:16:55 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Articulo generated by hbm2java
 */
public class Articulo  implements java.io.Serializable {


     private Integer idArticulo;
     private String nombre;
     private String nroArticulo;
     private Set<Justificacion> justificacions = new HashSet<Justificacion>(0);
     private Set<Licencia> licencias = new HashSet<Licencia>(0);

    public Articulo() {
    }

    public Articulo(String nombre, String nroArticulo, Set<Justificacion> justificacions, Set<Licencia> licencias) {
       this.nombre = nombre;
       this.nroArticulo = nroArticulo;
       this.justificacions = justificacions;
       this.licencias = licencias;
    }
    
    @Override
    public String toString() {
        return nombre+" "+nroArticulo;
    }
   
    public Integer getIdArticulo() {
        return this.idArticulo;
    }
    
    public void setIdArticulo(Integer idArticulo) {
        this.idArticulo = idArticulo;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNroArticulo() {
        return this.nroArticulo;
    }
    
    public void setNroArticulo(String nroArticulo) {
        this.nroArticulo = nroArticulo;
    }
    public Set<Justificacion> getJustificacions() {
        return this.justificacions;
    }
    
    public void setJustificacions(Set<Justificacion> justificacions) {
        this.justificacions = justificacions;
    }
    public Set<Licencia> getLicencias() {
        return this.licencias;
    }
    
    public void setLicencias(Set<Licencia> licencias) {
        this.licencias = licencias;
    }

//// GENERADO POR GONZALEZ FERNANDO
    
    
    public void guardarArticulo(Articulo unArticulo){
        Controlador.getPERSISTENCIA().insert(this);

//        JOptionPane.showMessageDialog(null,"El Artículo "+ 
//                String.valueOf(unArticulo.getIdArticulo()) +
//                " se guardo correctamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
    }


}


