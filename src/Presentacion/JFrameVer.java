/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Clases.Controlador;
import Clases.Personal;
import Clases.Tarea;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Image;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Fernando
 */
public class JFrameVer extends javax.swing.JFrame {

    /**
     * Creates new form JFrameVer
     */
    public Controlador Drive;
    public Personal adm;
//    int idsesion;
    Personal per = new Personal();
    Tarea tar=new Tarea();
    
    public JFrameVer(Controlador unDrive,Personal admin) {
        this.Drive=unDrive;
        this.adm=admin;
//        this.idsesion=id;
        initComponents();
        String ver=jComboBox1.getSelectedItem().toString();
        Date hoy=new Date();
        Date hora=new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
        try {
            hora = formateador.parse(formateador.format(hoy));
        } catch (ParseException ex) {
            Logger.getLogger(JFrameVer.class.getName()).log(Level.SEVERE, null, ex);
        }
        ImageIcon fott2 = new ImageIcon(getClass().getResource("/imagenes/no.png"));
        Icon icono2 = new ImageIcon(fott2.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        jButton1.setIcon(icono2);
        ImageIcon fott5 = new ImageIcon(getClass().getResource("/imagenes/Buscar.png"));
        Icon icono5 = new ImageIcon(fott5.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        jButton2.setIcon(icono5);
        
        jFormattedTextField1.setValue(formateador.format(hoy));
        if(ver.equals("PERSONAL")){
                JTableHeader th = jTable1.getTableHeader();
                TableColumnModel tcm = th.getColumnModel();
                TableColumn t = tcm.getColumn(0); //recordemos que las columnas inician a enumerarse desde cero
                t.setHeaderValue("Personal");
                TableColumn tc = tcm.getColumn(1); //recordemos que las columnas inician a enumerarse desde cero
                tc.setHeaderValue("DNI");
                TableColumn tc1 = tcm.getColumn(2);
                tc1.setHeaderValue("Correo electrónico");
                TableColumn tc2 = tcm.getColumn(3);
                tc2.setHeaderValue("Fecha ingreso");
                th.repaint();
                int[] anchos1 = {150, 80, 150,100};
                for (int i = 0; i < jTable1.getColumnCount(); i++) {
                    jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos1[i]);
                }
                DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
                modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
                jTable1.getColumnModel().getColumn(1).setCellRenderer(modelocentrar);
                jTable1.getColumnModel().getColumn(3).setCellRenderer(modelocentrar);
                jTable1.getTableHeader().setDefaultRenderer(new JFrameVer.HeaderRenderer(jTable1));
                
                JTableHeader thh = jTable2.getTableHeader();
                TableColumnModel tcmm = thh.getColumnModel();
                TableColumn tt = tcmm.getColumn(0); //recordemos que las columnas inician a enumerarse desde cero
                tt.setHeaderValue("Nombre");
                TableColumn ttc = tcmm.getColumn(1); //recordemos que las columnas inician a enumerarse desde cero
                ttc.setHeaderValue("Tipo");
                TableColumn ttc1 = tcmm.getColumn(2);
                ttc1.setHeaderValue("Inicio");
                TableColumn ttc2 = tcmm.getColumn(3);
                ttc2.setHeaderValue("Fin");
                thh.repaint();
                
                int[] anchos2 = {140, 110, 60,60};
                for (int i = 0; i < jTable2.getColumnCount(); i++) {
                    jTable2.getColumnModel().getColumn(i).setPreferredWidth(anchos2[i]);
                }
                DefaultTableCellRenderer modelocentrar2 = new DefaultTableCellRenderer();
                modelocentrar2.setHorizontalAlignment(SwingConstants.CENTER);
//                jTable2.getColumnModel().getColumn(1).setCellRenderer(modelocentrar2);
                jTable2.getColumnModel().getColumn(2).setCellRenderer(modelocentrar2);
                jTable2.getColumnModel().getColumn(3).setCellRenderer(modelocentrar2);
                jTable2.getTableHeader().setDefaultRenderer(new JFrameVer.HeaderRenderer(jTable2));
    //            jTable1.setEnabled(false);
                Drive.CargarTablaPerHoy(jTable1, hoy, hora);
//                Drive.CargarTablaTarHoy(jTable2, hoy, hora);
            }else if(ver.equals("ACTIVIDADES")){
                JTableHeader th = jTable1.getTableHeader();
                TableColumnModel tcm = th.getColumnModel();
                TableColumn t = tcm.getColumn(0); //recordemos que las columnas inician a enumerarse desde cero
                t.setHeaderValue("Nombre");
                TableColumn tc = tcm.getColumn(1); //recordemos que las columnas inician a enumerarse desde cero
                tc.setHeaderValue("Tipo");
                TableColumn tc1 = tcm.getColumn(2);
                tc1.setHeaderValue("Inicio");
                TableColumn tc2 = tcm.getColumn(3);
                tc2.setHeaderValue("Fin");
                th.repaint();
                int[] anchos2 = {140, 110, 60,60};
                for (int i = 0; i < jTable1.getColumnCount(); i++) {
                    jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos2[i]);
                }
                DefaultTableCellRenderer modelocentrar2 = new DefaultTableCellRenderer();
                modelocentrar2.setHorizontalAlignment(SwingConstants.CENTER);
                jTable1.getColumnModel().getColumn(2).setCellRenderer(modelocentrar2);
                jTable1.getColumnModel().getColumn(3).setCellRenderer(modelocentrar2);
                jTable1.getTableHeader().setDefaultRenderer(new JFrameVer.HeaderRenderer(jTable1));
                
                JTableHeader tth = jTable2.getTableHeader();
                TableColumnModel ttcm = tth.getColumnModel();
                TableColumn tt = ttcm.getColumn(0); //recordemos que las columnas inician a enumerarse desde cero
                tt.setHeaderValue("Personal");
                TableColumn ttc = ttcm.getColumn(1); //recordemos que las columnas inician a enumerarse desde cero
                ttc.setHeaderValue("DNI");
                TableColumn ttc1 = ttcm.getColumn(2);
                ttc1.setHeaderValue("Correo electrónico");
                TableColumn ttc2 = ttcm.getColumn(3);
                ttc2.setHeaderValue("Fecha ingreso");
                tth.repaint();
                int[] anchos1 = {150, 80, 150,100};
                for (int i = 0; i < jTable2.getColumnCount(); i++) {
                    jTable2.getColumnModel().getColumn(i).setPreferredWidth(anchos1[i]);
                }
                DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
                modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
                jTable2.getColumnModel().getColumn(1).setCellRenderer(modelocentrar);
                jTable2.getColumnModel().getColumn(3).setCellRenderer(modelocentrar);
                jTable2.getTableHeader().setDefaultRenderer(new JFrameVer.HeaderRenderer(jTable2));
                
    //            jTable1.setEnabled(false);
                Drive.CargarTablaTarHoy(jTable1, hoy, hora);
//                Drive.CargarTablaPerHoy(jTable2, hoy, hora);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SISTEMA DE ASISTENCIA DE PERSONAL EDUCATIVO");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel1.setText("Fecha:");

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
    dateChooserCombo1.setEnabled(false);
    dateChooserCombo1.setBehavior(datechooser.model.multiple.MultyModelBehavior.SELECT_SINGLE);

    jLabel2.setText("Ver:");

    jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PERSONAL", "ACTIVIDADES" }));
    jComboBox1.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            jComboBox1ItemStateChanged(evt);
        }
    });

    jTable1.setAutoCreateRowSorter(true);
    jTable1.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Personal", "DNI", "Correo electrónico", "Fecha ingreso"
        }
    ) {
        boolean[] canEdit = new boolean [] {
            false, false, false, false
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jTable1MouseClicked(evt);
        }
    });
    jScrollPane1.setViewportView(jTable1);

    jTable2.setAutoCreateRowSorter(true);
    jTable2.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Personal", "DNI", "Correo electrónico", "Fecha ingreso"
        }
    ) {
        boolean[] canEdit = new boolean [] {
            false, false, false, false
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    jScrollPane2.setViewportView(jTable2);

    jButton1.setText("Salir");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton1ActionPerformed(evt);
        }
    });

    jLabel3.setText("Hora:");

    try {
        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
    } catch (java.text.ParseException ex) {
        ex.printStackTrace();
    }
    jFormattedTextField1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jFormattedTextField1ActionPerformed(evt);
        }
    });
    jFormattedTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusLost(java.awt.event.FocusEvent evt) {
            jFormattedTextField1FocusLost(evt);
        }
    });

    jLabel9.setText("hh:mm");

    jButton2.setText("Buscar");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton2ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jButton1))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2)))))
            .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(jLabel2)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(16, 16, 16)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(jLabel1)
                .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel3)
                .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton2))
            .addGap(2, 2, 2)
            .addComponent(jLabel9)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jButton1)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addGap(23, 23, 23))
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
    
    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        try {
            String ver=jComboBox1.getSelectedItem().toString();
            Date hoy=new Date();
            SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
            Date hora = formateador.parse(jFormattedTextField1.getText());
            if(ver.equals("PERSONAL")){
                JTableHeader th = jTable1.getTableHeader();
                TableColumnModel tcm = th.getColumnModel();
                TableColumn t = tcm.getColumn(0); //recordemos que las columnas inician a enumerarse desde cero
                t.setHeaderValue("Personal");
                TableColumn tc = tcm.getColumn(1); //recordemos que las columnas inician a enumerarse desde cero
                tc.setHeaderValue("DNI");
                TableColumn tc1 = tcm.getColumn(2);
                tc1.setHeaderValue("Correo electrónico");
                TableColumn tc2 = tcm.getColumn(3);
                tc2.setHeaderValue("Fecha ingreso");
                th.repaint();
                int[] anchos1 = {150, 80, 150,100};
                for (int i = 0; i < jTable1.getColumnCount(); i++) {
                    jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos1[i]);
                }
                DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
                modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
                jTable1.getColumnModel().getColumn(1).setCellRenderer(modelocentrar);
                jTable1.getColumnModel().getColumn(3).setCellRenderer(modelocentrar);
//                jTable1.getTableHeader().setDefaultRenderer(new JFrameVer.HeaderRenderer(jTable1));
                
                JTableHeader thh = jTable2.getTableHeader();
                TableColumnModel tcmm = thh.getColumnModel();
                TableColumn tt = tcmm.getColumn(0); //recordemos que las columnas inician a enumerarse desde cero
                tt.setHeaderValue("Nombre");
                TableColumn ttc = tcmm.getColumn(1); //recordemos que las columnas inician a enumerarse desde cero
                ttc.setHeaderValue("Tipo");
                TableColumn ttc1 = tcmm.getColumn(2);
                ttc1.setHeaderValue("Inicio");
                TableColumn ttc2 = tcmm.getColumn(3);
                ttc2.setHeaderValue("Fin");
                thh.repaint();
                int[] anchos2 = {140, 110, 60,60};
                for (int i = 0; i < jTable2.getColumnCount(); i++) {
                    jTable2.getColumnModel().getColumn(i).setPreferredWidth(anchos2[i]);
                }
                DefaultTableCellRenderer modelocentrar2 = new DefaultTableCellRenderer();
                modelocentrar2.setHorizontalAlignment(SwingConstants.CENTER);
                jTable2.getColumnModel().getColumn(2).setCellRenderer(modelocentrar2);
                jTable2.getColumnModel().getColumn(3).setCellRenderer(modelocentrar2);
//                jTable2.getTableHeader().setDefaultRenderer(new JFrameVer.HeaderRenderer(jTable2));

                Drive.CargarTablaPerHoy(jTable1, hoy, hora);
            }else if(ver.equals("ACTIVIDADES")){
                JTableHeader th = jTable1.getTableHeader();
                TableColumnModel tcm = th.getColumnModel();
                TableColumn t = tcm.getColumn(0); //recordemos que las columnas inician a enumerarse desde cero
                t.setHeaderValue("Nombre");
                TableColumn tc = tcm.getColumn(1); //recordemos que las columnas inician a enumerarse desde cero
                tc.setHeaderValue("Tipo");
                TableColumn tc1 = tcm.getColumn(2);
                tc1.setHeaderValue("Inicio");
                TableColumn tc2 = tcm.getColumn(3);
                tc2.setHeaderValue("Fin");
                th.repaint();
                int[] anchos2 = {140, 110, 60,60};
                for (int i = 0; i < jTable1.getColumnCount(); i++) {
                    jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos2[i]);
                }
                DefaultTableCellRenderer modelocentrar2 = new DefaultTableCellRenderer();
                modelocentrar2.setHorizontalAlignment(SwingConstants.CENTER);
                jTable1.getColumnModel().getColumn(2).setCellRenderer(modelocentrar2);
                jTable1.getColumnModel().getColumn(3).setCellRenderer(modelocentrar2);
