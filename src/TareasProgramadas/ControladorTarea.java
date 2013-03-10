/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TareasProgramadas;

import Clases.Agenda;
import Clases.Ano;
import Clases.Asistencia;
import Clases.Dia;
import Clases.Feriado;
import Clases.Iniciofin;
import Clases.Mes;
import Clases.Personal;
import Persistencia.persistencia;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author fer
 */
public class ControladorTarea {
    public static persistencia PERSISTENCIA;

    public ControladorTarea() {
        PERSISTENCIA = new persistencia();
    }

    public static persistencia getPERSISTENCIA() {
        return PERSISTENCIA;
    }
    
    public boolean existeAsistencia(Iniciofin infi){
        Boolean band=false;
        Iterator it=infi.getAsistencias().iterator();
        while(it.hasNext()){
            band=true;
            it.next();
        }
        return band;
    }
    
    public boolean ExisteTareaContinua(Iniciofin in){
        boolean i=false;
        Calendar cel=Calendar.getInstance();
        Personal per=in.getDia().getMes().getAno().getAgenda().getPersonal();
        Iterator<Iniciofin> it= ControladorTarea.getPERSISTENCIA().ObtenerInicioFin(cel.getTime().getDate(), cel.getTime().getMonth(),cel.getTime().getYear()+1900).iterator();
        cel.setTime(in.getFin());
        cel.add(Calendar.MINUTE, 30);
        while(it.hasNext()){
            Iniciofin ini=it.next();
            Personal per1=ini.getDia().getMes().getAno().getAgenda().getPersonal();
            if(cel.getTime().compareTo(ini.getInicio())<=0 && per1.getIdPersonal()==per.getIdPersonal()){
                    i=true;
            }
        }
        return i;
    }
    
