package Clases;
// Generated 21/02/2013 23:49:41 by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 * Circular generated by hbm2java
 */
public class Circular  implements java.io.Serializable {


     private Integer idCircular;
     private Establecimiento establecimiento;
     private String descripcion;
     private String firma;
     private Date fecha;
     private Set<Circularpersonal> circularpersonals = new HashSet<Circularpersonal>(0);

    public Circular() {
    }

    @Override
    public String toString() {
        return firma;
    }
	
    public Circular(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }
    public Circular(Establecimiento establecimiento, String descripcion, String firma, Date fecha, Set<Circularpersonal> circularpersonals) {
       this.establecimiento = establecimiento;
       this.descripcion = descripcion;
       this.firma = firma;
       this.fecha = fecha;
       this.circularpersonals = circularpersonals;
    }
   
    public Integer getIdCircular() {
        return this.idCircular;
    }
    
    public void setIdCircular(Integer idCircular) {
        this.idCircular = idCircular;
    }
    public Establecimiento getEstablecimiento() {
        return this.establecimiento;
    }
    
    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getFirma() {
        return this.firma;
    }
    
    public void setFirma(String firma) {
        this.firma = firma;
    }
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public Set<Circularpersonal> getCircularpersonals() {
        return this.circularpersonals;
    }
    
    public void setCircularpersonals(Set<Circularpersonal> circularpersonals) {
        this.circularpersonals = circularpersonals;
    }

//// GENERADO POR GONZALEZ FERNANDO
    
    
    public void guardarCircular(Circular unCircular){
        Controlador.getPERSISTENCIA().insert(this);

        JOptionPane.showMessageDialog(null,"La circular se guardó correctamente","Registrar departamento",JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void eliminarCircular(Circular unCircular){
//        Iterator it=personalDepartamentos.iterator();
//        if(it.hasNext()){
//            JOptionPane.showMessageDialog(null,"El Departamento no se puede eliminar porque esta relacionada con otros personales","Eliminar departamento",JOptionPane.ERROR_MESSAGE);
//        }else{
        Controlador.getPERSISTENCIA().delete(this);
//        JOptionPane.showMessageDialog(null,"El Departamento se eliminó correctamente","Eliminar departamento",JOptionPane.INFORMATION_MESSAGE);
//        }
    }
    
    public void actualizarCircular(Circular unCircular){
//        Iterator it=personalDepartamentos.iterator();
//        if(it.hasNext()){
//            JOptionPane.showMessageDialog(null,"El Departamento no se puede eliminar porque esta relacionada con otros personales","Eliminar departamento",JOptionPane.ERROR_MESSAGE);
//        }else{
        Controlador.getPERSISTENCIA().update(this);
//        JOptionPane.showMessageDialog(null,"El Departamento se eliminó correctamente","Eliminar departamento",JOptionPane.INFORMATION_MESSAGE);
//        }
    }

    public void BorrarCircularpersonales(){
        Iterator it = circularpersonals.iterator();
        while(it.hasNext()){
            Circularpersonal cirper=(Circularpersonal) it.next();
            cirper.eliminarCircularpersonal(cirper);
        }
    }

}

