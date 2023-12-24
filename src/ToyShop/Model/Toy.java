package ToyShop.Model;

public class Toy implements Comparable<Toy> {
    private int id;
    private String name;
    private int frequency;

    public Toy(int id, String name, int frequency) {
        this.id = id;
        this.name = name;
        this.frequency = frequency;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getFrequency() {
        return frequency;
    }

    @Override
    public int compareTo(Toy otherToy) {
        return Integer.compare(this.frequency, otherToy.frequency);
    }
}