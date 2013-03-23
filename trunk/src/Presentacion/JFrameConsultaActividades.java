/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Clases.Controlador;
import Clases.Establecimiento;
import Clases.Personal;
import Clases.Tarea;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author fer
 */
public class JFrameConsultaActividades extends javax.swing.JFrame {

    /**
     * Creates new form JFrameConsultaActividades
     */
    public Controlador Drive;
    public Personal adm;
    Personal per= new Personal();
    StringBuffer buffer= new StringBuffer();
    HiloProgreso hilo1;
    Tarea tar=new Tarea();
    String bandera;
    int idsesion;
    
    public JFrameConsultaActividades(Controlador unDrive, Personal admin,int id) {
        this.Drive=unDrive;
        this.adm=admin;
        this.idsesion=id;
        initComponents();
        Controlador auxDrive = new Controlador();
        auxDrive.getPrimerEstablecimiento();
        Drive = auxDrive;
        String buscar=(String) jComboBox1.getSelectedItem();
        String buscar2=(String) jComboBox2.getSelectedItem();
        Drive.CargarTablaFiltroActividades(jTable1, buscar, buscar2, jTextField1.getText());
        jTextField1.grabFocus();
        bandera=(String) jComboBox2.getSelectedItem();
        int[] anchos1 = {150,150,150};
        for(int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos1[i]);
        }
        jTable1.getTableHeader().setDefaultRenderer(new JFrameConsultaActividades.HeaderRenderer(jTable1));      
        
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SISTEMA DE ASISTENCIA DE PERSONAL EDUCATIVO");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Actividad"));

        jLabel1.setText("Buscar por:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nombre", "Lugar" }));

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos", "Clase", "Reunión", "Extracurricular", "Otro" }));
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Lugar", "Comentario"
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

        jButton4.setText("Aceptar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton3.setText("Salir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

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
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton2, jButton3, jButton4});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    /**
     * @return the jPanel3
     */
    public javax.swing.JProgressBar getjProgressBar1() {
        return jProgressBar1;
    }
    
    public final void iniciarSplash() {
    this.getjProgressBar1().setBorderPainted(true);
    this.getjProgressBar1().setForeground(new Color(50, 50, 153, 100));
    this.getjProgressBar1().setStringPainted(true);
}
    
    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        Drive.LimpiarTabla(jTable1);
        char car=evt.getKeyChar();
        int m= buffer.capacity();
        if((car>='a' && car<='z') || (car>='A' && car<='Z')){
            buffer.append(evt.getKeyChar());
            String es=buffer.toString();
            String buscar=(String) jComboBox1.getSelectedItem();
            String buscar2=(String) jComboBox2.getSelectedItem();
            Drive.CargarTablaFiltroActividades(jTable1,buscar, buscar2, es.toUpperCase());
        }else if(car==(char)KeyEvent.VK_BACK_SPACE){
            buffer.deleteCharAt(buffer.length()-1);
            String es=buffer.toString();
            String buscar=(String) jComboBox1.getSelectedItem();
            String buscar2=(String) jComboBox2.getSelectedItem();
            Drive.CargarTablaFiltroActividades(jTable1,buscar, buscar2, es.toUpperCase());
        }
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Drive.LimpiarTabla(jTable1);
        String buscar=(String) jComboBox1.getSelectedItem();
        String buscar2=(String) jComboBox2.getSelectedItem();
        Drive.CargarTablaFiltroActividades(jTable1, buscar, buscar2, jTextField1.getText().toUpperCase());// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseEntered

    private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseReleased
        if(evt.getClickCount()==2){
            jTable1.getModel();
            tar=(Tarea)jTable1.getValueAt(jTable1.rowAtPoint(evt.getPoint()), 0);
            //int fila = jTable1.rowAtPoint(evt.getPoint());
//            if ((fila > -1)){
//                tar=col.getTarea(Integer.parseInt(jTable1.getValueAt(fila,0).toString()));
//            }
            if(adm.getPerfil().getNivel()<=2){
                iniciarSplash();
    //            //Creamos un objeto HiloProgreso al cual
    //            //le pasamos por parámetro la barra de progreso
                hilo1=new HiloProgreso(jProgressBar1,this,Drive,tar,adm,idsesion);
                hilo1.start();
                hilo1=null;
            }
        }                 // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try{
            Establecimiento col= Drive.getPrimerEstablecimiento();
            jTable1.getModel();
            jTable1.getModel();
            tar=(Tarea)jTable1.getValueAt(jTable1.getSelectedRow(), 0);
//            int fila = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
//            if ((fila > -1)){
//                tar=col.getTarea(fila);
//            }
            if(adm.getPerfil().getNivel()<=2){
                iniciarSplash();
    //            //Creamos un objeto HiloProgreso al cual
    //            //le pasamos por parámetro la barra de progreso
                hilo1=new HiloProgreso(jProgressBar1,this,Drive,tar,adm,idsesion);
                hilo1.start();
                hilo1=null;
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Seleccione una actividad","Consultar Actividad",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int confirmado = JOptionPane.showConfirmDialog(null,"¿Desea volver al menú principal?","Consultar Actividad",JOptionPane.YES_NO_OPTION);
        if (JOptionPane.OK_OPTION == confirmado){
            Frame vp=new JFramePrincipal(Drive,adm,idsesion); 
            this.dispose();
            vp.show();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try{
//            JOptionPane.showMessageDialog(null,"No se puede realizar el reporte","Error de impresion", JOptionPane.ERROR_MESSAGE);
            bandera=(String) jComboBox2.getSelectedItem();
            if(bandera.equals("Clase")){
                List consulta=Controlador.getPERSISTENCIA().getTareasClasesTrue(1);
//                List consulta=Controlador.getPERSISTENCIA().getTareas();
                Drive.mostrarReporte("Clases",consulta,"Lista de Clases");
            }else if(bandera.equals("Reunión")){
                List consulta=Controlador.getPERSISTENCIA().getTareasReunionTrue(1);
                Drive.mostrarReporte("Reuniones",consulta,"Lista de Reuniones");
            }else if(bandera.equals("Extracurricular")){
                List consulta=Controlador.getPERSISTENCIA().getTareasExtracurricularTrue(1);
                Drive.mostrarReporte("Tareas Extracurriculares",consulta,"Lista de Tareas Extracurriculares");
            }else if(bandera.equals("Otro")){
                List consulta=Controlador.getPERSISTENCIA().getTareasOtroTrue(1);
                Drive.mostrarReporte("Otras Actividades",consulta,"Lista de Otras Actividades");
            }else if(bandera.equals("Todos")){
                JOptionPane.showMessageDialog(null,"No se pueden imprimir todas las actividades, por favor seleccione una actividad en especial","Error de impresión", JOptionPane.ERROR_MESSAGE);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Ingrese correctamente los datos","Error de impresion", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        String es=buffer.toString();
        String buscar=(String) jComboBox1.getSelectedItem();
        String buscar2=(String) jComboBox2.getSelectedItem();
        Drive.LimpiarTabla(jTable1);
        Drive.CargarTablaFiltroActividades(jTable1,buscar, buscar2, es.toUpperCase());
        bandera=(String) jComboBox2.getSelectedItem();
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Frame vp=new JFramePrincipal(Drive,adm,idsesion); 
        this.dispose();
        vp.show();        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

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
//            java.util.logging.Logger.getLogger(JFrameConsultaActividades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(JFrameConsultaActividades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(JFrameConsultaActividades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(JFrameConsultaActividades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new JFrameConsultaActividades().setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
