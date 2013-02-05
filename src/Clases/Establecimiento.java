package Clases;
// Generated 31/01/2013 23:17:19 by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 * Establecimiento generated by hbm2java
 */
public class Establecimiento  implements java.io.Serializable {
     private Integer idEstablecimiento;
     private String nombre;
     private String calle;
     private Integer altura;
     private String piso;
     private String depto;
     private Set<Feriado> feriados = new HashSet<Feriado>(0);
     private Set<Tarea> tareas = new HashSet<Tarea>(0);
     private Set<Departamento> departamentos = new HashSet<Departamento>(0);
     private Set<Personal> personals = new HashSet<Personal>(0);
     private Set<DetalleEstablecimiento> detalleEstablecimientos = new HashSet<DetalleEstablecimiento>(0);
     private Set<Declaracionjurada> declaracionjuradas = new HashSet<Declaracionjurada>(0);

    public Establecimiento() {
    }

    public Establecimiento(String nombre, String calle, Integer altura, String piso, String depto, Set<Feriado> feriados, Set<Tarea> tareas, Set<Departamento> departamentos, Set<Personal> personals, Set<DetalleEstablecimiento> detalleEstablecimientos, Set<Declaracionjurada> declaracionjuradas) {
       this.nombre = nombre;
       this.calle = calle;
       this.altura = altura;
       this.piso = piso;
       this.depto = depto;
       this.feriados = feriados;
       this.tareas = tareas;
       this.departamentos = departamentos;
       this.personals = personals;
       this.detalleEstablecimientos = detalleEstablecimientos;
       this.declaracionjuradas = declaracionjuradas;
    }
    
    @Override
    public String toString() {
        return nombre;
    }
   
    public Integer getIdEstablecimiento() {
        return this.idEstablecimiento;
    }
    
