/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Persistencia.persistencia;
import java.awt.Label;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author fer
 */
public class Controlador {

    public static persistencia PERSISTENCIA;

    public Controlador() {
        PERSISTENCIA = new persistencia();
    }

    public static persistencia getPERSISTENCIA() {
        return PERSISTENCIA;
    }

    public boolean existeColegio() {
        boolean col = false;
        if (Controlador.getPERSISTENCIA().getEstablecimientos().size() > 0) {
            col = true;
        }
        return col;
    }

    public Establecimiento getPrimerEstablecimiento() {
        Establecimiento col = (Establecimiento) Controlador.getPERSISTENCIA().getEstablecimientos().get(0);
        return col;
    }
    
    public Establecimiento getColegio(int idcol) {
        Establecimiento cole = new Establecimiento();
        Iterator it = Controlador.getPERSISTENCIA().getEstablecimientos().iterator();
        while (it.hasNext()) {
            Establecimiento co = (Establecimiento) it.next();
            if (co.getIdEstablecimiento().equals(idcol)) {
                cole = co;
                break;
            }
        }
        return cole;
    }

    public void crearEstablecimiento(String nombre, String calle, Integer altura, String piso, String depto, Set<Feriado> feriados, Set<Tarea> tareas, Set<Departamento> departamentos, Set<Personal> personals, Set<DetalleEstablecimiento> detalleEstablecimientos, Set<Declaracionjurada> declaracionjuradas) {
        if (!existeColegio()) {
            Establecimiento unEstablecimiento = new Establecimiento(nombre, calle, altura, piso, depto, feriados, tareas, departamentos, personals, detalleEstablecimientos, declaracionjuradas);
            unEstablecimiento.guardarEstablecimiento(unEstablecimiento);
        } else {
            JOptionPane.showMessageDialog(null, "Ya existe el Colegio");
            //throw new GTUException("Ya existe la Universidad  :"+getUniversidad()+",  puede crear solo una!" );
        }
    }

