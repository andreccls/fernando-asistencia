package Clases;
// Generated 06-may-2013 16:29:46 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 * DetalleEstablecimiento generated by hbm2java
 */
public class DetalleEstablecimiento  implements java.io.Serializable {


     private Integer idDetalleestablecimiento;
     private Declaracionjurada declaracionjurada;
     private Establecimiento establecimiento;
     private Set<Cargo> cargos = new HashSet<Cargo>(0);

    public DetalleEstablecimiento() {
    }

	
    public DetalleEstablecimiento(Declaracionjurada declaracionjurada, Establecimiento establecimiento) {
        this.declaracionjurada = declaracionjurada;
        this.establecimiento = establecimiento;
    }
    public DetalleEstablecimiento(Declaracionjurada declaracionjurada, Establecimiento establecimiento, Set<Cargo> cargos) {
       this.declaracionjurada = declaracionjurada;
       this.establecimiento = establecimiento;
       this.cargos = cargos;
    }
   
    @Override
    public String toString() {
        return establecimiento.getNombre();
    }
    
    public Integer getIdDetalleestablecimiento() {
        return this.idDetalleestablecimiento;
    }
    
    public void setIdDetalleestablecimiento(Integer idDetalleestablecimiento) {
        this.idDetalleestablecimiento = idDetalleestablecimiento;
    }
    public Declaracionjurada getDeclaracionjurada() {
        return this.declaracionjurada;
    }
    
    public void setDeclaracionjurada(Declaracionjurada declaracionjurada) {
        this.declaracionjurada = declaracionjurada;
    }
    public Establecimiento getEstablecimiento() {
        return this.establecimiento;
    }
    
    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }
    public Set<Cargo> getCargos() {
        return this.cargos;
    }
    
    public void setCargos(Set<Cargo> cargos) {
        this.cargos = cargos;
    }

// GENERADO POR GONZALEZ FERNANDO
    
    
    public void guardarDetalleEstablecimiento(DetalleEstablecimiento unDetalleEstablecimiento){
        Controlador.getPERSISTENCIA().insert(this);

//        JOptionPane.showMessageDialog(null,"El DetalleEstablecimiento "+ 
//                String.valueOf(unDetalleEstablecimiento.getIdDetalleestablecimiento()) +
//                " se guardo correctamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void ActualizarDetalleEstablecimiento(DetalleEstablecimiento unDetalleEstablecimiento){
        Controlador.getPERSISTENCIA().saveupdate(this);

        JOptionPane.showMessageDialog(null,"El DetalleEstablecimiento se actualizó correctamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void eliminarDetalleEstablecimiento(DetalleEstablecimiento unDetalleEstablecimiento){
        Controlador.getPERSISTENCIA().delete(this);

        JOptionPane.showMessageDialog(null,"El DetalleEstablecimiento se elimino correctamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
    }
    
    public Cargo getCargo(Tipocargo tipo) {
        Cargo tip=new Cargo();
        Iterator it=cargos.iterator();
        while(it.hasNext()){
            Cargo c=(Cargo) it.next();
            if(tipo.getNombre().equals(c.getTipocargo().getNombre())){
                tip=c;
                break;
            }
        }
        return tip;
    }


}


