import java.util.Hashtable;

public class Library extends Building implements LibraryRequirements {

    private Hashtable<String, Boolean> collection;
    private boolean hasElevator;

    // original-style constructor
    public Library(String name, String address, int nFloors) {
        this(name, address, nFloors, true);
    }

    // overloaded constructor #1
    public Library(String name, String address) {
        this(name, address, 2, true);
    }

    // overloaded constructor #2
    public Library(String name, String address, int nFloors, boolean hasElevator) {
        super(name, address, nFloors);
        this.collection = new Hashtable<String, Boolean>();
        this.hasElevator = hasElevator;
        System.out.println("You have built a library: 📖");
    }

    public boolean hasElevator() {
        return this.hasElevator;
    }

    public void addTitle(String title) {
        this.collection.put(title, true);
    }

    // overloaded method #1
    public void addTitle(String title, boolean available) {
        this.collection.put(title, available);
    }

    public String removeTitle(String title) {
        Boolean removed = this.collection.remove(title);
        if (removed != null) {
            return title;
        }
        return null;
    }

    public void checkOut(String title) {
        if (this.collection.containsKey(title) && this.collection.get(title)) {
            this.collection.replace(title, false);
            System.out.println("Checked out: " + title);
        } else {
            System.out.println("Cannot check out: " + title);
        }
    }

    // overloaded method #2
    public void checkOut(String title, String borrower) {
        if (this.collection.containsKey(title) && this.collection.get(title)) {
            this.collection.replace(title, false);
            System.out.println(borrower + " checked out: " + title);
        } else {
            System.out.println("Cannot check out: " + title);
        }
    }

    public void returnBook(String title) {
        if (this.collection.containsKey(title)) {
            this.collection.replace(title, true);
            System.out.println("Returned: " + title);
        }
    }

    public boolean containsTitle(String title) {
        return this.collection.containsKey(title);
    }

    public boolean isAvailable(String title) {
        if (this.collection.containsKey(title)) {
            return this.collection.get(title);
        }
        return false;
    }

    public void printCollection() {
        for (String title : this.collection.keySet()) {
            boolean available = this.collection.get(title);
            String status = available ? "Available" : "Checked out";
            System.out.println(title + " -- " + status);
        }
    }

    @Override
    public void showOptions() {
        super.showOptions();
        System.out.println(" + addTitle(String title)");
        System.out.println(" + addTitle(String title, boolean available)");
        System.out.println(" + removeTitle(String title)");
        System.out.println(" + checkOut(String title)");
        System.out.println(" + checkOut(String title, String borrower)");
        System.out.println(" + returnBook(String title)");
        System.out.println(" + containsTitle(String title)");
        System.out.println(" + isAvailable(String title)");
        System.out.println(" + printCollection()");
    }

    @Override
    public void goToFloor(int floorNum) {
        if (this.activeFloor == -1) {
            throw new RuntimeException("You are not inside this Library. Must call enter() before navigating between floors.");
        }

        if (floorNum < 1 || floorNum > this.nFloors) {
            throw new RuntimeException("Invalid floor number. Valid range for this Library is 1-" + this.nFloors + ".");
        }

        int difference = Math.abs(floorNum - this.activeFloor);

        if (difference <= 1 || this.hasElevator) {
            super.goToFloor(floorNum);
        } else {
            throw new RuntimeException("This library does not have an elevator, so you can only move one floor at a time.");
        }
    }

    public String toString() {
        return super.toString()
            + " It is a library with "
            + this.collection.size()
            + " title(s) and elevator: "
            + this.hasElevator
            + ".";
    }

    public static void main(String[] args) {
        Library lib = new Library("Neilson Library", "7 Neilson Dr, Northampton, MA", 4, true);

        lib.addTitle("The Lorax by Dr. Seuss");
        lib.addTitle("1984 by George Orwell", false);

        lib.printCollection();
        lib.checkOut("The Lorax by Dr. Seuss", "Daphne");
        lib.returnBook("1984 by George Orwell");

        lib.enter();
        lib.goToFloor(4);
        lib.exit();
    }
}