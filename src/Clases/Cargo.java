package Clases;
// Generated 13/11/2012 06:11:39 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 * Cargo generated by hbm2java
 */
public class Cargo  implements java.io.Serializable {


     private Integer idCargo;
     private DetalleEstablecimiento detalleEstablecimiento;
     private Tipocargo tipocargo;
     private Set<Nivel> nivels = new HashSet<Nivel>(0);

    public Cargo() {
    }

	
    public Cargo(DetalleEstablecimiento detalleEstablecimiento, Tipocargo tipocargo) {
        this.detalleEstablecimiento = detalleEstablecimiento;
        this.tipocargo = tipocargo;
    }
    public Cargo(DetalleEstablecimiento detalleEstablecimiento, Tipocargo tipocargo, Set<Nivel> nivels) {
       this.detalleEstablecimiento = detalleEstablecimiento;
       this.tipocargo = tipocargo;
       this.nivels = nivels;
    }
    
    @Override
    public String toString() {
        return tipocargo.getNombre();
    }
   
    public Integer getIdCargo() {
        return this.idCargo;
    }
    
    public void setIdCargo(Integer idCargo) {
        this.idCargo = idCargo;
    }
    public DetalleEstablecimiento getDetalleEstablecimiento() {
        return this.detalleEstablecimiento;
    }
    
    public void setDetalleEstablecimiento(DetalleEstablecimiento detalleEstablecimiento) {
        this.detalleEstablecimiento = detalleEstablecimiento;
    }
    public Tipocargo getTipocargo() {
        return this.tipocargo;
    }
    
    public void setTipocargo(Tipocargo tipocargo) {
        this.tipocargo = tipocargo;
    }
    public Set<Nivel> getNivels() {
        return this.nivels;
    }
    
    public void setNivels(Set<Nivel> nivels) {
        this.nivels = nivels;
    }

/// GENERADO POR GONZALEZ FERNANDO
    
    
    public void guardarCargo(Cargo unCargo){
        Controlador.getPERSISTENCIA().insert(this);

//        JOptionPane.showMessageDialog(null,"El Cargo "+ 
//                String.valueOf(unCargo.getIdCargo()) +
//                " se guardo correctamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void ActualizarCargo(Cargo unCargo){
        Controlador.getPERSISTENCIA().saveupdate(this);

//        JOptionPane.showMessageDialog(null,"El Cargo "+ 
//                String.valueOf(unCargo.getIdCargo()) +
//                " se guardo correctamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void eliminarCargo(Cargo unCargo){
        Controlador.getPERSISTENCIA().delete(this);

        JOptionPane.showMessageDialog(null,"El Cargo se elimino correctamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
    }

    public Nivel getNivel(Tiponivel tiponivel) {
        Nivel tip=new Nivel();
        Iterator it=nivels.iterator();
           while(it.hasNext()){
           Nivel c=(Nivel) it.next();
           if(tiponivel.getNombre().equals(c.getTiponivel().getNombre())){
               tip=c;
               break;
           }
         }
        return tip;
    }


}