    public void CargarComboPersonaldoc(JComboBox JCombo) {
        try {
            //DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
            Establecimiento col = (Establecimiento) Controlador.getPERSISTENCIA().getEstablecimientos().get(0);
            Personal per = new Personal();
            Iterator rs = per.getPersonaldocentes().iterator();
            while (rs.hasNext()) {
                Personaldocente perdoc = (Personaldocente) rs.next();
                JCombo.addItem(perdoc);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    public void CargarComboPersonal(JComboBox JCombo) {
        try {
            Establecimiento col = getPrimerEstablecimiento();
            Iterator rs = col.getPersonals().iterator();
            while (rs.hasNext()) {
                Personal per = (Personal) rs.next();
                if(per.getEstado()==true){
                    JCombo.addItem(per);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    public void CargarComboSituacionRevista(JComboBox JCombo) {
        try {
            Iterator<Revista> rs = Controlador.getPERSISTENCIA().getSitRevista().iterator();
            while (rs.hasNext()) {
                Revista rev = (Revista) rs.next();
                JCombo.addItem(rev);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    public void CargarComboDepartamento(JComboBox JCombo) {
        try {
            //Establecimiento col = getPrimerEstablecimiento();
            Iterator rs = Controlador.getPERSISTENCIA().getDepartamentos().iterator();
            while (rs.hasNext()) {
                Departamento dep = (Departamento) rs.next();
                JCombo.addItem(dep);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    public void CargarComboRelacion(JComboBox JCombo) {
        try {
            Iterator rs = Controlador.getPERSISTENCIA().getRelaciones().iterator();
            while (rs.hasNext()) {
                Tiporelacion rel = (Tiporelacion) rs.next();
                JCombo.addItem(rel);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
    
    public void CargarComboArticulo(JComboBox JCombo) {
        try {
            Iterator rs = Controlador.getPERSISTENCIA().getArticulos().iterator();
            while (rs.hasNext()) {
                Articulo art = (Articulo) rs.next();
                JCombo.addItem(art);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    public void CargarComboTipodoc(JComboBox JCombo) {
        try {
            Iterator rs = Controlador.getPERSISTENCIA().getTipodocs().iterator();
            while (rs.hasNext()) {
                Tipodoc tipo = (Tipodoc) rs.next();
                JCombo.addItem(tipo);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    public void CargarComboActividad(JComboBox JCombo) {
        try {
            Iterator rs = Controlador.getPERSISTENCIA().getActividades().iterator();
            while (rs.hasNext()) {
                Actividad act = (Actividad) rs.next();
                JCombo.addItem(act);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    

    public void CargarComboEstablecimiento(JComboBox JCombo) {
        try {
            JCombo.removeAllItems();
            Iterator est = Controlador.getPERSISTENCIA().getEstablecimientos().iterator();
            while (est.hasNext()) {
                Establecimiento e = (Establecimiento) est.next();
                if (getPrimerEstablecimiento().getIdEstablecimiento() != e.getIdEstablecimiento()) {
                    JCombo.addItem(e);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    public void CargarComboEstablecimientoFiltro(JComboBox JCombo,String valor) {
        try {
            JCombo.removeAllItems();
            Iterator est = Controlador.getPERSISTENCIA().getEstablecimientos().iterator();
            while (est.hasNext()) {
                Establecimiento e = (Establecimiento) est.next();
                if (getPrimerEstablecimiento().getIdEstablecimiento() != e.getIdEstablecimiento()) {
                    int i = e.getNombre().indexOf(valor);
                    if(i==1){
                        JCombo.addItem(e);
                    }
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
    
    public void CargarComboTipocargo(JComboBox JCombo) {
        try {
            JCombo.removeAllItems();
            Iterator est = Controlador.getPERSISTENCIA().getTipocargos().iterator();
            while (est.hasNext()) {
                Tipocargo e = (Tipocargo) est.next();
                JCombo.addItem(e);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
    
    public void CargarComboTipocargoFiltro(JComboBox JCombo,String valor) {
        try {
            JCombo.removeAllItems();
            Iterator est = Controlador.getPERSISTENCIA().getTipocargos().iterator();
            while (est.hasNext()) {
                Tipocargo e = (Tipocargo) est.next();
                int i = e.getNombre().indexOf(valor);
                if(i==1){
                    JCombo.addItem(e);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    public void CargarComboTiponivel(JComboBox JCombo) {
        try {
            JCombo.removeAllItems();
            Iterator est = Controlador.getPERSISTENCIA().getTiponiveles().iterator();
            while (est.hasNext()) {
                Tiponivel e = (Tiponivel) est.next();
                JCombo.addItem(e);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    public void CargarComboTiponivelFiltro(JComboBox JCombo,String valor) {
        try {
            JCombo.removeAllItems();
            Iterator est = Controlador.getPERSISTENCIA().getTiponiveles().iterator();
            while (est.hasNext()) {
                Tiponivel e = (Tiponivel) est.next();
                int i = e.getNombre().indexOf(valor);
                if(i==1){
                    JCombo.addItem(e);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
    
    public void CargarComboCargoPersonal(JComboBox JCombo, DetalleEstablecimiento detalle) {
        try {
            JCombo.removeAllItems();
            Iterator<Cargo> cartip = detalle.getCargos().iterator();
            while (cartip.hasNext()) {
                Cargo tip = (Cargo) cartip.next();
                JCombo.addItem(tip);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    public void CargarComboNivelPersonal(JComboBox JCombo, Cargo cargo) {
        try {
            JCombo.removeAllItems();
            Iterator<Nivel> nivel = cargo.getNivels().iterator();
            while (nivel.hasNext()) {
                Nivel niv = (Nivel) nivel.next();
                JCombo.addItem(niv);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    public static void LimpiarCombo(JComboBox JCombo) {
        DefaultComboBoxModel mod = (DefaultComboBoxModel) JCombo.getModel();
        mod.removeAllElements();
        //        DefaultTableModel modelo = (DefaultTableModel) Tabla.getModel();
//        while(modelo.getRowCount()>0)
//            modelo.removeRow(0);
    }

    public static void LimpiarTabla(JTable Tabla) {
        DefaultTableModel modelo = (DefaultTableModel) Tabla.getModel();
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
    }

    public void CargarGrillaActividades(JTable tabla, Personal per, Date di) {
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        Iterator<Agenda> ag = per.getAgendas().iterator();
        while (ag.hasNext()) {
            Agenda agen = (Agenda) ag.next();
                Iterator<Iniciofin> it = agen.getDia2(di).getIniciofins().iterator();
                while (it.hasNext()) {
                    Iniciofin ini = (Iniciofin) it.next();
                    Object[] fila = new Object[3];
                    fila[0] = agen.getTarea().getNombre();
                    fila[1] = ini.getInicio();
                    fila[2] = ini.getFin();
                    model.addRow(fila);
                }
        }
        tabla.setModel(model);
    }

    public void CargarTablaActivo(JTable tabla, Nivel niv) {
        LimpiarTabla(tabla);
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        Iterator<Activo> activo = niv.getActivos().iterator();
        while (activo.hasNext()) {
            Activo act = (Activo) activo.next();
            Iterator<ActivoIniciofin> horas = act.getActivoIniciofins().iterator();
            while (horas.hasNext()) {
                ActivoIniciofin infin = (ActivoIniciofin) horas.next();
                Object[] fila = new Object[3];
                fila[0] = act.getDia();
                fila[1] = infin.getInicio();
                fila[2] = infin.getFin();
                model.addRow(fila);
            }
        }
        tabla.setModel(model);
    }
    
    public Asistencia getAsistencia(int nro) {
        Asistencia asis=(Asistencia)Controlador.getPERSISTENCIA().getAsistencia(nro).iterator().next();
        return asis;
    }

    public void CargarTablaInactivo(JTable tabla, Nivel niv) {
        LimpiarTabla(tabla);
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        Iterator inactivo = niv.getInactivos().iterator();
        while (inactivo.hasNext()) {
            Inactivo inact = (Inactivo) inactivo.next();
            Object[] fila = new Object[4];
            //fila[0] = inact.getIdInactivo();
            fila[0] = inact.getMotivo();
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
            fila[1] = formateador.format(inact.getFechaInicio());
            fila[2] = formateador.format(inact.getFechaFin());
            fila[3] = inact.getInstrumentoLegal();
            model.addRow(fila);
        }
        tabla.setModel(model);
    }
    
    public void CargarTablaFeriados(JTable tabla) {
        LimpiarTabla(tabla);
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        Iterator it = PERSISTENCIA.getFeriados().iterator();
        while (it.hasNext()) {
            Feriado fer = (Feriado) it.next();
            Object[] fila = new Object[3];
            fila[0] = fer.getIdFeriado();
            fila[1] = fer.getComentario();
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
            fila[2] = formateador.format(fer.getDia());
            model.addRow(fila);
        }
        tabla.setModel(model);
    }

    public void CargarTablaFiltroActividades(JTable tabla, String buscarpor, String valor) {
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        Iterator<Tarea> act = PERSISTENCIA.getTareas().iterator();
        while (act.hasNext()) {
            Tarea ac = act.next();
            if(ac.getEstado()==true){
                if (buscarpor.equals("Nombre") && ac.getEstado() == true) {
                    int i = ac.getNombre().indexOf(valor);
                    if (i == 0) {
                        Object[] fila = new Object[4];
                        fila[0] = ac.getIdTarea();
                        fila[1] = ac.getNombre();
                        fila[2] = ac.getLugar();
                        fila[3] = ac.getComentario();
                        model.addRow(fila);
                    }
                } else if (buscarpor.equals("Lugar") && ac.getEstado() == true) {
                    int i = ac.getLugar().indexOf(valor);
                    if (i == 0) {
                        Object[] fila = new Object[4];
                        fila[0] = ac.getIdTarea();
                        fila[1] = ac.getNombre();
                        fila[2] = ac.getLugar();
                        fila[3] = ac.getComentario();
                        model.addRow(fila);
                    }
                }
            }
        }
        tabla.setModel(model);
    }
    
    public void CargarTablaFiltro(JTable tabla, String buscarpor, String valor) {
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        Iterator<Personal> pe = PERSISTENCIA.getPersonales().iterator();
        while (pe.hasNext()) {
            Personal person = (Personal) pe.next();
                if (buscarpor.equals("Apellido") && person.getEstado() == true) {
                    int i = person.getApellido().indexOf(valor);
                    if (i == 0) {
                       Object[] fila = new Object[8];
                        fila[0] = person.getIdPersonal();
                        fila[1] = person.toString();
                        fila[2] = person.getDni();
                        fila[3] = person.getSexo();
                        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                        fila[4] = formateador.format(person.getIngreso());
                        fila[5] = person.getCorreoElectronico();
                        fila[6] = person.getCuil();
                        fila[7] = person.getEstadoCivil();
                        model.addRow(fila);
                    }
                } else if (buscarpor.equals("Nombre") && person.getEstado() == true) {
                    int i = person.getNombre().indexOf(valor);
                    if (i == 0) {
                        Object[] fila = new Object[8];
                        fila[0] = person.getIdPersonal();
                        fila[1] = person.toString();
                        fila[2] = person.getDni();
                        fila[3] = person.getSexo();
                        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                        fila[4] = formateador.format(person.getIngreso());
                        fila[5] = person.getCorreoElectronico();
                        fila[6] = person.getCuil();
                        fila[7] = person.getEstadoCivil();
                        model.addRow(fila);
                    }
                } else if (buscarpor.equals("Sexo") && person.getEstado() == true) {
                    int i = person.getSexo().indexOf(valor);
                    if (i == 0) {
                        Object[] fila = new Object[8];
                        fila[0] = person.getIdPersonal();
                        fila[1] = person.toString();
                        fila[2] = person.getDni();
                        fila[3] = person.getSexo();
                        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                        fila[4] = formateador.format(person.getIngreso());
                        fila[5] = person.getCorreoElectronico();
                        fila[6] = person.getCuil();
                        fila[7] = person.getEstadoCivil();
                        model.addRow(fila);
                    }
                } else if (buscarpor.equals("Estado civil") && person.getEstado() == true) {
                    int i = person.getEstadoCivil().indexOf(valor);
                    if (i == 0) {
                        Object[] fila = new Object[8];
                        fila[0] = person.getIdPersonal();
                        fila[1] = person.toString();
                        fila[2] = person.getDni();
                        fila[3] = person.getSexo();
                        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                        fila[4] = formateador.format(person.getIngreso());
                        fila[5] = person.getCorreoElectronico();
                        fila[6] = person.getCuil();
                        fila[7] = person.getEstadoCivil();
                        model.addRow(fila);
                    }
                }
            
        }
        tabla.setModel(model);
    }
    
    public void Cargarpersonal(JTable tabla, String buscarpor, String valor) {
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        Iterator<Personal> pe = getPrimerEstablecimiento().getPersonals().iterator();
        while (pe.hasNext()) {
            Personal person = (Personal) pe.next();
            if (buscarpor.equals("Apellido") && person.getEstado() == true) {
                int i = person.getApellido().indexOf(valor);
                if (i == 0) {
                    Object[] fila = new Object[5];
                    fila[0] = person.getIdPersonal();
                    fila[1] = person.toString();
                    fila[2] = person.getDni();
                    fila[3] = person.getSexo();
                    fila[4] = person.getEstadoCivil();
                    model.addRow(fila);
                }
            } else if (buscarpor.equals("Nombre") && person.getEstado() == true) {
                int i = person.getNombre().indexOf(valor);
                if (i == 0) {
                    Object[] fila = new Object[5];
                    fila[0] = person.getIdPersonal();
                    fila[1] = person.toString();
                    fila[2] = person.getDni();
                    fila[3] = person.getSexo();
                    fila[4] = person.getEstadoCivil();
                    model.addRow(fila);
                }
            } else if (buscarpor.equals("Sexo") && person.getEstado() == true) {
                int i = person.getSexo().indexOf(valor);
                if (i == 0) {
                    Object[] fila = new Object[5];
                    fila[0] = person.getIdPersonal();
                    fila[1] = person.toString();
                    fila[2] = person.getDni();
                    fila[3] = person.getSexo();
                    fila[4] = person.getEstadoCivil();
                    model.addRow(fila);
                }
            } else if (buscarpor.equals("Estado civil") && person.getEstado() == true) {
                int i = person.getEstadoCivil().indexOf(valor);
                if (i == 0) {
                    Object[] fila = new Object[5];
                    fila[0] = person.getIdPersonal();
                    fila[1] = person.toString();
                    fila[2] = person.getDni();
                    fila[3] = person.getSexo();
                    fila[4] = person.getEstadoCivil();
                    model.addRow(fila);
                }
            }
        }
        tabla.setModel(model);
    }

    public void CargarTablaflia(JTable Tabla, Personal per) {
        try {
            DefaultTableModel modelo = (DefaultTableModel) Tabla.getModel();
            Establecimiento col = getPrimerEstablecimiento();
            //Personal ppp=col.getPersonal(per.getIdPersonal());
            Iterator<PersonalFamiliar> rs = per.getPersonalFamiliarsForIdPersonal().iterator();
            while (rs.hasNext()) {
                PersonalFamiliar perfam = (PersonalFamiliar) rs.next();
                Personal pe = col.getPersonal(perfam.getId().getIdFamiliar());
                if (pe.getFamiliar() == true) {
                    Object fila[] = new Object[8];
                    fila[0] = pe.getIdPersonal();
                    fila[1] = pe.toString();
                    fila[2] = pe.getTipodoc().getTipodoc();
                    fila[3] = pe.getDni();
                    fila[4] = perfam.getTiporelacion().getRelacion();
                    SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                    fila[5] = formateador.format(pe.getFechaNac());

                    Boolean asig = perfam.getAsignacionFamiliar().booleanValue();
                    if (asig == true) {
                        fila[6] = "Si";
                    } else if (asig == false) {
                        fila[6] = "No";
                    }
                    modelo.addRow(fila);
                }
            }
            Tabla.setModel(modelo);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
    
    public void CargarTablaActividad(JTable Tabla, Tarea tar) {
        try {
            DefaultTableModel modelo = (DefaultTableModel) Tabla.getModel();
            if(!tar.getTareaclases().isEmpty()){
                if (tar.getEstado() == true) {
                    Object fila[] = new Object[8];
                    Iterator it=tar.getAgendas().iterator();
                    while(it.hasNext()){
                        Agenda age=(Agenda) it.next();
                        Iterator ita=age.getAnos().iterator();
                        while(ita.hasNext()){
                           Ano an=(Ano) ita.next();
                           Iterator itm=an.getMeses().iterator();
                           while(itm.hasNext()){
                               Mes me=(Mes) itm.next();
                               Iterator itd=me.getDias().iterator();
                               while(itd.hasNext()){
                                   Dia di=(Dia) itd.next();
                                   Iterator itini=di.getIniciofins().iterator();
                                   while(itini.hasNext()){                                    
                                       Iniciofin ini=(Iniciofin)itini.next();
                                       Tareaclase tarcla=tar.getTareaclases().iterator().next();
                                       Date fecha=new Date();
                                       fecha.setYear(an.getAno()-1900);
                                       fecha.setMonth(me.getMes());
                                       fecha.setDate(di.getDia());
                                       fila[0] = age.getPersonal().getIdPersonal();
                                       fila[1] = age.getPersonal().toString();
                                       fila[2] = age.getRevista().getNombre();
                                       fila[3] = tarcla.getAula();
                                       fila[4] = tarcla.getNumero();
                                       SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                                       SimpleDateFormat formateador2 = new SimpleDateFormat("HH:mm");
                                       fila[5] = formateador.format(fecha);
                                       fila[6] = formateador2.format(ini.getInicio());
                                       fila[7] = formateador2.format(ini.getFin());
                                       modelo.addRow(fila);
                                   }
                               }
                           }
                       }
                    }
                    
                }
            }else if(!tar.getTareareunions().isEmpty()){
                if (tar.getEstado() == true) {
                    Object fila[] = new Object[8];
                    Iterator it=tar.getAgendas().iterator();
                    while(it.hasNext()){
                        Agenda age=(Agenda) it.next();
                        Iterator ita=age.getAnos().iterator();
                        while(ita.hasNext()){
                           Ano an=(Ano) ita.next();
                           Iterator itm=an.getMeses().iterator();
                           while(itm.hasNext()){
                               Mes me=(Mes) itm.next();
                               Iterator itd=me.getDias().iterator();
                               while(itd.hasNext()){
                                   Dia di=(Dia) itd.next();
                                   Iterator itini=di.getIniciofins().iterator();
                                   while(itini.hasNext()){
                                       Iniciofin ini=(Iniciofin)itini.next();
                                       Tareareunion tarreu=tar.getTareareunions().iterator().next();
                                       Date fecha=new Date();
                                       fecha.setYear(an.getAno()-1900);
                                       fecha.setMonth(me.getMes());
                                       fecha.setDate(di.getDia());
                                       fila[0] = age.getPersonal().getIdPersonal();
                                       fila[1] = age.getPersonal().toString();
                                       fila[2] = tarreu.getMotivo();
                                       fila[3] = tarreu.getCaracter();
                                       //fila[4] = 
                                       SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                                       SimpleDateFormat formateador2 = new SimpleDateFormat("HH:mm");
                                       fila[5] = formateador.format(fecha);
                                       fila[6] = formateador2.format(ini.getInicio());
                                       fila[7] = formateador2.format(ini.getFin());
                                       modelo.addRow(fila);
                                   }
                               }
                           }
                       }
                    }
                    
                }
            }else if(!tar.getTareaextracurriculars().isEmpty()){
                if (tar.getEstado() == true) {
                    Object fila[] = new Object[8];
                    Iterator it=tar.getAgendas().iterator();
                    while(it.hasNext()){
                        Agenda age=(Agenda) it.next();
                        Iterator ita=age.getAnos().iterator();
                        while(ita.hasNext()){
                           Ano an=(Ano) ita.next();
                           Iterator itm=an.getMeses().iterator();
                           while(itm.hasNext()){
                               Mes me=(Mes) itm.next();
                               Iterator itd=me.getDias().iterator();
                               while(itd.hasNext()){
                                   Dia di=(Dia) itd.next();
                                   Iterator itini=di.getIniciofins().iterator();
                                   while(itini.hasNext()){
                                       Iniciofin ini=(Iniciofin)itini.next();
                                       Tareaextracurricular tarreu=tar.getTareaextracurriculars().iterator().next();
                                       Date fecha=new Date();
                                       String vacio="";
                                       fecha.setYear(an.getAno()-1900);
                                       fecha.setMonth(me.getMes());
                                       fecha.setDate(di.getDia());
                                       fila[0] = age.getPersonal().getIdPersonal();
                                       fila[1] = age.getPersonal().toString();
                                       fila[2] = tar.getComentario();
                                       SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                                       SimpleDateFormat formateador2 = new SimpleDateFormat("HH:mm");
                                       Franco fran=age.getFranco(age);
                                       if(fran.getIdFranco()!=null){fila[3] = formateador.format(fran.getDiaFranco());}
                                       fila[4] = formateador.format(tarreu.getDiaFin());
                                       fila[5] = formateador.format(fecha);
                                       fila[6] = formateador2.format(ini.getInicio());
                                       fila[7] = formateador2.format(ini.getFin());
                                       modelo.addRow(fila);
                                   }
                               }
                           }
                        }
                    }
                }
            }else if(!tar.getTareaotros().isEmpty()){
                if (tar.getEstado() == true) {
                    Object fila[] = new Object[8];
                    Iterator it=tar.getAgendas().iterator();
                    while(it.hasNext()){
                        Agenda age=(Agenda) it.next();
                        Iterator ita=age.getAnos().iterator();
                        while(ita.hasNext()){
                           Ano an=(Ano) ita.next();
                           Iterator itm=an.getMeses().iterator();
                           while(itm.hasNext()){
                               Mes me=(Mes) itm.next();
                               Iterator itd=me.getDias().iterator();
                               while(itd.hasNext()){
                                   Dia di=(Dia) itd.next();
                                   Iterator itini=di.getIniciofins().iterator();
                                   while(itini.hasNext()){
                                       Iniciofin ini=(Iniciofin)itini.next();
                                       Tareaotro tarreu=tar.getTareaotros().iterator().next();
                                       Date fecha=new Date();
                                       String vacio="";
                                       fecha.setYear(an.getAno()-1900);
                                       fecha.setMonth(me.getMes());
                                       fecha.setDate(di.getDia());
                                       fila[0] = age.getPersonal().getIdPersonal();
                                       fila[1] = age.getPersonal().toString();
                                       fila[2] = tar.getComentario();
                                       fila[3] = tarreu.getCaracteristica();
                                       SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                                       SimpleDateFormat formateador2 = new SimpleDateFormat("HH:mm");
                                       fila[4] = formateador.format(tarreu.getDiaFin());
                                       fila[5] = formateador.format(fecha);
                                       fila[6] = formateador2.format(ini.getInicio());
                                       fila[7] = formateador2.format(ini.getFin());
                                       modelo.addRow(fila);
                                   }
                               }
                           }
                        }
                    }
                }
            }
                
            Tabla.setModel(modelo);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
    
    public Agenda getAgenda(Tarea tar,int per) {
        Agenda band=new Agenda();
        Iterator it= PERSISTENCIA.getAgendas().iterator();
        while(it.hasNext()){
            Agenda age=(Agenda) it.next();
            if(age.getId().getIdTarea()==tar.getIdTarea()&& age.getId().getIdPersonal()==per){
                band=age;
                break;
            }
        }
        return band;
    }
    
    public void CargarTablaInasistencias(JTable Tabla,String m,int ano) {
        try {
            DefaultTableModel modelo = (DefaultTableModel) Tabla.getModel();
            Establecimiento col = getPrimerEstablecimiento();
            int mes=ObtenerMes(m);
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
            Iterator<Asistencia> ita = Controlador.getPERSISTENCIA().ObtenerListaInasistencia(mes, ano).iterator();
            while (ita.hasNext()) {
                Asistencia asis=ita.next();
                if(asis.getIniciofin().getDia().getMes().getAno().getAgenda().getTarea().getEstado()==true && asis.getIniciofin().getDia().getMes().getAno().getAgenda().getPersonal().getEstado()==true){
                    Date fecha=new Date();
                    fecha.setYear(asis.getIniciofin().getDia().getMes().getAno().getAno()-1900);
                    fecha.setMonth(asis.getIniciofin().getDia().getMes().getMes());
                    fecha.setDate(asis.getIniciofin().getDia().getDia());
                    if(asis.getIniciofin().getDia().getMes().getMes()==mes && asis.getIniciofin().getDia().getMes().getAno().getAno()==ano){
                        Object fila[] = new Object[9];
                        fila[0] = asis.getIdAsistencia();
                        fila[1] = asis.getIniciofin().getDia().getMes().getAno().getAgenda().getTarea().getNombre();
                        fila[2] = asis.getIniciofin().getDia().getMes().getAno().getAgenda().getPersonal().toString();
                        fila[3] = formateador.format(fecha);
                        fila[4] = asis.getIniciofin().getInicio();
                        fila[5] = asis.getIniciofin().getFin();
                        fila[6] = new Boolean(false);
                        if(asis.getTardanza()==true){
                            fila[7] = new Boolean(true);
                        }else{fila[7] = new Boolean(false);}
                        if(asis.getJustificacions().iterator().hasNext()){
                            fila[8] = asis.getJustificacions().iterator().next().getArticulo();
                            fila[9] = asis.getJustificacions().iterator().next().getMotivo();
                        }
                        modelo.addRow(fila);
                    }
            }
            }
            Iterator<Asistencia> itaa = Controlador.getPERSISTENCIA().ObtenerListaTardanza(mes, ano).iterator();
            while (itaa.hasNext()) {
                Asistencia asis=itaa.next();
                if(asis.getIniciofin().getDia().getMes().getAno().getAgenda().getTarea().getEstado()==true && asis.getIniciofin().getDia().getMes().getAno().getAgenda().getPersonal().getEstado()==true){
                    Date fecha=new Date();
                    fecha.setYear(asis.getIniciofin().getDia().getMes().getAno().getAno()-1900);
                    fecha.setMonth(asis.getIniciofin().getDia().getMes().getMes());
                    fecha.setDate(asis.getIniciofin().getDia().getDia());
                    if(asis.getIniciofin().getDia().getMes().getMes()==mes && asis.getIniciofin().getDia().getMes().getAno().getAno()==ano){
                        Object fila[] = new Object[10];
                        fila[0] = asis.getIdAsistencia();
                        fila[1] = asis.getIniciofin().getDia().getMes().getAno().getAgenda().getTarea().getNombre();
                        fila[2] = asis.getIniciofin().getDia().getMes().getAno().getAgenda().getPersonal().toString();
                        fila[3] = formateador.format(fecha);
                        fila[4] = asis.getIniciofin().getInicio();
                        fila[5] = asis.getIniciofin().getFin();
                        if(asis.getTardanza()==true){
                            fila[6] = new Boolean(true);
                        }else{fila[6] = new Boolean(false);}
                        if(asis.getTardanza()==true){
                            fila[7] = new Boolean(true);
                        }else{fila[7] = new Boolean(false);}
                        if(asis.getJustificacions().iterator().hasNext()){
                            fila[8] = asis.getJustificacions().iterator().next().getArticulo();
                            fila[9] = asis.getJustificacions().iterator().next().getMotivo();
                        }
                        modelo.addRow(fila);
                    }
                }
            }
            
            Tabla.setModel(modelo);
            Tabla.getColumnModel().getColumn(0).setWidth(30);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    public Boolean ControlFamiliar(Personal pe, Tiporelacion rel, Boolean bandera) {
        Boolean ban = true;
        if (bandera == false) {
            if (rel.getRelacion().equals("MADRE") || rel.getRelacion().equals("PADRE")) {
                ban = false;
                int contadorma = 0;
                int contadorpa = 0;
                if (rel.getRelacion().equals("MADRE")) {
                    contadorma++;
                } else if (rel.getRelacion().equals("PADRE")) {
                    contadorpa++;
                }
                Iterator<PersonalFamiliar> rs = pe.getPersonalFamiliarsForIdPersonal().iterator();
                while (rs.hasNext()) {
                    PersonalFamiliar perfam = (PersonalFamiliar) rs.next();
                    if (perfam.getTiporelacion().getRelacion().equals("MADRE")) {
                        contadorma++;
                    } else if (perfam.getTiporelacion().getRelacion().equals("PADRE")) {
                        contadorpa++;
                    }
                }
                if (contadorma == 2 && contadorpa == 0) {
                    ban = true;
                } else if (contadorpa == 2 && contadorma == 0) {
                    ban = true;
                } else if (contadorma <= 1 && contadorpa <= 1) {
                    ban = true;
                }
            }
        }
        return ban;
    }

    public Tiporelacion gettiporelacion(Tiporelacion unTiporelacion) {
        Tiporelacion t = new Tiporelacion();
        Iterator rs = Controlador.getPERSISTENCIA().getRelaciones().iterator();
        while (rs.hasNext()) {
            Tiporelacion per = (Tiporelacion) rs.next();
            if (per.getRelacion().equals(unTiporelacion.getRelacion())) {
                t = per;
            }
        }
        return t;
    }
        public void CargarTablacheck(JTable Tabla){
            try {
                DefaultTableModel modelo = (DefaultTableModel)Tabla.getModel();
                Establecimiento col= getPrimerEstablecimiento();
                Iterator rs=col.getPersonals().iterator();
                while (rs.hasNext()) {
                    Personal per=(Personal) rs.next();
                    if(per.getEstado()==true){
                        Object fila[]=new Object[3];
                        fila[0] = new Boolean(false);
                        fila[1] = per;
                        fila[2] = per.getDni();
                        modelo.addRow(fila); 
                    }
                }
                Tabla.setModel(modelo);
             } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.toString());
            }   
        }

    public static Date sumarFechasDias(Date fch, int dias) {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(fch.getTime());
        cal.add(Calendar.DATE, dias);
        return new Date(cal.getTimeInMillis());
    }

    public Personal getPersonal(Tipodoc tipo, String dni) {
        Personal pe= new Personal();
        if (!PERSISTENCIA.existePersonal(tipo.getIdTipodoc(), dni).isEmpty()){
           pe = (Personal) PERSISTENCIA.existePersonal(tipo.getIdTipodoc(), dni).get(0);
        }
        return  pe;
    }
    
//    public void CargarComboEstablecimientosPer(JComboBox JCombo, Personal per) {
//        if (!PERSISTENCIA.DecjuradaPer(per.getIdPersonal()).isEmpty()){
//           Declaracionjurada dec= (Declaracionjurada) PERSISTENCIA.DecjuradaPer(per.getIdPersonal()).get(0);
//           Iterator<DetalleEstablecimiento> car = dec.getDetalleEstablecimientos().iterator();
//            while (car.hasNext()) {
//                DetalleEstablecimiento ca = (DetalleEstablecimiento) car.next();
//                JCombo.addItem(ca);
//            }
//        }
//    }
    
    public void CargarComboEstablecimientosPerso(JComboBox JCombo, Personal per) {
        if (!PERSISTENCIA.DecjuradaPer(per.getIdPersonal()).isEmpty()){
           Declaracionjurada dec= (Declaracionjurada) PERSISTENCIA.DecjuradaPer(per.getIdPersonal()).get(0);
           Iterator<DetalleEstablecimiento> car = PERSISTENCIA.DetalledecjuradaPer(dec.getIdDeclaracionjurada()).iterator();
            while (car.hasNext()) {
                DetalleEstablecimiento ca = (DetalleEstablecimiento) car.next();
                JCombo.addItem(ca);
            }
        }
    }
    
    public String ObtenerDia(int i){
    String dia=null;
    if(i==0){
        dia="DOMINGO";
    }else if(i==1){
        dia="LUNES";
    }else if(i==2){
        dia="MARTES";
    }else if(i==3){
        dia="MIERCOLES";
    }else if(i==4){
        dia="JUEVES";
    }else if(i==5){
        dia="VIERNES";
    }else if(i==6){
        dia="SABADO";
    }
    return dia;
    }
    
    public int ObtenerMes(String mes){
    int m=0;
    if(mes.equals("ENERO")){
        m=0;
    }else if(mes.equals("FEBRERO")){
        m=1;
    }else if(mes.equals("MARZO")){
        m=2;    
    }else if(mes.equals("ABRIL")){
        m=3;    
    }else if(mes.equals("MAYO")){
        m=4;    
    }else if(mes.equals("JUNIO")){
        m=5;    
    }else if(mes.equals("JULIO")){
        m=6;    
    }else if(mes.equals("AGOSTO")){
        m=7;    
    }else if(mes.equals("SEPTIEMBRE")){
        m=8;    
    }else if(mes.equals("OCTUBRE")){
        m=9;    
    }else if(mes.equals("NOVIEMBRE")){
        m=10;    
    }else if(mes.equals("DICIEMBRE")){
        m=11;    
    }
    return m;
    }
    
    public int ObtenerInasistenciaPersonal(Personal per){
        Date date=new Date();
        int contador=0;
        Iterator<Asistencia> ita = Controlador.getPERSISTENCIA().ObtenerListaInasistencia(date.getMonth(), date.getYear()+1900).iterator();
        while (ita.hasNext()) {
            Asistencia asis=ita.next();
            int id=asis.getIniciofin().getDia().getMes().getAno().getAgenda().getPersonal().getIdPersonal();
            int id2= per.getIdPersonal();
            if(id==id2){
                contador++;
            }
        }
        return contador;
    }
    
     public int ObtenerTardanzaPersonal(Personal per){
        Date date=new Date();
        int contador=0;
        Iterator<Asistencia> ita = Controlador.getPERSISTENCIA().ObtenerListaTardanza(date.getMonth(), date.getYear()+1900).iterator();
        while (ita.hasNext()) {
            Asistencia asis=ita.next();
            int id=asis.getIniciofin().getDia().getMes().getAno().getAgenda().getPersonal().getIdPersonal();
            int id2= per.getIdPersonal();
            if(id==id2){
                contador++;
            }
        }
        return contador;
    }
     
     public boolean existeFeriado(Date unafecha) {
        boolean tmpres = false;
        Iterator it = PERSISTENCIA.getFeriados().iterator();
        while (it.hasNext()) {
            Feriado fer = (Feriado) it.next();
            if (fer.getDia().compareTo(unafecha)==0) {
                tmpres = true;
                break;
            }
        }
        return tmpres;
    }
     
     public Feriado getFeriado(int idfer) {
        Feriado fer=new Feriado();
        Iterator it=PERSISTENCIA.getFeriados().iterator();
           while(it.hasNext()){
           Feriado p=(Feriado) it.next();
           if(idfer == p.getIdFeriado()){
               fer=p;
               break;
           }
         }
        return fer;
    }
     
     public boolean VerificarCheckTabla(JTable tabla) {
        boolean band = false;
        int c=0;
        DefaultTableModel modelo = (DefaultTableModel)tabla.getModel();
        while (tabla.getRowCount() != c) {
            if (modelo.getValueAt(c, 0).equals(true)) {
                band=true;
                break;
            }
            c++;
        }
        return band;
    }
     
     public void mostrarReporte(String report, List consulta,String titulo) {
        try {
            String SO = System.getProperty("os.name");
            String master;
            if (SO.toUpperCase().equals("LINUX")) {
                master = System.getProperty("user.dir") + "/src/Reportes/" + report + ".jasper";
            } else {
                master = System.getProperty("user.dir") + "\\src\\Reportes\\" + report + ".jasper";
            }
            System.out.println("Dirección del Reporte en disco: " + master);
            if (master == null) {
                //javax.swing.JOptionPane msj=new javax.swing.JOptionPane() ;
                JOptionPane.showMessageDialog(null, "No se encontro el Reporte", "Error", 2, null);
            }
            //--------------------------------------------------------------------------------------------------------
            JasperReport masterReport = null;
            try {
                masterReport = (JasperReport) JRLoader.loadObject(master);

            } catch (JRException e) {
                //javax.swing.JOptionPane msj=new javax.swing.JOptionPane() ;
                JOptionPane.showMessageDialog(null, "Error al cargar el reporte", "Error", 2, null);
            }
//            Map parametro = new HashMap();
//            parametro = null;
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(consulta);
//            HibernateQueryResultDataSource ds = new HibernateQueryResultDataSource(usuarios,campos);
            //Reporte diseñado y compilado con iReport
            JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, new HashMap(),ds);
            //Se lanza el Viewer de Jasper, no termina aplicación al salir
            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            jviewer.setTitle(titulo);
            jviewer.setVisible(true);
            // CerrarConex();
        } catch (Exception j) {
            //javax.swing.JOptionPane msj=new javax.swing.JOptionPane() ;
            JOptionPane.showMessageDialog(null, "Error al cargar el reporte", "Error", 2, null);
        }

    }
}
