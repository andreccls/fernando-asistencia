package Clases;
// Generated 18-jun-2013 14:10:38 by Hibernate Tools 3.2.1.GA


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
     private byte[] imagen;
     private String leyenda;
     private Set<Auditoria> auditorias = new HashSet<Auditoria>(0);
     private Set<Departamento> departamentos = new HashSet<Departamento>(0);
     private Set<Tarea> tareas = new HashSet<Tarea>(0);
     private Set<Circular> circulars = new HashSet<Circular>(0);
     private Set<Curso> cursos = new HashSet<Curso>(0);
     private Set<Personal> personals = new HashSet<Personal>(0);
     private Set<Anolectivo> anolectivos = new HashSet<Anolectivo>(0);
     private Set<DetalleEstablecimiento> detalleEstablecimientos = new HashSet<DetalleEstablecimiento>(0);

    public Establecimiento() {
    }

    public Establecimiento(String nombre, String calle, Integer altura, String piso, String depto, byte[] imagen, String leyenda, Set<Auditoria> auditorias, Set<Departamento> departamentos, Set<Tarea> tareas, Set<Circular> circulars, Set<Curso> cursos, Set<Personal> personals, Set<Anolectivo> anolectivos, Set<DetalleEstablecimiento> detalleEstablecimientos) {
       this.nombre = nombre;
       this.calle = calle;
       this.altura = altura;
       this.piso = piso;
       this.depto = depto;
       this.imagen = imagen;
       this.leyenda = leyenda;
       this.auditorias = auditorias;
       this.departamentos = departamentos;
       this.tareas = tareas;
       this.circulars = circulars;
       this.cursos = cursos;
       this.personals = personals;
       this.anolectivos = anolectivos;
       this.detalleEstablecimientos = detalleEstablecimientos;
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
    public byte[] getImagen() {
        return this.imagen;
    }
    
    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
    public String getLeyenda() {
        return this.leyenda;
    }
    
    public void setLeyenda(String leyenda) {
        this.leyenda = leyenda;
    }
    public Set<Auditoria> getAuditorias() {
        return this.auditorias;
    }
    
    public void setAuditorias(Set<Auditoria> auditorias) {
        this.auditorias = auditorias;
    }
    public Set<Departamento> getDepartamentos() {
        return this.departamentos;
    }
    
    public void setDepartamentos(Set<Departamento> departamentos) {
        this.departamentos = departamentos;
    }
    public Set<Tarea> getTareas() {
        return this.tareas;
    }
    
    public void setTareas(Set<Tarea> tareas) {
        this.tareas = tareas;
    }
    public Set<Circular> getCirculars() {
        return this.circulars;
    }
    
    public void setCirculars(Set<Circular> circulars) {
        this.circulars = circulars;
    }
    public Set<Curso> getCursos() {
        return this.cursos;
    }
    
    public void setCursos(Set<Curso> cursos) {
        this.cursos = cursos;
    }
    public Set<Personal> getPersonals() {
        return this.personals;
    }
    
    public void setPersonals(Set<Personal> personals) {
        this.personals = personals;
    }
    public Set<Anolectivo> getAnolectivos() {
        return this.anolectivos;
    }
    
    public void setAnolectivos(Set<Anolectivo> anolectivos) {
        this.anolectivos = anolectivos;
    }
    public Set<DetalleEstablecimiento> getDetalleEstablecimientos() {
        return this.detalleEstablecimientos;
    }
    
    public void setDetalleEstablecimientos(Set<DetalleEstablecimiento> detalleEstablecimientos) {
        this.detalleEstablecimientos = detalleEstablecimientos;
    }

// GENERADO POR GONZALEZ FERNANDO
    
    public void guardarEstablecimiento(Establecimiento unEstablecimiento){
        Controlador.getPERSISTENCIA().insert(this);

        JOptionPane.showMessageDialog(null,"El Establecimiento "+ unEstablecimiento.getNombre() +
                " se guardo correctamente","Registrar establecimiento",JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void ActualizarEstablecimiento(Establecimiento unEstablecimiento){
        Controlador.getPERSISTENCIA().update(this);

//        JOptionPane.showMessageDialog(null,"El Establecimiento se actualizó correctamente","Actualizar establecimiento",JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void eliminarEstablecimiento(Establecimiento unEstablecimiento){
        Iterator it=anolectivos.iterator();
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

    public int crearPersonal(Tipodoc tipodoc, Perfil perfil, Establecimiento establecimiento, byte[] codigo, String dni, String apellido, String nombre, String cuil, String calle, Integer altura, String piso, String depto, String correoElectronico, String sexo, String estadoCivil, Date ingreso, Boolean estado, Boolean familiar, Date fechaNac, Set<Registroacceso> registroaccesos, Set<Circularpersonal> circularpersonals, Set<Declaracionjurada> declaracionjuradas, Set<Personalnodocente> personalnodocentes, Set<Personaldocente> personaldocentes, Set<Telefono> telefonos, Set<PersonalDepartamento> personalDepartamentos, Set<PersonalFamiliar> personalFamiliarsForIdPersonal, Set<PersonalFamiliar> personalFamiliarsForIdFamiliar, Set<Agenda> agendas, Set<Auditoria> auditoriasForIdAuditor, Set<Licencia> licencias, Set<Auditoria> auditoriasForIdPersonal){
        Personal unPersonal=new Personal(tipodoc,perfil, establecimiento, codigo, dni, apellido, nombre, cuil, calle,altura,piso,depto, correoElectronico,sexo, estadoCivil, ingreso, estado, familiar, fechaNac,registroaccesos, circularpersonals,declaracionjuradas, personalnodocentes, personaldocentes, telefonos, personalDepartamentos, personalFamiliarsForIdPersonal, personalFamiliarsForIdFamiliar, agendas,auditoriasForIdAuditor,licencias,auditoriasForIdPersonal);
        int per=0;
        if(!existePersonal(unPersonal)){
            unPersonal.guardarPersonal(unPersonal);
            per=unPersonal.getIdPersonal();
            //agregarReserva(unaReserva);
        }
        return per;
     }
    
//     public Tarea getTarea(String nombre) {
//        Tarea tar=null;
//        Iterator it=tareas.iterator();
//           while(it.hasNext()){
//           Tarea t=(Tarea) it.next();
//           if(nombre.equals(t.getNombre())){
//               tar=t;
//               break;
//           }
//         }
//        return tar;
//    }
//    
//     public Tarea getTarea(int id) {
//        Tarea tar=null;
//        Iterator it=tareas.iterator();
//           while(it.hasNext()){
//           Tarea t=(Tarea) it.next();
//           if(t.getIdTarea()==id){
//               tar=t;
//               break;
//           }
//         }
//        return tar;
//    }
//     
//    public Tarea existeTarea(Tarea unaTarea){
//          Tarea tmpres=null;
//          Iterator it=tareas.iterator();
//           while(it.hasNext()){
//           Tarea tar=(Tarea) it.next();
//           if(tar.getNombre().equals(unaTarea.getNombre())){
//               tmpres=tar;
//               break;
//           }
//         }
//         return tmpres;
//     }
         
//    public Tarea crearTarea(Lugar lugar, Establecimiento establecimiento, String nombre, String comentario, Boolean estado, Date diaInicio, Date diaFin, Set<Tareareunion> tareareunions, Set<Auditoria> auditorias, Set<Agenda> agendas, Set<Tareaotro> tareaotros, Set<Tareaextracurricular> tareaextracurriculars, Set<Tareaclase> tareaclases){
//        Tarea unaTarea=new Tarea(lugar,establecimiento, nombre,comentario, estado,diaInicio,diaFin, tareareunions,auditorias, agendas,tareaotros, tareaextracurriculars, tareaclases);
//        Tarea aux=existeTarea(unaTarea);
//        if(aux==null){
//            unaTarea.guardarTarea(unaTarea);
//            //agregarReserva(unaReserva);
//            return unaTarea;
//        }else{
//            JOptionPane.showMessageDialog(null, "La tarea ya existe");
//            return aux;
//        }
//     }   
    
    public void crearDepartamento(Establecimiento establecimiento, String nombre,Set<Auditoria> auditorias, Set<PersonalDepartamento> personalDepartamentos){
        Departamento unDepartamento =new Departamento (establecimiento, nombre,auditorias, personalDepartamentos);
            unDepartamento.guardarDepartamento(unDepartamento);
            //agregarReserva(unaReserva);
     }
    
    public void crearTipodoc(String tipodoc, Set<Personal> personals){
        Tipodoc unTipodoc =new Tipodoc (tipodoc, personals);
            unTipodoc.guardarTipodoc(unTipodoc);
            //agregarReserva(unaReserva);
     }

    
    
    

    public Anolectivo getAnoLectivo(int ano){
        Anolectivo an=new Anolectivo();
        Iterator it=anolectivos.iterator();
        while(it.hasNext()){
            Anolectivo ann=(Anolectivo) it.next();
            if(ann.getAno()==ano){
                an=ann;
            }
        }
        return an;
    }


}


