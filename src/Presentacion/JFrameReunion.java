/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JFrameReunion.java
 *
 * Created on 27/06/2012, 16:17:45
 */

package Presentacion;

import Clases.Agenda;
import Clases.AgendaId;
import Clases.Ano;
import Clases.Anolectivo;
import Clases.Auditoria;
import Clases.Controlador;
import Clases.Departamento;
import Clases.Dia;
import Clases.Establecimiento;
import Clases.Franco;
import Clases.Iniciofin;
import Clases.Lugar;
import Clases.Mes;
import Clases.Personal;
import Clases.PersonaldocenteId;
import Clases.Revista;
import Clases.Tarea;
import Clases.Tareareunion;
import Clases.TareareunionId;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.MaskFormatter;
/**
 *
 * @author fer
 */
public class JFrameReunion extends javax.swing.JFrame {

     Controlador Drive;
     Personal adm;
//     int idsesion;
    StringBuffer buffer= new StringBuffer();
    List lista = new ArrayList();
    Tarea tar = new Tarea();
    boolean cambio=false;
    Date fecha=new Date();
    /** Creates new form JFrameReunion */
    public JFrameReunion(Controlador unDrive, Personal admin, Tarea tarr) {
        this.Drive = unDrive;
        this.adm = admin;
//        this.idsesion = id;
        this.tar = tarr;
        initComponents();

        int[] anchos1 = {85, 200, 65};
        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos1[i]);
        }
        DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
        modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(modelocentrar);
        jTable1.getTableHeader().setDefaultRenderer(new HeaderRenderer(jTable1));
        Drive.CargarComboDepartamento(jComboBox1);
        Drive.CargarComboLugar(jComboBox3);
        String buscar;
        Object aux= jComboBox1.getSelectedItem();
        if(aux.equals("TODOS")){
            buscar=(String) aux;
        }else{
            Departamento dep=(Departamento) aux;
            buscar=dep.getNombre();
        }
        Drive.CargarTablacheck2(jTable1, buscar, buffer.toString().toUpperCase(), lista);
        jFormattedTextField1.setText("00:00");
        jFormattedTextField2.setText("00:00");
        ///Verificar si vengo desde principal o desde consultar tarea
        if (tar.getIdTarea() != null) {
            try {
                jTextField3.setText(tar.getNombre());
                jTextField3.setEnabled(false);
                jComboBox3.setSelectedItem(tar.getLugar());
                Tareareunion tareu = tar.getTareareunions().iterator().next();
                jTextField1.setText(tareu.getMotivo());
                jComboBox2.setSelectedItem(tareu.getCaracter());
                fecha = tar.ObtenerFechaMayor(new Date().getYear());
                if (fecha != null) {
                    Calendar ffecha = Calendar.getInstance();
                    ffecha.setTime(fecha);
                    dateChooserCombo1.setSelectedDate(ffecha);
                }
                Agenda age = tar.getAgendas().iterator().next();
                Dia d = age.getDia2(fecha);
                Iniciofin ini = d.getIniciofins().iterator().next();
                SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                jFormattedTextField1.setValue(formateador.format(ini.getInicio()));
                jFormattedTextField2.setValue(formateador.format(ini.getFin()));
                Drive.LimpiarTabla(jTable1);
                Iterator it = tar.getAgendas().iterator();
                while (it.hasNext()) {
                    Agenda agg = (Agenda) it.next();
                    lista.add(agg.getPersonal().getIdPersonal());
                }
                Drive.CargarTablacheck2(jTable1, buscar, buffer.toString().toUpperCase(), lista);
            } catch (Exception e) {
            }
        }
        ImageIcon fott2 = new ImageIcon(getClass().getResource("/imagenes/no.png"));
        Icon icono2 = new ImageIcon(fott2.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        jButton2.setIcon(icono2);
        ImageIcon fott3 = new ImageIcon(getClass().getResource("/imagenes/ok.png"));
        Icon icono3 = new ImageIcon(fott3.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        jButton1.setIcon(icono3);
        ///ICONO EDITAR
        ImageIcon fot = new ImageIcon(getClass().getResource("/imagenes/image.jpg"));
        Icon icono1 = new ImageIcon(fot.getImage().getScaledInstance(jLabel36.getWidth(), jLabel36.getHeight(), Image.SCALE_DEFAULT));
        jLabel36.setIcon(icono1);
        jLabel36.repaint();
        ImageIcon fott = new ImageIcon(getClass().getResource("/imagenes/eliminar.gif"));
        Icon icono0 = new ImageIcon(fott.getImage().getScaledInstance(jLabel21.getWidth(), jLabel21.getHeight(), Image.SCALE_DEFAULT));
        jLabel21.setIcon(icono0);
        jLabel21.repaint();
        if(adm.getPerfil().getActividadesins()==null&&tar.getIdTarea()==null){
            jButton1.setEnabled(false);
        }
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        jLabel2 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jTextField2 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jComboBox3 = new javax.swing.JComboBox();
        jLabel21 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SISTEMA DE ASISTENCIA DE PERSONAL EDUCATIVO");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de la reunión"));

        jLabel1.setText("Motivo:");

        jLabel4.setText("Profesor:");

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

        jTable1.setAutoCreateRowSorter(true);
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
        jScrollPane1.setViewportView(jTable1);

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jLabel5.setText("Caracter:");

        jLabel7.setText("Dia:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Obligatorio", "No obligatorio" }));

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

    jLabel2.setText("Nombre:");

    jTextField3.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jTextField3ActionPerformed(evt);
        }
    });
    jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            jTextField3KeyTyped(evt);
        }
    });

    jLabel6.setText("Lugar:");

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

    jLabel14.setText("*");

    jLabel16.setText("*");

    jLabel9.setText("Hora Fin:");

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

    jLabel17.setText("*");

    jLabel10.setText("hh:mm");

    jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            jTextField2KeyTyped(evt);
        }
    });

    jLabel12.setText("Departamento:");

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

    jLabel21.setText("E");
    jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jLabel21MouseClicked(evt);
        }
    });

    jLabel36.setText("N");
    jLabel36.setMaximumSize(new java.awt.Dimension(15, 15));
    jLabel36.setMinimumSize(new java.awt.Dimension(15, 15));
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
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jCheckBox1)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel26)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel25)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1)
                            .addGap(18, 18, 18)
                            .addComponent(jButton2)))))
            .addGap(10, 10, 10))
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(jLabel1)
                        .addComponent(jLabel7)
                        .addComponent(jLabel3))
                    .addGap(4, 4, 4)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(dateChooserCombo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField1)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel16))
                                .addComponent(jLabel8)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel14)))
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addGap(43, 43, 43)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel9)
                        .addComponent(jLabel5)
                        .addComponent(jLabel6))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel17))
                        .addComponent(jLabel10)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.LEADING, 0, 97, Short.MAX_VALUE)
                                .addComponent(jComboBox3, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jLabel12)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel4)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))))
    );

    jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jTextField1, jTextField2, jTextField3});

    jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2});

    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14)
                        .addComponent(jLabel2))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel21)
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))))
            .addGap(18, 18, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(26, 26, 26)
                    .addComponent(jLabel8))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(3, 3, 3)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17)
                        .addComponent(jLabel9))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel10)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel4)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel12)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jCheckBox1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(jLabel26)
                .addComponent(jLabel25)
                .addComponent(jButton1)
                .addComponent(jButton2))
            .addGap(6, 6, 6))
    );

    jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jTextField1, jTextField3});

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(19, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(18, Short.MAX_VALUE))
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
    
    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (tar.getIdTarea()==null) {
            if (!jTextField1.getText().isEmpty() || !jTextField3.getText().isEmpty()) {
                int confirmado = JOptionPane.showConfirmDialog(null, "¿Desea cancelar la reunión?", "Registrar Reunión", JOptionPane.YES_NO_OPTION);
                if (JOptionPane.OK_OPTION == confirmado) {
                    Frame vp = new JFramePrincipal(Drive, adm);
                    this.dispose();
                    vp.show();
                }
            } else {
                Frame vp = new JFramePrincipal(Drive, adm);
                this.dispose();
                vp.show();
            }
        } else {
            if (cambio==true) {
                int confirmado = JOptionPane.showConfirmDialog(null, "¿Desea cancelar la actualización de la reunión?", "Actualizar Reunión", JOptionPane.YES_NO_OPTION);
                if (JOptionPane.OK_OPTION == confirmado) {
                    Frame vp = new JFrameConsultaActividades(Drive, adm);
                    this.dispose();
                    vp.show();
                }
            } else {
                Frame vp = new JFrameConsultaActividades(Drive, adm);
                this.dispose();
                vp.show();
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            if (Drive.VerificarCheckTabla(jTable1)) {
                if (!jTextField3.getText().isEmpty() && !jFormattedTextField1.getText().contains(" ") && !jFormattedTextField2.getText().contains(" ")) {
                    Lugar lu=(Lugar) jComboBox3.getSelectedItem();
                    jButton1.setEnabled(false);
                    jButton2.setEnabled(false);
                    if (tar.getIdTarea() == null) {
                        // <editor-fold defaultstate="collapsed" desc="Guardar tarea nueva"> 
                        SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                        Date inicio = formateador.parse(jFormattedTextField1.getText());
                        Date fin = formateador.parse(jFormattedTextField2.getText());
                        if (inicio.compareTo(fin) < 0) {
                            String jcombo2 = (String) jComboBox2.getSelectedItem();
                            fecha = dateChooserCombo1.getSelectedDate().getTime();
                            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                            int cc = 0;
                            boolean control;
                            boolean est = false;
                            while (jTable1.getRowCount() != cc) {
                                if (model.getValueAt(cc, 0).equals(true)) {
                                    Personal per = (Personal) model.getValueAt(cc, 1);
                                    control=true;
                                    int id=0;
                                    if(tar.getIdTarea()!=null){id=tar.getIdTarea();}
                                    int res=Drive.ReunionVerificarDisponibilidad(per,fecha, inicio, fin, control,id);
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
                                tarr.setLugar(lu);
                                tarr.setComentario("REUNION");
                                tarr.setEstado(true);
                                
                                Date aux1=new Date();
                                aux1.setYear(fecha.getYear());
                                aux1.setMonth(fecha.getMonth());
                                aux1.setDate(fecha.getDate());
                                aux1.setHours(inicio.getHours());
                                aux1.setMinutes(inicio.getMinutes());
                                tarr.setDiaInicio(aux1);
                                Date aux2=fecha;
                                int hora2=fin.getHours();
                                int min2=fin.getMinutes();
                                aux2.setHours(hora2);
                                aux2.setMinutes(min2);
                                tarr.setDiaFin(aux2);
                                int idtar = tarr.guardarTarea(tarr);
                                // <editor-fold defaultstate="collapsed" desc="Auditoria"> 
                                Auditoria audi=new Auditoria();
                                audi.setPersonalByIdAuditor(adm);
                                audi.setOperacion("Insertar");
                                audi.setFecha(new Date());
                                audi.setTarea(tarr);
                                audi.guardarAuditoria(audi);
                                // </editor-fold>
                                TareareunionId id = new TareareunionId();
                                id.setIdTarea(idtar);
                                tarr.crearTareareunion(id, tarr, jTextField1.getText().toUpperCase(), jcombo2);
                                DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
                                int c = 0;
                                while (jTable1.getRowCount() != c) {
                                    if (modelo.getValueAt(c, 0).equals(true)) {
                                        Personal per = (Personal) modelo.getValueAt(c, 1);
                                        control=false;
                                        int idd=0;
                                        if(tar.getIdTarea()!=null){idd=tar.getIdTarea();}
                                        if (Drive.ReunionVerificarDisponibilidad(per,fecha, inicio, fin, control,idd)==1) {
                                            AgendaId ida = new AgendaId(per.getIdPersonal(), tarr.getIdTarea());
                                            Agenda age = new Agenda();
                                            age.setAnolectivo(Drive.getAnoLectivo());
                                            age.setId(ida);
                                            age.setPersonal(per);
                                            age.setTarea(tarr);
                                            age.setComentario(null);
                                            age.guardarAgenda(age);

                                            Ano anio = new Ano();
                                            anio.setAgenda(age);
                                            anio.setAno(fecha.getYear() + 1900);
                                            anio.guardarAno(anio);
                                            Mes mes = new Mes();
                                            mes.setAno(anio);
                                            mes.setMes(fecha.getMonth());
                                            mes.guardarMes(mes);
                                            Dia dia = new Dia();
                                            dia.setMes(mes);
                                            dia.setDia(fecha.getDate());
                                            dia.guardarDia(dia);

                                            Iniciofin in = new Iniciofin();
                                            in.setDia(dia);
                                            in.setInicio(inicio);
                                            in.setEstadoInicio(false);
                                            in.setFin(fin);
                                            in.guardarIniciofin(in);
                                        }else if(Drive.ReunionVerificarDisponibilidad(per,fecha, inicio, fin, control,tar.getIdTarea())==2){
                                            AgendaId ida = new AgendaId(per.getIdPersonal(), tarr.getIdTarea());
                                            Agenda age = new Agenda();
                                            age.setId(ida);
                                            age.setPersonal(per);
                                            Revista rev = (Revista) Drive.PERSISTENCIA.getSitRevista().get(0);
                                            age.setRevista(rev);
                                            age.setTarea(tarr);
                                            age.setComentario(null);
                                            age.guardarAgenda(age);

                                            Ano anio = new Ano();
                                            anio.setAgenda(age);
                                            anio.setAno(fecha.getYear() + 1900);
                                            anio.guardarAno(anio);
                                            Mes mes = new Mes();
                                            mes.setAno(anio);
                                            mes.setMes(fecha.getMonth());
                                            mes.guardarMes(mes);
                                            Dia dia = new Dia();
                                            dia.setMes(mes);
                                            dia.setDia(fecha.getDate());
                                            dia.guardarDia(dia);

                                            Iniciofin in = new Iniciofin();
                                            in.setDia(dia);
                                            in.setInicio(inicio);
                                            in.setFin(fin);
                                            in.guardarIniciofin(in);
                                        }
                                    }
                                    c++;
                                }
                                jTextField3.setText("");
                                jTextField1.setText("");
                                Drive.LimpiarTabla(jTable1);
                                lista.removeAll(lista);
                                String buscar = (String) jComboBox1.getSelectedItem();
                                Drive.CargarTablacheck2(jTable1, buscar, buffer.toString().toUpperCase(), lista);
                                Drive = new Controlador();
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Por favor ingrese la hora correctamente", "Registrar Reunión", JOptionPane.ERROR_MESSAGE);
                        }
//                        jButton1.setEnabled(true);
//                        jButton2.setEnabled(true);
                        // </editor-fold>
                    } else {
                        // <editor-fold defaultstate="collapsed" desc="Actualizar tarea"> 
//                        boolean band2=false;
                        SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                        formateador.setLenient(false);
                        Date inicio = formateador.parse(jFormattedTextField1.getText());
                        Date fin = formateador.parse(jFormattedTextField2.getText());
                        if (inicio.compareTo(fin) < 0) {
                            boolean mensaje=false;
                            String caracter = (String) jComboBox2.getSelectedItem();
                            fecha = dateChooserCombo1.getSelectedDate().getTime();
                            if (tar.getLugar().getIdLugar()!=lu.getIdLugar()) {
                                // <editor-fold defaultstate="collapsed" desc="Auditoria"> 
                                Auditoria audi= new Auditoria();
                                audi.setPersonalByIdAuditor(adm);
                                audi.setOperacion("Actualizar");
                                audi.setFecha(new Date());
                                audi.setTarea(tar);
                                audi.setCampo("Lugar");
                                audi.setElementoAnterior(tar.getLugar().getNombre());
                                audi.setElementoNuevo(lu.getNombre());
                                audi.guardarAuditoria(audi);
                                // </editor-fold>
                                tar.setLugar(lu);
                                tar.ActualizarTarea(tar);
                                mensaje=true;
                                jComboBox3.setSelectedItem(lu);
//                                band2=true;
                            }
                            Tareareunion tareu = tar.getTareareunions().iterator().next();
                            if (!tareu.getMotivo().equals(jTextField1.getText().toUpperCase())) {
                                // <editor-fold defaultstate="collapsed" desc="Auditoria"> 
                                Auditoria audi= new Auditoria();
                                audi.setPersonalByIdAuditor(adm);
                                audi.setOperacion("Actualizar");
                                audi.setFecha(new Date());
                                audi.setTarea(tar);
                                audi.setCampo("Motivo");
                                audi.setElementoAnterior(tareu.getMotivo());
                                audi.setElementoNuevo(jTextField1.getText().toUpperCase());
                                audi.guardarAuditoria(audi);
                                // </editor-fold>
                                tareu.setMotivo(jTextField1.getText().toUpperCase());
                                tareu.actualizarTareareunion(tareu);
                                mensaje=true;
                                jTextField1.setText(tareu.getMotivo());
                            }
                            if (!tareu.getCaracter().equals(caracter)) {
                                // <editor-fold defaultstate="collapsed" desc="Auditoria"> 
                                Auditoria audi= new Auditoria();
                                audi.setPersonalByIdAuditor(adm);
                                audi.setOperacion("Actualizar");
                                audi.setFecha(new Date());
                                audi.setTarea(tar);
                                audi.setCampo("Caracter");
                                audi.setElementoAnterior(tareu.getCaracter());
                                audi.setElementoNuevo(caracter);
                                audi.guardarAuditoria(audi);
                                // </editor-fold>
                                tareu.setCaracter(caracter);
                                tareu.actualizarTareareunion(tareu);
                                mensaje=true;
                                jComboBox2.setSelectedItem(tareu.getCaracter());
                            }
                            
                            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
                            int c = 0;
                            boolean controll=true;
                            int cont = tar.getAgendas().size();
                            int contper = 0;
                            List lista = tar.ListaPer(tar);
                            while (jTable1.getRowCount() != c) {
                                boolean aux = (Boolean) modelo.getValueAt(c, 0);
                                if (aux == true) {
                                    contper++;
                                    Personal person = (Personal) modelo.getValueAt(c, 1);
                                    if (!lista.contains(person)) {
                                        cambio = true;
                                    }
                                    if (Drive.ReunionVerificarDisponibilidad(person,fecha, inicio, fin, controll,tar.getIdTarea()) == 0) {
                                        controll=false;
                                        break;
                                    }
                                }
                                c++;
                            }
                            if (cont != contper) {
                                cambio = true;
                            }
                            if (cambio == true && controll==true) {
                                Date aux1=fecha;
                                Date aux2=fecha;
                                aux1.setHours(inicio.getHours());
                                aux1.setMinutes(inicio.getMinutes());
                                aux1.setSeconds(inicio.getSeconds());
                                aux2.setHours(fin.getHours());
                                aux2.setMinutes(fin.getMinutes());
                                aux2.setSeconds(fin.getSeconds());
                                if(tar.getDiaInicio().compareTo(aux1)!=0){
                                    // <editor-fold defaultstate="collapsed" desc="Auditoria"> 
                                    Auditoria audi= new Auditoria();
                                    audi.setPersonalByIdAuditor(adm);
                                    audi.setOperacion("Actualizar");
                                    audi.setFecha(new Date());
                                    audi.setTarea(tar);
                                    audi.setCampo("Fecha");
                                    SimpleDateFormat formatea = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                                    audi.setElementoAnterior(formatea.format(tar.getDiaInicio()));
                                    audi.setElementoNuevo(formatea.format(aux1));
                                    audi.guardarAuditoria(audi);
                                    // </editor-fold>
                                    tar.setDiaInicio(aux1);
                                    tar.ActualizarTarea(tar);
                                    mensaje=true;
                                }
                                if(tar.getDiaFin().compareTo(aux2)!=0){
                                    // <editor-fold defaultstate="collapsed" desc="Auditoria"> 
                                    Auditoria audi= new Auditoria();
                                    audi.setPersonalByIdAuditor(adm);
                                    audi.setOperacion("Actualizar");
                                    audi.setFecha(new Date());
                                    audi.setTarea(tar);
                                    audi.setCampo("Fecha");
                                     SimpleDateFormat formatea = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                                    audi.setElementoAnterior(formatea.format(tar.getDiaFin()));
                                    audi.setElementoNuevo(formatea.format(aux2));
                                    audi.guardarAuditoria(audi);
                                    // </editor-fold>
                                    tar.setDiaFin(aux2);
                                    tar.ActualizarTarea(tar);
                                    mensaje=true;
                                }

                                tar.BorrarTodo();
                                Drive=new Controlador();
                                tar=(Tarea) Drive.PERSISTENCIA.getTarea(tar.getIdTarea()).iterator().next();
                                c = 0;
                                while (jTable1.getRowCount() != c) {
                                    if (modelo.getValueAt(c, 0).equals(true)) {
                                        Personal per = (Personal) modelo.getValueAt(c, 1);
                                        boolean control=false;
                                        if (Drive.ReunionVerificarDisponibilidad(per,fecha, inicio, fin,control,tar.getIdTarea())==1) {
                                            Agenda age = tar.ObtenerAgenda(per.getIdPersonal());
                                            if (age.getId() == null) {
                                                AgendaId ida = new AgendaId(per.getIdPersonal(), tar.getIdTarea());
                                                age.setAnolectivo(Drive.getAnoLectivo());
                                                age.setId(ida);
                                                age.setPersonal(per);
                                                age.setTarea(tar);
                                                age.setComentario(null);
                                                age.guardarAgenda(age);
                                            }

                                            Ano anio = new Ano();
                                            anio.setAgenda(age);
                                            anio.setAno(fecha.getYear() + 1900);
                                            anio.guardarAno(anio);
                                            Mes mes = new Mes();
                                            mes.setAno(anio);
                                            mes.setMes(fecha.getMonth());
                                            mes.guardarMes(mes);
                                            Dia dia = new Dia();
                                            dia.setMes(mes);
                                            dia.setDia(fecha.getDate());
                                            dia.guardarDia(dia);

                                            Iniciofin in = new Iniciofin();
                                            in.setDia(dia);
                                            in.setInicio(inicio);
                                            in.setEstadoInicio(false);
                                            in.setFin(fin);
                                            in.guardarIniciofin(in);
                                        } else if(Drive.ReunionVerificarDisponibilidad(per,fecha, inicio, fin,control,tar.getIdTarea())==2) {
                                            Agenda age = tar.ObtenerAgenda(per.getIdPersonal());
                                            if (age.getId() == null) {
                                                AgendaId ida = new AgendaId(per.getIdPersonal(), tar.getIdTarea());
                                                age.setId(ida);
                                                age.setPersonal(per);
                                                age.setTarea(tar);
                                                age.setComentario(null);
                                                age.guardarAgenda(age);
                                            }

                                            Ano anio = new Ano();
                                            anio.setAgenda(age);
                                            anio.setAno(fecha.getYear() + 1900);
                                            anio.guardarAno(anio);
                                            Mes mes = new Mes();
                                            mes.setAno(anio);
                                            mes.setMes(fecha.getMonth());
                                            mes.guardarMes(mes);
                                            Dia dia = new Dia();
                                            dia.setMes(mes);
                                            dia.setDia(fecha.getDate());
                                            dia.guardarDia(dia);

                                            Iniciofin in = new Iniciofin();
                                            in.setDia(dia);
                                            in.setInicio(inicio);
                                            in.setEstadoInicio(false);
                                            in.setFin(fin);
                                            in.guardarIniciofin(in);
                                        } else{
                                            JOptionPane.showMessageDialog(null, "No existe disponibilidad de horario para "+ per +" porque existe una tarea extracurricular a ese horario", "Registrar Reunión", JOptionPane.ERROR_MESSAGE);
                                        }
                                    }
                                    c++;
                                }
                            }
                            if(mensaje==true){
                                JOptionPane.showMessageDialog(null,"La tarea se actualizó correctamente","Actualizar tarea",JOptionPane.INFORMATION_MESSAGE);
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Por favor ingrese la hora correctamente", "Registrar Reunión", JOptionPane.ERROR_MESSAGE);
                            jButton1.setEnabled(true);
                            jButton2.setEnabled(true);
                            return;
                        }
                        Frame vp = new JFrameConsultaActividades(Drive, adm);
                        this.dispose();
                        vp.show();
                        // </editor-fold>
                    }
                    jButton1.setEnabled(true);
                    jButton2.setEnabled(true);
//                    jFormattedTextField1.setText("00:00");
//                    jFormattedTextField2.setText("00:00");
//                    dateChooserCombo1.setSelectedDate(Calendar.getInstance());
                } else {
                    JOptionPane.showMessageDialog(null, "Todos los campos con '*' son obligatorios y los horarios no pueden contener espacios en blanco", "Registrar Reunión", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar al menos un personal", "Registrar Reunión", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ingrese correctamente los valores", "Registrar Reunión", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (tar.getIdTarea()==null) {
            if (!jTextField1.getText().isEmpty() || !jTextField3.getText().isEmpty()) {
                int confirmado = JOptionPane.showConfirmDialog(null, "¿Desea cancelar la reunión?", "Registrar Reunión", JOptionPane.YES_NO_OPTION);
                if (JOptionPane.OK_OPTION == confirmado) {
                    Frame vp = new JFramePrincipal(Drive, adm);
                    this.dispose();
                    vp.show();
                }
            } else {
                Frame vp = new JFramePrincipal(Drive, adm);
                this.dispose();
                vp.show();
            }
        } else {
            if (!cambio==true) {
                int confirmado = JOptionPane.showConfirmDialog(null, "¿Desea cancelar la actualización de la reunión?", "Actualizar Reunión", JOptionPane.YES_NO_OPTION);
                if (JOptionPane.OK_OPTION == confirmado) {
                    Frame vp = new JFrameConsultaActividades(Drive, adm);
                    this.dispose();
                    vp.show();
                }
            } else {
                Frame vp = new JFrameConsultaActividades(Drive, adm);
                this.dispose();
                vp.show();
            }
        }
    }//GEN-LAST:event_formWindowClosing

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
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
                Drive.CargarTablacheck2(jTable1,buscar, es.toUpperCase(),lista);
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
                Drive.CargarTablacheck2(jTable1,buscar, es.toUpperCase(),lista);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR","Registrar Reunión", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jTextField2KeyTyped

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
        Drive.CargarTablacheck2(jTable1,buscar, buffer.toString().toUpperCase(),lista);
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jFormattedTextField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextField2FocusLost
        try{
            String hora=jFormattedTextField2.getText();
            String h  = hora.substring(0,2);  
            String m  = hora.substring(3,5);  
            int conta_hora = Integer.parseInt(h);  
            int conta_minuto = Integer.parseInt(m);  
            if(conta_hora > 23 || conta_minuto > 59) {  
                JOptionPane.showMessageDialog(null, "Ingrese correctamente la hora" ,"Registrar Reunión",JOptionPane.ERROR_MESSAGE);  
                jFormattedTextField2.setText("00:00"); 
                return;  
            }
            SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
            Date inicio = formateador.parse(jFormattedTextField1.getText());
            Date fin = formateador.parse(jFormattedTextField2.getText());
            if (inicio.compareTo(fin) >= 0) {
                JOptionPane.showMessageDialog(null, "El horario de fin debe ser mayor al horario de inicio", "Registrar Reunión", JOptionPane.ERROR_MESSAGE);
                jFormattedTextField1.setText("00:00");
                jFormattedTextField2.setText("00:00");
                return;
            }
            if (tar.getIdTarea() != null) {
                Agenda age = tar.getAgendas().iterator().next();
                Dia d = age.getDia2(fecha);
                Iniciofin ini = d.getIniciofins().iterator().next();
                String est = formateador.format(ini.getFin());
                Date aux=formateador.parse(est);
                if(!fin.equals(aux)){
                    cambio=true;
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error","Registrar Reunión", JOptionPane.ERROR_MESSAGE);
            jFormattedTextField2.setText("00:00");
        }
    }//GEN-LAST:event_jFormattedTextField2FocusLost

    private void dateChooserCombo1OnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_dateChooserCombo1OnSelectionChange
        try {
            Date inicio=dateChooserCombo1.getSelectedDate().getTime();
            Date fech=new Date();
            inicio.setHours(fech.getDate());
            inicio.setMinutes(fech.getMinutes());
            inicio.setSeconds(fech.getSeconds());
            
            Anolectivo an=Drive.getPrimerEstablecimiento().getAnoLectivo(fech.getYear()+1900);
            if(an.getInicio().compareTo(inicio)>0 || an.getFin().compareTo(inicio)<0){
                JOptionPane.showMessageDialog(null,"La fecha debe estar contemplado dentro del año lectivo","Registrar Reunión",JOptionPane.ERROR_MESSAGE);
                Calendar cal = Calendar.getInstance();
                dateChooserCombo1.setSelectedDate(cal);
                return;
            }
            if(inicio.compareTo(fech)<=0){
                JOptionPane.showMessageDialog(null,"La fecha no puede ser menor a hoy","Registrar Reunión",JOptionPane.ERROR_MESSAGE);
                Calendar cal = Calendar.getInstance();
                dateChooserCombo1.setSelectedDate(cal);
                return;
            }
            if (tar.getIdTarea() != null) {
//                if (inicio.compareTo(f) >= 0) {
                inicio = dateChooserCombo1.getSelectedDate().getTime();
                SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                formateador.setLenient(false);
                String i = formateador.format(fecha);
                Date ffecha = formateador.parse(i);
                String e = formateador.format(inicio);
                Date iinicio = formateador.parse(e);
                if (!ffecha.equals(iinicio)) {
                    cambio = true;
                }
//                } else {
//                    JOptionPane.showMessageDialog(null, "La fecha no puede ser menor a la fecha de inicio de la reunión", "Actualizar reunión", JOptionPane.ERROR_MESSAGE);
//                    Calendar cal = Calendar.getInstance();
//                    cal.setTime(fecha);
//                    dateChooserCombo1.setSelectedDate(cal);
//                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_dateChooserCombo1OnSelectionChange

    private void jFormattedTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextField1FocusLost
        try {
            String hora=jFormattedTextField1.getText();
            String h  = hora.substring(0,2);  
            String m  = hora.substring(3,5);  
            int conta_hora = Integer.parseInt(h);  
            int conta_minuto = Integer.parseInt(m);  
            if(conta_hora > 23 || conta_minuto > 59) {  
                JOptionPane.showMessageDialog(null, "Ingrese correctamente la hora" ,"Registrar Reunión",JOptionPane.ERROR_MESSAGE);  
                jFormattedTextField1.setText("00:00"); 
                return;  
            }  
            SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
            Date inicio = formateador.parse(jFormattedTextField1.getText());
            Date fe=dateChooserCombo1.getSelectedDate().getTime();
            Date ahora = new Date();
            if (fe.compareTo(ahora)<=0) {
                ahora.setDate(inicio.getDate());
                ahora.setMonth(inicio.getMonth());
                ahora.setYear(inicio.getYear());
                if (inicio.compareTo(ahora) <= 0) {
                    JOptionPane.showMessageDialog(null, "El inicio de la reunión debe ser mayor que las: " + formateador.format(ahora), "Registrar Reunión", JOptionPane.ERROR_MESSAGE);
                    jFormattedTextField1.setText("00:00");
                    return;
                }
            }
            if (tar.getIdTarea() != null) {
                Agenda age = tar.getAgendas().iterator().next();
                Dia d = age.getDia2(fecha);
                Iniciofin ini = d.getIniciofins().iterator().next();
                String est = formateador.format(ini.getInicio());
                Date aux = formateador.parse(est);
                if (!inicio.equals(aux)) {
                    cambio = true;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese correctamente la hora", "Registrar Reunión", JOptionPane.ERROR_MESSAGE);
            jFormattedTextField1.setText("00:00");
        }
    }//GEN-LAST:event_jFormattedTextField1FocusLost

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped
        if(jTextField3.getText().length()==20) evt.consume();
    }//GEN-LAST:event_jTextField3KeyTyped

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        if(jTextField1.getText().length()==45) evt.consume();
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
        try{
            if(jCheckBox1.isSelected()){
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                int cc=0;
                while (jTable1.getRowCount() != cc) {
                    Personal per = (Personal) model.getValueAt(cc, 1);
                    if(!lista.contains(per.getIdPersonal())){
                        lista.add(per.getIdPersonal());
                    }
                    cc++;
                }
                Drive.LimpiarTabla(jTable1);
                Drive.CargarTablacheck2(jTable1,buffer.toString(), buffer.toString().toUpperCase(),lista);
            }else{
                lista.clear();
                Drive.LimpiarTabla(jTable1);
                String buscar;
                Object aux= jComboBox1.getSelectedItem();
                if(aux.equals("TODOS")){
                    buscar=(String) aux;
                }else{
                    Departamento dep=(Departamento) aux;
                    buscar=dep.getNombre();
                }
                Drive.CargarTablacheck2(jTable1,buscar, buffer.toString().toUpperCase(),lista);
            }
        }catch(Exception e){}
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        int confirmado = JOptionPane.showConfirmDialog(null,"¿Desea eliminar el Lugar?","Eliminar Lugar",JOptionPane.YES_NO_OPTION);
        if (JOptionPane.OK_OPTION == confirmado){
            Object o=jComboBox3.getSelectedItem();
            if(o!=null){
                Lugar lu=(Lugar) o;
                lu.EliminarLugar(lu);
                Drive.LimpiarCombo(jComboBox3);
                Drive.CargarComboLugar(jComboBox3);
            }else{
                JOptionPane.showMessageDialog(null, "Seleccione un lugar","Eliminar Lugar", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jLabel21MouseClicked

    private void jLabel36MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel36MouseClicked
        JTextField nombre = new JTextField();
        JTextField descripcion = new JTextField();
        String cadSalida;
        nombre.setText("");
        nombre.setSize(30, 30);
        String caddes = null;
        descripcion.setText("");
        descripcion.setSize(20, 20);

        Lugar au = new Lugar();
        JOptionPane.showMessageDialog(null, nombre, "Ingrese el nombre del lugar", JOptionPane.INFORMATION_MESSAGE);
        if (!nombre.getText().isEmpty() && nombre.getText().length() <= 45) {
            cadSalida = nombre.getText().toUpperCase();
            Iterator it = Drive.PERSISTENCIA.getLugar().iterator();
            boolean w = false;
            while (it.hasNext()) {
                Lugar tip = (Lugar) it.next();
                if (tip.getNombre().equals(cadSalida)) {
                    JOptionPane.showMessageDialog(null, "El lugar ya existe", "Registrar Lugar", JOptionPane.ERROR_MESSAGE);
                    w = true;
                }
            }
            if (w == false) {
                au.setNombre(cadSalida);
                JOptionPane.showMessageDialog(null, descripcion, "Ingrese la descripción del Lugar", JOptionPane.INFORMATION_MESSAGE);
                if (descripcion.getText().length() <= 100) {
                    caddes = descripcion.getText().toUpperCase();
                }
                au.setDescripcion(caddes);
                au.guardarLugar(au);
                Drive.LimpiarCombo(jComboBox3);
                Drive.CargarComboLugar(jComboBox3);
                jComboBox3.setSelectedItem(au);
            }
        } else {
            JOptionPane.showMessageDialog(null, "El nombre del lugar no puede estar vacio y puede contener hasta 45 caracteres", "Registrar Lugar", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jLabel36MouseClicked

    /**
    * @param args the command line arguments
    */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables

}
