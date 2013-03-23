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
import Clases.Establecimiento;
import Clases.Justificacion;
import Clases.Personal;
import Clases.Tipodoc;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
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
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author fer
 */
public class JFrameInasistencia extends javax.swing.JFrame {

    /**
     * Creates new form JFrameInasistencia
     */
    public Controlador Drive;
    public Personal adm;
    int idsesion;
    JComboBox comboBoxart = new JComboBox();
    JasperReport reporte;

    public JFrameInasistencia(Controlador unDrive, Personal admin, int id) {
        this.Drive = unDrive;
        this.adm = admin;
        this.idsesion = id;
        initComponents();

        jTextField1.setText(String.valueOf(Calendar.getInstance().getTime().getYear() + 1900));
        String mes = String.valueOf(jComboBox1.getSelectedItem());
        Drive.CargarTablaInasistencias(jTable1, mes, Integer.parseInt(jTextField1.getText()));

        TableColumn Column8 = jTable1.getColumnModel().getColumn(8);
        Drive.CargarComboArticulo(comboBoxart);
        Column8.setCellEditor(new DefaultCellEditor(comboBoxart));

        int[] anchos = {30, 130, 150, 75, 60, 60, 40, 40, 120, 300};
        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        jTable1.getTableHeader().setDefaultRenderer(new HeaderRenderer(jTable1));
        ImageIcon fot = new ImageIcon("src\\imagenes\\image.jpg");
        Icon icono1 = new ImageIcon(fot.getImage().getScaledInstance(jLabel3.getWidth(), jLabel3.getHeight(), Image.SCALE_DEFAULT));
        jLabel3.setIcon(icono1);
        jLabel3.repaint();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SISTEMA DE ASISTENCIA DE PERSONAL EDUCATIVO");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("INASISTENCIAS"));

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField1)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 822, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2, jButton3});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
        Drive.LimpiarTabla(jTable1);
        String mes = String.valueOf(jComboBox1.getSelectedItem());
        Drive.CargarTablaInasistencias(jTable1, mes, Integer.parseInt(jTextField1.getText()));
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            int e = 0;
            while (modelo.getRowCount() != e) {
                Asistencia asis = Drive.getAsistencia(Integer.parseInt(modelo.getValueAt(e, 0).toString()));
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
                            if (m.toString().length() <= 45) {
                                asis.setEstado(false);
                                asis.setTardanza(false);
                                asis.guardarAsistencia(asis);
                                Justificacion jus = asis.getJustificacion(asis);
                                jus.setAsistencia(asis);
                                jus.setMotivo(modelo.getValueAt(e, 9).toString());
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
                        if (a != null && m != null) {
                            if (m.toString().length() <= 45) {
                                Justificacion jus = asis.getJustificacion(asis);
                                if (!jus.getMotivo().contains("Inasistencia por tardanza")) {
                                    asis.setEstado(true);
                                    asis.setTardanza(true);
                                    asis.guardarAsistencia(asis);
                                    jus.setAsistencia(asis);
                                    jus.setMotivo(modelo.getValueAt(e, 9).toString());
                                    Articulo art = (Articulo) modelo.getValueAt(e, 8);
                                    jus.setArticulo(art);
                                    if (jus.getIdJustificacion() != null) {
                                        jus.actualizarJustificacion(jus);
                                    } else {
                                        jus.guardarJustificacion(jus);
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "No se puede modificar el motivo de esta tardanza", "Registrar Inasistencia", JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "El motivo es muy largo, por favor reduzcalo", "Registrar Inasistencia", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Para cambiar una inasistencia por una tardanza, necesita un artículo y un motivo", "Registrar Inasistencia", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                //            }
                e++;
            }
            Drive.LimpiarTabla(jTable1);
            String mes = String.valueOf(jComboBox1.getSelectedItem());
            Drive.CargarTablaInasistencias(jTable1, mes, Integer.parseInt(jTextField1.getText()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar la inasistencia", "Registrar Inasistencia", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int confirmado = JOptionPane.showConfirmDialog(null, "¿Desea volver al menu principal?", "", JOptionPane.YES_NO_OPTION);
        if (JOptionPane.OK_OPTION == confirmado) {
            Frame vp = new JFramePrincipal(Drive, adm, idsesion);
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
                comboBoxart.setSelectedItem(art);
            }
        }
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            JComboBox salida = new JComboBox();
            String cadSalida;
            salida.addItem("Asistencias");
            salida.addItem("Tardanzas");
            salida.addItem("Inasistencias");
            salida.setSize(25, 25);
            JOptionPane.showMessageDialog(null, salida, "¿Que desea imprimir?", JOptionPane.INFORMATION_MESSAGE);
            cadSalida = salida.getSelectedItem().toString();
            if (cadSalida.equals("Asistencias")) {
                List consulta = Controlador.getPERSISTENCIA().getAsistenciasReporte();
                Drive.mostrarReporte("Asistencias", consulta, "Lista de Asistencias","1","0","Asistencias");
            } else if (cadSalida.equals("Tardanzas")) {
                List consulta = Controlador.getPERSISTENCIA().getTardanzasReporte();
                Drive.mostrarReporte("Asistencia", consulta, "Lista de Tardanzas","1","1","Tardanzas");
            } else if (cadSalida.equals("Inasistencias")) {
                List consulta = Controlador.getPERSISTENCIA().getInasistenciasReporte();
                Drive.mostrarReporte("Asistencia", consulta, "Lista de Inasistencias","0","0","Inasistencias");
            }
        } catch (Exception Ex) {
            JOptionPane.showMessageDialog(null, "Ingrese correctamente los datos", "Error de impresion", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Frame vp = new JFramePrincipal(Drive, adm, idsesion);
        this.dispose();
        vp.show();        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing
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
