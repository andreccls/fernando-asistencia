/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

/**
 *
 * @author fer
 */
import Clases.Controlador;
import Clases.Personal;
import Clases.Tarea;
import javax.swing.JProgressBar;
import javax.swing.JFrame;
 
public class HiloProgreso extends Thread{
    JProgressBar progreso;
    JFrameActualizarPersonal framee;
    JFrameActualizarActividades frameee;
    JFrameConsulta fr;
    JFrameConsultaActividades frr;
    public Controlador conn;
    Personal perr;
    Tarea ta;
    int bandera;
    public Personal adm;
    int idsesion;
    
    public HiloProgreso(JProgressBar progreso1,JFrameConsulta frc,Controlador con, Personal per, Personal admin,int id){
        super();
        this.progreso=progreso1;
        this.conn=con;
        this.adm=admin;
        this.idsesion=id;
        this.perr=per;
        this.fr=frc;
        bandera=1;
    }
    
    public HiloProgreso(JProgressBar progreso1,JFrameConsultaActividades frc,Controlador con, Tarea tar, Personal admin,int id){        
        super();
        this.progreso=progreso1;
        this.conn=con;
        this.adm=admin;
        this.idsesion=id;
        this.ta=tar;
        this.frr=frc;
        bandera=2;
    }
    
    @Override
    public void run() {
        if(bandera==1){
            framee = new JFrameActualizarPersonal(conn, perr,adm,idsesion);
            for (int i = 1; i <= 100; i++) {
                progreso.setValue(i);
                pausa(40);
            }
            fr.hide();
            framee.show();
        }else if(bandera==2){
            frameee = new JFrameActualizarActividades(conn,ta,adm,idsesion);
            for(int i=1;i<=100;i++)    {
                progreso.setValue(i);
                pausa(50);
            }
            frr.hide();
            frameee.show();
        }
    }
//    public void run2(){
//    for(int i=1;i<=100;i++)    {
//        progreso.setValue(i);
//        pausa(50);
//    }
//    frameee = new JFrameActualizarActividades(conn,ta);
//    fr.hide();
//    framee.show();
//}
    
    public void pausa(int mlSeg){
    try{
        // pausa para el splash
        Thread.sleep(mlSeg);
    }catch(Exception e){}
}
}
