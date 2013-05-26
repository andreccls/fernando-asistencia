package Clases;
// Generated 02-may-2013 17:40:38 by Hibernate Tools 3.2.1.GA



/**
 * Tareareunion generated by hbm2java
 */
public class Tareareunion  implements java.io.Serializable {


     private TareareunionId id;
     private Tarea tarea;
     private String motivo;
     private String caracter;

    public Tareareunion() {
    }

	
    public Tareareunion(TareareunionId id, Tarea tarea) {
        this.id = id;
        this.tarea = tarea;
    }
    public Tareareunion(TareareunionId id, Tarea tarea, String motivo, String caracter) {
       this.id = id;
       this.tarea = tarea;
       this.motivo = motivo;
       this.caracter = caracter;
    }
    
    @Override
    public String toString() {
        return tarea.getNombre();
    }
   
    public TareareunionId getId() {
        return this.id;
    }
    
    public void setId(TareareunionId id) {
        this.id = id;
    }
    public Tarea getTarea() {
        return this.tarea;
    }
    
    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }
    public String getMotivo() {
        return this.motivo;
    }
    
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    public String getCaracter() {
        return this.caracter;
    }
    
    public void setCaracter(String caracter) {
        this.caracter = caracter;
    }

 //// GENERADO POR GONZALEZ FERNANDO
    
    
    public void guardarTareareunion(Tareareunion unaTareareunion){
        Controlador.getPERSISTENCIA().insert(this);

//        JOptionPane.showMessageDialog(null,"La Tarea reunion "+ 
//                String.valueOf(unaTareareunion.getId().getIdTarea()) +
//                " se guardo correctamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void actualizarTareareunion(Tareareunion unaTareareunion){
        Controlador.getPERSISTENCIA().update(this);

//        JOptionPane.showMessageDialog(null,"La Tarea reunion "+ 
//                String.valueOf(unaTareareunion.getId().getIdTarea()) +
//                " se guardo correctamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
    }


}


