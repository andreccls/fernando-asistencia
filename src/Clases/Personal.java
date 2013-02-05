package Clases;
// Generated 22/11/2012 14:19:28 by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 * Personal generated by hbm2java
 */
public class Personal  implements java.io.Serializable {


     private Integer idPersonal;
     private Tipodoc tipodoc;
     private Establecimiento establecimiento;
     private byte[] codigo;
     private String dni;
     private String apellido;
     private String nombre;
     private String cuil;
     private String calle;
     private Integer altura;
     private String piso;
     private String depto;
     private String correoElectronico;
     private String sexo;
     private String estadoCivil;
     private Date ingreso;
     private Boolean estado;
     private Boolean familiar;
     private Date fechaNac;
     private Set<PersonalDepartamento> personalDepartamentos = new HashSet<PersonalDepartamento>(0);
     private Set<Declaracionjurada> declaracionjuradas = new HashSet<Declaracionjurada>(0);
     private Set<Personalnodocente> personalnodocentes = new HashSet<Personalnodocente>(0);
     private Set<PersonalFamiliar> personalFamiliarsForIdPersonal = new HashSet<PersonalFamiliar>(0);
     private Set<PersonalFamiliar> personalFamiliarsForIdFamiliar = new HashSet<PersonalFamiliar>(0);
     private Set<Personaldocente> personaldocentes = new HashSet<Personaldocente>(0);
     private Set<Agenda> agendas = new HashSet<Agenda>(0);
     private Set<Telefono> telefonos = new HashSet<Telefono>(0);

    public Personal() {
    }

	
    public Personal(Tipodoc tipodoc, Establecimiento establecimiento, String dni, String apellido, String nombre) {
        this.tipodoc = tipodoc;
        this.establecimiento = establecimiento;
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
    }
    public Personal(Tipodoc tipodoc, Establecimiento establecimiento, byte[] codigo, String dni, String apellido, String nombre, String cuil, String calle, Integer altura, String piso, String depto, String correoElectronico, String sexo, String estadoCivil, Date ingreso, Boolean estado, Boolean familiar, Date fechaNac, Set<PersonalDepartamento> personalDepartamentos, Set<Declaracionjurada> declaracionjuradas, Set<Personalnodocente> personalnodocentes, Set<PersonalFamiliar> personalFamiliarsForIdPersonal, Set<PersonalFamiliar> personalFamiliarsForIdFamiliar, Set<Personaldocente> personaldocentes, Set<Agenda> agendas, Set<Telefono> telefonos) {
       this.tipodoc = tipodoc;
       this.establecimiento = establecimiento;
       this.codigo = codigo;
       this.dni = dni;
       this.apellido = apellido;
       this.nombre = nombre;
       this.cuil = cuil;
       this.calle = calle;
       this.altura = altura;
       this.piso = piso;
       this.depto = depto;
       this.correoElectronico = correoElectronico;
       this.sexo = sexo;
       this.estadoCivil = estadoCivil;
       this.ingreso = ingreso;
       this.estado = estado;
       this.familiar = familiar;
       this.fechaNac = fechaNac;
       this.personalDepartamentos = personalDepartamentos;
       this.declaracionjuradas = declaracionjuradas;
       this.personalnodocentes = personalnodocentes;
       this.personalFamiliarsForIdPersonal = personalFamiliarsForIdPersonal;
       this.personalFamiliarsForIdFamiliar = personalFamiliarsForIdFamiliar;
       this.personaldocentes = personaldocentes;
       this.agendas = agendas;
       this.telefonos = telefonos;
    }
   
    @Override
    public String toString() {
        return apellido+" "+nombre;
    }
    
    public Integer getIdPersonal() {
        return this.idPersonal;
    }
    
    public void setIdPersonal(Integer idPersonal) {
        this.idPersonal = idPersonal;
    }
    public Tipodoc getTipodoc() {
        return this.tipodoc;
    }
    
