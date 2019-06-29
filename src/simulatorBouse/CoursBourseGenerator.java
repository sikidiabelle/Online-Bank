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
        
        //Instanciation du Thread de g�n�ration
        Runnable command = new CoursBourseThread(event.getServletContext());
        
        //D�finition de la fr�quence d'execution
        // Decalage de 0 minutes � la 1�re �x�cution
        long initialDelay = 0;
        // La p�riode entre 2 executions successives
        long period = 1; // 5 Minutes
        //Unit� de temps pour les variables initialDelay et period
        TimeUnit unit = TimeUnit.MINUTES;
 
        scheduler.scheduleAtFixedRate(command, initialDelay, period, unit);
    }
 
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        scheduler.shutdownNow();
    }
 
}