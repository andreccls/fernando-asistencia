package Clases;
// Generated 11/12/2012 16:01:03 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 * Asistencia generated by hbm2java
 */
public class Asistencia  implements java.io.Serializable {


     private Integer idAsistencia;
     private Iniciofin iniciofin;
     private Boolean estado;
     private Boolean tardanza;
     private Set<Justificacion> justificacions = new HashSet<Justificacion>(0);

    public Asistencia() {
    }

	
    public Asistencia(Iniciofin iniciofin) {
        this.iniciofin = iniciofin;
    }
    public Asistencia(Iniciofin iniciofin, Boolean estado, Boolean tardanza, Set<Justificacion> justificacions) {
       this.iniciofin = iniciofin;
       this.estado = estado;
       this.tardanza = tardanza;
       this.justificacions = justificacions;
    }
   
    public Integer getIdAsistencia() {
        return this.idAsistencia;
    }
    
    public void setIdAsistencia(Integer idAsistencia) {
        this.idAsistencia = idAsistencia;
    }
    public Iniciofin getIniciofin() {
        return this.iniciofin;
    }
    
    public void setIniciofin(Iniciofin iniciofin) {
        this.iniciofin = iniciofin;
    }
    public Boolean getEstado() {
        return this.estado;
    }
    
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    public Boolean getTardanza() {
        return this.tardanza;
    }
    
    public void setTardanza(Boolean tardanza) {
        this.tardanza = tardanza;
    }
    public Set<Justificacion> getJustificacions() {
        return this.justificacions;
    }
    
    public void setJustificacions(Set<Justificacion> justificacions) {
        this.justificacions = justificacions;
    }

//// GENERADO POR GONZALEZ FERNANDO
    
    
    public void guardarAsistencia(Asistencia unaAsistencia){
        Controlador.getPERSISTENCIA().insert(this);
//
//        JOptionPane.showMessageDialog(null,"La asistencia "+ 
//                String.valueOf(unaAsistencia.getIdAsistencia()) +
//                " se guardo correctamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void ActualizarAsistencia(Asistencia unaAsistencia){
        Controlador.getPERSISTENCIA().update(this);
//
//        JOptionPane.showMessageDialog(null,"La asistencia "+ 
//                String.valueOf(unaAsistencia.getIdAsistencia()) +
//                " se actualizo correctamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void eliminarAsistencia(Asistencia unaAsistencia){
        Controlador.getPERSISTENCIA().delete(this);
//
//        JOptionPane.showMessageDialog(null,"La asistencia "+ 
//                String.valueOf(unaAsistencia.getIdAsistencia()) +
//                " se actualizo correctamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
    }

    public void crearJustificacion(Asistencia asistencia, Articulo articulo, String motivo){
        Justificacion unaJustificacion=new Justificacion (asistencia, articulo, motivo);
        //if(!existePersonal(unPersonal)){
            unaJustificacion.guardarJustificacion(unaJustificacion);
            //agregarReserva(unaReserva);
        //} 
     }
    
    public void crearArticulo(String nombre, String nroArticulo, Set<Justificacion> justificacions, Set<Licencia> licencias){
        Articulo unArticulo=new Articulo (nombre, nroArticulo,justificacions,licencias);
        //if(!existePersonal(unPersonal)){
            unArticulo.guardarArticulo(unArticulo);
            //agregarReserva(unaReserva);
        //} 
     }
    
    public Justificacion getJustificacion(Asistencia asi){
        Justificacion just=new Justificacion();
        if(asi.getJustificacions().iterator().hasNext()){
            just=asi.getJustificacions().iterator().next();
        }
        return just;
     }


}


