package Clases;
// Generated 31/10/2012 10:25:04 by Hibernate Tools 3.2.1.GA

import javax.swing.JOptionPane;




/**
 * PersonalDepartamento generated by hbm2java
 */
public class PersonalDepartamento  implements java.io.Serializable {


     private PersonalDepartamentoId id;
     private Departamento departamento;
     private Personal personal;
     private Boolean jefe;

    public PersonalDepartamento() {
    }

	
    public PersonalDepartamento(PersonalDepartamentoId id, Departamento departamento, Personal personal) {
        this.id = id;
        this.departamento = departamento;
        this.personal = personal;
    }
    public PersonalDepartamento(PersonalDepartamentoId id, Departamento departamento, Personal personal, Boolean jefe) {
       this.id = id;
       this.departamento = departamento;
       this.personal = personal;
       this.jefe = jefe;
    }
    
    @Override
    public String toString() {
        if(jefe.equals(false)){
            return departamento.getNombre();
        }
        else{return departamento.getNombre()+" JEFE";
        }
    }
   
    public PersonalDepartamentoId getId() {
        return this.id;
    }
    
    public void setId(PersonalDepartamentoId id) {
        this.id = id;
    }
    public Departamento getDepartamento() {
        return this.departamento;
    }
    
    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
    public Personal getPersonal() {
        return this.personal;
    }
    
    public void setPersonal(Personal personal) {
        this.personal = personal;
    }
    public Boolean getJefe() {
        return this.jefe;
    }
    
    public void setJefe(Boolean jefe) {
        this.jefe = jefe;
    }

//// GENERADO POR GONZALEZ FERNANDO
    
    public void guardarPersonalDepartamento(PersonalDepartamento unPersonaldepto){
        Controlador.getPERSISTENCIA().insert(this);

//        JOptionPane.showMessageDialog(null,"El personal Departamento "+ 
//                String.valueOf(unPersonaldepto.getId().getIdPersonal()) +
//                " se guardo correctamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
    }

    public void actualizarPersonalDepartamento(PersonalDepartamento unPersonaldepto){
        Controlador.getPERSISTENCIA().saveupdate(this);

//        JOptionPane.showMessageDialog(null,"El personal Departamento "+ 
//                String.valueOf(unPersonaldepto.getId().getIdPersonal()) +
//                " se actualizó correctamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void eliminarPersonalDepartamento(PersonalDepartamento unPersonaldepto){
        Controlador.getPERSISTENCIA().delete(this);

//        JOptionPane.showMessageDialog(null,"El personal Departamento "+ 
//                String.valueOf(unPersonaldepto.getId().getIdPersonal()) +
//                " se eliminó correctamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
    }

}