//                jTable1.getTableHeader().setDefaultRenderer(new JFrameVer.HeaderRenderer(jTable1));
                
                JTableHeader tth = jTable2.getTableHeader();
                TableColumnModel ttcm = tth.getColumnModel();
                TableColumn tt = ttcm.getColumn(0); //recordemos que las columnas inician a enumerarse desde cero
                tt.setHeaderValue("Personal");
                TableColumn ttc = ttcm.getColumn(1); //recordemos que las columnas inician a enumerarse desde cero
                ttc.setHeaderValue("DNI");
                TableColumn ttc1 = ttcm.getColumn(2);
                ttc1.setHeaderValue("Correo electrónico");
                TableColumn ttc2 = ttcm.getColumn(3);
                ttc2.setHeaderValue("Fecha ingreso");
                tth.repaint();
                int[] anchos1 = {150, 80, 150,100};
                for (int i = 0; i < jTable2.getColumnCount(); i++) {
                    jTable2.getColumnModel().getColumn(i).setPreferredWidth(anchos1[i]);
                }
                DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
                modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
                jTable2.getColumnModel().getColumn(1).setCellRenderer(modelocentrar);
                jTable2.getColumnModel().getColumn(3).setCellRenderer(modelocentrar);
//                jTable2.getTableHeader().setDefaultRenderer(new JFrameVer.HeaderRenderer(jTable2));
                Drive.CargarTablaTarHoy(jTable1, hoy, hora);
                