    public void setTipodoc(Tipodoc tipodoc) {
        this.tipodoc = tipodoc;
    }
    public Establecimiento getEstablecimiento() {
        return this.establecimiento;
    }
    
    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }
    public byte[] getCodigo() {
        return this.codigo;
    }
    
    public void setCodigo(byte[] codigo) {
        this.codigo = codigo;
    }
    public String getDni() {
        return this.dni;
    }
    
    public void setDni(String dni) {
        this.dni = dni;
    }
    public String getApellido() {
        return this.apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCuil() {
        return this.cuil;
    }
    
    public void setCuil(String cuil) {
        this.cuil = cuil;
    }
    public String getCalle() {
        return this.calle;
    }
    
    public void setCalle(String calle) {
        this.calle = calle;
    }
    public Integer getAltura() {
        return this.altura;
    }
    
    public void setAltura(Integer altura) {
        this.altura = altura;
    }
    public String getPiso() {
        return this.piso;
    }
    
    public void setPiso(String piso) {
        this.piso = piso;
    }
    public String getDepto() {
        return this.depto;
    }
    
    public void setDepto(String depto) {
        this.depto = depto;
    }
    public String getCorreoElectronico() {
        return this.correoElectronico;
    }
    
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
    public String getSexo() {
        return this.sexo;
    }
    
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public String getEstadoCivil() {
        return this.estadoCivil;
    }
    
    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }
    public Date getIngreso() {
        return this.ingreso;
    }
    
    public void setIngreso(Date ingreso) {
        this.ingreso = ingreso;
    }
    public Boolean getEstado() {
        return this.estado;
    }
    
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    public Boolean getFamiliar() {
        return this.familiar;
    }
    
    public void setFamiliar(Boolean familiar) {
        this.familiar = familiar;
    }
    public Date getFechaNac() {
        return this.fechaNac;
    }
    
    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }
    public Set<PersonalDepartamento> getPersonalDepartamentos() {
        return this.personalDepartamentos;
    }
    
    public void setPersonalDepartamentos(Set<PersonalDepartamento> personalDepartamentos) {
        this.personalDepartamentos = personalDepartamentos;
    }
    public Set<Declaracionjurada> getDeclaracionjuradas() {
        return this.declaracionjuradas;
    }
    
    public void setDeclaracionjuradas(Set<Declaracionjurada> declaracionjuradas) {
        this.declaracionjuradas = declaracionjuradas;
    }
    public Set<Personalnodocente> getPersonalnodocentes() {
        return this.personalnodocentes;
    }
    
    public void setPersonalnodocentes(Set<Personalnodocente> personalnodocentes) {
        this.personalnodocentes = personalnodocentes;
    }
    public Set<PersonalFamiliar> getPersonalFamiliarsForIdPersonal() {
        return this.personalFamiliarsForIdPersonal;
    }
    
    public void setPersonalFamiliarsForIdPersonal(Set<PersonalFamiliar> personalFamiliarsForIdPersonal) {
        this.personalFamiliarsForIdPersonal = personalFamiliarsForIdPersonal;
    }
    public Set<PersonalFamiliar> getPersonalFamiliarsForIdFamiliar() {
        return this.personalFamiliarsForIdFamiliar;
    }
    
    public void setPersonalFamiliarsForIdFamiliar(Set<PersonalFamiliar> personalFamiliarsForIdFamiliar) {
        this.personalFamiliarsForIdFamiliar = personalFamiliarsForIdFamiliar;
    }
    public Set<Personaldocente> getPersonaldocentes() {
        return this.personaldocentes;
    }
    
    public void setPersonaldocentes(Set<Personaldocente> personaldocentes) {
        this.personaldocentes = personaldocentes;
    }
    public Set<Agenda> getAgendas() {
        return this.agendas;
    }
    
    public void setAgendas(Set<Agenda> agendas) {
        this.agendas = agendas;
    }
    public Set<Telefono> getTelefonos() {
        return this.telefonos;
    }
    
    public void setTelefonos(Set<Telefono> telefonos) {
        this.telefonos = telefonos;
    }

