/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JFramePrincipal.java
 *
 * Created on 27/06/2012, 15:01:20
 */
package Presentacion;

import Clases.Agenda;
import Clases.Circular;
import Clases.Controlador;
import Clases.Declaracionjurada;
import javax.swing.JOptionPane;
import Clases.Personal;
import Clases.Personaldocente;
import Clases.Personalnodocente;
import Clases.Registroacceso;
import Clases.Tarea;
import Clases.Telefono;
import TareasProgramadas.Programacion;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LinearGradientPaint;
import java.awt.Point;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author fer
 */
public class JFramePrincipal extends javax.swing.JFrame {

    /**
     * Creates new form JFramePrincipal
     */
     Controlador Drive=new Controlador();
     Personal adm;
     public int idsesion;
     Tarea tar=new Tarea();
     Circular cir=new Circular();
     
     
    public JFramePrincipal(Controlador unDrive, Personal admin,int id) {
        this.Drive=unDrive;
        this.adm=admin;
        this.idsesion=id;
        initComponents();
        ImageIcon fott = new ImageIcon(getClass().getResource("/imagenes/gutenberg.png"));
        Icon icono = new ImageIcon(fott.getImage().getScaledInstance(jLabel1.getWidth(), jLabel1.getHeight(), Image.SCALE_DEFAULT));
        jLabel1.setIcon(icono);
        jLabel1.repaint();
        int nivel=0;
        if(adm.getIdPersonal()==null){
            nivel=1;
        }else{
        nivel=adm.getPerfil().getNivel();}
        if(nivel==4){
            jMenuItem1.setEnabled(false);//registrar personal
            jMenuItem2.setEnabled(false);//registrar huella
            jMenuItem3.setEnabled(false);//Consultar personal
            jMenuItem4.setEnabled(false);//registrar extracurricular
            jMenuItem5.setEnabled(false);//registrar clase
            jMenuItem6.setEnabled(false);//registrar reunion
            jMenuItem7.setEnabled(false);//registrar otro
            jMenuItem8.setEnabled(false);//Consultar inasistencia
            jMenuItem9.setEnabled(false);//Consultar actividades
            jMenuItem11.setEnabled(false);//registrar feriados
            jMenuItem12.setEnabled(false);//registrar feriados
            jMenuItem13.setEnabled(false);//registrar feriados
            jMenuItem14.setEnabled(false);//registrar feriados
//            jMenuItem12.setEnabled(false);//registrar feriados
//            jMenuItem13.setEnabled(false);//registrar feriados
        }else if(nivel==3){
            jMenuItem1.setEnabled(false);//registrar personal
            jMenuItem2.setEnabled(false);//registrar huella
            jMenuItem4.setEnabled(false);//registrar extracurricular
            jMenuItem5.setEnabled(false);//registrar clase
            jMenuItem6.setEnabled(false);//registrar reunion
            jMenuItem7.setEnabled(false);//registrar otro
//            jMenuItem13.setEnabled(false);//registrar feriados
        }
        ImageIcon fott1 = new ImageIcon(getClass().getResource("/imagenes/Personal.png"));
        Icon icono1 = new ImageIcon(fott1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        jMenuItem1.setIcon(icono1);
        ImageIcon fott2 = new ImageIcon(getClass().getResource("/imagenes/Huella.png"));
        Icon icono2 = new ImageIcon(fott2.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        jMenuItem2.setIcon(icono2);
        ImageIcon fott3 = new ImageIcon(getClass().getResource("/imagenes/Clase.png"));
        Icon icono3 = new ImageIcon(fott3.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        jMenuItem5.setIcon(icono3);
        ImageIcon fott4 = new ImageIcon(getClass().getResource("/imagenes/Reunion.png"));
        Icon icono4 = new ImageIcon(fott4.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        jMenuItem6.setIcon(icono4);
        ImageIcon fott5 = new ImageIcon(getClass().getResource("/imagenes/Extra.png"));
        Icon icono5 = new ImageIcon(fott5.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        jMenuItem4.setIcon(icono5);
        ImageIcon fott6 = new ImageIcon(getClass().getResource("/imagenes/Otro.png"));
        Icon icono6 = new ImageIcon(fott6.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        jMenuItem7.setIcon(icono6);
        ImageIcon fott7 = new ImageIcon(getClass().getResource("/imagenes/Circularr.png"));
        Icon icono7 = new ImageIcon(fott7.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        jMenuItem11.setIcon(icono7);
        ImageIcon fott8 = new ImageIcon(getClass().getResource("/imagenes/Consultapersonal.png"));
        Icon icono8 = new ImageIcon(fott8.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        jMenuItem3.setIcon(icono8);
        ImageIcon fott9 = new ImageIcon(getClass().getResource("/imagenes/Inasistencia.png"));
        Icon icono9 = new ImageIcon(fott9.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        jMenuItem8.setIcon(icono9);
        ImageIcon fott10 = new ImageIcon(getClass().getResource("/imagenes/Actividades.png"));
        Icon icono10 = new ImageIcon(fott10.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        jMenuItem9.setIcon(icono10);
        ImageIcon fott11 = new ImageIcon(getClass().getResource("/imagenes/Circulares.png"));
        Icon icono11 = new ImageIcon(fott11.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        jMenuItem12.setIcon(icono11);
        ImageIcon fott12 = new ImageIcon(getClass().getResource("/imagenes/Registro2.png"));
        Icon icono12 = new ImageIcon(fott12.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        jMenuItem13.setIcon(icono12);
        ImageIcon fott13 = new ImageIcon(getClass().getResource("/imagenes/Feriado.png"));
        Icon icono13 = new ImageIcon(fott13.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        jMenuItem14.setIcon(icono13);
        ImageIcon fott14 = new ImageIcon(getClass().getResource("/imagenes/no.png"));
        Icon icono14 = new ImageIcon(fott14.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        jButton3.setIcon(icono14);
        
//        ImageIcon fondo = new ImageIcon("src\\imagenes\\fondo.png");
//        Icon icon = new ImageIcon(fondo.getImage().getScaledInstance(jPanel1.getWidth(), jPanel1.getHeight(), Image.SCALE_DEFAULT));
//        jPanel1.setIcon(icon);
//        jPanel1.repaint();

    }
    

//    private void Inicio() {
//        
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem14 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SISTEMA DE ASISTENCIA DE PERSONAL EDUCATIVO");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe Script", 3, 12)); // NOI18N
        jLabel3.setText("Bienvenido al Sistema de Asistencia de Personal Educativo (S.A.P.E.)");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel1.setFocusable(false);
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setOpaque(true);

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("INSTITUTO \"GUTENBERG\"");

        jLabel4.setText("Incorporado a la Enseñanza oficial N º441"); // NOI18N

        jButton3.setText("Salir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(399, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(119, 119, 119)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap()))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel4});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(253, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(3, 3, 3)
                    .addComponent(jLabel3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel2)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel4)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jMenu1.setText("Personal");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem1.setText("Registrar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Registrar Huella");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Actividades");

        jMenuItem5.setText("Clases");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuItem6.setText("Reuniones");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenuItem4.setText("Extracurricular");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuItem7.setText("Otros");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuItem11.setText("Circular");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem11);

        jMenuBar1.add(jMenu3);

        jMenu2.setText("Consultar");

        jMenuItem3.setText("Personal");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem8.setText("Inasistencias");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);

        jMenuItem9.setText("Actividades");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem9);

        jMenuItem12.setText("Circulares");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem12);

        jMenuItem13.setText("Registro");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem13);

        jMenuItem10.setText("Auditoria");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem10);

        jMenuBar1.add(jMenu2);

        jMenu4.setText("Feriado");

        jMenuItem14.setText("Feriados");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem14);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        JFramePersonal vent2 = new JFramePersonal(Drive,adm,idsesion);
        this.hide();
        vent2.show();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        JFrameClase vent2 = new JFrameClase(Drive,adm,idsesion,tar);
        this.hide();
        vent2.show();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        JFrameReunion vent2 = new JFrameReunion(Drive,adm,idsesion,tar);
        this.hide();
        vent2.show();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        JFrameConsulta vent2 = new JFrameConsulta(Drive,adm,idsesion);
        this.hide();
        vent2.show();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        jDigitalPersona vent2 = new jDigitalPersona(Drive,adm,idsesion);
        this.hide();
        vent2.show();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        JFrameExtracurricular vent2 = new JFrameExtracurricular(Drive,adm,idsesion,tar);
        this.hide();
        vent2.show();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        JFrameotro vent2 = new JFrameotro(Drive,adm,idsesion,tar);
        this.hide();
        vent2.show();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        JFrameInasistencia vent2 = new JFrameInasistencia(Drive,adm,idsesion);
        this.hide();
        vent2.show();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        JFrameConsultaActividades vent2 = new JFrameConsultaActividades(Drive,adm,idsesion);
        this.hide();
        vent2.show();
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            int confirmado = JOptionPane.showConfirmDialog(null, "¿Desea salir?", "Cerrar sesión", JOptionPane.OK_CANCEL_OPTION);
            if (JOptionPane.OK_OPTION == confirmado) {
                JFrameInicio vp = new JFrameInicio();
                Registroacceso reg = adm.getRegistroacceso(idsesion);//Drive.getRegistroacceso(adm, vp.idsesion);
                if (reg.getIdRegistroacceso() != null) {
                    Date hoy = new Date();
                    String s = new SimpleDateFormat("HH:mm").format(hoy.getTime());
                    SimpleDateFormat fo = new SimpleDateFormat("HH:mm");
                    Date fin = fo.parse(s);
                    reg.setFin(fin);
                    reg.actualizarRegistroAcceso(reg);
                }
                this.dispose();
                vp.show();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            int confirmado = JOptionPane.showConfirmDialog(null, "¿Desea salir?", "Cerrar sesión", JOptionPane.OK_CANCEL_OPTION);
            if (JOptionPane.OK_OPTION == confirmado) {
                JFrameInicio vp = new JFrameInicio();
                Registroacceso reg = adm.getRegistroacceso(idsesion);//Drive.getRegistroacceso(adm, vp.idsesion);
                if (reg.getIdRegistroacceso() != null) {
                    Date hoy = new Date();
                    String s = new SimpleDateFormat("HH:mm").format(hoy.getTime());
                    SimpleDateFormat fo = new SimpleDateFormat("HH:mm");
                    Date fin = fo.parse(s);
                    reg.setFin(fin);
                    reg.actualizarRegistroAcceso(reg);
                }
                this.dispose();
                vp.show();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }//GEN-LAST:event_formWindowClosing

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        JFrameCircular vent2 = new JFrameCircular(Drive,adm,idsesion,cir);
        this.hide();
        vent2.show();
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        JFrameConsultaCircular vent2 = new JFrameConsultaCircular(Drive,adm,idsesion);
        this.hide();
        vent2.show();
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        JFrameRegistro vent2 = new JFrameRegistro(Drive,adm,idsesion);
        this.hide();
        vent2.show();
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        JFrameFeriados vent2 = new JFrameFeriados(Drive,adm,idsesion);
        this.hide();
        vent2.show();
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        JFrameAuditoria vent2 = new JFrameAuditoria(Drive,adm,idsesion);
        this.hide();
        vent2.show();
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new JFramePrincipal(Drive, adm).setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
