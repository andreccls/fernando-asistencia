/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

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

    public void insert(Object unObjeto) {
        Transaction tr = session.beginTransaction();
        session.save(unObjeto);
        tr.commit();
    }

    public void update(Object unObjeto) {
        Transaction tr = session.beginTransaction();
        //session.saveOrUpdate(unObjeto);
        session.update(unObjeto);
        tr.commit();
    }

    //Obtener lista de objetos
    public List getColegios() throws ArrayStoreException {
        
        String hql = "from Colegios";
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery(hql);
        items = q.list();
        tx.commit();
        return items;
        
    }

    public List getPersonales() throws ArrayStoreException {
//        String hql = "from Personales";
//        List items = new ArrayList();
//        Transaction tx = session.beginTransaction();
//        Query q = session.createSQLQuery(hql);
//        items = q.list();
//        tx.commit();
//        return items;
        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        items = session.createSQLQuery("select * from Personales").list();
        tx.commit();
        return items;
    }

    public List getTareas() throws ArrayStoreException {

        List items = new ArrayList();
        Transaction tx = session.beginTransaction();
        items = session.createSQLQuery("select * from Tareas").list();
        tx.commit();
        return items;
    }
}
