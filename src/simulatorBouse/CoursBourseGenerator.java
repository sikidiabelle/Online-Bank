package simulatorBouse;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
 
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class CoursBourseGenerator implements ServletContextListener {
 
    private ScheduledExecutorService scheduler;
 
    @Override
    public void contextInitialized(ServletContextEvent event) {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        
        //Instanciation du Thread de génération
        Runnable command = new CoursBourseThread(event.getServletContext());
        
        //Définition de la fréquence d'execution
        // Decalage de 0 minutes à la 1ère éxécution
        long initialDelay = 0;
        // La période entre 2 executions successives
        long period = 1; // 5 Minutes
        //Unité de temps pour les variables initialDelay et period
        TimeUnit unit = TimeUnit.MINUTES;
 
        scheduler.scheduleAtFixedRate(command, initialDelay, period, unit);
    }
 
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        scheduler.shutdownNow();
    }
 
}