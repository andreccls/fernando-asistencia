/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Clases.Agenda;
import Clases.Auditoria;
import Clases.Controlador;
import Clases.Franco;
import Clases.Personal;
import Clases.Tarea;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Image;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author fer
 */
public class JFrameActualizarActividades extends javax.swing.JFrame {

    public Controlador Drive;
    public Personal adm;
//    int idsesion;
    Tarea tar;
    boolean band=false;
    /**
     * Creates new form JFrameActualizarActividades
     */
    public JFrameActualizarActividades(Controlador unDrive,Tarea tarr, Personal admin,boolean bandera) {
        initComponents();
        this.Drive=unDrive;
        this.adm=admin;
        this.tar=tarr;
//        this.idsesion=id;
        this.band=bandera;
        Drive.LimpiarTabla(jTable1);
        Drive.CargarComboLugar(jComboBox1);
        jComboBox1.setSelectedItem(tar.getLugar());
        jTextField1.setText(tarr.getNombre());
        jTextField1.setEnabled(false);
        jButton1.setEnabled(false);
        int[] anchos1 = {170,120,90,90,80,80,80};
        for(int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos1[i]);
        }
        DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
        modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
        jTable1.getColumnModel().getColumn(4).setCellRenderer(modelocentrar); 
        jTable1.getColumnModel().getColumn(5).setCellRenderer(modelocentrar);
        jTable1.getColumnModel().getColumn(6).setCellRenderer(modelocentrar);
        
