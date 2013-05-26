/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TareasProgramadas;

/**
 *
 * @author fer
 */
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TareaInvocar implements Job {
ControladorTarea Drive=new ControladorTarea();

  public void execute(JobExecutionContext jec) throws JobExecutionException {
        Calendar cel = Calendar.getInstance();
        Drive.ObtenerListadeldia(cel);
        //Aca pueden poner la tarea o el job que desean automatizar
        //Por ejemplo enviar correo, revisar ciertos datos, etc
        SimpleDateFormat formato = new SimpleDateFormat("hh:mm:ss");
        System.out.println("Tarea invocada a la hora: " + formato.format(new Date()));
    }
}