    public boolean isFeriado(Date fecha) {
        boolean band = false;
        Iterator it = PERSISTENCIA.getFeriados().iterator();
        while (it.hasNext()) {
            try {
                Feriado fer = (Feriado) it.next();
                SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                String i=formateador.format(fecha);
                Date fe=formateador.parse(i);
                Date e=fer.getDia();
                if (fe.compareTo(fer.getDia()) == 0) {
                    band = true;
                }
            } catch (ParseException ex) {
                Logger.getLogger(ControladorTarea.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return band;
    }
    
    public void ObtenerListadeldia(){
        try{
            Date cal=Calendar.getInstance().getTime();
            Calendar cel=Calendar.getInstance();
            Iterator<Iniciofin> it= ControladorTarea.getPERSISTENCIA().ObtenerInicioFin(cel.getTime().getDate(), cel.getTime().getMonth(),cel.getTime().getYear()+1900).iterator();
            while(it.hasNext()){
                Iniciofin in= it.next();
                String com=in.getDia().getMes().getAno().getAgenda().getTarea().getComentario();
                if(com.equals("CLASE")){
                    Date aux=new Date();
                    aux.setDate(in.getDia().getDia());
                    aux.setMonth(in.getDia().getMes().getMes());
                    aux.setYear(in.getDia().getMes().getAno().getAno()-1900);
                    boolean band=isFeriado(aux);
                    if(band==true){
                        in.setEstadoInicio(null);
                        in.setEstadoFin(null);
                        in.actualizarIniciofin(in);
                    }
                }
                if(!existeAsistencia(in)){
                    if(in.getDia().getMes().getAno().getAgenda().getTarea().getEstado()==true && in.getDia().getMes().getAno().getAgenda().getPersonal().getEstado()==true){
                        if(in.getEstadoInicio()!=null && in.getEstadoFin()!=null){
                            System.out.println("existe obligación de inicio y fin(clase)");
                            if(!ExisteTareaContinua(in)){    //no existe tarea continua
                                if(in.getEstadoInicio()==true && in.getEstadoFin()==true){
                                    System.out.println("estadoinicio=true y estadofin=true");
                                    Asistencia asis= new Asistencia();
                                    asis.setIniciofin(in);
                                    asis.setEstado(true);
                                    asis.setTardanza(false);                               
                                    asis.guardarAsistencia(asis);
                                }else if(in.getEstadoInicio()==true && in.getEstadoFin()==false){
                                    System.out.println("ausencia momentanea de uno de los estados");
                                    cel.setTime(in.getFin());
                                    cel.add(Calendar.MINUTE, 30);
                                    String s=new SimpleDateFormat("HH:mm").format(cal);
                                    SimpleDateFormat formateador=new SimpleDateFormat("HH:mm");
                                    Date a=formateador.parse(s);
                                    if(cel.getTime().compareTo(a)<0){
                                        Asistencia asis= new Asistencia();
                                        asis.setIniciofin(in);
                                        asis.setEstado(true);
                                        asis.setTardanza(true);
                                        if(!this.existeAsistencia(in)){
                                        asis.guardarAsistencia(asis);
                                        }
                                        System.out.println("tardanza del personal");
                                    }
                                }else if(in.getEstadoInicio()==false && in.getEstadoFin()==true){
                                    System.out.println("ausencia momentanea de uno de los estados");
                                    cel.setTime(in.getFin());
                                    cel.add(Calendar.MINUTE, 30);
                                    String s=new SimpleDateFormat("HH:mm").format(cal);
                                    SimpleDateFormat formateador=new SimpleDateFormat("HH:mm");
                                    Date a=formateador.parse(s);
                                    if(cel.getTime().compareTo(a)<0){
                                        Asistencia asis= new Asistencia();
                                        asis.setIniciofin(in);
                                        asis.setEstado(true);
                                        asis.setTardanza(true);
                                        if(!this.existeAsistencia(in)){
                                        asis.guardarAsistencia(asis);
                                        }
                                        System.out.println("tardanza del personal");
                                    }
                                }else if(in.getEstadoInicio()==false && in.getEstadoFin()==false){
                                    System.out.println("ausencia momentanea de uno de los estados");
                                    cel.setTime(in.getFin());
                                    cel.add(Calendar.MINUTE, 30);
                                    String s=new SimpleDateFormat("HH:mm").format(cal);
                                    SimpleDateFormat formateador=new SimpleDateFormat("HH:mm");
                                    Date a=formateador.parse(s);
                                    if(cel.getTime().compareTo(a)<0){
                                        Asistencia asis= new Asistencia();
                                        asis.setIniciofin(in);
                                        asis.setEstado(false);
                                        asis.setTardanza(false);
                                        if(!this.existeAsistencia(in)){
                                        asis.guardarAsistencia(asis);
                                        }
                                        System.out.println("ausencia del personal");
                                    }
                                }
                            }else{ //existe tarea continua
                                if(in.getEstadoInicio()==true && in.getEstadoFin()==true){
                                    System.out.println("estadoinicio=true y estadofin=true");
                                    Asistencia asis= new Asistencia();
                                    asis.setIniciofin(in);
                                    asis.setEstado(true);
                                    asis.setTardanza(false);
                                    if(!this.existeAsistencia(in)){
                                        asis.guardarAsistencia(asis);
                                    }
                                }else if(in.getEstadoInicio()==false && in.getEstadoFin()==true){
                                    System.out.println("estadoinicio=false y estadofin=true");
                                    Asistencia asis= new Asistencia();
                                    asis.setIniciofin(in);
                                    asis.setEstado(true);
                                    asis.setTardanza(false);
                                    if(!this.existeAsistencia(in)){
                                        asis.guardarAsistencia(asis);
                                    }                            }
                            }
                        }else if(in.getEstadoInicio()!=null && in.getEstadoFin()==null){
                            System.out.println("existe obligación de inicio pero no de fin(reunion, extracurricular u otro)");
                            if(in.getEstadoInicio()==true){
                                System.out.println("estadoinicio=true");
                                Asistencia asis= new Asistencia();
                                asis.setIniciofin(in);
                                asis.setEstado(true);
                                asis.setTardanza(false);
                                if(!this.existeAsistencia(in)){
                                    asis.guardarAsistencia(asis);
                                }
                            }else{
                                Calendar cel3=Calendar.getInstance();
                                cel3.setTime(in.getFin());
                                cel3.add(Calendar.MINUTE, -10);
                                String s=new SimpleDateFormat("HH:mm").format(cal);
                                SimpleDateFormat formateador=new SimpleDateFormat("HH:mm");
                                Date a=formateador.parse(s);
                                if(cel3.getTime().compareTo(a)<0){
                                    Asistencia asis= new Asistencia();
                                    asis.setIniciofin(in);
                                    asis.setEstado(false);
                                    asis.setTardanza(false);
                                    if(!this.existeAsistencia(in)){
                                        asis.guardarAsistencia(asis);
                                    }
                                    System.out.println("ausencia del personal");
                                }
                            }
                        }
                    }
                }
            }
        }catch (Exception e){System.out.println(e.toString());}
    }
}
