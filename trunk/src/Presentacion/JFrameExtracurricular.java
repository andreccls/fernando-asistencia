/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Clases.Agenda;
import Clases.AgendaId;
import Clases.Ano;
import Clases.Auditoria;
import Clases.Controlador;
import Clases.Departamento;
import Clases.Dia;
import Clases.Establecimiento;
import Clases.Franco;
import Clases.Iniciofin;
import Clases.Mes;
import Clases.Personal;
import Clases.Revista;
import Clases.Tarea;
import Clases.Tareaextracurricular;
import Clases.TareaextracurricularId;
import Clases.Tareaotro;
import Clases.TareareunionId;
import datechooser.beans.DateChooserCombo;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
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
    Controlador Drive;
    Personal adm;
    int idsesion;
    Tarea tar;
    StringBuffer buffer = new StringBuffer();
    List lista = new ArrayList();
    boolean cambio=false;
    Date mayor=new Date();
    Date menor=new Date();
    Date fecha=new Date();
    
    public JFrameExtracurricular(Controlador unDrive, Personal admin,int id,Tarea tarr) {
        this.Drive=unDrive;
        this.adm=admin;
        this.idsesion=id;
        this.tar=tarr;
        initComponents();
        
        int[] anchos1 = {85,200 ,65};
        for(int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos1[i]);
        }
        DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
        modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(modelocentrar); 
        jTable1.getTableHeader().setDefaultRenderer(new HeaderRenderer(jTable1));
        Drive.CargarComboDepartamento(jComboBox1);
        String buscar;
        Object aux= jComboBox1.getSelectedItem();
        if(aux.equals("TODOS")){
            buscar=(String) aux;
        }else{
            Departamento dep=(Departamento) aux;
            buscar=dep.getNombre();
        }
        Drive.CargarTablacheck(jTable1,buscar, buffer.toString().toUpperCase(),lista);
        ///Verificar si vengo desde principal o desde consultar tarea
        if(tar.getIdTarea()!=null){
            try {
                jTextField3.setText(tar.getNombre());
                jTextField3.setEnabled(false);
                jTextField4.setText(tar.getLugar());
                Tareaextracurricular tarot = tar.getTareaextracurriculars().iterator().next();
                menor=tarot.getDiaInicio();
                mayor=tarot.getDiaFin();
                Calendar ffechafin = Calendar.getInstance();
                ffechafin.setTime(tarot.getDiaFin());
                dateChooserCombo2.setSelectedDate(ffechafin);
                Calendar ffechaini = Calendar.getInstance();
                ffechaini.setTime(tarot.getDiaInicio());
                dateChooserCombo1.setSelectedDate(ffechaini);
                fecha = tar.ObtenerFechaMayor(new Date().getYear());
                SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                jFormattedTextField1.setValue(formateador.format(menor));
                jFormattedTextField2.setValue(formateador.format(mayor));
                Drive.LimpiarTabla(jTable1);
                Iterator it = tar.getAgendas().iterator();
                while (it.hasNext()) {
                    Agenda agg = (Agenda) it.next();
                    lista.add(agg.getPersonal());
                }
                Drive.CargarTablacheck(jTable1, buscar, buffer.toString().toUpperCase(), lista);
            } catch (Exception e) {
            }
        }
        ImageIcon fott2 = new ImageIcon(getClass().getResource("/imagenes/no.png"));
        Icon icono2 = new ImageIcon(fott2.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        jButton2.setIcon(icono2);
        ImageIcon fott3 = new ImageIcon(getClass().getResource("/imagenes/ok.png"));
        Icon icono3 = new ImageIcon(fott3.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        jButton1.setIcon(icono3);
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
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SISTEMA DE ASISTENCIA DE PERSONAL EDUCATIVO");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de la tarea extracurricular"));

        jLabel2.setText("Nombre:");

        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField3KeyTyped(evt);
            }
        });

        jLabel6.setText("Lugar:");

        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField4KeyTyped(evt);
            }
        });

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
    dateChooserCombo1.setBehavior(datechooser.model.multiple.MultyModelBehavior.SELECT_SINGLE);
    dateChooserCombo1.addSelectionChangedListener(new datechooser.events.SelectionChangedListener() {
        public void onSelectionChange(datechooser.events.SelectionChangedEvent evt) {
            dateChooserCombo1OnSelectionChange(evt);
        }
    });

    jLabel3.setText("Hora Inicio:");

    try {
        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
    } catch (java.text.ParseException ex) {
        ex.printStackTrace();
    }
    jFormattedTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusLost(java.awt.event.FocusEvent evt) {
            jFormattedTextField1FocusLost(evt);
        }
    });

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

    jButton2.setText("Salir");
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
    jFormattedTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusLost(java.awt.event.FocusEvent evt) {
            jFormattedTextField2FocusLost(evt);
        }
    });

    jLabel9.setText("hh:mm");

    jLabel10.setText("Obligatorio:");

    buttonGroup1.add(jRadioButton1);
    jRadioButton1.setSelected(true);
    jRadioButton1.setText("Si");
    jRadioButton1.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            jRadioButton1ItemStateChanged(evt);
        }
    });

    buttonGroup1.add(jRadioButton2);
    jRadioButton2.setText("No");
    jRadioButton2.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            jRadioButton2ItemStateChanged(evt);
        }
    });

    jLabel14.setText("*");

    jLabel15.setText("*");

    jLabel16.setText("*");

    jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            jTextField1KeyTyped(evt);
        }
    });

    jLabel11.setText("Departamento:");

    jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TODOS" }));
    jComboBox1.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            jComboBox1ItemStateChanged(evt);
        }
    });

    jLabel25.setText("Son campos obligatorios");

    jLabel26.setText("*");

    jCheckBox1.setText("Todos");
    jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            jCheckBox1ItemStateChanged(evt);
        }
    });
    jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jCheckBox1ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jCheckBox1)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addComponent(jScrollPane1)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addComponent(jLabel26)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel25)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1)
                    .addGap(18, 18, 18)
                    .addComponent(jButton2))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
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
                        .addComponent(jLabel1)
                        .addComponent(jLabel6)
                        .addComponent(jLabel5))
                    .addGap(27, 27, 27)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel9)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel16))
                        .addComponent(dateChooserCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField4)))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jLabel11)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel4)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jTextField1))))
    );

    jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {dateChooserCombo1, dateChooserCombo2, jTextField3, jTextField4});

    jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2});

    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel14)
                        .addComponent(jLabel6))
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
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jRadioButton1)
                        .addComponent(jLabel10))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jRadioButton2))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(dateChooserCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(5, 5, 5)
                            .addComponent(jLabel1)))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16)
                        .addComponent(jLabel5))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel9)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel4)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel11)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(11, 11, 11)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jCheckBox1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(jLabel25)
                .addComponent(jLabel26)
                .addComponent(jButton1)
                .addComponent(jButton2))
            .addContainerGap())
    );

    jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jTextField3, jTextField4});

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
        if (tar.getIdTarea() == null) {
            if (!jTextField3.getText().isEmpty() || !jTextField4.getText().isEmpty() || !jFormattedTextField1.getText().contains(" ") || !jFormattedTextField2.getText().contains(" ")) {
                int confirmado = JOptionPane.showConfirmDialog(null, "¿Desea cancelar la tarea extracurricular?", "Registrar Tarea extracurricular", JOptionPane.YES_NO_OPTION);
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
                int confirmado = JOptionPane.showConfirmDialog(null, "¿Desea cancelar la actualización de la tarea extracurricular?", "", JOptionPane.YES_NO_OPTION);
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            if (Drive.VerificarCheckTabla(jTable1)) {
                if (!jTextField3.getText().isEmpty() && !jFormattedTextField1.getText().contains(" ") && !jFormattedTextField2.getText().contains(" ")) {
                    if (tar.getIdTarea() == null) {
                        // <editor-fold defaultstate="collapsed" desc="Guardar tarea nueva">
                        SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                        formateador.setLenient(false);
                        Date inicio = formateador.parse(jFormattedTextField1.getText());
                        Date fin = formateador.parse(jFormattedTextField2.getText());
                        if (inicio.compareTo(fin) < 0) {
                            Date fecha_inicio = dateChooserCombo1.getSelectedDate().getTime();
                            Date fecha_fin = dateChooserCombo2.getSelectedDate().getTime();
                            inicio.setYear(fecha_inicio.getYear());
                            inicio.setMonth(fecha_inicio.getMonth());
                            inicio.setDate(fecha_inicio.getDate());
                            fin.setYear(fecha_fin.getYear());
                            fin.setMonth(fecha_fin.getMonth());
                            fin.setDate(fecha_fin.getDate());
                            String i = formateador.format(inicio);
                            String e = formateador.format(fin);
                            Date ini = new Date();
                            Date fi = new Date();
                            ini = formateador.parse(i);
                            fi = formateador.parse(e);
                            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                            int cc = 0;
                            boolean est = false;
                            boolean control;
                            while (jTable1.getRowCount() != cc) {
                                if (model.getValueAt(cc, 0).equals(true)) {
                                    Personal per = (Personal) model.getValueAt(cc, 1);
                                    control=true;
                                    int res=per.ExtracurricularVerificarDisponibilidad(fecha_inicio, ini, fi, fecha_fin,control);
                                    if (res==1||res==2) {
                                        est = true;
                                    }
                                }
                                cc++;
                            }
                            Tarea tarr = new Tarea();
                            if (est == true) {
                                tarr.setEstablecimiento(Drive.getPrimerEstablecimiento());
                                tarr.setNombre(jTextField3.getText().toUpperCase());
                                tarr.setLugar(jTextField4.getText().toUpperCase());
                                tarr.setComentario("EXTRACURRICULAR");
                                tarr.setEstado(true);
                                int idtar = tarr.guardarTarea(tarr);
                                
                                TareaextracurricularId id = new TareaextracurricularId();
                                id.setIdTarea(idtar);
                                tarr.crearTareaextracurricular(id, tarr, inicio, fin);
                                // <editor-fold defaultstate="collapsed" desc="Auditoria"> 
                                Auditoria audi=new Auditoria();
                                audi.setPersonalByIdAuditor(adm);
                                audi.setOperacion("Insertar");
                                audi.setFecha(new Date());
                                audi.setTarea(tarr);
                                audi.guardarAuditoria(audi);
                                // </editor-fold>
                                DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
                                int c = 0;
                                while (jTable1.getRowCount() != c) {
                                    if (modelo.getValueAt(c, 0).equals(true)) {
                                        Personal per = (Personal) modelo.getValueAt(c, 1);
                                        control=false;
                                        if (per.ExtracurricularVerificarDisponibilidad(fecha_inicio, ini, fi, fecha_fin,control)==1) {
                                            AgendaId ida = new AgendaId(per.getIdPersonal(), tarr.getIdTarea());
                                            Agenda age = new Agenda();
                                            age.setId(ida);
                                            age.setPersonal(per);
                                            age.setTarea(tarr);
                                            age.setComentario(null);
                                            age.guardarAgenda(age);
                                            ///Guarda el dia y hora de inicio
                                            Ano anio = new Ano();
                                            anio.setAgenda(age);
                                            anio.setAno(fecha_inicio.getYear() + 1900);
                                            anio.guardarAno(anio);
                                            Mes mes = new Mes();
                                            mes.setAno(anio);
                                            mes.setMes(fecha_inicio.getMonth());
                                            mes.guardarMes(mes);
                                            Dia dia = new Dia();
                                            dia.setMes(mes);
                                            dia.setDia(fecha_inicio.getDate());
                                            dia.guardarDia(dia);

                                            Iniciofin in = new Iniciofin();
                                            in.setDia(dia);
                                            in.setInicio(inicio);
                                            if (jRadioButton1.isSelected()) {
                                                in.setEstadoInicio(false);
                                            }
                                            in.guardarIniciofin(in);
                                            ///Guarda el dia y hora de fin
                                            Ano anioo = new Ano();
                                            Mes mess = new Mes();
                                            Dia diaa = new Dia();
                                            if (fecha_inicio.getYear() != fecha_fin.getYear() || fecha_inicio.getMonth() != fecha_fin.getMonth() || fecha_inicio.getDate() != fecha_fin.getDate()) {
                                                if (fecha_inicio.getYear() != fecha_fin.getYear()) {
                                                    anioo.setAgenda(age);
                                                    anioo.setAno(fecha_fin.getYear() + 1900);
                                                    anioo.guardarAno(anioo);
                                                    mess.setAno(anioo);
                                                    mess.setMes(fecha_fin.getMonth());
                                                    mess.guardarMes(mess);
                                                    diaa.setMes(mess);
                                                    diaa.setDia(fecha_fin.getDate());
                                                    diaa.guardarDia(diaa);
                                                    Iniciofin finn = new Iniciofin();
                                                    finn.setDia(diaa);
                                                    finn.setFin(fin);
                                                    finn.guardarIniciofin(finn);
                                                } else if (fecha_inicio.getMonth() != fecha_fin.getMonth()) {
                                                    mess.setAno(anio);
                                                    mess.setMes(fecha_fin.getMonth());
                                                    mess.guardarMes(mess);
                                                    diaa.setMes(mess);
                                                    diaa.setDia(fecha_fin.getDate());
                                                    diaa.guardarDia(diaa);
                                                    Iniciofin finn = new Iniciofin();
                                                    finn.setDia(diaa);
                                                    finn.setFin(fin);
                                                    finn.guardarIniciofin(finn);
                                                } else if (fecha_inicio.getDate() != fecha_fin.getDate()) {
                                                    diaa.setMes(mes);
                                                    diaa.setDia(fecha_fin.getDate());
                                                    diaa.guardarDia(diaa);
                                                    Iniciofin finn = new Iniciofin();
                                                    finn.setDia(diaa);
                                                    finn.setFin(fin);
                                                    finn.guardarIniciofin(finn);
                                                }
                                            } else {
                                                in.setFin(fin);
                                                in.guardarIniciofin(in);
                                            }
                                        }else if(per.ExtracurricularVerificarDisponibilidad(fecha_inicio, inicio, fin, fecha_fin,control)==2){
                                            AgendaId ida = new AgendaId(per.getIdPersonal(), tarr.getIdTarea());
                                            Agenda age = new Agenda();
                                            age.setId(ida);
                                            age.setPersonal(per);
                                            Revista rev = (Revista) Drive.PERSISTENCIA.getSitRevista().get(0);
                                            age.setRevista(rev);
                                            age.setTarea(tarr);
                                            age.setComentario(null);
                                            age.guardarAgenda(age);
                                            ///Guarda el dia y hora de inicio
                                            Ano anio = new Ano();
                                            anio.setAgenda(age);
                                            anio.setAno(fecha_inicio.getYear() + 1900);
                                            anio.guardarAno(anio);
                                            Mes mes = new Mes();
                                            mes.setAno(anio);
                                            mes.setMes(fecha_inicio.getMonth());
                                            mes.guardarMes(mes);
                                            Dia dia = new Dia();
                                            dia.setMes(mes);
                                            dia.setDia(fecha_inicio.getDate());
                                            dia.guardarDia(dia);

                                            Iniciofin in = new Iniciofin();
                                            in.setDia(dia);
                                            in.setInicio(inicio);
//                                            if (jRadioButton1.isSelected()) {
//                                                in.setEstadoInicio(false);
//                                            }
                                            in.guardarIniciofin(in);
                                            ///Guarda el dia y hora de fin
                                            Ano anioo = new Ano();
                                            Mes mess = new Mes();
                                            Dia diaa = new Dia();
                                            if (fecha_inicio.getYear() != fecha_fin.getYear() || fecha_inicio.getMonth() != fecha_fin.getMonth() || fecha_inicio.getDate() != fecha_fin.getDate()) {
                                                if (fecha_inicio.getYear() != fecha_fin.getYear()) {
                                                    anioo.setAgenda(age);
                                                    anioo.setAno(fecha_fin.getYear() + 1900);
                                                    anioo.guardarAno(anioo);
                                                    mess.setAno(anioo);
                                                    mess.setMes(fecha_fin.getMonth());
                                                    mess.guardarMes(mess);
                                                    diaa.setMes(mess);
                                                    diaa.setDia(fecha_fin.getDate());
                                                    diaa.guardarDia(diaa);
                                                    Iniciofin finn = new Iniciofin();
                                                    finn.setDia(diaa);
                                                    finn.setFin(fin);
                                                    finn.guardarIniciofin(finn);
                                                } else if (fecha_inicio.getMonth() != fecha_fin.getMonth()) {
                                                    mess.setAno(anio);
                                                    mess.setMes(fecha_fin.getMonth());
                                                    mess.guardarMes(mess);
                                                    diaa.setMes(mess);
                                                    diaa.setDia(fecha_fin.getDate());
                                                    diaa.guardarDia(diaa);
                                                    Iniciofin finn = new Iniciofin();
                                                    finn.setDia(diaa);
                                                    finn.setFin(fin);
                                                    finn.guardarIniciofin(finn);
                                                } else if (fecha_inicio.getDate() != fecha_fin.getDate()) {
                                                    diaa.setMes(mes);
                                                    diaa.setDia(fecha_fin.getDate());
                                                    diaa.guardarDia(diaa);
                                                    Iniciofin finn = new Iniciofin();
                                                    finn.setDia(diaa);
                                                    finn.setFin(fin);
                                                    finn.guardarIniciofin(finn);
                                                }
                                            } else {
                                                in.setFin(fin);
                                                in.guardarIniciofin(in);
                                            }
                                        }else {
                                            JOptionPane.showMessageDialog(null, "No existe disponibilidad de horario para "+ per+" porque existe una tarea extracurricular a ese horario", "Registrar Tarea Extracurricular", JOptionPane.ERROR_MESSAGE);
                                        }
                                    }
                                    c++;
                                }
                                jTextField3.setText("");
                                jTextField4.setText("");
                                Drive.LimpiarTabla(jTable1);
                                lista.removeAll(lista);
                                String buscar = (String) jComboBox1.getSelectedItem();
                                Drive.CargarTablacheck(jTable1, buscar, buffer.toString().toUpperCase(), lista);
                                Drive = new Controlador();
                            }
                            jFormattedTextField1.setText("");
                            jFormattedTextField2.setText("");

//                            jTextField3.setText("");
//                            jTextField4.setText("");
//                            Drive.LimpiarTabla(jTable1);
//                            lista.removeAll(lista);
//                            String buscar = (String) jComboBox1.getSelectedItem();
//                            Drive.CargarTablacheck(jTable1, buscar, buffer.toString().toUpperCase(), lista);
//                            Drive = new Controlador();
                        }
                        // </editor-fold>
                    } else {
                        // <editor-fold defaultstate="collapsed" desc="Actualizar tarea">
                        boolean band2=false;
                        SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                        formateador.setLenient(false);
                        Date inicio = formateador.parse(jFormattedTextField1.getText());
                        Date fin = formateador.parse(jFormattedTextField2.getText());
                        if (inicio.compareTo(fin) < 0) {
                            Date fecha_inicio = dateChooserCombo1.getSelectedDate().getTime();
                            Date fecha_fin = dateChooserCombo2.getSelectedDate().getTime();
                            inicio.setYear(fecha_inicio.getYear());
                            inicio.setMonth(fecha_inicio.getMonth());
                            inicio.setDate(fecha_inicio.getDate());
                            fin.setYear(fecha_fin.getYear());
                            fin.setMonth(fecha_fin.getMonth());
                            fin.setDate(fecha_fin.getDate());
                            String i = formateador.format(inicio);
                            String e = formateador.format(fin);
                            Date ini = new Date();
                            Date fi = new Date();
                            ini = formateador.parse(i);
                            fi = formateador.parse(e);
                            if (!tar.getLugar().equals(jTextField4.getText().toUpperCase())) {
                                tar.setLugar(jTextField4.getText().toUpperCase());
                                tar.ActualizarTarea(tar);
                                jTextField4.setText(tar.getLugar());
                                band2=true;
                            }
                            Tareaextracurricular tarext = tar.getTareaextracurriculars().iterator().next();
                            tarext.setDiaInicio(inicio);
                            tarext.setDiaFin(fin);
                            tarext.actualizarTareaextracurricular(tarext);
                            
                            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
                            int c = 0;
                            boolean bander = false;
                            int cont = tar.getAgendas().size();
                            int contper = 0;
                            while (jTable1.getRowCount() != c) {
                                boolean aux = (Boolean) modelo.getValueAt(c, 0);
                                if (aux == true) {
                                    contper++;
                                    Personal person = (Personal) modelo.getValueAt(c, 1);
                                    Iterator it = tar.getAgendas().iterator();
                                    bander = false;
                                    while (it.hasNext()) {
                                        Agenda ag = (Agenda) it.next();
                                        Personal pp = ag.getPersonal();
                                        if (pp.getIdPersonal() == person.getIdPersonal()) {
                                            bander = true;
                                            break;
                                        }
                                    }
                                    if (bander == false) {
                                        cambio = true;
                                    }
                                }
                                c++;
                            }
                            if (cont != contper) {
                                cambio = true;
                            }
                            if (cambio == true) {
                                band2=true;
                                tar.BorrarTodo();
                                Drive=new Controlador();
                                tar=(Tarea) Drive.PERSISTENCIA.getTarea(tar.getIdTarea()).iterator().next();
                                c = 0;
                                while (jTable1.getRowCount() != c) {
                                    if (modelo.getValueAt(c, 0).equals(true)) {
                                        Personal per = (Personal) modelo.getValueAt(c, 1);
                                        boolean control=false;
                                        if (per.ExtracurricularVerificarDisponibilidad(fecha_inicio, ini, fi, fecha_fin,control)==1) {
                                            AgendaId ida = new AgendaId(per.getIdPersonal(), tar.getIdTarea());
                                            Agenda age = new Agenda();
                                            age.setId(ida);
                                            age.setPersonal(per);
                                            age.setTarea(tar);
                                            age.setComentario(null);
                                            age.guardarAgenda(age);
                                            ///Guarda el dia y hora de inicio
                                            Ano anio = new Ano();
                                            anio.setAgenda(age);
                                            anio.setAno(fecha_inicio.getYear() + 1900);
                                            anio.guardarAno(anio);
                                            Mes mes = new Mes();
                                            mes.setAno(anio);
                                            mes.setMes(fecha_inicio.getMonth());
                                            mes.guardarMes(mes);
                                            Dia dia = new Dia();
                                            dia.setMes(mes);
                                            dia.setDia(fecha_inicio.getDate());
                                            dia.guardarDia(dia);

                                            Iniciofin in = new Iniciofin();
                                            in.setDia(dia);
                                            in.setInicio(inicio);
                                            if (jRadioButton1.isSelected()) {
                                                in.setEstadoInicio(false);
                                            }
                                            in.guardarIniciofin(in);
                                            ///Guarda el dia y hora de fin
                                            Ano anioo = new Ano();
                                            Mes mess = new Mes();
                                            Dia diaa = new Dia();
                                            if (fecha_inicio.getYear() != fecha_fin.getYear() || fecha_inicio.getMonth() != fecha_fin.getMonth() || fecha_inicio.getDate() != fecha_fin.getDate()) {
                                                if (fecha_inicio.getYear() != fecha_fin.getYear()) {
                                                    anioo.setAgenda(age);
                                                    anioo.setAno(fecha_fin.getYear() + 1900);
                                                    anioo.guardarAno(anioo);
                                                    mess.setAno(anioo);
                                                    mess.setMes(fecha_fin.getMonth());
                                                    mess.guardarMes(mess);
                                                    diaa.setMes(mess);
                                                    diaa.setDia(fecha_fin.getDate());
                                                    diaa.guardarDia(diaa);
                                                    Iniciofin finn = new Iniciofin();
                                                    finn.setDia(diaa);
                                                    finn.setFin(fin);
                                                    finn.guardarIniciofin(finn);
                                                } else if (fecha_inicio.getMonth() != fecha_fin.getMonth()) {
                                                    mess.setAno(anio);
                                                    mess.setMes(fecha_fin.getMonth());
                                                    mess.guardarMes(mess);
                                                    diaa.setMes(mess);
                                                    diaa.setDia(fecha_fin.getDate());
                                                    diaa.guardarDia(diaa);
                                                    Iniciofin finn = new Iniciofin();
                                                    finn.setDia(diaa);
                                                    finn.setFin(fin);
                                                    finn.guardarIniciofin(finn);
                                                } else if (fecha_inicio.getDate() != fecha_fin.getDate()) {
                                                    diaa.setMes(mes);
                                                    diaa.setDia(fecha_fin.getDate());
                                                    diaa.guardarDia(diaa);
                                                    Iniciofin finn = new Iniciofin();
                                                    finn.setDia(diaa);
                                                    finn.setFin(fin);
                                                    finn.guardarIniciofin(finn);
                                                }
                                            } else {
                                                in.setFin(fin);
                                                in.guardarIniciofin(in);
                                            }
                                        } else if(per.ExtracurricularVerificarDisponibilidad(fecha_inicio, inicio, fin, fecha_fin,control)==2){
                                            AgendaId ida = new AgendaId(per.getIdPersonal(), tar.getIdTarea());
                                            Agenda age = new Agenda();
                                            age.setId(ida);
                                            age.setPersonal(per);
                                            age.setTarea(tar);
                                            age.setComentario(null);
                                            age.guardarAgenda(age);
                                            ///Guarda el dia y hora de inicio
                                            Ano anio = new Ano();
                                            anio.setAgenda(age);
                                            anio.setAno(fecha_inicio.getYear() + 1900);
                                            anio.guardarAno(anio);
                                            Mes mes = new Mes();
                                            mes.setAno(anio);
                                            mes.setMes(fecha_inicio.getMonth());
                                            mes.guardarMes(mes);
                                            Dia dia = new Dia();
                                            dia.setMes(mes);
                                            dia.setDia(fecha_inicio.getDate());
                                            dia.guardarDia(dia);

                                            Iniciofin in = new Iniciofin();
                                            in.setDia(dia);
                                            in.setInicio(inicio);
//                                            if (jRadioButton1.isSelected()) {
//                                                in.setEstadoInicio(false);
//                                            }
                                            in.guardarIniciofin(in);
                                            ///Guarda el dia y hora de fin
                                            Ano anioo = new Ano();
                                            Mes mess = new Mes();
                                            Dia diaa = new Dia();
                                            if (fecha_inicio.getYear() != fecha_fin.getYear() || fecha_inicio.getMonth() != fecha_fin.getMonth() || fecha_inicio.getDate() != fecha_fin.getDate()) {
                                                if (fecha_inicio.getYear() != fecha_fin.getYear()) {
                                                    anioo.setAgenda(age);
                                                    anioo.setAno(fecha_fin.getYear() + 1900);
                                                    anioo.guardarAno(anioo);
                                                    mess.setAno(anioo);
                                                    mess.setMes(fecha_fin.getMonth());
                                                    mess.guardarMes(mess);
                                                    diaa.setMes(mess);
                                                    diaa.setDia(fecha_fin.getDate());
                                                    diaa.guardarDia(diaa);
                                                    Iniciofin finn = new Iniciofin();
                                                    finn.setDia(diaa);
                                                    finn.setFin(fin);
                                                    finn.guardarIniciofin(finn);
                                                } else if (fecha_inicio.getMonth() != fecha_fin.getMonth()) {
                                                    mess.setAno(anio);
                                                    mess.setMes(fecha_fin.getMonth());
                                                    mess.guardarMes(mess);
                                                    diaa.setMes(mess);
                                                    diaa.setDia(fecha_fin.getDate());
                                                    diaa.guardarDia(diaa);
                                                    Iniciofin finn = new Iniciofin();
                                                    finn.setDia(diaa);
                                                    finn.setFin(fin);
                                                    finn.guardarIniciofin(finn);
                                                } else if (fecha_inicio.getDate() != fecha_fin.getDate()) {
                                                    diaa.setMes(mes);
                                                    diaa.setDia(fecha_fin.getDate());
                                                    diaa.guardarDia(diaa);
                                                    Iniciofin finn = new Iniciofin();
                                                    finn.setDia(diaa);
                                                    finn.setFin(fin);
                                                    finn.guardarIniciofin(finn);
                                                }
                                            } else {
                                                in.setFin(fin);
                                                in.guardarIniciofin(in);
                                            }
                                        }else {
                                            JOptionPane.showMessageDialog(null, "No existe disponibilidad de horario para: " + per.toString(), "Registrar Tarea extracurricular", JOptionPane.ERROR_MESSAGE);
                                        }

                                    }
                                    c++;
                                }
                            }
                            if(band2==true){
                                // <editor-fold defaultstate="collapsed" desc="Auditoria"> 
                                Auditoria audi=new Auditoria();
                                audi.setPersonalByIdAuditor(adm);
                                audi.setOperacion("Actualizar");
                                audi.setFecha(new Date());
                                audi.setTarea(tar);
                                audi.guardarAuditoria(audi);
                                // </editor-fold>
                            }
                        }
                        Frame vp = new JFrameConsultaActividades(Drive, adm, idsesion);
                        this.dispose();
                        vp.show();
                        // </editor-fold>
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Todos los campos con '*' son obligatorios y los horarios no pueden contener espacios en blanco", "Registrar Tarea extracurricular", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar al menos un personal", "Registrar Tarea extracurricular", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Registrar Tarea extracurricular", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (tar.getIdTarea() == null) {
            if (!jTextField3.getText().isEmpty() || !jTextField4.getText().isEmpty() || !jFormattedTextField1.getText().contains(" ") || !jFormattedTextField2.getText().contains(" ")) {
                int confirmado = JOptionPane.showConfirmDialog(null, "¿Desea cancelar la tarea extracurricular?", "Registrar Tarea extracurricular", JOptionPane.YES_NO_OPTION);
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
                int confirmado = JOptionPane.showConfirmDialog(null, "¿Desea cancelar la actualización de la tarea extracurricular?", "", JOptionPane.YES_NO_OPTION);
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

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        try{
        lista=Drive.ObtenerListaTabla(jTable1);
        Drive.LimpiarTabla(jTable1);
        char car=evt.getKeyChar();
            if((car>='a' && car<='z') || (car>='A' && car<='Z')){
                buffer.append(evt.getKeyChar());
                String es=buffer.toString();
                String buscar;
                Object aux= jComboBox1.getSelectedItem();
                if(aux.equals("TODOS")){
                    buscar=(String) aux;
                }else{
                    Departamento dep=(Departamento) aux;
                    buscar=dep.getNombre();
                }
                Drive.CargarTablacheck(jTable1,buscar, es.toUpperCase(),lista);
            }else if(car==(char)KeyEvent.VK_BACK_SPACE){
                int m= buffer.length();
                if(m!=0){
                    buffer.deleteCharAt(buffer.length()-1);
                }
                String es=buffer.toString();
                String buscar;
                Object aux= jComboBox1.getSelectedItem();
                if(aux.equals("TODOS")){
                    buscar=(String) aux;
                }else{
                    Departamento dep=(Departamento) aux;
                    buscar=dep.getNombre();
                }
                Drive.CargarTablacheck(jTable1,buscar, es.toUpperCase(),lista);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR","Registrar Tarea Extracurricular", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        lista=Drive.ObtenerListaTabla(jTable1);
        Drive.LimpiarTabla(jTable1);
        String buscar;
        Object aux= jComboBox1.getSelectedItem();
        if(aux.equals("TODOS")){
            buscar=(String) aux;
        }else{
            Departamento dep=(Departamento) aux;
            buscar=dep.getNombre();
        }
        Drive.CargarTablacheck(jTable1,buscar, buffer.toString().toUpperCase(),lista);
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jFormattedTextField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextField2FocusLost
        try{
            SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
            Date inicio = formateador.parse(jFormattedTextField1.getText());
            Date fin = formateador.parse(jFormattedTextField2.getText());
            SimpleDateFormat formateador2 = new SimpleDateFormat("dd/MM/yyyy");
            formateador.setLenient(false);
            String i=formateador2.format(dateChooserCombo1.getSelectedDate().getTime());
            String ii=formateador2.format(dateChooserCombo2.getSelectedDate().getTime());
            Date fini=formateador2.parse(i);
            Date ffin=formateador2.parse(ii);
            if(fini.compareTo(ffin)==0){
                if(inicio.compareTo(fin)>=0){
                    JOptionPane.showMessageDialog(null,"El horario de fin debe ser mayor al horario de inicio","Registrar Tarea extracurricular", JOptionPane.ERROR_MESSAGE);
                    jFormattedTextField2.setText("");
                }
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
            JOptionPane.showMessageDialog(null,"Error","Registrar Tarea extracurricular", JOptionPane.ERROR_MESSAGE);
        } 
    }//GEN-LAST:event_jFormattedTextField2FocusLost

    private void dateChooserCombo2OnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_dateChooserCombo2OnSelectionChange
        try{
        Date inicio=dateChooserCombo1.getSelectedDate().getTime();
        Date fin=dateChooserCombo2.getSelectedDate().getTime();
        if(inicio.compareTo(fin)>0){
            JOptionPane.showMessageDialog(null,"La fecha de inicio debe ser menor que la fecha de fin","Registrar Tarea extracurricular",JOptionPane.ERROR_MESSAGE);
            Calendar cal = Calendar.getInstance();
            dateChooserCombo2.setSelectedDate(cal);
            jFormattedTextField2.setText("");
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
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error","Registrar Tarea extracurricular",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_dateChooserCombo2OnSelectionChange

    private void dateChooserCombo1OnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_dateChooserCombo1OnSelectionChange
        try{
        Date inicio=dateChooserCombo1.getSelectedDate().getTime();
        Date fin=dateChooserCombo2.getSelectedDate().getTime();
        if(inicio.compareTo(fin)>0){
            JOptionPane.showMessageDialog(null,"La fecha de inicio debe ser menor que la fecha de fin","Registrar Tarea extracurricular",JOptionPane.ERROR_MESSAGE);
            Calendar cal = Calendar.getInstance();
            dateChooserCombo1.setSelectedDate(cal);
            dateChooserCombo2.setSelectedDate(cal);
            jFormattedTextField2.setText("");
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
            JOptionPane.showMessageDialog(null,"Error","Registrar Tarea extracurricular",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_dateChooserCombo1OnSelectionChange

    private void jFormattedTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextField1FocusLost
        try {
            if (tar.getIdTarea() != null) {
                Agenda age = tar.getAgendas().iterator().next();
                Dia d = age.getDia2(menor);
                Iniciofin ini = d.getIniciofins().iterator().next();
                SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                Date inicio = formateador.parse(jFormattedTextField1.getText());
                String est = formateador.format(ini.getInicio());
                Date aux = formateador.parse(est);
                if (!inicio.equals(aux)) {
                    cambio = true;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese correctamente la hora", "Registrar Tarea extracurricular", JOptionPane.ERROR_MESSAGE);
            jFormattedTextField1.setText("");
        }
    }//GEN-LAST:event_jFormattedTextField1FocusLost

    private void jRadioButton1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton1ItemStateChanged
        if(tar.getIdTarea()!=null){
            Agenda age=tar.getAgendas().iterator().next();
            Ano an=age.getAnos().iterator().next();
            Mes me=an.getMeses().iterator().next();
            Dia di=me.getDias().iterator().next();
            Iniciofin ini=di.getIniciofins().iterator().next();
            if(ini.getEstadoFin()==null){
                cambio=true;
            }
        }
    }//GEN-LAST:event_jRadioButton1ItemStateChanged

    private void jRadioButton2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton2ItemStateChanged
        if(tar.getIdTarea()!=null){
            Agenda age=tar.getAgendas().iterator().next();
            Ano an=age.getAnos().iterator().next();
            Mes me=an.getMeses().iterator().next();
            Dia di=me.getDias().iterator().next();
            Iniciofin ini=di.getIniciofins().iterator().next();
            if(ini.getEstadoFin()!=null){
                cambio=true;
            }
        }
    }//GEN-LAST:event_jRadioButton2ItemStateChanged

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped
        if(jTextField3.getText().length()==20) evt.consume();
    }//GEN-LAST:event_jTextField3KeyTyped

    private void jTextField4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyTyped
        if(jTextField4.getText().length()==45) evt.consume();
    }//GEN-LAST:event_jTextField4KeyTyped

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
        try{
            if(jCheckBox1.isSelected()){
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                int cc=0;
                while (jTable1.getRowCount() != cc) {
                    Personal per = (Personal) model.getValueAt(cc, 1);
                    lista.add(per);
                    cc++;
                }
                Drive.LimpiarTabla(jTable1);
                Drive.CargarTablacheck(jTable1,buffer.toString(), buffer.toString().toUpperCase(),lista);
            }else{
                lista.clear();
                Drive.LimpiarTabla(jTable1);
                Drive.CargarTablacheck(jTable1,buffer.toString(), buffer.toString().toUpperCase(),lista);
            }
        }catch(Exception e){}
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

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
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
