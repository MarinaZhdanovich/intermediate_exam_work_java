package ToyShop;

import ToyShop.Controller.ToyController;
import ToyShop.Model.Toy;
import ToyShop.View.ToyView;


public class Main {
    public static void main(String[] args) {
        ToyController toyController = new ToyController();
        ToyView toyView = new ToyView(toyController);

        toyView.start();
    }
}