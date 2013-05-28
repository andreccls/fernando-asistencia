/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Clases.Actividad;
import Clases.Activo;
import Clases.ActivoIniciofin;
import Clases.Anolectivo;
import Clases.Articulo;
import Clases.Auditoria;
import Clases.Cargo;
import Clases.Controlador;
import Clases.Declaracionjurada;
import Clases.Departamento;
import Clases.DetalleEstablecimiento;
import Clases.Establecimiento;
import Clases.Inactivo;
import Clases.Licencia;
import Clases.Nivel;
import Clases.Perfil;
import Clases.Personal;
import Clases.PersonalDepartamento;
import Clases.PersonalDepartamentoId;
import Clases.PersonalFamiliar;
import Clases.PersonalFamiliarId;
import Clases.Personaldocente;
import Clases.PersonaldocenteId;
import Clases.Personalnodocente;
import Clases.PersonalnodocenteId;
import Clases.Telefono;
import Clases.Tipocargo;
import Clases.Tipodoc;
import Clases.Tiporelacion;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Image;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.Renderer; 
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author fer
 */
public class JFrameActualizarPersonal extends javax.swing.JFrame {

    public Controlador Drive;
    public Personal adm;
//    int idsesion;
    Personal pe= new Personal();
    DefaultListModel modeloLista = new DefaultListModel();
    DefaultListModel modeloLista2 = new DefaultListModel();
    Declaracionjurada decju;
    Boolean modificar=false;
    Boolean mod=false;
    HiloProgreso hilo;
    Boolean bb=false;
    Licencia li=new Licencia();
    
    /**
     * Creates new form JFrameActualizarPersonal
     */
   
    public JFrameActualizarPersonal(Controlador unDrive,Personal per, Personal admin,boolean bandera) {
        this.adm=admin;
        this.Drive=unDrive;
//        this.idsesion=id;
        this.bb=bandera;
        initComponents();
        Controlador auxDrive = new Controlador();
        auxDrive.getPrimerEstablecimiento();
        Drive = auxDrive;
        jList1.setModel(modeloLista);
        jList2.setModel(modeloLista2);
        Iniciar(Drive,per,bb);
        
    }

