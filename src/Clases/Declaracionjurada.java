package Clases;
// Generated 27/11/2012 13:31:14 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 * Declaracionjurada generated by hbm2java
 */
public class Declaracionjurada  implements java.io.Serializable {

     private Integer idDeclaracionjurada;
     private Personal personal;
     private Establecimiento establecimiento;
     private Integer ano;
     private String observacion;
     private Set<DetalleEstablecimiento> detalleEstablecimientos = new HashSet<DetalleEstablecimiento>(0);

    public Declaracionjurada() {
    }

	
    public Declaracionjurada(Personal personal, Establecimiento establecimiento) {
        this.personal = personal;
        this.establecimiento = establecimiento;
    }
    public Declaracionjurada(Personal personal, Establecimiento establecimiento, Integer ano, String observacion, Set<DetalleEstablecimiento> detalleEstablecimientos) {
       this.personal = personal;
       this.establecimiento = establecimiento;
       this.ano = ano;
       this.observacion = observacion;
       this.detalleEstablecimientos = detalleEstablecimientos;
    }
   
    public Integer getIdDeclaracionjurada() {
        return this.idDeclaracionjurada;
    }
    
    public void setIdDeclaracionjurada(Integer idDeclaracionjurada) {
        this.idDeclaracionjurada = idDeclaracionjurada;
    }
    public Personal getPersonal() {
        return this.personal;
    }
    
    public void setPersonal(Personal personal) {
        this.personal = personal;
    }
    public Establecimiento getEstablecimiento() {
        return this.establecimiento;
    }
    
    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }
    public Integer getAno() {
        return this.ano;
    }
    
    public void setAno(Integer ano) {
        this.ano = ano;
    }
    public String getObservacion() {
        return this.observacion;
    }
    
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    public Set<DetalleEstablecimiento> getDetalleEstablecimientos() {
        return this.detalleEstablecimientos;
    }
    
    public void setDetalleEstablecimientos(Set<DetalleEstablecimiento> detalleEstablecimientos) {
        this.detalleEstablecimientos = detalleEstablecimientos;
    }

//// GENERADO POR GONZALEZ FERNANDO

    public void guardarDeclaracionjurada(Declaracionjurada unaDeclaracionjurada){
        Controlador.getPERSISTENCIA().insert(this);

        JOptionPane.showMessageDialog(null,"La Declaración jurada "+ 
                String.valueOf(unaDeclaracionjurada.getIdDeclaracionjurada()) +
                " se guardo correctamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
    }
   
    public void ActualizarDeclaracionjurada(Declaracionjurada unaDeclaracionjurada){
        Controlador.getPERSISTENCIA().update(this);

        JOptionPane.showMessageDialog(null,"La Declaración jurada "+ 
                String.valueOf(unaDeclaracionjurada.getIdDeclaracionjurada()) +
                " se actualizó correctamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    public DetalleEstablecimiento getDetalleEstablecimiento(Establecimiento est) {
        DetalleEstablecimiento detalle=new DetalleEstablecimiento();
        Iterator it=detalleEstablecimientos.iterator();
           while(it.hasNext()){
           DetalleEstablecimiento c=(DetalleEstablecimiento) it.next();
           if(est.getNombre().equals(c.getEstablecimiento().getNombre())){
               detalle=c;
               break;
           }
         }
        return detalle;
    }
    
    public void crearDetalleEstablecimiento(Declaracionjurada declaracionjurada, Establecimiento establecimiento, Set<Cargo> cargos){
        DetalleEstablecimiento unDetalleEstablecimiento=new DetalleEstablecimiento (declaracionjurada,establecimiento,cargos);
        //if(!existePersonal(unPersonal)){
            unDetalleEstablecimiento.guardarDetalleEstablecimiento(unDetalleEstablecimiento);
            //agregarReserva(unaReserva);
        //} 
     }
    
    public Set<Activo> ObtenerActivos (String di){
        Set<Activo> ini = new HashSet<Activo>(0);
        Iterator itdet=detalleEstablecimientos.iterator();
        while(itdet.hasNext()){
            DetalleEstablecimiento det=(DetalleEstablecimiento) itdet.next();
            Iterator itcar=det.getCargos().iterator();
            while(itcar.hasNext()){
                Cargo car=(Cargo) itcar.next();
                Iterator itniv=car.getNivels().iterator();
                while(itniv.hasNext()){
                    Nivel niv=(Nivel) itniv.next();
                    Iterator itact=niv.getActivos().iterator();
                    while(itact.hasNext()){
                        Activo act=(Activo) itact.next();
                        if(act.getDia().equals(di)){
                            ini.add(act);
                        }
                    }
                }
            }
        }
        return ini;
    }

}


