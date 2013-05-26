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
import Clases.Licencia;
import Clases.Mes;
import Clases.Personal;
import Clases.Registroacceso;
import Clases.Tarea;
import Clases.Tareaextracurricular;
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
        if(it.hasNext()){
            band=true;
        }
        return band;
    }
    
//    public boolean ExisteTareaContinua(Iniciofin in){
//        boolean i=false;
//        Calendar cel=Calendar.getInstance();
//        Personal per=in.getDia().getMes().getAno().getAgenda().getPersonal();
//        Iterator<Iniciofin> it= ControladorTarea.getPERSISTENCIA().ObtenerInicioFin(cel.getTime().getDate(), cel.getTime().getMonth(),cel.getTime().getYear()+1900).iterator();
//        cel.setTime(in.getFin());
//        cel.add(Calendar.MINUTE, 30);
//        while(it.hasNext()){
//            Iniciofin ini=it.next();
//            Personal per1=ini.getDia().getMes().getAno().getAgenda().getPersonal();
//            if(cel.getTime().compareTo(ini.getInicio())<=0 && per1.getIdPersonal()==per.getIdPersonal()){
//                    i=true;
//            }
//        }
//        return i;
//    }
    
    
    
//    public void ObtenerListadeldia(){
//        try{
//            Date cal=Calendar.getInstance().getTime();
//            Calendar cel=Calendar.getInstance();
//            Iterator<Iniciofin> it= ControladorTarea.getPERSISTENCIA().ObtenerInicioFin(cel.getTime().getDate(), cel.getTime().getMonth(),cel.getTime().getYear()+1900).iterator();
//            while(it.hasNext()){
//                Iniciofin in= it.next();
//                String com=in.getDia().getMes().getAno().getAgenda().getTarea().getComentario();
//                if(com.equals("CLASE")){
//                    Date aux=new Date();
//                    aux.setDate(in.getDia().getDia());
//                    aux.setMonth(in.getDia().getMes().getMes());
//                    aux.setYear(in.getDia().getMes().getAno().getAno()-1900);
//                    boolean band=isFeriado(aux);
//                    if(band==true){
//                        in.setEstadoInicio(null);
//                        in.setEstadoFin(null);
//                        in.actualizarIniciofin(in);
//                    }
//                }
//                if(!existeAsistencia(in)){
//                    if(in.getDia().getMes().getAno().getAgenda().getTarea().getEstado()==true && in.getDia().getMes().getAno().getAgenda().getPersonal().getEstado()==true){
//                        if(in.getEstadoInicio()!=null && in.getEstadoFin()!=null){
//                            System.out.println("existe obligación de inicio y fin(clase)");
//                            if(!ExisteTareaContinua(in)){    //no existe tarea continua
//                                if(in.getEstadoInicio()==true && in.getEstadoFin()==true){
//                                    System.out.println("estadoinicio=true y estadofin=true");
//                                    Asistencia asis= new Asistencia();
//                                    asis.setIniciofin(in);
//                                    asis.setEstado(true);
//                                    asis.setTardanza(false);                               
//                                    asis.guardarAsistencia(asis);
//                                }else if(in.getEstadoInicio()==true && in.getEstadoFin()==false){
//                                    System.out.println("ausencia momentanea de uno de los estados");
//                                    cel.setTime(in.getFin());
//                                    cel.add(Calendar.MINUTE, 30);
//                                    String s=new SimpleDateFormat("HH:mm").format(cal);
//                                    SimpleDateFormat formateador=new SimpleDateFormat("HH:mm");
//                                    Date a=formateador.parse(s);
//                                    if(cel.getTime().compareTo(a)<0){
//                                        Asistencia asis= new Asistencia();
//                                        asis.setIniciofin(in);
//                                        asis.setEstado(true);
//                                        asis.setTardanza(true);
//                                        if(!this.existeAsistencia(in)){
//                                        asis.guardarAsistencia(asis);
//                                        }
//                                        System.out.println("tardanza del personal");
//                                    }
//                                }else if(in.getEstadoInicio()==false && in.getEstadoFin()==true){
//                                    System.out.println("ausencia momentanea de uno de los estados");
//                                    cel.setTime(in.getFin());
//                                    cel.add(Calendar.MINUTE, 30);
//                                    String s=new SimpleDateFormat("HH:mm").format(cal);
//                                    SimpleDateFormat formateador=new SimpleDateFormat("HH:mm");
//                                    Date a=formateador.parse(s);
//                                    if(cel.getTime().compareTo(a)<0){
//                                        Asistencia asis= new Asistencia();
//                                        asis.setIniciofin(in);
//                                        asis.setEstado(true);
//                                        asis.setTardanza(true);
//                                        if(!this.existeAsistencia(in)){
//                                        asis.guardarAsistencia(asis);
//                                        }
//                                        System.out.println("tardanza del personal");
//                                    }
//                                }else if(in.getEstadoInicio()==false && in.getEstadoFin()==false){
//                                    System.out.println("ausencia momentanea de uno de los estados");
//                                    cel.setTime(in.getFin());
//                                    cel.add(Calendar.MINUTE, 30);
//                                    String s=new SimpleDateFormat("HH:mm").format(cal);
//                                    SimpleDateFormat formateador=new SimpleDateFormat("HH:mm");
//                                    Date a=formateador.parse(s);
//                                    if(cel.getTime().compareTo(a)<0){
//                                        Asistencia asis= new Asistencia();
//                                        asis.setIniciofin(in);
//                                        asis.setEstado(false);
//                                        asis.setTardanza(false);
//                                        if(!this.existeAsistencia(in)){
//                                        asis.guardarAsistencia(asis);
//                                        }
//                                        System.out.println("ausencia del personal");
//                                    }
//                                }
//                            }else{ //existe tarea continua
//                                if(in.getEstadoInicio()==true && in.getEstadoFin()==true){
//                                    System.out.println("estadoinicio=true y estadofin=true");
//                                    Asistencia asis= new Asistencia();
//                                    asis.setIniciofin(in);
//                                    asis.setEstado(true);
//                                    asis.setTardanza(false);
//                                    if(!this.existeAsistencia(in)){
//                                        asis.guardarAsistencia(asis);
//                                    }
//                                }else if(in.getEstadoInicio()==false && in.getEstadoFin()==true){
//                                    System.out.println("estadoinicio=false y estadofin=true");
//                                    Asistencia asis= new Asistencia();
//                                    asis.setIniciofin(in);
//                                    asis.setEstado(true);
//                                    asis.setTardanza(false);
//                                    if(!this.existeAsistencia(in)){
//                                        asis.guardarAsistencia(asis);
//                                    }                            }
//                            }
//                        }else if(in.getEstadoInicio()!=null && in.getEstadoFin()==null){
//                            System.out.println("existe obligación de inicio pero no de fin(reunion, extracurricular u otro)");
//                            if(in.getEstadoInicio()==true){
//                                System.out.println("estadoinicio=true");
//                                Asistencia asis= new Asistencia();
//                                asis.setIniciofin(in);
//                                asis.setEstado(true);
//                                asis.setTardanza(false);
//                                if(!this.existeAsistencia(in)){
//                                    asis.guardarAsistencia(asis);
//                                }
//                            }else{
//                                Calendar cel3=Calendar.getInstance();
//                                cel3.setTime(in.getFin());
//                                cel3.add(Calendar.MINUTE, -10);
//                                String s=new SimpleDateFormat("HH:mm").format(cal);
//                                SimpleDateFormat formateador=new SimpleDateFormat("HH:mm");
//                                Date a=formateador.parse(s);
//                                if(cel3.getTime().compareTo(a)<0){
//                                    Asistencia asis= new Asistencia();
//                                    asis.setIniciofin(in);
//                                    asis.setEstado(false);
//                                    asis.setTardanza(false);
//                                    if(!this.existeAsistencia(in)){
//                                        asis.guardarAsistencia(asis);
//                                    }
//                                    System.out.println("ausencia del personal");
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }catch (Exception e){System.out.println(e.toString());}
//    }
    
    public void ObtenerListadeldia(Calendar hoy){
        try{
            Calendar aux=Calendar.getInstance();
            Iterator<Iniciofin> it= ControladorTarea.getPERSISTENCIA().ObtenerInicioFin(hoy.getTime().getDate(), hoy.getTime().getMonth(),hoy.getTime().getYear()+1900).iterator();
            while(it.hasNext()){
                Iniciofin in= it.next();
                //Agrego 30 minutos mas a la hora de fin
                aux.setTime(in.getFin());
                aux.add(Calendar.MINUTE, 45);
                Date fi = new Date();
                fi.setHours(aux.getTime().getHours());
                fi.setMinutes(aux.getTime().getMinutes());
                fi.setSeconds(aux.getTime().getSeconds());
                //Obtengo el personal de ese horario
                Personal per=in.getDia().getMes().getAno().getAgenda().getPersonal();
                Calendar cel=Calendar.getInstance();
                if (!ControladorLicencia(cel.getTime(), per)) {
                //Comparo que ese horario sea menor a la hora actual
                    if(!VerificarRegistroIngreso(per,cel)){
                        if(fi.compareTo(hoy.getTime())<=0){
                            if(!existeAsistencia(in)){
                                Asistencia asis= new Asistencia();
                                asis.setIniciofin(in);
                                asis.setEstado(false);
                                asis.setTardanza(false);
                                asis.guardarAsistencia(asis);
                            }
                        }
                    }
                }
            }
        }catch (Exception e){System.out.println(e.toString());}
    }
   
    public boolean VerificarRegistroIngreso(Personal per, Calendar cel) {
        java.sql.Date sqldate = new java.sql.Date(cel.getTime().getTime());
        Iterator itt = PERSISTENCIA.getRegistroaccesoss(sqldate, per.getIdPersonal()).iterator();
        boolean ban = false;
        while (itt.hasNext()) {
            Registroacceso reg = (Registroacceso) itt.next();
            if (reg.getInicio() != null && reg.getFin() == null) {
                ban = true;
            }
        }
        return ban;
    }
    
    
    public boolean isFeriado(Date fecha) {
        boolean band = false;
        Iterator it = PERSISTENCIA.getFeriados(fecha.getYear()).iterator();
        while (it.hasNext()) {
            try {
                Feriado fer = (Feriado) it.next();
                SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                Date fe=formateador.parse(formateador.format(fecha));
                if (fe.compareTo(fer.getDia()) == 0) {
                    band = true;
                }
            } catch (ParseException ex) {
                Logger.getLogger(ControladorTarea.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return band;
    }
    
    public boolean ControladorLicencia(Date fecha, Personal per) {
        boolean band = false;
        Iterator it = PERSISTENCIA.getLicencias(per.getIdPersonal()).iterator();
        while (it.hasNext()) {
            Licencia li = (Licencia) it.next();
            if (li.getInicio().compareTo(fecha) <= 0 && li.getFin().compareTo(fecha) >= 0) {
                band = true;
                break;
            } else if (li.getInicio().getDate() == fecha.getDate() && li.getInicio().getMonth() == fecha.getMonth() && li.getInicio().getYear() == fecha.getYear()) {
                band = true;
                break;
            } else if (li.getFin().getDate() == fecha.getDate() && li.getFin().getMonth() == fecha.getMonth() && li.getFin().getYear() == fecha.getYear()) {
                band = true;
                break;
            }
        }
        return band;
    }

    public void GuardarAsistencia(Calendar cel, Personal per) {
        try {
            Iterator<Iniciofin> it = ControladorTarea.getPERSISTENCIA().ObtenerInicioFinPer(cel.getTime().getDate(), cel.getTime().getMonth(), cel.getTime().getYear() + 1900, per.getIdPersonal()).iterator();
            while (it.hasNext()) {
                Iniciofin in = it.next();
                Date aux = cel.getTime();
                Tarea com = in.getDia().getMes().getAno().getAgenda().getTarea();
                if (com.getComentario().equals("CLASE")) {
                    if (!isFeriado(aux)) {
                        if (!ControladorLicencia(cel.getTime(), per)) {
                            if (!existeAsistencia(in)) {
                                if (in.getDia().getMes().getAno().getAgenda().getTarea().getEstado() == true && per.getEstado() == true) {
                                    java.sql.Date sqldate = new java.sql.Date(aux.getTime());
                                    Iterator itt = PERSISTENCIA.getRegistroaccesoss(sqldate, per.getIdPersonal()).iterator();
                                    while (itt.hasNext()) {
                                        Registroacceso reg = (Registroacceso) itt.next();
                                        if (reg.getFin() != null) {
                                            if (reg.getInicio().compareTo(in.getInicio()) <= 0 && reg.getFin().compareTo(in.getInicio()) <= 0) {
                                            } else if (reg.getInicio().compareTo(in.getFin()) >= 0 && reg.getFin().compareTo(in.getFin()) >= 0) {
                                            } else {
                                                cel.setTime(in.getInicio());
                                                cel.add(Calendar.MINUTE, 10);
                                                Date inicio = cel.getTime();
                                                cel.setTime(in.getFin());
                                                cel.add(Calendar.MINUTE, -10);
                                                Date fin = cel.getTime();
                                                if (reg.getInicio().compareTo(inicio) <= 0 && reg.getFin().compareTo(fin) >= 0) {
                                                    Asistencia asis = new Asistencia();
                                                    asis.setIniciofin(in);
                                                    asis.setEstado(true);
                                                    asis.setTardanza(false);
                                                    if (!this.existeAsistencia(in)) {
                                                        asis.guardarAsistencia(asis);
                                                    }
                                                } else if (reg.getInicio().compareTo(inicio) >= 0) {
                                                    if (!this.existeAsistencia(in)) {
                                                        Asistencia asis = new Asistencia();
                                                        asis.setIniciofin(in);
                                                        asis.setEstado(true);
                                                        asis.setTardanza(true);
                                                        asis.guardarAsistencia(asis);
                                                    }
                                                } else if (reg.getFin().compareTo(fin) <= 0) {
                                                    if (!this.existeAsistencia(in)) {
                                                        Asistencia asis = new Asistencia();
                                                        asis.setIniciofin(in);
                                                        asis.setEstado(true);
                                                        asis.setTardanza(true);
                                                        asis.guardarAsistencia(asis);
                                                    }
                                                }
                                            }
                                        }
                                    }

                                }
                            }
                        }
                    }
                } else if (com.getComentario().equals("REUNION")) {
                    if (!ControladorLicencia(cel.getTime(), per)) {
                        if (!existeAsistencia(in)) {
                            if (in.getDia().getMes().getAno().getAgenda().getTarea().getEstado() == true && per.getEstado() == true) {
                                java.sql.Date sqldate = new java.sql.Date(aux.getTime());
                                Iterator itt = PERSISTENCIA.getRegistroaccesoss(sqldate, per.getIdPersonal()).iterator();
                                while (itt.hasNext()) {
                                    Registroacceso reg = (Registroacceso) itt.next();
                                    if (reg.getFin() != null) {
                                        if (reg.getInicio().compareTo(in.getInicio()) <= 0 && reg.getFin().compareTo(in.getInicio()) <= 0) {
                                        } else if (reg.getInicio().compareTo(in.getFin()) >= 0 && reg.getFin().compareTo(in.getFin()) >= 0) {
                                        } else {
                                            cel.setTime(in.getInicio());
                                            cel.add(Calendar.MINUTE, 10);
                                            Date inicio = cel.getTime();
                                            cel.setTime(in.getFin());
                                            cel.add(Calendar.MINUTE, -30);
                                            Date fin = cel.getTime();
                                            if (reg.getInicio().compareTo(inicio) <= 0 && reg.getFin().compareTo(fin) >= 0) {
                                                Asistencia asis = new Asistencia();
                                                asis.setIniciofin(in);
                                                asis.setEstado(true);
                                                asis.setTardanza(false);
                                                if (!this.existeAsistencia(in)) {
                                                    asis.guardarAsistencia(asis);
                                                }
                                            } else if (reg.getInicio().compareTo(inicio) >= 0) {
                                                if (!this.existeAsistencia(in)) {
                                                    Asistencia asis = new Asistencia();
                                                    asis.setIniciofin(in);
                                                    asis.setEstado(true);
                                                    asis.setTardanza(true);
                                                    asis.guardarAsistencia(asis);
                                                }
                                            } else if (reg.getFin().compareTo(fin) <= 0) {
                                                if (!this.existeAsistencia(in)) {
                                                    Asistencia asis = new Asistencia();
                                                    asis.setIniciofin(in);
                                                    asis.setEstado(true);
                                                    asis.setTardanza(true);
                                                    asis.guardarAsistencia(asis);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else if (com.getComentario().equals("EXTRACURRICULAR")) {
                    if (!ControladorLicencia(cel.getTime(), per)) {
                        if (!existeAsistencia(in)) {
                            if (in.getDia().getMes().getAno().getAgenda().getTarea().getEstado() == true && per.getEstado() == true) {
                                java.sql.Date sqldate = new java.sql.Date(aux.getTime());
                                Iterator itt = PERSISTENCIA.getRegistroaccesoss(sqldate, per.getIdPersonal()).iterator();
                                while (itt.hasNext()) {
                                    Registroacceso reg = (Registroacceso) itt.next();
                                    if (reg.getFin() != null) {
                                        if (reg.getInicio().compareTo(in.getInicio()) <= 0 && reg.getFin().compareTo(in.getInicio()) <= 0) {
                                        } else if (reg.getInicio().compareTo(in.getFin()) >= 0 && reg.getFin().compareTo(in.getFin()) >= 0) {
                                        } else {
                                            cel.setTime(in.getInicio());
                                            cel.add(Calendar.MINUTE, 10);
                                            Date inicio = cel.getTime();
                                            if (reg.getInicio().compareTo(inicio) <= 0) {
                                                Asistencia asis = new Asistencia();
                                                asis.setIniciofin(in);
                                                asis.setEstado(true);
                                                asis.setTardanza(false);
                                                if (!this.existeAsistencia(in)) {
                                                    asis.guardarAsistencia(asis);
                                                }
                                            } else if (reg.getInicio().compareTo(inicio) >= 0) {
                                                if (!this.existeAsistencia(in)) {
                                                    Asistencia asis = new Asistencia();
                                                    asis.setIniciofin(in);
                                                    asis.setEstado(true);
                                                    asis.setTardanza(true);
                                                    asis.guardarAsistencia(asis);
                                                }
                                            } 
                                        }
                                    }
                                }
                            }
                        }
                    }
                }else if (com.getComentario().equals("OTRO")) {
                    if (!ControladorLicencia(cel.getTime(), per)) {
                        if (!existeAsistencia(in)) {
                            if (in.getDia().getMes().getAno().getAgenda().getTarea().getEstado() == true && per.getEstado() == true) {
                                java.sql.Date sqldate = new java.sql.Date(aux.getTime());
                                Iterator itt = PERSISTENCIA.getRegistroaccesoss(sqldate, per.getIdPersonal()).iterator();
                                while (itt.hasNext()) {
                                    Registroacceso reg = (Registroacceso) itt.next();
                                    if (reg.getFin() != null) {
                                        if (reg.getInicio().compareTo(in.getInicio()) <= 0 && reg.getFin().compareTo(in.getInicio()) <= 0) {
                                        } else if (reg.getInicio().compareTo(in.getFin()) >= 0 && reg.getFin().compareTo(in.getFin()) >= 0) {
                                        } else {
                                            cel.setTime(in.getInicio());
                                            cel.add(Calendar.MINUTE, 10);
                                            Date inicio = cel.getTime();
                                            cel.setTime(in.getFin());
                                            cel.add(Calendar.MINUTE, -30);
                                            Date fin = cel.getTime();
                                            if (reg.getInicio().compareTo(inicio) <= 0 && reg.getFin().compareTo(fin) >= 0) {
                                                Asistencia asis = new Asistencia();
                                                asis.setIniciofin(in);
                                                asis.setEstado(true);
                                                asis.setTardanza(false);
                                                if (!this.existeAsistencia(in)) {
                                                    asis.guardarAsistencia(asis);
                                                }
                                            } else if (reg.getInicio().compareTo(inicio) >= 0) {
                                                if (!this.existeAsistencia(in)) {
                                                    Asistencia asis = new Asistencia();
                                                    asis.setIniciofin(in);
                                                    asis.setEstado(true);
                                                    asis.setTardanza(true);
                                                    asis.guardarAsistencia(asis);
                                                }
                                            } else if (reg.getFin().compareTo(fin) <= 0) {
                                                if (!this.existeAsistencia(in)) {
                                                    Asistencia asis = new Asistencia();
                                                    asis.setIniciofin(in);
                                                    asis.setEstado(true);
                                                    asis.setTardanza(true);
                                                    asis.guardarAsistencia(asis);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
}
