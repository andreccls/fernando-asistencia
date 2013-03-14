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
    /** Creates new form JFrameClase */
    public JFrameClase(Controlador unDrive, Personal admin,int id,Tarea tarr) {
        this.adm=admin;
        this.Drive=unDrive;
        this.idsesion=id;
        this.tar=tarr;
        initComponents();

        Drive.CargarComboSituacionRevista(jComboBox2);
        int[] anchos1 = {85,65 ,65,200,85};
        for(int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchos1[i]);
        }
        DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
        modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
        jTable1.getColumnModel().getColumn(4).setCellRenderer(modelocentrar); 
        jTable1.getTableHeader().setDefaultRenderer(new HeaderRenderer(jTable1));
        ///ICONO EDITAR
        ImageIcon fot = new ImageIcon("src\\imagenes\\image.jpg");
        Icon icono1 = new ImageIcon(fot.getImage().getScaledInstance(jLabel18.getWidth(), jLabel18.getHeight(), Image.SCALE_DEFAULT));
        jLabel18.setIcon(icono1);
        jLabel18.repaint();
        ///ICONO ELIMINAR
        ImageIcon fott = new ImageIcon("src\\imagenes\\eliminar.gif");
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
            String dia=Drive.ObtenerDia(mayor.getDay());
            jComboBox3.setSelectedItem(dia);
            Agenda age=tar.getAgendas().iterator().next();
            Dia d=age.getDia2(mayor);
            Iniciofin ini = d.getIniciofins().iterator().next();
            SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
            jFormattedTextField1.setValue(formateador.format(ini.getInicio()));
            jFormattedTextField2.setValue(formateador.format(ini.getFin()));
            Date inicio = formateador.parse(jFormattedTextField1.getText());
            Date fin = formateador.parse(jFormattedTextField2.getText());
            Drive.LimpiarTabla(jTable2);
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            Iterator it=tar.getAgendas().iterator();
            while(it.hasNext()){
                Agenda agg=(Agenda) it.next();
                Personal per=agg.getPersonal();
                Revista rev=agg.getRevista();
                Object[] fila = new Object[5];
                fila[0] = dia;
                fila[1] = formateador.format(inicio);
                fila[2] = formateador.format(fin);
                fila[3] = per;
                fila[4] = rev;
                model.addRow(fila);
            }
            jTable1.setModel(model);
            
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Error al cargar el formulario","Registrar Clase", JOptionPane.ERROR_MESSAGE);
            }
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
        jComboBox3 = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
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

        jLabel5.setText("Dia:");

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

        try {
            jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
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

        try {
            jFormattedTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
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

        jButton3.setText("+");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("-");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel2.setText("Lugar:");

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

    jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES", "SABADO" }));
    jComboBox3.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            jComboBox3ItemStateChanged(evt);
        }
    });

    jTable1.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Dia", "Inicio", "Fin", "Profesor", "Sit. Revista"
        }
    ) {
        boolean[] canEdit = new boolean [] {
            false, false, false, false, false
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

    jLabel16.setText("*");

    jLabel17.setText("*");

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
            .addContainerGap()
            .addComponent(jLabel15)
            .addGap(18, 18, 18)
            .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jLabel6)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(106, Short.MAX_VALUE))
        .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
    );
    jPanel5Layout.setVerticalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel5Layout.createSequentialGroup()
            .addContainerGap()
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
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jButton1)
                    .addGap(18, 18, 18)
                    .addComponent(jButton2))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton3)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5)
                        .addComponent(jLabel1)
                        .addComponent(jLabel10)
                        .addComponent(jLabel12))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextField1)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dateChooserCombo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel14)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel11)
                                .addComponent(jLabel2)
                                .addComponent(jLabel13))
                            .addGap(19, 19, 19)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField3)
                                .addComponent(dateChooserCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(10, 10, 10))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel17)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel16))
                                .addComponent(jLabel8))))))
            .addContainerGap())
    );

    jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton3, jButton4});

    jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {dateChooserCombo1, jTextField1, jTextField2, jTextField3});

    jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2});

    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17))
                    .addGap(4, 4, 4)
                    .addComponent(jLabel7))
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
                        .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel2)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jLabel11)
                        .addComponent(dateChooserCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16))
                    .addGap(4, 4, 4)
                    .addComponent(jLabel8)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGap(34, 34, 34)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jButton3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jButton4))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButton2)
                .addComponent(jButton1))
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
        if(!jTextField1.getText().isEmpty()||!jTextField2.getText().isEmpty()||!jTextField3.getText().isEmpty()||!jTextField4.getText().isEmpty()){
            int confirmado = JOptionPane.showConfirmDialog(null,"¿Desea cancelar la registración de la clase?","Registrar clase",JOptionPane.YES_NO_OPTION);
            if (JOptionPane.OK_OPTION == confirmado){
                Frame vp=new JFramePrincipal(Drive,adm,idsesion);
               this.dispose();
                vp.show();
            }
        }else{
            Frame vp=new JFramePrincipal(Drive,adm,idsesion);
            this.dispose();
                vp.show();}

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
                if (!jTextField1.getText().isEmpty() && !jFormattedTextField1.getText().contains(" ") && !jFormattedTextField2.getText().contains(" ")) {
                    if (tar.getIdTarea() == null) {
                        // <editor-fold defaultstate="collapsed" desc="Guardar tarea nueva"> 
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
                        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
                        int c = 0;
                        while (jTable1.getRowCount() != c) {
                            Personal person = (Personal) modelo.getValueAt(c, 3);
                            SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                            Date inn = formateador.parse((String) modelo.getValueAt(c, 1));
                            Date fii = formateador.parse((String) modelo.getValueAt(c, 2));
                            Iniciofin aux = new Iniciofin();
                            aux.setInicio(inn);
                            aux.setFin(fii);
                            Date inicioo = dateChooserCombo1.getSelectedDate().getTime();
                            Date finn = dateChooserCombo2.getSelectedDate().getTime();
                            String dse = (String) modelo.getValueAt(c, 0);
                            int[][] cant = person.VerificarDisponibilidadClase(inicioo, finn, aux, dse);
                            int ee = cant[0].length;
                            int eee = cant[1].length;
                            int confirmado = JOptionPane.showConfirmDialog(null, "El personal " + person.toString() + " tendrá " + ee + " inasistencias debido a actividades y " + eee + " debido a declaración jurada, ¿Desea continuar?", "Registrar clase", JOptionPane.YES_NO_OPTION);
                            if (JOptionPane.OK_OPTION == confirmado) {
                                AgendaId idage = new AgendaId(person.getIdPersonal(), tarr.getIdTarea());
                                Revista rev = (Revista) modelo.getValueAt(c, 4);
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
                                String dsem = (String) modelo.getValueAt(c, 0);
                                Date ot = inicio;
                                if (dsem.equals("LUNES")) {
                                    while (ot.getDay() != 1) {
                                        ot = Controlador.sumarFechasDias(ot, 1);
                                    }
                                } else if (dsem.equals("MARTES")) {
                                    while (ot.getDay() != 2) {
                                        ot = Controlador.sumarFechasDias(ot, 1);
                                    }
                                } else if (dsem.equals("MIERCOLES")) {
                                    while (ot.getDay() != 3) {
                                        ot = Controlador.sumarFechasDias(ot, 1);
                                    }
                                } else if (dsem.equals("JUEVES")) {
                                    while (ot.getDay() != 4) {
                                        ot = Controlador.sumarFechasDias(ot, 1);
                                    }
                                } else if (dsem.equals("VIERNES")) {
                                    while (ot.getDay() != 5) {
                                        ot = Controlador.sumarFechasDias(ot, 1);
                                    }
                                } else if (dsem.equals("SABADO")) {
                                    while (ot.getDay() != 6) {
                                        ot = Controlador.sumarFechasDias(ot, 1);
                                    }
                                }
                                Date otro = ot;
                                while (otro.compareTo(fin) <= 0) {
                                    Mes mes = anio.getMes(otro.getMonth());
                                    if (mes == null) {
                                        mes = new Mes();
                                        mes.setAno(anio);
                                        mes.setMes(otro.getMonth());
                                        mes.guardarMes(mes);
                                        int e = mes.getMes();
                                        while (otro.getMonth() == e && otro.compareTo(fin) <= 0) {
                                            Dia dia = mes.getDia(otro.getDate());
                                            if (dia == null) {
                                                dia = new Dia();
                                                dia.setMes(mes);
                                                dia.setDia(otro.getDate());
                                                dia.guardarDia(dia);
                                                int f = dia.getDia();
                                                while (otro.getDate() == f && otro.compareTo(fin) <= 0) {
                                                    //SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                                                    formateador.setLenient(false);
                                                    Date inici = formateador.parse((String) modelo.getValueAt(c, 1));
                                                    Date fi = formateador.parse((String) modelo.getValueAt(c, 2));
                                                    Iniciofin in = new Iniciofin();
                                                    in.setDia(dia);
                                                    in.setInicio(inici);
                                                    in.setEstadoInicio(false);
                                                    in.setFin(fi);
                                                    in.setEstadoFin(false);
                                                    in.guardarIniciofin(in);
                                                    otro = Drive.sumarFechasDias(otro, 7);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            c++;
                        }
                        jTextField1.setText("");
                        jTextField2.setText("");
                        jTextField3.setText("");
                        jTextField4.setText("");
                        jFormattedTextField1.setText("");
                        jFormattedTextField2.setText("");
                        Drive.LimpiarTabla(jTable1);
                        lista.removeAll(lista);
                        String buscar=(String) jComboBox4.getSelectedItem();
                        Drive.CargarpersonalSimple(jTable2,buscar, buffer.toString().toUpperCase(),lista);
                        Drive=new Controlador();
                        // </editor-fold>
                    } else {
                        // <editor-fold defaultstate="collapsed" desc="Actualizar tarea"> 
                        if (!tar.getLugar().equals(jTextField3.getText().toUpperCase())) {
                            Establecimiento est = Drive.getPrimerEstablecimiento();
                            tar.setLugar(jTextField3.getText().toUpperCase());
                            tar.ActualizarTarea(tar);
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
                        }
                        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
                        int c = 0;
                        boolean bander=false;
                        int cont=tar.getAgendas().size();
                        while (jTable1.getRowCount() != c) {
                            Personal person = (Personal) modelo.getValueAt(c, 3);
                            Revista rev = (Revista) modelo.getValueAt(c, 4);
                            Iterator it = tar.getAgendas().iterator();
                            bander=false;
                            while (it.hasNext()) {
                                Agenda ag = (Agenda) it.next();
                                Personal pp = ag.getPersonal();
                                if (pp.getIdPersonal() == person.getIdPersonal() && ag.getRevista().getIdRevista() == rev.getIdRevista()) {
                                    bander=true;
                                    break;
                                }
                                //bander=false;
                                //cambio = true;
                            }
                            if(bander==false){
                                cambio=true;
                            }
                            c++;
                        }
                        if(cont!=jTable1.getRowCount()){
                            cambio=true;
                        }
                        if (cambio == true) {
                            tar.BorrarTodo();
                            Drive=new Controlador();
                            tar=(Tarea) Drive.PERSISTENCIA.getTarea(tar.getIdTarea()).iterator().next();
                            c = 0;
                            while (jTable1.getRowCount() != c) {
                                Personal person = (Personal) modelo.getValueAt(c, 3);
                                SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                                Date inn = formateador.parse((String) modelo.getValueAt(c, 1));
                                Date fii = formateador.parse((String) modelo.getValueAt(c, 2));
                                Iniciofin aux = new Iniciofin();
                                aux.setInicio(inn);
                                aux.setFin(fii);
                                Date inicioo = dateChooserCombo1.getSelectedDate().getTime();
                                Date finn = dateChooserCombo2.getSelectedDate().getTime();
                                String dse = (String) modelo.getValueAt(c, 0);
                                int[][] cant = person.VerificarDisponibilidadClase(inicioo, finn, aux, dse);
                                int ee = cant[0].length;
                                int eee = cant[1].length;
                                int confirmado = JOptionPane.showConfirmDialog(null, "El personal" + person.toString() + " tendrá " + ee + " inasistencias debido a actividades y " + eee + " debido a declaración jurada, ¿Desea continuar?", "", JOptionPane.YES_NO_OPTION);
                                if (JOptionPane.OK_OPTION == confirmado) {
                                    Revista rev = (Revista) modelo.getValueAt(c, 4);
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
                                    String dsem = (String) modelo.getValueAt(c, 0);
                                    Date ot = inicio;
                                    if (dsem.equals("LUNES")) {
                                        while (ot.getDay() != 1) {
                                            ot = Controlador.sumarFechasDias(ot, 1);
                                        }
                                    } else if (dsem.equals("MARTES")) {
                                        while (ot.getDay() != 2) {
                                            ot = Controlador.sumarFechasDias(ot, 1);
                                        }
                                    } else if (dsem.equals("MIERCOLES")) {
                                        while (ot.getDay() != 3) {
                                            ot = Controlador.sumarFechasDias(ot, 1);
                                        }
                                    } else if (dsem.equals("JUEVES")) {
                                        while (ot.getDay() != 4) {
                                            ot = Controlador.sumarFechasDias(ot, 1);
                                        }
                                    } else if (dsem.equals("VIERNES")) {
                                        while (ot.getDay() != 5) {
                                            ot = Controlador.sumarFechasDias(ot, 1);
                                        }
                                    } else if (dsem.equals("SABADO")) {
                                        while (ot.getDay() != 6) {
                                            ot = Controlador.sumarFechasDias(ot, 1);
                                        }
                                    }
                                    Date otro = ot;
                                    while (otro.compareTo(fin) <= 0) {
                                        Mes mes = anio.getMes(otro.getMonth());
                                        if (mes == null) {
                                            mes = new Mes();
                                            mes.setAno(anio);
                                            mes.setMes(otro.getMonth());
                                            mes.guardarMes(mes);
                                            int e = mes.getMes();
                                            while (otro.getMonth() == e && otro.compareTo(fin) <= 0) {
                                                Dia dia = mes.getDia(otro.getDate());
                                                if (dia == null) {
                                                    dia = new Dia();
                                                    dia.setMes(mes);
                                                    dia.setDia(otro.getDate());
                                                    dia.guardarDia(dia);
                                                    int f = dia.getDia();
                                                    while (otro.getDate() == f && otro.compareTo(fin) <= 0) {
                                                        //SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                                                        formateador.setLenient(false);
                                                        Date inici = formateador.parse((String) modelo.getValueAt(c, 1));
                                                        Date fi = formateador.parse((String) modelo.getValueAt(c, 2));
                                                        Iniciofin in = new Iniciofin();
                                                        in.setDia(dia);
                                                        in.setInicio(inici);
                                                        in.setEstadoInicio(false);
                                                        in.setFin(fi);
                                                        in.setEstadoFin(false);
                                                        in.guardarIniciofin(in);
                                                        otro = Drive.sumarFechasDias(otro, 7);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                c++;
                            }
                        }
                        Frame vp = new JFrameConsultaActividades(Drive, adm, idsesion);
                        this.dispose();
                        vp.show();
                        // </editor-fold>
                    }
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
            if (!jTextField1.getText().isEmpty() && !jFormattedTextField1.getText().contains(" ") && !jFormattedTextField2.getText().contains(" ")) {
                if (per.getIdPersonal() != null) {
                    DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
                    Revista rev = (Revista) jComboBox2.getSelectedItem();
                    int c = 0;
                    boolean bandera = true;
                    while (jTable1.getRowCount() != c) {
                        Revista revista = (Revista) modelo.getValueAt(c, 4);
                        if (revista.getIdRevista() == rev.getIdRevista()) {
                            bandera = false;
                        }
                        c++;
                    }
                    if (bandera == true) {
                        String dia = (String) jComboBox3.getSelectedItem();
                        SimpleDateFormat formateador = new SimpleDateFormat("HH:mm");
                        Date inicio = formateador.parse(jFormattedTextField1.getText());
                        Date fin = formateador.parse(jFormattedTextField2.getText());
                        if (inicio.compareTo(fin) < 0) {
                            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                            Object[] fila = new Object[5];
                            fila[0] = dia;
                            fila[1] = formateador.format(inicio);
                            fila[2] = formateador.format(fin);
                            fila[3] = per;
                            fila[4] = rev;
                            model.addRow(fila);
                            jTable1.setModel(model);
                            Drive.LimpiarTabla(jTable2);
                            jTextField5.setText(buffer.toString());
                            per = new Personal();
                            
                        } else {JOptionPane.showMessageDialog(null, "Ingrese correctamente los horarios", "Registrar Clase", JOptionPane.ERROR_MESSAGE);}
                    } else {JOptionPane.showMessageDialog(null, "La situación de revista es unica por personal", "Registrar Clase", JOptionPane.ERROR_MESSAGE);}
                } else {JOptionPane.showMessageDialog(null, "Debe seleccionar un personal", "Registrar Clase", JOptionPane.ERROR_MESSAGE);}
            } else {JOptionPane.showMessageDialog(null, "Los campos con '*' son obligatorios y no puede contener espacios en blanco en los horarios", "Registrar Clase", JOptionPane.ERROR_MESSAGE);}
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Registrar Clase", JOptionPane.ERROR_MESSAGE);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try{
            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            lista.removeAll(lista);
            int c=0;
            modelo.removeRow(jTable1.getSelectedRow());
            while(jTable1.getRowCount()!=c){
                Personal per=(Personal) modelo.getValueAt(c, 3);
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
                    JOptionPane.showMessageDialog(null, "La situación de revista ya existe","Nueva Situación de Revista", JOptionPane.ERROR_MESSAGE);
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
        Frame vp=new JFramePrincipal(Drive,adm,idsesion);
        this.dispose();
        vp.show();
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
            JOptionPane.showMessageDialog(null, "ERROR","Registrar clase", JOptionPane.ERROR_MESSAGE);
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
        DefaultTableModel modelo = (DefaultTableModel)jTable1.getModel();
        int c=0;
        while(jTable1.getRowCount()!=c){
            Personal per=(Personal) modelo.getValueAt(c, 3);
            lista.add(per);
            c++;
        }
        Drive.LimpiarTabla(jTable2);
        String es=buffer.toString();
        String buscar=(String) jComboBox4.getSelectedItem();
        Drive.CargarpersonalSimple(jTable2,buscar, es.toUpperCase(),lista);
//        if(jTable1.getRowCount()>0){
//            jTextField1.setEnabled(false);
//            jTextField2.setEnabled(false);
//            jTextField3.setEnabled(false);
//            jTextField4.setEnabled(false);
//            jFormattedTextField1.setEnabled(false);
//            jFormattedTextField2.setEnabled(false);
//            dateChooserCombo1.setEnabled(false);
//            dateChooserCombo2.setEnabled(false);
//            jComboBox3.setEnabled(false);
//        }else{
//            //jTextField1.setEnabled(true);
//            jTextField2.setEnabled(true);
//            jTextField3.setEnabled(true);
//            jTextField4.setEnabled(true);
//            //jFormattedTextField1.setEnabled(true);
//            jFormattedTextField2.setEnabled(true);
//            dateChooserCombo1.setEnabled(true);
//            dateChooserCombo2.setEnabled(true);
//            jComboBox3.setEnabled(true);
//        }
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

    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged
        if(tar.getIdTarea()!=null){
            String dia=Drive.ObtenerDia(mayor.getDay());
            if(!dia.equals((String)jComboBox3.getSelectedItem())){
                cambio=true;
            }
        }
    }//GEN-LAST:event_jComboBox3ItemStateChanged

    private void jFormattedTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextField1FocusLost
        try {
            if (tar.getIdTarea() != null) {
                Agenda age = tar.getAgendas().iterator().next();
                Dia d = age.getDia2(mayor);
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
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
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
