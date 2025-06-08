package remiender;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RemienderUI {

    private final RemienderService _remienderService;
    private final Logger _logger = Logger.getLogger(RemienderUI.class.getName());

    public RemienderUI() {
        _remienderService = new RemienderService();
    }

    public void setReminder(Scanner scanner) {
        scanner.nextLine();
        LocalTime time = null;
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("HH:mm:ss");

        do {
            System.out.print("Enter time (HH:MM:SS): ");
            try {
                time = LocalTime.parse(scanner.nextLine(), formater);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time. Please enter correct time!");
            }
        } while (time == null);

        System.out.print("Enter message: ");
        String message = scanner.nextLine();


        Thread thread = _remienderService.createReminder(time);
        thread.start();

        Thread mainThread = new Thread(() -> {
            while (thread.isAlive()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    _logger.log(Level.SEVERE, "Interrupt exception", e);
                    System.exit(0);
                }
            }

            printMessage(message);
        });
        mainThread.start();
    }

    private void printMessage(String message) {
        System.out.println("\n***************************");
        System.out.println("NOTIFICATION: " + message);
        System.out.println("***************************");

    }
}