//                Drive.CargarTablaPerHoy(jTable2, hoy, hora);
            }
            Drive.LimpiarTabla(jTable2);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error", "Ver hoy", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jFormattedTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField1ActionPerformed

    private void jFormattedTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextField1FocusLost
        try {
            String hora = jFormattedTextField1.getText();
            String h = hora.substring(0, 2);
            String m = hora.substring(3, 5);
            int conta_hora = Integer.parseInt(h);
            int conta_minuto = Integer.parseInt(m);
            if (conta_hora > 23 || conta_minuto > 59) {
                JOptionPane.showMessageDialog(null, "Ingrese correctamente la hora", "Registrar Clase", JOptionPane.ERROR_MESSAGE);
                jFormattedTextField1.setText("00:00");
                return;
            }
            String ver = jComboBox1.getSelectedItem().toString();
            Date hoy = new Date();
            SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
            Date horaa = formateador.parse(jFormattedTextField1.getText());
            if (ver.equals("PERSONAL")) {
                JTableHeader th = jTable1.getTableHeader();
                TableColumnModel tcm = th.getColumnModel();
                TableColumn t = tcm.getColumn(0); //recordemos que las columnas inician a enumerarse desde cero
                t.setHeaderValue("Personal");
                TableColumn tc = tcm.getColumn(1); //recordemos que las columnas inician a enumerarse desde cero
                tc.setHeaderValue("DNI");
                TableColumn tc1 = tcm.getColumn(2);
                tc1.setHeaderValue("Correo electrónico");
                TableColumn tc2 = tcm.getColumn(3);
                tc2.setHeaderValue("Fecha ingreso");
                th.repaint();

                JTableHeader thh = jTable2.getTableHeader();
                TableColumnModel tcmm = thh.getColumnModel();
                TableColumn tt = tcmm.getColumn(0); //recordemos que las columnas inician a enumerarse desde cero
                tt.setHeaderValue("Nombre");
                TableColumn ttc = tcmm.getColumn(1); //recordemos que las columnas inician a enumerarse desde cero
                ttc.setHeaderValue("Tipo");
                TableColumn ttc1 = tcmm.getColumn(2);
                ttc1.setHeaderValue("Inicio");
                TableColumn ttc2 = tcmm.getColumn(3);
                ttc2.setHeaderValue("Fin");
                thh.repaint();
                Drive.CargarTablaPerHoy(jTable1, hoy, horaa);
            } else if (ver.equals("ACTIVIDADES")) {
                JTableHeader th = jTable1.getTableHeader();
                TableColumnModel tcm = th.getColumnModel();
                TableColumn t = tcm.getColumn(0); //recordemos que las columnas inician a enumerarse desde cero
                t.setHeaderValue("Nombre");
                TableColumn tc = tcm.getColumn(1); //recordemos que las columnas inician a enumerarse desde cero
                tc.setHeaderValue("Tipo");
                TableColumn tc1 = tcm.getColumn(2);
                tc1.setHeaderValue("Inicio");
                TableColumn tc2 = tcm.getColumn(3);
                tc2.setHeaderValue("Fin");
                th.repaint();

                JTableHeader tth = jTable2.getTableHeader();
                TableColumnModel ttcm = tth.getColumnModel();
                TableColumn tt = ttcm.getColumn(0); //recordemos que las columnas inician a enumerarse desde cero
                tt.setHeaderValue("Personal");
                TableColumn ttc = ttcm.getColumn(1); //recordemos que las columnas inician a enumerarse desde cero
                ttc.setHeaderValue("DNI");
                TableColumn ttc1 = ttcm.getColumn(2);
                ttc1.setHeaderValue("Correo electrónico");
                TableColumn ttc2 = ttcm.getColumn(3);
                ttc2.setHeaderValue("Fecha ingreso");
                tth.repaint();
                Drive.CargarTablaTarHoy(jTable1, hoy, horaa);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error", "Ver hoy", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jFormattedTextField1FocusLost

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        try {
            String ver=jComboBox1.getSelectedItem().toString();
            Date fecha=dateChooserCombo1.getSelectedDate().getTime();
            jTable1.getModel();
            int fila = jTable1.rowAtPoint(evt.getPoint());
            if(ver.equals("PERSONAL")){               
                if ((fila > -1)){
                    per=(Personal) jTable1.getValueAt(fila,0);
                    Drive.CargarTablaPerTarHoy(jTable2,per,fecha);
                }
            }else if(ver.equals("ACTIVIDADES")){
                if ((fila > -1)){
                    tar=(Tarea) jTable1.getValueAt(fila,0);
                    Drive.CargarTablaPerTarHoy(jTable2,tar,fecha);
                }
            }
//            String ver=jComboBox1.getSelectedItem().toString();
//            Date hoy=new Date();
//            SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
//            Date hora = formateador.parse(jFormattedTextField1.getText());
//            if(ver.equals("PERSONAL")){
//                JTableHeader th = jTable1.getTableHeader();
//                TableColumnModel tcm = th.getColumnModel();
//                TableColumn t = tcm.getColumn(0); //recordemos que las columnas inician a enumerarse desde cero
//                t.setHeaderValue("Personal");
//                TableColumn tc = tcm.getColumn(1); //recordemos que las columnas inician a enumerarse desde cero
//                tc.setHeaderValue("DNI");
//                TableColumn tc1 = tcm.getColumn(2);
//                tc1.setHeaderValue("Correo electrónico");
//                TableColumn tc2 = tcm.getColumn(3);
//                tc2.setHeaderValue("Fecha ingreso");
//                th.repaint();
//    //            jTable1.setEnabled(false);
//                Drive.CargarTablaPerHoy(jTable1, hoy, hora);
//                Drive.CargarTablaTarHoy(jTable2, hoy, hora);
//            }else if(ver.equals("ACTIVIDADES")){
//                JTableHeader th = jTable1.getTableHeader();
//                TableColumnModel tcm = th.getColumnModel();
//                TableColumn t = tcm.getColumn(0); //recordemos que las columnas inician a enumerarse desde cero
//                t.setHeaderValue("Nombre");
//                TableColumn tc = tcm.getColumn(1); //recordemos que las columnas inician a enumerarse desde cero
//                tc.setHeaderValue("Tipo");
//                TableColumn tc1 = tcm.getColumn(2);
//                tc1.setHeaderValue("Inicio");
//                TableColumn tc2 = tcm.getColumn(3);
//                tc2.setHeaderValue("Fin");
//                th.repaint();
//    //            jTable1.setEnabled(false);
//                Drive.CargarTablaTarHoy(jTable1, hoy, hora);
//                Drive.CargarTablaPerHoy(jTable2, hoy, hora);
//            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error", "Ver hoy", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            String ver = jComboBox1.getSelectedItem().toString();
            Date hoy = new Date();
            SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
            Date hora = formateador.parse(jFormattedTextField1.getText());
            if (ver.equals("PERSONAL")) {
                JTableHeader th = jTable1.getTableHeader();
                TableColumnModel tcm = th.getColumnModel();
                TableColumn t = tcm.getColumn(0); //recordemos que las columnas inician a enumerarse desde cero
                t.setHeaderValue("Personal");
                TableColumn tc = tcm.getColumn(1); //recordemos que las columnas inician a enumerarse desde cero
                tc.setHeaderValue("DNI");
                TableColumn tc1 = tcm.getColumn(2);
                tc1.setHeaderValue("Correo electrónico");
                TableColumn tc2 = tcm.getColumn(3);
                tc2.setHeaderValue("Fecha ingreso");
                th.repaint();

                JTableHeader thh = jTable2.getTableHeader();
                TableColumnModel tcmm = thh.getColumnModel();
                TableColumn tt = tcmm.getColumn(0); //recordemos que las columnas inician a enumerarse desde cero
                tt.setHeaderValue("Nombre");
                TableColumn ttc = tcmm.getColumn(1); //recordemos que las columnas inician a enumerarse desde cero
                ttc.setHeaderValue("Tipo");
                TableColumn ttc1 = tcmm.getColumn(2);
                ttc1.setHeaderValue("Inicio");
                TableColumn ttc2 = tcmm.getColumn(3);
                ttc2.setHeaderValue("Fin");
                thh.repaint();
                //            jTable1.setEnabled(false);
                Drive.CargarTablaPerHoy(jTable1, hoy, hora);
//                Drive.CargarTablaTarHoy(jTable2, hoy, hora);
            } else if (ver.equals("ACTIVIDADES")) {
                JTableHeader th = jTable1.getTableHeader();
                TableColumnModel tcm = th.getColumnModel();
                TableColumn t = tcm.getColumn(0); //recordemos que las columnas inician a enumerarse desde cero
                t.setHeaderValue("Nombre");
                TableColumn tc = tcm.getColumn(1); //recordemos que las columnas inician a enumerarse desde cero
                tc.setHeaderValue("Tipo");
                TableColumn tc1 = tcm.getColumn(2);
                tc1.setHeaderValue("Inicio");
                TableColumn tc2 = tcm.getColumn(3);
                tc2.setHeaderValue("Fin");
                th.repaint();

                JTableHeader tth = jTable2.getTableHeader();
                TableColumnModel ttcm = tth.getColumnModel();
                TableColumn tt = ttcm.getColumn(0); //recordemos que las columnas inician a enumerarse desde cero
                tt.setHeaderValue("Personal");
                TableColumn ttc = ttcm.getColumn(1); //recordemos que las columnas inician a enumerarse desde cero
                ttc.setHeaderValue("DNI");
                TableColumn ttc1 = ttcm.getColumn(2);
                ttc1.setHeaderValue("Correo electrónico");
                TableColumn ttc2 = ttcm.getColumn(3);
                ttc2.setHeaderValue("Fecha ingreso");
                tth.repaint();
                //            jTable1.setEnabled(false);
                Drive.CargarTablaTarHoy(jTable1, hoy, hora);
//                Drive.CargarTablaPerHoy(jTable2, hoy, hora);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error", "Ver hoy", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Frame vp = new JFramePrincipal(Drive, adm);
        this.dispose();
        vp.show();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Frame vp = new JFramePrincipal(Drive, adm);
        this.dispose();
        vp.show();
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
//            java.util.logging.Logger.getLogger(JFrameVer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(JFrameVer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(JFrameVer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(JFrameVer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new JFrameVer().setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
