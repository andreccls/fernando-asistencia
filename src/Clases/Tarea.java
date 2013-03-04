package Clases;
// Generated 19/11/2012 14:01:36 by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 * Tarea generated by hbm2java
 */
public class Tarea  implements java.io.Serializable {


     private Integer idTarea;
     private Establecimiento establecimiento;
     private String nombre;
     private String lugar;
     private String comentario;
     private Boolean estado;
     private Set<Tareareunion> tareareunions = new HashSet<Tareareunion>(0);
     private Set<Agenda> agendas = new HashSet<Agenda>(0);
     private Set<Tareaotro> tareaotros = new HashSet<Tareaotro>(0);
     private Set<Tareaextracurricular> tareaextracurriculars = new HashSet<Tareaextracurricular>(0);
     private Set<Tareaclase> tareaclases = new HashSet<Tareaclase>(0);

    public Tarea() {
    }

	
    public Tarea(Establecimiento establecimiento, String nombre) {
        this.establecimiento = establecimiento;
        this.nombre = nombre;
    }
    public Tarea(Establecimiento establecimiento, String nombre, String lugar, String comentario, Boolean estado, Set<Tareareunion> tareareunions, Set<Agenda> agendas, Set<Tareaotro> tareaotros, Set<Tareaextracurricular> tareaextracurriculars, Set<Tareaclase> tareaclases) {
       this.establecimiento = establecimiento;
       this.nombre = nombre;
       this.lugar = lugar;
       this.comentario = comentario;
       this.estado = estado;
       this.tareareunions = tareareunions;
       this.agendas = agendas;
       this.tareaotros = tareaotros;
       this.tareaextracurriculars = tareaextracurriculars;
       this.tareaclases = tareaclases;
    }
   
    public Integer getIdTarea() {
        return this.idTarea;
    }
    
    public void setIdTarea(Integer idTarea) {
        this.idTarea = idTarea;
    }
    public Establecimiento getEstablecimiento() {
        return this.establecimiento;
    }
    
    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getLugar() {
        return this.lugar;
    }
    
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
    public String getComentario() {
        return this.comentario;
    }
    
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    public Boolean getEstado() {
        return this.estado;
    }
    
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    public Set<Tareareunion> getTareareunions() {
        return this.tareareunions;
    }
    
    public void setTareareunions(Set<Tareareunion> tareareunions) {
        this.tareareunions = tareareunions;
    }
    public Set<Agenda> getAgendas() {
        return this.agendas;
    }
    
    public void setAgendas(Set<Agenda> agendas) {
        this.agendas = agendas;
    }
    public Set<Tareaotro> getTareaotros() {
        return this.tareaotros;
    }
    
    public void setTareaotros(Set<Tareaotro> tareaotros) {
        this.tareaotros = tareaotros;
    }
    public Set<Tareaextracurricular> getTareaextracurriculars() {
        return this.tareaextracurriculars;
    }
    
    public void setTareaextracurriculars(Set<Tareaextracurricular> tareaextracurriculars) {
        this.tareaextracurriculars = tareaextracurriculars;
    }
    public Set<Tareaclase> getTareaclases() {
        return this.tareaclases;
    }
    
    public void setTareaclases(Set<Tareaclase> tareaclases) {
        this.tareaclases = tareaclases;
    }

//// GENERADO POR GONZALEZ FERNANDO
       
