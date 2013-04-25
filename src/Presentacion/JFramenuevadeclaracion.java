/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Clases.Cargo;
import Clases.Controlador;
import Clases.Declaracionjurada;
import Clases.DetalleEstablecimiento;
import Clases.Establecimiento;
import Clases.Nivel;
import Clases.Personal;
import Clases.Tipocargo;
import Clases.Tiponivel;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

/**
 *
 * @author fer
 */
public class JFramenuevadeclaracion extends javax.swing.JFrame{

    /**
     * Creates new form JFramenuevadeclaracion
     */
    Controlador Drive=new Controlador();
    public Personal adm;
    Personal pe;
    int idsesion;
    StringBuffer buffer1=new StringBuffer();
    StringBuffer buffer2=new StringBuffer();
    StringBuffer buffer3=new StringBuffer();
    Declaracionjurada dec;
    
    public JFramenuevadeclaracion(Controlador unDrive,Personal per,Declaracionjurada decju, Personal admin,int id) {
        initComponents();
        Controlador auxDrive = new Controlador();
        auxDrive.getPrimerEstablecimiento();
        this.Drive=unDrive;
        this.pe=per;
        this.dec=decju;
        this.adm=admin;
        this.idsesion=id;
        Drive.PERSISTENCIA.DecjuradaPer(pe.getIdPersonal());
        Drive.CargarComboEstablecimiento(jComboBox1);
        Drive.CargarComboTipocargo(jComboBox2);
        Drive.CargarComboTiponivel(jComboBox3);
        ///ICONO EDITAR
        ImageIcon fot = new ImageIcon("src\\imagenes\\image.jpg");
        Icon icono1 = new ImageIcon(fot.getImage().getScaledInstance(jLabel1.getWidth(), jLabel1.getHeight(), Image.SCALE_DEFAULT));
        jLabel1.setIcon(icono1);
        jLabel1.repaint();
        jLabel2.setIcon(icono1);
        jLabel2.repaint();
        jLabel3.setIcon(icono1);
        jLabel3.repaint();
        ///ICONO ELIMINAR
        ImageIcon fott = new ImageIcon("src\\imagenes\\eliminar.gif");
        Icon icono4 = new ImageIcon(fott.getImage().getScaledInstance(jLabel34.getWidth(), jLabel34.getHeight(), Image.SCALE_DEFAULT));
        jLabel34.setIcon(icono4);
        jLabel34.repaint();
        jLabel35.setIcon(icono4);
        jLabel35.repaint();
        jLabel36.setIcon(icono4);
        jLabel36.repaint();
        ImageIcon fott2 = new ImageIcon("src\\imagenes\\no.png");
        Icon icono2 = new ImageIcon(fott2.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        jButton1.setIcon(icono2);
        ImageIcon fott3 = new ImageIcon("src\\imagenes\\ok.png");
        Icon icono3 = new ImageIcon(fott3.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        jButton2.setIcon(icono3);
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
        jLabel16 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jComboBox3 = new javax.swing.JComboBox();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SISTEMA DE ASISTENCIA DE PERSONAL EDUCATIVO");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Ingrese los datos de la declaración jurada"));

        jLabel16.setText("Establecimiento:");

        jLabel31.setText("Cargo:");

        jLabel32.setText("Nivel:");

        jLabel33.setText("Horas:");

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jComboBox1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBox1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jComboBox1KeyTyped(evt);
            }
        });

        jComboBox2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jComboBox2KeyTyped(evt);
            }
        });

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "  ", " " }));
        jComboBox3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jComboBox3KeyTyped(evt);
            }
        });

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField1FocusLost(evt);
            }
        });

        jLabel1.setText("N");
        jLabel1.setPreferredSize(new java.awt.Dimension(15, 15));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel2.setText("N");
        jLabel2.setPreferredSize(new java.awt.Dimension(15, 15));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel3.setText("N");
        jLabel3.setPreferredSize(new java.awt.Dimension(15, 15));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

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

        jLabel34.setText("E");
        jLabel34.setPreferredSize(new java.awt.Dimension(15, 15));
        jLabel34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel34MouseClicked(evt);
            }
        });

        jLabel35.setText("E");
        jLabel35.setPreferredSize(new java.awt.Dimension(15, 15));
        jLabel35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel35MouseClicked(evt);
            }
        });

        jLabel36.setText("E");
        jLabel36.setPreferredSize(new java.awt.Dimension(15, 15));
        jLabel36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel36MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel32)
                    .addComponent(jLabel31)
                    .addComponent(jLabel33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(285, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(292, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jComboBox1, jComboBox2, jComboBox3});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel31)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel32)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBox1, jComboBox2, jComboBox3});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton2});

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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Frame vp=new JFrameActualizarPersonal(Drive,pe,detalle,carg,niv);
    
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyPressed

    }//GEN-LAST:event_jComboBox1KeyPressed

    private void jComboBox1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyTyped

    }//GEN-LAST:event_jComboBox1KeyTyped

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed

    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jComboBox1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyReleased

    }//GEN-LAST:event_jComboBox1KeyReleased

    private void jComboBox2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox2KeyTyped

    }//GEN-LAST:event_jComboBox2KeyTyped

    private void jComboBox3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox3KeyTyped

    }//GEN-LAST:event_jComboBox3KeyTyped

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        try{
            JTextField salida = new JTextField();
            JTextField calle = new JTextField();
            JTextField numero = new JTextField();
            JTextField piso = new JTextField();
            JTextField depto = new JTextField();
            String cadSalida;
            salida.setText("");
            salida.setSize(25, 25);
            String cadcalle;
            calle.setText("");
            calle.setSize(25, 25);
            int cadnumero;
            numero.setText("");
            numero.setSize(25, 25);
            String cadpiso;
            piso.setText("");
            piso.setSize(25, 25);
            String caddepto;
            depto.setText("");
            depto.setSize(25, 25);
            Establecimiento est= new Establecimiento();
            JOptionPane.showMessageDialog(null,salida, "Ingrese el nombre del establecimiento", JOptionPane.INFORMATION_MESSAGE);
            if(!salida.getText().isEmpty()){
                cadSalida = salida.getText().toUpperCase();
                Iterator it=Drive.PERSISTENCIA.getEstablecimientos().iterator();
                boolean w=false;
                while(it.hasNext()){
                    Establecimiento tip=(Establecimiento) it.next();
                    if(tip.getNombre().equals(cadSalida)){
                        JOptionPane.showMessageDialog(null, "El establecimiento ya existe","Registrar Establecimiento", JOptionPane.ERROR_MESSAGE);
                        w=true;
                    }
                }
                if(w==false){
                    est.setNombre(cadSalida);
                    JOptionPane.showMessageDialog(null,calle, "Ingrese el nombre de la Calle", JOptionPane.INFORMATION_MESSAGE);
                    JOptionPane.showMessageDialog(null,numero, "Ingrese el numero de la Altura", JOptionPane.INFORMATION_MESSAGE);
                    JOptionPane.showMessageDialog(null,piso, "Ingrese el nombre/numero de Piso", JOptionPane.INFORMATION_MESSAGE);
                    JOptionPane.showMessageDialog(null,depto, "Ingrese el nombre/numero de Depto", JOptionPane.INFORMATION_MESSAGE);
                    if(!calle.getText().isEmpty()){
                        cadcalle=calle.getText().toUpperCase();
                        est.setCalle(cadcalle);
                    }
                    if(!numero.getText().isEmpty()){
                        cadnumero=Integer.parseInt(numero.getText());
                    }else{cadnumero=0;}
                    cadpiso=piso.getText().toUpperCase();
                    caddepto=depto.getText().toUpperCase();

                    est.setAltura(cadnumero);
                    est.setPiso(cadpiso);
                    est.setDepto(caddepto);
                    est.guardarEstablecimiento(est);
                    Drive.LimpiarCombo(jComboBox1);
                    Drive.CargarComboEstablecimiento(jComboBox1);
                    jComboBox1.setSelectedItem(est);
                }
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Ingrese correctamente los datos","Registrar Establecimiento", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        JTextField salida = new JTextField();
        String cadSalida;
        salida.setText("");
        salida.setSize(50, 50);
        JOptionPane.showMessageDialog(null, salida, "Ingrese un nuevo cargo", JOptionPane.INFORMATION_MESSAGE);
        if(!salida.getText().isEmpty()){
            cadSalida = salida.getText().toUpperCase();
            Iterator it=Drive.PERSISTENCIA.getTipocargos().iterator();
            boolean w=false;
            while(it.hasNext()){
                Tipocargo tip=(Tipocargo) it.next();
                if(tip.getNombre().equals(cadSalida)){
                    JOptionPane.showMessageDialog(null, "El cargo ya existe","Registrar Cargo", JOptionPane.ERROR_MESSAGE);
                    w=true;
                }
            }
            if(w==false){
                cadSalida = salida.getText().toUpperCase();
                Tipocargo carg= new Tipocargo();
                carg.setNombre(cadSalida);
                carg.guardarTipocargo(carg);
                Drive.LimpiarCombo(jComboBox2);
                Drive.CargarComboTipocargo(jComboBox2);
                jComboBox2.setSelectedItem(carg);
            }
        }
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        JTextField salida = new JTextField();
        String cadSalida;
        salida.setText("");
        salida.setSize(50, 50);
        JOptionPane.showMessageDialog(null, salida, "Ingrese un nuevo Nivel", JOptionPane.INFORMATION_MESSAGE);
        if(!salida.getText().isEmpty()){
            cadSalida = salida.getText().toUpperCase();
            Iterator it=Drive.PERSISTENCIA.getTiponiveles().iterator();
            boolean w=false;
            while(it.hasNext()){
                Tiponivel tip=(Tiponivel) it.next();
                if(tip.getNombre().equals(cadSalida)){
                    JOptionPane.showMessageDialog(null, "El nivel ya existe","Registrar Nivel", JOptionPane.ERROR_MESSAGE);
                    w=true;
                }
            }
            if(w==false){
                cadSalida = salida.getText().toUpperCase();
                Tiponivel niv= new Tiponivel();
                niv.setNombre(cadSalida);
                niv.guardarTiponivel(niv);
                Drive.LimpiarCombo(jComboBox3);
                Drive.CargarComboTiponivel(jComboBox3);
                jComboBox3.setSelectedItem(niv);
            }
        }
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFrameActualizarPersonal ventdec = new JFrameActualizarPersonal(Drive,pe,adm,idsesion);
        if(!jTextField1.getText().isEmpty()){
            int confirmado = JOptionPane.showConfirmDialog(null,"¿Desea cancelar la nueva declaración?","",JOptionPane.YES_NO_OPTION);
            if (JOptionPane.OK_OPTION == confirmado){
                this.hide();
                ventdec.show();
            }
        }else{this.dispose();
            ventdec.show();}
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(!jTextField1.getText().isEmpty()){
            Establecimiento est=(Establecimiento) jComboBox1.getSelectedItem();
            DetalleEstablecimiento detalle=dec.getDetalleEstablecimiento(est);
            if(detalle.getIdDetalleestablecimiento()==null){
                detalle.setDeclaracionjurada(dec);
                detalle.setEstablecimiento(est);
                detalle.guardarDetalleEstablecimiento(detalle);
            }
            
            Tipocargo tip= (Tipocargo) jComboBox2.getSelectedItem();
            Cargo carg= detalle.getCargo(tip);
            if(carg.getIdCargo()==null){
                carg.setDetalleEstablecimiento(detalle);
                carg.setTipocargo(tip);
                carg.guardarCargo(carg);
            }
            
            Tiponivel tiponiv= (Tiponivel) jComboBox3.getSelectedItem();
            Nivel niv = carg.getNivel(tiponiv);
            if(niv.getIdNivel()==null){
                niv.setCargo(carg);
                niv.setTiponivel(tiponiv);
                niv.setHoras(Integer.parseInt(jTextField1.getText()));
                niv.guardarNivel(niv);
            }else{
                if(niv.getHoras()!= Integer.parseInt(jTextField1.getText())){
                    niv.setHoras(Integer.parseInt(jTextField1.getText()));
                    niv.ActualizarNivel(niv);
                }
            }

            JFrameActualizarPersonal ventdec = new JFrameActualizarPersonal(Drive,pe,adm,idsesion);
            this.hide();
            ventdec.show();
        }else{JOptionPane.showMessageDialog(null, "INGRESE LAS HORAS","Registrar Declaración", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jLabel34MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel34MouseClicked
        int confirmado = JOptionPane.showConfirmDialog(null,"¿Desea eliminar el establecimiento?","",JOptionPane.YES_NO_OPTION);
        if (JOptionPane.OK_OPTION == confirmado){
            Establecimiento est=(Establecimiento) jComboBox1.getSelectedItem();
            est.eliminarEstablecimiento(est);
            Drive.LimpiarCombo(jComboBox1);
            Drive.CargarComboEstablecimiento(jComboBox1);
        }
    }//GEN-LAST:event_jLabel34MouseClicked

    private void jLabel35MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel35MouseClicked
        int confirmado = JOptionPane.showConfirmDialog(null,"¿Desea eliminar el tipo de cargo?","",JOptionPane.YES_NO_OPTION);
        if (JOptionPane.OK_OPTION == confirmado){
            Tipocargo car=(Tipocargo) jComboBox2.getSelectedItem();
            car.eliminarTipocargo(car);
            Drive.LimpiarCombo(jComboBox2);
            Drive.CargarComboTipocargo(jComboBox2);
        }
    }//GEN-LAST:event_jLabel35MouseClicked

    private void jLabel36MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel36MouseClicked
        int confirmado = JOptionPane.showConfirmDialog(null,"¿Desea eliminar el nivel?","",JOptionPane.YES_NO_OPTION);
        if (JOptionPane.OK_OPTION == confirmado){
            Tiponivel niv=(Tiponivel) jComboBox3.getSelectedItem();
            niv.eliminarTiponivel(niv);
            Drive.LimpiarCombo(jComboBox3);
            Drive.CargarComboTiponivel(jComboBox3);
        }
    }//GEN-LAST:event_jLabel36MouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        JFrameActualizarPersonal ventdec = new JFrameActualizarPersonal(Drive,pe,adm,idsesion);
        if(!jTextField1.getText().isEmpty()){
            int confirmado = JOptionPane.showConfirmDialog(null,"¿Desea cancelar la nueva declaración?","",JOptionPane.YES_NO_OPTION);
            if (JOptionPane.OK_OPTION == confirmado){
                this.hide();
                ventdec.show();
            }
        }else{this.dispose();
            ventdec.show();}
    }//GEN-LAST:event_formWindowClosing

    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost
        if(!jTextField1.getText().isEmpty()){
            if(Integer.parseInt(jTextField1.getText())>42){
                JOptionPane.showMessageDialog(null, "Las horas no puede ser mayor a 42","Nueva Declaración jurada", JOptionPane.ERROR_MESSAGE);
                jTextField1.setText("");
            }
        }
    }//GEN-LAST:event_jTextField1FocusLost

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
//            java.util.logging.Logger.getLogger(JFramenuevadeclaracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(JFramenuevadeclaracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(JFramenuevadeclaracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(JFramenuevadeclaracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new JFramenuevadeclaracion().setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}