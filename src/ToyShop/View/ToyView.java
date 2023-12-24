package ToyShop.View;

import ToyShop.Controller.ToyController;
import ToyShop.Model.Toy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToyView {
    private ToyController toyController;

    public ToyView(ToyController toyController) {
        this.toyController = toyController;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        List<String> lines = new ArrayList<>();

        System.out.println("Введите строки с id, частотой и названием игрушек:");
        for (int i = 0; i < 3; i++) {
            String line = scanner.nextLine();
            lines.add(line);
        }

        toyController.addItemsToQueue(lines.toArray(new String[0]));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("prize.txt"))) {
            for (int i = 0; i < 10; i++) {
                Toy prizeToy = toyController.choosePrizeToy();
                if (prizeToy != null) {
                    toyController.addPrizeToy(prizeToy);
                    toyController.removeAndWritePrizeToy(); // Удаляем из списка и записываем в файл

                    Toy nextPrizeToy = toyController.getPrizeToy();
                    if (nextPrizeToy != null) {
                        System.out.println("Получена призовая игрушка с id " + nextPrizeToy.getId());
                        toyController.decreasePrizeToyCount(); // Уменьшаем количество призовых игрушек
                    } else {
                        break;
                    }
                }
            }
            System.out.println("Результат розыгрыша записан в prize.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}
