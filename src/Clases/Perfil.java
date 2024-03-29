package Clases;
// Generated 08-may-2013 20:50:59 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Perfil generated by hbm2java
 */
public class Perfil  implements java.io.Serializable {


     private Integer idPerfil;
     private String nombre;
     private Boolean personalins;
     private Boolean personalact;
     private Boolean personaleli;
     private Boolean personalcon;
     private Boolean actividadesins;
     private Boolean actividadesact;
     private Boolean actividadeseli;
     private Boolean actividadescon;
     private Boolean asistenciasins;
     private Boolean asistenciasact;
     private Boolean asistenciaseli;
     private Boolean asistenciascon;
     private Boolean circularesins;
     private Boolean circularesact;
     private Boolean circulareseli;
     private Boolean circularescon;
     private Boolean configuracionins;
     private Boolean configuracionact;
     private Boolean configuracioneli;
     private Boolean configuracioncon;
     private Boolean registroins;
     private Boolean registroact;
     private Boolean registroeli;
     private Boolean registrocon;
     private Boolean auditoriains;
     private Boolean auditoriaact;
     private Boolean auditoriaeli;
     private Boolean auditoriacon;
     private Boolean historialins;
     private Boolean historialact;
     private Boolean historialeli;
     private Boolean historialcon;
     private Set<Personal> personals = new HashSet<Personal>(0);

    public Perfil() {
    }

    public Perfil(String nombre, Boolean personalins, Boolean personalact, Boolean personaleli, Boolean personalcon, Boolean actividadesins, Boolean actividadesact, Boolean actividadeseli, Boolean actividadescon, Boolean asistenciasins, Boolean asistenciasact, Boolean asistenciaseli, Boolean asistenciascon, Boolean circularesins, Boolean circularesact, Boolean circulareseli, Boolean circularescon, Boolean configuracionins, Boolean configuracionact, Boolean configuracioneli, Boolean configuracioncon, Boolean registroins, Boolean registroact, Boolean registroeli, Boolean registrocon, Boolean auditoriains, Boolean auditoriaact, Boolean auditoriaeli, Boolean auditoriacon, Boolean historialins, Boolean historialact, Boolean historialeli, Boolean historialcon, Set<Personal> personals) {
       this.nombre = nombre;
       this.personalins = personalins;
       this.personalact = personalact;
       this.personaleli = personaleli;
       this.personalcon = personalcon;
       this.actividadesins = actividadesins;
       this.actividadesact = actividadesact;
       this.actividadeseli = actividadeseli;
       this.actividadescon = actividadescon;
       this.asistenciasins = asistenciasins;
       this.asistenciasact = asistenciasact;
       this.asistenciaseli = asistenciaseli;
       this.asistenciascon = asistenciascon;
       this.circularesins = circularesins;
       this.circularesact = circularesact;
       this.circulareseli = circulareseli;
       this.circularescon = circularescon;
       this.configuracionins = configuracionins;
       this.configuracionact = configuracionact;
       this.configuracioneli = configuracioneli;
       this.configuracioncon = configuracioncon;
       this.registroins = registroins;
       this.registroact = registroact;
       this.registroeli = registroeli;
       this.registrocon = registrocon;
       this.auditoriains = auditoriains;
       this.auditoriaact = auditoriaact;
       this.auditoriaeli = auditoriaeli;
       this.auditoriacon = auditoriacon;
       this.historialins = historialins;
       this.historialact = historialact;
       this.historialeli = historialeli;
       this.historialcon = historialcon;
       this.personals = personals;
    }
   
    @Override
    public String toString() {
        return nombre;
    }
    
    public Integer getIdPerfil() {
        return this.idPerfil;
    }
    
    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Boolean getPersonalins() {
        return this.personalins;
    }
    
    public void setPersonalins(Boolean personalins) {
        this.personalins = personalins;
    }
    public Boolean getPersonalact() {
        return this.personalact;
    }
    
    public void setPersonalact(Boolean personalact) {
        this.personalact = personalact;
    }
    public Boolean getPersonaleli() {
        return this.personaleli;
    }
    
    public void setPersonaleli(Boolean personaleli) {
        this.personaleli = personaleli;
    }
    public Boolean getPersonalcon() {
        return this.personalcon;
    }
    
