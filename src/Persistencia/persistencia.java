/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Clases.Asistencia;
import Clases.Establecimiento;
import Clases.Iniciofin;
import Clases.Nivel;
import Clases.Personal;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import javax.transaction.*;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.hibernate.EntityMode;

/**
 *
 * @author fer
 */
public class persistencia {

    private SessionFactory sessionFactory;
    private Session session;

    public persistencia() {
        sessionFactory = new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
        session = sessionFactory.openSession();
    }

    public Connection getConnection() {
        return session.connection();
    }
    
    public void insert(Object unObjeto) {
        Transaction tr = session.beginTransaction();
        session.save(unObjeto);
        //session.getCacheMode();
        tr.commit();
    }

    public void insertstring(Object unObjeto) {
        Transaction tr = session.beginTransaction();
        session.save(unObjeto);
        tr.commit();
    }

    public void update(Object unObjeto) {
        Transaction tr = session.beginTransaction();
        session.merge(unObjeto);
        tr.commit();
    }
    
    public void update2(Object unObjeto) {
        Transaction tr = session.beginTransaction();
        session.update(unObjeto);
        tr.commit();
    }

    public void saveupdate(Object unObjeto) {
        Transaction tr = session.beginTransaction();
        session.saveOrUpdate(unObjeto);
        tr.commit();
    }

    public void delete(Object unObjeto) {
        Transaction tr = session.beginTransaction();
        session.delete(unObjeto);
        tr.commit();
    }

