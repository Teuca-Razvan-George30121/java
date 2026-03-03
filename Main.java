package isp.lab10.raceapp;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // creeaza fereastra pentru semafor
        JFrame semaphoreFrame = new JFrame("Semaphore");
        semaphoreFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SemaphorePanel semaphorePanel = new SemaphorePanel();
        semaphoreFrame.getContentPane().add(semaphorePanel);
        semaphoreFrame.pack();
        semaphoreFrame.setLocation(100, 100);
        semaphoreFrame.setVisible(true);

        SemaphoreThread semaphoreThread = new SemaphoreThread(semaphorePanel);
        semaphoreThread.start();

        try {
            // asteapta ca semaforul sa ajunga pe verde
            semaphoreThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        JFrame raceFrame = new JFrame("Car Race");
        raceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CarPanel carPanel = new CarPanel();
        raceFrame.getContentPane().add(carPanel);
        raceFrame.setSize(500, 300);
        raceFrame.setLocation(250, 100);
        raceFrame.setVisible(true);

        // porneste sunetul cursei
        PlaySound playSound = new PlaySound();
        playSound.playSound();  // metoda ta din clasa PlaySound


        Car car1 = new Car("Red", carPanel);
        Car car2 = new Car("Blue", carPanel);
        Car car3 = new Car("Green", carPanel);
        Car car4 = new Car("Yellow", carPanel);

        car1.start();
        car2.start();
        car3.start();
        car4.start();


        try {
            car1.join();
            car2.join();
            car3.join();
            car4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // opreste sunetul dupa cursa
        playSound.stopSound();
    }
}