    public void setPersonalcon(Boolean personalcon) {
        this.personalcon = personalcon;
    }
    public Boolean getActividadesins() {
        return this.actividadesins;
    }
    
    public void setActividadesins(Boolean actividadesins) {
        this.actividadesins = actividadesins;
    }
    public Boolean getActividadesact() {
        return this.actividadesact;
    }
    
    public void setActividadesact(Boolean actividadesact) {
        this.actividadesact = actividadesact;
    }
    public Boolean getActividadeseli() {
        return this.actividadeseli;
    }
    
    public void setActividadeseli(Boolean actividadeseli) {
        this.actividadeseli = actividadeseli;
    }
    public Boolean getActividadescon() {
        return this.actividadescon;
    }
    
    public void setActividadescon(Boolean actividadescon) {
        this.actividadescon = actividadescon;
    }
    public Boolean getAsistenciasins() {
        return this.asistenciasins;
    }
    
    public void setAsistenciasins(Boolean asistenciasins) {
        this.asistenciasins = asistenciasins;
    }
    public Boolean getAsistenciasact() {
        return this.asistenciasact;
    }
    
    public void setAsistenciasact(Boolean asistenciasact) {
        this.asistenciasact = asistenciasact;
    }
    public Boolean getAsistenciaseli() {
        return this.asistenciaseli;
    }
    
    public void setAsistenciaseli(Boolean asistenciaseli) {
        this.asistenciaseli = asistenciaseli;
    }
    public Boolean getAsistenciascon() {
        return this.asistenciascon;
    }
    
    public void setAsistenciascon(Boolean asistenciascon) {
        this.asistenciascon = asistenciascon;
    }
    public Boolean getCircularesins() {
        return this.circularesins;
    }
    
    public void setCircularesins(Boolean circularesins) {
        this.circularesins = circularesins;
    }
    public Boolean getCircularesact() {
        return this.circularesact;
    }
    
    public void setCircularesact(Boolean circularesact) {
        this.circularesact = circularesact;
    }
    public Boolean getCirculareseli() {
        return this.circulareseli;
    }
    
    public void setCirculareseli(Boolean circulareseli) {
        this.circulareseli = circulareseli;
    }
    public Boolean getCircularescon() {
        return this.circularescon;
    }
    
    public void setCircularescon(Boolean circularescon) {
        this.circularescon = circularescon;
    }
    public Boolean getConfiguracionins() {
        return this.configuracionins;
    }
    
    public void setConfiguracionins(Boolean configuracionins) {
        this.configuracionins = configuracionins;
    }
    public Boolean getConfiguracionact() {
        return this.configuracionact;
    }
    
    public void setConfiguracionact(Boolean configuracionact) {
        this.configuracionact = configuracionact;
    }
    public Boolean getConfiguracioneli() {
        return this.configuracioneli;
    }
    
    public void setConfiguracioneli(Boolean configuracioneli) {
        this.configuracioneli = configuracioneli;
    }
    public Boolean getConfiguracioncon() {
        return this.configuracioncon;
    }
    
    public void setConfiguracioncon(Boolean configuracioncon) {
        this.configuracioncon = configuracioncon;
    }
    public Boolean getRegistroins() {
        return this.registroins;
    }
    
    public void setRegistroins(Boolean registroins) {
        this.registroins = registroins;
    }
    public Boolean getRegistroact() {
        return this.registroact;
    }
    
    public void setRegistroact(Boolean registroact) {
        this.registroact = registroact;
    }
    public Boolean getRegistroeli() {
        return this.registroeli;
    }
    
    public void setRegistroeli(Boolean registroeli) {
        this.registroeli = registroeli;
    }
    public Boolean getRegistrocon() {
        return this.registrocon;
    }
    
    public void setRegistrocon(Boolean registrocon) {
        this.registrocon = registrocon;
    }
    public Boolean getAuditoriains() {
        return this.auditoriains;
    }
    
    public void setAuditoriains(Boolean auditoriains) {
        this.auditoriains = auditoriains;
    }
    public Boolean getAuditoriaact() {
        return this.auditoriaact;
    }
    
