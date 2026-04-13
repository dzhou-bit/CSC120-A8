public class Cafe extends Building implements CafeRequirements {

    private int nCoffeeOunces;
    private int nSugarPackets;
    private int nCreams;
    private int nCups;

    // original-style constructor
    public Cafe(String name, String address, int nFloors,
                int nCoffeeOunces, int nSugarPackets, int nCreams, int nCups) {
        super(name, address, nFloors);
        this.nCoffeeOunces = nCoffeeOunces;
        this.nSugarPackets = nSugarPackets;
        this.nCreams = nCreams;
        this.nCups = nCups;
        System.out.println("You have built a cafe: ☕");
    }

    // overloaded constructor #1
    public Cafe(String name, String address) {
        this(name, address, 1, 100, 50, 50, 25);
    }

    // overloaded constructor #2
    public Cafe(String name, String address, int nCoffeeOunces, int nSugarPackets, int nCreams, int nCups) {
        this(name, address, 1, nCoffeeOunces, nSugarPackets, nCreams, nCups);
    }

    public void sellCoffee(int size, int nSugarPackets, int nCreams) {
        if (this.nCoffeeOunces < size ||
            this.nSugarPackets < nSugarPackets ||
            this.nCreams < nCreams ||
            this.nCups < 1) {

            restock(size, nSugarPackets, nCreams, 1);
        }

        this.nCoffeeOunces -= size;
        this.nSugarPackets -= nSugarPackets;
        this.nCreams -= nCreams;
        this.nCups -= 1;

        System.out.println("Sold a " + size + " oz coffee with "
                + nSugarPackets + " sugar packet(s) and "
                + nCreams + " cream(s).");
    }

    // overloaded method #1
    public void sellCoffee(int size) {
        this.sellCoffee(size, 0, 0);
    }

    // overloaded method #2
    public void sellCoffee() {
        this.sellCoffee(12, 0, 0);
    }

    private void restock(int nCoffeeOunces, int nSugarPackets, int nCreams, int nCups) {
        this.nCoffeeOunces += nCoffeeOunces;
        this.nSugarPackets += nSugarPackets;
        this.nCreams += nCreams;
        this.nCups += nCups;
        System.out.println("Cafe restocked automatically.");
    }

    @Override
    public void showOptions() {
        super.showOptions();
        System.out.println(" + sellCoffee(int size, int nSugarPackets, int nCreams)");
        System.out.println(" + sellCoffee(int size)");
        System.out.println(" + sellCoffee()");
    }

    @Override
    public void goToFloor(int floorNum) {
        if (this.activeFloor == -1) {
            throw new RuntimeException("You are not inside this Cafe. Must call enter() before navigating between floors.");
        }

        if (floorNum < 1 || floorNum > this.nFloors) {
            throw new RuntimeException("Invalid floor number. Valid range for this Cafe is 1-" + this.nFloors + ".");
        }

        int difference = Math.abs(floorNum - this.activeFloor);

        if (difference <= 1) {
            super.goToFloor(floorNum);
        } else {
            throw new RuntimeException("This cafe does not have an elevator, so you can only move one floor at a time.");
        }
    }

    public String toString() {
        return super.toString()
            + " Supplies: "
            + this.nCoffeeOunces + " oz coffee, "
            + this.nSugarPackets + " sugar packet(s), "
            + this.nCreams + " cream(s), "
            + this.nCups + " cup(s).";
    }

    public static void main(String[] args) {
        Cafe myCafe = new Cafe("Campus Cafe", "25 Elm St");

        myCafe.sellCoffee();
        myCafe.sellCoffee(16);
        myCafe.sellCoffee(12, 2, 1);

        System.out.println(myCafe);
    }
}