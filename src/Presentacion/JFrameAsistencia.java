/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JFrameAsistencia.java
 *
 * Created on 27/06/2012, 14:54:10
 */
package Presentacion;

import Clases.Agenda;
import Clases.Asistencia;
import Clases.Circular;
import Clases.Controlador;
import Clases.Dia;
import Clases.Establecimiento;
import Clases.Iniciofin;
import Clases.Personal;
import Clases.Registroacceso;
import Clases.Tarea;
import TareasProgramadas.ControladorTarea;
import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.event.DPFPDataAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.digitalpersona.onetouch.capture.event.DPFPErrorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPErrorEvent;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
import com.digitalpersona.onetouch.capture.event.DPFPSensorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPSensorEvent;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import com.digitalpersona.onetouch.verification.DPFPVerification;
import com.digitalpersona.onetouch.verification.DPFPVerificationResult;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JOptionPane;

/**
 *
 * @author fer
 */
public class JFrameAsistencia extends javax.swing.JFrame {

    public Controlador Drive;
    public ControladorTarea Drive2= new ControladorTarea();
    Establecimiento es=new Establecimiento();
    public Personal per;
    private DPFPCapture Lector = DPFPGlobal.getCaptureFactory().createCapture();
    private DPFPEnrollment Reclutador = DPFPGlobal.getEnrollmentFactory().createEnrollment();
    private DPFPVerification Verificador = DPFPGlobal.getVerificationFactory().createVerification();
    private DPFPTemplate template;
    public static String TEMPLATE_PROPERTY = "template";
    //public DPFPFeatureSet featuresinscripcion;
    public DPFPFeatureSet featuresverificacion;

