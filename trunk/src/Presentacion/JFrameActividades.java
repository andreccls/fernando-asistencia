/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JFrameActividades.java
 *
 * Created on 27/06/2012, 14:27:24
 */
package Presentacion;

import Clases.Agenda;
import Clases.AgendaId;
import java.util.Iterator;
import java.util.List;
import Clases.Personal;
import Clases.Tarea;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.util.SortedSet;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import Clases.Controlador;
import Clases.Dia;
import Clases.Establecimiento;
import Clases.Iniciofin;
import Clases.Registroacceso;
import Persistencia.persistencia;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author fer
 */
public class JFrameActividades extends javax.swing.JFrame {

    public Controlador Drive=new Controlador();
    public Personal per=new Personal();
    Frame vp=new JFrameAsistencia();
    
    /**
     * Creates new form JFrameActividades
     */
    public JFrameActividades(Controlador unDrive,Personal pe) {
        this.Drive=unDrive;
        this.per=pe;
        initComponents();
        Calendar dia=dateChooserPanel1.getSelectedDate();
        Date d=dateChooserCombo1.getSelectedDate().getTime();
        String buscar = (String) jComboBox2.getSelectedItem();
        Drive.CargarGrillaActividades(jTable2, per,dia.getTime());
        Drive.CargarTablaRegistro(jTable1, per, d, buscar);
        jLabel3.setText(String.valueOf(Drive.ObtenerInasistenciaPersonal(per)));
        jLabel4.setText(String.valueOf(Drive.ObtenerTardanzaPersonal(per)));
        DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
        modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
        jTable1.getColumnModel().getColumn(0).setCellRenderer(modelocentrar); 
        jTable1.getColumnModel().getColumn(1).setCellRenderer(modelocentrar); 
        jTable1.getColumnModel().getColumn(2).setCellRenderer(modelocentrar); 
        jTable2.getColumnModel().getColumn(1).setCellRenderer(modelocentrar); 
        jTable2.getColumnModel().getColumn(2).setCellRenderer(modelocentrar); 
        jTable2.getTableHeader().setDefaultRenderer(new HeaderRenderer(jTable2));
        jTable1.getTableHeader().setDefaultRenderer(new HeaderRenderer(jTable1));
        
        ImageIcon fott1 = new ImageIcon("src\\imagenes\\no.png");
        Icon icono1 = new ImageIcon(fott1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        jButton1.setIcon(icono1);
        ImageIcon fott2 = new ImageIcon("src\\imagenes\\Imprimir2.png");
        Icon icono2 = new ImageIcon(fott2.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        jButton2.setIcon(icono2);
        jButton5.setIcon(icono2);
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
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        dateChooserPanel1 = new datechooser.beans.DateChooserPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        jLabel6 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jButton5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SISTEMA DE ASISTENCIA DE PERSONAL EDUCATIVO");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTabbedPane2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        dateChooserPanel1.setCurrentView(new datechooser.view.appearance.AppearancesList("Swing",
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
    dateChooserPanel1.addSelectionChangedListener(new datechooser.events.SelectionChangedListener() {
        public void onSelectionChange(datechooser.events.SelectionChangedEvent evt) {
            dateChooserPanel1OnSelectionChange(evt);
        }
    });

    jTable2.setAutoCreateRowSorter(true);
    jTable2.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Actividad", "Hora Inicio", "Hora Fin"
        }
    ) {
        boolean[] canEdit = new boolean [] {
            false, false, false
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    jTable2.setEnabled(false);
    jScrollPane2.setViewportView(jTable2);

    jLabel1.setText("Inasistencias:");

    jLabel2.setText("Tardanzas:");

    jLabel3.setText("jLabel3");

    jLabel4.setText("jLabel4");

    jButton2.setText("Imprimir");
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
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4)
                        .addComponent(jLabel3))
                    .addGap(137, 220, Short.MAX_VALUE)
                    .addComponent(jButton2))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dateChooserPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
            .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(dateChooserPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1)
                .addComponent(jLabel3))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel2)
                .addComponent(jLabel4)
                .addComponent(jButton2))
            .addContainerGap(16, Short.MAX_VALUE))
    );

    jTabbedPane2.addTab("Actividades", jPanel1);

    jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

    jTable1.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Fecha", "Ingreso", "Salida"
        }
    ) {
        boolean[] canEdit = new boolean [] {
            false, false, false
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jTable1MouseClicked(evt);
        }
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            jTable1MouseEntered(evt);
        }
        public void mouseReleased(java.awt.event.MouseEvent evt) {
            jTable1MouseReleased(evt);
        }
    });
    jScrollPane1.setViewportView(jTable1);

    jLabel5.setText("Seleccione un dia:");

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

    jLabel6.setText("Buscar por:");

    jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DIA", "SEMANA", "MES" }));
    jComboBox2.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            jComboBox2ItemStateChanged(evt);
        }
    });

    jButton5.setText("Imprimir");
    jButton5.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton5ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel3Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addComponent(jLabel5)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                    .addComponent(jLabel6)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jButton5)))
            .addContainerGap())
    );
    jPanel3Layout.setVerticalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel3Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jLabel5)
                .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
            .addComponent(jButton5)
            .addContainerGap())
    );

    jTabbedPane2.addTab("Registro de asistencias", jPanel3);

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
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton1)
            .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
            .addComponent(jButton1)
            .addContainerGap())
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
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
        vp.show();
}//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void dateChooserPanel1OnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_dateChooserPanel1OnSelectionChange
        Drive.LimpiarTabla(jTable2);
        Calendar dia=dateChooserPanel1.getSelectedDate();
        Drive.CargarGrillaActividades(jTable2, per,dia.getTime());
    }//GEN-LAST:event_dateChooserPanel1OnSelectionChange

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.dispose();
        vp.show();
    }//GEN-LAST:event_formWindowClosing

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            jButton2.setEnabled(false);
            jButton1.setEnabled(false);
            JComboBox salida = new JComboBox();
            String cadSalida;
            salida.addItem("DIA");
            salida.addItem("SEMANA");
            salida.addItem("MES");
            salida.setSize(25, 25);
            JOptionPane.showMessageDialog(null, salida, "¿Que desea imprimir?", JOptionPane.INFORMATION_MESSAGE);
            cadSalida = salida.getSelectedItem().toString();
            Date fin= dateChooserPanel1.getSelectedDate().getTime();
            List inifin= new ArrayList();
            if (cadSalida.equals("DIA")) {
                Iterator<Agenda> ag = per.getAgendas().iterator();
                while (ag.hasNext()) {
                    Agenda agen = ag.next();
                    Iterator<Iniciofin> it = agen.getDia2(fin).getIniciofins().iterator();
                    while (it.hasNext()) {
                        Iniciofin ini = (Iniciofin) it.next();
                        inifin.add(ini);
                    }
                }
                Drive.mostrarReporte("Actividades",inifin,"Registro de Actividades","Actividades");
            }else if (cadSalida.equals("SEMANA")) {
                int aa=fin.getDay();
                int a=0;
                Date aux=Drive.restarFechasDias(fin, aa);
                while(a!=6){
                    Iterator<Agenda> ag = per.getAgendas().iterator();
                    while (ag.hasNext()) {
                        Agenda agen = ag.next();
                        Iterator<Iniciofin> it = agen.getDia2(aux).getIniciofins().iterator();
                        while (it.hasNext()) {
                            Iniciofin ini = (Iniciofin) it.next();
                            inifin.add(ini);
                        }
                    }
                    a++;
                    aux=Drive.sumarFechasDias(aux, 1);
                }
                Drive.mostrarReporte("Actividades",inifin,"Registro de Actividades","Actividades");
            }else if (cadSalida.equals("MES")) {
                int aa=fin.getDate()-1;
                Date aux=Drive.restarFechasDias(fin, aa);
                while(aux.getMonth()== fin.getMonth()){
                    Iterator<Agenda> ag = per.getAgendas().iterator();
                    while (ag.hasNext()) {
                        Agenda agen = ag.next();
                        Iterator<Iniciofin> it = agen.getDia2(aux).getIniciofins().iterator();
                        while (it.hasNext()) {
                            Iniciofin ini = (Iniciofin) it.next();
                            inifin.add(ini);
                        }
                    }
                    aux=Drive.sumarFechasDias(aux, 1);
                }
                
                Drive.mostrarReporte("Actividades",inifin,"Registro de Actividades","Actividades");
            }
            jButton2.setEnabled(true);
            jButton1.setEnabled(true);
        } catch (Exception Ex) {
            JOptionPane.showMessageDialog(null, "Ingrese correctamente los datos", "Error de impresion", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseEntered

    private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseReleased

    }//GEN-LAST:event_jTable1MouseReleased

    private void dateChooserCombo1OnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_dateChooserCombo1OnSelectionChange
        Drive.LimpiarTabla(jTable1);
        Date dia= dateChooserCombo1.getSelectedDate().getTime();
        String buscar = (String) jComboBox2.getSelectedItem();
        Drive.CargarTablaRegistro(jTable1, per, dia, buscar);
    }//GEN-LAST:event_dateChooserCombo1OnSelectionChange

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        Drive.LimpiarTabla(jTable1);
        Date dia= dateChooserCombo1.getSelectedDate().getTime();
        String buscar = (String) jComboBox2.getSelectedItem();
        Drive.CargarTablaRegistro(jTable1, per, dia, buscar);
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            jButton5.setEnabled(false);
            jButton1.setEnabled(false);
            String combo=jComboBox2.getSelectedItem().toString();
            Date fin= dateChooserCombo1.getSelectedDate().getTime();
            List reg=new ArrayList();
            if (combo.equals("DIA")) {
                java.sql.Date sqlDate = new java.sql.Date(fin.getTime());
                Iterator it= Drive.PERSISTENCIA.getRegistroaccesoss(sqlDate,per.getIdPersonal()).iterator();
                while(it.hasNext()){
                    Registroacceso re=(Registroacceso) it.next();
                    if(re.getFecha().getDate()==fin.getDate()&&re.getFecha().getMonth()==fin.getMonth()&&re.getFecha().getYear()==fin.getYear()){
                        reg.add(re);
                    }
                }
                Drive.mostrarReporte("Registro",reg,"Registro de acceso");   
            }else if (combo.equals("SEMANA")) {
                Date aux=Drive.restarFechasDias(fin, 7);
                while(aux.compareTo(fin)<=0){
                    java.sql.Date sqlDate = new java.sql.Date(aux.getTime());
                    Iterator it= Drive.PERSISTENCIA.getRegistroaccesoss(sqlDate,per.getIdPersonal()).iterator();
                    while(it.hasNext()){
                        Registroacceso re=(Registroacceso) it.next();
                        if(re.getFecha().getDate()==aux.getDate()&&re.getFecha().getMonth()==aux.getMonth()&&re.getFecha().getYear()==aux.getYear()){
                            reg.add(re);
                        }
                    }
                    aux=Drive.sumarFechasDias(aux, 1);
                }
                Drive.mostrarReporte("Registro",reg,"Registro de acceso");
            }else if (combo.equals("MES")) {
                Date aux=fin;
                while(aux.compareTo(fin)<=0){
                    if(aux.getMonth()==fin.getMonth()){
                        java.sql.Date sqlDate = new java.sql.Date(aux.getTime());
                        Iterator it= Drive.PERSISTENCIA.getRegistroaccesoss(sqlDate,per.getIdPersonal()).iterator();
                        while(it.hasNext()){
                            Registroacceso re=(Registroacceso) it.next();
                            if(re.getFecha().getDate()==aux.getDate()&&re.getFecha().getMonth()==aux.getMonth()&&re.getFecha().getYear()==aux.getYear()){
                                reg.add(re);
                            }
                        }
                        aux=Drive.restarFechasDias(aux, 1);
                    }else{break;}
                }
                
                Drive.mostrarReporte("Registro",reg,"Registro de acceso");
            }
            jButton5.setEnabled(true);
            jButton1.setEnabled(true);
        } catch (Exception Ex) {
            JOptionPane.showMessageDialog(null, "Ingrese correctamente los datos", "Error de impresion", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private datechooser.beans.DateChooserPanel dateChooserPanel1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
