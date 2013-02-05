/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Clases.Agenda;
import Clases.AgendaId;
import Clases.Ano;
import Clases.Controlador;
import Clases.Dia;
import Clases.Establecimiento;
import Clases.Franco;
import Clases.Iniciofin;
import Clases.Mes;
import Clases.Personal;
import Clases.Revista;
import Clases.Tarea;
import Clases.TareaextracurricularId;
import Clases.TareareunionId;
import datechooser.beans.DateChooserCombo;
import java.awt.Component;
import java.awt.Frame;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author fer
 */
public class JFrameExtracurricular extends javax.swing.JFrame {

    /**
     * Creates new form JFrameExtracurricular
     */
    public Controlador Drive;
    Frame vp=new JFramePrincipal();
    public JFrameExtracurricular(Controlador unDrive) {
        this.Drive=unDrive;
        initComponents();
        Drive.CargarTablacheck(jTable1);
        int[] anchos1 = {85,200 ,65};
        for(int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos1[i]);
        }
        DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
        modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(modelocentrar); 
        jTable1.getTableHeader().setDefaultRenderer(new HeaderRenderer(jTable1));
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
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        jLabel3 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        dateChooserCombo2 = new datechooser.beans.DateChooserCombo();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de la tarea extracurricular"));

        jLabel2.setText("Nombre:");

        jLabel6.setText("Lugar:");

        jLabel7.setText("Dia inicio:");

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

    jLabel3.setText("Hora Inicio:");

    try {
        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
    } catch (java.text.ParseException ex) {
        ex.printStackTrace();
    }

    jLabel8.setText("hh:mm");

    jLabel4.setText("Profesor:");

