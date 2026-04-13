import java.util.ArrayList;

public class House extends Building implements HouseRequirements {

    private ArrayList<Student> residents;
    private boolean hasDiningRoom;
    private boolean hasElevator;

    // original-style constructor
    public House(String name, String address, int nFloors, boolean hasDiningRoom) {
        this(name, address, nFloors, hasDiningRoom, false);
    }

    // overloaded constructor #1
    public House(String name, String address, boolean hasDiningRoom) {
        this(name, address, 1, hasDiningRoom, false);
    }

    // overloaded constructor #2
    public House(String name, String address, int nFloors, boolean hasDiningRoom, boolean hasElevator) {
        super(name, address, nFloors);
        this.residents = new ArrayList<Student>();
        this.hasDiningRoom = hasDiningRoom;
        this.hasElevator = hasElevator;
        System.out.println("You have built a house: 🏠");
    }

    public boolean hasDiningRoom() {
        return this.hasDiningRoom;
    }

    public boolean hasElevator() {
        return this.hasElevator;
    }

    public int nResidents() {
        return this.residents.size();
    }

    public void moveIn(Student s) {
        if (!this.isResident(s)) {
            this.residents.add(s);
        }
    }

    // overloaded method #1
    public void moveIn(Student s1, Student s2) {
        this.moveIn(s1);
        this.moveIn(s2);
    }

    // overloaded method #2
    public void moveIn(Student[] students) {
        for (Student s : students) {
            this.moveIn(s);
        }
    }

    public Student moveOut(Student s) {
        boolean removed = this.residents.remove(s);
        if (removed) {
            return s;
        }
        return null;
    }

    public boolean isResident(Student s) {
        return this.residents.contains(s);
    }

    @Override
    public void showOptions() {
        super.showOptions();
        System.out.println(" + moveIn(Student s)");
        System.out.println(" + moveIn(Student s1, Student s2)");
        System.out.println(" + moveIn(Student[] students)");
        System.out.println(" + moveOut(Student s)");
        System.out.println(" + isResident(Student s)");
        System.out.println(" + nResidents()");
        System.out.println(" + hasDiningRoom()");
    }

    @Override
    public void goToFloor(int floorNum) {
        if (this.activeFloor == -1) {
            throw new RuntimeException("You are not inside this House. Must call enter() before navigating between floors.");
        }

        if (floorNum < 1 || floorNum > this.nFloors) {
            throw new RuntimeException("Invalid floor number. Valid range for this House is 1-" + this.nFloors + ".");
        }

        int difference = Math.abs(floorNum - this.activeFloor);

        if (difference <= 1 || this.hasElevator) {
            super.goToFloor(floorNum);
        } else {
            throw new RuntimeException("This house does not have an elevator, so you can only move one floor at a time.");
        }
    }

    public String toString() {
        return super.toString()
            + " It is a house with "
            + this.nResidents()
            + " resident(s), dining room: "
            + this.hasDiningRoom
            + ", elevator: "
            + this.hasElevator
            + ".";
    }

    public static void main(String[] args) {
        House king = new House("King House", "123 Main St", 3, true, true);
        House cutter = new House("Cutter House", "1 Paradise Rd", true);

        System.out.println(king);
        System.out.println(cutter);

        king.showOptions();

        king.enter();
        king.goToFloor(3);
        king.goDown();
        king.exit();
    }
}