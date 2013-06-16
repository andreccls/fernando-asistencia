/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Clases.Activo;
import Clases.ActivoIniciofin;
import Clases.Articulo;
import Clases.Asistencia;
import Clases.Controlador;
import Clases.Dia;
import Clases.Establecimiento;
import Clases.Iniciofin;
import Clases.Justificacion;
import Clases.Mes;
import Clases.Personal;
import Clases.Tarea;
import Clases.Tareaclase;
import Clases.Tipodoc;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author fer
 */
public class JFrameInasistencia extends javax.swing.JFrame {

    /**
     * Creates new form JFrameInasistencia
     */
    public Controlador Drive=new Controlador();;
    public Personal adm;
    public Personal person=new Personal();
//    int idsesion;
    JComboBox comboBoxart = new JComboBox();
    JasperReport reporte;
    StringBuffer buffer = new StringBuffer();
    
    public JFrameInasistencia(Controlador unDrive, Personal admin, Personal persona) {
        this.Drive = unDrive;
        this.adm = admin;
        this.person=persona;
//        this.idsesion = id;
        initComponents();

        jTextField1.setText(String.valueOf(Calendar.getInstance().getTime().getYear() + 1900));
        Date hoy=new Date();
        String m=Drive.ObtenerMes(hoy.getMonth());
        jComboBox1.setSelectedItem(m);
        if (person.getIdPersonal() == null) {
            String mes = String.valueOf(jComboBox1.getSelectedItem());
            Drive.CargarTablaInasistencias(jTable1, mes, Integer.parseInt(jTextField1.getText()));
        } else {
            String mes = String.valueOf(jComboBox1.getSelectedItem());
            Drive.CargarTablaInasistencias(jTable1, mes, Integer.parseInt(jTextField1.getText()), person);
        }

        TableColumn Column8 = jTable1.getColumnModel().getColumn(8);
        Drive.CargarComboArticulo(comboBoxart);
        Column8.setCellEditor(new DefaultCellEditor(comboBoxart));

        int[] anchos = {30, 130, 150, 75, 60, 60, 40, 40, 120, 300};
        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        jTable1.getTableHeader().setDefaultRenderer(new HeaderRenderer(jTable1));
        ImageIcon fot = new ImageIcon(getClass().getResource("/imagenes/image.jpg"));
        Icon icono1 = new ImageIcon(fot.getImage().getScaledInstance(jLabel3.getWidth(), jLabel3.getHeight(), Image.SCALE_DEFAULT));
        jLabel3.setIcon(icono1);
        jLabel3.repaint();
        
        ImageIcon fott2 = new ImageIcon(getClass().getResource("/imagenes/no.png"));
        Icon icono2 = new ImageIcon(fott2.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        jButton1.setIcon(icono2);
        ImageIcon fott3 = new ImageIcon(getClass().getResource("/imagenes/ok.png"));
        Icon icono3 = new ImageIcon(fott3.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        jButton2.setIcon(icono3);
        ImageIcon fott6 = new ImageIcon(getClass().getResource("/imagenes/Imprimir2.png"));
        Icon icono6 = new ImageIcon(fott6.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        jButton3.setIcon(icono6);
        
        if(adm.getPerfil().getAsistenciasins()==null||adm.getPerfil().getAsistenciasact()==null){
            jButton2.setEnabled(false);
        }
        ((JComponent) jTable1.getDefaultRenderer(Boolean.class)).setOpaque(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SISTEMA DE ASISTENCIA DE PERSONAL EDUCATIVO");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("INASISTENCIAS"));

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº", "Actividad", "Personal", "Fecha", "Inicio", "Fin", "Asist.", "Tard.", "Articulo", "Motivo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTable1KeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Mes:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jLabel2.setText("Año:");

        jTextField1.setEditable(false);
        jTextField1.setEnabled(false);

        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Aceptar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setPreferredSize(new java.awt.Dimension(15, 15));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jButton3.setText("Imprimir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Personal");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Todos");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 822, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4))))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2, jButton3});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton4, jButton5});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton4)
                        .addComponent(jButton5)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)
                            .addComponent(jButton3)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private static class HeaderRenderer implements TableCellRenderer {

        DefaultTableCellRenderer renderer;

        public HeaderRenderer(JTable table) {
            renderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
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

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
//        Drive.LimpiarTabla(jTable1);
        String mes = String.valueOf(jComboBox1.getSelectedItem());
        Drive.CargarTablaInasistencias(jTable1, mes, Integer.parseInt(jTextField1.getText()));
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            int e = 0;
            while (modelo.getRowCount() != e) {
                Asistencia asis = Drive.getAsistencia(Integer.parseInt(modelo.getValueAt(e, 0).toString()));
//                boolean ban=false;
//                if(asis.getJustificacions().iterator().hasNext()){
//                    if(!asis.getJustificacions().iterator().next().getMotivo().equals(modelo.getValueAt(e, 9))||!asis.getJustificacions().iterator().next().getArticulo().equals(modelo.getValueAt(e, 8))){
//                        ban=true;
//                    }
//                }
//                if(!modelo.getValueAt(e, 6).equals(asis.getEstado())||!modelo.getValueAt(e, 7).equals(asis.getTardanza())/*||ban*/){
                //Object combo= modelo.get;
                //if(!modelo.getValueAt(e, 6).equals(asis.getEstado()) || !modelo.getValueAt(e, 7).equals(asis.getTardanza())){
                if (modelo.getValueAt(e, 6).equals(true) && modelo.getValueAt(e, 7).equals(false)) {
                    Object a = modelo.getValueAt(e, 8);
                    Object m = modelo.getValueAt(e, 9);
                    boolean res = modelo.getValueAt(e, 6).equals(true);
                    boolean res2 = modelo.getValueAt(e, 7).equals(true);
                    if (asis.getEstado() != res) {
                        //Cambia una inasistencia o una tardanza por una asistencia
                        if (a != null && m != null) {
                            if (m.toString().length() <= 16) {
                                asis.setEstado(true);
                                asis.setTardanza(false);
                                asis.guardarAsistencia(asis);
                                Justificacion jus = asis.getJustificacion(asis);
                                jus.setAsistencia(asis);
                                jus.setMotivo("Inasistencia por asistencia: " + modelo.getValueAt(e, 9).toString());
                                Articulo art = (Articulo) modelo.getValueAt(e, 8);
                                jus.setArticulo(art);
                                if (jus.getIdJustificacion() != null) {
                                    jus.actualizarJustificacion(jus);
                                } else {
                                    jus.guardarJustificacion(jus);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "El motivo es muy largo, por favor reduzcalo", "Registrar Inasistencia", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Para cambiar una inasistencia por una asistencia, necesita un artículo y un motivo", "Registrar Inasistencia", JOptionPane.ERROR_MESSAGE);
                        }
                    } else if (asis.getTardanza() != res2) {
                        //Cambia una tardanza por una asistencia
                        if (a != null && m != null) {
                            if (m.toString().length() <= 20) {
                                asis.setEstado(true);
                                asis.setTardanza(false);
                                asis.guardarAsistencia(asis);
                                Justificacion jus = asis.getJustificacion(asis);
                                jus.setAsistencia(asis);
                                jus.setMotivo("Tardanza por asistencia: " + modelo.getValueAt(e, 9).toString());
                                Articulo art = (Articulo) modelo.getValueAt(e, 8);
                                jus.setArticulo(art);
                                if (jus.getIdJustificacion() != null) {
                                    jus.actualizarJustificacion(jus);
                                } else {
                                    jus.guardarJustificacion(jus);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "El motivo es muy largo, por favor reduzcalo", "Registrar Inasistencia", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Para cambiar una tardanza por una asistencia, necesita un artículo y un motivo", "Registrar Inasistencia", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else if (modelo.getValueAt(e, 6).equals(false) && modelo.getValueAt(e, 7).equals(true)) {
                    JOptionPane.showMessageDialog(null, "Esta operacion no esta permitida para la asistencia numero: " + asis.getIdAsistencia(), "Registrar Inasistencia", JOptionPane.ERROR_MESSAGE);
                } else if (modelo.getValueAt(e, 6).equals(false) && modelo.getValueAt(e, 7).equals(false)) {
                    Object a = modelo.getValueAt(e, 8);
                    Object m = modelo.getValueAt(e, 9);
                    boolean res = modelo.getValueAt(e, 6).equals(true);
                    boolean res2 = modelo.getValueAt(e, 7).equals(true);
                    if (asis.getEstado() == res && asis.getTardanza() == res2) {
                        if (a != null && m != null) {
                            if(asis.getJustificacions().iterator().hasNext()){
                                if(!asis.getJustificacions().iterator().next().getMotivo().equals(m)||!asis.getJustificacions().iterator().next().getArticulo().equals(a)){
                                    if (m.toString().length() <= 45) {
                                        asis.getJustificacions().iterator().next().setMotivo(modelo.getValueAt(e, 9).toString());
                                        asis.getJustificacions().iterator().next().setArticulo((Articulo) modelo.getValueAt(e, 8));
                                        asis.getJustificacions().iterator().next().actualizarJustificacion(asis.getJustificacions().iterator().next());
                                    } else {
                                        JOptionPane.showMessageDialog(null, "El motivo es muy largo, por favor reduzcalo", "Registrar Inasistencia", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            }else{  
                                if (m.toString().length() <= 45) {
//                                    asis.setEstado(false);
//                                    asis.setTardanza(false);
//                                    asis.ActualizarAsistencia(asis);
                                    Justificacion jus = asis.getJustificacion(asis);
                                    jus.setAsistencia(asis);
                                    jus.setMotivo(modelo.getValueAt(e, 9).toString());
                                    Articulo art = (Articulo) modelo.getValueAt(e, 8);
                                    jus.setArticulo(art);
//                                    if (jus.getIdJustificacion() != null) {
//                                        jus.actualizarJustificacion(jus);
//                                    } else {
                                        jus.guardarJustificacion(jus);
//                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "El motivo es muy largo, por favor reduzcalo", "Registrar Inasistencia", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No se puede cambiar una tardanza por una inasistencia", "Registrar Inasistencia", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (modelo.getValueAt(e, 6).equals(true) && modelo.getValueAt(e, 7).equals(true)) {
                        Object a = modelo.getValueAt(e, 8);
                        Object m = modelo.getValueAt(e, 9);
                        if (asis.getEstado() != modelo.getValueAt(e, 6).equals(true)) {
                            if (a != null && m != null) {
                                if (m.toString().length() <= 18) {
                                    asis.setEstado(true);
                                    asis.setTardanza(true);
                                    asis.guardarAsistencia(asis);
                                    Justificacion jus = asis.getJustificacion(asis);
                                    jus.setAsistencia(asis);
                                    jus.setMotivo("Inasistencia por tardanza: " + modelo.getValueAt(e, 9).toString());
                                    Articulo art = (Articulo) modelo.getValueAt(e, 8);
                                    jus.setArticulo(art);
                                    if (jus.getIdJustificacion() != null) {
                                        jus.actualizarJustificacion(jus);
                                    } else {
                                    jus.guardarJustificacion(jus);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "El motivo es muy largo, por favor reduzcalo", "Registrar Inasistencia", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Para cambiar una inasistencia por una tardanza, necesita un artículo y un motivo", "Registrar Inasistencia", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        if (asis.getJustificacions().iterator().hasNext()) {
                            if (!asis.getJustificacions().iterator().next().getMotivo().equals(m) || !asis.getJustificacions().iterator().next().getArticulo().equals(a)) {
                                JOptionPane.showMessageDialog(null, "No se puede modificar el motivo de esta tardanza", "Registrar Inasistencia", JOptionPane.ERROR_MESSAGE);
                            }
                        }else{
                            if (a != null && m != null) {
                                if (m.toString().length() <= 45) {
                                    Justificacion jus=new Justificacion();
                                    jus.setAsistencia(asis);
                                    jus.setMotivo(modelo.getValueAt(e, 9).toString());
                                    Articulo art=(Articulo) modelo.getValueAt(e, 8);
                                    jus.setArticulo(art);
                                    jus.guardarJustificacion(jus);
                                } else {
                                    JOptionPane.showMessageDialog(null, "El motivo es muy largo, por favor reduzcalo", "Registrar Inasistencia", JOptionPane.ERROR_MESSAGE);
                                }
//                            }else{
//                                JOptionPane.showMessageDialog(null, "Para guardar una tardanza, necesita un artículo y un motivo", "Registrar Inasistencia", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }
                e++;
            }
//            Drive.LimpiarTabla(jTable1);
            String mes = String.valueOf(jComboBox1.getSelectedItem());
            Drive.CargarTablaInasistencias(jTable1, mes, Integer.parseInt(jTextField1.getText()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar la inasistencia", "Registrar Inasistencia", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int confirmado = JOptionPane.showConfirmDialog(null, "¿Desea volver al menu principal?", "Registrar Inasistencia", JOptionPane.YES_NO_OPTION);
        if (JOptionPane.OK_OPTION == confirmado) {
            Frame vp = new JFramePrincipal(Drive, adm);
            this.dispose();
            vp.show();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
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
                    JOptionPane.showMessageDialog(null, "El Articulo ya existe");
                    w = true;
                }
            }
            if (w == false) {
                Articulo art = new Articulo();
                art.setNombre(cadNombre);
                art.setNroArticulo(cadnumero);
                art.guardarArticulo(art);
                Drive.LimpiarCombo(comboBoxart);
                Drive.CargarComboArticulo(comboBoxart);
//                comboBoxart.setSelectedItem(art);
            }
        }
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            int me=Drive.ObtenerMes(jComboBox1.getSelectedItem().toString());
            String mes=(String) jComboBox1.getSelectedItem();
            JComboBox salida = new JComboBox();
            String cadSalida;
            salida.addItem("Asistencias");
            salida.addItem("Asistencias justificadas");
            salida.addItem("Tardanzas");
            salida.addItem("Tardanzas justificadas");
            salida.addItem("Inasistencias");
            salida.addItem("Inasistencias justificadas");
            salida.setSize(25, 25);
            JOptionPane.showMessageDialog(null, salida, "¿Que desea imprimir?", JOptionPane.INFORMATION_MESSAGE);
            cadSalida = salida.getSelectedItem().toString();
            Iterator it=Drive.PERSISTENCIA.getMes(me).iterator();
            if (cadSalida.equals("Asistencias")) {
                List inicio= new ArrayList();
                while(it.hasNext()){
                    Mes m=(Mes) it.next();
                    if(m.getAno().getAgenda().getTarea().getEstado()==true && m.getAno().getAgenda().getPersonal().getEstado()==true){
                        Iterator itt=m.getDias().iterator();
                        while(itt.hasNext()){
                            Dia di=(Dia) itt.next();
                            Iterator ittt=di.getIniciofins().iterator();
                            while(ittt.hasNext()){
                                Iniciofin ini=(Iniciofin) ittt.next();
                                if(ini.getAsistencias().iterator().hasNext()){
                                    Asistencia as=ini.getAsistencias().iterator().next();
                                    if(!as.getJustificacions().iterator().hasNext()){
                                        if(ini.getAsistencias().iterator().next().getEstado()==true&&ini.getAsistencias().iterator().next().getTardanza()==false){
                                            inicio.add(ini);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                Drive.mostrarReporte("Injustificadas",inicio,"Lista de Asistencias","Asistencias",mes,inicio.size());
            }
            else if (cadSalida.equals("Asistencias justificadas")) {
                List just= new ArrayList();
                while(it.hasNext()){
                    Mes m=(Mes) it.next();
                    if(m.getAno().getAgenda().getTarea().getEstado()==true && m.getAno().getAgenda().getPersonal().getEstado()==true){
                        Iterator itt=m.getDias().iterator();
                        while(itt.hasNext()){
                            Dia di=(Dia) itt.next();
                            Iterator ittt=di.getIniciofins().iterator();
                            while(ittt.hasNext()){
                                Iniciofin ini=(Iniciofin) ittt.next();
                                if(ini.getAsistencias().iterator().hasNext()){
                                    Asistencia as=ini.getAsistencias().iterator().next();
                                    if(as.getJustificacions().iterator().hasNext()){
                                        if(ini.getAsistencias().iterator().next().getEstado()==true&&ini.getAsistencias().iterator().next().getTardanza()==false){
                                            Justificacion ju=as.getJustificacions().iterator().next();
                                            just.add(ju);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                Drive.mostrarReporte("Justificadas",just,"Lista de Asistencias Justificadas","Asistencias Justificadas",mes,just.size());
            }
            else if (cadSalida.equals("Tardanzas")) {
                List inicio= new ArrayList();
                while(it.hasNext()){
                    Mes m=(Mes) it.next();
                    if(m.getAno().getAgenda().getTarea().getEstado()==true && m.getAno().getAgenda().getPersonal().getEstado()==true){
                        Iterator itt=m.getDias().iterator();
                        while(itt.hasNext()){
                            Dia di=(Dia) itt.next();
                            Iterator ittt=di.getIniciofins().iterator();
                            while(ittt.hasNext()){
                                Iniciofin ini=(Iniciofin) ittt.next();
                                if(ini.getAsistencias().iterator().hasNext()){
                                    Asistencia as=ini.getAsistencias().iterator().next();
                                    if(!as.getJustificacions().iterator().hasNext()){
                                        if(ini.getAsistencias().iterator().next().getEstado()==true&&ini.getAsistencias().iterator().next().getTardanza()==true){
                                            inicio.add(ini);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                Drive.mostrarReporte("Injustificadas",inicio,"Lista de Tardanzas","Tardanzas",mes,inicio.size());
            }
            else if (cadSalida.equals("Tardanzas justificadas")) {
                List just= new ArrayList();
                while(it.hasNext()){
                    Mes m=(Mes) it.next();
                    if(m.getAno().getAgenda().getTarea().getEstado()==true && m.getAno().getAgenda().getPersonal().getEstado()==true){
                        Iterator itt=m.getDias().iterator();
                        while(itt.hasNext()){
                            Dia di=(Dia) itt.next();
                            Iterator ittt=di.getIniciofins().iterator();
                            while(ittt.hasNext()){
                                Iniciofin ini=(Iniciofin) ittt.next();
                                if(ini.getAsistencias().iterator().hasNext()){
                                    Asistencia as=ini.getAsistencias().iterator().next();
                                    if(as.getJustificacions().iterator().hasNext()){
                                        if(ini.getAsistencias().iterator().next().getEstado()==true&&ini.getAsistencias().iterator().next().getTardanza()==true){
                                            Justificacion ju=as.getJustificacions().iterator().next();
                                            just.add(ju);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                Drive.mostrarReporte("Justificadas",just,"Lista de Tardanzas justificadas","Tardanzas justificadas",mes,just.size());
            }
            else if (cadSalida.equals("Inasistencias")) {
                List inicio= new ArrayList();
                while(it.hasNext()){
                    Mes m=(Mes) it.next();
                    if(m.getAno().getAgenda().getTarea().getEstado()==true && m.getAno().getAgenda().getPersonal().getEstado()==true){
                        Iterator itt=m.getDias().iterator();
                        while(itt.hasNext()){
                            Dia di=(Dia) itt.next();
                            Iterator ittt=di.getIniciofins().iterator();
                            while(ittt.hasNext()){
                                Iniciofin ini=(Iniciofin) ittt.next();
                                if(ini.getAsistencias().iterator().hasNext()){
                                    Asistencia as=ini.getAsistencias().iterator().next();
                                    if(!as.getJustificacions().iterator().hasNext()){
                                        if(ini.getAsistencias().iterator().next().getEstado()==false&&ini.getAsistencias().iterator().next().getTardanza()==false){
                                            inicio.add(ini);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                Drive.mostrarReporte("Injustificadas",inicio,"Lista de Inasistencias injustificadas","Inasistencias injustificadas",mes,inicio.size());
            }
            else if (cadSalida.equals("Inasistencias justificadas")) {
                List just= new ArrayList();
                while(it.hasNext()){
                    Mes m=(Mes) it.next();
                    if(m.getAno().getAgenda().getTarea().getEstado()==true && m.getAno().getAgenda().getPersonal().getEstado()==true){
                        Iterator itt=m.getDias().iterator();
                        while(itt.hasNext()){
                            Dia di=(Dia) itt.next();
                            Iterator ittt=di.getIniciofins().iterator();
                            while(ittt.hasNext()){
                                Iniciofin ini=(Iniciofin) ittt.next();
                                if(ini.getAsistencias().iterator().hasNext()){
                                    Asistencia as=ini.getAsistencias().iterator().next();
                                    if(as.getJustificacions().iterator().hasNext()){
                                        if(ini.getAsistencias().iterator().next().getEstado()==false&&ini.getAsistencias().iterator().next().getTardanza()==false){
                                            Justificacion ju=as.getJustificacions().iterator().next();
                                            just.add(ju);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                Drive.mostrarReporte("Justificadas",just,"Lista de Inasistencias justificadas","Inasistencias justificadas",mes,just.size());
            }
        }catch (Exception Ex) {
            JOptionPane.showMessageDialog(null, "Ingrese correctamente los datos", "Error de impresion", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int confirmado = JOptionPane.showConfirmDialog(null, "¿Desea volver al menu principal?", "Registrar Inasistencia", JOptionPane.YES_NO_OPTION);
        if (JOptionPane.OK_OPTION == confirmado) {
            Frame vp = new JFramePrincipal(Drive, adm);
            this.dispose();
            vp.show();
        }else{}
    }//GEN-LAST:event_formWindowClosing

    private void jTable1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1KeyTyped

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        JFrameConsulta vent2 = new JFrameConsulta(Drive,adm,true);
        this.hide();
        vent2.show();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String mes = String.valueOf(jComboBox1.getSelectedItem());
        Drive.CargarTablaInasistencias(jTable1, mes, Integer.parseInt(jTextField1.getText()));
    }//GEN-LAST:event_jButton5ActionPerformed
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
//            java.util.logging.Logger.getLogger(JFrameInasistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(JFrameInasistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(JFrameInasistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(JFrameInasistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new JFrameInasistencia().setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
