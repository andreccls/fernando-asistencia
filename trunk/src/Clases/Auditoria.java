package Clases;
// Generated 25-abr-2013 1:29:18 by Hibernate Tools 3.2.1.GA


import java.util.Date;
import javax.swing.JOptionPane;

/**
 * Auditoria generated by hbm2java
 */
public class Auditoria  implements java.io.Serializable {


     private AuditoriaId id;
     private Personal personalByIdAuditor;
     private Departamento departamento;
     private Personal personalByIdPersonal;
     private Tarea tarea;
     private Establecimiento establecimiento;
     private Date fecha;
     private String operacion;

    public Auditoria() {
    }

	
    public Auditoria(AuditoriaId id, Personal personalByIdAuditor, Date fecha, String operacion) {
        this.id = id;
        this.personalByIdAuditor = personalByIdAuditor;
        this.fecha = fecha;
        this.operacion = operacion;
    }
    public Auditoria(AuditoriaId id, Personal personalByIdAuditor, Departamento departamento, Personal personalByIdPersonal, Tarea tarea, Establecimiento establecimiento, Date fecha, String operacion) {
       this.id = id;
       this.personalByIdAuditor = personalByIdAuditor;
       this.departamento = departamento;
       this.personalByIdPersonal = personalByIdPersonal;
       this.tarea = tarea;
       this.establecimiento = establecimiento;
       this.fecha = fecha;
       this.operacion = operacion;
    }
   
    public AuditoriaId getId() {
        return this.id;
    }
    
    public void setId(AuditoriaId id) {
        this.id = id;
    }
    public Personal getPersonalByIdAuditor() {
        return this.personalByIdAuditor;
    }
    
    public void setPersonalByIdAuditor(Personal personalByIdAuditor) {
        this.personalByIdAuditor = personalByIdAuditor;
    }
    public Departamento getDepartamento() {
        return this.departamento;
    }
    
    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
    public Personal getPersonalByIdPersonal() {
        return this.personalByIdPersonal;
    }
    
    public void setPersonalByIdPersonal(Personal personalByIdPersonal) {
        this.personalByIdPersonal = personalByIdPersonal;
    }
    public Tarea getTarea() {
        return this.tarea;
    }
    
    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }
    public Establecimiento getEstablecimiento() {
        return this.establecimiento;
    }
    
    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public String getOperacion() {
        return this.operacion;
    }
    
    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

// GENERADO POR GONZALEZ FERNANDO
    
    public void guardarAuditoria(Auditoria unaAuditoria){
        Controlador.getPERSISTENCIA().insert(this);
        JOptionPane.showMessageDialog(null,"La persona fue registrada correctamente","Registro de Persona",JOptionPane.INFORMATION_MESSAGE);
    }


}


