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
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
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
    
//    public boolean BackupDB(/*String dbName, String dbUserName, String dbPassword, String path*/) {
//        
//        String rut = System.getProperty("user.home") + System.getProperty("file.separator") + "Desktop";
//        String command = "C:\\Program Files (x86)\\MySQL\\MySQL Server 5.1\\bin\\mysqldump -h localhost  -u root -p root database asistencia -r "+rut+"";
//        Process runtimeProcess;
//        try {
//            runtimeProcess = Runtime.getRuntime().exec(command);
//            int processComplete = runtimeProcess.waitFor();
//            
//            if (processComplete == 0) {
//                System.out.println("Backup created successfully");
//                return true;
//            } else {
//                System.out.println("Could not create the backup");
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return false;
//    }
//    
//    public void transfer(InputStream input, OutputStream output) throws Exception {
//        byte[] buf = new byte[1024];
//        int len;
//        while ((len = input.read(buf)) > 0) {
//            output.write(buf, 0, len);
//        }
//        input.close();
//        output.close();
//    }

//    public boolean BackupDB2(/*String dbName, String dbUserName, String dbPassword, String path*/) throws IOException, Exception {
//        try {
//            String rut = System.getProperty("user.home") + System.getProperty("file.separator") + "Desktop";
//            String command = "C:\\Program Files (x86)\\MySQL\\MySQL Server 5.1\\bin\\mysqldump -h localhost  -u root -p root database asistencia -r "+rut+"";
//
//            java.lang.Process child = Runtime.getRuntime().exec(command);
//            InputStream input = child.getInputStream();
//
//            FileOutputStream output = new FileOutputStream(""+rut+"\\back.sql");
//            transfer(input, output);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        //////////////////////////////////////
//        String executeCmd = "C:\\Program Files (x86)\\MySQL\\MySQL Server 5.1\\bin\\mysqldump --opt --password=root --user=root --databases asistencia";
//        Process runtimeProcess;
//        try {
//            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
//            int processComplete = runtimeProcess.waitFor();
//            if (processComplete == 0) {
//                System.out.println("Backup created successfully");
//                return true;
//            } else {
//                System.out.println("Could not create the backup");
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return false;
//    }
    
    
    private int BUFFER = 10485760;  
    //para guardar en memmoria
    private StringBuffer temp = null;
    //para guardar el archivo SQL
    private FileWriter  fichero = null;
    private PrintWriter pw = null;
    
    public boolean CrearBackup(/*String host, String port, String user, String password, String db, String file_backup*/) {
        boolean ok = false;
        try {
            //sentencia para crear el BackUp
            Process run = Runtime.getRuntime().exec(
                    "mysqldump --host=" + "localhost" + " --port=" + "3306"
                    + " --user=" + "root" + " --password=" + "root"
                    + " --compact --complete-insert --extended-insert --skip-quote-names"
                    + " --skip-comments --skip-triggers " + "asistencia");
            //se guarda en memoria el backup
            InputStream in = run.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            temp = new StringBuffer();
            int count;
            char[] cbuf = new char[BUFFER];
            while ((count = br.read(cbuf, 0, BUFFER)) != -1) {
                temp.append(cbuf, 0, count);
            }
            br.close();
            in.close();
            /* se crea y escribe el archivo SQL */
            String rut = System.getProperty("user.home") + System.getProperty("file.separator") + "Desktop";
            fichero = new FileWriter(""+rut+"\\back2.sql");
            pw = new PrintWriter(fichero);
            pw.println(temp.toString());
            ok = true;
            JOptionPane.showMessageDialog(null, "ok");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return ok;
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
                lista.add(per.getIdPersonal());

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
                            fila[0] = ac.getTarea();
                            fila[1] = ac.getTarea().getLugar();
                            fila[2] = ac.getTarea().getComentario();
                            model.addRow(fila);
                        }
                    } else if (buscarpor.equals("Lugar") && ac.getTarea().getEstado() == true) {
                        int i = ac.getTarea().getLugar().getNombre().indexOf(valor);
                        if (i == 0) {
                            Object[] fila = new Object[3];
                            fila[0] = ac.getTarea();
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
                            fila[0] = ac.getTarea();
                            fila[1] = ac.getTarea().getLugar();
                            fila[2] = ac.getTarea().getComentario();
                            model.addRow(fila);
                        }
                    } else if (buscarpor.equals("Lugar") && ac.getTarea().getEstado() == true) {
                        int i = ac.getTarea().getLugar().getNombre().indexOf(valor);
                        if (i == 0) {
                            Object[] fila = new Object[3];
                            fila[0] = ac.getTarea();
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
                            fila[0] = acc.getTarea();
                            fila[1] = ac.getLugar();
                            fila[2] = ac.getComentario();
                            model.addRow(fila);
                        }
                    } else if (buscarpor.equals("Lugar") && ac.getEstado() == true) {
                        int i = ac.getLugar().getNombre().indexOf(valor);
                        if (i == 0) {
                            Object[] fila = new Object[3];
                            fila[0] = acc.getTarea();
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
                            fila[0] = acc.getTarea();
                            fila[1] = ac.getLugar();
                            fila[2] = ac.getComentario();
                            model.addRow(fila);
                        }
                    } else if (buscarpor.equals("Lugar") && ac.getEstado() == true) {
                        int i = ac.getLugar().getNombre().indexOf(valor);
                        if (i == 0) {
                            Object[] fila = new Object[3];
                            fila[0] = acc.getTarea();
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
            if(cir.getInicio().compareTo(cir.getFin())==0){
                if (cir.getInicio().compareTo(dia)<=0) {
                    Object[] fila = new Object[4];
                    fila[0] = cir;
                    SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                    fila[1] = formateador.format(cir.getInicio());
                    fila[2] = formateador.format(cir.getFin());
                    fila[3] = cir.getFirma();
                    model.addRow(fila);
                }
            }else{
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
    
    public void CargarTablaHistorialPer(JTable tablapersonal, Date fecha, String valor, String filtro) {
        List listap = new ArrayList();
        DefaultTableModel model = (DefaultTableModel) tablapersonal.getModel();
        if (valor.equals("AÑO")) {
            Iterator it = PERSISTENCIA.getAuditoriaPerAno(fecha.getYear() + 1900).iterator();
            while (it.hasNext()) {
                Auditoria audi = (Auditoria) it.next();
                Personal per=audi.getPersonalByIdPersonal();
                if(!listap.contains(per)){
                    listap.add(per);
                    if(filtro.equals("Todos")){
                        Object[] fila = new Object[6];
                        fila[0] = per;
                        fila[1] = per.getDni();
                        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                        fila[2] = formateador.format(per.getIngreso());
                        fila[3] = per.getCorreoElectronico();
                        fila[4] = audi.getOperacion();
                        SimpleDateFormat formateador2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                        fila[5] = formateador2.format(audi.getFecha());
                        model.addRow(fila);
                    }else if(filtro.equals("Activos")){
                        if(!PERSISTENCIA.getAuditoriaPerInactivos(per.getIdPersonal()).iterator().hasNext()){
                            Object[] fila = new Object[6];
                            fila[0] = per;
                            fila[1] = per.getDni();
                            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                            fila[2] = formateador.format(per.getIngreso());
                            fila[3] = per.getCorreoElectronico();
                            fila[4] = audi.getOperacion();
                            SimpleDateFormat formateador2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                            fila[5] = formateador2.format(audi.getFecha());
                            model.addRow(fila);
                        }
                    }else if(filtro.equals("Inactivos")){
                        Iterator i= PERSISTENCIA.getAuditoriaPerInactivos(per.getIdPersonal()).iterator();
                        if(i.hasNext()){
                            Auditoria a= (Auditoria) i.next();
                            Object[] fila = new Object[6];
                            fila[0] = per;
                            fila[1] = per.getDni();
                            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                            fila[2] = formateador.format(per.getIngreso());
                            fila[3] = per.getCorreoElectronico();
                            fila[4] = a.getOperacion();
                            SimpleDateFormat formateador2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                            fila[5] = formateador2.format(a.getFecha());
                            model.addRow(fila);
                        }
                    }
                }
            }
        }else if(valor.equals("MES")){
            Iterator it = PERSISTENCIA.getAuditoriaPerMes(fecha.getYear() + 1900,fecha.getMonth()+1).iterator();
            while (it.hasNext()) {
                Auditoria audi = (Auditoria) it.next();
                Personal per=audi.getPersonalByIdPersonal();
                if(!listap.contains(per)){
                    listap.add(per);
                    if(filtro.equals("Todos")){
                        Object[] fila = new Object[6];
                        fila[0] = per;
                        fila[1] = per.getDni();
                        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                        fila[2] = formateador.format(per.getIngreso());
                        fila[3] = per.getCorreoElectronico();
                        fila[4] = audi.getOperacion();
                        SimpleDateFormat formateador2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                        fila[5] = formateador2.format(audi.getFecha());
                        model.addRow(fila);
                    }else if(filtro.equals("Activos")){
                        if(!PERSISTENCIA.getAuditoriaPerInactivos(per.getIdPersonal()).iterator().hasNext()){
                            Object[] fila = new Object[6];
                            fila[0] = per;
                            fila[1] = per.getDni();
                            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                            fila[2] = formateador.format(per.getIngreso());
                            fila[3] = per.getCorreoElectronico();
                            fila[4] = audi.getOperacion();
                            SimpleDateFormat formateador2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                            fila[5] = formateador2.format(audi.getFecha());
                            model.addRow(fila);
                        }
                    }else if(filtro.equals("Inactivo")){
                        Iterator i= PERSISTENCIA.getAuditoriaPerInactivos(per.getIdPersonal()).iterator();
                        if(i.hasNext()){
                            Auditoria a= (Auditoria) i.next();
                            Object[] fila = new Object[6];
                            fila[0] = per;
                            fila[1] = per.getDni();
                            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                            fila[2] = formateador.format(per.getIngreso());
                            fila[3] = per.getCorreoElectronico();
                            fila[4] = a.getOperacion();
                            SimpleDateFormat formateador2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                            fila[5] = formateador2.format(a.getFecha());
                            model.addRow(fila);
                        }
                    }
                }
            }
        }else if(valor.equals("DIA")){
            Iterator it = PERSISTENCIA.getAuditoriaPerDia(fecha.getYear() + 1900,fecha.getMonth()+1,fecha.getDate()).iterator();
            while (it.hasNext()) {
                Auditoria audi = (Auditoria) it.next();
                Personal per=audi.getPersonalByIdPersonal();
                if(!listap.contains(per)){
                    listap.add(per);
                    if(filtro.equals("Todos")){
                        Object[] fila = new Object[6];
                        fila[0] = per;
                        fila[1] = per.getDni();
                        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                        fila[2] = formateador.format(per.getIngreso());
                        fila[3] = per.getCorreoElectronico();
                        fila[4] = audi.getOperacion();
                        SimpleDateFormat formateador2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                        fila[5] = formateador2.format(audi.getFecha());
                        model.addRow(fila);
                    }else if(filtro.equals("Activos")){
                        if(!PERSISTENCIA.getAuditoriaPerInactivos(per.getIdPersonal()).iterator().hasNext()){
                            Object[] fila = new Object[6];
                            fila[0] = per;
                            fila[1] = per.getDni();
                            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                            fila[2] = formateador.format(per.getIngreso());
                            fila[3] = per.getCorreoElectronico();
                            fila[4] = audi.getOperacion();
                            SimpleDateFormat formateador2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                            fila[5] = formateador2.format(audi.getFecha());
                            model.addRow(fila);
                        }
                    }else if(filtro.equals("Inactivo")){
                        Iterator i= PERSISTENCIA.getAuditoriaPerInactivos(per.getIdPersonal()).iterator();
                        if(i.hasNext()){
                            Auditoria a= (Auditoria) i.next();
                            Object[] fila = new Object[6];
                            fila[0] = per;
                            fila[1] = per.getDni();
                            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                            fila[2] = formateador.format(per.getIngreso());
                            fila[3] = per.getCorreoElectronico();
                            fila[4] = a.getOperacion();
                            SimpleDateFormat formateador2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                            fila[5] = formateador2.format(a.getFecha());
                            model.addRow(fila);
                        }
                    }
                }
            }
        }
        tablapersonal.setModel(model);
    }
    
    public void CargarTablaHistorialTar(Date fecha, String valor, String filtro,JTable tablaclases) {
        List listat= new ArrayList();
        DefaultTableModel modell = (DefaultTableModel) tablaclases.getModel();
        if (valor.equals("AÑO")) {
            Iterator it = PERSISTENCIA.getAuditoriaTarAno(fecha.getYear() + 1900).iterator();
            while (it.hasNext()) {
                Auditoria audi = (Auditoria) it.next();
                Tarea tar=audi.getTarea();
                if(!listat.contains(tar)){
                    listat.add(tar);
                    if(filtro.equals("Todos")){
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
                    }else if(filtro.equals("Activos")){
                        if(!PERSISTENCIA.getAuditoriaTareaInactivos(tar.getIdTarea()).iterator().hasNext()){
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
                    }else if(filtro.equals("Inactivos")){
                        Iterator i = PERSISTENCIA.getAuditoriaTareaInactivos(tar.getIdTarea()).iterator();
                        if(i.hasNext()){
                            Auditoria a = (Auditoria) i.next();
                            Object[] fila = new Object[6];
                            fila[0] = tar;
                            fila[1] = tar.getLugar();
                            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                            fila[2] = formateador.format(tar.getDiaInicio());
                            fila[3] = formateador.format(tar.getDiaFin());
                            fila[4] = a.getOperacion();
                            SimpleDateFormat formateador2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                            fila[5] = formateador2.format(a.getFecha());
                            modell.addRow(fila);
                        }
                    }
                }
            }
        }else if (valor.equals("MES")) {
            Iterator it = PERSISTENCIA.getAuditoriaTarMes(fecha.getYear() + 1900,fecha.getMonth()+1).iterator();
            while (it.hasNext()) {
                Auditoria audi = (Auditoria) it.next();
                Tarea tar=audi.getTarea();
                if(!listat.contains(tar)){
                    listat.add(tar);
                    if(filtro.equals("Todos")){
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
                    }else if(filtro.equals("Activos")){
                        if(!PERSISTENCIA.getAuditoriaTareaInactivos(tar.getIdTarea()).iterator().hasNext()){
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
                    }else if(filtro.equals("Inactivo")){
                        Iterator i = PERSISTENCIA.getAuditoriaTareaInactivos(tar.getIdTarea()).iterator();
                        if(i.hasNext()){
                            Auditoria a = (Auditoria) i.next();
                            Object[] fila = new Object[6];
                            fila[0] = tar;
                            fila[1] = tar.getLugar();
                            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                            fila[2] = formateador.format(tar.getDiaInicio());
                            fila[3] = formateador.format(tar.getDiaFin());
                            fila[4] = a.getOperacion();
                            SimpleDateFormat formateador2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                            fila[5] = formateador2.format(a.getFecha());
                            modell.addRow(fila);
                        }
                    }
                }
            }
        }else if (valor.equals("DIA")) {
            Iterator it = PERSISTENCIA.getAuditoriaTarDia(fecha.getYear() + 1900,fecha.getMonth()+1,fecha.getDate()).iterator();
            while (it.hasNext()) {
                Auditoria audi = (Auditoria) it.next();
                Tarea tar=audi.getTarea();
                if(!listat.contains(tar)){
                    listat.add(tar);
                    if(filtro.equals("Todos")){
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
                    }else if(filtro.equals("Activos")){
                        if(!PERSISTENCIA.getAuditoriaTareaInactivos(tar.getIdTarea()).iterator().hasNext()){
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
                    }else if(filtro.equals("Inactivo")){
                        Iterator i = PERSISTENCIA.getAuditoriaTareaInactivos(tar.getIdTarea()).iterator();
                        if(i.hasNext()){
                            Auditoria a = (Auditoria) i.next();
                            Object[] fila = new Object[6];
                            fila[0] = tar;
                            fila[1] = tar.getLugar();
                            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                            fila[2] = formateador.format(tar.getDiaInicio());
                            fila[3] = formateador.format(tar.getDiaFin());
                            fila[4] = a.getOperacion();
                            SimpleDateFormat formateador2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                            fila[5] = formateador2.format(a.getFecha());
                            modell.addRow(fila);
                        }
                    }
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
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
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
    
    public void CargarTablaOutHome(JTable tabla/*, Personal per*/,Date fecha){
        LimpiarTabla(tabla);
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        List lista=new ArrayList();
        int i=fecha.getDay();
        String dia=ObtenerDia(i);
        Iterator it=PERSISTENCIA.getOutHomeActivos(dia).iterator();
        while(it.hasNext()){
            ActivoIniciofin in=(ActivoIniciofin) it.next();
            Date inicio= new Date();
//            inicio=fecha;
            inicio.setHours(in.getInicio().getHours());
            inicio.setMinutes(in.getInicio().getMinutes());
            inicio.setSeconds(in.getInicio().getSeconds());
            Date fin= new Date();
//            fin=fecha;
            fin.setHours(in.getFin().getHours());
            fin.setMinutes(in.getFin().getMinutes());
            fin.setSeconds(in.getFin().getSeconds());
            Personal per=in.getActivo().getNivel().getCargo().getDetalleEstablecimiento().getDeclaracionjurada().getPersonal();
            if(fecha.compareTo(inicio)>=0 && fecha.compareTo(fin)<=0){
                if(!lista.contains(per)){
                    lista.add(per);
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
        java.sql.Date sqldate = new java.sql.Date(fecha.getTime());
        Iterator itt=PERSISTENCIA.getOutHomeInactivos(sqldate).iterator();
        while(itt.hasNext()){
            Inactivo in=(Inactivo) itt.next();
            Personal per=in.getNivel().getCargo().getDetalleEstablecimiento().getDeclaracionjurada().getPersonal();
            if(!lista.contains(per)){
                lista.add(per);
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
    
    public void CargarTablaOutHomePer(JTable tabla, Personal per,Date fecha){
        LimpiarTabla(tabla);
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        int i=fecha.getDay();
        String dia=ObtenerDia(i);
        Iterator it=PERSISTENCIA.getOutHomeActivoPer(dia, per.getIdPersonal()).iterator();
        while(it.hasNext()){
            ActivoIniciofin in=(ActivoIniciofin) it.next();
            Object[] fila = new Object[6];
            fila[0] = in.getActivo().getNivel().getCargo().getDetalleEstablecimiento().getEstablecimiento();
            fila[1] = in.getActivo().getNivel().getCargo();
            fila[2] = in.getActivo().getNivel();
            fila[3] = "ACTIVO";
            SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
            fila[4] = formateador.format(in.getInicio());
            fila[5] = formateador.format(in.getFin());
            model.addRow(fila);
        }
        java.sql.Date sqldate = new java.sql.Date(fecha.getTime());
        Iterator itt=PERSISTENCIA.getOutHomeInactivoPer(sqldate, per.getIdPersonal()).iterator();
        while(itt.hasNext()){
            Inactivo in=(Inactivo) itt.next();
            Object[] fila = new Object[6];
            fila[0] = in.getNivel().getCargo().getDetalleEstablecimiento().getEstablecimiento();
            fila[1] = in.getNivel().getCargo();
            fila[2] = in.getNivel();
            fila[3] = "INACTIVO";
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
            fila[4] = formateador.format(in.getFechaInicio());
            fila[5] = formateador.format(in.getFechaFin());
            model.addRow(fila);
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
    
//    public void CargarTablacheck(JTable tabla, String buscarpor, String valor, List personales) {
//        try {
//            DefaultTableModel model = (DefaultTableModel) tabla.getModel();
//            if (!personales.isEmpty()) {
//                //Cargar lista de checkeados
//                Iterator<Personal> it = getPrimerEstablecimiento().getPersonals().iterator();
//                while (it.hasNext()) {
//                    Personal person = (Personal) it.next();
//                    Iterator<Personal> itt = personales.iterator();
//                    while (itt.hasNext()) {
//                        Personal per = itt.next();
//                        if (per.getIdPersonal() == person.getIdPersonal()) {
//                            Object fila[] = new Object[3];
//                            fila[0] = new Boolean(true);
//                            fila[1] = person;
//                            fila[2] = person.getDni();
//                            model.addRow(fila);
//                        }
//                    }
//                }
//            }
//            boolean band = true;
//            Iterator<Personal> ite = getPrimerEstablecimiento().getPersonals().iterator();
//            while (ite.hasNext()) {
//                band = true;
//                Personal person = (Personal) ite.next();
//                //No cargar la lista de personales checkeados
//                if (!personales.isEmpty()) {
//                    Iterator<Personal> iter = personales.iterator();
//                    while (iter.hasNext()) {
//                        Personal per = iter.next();
//                        if (per.getIdPersonal() == person.getIdPersonal()) {
//                            band = false;
//                            break;
//                        }
//                    }
//                }
//                if (band == true) {
//                    //Cargar resto de personales
//                    boolean band2 = false;
//                    if (!buscarpor.equals("TODOS")) {
//                        Iterator it = person.getPersonalDepartamentos().iterator();
//                        while (it.hasNext()) {
//                            PersonalDepartamento perd = (PersonalDepartamento) it.next();
//                            if (perd.getDepartamento().getNombre().equals(buscarpor)) {
//                                band2 = true;
//                            }
//                        }
//                    } else {
//                        band2 = true;
//                    }
//
//                    if (person.getEstado() == true && band2 == true) {
//                        int i = person.getApellido().indexOf(valor);
//                        int e = person.getNombre().indexOf(valor);
//                        if (i == 0 || e == 0) {
//                            Object fila[] = new Object[3];
//                            fila[0] = new Boolean(false);
//                            fila[1] = person;
//                            fila[2] = person.getDni();
//                            model.addRow(fila);
//                        }
//                    }
//                    tabla.setModel(model);
//                }
//            }
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(null, ex.toString());
//        }
//    }
    
    public void CargarTablacheck2(JTable tabla, String buscarpor, String valor, List personales) {
        try {
            DefaultTableModel model = (DefaultTableModel) tabla.getModel();
            if (!personales.isEmpty()) {
                //Cargar lista de checkeados
                Iterator it = PERSISTENCIA.getPersonalesTrue(1).iterator();
                while (it.hasNext()) {
                    Personal person = (Personal) it.next();
                    if (personales.contains(person.getIdPersonal())) {
                        Object fila[] = new Object[3];
                        fila[0] = new Boolean(true);
                        fila[1] = person;
                        fila[2] = person.getDni();
                        model.addRow(fila);
                    }
                }
            }
            Iterator ite = PERSISTENCIA.getPersonalesTrue(1).iterator();
            while (ite.hasNext()) {
                Personal per = (Personal) ite.next();
                if (!personales.contains(per.getIdPersonal())) {
                    if (buscarpor.equals("TODOS")) {
                        int i = per.getApellido().indexOf(valor);
                        int e = per.getNombre().indexOf(valor);
                        if (i == 0 || e == 0) {
                            Object fila[] = new Object[3];
                            fila[0] = new Boolean(false);
                            fila[1] = per;
                            fila[2] = per.getDni();
                            model.addRow(fila);
                        }
                    } else {
                        Iterator it = per.getPersonalDepartamentos().iterator();
                        while (it.hasNext()) {
                            PersonalDepartamento perd = (PersonalDepartamento) it.next();
                            if (perd.getDepartamento().getNombre().equals(buscarpor)) {
                                Object fila[] = new Object[3];
                                fila[0] = new Boolean(false);
                                fila[1] = per;
                                fila[2] = per.getDni();
                                model.addRow(fila);
                            }
                        }
                    }
                }
            }
            tabla.setModel(model);
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
            Iterator<PersonalFamiliar> rs = PERSISTENCIA.getFamiliaresPersonal(per.getIdPersonal()).iterator();
            while (rs.hasNext()) {
                PersonalFamiliar perfam = (PersonalFamiliar) rs.next();
                    Object fila[] = new Object[6];
                    fila[0] = perfam;
                    fila[1] = perfam.getPersonalByIdFamiliar().getTipodoc().getTipodoc();
                    fila[2] = perfam.getPersonalByIdFamiliar().getDni();
                    fila[3] = perfam.getTiporelacion().getRelacion();
                    SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                    fila[4] = formateador.format(perfam.getPersonalByIdFamiliar().getFechaNac());
                    Boolean asig = perfam.getAsignacionFamiliar().booleanValue();
                    if (asig == true) {
                        fila[5] = "Si";
                    } else if (asig == false) {
                        fila[5] = "No";
                    }
                    modelo.addRow(fila);
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
    
     
     public PersonalFamiliar getPersonalFamiliar(Personal per, Personal otro) {
        PersonalFamiliar bandera = new PersonalFamiliar();
        Iterator<PersonalFamiliar> it = per.getPersonalFamiliarsForIdPersonal().iterator();
        while (it.hasNext()) {
            PersonalFamiliar perfam = (PersonalFamiliar) it.next();
            if (perfam.getId().getIdFamiliar() == otro.getIdPersonal()) {
                bandera = perfam;
                break;
            }
        }
        return bandera;
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
            LimpiarTabla(Tabla);
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
    
//    public boolean ControlarDNI(String dni){
//        boolean ban=true;
//        Iterator it=PERSISTENCIA.getPersonal(dni).iterator();
//        if(it.hasNext()){
//            ban=false;
//        }
//        return ban;
//    }
    
    public Boolean ControlFamiliar(Personal pe, Tiporelacion rel) {
        Boolean ban = true;
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
        return ban;
    }
    
    public Boolean ControlarDeclaración(Personal pe, String dia, ActivoIniciofin ac) {
        Boolean ban = false;
        Iterator it=PERSISTENCIA.getAgendasClases(pe.getIdPersonal()).iterator();
        while(it.hasNext()){
            Agenda age=(Agenda) it.next();
            String ff=age.getTarea().getNombre();
            String hh=ff;
            Date aux=new Date();
            Iterator ita=age.getAnos().iterator();
            while(ita.hasNext()){
                Ano an=(Ano) ita.next();
                aux.setYear(an.getAno()-1900);
                Iterator itm=an.getMeses().iterator();
                while(itm.hasNext()){
                    Mes me=(Mes) itm.next();
                    aux.setMonth(me.getMes());
                    Iterator itd=me.getDias().iterator();
                    while(itd.hasNext()){
                        Dia di=(Dia) itd.next();
                        aux.setDate(di.getDia());
                        int y=aux.getDay();
                        String dd= ObtenerDia(y);
                        if(dd.equals(dia)){
                            Iterator iti=di.getIniciofins().iterator();
                            while(iti.hasNext()){
                                Iniciofin ini=(Iniciofin) iti.next();
                                if(ini.getInicio().compareTo(ac.getInicio())<=0 && ini.getFin().compareTo(ac.getInicio())>=0){
                                    JOptionPane.showMessageDialog(null, "Verifique los horarios porque el personal tiene "+age.getTarea().toString(),"Registrar Activo",JOptionPane.ERROR_MESSAGE);
                                    ban=true;
                                    return ban;
                                }else if(ini.getInicio().compareTo(ac.getFin())<=0 && ini.getFin().compareTo(ac.getFin())>=0){
                                    JOptionPane.showMessageDialog(null, "Verifique los horarios porque el personal tiene "+age.getTarea().toString(),"Registrar Activo",JOptionPane.ERROR_MESSAGE);
                                    ban=true;
                                    return ban;
                                }else if(ini.getInicio().compareTo(ac.getInicio())<=0 && ini.getFin().compareTo(ac.getFin())>=0){
                                    JOptionPane.showMessageDialog(null, "Verifique los horarios porque el personal tiene "+age.getTarea().toString(),"Registrar Activo",JOptionPane.ERROR_MESSAGE);
                                    ban=true;
                                    return ban;
                                }else if(ini.getInicio().compareTo(ac.getInicio())>=0 && ini.getFin().compareTo(ac.getFin())<=0){
                                    JOptionPane.showMessageDialog(null, "Verifique los horarios porque el personal tiene "+age.getTarea().toString(),"Registrar Activo",JOptionPane.ERROR_MESSAGE);
                                    ban=true;
                                    return ban;
                                }
                            }
                        }
                    }
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
        Iterator it=PERSISTENCIA.existePersonal(tipo.getIdTipodoc(), dni).iterator();
        if (it.hasNext()) {
            pe = (Personal) it.next();
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
    
    public String ObtenerMes(int mes) {
        String m = null;
        if (mes==0) {
            m = "ENERO";
        } else if (mes==1) {
            m = "FEBRERO";
        } else if (mes==2) {
            m = "MARZO";
        } else if (mes==3) {
            m = "ABRIL";
        } else if (mes==4) {
            m = "MAYO";
        } else if (mes==5) {
            m = "JUNIO";
        } else if (mes==6) {
            m = "JULIO";
        } else if (mes==7) {
            m = "AGOSTO";
        } else if (mes==8) {
            m = "SEPTIEMBRE";
        } else if (mes==9) {
            m = "OCTUBRE";
        } else if (mes==10) {
            m = "NOVIEMBRE";
        } else if (mes==11) {
            m = "DICIEMBRE";
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
    
    public void BorrarCircularpersonales(Circular cir){
        Iterator it = PERSISTENCIA.getCircularPersonales(cir.getIdCircular()).iterator();
        while(it.hasNext()){
            Circularpersonal cirper=(Circularpersonal) it.next();
            cirper.eliminarCircularpersonal(cirper);
        }
    }
    
    public boolean DisponibilidadAula(Date inici, Date finn, HashMap inic, HashMap dsem, int id, Aula au) {
        boolean a = true;
        Date ot1 = inici;
        if (dsem.containsValue("LUNES")) {
            while (ot1.getDay() != 1) {
                ot1 = Controlador.sumarFechasDias(ot1, 1);
            }
            Date otro = ot1;
            Iniciofin ini = (Iniciofin) inic.get(1);
            // <editor-fold defaultstate="collapsed" desc="Controlar aula">
            Iterator i = PERSISTENCIA.getTareaclase(au.getIdAula()).iterator();
            while (i.hasNext()) {
                Tareaclase tarcla = (Tareaclase) i.next();
                Tarea tar = tarcla.getTarea();
                otro=ot1;
                while (otro.compareTo(finn) <= 0) {
                    if (!PERSISTENCIA.getAgendasTar(tar.getIdTarea()).isEmpty()) {
                        Iterator it = PERSISTENCIA.getAgendasTar(tar.getIdTarea()).iterator();
                        while (it.hasNext()) {
                            Agenda age = (Agenda) it.next();
                            if (age.getPersonal().getEstado() == true && age.getTarea().getEstado() == true && age.getTarea().getIdTarea() != id) {
                                if (age.getTarea().getTareaclases().iterator().hasNext()) {
                                    Dia di = age.getDia2(otro);
                                    if (di.getIdDia() != null) {
                                        Iterator itin = di.getIniciofins().iterator();
                                        while (itin.hasNext()) {
                                            Iniciofin in = (Iniciofin) itin.next();
                                            Date ii=ini.getInicio();
                                            Date iii=ini.getFin();
                                            if (ini.getInicio().compareTo(in.getInicio()) <= 0 && ini.getFin().compareTo(in.getInicio()) >= 0) {
                                                a = false;
                                                break;
                                            } else if (ini.getInicio().compareTo(in.getFin()) <= 0 && ini.getFin().compareTo(in.getFin()) >= 0) {
                                                a = false;
                                                break;
                                            } else if (ini.getInicio().compareTo(in.getInicio()) >= 0 && ini.getFin().compareTo(in.getInicio()) >= 0 && ini.getInicio().compareTo(in.getFin()) <= 0 && ini.getFin().compareTo(in.getFin()) <= 0) {
                                                a = false;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    otro = Controlador.sumarFechasDias(otro, 7);
                }
            }
            // </editor-fold>
            
        }
        Date ot2 = inici;
        if (dsem.containsValue("MARTES")) {
            while (ot2.getDay() != 2) {
                ot2 = Controlador.sumarFechasDias(ot2, 1);
            }
            Date otro = ot2;
            Iniciofin ini = (Iniciofin) inic.get(2);
            // <editor-fold defaultstate="collapsed" desc="Controlar aula">
            Iterator i = PERSISTENCIA.getTareaclase(au.getIdAula()).iterator();
            while (i.hasNext()) {
                Tareaclase tarcla = (Tareaclase) i.next();
                Tarea tar = tarcla.getTarea();
                otro=ot2;
                while (otro.compareTo(finn) <= 0) {
                    if (!PERSISTENCIA.getAgendasTar(tar.getIdTarea()).isEmpty()) {
                        Iterator it = PERSISTENCIA.getAgendasTar(tar.getIdTarea()).iterator();
                        while (it.hasNext()) {
                            Agenda age = (Agenda) it.next();
                            if (age.getPersonal().getEstado() == true && age.getTarea().getEstado() == true && age.getTarea().getIdTarea() != id) {
                                if (age.getTarea().getTareaclases().iterator().hasNext()) {
                                    Dia di = age.getDia2(otro);
                                    if (di.getIdDia() != null) {
                                        Iterator itin = di.getIniciofins().iterator();
                                        while (itin.hasNext()) {
                                            Iniciofin in = (Iniciofin) itin.next();
                                            if (ini.getInicio().compareTo(in.getInicio()) <= 0 && ini.getFin().compareTo(in.getInicio()) >= 0) {
                                                a = false;
                                                break;
                                            } else if (ini.getInicio().compareTo(in.getFin()) <= 0 && ini.getFin().compareTo(in.getFin()) >= 0) {
                                                a = false;
                                                break;
                                            } else if (ini.getInicio().compareTo(in.getInicio()) >= 0 && ini.getFin().compareTo(in.getInicio()) >= 0 && ini.getInicio().compareTo(in.getFin()) <= 0 && ini.getFin().compareTo(in.getFin()) <= 0) {
                                                a = false;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    otro = Controlador.sumarFechasDias(otro, 7);
                }
            }
            // </editor-fold>

        }
        Date ot3 = inici;
        if (dsem.containsValue("MIERCOLES")) {
            while (ot3.getDay() != 3) {
                ot3 = Controlador.sumarFechasDias(ot3, 1);
            }
            Date otro = ot3;
            Iniciofin ini = (Iniciofin) inic.get(3);
            // <editor-fold defaultstate="collapsed" desc="Controlar aula">
            Iterator i = PERSISTENCIA.getTareaclase(au.getIdAula()).iterator();
            while (i.hasNext()) {
                Tareaclase tarcla = (Tareaclase) i.next();
                Tarea tar = tarcla.getTarea();
                otro=ot3;
                while (otro.compareTo(finn) <= 0) {
                    if (!PERSISTENCIA.getAgendasTar(tar.getIdTarea()).isEmpty()) {
                        Iterator it = PERSISTENCIA.getAgendasTar(tar.getIdTarea()).iterator();
                        while (it.hasNext()) {
                            Agenda age = (Agenda) it.next();
                            if (age.getPersonal().getEstado() == true && age.getTarea().getEstado() == true && age.getTarea().getIdTarea() != id) {
                                if (age.getTarea().getTareaclases().iterator().hasNext()) {
                                    Dia di = age.getDia2(otro);
                                    if (di.getIdDia() != null) {
                                        Iterator itin = di.getIniciofins().iterator();
                                        while (itin.hasNext()) {
                                            Iniciofin in = (Iniciofin) itin.next();
                                            if (ini.getInicio().compareTo(in.getInicio()) <= 0 && ini.getFin().compareTo(in.getInicio()) >= 0) {
                                                a = false;
                                                break;
                                            } else if (ini.getInicio().compareTo(in.getFin()) <= 0 && ini.getFin().compareTo(in.getFin()) >= 0) {
                                                a = false;
                                                break;
                                            } else if (ini.getInicio().compareTo(in.getInicio()) >= 0 && ini.getFin().compareTo(in.getInicio()) >= 0 && ini.getInicio().compareTo(in.getFin()) <= 0 && ini.getFin().compareTo(in.getFin()) <= 0) {
                                                a = false;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    otro = Controlador.sumarFechasDias(otro, 7);
                }
            }
            // </editor-fold>

        }
        Date ot4 = inici;
        if (dsem.containsValue("JUEVES")) {
            while (ot4.getDay() != 4) {
                ot4 = Controlador.sumarFechasDias(ot4, 1);
            }
            Date otro = ot4;
            Iniciofin ini = (Iniciofin) inic.get(4);
            // <editor-fold defaultstate="collapsed" desc="Controlar aula">
            Iterator i = PERSISTENCIA.getTareaclase(au.getIdAula()).iterator();
            while (i.hasNext()) {
                Tareaclase tarcla = (Tareaclase) i.next();
                Tarea tar = tarcla.getTarea();
                otro=ot4;
                while (otro.compareTo(finn) <= 0) {
                    if (!PERSISTENCIA.getAgendasTar(tar.getIdTarea()).isEmpty()) {
                        Iterator it = PERSISTENCIA.getAgendasTar(tar.getIdTarea()).iterator();
                        while (it.hasNext()) {
                            Agenda age = (Agenda) it.next();
                            if (age.getPersonal().getEstado() == true && age.getTarea().getEstado() == true && age.getTarea().getIdTarea() != id) {
                                if (age.getTarea().getTareaclases().iterator().hasNext()) {
                                    Dia di = age.getDia2(otro);
                                    if (di.getIdDia() != null) {
                                        Iterator itin = di.getIniciofins().iterator();
                                        while (itin.hasNext()) {
                                            Iniciofin in = (Iniciofin) itin.next();
                                            if (ini.getInicio().compareTo(in.getInicio()) <= 0 && ini.getFin().compareTo(in.getInicio()) >= 0) {
                                                a = false;
                                                break;
                                            } else if (ini.getInicio().compareTo(in.getFin()) <= 0 && ini.getFin().compareTo(in.getFin()) >= 0) {
                                                a = false;
                                                break;
                                            } else if (ini.getInicio().compareTo(in.getInicio()) >= 0 && ini.getFin().compareTo(in.getInicio()) >= 0 && ini.getInicio().compareTo(in.getFin()) <= 0 && ini.getFin().compareTo(in.getFin()) <= 0) {
                                                a = false;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    otro = Controlador.sumarFechasDias(otro, 7);
                }
            }
            // </editor-fold>

        }
        Date ot5 = inici;
        if (dsem.containsValue("VIERNES")) {
            while (ot5.getDay() != 5) {
                ot5 = Controlador.sumarFechasDias(ot5, 1);
            }
            Date otro = ot5;
            Iniciofin ini = (Iniciofin) inic.get(5);
            // <editor-fold defaultstate="collapsed" desc="Controlar aula">
            Iterator i = PERSISTENCIA.getTareaclase(au.getIdAula()).iterator();
            while (i.hasNext()) {
                Tareaclase tarcla = (Tareaclase) i.next();
                Tarea tar = tarcla.getTarea();
                otro=ot5;
                while (otro.compareTo(finn) <= 0) {
                    if (!PERSISTENCIA.getAgendasTar(tar.getIdTarea()).isEmpty()) {
                        Iterator it = PERSISTENCIA.getAgendasTar(tar.getIdTarea()).iterator();
                        while (it.hasNext()) {
                            Agenda age = (Agenda) it.next();
                            if (age.getPersonal().getEstado() == true && age.getTarea().getEstado() == true && age.getTarea().getIdTarea() != id) {
                                if (age.getTarea().getTareaclases().iterator().hasNext()) {
                                    Dia di = age.getDia2(otro);
                                    if (di.getIdDia() != null) {
                                        Iterator itin = di.getIniciofins().iterator();
                                        while (itin.hasNext()) {
                                            Iniciofin in = (Iniciofin) itin.next();
                                            if (ini.getInicio().compareTo(in.getInicio()) <= 0 && ini.getFin().compareTo(in.getInicio()) >= 0) {
                                                a = false;
                                                break;
                                            } else if (ini.getInicio().compareTo(in.getFin()) <= 0 && ini.getFin().compareTo(in.getFin()) >= 0) {
                                                a = false;
                                                break;
                                            } else if (ini.getInicio().compareTo(in.getInicio()) >= 0 && ini.getFin().compareTo(in.getInicio()) >= 0 && ini.getInicio().compareTo(in.getFin()) <= 0 && ini.getFin().compareTo(in.getFin()) <= 0) {
                                                a = false;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    otro = Controlador.sumarFechasDias(otro, 7);
                }
            }
            // </editor-fold>

        }
        Date ot6 = inici;
        if (dsem.containsValue("SABADO")) {
            while (ot6.getDay() != 6) {
                ot6 = Controlador.sumarFechasDias(ot6, 1);
            }
            Date otro = ot6;
            Iniciofin ini = (Iniciofin) inic.get(6);
            // <editor-fold defaultstate="collapsed" desc="Controlar aula">
            Iterator i = PERSISTENCIA.getTareaclase(au.getIdAula()).iterator();
            while (i.hasNext()) {
                Tareaclase tarcla = (Tareaclase) i.next();
                Tarea tar = tarcla.getTarea();
                otro=ot2;
                while (otro.compareTo(finn) <= 0) {
                    if (!PERSISTENCIA.getAgendasTar(tar.getIdTarea()).isEmpty()) {
                        Iterator it = PERSISTENCIA.getAgendasTar(tar.getIdTarea()).iterator();
                        while (it.hasNext()) {
                            Agenda age = (Agenda) it.next();
                            if (age.getPersonal().getEstado() == true && age.getTarea().getEstado() == true && age.getTarea().getIdTarea() != id) {
                                if (age.getTarea().getTareaclases().iterator().hasNext()) {
                                    Dia di = age.getDia2(otro);
                                    if (di.getIdDia() != null) {
                                        Iterator itin = di.getIniciofins().iterator();
                                        while (itin.hasNext()) {
                                            Iniciofin in = (Iniciofin) itin.next();
                                            if (ini.getInicio().compareTo(in.getInicio()) <= 0 && ini.getFin().compareTo(in.getInicio()) >= 0) {
                                                a = false;
                                                break;
                                            } else if (ini.getInicio().compareTo(in.getFin()) <= 0 && ini.getFin().compareTo(in.getFin()) >= 0) {
                                                a = false;
                                                break;
                                            } else if (ini.getInicio().compareTo(in.getInicio()) >= 0 && ini.getFin().compareTo(in.getInicio()) >= 0 && ini.getInicio().compareTo(in.getFin()) <= 0 && ini.getFin().compareTo(in.getFin()) <= 0) {
                                                a = false;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    otro = Controlador.sumarFechasDias(otro, 7);
                }
            }
            // </editor-fold>

        }

        return a;
    }
    
    //CLASES
    // <editor-fold defaultstate="collapsed" desc="Clase"> 
    public boolean DisponibilidadClase(Personal per,Date inici, Date finn, HashMap inic,HashMap dsem, int id){
        boolean a=true;
//        int d=0;
        Date ot1 = inici;
        if (dsem.containsValue("LUNES")) {
            while (ot1.getDay() != 1) {
                ot1 = Controlador.sumarFechasDias(ot1, 1);
            }
             Date otro = ot1;
             Iniciofin ini= (Iniciofin) inic.get(1);
            // <editor-fold defaultstate="collapsed" desc="Guarda meses y dias">
        while (otro.compareTo(finn) <= 0) {
            if (!PERSISTENCIA.getAgendas(per.getIdPersonal()).isEmpty()) {
                Iterator it = PERSISTENCIA.getAgendas(per.getIdPersonal()).iterator();
                while (it.hasNext()) {
                    Agenda age = (Agenda) it.next();
                    if (age.getPersonal().getEstado() == true && age.getTarea().getEstado() == true && age.getTarea().getIdTarea()!=id) {
                        if (age.getTarea().getTareaclases().iterator().hasNext()) {
                            Dia di = age.getDia2(otro);
                            if (di.getIdDia() != null) {
                                Iterator itin = di.getIniciofins().iterator();
                                while (itin.hasNext()) {
                                    Iniciofin in = (Iniciofin) itin.next();
                                    if (ini.getInicio().compareTo(in.getInicio()) <= 0 && ini.getFin().compareTo(in.getInicio()) >= 0) {
                                        a=false;
                                        break;
                                    } else if (ini.getInicio().compareTo(in.getFin()) <= 0 && ini.getFin().compareTo(in.getFin()) >= 0) {
                                        a=false;
                                        break;
                                    } else if (ini.getInicio().compareTo(in.getInicio()) >= 0 && ini.getFin().compareTo(in.getInicio()) >= 0 && ini.getInicio().compareTo(in.getFin()) <= 0 && ini.getFin().compareTo(in.getFin()) <= 0) {
                                        a=false;
                                        break;
                                    }
                                }
                            }
                        } 
                    }
                }
            }
            if (!per.getDeclaracionjuradas().isEmpty()) {
                Iterator itact = per.getDeclaracionjuradas().iterator().next().ObtenerActivos("LUNES").iterator();
                while (itact.hasNext()) {
                    Activo act = (Activo) itact.next();
                    Iterator itin = act.getActivoIniciofins().iterator();
                    while (itin.hasNext()) {
                        ActivoIniciofin in = (ActivoIniciofin) itin.next();
                        if (ini.getInicio().compareTo(in.getInicio()) <= 0 && ini.getFin().compareTo(in.getInicio()) >= 0) {
                            a=false;
                            break;
                        } else if (ini.getInicio().compareTo(in.getFin()) <= 0 && ini.getFin().compareTo(in.getFin()) >= 0) {
                            a=false;
                            break;
                        } else if (ini.getInicio().compareTo(in.getInicio()) >= 0 && ini.getFin().compareTo(in.getInicio()) >= 0 && ini.getInicio().compareTo(in.getFin()) <= 0 && ini.getFin().compareTo(in.getFin()) <= 0) {
                            a=false;
                            break;
                        }
                    }
                }
            }
            otro = Controlador.sumarFechasDias(otro, 7);
        }
        // </editor-fold>
        
        }
        Date ot2 = inici;
        if (dsem.containsValue("MARTES")) {
            while (ot2.getDay() != 2) {
                ot2 = Controlador.sumarFechasDias(ot2, 1);
            }
             Date otro = ot2;
             Iniciofin ini=  (Iniciofin) inic.get(2);
            // <editor-fold defaultstate="collapsed" desc="Guardar meses y dias">
        while (otro.compareTo(finn) <= 0) {
            if (!PERSISTENCIA.getAgendas(per.getIdPersonal()).isEmpty()) {
                Iterator it = PERSISTENCIA.getAgendas(per.getIdPersonal()).iterator();
                while (it.hasNext()) {
                    Agenda age = (Agenda) it.next();
                    if (age.getPersonal().getEstado() == true && age.getTarea().getEstado() == true &&age.getTarea().getIdTarea()!=id) {
                        if (age.getTarea().getTareaclases().iterator().hasNext()) {
                            Dia di = age.getDia2(otro);
                            if (di.getIdDia() != null) {
                                Iterator itin = di.getIniciofins().iterator();
                                while (itin.hasNext()) {
                                    Iniciofin in = (Iniciofin) itin.next();
                                    if (ini.getInicio().compareTo(in.getInicio()) <= 0 && ini.getFin().compareTo(in.getInicio()) >= 0) {
                                        a=false;
                                        break;
                                    } else if (ini.getInicio().compareTo(in.getFin()) <= 0 && ini.getFin().compareTo(in.getFin()) >= 0) {
                                        a=false;
                                        break;
                                    } else if (ini.getInicio().compareTo(in.getInicio()) >= 0 && ini.getFin().compareTo(in.getInicio()) >= 0 && ini.getInicio().compareTo(in.getFin()) <= 0 && ini.getFin().compareTo(in.getFin()) <= 0) {
                                        a=false;
                                        break;
                                    }
                                }
                            }
                        } 
                    }
                }
            }
            if (!per.getDeclaracionjuradas().isEmpty()) {
                Iterator itact = per.getDeclaracionjuradas().iterator().next().ObtenerActivos("LUNES").iterator();
                while (itact.hasNext()) {
                    Activo act = (Activo) itact.next();
                    Iterator itin = act.getActivoIniciofins().iterator();
                    while (itin.hasNext()) {
                        ActivoIniciofin in = (ActivoIniciofin) itin.next();
                        if (ini.getInicio().compareTo(in.getInicio()) <= 0 && ini.getFin().compareTo(in.getInicio()) >= 0) {
                            a=false;
                            break;
                        } else if (ini.getInicio().compareTo(in.getFin()) <= 0 && ini.getFin().compareTo(in.getFin()) >= 0) {
                            a=false;
                            break;
                        } else if (ini.getInicio().compareTo(in.getInicio()) >= 0 && ini.getFin().compareTo(in.getInicio()) >= 0 && ini.getInicio().compareTo(in.getFin()) <= 0 && ini.getFin().compareTo(in.getFin()) <= 0) {
                            a=false;
                            break;
                        }
                    }
                }
            }
            otro = Controlador.sumarFechasDias(otro, 7);
        }
        // </editor-fold>
        }
        Date ot3 = inici;
        if (dsem.containsValue("MIERCOLES")) {
            while (ot3.getDay() != 3) {
                ot3 = Controlador.sumarFechasDias(ot3, 1);
            }
             Date otro = ot3;
             Iniciofin ini= (Iniciofin) inic.get(3);
            // <editor-fold defaultstate="collapsed" desc="Guarda meses y dias">
        while (otro.compareTo(finn) <= 0) {
            if (!PERSISTENCIA.getAgendas(per.getIdPersonal()).isEmpty()) {
                Iterator it = PERSISTENCIA.getAgendas(per.getIdPersonal()).iterator();
                while (it.hasNext()) {
                    Agenda age = (Agenda) it.next();
                    if (age.getPersonal().getEstado() == true && age.getTarea().getEstado() == true && age.getTarea().getIdTarea()!=id) {
                        if (age.getTarea().getTareaclases().iterator().hasNext()) {
                            Dia di = age.getDia2(otro);
                            if (di.getIdDia() != null) {
                                Iterator itin = di.getIniciofins().iterator();
                                while (itin.hasNext()) {
                                    Iniciofin in = (Iniciofin) itin.next();
                                    if (ini.getInicio().compareTo(in.getInicio()) <= 0 && ini.getFin().compareTo(in.getInicio()) >= 0) {
                                        a=false;
                                        break;
                                    } else if (ini.getInicio().compareTo(in.getFin()) <= 0 && ini.getFin().compareTo(in.getFin()) >= 0) {
                                        a=false;
                                        break;
                                    } else if (ini.getInicio().compareTo(in.getInicio()) >= 0 && ini.getFin().compareTo(in.getInicio()) >= 0 && ini.getInicio().compareTo(in.getFin()) <= 0 && ini.getFin().compareTo(in.getFin()) <= 0) {
                                        a=false;
                                        break;
                                    }
                                }
                            }
                        } 
                    }
                }
            }
            if (!per.getDeclaracionjuradas().isEmpty()) {
                Iterator itact = per.getDeclaracionjuradas().iterator().next().ObtenerActivos("LUNES").iterator();
                while (itact.hasNext()) {
                    Activo act = (Activo) itact.next();
                    Iterator itin = act.getActivoIniciofins().iterator();
                    while (itin.hasNext()) {
                        ActivoIniciofin in = (ActivoIniciofin) itin.next();
                        if (ini.getInicio().compareTo(in.getInicio()) <= 0 && ini.getFin().compareTo(in.getInicio()) >= 0) {
                            a=false;
                            break;
                        } else if (ini.getInicio().compareTo(in.getFin()) <= 0 && ini.getFin().compareTo(in.getFin()) >= 0) {
                            a=false;
                            break;
                        } else if (ini.getInicio().compareTo(in.getInicio()) >= 0 && ini.getFin().compareTo(in.getInicio()) >= 0 && ini.getInicio().compareTo(in.getFin()) <= 0 && ini.getFin().compareTo(in.getFin()) <= 0) {
                            a=false;
                            break;
                        }
                    }
                }
            }
            otro = Controlador.sumarFechasDias(otro, 7);
        }
        // </editor-fold>
        
        }
        Date ot4 = inici;
        if (dsem.containsValue("JUEVES")) {
            while (ot4.getDay() != 4) {
                ot4= Controlador.sumarFechasDias(ot4, 1);
            }
             Date otro = ot4;
             Iniciofin ini= (Iniciofin) inic.get(4);
            // <editor-fold defaultstate="collapsed" desc="Guarda meses y dias">
        while (otro.compareTo(finn) <= 0) {
            if (!PERSISTENCIA.getAgendas(per.getIdPersonal()).isEmpty()) {
                Iterator it = PERSISTENCIA.getAgendas(per.getIdPersonal()).iterator();
                while (it.hasNext()) {
                    Agenda age = (Agenda) it.next();
                    if (age.getPersonal().getEstado() == true && age.getTarea().getEstado() == true && age.getTarea().getIdTarea()!=id) {
                        if (age.getTarea().getTareaclases().iterator().hasNext()) {
                            Dia di = age.getDia2(otro);
                            if (di.getIdDia() != null) {
                                Iterator itin = di.getIniciofins().iterator();
                                while (itin.hasNext()) {
                                    Iniciofin in = (Iniciofin) itin.next();
                                    if (ini.getInicio().compareTo(in.getInicio()) <= 0 && ini.getFin().compareTo(in.getInicio()) >= 0) {
                                        a=false;
                                        break;
                                    } else if (ini.getInicio().compareTo(in.getFin()) <= 0 && ini.getFin().compareTo(in.getFin()) >= 0) {
                                        a=false;
                                        break;
                                    } else if (ini.getInicio().compareTo(in.getInicio()) >= 0 && ini.getFin().compareTo(in.getInicio()) >= 0 && ini.getInicio().compareTo(in.getFin()) <= 0 && ini.getFin().compareTo(in.getFin()) <= 0) {
                                        a=false;
                                        break;
                                    }
                                }
                            }
                        } 
                    }
                }
            }
            if (!per.getDeclaracionjuradas().isEmpty()) {
                Iterator itact = per.getDeclaracionjuradas().iterator().next().ObtenerActivos("LUNES").iterator();
                while (itact.hasNext()) {
                    Activo act = (Activo) itact.next();
                    Iterator itin = act.getActivoIniciofins().iterator();
                    while (itin.hasNext()) {
                        ActivoIniciofin in = (ActivoIniciofin) itin.next();
                        if (ini.getInicio().compareTo(in.getInicio()) <= 0 && ini.getFin().compareTo(in.getInicio()) >= 0) {
                            a=false;
                            break;
                        } else if (ini.getInicio().compareTo(in.getFin()) <= 0 && ini.getFin().compareTo(in.getFin()) >= 0) {
                            a=false;
                            break;
                        } else if (ini.getInicio().compareTo(in.getInicio()) >= 0 && ini.getFin().compareTo(in.getInicio()) >= 0 && ini.getInicio().compareTo(in.getFin()) <= 0 && ini.getFin().compareTo(in.getFin()) <= 0) {
                            a=false;
                            break;
                        }
                    }
                }
            }
            otro = Controlador.sumarFechasDias(otro, 7);
        }
        // </editor-fold>
        
        }
        Date ot5 = inici;
        if (dsem.containsValue("VIERNES")) {
            while (ot5.getDay() != 5) {
                ot5 = Controlador.sumarFechasDias(ot5, 1);
            }
             Date otro = ot5;
             Iniciofin ini= (Iniciofin) inic.get(5);
             // <editor-fold defaultstate="collapsed" desc="Guarda meses y dias">
        while (otro.compareTo(finn) <= 0) {
            if (!PERSISTENCIA.getAgendas(per.getIdPersonal()).isEmpty()) {
                Iterator it = PERSISTENCIA.getAgendas(per.getIdPersonal()).iterator();
                while (it.hasNext()) {
                    Agenda age = (Agenda) it.next();
                    if (age.getPersonal().getEstado() == true && age.getTarea().getEstado() == true && age.getTarea().getIdTarea()!=id) {
                        if (age.getTarea().getTareaclases().iterator().hasNext()) {
                            Dia di = age.getDia2(otro);
                            if (di.getIdDia() != null) {
                                Iterator itin = di.getIniciofins().iterator();
                                while (itin.hasNext()) {
                                    Iniciofin in = (Iniciofin) itin.next();
                                    if (ini.getInicio().compareTo(in.getInicio()) <= 0 && ini.getFin().compareTo(in.getInicio()) >= 0) {
                                        a=false;
                                        break;
                                    } else if (ini.getInicio().compareTo(in.getFin()) <= 0 && ini.getFin().compareTo(in.getFin()) >= 0) {
                                        a=false;
                                        break;
                                    } else if (ini.getInicio().compareTo(in.getInicio()) >= 0 && ini.getFin().compareTo(in.getInicio()) >= 0 && ini.getInicio().compareTo(in.getFin()) <= 0 && ini.getFin().compareTo(in.getFin()) <= 0) {
                                        a=false;
                                        break;
                                    }
                                }
                            }
                        } 
                    }
                }
            }
            if (!per.getDeclaracionjuradas().isEmpty()) {
                Iterator itact = per.getDeclaracionjuradas().iterator().next().ObtenerActivos("LUNES").iterator();
                while (itact.hasNext()) {
                    Activo act = (Activo) itact.next();
                    Iterator itin = act.getActivoIniciofins().iterator();
                    while (itin.hasNext()) {
                        ActivoIniciofin in = (ActivoIniciofin) itin.next();
                        if (ini.getInicio().compareTo(in.getInicio()) <= 0 && ini.getFin().compareTo(in.getInicio()) >= 0) {
                            a=false;
                            break;
                        } else if (ini.getInicio().compareTo(in.getFin()) <= 0 && ini.getFin().compareTo(in.getFin()) >= 0) {
                            a=false;
                            break;
                        } else if (ini.getInicio().compareTo(in.getInicio()) >= 0 && ini.getFin().compareTo(in.getInicio()) >= 0 && ini.getInicio().compareTo(in.getFin()) <= 0 && ini.getFin().compareTo(in.getFin()) <= 0) {
                            a=false;
                            break;
                        }
                    }
                }
            }
            otro = Controlador.sumarFechasDias(otro, 7);
        }
        // </editor-fold>
        
        }
        Date ot6 = inici;
        if (dsem.containsValue("SABADO")) {
            while (ot6.getDay() != 6) {
                ot6 = Controlador.sumarFechasDias(ot6, 1);
            }
             Date otro = ot6;
             Iniciofin ini= (Iniciofin) inic.get(6);
             // <editor-fold defaultstate="collapsed" desc="Guarda meses y dias">
        while (otro.compareTo(finn) <= 0) {
            if (!PERSISTENCIA.getAgendas(per.getIdPersonal()).isEmpty()) {
                Iterator it = PERSISTENCIA.getAgendas(per.getIdPersonal()).iterator();
                while (it.hasNext()) {
                    Agenda age = (Agenda) it.next();
                    if (age.getPersonal().getEstado() == true && age.getTarea().getEstado() == true && age.getTarea().getIdTarea()!=id) {
                        if (age.getTarea().getTareaclases().iterator().hasNext()) {
                            Dia di = age.getDia2(otro);
                            if (di.getIdDia() != null) {
                                Iterator itin = di.getIniciofins().iterator();
                                while (itin.hasNext()) {
                                    Iniciofin in = (Iniciofin) itin.next();
                                    if (ini.getInicio().compareTo(in.getInicio()) <= 0 && ini.getFin().compareTo(in.getInicio()) >= 0) {
                                        a=false;
                                        break;
                                    } else if (ini.getInicio().compareTo(in.getFin()) <= 0 && ini.getFin().compareTo(in.getFin()) >= 0) {
                                        a=false;
                                        break;
                                    } else if (ini.getInicio().compareTo(in.getInicio()) >= 0 && ini.getFin().compareTo(in.getInicio()) >= 0 && ini.getInicio().compareTo(in.getFin()) <= 0 && ini.getFin().compareTo(in.getFin()) <= 0) {
                                        a=false;
                                        break;
                                    }
                                }
                            }
                        } 
                    }
                }
            }
            if (!per.getDeclaracionjuradas().isEmpty()) {
                Iterator itact = per.getDeclaracionjuradas().iterator().next().ObtenerActivos("LUNES").iterator();
                while (itact.hasNext()) {
                    Activo act = (Activo) itact.next();
                    Iterator itin = act.getActivoIniciofins().iterator();
                    while (itin.hasNext()) {
                        ActivoIniciofin in = (ActivoIniciofin) itin.next();
                        if (ini.getInicio().compareTo(in.getInicio()) <= 0 && ini.getFin().compareTo(in.getInicio()) >= 0) {
                            a=false;
                            break;
                        } else if (ini.getInicio().compareTo(in.getFin()) <= 0 && ini.getFin().compareTo(in.getFin()) >= 0) {
                            a=false;
                            break;
                        } else if (ini.getInicio().compareTo(in.getInicio()) >= 0 && ini.getFin().compareTo(in.getInicio()) >= 0 && ini.getInicio().compareTo(in.getFin()) <= 0 && ini.getFin().compareTo(in.getFin()) <= 0) {
                            a=false;
                            break;
                        }
                    }
                }
            }
            otro = Controlador.sumarFechasDias(otro, 7);
        }
        // </editor-fold>
        
        }

        return a;
    }
    //     </editor-fold>
    
        ///REUNIONES
    // <editor-fold defaultstate="collapsed" desc="REUNIONES"> 
    public int ReunionVerificarDisponibilidad (Personal per,Date diaini,Date hini,Date hfin, boolean control, int id){
        int band=1;
        try {
            if (!PERSISTENCIA.getAgendas(per.getIdPersonal()).isEmpty()) {
                Iterator it = PERSISTENCIA.getAgendas(per.getIdPersonal()).iterator();
                while (it.hasNext()) {
                    Agenda age = (Agenda) it.next();
                    if (age.getPersonal().getEstado() == true && age.getTarea().getEstado() == true && age.getTarea().getIdTarea()!=id) {
                        if(age.getTarea().getTareaotros().iterator().hasNext()){
//                            Tareaotro tarot=age.getTarea().getTareaotros().iterator().next();
                            Date inicio=age.getTarea().getDiaInicio();
                            Date fin=age.getTarea().getDiaFin();
                            if(diaini.compareTo(inicio)>=0 && diaini.compareTo(fin)<=0){
                                Date hinicio = age.getTarea().getDiaInicio();
                                Date hhfin = age.getTarea().getDiaFin();
                                if(hini.compareTo(hinicio)<=0&&hfin.compareTo(hinicio)>=0){
                                    band = 1;
                                    if(control==true){JOptionPane.showMessageDialog(null, "El personal "+ per.toString() +" tiene otra tarea no obligatoria", "Registrar Reunión", JOptionPane.INFORMATION_MESSAGE);}
                                    //return band;
                                }else if(hini.compareTo(hhfin)<=0&&hfin.compareTo(hhfin)>=0){
                                    band = 1;
                                    if(control==true){JOptionPane.showMessageDialog(null, "El personal "+ per.toString() +" tiene otra tarea no obligatoria", "Registrar Reunión", JOptionPane.INFORMATION_MESSAGE);}
                                    //return band;
                                }else if(hini.compareTo(hinicio)>=0&&hfin.compareTo(hinicio)>=0&&hini.compareTo(hhfin)<=0&&hfin.compareTo(hhfin)<=0){
                                    band = 1;
                                    if(control==true){JOptionPane.showMessageDialog(null, "El personal "+ per.toString() +" tiene otra tarea no obligatoria", "Registrar Reunión", JOptionPane.INFORMATION_MESSAGE);}
                                    //return band;
                                }
                            }
                        }else if(age.getTarea().getTareaextracurriculars().iterator().hasNext()){
//                            Tareaextracurricular tarot=age.getTarea().getTareaextracurriculars().iterator().next();
                            Date inicio=age.getTarea().getDiaInicio();
                            Date fin=age.getTarea().getDiaFin();
                            hini.setYear(diaini.getYear());
                            hini.setMonth(diaini.getMonth());
                            hini.setDate(diaini.getDate());
                            hfin.setYear(diaini.getYear());
                            hfin.setMonth(diaini.getMonth());
                            hfin.setDate(diaini.getDate());
                            if(hini.compareTo(inicio)<=0&&hfin.compareTo(inicio)>=0){
                                band = 0;
                                if(control==true){JOptionPane.showMessageDialog(null, "No existe disponibilidad de horario para "+ per.toString() +" porque existe una tarea extracurricular a ese horario", "Registrar Reunión", JOptionPane.ERROR_MESSAGE);}
                                return band;
                            }else if(hini.compareTo(fin)<=0&&hfin.compareTo(fin)>=0){
                                band = 0;
                                if(control==true){JOptionPane.showMessageDialog(null, "No existe disponibilidad de horario para "+ per.toString() +" porque existe una tarea extracurricular a ese horario", "Registrar Reunión", JOptionPane.ERROR_MESSAGE);}
                                return band;
                            }else if(hini.compareTo(inicio)>=0&&hfin.compareTo(inicio)>=0&&hini.compareTo(fin)<=0&&hfin.compareTo(fin)<=0){
                                band = 0;
                                if(control==true){JOptionPane.showMessageDialog(null, "No existe disponibilidad de horario para "+ per.toString() +" porque existe una tarea extracurricular a ese horario", "Registrar Reunión", JOptionPane.ERROR_MESSAGE);}
                                return band;
                            }else if(hini.compareTo(inicio)<=0&&hfin.compareTo(fin)>=0){
                                band = 0;
                                if(control==true){JOptionPane.showMessageDialog(null, "No existe disponibilidad de horario para "+ per.toString() +" porque existe una tarea extracurricular a ese horario", "Registrar Reunión", JOptionPane.ERROR_MESSAGE);}
                                return band;
                            }
                        }else if(age.getTarea().getTareaclases().iterator().hasNext()){
                            Dia di = age.getDia2(diaini);
                            if (di.getIdDia() != null) {
                                Iterator itin = di.getIniciofins().iterator();
                                while (itin.hasNext()) {
                                    Iniciofin in = (Iniciofin) itin.next();
                                    SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                                    String i=formateador.format(in.getInicio());
                                    String ii=formateador.format(in.getFin());
                                    Date inicio = formateador.parse(i);
                                    Date fin = formateador.parse(ii);
                                    if(hini.compareTo(inicio)<=0&&hfin.compareTo(inicio)>=0){
                                        if(control==true){
                                            JOptionPane.showMessageDialog(null, "El personal "+ per.toString() +" continuará con su clase luego de realizar la reunión", "Registrar Reunión", JOptionPane.INFORMATION_MESSAGE);
                                            band = 1;
                                            //return band;
                                        }
                                        in.setEstadoInicio(true);
                                        in.actualizarIniciofin(in);
                                    }else if(hini.compareTo(fin)<=0&&hfin.compareTo(fin)>=0){
                                        if(control==true){
                                            JOptionPane.showMessageDialog(null, "El personal "+ per.toString() +" suspenderá su clase para realizar la reunión", "Registrar Reunión", JOptionPane.INFORMATION_MESSAGE);
                                            band = 1;
                                            //return band;
                                        }
                                        in.setEstadoFin(true);
                                        in.actualizarIniciofin(in);
                                    }else if(hini.compareTo(inicio)>=0&&hfin.compareTo(inicio)>=0&&hini.compareTo(fin)<=0&&hfin.compareTo(fin)<=0){
                                        if(control==true){
                                            JOptionPane.showMessageDialog(null, "El personal "+ per.toString() +" suspenderá su clase para realizar la reunión", "Registrar Reunión", JOptionPane.INFORMATION_MESSAGE);
                                            band = 2;
                                            //return band;
                                        }
                                    }else if(hini.compareTo(inicio)<=0&&hfin.compareTo(inicio)>=0&&hini.compareTo(fin)<=0&&hfin.compareTo(fin)>=0){
                                        if(control==true){
                                            JOptionPane.showMessageDialog(null, "El personal "+ per.toString() +" suspenderá su clase para realizar la reunión", "Registrar Reunión", JOptionPane.INFORMATION_MESSAGE);
                                            band = 1;
                                            //return band;
                                        }
                                        in.setEstadoInicio(true);
                                        in.setEstadoFin(true);
                                    }
                                }
                            }
                        }else if(age.getTarea().getTareareunions().iterator().hasNext()){
                            Dia di = age.getDia2(diaini);
                            if (di.getIdDia() != null) {
                                Iterator itin = di.getIniciofins().iterator();
                                while (itin.hasNext()) {
                                    Iniciofin in = (Iniciofin) itin.next();
                                    SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                                    String i=formateador.format(in.getInicio());
                                    String ii=formateador.format(in.getFin());
                                    Date inicio = formateador.parse(i);
                                    Date fin = formateador.parse(ii);
                                    if (hini.compareTo(inicio) <= 0 && hfin.compareTo(inicio) >= 0) {
                                        band = 0;
                                        if(control==true){JOptionPane.showMessageDialog(null, "No existe disponibilidad de horario para " + per.toString() + " porque existe otra reunión a ese horario", "Registrar Reunión", JOptionPane.ERROR_MESSAGE);}
                                        return band;
                                    } else if (hini.compareTo(fin) <= 0 && hfin.compareTo(fin) >= 0) {
                                        band = 0;
                                        if(control==true){JOptionPane.showMessageDialog(null, "No existe disponibilidad de horario para " + per.toString() + " porque existe otra reunión a ese horario", "Registrar Reunión", JOptionPane.ERROR_MESSAGE);}
                                        return band;
                                    } else if (hini.compareTo(inicio) >= 0 && hfin.compareTo(inicio) >= 0 && hini.compareTo(fin) <= 0 && hfin.compareTo(fin) <= 0) {
                                        band = 0;
                                        if(control==true){JOptionPane.showMessageDialog(null, "No existe disponibilidad de horario para " + per.toString() + " porque existe otra reunión a ese horario", "Registrar Reunión", JOptionPane.ERROR_MESSAGE);}
                                        return band;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            
            if(!per.getDeclaracionjuradas().isEmpty()){
                Date diaux=diaini;
                    String di= ObtenerDia(diaux.getDay());
                    Iterator itact=per.getDeclaracionjuradas().iterator().next().ObtenerActivos(di).iterator();
                    while(itact.hasNext()){
                        Activo act=(Activo) itact.next();
                        Iterator itin=act.getActivoIniciofins().iterator();
                        while(itin.hasNext()){
                            ActivoIniciofin in=(ActivoIniciofin) itin.next();
                            if (hini.compareTo(in.getInicio()) <= 0 && hfin.compareTo(in.getInicio()) >= 0) {
                                band=0;
                                if(control==true){JOptionPane.showMessageDialog(null,"no existe disponibilidad por parte de la declaración jurada a ese horario", "Registrar Reunión", JOptionPane.ERROR_MESSAGE);}
                                return band;
                            } else if (hini.compareTo(in.getFin()) <= 0 && hfin.compareTo(in.getFin()) >= 0) {
                                band=0;
                                if(control==true){JOptionPane.showMessageDialog(null,"no existe disponibilidad por parte de la declaración jurada a ese horario", "Registrar Reunión", JOptionPane.ERROR_MESSAGE);}
                                return band;
                            } else if (hini.compareTo(in.getInicio()) >= 0 && hfin.compareTo(in.getInicio()) >= 0 && hini.compareTo(in.getFin()) <= 0 && hfin.compareTo(in.getFin()) <= 0) {
                                band=0;
                                if(control==true){JOptionPane.showMessageDialog(null,"no existe disponibilidad por parte de la declaración jurada a ese horario", "Registrar Reunión", JOptionPane.ERROR_MESSAGE);}
                                return band;
                            }
                        }
                    }
            }
        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex.toString());}
        return band;
    }
    // </editor-fold>
    
    ///EXTRACURRICULARES
    // <editor-fold defaultstate="collapsed" desc="EXTRACURRICULARES"> 
    public int ExtracurricularVerificarDisponibilidad (Personal per,Date diaini,Date hini,Date hfin,Date diafin,boolean control, int id){
        int band=1;
        try {
            if (!PERSISTENCIA.getAgendas(per.getIdPersonal()).isEmpty()) {
                Iterator it = PERSISTENCIA.getAgendas(per.getIdPersonal()).iterator();
                while (it.hasNext()) {
                    Agenda age = (Agenda) it.next();
                    if (age.getPersonal().getEstado() == true && age.getTarea().getEstado() == true && age.getTarea().getIdTarea()!=id) {
                        if(age.getTarea().getTareaotros().iterator().hasNext()){
                            //Tareaotro tarot=age.getTarea().getTareaotros().iterator().next();
                            Date inicio=age.getTarea().getDiaInicio();
                            Date fin=age.getTarea().getDiaFin();
                            if(diaini.compareTo(inicio)>=0 ||diafin.compareTo(inicio)>= 0 && diaini.compareTo(fin)<=0 || diafin.compareTo(fin)<=0){
                                Date hinicio = age.getTarea().getDiaInicio();
                                Date hhfin = age.getTarea().getDiaFin();
                                if(hini.compareTo(hinicio)<=0&&hfin.compareTo(hinicio)>=0){
                                    band = 1;
                                    if(control==true){JOptionPane.showMessageDialog(null, "El personal "+ per.toString() +" tiene una tarea no obligatoria a este horario", "Registrar Tarea Extracurricular", JOptionPane.ERROR_MESSAGE);}
                                    //return band;
                                }else if(hini.compareTo(hhfin)<=0&&hfin.compareTo(hhfin)>=0){
                                    band = 1;
                                    if(control==true){JOptionPane.showMessageDialog(null, "El personal "+ per.toString() +" tiene una tarea no obligatoria a este horario", "Registrar Tarea Extracurricular", JOptionPane.ERROR_MESSAGE);}
                                    //return band;
                                }else if(hini.compareTo(hinicio)>=0&&hfin.compareTo(hinicio)>=0&&hini.compareTo(hhfin)<=0&&hfin.compareTo(hhfin)<=0){
                                    band = 1;
                                    if(control==true){JOptionPane.showMessageDialog(null, "El personal "+ per.toString() +" tiene una tarea no obligatoria a este horario", "Registrar Tarea Extracurricular", JOptionPane.ERROR_MESSAGE);}
                                    //return band;
                                }
                            }
                        }else if(age.getTarea().getTareaextracurriculars().iterator().hasNext()){
//                            Tareaextracurricular tarot=age.getTarea().getTareaextracurriculars().iterator().next();
                            Date inicio=age.getTarea().getDiaInicio();
                            Date fin=age.getTarea().getDiaFin();
                            if(diaini.compareTo(inicio)>=0 ||diafin.compareTo(inicio)>= 0 && diaini.compareTo(fin)<=0 || diafin.compareTo(fin)<=0){
                                Date hinicio = age.getTarea().getDiaInicio();
                                Date hhfin = age.getTarea().getDiaFin();
                                if(hini.compareTo(hinicio)<=0&&hfin.compareTo(hinicio)>=0){
                                    band = 0;
                                    if(control==true){JOptionPane.showMessageDialog(null, "No existe disponibilidad de horario para "+ per.toString() +" porque existe una tarea extracurricular a ese horario", "Registrar Tarea Extracurricular", JOptionPane.ERROR_MESSAGE);}
                                    return band;
                                }else if(hini.compareTo(hhfin)<=0&&hfin.compareTo(hhfin)>=0){
                                    band = 0;
                                    if(control==true){JOptionPane.showMessageDialog(null, "No existe disponibilidad de horario para "+ per.toString() +" porque existe una tarea extracurricular a ese horario", "Registrar Tarea Extracurricular", JOptionPane.ERROR_MESSAGE);}
                                    return band;
                                }else if(hini.compareTo(hinicio)>=0&&hfin.compareTo(hinicio)>=0&&hini.compareTo(hhfin)<=0&&hfin.compareTo(hhfin)<=0){
                                    band = 0;
                                    if(control==true){JOptionPane.showMessageDialog(null, "No existe disponibilidad de horario para "+ per.toString() +" porque existe una tarea extracurricular a ese horario", "Registrar Tarea Extracurricular", JOptionPane.ERROR_MESSAGE);}
                                    return band;
                                }
                            }
                        }else if(age.getTarea().getTareaclases().iterator().hasNext()){
                            Date diaaux = diaini;
                            if (diaini.getYear()==diafin.getYear()&&diaini.getMonth()==diafin.getMonth()&&diaini.getDate()==diafin.getDate()) {
                                Dia di = age.getDia2(diaaux);
                                if (di.getIdDia() != null) {
                                    Iterator itin = di.getIniciofins().iterator();
                                    while (itin.hasNext()) {
                                        Iniciofin in = (Iniciofin) itin.next();
                                        SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                                        String i=formateador.format(in.getInicio());
                                        String ii=formateador.format(in.getFin());
                                        Date inicio = formateador.parse(i);
                                        Date fin = formateador.parse(ii);
                                        if(hini.compareTo(inicio)<=0&&hfin.compareTo(inicio)>=0){
                                            if(control==true){
                                                JOptionPane.showMessageDialog(null, "El personal "+ per.toString() +" continuará con su clase luego de realizar la tarea", "Registrar Tarea Extracurricular", JOptionPane.INFORMATION_MESSAGE);
                                                band = 1;
                                                //return band;
                                            }
                                            in.setEstadoInicio(true);
                                            in.actualizarIniciofin(in);
                                        }else if(hini.compareTo(fin)<=0&&hfin.compareTo(fin)>=0){
                                            if(control==true){
                                                JOptionPane.showMessageDialog(null, "El personal "+ per.toString() +" suspenderá su clase para realizar la tarea", "Registrar Tarea Extracurricular", JOptionPane.INFORMATION_MESSAGE);
                                                band = 1;
                                                //return band;
                                            }
                                            in.setEstadoFin(true);
                                            in.actualizarIniciofin(in);
                                        }else if(hini.compareTo(inicio)>=0&&hfin.compareTo(inicio)>=0&&hini.compareTo(fin)<=0&&hfin.compareTo(fin)<=0){
                                            if(control==true){
                                                JOptionPane.showMessageDialog(null, "El personal "+ per.toString() +" suspenderá su clase para realizar la tarea", "Registrar Tarea Extracurricular", JOptionPane.INFORMATION_MESSAGE);
                                                band = 2;
                                                //return band;
                                            }
                                        }else if(hini.compareTo(inicio)<=0&&hfin.compareTo(inicio)>=0&&hini.compareTo(fin)<=0&&hfin.compareTo(fin)>=0){
                                            if(control==true){
                                                JOptionPane.showMessageDialog(null, "El personal "+ per.toString() +" suspenderá su clase para realizar la tarea", "Registrar Tarea Extracurricular", JOptionPane.INFORMATION_MESSAGE);
                                                band = 1;
                                                //return band;
                                            }
                                            in.setEstadoInicio(true);
                                            in.setEstadoFin(true);
                                        }
                                    }
                                }
                            } else {
                                while (diaaux.compareTo(diafin) <= 0) {
                                    Dia di = age.getDia2(diaaux);
                                    if (di.getIdDia() != null) {
                                        Iterator itin = di.getIniciofins().iterator();
                                        while (itin.hasNext()) {
                                            Iniciofin in = (Iniciofin) itin.next();
                                            SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                                            String i = formateador.format(in.getInicio());
                                            String ii = formateador.format(in.getFin());
                                            Date inicio = formateador.parse(i);
                                            Date fin = formateador.parse(ii);
                                            if(diaini.getDate()<di.getDia()&&diafin.getDate()>di.getDia()){
                                                if(in.getEstadoInicio()!=null || in.getEstadoFin()!=null){
                                                    if(control==true){
                                                        JOptionPane.showMessageDialog(null, "El personal " + per.toString() + " tiene clases pero se suspenderán por la tarea extracurricular", "Registrar Tarea Extracurricular", JOptionPane.INFORMATION_MESSAGE);
                                                        band = 1;
                                                        //return band;
                                                    }
                                                    in.setEstadoInicio(null);
                                                    in.setEstadoFin(null);
                                                    in.actualizarIniciofin(in);
                                                }
                                            }else if(diaini.getDate()==di.getDia()){
                                                if(hini.compareTo(inicio)<=0){
                                                    if(in.getEstadoInicio()!=null || in.getEstadoFin()!=null){
                                                        if(control==true){
                                                            JOptionPane.showMessageDialog(null, "El personal " + per.toString() + " tiene clases pero se suspenderán por la tarea extracurricular", "Registrar Tarea Extracurricular", JOptionPane.INFORMATION_MESSAGE);
                                                            band = 1;
                                                            //return band;
                                                        }
                                                        in.setEstadoInicio(null);
                                                        in.setEstadoFin(null);
                                                        in.actualizarIniciofin(in);  
                                                    }
                                                }else if(hini.compareTo(inicio)>=0){
                                                    if(in.getEstadoFin()!=null){
                                                        if(control==true){
                                                            JOptionPane.showMessageDialog(null, "El personal " + per.toString() + " tiene clases pero se suspenderán por la tarea extracurricular", "Registrar Tarea Extracurricular", JOptionPane.INFORMATION_MESSAGE);
                                                            band = 2;
                                                            //return band;
                                                        }
                                                        in.setEstadoFin(null);
                                                        in.actualizarIniciofin(in);
                                                    }
                                                }
                                            }else if(diafin.getDate()==di.getDia()){
                                                if(hfin.compareTo(fin)>=0){
                                                    if(in.getEstadoInicio()!=null || in.getEstadoFin()!=null){
                                                        if(control==true){
                                                            JOptionPane.showMessageDialog(null, "El personal " + per.toString() + " tiene clases pero se suspenderán por la tarea extracurricular", "Registrar Tarea Extracurricular", JOptionPane.INFORMATION_MESSAGE);
                                                            band = 1;
                                                            //return band;
                                                        }
                                                        in.setEstadoInicio(null);
                                                        in.setEstadoFin(null);
                                                        in.actualizarIniciofin(in);
                                                    }
                                                }else if(hini.compareTo(fin)<=0){
                                                    if(in.getEstadoInicio()!=null || in.getEstadoFin()!=null){
                                                        if(control==true){
                                                            JOptionPane.showMessageDialog(null, "El personal " + per.toString() + " tiene clases pero se suspenderán por la tarea extracurricular", "Registrar Tarea Extracurricular", JOptionPane.INFORMATION_MESSAGE);
                                                            band = 1;
                                                            //return band;
                                                        }
                                                        in.setEstadoInicio(null);
                                                        in.setEstadoFin(null);
                                                        in.actualizarIniciofin(in);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    diaaux = Controlador.sumarFechasDias(diaaux, 1);
                                }
                            }
                        }else if(age.getTarea().getTareareunions().iterator().hasNext()){
                            Date diaaux=diaini;
                            while (diaaux.compareTo(diafin) <= 0) {
                                Dia di = age.getDia2(diaaux);
                                if (di.getIdDia() != null) {
                                    Iterator itin = di.getIniciofins().iterator();
                                    while (itin.hasNext()) {
                                        Iniciofin in = (Iniciofin) itin.next();
                                        SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                                        String i = formateador.format(in.getInicio());
                                        String ii = formateador.format(in.getFin());
                                        Date inicio = formateador.parse(i);
                                        Date fin = formateador.parse(ii);
                                        if (diaini.getDate() < di.getDia() && diafin.getDate() > di.getDia()) {
                                            if (in.getEstadoInicio() != null || in.getEstadoFin() != null) {
                                                if (control == true) {
                                                    JOptionPane.showMessageDialog(null, "El personal " + per.toString() + " tiene reuniones pero se suspenderán por la tarea extracurricular", "Registrar Tarea Extracurricular", JOptionPane.INFORMATION_MESSAGE);
                                                    band = 1;
                                                    //return band;
                                                }
                                                in.setEstadoInicio(null);
                                                in.actualizarIniciofin(in);
                                            }
                                        } else if (diaini.getDate() == di.getDia()) {
                                            if (hini.compareTo(inicio) <= 0) {
                                                if (in.getEstadoInicio() != null || in.getEstadoFin() != null) {
                                                    if (control == true) {
                                                        JOptionPane.showMessageDialog(null, "El personal " + per.toString() + " tiene reuniones pero se suspenderán por la tarea extracurricular", "Registrar Tarea Extracurricular", JOptionPane.INFORMATION_MESSAGE);
                                                        band = 1;
                                                        //return band;   
                                                    }
                                                    in.setEstadoInicio(null);
                                                    in.setEstadoFin(null);
                                                    in.actualizarIniciofin(in);
                                                }
                                            } else if (hini.compareTo(inicio) >= 0) {
                                                if (in.getEstadoFin() != null) {
                                                    if (control == true) {
                                                        JOptionPane.showMessageDialog(null, "El personal " + per.toString() + " tiene reuniones pero se suspenderán por la tarea extracurricular", "Registrar Tarea Extracurricular", JOptionPane.INFORMATION_MESSAGE);
                                                        band = 2;
                                                        //return band;
                                                    }
                                                    in.setEstadoFin(null);
                                                    in.actualizarIniciofin(in);
                                                }
                                            }
                                        }
                                    }
                                }
                                diaaux = Controlador.sumarFechasDias(diaaux, 1);
                            }
                        }
                    }
                }
            }
        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex.toString());}
        return band;
    }
    // </editor-fold>
    
    ///OTROS
    // <editor-fold defaultstate="collapsed" desc="OTROS"> 
    public boolean OtrosVerificarDisponibilidad (Personal per,Date diaini,Date hini,Date hfin,Date diafin, boolean control, int id){
        boolean band=true;
        try {
            if (!PERSISTENCIA.getAgendas(per.getIdPersonal()).isEmpty()) {
                Iterator it = PERSISTENCIA.getAgendas(per.getIdPersonal()).iterator();
                while (it.hasNext()) {
                    Agenda age = (Agenda) it.next();
                    if (age.getPersonal().getEstado() == true && age.getTarea().getEstado() == true && age.getTarea().getIdTarea()!=id) {
                        if(age.getTarea().getTareaotros().iterator().hasNext()){
//                            Tareaotro tarot=age.getTarea().getTareaotros().iterator().next();
                            Date inicio=age.getTarea().getDiaInicio();
                            Date fin=age.getTarea().getDiaFin();
                            if(diaini.compareTo(inicio)>=0 ||diafin.compareTo(inicio)>= 0 && diaini.compareTo(fin)<=0 || diafin.compareTo(fin)<=0){
                                Date hinicio = age.getTarea().getDiaInicio();
                                Date hhfin = age.getTarea().getDiaFin();
                                if(hini.compareTo(hinicio)<=0&&hfin.compareTo(hinicio)>=0){
                                    band = false;
                                    JOptionPane.showMessageDialog(null, "No existe disponibilidad de horario para "+ per.toString() +" porque existe otra tarea a ese horario", "Registrar Tarea", JOptionPane.ERROR_MESSAGE);
                                    return band;
                                }else if(hini.compareTo(hhfin)<=0&&hfin.compareTo(hhfin)>=0){
                                    band = false;
                                    JOptionPane.showMessageDialog(null, "No existe disponibilidad de horario para "+ per.toString() +" porque existe otra tarea a ese horario", "Registrar Tarea", JOptionPane.ERROR_MESSAGE);
                                    return band;
                                }else if(hini.compareTo(hinicio)>=0&&hfin.compareTo(hinicio)>=0&&hini.compareTo(hhfin)<=0&&hfin.compareTo(hhfin)<=0){
                                    band = false;
                                    JOptionPane.showMessageDialog(null, "No existe disponibilidad de horario para "+ per.toString() +" porque existe otra tarea a ese horario", "Registrar Tarea", JOptionPane.ERROR_MESSAGE);
                                    return band;
                                }
                            }
                        }else if(age.getTarea().getTareaextracurriculars().iterator().hasNext()){
//                            Tareaextracurricular tarot=age.getTarea().getTareaextracurriculars().iterator().next();
                            Date inicio=age.getTarea().getDiaInicio();
                            Date fin=age.getTarea().getDiaFin();
                            if(diaini.compareTo(inicio)>=0 ||diafin.compareTo(inicio)>= 0 && diaini.compareTo(fin)<=0 || diafin.compareTo(fin)<=0){
                                hini.setYear(diaini.getYear());
                                hini.setMonth(diaini.getMonth());
                                hini.setDate(diaini.getDate());
                                hfin.setYear(diafin.getYear());
                                hfin.setMonth(diafin.getMonth());
                                hfin.setDate(diafin.getDate());

                                if(hini.compareTo(inicio)<=0&&hfin.compareTo(inicio)>=0){
                                    band = false;
                                    JOptionPane.showMessageDialog(null, "No existe disponibilidad de horario para "+ per.toString() +" porque existe una tarea extracurricular a ese horario", "Registrar Tarea", JOptionPane.ERROR_MESSAGE);
                                    return band;
                                }else if(hini.compareTo(fin)<=0&&hfin.compareTo(fin)>=0){
                                    band = false;
                                    JOptionPane.showMessageDialog(null, "No existe disponibilidad de horario para "+ per.toString() +" porque existe una tarea extracurricular a ese horario", "Registrar Tarea", JOptionPane.ERROR_MESSAGE);
                                    return band;
                                }else if(hini.compareTo(inicio)>=0&&hfin.compareTo(fin)<=0){
                                    band = false;
                                    JOptionPane.showMessageDialog(null, "No existe disponibilidad de horario para "+ per.toString() +" porque existe una tarea extracurricular a ese horario", "Registrar Tarea", JOptionPane.ERROR_MESSAGE);
                                    return band;
                                }else if(hini.compareTo(inicio)<=0&&hfin.compareTo(fin)>=0){
                                    band = false;
                                    if(control==true){JOptionPane.showMessageDialog(null, "No existe disponibilidad de horario para "+ per.toString() +" porque existe una tarea extracurricular a ese horario", "Registrar Reunión", JOptionPane.ERROR_MESSAGE);}
                                    return band;
                                }
                            }
                        }else if(age.getTarea().getTareaclases().iterator().hasNext()){
                            if (diaini.getYear()==diafin.getYear()&&diaini.getMonth()==diafin.getMonth()&&diaini.getDate()==diafin.getDate()) {
                                Dia di = age.getDia2(diaini);
                                if (di.getIdDia() != null) {
                                    Iterator itin = di.getIniciofins().iterator();
                                    while (itin.hasNext()) {
                                        Iniciofin in = (Iniciofin) itin.next();
                                        SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                                        String i=formateador.format(in.getInicio());
                                        String ii=formateador.format(in.getFin());
                                        Date inicio = formateador.parse(i);
                                        Date fin = formateador.parse(ii);
                                        if(hini.compareTo(inicio)<=0&&hfin.compareTo(inicio)>=0){
                                            if(control==true){JOptionPane.showMessageDialog(null, "No existe disponibilidad de horario para " + per.toString() + " porque existe una clase a ese horario", "Registrar Tarea", JOptionPane.ERROR_MESSAGE);}
                                            band = false;
                                            return band;
                                        }else if(hini.compareTo(fin)<=0&&hfin.compareTo(fin)>=0){
                                            if(control==true){JOptionPane.showMessageDialog(null, "No existe disponibilidad de horario para " + per.toString() + " porque existe una clase a ese horario", "Registrar Tarea", JOptionPane.ERROR_MESSAGE);}
                                            band = false;
                                            return band;
                                        }else if(hini.compareTo(inicio)>=0&&hfin.compareTo(inicio)>=0&&hini.compareTo(fin)<=0&&hfin.compareTo(fin)<=0){
                                            band = false;
                                            if(control==true){JOptionPane.showMessageDialog(null, "No existe disponibilidad de horario para " + per.toString() + " porque existe una clase a ese horario", "Registrar Tarea", JOptionPane.ERROR_MESSAGE);}
                                            return band;
                                        }else if(hini.compareTo(inicio)<=0&&hfin.compareTo(inicio)>=0&&hini.compareTo(fin)<=0&&hfin.compareTo(fin)>=0){
                                            band = false;
                                            if(control==true){JOptionPane.showMessageDialog(null, "No existe disponibilidad de horario para " + per.toString() + " porque existe una clase a ese horario", "Registrar Tarea", JOptionPane.ERROR_MESSAGE);}
                                            return band;
                                        }
                                    }
                                }
                            } else {
                                Date diaaux = diaini;
                                while (diaaux.compareTo(diafin) <= 0) {
                                    Dia di = age.getDia2(diaaux);
                                    if (di.getIdDia() != null) {
                                        Iterator itin = di.getIniciofins().iterator();
                                        while (itin.hasNext()) {
                                            Iniciofin in = (Iniciofin) itin.next();
                                            SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                                            String i = formateador.format(in.getInicio());
                                            String ii = formateador.format(in.getFin());
                                            Date inicio = formateador.parse(i);
                                            Date fin = formateador.parse(ii);
                                            if(diaini.getDate()<di.getDia()&&diafin.getDate()>di.getDia()){
                                                if(control==true){JOptionPane.showMessageDialog(null, "No existe disponibilidad de horario para " + per.toString() + " porque existe una clase a ese horario", "Registrar Tarea", JOptionPane.ERROR_MESSAGE);}
                                                band = false;
                                                return band;
                                            }else if(diaini.getDate()==di.getDia()){
                                                if(hini.compareTo(inicio)<=0&&hfin.compareTo(inicio)>=0){
                                                    if(control==true){JOptionPane.showMessageDialog(null, "No existe disponibilidad de horario para " + per.toString() + " porque existe una clase a ese horario", "Registrar Tarea", JOptionPane.ERROR_MESSAGE);}
                                                    band = false;
                                                    return band;
                                                }else if(hini.compareTo(inicio)>=0&&hini.compareTo(fin)<=0){
                                                    if(control==true){JOptionPane.showMessageDialog(null, "No existe disponibilidad de horario para " + per.toString() + " porque existe una clase a ese horario", "Registrar Tarea", JOptionPane.ERROR_MESSAGE);}
                                                    band = false;
                                                    return band;
                                                }
                                            }else if(diafin.getDate()==di.getDia()){
                                                if(hfin.compareTo(fin)>=0){
                                                    if(control==true){JOptionPane.showMessageDialog(null, "No existe disponibilidad de horario para " + per.toString() + " porque existe una clase a ese horario", "Registrar Tarea", JOptionPane.ERROR_MESSAGE);}
                                                    band = false;
                                                    return band;
                                                }else if(hini.compareTo(inicio)>=0&&hfin.compareTo(fin)<=0){
                                                    if(control==true){JOptionPane.showMessageDialog(null, "No existe disponibilidad de horario para " + per.toString() + " porque existe una clase a ese horario", "Registrar Tarea", JOptionPane.ERROR_MESSAGE);}
                                                    band = false;
                                                    return band;
                                                }
                                            }
                                        }
                                    }
                                    diaaux = Controlador.sumarFechasDias(diaaux, 1);
                                }
                            }
                        }else if(age.getTarea().getTareareunions().iterator().hasNext()){
                            Date diaaux = diaini;
                            while (diaaux.compareTo(diafin) <= 0) {
                                Dia di = age.getDia2(diaaux);
                                if (di.getIdDia() != null) {
                                    Iterator itin = di.getIniciofins().iterator();
                                    while (itin.hasNext()) {
                                        Iniciofin in = (Iniciofin) itin.next();
                                        SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                                        String i = formateador.format(in.getInicio());
                                        String ii = formateador.format(in.getFin());
                                        Date inicio = formateador.parse(i);
                                        Date fin = formateador.parse(ii);
                                        if (hini.compareTo(inicio) <= 0 && hfin.compareTo(inicio) >= 0) {
                                            band = false;
                                            JOptionPane.showMessageDialog(null, "No existe disponibilidad de horario para " + per.toString() + " porque existe una reunión a ese horario", "Registrar Tarea", JOptionPane.ERROR_MESSAGE);
                                            return band;
                                        } else if (hini.compareTo(fin) <= 0 && hfin.compareTo(fin) >= 0) {
                                            band = false;
                                            JOptionPane.showMessageDialog(null, "No existe disponibilidad de horario para " + per.toString() + " porque existe una reunión a ese horario", "Registrar Tarea", JOptionPane.ERROR_MESSAGE);
                                            return band;
                                        } else if (hini.compareTo(inicio) >= 0 && hfin.compareTo(inicio) >= 0 && hini.compareTo(fin) <= 0 && hfin.compareTo(fin) <= 0) {
                                            band = false;
                                            JOptionPane.showMessageDialog(null, "No existe disponibilidad de horario para " + per.toString() + " porque existe una reunión a ese horario", "Registrar Tarea", JOptionPane.ERROR_MESSAGE);
                                            return band;
                                        }
                                    }
                                }
                                diaaux = Controlador.sumarFechasDias(diaaux, 1);
                            }
                        }
                    }
                }
            }
            
            if(!per.getDeclaracionjuradas().isEmpty()){
                Date diaux=diaini;
                while(diaux.compareTo(diafin)<=0) {
                    String di= ObtenerDia(diaux.getDay());
                    Iterator itact=per.getDeclaracionjuradas().iterator().next().ObtenerActivos(di).iterator();
                    while(itact.hasNext()){
                        Activo act=(Activo) itact.next();
                        Iterator itin=act.getActivoIniciofins().iterator();
                        while(itin.hasNext()){
                            ActivoIniciofin in=(ActivoIniciofin) itin.next();
                            if (hini.compareTo(in.getInicio()) <= 0 && hfin.compareTo(in.getInicio()) >= 0) {
                                band=false;
                                if(control==true){JOptionPane.showMessageDialog(null,"no existe disponibilidad por parte de la declaración jurada a ese horario", "Registrar Reunión", JOptionPane.ERROR_MESSAGE);}
                                return band;
                            } else if (hini.compareTo(in.getFin()) <= 0 && hfin.compareTo(in.getFin()) >= 0) {
                                band=false;
                                if(control==true){JOptionPane.showMessageDialog(null,"no existe disponibilidad por parte de la declaración jurada a ese horario", "Registrar Reunión", JOptionPane.ERROR_MESSAGE);}
                                return band;
                            } else if (hini.compareTo(in.getInicio()) >= 0 && hfin.compareTo(in.getInicio()) >= 0 && hini.compareTo(in.getFin()) <= 0 && hfin.compareTo(in.getFin()) <= 0) {
                                band=false;
                                if(control==true){JOptionPane.showMessageDialog(null,"no existe disponibilidad por parte de la declaración jurada a ese horario", "Registrar Reunión", JOptionPane.ERROR_MESSAGE);}
                                return band;
                            }
                        }
                    }
                    diaux=Controlador.sumarFechasDias(diaux, 1);
                }
            }
        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex.toString());}
        return band;
    }
    // </editor-fold>
    
}
