/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Clases.Auditoria;
import Clases.Controlador;
import Clases.Personal;
import java.awt.Component;
import java.awt.Frame;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Fernando
 */
public class JFrameAuditoria extends javax.swing.JFrame {

    /**
     * Creates new form JFrameAuditoria
     */
    public Controlador Drive;
    public Personal adm;
//    int idsesion;
    Personal per = new Personal();
    StringBuffer buffer = new StringBuffer();
    
    public JFrameAuditoria(Controlador unDrive,Personal admin) {
        this.adm=admin;
        this.Drive = unDrive;
//        this.idsesion=id;
        initComponents();
        Date dia= dateChooserCombo1.getSelectedDate().getTime();
        String buscar = (String) jComboBox2.getSelectedItem();
        String filtro = (String) jComboBox1.getSelectedItem();
        Drive.CargarTablaAuditoria(jTable1, dia, buscar,filtro);
        if(buscar.equals("DIA")){
        jLabel4.setText(String.valueOf(dia.getDate()));
        }else if(buscar.equals("SEMANA")){
            jLabel4.setText(String.valueOf(dia.getDate()));
        }else if(buscar.equals("MES")){
            jLabel4.setText(String.valueOf(dia.getMonth()+1));
        }else if(buscar.equals("AÑO")){
            jLabel4.setText(String.valueOf(dia.getYear()+1900));
        }
        ImageIcon fott1 = new ImageIcon(getClass().getResource("/imagenes/no.png"));
        Icon icono1 = new ImageIcon(fott1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        jButton3.setIcon(icono1);
        ImageIcon fott2 = new ImageIcon(getClass().getResource("/imagenes/Imprimir2.png"));
        Icon icono2 = new ImageIcon(fott2.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        jButton5.setIcon(icono2);
        int[] anchos1 = {200, 70, 120, 170,120,200,200};
        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos1[i]);
        }
        jTable1.getTableHeader().setDefaultRenderer(new JFrameAuditoria.HeaderRenderer(jTable1));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        jLabel1 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SISTEMA DE ASISTENCIA DE PERSONAL EDUCATIVO");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Auditoria"));

        jLabel2.setText("Seleccione un dia:");

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

    jLabel1.setText("Buscar por:");

    jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DIA", "SEMANA", "MES", "AÑO" }));
    jComboBox2.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            jComboBox2ItemStateChanged(evt);
        }
    });

    jTable1.setAutoCreateRowSorter(true);
    jTable1.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Elemento", "Acción", "Fecha", "Personal", "Campo", "Elemento anterior", "Elemento posterior"
        }
    ) {
        boolean[] canEdit = new boolean [] {
            false, false, false, false, false, false, false
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
    jTable1.setRowHeight(20);
    jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseReleased(java.awt.event.MouseEvent evt) {
            jTable1MouseReleased(evt);
        }
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            jTable1MouseEntered(evt);
        }
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jTable1MouseClicked(evt);
        }
    });
    jScrollPane1.setViewportView(jTable1);

    jButton5.setText("Imprimir");
    jButton5.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton5ActionPerformed(evt);
        }
    });

    jButton3.setText("Salir");
    jButton3.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton3ActionPerformed(evt);
        }
    });

    jLabel3.setText("Filtrar por:");

    jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TODOS", "PERSONAL", "TAREA", "DEPARTAMENTO", "COLEGIO" }));
    jComboBox1.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            jComboBox1ItemStateChanged(evt);
        }
    });

    jLabel4.setText("Dia");

    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel3Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jButton5)
                    .addGap(18, 18, 18)
                    .addComponent(jButton3))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(51, 51, 51)
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel4)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addContainerGap())
    );
    jPanel3Layout.setVerticalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(jLabel2)
                .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel1)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel4))
            .addGap(18, 18, 18)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel3)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(19, 19, 19)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5)))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(17, Short.MAX_VALUE))
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
        String filtro = (String) jComboBox1.getSelectedItem();
        Drive.CargarTablaAuditoria(jTable1, dia, buscar,filtro);
        if(buscar.equals("DIA")){
        jLabel4.setText(String.valueOf(dia.getDate()));
        }else if(buscar.equals("SEMANA")){
            jLabel4.setText(String.valueOf(dia.getDate()));
        }else if(buscar.equals("MES")){
            jLabel4.setText(String.valueOf(dia.getMonth()+1));
        }else if(buscar.equals("AÑO")){
            jLabel4.setText(String.valueOf(dia.getYear()+1900));
        }
    }//GEN-LAST:event_dateChooserCombo1OnSelectionChange

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        Drive.LimpiarTabla(jTable1);
        Date dia= dateChooserCombo1.getSelectedDate().getTime();
        String buscar = (String) jComboBox2.getSelectedItem();
        String filtro = (String) jComboBox1.getSelectedItem();
        Drive.CargarTablaAuditoria(jTable1, dia, buscar,filtro);
        if(buscar.equals("DIA")){
        jLabel4.setText(String.valueOf(dia.getDate()));
        }else if(buscar.equals("SEMANA")){
            jLabel4.setText(String.valueOf(dia.getDate()));
        }else if(buscar.equals("MES")){
            jLabel4.setText(String.valueOf(dia.getMonth()+1));
        }else if(buscar.equals("AÑO")){
            jLabel4.setText(String.valueOf(dia.getYear()+1900));
        }
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int confirmado = JOptionPane.showConfirmDialog(null, "¿Desea volver al menú principal?", "Consultar Auditoria", JOptionPane.YES_NO_OPTION);
        if (JOptionPane.OK_OPTION == confirmado) {
            Frame vp = new JFramePrincipal(Drive,adm);
            this.dispose();
            vp.show();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            String filtro2 = (String) jComboBox1.getSelectedItem();
            String filtro1 = (String) jComboBox2.getSelectedItem();
            Date fin= dateChooserCombo1.getSelectedDate().getTime();
            List lista = new ArrayList();
            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            int c = 0;
            while (modelo.getRowCount() != c) {
                Object o=jTable1.getValueAt(c, 3);
                if (o != null) {
                    Auditoria audi = (Auditoria) o;
                    lista.add(audi);
                }
                c++;
            }
            if(filtro1.equals("DIA")){
            filtro1= filtro1+": "+fin.getDate();
            }else if(filtro1.equals("SEMANA")){
                filtro1= filtro1+" del dia: "+fin.getDate();
            }else if(filtro1.equals("MES")){
                filtro1= filtro1+": "+(fin.getMonth()+1);
            }else if(filtro1.equals("AÑO")){
                filtro1= filtro1+": "+(fin.getYear()+1900);
            }
            
            Drive.mostrarReporte("Auditoria",lista,"Registro de Auditoria",filtro1,filtro2,lista.size());
        } catch (Exception Ex) {
            JOptionPane.showMessageDialog(null, "Ingrese correctamente los datos", "Error de impresion", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int confirmado = JOptionPane.showConfirmDialog(null, "¿Desea volver al menú principal?", "Consultar Auditoria", JOptionPane.YES_NO_OPTION);
        if (JOptionPane.OK_OPTION == confirmado) {
            Frame vp = new JFramePrincipal(Drive,adm);
            this.dispose();
            vp.show();
        }
    }//GEN-LAST:event_formWindowClosing

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        Drive.LimpiarTabla(jTable1);
        Date dia= dateChooserCombo1.getSelectedDate().getTime();
        String buscar = (String) jComboBox2.getSelectedItem();
        String filtro = (String) jComboBox1.getSelectedItem();
        Drive.CargarTablaAuditoria(jTable1, dia, buscar,filtro);
    }//GEN-LAST:event_jComboBox1ItemStateChanged

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
//            java.util.logging.Logger.getLogger(JFrameAuditoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(JFrameAuditoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(JFrameAuditoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(JFrameAuditoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new JFrameAuditoria().setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
