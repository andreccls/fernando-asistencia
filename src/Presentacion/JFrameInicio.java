/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;


import Clases.Controlador;
import Clases.Dia;
import Clases.Establecimiento;
import Clases.Iniciofin;
import Clases.Perfil;
import Clases.Personal;
import Clases.Registroacceso;
import TareasProgramadas.Programacion;
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
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;

/**
 *
 * @author fer
 */
public class JFrameInicio extends javax.swing.JFrame {

    /**
     * Creates new form JFrameInicio
     */
    public Personal adm=new Personal();
    public int idsesion=0;
    private DPFPCapture Lector = DPFPGlobal.getCaptureFactory().createCapture();
    private DPFPEnrollment Reclutador = DPFPGlobal.getEnrollmentFactory().createEnrollment();
    private DPFPVerification Verificador = DPFPGlobal.getVerificationFactory().createVerification();
    private DPFPTemplate template;
    public static String TEMPLATE_PROPERTY = "template";
    //public DPFPFeatureSet featuresinscripcion;
    public DPFPFeatureSet featuresverificacion;
    
    Controlador Drive;
    public JFrameInicio() {
        try{
            JFrame.setDefaultLookAndFeelDecorated( true );
            UIManager.setLookAndFeel( new com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel() );
        }catch( Exception e ){ e.printStackTrace(); }
        Controlador auxDrive = new Controlador();
        try {
//            Establecimiento col = (Establecimiento)
            if (auxDrive.getPrimerEstablecimiento()== null) {
                JOptionPane.showMessageDialog(null, "Debe ingresar Colegio");
            } else {
                auxDrive.getPrimerEstablecimiento();
                Drive = auxDrive;
            }
        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(null, ex.toString());
            Establecimiento col = new Establecimiento();
            col.setNombre("GUTENBERG");
            col.setCalle("ENTRE RIOS");
            col.setAltura(2267);
            col.guardarEstablecimiento(col);
            
            Perfil per1=new Perfil();
            per1.setDescripcion("ADMINISTRADOR");
            per1.setNivel(1);
            per1.guardarPerfil(per1);
            Perfil per2=new Perfil();
            per2.setDescripcion("AUXILIAR");
            per2.setNivel(2);
            per2.guardarPerfil(per2);
            Perfil per3=new Perfil();
            per3.setDescripcion("DIRECTOR");
            per3.setNivel(3);
            per3.guardarPerfil(per3);
            Perfil per4=new Perfil();
            per4.setDescripcion("PERSONAL");
            per4.setNivel(4);
            per4.guardarPerfil(per4);
//            Drive.PERSISTENCIA.AlterCodigo();
//            insert into perfil(descripcion,nivel) values ("ADMINISTRADOR",1);
//            insert into perfil(descripcion,nivel) values ("AUXILIAR",2);
//            insert into perfil(descripcion,nivel) values ("DIRECTOR",3);
//            insert into perfil(descripcion,nivel) values ("PERSONAL",4);
        }
        
        initComponents();
        
        ImageIcon fott = new ImageIcon("src\\imagenes\\gutenberg.png");
        Icon icono4 = new ImageIcon(fott.getImage().getScaledInstance(jLabel1.getWidth(), jLabel1.getHeight(), Image.SCALE_DEFAULT));
        jLabel1.setIcon(icono4);
        jLabel1.repaint();
        ImageIcon fot = new ImageIcon("src\\imagenes\\Lector1.gif");
        Icon icono5 = new ImageIcon(fot.getImage().getScaledInstance(jLabel5.getWidth(), jLabel5.getHeight(), Image.SCALE_DEFAULT));
        jLabel5.setIcon(icono5);
        jLabel5.repaint();
        new Programacion().iniciarTarea();
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SISTEMA DE ASISTENCIA DE PERSONAL EDUCATIVO");
        addWindowListener(new java.awt.event.WindowAdapter() {
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
                .addContainerGap(11, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void formWindowDeactivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowDeactivated
        stop();
    }//GEN-LAST:event_formWindowDeactivated

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

        
    }//GEN-LAST:event_formWindowClosing

    public boolean runn() {
        boolean band=true;
        Iterator it = Drive.PERSISTENCIA.getPersonales().iterator();
        if (!it.hasNext()) {
            JFramePrincipal vp = new JFramePrincipal(Drive, adm, idsesion);
//            setTemplate(null);
            stop();
            this.dispose();
            vp.show();
            band=false;
        }
        return band;
    }
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
    public void identificarHuella() throws IOException {
        try {
            boolean ba=runn();
            if(ba==true){
            Iterator<Personal> it = Drive.PERSISTENCIA.getPersonalesTrue(1).iterator();
            while (it.hasNext()) {
                Personal pp = it.next();
                byte templateBuffer[] = pp.getCodigo();
                DPFPTemplate referenceTemplate = DPFPGlobal.getTemplateFactory().createTemplate(templateBuffer);
                setTemplate(referenceTemplate);
                DPFPVerificationResult result = Verificador.verify(featuresverificacion, getTemplate());
                if (result.isVerified()) {
                    adm=pp;
                    Date hoy=new Date();
                    String s = new SimpleDateFormat("HH:mm").format(hoy.getTime());
                    SimpleDateFormat fo = new SimpleDateFormat("HH:mm");
                    Date inicio = fo.parse(s);
                    Registroacceso reg=new Registroacceso();
                    reg.setPersonal(pp);
                    reg.setFecha(hoy);
                    reg.setInicio(inicio);
                    idsesion=reg.guardarRegistroAcceso(reg);
                    JOptionPane.showMessageDialog(null, "BIENVENIDO " + pp, "Verificacion de Huella", JOptionPane.INFORMATION_MESSAGE);
                    JFramePrincipal vp= new JFramePrincipal(Drive, adm,idsesion);
                    this.dispose();
                    vp.show();
//                    setTemplate(null);
//                    stop();
//                    t.start();
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "La huella no coincide con ningun personal. Por favor consulte a la Secretaria", "Verificacion de Huella", JOptionPane.ERROR_MESSAGE);
            setTemplate(null);
            stop();
            start();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hubo un problema con la identificación de la huella. Por favor intentelo nuevamente", "Verificacion de Huella", JOptionPane.ERROR_MESSAGE);
            Reclutador.clear();
            stop();
            setTemplate(null);
            start();
        }
    }
    // </editor-fold>
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrameInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameInicio().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
