/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TareasProgramadas;

import java.util.Date;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;

public class Programacion {
    private Scheduler horario;
//    private Scheduler horario2;
    
    private void crearProgramacio() {
        try {
            SchedulerFactory factoria = new StdSchedulerFactory();
            horario = factoria.getScheduler();
            horario.start();
        }catch (Exception ex) {System.out.println(ex.getMessage());}
    }
    
//    private void crearProgramacion2() {
//        try {
//            SchedulerFactory factoria2 = new StdSchedulerFactory();
//            horario2 = factoria2.getScheduler();
//            horario2.start();
//        } catch (Exception ex) {System.out.println(ex.getMessage());}
//    }

    public void iniciarTarea() {
        if (this.horario == null) {
            this.crearProgramacio();
        }
//        if (this.horario2 == null) {
//            this.crearProgramacion2();
//        }
        
        //Informacion sobre la tarea
        //La informacion que se pide un nombre, a que grupo pertenece 
        //y a que clase que implemente de Job va a ser llamado
        JobDetail job = new JobDetail("TareaAutomatizar", null, TareaInvocar.class);
        Trigger trigger = TriggerUtils.makeMinutelyTrigger(5);
        trigger.setName("CadaCincoMinutos");
        trigger.setStartTime(new Date());
        
//        JobDetail job2 = new JobDetail("TareaAutomatizar2", null, ControlarTardanzas.class);
//        //Trigger trigger2 = TriggerUtils.makeDailyTrigger(7,00);
//        Trigger trigger2 = TriggerUtils.makeSecondlyTrigger("mama",10, 10);
//        trigger2.setStartTime(new Date());
        try {
            this.horario.scheduleJob(job, trigger);
//            this.horario.scheduleJob(job2, trigger2);
        }catch (SchedulerException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void stop(){  
        try {  
            horario.shutdown();  
        } catch (Exception ex) {}             
    } 
}
