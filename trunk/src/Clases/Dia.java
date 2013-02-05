package Clases;
// Generated 17/10/2012 08:54:15 by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 * Dia generated by hbm2java
 */
public class Dia  implements java.io.Serializable {


     private Integer idDia;
     private Mes mes;
     private Integer dia;
     private Set<Iniciofin> iniciofins = new HashSet<Iniciofin>(0);

    public Dia() {
    }

	
    public Dia(Mes mes) {
        this.mes = mes;
    }
    public Dia(Mes mes, Integer dia, Set<Iniciofin> iniciofins) {
       this.mes = mes;
       this.dia = dia;
       this.iniciofins = iniciofins;
    }
   
    public Integer getIdDia() {
        return this.idDia;
    }
    
    public void setIdDia(Integer idDia) {
        this.idDia = idDia;
    }
    public Mes getMes() {
        return this.mes;
    }
    
    public void setMes(Mes mes) {
        this.mes = mes;
    }
    public Integer getDia() {
        return this.dia;
    }
    
    public void setDia(Integer dia) {
        this.dia = dia;
    }
    public Set<Iniciofin> getIniciofins() {
        return this.iniciofins;
    }
    
    public void setIniciofins(Set<Iniciofin> iniciofins) {
        this.iniciofins = iniciofins;
    }

//// GENERADO POR GONZALEZ FERNANDO
    
    
    public void guardarDia(Dia unDia){
        Controlador.getPERSISTENCIA().insert(this);

//        JOptionPane.showMessageDialog(null,"El Dia "+ 
//                String.valueOf(unDia.getDia()) +
//                " se guardo correctamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
    }
    
//    public boolean existeIniciofin(Iniciofin unIniciofin){
//          boolean tmpres=false;
//          Iterator it=iniciofins.iterator();
//           while(it.hasNext()){
//           Iniciofin an=(Iniciofin) it.next();
//           if(unIniciofin.equals(an)){
//               tmpres=true;
//               break;
//           }
//         }
//         return tmpres;
//     }
//    
//    public Iniciofin crearIniciofin(Dia dia, Date inicio, Date fin){
//        Iniciofin unIniciofin=new Iniciofin(dia, inicio,fin);
//        if(!existeIniciofin(unIniciofin)){
//            unIniciofin.guardarIniciofin(unIniciofin);
//        } 
//        return unIniciofin;
//     }


}