    jTable1.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Seleccionar", "Apellido y nombre", "DNI"
        }
    ) {
        Class[] types = new Class [] {
            java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
        };
        boolean[] canEdit = new boolean [] {
            true, false, false
        };

        public Class getColumnClass(int columnIndex) {
            return types [columnIndex];
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
    jScrollPane1.setViewportView(jTable1);

    jLabel1.setText("Dia fin:");

    jButton2.setText("Cancelar");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton2ActionPerformed(evt);
        }
    });

    jButton1.setText("Aceptar");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton1ActionPerformed(evt);
        }
    });

    jLabel5.setText("Hora fin:");

    try {
        jFormattedTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
    } catch (java.text.ParseException ex) {
        ex.printStackTrace();
    }

    jLabel9.setText("hh:mm");

    jLabel10.setText("Obligatorio:");

    buttonGroup1.add(jRadioButton1);
    jRadioButton1.setSelected(true);
    jRadioButton1.setText("Si");

    buttonGroup1.add(jRadioButton2);
    jRadioButton2.setText("No");

    jLabel11.setText("Comentario:");

    jLabel14.setText("*");

    jLabel15.setText("*");

    jLabel16.setText("*");

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jButton1)
                    .addGap(18, 18, 18)
                    .addComponent(jButton2))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jLabel4)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addComponent(jLabel10)
                        .addComponent(jLabel7)
                        .addComponent(jLabel2))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextField3)
                        .addComponent(jRadioButton1)
                        .addComponent(jLabel8)
                        .addComponent(dateChooserCombo1, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel15))
                        .addComponent(jRadioButton2))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel14)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel11)
                        .addComponent(jLabel1)
                        .addComponent(jLabel6)
                        .addComponent(jLabel5))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel9)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel16))
                        .addComponent(dateChooserCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField4)
                        .addComponent(jTextField1))))
            .addContainerGap())
    );

    jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {dateChooserCombo1, dateChooserCombo2, jTextField1, jTextField3, jTextField4});

    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel14))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(dateChooserCombo1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel15))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel8)
                    .addGap(21, 21, 21)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jRadioButton1)
                        .addComponent(jLabel10))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jRadioButton2))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(dateChooserCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(5, 5, 5)
                            .addComponent(jLabel1)))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel16))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel9)))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGap(11, 11, 11)
            .addComponent(jLabel4)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButton2)
                .addComponent(jButton1))
            .addContainerGap())
    );

    jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jTextField3, jTextField4});

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
        if(!jTextField3.getText().isEmpty()||!jTextField4.getText().isEmpty()||!jTextField1.getText().isEmpty()||!jFormattedTextField1.getText().isEmpty()||!jFormattedTextField2.getText().isEmpty()){
            int confirmado = JOptionPane.showConfirmDialog(null,"¿Desea cancelar la tarea extracurricular?","",JOptionPane.YES_NO_OPTION);
            if (JOptionPane.OK_OPTION == confirmado){
                this.dispose();
                vp.show();
            }
        }else{this.dispose();
            vp.show();}
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            if(Drive.VerificarCheckTabla(jTable1)){
            if(!jTextField3.getText().isEmpty()&&!jFormattedTextField1.getText().contains(" ") &&!jFormattedTextField2.getText().contains(" ")){
                SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                formateador.setLenient(false);
                Date inicio=formateador.parse(jFormattedTextField1.getText());
                Date fin=formateador.parse(jFormattedTextField2.getText());
                if(inicio.compareTo(fin) < 0){
                    Establecimiento col= Drive.getPrimerEstablecimiento();
                    Date fecha_inicio = dateChooserCombo1.getSelectedDate().getTime();
                    Date fecha_fin = dateChooserCombo2.getSelectedDate().getTime();
                    Tarea tar=col.crearTarea(col, jTextField3.getText().toUpperCase(), jTextField4.getText().toUpperCase(), jTextField1.getText().toUpperCase(), true, null, null, null, null, null);
                    TareaextracurricularId id=new TareaextracurricularId();
                    id.setIdTarea(tar.getIdTarea());
                    tar.crearTareaextracurricular(id, tar,fecha_inicio,fecha_fin);
                    DefaultTableModel modelo = (DefaultTableModel)jTable1.getModel();
                    int c=0;
                    while(jTable1.getRowCount()!=c){
                        if(modelo.getValueAt(c, 0).equals(true)){
                            Personal per=(Personal) modelo.getValueAt(c, 1);
                            AgendaId ida=new AgendaId(per.getIdPersonal(),tar.getIdTarea());
                            Agenda age=new Agenda();
                            age.setId(ida);
                            age.setPersonal(per);
                            Revista rev=(Revista) Drive.PERSISTENCIA.getSitRevista().get(0);
                            age.setRevista(rev);
                            age.setTarea(tar);
                            age.setComentario(null);
                            age.guardarAgenda(age);
                            ///Guarda el dia y hora de inicio
                            Ano anio=new Ano();
                            anio.setAgenda(age);
                            anio.setAno(fecha_inicio.getYear()+1900);
                            anio.guardarAno(anio);
                            Mes mes=new Mes();
                            mes.setAno(anio);
                            mes.setMes(fecha_inicio.getMonth());
                            mes.guardarMes(mes);
                            Dia dia=new Dia();
                            dia.setMes(mes);
                            dia.setDia(fecha_inicio.getDate());
                            dia.guardarDia(dia);
                            
                            Iniciofin in=new Iniciofin();
                            in.setDia(dia);
                            in.setInicio(inicio);
                            if(jRadioButton1.isSelected()){
                                in.setEstadoInicio(false);
                            }
                            in.guardarIniciofin(in);
                            ///Guarda el dia y hora de fin
                            Ano anioo=new Ano();
                            Mes mess=new Mes();
                            Dia diaa=new Dia();
                            if(fecha_inicio.getYear()!=fecha_fin.getYear()||fecha_inicio.getMonth()!=fecha_fin.getMonth()||fecha_inicio.getDate()!=fecha_fin.getDate()){
                                if(fecha_inicio.getYear()!=fecha_fin.getYear()){
                                    anioo.setAgenda(age);
                                    anioo.setAno(fecha_fin.getYear()+1900);
                                    anioo.guardarAno(anioo);
                                }
                                if(fecha_inicio.getMonth()!=fecha_fin.getMonth()){
                                    mess.setAno(anioo);
                                    mess.setMes(fecha_fin.getMonth());
                                    mess.guardarMes(mess);
                                }
                                if(fecha_inicio.getDate()!=fecha_fin.getDate()){
                                    diaa.setMes(mess);
                                    diaa.setDia(fecha_fin.getDate());
                                    diaa.guardarDia(diaa);
                                    Iniciofin finn=new Iniciofin();
                                    finn.setDia(diaa);
                                    finn.setFin(fin);
                                    finn.guardarIniciofin(finn);
                                }
                            }else{
                                in.setFin(fin);
                                in.guardarIniciofin(in);
                            }
                        }
                        c++;
                    }
                    jFormattedTextField1.setText("");
                    jFormattedTextField2.setText("");
                    jTextField1.setText("");
                    jTextField3.setText("");
                    jTextField4.setText("");
                    Drive.LimpiarTabla(jTable1);
                    Drive.CargarTablacheck(jTable1);
                }
            }else{JOptionPane.showMessageDialog(null, "Todos los campos con '*' son obligatorios y los horarios no pueden contener espacios en blanco");}
            }else{JOptionPane.showMessageDialog(null, "Debe seleccionar al menos un personal");}
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
//            java.util.logging.Logger.getLogger(JFrameExtracurricular.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(JFrameExtracurricular.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(JFrameExtracurricular.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(JFrameExtracurricular.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new JFrameExtracurricular().setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private datechooser.beans.DateChooserCombo dateChooserCombo2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
