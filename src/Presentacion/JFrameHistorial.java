/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Clases.Ano;
import Clases.Controlador;
import Clases.Dia;
import Clases.Mes;
import Clases.Personal;
import Clases.Registroacceso;
import Clases.Tarea;
import java.awt.Component;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Fernando
 */


public class JFrameHistorial extends javax.swing.JFrame {

    /**
     * Creates new form JFrameHistorial
     */
    public Controlador Drive;
    public Personal adm;
//    int idsesion;
    Personal per = new Personal();
    Tarea tar = new Tarea();
    
    public JFrameHistorial(Controlador unDrive,Personal admin) {
        this.Drive=unDrive;
        this.adm=admin;
//        this.idsesion=id;
        initComponents();
        Drive.LimpiarTabla(jTable1);
        Drive.LimpiarTabla(jTable2);
        Date fecha=dateChooserCombo1.getSelectedDate().getTime();
        String buscar = (String) jComboBox1.getSelectedItem();
        if(jTabbedPane1.getSelectedIndex()==0){
            String filtro = (String) jComboBox2.getSelectedItem();
            Drive.CargarTablaHistorialPer(jTable1,fecha, buscar,filtro);
        }else{
            String filtrotar = (String) jComboBox3.getSelectedItem();
            Drive.CargarTablaHistorialTar(fecha, buscar,filtrotar,jTable2);
        }
//        int u = jTabbedPane1.getTabRunCount();
        if(buscar.equals("DIA")){
            jLabel4.setText(String.valueOf(fecha.getDate()));
        }else if(buscar.equals("MES")){
            jLabel4.setText(String.valueOf(fecha.getMonth()+1));
        }else if(buscar.equals("AÑO")){
            jLabel4.setText(String.valueOf(fecha.getYear()+1900));
        }
        ImageIcon fott5 = new ImageIcon(getClass().getResource("/imagenes/Buscar.png"));
        Icon icono5 = new ImageIcon(fott5.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        jButton4.setIcon(icono5);
        ImageIcon fott6 = new ImageIcon(getClass().getResource("/imagenes/Imprimir2.png"));
        Icon icono6 = new ImageIcon(fott6.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
//        jButton2.setIcon(icono6);
//        jButton3.setIcon(icono6);
        ImageIcon fott2 = new ImageIcon(getClass().getResource("/imagenes/no.png"));
        Icon icono2 = new ImageIcon(fott2.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        jButton1.setIcon(icono2);
        ImageIcon fott4 = new ImageIcon(getClass().getResource("/imagenes/Consulta.png"));
        Icon icono4 = new ImageIcon(fott4.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        jButton5.setIcon(icono4);
        
        int[] anchos1 = {150, 60, 60, 150, 60, 100};
        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos1[i]);
        }
        int[] anchos2 = {120, 120, 80, 80, 60, 100};
        for (int i = 0; i < jTable2.getColumnCount(); i++) {
            jTable2.getColumnModel().getColumn(i).setPreferredWidth(anchos2[i]);
        }
        int[] anchos3 = {150, 150, 120, 120};
        for (int i = 0; i < jTable3.getColumnCount(); i++) {
            jTable3.getColumnModel().getColumn(i).setPreferredWidth(anchos3[i]);
        }
        int[] anchos4 = {150, 80, 80, 250};
        for (int i = 0; i < jTable4.getColumnCount(); i++) {
            jTable4.getColumnModel().getColumn(i).setPreferredWidth(anchos4[i]);
        }
        jTable1.getTableHeader().setDefaultRenderer(new JFrameHistorial.HeaderRenderer(jTable1));
        jTable2.getTableHeader().setDefaultRenderer(new JFrameHistorial.HeaderRenderer(jTable2));
        jTable3.getTableHeader().setDefaultRenderer(new JFrameHistorial.HeaderRenderer(jTable3));
        jTable4.getTableHeader().setDefaultRenderer(new JFrameHistorial.HeaderRenderer(jTable4));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jComboBox3 = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jButton4 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SISTEMA DE ASISTENCIA DE PERSONAL EDUCATIVO");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Apellido y Nombre", "DNI", "Ingreso", "Correo Electrónico", "Operación", "Fecha Operación"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable1MouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel3.setText("Ver:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos", "Activos", "Inactivos" }));
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });

        jTable3.setAutoCreateRowSorter(true);
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Tipo", "Inicio", "Fin"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable3MouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Personal", jPanel1);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jTable2.setAutoCreateRowSorter(true);
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Lugar", "Inicio", "Fin", "Operación", "Fecha operación"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable2MouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jTable4.setAutoCreateRowSorter(true);
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Apellido y Nombre", "DNI", "Ingreso", "Correo Electrónico"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable4MouseReleased(evt);
            }
        });
        jScrollPane4.setViewportView(jTable4);

        jButton5.setText("Ver Asistencias");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos", "Activos", "Inactivos" }));
        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox3ItemStateChanged(evt);
            }
        });

        jLabel5.setText("Ver:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton5)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Actividades", jPanel3);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Seleccione una fecha"));

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
    dateChooserCombo1.setBehavior(datechooser.model.multiple.MultyModelBehavior.SELECT_SINGLE);
    dateChooserCombo1.addSelectionChangedListener(new datechooser.events.SelectionChangedListener() {
        public void onSelectionChange(datechooser.events.SelectionChangedEvent evt) {
            dateChooserCombo1OnSelectionChange(evt);
        }
    });

    jLabel2.setText("Buscar por:");

    jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AÑO", "MES", "DIA" }));
    jComboBox1.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            jComboBox1ItemStateChanged(evt);
        }
    });

    jButton4.setText("Buscar");
    jButton4.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton4ActionPerformed(evt);
        }
    });

    jLabel4.setText("AÑO");

    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
    jPanel5.setLayout(jPanel5Layout);
    jPanel5Layout.setHorizontalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel5Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jLabel2)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabel4)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton4))
    );
    jPanel5Layout.setVerticalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel5Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel1)
                .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel2)
                .addComponent(jButton4)
                .addComponent(jLabel4))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

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
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTabbedPane1)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jButton1)))
            .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton1)
            .addGap(36, 36, 36))
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
    
    private void dateChooserCombo1OnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_dateChooserCombo1OnSelectionChange
        try{
            Drive.LimpiarTabla(jTable1);
            Drive.LimpiarTabla(jTable2);
            Drive.LimpiarTabla(jTable3);
            Drive.LimpiarTabla(jTable4);
            Date fecha=dateChooserCombo1.getSelectedDate().getTime();
            String buscar = (String) jComboBox1.getSelectedItem();
            if (jTabbedPane1.getSelectedIndex() == 0) {
                String filtro = (String) jComboBox2.getSelectedItem();
                Drive.CargarTablaHistorialPer(jTable1, fecha, buscar, filtro);
            } else {
                String filtrotar = (String) jComboBox3.getSelectedItem();
                Drive.CargarTablaHistorialTar(fecha, buscar, filtrotar, jTable2);
            }
            if(buscar.equals("DIA")){
            jLabel4.setText(String.valueOf(fecha.getDate()));
            }else if(buscar.equals("MES")){
                jLabel4.setText(String.valueOf(fecha.getMonth()+1));
            }else if(buscar.equals("AÑO")){
                jLabel4.setText(String.valueOf(fecha.getYear()+1900));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_dateChooserCombo1OnSelectionChange

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try{
            Drive.LimpiarTabla(jTable1);
            Drive.LimpiarTabla(jTable2);
            Drive.LimpiarTabla(jTable3);
            Drive.LimpiarTabla(jTable4);
            Date fecha=dateChooserCombo1.getSelectedDate().getTime();
            String buscar = (String) jComboBox1.getSelectedItem();
            if (jTabbedPane1.getSelectedIndex() == 0) {
                String filtro = (String) jComboBox2.getSelectedItem();
                Drive.CargarTablaHistorialPer(jTable1, fecha, buscar, filtro);
            } else {
                String filtrotar = (String) jComboBox3.getSelectedItem();
                Drive.CargarTablaHistorialTar(fecha, buscar, filtrotar, jTable2);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        try{
            
            Drive.LimpiarTabla(jTable1);
            Drive.LimpiarTabla(jTable2);
            Drive.LimpiarTabla(jTable3);
            Drive.LimpiarTabla(jTable4);
            Date fecha=dateChooserCombo1.getSelectedDate().getTime();
            String buscar = (String) jComboBox1.getSelectedItem();
            if (jTabbedPane1.getSelectedIndex() == 0) {
                String filtro = (String) jComboBox2.getSelectedItem();
                Drive.CargarTablaHistorialPer(jTable1, fecha, buscar, filtro);
            } else {
                String filtrotar = (String) jComboBox3.getSelectedItem();
                Drive.CargarTablaHistorialTar(fecha, buscar, filtrotar, jTable2);
            }
            if(buscar.equals("DIA")){
            jLabel4.setText(String.valueOf(fecha.getDate()));
            }else if(buscar.equals("MES")){
                jLabel4.setText(String.valueOf(fecha.getMonth()+1));
            }else if(buscar.equals("AÑO")){
                jLabel4.setText(String.valueOf(fecha.getYear()+1900));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        try{
            Drive.LimpiarTabla(jTable1);
            Drive.LimpiarTabla(jTable3);
            Date fecha=dateChooserCombo1.getSelectedDate().getTime();
            String buscar = (String) jComboBox1.getSelectedItem();
            String filtro = (String) jComboBox2.getSelectedItem();
            Drive.CargarTablaHistorialPer(jTable1,fecha, buscar,filtro);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseReleased
        if (evt.getClickCount() == 2) {
            jTable1.getModel();
            jTable1.setEnabled(false);
            jTable2.setEnabled(false);
            jTable3.setEnabled(false);
            jTable4.setEnabled(false);
            per = (Personal) jTable1.getValueAt(jTable1.rowAtPoint(evt.getPoint()), 0);
            JFrameActualizarPersonal vent2 = new JFrameActualizarPersonal(Drive, per, adm, true);
            this.hide();
            vent2.show();
        }
    }//GEN-LAST:event_jTable1MouseReleased

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        JFramePrincipal vent2 = new JFramePrincipal(Drive,adm);
        this.hide();
        vent2.show();
    }//GEN-LAST:event_formWindowClosing

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFramePrincipal vent2 = new JFramePrincipal(Drive,adm);
        this.hide();
        vent2.show();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseReleased
        if (evt.getClickCount() == 2) {
            jTable4.getModel();
            per = (Personal) jTable4.getValueAt(jTable4.rowAtPoint(evt.getPoint()), 0);
            JFrameActualizarPersonal vent2 = new JFrameActualizarPersonal(Drive, per, adm, true);
            this.hide();
            vent2.show();
        }
    }//GEN-LAST:event_jTable4MouseReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        jTable1.getModel();
        int fila = jTable1.rowAtPoint(evt.getPoint());
        if ((fila > -1)){
            per=(Personal) jTable1.getValueAt(fila,0);
            Drive.CargarTablaPerTar(jTable3,per);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        jTable2.getModel();
        int fila = jTable2.rowAtPoint(evt.getPoint());
        if ((fila > -1)){
            tar=(Tarea) jTable2.getValueAt(fila,0);
            Drive.CargarTablaPerTar(jTable4,tar);
        }else{
            tar = new Tarea();
        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseReleased
        if (evt.getClickCount() == 2) {
            jTable2.getModel();
            tar = (Tarea) jTable2.getValueAt(jTable2.rowAtPoint(evt.getPoint()), 0);
            JFrameActualizarActividades vent2 = new JFrameActualizarActividades(Drive, tar, adm, true);
            this.hide();
            vent2.show();
        }
    }//GEN-LAST:event_jTable2MouseReleased

    private void jTable3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseReleased
        if (evt.getClickCount() == 2) {
            jTable3.getModel();
            Tarea tare = (Tarea) jTable3.getValueAt(jTable3.rowAtPoint(evt.getPoint()), 0);
            JFrameActualizarActividades vent2 = new JFrameActualizarActividades(Drive, tare, adm, true);
            this.hide();
            vent2.show();
        }
    }//GEN-LAST:event_jTable3MouseReleased

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if(tar.getIdTarea()!=null){
//            if(!tar.getComentario().equals("CLASE")){
                jTable3.getModel();
                JFrameAsistenciaTarea vent2 = new JFrameAsistenciaTarea(Drive, tar, adm);
                this.hide();
                vent2.show();
//            }else{
//                JOptionPane.showMessageDialog(null, "La vista de asistencia no es para clases", "Ver asistencia", JOptionPane.ERROR_MESSAGE);
//            }
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione una tarea por favor", "Ver asistencia", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged
        try{
            Drive.LimpiarTabla(jTable2);
            Drive.LimpiarTabla(jTable4);
            Date fecha=dateChooserCombo1.getSelectedDate().getTime();
            String buscar = (String) jComboBox1.getSelectedItem();
            String filtrotar = (String) jComboBox3.getSelectedItem();
            Drive.CargarTablaHistorialTar(fecha, buscar,filtrotar,jTable2);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jComboBox3ItemStateChanged

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        try{
            Drive.LimpiarTabla(jTable1);
            Drive.LimpiarTabla(jTable2);
            Drive.LimpiarTabla(jTable3);
            Drive.LimpiarTabla(jTable4);
            Date fecha=dateChooserCombo1.getSelectedDate().getTime();
            String buscar = (String) jComboBox1.getSelectedItem();
            if (jTabbedPane1.getSelectedIndex() == 0) {
                String filtro = (String) jComboBox2.getSelectedItem();
                Drive.CargarTablaHistorialPer(jTable1, fecha, buscar, filtro);
            } else {
                String filtrotar = (String) jComboBox3.getSelectedItem();
                Drive.CargarTablaHistorialTar(fecha, buscar, filtrotar, jTable2);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jTabbedPane1MouseClicked

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
//            java.util.logging.Logger.getLogger(JFrameHistorial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(JFrameHistorial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(JFrameHistorial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(JFrameHistorial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new JFrameHistorial().setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    // End of variables declaration//GEN-END:variables
}