// GENERADO POR GONZALEZ FERNANDO
    
    public int guardarPersonal(Personal unPersonal){
        Controlador.getPERSISTENCIA().insertstring(this);
        //Controlador.getPERSISTENCIA().update(this);

        JOptionPane.showMessageDialog(null,"El personal "+ 
                String.valueOf(unPersonal.getIdPersonal()) +
                " se guardo correctamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
        return unPersonal.getIdPersonal();
    }
    
    public void actualizarPersonal(Personal unPersonal){
        Controlador.getPERSISTENCIA().update(this);

        JOptionPane.showMessageDialog(null,"El personal "+ 
                String.valueOf(unPersonal.getIdPersonal()) +
                " se actualizó correctamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
    }

    public Personaldocente getPersonaldoc(int idperdoc) {
        Personaldocente per=new Personaldocente();
        Iterator it=personaldocentes.iterator();
           while(it.hasNext()){
           Personaldocente p=(Personaldocente) it.next();
           System.out.println("Personal que tengo "+p.getId().getIdDocente());
           if(idperdoc==p.getId().getIdPersonal()){
               //System.out.println("Existe el cliente   =" + String.valueOf(c.equals(unCliente)));
               per=p;
               break;
           }
         }
        return per;
    }
    
    public void crearPersonaldoc(PersonaldocenteId id, Personal personal, Integer cargohoras, Integer antiguedadDoc){
        Personaldocente unPersonaldoc=new Personaldocente(id,personal,cargohoras,antiguedadDoc);
        //if(!existePersonal(unPersonal)){
            unPersonaldoc.guardarPersonaldoc(unPersonaldoc);
            //agregarReserva(unaReserva);
        //} 
     }
    
//    public void actualizarPersonaldoc(PersonaldocenteId id, Personal personal, Integer cargohoras, Integer antiguedadDoc){
//        Personaldocente unPersonaldoc=new Personaldocente(id,personal,cargohoras,antiguedadDoc);
//        //if(!existePersonal(unPersonal)){
//            unPersonaldoc.actualizarPersonaldoc(unPersonaldoc);
//            //agregarReserva(unaReserva);
//        //} 
//     }
    
    public Personalnodocente getPersonalnodoc(int idperdoc) {
        Personalnodocente per=new Personalnodocente();
        Iterator it=personalnodocentes.iterator();
           while(it.hasNext()){
           Personalnodocente p=(Personalnodocente) it.next();
           System.out.println("Personal que tengo "+p.getId().getIdNodocente());
           if(idperdoc==p.getId().getIdPersonal()){
               //System.out.println("Existe el cliente   =" + String.valueOf(c.equals(unCliente)));
               per=p;
               break;
           }
         }
        return per;
    }
    
    public void crearPersonalnodoc(PersonalnodocenteId id, Personal personal, Actividad actividad){
        Personalnodocente unPersonalnodocente=new Personalnodocente (id, personal, actividad);
        //if(!existePersonal(unPersonal)){
            unPersonalnodocente.guardarPersonalnodoc(unPersonalnodocente);
            //agregarReserva(unaReserva);
        //} 
     }
    
//     public void actualizarPersonalnodoc(PersonalnodocenteId id, Personal personal, Actividad actividad){
//        Personalnodocente unPersonalnodocente=new Personalnodocente (id, personal, actividad);
//        //if(!existePersonal(unPersonal)){
//            unPersonalnodocente.actualizarPersonalnodoc(unPersonalnodocente);
//            //agregarReserva(unaReserva);
//        //} 
//     }

     public Boolean getEstadoPersonalFamiliar(Personal per, Personal otro) {
        Boolean bandera=false;
        Iterator<PersonalFamiliar> it=per.personalFamiliarsForIdPersonal.iterator();
           while(it.hasNext()){
           PersonalFamiliar perfam= it.next();
           Personal pp= perfam.getPersonalByIdFamiliar();
           if(pp.getEstado()==true && pp.getFamiliar()==true){
               int id1=pp.getIdPersonal();
               int id2=otro.getIdPersonal();
               if(id1 != id2){
                   bandera=true;
                   break;
               }
           }
         }
        return bandera;
    }
     
     public PersonalFamiliar getPersonalFamiliar(Personal per, Personal otro) {
        Iterator<PersonalFamiliar> it= per.personalFamiliarsForIdPersonal.iterator();
        PersonalFamiliar bandera=new PersonalFamiliar();
           while(it.hasNext()){
           PersonalFamiliar perfam=(PersonalFamiliar) it.next();
           if(perfam.getId().getIdFamiliar()== otro.getIdPersonal()){
                   bandera=perfam;
                   break;
           }
         }
        return bandera;
    }
     
    public void crearPersonalFamiliar(PersonalFamiliarId id, Personal personalByIdPersonal, Personal personalByIdFamiliar, Tiporelacion tiporelacion, Boolean asignacionFamiliar){
        PersonalFamiliar unPersonalFamiliar=new PersonalFamiliar (id, personalByIdPersonal, personalByIdFamiliar, tiporelacion,asignacionFamiliar);
        unPersonalFamiliar.guardarPersonalFamiliar(unPersonalFamiliar);
        //PersonalFamiliar per=unPersonalFamiliar;

     }
    
    public void crearDeclaracionjurada(Personal personal, Establecimiento establecimiento, int ano, String observacion, Set<DetalleEstablecimiento> detalleEstablecimientos){
        Declaracionjurada unaDeclaracionjurada=new Declaracionjurada (personal, establecimiento, ano, observacion, detalleEstablecimientos);
        //if(!existePersonal(unPersonal)){
            unaDeclaracionjurada.guardarDeclaracionjurada(unaDeclaracionjurada);
            //agregarReserva(unaReserva);
        //} 
     }
    
    public void crearTelefono(Personal personal, String nombre, String numero){
        Telefono unTelefono=new Telefono (personal, nombre, numero);
        //if(!existePersonal(unPersonal)){
            unTelefono.guardarTelefono(unTelefono);
            //agregarReserva(unaReserva);
        //} 
     }
//    public void actualizarTelefono(Personal personal, String nombre, String numero){
//        Telefono unTelefono=new Telefono (personal, nombre, numero);
//        //if(!existePersonal(unPersonal)){
//            unTelefono.actualizarTelefono(unTelefono);
//            //agregarReserva(unaReserva);
//        //} 
//     }
    
    
    public void crearPersonaldepartamento(PersonalDepartamentoId id, Departamento departamento, Personal personal, Boolean jefe){
        PersonalDepartamento unPersonalDepartamento=new PersonalDepartamento (id, departamento, personal, jefe);
        //if(!existePersonal(unPersonal)){
            unPersonalDepartamento.guardarPersonalDepartamento(unPersonalDepartamento);
            //agregarReserva(unaReserva);
        //} 
     }
    
    public void actualizarPersonaldepartamento(PersonalDepartamentoId id, Departamento departamento, Personal personal, Boolean jefe){
        PersonalDepartamento unPersonalDepartamento=new PersonalDepartamento (id, departamento, personal, jefe);
        //if(!existePersonal(unPersonal)){
            unPersonalDepartamento.actualizarPersonalDepartamento(unPersonalDepartamento);
            //agregarReserva(unaReserva);
        //} 
     }
    
    public void CargarListTelefono(JList Jlist,DefaultListModel modeloLista){
        try {
            //DefaultListModel modeloLista = new DefaultListModel();
            Iterator rs= telefonos.iterator();
            while (rs.hasNext()) {
                Telefono tel=(Telefono) rs.next();
                modeloLista.addElement(tel);
                Jlist.setModel(modeloLista);
            }
        } catch (Exception ex) {
             JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    public void CargarListDepartamento(JList Jlist,DefaultListModel modeloLista){
        try {
            //DefaultListModel modeloLista = new DefaultListModel();
            Iterator rs= personalDepartamentos.iterator();
            while (rs.hasNext()) {
                PersonalDepartamento perdepto=(PersonalDepartamento) rs.next();
                modeloLista.addElement(perdepto);
                Jlist.setModel(modeloLista);
            }
        } catch (Exception ex) {
             JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    public String ObtenerDia(int i){
    String dia=null;
    if(i==0){
        dia="DOMINGO";
    }else if(i==1){
        dia="LUNES";
    }else if(i==2){
        dia="MARTES";
    }else if(i==3){
        dia="MIERCOLES";
    }else if(i==4){
        dia="JUEVES";
    }else if(i==5){
        dia="VIERNES";
    }else if(i==6){
        dia="SABADO";
    }
    return dia;
    }
    
    public boolean VerificarDisponibilidadReunion (Date dia, Iniciofin ini){
        boolean band=true;
        try{
            if(!agendas.isEmpty()){
                Iterator it=agendas.iterator();
                while(it.hasNext()){
                    Agenda age=(Agenda) it.next();
                    Dia di=age.getDia2(dia);
                    if(di.getIdDia()!=null){
                        Iterator itin=di.getIniciofins().iterator();
                        while(itin.hasNext()){
                            Iniciofin in=(Iniciofin) itin.next();
                            if(in.getInicio().compareTo(ini.getInicio())>0 && in.getInicio().compareTo(ini.getFin())<0 || in.getFin().compareTo(ini.getInicio())>0 && in.getFin().compareTo(ini.getFin())<0){
                                band=false;
                                JOptionPane.showMessageDialog(null,"no existe disponibilidad porque hay otra tarea a ese horario");
                                return band;
                            }
                        }
                    }
                }
            }
            if(!declaracionjuradas.isEmpty()){
                String di= ObtenerDia(dia.getDay());
                Iterator itact=declaracionjuradas.iterator().next().ObtenerActivos(di).iterator();
                while(itact.hasNext()){
                    Activo act=(Activo) itact.next();
                    Iterator itin=act.getActivoIniciofins().iterator();
                    while(itin.hasNext()){
                        ActivoIniciofin in=(ActivoIniciofin) itin.next();
                        if(in.getInicio().compareTo(ini.getInicio())>0 && in.getInicio().compareTo(ini.getFin())<0 || in.getFin().compareTo(ini.getInicio())>0 && in.getFin().compareTo(ini.getFin())<0){
                            band=false;
                            JOptionPane.showMessageDialog(null,"no existe disponibilidad por parte de la declaración jurada a ese horario");
                            return band;
                        }
                    }
                }
            }
        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex.toString());}
        return band;
    }
    
    
    public int[][] VerificarDisponibilidadClase(Date inicio, Date fin, Iniciofin ini,String dsem){
        int a=0;
        int d=0;
        Date ot=inicio;
        if(dsem.equals("LUNES")){while(ot.getDay()!=1){ot=Controlador.sumarFechasDias(ot, 1);}
        }else if(dsem.equals("MARTES")){while(ot.getDay()!=2){ot=Controlador.sumarFechasDias(ot, 1);}
        }else if(dsem.equals("MIERCOLES")){while(ot.getDay()!=3){ot=Controlador.sumarFechasDias(ot, 1);}
        }else if(dsem.equals("JUEVES")){while(ot.getDay()!=4){ot=Controlador.sumarFechasDias(ot, 1);}
        }else if(dsem.equals("VIERNES")){while(ot.getDay()!=5){ot=Controlador.sumarFechasDias(ot, 1);}
        }else if(dsem.equals("SABADO")){while(ot.getDay()!=6){ot=Controlador.sumarFechasDias(ot, 1);}}
        
        Date otro=ot;
        while(otro.compareTo(fin)<=0){
            if(!agendas.isEmpty()){
                Iterator it=agendas.iterator();
                while(it.hasNext()){Agenda age=(Agenda) it.next();
                    Dia di=age.getDia2(otro);
                    if(di.getIdDia()!=null){Iterator itin=di.getIniciofins().iterator();
                        while(itin.hasNext()){Iniciofin in=(Iniciofin) itin.next();
                            if(in.getInicio().compareTo(ini.getInicio())>0 && in.getInicio().compareTo(ini.getFin())<0 || in.getFin().compareTo(ini.getInicio())>0 && in.getFin().compareTo(ini.getFin())<0){
                                a++;
                            }
                        }
                    }
                }
            }
            if(!declaracionjuradas.isEmpty()){
                Iterator itact=declaracionjuradas.iterator().next().ObtenerActivos(dsem).iterator();
                while(itact.hasNext()){
                    Activo act=(Activo) itact.next();
                    Iterator itin=act.getActivoIniciofins().iterator();
                    while(itin.hasNext()){
                        ActivoIniciofin in=(ActivoIniciofin) itin.next();
                        if(in.getInicio().compareTo(ini.getInicio())>0 && in.getInicio().compareTo(ini.getFin())<0 || in.getFin().compareTo(ini.getInicio())>0 && in.getFin().compareTo(ini.getFin())<0){
                            d++;
                        }
                    }
                }
            }
            otro=Controlador.sumarFechasDias(otro, 7);
        }
        int[][] cant=new int[2][];
        cant[0]= new int [a];
        cant[1]= new int [d];
        return cant;
    }
    
    public Declaracionjurada getDeclaracionjurada() {
        Declaracionjurada dec=new Declaracionjurada();
        Iterator it=declaracionjuradas.iterator();
        while(it.hasNext()){
            dec=(Declaracionjurada) it.next();
        }
        return dec;
    }
}


