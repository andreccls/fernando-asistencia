package Clases;
// Generated 25/10/2012 04:33:33 by Hibernate Tools 3.2.1.GA

import javax.swing.JOptionPane;




/**
 * Personaldocente generated by hbm2java
 */
public class Personaldocente  implements java.io.Serializable {


     private PersonaldocenteId id;
     private Personal personal;
     private int cargohoras;
     private int antiguedadDoc;

    public Personaldocente() {
    }

    public Personaldocente(PersonaldocenteId id, Personal personal, int cargohoras, int antiguedadDoc) {
       this.id = id;
       this.personal = personal;
       this.cargohoras = cargohoras;
       this.antiguedadDoc = antiguedadDoc;
    }
   
    public PersonaldocenteId getId() {
        return this.id;
    }
    
    public void setId(PersonaldocenteId id) {
        this.id = id;
    }
    public Personal getPersonal() {
        return this.personal;
    }
    
    public void setPersonal(Personal personal) {
        this.personal = personal;
    }
    public int getCargohoras() {
        return this.cargohoras;
    }
    
    public void setCargohoras(int cargohoras) {
        this.cargohoras = cargohoras;
    }
    public int getAntiguedadDoc() {
        return this.antiguedadDoc;
    }
    
    public void setAntiguedadDoc(int antiguedadDoc) {
        this.antiguedadDoc = antiguedadDoc;
    }

//// GENERADO POR GONZALEZ FERNANDO
    
    
    public void guardarPersonaldoc(Personaldocente unPersonaldoc){
        Controlador.getPERSISTENCIA().insertstring(this);

        JOptionPane.showMessageDialog(null,"El docente "+ 
                String.valueOf(unPersonaldoc.getId().getIdDocente()) +
                " se guardo correctamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void actualizarPersonaldoc(Personaldocente unPersonaldoc){
        Controlador.getPERSISTENCIA().update(this);

        JOptionPane.showMessageDialog(null,"El docente "+ 
                String.valueOf(unPersonaldoc.getId().getIdDocente()) +
                " se actualizó correctamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
    }

    public void eliminarPersonaldoc(Personaldocente unPersonaldoc){
        Controlador.getPERSISTENCIA().delete(this);

        JOptionPane.showMessageDialog(null,"El docente "+ 
                String.valueOf(unPersonaldoc.getId().getIdDocente()) +
                " se eliminar correctamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
    }
}

