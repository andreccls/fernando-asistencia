/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Clases.Asistencia;
import Clases.Establecimiento;
import Clases.Iniciofin;
import Clases.Nivel;
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

    public List getEstablecimientos() throws ArrayStoreException {
        String hql = "from Establecimiento";
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

    public List getFeriados() throws ArrayStoreException {
        String hql = "from Feriado";
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
    
    public List getPersonalesTrue(int i) throws ArrayStoreException {
        String hql = "from Personal where estado="+i;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;

    }
    
    public List getTareasTrue(int i) throws ArrayStoreException {
        String hql = "from Tarea where estado="+i;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;

    }
    
    public List getTareasClasesTrue(int i) throws ArrayStoreException {
        String hql = "from Tarea as tar,Tareaclase as tarcla where tar.idTarea=tarcla.tarea.idTarea and estado="+i;
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

    public List existePersonal(int tipodoc, String nrodoc) throws ArrayStoreException {
        String hql = "from Personal where id_tipodoc =" + tipodoc + " and dni='" + nrodoc + "'";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;

    }

    public List ObtenerInicioFin(int dia, int mes, int ano) throws ArrayStoreException {
        String hql = "select id_iniciofin,inicio,estado_inicio,fin,estado_fin,iniciofin.id_dia from iniciofin,dia,mes,ano where iniciofin.id_dia=dia.id_dia and dia.dia=" + dia + " and dia.id_mes=mes.id_mes and mes.mes=" + mes + " and mes.id_ano=ano.id_ano and ano.ano=" + ano;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createSQLQuery(hql).addEntity(Iniciofin.class);
        items = q.list();
        tx.commit();
        return items;
    }

    public List ObtenerListaInasistencia(int mes, int ano) throws ArrayStoreException {
        String hql = "select id_asistencia,estado,tardanza,asistencia.id_iniciofin from asistencia,iniciofin,dia,mes,ano where asistencia.id_iniciofin=iniciofin.id_iniciofin and iniciofin.id_dia=dia.id_dia and dia.id_mes=mes.id_mes and mes.mes=" + mes + " and mes.id_ano=ano.id_ano and ano.ano=" + ano + " and asistencia.estado=0";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createSQLQuery(hql).addEntity(Asistencia.class);
        items = q.list();
        tx.commit();
        return items;
    }

    public List ObtenerListaTardanza(int mes, int ano) throws ArrayStoreException {
        String hql = "select id_asistencia,estado,tardanza,asistencia.id_iniciofin from asistencia,iniciofin,dia,mes,ano where asistencia.id_iniciofin=iniciofin.id_iniciofin and iniciofin.id_dia=dia.id_dia and dia.id_mes=mes.id_mes and mes.mes=" + mes + " and mes.id_ano=ano.id_ano and ano.ano=" + ano + " and asistencia.estado=1 and asistencia.tardanza=1";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createSQLQuery(hql).addEntity(Asistencia.class);
        items = q.list();
        tx.commit();
        return items;
    }

    public List DecjuradaPer(int idper) throws ArrayStoreException {
        String hql = "from Declaracionjurada where id_personal=" + idper;
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

    public List getDecJuradaPersonal(int id) throws ArrayStoreException {
        String hql = "from Declaracionjurada where id_personal=" + id;
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

    public List getDepartamentos() throws ArrayStoreException {
        String hql = "from Departamento";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
    }
}