    public void setAuditoriaact(Boolean auditoriaact) {
        this.auditoriaact = auditoriaact;
    }
    public Boolean getAuditoriaeli() {
        return this.auditoriaeli;
    }
    
    public void setAuditoriaeli(Boolean auditoriaeli) {
        this.auditoriaeli = auditoriaeli;
    }
    public Boolean getAuditoriacon() {
        return this.auditoriacon;
    }
    
    public void setAuditoriacon(Boolean auditoriacon) {
        this.auditoriacon = auditoriacon;
    }
    public Boolean getHistorialins() {
        return this.historialins;
    }
    
    public void setHistorialins(Boolean historialins) {
        this.historialins = historialins;
    }
    public Boolean getHistorialact() {
        return this.historialact;
    }
    
    public void setHistorialact(Boolean historialact) {
        this.historialact = historialact;
    }
    public Boolean getHistorialeli() {
        return this.historialeli;
    }
    
    public void setHistorialeli(Boolean historialeli) {
        this.historialeli = historialeli;
    }
    public Boolean getHistorialcon() {
        return this.historialcon;
    }
    
    public void setHistorialcon(Boolean historialcon) {
        this.historialcon = historialcon;
    }
    public Set<Personal> getPersonals() {
        return this.personals;
    }
    
    public void setPersonals(Set<Personal> personals) {
        this.personals = personals;
    }

// GENERADO POR GONZALEZ FERNANDO
    
    public void guardarPerfil(Perfil unPerfil){
        Controlador.getPERSISTENCIA().insert(this);
        JOptionPane.showMessageDialog(null,"El Perfil fue registrado correctamente","Registrar Perfil",JOptionPane.INFORMATION_MESSAGE);
//        return unPersonal.getIdPersonal();
    }
    
