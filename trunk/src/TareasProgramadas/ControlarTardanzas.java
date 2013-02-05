/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TareasProgramadas;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author fer
 */
public class ControlarTardanzas implements Job {
ControladorTarea Drive=new ControladorTarea();

  public void execute(JobExecutionContext jec) throws JobExecutionException {
    //Drive.Controlartardanza();
      //Aca pueden poner la tarea o el job que desean automatizar
    //Por ejemplo enviar correo, revisar ciertos datos, etc
    SimpleDateFormat formato = new SimpleDateFormat("hh:mm:ss");
    System.out.println( "Tarea invocada a la hora: " + formato.format(new Date()));
  }
}
