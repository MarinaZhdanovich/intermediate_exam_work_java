package ToyShop.Controller;

import ToyShop.Model.Toy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class ToyController {
    private PriorityQueue<Toy> toyQueue;
    private Random random;
    private List<Toy> prizeToys;

    public ToyController() {
        toyQueue = new PriorityQueue<>();
        random = new Random();
        prizeToys = new ArrayList<>();
    }

    public void addItemsToQueue(String... lines) {
        for (String line : lines) {
            String[] parts = line.split(" ");
            try {
                int id = Integer.parseInt(parts[0]);
                String name = parts[2];
                int frequency = Integer.parseInt(parts[1]);
                toyQueue.offer(new Toy(id, name, frequency));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.err.println("Ошибка при обработке строки: " + line);
                e.printStackTrace();
            }
        }
    }

    public Toy choosePrizeToy() {
        int totalFrequency = toyQueue.stream().mapToInt(Toy::getFrequency).sum();
        int randomNum = random.nextInt(totalFrequency) + 1;

        int cumulativeFrequency = 0;
        for (Toy toy : toyQueue) {
            cumulativeFrequency += toy.getFrequency();
            if (randomNum <= cumulativeFrequency) {
                return toy;
            }
        }
        return null;
    }

    public void addPrizeToy(Toy toy) {
        prizeToys.add(toy);
    }

    public Toy getPrizeToy() {
        if (!prizeToys.isEmpty()) {
            return prizeToys.get(0);
        }
        return null;
    }

    public void removeAndWritePrizeToy() {
        if (!prizeToys.isEmpty()) {
            Toy firstToy = prizeToys.remove(0);
            System.out.println("Призовая игрушка с id " + firstToy.getId() + " удалена из списка.");

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("prize.txt", true))) {
                writer.write(firstToy.getId() + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void decreasePrizeToyCount() {
        if (!prizeToys.isEmpty()) {
            prizeToys.remove(0);
        }
    }
}