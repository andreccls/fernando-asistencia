package Clases;
// Generated 14/12/2012 20:39:39 by Hibernate Tools 3.2.1.GA


import java.util.Date;
import javax.swing.JOptionPane;

/**
 * Franco generated by hbm2java
 */
public class Franco  implements java.io.Serializable {


     private Integer idFranco;
     private Agenda agenda;
     private Date diaFranco;

    public Franco() {
    }

	
    public Franco(Agenda agenda) {
        this.agenda = agenda;
    }
    public Franco(Agenda agenda, Date diaFranco) {
       this.agenda = agenda;
       this.diaFranco = diaFranco;
    }
   
    public Integer getIdFranco() {
        return this.idFranco;
    }
    
    public void setIdFranco(Integer idFranco) {
        this.idFranco = idFranco;
    }
    public Agenda getAgenda() {
        return this.agenda;
    }
    
    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }
    public Date getDiaFranco() {
        return this.diaFranco;
    }
    
    public void setDiaFranco(Date diaFranco) {
        this.diaFranco = diaFranco;
    }

// GENERADO POR GONZALEZ FERNANDO
    
    public void guardarFranco(Franco unFranco){
        Controlador.getPERSISTENCIA().insert(this);
        //Controlador.getPERSISTENCIA().update(this);

        JOptionPane.showMessageDialog(null,"El Feriado "+ 
                String.valueOf(unFranco.getIdFranco()) +
                " se guardo correctamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void actualizarFranco(Franco unFranco){
        Controlador.getPERSISTENCIA().update(this);

        JOptionPane.showMessageDialog(null,"El Feriado "+ 
                String.valueOf(unFranco.getIdFranco()) +
                " se actualizó correctamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void eliminarFranco(Franco unFranco){
        Controlador.getPERSISTENCIA().delete(this);

        JOptionPane.showMessageDialog(null,"El Feriado "+ 
                String.valueOf(unFranco.getIdFranco()) +
                " se elimino correctamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
    }



}

