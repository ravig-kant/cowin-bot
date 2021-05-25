import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        logger.info("Launching application");
        try{
            final ConfigData instance = ConfigData.getInstance();
        } catch (Exception e) {
            logger.severe("Error starting the application " + e.getMessage());
            System.exit(255);
        }

        ScheduledExecutorService executorService
                = Executors.newScheduledThreadPool(10);

        executorService.scheduleWithFixedDelay(new Worker(),
                0,5000, TimeUnit.MILLISECONDS);

        try {
            executorService.awaitTermination(0, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }
}

final class Worker implements Runnable{
    private static Logger logger = Logger.getLogger(Worker.class.getName());
    private AppointmentLookupService appointmentLookupService = new AppointmentLookupService();

    @Override
    public void run() {
        try{
            appointmentLookupService.findByDistrict(ConfigData.getInstance().getDistrict())
                                    .filteredByAvailability()
                                    .forEach(this::sendNotification);
        } catch(Throwable throwable){
            logger.log(Level.SEVERE, "Exception in worker", throwable);
        }
    }

    private void sendNotification(Appointment appointment){
        String correlationId = appointment.getName();
        String message = appointment.message();
        Notification.send(correlationId, message);
    }
}
