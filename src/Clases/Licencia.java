package Clases;
// Generated 13-may-2013 19:14:47 by Hibernate Tools 3.2.1.GA


import java.util.Date;
import javax.swing.JOptionPane;

/**
 * Licencia generated by hbm2java
 */
public class Licencia  implements java.io.Serializable {


     private Integer idLicencia;
     private Personal personal;
     private Articulo articulo;
     private Date inicio;
     private Date fin;
     private String descripcion;

    public Licencia() {
    }

	
    public Licencia(Personal personal, Articulo articulo) {
        this.personal = personal;
        this.articulo = articulo;
    }
    public Licencia(Personal personal, Articulo articulo, Date inicio, Date fin, String descripcion) {
       this.personal = personal;
       this.articulo = articulo;
       this.inicio = inicio;
       this.fin = fin;
       this.descripcion = descripcion;
    }
    
    @Override
    public String toString() {
        return descripcion;
    }
   
    public Integer getIdLicencia() {
        return this.idLicencia;
    }
    
    public void setIdLicencia(Integer idLicencia) {
        this.idLicencia = idLicencia;
    }
    public Personal getPersonal() {
        return this.personal;
    }
    
    public void setPersonal(Personal personal) {
        this.personal = personal;
    }
    public Articulo getArticulo() {
        return this.articulo;
    }
    
    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
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
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

//// GENERADO POR GONZALEZ FERNANDO
    public void guardarLicencia(Licencia unaLicencia){
        Controlador.getPERSISTENCIA().insert(this);

        JOptionPane.showMessageDialog(null,"La licencia se guardo correctamente","Guardar Licencia",JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void eliminarLicencia(Licencia unaLicencia){
        Controlador.getPERSISTENCIA().delete(this);

        JOptionPane.showMessageDialog(null,"La licencia "+unaLicencia.getDescripcion()+" se eliminó correctamente","Eliminar Licencia",JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void actualizarLicencia(Licencia unaLicencia){
        Controlador.getPERSISTENCIA().update(this);

//        JOptionPane.showMessageDialog(null,"El Año "+ 
//                String.valueOf(unAno.getAno()) +
//                " se guardo correctamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
    }


}


