/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JFrameClase.java
 *
 * Created on 27/06/2012, 16:02:13
 */

package Presentacion;

import Clases.Agenda;
import Clases.AgendaId;
import Clases.Ano;
import Clases.Articulo;
import Clases.Auditoria;
import Clases.Controlador;
import Clases.Dia;
import Clases.Establecimiento;
import Clases.Franco;
import Clases.Iniciofin;
import Clases.Mes;
import Clases.Personal;
import Clases.Revista;
import Clases.Tarea;
import Clases.Tareaclase;
import Clases.TareaclaseId;
import Clases.Tipodoc;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
/**
 *
 * @author fer
 */
public class JFrameClase extends javax.swing.JFrame {
     Controlador Drive;
     Personal adm;
     int idsesion;
    StringBuffer buffer= new StringBuffer();
    List lista = new ArrayList();
    Personal per=new Personal();
    Tarea tar=new Tarea();
    boolean cambio=false;
    Date mayor=new Date();
    Date menor=new Date();
    List diass=new ArrayList();
    /** Creates new form JFrameClase */
    public JFrameClase(Controlador unDrive, Personal admin,int id,Tarea tarr) {
        this.adm=admin;
        this.Drive=unDrive;
        this.idsesion=id;
        this.tar=tarr;
        initComponents();

        Drive.CargarComboSituacionRevista(jComboBox2);
        int[] anchos1 = {200,85,65 ,65};
        for(int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos1[i]);
        }
        DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
        modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
        jTable1.getColumnModel().getColumn(1).setCellRenderer(modelocentrar); 
        jTable1.getTableHeader().setDefaultRenderer(new HeaderRenderer(jTable1));
        ///ICONO EDITAR
        ImageIcon fot = new ImageIcon(getClass().getResource("/imagenes/image.jpg"));
        Icon icono1 = new ImageIcon(fot.getImage().getScaledInstance(jLabel18.getWidth(), jLabel18.getHeight(), Image.SCALE_DEFAULT));
        jLabel18.setIcon(icono1);
        jLabel18.repaint();
        ///ICONO ELIMINAR
        ImageIcon fott = new ImageIcon(getClass().getResource("/imagenes/eliminar.gif"));
        Icon icono2 = new ImageIcon(fott.getImage().getScaledInstance(jLabel19.getWidth(), jLabel19.getHeight(), Image.SCALE_DEFAULT));
        jLabel19.setIcon(icono2);
        jLabel19.repaint();
        ///Verificar si vengo desde principal o desde consultar tarea
        if(tar.getIdTarea()!=null){
            try{
            jTextField1.setText(tar.getNombre());
            jTextField1.setEnabled(false);
            jTextField3.setText(tar.getLugar());
            Tareaclase tarcla=tar.getTareaclases().iterator().next();
            jTextField2.setText(tarcla.getAula());
            jTextField4.setText(String.valueOf(tarcla.getNumero()));
            this.mayor=tar.ObtenerFechaMayor(new Date().getYear());
            if(mayor!=null){
                Calendar mmayor = Calendar.getInstance();
                mmayor.setTime(mayor);
                dateChooserCombo2.setSelectedDate(mmayor);
            }
            this.menor=tar.ObtenerFechaMenor(new Date().getYear());
            if(menor!=null){
                Calendar mmenor = Calendar.getInstance();
                mmenor.setTime(menor);                
                dateChooserCombo1.setSelectedDate(mmenor);
            }
            SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
            diass=tar.ObtenerListaDias();
            Iterator itt=diass.iterator();
            while(itt.hasNext()){
                Dia dd=(Dia) itt.next();
                Date aux=new Date();
                aux.setDate(dd.getDia());
                aux.setMonth(dd.getMes().getMes());
                aux.setYear(dd.getMes().getAno().getAno()-1900);
                int ii=aux.getDay();
                if(ii==1){
                    jCheckBox1.setSelected(true);
                    jFormattedTextField1.setValue(formateador.format(dd.getIniciofins().iterator().next().getInicio()));
                    jFormattedTextField2.setValue(formateador.format(dd.getIniciofins().iterator().next().getFin()));
                }else if(ii==2){
                    jCheckBox2.setSelected(true);
                    jFormattedTextField3.setValue(formateador.format(dd.getIniciofins().iterator().next().getInicio()));
                    jFormattedTextField4.setValue(formateador.format(dd.getIniciofins().iterator().next().getFin()));
                }else if(ii==3){
                    jCheckBox3.setSelected(true);
                    jFormattedTextField5.setValue(formateador.format(dd.getIniciofins().iterator().next().getInicio()));
                    jFormattedTextField6.setValue(formateador.format(dd.getIniciofins().iterator().next().getFin()));
                }else if(ii==4){
                    jCheckBox4.setSelected(true);
                    jFormattedTextField8.setValue(formateador.format(dd.getIniciofins().iterator().next().getInicio()));
                    jFormattedTextField7.setValue(formateador.format(dd.getIniciofins().iterator().next().getFin()));
                }else if(ii==5){
                    jCheckBox5.setSelected(true);
                    jFormattedTextField9.setValue(formateador.format(dd.getIniciofins().iterator().next().getInicio()));
                    jFormattedTextField10.setValue(formateador.format(dd.getIniciofins().iterator().next().getFin()));
                }else if(ii==6){
                    jCheckBox6.setSelected(true);
                    jFormattedTextField11.setValue(formateador.format(dd.getIniciofins().iterator().next().getInicio()));
                    jFormattedTextField12.setValue(formateador.format(dd.getIniciofins().iterator().next().getFin()));
                }
            }
            Drive.LimpiarTabla(jTable2);
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            Iterator it=tar.getAgendas().iterator();
            while(it.hasNext()){
                Agenda agg=(Agenda) it.next();
                Personal per=agg.getPersonal();
                Revista rev=agg.getRevista();
                Object[] fila = new Object[4];
                fila[0] = per;
                fila[1] = rev;
                model.addRow(fila);
            }
            jTable1.setModel(model);
            
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Error al cargar el formulario","Registrar Clase", JOptionPane.ERROR_MESSAGE);
            }
        }
        ImageIcon fott1 = new ImageIcon(getClass().getResource("/imagenes/no.png"));
        Icon icono3 = new ImageIcon(fott1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        jButton2.setIcon(icono3);
        ImageIcon fott2 = new ImageIcon(getClass().getResource("/imagenes/ok.png"));
        Icon icono4 = new ImageIcon(fott2.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        jButton1.setIcon(icono4);
        ImageIcon fott6 = new ImageIcon(getClass().getResource("/imagenes/Mas.png"));
        Icon icono6 = new ImageIcon(fott6.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        jButton3.setIcon(icono6);
        ImageIcon fott7 = new ImageIcon(getClass().getResource("/imagenes/Menos.png"));
        Icon icono7 = new ImageIcon(fott7.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        jButton4.setIcon(icono7);
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        jLabel11 = new javax.swing.JLabel();
        dateChooserCombo2 = new datechooser.beans.DateChooserCombo();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox();
        jTextField5 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jFormattedTextField3 = new javax.swing.JFormattedTextField();
        jFormattedTextField4 = new javax.swing.JFormattedTextField();
        jFormattedTextField5 = new javax.swing.JFormattedTextField();
        jFormattedTextField6 = new javax.swing.JFormattedTextField();
        jFormattedTextField7 = new javax.swing.JFormattedTextField();
        jFormattedTextField8 = new javax.swing.JFormattedTextField();
        jFormattedTextField9 = new javax.swing.JFormattedTextField();
        jFormattedTextField10 = new javax.swing.JFormattedTextField();
        jFormattedTextField11 = new javax.swing.JFormattedTextField();
        jFormattedTextField12 = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SISTEMA DE ASISTENCIA DE PERSONAL EDUCATIVO");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de la clase"));

        jLabel1.setText("Nombre:");

        jLabel3.setText("Hora inicio:");

        jLabel4.setText("Hora fin:");

        jLabel5.setText("Dias:");

        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Salir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jFormattedTextField1.setEditable(false);
        try {
            jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField1.setEnabled(false);
        jFormattedTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField1ActionPerformed(evt);
            }
        });
        jFormattedTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFormattedTextField1FocusLost(evt);
            }
        });

        jFormattedTextField2.setEditable(false);
        try {
            jFormattedTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField2.setEnabled(false);
        jFormattedTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField2ActionPerformed(evt);
            }
        });
        jFormattedTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFormattedTextField2FocusLost(evt);
            }
        });

        jLabel7.setText("hh:mm");

        jLabel8.setText("hh:mm");

        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setPreferredSize(new java.awt.Dimension(41, 23));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel2.setText("Lugar:");

        jTextField3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField3FocusLost(evt);
            }
        });
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField3KeyTyped(evt);
            }
        });

        jLabel10.setText("Desde:");

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

    jLabel11.setText("Hasta:");

    dateChooserCombo2.setCurrentView(new datechooser.view.appearance.AppearancesList("Grey",
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
dateChooserCombo2.setBehavior(datechooser.model.multiple.MultyModelBehavior.SELECT_SINGLE);
dateChooserCombo2.addSelectionChangedListener(new datechooser.events.SelectionChangedListener() {
    public void onSelectionChange(datechooser.events.SelectionChangedEvent evt) {
        dateChooserCombo2OnSelectionChange(evt);
    }
    });

    jTable1.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Profesor", "Sit. Revista"
        }
    ) {
        boolean[] canEdit = new boolean [] {
            false, false
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    jTable1.addComponentListener(new java.awt.event.ComponentAdapter() {
        public void componentResized(java.awt.event.ComponentEvent evt) {
            jTable1ComponentResized(evt);
        }
    });
    jTable1.addContainerListener(new java.awt.event.ContainerAdapter() {
        public void componentAdded(java.awt.event.ContainerEvent evt) {
            jTable1ComponentAdded(evt);
        }
    });
    jTable1.addAncestorListener(new javax.swing.event.AncestorListener() {
        public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
        }
        public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            jTable1AncestorAdded(evt);
        }
        public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
        }
    });
    jScrollPane2.setViewportView(jTable1);

    jLabel12.setText("Aula:");

    jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            jTextField2KeyTyped(evt);
        }
    });

    jLabel13.setText("Nº Aula:");

    jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            jTextField4KeyTyped(evt);
        }
    });

    jLabel14.setText("*");

    jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Seleccione el personal"));

    jLabel15.setText("Buscar por:");

    jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Apellido", "Nombre" }));

    jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            jTextField5KeyTyped(evt);
        }
    });

    jTable2.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Apellido", "DNI", "Sexo", "Estado civil"
        }
    ) {
        boolean[] canEdit = new boolean [] {
            false, false, false, false
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jTable2MouseClicked(evt);
        }
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            jTable2MouseEntered(evt);
        }
        public void mouseReleased(java.awt.event.MouseEvent evt) {
            jTable2MouseReleased(evt);
        }
    });
    jScrollPane3.setViewportView(jTable2);

    jLabel6.setText("Sit. Revista:");

    jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "   " }));
    jComboBox2.setSelectedIndex(-1);

    jLabel18.setText("N");
    jLabel18.setPreferredSize(new java.awt.Dimension(15, 15));
    jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jLabel18MouseClicked(evt);
        }
    });

    jLabel19.setText("E");
    jLabel19.setPreferredSize(new java.awt.Dimension(15, 15));
    jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jLabel19MouseClicked(evt);
        }
    });

    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
    jPanel5.setLayout(jPanel5Layout);
    jPanel5Layout.setHorizontalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel5Layout.createSequentialGroup()
            .addComponent(jLabel15)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jLabel6)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
        .addComponent(jScrollPane3)
    );
    jPanel5Layout.setVerticalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel5Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGap(18, 18, 18)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
    );

    jCheckBox1.setText("LUNES");
    jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            jCheckBox1ItemStateChanged(evt);
        }
    });

    jCheckBox2.setText("MARTES");
    jCheckBox2.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            jCheckBox2ItemStateChanged(evt);
        }
    });

    jCheckBox3.setText("MIERCOLES");
    jCheckBox3.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            jCheckBox3ItemStateChanged(evt);
        }
    });

    jCheckBox4.setText("JUEVES");
    jCheckBox4.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            jCheckBox4ItemStateChanged(evt);
        }
    });

    jCheckBox5.setText("VIERNES");
    jCheckBox5.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            jCheckBox5ItemStateChanged(evt);
        }
    });

    jCheckBox6.setText("SABADO");
    jCheckBox6.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            jCheckBox6ItemStateChanged(evt);
        }
    });

    jFormattedTextField3.setEditable(false);
    try {
        jFormattedTextField3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
    } catch (java.text.ParseException ex) {
        ex.printStackTrace();
    }
    jFormattedTextField3.setEnabled(false);
    jFormattedTextField3.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jFormattedTextField3ActionPerformed(evt);
        }
    });
    jFormattedTextField3.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusLost(java.awt.event.FocusEvent evt) {
            jFormattedTextField3FocusLost(evt);
        }
    });

    jFormattedTextField4.setEditable(false);
    try {
        jFormattedTextField4.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
    } catch (java.text.ParseException ex) {
        ex.printStackTrace();
    }
    jFormattedTextField4.setEnabled(false);
    jFormattedTextField4.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jFormattedTextField4ActionPerformed(evt);
        }
    });
    jFormattedTextField4.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusLost(java.awt.event.FocusEvent evt) {
            jFormattedTextField4FocusLost(evt);
        }
    });

    jFormattedTextField5.setEditable(false);
    try {
        jFormattedTextField5.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
    } catch (java.text.ParseException ex) {
        ex.printStackTrace();
    }
    jFormattedTextField5.setEnabled(false);
    jFormattedTextField5.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jFormattedTextField5ActionPerformed(evt);
        }
    });
    jFormattedTextField5.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusLost(java.awt.event.FocusEvent evt) {
            jFormattedTextField5FocusLost(evt);
        }
    });

    jFormattedTextField6.setEditable(false);
    try {
        jFormattedTextField6.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
    } catch (java.text.ParseException ex) {
        ex.printStackTrace();
    }
    jFormattedTextField6.setEnabled(false);
    jFormattedTextField6.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jFormattedTextField6ActionPerformed(evt);
        }
    });
    jFormattedTextField6.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusLost(java.awt.event.FocusEvent evt) {
            jFormattedTextField6FocusLost(evt);
        }
    });

    jFormattedTextField7.setEditable(false);
    try {
        jFormattedTextField7.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
    } catch (java.text.ParseException ex) {
        ex.printStackTrace();
    }
    jFormattedTextField7.setEnabled(false);
    jFormattedTextField7.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jFormattedTextField7ActionPerformed(evt);
        }
    });
    jFormattedTextField7.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusLost(java.awt.event.FocusEvent evt) {
            jFormattedTextField7FocusLost(evt);
        }
    });

    jFormattedTextField8.setEditable(false);
    try {
        jFormattedTextField8.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
    } catch (java.text.ParseException ex) {
        ex.printStackTrace();
    }
    jFormattedTextField8.setEnabled(false);
    jFormattedTextField8.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jFormattedTextField8ActionPerformed(evt);
        }
    });
    jFormattedTextField8.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusLost(java.awt.event.FocusEvent evt) {
            jFormattedTextField8FocusLost(evt);
        }
    });

    jFormattedTextField9.setEditable(false);
    try {
        jFormattedTextField9.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
    } catch (java.text.ParseException ex) {
        ex.printStackTrace();
    }
    jFormattedTextField9.setEnabled(false);
    jFormattedTextField9.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jFormattedTextField9ActionPerformed(evt);
        }
    });
    jFormattedTextField9.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusLost(java.awt.event.FocusEvent evt) {
            jFormattedTextField9FocusLost(evt);
        }
    });

    jFormattedTextField10.setEditable(false);
    try {
        jFormattedTextField10.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
    } catch (java.text.ParseException ex) {
        ex.printStackTrace();
    }
    jFormattedTextField10.setEnabled(false);
    jFormattedTextField10.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jFormattedTextField10ActionPerformed(evt);
        }
    });
    jFormattedTextField10.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusLost(java.awt.event.FocusEvent evt) {
            jFormattedTextField10FocusLost(evt);
        }
    });

    jFormattedTextField11.setEditable(false);
    try {
        jFormattedTextField11.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
    } catch (java.text.ParseException ex) {
        ex.printStackTrace();
    }
    jFormattedTextField11.setEnabled(false);
    jFormattedTextField11.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jFormattedTextField11ActionPerformed(evt);
        }
    });
    jFormattedTextField11.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusLost(java.awt.event.FocusEvent evt) {
            jFormattedTextField11FocusLost(evt);
        }
    });

    jFormattedTextField12.setEditable(false);
    try {
        jFormattedTextField12.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
    } catch (java.text.ParseException ex) {
        ex.printStackTrace();
    }
    jFormattedTextField12.setEnabled(false);
    jFormattedTextField12.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jFormattedTextField12ActionPerformed(evt);
        }
    });
    jFormattedTextField12.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusLost(java.awt.event.FocusEvent evt) {
            jFormattedTextField12FocusLost(evt);
        }
    });

    jLabel9.setText("hh:mm");

    jLabel16.setText("hh:mm");

    jLabel17.setText("Hora inicio:");

    jLabel20.setText("Hora fin:");

    jLabel25.setText("Son campos obligatorios");

    jLabel26.setText("*");

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2))
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField1)
                                    .addComponent(dateChooserCombo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jCheckBox1)
                                    .addComponent(jCheckBox2)
                                    .addComponent(jCheckBox3))
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFormattedTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel4)
                                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFormattedTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFormattedTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(54, 54, 54)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(dateChooserCombo2, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField3)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBox4)
                                    .addComponent(jCheckBox5)
                                    .addComponent(jCheckBox6))
                                .addGap(22, 22, 22)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jFormattedTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jFormattedTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jFormattedTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7))
                                        .addGap(11, 11, 11)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jFormattedTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jFormattedTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jFormattedTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel8)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel17)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel20)))))))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton3)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton3, jButton4});

    jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {dateChooserCombo1, jTextField1, jTextField2});

    jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2});

    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14)
                        .addComponent(jLabel1))
                    .addGap(17, 17, 17)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel10)
                        .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(dateChooserCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel2)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5)
                        .addComponent(jLabel4)
                        .addComponent(jLabel3)))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel20)
                        .addComponent(jLabel17))))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBox4))
                        .addComponent(jFormattedTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBox5))
                        .addComponent(jFormattedTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jFormattedTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jFormattedTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox6)))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBox1)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jFormattedTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jFormattedTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jCheckBox2))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jFormattedTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jCheckBox3))))))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel9)
                .addComponent(jLabel8)
                .addComponent(jLabel7)
                .addComponent(jLabel16))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jButton3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(jLabel25)
                .addComponent(jLabel26)
                .addComponent(jButton1)
                .addComponent(jButton2))
            .addContainerGap())
    );

    jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton3, jButton4});

    jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {dateChooserCombo1, dateChooserCombo2});

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        if (tar.getIdTarea() == null) {
            if (!jTextField1.getText().isEmpty() || !jTextField2.getText().isEmpty() || !jTextField3.getText().isEmpty() || !jTextField4.getText().isEmpty()) {
                int confirmado = JOptionPane.showConfirmDialog(null, "¿Desea cancelar la registración de la clase?", "Registrar clase", JOptionPane.YES_NO_OPTION);
                if (JOptionPane.OK_OPTION == confirmado) {
                    Frame vp = new JFramePrincipal(Drive, adm, idsesion);
                    this.dispose();
                    vp.show();
                }
            } else {
                Frame vp = new JFramePrincipal(Drive, adm, idsesion);
                this.dispose();
                vp.show();
            }
        } else {
            if (!cambio == true) {
                int confirmado = JOptionPane.showConfirmDialog(null, "¿Desea cancelar la actualización de la clase?", "Actualizar clase", JOptionPane.YES_NO_OPTION);
                if (JOptionPane.OK_OPTION == confirmado) {
                    Frame vp = new JFrameConsultaActividades(Drive, adm, idsesion);
                    this.dispose();
                    vp.show();
                }
            } else {
                Frame vp = new JFrameConsultaActividades(Drive, adm, idsesion);
                this.dispose();
                vp.show();
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jFormattedTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField1ActionPerformed

    private void jFormattedTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            if (jTable1.getRowCount() != 0) {
                boolean verif=true;
                if (jCheckBox1.isSelected()) {if(jFormattedTextField1.getText().contains(" ") || jFormattedTextField2.getText().contains(" ")){verif=false;}}
                if (jCheckBox2.isSelected()) {if(jFormattedTextField3.getText().contains(" ") || jFormattedTextField4.getText().contains(" ")){verif=false;}}
                if (jCheckBox3.isSelected()) {if(jFormattedTextField5.getText().contains(" ") || jFormattedTextField6.getText().contains(" ")){verif=false;}}
                if (jCheckBox4.isSelected()) {if(jFormattedTextField7.getText().contains(" ") || jFormattedTextField8.getText().contains(" ")){verif=false;}}
                if (jCheckBox5.isSelected()) {if(jFormattedTextField9.getText().contains(" ") || jFormattedTextField10.getText().contains(" ")){verif=false;}}
                if (jCheckBox6.isSelected()) {if(jFormattedTextField11.getText().contains(" ") || jFormattedTextField12.getText().contains(" ")){verif=false;}}
                    
                if (!jTextField1.getText().isEmpty() && verif==true) {
                    jButton1.setEnabled(false);
                    jButton2.setEnabled(false);
                    jButton3.setEnabled(false);
                    jButton4.setEnabled(false);
                    
                    if (tar.getIdTarea() == null) {
                        // <editor-fold defaultstate="collapsed" desc="Guardar tarea nueva"> 
                        boolean band=true;
                        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
                        SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                        int c = 0;
                        while (jTable1.getRowCount() != c) {
                            Personal person = (Personal) modelo.getValueAt(c, 0);
                            // <editor-fold defaultstate="collapsed" desc="verificar">
                            Date inn = new Date();
                            Date fii = new Date();
                            HashMap items= new HashMap();
//                            List items = new ArrayList(6);
                            HashMap listini = new HashMap();
                            if (jCheckBox1.isSelected()) {
                                items.put(1,"LUNES");
                                inn = formateador.parse(jFormattedTextField1.getText());
                                fii = formateador.parse(jFormattedTextField2.getText());
                                Iniciofin aux = new Iniciofin();
                                aux.setInicio(inn);
                                aux.setFin(fii);
                                listini.put(1,aux);
                            }
                            if (jCheckBox2.isSelected()) {
                                items.put(2,"MARTES");
                                inn = formateador.parse(jFormattedTextField3.getText());
                                fii = formateador.parse(jFormattedTextField4.getText());
                                Iniciofin aux = new Iniciofin();
                                aux.setInicio(inn);
                                aux.setFin(fii);
                                listini.put(2,aux);
                            }
                            if (jCheckBox3.isSelected()) {
                                items.put(3,"MIERCOLES");
                                inn = formateador.parse(jFormattedTextField5.getText());
                                fii = formateador.parse(jFormattedTextField6.getText());
                                Iniciofin aux = new Iniciofin();
                                aux.setInicio(inn);
                                aux.setFin(fii);
                                listini.put(3,aux);
                            }
                            if (jCheckBox4.isSelected()) {
                                items.put(4,"JUEVES");
                                inn = formateador.parse(jFormattedTextField8.getText());
                                fii = formateador.parse(jFormattedTextField7.getText());
                                Iniciofin aux = new Iniciofin();
                                aux.setInicio(inn);
                                aux.setFin(fii);
                                listini.put(4,aux);
                            }
                            if (jCheckBox5.isSelected()) {
                                items.put(5,"VIERNES");
                                inn = formateador.parse(jFormattedTextField9.getText());
                                fii = formateador.parse(jFormattedTextField10.getText());
                                Iniciofin aux = new Iniciofin();
                                aux.setInicio(inn);
                                aux.setFin(fii);
                                listini.put(5,aux);
                            }
                            if (jCheckBox6.isSelected()) {
                                items.put(6,"SABADO");
                                inn = formateador.parse(jFormattedTextField11.getText());
                                fii = formateador.parse(jFormattedTextField12.getText());
                                Iniciofin aux = new Iniciofin();
                                aux.setInicio(inn);
                                aux.setFin(fii);
                                listini.put(6,aux);
                            }
                            Date inicioo = dateChooserCombo1.getSelectedDate().getTime();
                            Date finn = dateChooserCombo2.getSelectedDate().getTime();
                            // </editor-fold>
                            if (person.DisponibilidadClase(inicioo, finn, listini, items,0)) {
                                band=true;
                            }else{
                                band=false;
                                JOptionPane.showMessageDialog(null,"No existe disponibilidad", "Registrar clase",JOptionPane.ERROR_MESSAGE);
                                break;
                            }
                            c++;
                        }
                        if(band==true){
                            Tarea tarr=new Tarea();
                            tarr.setEstablecimiento(Drive.getPrimerEstablecimiento());
                            tarr.setNombre(jTextField1.getText().toUpperCase());
                            tarr.setLugar(jTextField3.getText().toUpperCase());
                            tarr.setComentario("CLASE");
                            tarr.setEstado(true);
                            int idtar=tarr.guardarTarea(tarr);                            
                            TareaclaseId id = new TareaclaseId();
                            id.setIdTarea(idtar);
                            int i = 0;
                            if (!jTextField4.getText().isEmpty()) {
                                i = Integer.parseInt(jTextField4.getText());
                            }
                            tarr.crearTareaclase(id, tarr, jTextField2.getText().toUpperCase(), i);

                            // <editor-fold defaultstate="collapsed" desc="Auditoria"> 
                            Auditoria audi= new Auditoria();
                            audi.setPersonalByIdAuditor(adm);
                            audi.setOperacion("Insertar");
                            audi.setFecha(new Date());
                            audi.setTarea(tarr);
                            audi.guardarAuditoria(audi);
                            // </editor-fold>
                            c = 0;
                            while (jTable1.getRowCount() != c) {
                                Personal person = (Personal) modelo.getValueAt(c, 0);
                                AgendaId idage = new AgendaId(person.getIdPersonal(), tarr.getIdTarea());
                                Revista rev = (Revista) modelo.getValueAt(c, 1);
                                Agenda age = new Agenda();
                                age.setId(idage);
                                age.setRevista(rev);
                                age.setComentario(null);
                                age.guardarAgenda(age);

                                Date inicio = dateChooserCombo1.getSelectedDate().getTime();
                                Date fin = dateChooserCombo2.getSelectedDate().getTime();

                                Ano anio = new Ano();
                                anio.setAgenda(age);
                                anio.setAno(inicio.getYear() + 1900);
                                anio.guardarAno(anio);

                                Date ot1 = inicio;
                                if (jCheckBox1.isSelected()) {
                                    while (ot1.getDay() != 1) {
                                        ot1 = Controlador.sumarFechasDias(ot1, 1);
                                    }
                                    Date otro1 = ot1;
                                    // <editor-fold defaultstate="collapsed" desc="Guardar meses y dias">
                                    while (otro1.compareTo(fin) <= 0) {
                                    Mes mes = anio.getMes(otro1.getMonth());
                                    if (mes == null) {
                                        mes = new Mes();
                                        mes.setAno(anio);
                                        mes.setMes(otro1.getMonth());
                                        mes.guardarMes(mes);
                                        int e = mes.getMes();
                                        while (otro1.getMonth() == e && otro1.compareTo(fin) <= 0) {
                                            Dia dia = mes.getDia(otro1.getDate());
                                            if (dia == null) {
                                                dia = new Dia();
                                                dia.setMes(mes);
                                                dia.setDia(otro1.getDate());
                                                dia.guardarDia(dia);
                                                int f = dia.getDia();
                                                while (otro1.getDate() == f && otro1.compareTo(fin) <= 0) {
                                                    formateador.setLenient(false);
                                                    Date hini = formateador.parse(jFormattedTextField1.getText());
                                                    Date hfin = formateador.parse(jFormattedTextField2.getText());
                                                    Iniciofin in = new Iniciofin();
                                                    in.setDia(dia);
                                                    in.setInicio(hini);
                                                    in.setEstadoInicio(false);
                                                    in.setFin(hfin);
                                                    in.setEstadoFin(false);
                                                    in.guardarIniciofin(in);
                                                    otro1 = Drive.sumarFechasDias(otro1, 7);
                                                }
                                            }
                                        }
                                    }
                                }
                                    // </editor-fold>
                                }
                                Date ot2 = inicio;
                                if (jCheckBox2.isSelected()) {
                                    while (ot2.getDay() != 2) {
                                        ot2 = Controlador.sumarFechasDias(ot2, 1);
                                    }
                                    Date otro2 = ot2;
                                    // <editor-fold defaultstate="collapsed" desc="Guardar meses y dias">
                                while (otro2.compareTo(fin) <= 0) {
                                    Mes mes = anio.getMes(otro2.getMonth());
                                    if (mes == null) {
                                        mes = new Mes();
                                        mes.setAno(anio);
                                        mes.setMes(otro2.getMonth());
                                        mes.guardarMes(mes);
                                        int e = mes.getMes();
                                        while (otro2.getMonth() == e && otro2.compareTo(fin) <= 0) {
                                            Dia dia = mes.getDia(otro2.getDate());
                                            if (dia == null) {
                                                dia = new Dia();
                                                dia.setMes(mes);
                                                dia.setDia(otro2.getDate());
                                                dia.guardarDia(dia);
                                                int f = dia.getDia();
                                                while (otro2.getDate() == f && otro2.compareTo(fin) <= 0) {
                                                    //SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                                                    formateador.setLenient(false);
                                                    Date hini = formateador.parse(jFormattedTextField3.getText());
                                                    Date hfin = formateador.parse(jFormattedTextField4.getText());
                                                    Iniciofin in = new Iniciofin();
                                                    in.setDia(dia);
                                                    in.setInicio(hini);
                                                    in.setEstadoInicio(false);
                                                    in.setFin(hfin);
                                                    in.setEstadoFin(false);
                                                    in.guardarIniciofin(in);
                                                    otro2 = Drive.sumarFechasDias(otro2, 7);
                                                }
                                            }
                                        }
                                    }
                                }
                                    // </editor-fold>
                                }
                                Date ot3 = inicio;
                                if (jCheckBox3.isSelected()) {
                                    while (ot3.getDay() != 3) {
                                        ot3 = Controlador.sumarFechasDias(ot3, 1);
                                    }
                                    Date otro3 = ot3;
                                    // <editor-fold defaultstate="collapsed" desc="Guardar meses y dias">
                                while (otro3.compareTo(fin) <= 0) {
                                    Mes mes = anio.getMes(otro3.getMonth());
                                    if (mes == null) {
                                        mes = new Mes();
                                        mes.setAno(anio);
                                        mes.setMes(otro3.getMonth());
                                        mes.guardarMes(mes);
                                        int e = mes.getMes();
                                        while (otro3.getMonth() == e && otro3.compareTo(fin) <= 0) {
                                            Dia dia = mes.getDia(otro3.getDate());
                                            if (dia == null) {
                                                dia = new Dia();
                                                dia.setMes(mes);
                                                dia.setDia(otro3.getDate());
                                                dia.guardarDia(dia);
                                                int f = dia.getDia();
                                                while (otro3.getDate() == f && otro3.compareTo(fin) <= 0) {
                                                    //SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                                                    formateador.setLenient(false);
                                                    Date hini = formateador.parse(jFormattedTextField5.getText());
                                                    Date hfin = formateador.parse(jFormattedTextField6.getText());
                                                    Iniciofin in = new Iniciofin();
                                                    in.setDia(dia);
                                                    in.setInicio(hini);
                                                    in.setEstadoInicio(false);
                                                    in.setFin(hfin);
                                                    in.setEstadoFin(false);
                                                    in.guardarIniciofin(in);
                                                    otro3 = Drive.sumarFechasDias(otro3, 7);
                                                }
                                            }
                                        }
                                    }
                                }
                                    // </editor-fold>
                                }
                                Date ot4 = inicio;
                                if (jCheckBox4.isSelected()) {
                                    while (ot4.getDay() != 4) {
                                        ot4 = Controlador.sumarFechasDias(ot4, 1);
                                    }
                                    Date otro4 = ot4;
                                    // <editor-fold defaultstate="collapsed" desc="Guardar meses y dias">
                                while (otro4.compareTo(fin) <= 0) {
                                    Mes mes = anio.getMes(otro4.getMonth());
                                    if (mes == null) {
                                        mes = new Mes();
                                        mes.setAno(anio);
                                        mes.setMes(otro4.getMonth());
                                        mes.guardarMes(mes);
                                        int e = mes.getMes();
                                        while (otro4.getMonth() == e && otro4.compareTo(fin) <= 0) {
                                            Dia dia = mes.getDia(otro4.getDate());
                                            if (dia == null) {
                                                dia = new Dia();
                                                dia.setMes(mes);
                                                dia.setDia(otro4.getDate());
                                                dia.guardarDia(dia);
                                                int f = dia.getDia();
                                                while (otro4.getDate() == f && otro4.compareTo(fin) <= 0) {
                                                    //SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                                                    formateador.setLenient(false);
                                                    Date hini = formateador.parse(jFormattedTextField8.getText());
                                                    Date hfin = formateador.parse(jFormattedTextField7.getText());
                                                    Iniciofin in = new Iniciofin();
                                                    in.setDia(dia);
                                                    in.setInicio(hini);
                                                    in.setEstadoInicio(false);
                                                    in.setFin(hfin);
                                                    in.setEstadoFin(false);
                                                    in.guardarIniciofin(in);
                                                    otro4 = Drive.sumarFechasDias(otro4, 7);
                                                }
                                            }
                                        }
                                    }
                                }
                                    // </editor-fold>
                                }
                                Date ot5 = inicio;
                                if (jCheckBox5.isSelected()) {
                                    while (ot5.getDay() != 5) {
                                        ot5 = Controlador.sumarFechasDias(ot5, 1);
                                    }
                                    Date otro5 = ot5;
                                    // <editor-fold defaultstate="collapsed" desc="Guardar meses y dias">
                                while (otro5.compareTo(fin) <= 0) {
                                    Mes mes = anio.getMes(otro5.getMonth());
                                    if (mes == null) {
                                        mes = new Mes();
                                        mes.setAno(anio);
                                        mes.setMes(otro5.getMonth());
                                        mes.guardarMes(mes);
                                        int e = mes.getMes();
                                        while (otro5.getMonth() == e && otro5.compareTo(fin) <= 0) {
                                            Dia dia = mes.getDia(otro5.getDate());
                                            if (dia == null) {
                                                dia = new Dia();
                                                dia.setMes(mes);
                                                dia.setDia(otro5.getDate());
                                                dia.guardarDia(dia);
                                                int f = dia.getDia();
                                                while (otro5.getDate() == f && otro5.compareTo(fin) <= 0) {
                                                    //SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                                                    formateador.setLenient(false);
                                                    Date hini = formateador.parse(jFormattedTextField9.getText());
                                                    Date hfin = formateador.parse(jFormattedTextField10.getText());
                                                    Iniciofin in = new Iniciofin();
                                                    in.setDia(dia);
                                                    in.setInicio(hini);
                                                    in.setEstadoInicio(false);
                                                    in.setFin(hfin);
                                                    in.setEstadoFin(false);
                                                    in.guardarIniciofin(in);
                                                    otro5 = Drive.sumarFechasDias(otro5, 7);
                                                }
                                            }
                                        }
                                    }
                                }
                                    // </editor-fold>
                                }
                                Date ot6 = inicio;
                                if (jCheckBox6.isSelected()) {
                                    while (ot6.getDay() != 6) {
                                        ot6 = Controlador.sumarFechasDias(ot6, 1);
                                    }
                                    Date otro6 = ot6;
                                    // <editor-fold defaultstate="collapsed" desc="Guardar meses y dias">
                                while (otro6.compareTo(fin) <= 0) {
                                    Mes mes = anio.getMes(otro6.getMonth());
                                    if (mes == null) {
                                        mes = new Mes();
                                        mes.setAno(anio);
                                        mes.setMes(otro6.getMonth());
                                        mes.guardarMes(mes);
                                        int e = mes.getMes();
                                        while (otro6.getMonth() == e && otro6.compareTo(fin) <= 0) {
                                            Dia dia = mes.getDia(otro6.getDate());
                                            if (dia == null) {
                                                dia = new Dia();
                                                dia.setMes(mes);
                                                dia.setDia(otro6.getDate());
                                                dia.guardarDia(dia);
                                                int f = dia.getDia();
                                                while (otro6.getDate() == f && otro6.compareTo(fin) <= 0) {
                                                    //SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                                                    formateador.setLenient(false);
                                                    Date hini = formateador.parse(jFormattedTextField11.getText());
                                                    Date hfin = formateador.parse(jFormattedTextField12.getText());
                                                    Iniciofin in = new Iniciofin();
                                                    in.setDia(dia);
                                                    in.setInicio(hini);
                                                    in.setEstadoInicio(false);
                                                    in.setFin(hfin);
                                                    in.setEstadoFin(false);
                                                    in.guardarIniciofin(in);
                                                    otro6 = Drive.sumarFechasDias(otro6, 7);
                                                }
                                            }
                                        }
                                    }
                                }
                                    // </editor-fold>
                                }
                                c++;
                            }
                            jTextField1.setText("");
                            jTextField2.setText("");
                            jTextField3.setText("");
                            jTextField4.setText("");
                            jFormattedTextField1.setText("");
                            jFormattedTextField2.setText("");
                            jFormattedTextField3.setText("");
                            jFormattedTextField4.setText("");
                            jFormattedTextField5.setText("");
                            jFormattedTextField6.setText("");
                            jFormattedTextField7.setText("");
                            jFormattedTextField8.setText("");
                            jFormattedTextField9.setText("");
                            jFormattedTextField10.setText("");
                            jFormattedTextField11.setText("");
                            jFormattedTextField12.setText("");
                            Drive.LimpiarTabla(jTable1);
                            lista.removeAll(lista);
                            String buscar=(String) jComboBox4.getSelectedItem();
                            Drive.CargarpersonalSimple(jTable2,buscar, buffer.toString().toUpperCase(),lista);
                            Drive=new Controlador();
                            jCheckBox1.setSelected(false);
                            jCheckBox2.setSelected(false);
                            jCheckBox3.setSelected(false);
                            jCheckBox4.setSelected(false);
                            jCheckBox5.setSelected(false);
                            jCheckBox6.setSelected(false);
                        }
                        // </editor-fold>
                    }else{
                        // <editor-fold defaultstate="collapsed" desc="Actualizar tarea"> 
                        boolean band2=false;
                        if (!tar.getLugar().equals(jTextField3.getText().toUpperCase())) {
                            tar.setLugar(jTextField3.getText().toUpperCase());
                            tar.ActualizarTarea(tar);
                            jTextField3.setText(tar.getLugar());
                            band2=true;
                        }
                        Tareaclase clase = tar.getTareaclases().iterator().next();
                        int i = 0;
                        if (!jTextField4.getText().isEmpty()) {
                            i = Integer.parseInt(jTextField4.getText());
                        }
                        if (!clase.getAula().equals(jTextField2.getText().toUpperCase()) || clase.getNumero() != i) {
                            clase.setAula(jTextField2.getText().toUpperCase());
                            clase.setNumero(i);
                            clase.actualizarTareaclase(clase);
                            jTextField2.setText(clase.getAula());
                            jTextField4.setText(String.valueOf(clase.getNumero()));
                            band2=true;
                        }
                        
                        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
                        int c = 0;
                        boolean bander=false;
                        int cont=tar.getAgendas().size();
                        while (jTable1.getRowCount() != c) {
                            Personal person = (Personal) modelo.getValueAt(c, 0);
                            Revista rev = (Revista) modelo.getValueAt(c, 1);
                            Iterator it = tar.getAgendas().iterator();
                            bander=false;
                            while (it.hasNext()) {
                                Agenda ag = (Agenda) it.next();
                                Personal pp = ag.getPersonal();
                                if (pp.getIdPersonal() == person.getIdPersonal() && ag.getRevista().getIdRevista() == rev.getIdRevista()) {
                                    bander=true;
                                    break;
                                }
                            }
                            if(bander==false){
                                cambio=true;
                            }
                            c++;
                        }
                        if(cont!=jTable1.getRowCount()){
                            cambio=true;
                        }
                        List day = tar.ObtenerListaDiass();
                        List sel = new ArrayList();
                        if (jCheckBox1.isSelected()) {sel.add(1);}
                        if (jCheckBox2.isSelected()) {sel.add(2);}
                        if (jCheckBox3.isSelected()) {sel.add(3);}
                        if (jCheckBox4.isSelected()) {sel.add(4);}
                        if (jCheckBox5.isSelected()) {sel.add(5);}
                        if (jCheckBox6.isSelected()) {sel.add(6);}
                        
                        if(!sel.containsAll(day)){
                            cambio=true;
                        }
                        if (cambio == true) {
                            c = 0;
                            boolean band=true;
                            SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                            while (jTable1.getRowCount() != c) {
                                Personal person = (Personal) modelo.getValueAt(c, 0);
                                // <editor-fold defaultstate="collapsed" desc="verificar">
                                Date inn = new Date();
                                Date fii = new Date();
                                HashMap items = new HashMap();
                                HashMap listini = new HashMap();
                                if (jCheckBox1.isSelected()) {
                                    items.put(1,"LUNES");
                                    inn = formateador.parse(jFormattedTextField1.getText());
                                    fii = formateador.parse(jFormattedTextField2.getText());
                                    Iniciofin aux = new Iniciofin();
                                    aux.setInicio(inn);
                                    aux.setFin(fii);
                                    listini.put(1,aux);
                                }
                                if (jCheckBox2.isSelected()) {
                                    items.put(2,"MARTES");
                                    inn = formateador.parse(jFormattedTextField3.getText());
                                    fii = formateador.parse(jFormattedTextField4.getText());
                                    Iniciofin aux = new Iniciofin();
                                    aux.setInicio(inn);
                                    aux.setFin(fii);
                                    listini.put(2,aux);
                                }
                                if (jCheckBox3.isSelected()) {
                                    items.put(3,"MIERCOLES");
                                    inn = formateador.parse(jFormattedTextField5.getText());
                                    fii = formateador.parse(jFormattedTextField6.getText());
                                    Iniciofin aux = new Iniciofin();
                                    aux.setInicio(inn);
                                    aux.setFin(fii);
                                    listini.put(3,aux);
                                }
                                if (jCheckBox4.isSelected()) {
                                    items.put(4,"JUEVES");
                                    inn = formateador.parse(jFormattedTextField8.getText());
                                    fii = formateador.parse(jFormattedTextField7.getText());
                                    Iniciofin aux = new Iniciofin();
                                    aux.setInicio(inn);
                                    aux.setFin(fii);
                                    listini.put(4,aux);
                                }
                                if (jCheckBox5.isSelected()) {
                                    items.put(5,"VIERNES");
                                    inn = formateador.parse(jFormattedTextField9.getText());
                                    fii = formateador.parse(jFormattedTextField10.getText());
                                    Iniciofin aux = new Iniciofin();
                                    aux.setInicio(inn);
                                    aux.setFin(fii);
                                    listini.put(5,aux);
                                }
                                if (jCheckBox6.isSelected()) {
                                    items.put(6,"SABADO");
                                    inn = formateador.parse(jFormattedTextField11.getText());
                                    fii = formateador.parse(jFormattedTextField12.getText());
                                    Iniciofin aux = new Iniciofin();
                                    aux.setInicio(inn);
                                    aux.setFin(fii);
                                    listini.put(6,aux);
                                }
                                Date inicioo = dateChooserCombo1.getSelectedDate().getTime();
                                Date finn = dateChooserCombo2.getSelectedDate().getTime();
                                // </editor-fold>
                                if (person.DisponibilidadClase(inicioo, finn, listini, items,tar.getIdTarea())) {
                                    band=true;
                                }else{
                                    band=false;
                                    JOptionPane.showMessageDialog(null,"No existe disponibilidad", "Actualizar clase",JOptionPane.ERROR_MESSAGE);
                                    break;
                                }
                                c++;
                            }
                            if(band==true){
                                band2=true;
                                tar.BorrarTodo();
                                Drive=new Controlador();
                                tar=(Tarea) Drive.PERSISTENCIA.getTarea(tar.getIdTarea()).iterator().next();
                                c = 0;
                                while (jTable1.getRowCount() != c) {
                                    Personal person = (Personal) modelo.getValueAt(c, 0);
                                    Revista rev = (Revista) modelo.getValueAt(c, 1);
                                    Agenda age=tar.ObtenerAgenda(person.getIdPersonal(),rev.getIdRevista());
                                    if(age.getId()==null){
                                        AgendaId idage = new AgendaId(person.getIdPersonal(), tar.getIdTarea());
                                        age = new Agenda();
                                        age.setId(idage);
                                        age.setRevista(rev);
                                        age.setComentario(null);
                                        age.guardarAgenda(age);
                                    }
                                    Date inicio = dateChooserCombo1.getSelectedDate().getTime();
                                    Date fin = dateChooserCombo2.getSelectedDate().getTime();

                                    Ano anio = new Ano();
                                    anio.setAgenda(age);
                                    anio.setAno(inicio.getYear() + 1900);
                                    anio.guardarAno(anio);
                                    Date ot1 = inicio;
                                    if (jCheckBox1.isSelected()) {
                                        while (ot1.getDay() != 1) {
                                            ot1 = Controlador.sumarFechasDias(ot1, 1);
                                        }
                                        Date otro1 = ot1;
                                        // <editor-fold defaultstate="collapsed" desc="Actualiza meses y dias">
                                    while (otro1.compareTo(fin) <= 0) {
                                        Mes mes = anio.getMes(otro1.getMonth());
                                        if (mes == null) {
                                            mes = new Mes();
                                            mes.setAno(anio);
                                            mes.setMes(otro1.getMonth());
                                            mes.guardarMes(mes);
                                            int e = mes.getMes();
                                            while (otro1.getMonth() == e && otro1.compareTo(fin) <= 0) {
                                                Dia dia = mes.getDia(otro1.getDate());
                                                if (dia == null) {
                                                    dia = new Dia();
                                                    dia.setMes(mes);
                                                    dia.setDia(otro1.getDate());
                                                    dia.guardarDia(dia);
                                                    int f = dia.getDia();
                                                    while (otro1.getDate() == f && otro1.compareTo(fin) <= 0) {
                                                        //SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                                                        formateador.setLenient(false);
                                                    Date hini = formateador.parse(jFormattedTextField1.getText());
                                                    Date hfin = formateador.parse(jFormattedTextField2.getText());
                                                    Iniciofin in = new Iniciofin();
                                                    in.setDia(dia);
                                                    in.setInicio(hini);
                                                    in.setEstadoInicio(false);
                                                    in.setFin(hfin);
                                                    in.setEstadoFin(false);
                                                    in.guardarIniciofin(in);
                                                        otro1 = Drive.sumarFechasDias(otro1, 7);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    // </editor-fold>
                                    }
                                    Date ot2 = inicio;
                                    if (jCheckBox2.isSelected()) {
                                        while (ot2.getDay() != 2) {
                                            ot2 = Controlador.sumarFechasDias(ot2, 1);
                                        }
                                        Date otro2 = ot2;
                                        // <editor-fold defaultstate="collapsed" desc="Actualiza meses y dias">
                                    while (otro2.compareTo(fin) <= 0) {
                                        Mes mes = anio.getMes(otro2.getMonth());
                                        if (mes == null) {
                                            mes = new Mes();
                                            mes.setAno(anio);
                                            mes.setMes(otro2.getMonth());
                                            mes.guardarMes(mes);
                                            int e = mes.getMes();
                                            while (otro2.getMonth() == e && otro2.compareTo(fin) <= 0) {
                                                Dia dia = mes.getDia(otro2.getDate());
                                                if (dia == null) {
                                                    dia = new Dia();
                                                    dia.setMes(mes);
                                                    dia.setDia(otro2.getDate());
                                                    dia.guardarDia(dia);
                                                    int f = dia.getDia();
                                                    while (otro2.getDate() == f && otro2.compareTo(fin) <= 0) {
                                                        //SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                                                        formateador.setLenient(false);
                                                    Date hini = formateador.parse(jFormattedTextField3.getText());
                                                    Date hfin = formateador.parse(jFormattedTextField4.getText());
                                                    Iniciofin in = new Iniciofin();
                                                    in.setDia(dia);
                                                    in.setInicio(hini);
                                                    in.setEstadoInicio(false);
                                                    in.setFin(hfin);
                                                    in.setEstadoFin(false);
                                                    in.guardarIniciofin(in);
                                                        otro2 = Drive.sumarFechasDias(otro2, 7);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    // </editor-fold>
                                    }
                                    Date ot3 = inicio;
                                    if (jCheckBox3.isSelected()) {
                                        while (ot3.getDay() != 3) {
                                            ot3 = Controlador.sumarFechasDias(ot3, 1);
                                        }
                                        Date otro3 = ot3;
                                        // <editor-fold defaultstate="collapsed" desc="Actualiza meses y dias">
                                    while (otro3.compareTo(fin) <= 0) {
                                        Mes mes = anio.getMes(otro3.getMonth());
                                        if (mes == null) {
                                            mes = new Mes();
                                            mes.setAno(anio);
                                            mes.setMes(otro3.getMonth());
                                            mes.guardarMes(mes);
                                            int e = mes.getMes();
                                            while (otro3.getMonth() == e && otro3.compareTo(fin) <= 0) {
                                                Dia dia = mes.getDia(otro3.getDate());
                                                if (dia == null) {
                                                    dia = new Dia();
                                                    dia.setMes(mes);
                                                    dia.setDia(otro3.getDate());
                                                    dia.guardarDia(dia);
                                                    int f = dia.getDia();
                                                    while (otro3.getDate() == f && otro3.compareTo(fin) <= 0) {
                                                        //SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                                                        formateador.setLenient(false);
                                                    Date hini = formateador.parse(jFormattedTextField5.getText());
                                                    Date hfin = formateador.parse(jFormattedTextField6.getText());
                                                    Iniciofin in = new Iniciofin();
                                                    in.setDia(dia);
                                                    in.setInicio(hini);
                                                    in.setEstadoInicio(false);
                                                    in.setFin(hfin);
                                                    in.setEstadoFin(false);
                                                    in.guardarIniciofin(in);
                                                        otro3 = Drive.sumarFechasDias(otro3, 7);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    // </editor-fold>
                                    }
                                    Date ot4 = inicio;
                                    if (jCheckBox4.isSelected()) {
                                        while (ot4.getDay() != 4) {
                                            ot4 = Controlador.sumarFechasDias(ot4, 1);
                                        }
                                        Date otro4 = ot4;
                                        // <editor-fold defaultstate="collapsed" desc="Actualiza meses y dias">
                                    while (otro4.compareTo(fin) <= 0) {
                                        Mes mes = anio.getMes(otro4.getMonth());
                                        if (mes == null) {
                                            mes = new Mes();
                                            mes.setAno(anio);
                                            mes.setMes(otro4.getMonth());
                                            mes.guardarMes(mes);
                                            int e = mes.getMes();
                                            while (otro4.getMonth() == e && otro4.compareTo(fin) <= 0) {
                                                Dia dia = mes.getDia(otro4.getDate());
                                                if (dia == null) {
                                                    dia = new Dia();
                                                    dia.setMes(mes);
                                                    dia.setDia(otro4.getDate());
                                                    dia.guardarDia(dia);
                                                    int f = dia.getDia();
                                                    while (otro4.getDate() == f && otro4.compareTo(fin) <= 0) {
                                                        //SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                                                        formateador.setLenient(false);
                                                    Date hini = formateador.parse(jFormattedTextField8.getText());
                                                    Date hfin = formateador.parse(jFormattedTextField7.getText());
                                                    Iniciofin in = new Iniciofin();
                                                    in.setDia(dia);
                                                    in.setInicio(hini);
                                                    in.setEstadoInicio(false);
                                                    in.setFin(hfin);
                                                    in.setEstadoFin(false);
                                                    in.guardarIniciofin(in);
                                                        otro4 = Drive.sumarFechasDias(otro4, 7);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    // </editor-fold>
                                    }
                                    Date ot5 = inicio;
                                    if (jCheckBox5.isSelected()) {
                                        while (ot5.getDay() != 5) {
                                            ot5 = Controlador.sumarFechasDias(ot5, 1);
                                        }
                                        Date otro5 = ot5;
                                        // <editor-fold defaultstate="collapsed" desc="Actualiza meses y dias">
                                    while (otro5.compareTo(fin) <= 0) {
                                        Mes mes = anio.getMes(otro5.getMonth());
                                        if (mes == null) {
                                            mes = new Mes();
                                            mes.setAno(anio);
                                            mes.setMes(otro5.getMonth());
                                            mes.guardarMes(mes);
                                            int e = mes.getMes();
                                            while (otro5.getMonth() == e && otro5.compareTo(fin) <= 0) {
                                                Dia dia = mes.getDia(otro5.getDate());
                                                if (dia == null) {
                                                    dia = new Dia();
                                                    dia.setMes(mes);
                                                    dia.setDia(otro5.getDate());
                                                    dia.guardarDia(dia);
                                                    int f = dia.getDia();
                                                    while (otro5.getDate() == f && otro5.compareTo(fin) <= 0) {
                                                        //SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                                                        formateador.setLenient(false);
                                                    Date hini = formateador.parse(jFormattedTextField9.getText());
                                                    Date hfin = formateador.parse(jFormattedTextField10.getText());
                                                    Iniciofin in = new Iniciofin();
                                                    in.setDia(dia);
                                                    in.setInicio(hini);
                                                    in.setEstadoInicio(false);
                                                    in.setFin(hfin);
                                                    in.setEstadoFin(false);
                                                    in.guardarIniciofin(in);
                                                        otro5 = Drive.sumarFechasDias(otro5, 7);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    // </editor-fold>
                                    }
                                    Date ot6 = inicio;
                                    if (jCheckBox6.isSelected()) {
                                        while (ot6.getDay() != 6) {
                                            ot6 = Controlador.sumarFechasDias(ot6, 1);
                                        }
                                        Date otro6 = ot6;
                                        // <editor-fold defaultstate="collapsed" desc="Actualiza meses y dias">
                                    while (otro6.compareTo(fin) <= 0) {
                                        Mes mes = anio.getMes(otro6.getMonth());
                                        if (mes == null) {
                                            mes = new Mes();
                                            mes.setAno(anio);
                                            mes.setMes(otro6.getMonth());
                                            mes.guardarMes(mes);
                                            int e = mes.getMes();
                                            while (otro6.getMonth() == e && otro6.compareTo(fin) <= 0) {
                                                Dia dia = mes.getDia(otro6.getDate());
                                                if (dia == null) {
                                                    dia = new Dia();
                                                    dia.setMes(mes);
                                                    dia.setDia(otro6.getDate());
                                                    dia.guardarDia(dia);
                                                    int f = dia.getDia();
                                                    while (otro6.getDate() == f && otro6.compareTo(fin) <= 0) {
                                                        //SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                                                        formateador.setLenient(false);
                                                    Date hini = formateador.parse(jFormattedTextField11.getText());
                                                    Date hfin = formateador.parse(jFormattedTextField12.getText());
                                                    Iniciofin in = new Iniciofin();
                                                    in.setDia(dia);
                                                    in.setInicio(hini);
                                                    in.setEstadoInicio(false);
                                                    in.setFin(hfin);
                                                    in.setEstadoFin(false);
                                                    in.guardarIniciofin(in);
                                                        otro6 = Drive.sumarFechasDias(otro6, 7);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    // </editor-fold>
                                    }
                                    c++;
                                }
                                Frame vp = new JFrameConsultaActividades(Drive, adm, idsesion);
                                this.dispose();
                                vp.show();
                            }else{
                                Frame vp = new JFrameConsultaActividades(Drive, adm, idsesion);
                                this.dispose();
                                vp.show();
                            }
                        }
                        if (band2==true){
                             // <editor-fold defaultstate="collapsed" desc="Auditoria"> 
                                Auditoria audi=new Auditoria();
                                audi.setPersonalByIdAuditor(adm);
                                audi.setOperacion("Actualizar");
                                audi.setFecha(new Date());
                                audi.setTarea(tar);
                                audi.guardarAuditoria(audi);
                                // </editor-fold>
                        }
                        // </editor-fold>
                    }
                    jButton1.setEnabled(true);
                    jButton2.setEnabled(true);
                    jButton3.setEnabled(true);
                    jButton4.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Los campos con '*' son obligatorios y no puede contener espacios en blanco en los horarios", "Registrar Clase", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Debe ingresar un personal", "Registrar Clase", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Registrar Clase", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            if (!jTextField1.getText().isEmpty()) {
                if (per.getIdPersonal() != null) {
                    DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
                    Revista rev = (Revista) jComboBox2.getSelectedItem();
                    if(rev!=null){
                    int c = 0;
                    boolean bandera = true;
                    while (jTable1.getRowCount() != c) {
                        Revista revista = (Revista) modelo.getValueAt(c, 1);
                        if (revista.getIdRevista() == rev.getIdRevista()) {
                            bandera = false;
                        }
                        c++;
                    }
                    if (bandera == true) {
                            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                            Object[] fila = new Object[4];
                            fila[0] = per;
                            fila[1] = rev;
                            model.addRow(fila);
                            jTable1.setModel(model);
                            Drive.LimpiarTabla(jTable2);
                            jTextField5.setText(buffer.toString());
                            per = new Personal();
                            String buscar=(String) jComboBox4.getSelectedItem();
                            Drive.CargarpersonalSimple(jTable2,buscar, buffer.toString().toUpperCase(),lista);
                    } else {JOptionPane.showMessageDialog(null, "La situación de revista es unica por personal", "Registrar Clase", JOptionPane.ERROR_MESSAGE);}
                    }else{JOptionPane.showMessageDialog(null, "Seleccione una situación de revista para el personal", "Registrar Clase", JOptionPane.ERROR_MESSAGE);}
                } else {JOptionPane.showMessageDialog(null, "Debe seleccionar un personal", "Registrar Clase", JOptionPane.ERROR_MESSAGE);}
            } else {JOptionPane.showMessageDialog(null, "Los campos con '*' son obligatorios y no puede contener espacios en blanco en los horarios", "Registrar Clase", JOptionPane.ERROR_MESSAGE);}
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Seleccione una situación de revista para el personal", "Registrar Clase", JOptionPane.ERROR_MESSAGE);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try{
            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            lista.removeAll(lista);
            int c=0;
            modelo.removeRow(jTable1.getSelectedRow());
            while(jTable1.getRowCount()!=c){
                Personal per=(Personal) modelo.getValueAt(c, 0);
               lista.add(per); 
               c++;
            }
            Drive.LimpiarTabla(jTable2);
            String es=buffer.toString();
            String buscar=(String) jComboBox4.getSelectedItem();
            Drive.CargarpersonalSimple(jTable2,buscar, es.toUpperCase(),lista);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Debe seleccionar un personal","Registrar Clase", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        JTextField salida = new JTextField();
        String cadSalida;
        salida.setText("");
        salida.setSize(25, 25);
        JOptionPane.showMessageDialog(null,salida, "Ingrese nombre de la nueva situación de revista", JOptionPane.INFORMATION_MESSAGE);
        if(!salida.getText().isEmpty()&&salida.getText().length()<=45){
            cadSalida = salida.getText().toUpperCase();
            Iterator it=Drive.PERSISTENCIA.getSitRevista().iterator();
            boolean w=false;
            while(it.hasNext()){
                Revista tip=(Revista) it.next();
                if(tip.getNombre().equals(cadSalida)){
                    JOptionPane.showMessageDialog(null, "La situación de revista ya existe","Registrar Situación de Revista", JOptionPane.ERROR_MESSAGE);
                    w=true;
                }
            }
            if(w==false){
                Revista tip= new Revista();
                tip.setNombre(cadSalida);
                tip.guardarRevista(tip);
                Drive.LimpiarCombo(jComboBox2);
                Drive.CargarComboSituacionRevista(jComboBox2);
                jComboBox2.setSelectedItem(tip);
            }
        }else {
            JOptionPane.showMessageDialog(null, "La situación de revista no puede estar vacio y puede contener hasta 45 caracteres", "Registrar Tipo de Documento", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jLabel18MouseClicked

    private void jTextField4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_jTextField4KeyTyped

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        try{
            int confirmado = JOptionPane.showConfirmDialog(null,"¿Desea eliminar la situación de revista?","Eliminar situación de revista",JOptionPane.YES_NO_OPTION);
            if (JOptionPane.OK_OPTION == confirmado){
                Revista rev=(Revista) jComboBox2.getSelectedItem();
                rev.eliminarRevista(rev);
                Drive.LimpiarCombo(jComboBox2);
                Drive.CargarComboSituacionRevista(jComboBox2);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error al eliminar una situación de revista","Eliminar situación de revista",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jLabel19MouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (tar.getIdTarea() == null) {
            if (!jTextField1.getText().isEmpty() || !jTextField2.getText().isEmpty() || !jTextField3.getText().isEmpty() || !jTextField4.getText().isEmpty()) {
                int confirmado = JOptionPane.showConfirmDialog(null, "¿Desea cancelar la registración de la clase?", "Registrar clase", JOptionPane.YES_NO_OPTION);
                if (JOptionPane.OK_OPTION == confirmado) {
                    Frame vp = new JFramePrincipal(Drive, adm, idsesion);
                    this.dispose();
                    vp.show();
                }
            } else {
                Frame vp = new JFramePrincipal(Drive, adm, idsesion);
                this.dispose();
                vp.show();
            }
        } else {
            if (!cambio == true) {
                int confirmado = JOptionPane.showConfirmDialog(null, "¿Desea cancelar la actualización de la clase?", "Actualizar clase", JOptionPane.YES_NO_OPTION);
                if (JOptionPane.OK_OPTION == confirmado) {
                    Frame vp = new JFrameConsultaActividades(Drive, adm, idsesion);
                    this.dispose();
                    vp.show();
                }
            } else {
                Frame vp = new JFrameConsultaActividades(Drive, adm, idsesion);
                this.dispose();
                vp.show();
            }
        }
    }//GEN-LAST:event_formWindowClosing

    private void dateChooserCombo1OnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_dateChooserCombo1OnSelectionChange
        try{
        Date inicio=dateChooserCombo1.getSelectedDate().getTime();
        Date fin=dateChooserCombo2.getSelectedDate().getTime();
        if(inicio.compareTo(fin)>0){
            JOptionPane.showMessageDialog(null,"La fecha de inicio debe ser menor que la fecha de fin","Registrar clase",JOptionPane.ERROR_MESSAGE);
            Calendar cal = Calendar.getInstance();
            dateChooserCombo1.setSelectedDate(cal);
            dateChooserCombo2.setSelectedDate(cal);
        }
        if(tar.getIdTarea()!=null){
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
            formateador.setLenient(false);
            String i=formateador.format(menor);
            Date mmenor=formateador.parse(i);
            String e=formateador.format(inicio);
            Date iinicio=formateador.parse(e);
            if(!mmenor.equals(iinicio)){
                cambio=true;
            }
        }
        }catch(Exception e){
            
        }
        
    }//GEN-LAST:event_dateChooserCombo1OnSelectionChange

    private void dateChooserCombo2OnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_dateChooserCombo2OnSelectionChange
        try{
        Date inicio=dateChooserCombo1.getSelectedDate().getTime();
        Date fin=dateChooserCombo2.getSelectedDate().getTime();
        if(inicio.compareTo(fin)>0){
            JOptionPane.showMessageDialog(null,"La fecha de inicio debe ser menor que la fecha de fin","Registrar clase",JOptionPane.ERROR_MESSAGE);
            Calendar cal = Calendar.getInstance();
            //dateChooserCombo1.setSelectedDate(cal);
            dateChooserCombo2.setSelectedDate(cal);
        }
        if(tar.getIdTarea()!=null){
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
            formateador.setLenient(false);
            String i=formateador.format(mayor);
            Date mmayor=formateador.parse(i);
            String e=formateador.format(fin);
            Date ffin=formateador.parse(e);
            if(!mmayor.equals(ffin)){
                cambio=true;
            }
        }
        }catch(Exception e){}
    }//GEN-LAST:event_dateChooserCombo2OnSelectionChange

    private void jTextField5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyTyped
        try{
            Drive.LimpiarTabla(jTable2);
            char car=evt.getKeyChar();
            if((car>='a' && car<='z') || (car>='A' && car<='Z')){
                buffer.append(evt.getKeyChar());
                String es=buffer.toString();
                String buscar=(String) jComboBox4.getSelectedItem();
                Drive.CargarpersonalSimple(jTable2,buscar, es.toUpperCase(),lista);
            }else if(car==(char)KeyEvent.VK_BACK_SPACE){
                int m= buffer.length();
                if(m!=0){
                    buffer.deleteCharAt(buffer.length()-1);
                }
                String es=buffer.toString();
                String buscar=(String) jComboBox4.getSelectedItem();
                Drive.CargarpersonalSimple(jTable2,buscar, es.toUpperCase(),lista);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.toString(),"Registrar clase", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jTextField5KeyTyped

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        //Establecimiento col= Drive.getPrimerEstablecimiento();
        jTable2.getModel();
        int fila = jTable2.rowAtPoint(evt.getPoint());
        if ((fila > -1)){
            per=(Personal) jTable2.getValueAt(fila,0);
        }

    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable2MouseEntered

    private void jTable2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseReleased
        //        if(evt.getClickCount()==2){
            //
            //        }                 // TODO add your handling code here:
    }//GEN-LAST:event_jTable2MouseReleased

    private void jTable1ComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_jTable1ComponentAdded
        
    }//GEN-LAST:event_jTable1ComponentAdded

    private void jTable1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTable1AncestorAdded
 
    }//GEN-LAST:event_jTable1AncestorAdded

    private void jTable1ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jTable1ComponentResized
        try{
        DefaultTableModel modelo = (DefaultTableModel)jTable1.getModel();
        int c=0;
        while(jTable1.getRowCount()!=c){
            Personal per=(Personal) modelo.getValueAt(c, 0);
            lista.add(per);
            c++;
        }
        Drive.LimpiarTabla(jTable2);
        String es=buffer.toString();
        String buscar=(String) jComboBox4.getSelectedItem();
        Drive.CargarpersonalSimple(jTable2,buscar, es.toUpperCase(),lista);
        if(modelo.getRowCount()>0){
            jCheckBox1.setEnabled(false);
            jCheckBox2.setEnabled(false);
            jCheckBox3.setEnabled(false);
            jCheckBox4.setEnabled(false);
            jCheckBox5.setEnabled(false);
            jCheckBox6.setEnabled(false);
            jFormattedTextField1.setEnabled(false);
            jFormattedTextField2.setEnabled(false);
            jFormattedTextField3.setEnabled(false);
            jFormattedTextField4.setEnabled(false);
            jFormattedTextField5.setEnabled(false);
            jFormattedTextField6.setEnabled(false);
            jFormattedTextField7.setEnabled(false);
            jFormattedTextField8.setEnabled(false);
            jFormattedTextField9.setEnabled(false);
            jFormattedTextField10.setEnabled(false);
            jFormattedTextField11.setEnabled(false);
            jFormattedTextField12.setEnabled(false);
        }else{
            jCheckBox1.setEnabled(true);
            jCheckBox2.setEnabled(true);
            jCheckBox3.setEnabled(true);
            jCheckBox4.setEnabled(true);
            jCheckBox5.setEnabled(true);
            jCheckBox6.setEnabled(true);
            jFormattedTextField1.setEnabled(true);
            jFormattedTextField2.setEnabled(true);
            jFormattedTextField3.setEnabled(true);
            jFormattedTextField4.setEnabled(true);
            jFormattedTextField5.setEnabled(true);
            jFormattedTextField6.setEnabled(true);
            jFormattedTextField7.setEnabled(true);
            jFormattedTextField8.setEnabled(true);
            jFormattedTextField9.setEnabled(true);
            jFormattedTextField10.setEnabled(true);
            jFormattedTextField11.setEnabled(true);
            jFormattedTextField12.setEnabled(true);
        }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error","Registrar Clase", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jTable1ComponentResized

    private void jFormattedTextField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextField2FocusLost
        try{
            SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
            Date inicio = formateador.parse(jFormattedTextField1.getText());
            Date fin = formateador.parse(jFormattedTextField2.getText());
            if(inicio.compareTo(fin)>=0){
                JOptionPane.showMessageDialog(null,"El horario de fin debe ser mayor al horario de inicio","Registrar Clase", JOptionPane.ERROR_MESSAGE);
                jFormattedTextField2.setText("");
            }
            if(tar.getIdTarea()!=null){
                Agenda age=tar.getAgendas().iterator().next();
                Dia d=age.getDia2(mayor);
                Iniciofin ini = d.getIniciofins().iterator().next();
                String est=formateador.format(ini.getFin());
                Date aux=formateador.parse(est);
                if(!fin.equals(aux)){
                    cambio=true;
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error","Registrar Clase", JOptionPane.ERROR_MESSAGE);
        }
            
    }//GEN-LAST:event_jFormattedTextField2FocusLost

    private void jFormattedTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextField1FocusLost
        try {
            if (tar.getIdTarea() != null) {
                Agenda age = tar.getAgendas().iterator().next();
                Dia d = age.getDia2(mayor);
                Iniciofin ini = d.getIniciofins().iterator().next();
                SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                Date inicio = formateador.parse(jFormattedTextField1.getText());
                Date aux = formateador.parse(formateador.format(ini.getInicio()));
                if (!inicio.equals(aux)) {
                    cambio = true;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese correctamente la hora", "Registrar Clase", JOptionPane.ERROR_MESSAGE);
            jFormattedTextField1.setText("");
        }
    }//GEN-LAST:event_jFormattedTextField1FocusLost

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        if(jTextField1.getText().length()==20) evt.consume();
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped
        if(jTextField3.getText().length()==45) evt.consume();
    }//GEN-LAST:event_jTextField3KeyTyped

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
        if(jTextField2.getText().length()==20) evt.consume();
    }//GEN-LAST:event_jTextField2KeyTyped

    private void jFormattedTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField3ActionPerformed

    private void jFormattedTextField3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextField3FocusLost
        try {
            if (tar.getIdTarea() != null) {
                Agenda age = tar.getAgendas().iterator().next();
                Dia d = age.getDia2(mayor);
                Iniciofin ini = d.getIniciofins().iterator().next();
                SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                Date inicio = formateador.parse(jFormattedTextField3.getText());
                Date aux = formateador.parse(formateador.format(ini.getInicio()));
                if (!inicio.equals(aux)) {
                    cambio = true;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese correctamente la hora", "Registrar Clase", JOptionPane.ERROR_MESSAGE);
            jFormattedTextField1.setText("");
        }
    }//GEN-LAST:event_jFormattedTextField3FocusLost

    private void jFormattedTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField4ActionPerformed

    private void jFormattedTextField4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextField4FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField4FocusLost

    private void jFormattedTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField5ActionPerformed

    private void jFormattedTextField5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextField5FocusLost
        try {
            if (tar.getIdTarea() != null) {
                Agenda age = tar.getAgendas().iterator().next();
                Dia d = age.getDia2(mayor);
                Iniciofin ini = d.getIniciofins().iterator().next();
                SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                Date inicio = formateador.parse(jFormattedTextField5.getText());
                Date aux = formateador.parse(formateador.format(ini.getInicio()));
                if (!inicio.equals(aux)) {
                    cambio = true;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese correctamente la hora", "Registrar Clase", JOptionPane.ERROR_MESSAGE);
            jFormattedTextField1.setText("");
        }
    }//GEN-LAST:event_jFormattedTextField5FocusLost

    private void jFormattedTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField6ActionPerformed

    private void jFormattedTextField6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextField6FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField6FocusLost

    private void jFormattedTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField7ActionPerformed

    private void jFormattedTextField7FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextField7FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField7FocusLost

    private void jFormattedTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField8ActionPerformed

    private void jFormattedTextField8FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextField8FocusLost
        try {
            if (tar.getIdTarea() != null) {
                Agenda age = tar.getAgendas().iterator().next();
                Dia d = age.getDia2(mayor);
                Iniciofin ini = d.getIniciofins().iterator().next();
                SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                Date inicio = formateador.parse(jFormattedTextField8.getText());
                Date aux = formateador.parse(formateador.format(ini.getInicio()));
                if (!inicio.equals(aux)) {
                    cambio = true;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese correctamente la hora", "Registrar Clase", JOptionPane.ERROR_MESSAGE);
            jFormattedTextField1.setText("");
        }
    }//GEN-LAST:event_jFormattedTextField8FocusLost

    private void jFormattedTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField9ActionPerformed

    private void jFormattedTextField9FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextField9FocusLost
        try {
            if (tar.getIdTarea() != null) {
                Agenda age = tar.getAgendas().iterator().next();
                Dia d = age.getDia2(mayor);
                Iniciofin ini = d.getIniciofins().iterator().next();
                SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                Date inicio = formateador.parse(jFormattedTextField9.getText());
                Date aux = formateador.parse(formateador.format(ini.getInicio()));
                if (!inicio.equals(aux)) {
                    cambio = true;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese correctamente la hora", "Registrar Clase", JOptionPane.ERROR_MESSAGE);
            jFormattedTextField1.setText("");
        }
    }//GEN-LAST:event_jFormattedTextField9FocusLost

    private void jFormattedTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField10ActionPerformed

    private void jFormattedTextField10FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextField10FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField10FocusLost

    private void jFormattedTextField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField11ActionPerformed

    private void jFormattedTextField11FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextField11FocusLost
        try {
            if (tar.getIdTarea() != null) {
                Agenda age = tar.getAgendas().iterator().next();
                Dia d = age.getDia2(mayor);
                Iniciofin ini = d.getIniciofins().iterator().next();
                SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                Date inicio = formateador.parse(jFormattedTextField11.getText());
                Date aux = formateador.parse(formateador.format(ini.getInicio()));
                if (!inicio.equals(aux)) {
                    cambio = true;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese correctamente la hora", "Registrar Clase", JOptionPane.ERROR_MESSAGE);
            jFormattedTextField1.setText("");
        }
    }//GEN-LAST:event_jFormattedTextField11FocusLost

    private void jFormattedTextField12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField12ActionPerformed

    private void jFormattedTextField12FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextField12FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField12FocusLost

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
        if(jCheckBox1.isSelected()){
            jFormattedTextField1.setEnabled(true);
            jFormattedTextField2.setEnabled(true);
            jFormattedTextField1.setEditable(true);
            jFormattedTextField2.setEditable(true);
        }else{
            jFormattedTextField1.setEnabled(false);
            jFormattedTextField2.setEnabled(false);
            jFormattedTextField1.setEditable(true);
            jFormattedTextField2.setEditable(true);
        }
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void jCheckBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox2ItemStateChanged
        if(jCheckBox2.isSelected()){
            jFormattedTextField3.setEnabled(true);
            jFormattedTextField4.setEnabled(true);
            jFormattedTextField3.setEditable(true);
            jFormattedTextField4.setEditable(true);
        }else{
            jFormattedTextField3.setEnabled(false);
            jFormattedTextField4.setEnabled(false);
            jFormattedTextField3.setEditable(true);
            jFormattedTextField4.setEditable(true);
        }
    }//GEN-LAST:event_jCheckBox2ItemStateChanged

    private void jCheckBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox3ItemStateChanged
        if(jCheckBox3.isSelected()){
            jFormattedTextField5.setEnabled(true);
            jFormattedTextField6.setEnabled(true);
            jFormattedTextField5.setEditable(true);
            jFormattedTextField6.setEditable(true);
        }else{
            jFormattedTextField5.setEnabled(false);
            jFormattedTextField6.setEnabled(false);
            jFormattedTextField5.setEditable(true);
            jFormattedTextField6.setEditable(true);
        }
    }//GEN-LAST:event_jCheckBox3ItemStateChanged

    private void jCheckBox4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox4ItemStateChanged
        if(jCheckBox4.isSelected()){
            jFormattedTextField7.setEnabled(true);
            jFormattedTextField8.setEnabled(true);
            jFormattedTextField7.setEditable(true);
            jFormattedTextField8.setEditable(true);
        }else{
            jFormattedTextField7.setEnabled(false);
            jFormattedTextField8.setEnabled(false);
            jFormattedTextField7.setEditable(true);
            jFormattedTextField8.setEditable(true);
        }
    }//GEN-LAST:event_jCheckBox4ItemStateChanged

    private void jCheckBox5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox5ItemStateChanged
        if(jCheckBox5.isSelected()){
            jFormattedTextField10.setEnabled(true);
            jFormattedTextField9.setEnabled(true);
            jFormattedTextField10.setEditable(true);
            jFormattedTextField9.setEditable(true);
        }else{
            jFormattedTextField10.setEnabled(false);
            jFormattedTextField9.setEnabled(false);
            jFormattedTextField10.setEditable(true);
            jFormattedTextField9.setEditable(true);
        }
    }//GEN-LAST:event_jCheckBox5ItemStateChanged

    private void jCheckBox6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox6ItemStateChanged
        if(jCheckBox6.isSelected()){
            jFormattedTextField11.setEnabled(true);
            jFormattedTextField12.setEnabled(true);
            jFormattedTextField11.setEditable(true);
            jFormattedTextField12.setEditable(true);
        }else{
            jFormattedTextField11.setEnabled(false);
            jFormattedTextField12.setEnabled(false);
            jFormattedTextField11.setEditable(true);
            jFormattedTextField12.setEditable(true);
        }
    }//GEN-LAST:event_jCheckBox6ItemStateChanged

    private void jTextField3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField3FocusLost
        
    }//GEN-LAST:event_jTextField3FocusLost

    /**
    * @param args the command line arguments
    */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private datechooser.beans.DateChooserCombo dateChooserCombo2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField10;
    private javax.swing.JFormattedTextField jFormattedTextField11;
    private javax.swing.JFormattedTextField jFormattedTextField12;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JFormattedTextField jFormattedTextField3;
    private javax.swing.JFormattedTextField jFormattedTextField4;
    private javax.swing.JFormattedTextField jFormattedTextField5;
    private javax.swing.JFormattedTextField jFormattedTextField6;
    private javax.swing.JFormattedTextField jFormattedTextField7;
    private javax.swing.JFormattedTextField jFormattedTextField8;
    private javax.swing.JFormattedTextField jFormattedTextField9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables

}