    public int guardarTarea(Tarea unaTarea){
    Controlador.getPERSISTENCIA().insert(this);
    int id=unaTarea.getIdTarea();
    JOptionPane.showMessageDialog(null,"La tarea se guardó correctamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
    return id;
    }
    
    public void ActualizarTarea(Tarea unaTarea){
    Controlador.getPERSISTENCIA().update(this);

    JOptionPane.showMessageDialog(null,"La tarea se actualizó correctamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void crearTareaclase(TareaclaseId id, Tarea tarea, String aula, Integer numero){
        Tareaclase unaTareaclase=new Tareaclase(id,tarea,aula,numero);
            unaTareaclase.guardarTareaclase(unaTareaclase);
     }
    
    public void crearTareareunion(TareareunionId id, Tarea tarea, String motivo, String caracter){
        Tareareunion unaTareareunion=new Tareareunion(id,tarea,motivo,caracter);
            unaTareareunion.guardarTareareunion(unaTareareunion);
     }
    
    public void crearTareaextracurricular(TareaextracurricularId id, Tarea tarea, Date diaInicio, Date diaFin){
        Tareaextracurricular unaTareaextracurricular=new Tareaextracurricular(id,tarea,diaInicio,diaFin);
            unaTareaextracurricular.guardarTareaextracurricular(unaTareaextracurricular);
     }
    
    public void crearTareaotro(TareaotroId id, Tarea tarea, String caracteristica, Date diaInicio, Date diaFin){
        Tareaotro unaTareaotro=new Tareaotro(id,tarea,caracteristica,diaInicio,diaFin);
            unaTareaotro.guardarTareaotro(unaTareaotro);
     }
    
    public Date ObtenerFechaMayor(int anio){
        Date mayor = new Date();
        mayor.setYear(anio-20);
        Date aux=new Date();
        aux.setYear(anio);
        Iterator it=agendas.iterator();
        while(it.hasNext()){
            Agenda age=(Agenda) it.next();
            Iterator ita=age.getAnos().iterator();
            while(ita.hasNext()){
                Ano an=(Ano) ita.next();
                if(an.getAno()==anio+1900){
                    Iterator itm=an.getMeses().iterator();
                    while(itm.hasNext()){
                        Mes me=(Mes) itm.next();
                        aux.setMonth(me.getMes());
                        Iterator itd=me.getDias().iterator();
                        while(itd.hasNext()){
                            Dia di=(Dia) itd.next();
                            aux.setDate(di.getDia());
                            if(aux.compareTo(mayor)>0){
                                mayor.setDate(aux.getDate());
                                mayor.setMonth(aux.getMonth());
                                mayor.setYear(aux.getYear());
                            }
                        }
                    }
                }
            }
            break;
        } 
        return mayor;
    }
    
    public Date ObtenerFechaMenor(int anio){
        Date menor = new Date();
        menor.setYear(anio+20);
        Date aux=new Date();
        aux.setYear(anio);
        Iterator it=agendas.iterator();
        while(it.hasNext()){
            Agenda age=(Agenda) it.next();
            Iterator ita=age.getAnos().iterator();
            while(ita.hasNext()){
                Ano an=(Ano) ita.next();
                if(an.getAno()==anio+1900){
                    Iterator itm=an.getMeses().iterator();
                    while(itm.hasNext()){
                        Mes me=(Mes) itm.next();
                        aux.setMonth(me.getMes());
                        Iterator itd=me.getDias().iterator();
                        while(itd.hasNext()){
                            Dia di=(Dia) itd.next();
                            aux.setDate(di.getDia());
                            if(aux.compareTo(menor)<0){
                                menor.setDate(aux.getDate());
                                menor.setMonth(aux.getMonth());
                                menor.setYear(aux.getYear());
                            }
                        }
                    }
                }
            }
            break;
        } 
        return menor;
    }
    
    public void BorrarTodo() {
        Iterator it = agendas.iterator();
        Date aux=new Date();
        Date hoy=new Date();
        while (it.hasNext()) {
            Agenda age = (Agenda) it.next();
            Iterator ita = age.getAnos().iterator();
            while (ita.hasNext()) {
                Ano an = (Ano) ita.next();
                aux.setYear(an.getAno()-1900);
                Iterator itm = an.getMeses().iterator();
                while (itm.hasNext()) {
                    Mes me = (Mes) itm.next();
                    aux.setMonth(me.getMes());
                    Iterator itd = me.getDias().iterator();
                    while (itd.hasNext()) {
                        Dia di = (Dia) itd.next();
                        aux.setDate(di.getDia());
                        aux.setHours(di.getIniciofins().iterator().next().getFin().getHours());
                        aux.setMinutes(di.getIniciofins().iterator().next().getFin().getMinutes());
                        if(aux.compareTo(hoy)>=0){
                            Iterator iti=di.getIniciofins().iterator();
                            while(iti.hasNext()){
                                Iniciofin ini=(Iniciofin) iti.next();
                                //Asistencia asi=new Asistencia();
                                if (ini.getAsistencias().iterator().hasNext()) {
                                    Asistencia asi = ini.getAsistencias().iterator().next();
                                    Iterator<Justificacion> jus = asi.getJustificacions().iterator();
                                    //Justificacion just;
                                    while (jus.hasNext()) {
                                        Justificacion just = (Justificacion) jus.next();
                                        just.eliminarJustificacion(just);
                                    }
                                    asi.eliminarAsistencia(asi);
                                }
                                ini.eliminarIniciofin(ini);
                                di.eliminarDia(di);
                            }
                        }
                    }
                    if(!Controlador.getPERSISTENCIA().getDias(me.getIdMes()).iterator().hasNext()){
                        me.eliminarMes(me);
                    }
                }
                if(!Controlador.getPERSISTENCIA().getMeses(an.getIdAno()).iterator().hasNext()){
                    an.eliminarAno(an);
                }
            }
            Iterator itf=age.getFrancos().iterator();
            if(itf.hasNext()){
                Franco fran=(Franco)itf.next();
                fran.eliminarFranco(fran);
            }
            if(!Controlador.getPERSISTENCIA().getAnos(age.getId().getIdPersonal(), age.getId().getIdTarea()).iterator().hasNext()){
                age.eliminarAgenda(age);
            }
        }
    }
    
    public Agenda ObtenerAgenda(int idper, int idrev) {
        Agenda age = new Agenda();
        Iterator it=agendas.iterator();
        while(it.hasNext()){
            Agenda ag=(Agenda) it.next();
            if(ag.getId().getIdPersonal()==idper && ag.getRevista().getIdRevista()==idrev){
                age=ag;
                break;
            }
        }
        return age;
    }
}

