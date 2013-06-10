/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
import Clases.Iniciofin;
import Clases.Lugar;
import Clases.Mes;
import Clases.Personal;
import Clases.Tarea;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
 * @author Fernando
 */
public class JFrameAdministrativo extends javax.swing.JFrame {

    /**
     * Creates new form JFrameAdministrativo
     */
    
    Controlador Drive;
     Personal adm;
//     int idsesion;
    StringBuffer buffer= new StringBuffer();
    List lista = new ArrayList();
    Personal per=new Personal();
    Tarea tar=new Tarea();
    boolean cambio=false;
    Date mayor=new Date();
    Date menor=new Date();
    List diass=new ArrayList();
    
    HashMap items= new HashMap();
    HashMap listini = new HashMap();
    
    public JFrameAdministrativo(Controlador unDrive, Personal admin,Tarea tarr) {
        this.adm=admin;
        this.Drive=unDrive;
//        this.idsesion=id;
        this.tar=tarr;
        initComponents();
        
        int[] anchos1 = {85, 200, 65};
        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos1[i]);
        }
        DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
        modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(modelocentrar);
        jTable1.getTableHeader().setDefaultRenderer(new JFrameAdministrativo.HeaderRenderer(jTable1));
        Drive.CargarComboDepartamento(jComboBox1);
        Drive.CargarComboLugar(jComboBox3);
        
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
        if(tar.getIdTarea()!=null){
            jTextField1.setText(tar.getNombre());
            jTextField1.setEnabled(false);
            jComboBox3.setSelectedItem(tar.getLugar());
            jComboBox2.setSelectedItem(tar.getComentario());
            Calendar ca=Calendar.getInstance();
            ca.setTime(tar.getDiaFin());
            dateChooserCombo2.setSelectedDate(ca);
            ca.setTime(tar.getDiaInicio());
            dateChooserCombo1.setSelectedDate(ca);
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
            Iterator it = tar.getAgendas().iterator();
            while (it.hasNext()) {
                Agenda agg = (Agenda) it.next();
                lista.add(agg.getPersonal().getIdPersonal());
            }
        }
        String buscar;
        Object aux= jComboBox1.getSelectedItem();
        if(aux.equals("TODOS")){
            buscar=(String) aux;
        }else{
            Departamento dep=(Departamento) aux;
            buscar=dep.getNombre();
        }
        Drive.CargarTablacheck2(jTable1, buscar, buffer.toString().toUpperCase(), lista);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
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
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        jLabel11 = new javax.swing.JLabel();
        dateChooserCombo2 = new datechooser.beans.DateChooserCombo();
        jLabel14 = new javax.swing.JLabel();
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
        jSeparator1 = new javax.swing.JSeparator();
        jComboBox3 = new javax.swing.JComboBox();
        jLabel36 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jCheckBox7 = new javax.swing.JCheckBox();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        jLabel2.setText("Lugar:");

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

    jLabel14.setText("*");

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

    jLabel36.setText("N");
    jLabel36.setMaximumSize(new java.awt.Dimension(15, 15));
    jLabel36.setMinimumSize(new java.awt.Dimension(15, 15));
    jLabel36.setPreferredSize(new java.awt.Dimension(15, 15));
    jLabel36.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jLabel36MouseClicked(evt);
        }
    });

    jLabel21.setText("E");
    jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseReleased(java.awt.event.MouseEvent evt) {
            jLabel21MouseReleased(evt);
        }
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jLabel21MouseClicked(evt);
        }
    });

    jLabel12.setText("Departamento:");

    jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TODOS" }));
    jComboBox1.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            jComboBox1ItemStateChanged(evt);
        }
    });

    jLabel13.setText("Profesor:");

    jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            jTextField2KeyTyped(evt);
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
    jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jTable1MouseClicked(evt);
        }
    });
    jTable1.addComponentListener(new java.awt.event.ComponentAdapter() {
        public void componentResized(java.awt.event.ComponentEvent evt) {
            jTable1ComponentResized(evt);
        }
    });
    jScrollPane1.setViewportView(jTable1);

    jCheckBox7.setText("Todos");
    jCheckBox7.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            jCheckBox7ItemStateChanged(evt);
        }
    });

    jLabel6.setText("Trabajo:");

    jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ADMINISTRATIVO", "MAESTRANZA", "OTROS" }));
    jComboBox2.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            jComboBox2ItemStateChanged(evt);
        }
    });

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jSeparator2)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel10))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel14)
                                    .addGap(53, 53, 53))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(dateChooserCombo1, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                                    .addGap(59, 59, 59))))
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
                                .addComponent(jFormattedTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(23, 23, 23)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel11))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(dateChooserCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addGap(11, 11, 11))))
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
                                    .addComponent(jLabel20)))))
                    .addGap(0, 19, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jLabel26)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel25)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1)
                    .addGap(18, 18, 18)
                    .addComponent(jButton2)))
            .addContainerGap())
        .addComponent(jSeparator1)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(10, 10, 10)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jCheckBox7)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jLabel12)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel13)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(89, 89, 89))))
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel6)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );

    jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2});

    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(14, 14, 14)
                    .addComponent(jLabel1))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel14)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(11, 11, 11)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel2)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel21))))
            .addGap(11, 11, 11)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel6)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(12, 12, 12)
            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(jLabel10)
                .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel11)
                .addComponent(dateChooserCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            .addGap(19, 19, 19)
            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel13)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel12)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jCheckBox7)
            .addGap(6, 6, 6)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                .addComponent(jLabel25)
                .addComponent(jLabel26)
                .addComponent(jButton1)
                .addComponent(jButton2))
            .addContainerGap())
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(37, Short.MAX_VALUE))
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
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            if (jTable1.getRowCount() != 0) {
                Lugar lu=(Lugar)jComboBox3.getSelectedItem();
                if (!jTextField1.getText().isEmpty()) {
                    jButton1.setEnabled(false);
                    jButton2.setEnabled(false);
                    jTable1.setEnabled(false);
                    if (tar.getIdTarea() == null) {
                        // <editor-fold defaultstate="collapsed" desc="Guardar tarea nueva">
                        boolean band=true;
                        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
                        SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                        Date inicio = dateChooserCombo1.getSelectedDate().getTime();
                        Date fin = dateChooserCombo2.getSelectedDate().getTime();

                        int c = 0;
                        while (jTable1.getRowCount() != c) {
                            boolean b= (Boolean) modelo.getValueAt(c, 0);
                            if(b){
                                Personal person = (Personal) modelo.getValueAt(c, 1);
                                if (Drive.DisponibilidadAdm(person,inicio, fin, listini, items,0)) {
                                    band=true;
                                }else{
                                    band=false;
                                    JOptionPane.showMessageDialog(null,"No existe disponibilidad", "Registrar clase",JOptionPane.ERROR_MESSAGE);
                                    break;
                                }
                            }
                            c++;
                        }
                        if(band==true){
                            Tarea tarr=new Tarea();
                            tarr.setEstablecimiento(Drive.getPrimerEstablecimiento());
                            tarr.setNombre(jTextField1.getText().toUpperCase());
                            tarr.setLugar(lu);
                            String com=(String) jComboBox2.getSelectedItem();
                            tarr.setComentario(com);
                            tarr.setEstado(true);
                            tarr.setDiaInicio(inicio);
                            tarr.setDiaFin(fin);
                            int idtar=tarr.guardarTarea(tarr);
                            
                            // <editor-fold defaultstate="collapsed" desc="Auditoria">
                            Auditoria audi= new Auditoria();
                            audi.setPersonalByIdAuditor(adm);
                            audi.setOperacion("Insertar");
                            audi.setFecha(new Date());
                            audi.setTarea(tarr);
                            audi.guardarAuditoria(audi);
                            // </editor-fold>
                            c = 0;
                            Iterator lis=lista.iterator();
                            while (lis.hasNext()) {
                                Personal person=(Personal) lis.next();
                                AgendaId idage = new AgendaId(person.getIdPersonal(), tarr.getIdTarea());
                                Agenda age = new Agenda();
                                age.setId(idage);
                                age.setComentario(null);
                                age.setAnolectivo(Drive.getAnoLectivo());
                                age.guardarAgenda(age);

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
                            }
                            jTextField1.setText("");
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
                            String buscar;
                            Object aux= jComboBox1.getSelectedItem();
                            if(aux.equals("TODOS")){
                                buscar=(String) aux;
                            }else{
                                Departamento dep=(Departamento) aux;
                                buscar=dep.getNombre();
                            }
                            Drive.CargarTablacheck2(jTable1, buscar, buffer.toString().toUpperCase(), lista);
//                            String buscar=(String) jComboBox4.getSelectedItem();
//                            Drive.CargarpersonalSimple(jTable2,buscar, buffer.toString().toUpperCase(),lista);
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
                        jButton1.setEnabled(false);
                        jButton2.setEnabled(false);
                        jTable1.setEnabled(false);
                        boolean mensaje=false;
                        // <editor-fold defaultstate="collapsed" desc="Actualizar tarea">
                        if (tar.getLugar().getIdLugar()!=lu.getIdLugar()) {
                            // <editor-fold defaultstate="collapsed" desc="Auditoria">
                            Auditoria audi=new Auditoria();
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
                        }
                        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
                        int c = 0;
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
                                boolean b= (Boolean) modelo.getValueAt(c, 0);
                                if(b){
                                    Personal person = (Personal) modelo.getValueAt(c, 1);
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
                                    if (Drive.DisponibilidadAdm(person,inicioo, finn, listini, items,tar.getIdTarea())) {
                                        band=true;
                                    }else{
                                        band=false;
                                        JOptionPane.showMessageDialog(null,"No existe disponibilidad", "Actualizar clase",JOptionPane.ERROR_MESSAGE);
                                        break;
                                    }
                                }
                                c++;
                            }
                            if(band==true){
                                Date inicio = dateChooserCombo1.getSelectedDate().getTime();
                                Date fin = dateChooserCombo2.getSelectedDate().getTime();
                                Date aux1=tar.getDiaInicio();
                                Date aux2=tar.getDiaInicio();
                                if(aux1.getDate()!=inicio.getDate()||aux1.getMonth()!=inicio.getMonth()||aux1.getYear()!=inicio.getYear()){
                                    // <editor-fold defaultstate="collapsed" desc="Auditoria">
                                    Auditoria audi=new Auditoria();
                                    audi.setPersonalByIdAuditor(adm);
                                    audi.setOperacion("Actualizar");
                                    audi.setFecha(new Date());
                                    audi.setTarea(tar);
                                    audi.setCampo("Fecha inicio");
                                    //                                    SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                                    audi.setElementoAnterior(formateador.format(aux1));
                                    audi.setElementoNuevo(formateador.format(inicio));
                                    audi.guardarAuditoria(audi);
                                    // </editor-fold>
                                    tar.setDiaInicio(inicio);
                                    tar.ActualizarTarea(tar);
                                    mensaje=true;
                                }
                                if(aux2.getDate()!=fin.getDate()||aux2.getMonth()!=fin.getMonth()||aux2.getYear()!=fin.getYear()){
                                    // <editor-fold defaultstate="collapsed" desc="Auditoria">
                                    Auditoria audi=new Auditoria();
                                    audi.setPersonalByIdAuditor(adm);
                                    audi.setOperacion("Actualizar");
                                    audi.setFecha(new Date());
                                    audi.setTarea(tar);
                                    audi.setCampo("Fecha fin");
                                    audi.setElementoAnterior(formateador.format(aux2));
                                    audi.setElementoNuevo(formateador.format(fin));
                                    audi.guardarAuditoria(audi);
                                    // </editor-fold>
                                    tar.setDiaFin(fin);
                                    tar.ActualizarTarea(tar);
                                    mensaje=true;
                                }
                                String com=(String) jComboBox2.getSelectedItem();
                                if(!tar.getComentario().equals(com)){
                                    // <editor-fold defaultstate="collapsed" desc="Auditoria">
                                    Auditoria audi=new Auditoria();
                                    audi.setPersonalByIdAuditor(adm);
                                    audi.setOperacion("Actualizar");
                                    audi.setFecha(new Date());
                                    audi.setTarea(tar);
                                    audi.setCampo("Comentario");
                                    audi.setElementoAnterior(tar.getComentario());
                                    audi.setElementoNuevo(com);
                                    audi.guardarAuditoria(audi);
                                    // </editor-fold>
                                    tar.setComentario(com);
                                    tar.ActualizarTarea(tar);
                                    mensaje=true;
                                }
                                tar.BorrarTodo();
                                Drive=new Controlador();
                                tar=(Tarea) Drive.PERSISTENCIA.getTarea(tar.getIdTarea()).iterator().next();
                                c = 0;
                                //
                                Iterator li=lista.iterator();
                                while (li.hasNext()) {
//                                while (jTable1.getRowCount() != c) {
//                                    Personal person = (Personal) modelo.getValueAt(c, 0);
                                    int i=(Integer) li.next();
                                    Personal person = (Personal) Drive.PERSISTENCIA.getPersonal(i).iterator().next();
                                    Agenda age=tar.ObtenerAgenda(person.getIdPersonal());
                                    if(age.getId()==null){
                                        AgendaId idage = new AgendaId(person.getIdPersonal(), tar.getIdTarea());
                                        age = new Agenda();
                                        age.setId(idage);
                                        age.setComentario(null);
                                        age.setAnolectivo(Drive.getAnoLectivo());
                                        age.guardarAgenda(age);
                                    }
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
                                        Date aux=new Date();

                                        while (otro2.compareTo(fin) <= 0) {
                                            Mes mes = anio.getMes(otro2.getMonth());
                                            aux.setYear(inicio.getYear());
                                            aux.setMonth(otro2.getMonth());
                                            aux.setDate(otro2.getDate());
                                            if(Drive.ExisteFecha(tar, aux)){
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
                                            }else{
                                                otro2 = Drive.sumarFechasDias(otro2, 7);
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
//                                    c++;
                                }
                            }
                    }
                    if(mensaje==true){
                        JOptionPane.showMessageDialog(null,"La tarea se actualizó correctamente","Actualizar tarea",JOptionPane.INFORMATION_MESSAGE);
                    }
                    Frame vp = new JFrameConsultaActividades(Drive, adm);
                    this.dispose();
                    vp.show();
                    // </editor-fold>
                }
                jButton1.setEnabled(true);
                jButton2.setEnabled(true);
                jTable1.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "Los campos con '*' son obligatorios", "Registrar Clase", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Debe ingresar un personal", "Registrar Clase", JOptionPane.ERROR_MESSAGE);
        }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Registrar Clase", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (tar.getIdTarea() == null) {
            if (!jTextField1.getText().isEmpty()) {
                int confirmado = JOptionPane.showConfirmDialog(null, "¿Desea cancelar la registración de la clase?", "Registrar clase", JOptionPane.YES_NO_OPTION);
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
            if (!cambio == true) {
                int confirmado = JOptionPane.showConfirmDialog(null, "¿Desea cancelar la actualización de la clase?", "Actualizar clase", JOptionPane.YES_NO_OPTION);
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

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        if(jTextField1.getText().length()==20) evt.consume();
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jFormattedTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField1ActionPerformed

    private void jFormattedTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextField1FocusLost
        try {
            if(jCheckBox1.isSelected()){
                String hora=jFormattedTextField1.getText();
                String h  = hora.substring(0,2);
                String m  = hora.substring(3,5);
                int conta_hora = Integer.parseInt(h);
                int conta_minuto = Integer.parseInt(m);
                if(conta_hora > 23 || conta_minuto > 59) {
                    JOptionPane.showMessageDialog(null, "Ingrese correctamente la hora" ,"Registrar Clase",JOptionPane.ERROR_MESSAGE);
                    jFormattedTextField1.setText("00:00");
                    return;
                }
                Date inici=dateChooserCombo1.getSelectedDate().getTime();
                Date fin=dateChooserCombo2.getSelectedDate().getTime();
                Date aux = inici;
                while (aux.getDay() != 1) {
                    aux = Controlador.sumarFechasDias(aux, 1);
                }
                if (aux.compareTo(fin)>0){
                    JOptionPane.showMessageDialog(null,"Ingrese correctamente el inicio y fin porque no hay ningun lunes entre esas fechas","Registrar Clase", JOptionPane.ERROR_MESSAGE);
                    jCheckBox1.setSelected(false);
                }else{
                    SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                    Date inicio = formateador.parse(jFormattedTextField1.getText());
                    Date fe=dateChooserCombo1.getSelectedDate().getTime();
                    Date ahora = new Date();
                    if(fe.getDate()==aux.getDate() && fe.getMonth()==aux.getMonth() && fe.getYear()==aux.getYear()){
                        if(fe.getDate()==ahora.getDate() && fe.getMonth()==ahora.getMonth() && fe.getYear()==ahora.getYear()){
                            if (fe.compareTo(aux) <= 0) {
                                aux.setDate(inicio.getDate());
                                aux.setMonth(inicio.getMonth());
                                aux.setYear(inicio.getYear());
                                if (inicio.compareTo(aux) <= 0) {
                                    JOptionPane.showMessageDialog(null, "El inicio de la clase debe ser mayor que las: " + formateador.format(aux), "Registrar Clase", JOptionPane.ERROR_MESSAGE);
                                    jFormattedTextField1.setText("00:00");
                                    jFormattedTextField2.setText("00:00");
                                    return;
                                }
                            }
                        }
                    }
                }
            }
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
            jFormattedTextField1.setText("00:00");
        }
    }//GEN-LAST:event_jFormattedTextField1FocusLost

    private void jFormattedTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField2ActionPerformed

    private void jFormattedTextField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextField2FocusLost
        try{
            if(jCheckBox1.isSelected()){
                String hora=jFormattedTextField2.getText();
                String h  = hora.substring(0,2);
                String m  = hora.substring(3,5);
                int conta_hora = Integer.parseInt(h);
                int conta_minuto = Integer.parseInt(m);
                if(conta_hora > 23 || conta_minuto > 59) {
                    JOptionPane.showMessageDialog(null, "Ingrese correctamente la hora" ,"Registrar Clase",JOptionPane.ERROR_MESSAGE);
                    jFormattedTextField2.setText("00:00");
                    return;
                }
            }
            SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
            Date inicio = formateador.parse(jFormattedTextField1.getText());
            Date fin = formateador.parse(jFormattedTextField2.getText());
            if(inicio.compareTo(fin)>=0){
                JOptionPane.showMessageDialog(null,"El horario de fin debe ser mayor al horario de inicio","Registrar Clase", JOptionPane.ERROR_MESSAGE);
                jFormattedTextField1.setText("00:00");
                jFormattedTextField2.setText("00:00");
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
            JOptionPane.showMessageDialog(null,"Ingrese correctamente la hora","Registrar Clase", JOptionPane.ERROR_MESSAGE);
            jFormattedTextField2.setText("00:00");
        }

    }//GEN-LAST:event_jFormattedTextField2FocusLost

    private void dateChooserCombo1OnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_dateChooserCombo1OnSelectionChange
        try{
            Date inicio=dateChooserCombo1.getSelectedDate().getTime();
            Date fin=dateChooserCombo2.getSelectedDate().getTime();
            Date fecha=new Date();
            Anolectivo an=Drive.getPrimerEstablecimiento().getAnoLectivo(fecha.getYear()+1900);
            if(inicio.compareTo(fin)>0 || an.getInicio().compareTo(inicio)>0 || an.getFin().compareTo(fin)<0){
                JOptionPane.showMessageDialog(null,"La fecha de inicio debe ser menor que la fecha de fin y estar contemplado dentro del año lectivo","Registrar clase",JOptionPane.ERROR_MESSAGE);
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
            Date fecha=new Date();
            Anolectivo an=Drive.getPrimerEstablecimiento().getAnoLectivo(fecha.getYear()+1900);
            if(inicio.compareTo(fin)>0 || an.getInicio().compareTo(inicio)>0 || an.getFin().compareTo(fin)<0){
                JOptionPane.showMessageDialog(null,"La fecha de inicio debe ser menor que la fecha de fin y estar contemplado dentro del año lectivo","Registrar clase",JOptionPane.ERROR_MESSAGE);
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

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
        if(jCheckBox1.isSelected()){
            jFormattedTextField1.setEnabled(true);
            jFormattedTextField2.setEnabled(true);
            jFormattedTextField1.setEditable(true);
            jFormattedTextField2.setEditable(true);
            jFormattedTextField1.setText("00:00");
            jFormattedTextField2.setText("00:00");
            ////////////////////////////////////////////
            Date inicio=dateChooserCombo1.getSelectedDate().getTime();
            Date fin=dateChooserCombo2.getSelectedDate().getTime();
            Date aux = inicio;
            while (aux.getDay() != 1) {
                aux = Controlador.sumarFechasDias(aux, 1);
            }
            if (aux.compareTo(fin)>0){
                JOptionPane.showMessageDialog(null,"Ingrese correctamente el inicio y fin porque no hay ningun lunes entre esas fechas","Registrar Clase", JOptionPane.ERROR_MESSAGE);
                jCheckBox1.setSelected(false);
            }
        }else{
            jFormattedTextField1.setEnabled(false);
            jFormattedTextField2.setEnabled(false);
            jFormattedTextField1.setEditable(true);
            jFormattedTextField2.setEditable(true);
            jFormattedTextField1.setText("");
            jFormattedTextField2.setText("");
        }
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void jCheckBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox2ItemStateChanged
        if(jCheckBox2.isSelected()){
            jFormattedTextField3.setEnabled(true);
            jFormattedTextField4.setEnabled(true);
            jFormattedTextField3.setEditable(true);
            jFormattedTextField4.setEditable(true);
            jFormattedTextField3.setText("00:00");
            jFormattedTextField4.setText("00:00");
            ////////////////////////////////////////////
            Date inicio=dateChooserCombo1.getSelectedDate().getTime();
            Date fin=dateChooserCombo2.getSelectedDate().getTime();
            Date aux = inicio;
            while (aux.getDay() != 2) {
                aux = Controlador.sumarFechasDias(aux, 1);
            }
            if (aux.compareTo(fin)>0){
                JOptionPane.showMessageDialog(null,"Ingrese correctamente el inicio y fin porque no hay ningun martes entre esas fechas","Registrar Clase", JOptionPane.ERROR_MESSAGE);
                jCheckBox2.setSelected(false);
            }
        }else{
            jFormattedTextField3.setEnabled(false);
            jFormattedTextField4.setEnabled(false);
            jFormattedTextField3.setEditable(true);
            jFormattedTextField4.setEditable(true);
            jFormattedTextField3.setText("");
            jFormattedTextField4.setText("");
        }
    }//GEN-LAST:event_jCheckBox2ItemStateChanged

    private void jCheckBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox3ItemStateChanged
        if(jCheckBox3.isSelected()){
            jFormattedTextField5.setEnabled(true);
            jFormattedTextField6.setEnabled(true);
            jFormattedTextField5.setEditable(true);
            jFormattedTextField6.setEditable(true);
            jFormattedTextField5.setText("00:00");
            jFormattedTextField6.setText("00:00");
            ////////////////////////////////////////////
            Date inicio=dateChooserCombo1.getSelectedDate().getTime();
            Date fin=dateChooserCombo2.getSelectedDate().getTime();
            Date aux = inicio;
            while (aux.getDay() != 3) {
                aux = Controlador.sumarFechasDias(aux, 1);
            }
            if (aux.compareTo(fin)>0){
                JOptionPane.showMessageDialog(null,"Ingrese correctamente el inicio y fin porque no hay ningun miercoles entre esas fechas","Registrar Clase", JOptionPane.ERROR_MESSAGE);
                jCheckBox3.setSelected(false);
            }
        }else{
            jFormattedTextField5.setEnabled(false);
            jFormattedTextField6.setEnabled(false);
            jFormattedTextField5.setEditable(true);
            jFormattedTextField6.setEditable(true);
            jFormattedTextField5.setText("");
            jFormattedTextField6.setText("");
        }
    }//GEN-LAST:event_jCheckBox3ItemStateChanged

    private void jCheckBox4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox4ItemStateChanged
        if(jCheckBox4.isSelected()){
            jFormattedTextField7.setEnabled(true);
            jFormattedTextField8.setEnabled(true);
            jFormattedTextField7.setEditable(true);
            jFormattedTextField8.setEditable(true);
            jFormattedTextField7.setText("00:00");
            jFormattedTextField8.setText("00:00");
            ////////////////////////////////////////////
            Date inicio=dateChooserCombo1.getSelectedDate().getTime();
            Date fin=dateChooserCombo2.getSelectedDate().getTime();
            Date aux = inicio;
            while (aux.getDay() != 4) {
                aux = Controlador.sumarFechasDias(aux, 1);
            }
            if (aux.compareTo(fin)>0){
                JOptionPane.showMessageDialog(null,"Ingrese correctamente el inicio y fin porque no hay ningun jueves entre esas fechas","Registrar Clase", JOptionPane.ERROR_MESSAGE);
                jCheckBox4.setSelected(false);
            }
        }else{
            jFormattedTextField7.setEnabled(false);
            jFormattedTextField8.setEnabled(false);
            jFormattedTextField7.setEditable(true);
            jFormattedTextField8.setEditable(true);
            jFormattedTextField7.setText("");
            jFormattedTextField8.setText("");
        }
    }//GEN-LAST:event_jCheckBox4ItemStateChanged

    private void jCheckBox5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox5ItemStateChanged
        if(jCheckBox5.isSelected()){
            jFormattedTextField10.setEnabled(true);
            jFormattedTextField9.setEnabled(true);
            jFormattedTextField10.setEditable(true);
            jFormattedTextField9.setEditable(true);
            jFormattedTextField9.setText("00:00");
            jFormattedTextField10.setText("00:00");
            ////////////////////////////////////////////
            Date inicio=dateChooserCombo1.getSelectedDate().getTime();
            Date fin=dateChooserCombo2.getSelectedDate().getTime();
            Date aux = inicio;
            while (aux.getDay() != 5) {
                aux = Controlador.sumarFechasDias(aux, 1);
            }
            if (aux.compareTo(fin)>0){
                JOptionPane.showMessageDialog(null,"Ingrese correctamente el inicio y fin porque no hay ningun viernes entre esas fechas","Registrar Clase", JOptionPane.ERROR_MESSAGE);
                jCheckBox5.setSelected(false);
            }
        }else{
            jFormattedTextField10.setEnabled(false);
            jFormattedTextField9.setEnabled(false);
            jFormattedTextField10.setEditable(true);
            jFormattedTextField9.setEditable(true);
            jFormattedTextField9.setText("");
            jFormattedTextField10.setText("");
        }
    }//GEN-LAST:event_jCheckBox5ItemStateChanged

    private void jCheckBox6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox6ItemStateChanged
        if(jCheckBox6.isSelected()){
            jFormattedTextField11.setEnabled(true);
            jFormattedTextField12.setEnabled(true);
            jFormattedTextField11.setEditable(true);
            jFormattedTextField12.setEditable(true);
            jFormattedTextField11.setText("00:00");
            jFormattedTextField12.setText("00:00");
            ////////////////////////////////////////////
            Date inicio=dateChooserCombo1.getSelectedDate().getTime();
            Date fin=dateChooserCombo2.getSelectedDate().getTime();
            Date aux = inicio;
            while (aux.getDay() != 6) {
                aux = Controlador.sumarFechasDias(aux, 1);
            }
            if (aux.compareTo(fin)>0){
                JOptionPane.showMessageDialog(null,"Ingrese correctamente el inicio y fin porque no hay ningun sabado entre esas fechas","Registrar Clase", JOptionPane.ERROR_MESSAGE);
                jCheckBox6.setSelected(false);
            }
        }else{
            jFormattedTextField11.setEnabled(false);
            jFormattedTextField12.setEnabled(false);
            jFormattedTextField11.setEditable(true);
            jFormattedTextField12.setEditable(true);
            jFormattedTextField11.setText("");
            jFormattedTextField12.setText("");
        }
    }//GEN-LAST:event_jCheckBox6ItemStateChanged

    private void jFormattedTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField3ActionPerformed

    private void jFormattedTextField3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextField3FocusLost
        try {
            if(jCheckBox2.isSelected()){
                String hora=jFormattedTextField3.getText();
                String h  = hora.substring(0,2);
                String m  = hora.substring(3,5);
                int conta_hora = Integer.parseInt(h);
                int conta_minuto = Integer.parseInt(m);
                if(conta_hora > 23 || conta_minuto > 59) {
                    JOptionPane.showMessageDialog(null, "Ingrese correctamente la hora" ,"Registrar Clase",JOptionPane.ERROR_MESSAGE);
                    jFormattedTextField3.setText("00:00");
                    return;
                }
                Date inici = dateChooserCombo1.getSelectedDate().getTime();
                Date fin = dateChooserCombo2.getSelectedDate().getTime();
                Date aux = inici;
                while (aux.getDay() != 2) {
                    aux = Controlador.sumarFechasDias(aux, 1);
                }
                if (aux.compareTo(fin) > 0) {
                    JOptionPane.showMessageDialog(null, "Ingrese correctamente el inicio y fin porque no hay ningun martes entre esas fechas", "Registrar Clase", JOptionPane.ERROR_MESSAGE);
                    jCheckBox2.setSelected(false);
                } else {
                    SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                    Date inicio = formateador.parse(jFormattedTextField3.getText());
                    Date fe = dateChooserCombo1.getSelectedDate().getTime();
                    Date ahora = new Date();
                    if(fe.getDate()==aux.getDate() && fe.getMonth()==aux.getMonth() && fe.getYear()==aux.getYear()){
                        if(fe.getDate()==ahora.getDate() && fe.getMonth()==ahora.getMonth() && fe.getYear()==ahora.getYear()){
                            if (fe.compareTo(aux) <= 0) {
                                aux.setDate(inicio.getDate());
                                aux.setMonth(inicio.getMonth());
                                aux.setYear(inicio.getYear());
                                if (inicio.compareTo(aux) <= 0) {
                                    JOptionPane.showMessageDialog(null, "El inicio de la clase debe ser mayor que las: " + formateador.format(aux), "Registrar Clase", JOptionPane.ERROR_MESSAGE);
                                    jFormattedTextField3.setText("00:00");
                                    jFormattedTextField4.setText("00:00");
                                    return;
                                }
                            }
                        }
                    }
                }
            }
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
            jFormattedTextField3.setText("00:00");
        }
    }//GEN-LAST:event_jFormattedTextField3FocusLost

    private void jFormattedTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField4ActionPerformed

    private void jFormattedTextField4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextField4FocusLost
        try{
            if(jCheckBox2.isSelected()){
                String hora=jFormattedTextField4.getText();
                String h  = hora.substring(0,2);
                String m  = hora.substring(3,5);
                int conta_hora = Integer.parseInt(h);
                int conta_minuto = Integer.parseInt(m);
                if(conta_hora > 23 || conta_minuto > 59) {
                    JOptionPane.showMessageDialog(null, "Ingrese correctamente la hora" ,"Registrar Clase",JOptionPane.ERROR_MESSAGE);
                    jFormattedTextField4.setText("00:00");
                    return;
                }
            }
            SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
            Date inicio = formateador.parse(jFormattedTextField3.getText());
            Date fin = formateador.parse(jFormattedTextField4.getText());
            if(inicio.compareTo(fin)>=0){
                JOptionPane.showMessageDialog(null,"El horario de fin debe ser mayor al horario de inicio","Registrar Clase", JOptionPane.ERROR_MESSAGE);
                jFormattedTextField3.setText("00:00");
                jFormattedTextField4.setText("00:00");
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
            JOptionPane.showMessageDialog(null,"Ingrese correctamente la hora","Registrar Clase", JOptionPane.ERROR_MESSAGE);
            jFormattedTextField4.setText("00:00");
        }
    }//GEN-LAST:event_jFormattedTextField4FocusLost

    private void jFormattedTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField5ActionPerformed

    private void jFormattedTextField5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextField5FocusLost
        try {
            if(jCheckBox3.isSelected()){
                String hora=jFormattedTextField5.getText();
                String h  = hora.substring(0,2);
                String m  = hora.substring(3,5);
                int conta_hora = Integer.parseInt(h);
                int conta_minuto = Integer.parseInt(m);
                if(conta_hora > 23 || conta_minuto > 59) {
                    JOptionPane.showMessageDialog(null, "Ingrese correctamente la hora" ,"Registrar Clase",JOptionPane.ERROR_MESSAGE);
                    jFormattedTextField5.setText("00:00");
                    return;
                }
                Date inici = dateChooserCombo1.getSelectedDate().getTime();
                Date fin = dateChooserCombo2.getSelectedDate().getTime();
                Date aux = inici;
                while (aux.getDay() != 3) {
                    aux = Controlador.sumarFechasDias(aux, 1);
                }
                if (aux.compareTo(fin) > 0) {
                    JOptionPane.showMessageDialog(null, "Ingrese correctamente el inicio y fin porque no hay ningun miercoles entre esas fechas", "Registrar Clase", JOptionPane.ERROR_MESSAGE);
                    jCheckBox3.setSelected(false);
                } else {
                    SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                    Date inicio = formateador.parse(jFormattedTextField5.getText());
                    Date fe = dateChooserCombo1.getSelectedDate().getTime();
                    Date ahora = new Date();
                    if(fe.getDate()==aux.getDate() && fe.getMonth()==aux.getMonth() && fe.getYear()==aux.getYear()){
                        if(fe.getDate()==ahora.getDate() && fe.getMonth()==ahora.getMonth() && fe.getYear()==ahora.getYear()){
                            if (fe.compareTo(aux) <= 0) {
                                aux.setDate(inicio.getDate());
                                aux.setMonth(inicio.getMonth());
                                aux.setYear(inicio.getYear());
                                if (inicio.compareTo(aux) <= 0) {
                                    JOptionPane.showMessageDialog(null, "El inicio de la clase debe ser mayor que las: " + formateador.format(aux), "Registrar Clase", JOptionPane.ERROR_MESSAGE);
                                    jFormattedTextField5.setText("00:00");
                                    jFormattedTextField6.setText("00:00");
                                    return;
                                }
                            }
                        }
                    }
                }
            }
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
            jFormattedTextField5.setText("00:00");
        }
    }//GEN-LAST:event_jFormattedTextField5FocusLost

    private void jFormattedTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField6ActionPerformed

    private void jFormattedTextField6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextField6FocusLost
        try{
            if(jCheckBox3.isSelected()){
                String hora=jFormattedTextField6.getText();
                String h  = hora.substring(0,2);
                String m  = hora.substring(3,5);
                int conta_hora = Integer.parseInt(h);
                int conta_minuto = Integer.parseInt(m);
                if(conta_hora > 23 || conta_minuto > 59) {
                    JOptionPane.showMessageDialog(null, "Ingrese correctamente la hora" ,"Registrar Clase",JOptionPane.ERROR_MESSAGE);
                    jFormattedTextField6.setText("00:00");
                    return;
                }
            }
            SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
            Date inicio = formateador.parse(jFormattedTextField5.getText());
            Date fin = formateador.parse(jFormattedTextField6.getText());
            if(inicio.compareTo(fin)>=0){
                JOptionPane.showMessageDialog(null,"El horario de fin debe ser mayor al horario de inicio","Registrar Clase", JOptionPane.ERROR_MESSAGE);
                jFormattedTextField5.setText("00:00");
                jFormattedTextField6.setText("00:00");
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
            JOptionPane.showMessageDialog(null,"Ingrese correctamente la hora","Registrar Clase", JOptionPane.ERROR_MESSAGE);
            jFormattedTextField6.setText("00:00");
        }
    }//GEN-LAST:event_jFormattedTextField6FocusLost

    private void jFormattedTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField7ActionPerformed

    private void jFormattedTextField7FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextField7FocusLost
        try{
            if(jCheckBox4.isSelected()){
                String hora=jFormattedTextField7.getText();
                String h  = hora.substring(0,2);
                String m  = hora.substring(3,5);
                int conta_hora = Integer.parseInt(h);
                int conta_minuto = Integer.parseInt(m);
                if(conta_hora > 23 || conta_minuto > 59) {
                    JOptionPane.showMessageDialog(null, "Ingrese correctamente la hora" ,"Registrar Clase",JOptionPane.ERROR_MESSAGE);
                    jFormattedTextField7.setText("00:00");
                    return;
                }
            }
            SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
            Date inicio = formateador.parse(jFormattedTextField8.getText());
            Date fin = formateador.parse(jFormattedTextField7.getText());
            if(inicio.compareTo(fin)>=0){
                JOptionPane.showMessageDialog(null,"El horario de fin debe ser mayor al horario de inicio","Registrar Clase", JOptionPane.ERROR_MESSAGE);
                jFormattedTextField8.setText("00:00");
                jFormattedTextField7.setText("00:00");
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
            JOptionPane.showMessageDialog(null,"Ingrese correctamente la hora","Registrar Clase", JOptionPane.ERROR_MESSAGE);
            jFormattedTextField7.setText("00:00");
        }
    }//GEN-LAST:event_jFormattedTextField7FocusLost

    private void jFormattedTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField8ActionPerformed

    private void jFormattedTextField8FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextField8FocusLost
        try {
            if(jCheckBox4.isSelected()){
                String hora=jFormattedTextField8.getText();
                String h  = hora.substring(0,2);
                String m  = hora.substring(3,5);
                int conta_hora = Integer.parseInt(h);
                int conta_minuto = Integer.parseInt(m);
                if(conta_hora > 23 || conta_minuto > 59) {
                    JOptionPane.showMessageDialog(null, "Ingrese correctamente la hora" ,"Registrar Clase",JOptionPane.ERROR_MESSAGE);
                    jFormattedTextField8.setText("00:00");
                    return;
                }
                Date inici = dateChooserCombo1.getSelectedDate().getTime();
                Date fin = dateChooserCombo2.getSelectedDate().getTime();
                Date aux = inici;
                while (aux.getDay() != 4) {
                    aux = Controlador.sumarFechasDias(aux, 1);
                }
                if (aux.compareTo(fin) > 0) {
                    JOptionPane.showMessageDialog(null, "Ingrese correctamente el inicio y fin porque no hay ningun jueves entre esas fechas", "Registrar Clase", JOptionPane.ERROR_MESSAGE);
                    jCheckBox4.setSelected(false);
                } else {
                    SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                    Date inicio = formateador.parse(jFormattedTextField8.getText());
                    Date fe = dateChooserCombo1.getSelectedDate().getTime();
                    Date ahora = new Date();
                    if(fe.getDate()==aux.getDate() && fe.getMonth()==aux.getMonth() && fe.getYear()==aux.getYear()){
                        if(fe.getDate()==ahora.getDate() && fe.getMonth()==ahora.getMonth() && fe.getYear()==ahora.getYear()){
                            if (fe.compareTo(aux) <= 0) {
                                aux.setDate(inicio.getDate());
                                aux.setMonth(inicio.getMonth());
                                aux.setYear(inicio.getYear());
                                if (inicio.compareTo(aux) <= 0) {
                                    JOptionPane.showMessageDialog(null, "El inicio de la clase debe ser mayor que las: " + formateador.format(aux), "Registrar Clase", JOptionPane.ERROR_MESSAGE);
                                    jFormattedTextField8.setText("00:00");
                                    jFormattedTextField7.setText("00:00");
                                    return;
                                }
                            }
                        }
                    }
                }
            }
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
            jFormattedTextField8.setText("00:00");
        }
    }//GEN-LAST:event_jFormattedTextField8FocusLost

    private void jFormattedTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField9ActionPerformed

    private void jFormattedTextField9FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextField9FocusLost
        try {
            if(jCheckBox5.isSelected()){
                String hora=jFormattedTextField9.getText();
                String h  = hora.substring(0,2);
                String m  = hora.substring(3,5);
                int conta_hora = Integer.parseInt(h);
                int conta_minuto = Integer.parseInt(m);
                if(conta_hora > 23 || conta_minuto > 59) {
                    JOptionPane.showMessageDialog(null, "Ingrese correctamente la hora" ,"Registrar Clase",JOptionPane.ERROR_MESSAGE);
                    jFormattedTextField9.setText("00:00");
                    return;
                }
                Date inici = dateChooserCombo1.getSelectedDate().getTime();
                Date fin = dateChooserCombo2.getSelectedDate().getTime();
                Date aux = inici;
                while (aux.getDay() != 5) {
                    aux = Controlador.sumarFechasDias(aux, 1);
                }
                if (aux.compareTo(fin) > 0) {
                    JOptionPane.showMessageDialog(null, "Ingrese correctamente el inicio y fin porque no hay ningun viernes entre esas fechas", "Registrar Clase", JOptionPane.ERROR_MESSAGE);
                    jCheckBox5.setSelected(false);
                } else {
                    SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                    Date inicio = formateador.parse(jFormattedTextField9.getText());
                    Date fe = dateChooserCombo1.getSelectedDate().getTime();
                    Date ahora = new Date();
                    if(fe.getDate()==aux.getDate() && fe.getMonth()==aux.getMonth() && fe.getYear()==aux.getYear()){
                        if(fe.getDate()==ahora.getDate() && fe.getMonth()==ahora.getMonth() && fe.getYear()==ahora.getYear()){
                            if (fe.compareTo(aux) <= 0) {
                                aux.setDate(inicio.getDate());
                                aux.setMonth(inicio.getMonth());
                                aux.setYear(inicio.getYear());
                                if (inicio.compareTo(aux) <= 0) {
                                    JOptionPane.showMessageDialog(null, "El inicio de la clase debe ser mayor que las: " + formateador.format(aux), "Registrar Clase", JOptionPane.ERROR_MESSAGE);
                                    jFormattedTextField9.setText("00:00");
                                    jFormattedTextField10.setText("00:00");
                                    return;
                                }
                            }
                        }
                    }
                }
            }
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
            jFormattedTextField9.setText("00:00");
        }
    }//GEN-LAST:event_jFormattedTextField9FocusLost

    private void jFormattedTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField10ActionPerformed

    private void jFormattedTextField10FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextField10FocusLost
        try{
            if(jCheckBox5.isSelected()){
                String hora=jFormattedTextField10.getText();
                String h  = hora.substring(0,2);
                String m  = hora.substring(3,5);
                int conta_hora = Integer.parseInt(h);
                int conta_minuto = Integer.parseInt(m);
                if(conta_hora > 23 || conta_minuto > 59) {
                    JOptionPane.showMessageDialog(null, "Ingrese correctamente la hora" ,"Registrar Clase",JOptionPane.ERROR_MESSAGE);
                    jFormattedTextField10.setText("00:00");
                    return;
                }
            }
            SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
            Date inicio = formateador.parse(jFormattedTextField9.getText());
            Date fin = formateador.parse(jFormattedTextField10.getText());
            if(inicio.compareTo(fin)>=0){
                JOptionPane.showMessageDialog(null,"El horario de fin debe ser mayor al horario de inicio","Registrar Clase", JOptionPane.ERROR_MESSAGE);
                jFormattedTextField9.setText("00:00");
                jFormattedTextField10.setText("00:00");
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
            JOptionPane.showMessageDialog(null,"Ingrese correctamente la hora","Registrar Clase", JOptionPane.ERROR_MESSAGE);
            jFormattedTextField10.setText("00:00");
        }
    }//GEN-LAST:event_jFormattedTextField10FocusLost

    private void jFormattedTextField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField11ActionPerformed

    private void jFormattedTextField11FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextField11FocusLost
        try {
            if(jCheckBox6.isSelected()){
                String hora=jFormattedTextField11.getText();
                String h  = hora.substring(0,2);
                String m  = hora.substring(3,5);
                int conta_hora = Integer.parseInt(h);
                int conta_minuto = Integer.parseInt(m);
                if(conta_hora > 23 || conta_minuto > 59) {
                    JOptionPane.showMessageDialog(null, "Ingrese correctamente la hora" ,"Registrar Clase",JOptionPane.ERROR_MESSAGE);
                    jFormattedTextField11.setText("00:00");
                    return;
                }
                Date inici = dateChooserCombo1.getSelectedDate().getTime();
                Date fin = dateChooserCombo2.getSelectedDate().getTime();
                Date aux = inici;
                while (aux.getDay() != 6) {
                    aux = Controlador.sumarFechasDias(aux, 1);
                }
                if (aux.compareTo(fin) > 0) {
                    JOptionPane.showMessageDialog(null, "Ingrese correctamente el inicio y fin porque no hay ningun sabado entre esas fechas", "Registrar Clase", JOptionPane.ERROR_MESSAGE);
                    jCheckBox6.setSelected(false);
                } else {
                    SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                    Date inicio = formateador.parse(jFormattedTextField11.getText());
                    Date fe = dateChooserCombo1.getSelectedDate().getTime();
                    Date ahora = new Date();
                    if(fe.getDate()==aux.getDate() && fe.getMonth()==aux.getMonth() && fe.getYear()==aux.getYear()){
                        if(fe.getDate()==ahora.getDate() && fe.getMonth()==ahora.getMonth() && fe.getYear()==ahora.getYear()){
                            if (fe.compareTo(aux) <= 0) {
                                aux.setDate(inicio.getDate());
                                aux.setMonth(inicio.getMonth());
                                aux.setYear(inicio.getYear());
                                if (inicio.compareTo(aux) <= 0) {
                                    JOptionPane.showMessageDialog(null, "El inicio de la clase debe ser mayor que las: " + formateador.format(aux), "Registrar Clase", JOptionPane.ERROR_MESSAGE);
                                    jFormattedTextField11.setText("00:00");
                                    jFormattedTextField12.setText("00:00");
                                    return;
                                }
                            }
                        }
                    }
                }
            }
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
            jFormattedTextField11.setText("00:00");
        }
    }//GEN-LAST:event_jFormattedTextField11FocusLost

    private void jFormattedTextField12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField12ActionPerformed

    private void jFormattedTextField12FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextField12FocusLost
        try{
            if(jCheckBox6.isSelected()){
                String hora=jFormattedTextField12.getText();
                String h  = hora.substring(0,2);
                String m  = hora.substring(3,5);
                int conta_hora = Integer.parseInt(h);
                int conta_minuto = Integer.parseInt(m);
                if(conta_hora > 23 || conta_minuto > 59) {
                    JOptionPane.showMessageDialog(null, "Ingrese correctamente la hora" ,"Registrar Clase",JOptionPane.ERROR_MESSAGE);
                    jFormattedTextField12.setText("00:00");
                    return;
                }
            }
            SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
            Date inicio = formateador.parse(jFormattedTextField11.getText());
            Date fin = formateador.parse(jFormattedTextField12.getText());
            if(inicio.compareTo(fin)>=0){
                JOptionPane.showMessageDialog(null,"El horario de fin debe ser mayor al horario de inicio","Registrar Clase", JOptionPane.ERROR_MESSAGE);
                jFormattedTextField11.setText("00:00");
                jFormattedTextField12.setText("00:00");
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
            JOptionPane.showMessageDialog(null,"Ingrese correctamente la hora","Registrar Clase", JOptionPane.ERROR_MESSAGE);
            jFormattedTextField12.setText("00:00");
        }
    }//GEN-LAST:event_jFormattedTextField12FocusLost

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

    private void jLabel21MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel21MouseReleased

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

    private void jCheckBox7ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox7ItemStateChanged
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
    }//GEN-LAST:event_jCheckBox7ItemStateChanged

    private void jTable1ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jTable1ComponentResized
    }//GEN-LAST:event_jTable1ComponentResized

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        try {
            if(listini.isEmpty()){
                SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
//                    Date inicio = dateChooserCombo1.getSelectedDate().getTime();
//                    Date fin = dateChooserCombo2.getSelectedDate().getTime();
                    // <editor-fold defaultstate="collapsed" desc="verificar">
                    Date inn = new Date();
                    Date fii = new Date();
                    if (jCheckBox1.isSelected()) {
                        items.put(1, "LUNES");
                        inn = formateador.parse(jFormattedTextField1.getText());
                        fii = formateador.parse(jFormattedTextField2.getText());
                        Iniciofin aux = new Iniciofin();
                        aux.setInicio(inn);
                        aux.setFin(fii);
                        listini.put(1, aux);
                    }
                    if (jCheckBox2.isSelected()) {
                        items.put(2, "MARTES");
                        inn = formateador.parse(jFormattedTextField3.getText());
                        fii = formateador.parse(jFormattedTextField4.getText());
                        Iniciofin aux = new Iniciofin();
                        aux.setInicio(inn);
                        aux.setFin(fii);
                        listini.put(2, aux);
                    }
                    if (jCheckBox3.isSelected()) {
                        items.put(3, "MIERCOLES");
                        inn = formateador.parse(jFormattedTextField5.getText());
                        fii = formateador.parse(jFormattedTextField6.getText());
                        Iniciofin aux = new Iniciofin();
                        aux.setInicio(inn);
                        aux.setFin(fii);
                        listini.put(3, aux);
                    }
                    if (jCheckBox4.isSelected()) {
                        items.put(4, "JUEVES");
                        inn = formateador.parse(jFormattedTextField8.getText());
                        fii = formateador.parse(jFormattedTextField7.getText());
                        Iniciofin aux = new Iniciofin();
                        aux.setInicio(inn);
                        aux.setFin(fii);
                        listini.put(4, aux);
                    }
                    if (jCheckBox5.isSelected()) {
                        items.put(5, "VIERNES");
                        inn = formateador.parse(jFormattedTextField9.getText());
                        fii = formateador.parse(jFormattedTextField10.getText());
                        Iniciofin aux = new Iniciofin();
                        aux.setInicio(inn);
                        aux.setFin(fii);
                        listini.put(5, aux);
                    }
                    if (jCheckBox6.isSelected()) {
                        items.put(6, "SABADO");
                        inn = formateador.parse(jFormattedTextField11.getText());
                        fii = formateador.parse(jFormattedTextField12.getText());
                        Iniciofin aux = new Iniciofin();
                        aux.setInicio(inn);
                        aux.setFin(fii);
                        listini.put(6, aux);
                    }
//                            Date inicioo = dateChooserCombo1.getSelectedDate().getTime();
//                            Date finn = dateChooserCombo2.getSelectedDate().getTime();
                    // </editor-fold>
            }
            DefaultTableModel modelo = (DefaultTableModel)jTable1.getModel();
            int c=0;
            lista.removeAll(lista);
            while(jTable1.getRowCount()!=c){
                boolean d=(Boolean) modelo.getValueAt(c, 0);
                if(d){
                    Personal per=(Personal) modelo.getValueAt(c, 1);
                    lista.add(per);
                }
                c++;
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        String com=(String) jComboBox2.getSelectedItem();
        if(!tar.getComentario().equals(com)){
            cambio=true;
        }
    }//GEN-LAST:event_jComboBox2ItemStateChanged

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
//            java.util.logging.Logger.getLogger(JFrameAdministrativo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(JFrameAdministrativo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(JFrameAdministrativo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(JFrameAdministrativo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new JFrameAdministrativo().setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private datechooser.beans.DateChooserCombo dateChooserCombo2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
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
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