    private void Iniciar(Controlador Drive,Personal per,boolean bandera) {
        Drive.LimpiarTabla(jTable1);
        Drive.CargarTablaflia(jTable1, per);
        Drive.CargarComboTipodoc(jComboBox6);
        Drive.CargarComboPerfil(jComboBox12);
        Drive.CargarComboArticulo(jComboBox15);
        String valor=jComboBox14.getSelectedItem().toString();
        Date ff=dateChooserCombo11.getSelectedDate().getTime();
        Drive.CargarTablaLicencia(jTable2, per,ff, valor);
        jFormattedTextField7.setText(per.getDni());
        jFormattedTextField7.disable();
        jTextField8.setText(per.getApellido());
        jTextField7.setText(per.getNombre());
        jTextField1.setText(per.getCuil());
        jTextField1.disable();
        jComboBox2.setSelectedItem(per.getSexo());
        jComboBox5.setSelectedItem(per.getEstadoCivil());
        jComboBox12.setSelectedItem(per.getPerfil());
        jTextField21.setText(per.getCorreoElectronico());
        jTextField11.setText(per.getCalle());
        jTextField14.setText(String.valueOf(per.getAltura()));
        jTextField18.setText(per.getPiso());
        //jTextField16.setText(null);
        jTextField19.setText(per.getDepto());
        Calendar cal = Calendar.getInstance();
        cal.setTime(per.getFechaNac());
        dateChooserCombo1.setSelectedDate(cal);
        cal.setTime(per.getIngreso());
        dateChooserCombo2.setSelectedDate(cal);
        per.CargarListTelefono(jList1,modeloLista);
        Drive.CargarComboDepartamento(jComboBox3);
        Drive.CargarComboActividad(jComboBox7);
        
        Personaldocente perdoc=per.getPersonaldoc(per.getIdPersonal());
        if(perdoc.getId() != null){
            jRadioButton3.setSelected(true);
            per.CargarListDepartamento(jList2,modeloLista2);
            jTextField16.setText(String.valueOf(perdoc.getCargohoras()));
            jTextField17.setText(String.valueOf(perdoc.getAntiguedadDoc()));
        }else{jRadioButton3.setSelected(false);}
        Personalnodocente pernodoc=per.getPersonalnodoc(per.getIdPersonal());
        if(pernodoc.getId() != null){
            jRadioButton4.setSelected(true);
            //int id=pernodoc.getActividad().getIdActividad();
            Actividad act=pernodoc.getActividad();
            jComboBox7.setSelectedItem(act);
        }else{jRadioButton4.setSelected(false);}
        
        Date ano=new Date();
        Iterator it=per.getDeclaracionjuradas().iterator();
        while(it.hasNext()){
            Declaracionjurada dec=(Declaracionjurada) it.next();
            if(dec.getAnolectivo().getAno()==ano.getYear()){
                jTextField4.setText(dec.getObservacion());
            }
        }
        jFormattedTextField6.setText(String.valueOf(ano.getYear()+1900));
        Drive.CargarComboTipodoc(jComboBox1);
        Drive.CargarComboRelacion(jComboBox8);
        
        if(bandera==false){
            int ii=0;
            ii= Integer.valueOf(jFormattedTextField6.getText());
            Drive.CargarComboEstablecimientosPerso(jComboBox9, per,ii);
        }
        TableColumn Column1 = jTable3.getColumnModel().getColumn(0);
        TableColumn Column2 = jTable3.getColumnModel().getColumn(1);
        TableColumn Column3 = jTable3.getColumnModel().getColumn(2);
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("LUNES");
        comboBox.addItem("MARTES");
        comboBox.addItem("MIERCOLES");
        comboBox.addItem("JUEVES");
        comboBox.addItem("VIERNES");
        comboBox.addItem("SABADO");
        Column1.setCellEditor(new DefaultCellEditor(comboBox));
        try {
            MaskFormatter campo=null;
            campo = new MaskFormatter("HH:mm");
            JFormattedTextField text = new JFormattedTextField(campo);
            MaskFormatter uppercase=null;
            uppercase = new MaskFormatter("##:##");
            DefaultFormatterFactory factory = new DefaultFormatterFactory(uppercase);
            text.setFormatterFactory(factory);
            Column2.setCellEditor(new DefaultCellEditor(text));
            Column3.setCellEditor(new DefaultCellEditor(text));
        } catch (ParseException ex) {
            Logger.getLogger(JFrameActualizarPersonal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        pe=Drive.getPersonal(per.getTipodoc(), per.getDni());
        int[] anchos1 = {50, 200,60,100, 90, 75, 100};
        for(int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos1[i]);
        }
        DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
        modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
        jTable1.getColumnModel().getColumn(6).setCellRenderer(modelocentrar); 
        
        int[] anchos2 = {75, 75, 75};
        for(int i = 0; i < jTable3.getColumnCount(); i++) {
            jTable3.getColumnModel().getColumn(i).setPreferredWidth(anchos2[i]);
        }
        
        jTable1.getTableHeader().setDefaultRenderer(new HeaderRenderer(jTable1));
        jTable3.getTableHeader().setDefaultRenderer(new HeaderRenderer(jTable3));
        ///ICONO EDITAR
        ImageIcon fot = new ImageIcon(getClass().getResource("/imagenes/image.jpg"));
        Icon icono = new ImageIcon(fot.getImage().getScaledInstance(jLabel49.getWidth(), jLabel49.getHeight(), Image.SCALE_DEFAULT));
        jLabel49.setIcon(icono);
        jLabel49.repaint();
        jLabel41.setIcon(icono);
        jLabel41.repaint();
        jLabel48.setIcon(icono);
        jLabel48.repaint();
        jLabel66.setIcon(icono);
        jLabel66.repaint();
        ///ICONO ELIMINAR
        ImageIcon fott1 = new ImageIcon(getClass().getResource("/imagenes/eliminar.gif"));
        Icon icono1 = new ImageIcon(fott1.getImage().getScaledInstance(jLabel55.getWidth(), jLabel55.getHeight(), Image.SCALE_DEFAULT));
        jLabel55.setIcon(icono1);
        jLabel55.repaint();
        jLabel56.setIcon(icono1);
        jLabel56.repaint();
        jLabel57.setIcon(icono1);
        jLabel57.repaint();
        ImageIcon fott2 = new ImageIcon(getClass().getResource("/imagenes/no.png"));
        Icon icono2 = new ImageIcon(fott2.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        jButton5.setIcon(icono2);
        ImageIcon fott3 = new ImageIcon(getClass().getResource("/imagenes/ok.png"));
        Icon icono3 = new ImageIcon(fott3.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        jButton4.setIcon(icono3);
        jButton2.setIcon(icono3);
        jButton14.setIcon(icono3);
        jButton15.setIcon(icono3);
        jButton16.setIcon(icono3);
        jButton17.setIcon(icono3);
        ImageIcon fott5 = new ImageIcon(getClass().getResource("/imagenes/Buscar.png"));
        Icon icono5 = new ImageIcon(fott5.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        jButton3.setIcon(icono5);
        ImageIcon fott4 = new ImageIcon(getClass().getResource("/imagenes/Eliminar.png"));
        Icon icono4 = new ImageIcon(fott4.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        jButton7.setIcon(icono4);
        jButton8.setIcon(icono4);
        jButton18.setIcon(icono4);
        jButton19.setIcon(icono4);
        ImageIcon fott8 = new ImageIcon(getClass().getResource("/imagenes/Nuevo.png"));
        Icon icono8 = new ImageIcon(fott8.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        jButton1.setIcon(icono8);
        ImageIcon fott6 = new ImageIcon(getClass().getResource("/imagenes/Mas.png"));
        Icon icono6 = new ImageIcon(fott6.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        jButton6.setIcon(icono6);
        jButton9.setIcon(icono6);
        jButton11.setIcon(icono6);
        ImageIcon fott7 = new ImageIcon(getClass().getResource("/imagenes/Menos.png"));
        Icon icono7 = new ImageIcon(fott7.getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
        jButton13.setIcon(icono7);
        jButton10.setIcon(icono7);
        jButton12.setIcon(icono7);
        ImageIcon fott9= new ImageIcon(getClass().getResource("/imagenes/Editar.png"));
        Icon icono9 = new ImageIcon(fott9.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        jButton20.setIcon(icono9);
        jLabel10.setVisible(false);
        jLabel27.setVisible(false);
        jLabel38.setVisible(false);
        jComboBox13.setVisible(false);
        dateChooserCombo6.setVisible(false);
        dateChooserCombo7.setVisible(false);
        if(bandera==true){
            Drive.CargarComboDecjurada(jComboBox13, per);
            jLabel10.setVisible(true);
            jLabel27.setVisible(true);
            jLabel38.setVisible(true);
            jComboBox13.setVisible(true);
            dateChooserCombo6.setVisible(true);
            dateChooserCombo7.setVisible(true);
            dateChooserCombo9.setEnabled(false);
            dateChooserCombo10.setEnabled(false);
            dateChooserCombo11.setEnabled(false);
//            Object o=jComboBox13.getSelectedItem();
//            Anolectivo an=(Anolectivo) jComboBox13.getSelectedItem();
//            Calendar ca=Calendar.getInstance();
//            ca.setTime(an.getInicio());
//            dateChooserCombo6.setSelectedDate(ca);
            dateChooserCombo6.setEnabled(false);
//            ca.setTime(an.getFin());
//            dateChooserCombo7.setSelectedDate(ca);
            dateChooserCombo7.setEnabled(false);
            
            jLabel2.setVisible(false);
            jLabel1.setVisible(false);
            jTextField4.setVisible(false);
            jTextField6.setVisible(false);
            jFormattedTextField6.setVisible(false);
            dateChooserCombo1.setEnabled(false);
            dateChooserCombo2.setEnabled(false);
            dateChooserCombo3.setEnabled(false);
            dateChooserCombo4.setEnabled(false);
            dateChooserCombo5.setEnabled(false);
            jButton1.setEnabled(false);
            jButton10.setEnabled(false);
            jButton11.setEnabled(false);
            jButton12.setEnabled(false);
            jButton13.setEnabled(false);
            jButton14.setEnabled(false);
            jButton17.setEnabled(false);
            jButton19.setEnabled(false);
            jButton2.setEnabled(false);
            jButton4.setEnabled(false);
            jButton6.setEnabled(false);
            jButton7.setEnabled(false);
            jButton8.setEnabled(false);
            jButton9.setEnabled(false);
            jButton15.setEnabled(false);
            jButton16.setEnabled(false);
            jButton18.setEnabled(false);
            jButton20.setEnabled(false);
            jButton3.setEnabled(false);
            jCheckBox1.setEnabled(false);
            jComboBox1.setEnabled(false);
            jComboBox12.setEnabled(false);
            jComboBox2.setEnabled(false);
            jComboBox3.setEnabled(false);
            jComboBox4.setEnabled(false);
            jComboBox5.setEnabled(false);
            jComboBox6.setEnabled(false);
            jComboBox7.setEnabled(false);
            jComboBox8.setEnabled(false);
            jComboBox15.setEnabled(false);
            jComboBox14.setEnabled(false);
            
            jFormattedTextField7.setEnabled(false);
            jFormattedTextField8.setEnabled(false);
            jList1.setEnabled(false);
            jList2.setEnabled(false);
            jRadioButton3.setEnabled(false);
            jRadioButton4.setEnabled(false);
            jRadioButton5.setEnabled(false);
            jRadioButton6.setEnabled(false);
            jTable1.setEnabled(false);
            jTable3.setEnabled(false);
            jTextField1.setEnabled(false);
            jTextField11.setEnabled(false);
            jTextField12.setEnabled(false);
            jTextField13.setEnabled(false);
            jTextField14.setEnabled(false);
            jTextField16.setEnabled(false);
            jTextField17.setEnabled(false);
            jTextField18.setEnabled(false);
            jTextField19.setEnabled(false);
            jTextField2.setEnabled(false);
            jTextField20.setEnabled(false);
            jTextField21.setEnabled(false);
            jTextField3.setEnabled(false);
            
            jTextField5.setEnabled(false);
            jTextField7.setEnabled(false);
            jTextField8.setEnabled(false);
            jLabel48.setEnabled(false);
            jLabel56.setEnabled(false);
            jLabel49.setEnabled(false);
            jLabel55.setEnabled(false);
            jLabel41.setEnabled(false);
            jLabel57.setEnabled(false);
            jLabel66.setEnabled(false);
        }
        if(adm.getPerfil().getPersonalact()==null){
            jButton2.setEnabled(false);
            jButton7.setEnabled(false);
            jButton4.setEnabled(false);
            jButton1.setEnabled(false);
            jButton19.setEnabled(false);
            jButton14.setEnabled(false);
            jButton8.setEnabled(false);
            jButton17.setEnabled(false);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jLabel23 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox();
        jLabel24 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jComboBox4 = new javax.swing.JComboBox();
        jTextField13 = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jLabel15 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jLabel30 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox();
        jSeparator4 = new javax.swing.JSeparator();
        jTextField21 = new javax.swing.JTextField();
        jFormattedTextField7 = new javax.swing.JFormattedTextField();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jComboBox12 = new javax.swing.JComboBox();
        jTextField1 = new javax.swing.JTextField();
        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        dateChooserCombo2 = new datechooser.beans.DateChooserCombo();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jComboBox8 = new javax.swing.JComboBox();
        jLabel41 = new javax.swing.JLabel();
        jFormattedTextField8 = new javax.swing.JFormattedTextField();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        dateChooserCombo3 = new datechooser.beans.DateChooserCombo();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jButton17 = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jLabel50 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        dateChooserCombo4 = new datechooser.beans.DateChooserCombo();
        dateChooserCombo5 = new datechooser.beans.DateChooserCombo();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jComboBox9 = new javax.swing.JComboBox();
        jLabel31 = new javax.swing.JLabel();
        jComboBox10 = new javax.swing.JComboBox();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jComboBox11 = new javax.swing.JComboBox();
        jSeparator5 = new javax.swing.JSeparator();
        jFormattedTextField6 = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        jComboBox13 = new javax.swing.JComboBox();
        jLabel27 = new javax.swing.JLabel();
        dateChooserCombo6 = new datechooser.beans.DateChooserCombo();
        jLabel38 = new javax.swing.JLabel();
        dateChooserCombo7 = new datechooser.beans.DateChooserCombo();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jComboBox14 = new javax.swing.JComboBox();
        jButton3 = new javax.swing.JButton();
        dateChooserCombo11 = new datechooser.beans.DateChooserCombo();
        jPanel9 = new javax.swing.JPanel();
        dateChooserCombo9 = new datechooser.beans.DateChooserCombo();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        dateChooserCombo10 = new datechooser.beans.DateChooserCombo();
        jLabel60 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        jComboBox15 = new javax.swing.JComboBox();
        jLabel66 = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SISTEMA DE ASISTENCIA DE PERSONAL EDUCATIVO");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel2.setAutoscrolls(true);

        jLabel11.setText("Nombre:");

        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField7KeyTyped(evt);
            }
        });

        jLabel12.setText("Apellido:");

        jTextField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField8KeyTyped(evt);
            }
        });

        jLabel13.setText("Nro:");

        jLabel14.setText("CUIL:");

        jLabel19.setText("Sexo:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "MASCULINO", "FEMENINO" }));
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });

        jLabel20.setText("Correo Elec.:");

        jLabel21.setText("Departamento:");

        jComboBox3.setEnabled(false);
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jLabel23.setText("Estado civil:");

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SOLTERO", "CASADO", "VIUDO" }));

        jLabel24.setText("Cargo/horas:");

        jTextField16.setEnabled(false);
        jTextField16.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField16KeyTyped(evt);
            }
        });

        jLabel25.setText("Ingreso:");

        jLabel26.setText("Antiguedad:");

        jTextField17.setEnabled(false);
        jTextField17.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField17KeyTyped(evt);
            }
        });

        jLabel28.setText("Telefono:");

        jButton4.setText("Aceptar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CASA", "CEL", "OTRO" }));
        jComboBox4.setAutoscrolls(true);

        jTextField13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField13KeyTyped(evt);
            }
        });

        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setPreferredSize(new java.awt.Dimension(41, 23));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(jList1);

        jCheckBox1.setText("Jefe");
        jCheckBox1.setEnabled(false);
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jButton11.setEnabled(false);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setEnabled(false);
        jButton12.setPreferredSize(new java.awt.Dimension(41, 23));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jScrollPane5.setViewportView(jList2);

        jLabel15.setText("Tipo:");

        jComboBox6.setEnabled(false);

        jLabel17.setText("Calle:");

        jTextField11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField11KeyTyped(evt);
            }
        });

        jLabel18.setText("Nro:");

        jTextField14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField14KeyTyped(evt);
            }
        });

        jLabel22.setText("Piso:");

        jTextField18.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField18KeyTyped(evt);
            }
        });

        jLabel29.setText("Depto:");

        jTextField19.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField19KeyTyped(evt);
            }
        });

        jRadioButton3.setText("Docente");
        jRadioButton3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton3ItemStateChanged(evt);
            }
        });
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        jRadioButton4.setText("No docente");
        jRadioButton4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton4ItemStateChanged(evt);
            }
        });

        jLabel30.setText("Actividad:");

        jComboBox7.setEnabled(false);

        jTextField21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField21ActionPerformed(evt);
            }
        });
        jTextField21.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField21FocusLost(evt);
            }
        });
        jTextField21.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField21KeyTyped(evt);
            }
        });

        jFormattedTextField7.setEditable(false);
        try {
            jFormattedTextField7.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFormattedTextField7FocusLost(evt);
            }
        });

        jLabel48.setText("N");
        jLabel48.setPreferredSize(new java.awt.Dimension(15, 15));
        jLabel48.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel48MouseClicked(evt);
            }
        });

        jLabel49.setText("N");
        jLabel49.setMaximumSize(new java.awt.Dimension(15, 15));
        jLabel49.setMinimumSize(new java.awt.Dimension(15, 15));
        jLabel49.setPreferredSize(new java.awt.Dimension(15, 15));
        jLabel49.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel49MouseClicked(evt);
            }
        });

        jLabel55.setText("E");
        jLabel55.setMaximumSize(new java.awt.Dimension(15, 15));
        jLabel55.setMinimumSize(new java.awt.Dimension(15, 15));
        jLabel55.setPreferredSize(new java.awt.Dimension(15, 15));
        jLabel55.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel55MouseClicked(evt);
            }
        });

        jLabel56.setText("E");
        jLabel56.setPreferredSize(new java.awt.Dimension(15, 15));
        jLabel56.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel56MouseClicked(evt);
            }
        });

        jLabel58.setText("*");

        jLabel59.setText("*");

        jLabel61.setText("Fecha nac:");

        jLabel62.setText("*");

        jLabel63.setText("*");

        jLabel64.setText("Perfil:");

        jTextField1.setEditable(false);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel14)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel17))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel64))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel29))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel15)
                            .addComponent(jLabel61))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel59))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28)
                            .addComponent(jLabel20)
                            .addComponent(jLabel23)
                            .addComponent(jLabel25)
                            .addComponent(jLabel11)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(dateChooserCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel63))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(3, 3, 3)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jButton10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton9, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addGap(1, 1, 1)
                                    .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jFormattedTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel58)))
                        .addGap(39, 39, 39))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel62)
                        .addGap(382, 382, 382))))
            .addComponent(jSeparator3)
            .addComponent(jSeparator2)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jRadioButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel21))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jCheckBox1)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel26))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jRadioButton3))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jSeparator4)
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jTextField16, jTextField17});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton10, jButton11, jButton12, jButton9});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {dateChooserCombo1, dateChooserCombo2, jComboBox12, jComboBox2, jComboBox5, jComboBox6, jFormattedTextField7, jTextField1, jTextField11, jTextField7, jTextField8});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jFormattedTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel11)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel58)
                    .addComponent(jLabel59)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(dateChooserCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel63)
                    .addComponent(jLabel25)
                    .addComponent(jLabel62)
                    .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel61))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel19)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel23))
                    .addComponent(jComboBox5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel14)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(jComboBox12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel17)
                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18)
                            .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(jLabel29)
                                .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel22))
                            .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton11)
                                    .addComponent(jCheckBox1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26)))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton4)
                    .addComponent(jLabel30)
                    .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addContainerGap(137, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jTextField16, jTextField17});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton10, jButton11, jButton12, jButton9});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jSeparator2, jSeparator3, jSeparator4});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {dateChooserCombo1, dateChooserCombo2, jComboBox12, jComboBox2, jComboBox5, jComboBox6, jFormattedTextField7, jTextField1, jTextField11, jTextField7, jTextField8});

        jTabbedPane1.addTab("Datos del personal", jPanel2);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del familiar"));

        jLabel3.setText("Apellido:");

        jLabel4.setText("Nombre:");

        jLabel5.setText("Relación:");

        jLabel6.setText("Tipo:");

        jLabel7.setText("Nro:");

        jLabel8.setText("Fecha nac:");

        jLabel9.setText("Asignación familiar:");

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField2KeyTyped(evt);
            }
        });

        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField3KeyTyped(evt);
            }
        });

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº", "Apellido y Nombre", "Tipo doc", "Nro doc", "Relación", "Fecha nac", "Asignación"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable1MouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton2.setText("Aceptar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton7.setText("Eliminar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton5);
        jRadioButton5.setSelected(true);
        jRadioButton5.setText("No");
        jRadioButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton5ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton6);
        jRadioButton6.setText("Si");

        jLabel41.setText("N");
        jLabel41.setPreferredSize(new java.awt.Dimension(15, 15));
        jLabel41.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel41MouseClicked(evt);
            }
        });

        try {
            jFormattedTextField8.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel42.setText("*");

        jLabel43.setText("*");

        jLabel44.setText("*");

        jLabel45.setText("*");

        jLabel57.setText("E");
        jLabel57.setPreferredSize(new java.awt.Dimension(15, 15));
        jLabel57.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel57MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel44)
                                .addGap(48, 48, 48)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel7)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField3)
                            .addComponent(jFormattedTextField8, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                            .addComponent(dateChooserCombo3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel42)
                                .addComponent(jLabel43))
                            .addComponent(jLabel45, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton6)
                            .addComponent(jRadioButton5))))
                .addContainerGap(91, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
            .addComponent(jSeparator1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton7))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jRadioButton5, jRadioButton6});

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jComboBox1, jComboBox8, jTextField2});

        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel44))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(jFormattedTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel43)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel42))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel45)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel8)
                            .addComponent(dateChooserCombo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jRadioButton5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton6)
                        .addGap(4, 4, 4)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton7))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBox1, jComboBox8, jTextField2});

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(132, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Familiares", jPanel3);

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dia", "Hora inicio", "Hora fin"
            }
        ));
        jTable3.setRowHeight(25);
        jScrollPane3.setViewportView(jTable3);

        jButton6.setPreferredSize(new java.awt.Dimension(37, 23));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setText("Aceptar");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton14)
                .addContainerGap())
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton13, jButton6});

        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton13)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jButton14)
                .addContainerGap())
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton13, jButton6});

        jTabbedPane2.addTab("Activo", jPanel6);

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton17.setText("Aceptar");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jLabel34.setText("Motivo:");

        jLabel35.setText("Fecha Inicio:");

        jLabel36.setText("Fecha Fin:");

        jLabel37.setText("Instrumento Legal:");

        jButton8.setText("Eliminar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel50.setText("*");

        jLabel53.setText("*");

        jLabel54.setText("Son campos obligatorios");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel37)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel34)
                                    .addComponent(jLabel35))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(dateChooserCombo4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel36)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dateChooserCombo5, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(4, 4, 4)
                                .addComponent(jLabel50)))
                        .addGap(0, 137, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel53)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel54)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8)))
                .addContainerGap())
        );

        jPanel7Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {dateChooserCombo4, dateChooserCombo5});

        jPanel7Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton17, jButton8});

        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel34)
                        .addComponent(jLabel50))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel35)
                        .addComponent(dateChooserCombo4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel36)
                        .addComponent(dateChooserCombo5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel53)
                        .addComponent(jLabel54))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton17)
                        .addComponent(jButton8)))
                .addContainerGap())
        );

        jTabbedPane2.addTab("Inactivo", jPanel7);

        jLabel2.setText("Año:");

        jLabel1.setText("Observación:");

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField4KeyTyped(evt);
            }
        });

        jLabel16.setText("Establecimiento:");

        jComboBox9.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox9ItemStateChanged(evt);
            }
        });
        jComboBox9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox9ActionPerformed(evt);
            }
        });

        jLabel31.setText("Cargo:");

        jComboBox10.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox10ItemStateChanged(evt);
            }
        });
        jComboBox10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox10ActionPerformed(evt);
            }
        });

        jLabel32.setText("Nivel:");

        jLabel33.setText("Horas:");

        jTextField12.setEnabled(false);

        jButton1.setText("Nuevo");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton19.setText("Eliminar");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jComboBox11.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox11ItemStateChanged(evt);
            }
        });

        try {
            jFormattedTextField6.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField6.setEnabled(false);

        jLabel10.setText("Año:");

        jComboBox13.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        jComboBox13.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox13ItemStateChanged(evt);
            }
        });

        jLabel27.setText("Inicio:");

        jLabel38.setText("Fin:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addGap(55, 55, 55)
                        .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton19))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox13, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jFormattedTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateChooserCombo6, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel38)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateChooserCombo7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                    .addComponent(jSeparator5))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton19});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {dateChooserCombo6, dateChooserCombo7});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel10)
                    .addComponent(jComboBox13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27)
                    .addComponent(dateChooserCombo6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38)
                    .addComponent(dateChooserCombo7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel16)
                    .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton19)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33)
                    .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32)
                    .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(196, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton19});

        jTabbedPane1.addTab("Declaración Jurada", jPanel1);

        jTable2.setAutoCreateRowSorter(true);
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Inicio", "Fin", "Descripción", "Articulo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable2MouseReleased(evt);
            }
        });
        jScrollPane4.setViewportView(jTable2);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Seleccione una fecha"));

        jLabel39.setText("Fecha:");

        jLabel40.setText("Ver por:");

        jComboBox14.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TODO", "AÑO", "MES", "DIA" }));
        jComboBox14.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox14ItemStateChanged(evt);
            }
        });

        jButton3.setText("Buscar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        dateChooserCombo11.setCurrentView(new datechooser.view.appearance.AppearancesList("Grey",
            new datechooser.view.appearance.ViewAppearance("custom",
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    true,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 255),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(128, 128, 128),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(255, 0, 0),
                    false,
                    false,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                (datechooser.view.BackRenderer)null,
                false,
                true)));
    dateChooserCombo11.setBehavior(datechooser.model.multiple.MultyModelBehavior.SELECT_SINGLE);
    dateChooserCombo11.addSelectionChangedListener(new datechooser.events.SelectionChangedListener() {
        public void onSelectionChange(datechooser.events.SelectionChangedEvent evt) {
            dateChooserCombo11OnSelectionChange(evt);
        }
    });

    javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
    jPanel8.setLayout(jPanel8Layout);
    jPanel8Layout.setHorizontalGroup(
        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel8Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel39)
            .addGap(28, 28, 28)
            .addComponent(dateChooserCombo11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(47, 47, 47)
            .addComponent(jLabel40)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jComboBox14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton3)
            .addContainerGap())
    );
    jPanel8Layout.setVerticalGroup(
        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel8Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(jLabel39)
                .addComponent(jLabel40)
                .addComponent(jComboBox14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton3)
                .addComponent(dateChooserCombo11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Nueva licencia"));

    dateChooserCombo9.setCurrentView(new datechooser.view.appearance.AppearancesList("Grey",
        new datechooser.view.appearance.ViewAppearance("custom",
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                true,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 255),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(128, 128, 128),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.LabelPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.LabelPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(255, 0, 0),
                false,
                false,
                new datechooser.view.appearance.swing.ButtonPainter()),
            (datechooser.view.BackRenderer)null,
            false,
            true)));
