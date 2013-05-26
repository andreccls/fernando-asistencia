package Clases;
// Generated 14-may-2013 5:19:10 by Hibernate Tools 3.2.1.GA



/**
 * Tareaclase generated by hbm2java
 */
public class Tareaclase  implements java.io.Serializable {


     private TareaclaseId id;
     private Aula aula;
     private Tarea tarea;

    public Tareaclase() {
    }

	
    public Tareaclase(TareaclaseId id, Tarea tarea) {
        this.id = id;
        this.tarea = tarea;
    }
    public Tareaclase(TareaclaseId id, Aula aula, Tarea tarea) {
       this.id = id;
       this.aula = aula;
       this.tarea = tarea;
    }
    
    @Override
    public String toString() {
        return tarea.getNombre();
    }
   
    public TareaclaseId getId() {
        return this.id;
    }
    
    public void setId(TareaclaseId id) {
        this.id = id;
    }
    public Aula getAula() {
        return this.aula;
    }
    
    public void setAula(Aula aula) {
        this.aula = aula;
    }
    public Tarea getTarea() {
        return this.tarea;
    }
    
    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

//// GENERADO POR GONZALEZ FERNANDO
    
    
    public void guardarTareaclase(Tareaclase unaTareaclase){
        Controlador.getPERSISTENCIA().insert(this);

//        JOptionPane.showMessageDialog(null,"La tareaclas "+ 
//                String.valueOf(unaTareaclase.getId()) +
//                " se guardo correctamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void actualizarTareaclase(Tareaclase unaTareaclase){
        Controlador.getPERSISTENCIA().update(this);

//        JOptionPane.showMessageDialog(null,"La tareaclas "+ 
//                String.valueOf(unaTareaclase.getId()) +
//                " se guardo correctamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
    }


}