    public void actualizarPerfil(Perfil unPerfil){
        Controlador.getPERSISTENCIA().update(this);
        JOptionPane.showMessageDialog(null,"El Perfil se actualizó correctamente","Actualización Perfil",JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void eliminarPerfil(Perfil unPerfil){
        Iterator it=personals.iterator();
        if(it.hasNext()){
            JOptionPane.showMessageDialog(null,"El Perfil no se puede eliminar porque esta relacionada con otros personales","Eliminar Perfil",JOptionPane.ERROR_MESSAGE);
        }else{
            Controlador.getPERSISTENCIA().delete(this);
            JOptionPane.showMessageDialog(null,"El Perfil se eliminó correctamente","Eliminar Perfil",JOptionPane.INFORMATION_MESSAGE);
        }
        
    }

    public void Asignarpermisos(JTable tabla, Perfil per) {
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        Boolean ins0 = (Boolean) model.getValueAt(0, 1);
        Boolean act0 = (Boolean) model.getValueAt(0, 2);
        Boolean eli0 = (Boolean) model.getValueAt(0, 3);
        Boolean con0 = (Boolean) model.getValueAt(0, 4);
        per.setPersonalins(ins0);
        per.setPersonalact(act0);
        per.setPersonaleli(eli0);
        per.setPersonalcon(con0);
        Boolean ins1 = (Boolean) model.getValueAt(1, 1);
        Boolean act1 = (Boolean) model.getValueAt(1, 2);
        Boolean eli1 = (Boolean) model.getValueAt(1, 3);
        Boolean con1 = (Boolean) model.getValueAt(1, 4);
        per.setActividadesins(ins1);
        per.setActividadesact(act1);
        per.setActividadeseli(eli1);
        per.setActividadescon(con1);
        Boolean ins2 = (Boolean) model.getValueAt(2, 1);
        Boolean act2 = (Boolean) model.getValueAt(2, 2);
        Boolean eli2 = (Boolean) model.getValueAt(2, 3);
        Boolean con2 = (Boolean) model.getValueAt(2, 4);
        per.setAsistenciasins(ins2);
        per.setAsistenciasact(act2);
        per.setAsistenciaseli(eli2);
        per.setAsistenciascon(con2);
        Boolean ins3 = (Boolean) model.getValueAt(3, 1);
        Boolean act3 = (Boolean) model.getValueAt(3, 2);
        Boolean eli3 = (Boolean) model.getValueAt(3, 3);
        Boolean con3 = (Boolean) model.getValueAt(3, 4);
        per.setCircularesins(ins3);
        per.setCircularesact(act3);
        per.setCirculareseli(eli3);
        per.setCircularescon(con3);
        Boolean ins4 = (Boolean) model.getValueAt(4, 1);
        Boolean act4 = (Boolean) model.getValueAt(4, 2);
        Boolean eli4 = (Boolean) model.getValueAt(4, 3);
        Boolean con4 = (Boolean) model.getValueAt(4, 4);
        per.setConfiguracionins(ins4);
        per.setConfiguracionact(act4);
        per.setConfiguracioneli(eli4);
        per.setConfiguracioncon(con4);
        Boolean ins5 = (Boolean) model.getValueAt(5, 1);
        Boolean act5 = (Boolean) model.getValueAt(5, 2);
        Boolean eli5 = (Boolean) model.getValueAt(5, 3);
        Boolean con5 = (Boolean) model.getValueAt(5, 4);
        per.setRegistroins(ins5);
        per.setRegistroact(act5);
        per.setRegistroeli(eli5);
        per.setRegistrocon(con5);
        Boolean ins6 = (Boolean) model.getValueAt(6, 1);
        Boolean act6 = (Boolean) model.getValueAt(6, 2);
        Boolean eli6 = (Boolean) model.getValueAt(6, 3);
        Boolean con6 = (Boolean) model.getValueAt(6, 4);
        per.setAuditoriains(ins6);
        per.setAuditoriaact(act6);
        per.setAuditoriaeli(eli6);
        per.setAuditoriacon(con6);
        Boolean ins7 = (Boolean) model.getValueAt(7, 1);
        Boolean act7 = (Boolean) model.getValueAt(7, 2);
        Boolean eli7 = (Boolean) model.getValueAt(7, 3);
        Boolean con7 = (Boolean) model.getValueAt(7, 4);
        per.setHistorialins(ins7);
        per.setHistorialact(act7);
        per.setHistorialeli(eli7);
        per.setHistorialcon(con7);
    }
    
    public void CargarTabla(JTable tabla, Perfil per) {
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        model.setValueAt(per.getPersonalins(), 0, 1);
        model.setValueAt(per.getPersonalact(), 0, 2);
        model.setValueAt(per.getPersonaleli(), 0, 3);
        model.setValueAt(per.getPersonalcon(), 0, 4);
        model.setValueAt(per.getActividadesins(), 1, 1);
        model.setValueAt(per.getActividadesact(), 1, 2);
        model.setValueAt(per.getActividadeseli(), 1, 3);
        model.setValueAt(per.getActividadescon(), 1, 4);
        model.setValueAt(per.getAsistenciasins(), 2, 1);
        model.setValueAt(per.getAsistenciasact(), 2, 2);
        model.setValueAt(per.getAsistenciaseli(), 2, 3);
        model.setValueAt(per.getAsistenciascon(), 2, 4);
        model.setValueAt(per.getCircularesins(), 3, 1);
        model.setValueAt(per.getCircularesact(), 3, 2);
        model.setValueAt(per.getCirculareseli(), 3, 3);
        model.setValueAt(per.getCircularescon(), 3, 4);
        model.setValueAt(per.getConfiguracionins(), 4, 1);
        model.setValueAt(per.getConfiguracionact(), 4, 2);
        model.setValueAt(per.getConfiguracioneli(), 4, 3);
        model.setValueAt(per.getConfiguracioncon(), 4, 4);
        model.setValueAt(per.getRegistroins(), 5, 1);
        model.setValueAt(per.getRegistroact(), 5, 2);
        model.setValueAt(per.getRegistroeli(), 5, 3);
        model.setValueAt(per.getRegistrocon(), 5, 4);
        model.setValueAt(per.getAuditoriains(), 6, 1);
        model.setValueAt(per.getAuditoriaact(), 6, 2);
        model.setValueAt(per.getAuditoriaeli(), 6, 3);
        model.setValueAt(per.getAuditoriacon(), 6, 4);
        model.setValueAt(per.getHistorialins(), 7, 1);
        model.setValueAt(per.getHistorialact(), 7, 2);
        model.setValueAt(per.getHistorialeli(), 7, 3);
        model.setValueAt(per.getHistorialcon(), 7, 4);
    }


}