    public void setIdEstablecimiento(Integer idEstablecimiento) {
        this.idEstablecimiento = idEstablecimiento;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCalle() {
        return this.calle;
    }
    
    public void setCalle(String calle) {
        this.calle = calle;
    }
    public Integer getAltura() {
        return this.altura;
    }
    
    public void setAltura(Integer altura) {
        this.altura = altura;
    }
    public String getPiso() {
        return this.piso;
    }
    
    public void setPiso(String piso) {
        this.piso = piso;
    }
    public String getDepto() {
        return this.depto;
    }
    
    public void setDepto(String depto) {
        this.depto = depto;
    }
    public Set<Feriado> getFeriados() {
        return this.feriados;
    }
    
    public void setFeriados(Set<Feriado> feriados) {
        this.feriados = feriados;
    }
    public Set<Tarea> getTareas() {
        return this.tareas;
    }
    
    public void setTareas(Set<Tarea> tareas) {
        this.tareas = tareas;
    }
    public Set<Departamento> getDepartamentos() {
        return this.departamentos;
    }
    
    public void setDepartamentos(Set<Departamento> departamentos) {
        this.departamentos = departamentos;
    }
    public Set<Personal> getPersonals() {
        return this.personals;
    }
    
    public void setPersonals(Set<Personal> personals) {
        this.personals = personals;
    }
    public Set<DetalleEstablecimiento> getDetalleEstablecimientos() {
        return this.detalleEstablecimientos;
    }
    
    public void setDetalleEstablecimientos(Set<DetalleEstablecimiento> detalleEstablecimientos) {
        this.detalleEstablecimientos = detalleEstablecimientos;
    }
    public Set<Declaracionjurada> getDeclaracionjuradas() {
        return this.declaracionjuradas;
    }
    
    public void setDeclaracionjuradas(Set<Declaracionjurada> declaracionjuradas) {
        this.declaracionjuradas = declaracionjuradas;
    }

// GENERADO POR GONZALEZ FERNANDO
    
    public void guardarEstablecimiento(Establecimiento unEstablecimiento){
        Controlador.getPERSISTENCIA().insert(this);

        JOptionPane.showMessageDialog(null,"El Establecimiento "+ 
                String.valueOf(unEstablecimiento.getIdEstablecimiento()) +
                " se guardo correctamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void eliminarEstablecimiento(Establecimiento unEstablecimiento){
        Iterator it=detalleEstablecimientos.iterator();
        if(it.hasNext()){
            JOptionPane.showMessageDialog(null,"El establecimiento no se puede eliminar porque esta relacionada con otras declaraciones juradas","Mensaje",JOptionPane.INFORMATION_MESSAGE);
        }else{
            Controlador.getPERSISTENCIA().delete(this);
            JOptionPane.showMessageDialog(null,"El Establecimiento "+ 
                    String.valueOf(unEstablecimiento.getIdEstablecimiento()) +
                    " se eliminó correctamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public Personal getPersonal(int idper) {
        Personal per=new Personal();
        Iterator it=personals.iterator();
           while(it.hasNext()){
           Personal p=(Personal) it.next();
           if(idper == p.getIdPersonal()){
               per=p;
               break;
           }
         }
        return per;
    }
    //Controlador Drive=new Controlador();
    public Personal getPersonal(Tipodoc tipo,String dni) {
        Personal per=new Personal();
        Iterator it=personals.iterator();
           while(it.hasNext()){
           Personal p=(Personal) it.next();
           if(p.getDni().equals(dni)){
               Tipodoc tip=p.getTipodoc();
               if(tip.getTipodoc().equals(tipo.getTipodoc())){
                   per=p;
                   break;
               }
           }
         }
        return per;
    }
      
     public boolean existePersonal(Personal unPersonal){
          boolean tmpres=false;
          Iterator it=personals.iterator();
           while(it.hasNext()){
           Personal per=(Personal) it.next();
           if(unPersonal.equals(per)){
               tmpres=true;
               break;
           }
         }
         return tmpres;
     }

    public int crearPersonal(Tipodoc tipodoc, Establecimiento establecimiento, byte[] codigo, String dni, String apellido, String nombre, String cuil, String calle, Integer altura, String piso, String depto, String correoElectronico, String sexo, String estadoCivil, Date ingreso, Boolean estado, Boolean familiar, Date fechaNac, Set<PersonalDepartamento> personalDepartamentos, Set<Declaracionjurada> declaracionjuradas, Set<Personalnodocente> personalnodocentes, Set<PersonalFamiliar> personalFamiliarsForIdPersonal, Set<PersonalFamiliar> personalFamiliarsForIdFamiliar, Set<Personaldocente> personaldocentes, Set<Agenda> agendas, Set<Telefono> telefonos){
        Personal unPersonal=new Personal(tipodoc, establecimiento, codigo, dni, apellido, nombre, cuil, calle,altura,piso,depto, correoElectronico,sexo, estadoCivil, ingreso, estado, familiar, fechaNac, personalDepartamentos,declaracionjuradas, personalnodocentes, personalFamiliarsForIdPersonal, personalFamiliarsForIdFamiliar, personaldocentes, agendas, telefonos);
        int per=0;
        if(!existePersonal(unPersonal)){
            unPersonal.guardarPersonal(unPersonal);
            per=unPersonal.getIdPersonal();
            //agregarReserva(unaReserva);
        }
        return per;
     }
    
     public Tarea getTarea(String nombre) {
        Tarea tar=null;
        Iterator it=tareas.iterator();
           while(it.hasNext()){
           Tarea t=(Tarea) it.next();
           if(nombre.equals(t.getNombre())){
               tar=t;
               break;
           }
         }
        return tar;
    }
    
     public Tarea getTarea(int id) {
        Tarea tar=null;
        Iterator it=tareas.iterator();
           while(it.hasNext()){
           Tarea t=(Tarea) it.next();
           if(t.getIdTarea()==id){
               tar=t;
               break;
           }
         }
        return tar;
    }
     
    public Tarea existeTarea(Tarea unaTarea){
          Tarea tmpres=null;
          Iterator it=tareas.iterator();
           while(it.hasNext()){
           Tarea tar=(Tarea) it.next();
           if(tar.getNombre().equals(unaTarea.getNombre())){
               tmpres=tar;
               break;
           }
         }
         return tmpres;
     }
         
    public Tarea crearTarea(Establecimiento establecimiento, String nombre, String lugar, String comentario, Boolean estado, Set<Tareareunion> tareareunions, Set<Agenda> agendas, Set<Tareaotro> tareaotros, Set<Tareaextracurricular> tareaextracurriculars, Set<Tareaclase> tareaclases){
        Tarea unaTarea=new Tarea(establecimiento, nombre, lugar,comentario, estado, tareareunions, agendas,tareaotros, tareaextracurriculars, tareaclases);
        Tarea aux=existeTarea(unaTarea);
        if(aux==null){
            unaTarea.guardarTarea(unaTarea);
            //agregarReserva(unaReserva);
            return unaTarea;
        }else{
            JOptionPane.showMessageDialog(null, "La tarea ya existe");
            return aux;
        }
        
     }   
    
    public void crearDepartamento(Establecimiento establecimiento, String nombre, Set<PersonalDepartamento> personalDepartamentos){
        Departamento unDepartamento =new Departamento (establecimiento, nombre, personalDepartamentos);
            unDepartamento.guardarDepartamento(unDepartamento);
            //agregarReserva(unaReserva);
     }
    
    public void crearTipodoc(String tipodoc, Set<Personal> personals){
        Tipodoc unTipodoc =new Tipodoc (tipodoc, personals);
            unTipodoc.guardarTipodoc(unTipodoc);
            //agregarReserva(unaReserva);
     }

    
    
    public boolean existeFeriado(Date unafecha) {
        boolean tmpres = false;
        Iterator it = feriados.iterator();
        while (it.hasNext()) {
            Feriado fer = (Feriado) it.next();
            if (fer.getDia().compareTo(unafecha)==0) {
                tmpres = true;
                break;
            }
        }
        return tmpres;
    }
}