        jTable1.getTableHeader().setDefaultRenderer(new JFrameActualizarActividades.HeaderRenderer(jTable1));
        if(tar.getComentario().equals("EXTRACURRICULAR")){
            jButton1.setEnabled(true);
            JTableHeader th = jTable1.getTableHeader();
            TableColumnModel tcm = th.getColumnModel();
            TableColumn tc = tcm.getColumn(2); //recordemos que las columnas inician a enumerarse desde cero
            tc.setHeaderValue("Franco");
            TableColumn tc1 = tcm.getColumn(3); //recordemos que las columnas inician a enumerarse desde cero
            tc1.setHeaderValue("Fecha fin");
            TableColumn tc2 = tcm.getColumn(4); //recordemos que las columnas inician a enumerarse desde cero
            tc2.setHeaderValue("Fecha inicio");
            th.repaint(); //actualizamos el header
            TableColumn Column3 = jTable1.getColumnModel().getColumn(2);
            try {
                MaskFormatter campo=null;
                campo = new MaskFormatter("dd/MM/yyyy");
                JFormattedTextField text = new JFormattedTextField(campo);
                MaskFormatter uppercase=null;
                uppercase = new MaskFormatter("##/##/####");
                DefaultFormatterFactory factory = new DefaultFormatterFactory(uppercase);
                text.setFormatterFactory(factory);
                Column3.setCellEditor(new DefaultCellEditor(text));
                
            } catch (ParseException ex) {
                Logger.getLogger(JFrameActualizarActividades.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(tar.getComentario().equals("OTRO")){
            JTableHeader th = jTable1.getTableHeader();
            TableColumnModel tcm = th.getColumnModel();
            TableColumn t = tcm.getColumn(1); //recordemos que las columnas inician a enumerarse desde cero
            t.setHeaderValue("Descripción");
            TableColumn tc = tcm.getColumn(2); //recordemos que las columnas inician a enumerarse desde cero
            tc.setHeaderValue("Comentario");
            TableColumn tc1 = tcm.getColumn(3);
            tc1.setHeaderValue("Fecha fin");
            TableColumn tc2 = tcm.getColumn(4);
            tc2.setHeaderValue("Fecha inicio");
            th.repaint();
            jTable1.setEnabled(false);
        }else if(tar.getComentario().equals("CLASE")){
            JTableHeader th = jTable1.getTableHeader();
            TableColumnModel tcm = th.getColumnModel();
            TableColumn t = tcm.getColumn(1); //recordemos que las columnas inician a enumerarse desde cero
            t.setHeaderValue("Sit. Revista");
            TableColumn tc = tcm.getColumn(2); //recordemos que las columnas inician a enumerarse desde cero
            tc.setHeaderValue("Aula");
            TableColumn tc1 = tcm.getColumn(3);
            tc1.setHeaderValue("Numero");
            th.repaint();
            jTable1.setEnabled(false);
        }else if(tar.getComentario().equals("REUNION")){
            JTableHeader th = jTable1.getTableHeader();
            TableColumnModel tcm = th.getColumnModel();
            TableColumn t = tcm.getColumn(1); //recordemos que las columnas inician a enumerarse desde cero
            t.setHeaderValue("Motivo");
            TableColumn tc = tcm.getColumn(2); //recordemos que las columnas inician a enumerarse desde cero
            tc.setHeaderValue("Caracter");
//            TableColumn tc1 = tcm.getColumn(4);
//            tc1.setHeaderValue("Numero");
            th.repaint();
            jTable1.setEnabled(false);
        } else {
            JTableHeader th = jTable1.getTableHeader();
            TableColumnModel tcm = th.getColumnModel();
            TableColumn t = tcm.getColumn(1); //recordemos que las columnas inician a enumerarse desde cero
            t.setHeaderValue("Lugar");
            TableColumn tc = tcm.getColumn(2); //recordemos que las columnas inician a enumerarse desde cero
            tc.setHeaderValue("Comentario");
//            TableColumn tc1 = tcm.getColumn(3);
//            tc1.setHeaderValue("Fecha fin");
//            TableColumn tc2 = tcm.getColumn(4);
//            tc2.setHeaderValue("Fecha");
            th.repaint();
            jTable1.setEnabled(false);
        }
        Drive.CargarTablaActividad(jTable1, tar);
        ImageIcon fott1 = new ImageIcon(getClass().getResource("/imagenes/no.png"));
        Icon icono1 = new ImageIcon(fott1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        jButton2.setIcon(icono1);
        ImageIcon fott2 = new ImageIcon(getClass().getResource("/imagenes/ok.png"));
        Icon icono2 = new ImageIcon(fott2.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        jButton1.setIcon(icono2);
        ImageIcon fott3 = new ImageIcon(getClass().getResource("/imagenes/Editar.png"));
        Icon icono3 = new ImageIcon(fott3.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        jButton4.setIcon(icono3);
        ImageIcon fott4 = new ImageIcon(getClass().getResource("/imagenes/Eliminar.png"));
        Icon icono4 = new ImageIcon(fott4.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        jButton3.setIcon(icono4);
        if(band==true){
            jButton1.setEnabled(false);
            jButton3.setEnabled(false);
            jButton4.setEnabled(false);
            jTextField1.setEnabled(false);
        }
        if(adm.getPerfil().getActividadesact()==null){
            jButton4.setEnabled(false);
        }
        if(adm.getPerfil().getActividadeseli()==null){
            jButton3.setEnabled(false);
        }
        if(adm.getPerfil().getActividadesins()==null){
            jButton1.setEnabled(false);
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
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SISTEMA DE ASISTENCIA DE PERSONAL EDUCATIVO");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de la Tarea"));

        jLabel1.setText("Nombre:");

        jButton2.setText("Salir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Lugar:");

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Personal", "Descripción 1", "Descripción 2", "Descripción 3", "Fecha", "Inicio", "Fin"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(25);
        jScrollPane2.setViewportView(jTable1);

        jButton3.setText("Eliminar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setText("Modificar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(28, 28, 28)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2, jButton3, jButton4});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton1)
                    .addComponent(jButton4))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
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
        if(band==true){
            Frame vp=new JFrameHistorial(Drive,adm);
            this.dispose();
            vp.show();
        }else{
            JFrameConsultaActividades vpp=new JFrameConsultaActividades(Drive,adm);
            this.hide();
            vpp.show();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try{
            int confirmado = JOptionPane.showConfirmDialog(null,"¿Esta seguro que desea eliminar la tarea y todos sus horarios?","Eliminar tarea",JOptionPane.YES_NO_OPTION);
            if (JOptionPane.OK_OPTION == confirmado){
                Date fecha=new Date();
                Date diaini= new Date();
                SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat formateador2 = new SimpleDateFormat("HH:mm");
                if(!tar.getTareaextracurriculars().isEmpty()){
                    diaini=tar.getDiaInicio();
                }else if(!tar.getTareareunions().isEmpty()){
                    
                    diaini=formateador.parse(jTable1.getModel().getValueAt(0,4).toString());
                    Date hini=formateador2.parse(jTable1.getModel().getValueAt(0,5).toString());
                    diaini.setHours(hini.getHours());
                    diaini.setMinutes(hini.getMinutes());
                    diaini.setSeconds(hini.getSeconds());
                } else if(!tar.getTareaotros().isEmpty()){
                    diaini=tar.getDiaInicio();
                } else if (!tar.getTareaclases().isEmpty()) {
                    DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
                    int e = 0;
                    while (modelo.getRowCount() != e) {
                        Date aux = formateador.parse(jTable1.getModel().getValueAt(e, 4).toString());
                        Date hini = formateador2.parse(jTable1.getModel().getValueAt(e, 5).toString());
                        aux.setHours(hini.getHours());
                        aux.setMinutes(hini.getMinutes());
                        aux.setSeconds(hini.getSeconds());
                        if(aux.compareTo(diaini)<0){
                            diaini=aux;
                        }
                        e++;
                    }
                }
                if(fecha.compareTo(diaini)<=0){
                    tar.RecuperarAsistencia(tar,diaini);
                    tar.setEstado(false);
                    tar.ActualizarTarea(tar);
                    Auditoria audi=new Auditoria();
                    audi.setPersonalByIdAuditor(adm);
                    audi.setTarea(tar);
                    audi.setOperacion("Eliminar");
                    audi.setFecha(new Date());
                    audi.guardarAuditoria(audi);
                    JOptionPane.showMessageDialog(null,"La tarea se eliminó correctamente","Eliminar tarea",JOptionPane.INFORMATION_MESSAGE);
                    JFrameConsultaActividades vpp=new JFrameConsultaActividades(Drive,adm);
                    this.hide();
                    vpp.show();
                }else{
                    tar.setEstado(false);
                    tar.ActualizarTarea(tar);
                    Auditoria audi=new Auditoria();
                    audi.setPersonalByIdAuditor(adm);
                    audi.setTarea(tar);
                    audi.setOperacion("Eliminar");
                    audi.setFecha(new Date());
                    audi.guardarAuditoria(audi);
                    JOptionPane.showMessageDialog(null,"La tarea se eliminó correctamente","Eliminar tarea",JOptionPane.INFORMATION_MESSAGE);
                    JFrameConsultaActividades vpp=new JFrameConsultaActividades(Drive,adm);
                    this.hide();
                    vpp.show();
                }
            }   
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se ha podido eliminar la tarea","Eliminar Tarea",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            if(tar.getComentario().equals("EXTRACURRICULAR")){
                DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
                int e = 0;
                while (modelo.getRowCount() != e) {
                    Personal id= (Personal) modelo.getValueAt(e, 0);
                    if(modelo.getValueAt(e, 2)!=null){
                            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                            formateador.setLenient(false);
                            Agenda age=Drive.getAgenda(tar, id.getIdPersonal());
                            Franco fran=age.getFranco(age);
                            fran.setDiaFranco(formateador.parse(modelo.getValueAt(e, 2).toString()));
                            if(fran.getIdFranco()!=null){
                                fran.actualizarFranco(fran);
                            }else{
                                fran.setAgenda(age);
                                fran.guardarFranco(fran);
                            }
                    }
                    e++;
                }
            }
            JFrameConsultaActividades vpp=new JFrameConsultaActividades(Drive,adm);
            this.hide();
            vpp.show();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ingrese correctamente la fecha de franco","Registrar Franco", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if(band==true){
            Frame vp=new JFrameHistorial(Drive,adm);
            this.dispose();
            vp.show();
        }else{
            JFrameConsultaActividades vpp=new JFrameConsultaActividades(Drive,adm);
            this.dispose();
            vpp.show();
        }
    }//GEN-LAST:event_formWindowClosing

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int confirmado = JOptionPane.showConfirmDialog(null, "¿Desea modificar la Tarea?", "Actualizar Tarea", JOptionPane.YES_NO_OPTION);
        if (JOptionPane.OK_OPTION == confirmado) {
            if (tar.getComentario().equals("EXTRACURRICULAR")) {
                try {
                    DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
                    if (jTable1.getRowCount() > 0) {
                        Object o = modelo.getValueAt(0, 3);
                        Object oo = modelo.getValueAt(0, 6);
                        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                        SimpleDateFormat formateador2 = new SimpleDateFormat("HH:mm");
                        Date fecha = formateador.parse(o.toString());
                        Date hora = formateador2.parse(oo.toString());
                        fecha.setHours(hora.getHours());
                        fecha.setMinutes(hora.getMinutes());
                        Date aux = new Date();
                        if (aux.compareTo(fecha) > 0) {
                            JOptionPane.showMessageDialog(null, "No puede modificar una tarea que ya ocurrió", "Actualizar Tarea", JOptionPane.ERROR_MESSAGE);
                        } else {
                            JFrameExtracurricular vent2 = new JFrameExtracurricular(Drive, adm,tar);
                            this.hide();
                            vent2.show();
                        }
                    }
                } catch (Exception e) {
                }
                
            } else if (tar.getComentario().equals("OTRO")) {
                try {
                    DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
                    if (jTable1.getRowCount() > 0) {
                        Object o = modelo.getValueAt(0, 3);
                        Object oo = modelo.getValueAt(0, 6);
                        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                        SimpleDateFormat formateador2 = new SimpleDateFormat("HH:mm");
                        Date fecha = formateador.parse(o.toString());
                        Date hora = formateador2.parse(oo.toString());
                        fecha.setHours(hora.getHours());
                        fecha.setMinutes(hora.getMinutes());
                        Date aux = new Date();
                        if (aux.compareTo(fecha) > 0) {
                            JOptionPane.showMessageDialog(null, "No puede modificar una tarea que ya ocurrió", "Actualizar Tarea", JOptionPane.ERROR_MESSAGE);
                        } else {
                            JFrameotro vent2 = new JFrameotro(Drive, adm, tar);
                            this.hide();
                            vent2.show();
                        }
                    }
                } catch (Exception e) {
                }
            } else if (tar.getComentario().equals("CLASE")) {
                JFrameClase vent2 = new JFrameClase(Drive, adm, tar);
                this.hide();
                vent2.show();
            } else if (tar.getComentario().equals("REUNION")) {
                try {
                    DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
                    if (jTable1.getRowCount() > 0) {
                        Object o = modelo.getValueAt(0, 4);
                        Object oo = modelo.getValueAt(0, 6);
                        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                        SimpleDateFormat formateador2 = new SimpleDateFormat("HH:mm");
                        Date fecha = formateador.parse(o.toString());
                        Date hora = formateador2.parse(oo.toString());
                        fecha.setHours(hora.getHours());
                        fecha.setMinutes(hora.getMinutes());
                        Date aux = new Date();
                        if (aux.compareTo(fecha) > 0) {
                            JOptionPane.showMessageDialog(null, "No puede modificar una tarea que ya ocurrió", "Actualizar Tarea", JOptionPane.ERROR_MESSAGE);
                        } else {
                            JFrameReunion vent2 = new JFrameReunion(Drive, adm, tar);
                            this.hide();
                            vent2.show();
                        }
                    }
                } catch (Exception e) {
                }
                
            }else{
                JFrameAdministrativo vent2 = new JFrameAdministrativo(Drive, adm, tar);
                this.hide();
                vent2.show();
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

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
//            java.util.logging.Logger.getLogger(JFrameActualizarActividades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(JFrameActualizarActividades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(JFrameActualizarActividades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(JFrameActualizarActividades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new JFrameActualizarActividades().setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
