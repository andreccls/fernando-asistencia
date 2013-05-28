/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Persistencia.ConexionJDBC;
import Persistencia.persistencia;
import Presentacion.JFrameActividades;
import java.awt.Image;
import java.awt.Label;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
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
    public static ConexionJDBC conexion;

    public Controlador() {
        try {        
            PERSISTENCIA = new persistencia();
            conexion = new ConexionJDBC();
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static persistencia getPERSISTENCIA() {
        return PERSISTENCIA;
    }
    
    public static ConexionJDBC getConexionJDBC() {
        return conexion;
    }


    public boolean existeColegio() {
        boolean col = false;
        if (Controlador.getPERSISTENCIA().getEstablecimientos().size() > 0) {
            col = true;
        }
        return col;
    }
    
    public boolean VerificarLicencia(Personal per,Date inicio, Date fin) {
        boolean col = true;
        Iterator it = PERSISTENCIA.getLicencias(per.getIdPersonal()).iterator();
        while(it.hasNext()){
            try {
                Licencia lic=(Licencia) it.next();
                Date licini=lic.getInicio();
                Date licfin=lic.getFin();
                SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                Date ini=formateador.parse(formateador.format(inicio));
                Date fi=formateador.parse(formateador.format(fin));
                if((ini.compareTo(licini)<=0 && fi.compareTo(licini)>=0) || (ini.compareTo(licfin)<=0 && fi.compareTo(licfin)>=0)|| (ini.compareTo(licini)<=0 && fi.compareTo(licfin)>=0) || (ini.compareTo(licini)>=0 && fi.compareTo(licfin)<=0)){
                    col=false;
                }
            } catch (ParseException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return col;
    }
    
    public boolean VerificarLicencia(Personal per,Date inicio, Date fin,int idlic) {
        boolean col = true;
        Iterator it = PERSISTENCIA.getLicencias(per.getIdPersonal()).iterator();
        while(it.hasNext()){
            try {
                Licencia lic=(Licencia) it.next();
                if(lic.getIdLicencia()!=idlic){
                    Date licini=lic.getInicio();
                    Date licfin=lic.getFin();
                    SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                    Date ini=formateador.parse(formateador.format(inicio));
                    Date fi=formateador.parse(formateador.format(fin));
                    if((ini.compareTo(licini)<=0 && fi.compareTo(licini)>=0) || (ini.compareTo(licfin)<=0 && fi.compareTo(licfin)>=0)|| (ini.compareTo(licini)<=0 && fi.compareTo(licfin)>=0) || (ini.compareTo(licini)>=0 && fi.compareTo(licfin)<=0)){
                        col=false;
                    }
                }
            } catch (ParseException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return col;
    }

    public Establecimiento getPrimerEstablecimiento() {
        Establecimiento col = new Establecimiento();
        Iterator it=PERSISTENCIA.getEstablecimientos().iterator();
        if(it.hasNext()){
            col=(Establecimiento) PERSISTENCIA.getEstablecimientos().get(0);
        }
        return col;
    }
    
    public Anolectivo getAnoLectivo() {
        Anolectivo an = new Anolectivo();
        Date ano=new Date();
        int a=ano.getYear()+1900;
        Iterator it=PERSISTENCIA.getAnoLectivo(a).iterator();
        if(it.hasNext()){
            an=(Anolectivo) it.next();
        }
        return an;
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

    public void crearEstablecimiento(String nombre, String calle, Integer altura, String piso, String depto, byte[] imagen, String leyenda, Set<Auditoria> auditorias, Set<Tarea> tareas, Set<Departamento> departamentos, Set<Circular> circulars, Set<Personal> personals, Set<Anolectivo> anolectivos, Set<DetalleEstablecimiento> detalleEstablecimientos) {
        if (!existeColegio()) {
            Establecimiento unEstablecimiento = new Establecimiento(nombre, calle, altura, piso, depto,imagen,leyenda,auditorias, tareas, departamentos, circulars, personals,anolectivos,detalleEstablecimientos);
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
                if (per.getEstado() == true) {
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
    
    public void CargarComboLugar(JComboBox JCombo) {
        try {
            Iterator<Lugar> rs = Controlador.getPERSISTENCIA().getLugar().iterator();
            while (rs.hasNext()) {
                Lugar lug = rs.next();
                JCombo.addItem(lug);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
    
    public void CargarComboAulas(JComboBox JCombo) {
        try {
            Iterator<Aula> rs = Controlador.getPERSISTENCIA().getAulas().iterator();
            while (rs.hasNext()) {
                Aula au = (Aula) rs.next();
                JCombo.addItem(au);
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

    public void CargarComboPerfil(JComboBox JCombo) {
        try {
//            LimpiarCombo(JCombo);
            Iterator rs = PERSISTENCIA.getPerfiles().iterator();
            while (rs.hasNext()) {
                Perfil perf = (Perfil) rs.next();
                JCombo.addItem(perf);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    public byte[] ObtenerByte (String direccion){
        File file = new File(direccion);
        byte[] bFile = new byte[(int) file.length()];
        try {
             FileInputStream fileInputStream = new FileInputStream(file);
             fileInputStream.read(bFile);
             fileInputStream.close();
        } catch (Exception e) {
             e.printStackTrace();
        }
        return bFile;
    }
    
    
//    public byte[] extractBytes(String ImageName) throws IOException {
//        // open image
//        File imgPath = new File(ImageName);
//        BufferedImage bufferedImage = ImageIO.read(imgPath);
//        // get DataBufferBytes from Raster
//        WritableRaster raster = bufferedImage.getRaster();
//        DataBufferByte data = (DataBufferByte) raster.getDataBuffer();
//
//        return (data.getData());
//    }
    
//    public byte[] simple(String ImageName) throws IOException {
//        File fi = new File(ImageName);
//        byte[] fileContent = Files.readAllBytes(fi.toPath());
//        return fileContent;
//    }
    
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
    
    public void CargarComboDecjurada(JComboBox JCombo, Personal per) {
        try {
            Iterator rs = per.getDeclaracionjuradas().iterator();
            while (rs.hasNext()) {
                Declaracionjurada dec = (Declaracionjurada) rs.next();
                Anolectivo ano=dec.getAnolectivo();
                JCombo.addItem(ano);
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

    public void CargarComboEstablecimientoFiltro(JComboBox JCombo, String valor) {
        try {
            JCombo.removeAllItems();
            Iterator est = Controlador.getPERSISTENCIA().getEstablecimientos().iterator();
            while (est.hasNext()) {
                Establecimiento e = (Establecimiento) est.next();
                if (getPrimerEstablecimiento().getIdEstablecimiento() != e.getIdEstablecimiento()) {
                    int i = e.getNombre().indexOf(valor);
                    if (i == 1) {
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

    public void CargarComboTipocargoFiltro(JComboBox JCombo, String valor) {
        try {
            JCombo.removeAllItems();
            Iterator est = Controlador.getPERSISTENCIA().getTipocargos().iterator();
            while (est.hasNext()) {
                Tipocargo e = (Tipocargo) est.next();
                int i = e.getNombre().indexOf(valor);
                if (i == 1) {
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

    public void CargarComboTiponivelFiltro(JComboBox JCombo, String valor) {
        try {
            JCombo.removeAllItems();
            Iterator est = Controlador.getPERSISTENCIA().getTiponiveles().iterator();
            while (est.hasNext()) {
                Tiponivel e = (Tiponivel) est.next();
                int i = e.getNombre().indexOf(valor);
                if (i == 1) {
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

    public boolean ExisteFecha(Tarea tar, Date fecha){
        boolean existe= true;
        Iterator it=PERSISTENCIA.getIniciofinTarea(tar.getIdTarea()).iterator();
        while(it.hasNext()){
            Iniciofin in=(Iniciofin) it.next();
            Date dd=fecha;
            if(in.getDia().getDia()==dd.getDate() && in.getDia().getMes().getMes()==dd.getMonth()/* && in.getDia().getMes().getAno().getAno()==(dd.getYear()+1900)*/){
                existe=false;
            }
        }
        return existe;
    }
    
    public static void LimpiarCombo(JComboBox JCombo) {
        DefaultComboBoxModel mod = (DefaultComboBoxModel) JCombo.getModel();
        mod.removeAllElements();
    }

    public static void LimpiarTabla(JTable Tabla) {
        DefaultTableModel modelo = (DefaultTableModel) Tabla.getModel();
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
    }

    public List ObtenerListaTabla(JTable Tabla) {
        List lista = new ArrayList();
        DefaultTableModel modelo = (DefaultTableModel) Tabla.getModel();
        int c = 0;
        while (modelo.getRowCount() != c) {
            boolean check = (Boolean) Tabla.getValueAt(c, 0);
            if (check == true) {
                Personal per = (Personal) Tabla.getValueAt(c, 1);
                lista.add(per);

            }
            c++;
        }
        return lista;
    }

    public void CargarGrillaActividades(JTable tabla, Personal per, Date di) {
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        Iterator<Agenda> ag = per.getAgendas().iterator();
        while (ag.hasNext()) {
            Agenda agen = (Agenda) ag.next();
            Iterator<Iniciofin> it = agen.getDia2(di).getIniciofins().iterator();
            while (it.hasNext()) {
                Iniciofin ini = (Iniciofin) it.next();
                Object[] fila = new Object[4];
                fila[0] = agen.getTarea().getNombre();
                fila[1] = agen.getTarea().getLugar();
                fila[2] = ini.getInicio();
                fila[3] = ini.getFin();
                model.addRow(fila);
            }
        }
        tabla.setModel(model);
    }

    public void CargarTablaActivo(JTable tabla, Nivel niv) {
        LimpiarTabla(tabla);
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        Iterator<Activo> activo = PERSISTENCIA.getActivos(niv.getIdNivel()).iterator();
        while (activo.hasNext()) {
            Activo act = (Activo) activo.next();
            Iterator<ActivoIniciofin> horas = PERSISTENCIA.getActivoiniciofin(act.getIdActivo()).iterator();
            while (horas.hasNext()) {
                ActivoIniciofin infin = (ActivoIniciofin) horas.next();
                Object[] fila = new Object[3];
                fila[0] = act.getDia();
                SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                fila[1] = formateador.format(infin.getInicio());
                fila[2] = formateador.format(infin.getFin());
                model.addRow(fila);
            }
        }
        tabla.setModel(model);
    }

    public Asistencia getAsistencia(int nro) {
        Asistencia asis = (Asistencia) Controlador.getPERSISTENCIA().getAsistencia(nro).iterator().next();
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

    public void CargarTablaFeriados(JTable tabla,Date fecha) {
        LimpiarTabla(tabla);
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        Iterator it = PERSISTENCIA.getFeriados(fecha.getYear()+1900).iterator();
        while (it.hasNext()) {
            Feriado fer = (Feriado) it.next();
            Object[] fila = new Object[2];
            fila[0] = fer;
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
            fila[1] = formateador.format(fer.getDia());
            model.addRow(fila);
        }
        tabla.setModel(model);
    }

    public void CargarTablaFiltroActividades(JTable tabla, String buscarpor, String tipo, String valor) {
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        Anolectivo ano=getAnoLectivo();
        if (tipo.equals("Clase")) {
            Iterator<Tareaclase> act = PERSISTENCIA.getTareasClases().iterator();
            while (act.hasNext()) {
                Tareaclase ac = act.next();
                if (ac.getTarea().getEstado()&&ac.getTarea().getAgendas().size()>0) {
                    Agenda ag=ac.getTarea().getAgendas().iterator().next();
                    if(ag.getAnolectivo().getIdAnolectivo()==ano.getIdAnolectivo()){
                    if (buscarpor.equals("Nombre") && ac.getTarea().getEstado() == true) {
                        int i = ac.getTarea().getNombre().indexOf(valor);
                        if (i == 0) {
                            Object[] fila = new Object[3];
                            fila[0] = ac;
                            fila[1] = ac.getTarea().getLugar();
                            fila[2] = ac.getTarea().getComentario();
                            model.addRow(fila);
                        }
                    } else if (buscarpor.equals("Lugar") && ac.getTarea().getEstado() == true) {
                        int i = ac.getTarea().getLugar().getNombre().indexOf(valor);
                        if (i == 0) {
                            Object[] fila = new Object[3];
                            fila[0] = ac;
                            fila[1] = ac.getTarea().getLugar();
                            fila[2] = ac.getTarea().getComentario();
                            model.addRow(fila);
                        }
                    }
                }
                }
                
            }
        } else if (tipo.equals("Reunión")) {
            Iterator<Tareareunion> act = PERSISTENCIA.getTareasReuniones().iterator();
            while (act.hasNext()) {
                Tareareunion ac = act.next();
                if (ac.getTarea().getEstado()&&ac.getTarea().getAgendas().size()>0) {
                    Agenda ag=ac.getTarea().getAgendas().iterator().next();
                    if(ag.getAnolectivo().getIdAnolectivo()==ano.getIdAnolectivo()){
                    if (buscarpor.equals("Nombre") && ac.getTarea().getEstado() == true) {
                        int i = ac.getTarea().getNombre().indexOf(valor);
                        if (i == 0) {
                            Object[] fila = new Object[3];
                            fila[0] = ac;
                            fila[1] = ac.getTarea().getLugar();
                            fila[2] = ac.getTarea().getComentario();
                            model.addRow(fila);
                        }
                    } else if (buscarpor.equals("Lugar") && ac.getTarea().getEstado() == true) {
                        int i = ac.getTarea().getLugar().getNombre().indexOf(valor);
                        if (i == 0) {
                            Object[] fila = new Object[3];
                            fila[0] = ac;
                            fila[1] = ac.getTarea().getLugar();
                            fila[2] = ac.getTarea().getComentario();
                            model.addRow(fila);
                        }
                    }
                }
                }
            }
        } else if (tipo.equals("Extracurricular")) {
            Iterator<Tareaextracurricular> act = PERSISTENCIA.getTareasExtracurriculares().iterator();
            while (act.hasNext()) {
                Tareaextracurricular acc = act.next();
                Tarea ac = acc.getTarea();
                if (ac.getEstado()&&ac.getAgendas().size()>0) {
                    Agenda ag=ac.getAgendas().iterator().next();
                    if(ag.getAnolectivo().getIdAnolectivo()==ano.getIdAnolectivo()){
                    if (buscarpor.equals("Nombre") && ac.getEstado() == true) {
                        int i = ac.getNombre().indexOf(valor);
                        if (i == 0) {
                            Object[] fila = new Object[3];
                            fila[0] = acc;
                            fila[1] = ac.getLugar();
                            fila[2] = ac.getComentario();
                            model.addRow(fila);
                        }
                    } else if (buscarpor.equals("Lugar") && ac.getEstado() == true) {
                        int i = ac.getLugar().getNombre().indexOf(valor);
                        if (i == 0) {
                            Object[] fila = new Object[3];
                            fila[0] = acc;
                            fila[1] = ac.getLugar();
                            fila[2] = ac.getComentario();
                            model.addRow(fila);
                        }
                    }
                }
                }
            }
        } else if (tipo.equals("Otro")) {
            Iterator<Tareaotro> act = PERSISTENCIA.getTareasOtros().iterator();
            while (act.hasNext()) {
                Tareaotro acc = act.next();
                Tarea ac = acc.getTarea();
//                Agenda ag=ac.getAgendas().iterator().next();
                if (ac.getEstado()&&ac.getAgendas().size()>0) {
                    Agenda ag=ac.getAgendas().iterator().next();
                    if(ag.getAnolectivo().getIdAnolectivo()==ano.getIdAnolectivo()){
                    if (buscarpor.equals("Nombre") && ac.getEstado() == true) {
                        int i = ac.getNombre().indexOf(valor);
                        if (i == 0) {
                            Object[] fila = new Object[3];
                            fila[0] = acc;
                            fila[1] = ac.getLugar();
                            fila[2] = ac.getComentario();
                            model.addRow(fila);
                        }
                    } else if (buscarpor.equals("Lugar") && ac.getEstado() == true) {
                        int i = ac.getLugar().getNombre().indexOf(valor);
                        if (i == 0) {
                            Object[] fila = new Object[3];
                            fila[0] = acc;
                            fila[1] = ac.getLugar();
                            fila[2] = ac.getComentario();
                            model.addRow(fila);
                        }
                    }
                }
                }
            }
        } else if (tipo.equals("Todos")) {
            Iterator<Tarea> act = PERSISTENCIA.getTareas().iterator();
            while (act.hasNext()) {
                Tarea ac = act.next();
//                Agenda ag=ac.getAgendas().iterator().next();
                if (ac.getEstado()&&ac.getAgendas().size()>0) {
                    Agenda ag=ac.getAgendas().iterator().next();
                    if(ag.getAnolectivo().getIdAnolectivo()==ano.getIdAnolectivo()){
                    if (buscarpor.equals("Nombre") && ac.getEstado() == true) {
                        int i = ac.getNombre().indexOf(valor);
                        if (i == 0) {
                            Object[] fila = new Object[3];
                            fila[0] = ac;
                            fila[1] = ac.getLugar();
                            fila[2] = ac.getComentario();
                            model.addRow(fila);
                        }
                    } else if (buscarpor.equals("Lugar") && ac.getEstado() == true) {
                        int i = ac.getLugar().getNombre().indexOf(valor);
                        if (i == 0) {
                            Object[] fila = new Object[3];
                            fila[0] = ac;
                            fila[1] = ac.getLugar();
                            fila[2] = ac.getComentario();
                            model.addRow(fila);
                        }
                    }
                }
                }
            }
        }

        tabla.setModel(model);
    }

    public void CargarTablaCirculares(JTable tabla, Date dia) {
        LimpiarTabla(tabla);
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        Iterator<Circular> it = PERSISTENCIA.getCirculares().iterator();
        while (it.hasNext()) {
            Circular cir = it.next();
            if (cir.getInicio().compareTo(dia)<=0 && cir.getFin().compareTo(dia)>=0) {
                Object[] fila = new Object[4];
                fila[0] = cir;
                SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                fila[1] = formateador.format(cir.getInicio());
                fila[2] = formateador.format(cir.getFin());
                fila[3] = cir.getFirma();
                model.addRow(fila);
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
                    Object[] fila = new Object[4];
                    fila[0] = person;
                    fila[1] = person.getDni();
                    fila[2] = person.getSexo();
                    fila[3] = person.getEstadoCivil();
                    model.addRow(fila);
                }
            } else if (buscarpor.equals("Nombre") && person.getEstado() == true) {
                int i = person.getNombre().indexOf(valor);
                if (i == 0) {
                    Object[] fila = new Object[4];
                    fila[0] = person;
                    fila[1] = person.getDni();
                    fila[2] = person.getSexo();
                    fila[3] = person.getEstadoCivil();
                    model.addRow(fila);
                }
            } else if (buscarpor.equals("Sexo") && person.getEstado() == true) {
                int i = person.getSexo().indexOf(valor);
                if (i == 0) {
                    Object[] fila = new Object[4];
                    fila[0] = person;
                    fila[1] = person.getDni();
                    fila[2] = person.getSexo();
                    fila[3] = person.getEstadoCivil();
                    model.addRow(fila);
                }
            } else if (buscarpor.equals("Estado civil") && person.getEstado() == true) {
                int i = person.getEstadoCivil().indexOf(valor);
                if (i == 0) {
                    Object[] fila = new Object[4];
                    fila[0] = person;
                    fila[1] = person.getDni();
                    fila[2] = person.getSexo();
                    fila[3] = person.getEstadoCivil();
                    model.addRow(fila);
                }
            }
        }
        tabla.setModel(model);
    }

    public void CargarHistorial (String valor, Date fecha, List listaper, List listatar){
        if (valor.equals("Año")) {
                Iterator it = PERSISTENCIA.getAnos(fecha.getYear() + 1900).iterator();
                while (it.hasNext()) {
                    Ano ano = (Ano) it.next();
                    Personal per = ano.getAgenda().getPersonal();
                    Tarea tar=ano.getAgenda().getTarea();
                    if (!listaper.contains(per)) {
                        listaper.add(per);
                    }
                    if (!listatar.contains(tar)) {
                        listatar.add(tar);
                    }
                    
                }
            } else if (valor.equals("Mes")) {
                Iterator it = PERSISTENCIA.getMeses(fecha.getYear() + 1900, fecha.getMonth()).iterator();
                while (it.hasNext()) {
                    Mes mes = (Mes) it.next();
                    Personal per = mes.getAno().getAgenda().getPersonal();
                    Tarea tar=mes.getAno().getAgenda().getTarea();
                    if (!listaper.contains(per)) {
                        listaper.add(per);
                    }
                    if (!listatar.contains(tar)) {
                        listatar.add(tar);
                    }
                }
            } else if (valor.equals("Dia")) {
                Iterator it = PERSISTENCIA.getDias(fecha.getYear()+1900, fecha.getMonth(), fecha.getDate()).iterator();
                while (it.hasNext()) {
                    Dia dia = (Dia) it.next();
                    Personal per = dia.getMes().getAno().getAgenda().getPersonal();
                    Tarea tar=dia.getMes().getAno().getAgenda().getTarea();
                    if (!listaper.contains(per)) {
                        listaper.add(per);
                    }
                    if (!listatar.contains(tar)) {
                        listatar.add(tar);
                    }
                }
            }
    }
    public void CargarTablaHistorial(JTable tablapersonal, Date fecha, String valor, String filtro,JTable tablaclases) {
        List lista = new ArrayList();
        List listac= new ArrayList();
        if (valor.equals("AÑO")) {
            Iterator it = PERSISTENCIA.getAnos(fecha.getYear() + 1900).iterator();
            while (it.hasNext()) {
                Ano ano = (Ano) it.next();
                Personal per = ano.getAgenda().getPersonal();
                Tarea tar=ano.getAgenda().getTarea();
                if (!lista.contains(per)) {
                    lista.add(per);
                }
                if (!listac.contains(tar)) {
                    listac.add(tar);
                }
            }
        } else if (valor.equals("MES")) {
            Iterator it = PERSISTENCIA.getMeses(fecha.getYear() + 1900, fecha.getMonth()).iterator();
            while (it.hasNext()) {
                Mes mes = (Mes) it.next();
                Personal per = mes.getAno().getAgenda().getPersonal();
                Tarea tar=mes.getAno().getAgenda().getTarea();
                if (!lista.contains(per)) {
                    lista.add(per);
                }
                if (!listac.contains(tar)) {
                    listac.add(tar);
                }
            }
        } else if (valor.equals("DIA")) {
            Iterator it = PERSISTENCIA.getDias(fecha.getYear()+1900, fecha.getMonth(), fecha.getDate()).iterator();
            while (it.hasNext()) {
                Dia dia = (Dia) it.next();
                Personal per = dia.getMes().getAno().getAgenda().getPersonal();
                Tarea tar=dia.getMes().getAno().getAgenda().getTarea();
                if (!lista.contains(per)) {
                    lista.add(per);
                }
                if (!listac.contains(tar)) {
                    listac.add(tar);
                }
            }
        }
        
        DefaultTableModel model = (DefaultTableModel) tablapersonal.getModel();
        Iterator itt = lista.iterator();
        while (itt.hasNext()) {
            Personal pe = (Personal) itt.next();
            Iterator ittt = PERSISTENCIA.getAuditoria(pe.getIdPersonal()).iterator();
            while (ittt.hasNext()) {
                Auditoria audi = (Auditoria) ittt.next();
                if (filtro.equals("Todos")) {
                    Object[] fila = new Object[6];
                    fila[0] = pe;
                    fila[1] = pe.getDni();
                    SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                    fila[2] = formateador.format(pe.getIngreso());
                    fila[3] = pe.getCorreoElectronico();
                    fila[4] = audi.getOperacion();
                    SimpleDateFormat formateador2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                    fila[5] = formateador2.format(audi.getFecha());
                    model.addRow(fila);
                } else if (filtro.equals("Activos") && audi.getOperacion().equals("Insertar")) {
                    Object[] fila = new Object[6];
                    fila[0] = pe;
                    fila[1] = pe.getDni();
                    SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                    fila[2] = formateador.format(pe.getIngreso());
                    fila[3] = pe.getCorreoElectronico();
                    fila[4] = audi.getOperacion();
                    SimpleDateFormat formateador2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                    fila[5] = formateador2.format(audi.getFecha());
                    model.addRow(fila);
                } else if (filtro.equals("Inactivos") && audi.getOperacion().equals("Eliminar")) {
                    Object[] fila = new Object[6];
                    fila[0] = pe;
                    fila[1] = pe.getDni();
                    SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                    fila[2] = formateador.format(pe.getIngreso());
                    fila[3] = pe.getCorreoElectronico();
                    fila[4] = audi.getOperacion();
                    SimpleDateFormat formateador2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                    fila[5] = formateador2.format(audi.getFecha());
                    model.addRow(fila);
                }
            }
        }
        tablapersonal.setModel(model);
        
        DefaultTableModel modell = (DefaultTableModel) tablaclases.getModel();
        Iterator itc = listac.iterator();
        while (itc.hasNext()) {
            Tarea tar = (Tarea) itc.next();
            Iterator ittt = PERSISTENCIA.getAuditoriaTarea(tar.getIdTarea()).iterator();
            while (ittt.hasNext()) {
                Auditoria audi = (Auditoria) ittt.next();
                if (filtro.equals("Todos")) {
                    Object[] fila = new Object[6];
                    fila[0] = tar;
                    fila[1] = tar.getLugar();
                    SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                    fila[2] = formateador.format(tar.getDiaInicio());
                    fila[3] = formateador.format(tar.getDiaFin());
                    fila[4] = audi.getOperacion();
                    SimpleDateFormat formateador2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                    fila[5] = formateador2.format(audi.getFecha());
                    modell.addRow(fila);
                } 
                else if (filtro.equals("Activos") && audi.getOperacion().equals("Insertar")) {
                    Object[] fila = new Object[6];
                    fila[0] = tar;
                    fila[1] = tar.getLugar();
                    SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                    fila[2] = formateador.format(tar.getDiaInicio());
                    fila[3] = formateador.format(tar.getDiaFin());
                    fila[4] = audi.getOperacion();
                    SimpleDateFormat formateador2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                    fila[5] = formateador2.format(audi.getFecha());
                    modell.addRow(fila);
                } else if (filtro.equals("Inactivos") && audi.getOperacion().equals("Eliminar")) {
                    Object[] fila = new Object[6];
                    fila[0] = tar;
                    fila[1] = tar.getLugar();
                    SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                    fila[2] = formateador.format(tar.getDiaInicio());
                    fila[3] = formateador.format(tar.getDiaFin());
                    fila[4] = audi.getOperacion();
                    SimpleDateFormat formateador2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                    fila[5] = formateador2.format(audi.getFecha());
                    modell.addRow(fila);
                }
            }
        }
        tablaclases.setModel(modell);
    }
    
    public void CargarTablaPerTar(JTable tabla, Personal per){
        LimpiarTabla(tabla);
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        Iterator it=per.getAgendas().iterator();
        while(it.hasNext()){
            Agenda age=(Agenda) it.next();
            Tarea tar=age.getTarea();
            Object[] fila = new Object[4];
            fila[0] = tar;
            fila[1] = tar.getComentario();
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            fila[2] = formateador.format(tar.getDiaInicio());
            fila[3] = formateador.format(tar.getDiaFin());
            model.addRow(fila);
        }
    }
    
    public void CargarTablaPerTar(JTable tabla, Tarea tar){
        LimpiarTabla(tabla);
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        Iterator it=tar.getAgendas().iterator();
        while(it.hasNext()){
            Agenda age=(Agenda) it.next();
            Personal per=age.getPersonal();
            Object[] fila = new Object[4];
            fila[0] = per;
            fila[1] = per.getDni();
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
            fila[2] = formateador.format(per.getIngreso());
            fila[3] = per.getCorreoElectronico();
            model.addRow(fila);
        }
    }
    
    public void CargarTablaPerTarHoy(JTable tabla, Tarea tar,Date fecha){
        LimpiarTabla(tabla);
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        Iterator it=PERSISTENCIA.getIniciofinTar(fecha.getDate(), fecha.getMonth(), fecha.getYear()+1900, tar.getIdTarea()).iterator();
        while(it.hasNext()){
            Iniciofin in=(Iniciofin) it.next();
            Personal per=in.getDia().getMes().getAno().getAgenda().getPersonal();
            if(per.getEstado()){
                Object[] fila = new Object[4];
                fila[0] = per;
                fila[1] = per.getDni();
                SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                fila[2] = per.getCorreoElectronico();
                fila[3] = formateador.format(per.getIngreso());
                model.addRow(fila);
            }
        }
    }
    
    public void CargarTablaPerTarHoy(JTable tabla, Personal per,Date fecha){
        LimpiarTabla(tabla);
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        Iterator it=PERSISTENCIA.getIniciofinPer(fecha.getDate(), fecha.getMonth(), fecha.getYear()+1900, per.getIdPersonal()).iterator();
        while(it.hasNext()){
            Iniciofin in=(Iniciofin) it.next();
            Tarea tar= in.getDia().getMes().getAno().getAgenda().getTarea();
            if(tar.getEstado()){
                Object[] fila = new Object[4];
                fila[0] = tar;
                fila[1] = tar.getComentario();
                SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                fila[2] = formateador.format(in.getInicio());
                fila[3] = formateador.format(in.getFin());
                model.addRow(fila);
            }
        }
    }
    
    public void CargarTablaPerHoy(JTable tabla,Date hoy, Date hora){
        LimpiarTabla(tabla);
        List<Personal> pe=new ArrayList();
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        Iterator it=PERSISTENCIA.getIniciofin(hoy.getDate(), hoy.getMonth(), hoy.getYear()+1900).iterator();
        while(it.hasNext()){
            Iniciofin in=(Iniciofin) it.next();
            if(in.getInicio().compareTo(hora)<=0 && in.getFin().compareTo(hora)>=0){
                Personal per=in.getDia().getMes().getAno().getAgenda().getPersonal();
                if(!pe.contains(per)&&per.getEstado()){
                    pe.add(per);
                    Object[] fila = new Object[4];
                    fila[0] = per;
                    fila[1] = per.getDni();
                    SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                    fila[2] = per.getCorreoElectronico();
                    fila[3] = formateador.format(per.getIngreso());
                    model.addRow(fila);
                }
            }
        }
    }
    
    public void CargarTablaTarHoy(JTable tabla,Date hoy, Date hora){
        LimpiarTabla(tabla);
        List<Tarea> ta=new ArrayList();
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        Iterator it=PERSISTENCIA.getIniciofin(hoy.getDate(), hoy.getMonth(), hoy.getYear()+1900).iterator();
        while(it.hasNext()){
            Iniciofin in=(Iniciofin) it.next();
            if(in.getInicio().compareTo(hora)<=0 && in.getFin().compareTo(hora)>=0){
                Tarea tar=in.getDia().getMes().getAno().getAgenda().getTarea();
                if(!ta.contains(tar)&&tar.getEstado()){
                    ta.add(tar);
                    Object[] fila = new Object[4];
                    fila[0] = tar;
                    fila[1] = tar.getComentario();
                    SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                    fila[2] = formateador.format(in.getInicio());
                    fila[3] = formateador.format(in.getFin());
                    model.addRow(fila);
                }
            }
        }
    }
    
    public void CargarTablaFiltro(JTable tabla, String buscarpor, String valor) {
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        Iterator<Personal> pe = PERSISTENCIA.getPersonales().iterator();
        while (pe.hasNext()) {
            Personal person = (Personal) pe.next();
            if (buscarpor.equals("Apellido") && person.getEstado() == true) {
                int i = person.getApellido().indexOf(valor);
                if (i == 0) {
                    Object[] fila = new Object[7];
                    fila[0] = person;
                    fila[1] = person.getDni();
                    fila[2] = person.getSexo();
                    SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                    fila[3] = formateador.format(person.getIngreso());
                    fila[4] = person.getCorreoElectronico();
                    fila[5] = person.getCuil();
                    fila[6] = person.getEstadoCivil();
                    model.addRow(fila);
                }
            } else if (buscarpor.equals("Nombre") && person.getEstado() == true) {
                int i = person.getNombre().indexOf(valor);
                if (i == 0) {
                    Object[] fila = new Object[7];
                    fila[0] = person;
                    fila[1] = person.getDni();
                    fila[2] = person.getSexo();
                    SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                    fila[3] = formateador.format(person.getIngreso());
                    fila[4] = person.getCorreoElectronico();
                    fila[5] = person.getCuil();
                    fila[6] = person.getEstadoCivil();
                    model.addRow(fila);
                }
            } else if (buscarpor.equals("Sexo") && person.getEstado() == true) {
                int i = person.getSexo().indexOf(valor);
                if (i == 0) {
                    Object[] fila = new Object[7];
                    fila[0] = person.toString();
                    fila[1] = person.getDni();
                    fila[2] = person.getSexo();
                    SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                    fila[3] = formateador.format(person.getIngreso());
                    fila[4] = person.getCorreoElectronico();
                    fila[5] = person.getCuil();
                    fila[6] = person.getEstadoCivil();
                    model.addRow(fila);
                }
            } else if (buscarpor.equals("Estado civil") && person.getEstado() == true) {
                int i = person.getEstadoCivil().indexOf(valor);
                if (i == 0) {
                    Object[] fila = new Object[7];
                    fila[0] = person.toString();
                    fila[1] = person.getDni();
                    fila[2] = person.getSexo();
                    SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                    fila[3] = formateador.format(person.getIngreso());
                    fila[7] = person.getCorreoElectronico();
                    fila[5] = person.getCuil();
                    fila[6] = person.getEstadoCivil();
                    model.addRow(fila);
                }
            }

        }
        tabla.setModel(model);
    }
    
    public void CargarTablaRegistro(JTable tabla, Personal per, Date dia, String buscar) {
        try {
            DefaultTableModel model = (DefaultTableModel) tabla.getModel();
            Iterator<Registroacceso> it = per.getRegistroaccesos().iterator();
            while (it.hasNext()) {
                Registroacceso reg = (Registroacceso) it.next();
                if (buscar.equals("DIA")) {
                    if (reg.getFecha().getYear() == dia.getYear() && reg.getFecha().getMonth() == dia.getMonth() && reg.getFecha().getDate() == dia.getDate()) {
                        Object fila[] = new Object[3];
                        SimpleDateFormat formateador2 = new SimpleDateFormat("HH:mm");
                        fila[0] = reg;
                        if (reg.getInicio() != null) {
                                fila[1] = formateador2.format(reg.getInicio());
                            }
                            if (reg.getFin() != null) {
                                fila[2] = formateador2.format(reg.getFin());
                            }
                        model.addRow(fila);
                    }

                } else if (buscar.equals("SEMANA")) {
                    Date aux=restarFechasDias(dia, 7);
                    while (aux.compareTo(dia)<=0) {
                        if (reg.getFecha().getYear() == aux.getYear() && reg.getFecha().getMonth() == aux.getMonth() && reg.getFecha().getDate() == aux.getDate()) {
                            Object fila[] = new Object[3];
                            SimpleDateFormat formateador2 = new SimpleDateFormat("HH:mm");
                            fila[0] = reg;
                            if (reg.getInicio() != null) {
                                    fila[1] = formateador2.format(reg.getInicio());
                                }
                                if (reg.getFin() != null) {
                                    fila[2] = formateador2.format(reg.getFin());
                                }
                            model.addRow(fila);
                        }
                        aux = sumarFechasDias(aux, 1);
                    }
                }else if (buscar.equals("MES")) {
                    if (reg.getFecha().getYear() == dia.getYear() && reg.getFecha().getMonth() == dia.getMonth()) {
                        Object fila[] = new Object[3];
                        SimpleDateFormat formateador2 = new SimpleDateFormat("HH:mm");
                        fila[0] = reg;
                        if (reg.getInicio() != null) {
                                fila[1] = formateador2.format(reg.getInicio());
                            }
                            if (reg.getFin() != null) {
                                fila[2] = formateador2.format(reg.getFin());
                            }
                        model.addRow(fila);
                    }
                }else if (buscar.equals("AÑO")) {
                    if (reg.getFecha().getYear() == dia.getYear()) {
                        Object fila[] = new Object[3];
                        SimpleDateFormat formateador2 = new SimpleDateFormat("HH:mm");
                        fila[0] = reg;
                        if (reg.getInicio() != null) {
                                fila[1] = formateador2.format(reg.getInicio());
                            }
                            if (reg.getFin() != null) {
                                fila[2] = formateador2.format(reg.getFin());
                            }
                        model.addRow(fila);
                    }
                }
                
            }
            tabla.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
    
    public void CargarTablaRegistro2(JTable tabla, Personal per, Date dia, String buscar) {
        try {
            DefaultTableModel model = (DefaultTableModel) tabla.getModel();
            Iterator<Registroacceso> it = per.getRegistroaccesos().iterator();
            while (it.hasNext()) {
                Registroacceso reg = (Registroacceso) it.next();
                if (buscar.equals("DIA")) {
                    if (reg.getFecha().getYear() == dia.getYear() && reg.getFecha().getMonth() == dia.getMonth() && reg.getFecha().getDate() == dia.getDate()) {
                        Object fila[] = new Object[3];
//                        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                        SimpleDateFormat formateador2 = new SimpleDateFormat("HH:mm");
                        fila[0] = reg;
                        if (reg.getInicio() != null) {
                                fila[1] = formateador2.format(reg.getInicio());
                            }
                            if (reg.getFin() != null) {
                                fila[2] = formateador2.format(reg.getFin());
                            }
                        model.addRow(fila);
                    }

                } else if (buscar.equals("SEMANA")) {
                    Date aux=restarFechasDias(dia, 7);
                    while (aux.compareTo(dia)<=0) {
                        if (reg.getFecha().getYear() == aux.getYear() && reg.getFecha().getMonth() == aux.getMonth() && reg.getFecha().getDate() == aux.getDate()) {
                            Object fila[] = new Object[3];
//                            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                            SimpleDateFormat formateador2 = new SimpleDateFormat("HH:mm");
                            fila[0] = reg;
                            if (reg.getInicio() != null) {
                                    fila[1] = formateador2.format(reg.getInicio());
                                }
                                if (reg.getFin() != null) {
                                    fila[2] = formateador2.format(reg.getFin());
                                }
                            model.addRow(fila);
                        }
                        aux = sumarFechasDias(aux, 1);
                    }
                }else if (buscar.equals("MES")) {
                    if (reg.getFecha().getYear() == dia.getYear() && reg.getFecha().getMonth() == dia.getMonth()) {
                        Object fila[] = new Object[3];
//                        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                        SimpleDateFormat formateador2 = new SimpleDateFormat("HH:mm");
                        fila[0] = reg;
                        if (reg.getInicio() != null) {
                                fila[1] = formateador2.format(reg.getInicio());
                            }
                            if (reg.getFin() != null) {
                                fila[2] = formateador2.format(reg.getFin());
                            }
                        model.addRow(fila);
                    }
                }else if (buscar.equals("AÑO")) {
                    if (reg.getFecha().getYear() == dia.getYear()) {
                        Object fila[] = new Object[3];
//                        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                        SimpleDateFormat formateador2 = new SimpleDateFormat("HH:mm");
                        fila[0] = reg;
                        if (reg.getInicio() != null) {
                                fila[1] = formateador2.format(reg.getInicio());
                            }
                            if (reg.getFin() != null) {
                                fila[2] = formateador2.format(reg.getFin());
                            }
                        model.addRow(fila);
                    }
                }
                
            }
            tabla.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
    
    public String Obtenerhoras(JTable tabla) {
        int hora=0;
        int minuto=0;
        String trab=null;
        try {
            DefaultTableModel model = (DefaultTableModel) tabla.getModel();
            SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
            int c = 0;
            while (model.getRowCount() != c) {
                String oi = (String)model.getValueAt(c, 1);
                String of = (String)model.getValueAt(c, 2);
                if (oi != null && of != null) {
                    Date inicio = formateador.parse(oi);
                    Date fin = formateador.parse(of);
                    long tiempoInicial=inicio.getTime();
                    long tiempoFinal=fin.getTime(); 
                    long tiempo= tiempoFinal - tiempoInicial;
//                    int seconds = (int) (tiempo / 1000) % 60 ;
                    int minutos = (int) ((tiempo / (1000*60)) % 60);
                    int horas   = (int) ((tiempo / (1000*60*60)) % 24);
//                    long horas = (tiempo/3600000);
//                    
//                    //el metodo getTime te devuelve en mili segundos para saberlo en mins debes hacer
//                    resta=resta /(1000*60);
                    hora=hora+horas;
                    minuto=minuto+minutos;
                    if(minuto>=60){
                        hora=hora+1;
                        minuto=minuto-60;
                    }
                }
                c++;
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return trab= String.valueOf(hora)+":"+String.valueOf(minuto);
    }
    
    public String Obtenerhoras2(JTable tabla) {
        int hora=0;
        int minuto=0;
        String trab=null;
        try {
            DefaultTableModel model = (DefaultTableModel) tabla.getModel();
            SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
            int c = 0;
            while (model.getRowCount() != c) {
                String oi = (String)model.getValueAt(c, 1);
                String of = (String)model.getValueAt(c, 2);
                if (oi != null && of != null) {
                    Date inicio = formateador.parse(oi);
                    Date fin = formateador.parse(of);
                    long tiempoInicial=inicio.getTime();
                    long tiempoFinal=fin.getTime(); 
                    long tiempo= tiempoFinal - tiempoInicial;
//                    int seconds = (int) (tiempo / 1000) % 60 ;
                    int minutos = (int) ((tiempo / (1000*60)) % 60);
                    int horas   = (int) ((tiempo / (1000*60*60)) % 24);
//                    long horas = (tiempo/3600000);
//                    
//                    //el metodo getTime te devuelve en mili segundos para saberlo en mins debes hacer
//                    resta=resta /(1000*60);
                    hora=hora+horas;
                    minuto=minuto+minutos;
                    if(minuto>=60){
                        hora=hora+1;
                        minuto=minuto-60;
                    }
                }
                c++;
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return trab= String.valueOf(hora)+":"+String.valueOf(minuto);
    }
    
    public void CargarTablaCircularPer(JTable tabla, Personal per, Date dia, String buscar) {
        try {
            DefaultTableModel model = (DefaultTableModel) tabla.getModel();
            Iterator<Circularpersonal> it = per.getCircularpersonals().iterator();
            while (it.hasNext()) {
                Circularpersonal ci = (Circularpersonal) it.next();
                Circular cir= ci.getCircular();
                Date inicio=cir.getInicio();
                Date fin=cir.getFin();
                SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                Date d=formateador.parse(formateador.format(dia));
                if (buscar.equals("DIA")) {
                    if (inicio.compareTo(d)<=0 && fin.compareTo(d)>=0) {
                        Object fila[] = new Object[5];
                        fila[0] = ci;
                        fila[1] = cir;
                        fila[2] = formateador.format(cir.getInicio());
//                        fila[3] = ci.getEstado();
                        if (ci.getEstado()==true) {
                            fila[3] = new Boolean(true);
                        } else {
                            fila[3] = new Boolean(false);
                        }
                        fila[4] = ci.getDescripcion();
                        model.addRow(fila);
                    }

                } else if (buscar.equals("MES")) {
                    if (inicio.getMonth()<=d.getMonth() && fin.getMonth()>=d.getMonth()) {
                        Object fila[] = new Object[5];
                        fila[0] = cir.getFirma();
                        fila[1] = cir;
                        fila[2] = formateador.format(cir.getInicio());
//                        fila[3] = ci.getEstado();
                        if (ci.getEstado()==true) {
                            fila[3] = new Boolean(true);
                        } else {
                            fila[3] = new Boolean(false);
                        }
                        fila[4] = ci;
                        model.addRow(fila);
                    }
                }else if (buscar.equals("AÑO")) {
                    if (inicio.getYear()==d.getYear() && fin.getYear()==d.getYear()) {
                        Object fila[] = new Object[5];
                        fila[0] = cir.getFirma();
                        fila[1] = cir;
                        fila[2] = formateador.format(cir.getInicio());
                         fila[3] = ci.getEstado();
//                        fila[3] = ci.getEstado();
                        if (ci.getEstado()==true) {
                            fila[3] = new Boolean(true);
                        } else {
                            fila[3] = new Boolean(false);
                        }
                        fila[4] = ci;
                        model.addRow(fila);
                    }
                }
                
            }
            tabla.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
    
    public void CargarTablaAuditoria(JTable tabla, Date dia, String buscar,String filtro) {
        try {
            DefaultTableModel model = (DefaultTableModel) tabla.getModel();
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Iterator<Auditoria> it = PERSISTENCIA.getAuditoria().iterator();
            while (it.hasNext()) {
                Auditoria reg = (Auditoria) it.next();
                if (buscar.equals("DIA")) {
                    if (reg.getFecha().getYear() == dia.getYear() && reg.getFecha().getMonth() == dia.getMonth() && reg.getFecha().getDate() == dia.getDate()) {
                        Object fila[] = new Object[7];
                        if(filtro.equals("TODOS")){
                            if(reg.getEstablecimiento()!=null){
                                fila[0] = "Colegio: "+reg.getEstablecimiento().getNombre();
                            }else if(reg.getPersonalByIdPersonal()!=null){
                                fila[0] = "Personal: "+reg.getPersonalByIdPersonal().getApellido()+" "+reg.getPersonalByIdPersonal().getNombre();
                            }else if(reg.getTarea()!=null){
                                fila[0] = "Tarea: "+reg.getTarea().getNombre();
                            }else if(reg.getDepartamento()!=null){
                                fila[0] = "Departamento: "+reg.getDepartamento().getNombre();
                            }
                            fila[1] = reg.getOperacion();
                            fila[2] = formateador.format(reg.getFecha());
                            fila[3] = reg;
                            fila[4] = reg.getCampo();   
                            fila[5] = reg.getElementoAnterior();
                            fila[6] = reg.getElementoNuevo();  
                            model.addRow(fila);
                        }else if(filtro.equals("PERSONAL")){
                            if(reg.getPersonalByIdPersonal()!=null){
                                fila[0] = "Personal: "+reg.getPersonalByIdPersonal().getApellido()+" "+reg.getPersonalByIdPersonal().getNombre();
                                fila[1] = reg.getOperacion();
                                fila[2] = formateador.format(reg.getFecha());
                                fila[3] = reg;
                                fila[4] = reg.getCampo();   
                                fila[5] = reg.getElementoAnterior();
                                fila[6] = reg.getElementoNuevo(); 
                                model.addRow(fila);
                            }
                        }else if(filtro.equals("TAREA")){
                            if(reg.getTarea()!=null){
                                fila[0] = "Tarea: "+reg.getTarea().getNombre();
                                fila[1] = reg.getOperacion();
                                fila[2] = formateador.format(reg.getFecha());
                                fila[3] = reg;
                                fila[4] = reg.getCampo();   
                                fila[5] = reg.getElementoAnterior();
                                fila[6] = reg.getElementoNuevo(); 
                                model.addRow(fila);
                            }
                        }else if(filtro.equals("DEPARTAMENTO")){
                            if(reg.getDepartamento()!=null){
                                fila[0] = "Departamento: "+reg.getDepartamento().getNombre();
                                fila[1] = reg.getOperacion();
                                fila[2] = formateador.format(reg.getFecha());
                                fila[3] = reg;   
                                fila[4] = reg.getCampo();   
                                fila[5] = reg.getElementoAnterior();
                                fila[6] = reg.getElementoNuevo(); 
                                model.addRow(fila);
                            }
                        }else if(filtro.equals("COLEGIO")){
                            if(reg.getEstablecimiento()!=null){
                                fila[0] = "Colegio: "+reg.getEstablecimiento().getNombre();
                                fila[1] = reg.getOperacion();
                                fila[2] = formateador.format(reg.getFecha());
                                fila[3] = reg;
                                fila[4] = reg.getCampo();   
                                fila[5] = reg.getElementoAnterior();
                                fila[6] = reg.getElementoNuevo(); 
                                model.addRow(fila);
                            }
                        }
                    }

                } else if (buscar.equals("SEMANA")) {
                    Date aux=restarFechasDias(dia, 7);
                    while (aux.compareTo(dia)<=0) {
                        if (reg.getFecha().getYear() == aux.getYear() && reg.getFecha().getMonth() == aux.getMonth() && reg.getFecha().getDate() == aux.getDate()) {
                            Object fila[] = new Object[7];
                            if(filtro.equals("TODOS")){
                                if(reg.getEstablecimiento()!=null){
                                    fila[0] = "Colegio: "+reg.getEstablecimiento().getNombre();
                                }else if(reg.getPersonalByIdPersonal()!=null){
                                    fila[0] = "Personal: "+reg.getPersonalByIdPersonal().getApellido()+" "+reg.getPersonalByIdPersonal().getNombre();
                                }else if(reg.getTarea()!=null){
                                    fila[0] = "Tarea: "+reg.getTarea().getNombre();
                                }else if(reg.getDepartamento()!=null){
                                    fila[0] = "Departamento: "+reg.getDepartamento().getNombre();
                                }
                                fila[1] = reg.getOperacion();
                                fila[2] = formateador.format(reg.getFecha());
                                fila[3] = reg;
                                fila[4] = reg.getCampo();   
                                fila[5] = reg.getElementoAnterior();
                                fila[6] = reg.getElementoNuevo(); 
                                model.addRow(fila);
                            }else if(filtro.equals("PERSONAL")){
                                if(reg.getPersonalByIdPersonal()!=null){
                                    fila[0] = "Personal: "+reg.getPersonalByIdPersonal().getApellido()+" "+reg.getPersonalByIdPersonal().getNombre();
                                    fila[1] = reg.getOperacion();
                                    fila[2] = formateador.format(reg.getFecha());
                                    fila[3] = reg;
                                    fila[4] = reg.getCampo();   
                                    fila[5] = reg.getElementoAnterior();
                                    fila[6] = reg.getElementoNuevo(); 
                                    model.addRow(fila);
                                }
                            }else if(filtro.equals("TAREA")){
                                if(reg.getTarea()!=null){
                                    fila[0] = "Tarea: "+reg.getTarea().getNombre();
                                    fila[1] = reg.getOperacion();
                                    fila[2] = formateador.format(reg.getFecha());
                                    fila[3] = reg;
                                    fila[4] = reg.getCampo();   
                                    fila[5] = reg.getElementoAnterior();
                                    fila[6] = reg.getElementoNuevo(); 
                                    model.addRow(fila);
                                }
                            }else if(filtro.equals("DEPARTAMENTO")){
                                if(reg.getDepartamento()!=null){
                                    fila[0] = "Departamento: "+reg.getDepartamento().getNombre();
                                    fila[1] = reg.getOperacion();
                                    fila[2] = formateador.format(reg.getFecha());
                                    fila[3] = reg;
                                    fila[4] = reg.getCampo();   
                                    fila[5] = reg.getElementoAnterior();
                                    fila[6] = reg.getElementoNuevo(); 
                                    model.addRow(fila);
                                }
                            }else if(filtro.equals("COLEGIO")){
                                if(reg.getEstablecimiento()!=null){
                                    fila[0] = "Colegio: "+reg.getEstablecimiento().getNombre();
                                    fila[1] = reg.getOperacion();
                                    fila[2] = formateador.format(reg.getFecha());
                                    fila[3] = reg;
                                    fila[4] = reg.getCampo();   
                                    fila[5] = reg.getElementoAnterior();
                                    fila[6] = reg.getElementoNuevo(); 
                                    model.addRow(fila);
                                }
                            }
                        }
                        aux = sumarFechasDias(aux, 1);
                    }
                }else if (buscar.equals("MES")) {
                    if (reg.getFecha().getYear() == dia.getYear() && reg.getFecha().getMonth() == dia.getMonth()) {
                        Object fila[] = new Object[7];
                        if(filtro.equals("TODOS")){
                            if(reg.getEstablecimiento()!=null){
                                fila[0] = "Colegio: "+reg.getEstablecimiento().getNombre();
                            }else if(reg.getPersonalByIdPersonal()!=null){
                                fila[0] = "Personal: "+reg.getPersonalByIdPersonal().getApellido()+" "+reg.getPersonalByIdPersonal().getNombre();
                            }else if(reg.getTarea()!=null){
                                fila[0] = "Tarea: "+reg.getTarea().getNombre();
                            }else if(reg.getDepartamento()!=null){
                                fila[0] = "Departamento: "+reg.getDepartamento().getNombre();
                            }
                            fila[1] = reg.getOperacion();
                            fila[2] = formateador.format(reg.getFecha());
                            fila[3] = reg;
                            fila[4] = reg.getCampo();   
                            fila[5] = reg.getElementoAnterior();
                            fila[6] = reg.getElementoNuevo(); 
                            model.addRow(fila);
                        }else if(filtro.equals("PERSONAL")){
                            if(reg.getPersonalByIdPersonal()!=null){
                                fila[0] = "Personal: "+reg.getPersonalByIdPersonal().getApellido()+" "+reg.getPersonalByIdPersonal().getNombre();
                                fila[1] = reg.getOperacion();
                                fila[2] = formateador.format(reg.getFecha());
                                fila[3] = reg;
                                fila[4] = reg.getCampo();   
                                fila[5] = reg.getElementoAnterior();
                                fila[6] = reg.getElementoNuevo(); 
                                model.addRow(fila);
                            }
                        }else if(filtro.equals("TAREA")){
                            if(reg.getTarea()!=null){
                                fila[0] = "Tarea: "+reg.getTarea().getNombre();
                                fila[1] = reg.getOperacion();
                                fila[2] = formateador.format(reg.getFecha());
                                fila[3] = reg;
                                fila[4] = reg.getCampo();   
                                fila[5] = reg.getElementoAnterior();
                                fila[6] = reg.getElementoNuevo(); 
                                model.addRow(fila);
                            }
                        }else if(filtro.equals("DEPARTAMENTO")){
                            if(reg.getDepartamento()!=null){
                                fila[0] = "Departamento: "+reg.getDepartamento().getNombre();
                                fila[1] = reg.getOperacion();
                                fila[2] = formateador.format(reg.getFecha());
                                fila[3] = reg;
                                fila[4] = reg.getCampo();   
                                fila[5] = reg.getElementoAnterior();
                                fila[6] = reg.getElementoNuevo(); 
                                model.addRow(fila);
                            }
                        }else if(filtro.equals("COLEGIO")){
                            if(reg.getEstablecimiento()!=null){
                                fila[0] = "Colegio: "+reg.getEstablecimiento().getNombre();
                                fila[1] = reg.getOperacion();
                                fila[2] = formateador.format(reg.getFecha());
                                fila[3] = reg;
                                fila[4] = reg.getCampo();   
                                fila[5] = reg.getElementoAnterior();
                                fila[6] = reg.getElementoNuevo(); 
                                model.addRow(fila);
                            }
                        }
                    }
                }else if (buscar.equals("AÑO")) {
                    if (reg.getFecha().getYear() == dia.getYear()) {
                        Object fila[] = new Object[7];
                        if(filtro.equals("TODOS")){
                            if(reg.getEstablecimiento()!=null){
                                fila[0] = "Colegio: "+reg.getEstablecimiento().getNombre();
                            }else if(reg.getPersonalByIdPersonal()!=null){
                                fila[0] = "Personal: "+reg.getPersonalByIdPersonal().getApellido()+" "+reg.getPersonalByIdPersonal().getNombre();
                            }else if(reg.getTarea()!=null){
                                fila[0] = "Tarea: "+reg.getTarea().getNombre();
                            }else if(reg.getDepartamento()!=null){
                                fila[0] = "Departamento: "+reg.getDepartamento().getNombre();
                            }
                            fila[1] = reg.getOperacion();
                            fila[2] = formateador.format(reg.getFecha());
                            fila[3] = reg;
                            fila[4] = reg.getCampo();   
                            fila[5] = reg.getElementoAnterior();
                            fila[6] = reg.getElementoNuevo(); 
                            model.addRow(fila);
                        }else if(filtro.equals("PERSONAL")){
                            if(reg.getPersonalByIdPersonal()!=null){
                                fila[0] = "Personal: "+reg.getPersonalByIdPersonal().getApellido()+" "+reg.getPersonalByIdPersonal().getNombre();
                                fila[1] = reg.getOperacion();
                                fila[2] = formateador.format(reg.getFecha());
                                fila[3] = reg;
                                fila[4] = reg.getCampo();   
                                fila[5] = reg.getElementoAnterior();
                                fila[6] = reg.getElementoNuevo(); 
                                model.addRow(fila);
                            }
                        }else if(filtro.equals("TAREA")){
                            if(reg.getTarea()!=null){
                                fila[0] = "Tarea: "+reg.getTarea().getNombre();
                                fila[1] = reg.getOperacion();
                                fila[2] = formateador.format(reg.getFecha());
                                fila[3] = reg;
                                fila[4] = reg.getCampo();   
                                fila[5] = reg.getElementoAnterior();
                                fila[6] = reg.getElementoNuevo(); 
                                model.addRow(fila);
                            }
                        }else if(filtro.equals("DEPARTAMENTO")){
                            if(reg.getDepartamento()!=null){
                                fila[0] = "Departamento: "+reg.getDepartamento().getNombre();
                                fila[1] = reg.getOperacion();
                                fila[2] = formateador.format(reg.getFecha());
                                fila[3] = reg;
                                fila[4] = reg.getCampo();   
                                fila[5] = reg.getElementoAnterior();
                                fila[6] = reg.getElementoNuevo(); 
                                model.addRow(fila);
                            }
                        }else if(filtro.equals("COLEGIO")){
                            if(reg.getEstablecimiento()!=null){
                                fila[0] = "Colegio: "+reg.getEstablecimiento().getNombre();
                                fila[1] = reg.getOperacion();
                                fila[2] = formateador.format(reg.getFecha());
                                fila[3] = reg;
                                fila[4] = reg.getCampo();   
                                fila[5] = reg.getElementoAnterior();
                                fila[6] = reg.getElementoNuevo(); 
                                model.addRow(fila);
                            }
                        }
                    }
                }
                
            }
            tabla.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    public boolean verificarPerfil (String nombre){
        boolean bandera=false;
        Iterator it = PERSISTENCIA.getPerfiles().iterator();
        while(it.hasNext()){
            Perfil per=(Perfil) it.next();
            if(per.getNombre().equals(nombre)){
                bandera=true;
            }
        }
        return bandera;
    }
    
    
    public void CargarTablacheck(JTable tabla, String buscarpor, String valor, List personales) {
        try {
            DefaultTableModel model = (DefaultTableModel) tabla.getModel();
            if (!personales.isEmpty()) {
                //Cargar lista de checkeados
                Iterator<Personal> it = getPrimerEstablecimiento().getPersonals().iterator();
                while (it.hasNext()) {
                    Personal person = (Personal) it.next();
                    Iterator<Personal> itt = personales.iterator();
                    while (itt.hasNext()) {
                        Personal per = itt.next();
                        if (per.getIdPersonal() == person.getIdPersonal()) {
                            Object fila[] = new Object[3];
                            fila[0] = new Boolean(true);
                            fila[1] = person;
                            fila[2] = person.getDni();
                            model.addRow(fila);
                        }
                    }
                }
            }
            boolean band = true;
            Iterator<Personal> ite = getPrimerEstablecimiento().getPersonals().iterator();
            while (ite.hasNext()) {
                band = true;
                Personal person = (Personal) ite.next();
                //No cargar la lista de personales checkeados
                if (!personales.isEmpty()) {
                    Iterator<Personal> iter = personales.iterator();
                    while (iter.hasNext()) {
                        Personal per = iter.next();
                        if (per.getIdPersonal() == person.getIdPersonal()) {
                            band = false;
                            break;
                        }
                    }
                }
                if (band == true) {
                    //Cargar resto de personales
                    boolean band2 = false;
                    if (!buscarpor.equals("TODOS")) {
                        Iterator it = person.getPersonalDepartamentos().iterator();
                        while (it.hasNext()) {
                            PersonalDepartamento perd = (PersonalDepartamento) it.next();
                            if (perd.getDepartamento().getNombre().equals(buscarpor)) {
                                band2 = true;
                            }
                        }
                    } else {
                        band2 = true;
                    }

                    if (person.getEstado() == true && band2 == true) {
                        int i = person.getApellido().indexOf(valor);
                        int e = person.getNombre().indexOf(valor);
                        if (i == 0 || e == 0) {
                            Object fila[] = new Object[3];
                            fila[0] = new Boolean(false);
                            fila[1] = person;
                            fila[2] = person.getDni();
                            model.addRow(fila);
                        }
                    }
                    tabla.setModel(model);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    public void CargarpersonalSimple(JTable tabla, String buscarpor, String valor, List personales) {
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        boolean band = true;
        Iterator<Personal> pe = getPrimerEstablecimiento().getPersonals().iterator();
        while (pe.hasNext()) {
            band = true;
            Personal person = (Personal) pe.next();
            Iterator<Personal> it = personales.iterator();
            while (it.hasNext()) {
                Personal per = it.next();
                if (per.getIdPersonal() == person.getIdPersonal()) {
                    band = false;
                    break;
                }
            }
            if (band == true) {
                if (buscarpor.equals("Apellido") && person.getEstado() == true) {
                    int i = person.getApellido().indexOf(valor);
                    if (i == 0) {
                        Object[] fila = new Object[4];
                        fila[0] = person;
                        fila[1] = person.getDni();
                        fila[2] = person.getSexo();
                        fila[3] = person.getEstadoCivil();
                        model.addRow(fila);
                    }
                } else if (buscarpor.equals("Nombre") && person.getEstado() == true) {
                    int i = person.getNombre().indexOf(valor);
                    if (i == 0) {
                        Object[] fila = new Object[4];
                        fila[0] = person;
                        fila[1] = person.getDni();
                        fila[2] = person.getSexo();
                        fila[3] = person.getEstadoCivil();
                        model.addRow(fila);
                    }
                }
            }
        }
        tabla.setModel(model);
    }

    public boolean ControlarAnoLectivo(Date inicio,Date fin){
        boolean band=false;
        Anolectivo lec=getAnoLectivo();
        if(lec.getIdAnolectivo()!=null){
            if(lec.getInicio().compareTo(inicio)<=0 &&lec.getFin().compareTo(fin)>=0){
                band=true;
            }
        }
        return band;
    }
    
    public boolean ControlarAnoLectivo(Date fecha){
        boolean band=false;
        Anolectivo lec=getAnoLectivo();
        if(lec.getIdAnolectivo()!=null){
            if(lec.getInicio().compareTo(fecha)<=0 &&lec.getFin().compareTo(fecha)>=0){
                band=true;
            }
        }else{
            JOptionPane.showMessageDialog(null, "Primero debe configurar el año lectivo", "Configurar año lectivo", JOptionPane.ERROR_MESSAGE);
        }
        return band;
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
    
    public void CargarTablaLicencia(JTable Tabla, Personal per,Date fecha,String valor) {
        try {
            DefaultTableModel modelo = (DefaultTableModel) Tabla.getModel();
            //Personal ppp=col.getPersonal(per.getIdPersonal());
            Iterator<Licencia> rs = PERSISTENCIA.getLicencias(per.getIdPersonal()).iterator();
            while (rs.hasNext()) {
                Licencia lic = (Licencia) rs.next();
                if (valor.equals("TODO")) {
                    Object fila[] = new Object[4];
                    SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                    fila[0] = formateador.format(lic.getInicio());
                    fila[1] = formateador.format(lic.getFin());
                    fila[2] = lic;
                    fila[3] = lic.getArticulo();
                    modelo.addRow(fila);
                }else if (valor.equals("AÑO")) {
                    if(lic.getInicio().getYear()==fecha.getYear()||lic.getFin().getYear()==fecha.getYear()){
                        Object fila[] = new Object[4];
                        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                        fila[0] = formateador.format(lic.getInicio());
                        fila[1] = formateador.format(lic.getFin());
                        fila[2] = lic;
                        fila[3] = lic.getArticulo();
                        modelo.addRow(fila);
                    }
                }else if (valor.equals("MES")) {
                    if(lic.getInicio().getMonth()<=fecha.getMonth()&& lic.getFin().getMonth()>=fecha.getMonth()){
                        Object fila[] = new Object[4];
                        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                        fila[0] = formateador.format(lic.getInicio());
                        fila[1] = formateador.format(lic.getFin());
                        fila[2] = lic;
                        fila[3] = lic.getArticulo();
                        modelo.addRow(fila);
                    }
                }else if (valor.equals("DIA")) {
                    if(lic.getInicio().compareTo(fecha) <=0 && lic.getFin().compareTo(fecha) >=0){
                        Object fila[] = new Object[4];
                        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                        fila[0] = formateador.format(lic.getInicio());
                        fila[1] = formateador.format(lic.getFin());
                        fila[2] = lic;
                        fila[3] = lic.getArticulo();
                        modelo.addRow(fila);
                    }
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
            if (!tar.getTareaclases().isEmpty()) {
                if (tar.getEstado() == true) {
                    Object fila[] = new Object[7];
                    Iterator it = tar.getAgendas().iterator();
                    while (it.hasNext()) {
                        Agenda age = (Agenda) it.next();
                        Iterator ita = age.getAnos().iterator();
                        while (ita.hasNext()) {
                            Ano an = (Ano) ita.next();
                            Iterator itm = an.getMeses().iterator();
                            while (itm.hasNext()) {
                                Mes me = (Mes) itm.next();
                                Iterator itd = me.getDias().iterator();
                                while (itd.hasNext()) {
                                    Dia di = (Dia) itd.next();
                                    Iterator itini = di.getIniciofins().iterator();
                                    while (itini.hasNext()) {
                                        Iniciofin ini = (Iniciofin) itini.next();
                                        Tareaclase tarcla = tar.getTareaclases().iterator().next();
                                        Date fecha = new Date();
                                        fecha.setYear(an.getAno() - 1900);
                                        fecha.setMonth(me.getMes());
                                        fecha.setDate(di.getDia());
                                        fila[0] = age.getPersonal();
                                        fila[1] = age.getRevista().getNombre();
                                        fila[2] = tarcla.getAula().getNombre();
                                        fila[3] = tarcla.getAula().getNumero();
                                        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                                        SimpleDateFormat formateador2 = new SimpleDateFormat("HH:mm");
                                        fila[4] = formateador.format(fecha);
                                        fila[5] = formateador2.format(ini.getInicio());
                                        fila[6] = formateador2.format(ini.getFin());
                                        modelo.addRow(fila);
                                    }
                                }
                            }
                        }
                    }

                }
            } else if (!tar.getTareareunions().isEmpty()) {
                if (tar.getEstado() == true) {
                    Object fila[] = new Object[7];
                    Iterator it = tar.getAgendas().iterator();
                    while (it.hasNext()) {
                        Agenda age = (Agenda) it.next();
                        Iterator ita = age.getAnos().iterator();
                        while (ita.hasNext()) {
                            Ano an = (Ano) ita.next();
                            Iterator itm = an.getMeses().iterator();
                            while (itm.hasNext()) {
                                Mes me = (Mes) itm.next();
                                Iterator itd = me.getDias().iterator();
                                while (itd.hasNext()) {
                                    Dia di = (Dia) itd.next();
                                    Iterator itini = di.getIniciofins().iterator();
                                    while (itini.hasNext()) {
                                        Iniciofin ini = (Iniciofin) itini.next();
                                        Tareareunion tarreu = tar.getTareareunions().iterator().next();
                                        Date fecha = new Date();
                                        fecha.setYear(an.getAno() - 1900);
                                        fecha.setMonth(me.getMes());
                                        fecha.setDate(di.getDia());
                                        fila[0] = age.getPersonal();
                                        fila[1] = tarreu.getMotivo();
                                        fila[2] = tarreu.getCaracter();
                                        //fila[4] = 
                                        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                                        SimpleDateFormat formateador2 = new SimpleDateFormat("HH:mm");
                                        fila[4] = formateador.format(fecha);
                                        fila[5] = formateador2.format(ini.getInicio());
                                        fila[6] = formateador2.format(ini.getFin());
                                        modelo.addRow(fila);
                                    }
                                }
                            }
                        }
                    }

                }
            } else if (!tar.getTareaextracurriculars().isEmpty()) {
                if (tar.getEstado() == true) {
                    Object fila[] = new Object[7];
                    Iterator it = tar.getAgendas().iterator();
                    while (it.hasNext()) {
                        Agenda age = (Agenda) it.next();
                        fila[0] = age.getPersonal();
                        fila[1] = tar.getComentario();
                        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                        SimpleDateFormat formateador2 = new SimpleDateFormat("HH:mm");
                        Franco fran = age.getFranco(age);
                        if (fran.getIdFranco() != null) {
                            fila[3] = formateador.format(fran.getDiaFranco());
                        }
                        fila[3] = formateador.format(tar.getDiaFin());
                        fila[4] = formateador.format(tar.getDiaInicio());
                        fila[5] = formateador2.format(tar.getDiaInicio());
                        fila[6] = formateador2.format(tar.getDiaFin());
                        modelo.addRow(fila);
                    }
//                               }
//                           }
//                        }
//                    }
                }
            } else if (!tar.getTareaotros().isEmpty()) {
                if (tar.getEstado() == true) {
                    Object fila[] = new Object[7];
                    Iterator it = tar.getAgendas().iterator();
                    while (it.hasNext()) {
                        Agenda age = (Agenda) it.next();
                        Tareaotro tarreu = tar.getTareaotros().iterator().next();
                        //Date fecha = new Date();
                        fila[0] = age.getPersonal();
                        fila[1] = tar.getComentario();
                        fila[2] = tarreu.getCaracteristica();
                        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                        SimpleDateFormat formateador2 = new SimpleDateFormat("HH:mm");
                        fila[3] = formateador.format(tar.getDiaFin());
                        fila[4] = formateador.format(tar.getDiaInicio());
                        fila[5] = formateador2.format(tar.getDiaInicio());
                        fila[6] = formateador2.format(tar.getDiaFin());
                        modelo.addRow(fila);
                    }
                }
            }
            Tabla.setModel(modelo);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    public Agenda getAgenda(Tarea tar, int per) {
        Agenda band = new Agenda();
        Iterator it = PERSISTENCIA.getAgendas().iterator();
        while (it.hasNext()) {
            Agenda age = (Agenda) it.next();
            if (age.getId().getIdTarea() == tar.getIdTarea() && age.getId().getIdPersonal() == per) {
                band = age;
                break;
            }
        }
        return band;
    }

    public void CargarTablaInasistencias(JTable Tabla, String m, int ano) {
        try {
            DefaultTableModel modelo = (DefaultTableModel) Tabla.getModel();
//            Establecimiento col = getPrimerEstablecimiento();
            Controlador cc=new Controlador();
            int mes = ObtenerMes(m);
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
            Iterator ita = cc.PERSISTENCIA.ObtenerListaInasistencia(mes, ano).iterator();
            while (ita.hasNext()) {
                Asistencia asis =(Asistencia) ita.next();
                if (asis.getIniciofin().getDia().getMes().getAno().getAgenda().getTarea().getEstado() == true && asis.getIniciofin().getDia().getMes().getAno().getAgenda().getPersonal().getEstado() == true) {
                    Date fecha = new Date();
                    fecha.setYear(asis.getIniciofin().getDia().getMes().getAno().getAno() - 1900);
                    fecha.setMonth(asis.getIniciofin().getDia().getMes().getMes());
                    fecha.setDate(asis.getIniciofin().getDia().getDia());
                    if (asis.getIniciofin().getDia().getMes().getMes() == mes && asis.getIniciofin().getDia().getMes().getAno().getAno() == ano) {
                        Object fila[] = new Object[10];
                        fila[0] = asis.getIdAsistencia();
                        fila[1] = asis.getIniciofin().getDia().getMes().getAno().getAgenda().getTarea().getNombre();
                        fila[2] = asis.getIniciofin().getDia().getMes().getAno().getAgenda().getPersonal().toString();
                        fila[3] = formateador.format(fecha);
                        fila[4] = asis.getIniciofin().getInicio();
                        fila[5] = asis.getIniciofin().getFin();
                        fila[6] = new Boolean(false);
                        if (asis.getTardanza() == true) {
                            fila[7] = new Boolean(true);
                        } else {
                            fila[7] = new Boolean(false);
                        }
                        if (asis.getJustificacions().iterator().hasNext()) {
                            fila[8] = asis.getJustificacions().iterator().next().getArticulo();
                            fila[9] = asis.getJustificacions().iterator().next().getMotivo();
                        }
                        modelo.addRow(fila);
                    }
                }
            }
            Iterator<Asistencia> itaa = Controlador.getPERSISTENCIA().ObtenerListaTardanza(mes, ano).iterator();
            while (itaa.hasNext()) {
                Asistencia asis = itaa.next();
                if (asis.getIniciofin().getDia().getMes().getAno().getAgenda().getTarea().getEstado() == true && asis.getIniciofin().getDia().getMes().getAno().getAgenda().getPersonal().getEstado() == true) {
                    Date fecha = new Date();
                    fecha.setYear(asis.getIniciofin().getDia().getMes().getAno().getAno() - 1900);
                    fecha.setMonth(asis.getIniciofin().getDia().getMes().getMes());
                    fecha.setDate(asis.getIniciofin().getDia().getDia());
                    if (asis.getIniciofin().getDia().getMes().getMes() == mes && asis.getIniciofin().getDia().getMes().getAno().getAno() == ano) {
                        Object fila[] = new Object[10];
                        fila[0] = asis.getIdAsistencia();
                        fila[1] = asis.getIniciofin().getDia().getMes().getAno().getAgenda().getTarea().getNombre();
                        fila[2] = asis.getIniciofin().getDia().getMes().getAno().getAgenda().getPersonal().toString();
                        fila[3] = formateador.format(fecha);
                        fila[4] = asis.getIniciofin().getInicio();
                        fila[5] = asis.getIniciofin().getFin();
                        if (asis.getEstado() == true) {
                            fila[6] = new Boolean(true);
                        } else {
                            fila[6] = new Boolean(false);
                        }
                        if (asis.getTardanza() == true) {
                            fila[7] = new Boolean(true);
                        } else {
                            fila[7] = new Boolean(false);
                        }
                        if (asis.getJustificacions().iterator().hasNext()) {
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

    public void CargarTablaAsisteciaTarea(JTable Tabla,Tarea tar) {
        try {
            DefaultTableModel modelo = (DefaultTableModel) Tabla.getModel();
            Date hoy=new Date();
            Iterator it = PERSISTENCIA.getIniciofinTarea(tar.getIdTarea()).iterator();
            while (it.hasNext()) {
                Iniciofin in = (Iniciofin) it.next();
                Date fecha=new Date();
                fecha.setDate(in.getDia().getDia());
                fecha.setMonth(in.getDia().getMes().getMes());
                fecha.setYear(in.getDia().getMes().getAno().getAno()-1900);
                fecha.setHours(in.getFin().getHours());
                fecha.setMinutes(in.getFin().getMinutes());
                fecha.setSeconds(in.getFin().getSeconds());
                if(fecha.compareTo(hoy)<=0){
                    if (in.getAsistencias().iterator().hasNext()) {
                        Asistencia asis = in.getAsistencias().iterator().next();
                        Object fila[] = new Object[5];
                        fila[0] = in.getDia().getMes().getAno().getAgenda().getPersonal();
                        fila[1] = in.getDia().getMes().getAno().getAgenda().getPersonal().getDni();
                        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                        fila[2] = formateador.format(fecha);
                        if (asis.getEstado() == true) {
                            fila[3] = "SI";
                        } else {
                            fila[3] = "NO";
                        }
                        if (asis.getTardanza() == true) {
                            fila[4] = "SI";
                        } else {
                            fila[4] = "NO";
                        }
                        modelo.addRow(fila);
                    }
                }
            }
            Tabla.setModel(modelo);
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

    public static Date sumarFechasDias(Date fch, int dias) {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(fch.getTime());
        cal.add(Calendar.DATE, dias);
        return new Date(cal.getTimeInMillis());
    }
    
    public static Date restarFechasDias(Date fch, int dias) {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(fch.getTime());
        cal.add(Calendar.DATE, -dias);
        return new Date(cal.getTimeInMillis());
    }

    public Personal getPersonal(Tipodoc tipo, String dni) {
        Personal pe = new Personal();
        if (!PERSISTENCIA.existePersonal(tipo.getIdTipodoc(), dni).isEmpty()) {
            pe = (Personal) PERSISTENCIA.existePersonal(tipo.getIdTipodoc(), dni).get(0);
        }
        return pe;
    }

    public void CargarComboEstablecimientosPerso(JComboBox JCombo, Personal per, int ano) {
        LimpiarCombo(JCombo);
        if(ano!=0){
            Iterator it = PERSISTENCIA.getDecJuradaPersonal(per.getIdPersonal()).iterator();
            while(it.hasNext()){
                Declaracionjurada dec=(Declaracionjurada) it.next();
                if(dec.getAnolectivo().getAno()==ano){
                    Iterator<DetalleEstablecimiento> car = PERSISTENCIA.DetalledecjuradaPer(dec.getIdDeclaracionjurada()).iterator();
                    while (car.hasNext()) {
                        DetalleEstablecimiento ca = (DetalleEstablecimiento) car.next();
                        JCombo.addItem(ca);
                    }
                }
            }
        }
//        if (!PERSISTENCIA.DecjuradaPer(per.getIdPersonal()).isEmpty()) {
//            Declaracionjurada dec = (Declaracionjurada) PERSISTENCIA.DecjuradaPer(per.getIdPersonal()).get(0);
//            Iterator<DetalleEstablecimiento> car = PERSISTENCIA.DetalledecjuradaPer(dec.getIdDeclaracionjurada()).iterator();
//            while (car.hasNext()) {
//                DetalleEstablecimiento ca = (DetalleEstablecimiento) car.next();
//                JCombo.addItem(ca);
//            }
//        }
    }

    public String ObtenerDia(int i) {
        String dia = null;
        if (i == 0) {
            dia = "DOMINGO";
        } else if (i == 1) {
            dia = "LUNES";
        } else if (i == 2) {
            dia = "MARTES";
        } else if (i == 3) {
            dia = "MIERCOLES";
        } else if (i == 4) {
            dia = "JUEVES";
        } else if (i == 5) {
            dia = "VIERNES";
        } else if (i == 6) {
            dia = "SABADO";
        }
        return dia;
    }

    public int ObtenerMes(String mes) {
        int m = 0;
        if (mes.equals("ENERO")) {
            m = 0;
        } else if (mes.equals("FEBRERO")) {
            m = 1;
        } else if (mes.equals("MARZO")) {
            m = 2;
        } else if (mes.equals("ABRIL")) {
            m = 3;
        } else if (mes.equals("MAYO")) {
            m = 4;
        } else if (mes.equals("JUNIO")) {
            m = 5;
        } else if (mes.equals("JULIO")) {
            m = 6;
        } else if (mes.equals("AGOSTO")) {
            m = 7;
        } else if (mes.equals("SEPTIEMBRE")) {
            m = 8;
        } else if (mes.equals("OCTUBRE")) {
            m = 9;
        } else if (mes.equals("NOVIEMBRE")) {
            m = 10;
        } else if (mes.equals("DICIEMBRE")) {
            m = 11;
        }
        return m;
    }

    public int ObtenerInasistenciaPersonal(Personal per) {
        Date date = new Date();
        int contador = 0;
        Iterator<Asistencia> ita = Controlador.getPERSISTENCIA().ObtenerListaInasistencia(date.getMonth(), date.getYear() + 1900).iterator();
        while (ita.hasNext()) {
            Asistencia asis = ita.next();
            int id = asis.getIniciofin().getDia().getMes().getAno().getAgenda().getPersonal().getIdPersonal();
            int id2 = per.getIdPersonal();
            if (id == id2) {
                contador++;
            }
        }
        return contador;
    }

    public int ObtenerTardanzaPersonal(Personal per) {
        Date date = new Date();
        int contador = 0;
        Iterator<Asistencia> ita = Controlador.getPERSISTENCIA().ObtenerListaTardanza(date.getMonth(), date.getYear() + 1900).iterator();
        while (ita.hasNext()) {
            Asistencia asis = ita.next();
            int id = asis.getIniciofin().getDia().getMes().getAno().getAgenda().getPersonal().getIdPersonal();
            int id2 = per.getIdPersonal();
            if (id == id2) {
                contador++;
            }
        }
        return contador;
    }

    public boolean existeFeriado(Date unafecha) {
        boolean tmpres = false;
        Iterator it = PERSISTENCIA.getFeriados(unafecha.getYear()).iterator();
        while (it.hasNext()) {
            Feriado fer = (Feriado) it.next();
            if (fer.getDia().compareTo(unafecha) == 0) {
                tmpres = true;
                break;
            }
        }
        return tmpres;
    }

    public boolean VerificarCheckTabla(JTable tabla) {
        boolean band = false;
        int c = 0;
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        while (tabla.getRowCount() != c) {
            if (modelo.getValueAt(c, 0).equals(true)) {
                band = true;
                break;
            }
            c++;
        }
        return band;
    }
  
    public static void mostrarReporte(String report, List consulta, String titulo,int total) throws FileNotFoundException, JRException {
        String path = System.getProperty("user.dir") + "\\Reportes\\";
        String templateName = path + report + ".jrxml";
        HashMap parametros = new HashMap();
        parametros.clear();
        parametros.put("total", total);
        
        JasperReport jasper = JasperCompileManager.compileReport(templateName);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, parametros, new JRBeanCollectionDataSource(consulta));
        JasperViewer jviewer = new JasperViewer(jasperPrint, false);
        if(jasperPrint.getPages().iterator().hasNext()){
            jviewer.setTitle(titulo);
            jviewer.show();
        }
    }
    
    public static void mostrarReporte(String report, List consulta, String titulo, String label,int total) throws FileNotFoundException, JRException {
        String path = System.getProperty("user.dir") + "\\Reportes\\";
        String templateName = path + report + ".jrxml";
//        String templateName;
//        String templateName = "/reportes/"+report+".jrxml";
//        String templateName=null;
//        templateName = getClass().getResource("/reportes/"+report+".jrxml").getPath();
//        URL archivo = this.getClass().getResource("/reportes/rptHojaAsitencia.jasper");
        HashMap parametros = new HashMap();
        parametros.clear();
        parametros.put("Titulo", label);
        parametros.put("total", total);

        JasperReport jasper = JasperCompileManager.compileReport(templateName);
//        JasperReport jasper = (JasperReport) JRLoader.loadObject(templateName);
//        JasperReport jasperReport = (JasperReport)JRLoader.loadObject(archivo);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, parametros, new JRBeanCollectionDataSource(consulta));
        JasperViewer jviewer = new JasperViewer(jasperPrint, false);
        if(jasperPrint.getPages().iterator().hasNext()){
            jviewer.setTitle(titulo);
            jviewer.show();
        }
        
    }
    
    public static void mostrarReporte(String report, List consulta, String titulo, String filtro1,String filtro2,int total) throws FileNotFoundException, JRException {
        String path = System.getProperty("user.dir") + "\\Reportes\\";
        String templateName = path + report + ".jrxml";
        HashMap parametros = new HashMap();
        parametros.clear();
        parametros.put("total", total);
        parametros.put("filtro1", filtro1);
        parametros.put("filtro2", filtro2);

        JasperReport jasper = JasperCompileManager.compileReport(templateName);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, parametros, new JRBeanCollectionDataSource(consulta));
        JasperViewer jviewer = new JasperViewer(jasperPrint, false);
        if(jasperPrint.getPages().iterator().hasNext()){
            jviewer.setTitle(titulo);
            jviewer.show();
        }
        
    }
    public void VerificarCircular(Personal per, Date hoy) {
        boolean band=false;
        Iterator it = per.getCircularpersonals().iterator();
        while (it.hasNext()) {
            Circularpersonal cirper = (Circularpersonal) it.next();
            if(!cirper.getEstado()){
                band=true;
                break;
            }
        }
        if(band==true){
            JOptionPane.showMessageDialog(null, "Usted tiene circulares que aún no ha leido","Leer Circulares",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public Circular VerificarCircular2(Personal per, Date hoy) {
        Circular circ = new Circular();
        Iterator it = per.getCircularpersonals().iterator();
        while (it.hasNext()) {
            Circularpersonal cirper = (Circularpersonal) it.next();
            Circular cir = cirper.getCircular();
            if(!cirper.getEstado()){
                
            }
        }
        return circ;
    }
}
