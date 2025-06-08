package remiender;

import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RemienderService {

    private final Logger _logger = Logger.getLogger(RemienderService.class.getName());

    public Thread createReminder(LocalTime reminder) {

        return new Thread(() ->{
                while(reminder.isAfter(LocalTime.now())){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        _logger.log(Level.SEVERE, "Interrupt Exception", e);
                        System.exit(0);
                    }
                }
        });

    }

}