dateChooserCombo9.setBehavior(datechooser.model.multiple.MultyModelBehavior.SELECT_SINGLE);
dateChooserCombo9.addSelectionChangedListener(new datechooser.events.SelectionChangedListener() {
    public void onSelectionChange(datechooser.events.SelectionChangedEvent evt) {
        dateChooserCombo9OnSelectionChange(evt);
    }
    });

    jLabel51.setText("Inicio:");

    jLabel52.setText("Fin:");

    dateChooserCombo10.setCurrentView(new datechooser.view.appearance.AppearancesList("Grey",
        new datechooser.view.appearance.ViewAppearance("custom",
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                true,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 255),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(128, 128, 128),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.LabelPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.LabelPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(255, 0, 0),
                false,
                false,
                new datechooser.view.appearance.swing.ButtonPainter()),
            (datechooser.view.BackRenderer)null,
            false,
            true)));
dateChooserCombo10.setBehavior(datechooser.model.multiple.MultyModelBehavior.SELECT_SINGLE);
dateChooserCombo10.addSelectionChangedListener(new datechooser.events.SelectionChangedListener() {
    public void onSelectionChange(datechooser.events.SelectionChangedEvent evt) {
        dateChooserCombo10OnSelectionChange(evt);
    }
    });

    jLabel60.setText("Descripción:");

    jLabel65.setText("Articulo:");

    jLabel66.setPreferredSize(new java.awt.Dimension(15, 15));
    jLabel66.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jLabel66MouseClicked(evt);
        }
    });

    jButton15.setText("Aceptar");
    jButton15.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton15ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
    jPanel9.setLayout(jPanel9Layout);
    jPanel9Layout.setHorizontalGroup(
        jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel9Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel51)
                .addComponent(jLabel60)
                .addComponent(jLabel65))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(dateChooserCombo9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox15, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addGap(3, 3, 3)
                            .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addGap(45, 45, 45)
                            .addComponent(jLabel52)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(dateChooserCombo10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addComponent(jTextField6))
            .addContainerGap(98, Short.MAX_VALUE))
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton15)
            .addContainerGap())
    );
    jPanel9Layout.setVerticalGroup(
        jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel9Layout.createSequentialGroup()
            .addGap(6, 6, 6)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(jLabel51)
                .addComponent(dateChooserCombo9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(dateChooserCombo10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel52))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65)
                    .addComponent(jComboBox15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel60)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jButton15)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jButton16.setText("Aceptar");

    jButton18.setText("Eliminar");
    jButton18.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton18ActionPerformed(evt);
        }
    });

    jButton20.setText("Modificar");
    jButton20.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton20ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
    jPanel4.setLayout(jPanel4Layout);
    jPanel4Layout.setHorizontalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel4Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jButton20)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jButton18)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jButton16)))
            .addContainerGap())
    );
    jPanel4Layout.setVerticalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButton16)
                .addComponent(jButton18)
                .addComponent(jButton20))
            .addContainerGap(134, Short.MAX_VALUE))
    );

    jTabbedPane1.addTab("Licencia", jPanel4);

    jButton5.setText("Salir");
    jButton5.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton5ActionPerformed(evt);
        }
    });

    jLabel46.setText("Son campos obligatorios");

    jLabel47.setText("*");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addGroup(layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(jLabel47)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel46)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5))
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(21, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel47)
                .addComponent(jLabel46)
                .addComponent(jButton5))
            .addContainerGap(21, Short.MAX_VALUE))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    private static class HeaderRenderer implements TableCellRenderer {
        DefaultTableCellRenderer renderer;
        public HeaderRenderer(JTable table) {
            renderer = (DefaultTableCellRenderer)
                table.getTableHeader().getDefaultRenderer();
            renderer.setHorizontalAlignment(JLabel.CENTER);
        }
        @Override
        public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int col) {
            return renderer.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, col);
        }
    }
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            Controlador auxx=new Controlador();
            Establecimiento estt=auxx.getPrimerEstablecimiento();
            Personal pppp=estt.getPersonal(pe.getIdPersonal());
            Tiporelacion rel= (Tiporelacion) jComboBox8.getSelectedItem();
            if(!jTextField2.getText().isEmpty() && !jTextField3.getText().isEmpty() && !jFormattedTextField8.getText().contains(" ") ){
                if(Drive.ControlFamiliar(pppp, rel,modificar)==true){
                    Establecimiento col= Drive.getPrimerEstablecimiento();
//                    SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
//                    formateador.setLenient(false);
                    Date fechanac = dateChooserCombo3.getSelectedDate().getTime();

                    Tipodoc tipodoc= (Tipodoc) jComboBox1.getSelectedItem();
                    String nro=jFormattedTextField8.getText();
                    nro = nro.replaceAll("[.-]", "");
                    Personal p=col.getPersonal(tipodoc,nro);
                    boolean bb=false;
                    Boolean valor=false;
                    if(jRadioButton6.isSelected()){
                        valor= true;
                        Iterator it=p.getPersonalFamiliarsForIdFamiliar().iterator();
                        while(it.hasNext()){
                            PersonalFamiliar perfam=(PersonalFamiliar)it.next();
                            if(perfam.getId().getIdFamiliar()==p.getIdPersonal()){
                                if(perfam.getAsignacionFamiliar()==true){
                                    bb=true;
                                }
                            }

                        }
                    }else if(jRadioButton5.isSelected()){
                        valor=false;
                    }
                    
                    
                    if(bb==false){
//                        Iterator itt=Drive.PERSISTENCIA.getPerfiles().iterator();
//                        Perfil perf=null;
//                        while(itt.hasNext()){
//                        Perfil per=(Perfil) itt.next();
//                            if(per.getNivel()==4){
//                                perf=per;
//                                break;
//                            }
//                        }
                        if(p.getIdPersonal()==null){
                            int i=col.crearPersonal(tipodoc,null, col, null, nro, jTextField2.getText().toUpperCase(), jTextField3.getText().toUpperCase(), null, null, WIDTH, null, null, null, null, null, null, false, true, fechanac, null, null,null, null, null,null, null, null,null,null, null,null, null);
                            PersonalFamiliarId id=new PersonalFamiliarId();
                            id.setIdPersonal(pe.getIdPersonal());
                            id.setIdFamiliar(i);
                            Personal fam= col.getPersonal(i);
                            pe.crearPersonalFamiliar(id, pe, fam, rel, valor);
                        }else{
                            PersonalFamiliarId id=new PersonalFamiliarId();
                            id.setIdPersonal(pe.getIdPersonal());
                            id.setIdFamiliar(p.getIdPersonal());
                            pe.crearPersonalFamiliar(id, pe, p, rel, valor);
                        }
                        if(p.getIdPersonal()!=null && p.getFamiliar()==false){
                            p.setFamiliar(true);
                            p.actualizarPersonal(p);
                        } 
                        if(p.getIdPersonal()!=null && p.getEstado()==true){
                            PersonalFamiliarId id=new PersonalFamiliarId();
                            id.setIdPersonal(pe.getIdPersonal());
                            id.setIdFamiliar(p.getIdPersonal());
                            Personal fam= p;
                            pe.crearPersonalFamiliar(id, pe, fam, rel, valor);
                            if(pe.getFamiliar()==false){
                                pe.setFamiliar(true);
                                pe.actualizarPersonal(pe);
                            }
                        }
                        if(modificar==true){
                            Controlador au=new Controlador();
                            Establecimiento ess=au.getPrimerEstablecimiento();
                            Personal pepe=ess.getPersonal(pe.getIdPersonal());
                            PersonalFamiliar pp=p.getPersonalFamiliar(pepe, p);
                            p.setApellido(jTextField2.getText().toUpperCase());
                            p.setNombre(jTextField3.getText().toUpperCase());
                            p.setFechaNac(fechanac);
                            pp.setTiporelacion(rel);
                            pp.setAsignacionFamiliar(valor);
                            pp.actualizarPersonalFamiliar(pp);
                            p.actualizarPersonal(p);
                        }

                        Controlador aux=new Controlador();
                        Establecimiento est=aux.getPrimerEstablecimiento();
                        Personal ppp=est.getPersonal(pe.getIdPersonal());
                        Drive.LimpiarTabla(jTable1);
                        Drive.CargarTablaflia(jTable1, ppp);
                        jFormattedTextField8.setText("");
                        jTextField2.setText("");
                        jTextField3.setText("");
                        jFormattedTextField8.enable(true);
                        jComboBox1.enable(true);
                        modificar=false;
                    }else{
                        JOptionPane.showMessageDialog( null, "Otra persona cobra asignación familiar por esta persona","Actualizar Personal", JOptionPane.ERROR_MESSAGE);
                        jFormattedTextField8.setText("");
                        jTextField2.setText("");
                        jTextField3.setText("");
                    }
                }else{
                    JOptionPane.showMessageDialog( null, "YA NO PUEDE ASIGNAR MAS PADRES A ESTA PERSONA","Actualizar Personal", JOptionPane.ERROR_MESSAGE);
                    jFormattedTextField8.setText("");
                    jTextField2.setText("");
                    jTextField3.setText("");
                }
            }else{
                JOptionPane.showMessageDialog(null,"Los campos con '*' son obligatorios y no pueden tener espacios en blanco","Actualizar Personal", JOptionPane.ERROR_MESSAGE);
//                jFormattedTextField5.setText("");
                jFormattedTextField8.setText("");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(),"Actualizar Personal", JOptionPane.ERROR_MESSAGE);
        }// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String sexo = (String) jComboBox2.getSelectedItem();
        String civil = (String) jComboBox5.getSelectedItem();
        Perfil perf=(Perfil) jComboBox12.getSelectedItem();
        
        if (!jTextField7.getText().isEmpty() && !jTextField8.getText().isEmpty()) {
            try {
                Date ingreso = dateChooserCombo2.getSelectedDate().getTime();
                Date fechanac= dateChooserCombo1.getSelectedDate().getTime();
                String validExpression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
                Pattern compare = Pattern.compile(validExpression, Pattern.CASE_INSENSITIVE);
                if (!jTextField21.getText().isEmpty()) {
                    Matcher matcher = compare.matcher(jTextField21.getText());
                    if (!matcher.matches()) {
                        Exception noesvalido = new Exception("INGRESE CORRECTAMENTE LA DIRECCION DE CORREO ELECTRONICO");
                        throw noesvalido;
                    }
                }
                Date hoy=new Date();
                Calendar cal = new GregorianCalendar();
                cal.setTimeInMillis(fechanac.getTime());
                cal.add(Calendar.YEAR, 18);
                Date aux=new Date(cal.getTimeInMillis());
                boolean mensaje=false;
                if(aux.compareTo(hoy)<=0){
                    if(pe.getPerfil().getIdPerfil()!=perf.getIdPerfil()){
                        // <editor-fold defaultstate="collapsed" desc="Auditoria"> 
                        Auditoria audi = new Auditoria();
                        audi.setPersonalByIdAuditor(adm);
                        audi.setOperacion("Actualizar");
                        audi.setFecha(new Date());
                        audi.setPersonalByIdPersonal(pe);
                        audi.setCampo("Perfil");
                        audi.setElementoAnterior(pe.getPerfil().getNombre());
                        audi.setElementoNuevo(perf.getNombre());
                        audi.guardarAuditoria(audi);
                        // </editor-fold>
                        pe.setPerfil(perf);
                        pe.actualizarPersonal(pe);
                        mensaje=true;
                    }
                    if(!pe.getApellido().equals(jTextField8.getText().toUpperCase())){
                        // <editor-fold defaultstate="collapsed" desc="Auditoria"> 
                        Auditoria audi = new Auditoria();
                        audi.setPersonalByIdAuditor(adm);
                        audi.setOperacion("Actualizar");
                        audi.setFecha(new Date());
                        audi.setPersonalByIdPersonal(pe);
                        audi.setCampo("Apellido");
                        audi.setElementoAnterior(pe.getApellido());
                        audi.setElementoNuevo(jTextField8.getText().toUpperCase());
                        audi.guardarAuditoria(audi);
                        // </editor-fold>
                        pe.setApellido(jTextField8.getText().toUpperCase());
                        pe.actualizarPersonal(pe);
                        mensaje=true;
                    }
                    if(!pe.getNombre().equals(jTextField7.getText().toUpperCase())){
                        // <editor-fold defaultstate="collapsed" desc="Auditoria"> 
                        Auditoria audi = new Auditoria();
                        audi.setPersonalByIdAuditor(adm);
                        audi.setOperacion("Actualizar");
                        audi.setFecha(new Date());
                        audi.setPersonalByIdPersonal(pe);
                        audi.setCampo("Nombre");
                        audi.setElementoAnterior(pe.getNombre());
                        audi.setElementoNuevo(jTextField7.getText().toUpperCase());
                        audi.guardarAuditoria(audi);
                        // </editor-fold>
                        pe.setNombre(jTextField7.getText().toUpperCase());
                        pe.actualizarPersonal(pe);
                        mensaje=true;
                    }
                    if(!pe.getCuil().equals(jTextField1.getText())){
                        // <editor-fold defaultstate="collapsed" desc="Auditoria"> 
                        Auditoria audi = new Auditoria();
                        audi.setPersonalByIdAuditor(adm);
                        audi.setOperacion("Actualizar");
                        audi.setFecha(new Date());
                        audi.setPersonalByIdPersonal(pe);
                        audi.setCampo("Cuil");
                        audi.setElementoAnterior(pe.getCuil());
                        audi.setElementoNuevo(jTextField1.getText().toUpperCase());
                        audi.guardarAuditoria(audi);
                        // </editor-fold>
                        pe.setCuil(jTextField1.getText());
                        pe.actualizarPersonal(pe);
                        mensaje=true;
                    }
                    if(!pe.getCalle().equals(jTextField11.getText().toUpperCase())){
                        // <editor-fold defaultstate="collapsed" desc="Auditoria"> 
                        Auditoria audi = new Auditoria();
                        audi.setPersonalByIdAuditor(adm);
                        audi.setOperacion("Actualizar");
                        audi.setFecha(new Date());
                        audi.setPersonalByIdPersonal(pe);
                        audi.setCampo("Calle");
                        audi.setElementoAnterior(pe.getCalle());
                        audi.setElementoNuevo(jTextField11.getText().toUpperCase());
                        audi.guardarAuditoria(audi);
                        // </editor-fold>
                        pe.setCalle(jTextField11.getText().toUpperCase());
                        pe.actualizarPersonal(pe);
                        mensaje=true;
                    }
                    int i = 0;
                    if (!jTextField14.getText().isEmpty()) {
                        i = Integer.parseInt(jTextField14.getText());
                    }
                    if(pe.getAltura()!=i){
                        // <editor-fold defaultstate="collapsed" desc="Auditoria"> 
                        Auditoria audi = new Auditoria();
                        audi.setPersonalByIdAuditor(adm);
                        audi.setOperacion("Actualizar");
                        audi.setFecha(new Date());
                        audi.setPersonalByIdPersonal(pe);
                        audi.setCampo("Altura");
                        audi.setElementoAnterior(String.valueOf(pe.getAltura()));
                        audi.setElementoNuevo(String.valueOf(i));
                        audi.guardarAuditoria(audi);
                        // </editor-fold>
                        pe.setAltura(i);
                        pe.actualizarPersonal(pe);
                        mensaje=true;
                    }
                    if(!pe.getPiso().equals(jTextField18.getText().toUpperCase())){
                        // <editor-fold defaultstate="collapsed" desc="Auditoria"> 
                        Auditoria audi = new Auditoria();
                        audi.setPersonalByIdAuditor(adm);
                        audi.setOperacion("Actualizar");
                        audi.setFecha(new Date());
                        audi.setPersonalByIdPersonal(pe);
                        audi.setCampo("Piso");
                        audi.setElementoAnterior(pe.getPiso());
                        audi.setElementoNuevo(jTextField18.getText().toUpperCase());
                        audi.guardarAuditoria(audi);
                        // </editor-fold>
                        pe.setPiso(jTextField18.getText().toUpperCase());
                        pe.actualizarPersonal(pe);
                        mensaje=true;
                    }
                    if(!pe.getDepto().equals(jTextField19.getText().toUpperCase())){
                        // <editor-fold defaultstate="collapsed" desc="Auditoria"> 
                        Auditoria audi = new Auditoria();
                        audi.setPersonalByIdAuditor(adm);
                        audi.setOperacion("Actualizar");
                        audi.setFecha(new Date());
                        audi.setPersonalByIdPersonal(pe);
                        audi.setCampo("Departamento");
                        audi.setElementoAnterior(pe.getDepto());
                        audi.setElementoNuevo(jTextField19.getText().toUpperCase());
                        audi.guardarAuditoria(audi);
                        // </editor-fold>
                        pe.setDepto(jTextField19.getText().toUpperCase());
                        pe.actualizarPersonal(pe);
                        mensaje=true;
                    }
                    if(!pe.getCorreoElectronico().equals(jTextField21.getText())){
                        // <editor-fold defaultstate="collapsed" desc="Auditoria"> 
                        Auditoria audi = new Auditoria();
                        audi.setPersonalByIdAuditor(adm);
                        audi.setOperacion("Actualizar");
                        audi.setFecha(new Date());
                        audi.setPersonalByIdPersonal(pe);
                        audi.setCampo("Correo Electrónico");
                        audi.setElementoAnterior(pe.getCorreoElectronico());
                        audi.setElementoNuevo(jTextField21.getText());
                        audi.guardarAuditoria(audi);
                        // </editor-fold>
                        pe.setCorreoElectronico(jTextField21.getText());
                        pe.actualizarPersonal(pe);
                        mensaje=true;
                    }
                    if(!pe.getSexo().equals(sexo)){
                        // <editor-fold defaultstate="collapsed" desc="Auditoria"> 
                        Auditoria audi = new Auditoria();
                        audi.setPersonalByIdAuditor(adm);
                        audi.setOperacion("Actualizar");
                        audi.setFecha(new Date());
                        audi.setPersonalByIdPersonal(pe);
                        audi.setCampo("Sexo");
                        audi.setElementoAnterior(pe.getSexo());
                        audi.setElementoNuevo(sexo);
                        audi.guardarAuditoria(audi);
                        // </editor-fold>
                        pe.setSexo(sexo);
                        pe.actualizarPersonal(pe);
                        mensaje=true;
                    }
                    if(!pe.getEstadoCivil().equals(civil)){
                        // <editor-fold defaultstate="collapsed" desc="Auditoria"> 
                        Auditoria audi = new Auditoria();
                        audi.setPersonalByIdAuditor(adm);
                        audi.setOperacion("Actualizar");
                        audi.setFecha(new Date());
                        audi.setPersonalByIdPersonal(pe);
                        audi.setCampo("Estado Civil");
                        audi.setElementoAnterior(pe.getEstadoCivil());
                        audi.setElementoNuevo(civil);
                        audi.guardarAuditoria(audi);
                        // </editor-fold>
                        pe.setEstadoCivil(civil);
                        pe.actualizarPersonal(pe);
                        mensaje=true;
                    }
                    Date auxx= pe.getIngreso();
                    if(auxx.getDate()!=ingreso.getDate()||auxx.getMonth()!=ingreso.getMonth()||auxx.getYear()!=ingreso.getYear()){
                        // <editor-fold defaultstate="collapsed" desc="Auditoria"> 
                        Auditoria audi = new Auditoria();
                        audi.setPersonalByIdAuditor(adm);
                        audi.setOperacion("Actualizar");
                        audi.setFecha(new Date());
                        audi.setPersonalByIdPersonal(pe);
                        audi.setCampo("Ingreso");
                        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                        audi.setElementoAnterior(formateador.format(pe.getIngreso()));
                        audi.setElementoNuevo(formateador.format(auxx));
                        audi.guardarAuditoria(audi);
                        // </editor-fold>
                        pe.setIngreso(ingreso);
                        pe.actualizarPersonal(pe);
                        mensaje=true;
                    }
                    Date auxxx= pe.getFechaNac();
                    if(auxxx.getDate()!=fechanac.getDate()||auxxx.getMonth()!=fechanac.getMonth()||auxxx.getYear()!=fechanac.getYear()){
                        // <editor-fold defaultstate="collapsed" desc="Auditoria"> 
                        Auditoria audi = new Auditoria();
                        audi.setPersonalByIdAuditor(adm);
                        audi.setOperacion("Actualizar");
                        audi.setFecha(new Date());
                        audi.setPersonalByIdPersonal(pe);
                        audi.setCampo("Fecha Nacimiento");
                        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                        audi.setElementoAnterior(formateador.format(pe.getFechaNac()));
                        audi.setElementoNuevo(formateador.format(auxxx));
                        audi.guardarAuditoria(audi);
                        // </editor-fold>
                        pe.setFechaNac(fechanac);
                        pe.actualizarPersonal(pe);
                        mensaje=true;
                    }
                    if(pe.getEstado()==false){
                        // <editor-fold defaultstate="collapsed" desc="Auditoria"> 
                        Auditoria audi = new Auditoria();
                        audi.setPersonalByIdAuditor(adm);
                        audi.setOperacion("Actualizar");
                        audi.setFecha(new Date());
                        audi.setPersonalByIdPersonal(pe);
                        audi.setCampo("Estado");
                        audi.setElementoAnterior("Activo");
                        audi.setElementoNuevo("Inactivo");
                        audi.guardarAuditoria(audi);
                        // </editor-fold>
                        pe.setEstado(true);
                        pe.actualizarPersonal(pe);
                        mensaje=true;
                    }
//                    if(!pe.getCalle().equals(jTextField11.getText().toUpperCase())){
//                        pe.setFamiliar(false);
//                        pe.actualizarPersonal(pe);
//                        // <editor-fold defaultstate="collapsed" desc="Auditoria"> 
//                        Auditoria audi = new Auditoria();
//                        audi.setPersonalByIdAuditor(adm);
//                        audi.setOperacion("Actualizar");
//                        audi.setFecha(new Date());
//                        audi.setPersonalByIdPersonal(pe);
//                        audi.setCampo("Imagen");
//                        audi.guardarAuditoria(audi);
//                        audi.setCampo("Calle");
//                        // </editor-fold>
//                    }
                    
//                    
//                    pe.actualizarPersonal(pe);
//                    // <editor-fold defaultstate="collapsed" desc="Auditoria"> 
//                    Auditoria audi = new Auditoria();
//                    audi.setPersonalByIdAuditor(adm);
//                    audi.setOperacion("Actualizar");
//                    audi.setFecha(new Date());
//                    audi.setPersonalByIdPersonal(pe);
//                    audi.setCampo("Imagen");
//                    audi.guardarAuditoria(audi);    
//                    // </editor-fold>
                    
                    if (jRadioButton3.isSelected()) {
                        Personaldocente perdoc = pe.getPersonaldoc(pe.getIdPersonal());
                        int m = 0;
                        if (!jTextField16.getText().isEmpty()) {
                            m = Integer.parseInt(jTextField16.getText());
                        }
                        int n = 0;
                        if (!jTextField17.getText().isEmpty()) {
                            n = Integer.parseInt(jTextField17.getText());
                        }
                        if (perdoc.getId() != null) {
                            if (perdoc.getCargohoras() != m || perdoc.getAntiguedadDoc() != n) {
                                perdoc.setCargohoras(m);
                                perdoc.setAntiguedadDoc(n);
                                perdoc.actualizarPersonaldoc(perdoc);
                                mensaje=true;
                            }
                        } else {
                            PersonaldocenteId iddoc = new PersonaldocenteId();
                            iddoc.setIdPersonal(pe.getIdPersonal());
                            pe.crearPersonaldoc(iddoc, pe, m, n);
                            mensaje=true;
                        }
                    } else if (jRadioButton3.isSelected() == false) {
                        Personaldocente perdoc = pe.getPersonaldoc(pe.getIdPersonal());
                        if (perdoc.getId() != null) {
                            perdoc.eliminarPersonaldoc(perdoc);
                            mensaje=true;
                        }
                        int c = 0;
                        while (modeloLista2.size() != c) {
                            PersonalDepartamento perdepto = (PersonalDepartamento) modeloLista2.getElementAt(c);
                            perdepto.eliminarPersonalDepartamento(perdepto);
                            mensaje=true;
                            c++;
                        }
                    }
                    if (jRadioButton4.isSelected()) {
                        Personalnodocente pernodoc = pe.getPersonalnodoc(pe.getIdPersonal());
                        if (pernodoc.getId() != null) {
                            if (pernodoc.getActividad() != (Actividad) jComboBox7.getSelectedItem()) {
                                pernodoc.setActividad((Actividad) jComboBox7.getSelectedItem());
                            }
                            pernodoc.actualizarPersonalnodoc(pernodoc);
                            mensaje=true;
                        } else {
                            PersonalnodocenteId id = new PersonalnodocenteId();
                            id.setIdPersonal(pe.getIdPersonal());
                            pe.crearPersonalnodoc(id, pe, (Actividad) jComboBox7.getSelectedItem());
                            mensaje=true;
                        }

                    } else if (jRadioButton4.isSelected() == false) {
                        Personalnodocente pernodoc = pe.getPersonalnodoc(pe.getIdPersonal());
                        if (pernodoc.getId() != null) {
                            pernodoc.eliminarPersonalnodoc(pernodoc);
                            mensaje=true;
                        }
                    }
                    if(mensaje==true){
                        JOptionPane.showMessageDialog(null,"La persona se actualizó correctamente","Actualizar Personal",JOptionPane.INFORMATION_MESSAGE);
                    }
                    Frame vp=new JFrameConsulta(Drive,adm);
                    this.dispose();
                    vp.show();
                }else{
                    JOptionPane.showMessageDialog(null,"El Personal debe ser mayor de edad","Actualizar Personal", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.toString(), "Actualizar Personal", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null,"Los campos con '*' son obligatorios y no pueden tener espacios en blanco en la fecha","Actualizar Personal", JOptionPane.ERROR_MESSAGE);
            jTextField1.setText("");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if(bb==true){
            Frame vp=new JFrameHistorial(Drive,adm);
            this.dispose();
            vp.show();
        }else if(!jTextField7.getText().equals(pe.getNombre())|| 
                !jTextField8.getText().equals(pe.getApellido())||
                !jTextField21.getText().equals(pe.getCorreoElectronico())||
                !jTextField11.getText().equals(pe.getCalle())||
                !jTextField14.getText().equals(String.valueOf(pe.getAltura()))||
                !jTextField18.getText().equals(pe.getPiso())||
                !jTextField19.getText().equals(pe.getDepto())){
            int confirmado = JOptionPane.showConfirmDialog(null,"¿Desea cancelar la actualización del personal?","Actualizar Personal",JOptionPane.YES_NO_OPTION);
            if (JOptionPane.OK_OPTION == confirmado){
               Frame vp=new JFrameConsulta(Drive,adm);
                this.dispose();
                vp.show();
            }
        }else{
            Frame vp=new JFrameConsulta(Drive,adm);
            this.dispose();
            vp.show();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {
            Establecimiento col=(Establecimiento) Drive.getPrimerEstablecimiento();
            Object i= jTable1.getValueAt(jTable1.getSelectedRow(),0);
            
            int id=Integer.parseInt(i.toString());
            Personal per=col.getPersonal(id);
            Boolean est=pe.getEstadoPersonalFamiliar(pe,per);
            if(per.getFamiliar()==true){
                per.setFamiliar(false);
                per.actualizarPersonal(per);
            }
            if(est==false){
                pe.setFamiliar(false);
                pe.actualizarPersonal(pe);
            }
            Drive.LimpiarTabla(jTable1);
            Drive.CargarTablaflia(jTable1, pe);
//            Familiar fam = per.getFamiliar(per, rel, apel, nom, cargo, doc, ingreso, as);
//            per.eliminarFamiliar(fam);
//            modelo.removeRow(jTable1.getSelectedRow());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(),"Actualizar Personal", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
            Telefono tel=new Telefono();
            tel.setPersonal(pe);
            tel.setNombre((String)jComboBox4.getSelectedItem());
            tel.setNumero(jTextField13.getText());
            modeloLista.addElement(tel);
            tel.guardarTelefono(tel);
            jList1.setModel(modeloLista);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        if(jList1.getSelectedIndex()>=0){    
            Telefono tel=(Telefono) modeloLista.getElementAt(jList1.getSelectedIndex());
            modeloLista.removeElementAt(jList1.getSelectedIndex());// TODO add your handling code here:
            tel.eliminarTelefono(tel);
            jList1.setModel(modeloLista);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        Departamento depto=(Departamento)jComboBox3.getSelectedItem();
        boolean band=true;
        boolean band2=true;
        int c=0;
        while(modeloLista2.size()!=c){
            PersonalDepartamento pdepto=(PersonalDepartamento) modeloLista2.getElementAt(c);
            if(pdepto.getId().getIdDepartamento()==depto.getIdDepartamento()){
                JOptionPane.showMessageDialog(null, "Ya agregó este departamento","Actualizar Personal", JOptionPane.ERROR_MESSAGE);
                band2=false;
                break;
            }
            c++;
        }
        if(jCheckBox1.isSelected()&&band2==true){
            Iterator it=Drive.PERSISTENCIA.getPersonalDepto().iterator();
            while(it.hasNext()){
                PersonalDepartamento pdepto=(PersonalDepartamento) it.next();
                if(pdepto.getDepartamento().getIdDepartamento()==depto.getIdDepartamento()&&pdepto.getJefe()){
                    JOptionPane.showMessageDialog(null, "El jefe de este departamento es "+pdepto.getPersonal().toString(),"Actualizar Personal", JOptionPane.ERROR_MESSAGE);
                    band=false;
                    break;
                }
            }
        }
        if(band==true && band2==true){
            PersonalDepartamentoId id=new PersonalDepartamentoId(pe.getIdPersonal(),depto.getIdDepartamento());
            PersonalDepartamento perdepto=new PersonalDepartamento();
            perdepto.setId(id);
            perdepto.setDepartamento(depto);
            perdepto.setPersonal(pe);
            perdepto.setJefe(true);
            perdepto.guardarPersonalDepartamento(perdepto);
            modeloLista2.addElement(perdepto);  
        }else if(!jCheckBox1.isSelected() && band2==true){
            //Personal per=new Personal();
            PersonalDepartamentoId id=new PersonalDepartamentoId();
            PersonalDepartamento perdepto=new PersonalDepartamento();
            id.setIdDepartamento(depto.getIdDepartamento());
            perdepto.setId(id);
            perdepto.setDepartamento(depto);
            perdepto.setPersonal(pe);
            perdepto.setJefe(false);
            perdepto.guardarPersonalDepartamento(perdepto);
            modeloLista2.addElement(perdepto);  
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        if(jRadioButton3.isSelected()){
            PersonalDepartamento perdepto=(PersonalDepartamento) modeloLista2.getElementAt(jList2.getSelectedIndex());
            modeloLista2.removeElementAt(jList2.getSelectedIndex());        // TODO add your handling code here:
            perdepto.eliminarPersonalDepartamento(perdepto);
            jList2.setModel(modeloLista2);
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton5ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jComboBox9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox9ActionPerformed
      // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox9ActionPerformed

    private void jComboBox9ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox9ItemStateChanged
        try {
            if (jComboBox9.getModel().getSize() != 0) {
                DetalleEstablecimiento c = (DetalleEstablecimiento) jComboBox9.getSelectedItem();
                Drive.CargarComboCargoPersonal(jComboBox10, c);
            } else {
                Drive.LimpiarCombo(jComboBox10);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(),"Actualizar Personal", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jComboBox9ItemStateChanged

    private void jComboBox10ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox10ItemStateChanged
        try {
            if (jComboBox10.getModel().getSize() != 0) {
                Cargo carg = (Cargo) jComboBox10.getSelectedItem();
                Drive.CargarComboNivelPersonal(jComboBox11, carg);
            } else {
                Drive.LimpiarCombo(jComboBox11);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(),"Actualizar Personal", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jComboBox10ItemStateChanged

    private void jComboBox10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox10ActionPerformed

    private void jComboBox11ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox11ItemStateChanged
        try{
            if(jComboBox11.getModel().getSize()!=0){
                Nivel nivels=(Nivel) jComboBox11.getSelectedItem();
                //Nivel niv=(Nivel)Drive.PERSISTENCIA.NivelPerso(nivels.getIdNivel()).iterator().next();
                jTextField12.setText(String.valueOf(nivels.getHoras()));
                Drive.CargarTablaActivo(jTable3, nivels);  
                if(!nivels.getInactivos().isEmpty()){
                    Inactivo in=nivels.getInactivos().iterator().next();
                    jTextField5.setText(in.getMotivo());
//                    SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                    Calendar cal=Calendar.getInstance();
                    cal.setTime(in.getFechaInicio());
                    dateChooserCombo4.setSelectedDate(cal);
                    cal.setTime(in.getFechaFin());
                    dateChooserCombo5.setSelectedDate(cal);
//                    jFormattedTextField3.setValue(formateador.format(in.getFechaInicio()));
//                    jFormattedTextField4.setValue(formateador.format(in.getFechaFin()));
                    jTextField20.setText(in.getInstrumentoLegal());
                }else{
                    jTextField5.setText("");
                    jTextField20.setText("");
                }
                //Drive.CargarTablaInactivo(jTable2, nivels);
            }else{jTextField12.setText("");}
        }catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(),"Actualizar Personal", JOptionPane.ERROR_MESSAGE);
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox11ItemStateChanged

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        DefaultTableModel temp = (DefaultTableModel) jTable3.getModel();        
        Object nuevo[]= {};
        temp.addRow(nuevo);

    }//GEN-LAST:event_jButton6ActionPerformed
    
    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        DefaultTableModel temp = (DefaultTableModel) jTable3.getModel();
        temp.removeRow(temp.getRowCount()-1);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            int ano = Integer.valueOf(jFormattedTextField6.getText());
            Declaracionjurada dec = pe.getDeclaracionjurada(ano);
            if (dec.getIdDeclaracionjurada() == null) {
                Anolectivo an = pe.getEstablecimiento().getAnoLectivo(ano);
                if(an.getIdAnolectivo()==null){
                    an.setEstablecimiento(pe.getEstablecimiento());
                    an.setAno(ano);
                    an.guardarAnolectivo(an);
                }
                dec.setObservacion(jTextField4.getText());
                dec.setPersonal(pe);
                dec.setAnolectivo(an);
                dec.guardarDeclaracionjurada(dec);
            } else {
                if (!dec.getObservacion().equals(jTextField4.getText())) {
                    dec.setObservacion(jTextField4.getText());
                    dec.ActualizarDeclaracionjurada(dec);
                }
            }
            decju = dec;
            JFramenuevadeclaracion ventdec = new JFramenuevadeclaracion(Drive, pe, decju, adm);
            this.setVisible(false);
            ventdec.show();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error al registrar una nueva declaración jurada", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
              // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseClicked

    private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseReleased
        if(evt.getClickCount()==2){
            Establecimiento col= Drive.getPrimerEstablecimiento();
            jTable1.getModel();
            int fila = jTable1.rowAtPoint(evt.getPoint());
            if ((fila > -1)){
                Personal per=col.getPersonal(Integer.parseInt(jTable1.getValueAt(fila,0).toString()));
                PersonalFamiliar perfam=per.getPersonalFamiliar(pe, per);
                jComboBox1.setSelectedItem(per.getTipodoc());
                jComboBox1.enable(false);
                jFormattedTextField8.setText(per.getDni());
                jFormattedTextField8.enable(false);
                jTextField2.setText(per.getApellido());
                jTextField3.setText(per.getNombre());
                
//                SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                Calendar cal=Calendar.getInstance();
                cal.setTime(per.getFechaNac());
                dateChooserCombo3.setSelectedDate(cal);
                if(perfam.getAsignacionFamiliar()==true){
                    jRadioButton6.setSelected(true);
                }else{jRadioButton5.setSelected(true);}
                
                //Tiporelacion tip=perfam.getTiporelacion();
                Tiporelacion tip=Drive.gettiporelacion(perfam.getTiporelacion());
                jComboBox8.setSelectedItem(tip);
            }
            
            modificar=true;
//            JFrameActualizarPersonal ventdec = new JFrameActualizarPersonal(Drive,per,null,null,null);
//            this.hide();
//            ventdec.show();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseReleased

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        try {
            if(!jTextField5.getText().isEmpty()){
                Nivel nivels=(Nivel) jComboBox11.getSelectedItem();
                if(nivels.getInactivos().isEmpty()){
                    Inactivo in=new Inactivo();
                    in.setNivel(nivels);
                    in.setMotivo(jTextField5.getText());
//                    SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
//                    formateador.setLenient(false);
                    in.setFechaInicio(dateChooserCombo4.getSelectedDate().getTime());
                    in.setFechaFin(dateChooserCombo5.getSelectedDate().getTime());
                    in.setInstrumentoLegal(jTextField20.getText());
                    in.guardarInactivo(in);
                }else{
                    Inactivo in=nivels.getInactivos().iterator().next();
                    in.setMotivo(jTextField5.getText());
//                    SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
//                    formateador.setLenient(false);
                    in.setFechaInicio(dateChooserCombo4.getSelectedDate().getTime());
                    in.setFechaFin(dateChooserCombo5.getSelectedDate().getTime());
                    in.setInstrumentoLegal(jTextField20.getText());
                    in.actualizarInactivo(in);
                }
                Personal pp=Drive.getPersonal(pe.getTipodoc(), pe.getDni());
                pe=pp;
            }
         } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
         }           // TODO add your handling code here:
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
         try {
                Nivel nivels=(Nivel) jComboBox11.getSelectedItem();
                Iterator it=Drive.PERSISTENCIA.getActivos(nivels.getIdNivel()).iterator();
                //ELIMINA TODOS LOS ACTIVOS DE LA BD
                while(it.hasNext()){
                    Activo act=(Activo) it.next();
                    Iterator<ActivoIniciofin> itt=act.getActivoIniciofins().iterator();
                    while(itt.hasNext()){
                        ActivoIniciofin actini= itt.next();
                        actini.eliminarActivoiniciofin(actini);
                    }
                    act.eliminarActivo(act);
                }
                
                DefaultTableModel modelo = (DefaultTableModel)jTable3.getModel();
                int e=0;
                while(modelo.getRowCount()!=e){
                    Activo ac=new Activo();
                    ac.setNivel(nivels);
                    ac.setDia(modelo.getValueAt(e,0).toString());
                    Boolean existe=nivels.existeActivo(ac, nivels);
                    if(existe==false){
                        ac.guardarActivo(ac);
                        ActivoIniciofin inifin=new ActivoIniciofin();
                        inifin.setActivo(ac);
                        SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                        formateador.setLenient(false);
                        inifin.setInicio(formateador.parse(modelo.getValueAt(e, 1).toString()));
                        inifin.setFin(formateador.parse(modelo.getValueAt(e, 2).toString()));
                        inifin.guardarActivoiniciofin(inifin);
                    }else{
                        Activo aux=nivels.getActivo(ac,nivels);
                        ActivoIniciofin inifin=new ActivoIniciofin();
                        inifin.setActivo(aux);
                        SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                        formateador.setLenient(false);
                        String in=modelo.getValueAt(e, 1).toString();
                        String fi=modelo.getValueAt(e, 2).toString();
                        inifin.setInicio(formateador.parse(in));
                        inifin.setFin(formateador.parse(fi));
                        inifin.guardarActivoiniciofin(inifin);
                    }
                    e++;
                }
                Personal pp=Drive.getPersonal(pe.getTipodoc(), pe.getDni());
                pe=pp;
                //Nivel niv=(Nivel)Drive.PERSISTENCIA.NivelPerso(nivels.getIdNivel()).iterator().next();
                Drive.CargarTablaActivo(jTable3, nivels);
//                int ii= Integer.valueOf(jFormattedTextField6.getText());
//                Drive.CargarComboEstablecimientosPerso(jComboBox9, pe,ii);
             } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.toString());
            }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        Nivel nivels=(Nivel) jComboBox11.getSelectedItem();
        if(!nivels.getInactivos().isEmpty()){
            Inactivo in = nivels.getInactivos().iterator().next();
            in.eliminarInactivo(in);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTextField21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField21ActionPerformed

    private void jTextField21FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField21FocusLost
        if(!jTextField21.getText().isEmpty()){
            boolean isValidEmail = false;
            String validExpression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern compare = Pattern.compile(validExpression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = compare.matcher(jTextField21.getText());
            if(matcher.matches()) {
                isValidEmail = true;
            }else {
                JOptionPane.showMessageDialog(null, "INGRESE CORRECTAMENTE LA DIRECCION DE CORREO ELECTRONICO","Actualizar Personal", JOptionPane.ERROR_MESSAGE);
                jTextField21.setText("");
            }

        }
    }//GEN-LAST:event_jTextField21FocusLost

    private void jTextField16KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField16KeyTyped
        char car = evt.getKeyChar();
        if(jRadioButton3.isSelected()){
            if((car<'0' || car>'9')) evt.consume();
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar 'DOCENTE' ","Actualizar Personal", JOptionPane.ERROR_MESSAGE);
            evt.consume();
        }
    }//GEN-LAST:event_jTextField16KeyTyped

    private void jTextField17KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField17KeyTyped
        char car = evt.getKeyChar();
        if(jRadioButton3.isSelected()){
            if((car<'0' || car>'9')) evt.consume();
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar 'DOCENTE' ","Actualizar Personal", JOptionPane.ERROR_MESSAGE);
            evt.consume();
        }
    }//GEN-LAST:event_jTextField17KeyTyped

    private void jLabel41MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel41MouseClicked
        JTextField salida = new JTextField();
        String cadSalida;
        salida.setText("");
        salida.setSize(25, 25);

        JOptionPane.showMessageDialog(null,salida, "Ingrese una nueva relación", JOptionPane.INFORMATION_MESSAGE);
        if(!salida.getText().isEmpty()&&salida.getText().length()<=20){
            cadSalida = salida.getText().toUpperCase();
            Iterator it=Drive.PERSISTENCIA.getRelaciones().iterator();
            boolean w=false;
            while(it.hasNext()){
                Tiporelacion tip=(Tiporelacion) it.next();
                if(tip.getRelacion().equals(cadSalida)){
                    JOptionPane.showMessageDialog(null, "La relación ya existe","Registrar Relación", JOptionPane.ERROR_MESSAGE);
                    w=true;
                }
            }
            if(w==false){
                Tiporelacion tip= new Tiporelacion();
                tip.setRelacion(cadSalida);
                tip.guardarTiporelacion(tip);
                Drive.LimpiarCombo(jComboBox8);
                Drive.CargarComboRelacion(jComboBox8);
                jComboBox8.setSelectedItem(tip);
            }
        }else {
                JOptionPane.showMessageDialog(null, "La relación no puede estar vacio y puede contener hasta 20 caracteres", "Registrar Relación", JOptionPane.ERROR_MESSAGE);
            }
    }//GEN-LAST:event_jLabel41MouseClicked

    private void jLabel48MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel48MouseClicked
        JTextField salida = new JTextField();
        String cadSalida;
        salida.setText("");
        salida.setSize(25, 25);
        salida.setFocusable(true);
        JOptionPane.showMessageDialog(null,salida, "Ingrese un nuevo Departamento", JOptionPane.INFORMATION_MESSAGE);
        
        if(!salida.getText().isEmpty()&&salida.getText().length()<=45){
            cadSalida = salida.getText().toUpperCase();
            Iterator it=Drive.PERSISTENCIA.getDepartamentos().iterator();
            boolean w=false;
            while(it.hasNext()){
                Departamento tip=(Departamento) it.next();
                if(tip.getNombre().equals(cadSalida)){
                    JOptionPane.showMessageDialog(null, "El departamento ya existe","Registrar Departamento", JOptionPane.ERROR_MESSAGE);
                    w=true;
                }
            }
            if(w==false){
                Departamento tip= new Departamento();
                tip.setNombre(cadSalida);
                tip.setEstablecimiento(Drive.getPrimerEstablecimiento());
                tip.guardarDepartamento(tip);
                Drive.LimpiarCombo(jComboBox3);
                Drive.CargarComboDepartamento(jComboBox3);
                jComboBox3.setSelectedItem(tip);
            }
        }else{
            JOptionPane.showMessageDialog(null, "El departamentono no puede estar vacio y puede contener hasta 45 caracteres","Registrar Departamento", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jLabel48MouseClicked

    private void jLabel49MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel49MouseClicked
        JTextField salida = new JTextField();
        String cadSalida;
        salida.setText("");
        salida.setSize(25, 25);
        
        JOptionPane.showMessageDialog(null,salida, "Ingrese una nueva actividad", JOptionPane.INFORMATION_MESSAGE);
        if(!salida.getText().isEmpty()&&salida.getText().length() <= 45){
            cadSalida = salida.getText().toUpperCase();
            Iterator it=Drive.PERSISTENCIA.getActividades().iterator();
            boolean w=false;
            while(it.hasNext()){
                Actividad tip=(Actividad) it.next();
                if(tip.getActividad().equals(cadSalida)){
                    JOptionPane.showMessageDialog(null, "La actividad ya existe","Registrar Actividad", JOptionPane.ERROR_MESSAGE);
                    w=true;
                }
            }
            if(w==false){
                Actividad tip= new Actividad();
                tip.setActividad(cadSalida);
                tip.guardarActividad(tip);
                Drive.LimpiarCombo(jComboBox7);
                Drive.CargarComboActividad(jComboBox7);
                jComboBox6.setSelectedItem(tip);
            }
        }else{
            JOptionPane.showMessageDialog(null, "La actividad no puede estar vacio y puede contener hasta 45 caracteres","Registrar Actividad", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jLabel49MouseClicked

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        try {
            Nivel nivels = (Nivel) jComboBox11.getSelectedItem();
            Iterator it = Drive.PERSISTENCIA.getActivos(nivels.getIdNivel()).iterator();
            //ELIMINA TODOS LOS ACTIVOS DE LA BD
            while (it.hasNext()) {
                Activo act = (Activo) it.next();
                Iterator<ActivoIniciofin> itt = act.getActivoIniciofins().iterator();
                while (itt.hasNext()) {
                    ActivoIniciofin actini = itt.next();
                    actini.eliminarActivoiniciofin(actini);
                }
                act.eliminarActivo(act);
            }
            if (nivels.getInactivos().iterator().hasNext()) {
                Inactivo ina = nivels.getInactivos().iterator().next();
                ina.eliminarInactivo(ina);
            }
            nivels.eliminarNivel(nivels);
            Cargo cargo = (Cargo) jComboBox10.getSelectedItem();
            cargo.eliminarCargo(cargo);
            DetalleEstablecimiento det = (DetalleEstablecimiento) jComboBox9.getSelectedItem();
            det.eliminarDetalleEstablecimiento(det);
            Drive.LimpiarCombo(jComboBox9);
            int ii=0;
            ii= Integer.valueOf(jFormattedTextField6.getText());
            Drive.CargarComboEstablecimientosPerso(jComboBox9, pe,ii);
//            Drive.CargarComboEstablecimientosPerso(jComboBox9, pe);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jTextField14KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField14KeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_jTextField14KeyTyped

    private void jLabel55MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel55MouseClicked
        int confirmado = JOptionPane.showConfirmDialog(null,"¿Desea eliminar la actividad?","Eliminar Actividad",JOptionPane.YES_NO_OPTION);
        if (JOptionPane.OK_OPTION == confirmado){
            Actividad act=(Actividad) jComboBox7.getSelectedItem();
            act.eliminarActividad(act);
            Drive.LimpiarCombo(jComboBox7);
            Drive.CargarComboActividad(jComboBox7);
        }
    }//GEN-LAST:event_jLabel55MouseClicked

    private void jLabel56MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel56MouseClicked
        int confirmado = JOptionPane.showConfirmDialog(null,"¿Desea eliminar el departamento?","Eliminar Departamento",JOptionPane.YES_NO_OPTION);
        if (JOptionPane.OK_OPTION == confirmado){
            Departamento depto=(Departamento) jComboBox3.getSelectedItem();
            depto.eliminarDepartamento(depto);
            Drive.LimpiarCombo(jComboBox3);
            Drive.CargarComboDepartamento(jComboBox3);
        }
    }//GEN-LAST:event_jLabel56MouseClicked

    private void jLabel57MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel57MouseClicked
        int confirmado = JOptionPane.showConfirmDialog(null,"¿Desea eliminar el tipo de relación?","Eliminar Relación",JOptionPane.YES_NO_OPTION);
        if (JOptionPane.OK_OPTION == confirmado){
            Tiporelacion act=(Tiporelacion) jComboBox8.getSelectedItem();
            act.eliminarTiporelacion(act);
            Drive.LimpiarCombo(jComboBox8);
            Drive.CargarComboRelacion(jComboBox8);
        }
    }//GEN-LAST:event_jLabel57MouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if(bb==true){
            Frame vp=new JFrameHistorial(Drive,adm);
            this.dispose();
            vp.show();
        }else if(!jTextField7.getText().equals(pe.getNombre())|| 
                !jTextField8.getText().equals(pe.getApellido())||
                !jTextField21.getText().equals(pe.getCorreoElectronico())||
                !jTextField11.getText().equals(pe.getCalle())||
                !jTextField14.getText().equals(String.valueOf(pe.getAltura()))||
                !jTextField18.getText().equals(pe.getPiso())||
                !jTextField19.getText().equals(pe.getDepto())){
            int confirmado = JOptionPane.showConfirmDialog(null,"¿Desea cancelar la actualización del personal?","Actualizar Personal",JOptionPane.YES_NO_OPTION);
            if (JOptionPane.OK_OPTION == confirmado){
               Frame vp=new JFrameConsulta(Drive,adm);
                this.dispose();
                vp.show();
            }
        }else{
            Frame vp=new JFrameConsulta(Drive,adm);
            this.dispose();
            vp.show();
        }
    }//GEN-LAST:event_formWindowClosing

    private void jRadioButton3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton3ItemStateChanged
        if(jRadioButton3.isSelected()){
            jComboBox3.setEnabled(true);
            jCheckBox1.setEnabled(true);
            jButton11.setEnabled(true);
            jButton12.setEnabled(true);
            jTextField16.setEnabled(true);
            jTextField17.setEnabled(true);
        }else{
            jComboBox3.setEnabled(false);
            jCheckBox1.setEnabled(false);
            jButton11.setEnabled(false);
            jButton12.setEnabled(false);
            jTextField16.setEnabled(false);
            jTextField17.setEnabled(false);
        }
    }//GEN-LAST:event_jRadioButton3ItemStateChanged

    private void jRadioButton4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton4ItemStateChanged
        if(jRadioButton4.isSelected()){
           jComboBox7.setEnabled(true);
            if(!jTextField16.getText().isEmpty()){
                if(Integer.parseInt(jTextField16.getText())>21){
                    JOptionPane.showMessageDialog(null, "El cargo de hora no puede ser mayor a 21","Registrar Cargo/horas", JOptionPane.ERROR_MESSAGE);
                    jTextField16.setText("");
                }
            }
        }else{
           jComboBox7.setEnabled(false);
            if(!jTextField16.getText().isEmpty()){
                if(Integer.parseInt(jTextField16.getText())>42){
                    JOptionPane.showMessageDialog(null, "El cargo de hora no puede ser mayor a 42","Registrar Cargo/horas", JOptionPane.ERROR_MESSAGE);
                    jTextField16.setText("");
                }
            }
        }
    }//GEN-LAST:event_jRadioButton4ItemStateChanged

    private void jFormattedTextField7FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextField7FocusLost
        if(!jFormattedTextField7.getText().contains(" ")){
            Cuit cuil=new Cuit();
            String d=jFormattedTextField7.getText().replace(".", "");
            Integer.parseInt(d);
            char s;
            if(jComboBox1.getSelectedItem().equals("MASCULINO")){
                s='m';
            }else { s='f';}
            jTextField1.setText(cuil.generar(Integer.parseInt(d),s ));
        }else{
            JOptionPane.showMessageDialog(null, "Ingrese correctamente el numero de documento","Registrar Tipo de Documento", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jFormattedTextField7FocusLost

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        Cuit cuil=new Cuit();
        try{
        //if(!jTextField1.getText().isEmpty()){
            String d=jFormattedTextField7.getText().replace(".", "");
            Integer.parseInt(d);
            char s;
            if(jComboBox2.getSelectedItem().equals("MASCULINO")){
                s='m';
            }else { s='f';}
            jTextField1.setText(cuil.generar(Integer.parseInt(d),s ));
        }catch(Exception e){}
        //}
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField8KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyTyped
        if(jTextField8.getText().length()==45) evt.consume();
    }//GEN-LAST:event_jTextField8KeyTyped

    private void jTextField7KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyTyped
        if(jTextField7.getText().length()==45) evt.consume();
    }//GEN-LAST:event_jTextField7KeyTyped

    private void jTextField13KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField13KeyTyped
        if(jTextField13.getText().length()==45) evt.consume();
    }//GEN-LAST:event_jTextField13KeyTyped

    private void jTextField21KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField21KeyTyped
        if(jTextField21.getText().length()==45) evt.consume();
    }//GEN-LAST:event_jTextField21KeyTyped

    private void jTextField11KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField11KeyTyped
        if(jTextField11.getText().length()==20) evt.consume();
    }//GEN-LAST:event_jTextField11KeyTyped

    private void jTextField18KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField18KeyTyped
        if(jTextField18.getText().length()==3) evt.consume();
    }//GEN-LAST:event_jTextField18KeyTyped

    private void jTextField19KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField19KeyTyped
        if(jTextField18.getText().length()==3) evt.consume();
    }//GEN-LAST:event_jTextField19KeyTyped

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
        if(jTextField2.getText().length()==45) evt.consume();
    }//GEN-LAST:event_jTextField2KeyTyped

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped
        if(jTextField3.getText().length()==45) evt.consume();
    }//GEN-LAST:event_jTextField3KeyTyped

    private void jTextField4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyTyped
        if(jTextField4.getText().length()==50) evt.consume();
    }//GEN-LAST:event_jTextField4KeyTyped

    private void jComboBox13ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox13ItemStateChanged
        
        Anolectivo an=new Anolectivo();
        Object o=jComboBox13.getSelectedItem();
        if(!o.equals(" ")){
            an=(Anolectivo) o;
        }
        if(an.getInicio()!=null && an.getFin()!=null){
            Calendar ca=Calendar.getInstance();
            ca.setTime(an.getInicio());
            dateChooserCombo6.setSelectedDate(ca);
            dateChooserCombo6.setEnabled(false);
            ca.setTime(an.getFin());
            dateChooserCombo7.setSelectedDate(ca);
            dateChooserCombo7.setEnabled(false);
        }
        int ii=0;
        if(an.getIdAnolectivo()!=null){
            ii= an.getAno();
        }
        Drive.CargarComboEstablecimientosPerso(jComboBox9, pe,ii);
    }//GEN-LAST:event_jComboBox13ItemStateChanged

    private void jLabel66MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel66MouseClicked
        JTextField nombre = new JTextField();
        JTextField numero = new JTextField();
        String cadNombre;
        nombre.setText("");
        nombre.setSize(25, 25);
        String cadnumero;
        numero.setText("");
        numero.setSize(25, 25);

        JOptionPane.showMessageDialog(null, nombre, "Ingrese el nombre del artículo", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(null, numero, "Ingrese el numero del artículo", JOptionPane.INFORMATION_MESSAGE);
        if (!nombre.getText().isEmpty() || !numero.getText().isEmpty()) {
            cadNombre = nombre.getText().toUpperCase();
            cadnumero = numero.getText().toUpperCase();
            Iterator it = Drive.PERSISTENCIA.getArticulos().iterator();
            boolean w = false;
            while (it.hasNext()) {
                Articulo tip = (Articulo) it.next();
                if (tip.getNombre().equals(cadNombre) || tip.getNroArticulo().equals(cadnumero)) {
                    JOptionPane.showMessageDialog(null, "El Articulo ya existe","Registrar Articulo",JOptionPane.ERROR_MESSAGE);
                    w = true;
                }
            }
            if (w == false) {
                Articulo art = new Articulo();
                art.setNombre(cadNombre);
                art.setNroArticulo(cadnumero);
                art.guardarArticulo(art);
                Drive.LimpiarCombo(jComboBox15);
                Drive.CargarComboArticulo(jComboBox15);
                //                comboBoxart.setSelectedItem(art);
            }
        }
    }//GEN-LAST:event_jLabel66MouseClicked

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        try{
            if(mod==false){
                Date inicio=dateChooserCombo9.getSelectedDate().getTime();
                Date fin=dateChooserCombo10.getSelectedDate().getTime();
                Articulo art=(Articulo)jComboBox15.getSelectedItem();
                if(Drive.VerificarLicencia(pe, inicio, fin)){
                    Licencia lic=new Licencia();
                    lic.setPersonal(pe);
                    lic.setInicio(inicio);
                    lic.setFin(fin);
                    lic.setArticulo(art);
                    lic.setDescripcion(jTextField6.getText().toUpperCase());
                    lic.guardarLicencia(lic);
                }else{
                    JOptionPane.showMessageDialog(null, "Ya existe una licencia tomada en esa fecha","Registrar Licencia",JOptionPane.ERROR_MESSAGE);
                }
                jTextField6.setText("");
                dateChooserCombo9.setSelectedDate(Calendar.getInstance());
                dateChooserCombo10.setSelectedDate(Calendar.getInstance());
            }else{
                Date inicio=dateChooserCombo9.getSelectedDate().getTime();
                Date fin=dateChooserCombo10.getSelectedDate().getTime();
                Articulo art=(Articulo)jComboBox15.getSelectedItem();
                if(Drive.VerificarLicencia(pe, inicio, fin,li.getIdLicencia())){
                    li.setInicio(inicio);
                    li.setFin(fin);
                    li.setArticulo(art);
                    li.setDescripcion(jTextField6.getText().toUpperCase());
                    li.actualizarLicencia(li);
                    
                }else{
                    JOptionPane.showMessageDialog(null, "Ya existe una licencia tomada en esa fecha","Registrar Licencia",JOptionPane.ERROR_MESSAGE);
                }
                
            }
            mod=false;
            jTextField6.setText("");
            dateChooserCombo9.setSelectedDate(Calendar.getInstance());
            dateChooserCombo10.setSelectedDate(Calendar.getInstance());
            Drive.LimpiarTabla(jTable2);
            Date fecha=dateChooserCombo11.getSelectedDate().getTime();
            String valor=jComboBox14.getSelectedItem().toString();
            Drive.CargarTablaLicencia(jTable2, pe, fecha, valor);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error","Registrar Licencia",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        try{
            int confirmado = JOptionPane.showConfirmDialog(null, "¿Desea eliminar la licencia?", "Eliminar Licencia", JOptionPane.YES_NO_OPTION);
            if (JOptionPane.OK_OPTION == confirmado) {
                jTable2.getModel();
                int i=jTable2.getSelectedRow();
                if(i>-1){
                    Licencia lic = (Licencia) jTable2.getValueAt(i, 2);
                    Date dia=new Date();
                    if (lic.getInicio().compareTo(dia)<=0) {
                        JOptionPane.showMessageDialog(null, "No se puede eliminar la licencia", "Eliminar Licencia", JOptionPane.ERROR_MESSAGE);
                    }else{
                        lic.eliminarLicencia(lic);
                    }
                    Drive.LimpiarTabla(jTable2);
                    String valor=jComboBox14.getSelectedItem().toString();
                    Date ff=dateChooserCombo11.getSelectedDate().getTime();
                    Drive.CargarTablaLicencia(jTable2, pe,ff, valor);
                }else{
                    JOptionPane.showMessageDialog(null, "Seleccione una licencia", "Eliminar Licencia", JOptionPane.ERROR_MESSAGE);
                }
            }
        }catch(Exception e){
        }
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jTable2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseReleased
        if(evt.getClickCount()==2){
            jTable2.getModel();
            int i = jTable2.getSelectedRow();
            if (i > -1) {
                li = (Licencia) jTable2.getValueAt(i, 2);
                Calendar cc = Calendar.getInstance();
                cc.setTime(li.getFin());
                dateChooserCombo10.setSelectedDate(cc);
                cc.setTime(li.getInicio());
                dateChooserCombo9.setSelectedDate(cc);
                Articulo art = (Articulo) jTable2.getValueAt(i, 3);
                jComboBox15.setSelectedItem(art);
                jTextField6.setText(li.toString());
            }else{
                JOptionPane.showMessageDialog(null, "Seleccione una licencia", "Modificar Licencia", JOptionPane.ERROR_MESSAGE);
            }
            mod = true;
        }   
    }//GEN-LAST:event_jTable2MouseReleased

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        jTable2.getModel();
        int i = jTable2.getSelectedRow();
        if (i > -1) {
            li = (Licencia) jTable2.getValueAt(i, 2);
            Calendar cc = Calendar.getInstance();
            cc.setTime(li.getFin());
            dateChooserCombo10.setSelectedDate(cc);
            cc.setTime(li.getInicio());
            dateChooserCombo9.setSelectedDate(cc);
            Articulo art = (Articulo) jTable2.getValueAt(i, 3);
            jComboBox15.setSelectedItem(art);
            jTextField6.setText(li.toString());
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione una licencia", "Actualizar Licencia", JOptionPane.ERROR_MESSAGE);
        }
        mod = true;
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jComboBox14ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox14ItemStateChanged
        try{
            Drive.LimpiarTabla(jTable2);
            String valor=jComboBox14.getSelectedItem().toString();
            Date ff=dateChooserCombo11.getSelectedDate().getTime();
            Drive.CargarTablaLicencia(jTable2, pe,ff, valor);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error", "Cargar licencias", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jComboBox14ItemStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try{
            Drive.LimpiarTabla(jTable2);
            String valor=jComboBox14.getSelectedItem().toString();
            Date ff=dateChooserCombo11.getSelectedDate().getTime();
            Drive.CargarTablaLicencia(jTable2, pe,ff, valor);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error", "Cargar licencias", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void dateChooserCombo11OnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_dateChooserCombo11OnSelectionChange
        try{
            Drive.LimpiarTabla(jTable2);
            String valor=jComboBox14.getSelectedItem().toString();
            Date ff=dateChooserCombo11.getSelectedDate().getTime();
            Drive.CargarTablaLicencia(jTable2, pe,ff, valor);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error", "Cargar licencias", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_dateChooserCombo11OnSelectionChange

    private void dateChooserCombo9OnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_dateChooserCombo9OnSelectionChange
        try{
            Date inicio=dateChooserCombo9.getSelectedDate().getTime();
            Date fin=dateChooserCombo10.getSelectedDate().getTime();
            Date fecha=new Date();
            Anolectivo an=Drive.getPrimerEstablecimiento().getAnoLectivo(fecha.getYear()+1900);
            if(inicio.compareTo(fin)>0 || an.getInicio().compareTo(inicio)>0 || an.getFin().compareTo(fin)<0){
                JOptionPane.showMessageDialog(null,"La fecha de inicio debe ser menor que la fecha de fin y estar contemplado dentro del año lectivo","Registrar Licencia",JOptionPane.ERROR_MESSAGE);
                Calendar cal = Calendar.getInstance();
                dateChooserCombo9.setSelectedDate(cal);
                dateChooserCombo10.setSelectedDate(cal);
            }
        }catch(Exception e){
            
        }
    }//GEN-LAST:event_dateChooserCombo9OnSelectionChange

    private void dateChooserCombo10OnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_dateChooserCombo10OnSelectionChange
        try{
            Date inicio=dateChooserCombo9.getSelectedDate().getTime();
            Date fin=dateChooserCombo10.getSelectedDate().getTime();
            Date fecha=new Date();
            Anolectivo an=Drive.getPrimerEstablecimiento().getAnoLectivo(fecha.getYear()+1900);
            if(inicio.compareTo(fin)>0 || an.getInicio().compareTo(inicio)>0 || an.getFin().compareTo(fin)<0){
                JOptionPane.showMessageDialog(null,"La fecha de inicio debe ser menor que la fecha de fin y estar contemplado dentro del año lectivo","Registrar Licencia",JOptionPane.ERROR_MESSAGE);
                Calendar cal = Calendar.getInstance();
                //dateChooserCombo1.setSelectedDate(cal);
                dateChooserCombo10.setSelectedDate(cal);
            }

        }catch(Exception e){}
    }//GEN-LAST:event_dateChooserCombo10OnSelectionChange

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(JFrameActualizarPersonal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(JFrameActualizarPersonal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(JFrameActualizarPersonal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(JFrameActualizarPersonal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new JFrameActualizarPersonal().setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private datechooser.beans.DateChooserCombo dateChooserCombo10;
    private datechooser.beans.DateChooserCombo dateChooserCombo11;
    private datechooser.beans.DateChooserCombo dateChooserCombo2;
    private datechooser.beans.DateChooserCombo dateChooserCombo3;
    private datechooser.beans.DateChooserCombo dateChooserCombo4;
    private datechooser.beans.DateChooserCombo dateChooserCombo5;
    private datechooser.beans.DateChooserCombo dateChooserCombo6;
    private datechooser.beans.DateChooserCombo dateChooserCombo7;
    private datechooser.beans.DateChooserCombo dateChooserCombo9;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox10;
    private javax.swing.JComboBox jComboBox11;
    private javax.swing.JComboBox jComboBox12;
    private javax.swing.JComboBox jComboBox13;
    private javax.swing.JComboBox jComboBox14;
    private javax.swing.JComboBox jComboBox15;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JComboBox jComboBox5;
    private javax.swing.JComboBox jComboBox6;
    private javax.swing.JComboBox jComboBox7;
    private javax.swing.JComboBox jComboBox8;
    private javax.swing.JComboBox jComboBox9;
    private javax.swing.JFormattedTextField jFormattedTextField6;
    private javax.swing.JFormattedTextField jFormattedTextField7;
    private javax.swing.JFormattedTextField jFormattedTextField8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables
}
