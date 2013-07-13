/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Clases.Anolectivo;
import Clases.Auditoria;
import Clases.Controlador;
import Clases.Establecimiento;
import Clases.Perfil;
import Clases.Personal;
import java.awt.Frame;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.hibernate.Hibernate;

/**
 *
 * @author Fernando
 */
public class JFrameConfiguracion extends javax.swing.JFrame {

    /**
     * Creates new form JFrameConfiguracion
     */
    public Controlador Drive=new Controlador();
    public Personal adm=new Personal();
//    int idsesion=0;
    Establecimiento est=new Establecimiento();
    Anolectivo lectivo= new Anolectivo();
    
    public JFrameConfiguracion(Controlador unDrive,Personal admin) {
        this.Drive=unDrive;
        this.adm=admin;
//        this.idsesion=id;
        initComponents();
        if(adm.getIdPersonal()!=null){
            est=Drive.getPrimerEstablecimiento();
            jTextField1.setText(est.getNombre());
            jTextField2.setText(est.getCalle());
            jTextField3.setText(String.valueOf(est.getAltura()));
            jTextField4.setText(est.getPiso());
            jTextField5.setText(est.getDepto());
            jTextArea1.setText(est.getLeyenda());
            if(est.getImagen()!=null){
                ImageIcon fott = new ImageIcon(est.getImagen());
                Icon icono4 = new ImageIcon(fott.getImage().getScaledInstance(jLabel23.getWidth(), jLabel23.getHeight(), Image.SCALE_DEFAULT));
                jLabel23.setIcon(icono4);
                jLabel23.repaint();
            }
            Calendar cal= Calendar.getInstance();
            lectivo=est.getAnoLectivo(cal.getTime().getYear()+1900);
            if(lectivo.getIdAnolectivo()!=null){
//                SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                jTextField6.setText(String.valueOf(lectivo.getAno()));
                if(lectivo.getInicio()!=null && lectivo.getFin()!=null){
                    cal.setTime(lectivo.getInicio());
                    dateChooserCombo1.setSelectedDate(cal);
                    cal.setTime(lectivo.getFin());
                    dateChooserCombo2.setSelectedDate(cal);
                }
            }
            
        }else{
//            jButton4.setEnabled(false);
            jTextField1.setEnabled(true);
            Date fecha=new Date();
            jTextField6.setText(String.valueOf(fecha.getYear()+1900));
        }
        Drive.CargarComboPerfil(jComboBox1);
        jTextField7.setVisible(false);
        jLabel18.setVisible(false);
        jLabel19.setVisible(false);
        
        ImageIcon fott3 = new ImageIcon(getClass().getResource("/imagenes/ok.png"));
        Icon icono3 = new ImageIcon(fott3.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        jButton3.setIcon(icono3);
        jButton2.setIcon(icono3);
        ImageIcon fott4 = new ImageIcon(getClass().getResource("/imagenes/no.png"));
        Icon icono4 = new ImageIcon(fott4.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        jButton1.setIcon(icono4);
        ImageIcon fot = new ImageIcon(getClass().getResource("/imagenes/image.jpg"));
        Icon icono = new ImageIcon(fot.getImage().getScaledInstance(jLabel16.getWidth(), jLabel16.getHeight(), Image.SCALE_DEFAULT));
        jLabel16.setIcon(icono);
        jLabel22.setIcon(icono);
        ImageIcon fott1 = new ImageIcon(getClass().getResource("/imagenes/eliminar.gif"));
        Icon icono1 = new ImageIcon(fott1.getImage().getScaledInstance(jLabel17.getWidth(), jLabel17.getHeight(), Image.SCALE_DEFAULT));
        jLabel17.setIcon(icono1);
        ImageIcon fott2 = new ImageIcon(getClass().getResource("/imagenes/Menos.png"));
        Icon icono2 = new ImageIcon(fott2.getImage().getScaledInstance(jLabel19.getWidth(), jLabel19.getHeight(), Image.SCALE_DEFAULT));
        jLabel19.setIcon(icono2);
        ImageIcon fott5 = new ImageIcon(getClass().getResource("/imagenes/Backup.png"));
        Icon icono5 = new ImageIcon(fott5.getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT));
        jButton5.setIcon(icono5);
        ImageIcon fott6= new ImageIcon(getClass().getResource("/imagenes/Restore.jpg"));
        Icon icono6 = new ImageIcon(fott6.getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT));
        jButton6.setIcon(icono6);
        
        if(adm.getIdPersonal()!=null && adm.getPerfil().getConfiguracionact()==null){
            jButton2.setEnabled(false);
            jButton3.setEnabled(false);
            jButton5.setEnabled(false);
            jButton6.setEnabled(false);
        }
        if(adm.getIdPersonal()!=null && adm.getPerfil().getConfiguracionins()==null){
            jLabel16.setVisible(false);
        }
        if(adm.getIdPersonal()!=null && adm.getPerfil().getConfiguracioneli()==null){
            jLabel17.setVisible(false);
        }
//        jTable1.getColumnModel().getColumn(4).setCellEditor(boolean.class);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel23 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        dateChooserCombo2 = new datechooser.beans.DateChooserCombo();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SISTEMA DE ASISTENCIA DE PERSONAL EDUCATIVO");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel1.setText("Colegio:");

        jTextField1.setEnabled(false);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Dirección"));

        jLabel2.setText("Calle:");

        jLabel3.setText("Numero:");

        jLabel4.setText("Piso:");

        jLabel5.setText("Depto:");

        jLabel11.setText("*");

        jLabel12.setText("*");

        jTextField8.setEnabled(false);
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });

        jButton4.setText("Seleccionar archivo");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel20.setText("Imagen:");

        jLabel21.setText("Leyenda:");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(5, 5, 5)
                                        .addComponent(jLabel11))
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(67, 67, 67)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel12))
                                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jTextField8)
                                .addGap(10, 10, 10)))))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Año lectivo"));

        jLabel6.setText("Año:");

        jTextField6.setEnabled(false);

        jLabel8.setText("Fin:");

        jLabel7.setText("Inicio:");

        dateChooserCombo1.setCurrentView(new datechooser.view.appearance.AppearancesList("Grey",
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
    dateChooserCombo1.setBehavior(datechooser.model.multiple.MultyModelBehavior.SELECT_SINGLE);

    dateChooserCombo2.setCurrentView(new datechooser.view.appearance.AppearancesList("Grey",
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
dateChooserCombo2.setBehavior(datechooser.model.multiple.MultyModelBehavior.SELECT_SINGLE);

jLabel13.setText("*");

jLabel14.setText("*");

jLabel15.setText("*");

javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
jPanel4.setLayout(jPanel4Layout);
jPanel4Layout.setHorizontalGroup(
    jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(jPanel4Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6)
            .addComponent(jLabel7))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addGap(40, 40, 40)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateChooserCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)))
        .addContainerGap(62, Short.MAX_VALUE))
    );
    jPanel4Layout.setVerticalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel4Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel6)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel13))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(jLabel7)
                .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel14)
                .addComponent(jLabel8)
                .addComponent(dateChooserCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel15))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jButton2.setText("Aceptar");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton2ActionPerformed(evt);
        }
    });

    jLabel9.setText("* Son campos obligatorios");

    jLabel10.setText("*");

    jLabel22.setText("E");
    jLabel22.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jLabel22MouseClicked(evt);
        }
    });

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel10)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jLabel9)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2)))
            .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel10)
                .addComponent(jLabel22))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(3, 3, 3)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButton2)
                .addComponent(jLabel9))
            .addContainerGap(97, Short.MAX_VALUE))
    );

    jTabbedPane1.addTab("Establecimiento", jPanel1);

    jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

    jComboBox1.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            jComboBox1ItemStateChanged(evt);
        }
    });

    jLabel16.setText("N");
    jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jLabel16MouseClicked(evt);
        }
    });

    jLabel17.setText("E");
    jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jLabel17MouseClicked(evt);
        }
    });

    jTable1.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {"Personal", null, null, null, null},
            {"Actividades", null, null, null, null},
            {"Asistencias", null, null, null, null},
            {"Circulares", null, null, null, null},
            {"Configuración", null, null, null, null},
            {"Registro", null, null, null, null},
            {"Auditoria", null, null, null, null},
            {"Historial", null, null, null, null}
        },
        new String [] {
            "", "Insertar", "Actualizar", "Eliminar", "Consultar"
        }
    ) {
        Class[] types = new Class [] {
            java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class
        };
        boolean[] canEdit = new boolean [] {
            false, true, true, true, true
        };

        public Class getColumnClass(int columnIndex) {
            return types [columnIndex];
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    jTable1.setRowHeight(20);
    jScrollPane1.setViewportView(jTable1);

    jLabel18.setText("Nombre:");

    jButton3.setText("Aceptar");
    jButton3.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton3ActionPerformed(evt);
        }
    });

    jLabel19.setText("C");
    jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jLabel19MouseClicked(evt);
        }
    });

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jButton3))
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel18)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE))
                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addContainerGap())
    );
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel16)
                .addComponent(jLabel17))
            .addGap(13, 13, 13)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel18)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel19))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 232, Short.MAX_VALUE)
            .addComponent(jButton3)
            .addContainerGap())
    );

    jTabbedPane1.addTab("Perfiles de usuario", jPanel2);

    jButton5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jButton5.setText("Generar BackUp");
    jButton5.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton5ActionPerformed(evt);
        }
    });

    jButton6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jButton6.setText("Restaurar BackUp");
    jButton6.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton6ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
    jPanel5.setLayout(jPanel5Layout);
    jPanel5Layout.setHorizontalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel5Layout.createSequentialGroup()
            .addGap(108, 108, 108)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(jButton6)
                .addComponent(jButton5))
            .addContainerGap(172, Short.MAX_VALUE))
    );

    jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton5, jButton6});

    jPanel5Layout.setVerticalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel5Layout.createSequentialGroup()
            .addGap(123, 123, 123)
            .addComponent(jButton5)
            .addGap(26, 26, 26)
            .addComponent(jButton6)
            .addContainerGap(329, Short.MAX_VALUE))
    );

    jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton5, jButton6});

    jTabbedPane1.addTab("BackUp", jPanel5);

    jButton1.setText("Salir");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton1ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton1)
            .addContainerGap())
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jButton1)
            .addContainerGap(16, Short.MAX_VALUE))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int confirmado = JOptionPane.showConfirmDialog(null, "¿Desea volver al menú principal?", "Configuración Inicial", JOptionPane.YES_NO_OPTION);
        if (JOptionPane.OK_OPTION == confirmado) {
            if(adm.getIdPersonal()!=null){
                Frame vp = new JFramePrincipal(Drive,adm);
                this.dispose();
                vp.show();
            }else{
                JFrameInicio vent2= new JFrameInicio();
                this.dispose();
                vent2.show();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            int ano = Integer.parseInt(jTextField6.getText());
            byte[] imagen=null;
            String im=jTextField8.getText();
            im=im.replace(".JPG", ".jpg");
            im=im.replace(".PNG", ".png");
            im=im.replace(".GIF", ".gif");
            if(im.length()!=0){
                imagen = Drive.ObtenerByte(im);
            }
            if (est.getIdEstablecimiento() != null) {
                boolean mensaje=false;
                String nombre = jTextField1.getText().toUpperCase();
                String calle = jTextField2.getText().toUpperCase();
                int numero = Integer.parseInt(jTextField3.getText());
                String piso = jTextField4.getText().toUpperCase();
                String depto = jTextField5.getText().toUpperCase();
                String ley = jTextArea1.getText();
                boolean bimagen=true;
                if(imagen!=null){
                    bimagen = Arrays.equals(imagen, est.getImagen());
                    if(!bimagen){
                        est.setImagen(imagen);
                        est.ActualizarEstablecimiento(est);
                        // <editor-fold defaultstate="collapsed" desc="Auditoria"> 
                        Auditoria audi = new Auditoria();
                        audi.setPersonalByIdAuditor(adm);
                        audi.setOperacion("Actualizar");
                        audi.setFecha(new Date());
                        audi.setEstablecimiento(est);
                        audi.setCampo("Imagen");
                        audi.guardarAuditoria(audi);
                        // </editor-fold>
                        mensaje=true;
                    }
                }
                if (!est.getNombre().equals(nombre)) {
                    // <editor-fold defaultstate="collapsed" desc="Auditoria"> 
                    Auditoria audi = new Auditoria();
                    audi.setPersonalByIdAuditor(adm);
                    audi.setOperacion("Actualizar");
                    audi.setFecha(new Date());
                    audi.setEstablecimiento(est);
                    audi.setCampo("Nombre");
                    audi.setElementoAnterior(est.getNombre());
                    audi.setElementoNuevo(nombre);
                    audi.guardarAuditoria(audi);
                    // </editor-fold>
                    est.setNombre(nombre);
                    est.ActualizarEstablecimiento(est);
                    mensaje=true;
                }
                if (!est.getCalle().equals(calle)) {
                    // <editor-fold defaultstate="collapsed" desc="Auditoria"> 
                    Auditoria audi = new Auditoria();
                    audi.setPersonalByIdAuditor(adm);
                    audi.setOperacion("Actualizar");
                    audi.setFecha(new Date());
                    audi.setEstablecimiento(est);
                    audi.setCampo("Calle");
                    audi.setElementoAnterior(est.getCalle());
                    audi.setElementoNuevo(calle);
                    audi.guardarAuditoria(audi);
                    // </editor-fold>
                    est.setCalle(calle);
                    est.ActualizarEstablecimiento(est);
                    mensaje=true;
                }
                if (est.getAltura() != numero) {
                    // <editor-fold defaultstate="collapsed" desc="Auditoria"> 
                    Auditoria audi = new Auditoria();
                    audi.setPersonalByIdAuditor(adm);
                    audi.setOperacion("Actualizar");
                    audi.setFecha(new Date());
                    audi.setEstablecimiento(est);
                    audi.setCampo("Altura");
                    audi.setElementoAnterior(String.valueOf(est.getAltura()));
                    audi.setElementoNuevo(String.valueOf(numero));
                    audi.guardarAuditoria(audi);
                    // </editor-fold>
                    est.setAltura(numero);
                    est.ActualizarEstablecimiento(est);
                    mensaje=true;
                }
                if (!piso.equals(est.getPiso())) {
                    // <editor-fold defaultstate="collapsed" desc="Auditoria"> 
                    Auditoria audi = new Auditoria();
                    audi.setPersonalByIdAuditor(adm);
                    audi.setOperacion("Actualizar");
                    audi.setFecha(new Date());
                    audi.setEstablecimiento(est);
                    audi.setCampo("Piso");
                    audi.setElementoAnterior(est.getPiso());
                    audi.setElementoNuevo(piso);
                    audi.guardarAuditoria(audi);
                    // </editor-fold>
                    mensaje=true;
                    est.setPiso(piso);
                    est.ActualizarEstablecimiento(est);
                }
                if (!depto.equals(est.getDepto())) {
                    // <editor-fold defaultstate="collapsed" desc="Auditoria"> 
                    Auditoria audi = new Auditoria();
                    audi.setPersonalByIdAuditor(adm);
                    audi.setOperacion("Actualizar");
                    audi.setFecha(new Date());
                    audi.setEstablecimiento(est);
                    audi.setCampo("Departamento");
                    audi.setElementoAnterior(est.getDepto());
                    audi.setElementoNuevo(depto);
                    audi.guardarAuditoria(audi);
                    // </editor-fold>
                    est.setDepto(depto);
                    est.ActualizarEstablecimiento(est);
                    mensaje=true;
                }
                if (!ley.equals(est.getLeyenda())) {
                    // <editor-fold defaultstate="collapsed" desc="Auditoria"> 
                    Auditoria audi = new Auditoria();
                    audi.setPersonalByIdAuditor(adm);
                    audi.setOperacion("Actualizar");
                    audi.setFecha(new Date());
                    audi.setEstablecimiento(est);
                    audi.setCampo("Leyenda");
                    audi.setElementoAnterior(est.getLeyenda());
                    audi.setElementoNuevo(ley);
                    audi.guardarAuditoria(audi);
                    // </editor-fold>
                    mensaje=true;
                    est.setLeyenda(ley);
                    est.ActualizarEstablecimiento(est);
                }
                Date inicio = dateChooserCombo1.getSelectedDate().getTime();
                Date fin = dateChooserCombo2.getSelectedDate().getTime();
                if (inicio.compareTo(fin) < 0) {
//                    Anolectivo a=Drive.getAnoLectivo();
//                    if(lectivo.getIdAnolectivo()!=null){
                    if ((inicio.getYear() + 1900) == ano && (fin.getYear() + 1900) == ano) {
                        if (lectivo.getInicio() != null && lectivo.getFin() != null) {
                            if(lectivo.ControlarAnolectivo(inicio,fin)){
                                boolean band = false;
                                if (lectivo.getInicio().getDate() != inicio.getDate() || lectivo.getInicio().getMonth() != inicio.getMonth() || lectivo.getInicio().getYear() != inicio.getYear()) {
                                    lectivo.setInicio(inicio);
                                    lectivo.actualizarAnolectivo(lectivo);
                                    band = true;
                                }
                                if (lectivo.getFin().getDate() != fin.getDate() || lectivo.getFin().getMonth() != fin.getMonth() || lectivo.getFin().getYear() != fin.getYear()) {
                                    lectivo.setInicio(fin);
                                    lectivo.actualizarAnolectivo(lectivo);
                                    band = true;
                                }
                                if (band == true) {
                                    JOptionPane.showMessageDialog(null, "El Año lectvo se actualizó correctamente", "Actualizar Año lectivo", JOptionPane.INFORMATION_MESSAGE);
                                }
                            }else{
                                JOptionPane.showMessageDialog(null, "El Año lectvo no se puede actualizar porque tiene actividades", "Actualizar Año lectivo", JOptionPane.ERROR_MESSAGE);
                                Calendar cal= Calendar.getInstance();
                                cal.setTime(lectivo.getInicio());
                                dateChooserCombo1.setSelectedDate(cal);
                                cal.setTime(lectivo.getFin());
                                dateChooserCombo2.setSelectedDate(cal);
                            }
                        } else {
                            lectivo.setInicio(inicio);
                            lectivo.setFin(fin);
                            lectivo.actualizarAnolectivo(lectivo);
                            JOptionPane.showMessageDialog(null, "El Año lectvo se actualizó correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "La fecha de inicio y fin debe ser dentro del año lectivo", "Actualizar Año lectivo", JOptionPane.ERROR_MESSAGE);

                    }
//                }
                } else {
                    JOptionPane.showMessageDialog(null, "La fecha de inicio debe ser menor a la fecha de fin", "Actualizar Año lectivo", JOptionPane.ERROR_MESSAGE);
                    if (lectivo.getInicio() != null && lectivo.getFin() != null) {
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(lectivo.getInicio());
                        dateChooserCombo1.setSelectedDate(cal);
                        cal.setTime(lectivo.getFin());
                        dateChooserCombo2.setSelectedDate(cal);
                    }
                }
                jTextField1.setEnabled(false);
                if(mensaje==true){
                    JOptionPane.showMessageDialog(null,"El Establecimiento se actualizó correctamente","Actualizar establecimiento",JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                Date inicio = dateChooserCombo1.getSelectedDate().getTime();
                Date fin = dateChooserCombo2.getSelectedDate().getTime();
                if (!jTextField1.getText().isEmpty() && !jTextField2.getText().isEmpty() && !jTextField3.getText().isEmpty()) {
                    if (inicio.compareTo(fin) < 0) {
                        if ((inicio.getYear() + 1900) == ano && (fin.getYear() + 1900) == ano) {
                            est.setNombre(jTextField1.getText().toUpperCase());
                            est.setCalle(jTextField2.getText().toUpperCase());
                            est.setAltura(Integer.parseInt(jTextField3.getText()));
                            est.setPiso(jTextField4.getText().toUpperCase());
                            est.setDepto(jTextField5.getText().toUpperCase());
                            est.setImagen(imagen);
                            est.setLeyenda(jTextArea1.getText());
                            est.guardarEstablecimiento(est);
                            lectivo.setEstablecimiento(est);
                            lectivo.setAno(ano);
                            lectivo.setInicio(inicio);
                            lectivo.setFin(fin);
                            lectivo.guardarAnolectivo(lectivo);
                            JOptionPane.showMessageDialog(null, "El Año lectvo se guardó correctamente", "Registrar año lectivo", JOptionPane.INFORMATION_MESSAGE); 
                        } else {
                            JOptionPane.showMessageDialog(null, "La fecha de inicio y fin debe ser dentro del año lectivo", "Guardar Colegio", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "La fecha de inicio debe ser menor a la fecha de fin", "Guardar Colegio", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Todos los campos con * son obligatorios", "Guardar Colegio", JOptionPane.ERROR_MESSAGE);
                }
            }
            if(est.getImagen()!=null){
                ImageIcon fott = new ImageIcon(est.getImagen());
                Icon icono4 = new ImageIcon(fott.getImage().getScaledInstance(jLabel23.getWidth(), jLabel23.getHeight(), Image.SCALE_DEFAULT));
                jLabel23.setIcon(icono4);
                jLabel23.repaint();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"La imagen es muy grande para ser guardada en la Base de datos", "Guardar Colegio", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int confirmado = JOptionPane.showConfirmDialog(null, "¿Desea volver al menú principal?", "Configuración Inicial", JOptionPane.YES_NO_OPTION);
        if (JOptionPane.OK_OPTION == confirmado) {
            if(adm.getIdPersonal()!=null){
                Frame vp = new JFramePrincipal(Drive,adm);
                this.dispose();
                vp.show();
            }else{
                JFrameInicio vent2= new JFrameInicio();
                this.hide();
                vent2.show();
            }
        }
    }//GEN-LAST:event_formWindowClosing

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try{
            if(jTextField7.isVisible()){
                if(!jTextField7.getText().isEmpty()){
                    String nom=jTextField7.getText().toUpperCase();
                    if(!Drive.verificarPerfil(nom)){
                        Perfil per=new Perfil();
                        per.setNombre(nom);
                        per.Asignarpermisos(jTable1, per);
                        per.guardarPerfil(per);
                        Drive.LimpiarCombo(jComboBox1);
                        Drive.CargarComboPerfil(jComboBox1);
                        jTextField7.setVisible(false);
                        jLabel18.setVisible(false);
                        jLabel19.setVisible(false);
                    }else{
                        JOptionPane.showMessageDialog(null, "El Perfil ya existe", "Perfil de usuario", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Debe asignar un nombre al perfil", "Perfil de usuario", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                Perfil per=(Perfil) jComboBox1.getSelectedItem();
                per.Asignarpermisos(jTable1, per);
                per.actualizarPerfil(per);
                Drive.LimpiarCombo(jComboBox1);
                Drive.CargarComboPerfil(jComboBox1);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.toString(), "", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        Object o=jComboBox1.getSelectedItem();
        if(o!=null){
            Perfil per=(Perfil) o;
            per.CargarTabla(jTable1, per);
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        jTextField7.setVisible(true);
        jLabel18.setVisible(true);
        jLabel19.setVisible(true);
        Perfil per=new Perfil();
        per.CargarTabla(jTable1, per);
//        Drive.LimpiarTabla(jTable1);
    }//GEN-LAST:event_jLabel16MouseClicked

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        jTextField7.setVisible(false);
        jLabel18.setVisible(false);
        jLabel19.setVisible(false);
        Perfil per=(Perfil) jComboBox1.getSelectedItem();
        per.CargarTabla(jTable1, per);
    }//GEN-LAST:event_jLabel19MouseClicked

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        Perfil per=(Perfil) jComboBox1.getSelectedItem();
        per.eliminarPerfil(per);
        Drive.LimpiarCombo(jComboBox1);
        Drive.CargarComboPerfil(jComboBox1);
    }//GEN-LAST:event_jLabel17MouseClicked

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        FileNameExtensionFilter filtro= new FileNameExtensionFilter("Archivos de imagen...(*.png, *.jpg, *.gif)","png","jpg","gif");
        JFileChooser file= new JFileChooser();
        file.setFileFilter(filtro);
        file.setDialogTitle("Abrir archivo...");
        String rut = System.getProperty("user.home")+ System.getProperty("file.separator")+"Desktop";
        File ruta= new File(rut);
        
        file.setCurrentDirectory(ruta);
        int res= file.showOpenDialog(null);
        if(res==JFileChooser.APPROVE_OPTION){
            File fi=file.getSelectedFile();
            jTextField8.setText(String.valueOf(fi));
            String im=String.valueOf(fi);
            im=im.replace("\\","/");
            im=im.replace(".JPG", ".jpg");
            im=im.replace(".PNG", ".png");
            im=im.replace(".GIF", ".gif");
            ImageIcon fott = new ImageIcon(im);
            Icon icono4 = new ImageIcon(fott.getImage().getScaledInstance(jLabel23.getWidth(), jLabel23.getHeight(), Image.SCALE_DEFAULT));
            jLabel23.setIcon(icono4);
            jLabel23.repaint();
        }
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jLabel22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseClicked
        jTextField1.setEnabled(true);
    }//GEN-LAST:event_jLabel22MouseClicked

//    public static boolean backupDB(String dbName, String dbUserName, String dbPassword, String path) {
//        String executeCmd = Sistema.configuraciones.rutaDBMS+"mysqldump -u " + dbUserName + " -p" + dbPassword + " --add-drop-database -B " + dbName + " -r " + path;
//        Process runtimeProcess;
//        try {
//
//            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
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
//
//        return false;
//    }
    
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        
        try {
        
//            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de imagen...(*.png, *.jpg, *.gif)", "png", "jpg", "gif");
//            JFileChooser RealizarBackupMySQL = new JFileChooser();
//            RealizarBackupMySQL.setFileFilter(filtro);
//            RealizarBackupMySQL.setDialogTitle("Guardar archivo...");
//            String rut = System.getProperty("user.home") + System.getProperty("file.separator") + "Desktop";
//            File ruta = new File(rut);
//            RealizarBackupMySQL.setCurrentDirectory(ruta);
//            int resp;
//            Date hoy=new Date();
//            resp = RealizarBackupMySQL.showSaveDialog(this);//JFileChooser de nombre RealizarBackupMySQL
//            if (resp == JFileChooser.APPROVE_OPTION) {//Si el usuario presiona aceptar; se genera el Backup
//                try {
//                    Runtime runtime = Runtime.getRuntime();
//                    File backupFile;
//                    backupFile = new File(String.valueOf(RealizarBackupMySQL.getSelectedFile().toString()) + " "+hoy.getDate()+"-"+(hoy.getMonth()+1)+"-"+(hoy.getYear()+1900)+".sql");
//                    FileWriter fw = new FileWriter(backupFile);
//                    Process child = runtime.exec("C:\\Program Files (x86)\\MySQL\\MySQL Server 5.1\\bin\\mysqldump --opt --password=root --user=root --databases asistencia");
//                    InputStreamReader irs = new InputStreamReader(child.getInputStream());
//                    BufferedReader br = new BufferedReader(irs);
//                    String line;
//                    while ((line = br.readLine()) != null) {
//                        fw.write(line + "\n");
//                    }
//                    fw.close();
//                    irs.close();
//                    br.close();
//                    JOptionPane.showMessageDialog(null, "El Backup ha sido generado", "Generar Backup", JOptionPane.INFORMATION_MESSAGE);
//                } catch (Exception e) {
//                    JOptionPane.showMessageDialog(null, "Error no se genero el archivo por el siguiente motivo:" + e.getMessage(), "Verificar", JOptionPane.ERROR_MESSAGE);
//                }
//            } else if (resp == JFileChooser.CANCEL_OPTION) {
//            }
            
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de texto....(*.sql, *.txt)", "sql", "txt");
            JFileChooser RealizarBackupMySQL = new JFileChooser();
            RealizarBackupMySQL.setFileFilter(filtro);
            RealizarBackupMySQL.setDialogTitle("Guardar archivo...");
            String rut = System.getProperty("user.home") + System.getProperty("file.separator") + "Desktop";
            File ruta = new File(rut);
            RealizarBackupMySQL.setCurrentDirectory(ruta);
            int resp;
            Date hoy=new Date();
            resp = RealizarBackupMySQL.showSaveDialog(this);//JFileChooser de nombre RealizarBackupMySQL
            if (resp == JFileChooser.APPROVE_OPTION) {//Si el usuario presiona aceptar; se genera el Backup
                String g=String.valueOf(RealizarBackupMySQL.getSelectedFile().toString()) + " "+hoy.getDate()+"-"+(hoy.getMonth()+1)+"-"+(hoy.getYear()+1900)+".sql";
                if(Drive.BackupDB("asistencia","root","root",g)){
                    JOptionPane.showMessageDialog(null, "Se genero correctamente");
                }
                
            }
            
//            backupDB("asistencia","root","root",rut);
        } catch (Exception ex) {
            Logger.getLogger(JFrameConfiguracion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de texto....(*.sql, *.txt)", "sql", "txt");
        JFileChooser RealizarBackupMySQL = new JFileChooser();
        RealizarBackupMySQL.setFileFilter(filtro);
        RealizarBackupMySQL.setDialogTitle("Abrir archivo...");
        String rut = System.getProperty("user.home") + System.getProperty("file.separator") + "Desktop";
        File ruta = new File(rut);
        RealizarBackupMySQL.setCurrentDirectory(ruta);
        int resp;
        Date hoy=new Date();
        resp = RealizarBackupMySQL.showSaveDialog(this);//JFileChooser de nombre RealizarBackupMySQL
        if (resp == JFileChooser.APPROVE_OPTION){
            String r = RealizarBackupMySQL.getSelectedFile().getAbsolutePath();
            try {
                Process p = Runtime.getRuntime().exec("C:\\Program Files (x86)\\MySQL\\MySQL Server 5.1\\bin\\mysqldump --opt --password=root --user=root --databases asistencia"); 
                InputStream is = p.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                FileWriter fileW = new FileWriter(r);
                PrintWriter pw = new PrintWriter(fileW);
                String aux = br.readLine();
                while (aux != null) {
                    pw.println(aux);
                    aux = br.readLine();
                }
                pw.close();
                fileW.close();
                JOptionPane.showMessageDialog(null, "La restauración ha sido generada", "Generar Restauración", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_jButton6ActionPerformed

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
//            java.util.logging.Logger.getLogger(JFrameConfiguracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(JFrameConfiguracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(JFrameConfiguracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(JFrameConfiguracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new JFrameConfiguracion().setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private datechooser.beans.DateChooserCombo dateChooserCombo2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox jComboBox1;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables
}