    //Obtener lista de objetos
    public List getTipodocs() throws ArrayStoreException {
        String hql = "from Tipodoc";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getActividades() throws ArrayStoreException {
        String hql = "from Actividad";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;

    }

    public List getActivos(int nivel) throws ArrayStoreException {
        String hql = "from Activo where id_nivel=" + nivel;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;

    }
    
//    public List getMaterias(int idcurso) throws ArrayStoreException {
//        String hql = "from Tarea where curso=" + idcurso;
//        List items = new ArrayList();
//        Transaction tx = session.beginTransaction();
//        Query q = session.createQuery(hql);
//        items = q.list();
//        tx.commit();
//        return items;
//    }
    
    public List getActivoiniciofin(int idact) throws ArrayStoreException {
        String hql = "from ActivoIniciofin where id_activo=" + idact;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;

    }

    public List getEstablecimientos() throws ArrayStoreException {
        String hql = "from Establecimiento";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;

    }
    
    public List getAuditoria() throws ArrayStoreException {
        String hql = "from Auditoria";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }

    public List getAuditoriaPerTodos(int idper) throws ArrayStoreException {
        String hql = "from Auditoria where personalByIdPersonal="+idper;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getAuditoriaPerAno(int ano) throws ArrayStoreException {
        String hql = "from Auditoria where personalByIdPersonal!=null and year(fecha)="+ano;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getAuditoriaPerMes(int ano,int mes) throws ArrayStoreException {
        String hql = "from Auditoria where personalByIdPersonal!=null and year(fecha)="+ano+" and month(fecha)="+mes;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getAuditoriaPerDia(int ano,int mes,int dia) throws ArrayStoreException {
        String hql = "from Auditoria where personalByIdPersonal!=null and year(fecha)="+ano+" and month(fecha)="+mes+" and day(fecha)="+dia;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getTareaclase(int idaula) throws ArrayStoreException {
        String hql = "from Tareaclase where id_aula="+idaula;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getJustificacion(int idasistencia) throws ArrayStoreException {
        String hql = "from Justificacion where asistencia="+idasistencia;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getAuditoriaTareaTodos(int idtar) throws ArrayStoreException {
        String hql = "from Auditoria where tarea="+idtar;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    public List getAuditoriaTarAno(int ano) throws ArrayStoreException {
        String hql = "from Auditoria where tarea!=null and year(fecha)="+ano;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getAuditoriaTarMes(int ano,int mes) throws ArrayStoreException {
        String hql = "from Auditoria where tarea!=null and year(fecha)="+ano+" and month(fecha)="+mes;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getAuditoriaTarDia(int ano,int mes,int dia) throws ArrayStoreException {
        String hql = "from Auditoria where tarea!=null and year(fecha)="+ano+" and month(fecha)="+mes+" and day(fecha)="+dia;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getAuditoriaTareaActivos(int idtar) throws ArrayStoreException {
        String hql = "from Auditoria where tarea="+idtar+" and operacion='Insertar'";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getAuditoriaTareaInactivos(int idtar) throws ArrayStoreException {
        String hql = "from Auditoria where tarea="+idtar+" and operacion='Eliminar'";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getAuditoria(Date fecha) throws ArrayStoreException {
        String hql = "from Auditoria where day(fecha)='"+fecha.getDate()+"' and month(fecha)='"+(fecha.getMonth()+1)+"' and year(fecha)='"+(fecha.getYear()+1900)+"'";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getIniciofin(int dia, int mes, int ano) throws ArrayStoreException {
        String hql = "from Iniciofin as ini where ini.dia.dia="+dia+" and ini.dia.mes.mes="+mes+" and ini.dia.mes.ano.ano="+ano+" and ini.dia.mes.ano.agenda.personal.estado=1 and ini.dia.mes.ano.agenda.tarea.estado=1";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getIniciofin(int iddia) throws ArrayStoreException {
        String hql = "from Iniciofin as ini where dia="+iddia+" and ini.dia.mes.ano.agenda.personal.estado=1 and ini.dia.mes.ano.agenda.tarea.estado=1";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getIniciofinPer(int dia, int mes, int ano,int idper) throws ArrayStoreException {
        String hql = "from Iniciofin as ini where ini.dia.dia="+dia+" and ini.dia.mes.mes="+mes+" and ini.dia.mes.ano.ano="+ano+" and ini.dia.mes.ano.agenda.personal.idPersonal="+idper;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getOutHomeInactivoPer(Date fecha, int idper) throws ArrayStoreException {
        String hql = "from Inactivo as ini where ini.fechaInicio<'"+fecha+"' and ini.fechaFin>'"+fecha+"' and ini.nivel.cargo.detalleEstablecimiento.declaracionjurada.personal.idPersonal="+idper;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getOutHomeInactivos(Date fecha) throws ArrayStoreException {
        String hql = "from Inactivo as ini where ini.fechaInicio<'"+fecha+"' and ini.fechaFin>'"+fecha+"'";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getOutHomeActivoPer(String dia, int idper) throws ArrayStoreException {
        String hql = "from ActivoIniciofin as ini where ini.activo.dia='"+dia+"' and ini.activo.nivel.cargo.detalleEstablecimiento.declaracionjurada.personal.idPersonal="+idper;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getOutHomeActivos(String dia) throws ArrayStoreException {
        String hql = "from ActivoIniciofin as ini where ini.activo.dia='"+dia+"'";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getIniciofinTar(int dia, int mes, int ano,int idtar) throws ArrayStoreException {
        String hql = "from Iniciofin as ini where ini.dia.dia="+dia+" and ini.dia.mes.mes="+mes+" and ini.dia.mes.ano.ano="+ano+" and ini.dia.mes.ano.agenda.tarea.idTarea="+idtar;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getIniciofinTarea(int idtar) throws ArrayStoreException {
        String hql = "from Iniciofin as ini where ini.dia.mes.ano.agenda.tarea.idTarea="+idtar;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getAnos(int idper, int idtar) throws ArrayStoreException {
        String hql = "from Ano where id_personal="+idper+" and id_tarea="+idtar;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getAnos(int ano) throws ArrayStoreException {
        String hql = "from Ano where ano="+ano;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getAnoLectivo(int ano) throws ArrayStoreException {
        String hql = "from Anolectivo where ano="+ano;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getMeses(int ano,int mes) throws ArrayStoreException {
        String hql = "select me from Mes as me, Ano as an where me.mes="+mes+" and me.ano.idAno=an.idAno and an.ano="+ano;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getMeses(int idano) throws ArrayStoreException {
        String hql = "from Mes where id_ano="+idano;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getMes(int mes) throws ArrayStoreException {
        String hql = "from Mes where mes="+mes;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getCirculares() throws ArrayStoreException {
        String hql = "from Circular";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getCircularPersonales(int idcir) throws ArrayStoreException {
        String hql = "from Circularpersonal where id_circular="+idcir;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getDias(int idmes) throws ArrayStoreException {
        String hql = "from Dia where id_mes="+idmes;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getDias(int ano, int mes, int dia) throws ArrayStoreException {
        String hql = "select di from Dia as di, Mes as me, Ano as an where di.dia="+dia+" and di.mes.idMes=me.idMes and me.mes="+mes+" and me.ano.idAno=an.idAno and an.ano="+ano;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
//    public List getDiass(int idmes) throws ArrayStoreException {
//        String hql = "select d.idDia as idDia,d.dia as dia,d.mes  from Dia as d,Mes as m where d.mes.idMes=m.idMes and m.mes="+idmes;
//        List items = new ArrayList();
//        Transaction tx = session.beginTransaction();
//        Query q = session.createQuery(hql);
//        items = q.list();
//        tx.commit();
//        return items;
//    }
    
    public List getRegistroaccesos(int idper) throws ArrayStoreException {
        String hql = "from Registroacceso where id_personal="+idper;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getRegistroaccesoss(Date fecha, int idper) throws ArrayStoreException {
        String hql = "from Registroacceso where fecha='"+fecha+"' and id_personal="+idper;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }

    public List getTareas() throws ArrayStoreException {
        String hql = "from Tarea";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
//    public List getTareas(int ano) throws ArrayStoreException {
//        String hql = "from Tarea";
//        List items = new ArrayList();
//        Transaction tx = session.beginTransaction();
//        Query q = session.createQuery(hql);
//        items = q.list();
//        tx.commit();
//        return items;
//    }
    
    public List getTarea(int idtar) throws ArrayStoreException {
        String hql = "from Tarea where id_tarea="+idtar;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getTareas(int iddiv) throws ArrayStoreException {
        String hql = "from Tarea where division="+iddiv;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getTareasReuniones() throws ArrayStoreException {
        String hql = "from Tareareunion";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getTareasExtracurriculares() throws ArrayStoreException {
        String hql = "from Tareaextracurricular";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getPerfiles() throws ArrayStoreException {
        String hql = "from Perfil";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getTareasClases() throws ArrayStoreException {
        String hql = "from Tareaclase";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getTareasOtros() throws ArrayStoreException {
        String hql = "from Tareaotro";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getTareasAdm() throws ArrayStoreException {
        String hql = "from Tarea where comentario='ADMINISTRATIVO'";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getTareasMaes() throws ArrayStoreException {
        String hql = "from Tarea where comentario='MAESTRANZA'";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getTareasOtro() throws ArrayStoreException {
        String hql = "from Tarea where comentario='OTROS'";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }

    public List getFeriados(int ano) throws ArrayStoreException {
        String hql = "from Feriado where year(dia)="+ano;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getLicencias(int idper) throws ArrayStoreException {
        String hql = "from Licencia as li where li.personal.idPersonal="+idper;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }

    public List getPersonales() throws ArrayStoreException {
        String hql = "from Personal";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;

    }
    
    public List getFamiliaresPersonal(int idper) throws ArrayStoreException {
        String hql = "from PersonalFamiliar where personalByIdPersonal.idPersonal="+idper;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;

    }
    
    public List getPersonal(String dni) throws ArrayStoreException {
        String hql = "from Personal where dni='"+dni+"' and estado=1";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getPersonal(int idper) throws ArrayStoreException {
        String hql = "from Personal where idPersonal="+idper;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getPersonalesTrue(int i) throws ArrayStoreException {
        String hql = "from Personal where estado="+i;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;

    }
    

    
//    public List getTareasTrue(int i) throws ArrayStoreException {
//        String hql = "from Tarea where estado="+i;
//        List items = new ArrayList();
//        Transaction tx = session.beginTransaction();
//        Query q = session.createQuery(hql);
//        items = q.list();
//        tx.commit();
//        return items;
//
//    }
    
    public List getTareasClasesTrue(int i) throws ArrayStoreException {
        String hql = "select tar.nombre as nombre, tar.lugar as lugar, tarcla.aula as aula, tarcla.numero as numero from Tarea tar inner join tar.tareaclases tarcla where tar.estado="+i;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getTareasReunionTrue(int i) throws ArrayStoreException {
        String hql = "from Tarea as tar,Tareareunion as tarreu where tar.idTarea=tarreu.tarea.idTarea and estado="+i;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getTareasExtracurricularTrue(int i) throws ArrayStoreException {
        String hql = "from Tarea as tar,Tareaextracurricular as tarext where tar.idTarea=tarext.tarea.idTarea and estado="+i;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getTareasOtroTrue(int i) throws ArrayStoreException {
        String hql = "from Tarea as tar,Tareaotro as tarot where tar.idTarea=tarot.tarea.idTarea and estado="+i;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getAsistenciasReporte() throws ArrayStoreException {
        String hql = "select per.apellido as apellido, per.nombre as nombre, tar.nombre as tareanombre, ann.ano as anio, me.mes as mes, di.dia as dia, hour(ini.inicio) as iniciohr,minute(ini.inicio) as iniciomin, hour(ini.fin) as finhr,minute(ini.fin) as finmin, asi.estado as estado, asi.tardanza as tardanza, just.motivo as motiv, art.nombre as nombreart, art.nroArticulo as nroart from Personal as per, Tarea as tar, Agenda as age, Ano as ann, Mes as me, Dia as di, Iniciofin as ini, Asistencia as asi, Justificacion as just, Articulo as art where per.idPersonal=age.personal.idPersonal and tar.idTarea=age.tarea.idTarea and age.tarea.idTarea=ann.agenda.tarea.idTarea and age.personal.idPersonal=ann.agenda.personal.idPersonal and me.ano.idAno=ann.idAno and me.idMes=di.mes.idMes and di.idDia = ini.dia.idDia and asi.iniciofin.idIniciofin=ini.idIniciofin and asi.estado=1 and asi.tardanza=0 and asi.idAsistencia=just.asistencia.idAsistencia and just.articulo.idArticulo=art.idArticulo";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getReporteDia(int dia, int mes) throws ArrayStoreException {
        String hql = "select per.apellido as apellido, per.nombre as nombre, tar.nombre as tareanombre, ann.ano as anio, me.mes as mes, di.dia as dia, hour(ini.inicio) as iniciohr,minute(ini.inicio) as iniciomin, hour(ini.fin) as finhr,minute(ini.fin) as finmin, asi.estado as estado, asi.tardanza as tardanza, just.motivo as motiv, art.nombre as nombreart, art.nroArticulo as nroart from Personal as per, Tarea as tar, Agenda as age, Ano as ann, Mes as me, Dia as di, Iniciofin as ini, Asistencia as asi, Justificacion as just, Articulo as art where per.idPersonal=age.personal.idPersonal and tar.idTarea=age.tarea.idTarea and age.tarea.idTarea=ann.agenda.tarea.idTarea and age.personal.idPersonal=ann.agenda.personal.idPersonal and me.ano.idAno=ann.idAno and me.idMes=di.mes.idMes and di.idDia = ini.dia.idDia and asi.iniciofin.idIniciofin=ini.idIniciofin and asi.estado=1 and asi.tardanza=0 and asi.idAsistencia=just.asistencia.idAsistencia and just.articulo.idArticulo=art.idArticulo";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getReporteSemana(int dia, int mes) throws ArrayStoreException {
        String hql = "select per.apellido as apellido, per.nombre as nombre, tar.nombre as tareanombre, ann.ano as anio, me.mes as mes, di.dia as dia, hour(ini.inicio) as iniciohr,minute(ini.inicio) as iniciomin, hour(ini.fin) as finhr,minute(ini.fin) as finmin, asi.estado as estado, asi.tardanza as tardanza, just.motivo as motiv, art.nombre as nombreart, art.nroArticulo as nroart from Personal as per, Tarea as tar, Agenda as age, Ano as ann, Mes as me, Dia as di, Iniciofin as ini, Asistencia as asi, Justificacion as just, Articulo as art where per.idPersonal=age.personal.idPersonal and tar.idTarea=age.tarea.idTarea and age.tarea.idTarea=ann.agenda.tarea.idTarea and age.personal.idPersonal=ann.agenda.personal.idPersonal and me.ano.idAno=ann.idAno and me.idMes=di.mes.idMes and di.idDia = ini.dia.idDia and asi.iniciofin.idIniciofin=ini.idIniciofin and asi.estado=1 and asi.tardanza=0 and asi.idAsistencia=just.asistencia.idAsistencia and just.articulo.idArticulo=art.idArticulo";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getReporteMes(int mes) throws ArrayStoreException {
        String hql = "select per.apellido as apellido, per.nombre as nombre, tar.nombre as tareanombre, ann.ano as anio, me.mes as mes, di.dia as dia, hour(ini.inicio) as iniciohr,minute(ini.inicio) as iniciomin, hour(ini.fin) as finhr,minute(ini.fin) as finmin, asi.estado as estado, asi.tardanza as tardanza, just.motivo as motiv, art.nombre as nombreart, art.nroArticulo as nroart from Personal as per, Tarea as tar, Agenda as age, Ano as ann, Mes as me, Dia as di, Iniciofin as ini, Asistencia as asi, Justificacion as just, Articulo as art where per.idPersonal=age.personal.idPersonal and tar.idTarea=age.tarea.idTarea and age.tarea.idTarea=ann.agenda.tarea.idTarea and age.personal.idPersonal=ann.agenda.personal.idPersonal and me.ano.idAno=ann.idAno and me.idMes=di.mes.idMes and di.idDia = ini.dia.idDia and asi.iniciofin.idIniciofin=ini.idIniciofin and asi.estado=1 and asi.tardanza=0 and asi.idAsistencia=just.asistencia.idAsistencia and just.articulo.idArticulo=art.idArticulo";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getInasistenciasReporte() throws ArrayStoreException {
        String hql = "select per.apellido as apellido, per.nombre as nombre, tar.nombre as tareanombre, ann.ano as anio, me.mes as mes, di.dia as dia, hour(ini.inicio) as iniciohr,minute(ini.inicio) as iniciomin, hour(ini.fin) as finhr,minute(ini.fin) as finmin, asi.estado as estado, asi.tardanza as tardanza, just.motivo as motiv, art.nombre as nombreart, art.nroArticulo as nroart from Personal as per, Tarea as tar, Agenda as age, Ano as ann, Mes as me, Dia as di, Iniciofin as ini, Asistencia as asi, Justificacion as just, Articulo as art where per.idPersonal=age.personal.idPersonal and tar.idTarea=age.tarea.idTarea and age.tarea.idTarea=ann.agenda.tarea.idTarea and age.personal.idPersonal=ann.agenda.personal.idPersonal and me.ano.idAno=ann.idAno and me.idMes=di.mes.idMes and di.idDia = ini.dia.idDia and asi.iniciofin.idIniciofin=ini.idIniciofin and asi.estado=0 and asi.tardanza=0 and asi.idAsistencia=just.asistencia.idAsistencia and just.articulo.idArticulo=art.idArticulo";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getTardanzasReporte() throws ArrayStoreException {
        String hql = "select per.apellido as apellido, per.nombre as nombre, tar.nombre as tareanombre, ann.ano as anio, me.mes as mes, di.dia as dia, hour(ini.inicio) as iniciohr,minute(ini.inicio) as iniciomin, hour(ini.fin) as finhr,minute(ini.fin) as finmin, asi.estado as estado, asi.tardanza as tardanza, just.motivo as motiv, art.nombre as nombreart, art.nroArticulo as nroart from Personal as per, Tarea as tar, Agenda as age, Ano as ann, Mes as me, Dia as di, Iniciofin as ini, Asistencia as asi, Justificacion as just, Articulo as art where per.idPersonal=age.personal.idPersonal and tar.idTarea=age.tarea.idTarea and age.tarea.idTarea=ann.agenda.tarea.idTarea and age.personal.idPersonal=ann.agenda.personal.idPersonal and me.ano.idAno=ann.idAno and me.idMes=di.mes.idMes and di.idDia = ini.dia.idDia and asi.iniciofin.idIniciofin=ini.idIniciofin and asi.estado=1 and asi.tardanza=1 and asi.idAsistencia=just.asistencia.idAsistencia and just.articulo.idArticulo=art.idArticulo";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }

    public List existePersonal(int tipodoc, String nrodoc) throws ArrayStoreException {
        String hql = "from Personal where tipodoc.idTipodoc =" + tipodoc + " and dni='" + nrodoc + "'";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;

    }

//    public List ObtenerInicioFin(int dia, int mes, int ano) throws ArrayStoreException {
//        String hql = "select id_iniciofin,inicio,estado_inicio,fin,estado_fin,iniciofin.id_dia from iniciofin,dia,mes,ano where iniciofin.id_dia=dia.id_dia and dia.dia=" + dia + " and dia.id_mes=mes.id_mes and mes.mes=" + mes + " and mes.id_ano=ano.id_ano and ano.ano=" + ano;
//        List items = new ArrayList();
//        Transaction tx = session.beginTransaction();
//        Query q = session.createSQLQuery(hql).addEntity(Iniciofin.class);
//        items = q.list();
//        tx.commit();
//        return items;
//    }
    
    public List ObtenerInicioFinPer(int dia, int mes, int ano, int idper) throws ArrayStoreException {
        String hql = "from Iniciofin as ini where ini.dia.dia="+dia+" and ini.dia.mes.mes="+mes+" and ini.dia.mes.ano.ano="+ano+" and ini.dia.mes.ano.agenda.personal.idPersonal="+idper+" and ini.dia.mes.ano.agenda.tarea.estado=1";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }

    public List ObtenerInicioFin(int dia, int mes, int ano) throws ArrayStoreException {
        String hql = "from Iniciofin as ini where ini.dia.dia="+dia+" and ini.dia.mes.mes="+mes+" and ini.dia.mes.ano.ano="+ano;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List ObtenerListaInasistencia(int dia,int mes, int ano) throws ArrayStoreException {
        String hql = "from Asistencia as asi where asi.iniciofin.dia.dia="+dia+" and asi.iniciofin.dia.mes.mes="+mes+" and asi.iniciofin.dia.mes.ano.ano="+ano+" and asi.estado=0";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List ObtenerListaInasistencia(int mes, int ano) throws ArrayStoreException {
        String hql = "from Asistencia as asi where asi.iniciofin.dia.mes.mes="+mes+" and asi.iniciofin.dia.mes.ano.ano="+ano+" and asi.estado=0";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List ObtenerListaInasistencia(int ano) throws ArrayStoreException {
        String hql = "from Asistencia as asi where asi.iniciofin.dia.mes.ano.ano="+ano+" and asi.estado=0";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }

    public List ObtenerListaTardanza(int dia,int mes, int ano) throws ArrayStoreException {
        String hql = "from Asistencia as asi where asi.iniciofin.dia.dia="+dia+" and asi.iniciofin.dia.mes.mes="+mes+" and asi.iniciofin.dia.mes.ano.ano="+ano+" and asi.estado=1 and asi.tardanza=1";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List ObtenerListaTardanza(int mes, int ano) throws ArrayStoreException {
        String hql = "from Asistencia as asi where asi.iniciofin.dia.mes.mes="+mes+" and asi.iniciofin.dia.mes.ano.ano="+ano+" and asi.estado=1 and asi.tardanza=1";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List ObtenerListaTardanza(int ano) throws ArrayStoreException {
        String hql = "from Asistencia as asi where asi.iniciofin.dia.mes.ano.ano="+ano+" and asi.estado=1 and asi.tardanza=1";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List ObtenerListaAsistencia(int dia,int mes, int ano) throws ArrayStoreException {
        String hql = "from Asistencia as asi where asi.iniciofin.dia.dia="+dia+" and asi.iniciofin.dia.mes.mes="+mes+" and asi.iniciofin.dia.mes.ano.ano="+ano+" and asi.estado=1 and asi.tardanza=0";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List ObtenerListaAsistencia(int mes, int ano) throws ArrayStoreException {
        String hql = "from Asistencia as asi where asi.iniciofin.dia.mes.mes="+mes+" and asi.iniciofin.dia.mes.ano.ano="+ano+" and asi.estado=1 and asi.tardanza=0";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List ObtenerListaAsistencia(int ano) throws ArrayStoreException {
        String hql = "from Asistencia as asi where asi.iniciofin.dia.mes.ano.ano="+ano+" and asi.estado=1 and asi.tardanza=0";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }

    public List DecjuradaPer(int idper) throws ArrayStoreException {
        String hql = "from Declaracionjurada where personal=" + idper;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;

    }

    public List DetalledecjuradaPer(int decju) throws ArrayStoreException {
        String hql = "from DetalleEstablecimiento where id_declaracionjurada=" + decju;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;

    }

    public List NivelPerso(int idniv) throws ArrayStoreException {
        String hql = "from Nivel where id_nivel=" + idniv;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;

    }

    public List CargoPer(int detalle) throws ArrayStoreException {
        String hql = "from Cargo where id_detalleestablecimiento=" + detalle;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;

    }

    public List NivelPer(int cargo) throws ArrayStoreException {
        String hql = "from Nivel where id_cargo=" + cargo;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;

    }

    public List getTipocargos() throws ArrayStoreException {
        String hql = "from Tipocargo";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;

    }

    public List getArticulos() throws ArrayStoreException {
        String hql = "from Articulo";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;

    }

    public List getAsistencia(int nro) throws ArrayStoreException {
        String hql = "from Asistencia where id_asistencia=" + nro;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;

    }
    
    public List getRegistro(int idreg) throws ArrayStoreException {
        String hql = "from Registroacceso where idRegistroacceso=" + idreg;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;

    }

    public List getDecJuradaPersonal(int id) throws ArrayStoreException {
        String hql = "from Declaracionjurada where personal="+id;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;

    }

    public List getTiponiveles() throws ArrayStoreException {
        String hql = "from Tiponivel";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;

    }

    public List getRelaciones() throws ArrayStoreException {
        String hql = "from Tiporelacion";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;

    }

    public List getSitRevista() throws ArrayStoreException {
        String hql = "from Revista";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getLugar() throws ArrayStoreException {
        String hql = "from Lugar";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getAulas() throws ArrayStoreException {
        String hql = "from Aula";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getCursos(int ano) throws ArrayStoreException {
        String hql = "from Curso as cu where cu.anolectivo.ano="+ano;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getDivisiones(int idcurso) throws ArrayStoreException {
        String hql = "from Division where curso="+idcurso;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
//    public List getMaterias(int iddivision) throws ArrayStoreException {
//        String hql = "from Division where curso="+iddivision;
//        List items = new ArrayList();
//        Transaction tx = session.beginTransaction();
//        Query q = session.createQuery(hql);
//        items = q.list();
//        tx.commit();
//        return items;
//    }

    public List getFranco() throws ArrayStoreException {
        String hql = "from Franco";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }

    public List getAgendas() throws ArrayStoreException {
        String hql = "from Agenda";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getDiasA(int ano) throws ArrayStoreException {
        String hql = "from Dia as di where di.mes.ano.ano="+ano;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }

    public List getDiasM(int ano, int mes) throws ArrayStoreException {
        String hql = "from Dia as di where di.mes.ano.ano="+ano+" and di.mes.mes="+mes;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getDiasD(int ano, int mes, int dia) throws ArrayStoreException {
        String hql = "from Dia as di where di.mes.ano.ano="+ano+" and di.mes.mes="+mes+" and di.dia="+dia;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getAgendas(int idper) throws ArrayStoreException {
        String hql = "from Agenda as age where age.personal.idPersonal="+idper+"  and age.personal.estado=1 and age.tarea.estado=1";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getAgendasClases(int idper) throws ArrayStoreException {
        String hql = "from Agenda as age where age.personal.idPersonal="+idper+"  and age.personal.estado=1 and age.tarea.estado=1 and age.tarea.comentario='CLASE'";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getAgendasTar(int idtar) throws ArrayStoreException {
        String hql = "from Agenda as age where age.tarea.idTarea="+idtar+"  and age.personal.estado=1 and age.tarea.estado=1";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }

    public List getDepartamentos() throws ArrayStoreException {
        String hql = "from Departamento";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
    
    public List getPersonalDepto() throws ArrayStoreException {
        String hql = "from PersonalDepartamento";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
}