    /**
     * Creates new form JFrameAsistencia
     */
    public JFrameAsistencia() {
        try{
            JFrame.setDefaultLookAndFeelDecorated( true );
            UIManager.setLookAndFeel( new com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel() );
        }catch( Exception e ){ e.printStackTrace(); }
        initComponents();
        Inicio();
    }
    private void Inicio() throws ArrayStoreException {
        Controlador auxDrive = new Controlador();
        try {
            if (auxDrive.getPrimerEstablecimiento()== null) {
                JOptionPane.showMessageDialog(null, "Debe ingresar Colegio","Error de Inicio",JOptionPane.ERROR_MESSAGE);
            } else {
                es=auxDrive.getPrimerEstablecimiento();
                Drive = auxDrive;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(),"Error de Inicio",JOptionPane.ERROR_MESSAGE);
        }
        if(es.getIdEstablecimiento()!=null){
            if(es.getImagen()!=null){
                ImageIcon fott = new ImageIcon(es.getImagen());
                Icon icono4 = new ImageIcon(fott.getImage().getScaledInstance(jLabel1.getWidth(), jLabel1.getHeight(), Image.SCALE_DEFAULT));
                jLabel1.setIcon(icono4);
                jLabel1.repaint();
            }
            jLabel4.setText(es.getLeyenda());
            jLabel2.setText("INSTITUTO "+es.getNombre());
        }
        ImageIcon fot = new ImageIcon(getClass().getResource("/imagenes/Lector1.gif"));
        Icon icono5 = new ImageIcon(fot.getImage().getScaledInstance(jLabel5.getWidth(), jLabel5.getHeight(), Image.SCALE_DEFAULT));
        jLabel5.setIcon(icono5);
        jLabel5.repaint();
        ImageIcon fott1 = new ImageIcon(getClass().getResource("/imagenes/Actividades.png"));
        Icon icono1 = new ImageIcon(fott1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        jButton1.setIcon(icono1);
        Iniciar();
	start();
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
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SISTEMA DE ASISTENCIA DE PERSONAL EDUCATIVO");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowDeactivated(java.awt.event.WindowEvent evt) {
                formWindowDeactivated(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel1.setFocusable(false);
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setOpaque(true);

        jButton1.setText("Ver Actividades");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe Script", 3, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Bienvenido al Sistema de Asistencia de Personal Educativo (S.A.P.E.)");

        jLabel4.setText("Incorporado a la Enseñanza oficial N º441"); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("INSTITUTO \"GUTENBERG\"");

        jLabel6.setText("Apoye su dedo en el lector:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(163, 163, 163))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(162, 162, 162))))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel4});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    // <editor-fold defaultstate="collapsed" desc="Funciones"> 
    protected void Iniciar(){
        Lector.addDataListener(new DPFPDataAdapter() {
            @Override public void dataAcquired(final DPFPDataEvent e){
                SwingUtilities.invokeLater(new Runnable() {public void run() {
//                        EnviarTexto("La Huella Digital ha sido Capturada"); 
                    ProcesarCaptura(e.getSample());
                }});}
        });
        
        Lector.addReaderStatusListener(new DPFPReaderStatusAdapter() {
            @Override public void readerConnected(final DPFPReaderStatusEvent e) {
                SwingUtilities.invokeLater(new Runnable() {    public void run() {
//                    System.err.println("El Sensor de Huella Digital esta Activado o Conectado");
                }});
            }
            @Override public void readerDisconnected(final DPFPReaderStatusEvent e) {
                SwingUtilities.invokeLater(new Runnable() {    public void run() {
//                    System.err.println("El Sensor de Huella Digital esta Desactivado o no Conecatado");
                }});
            }
        });

        Lector.addSensorListener(new DPFPSensorAdapter() {
            @Override public void fingerTouched(final DPFPSensorEvent e) {
                SwingUtilities.invokeLater(new Runnable() {    public void run() {
//                    System.err.println("El dedo ha sido colocado sobre el Lector de Huella");
                }});
            }
            @Override public void fingerGone(final DPFPSensorEvent e) {
                SwingUtilities.invokeLater(new Runnable() {    public void run() {
//                    System.err.println("El dedo ha sido quitado del Lector de Huella");
                }});
            }
        });

        Lector.addErrorListener(new DPFPErrorAdapter(){
            public void errorReader(final DPFPErrorEvent e){
                SwingUtilities.invokeLater(new Runnable() {  public void run() {
//                    System.err.println("Error: "+e.getError());
                }});
            }
        });

        Lector.addErrorListener(new DPFPErrorAdapter(){
            public void errorReader(final DPFPErrorEvent e){
                SwingUtilities.invokeLater(new Runnable() {  public void run() {
                    System.err.println("Error: "+e.getError());
                }});
            }
        });
    }
    
    public void ProcesarCaptura(DPFPSample sample) {
        featuresverificacion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);
        if (featuresverificacion != null) {
            try {
                System.out.println("Las Caracteristicas de la Huella han sido creada");
                Reclutador.addFeatures(featuresverificacion);

            } catch (/*DPFPImageQualityException*/Exception ex) {
                System.err.println("Error: " + ex.getMessage());
            } finally {
                try {
                    identificarHuella();
                } catch (IOException ex) {
                    Logger.getLogger(JFrameAsistencia.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public DPFPFeatureSet extraerCaracteristicas(DPFPSample sample, DPFPDataPurpose purpose) {
        DPFPFeatureExtraction extractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
        try {
            return extractor.createFeatureSet(sample, purpose);
        } catch (DPFPImageQualityException e) {
            return null;
        }
    }
    public  void start(){
        Lector.startCapture();
    }

    public  void stop(){
        Lector.stopCapture();
    }

    public DPFPTemplate getTemplate() {
        return template;
    }

    public void setTemplate(DPFPTemplate template) {
        DPFPTemplate old = this.template;
        this.template = template;
        firePropertyChange(TEMPLATE_PROPERTY, old, template);
    }
    
    
     // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Identidicadores"> 
    Timer t = new Timer(3000, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            setTemplate(null);
            stop();
            start();
            t.stop();
        }
    });
    
//    Timer tt = new Timer(60000, new ActionListener() {
//        public void actionPerformed(ActionEvent e) {
//            tt.stop();
//        }
//    });

    public void identificarHuella() throws IOException {
        try {
            Iterator<Personal> it = Drive.PERSISTENCIA.getPersonalesTrue(1).iterator();
            while (it.hasNext()) {
                Personal pp = it.next();
                byte templateBuffer[] = pp.getCodigo();
                DPFPTemplate referenceTemplate = DPFPGlobal.getTemplateFactory().createTemplate(templateBuffer);
                setTemplate(referenceTemplate);
                DPFPVerificationResult result = Verificador.verify(featuresverificacion, getTemplate());
                if (result.isVerified()) {
//                    if (!tt.isRunning()) {
                        per = pp;
                        ////Guardar registro
                        Calendar ca = Calendar.getInstance();
                        Date cal = ca.getTime();
                        SimpleDateFormat forma = new SimpleDateFormat("HH:mm");
                        Date hora = forma.parse(forma.format(cal));
                        boolean existe = false;
                        java.sql.Date sqldate = new java.sql.Date(cal.getTime());
                        Iterator iit = Drive.PERSISTENCIA.getRegistroaccesoss(sqldate,per.getIdPersonal()).iterator();
                        while (iit.hasNext()) {
                            Registroacceso reg = (Registroacceso) iit.next();
//                            if (reg.getFecha().getYear() == cal.getYear() && reg.getFecha().getMonth() == cal.getMonth() && reg.getFecha().getDate() == cal.getDate()) {
                                    if (reg.getInicio() != null && reg.getFin() == null) {
                                        if(per.VerificarRegistro(reg)){
                                            reg.setFin(hora);
                                            reg.actualizarRegistroAcceso(reg);
                                            String s = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(cal.getTime());
                                            String tex="Que tenga buen dia " + pp.toString()+ "\nHora: "+ s;
                                            JOptionPane.showMessageDialog(null, tex, "Registrar salida", JOptionPane.INFORMATION_MESSAGE);
                                            
                                            Drive2.GuardarAsistencia(ca, per);
                                            existe = true;
                                            break;
                                        }else{
                                            JOptionPane.showMessageDialog(null, "Usted ya ha sido registrado " + per, "Registrar ingreso", JOptionPane.INFORMATION_MESSAGE);
                                            return;
                                        }
                                    }
                                
//                            }
                        }
                        if (existe == false) {
                            Registroacceso reg = new Registroacceso();
                            reg.setPersonal(pp);
                            reg.setFecha(cal);
                            reg.setInicio(hora);
                            reg.guardarRegistroAcceso(reg);
                            String s = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(cal.getTime());
                            String tex="BIENVENIDO " + pp.toString()+ "\nHora: "+ s;
                            JOptionPane.showMessageDialog(null, tex, "Registrar ingreso", JOptionPane.INFORMATION_MESSAGE);
                        }
//                        tt.start();
                        ////VERIFICAR CIRCULAR
                        Drive.VerificarCircular(per, cal);
                        setTemplate(null);
                        stop();
                        start();
                        return;
                }
            }
            JOptionPane.showMessageDialog(null, "La huella no coincide con ningun personal. Por favor dirigirse a secretaria", "Verificacion de Huella", JOptionPane.ERROR_MESSAGE);
            setTemplate(null);
            stop();
            start();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hubo un problema con la identificación de la huella. Por favor intentelo nuevamente", "Verificacion de Huella", JOptionPane.ERROR_MESSAGE);
            Reclutador.clear();
            stop();
            setTemplate(null);
            start();
        }
    }
    // </editor-fold>
    
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    }//GEN-LAST:event_formWindowClosing

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            jButton1.setEnabled(false);
            stop();
            JFrameAux frame=new JFrameAux(Drive);
            this.dispose();
            frame.show();
            
        } catch (Exception ex) {
            Logger.getLogger(JFrameAsistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowDeactivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowDeactivated
    }//GEN-LAST:event_formWindowDeactivated

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
    }//GEN-LAST:event_formWindowActivated
    /**
     * @param args the command line arguments
     */
     public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new JFrameAsistencia().setVisible(true);
            }
        });
    }
     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
