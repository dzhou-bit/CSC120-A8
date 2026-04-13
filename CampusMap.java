import java.util.ArrayList;

public class CampusMap {

    ArrayList<Building> buildings;

    /* Default constructor, initializes empty ArrayList */
    public CampusMap() {
        buildings = new ArrayList<Building>();
    }

    /**
     * Adds a Building to the map
     * @param b the Building to add
     */
    public void addBuilding(Building b) {
        System.out.println("Adding building...");
        buildings.add(b);
        System.out.println("--> Successfully added " + b.getName() + " to the map.");
    }

    /**
     * Removes a Building from the map
     * @param b the Building to remove
     * @return the removed Building
     */
    public Building removeBuilding(Building b) {
        System.out.println("Removing building...");
        buildings.remove(b);
        System.out.println("--> Successfully removed " + b.getName() + " from the map.");
        return b;
    }

    public String toString() {
        String mapString = "DIRECTORY of BUILDINGS";

        for (int i = 0; i < this.buildings.size(); i++) {
            mapString += "\n  " + (i + 1) + ". " + this.buildings.get(i).getName()
                    + " (" + this.buildings.get(i).getAddress() + ")";
        }
        return mapString;
    }

    public static void main(String[] args) {
        CampusMap myMap = new CampusMap();

        // original example buildings
        Building fordHall = new Building("Ford Hall", "100 Green Street Northampton, MA 01063", 4);
        Building bassHall = new Building("Bass Hall", "4 Tyler Court Northampton, MA 01063", 4);

        // 10+ more Smith buildings
        Library neilson = new Library("Neilson Library", "7 Neilson Dr, Northampton, MA", 4, true);
        Library huenes = new Library("Hillyer / Art Library", "Lyman Plant House area, Northampton, MA");
        House king = new House("King House", "51 College Lane, Northampton, MA", 3, true, true);
        House cutter = new House("Cutter House", "1 Paradise Road, Northampton, MA", true);
        House chapin = new House("Chapin House", "9 Paradise Road, Northampton, MA", 4, true, false);
        House wilson = new House("Wilson House", "Elm Street, Northampton, MA", 3, false);
        Cafe campusCafe = new Cafe("Campus Cafe", "25 Elm Street, Northampton, MA");
        Cafe compass = new Cafe("Compass Cafe", "Neilson Library, Northampton, MA", 1, 80, 40, 30, 20);
        Building seelye = new Building("Seelye Hall", "College Lane, Northampton, MA", 5);
        Building sabinReed = new Building("Sabin-Reed Hall", "Green Street, Northampton, MA", 4);
        Building burton = new Building("Burton Hall", "Green Street, Northampton, MA", 4);
        Building wright = new Building("Wright Hall", "Green Street, Northampton, MA", 4);
        Building josten = new Building("Josten Performing Arts Center", "Green Street, Northampton, MA", 3);
        Building collegeHall = new Building("College Hall", "Elm Street, Northampton, MA", 3);

        myMap.addBuilding(fordHall);
        myMap.addBuilding(bassHall);
        myMap.addBuilding(neilson);
        myMap.addBuilding(huenes);
        myMap.addBuilding(king);
        myMap.addBuilding(cutter);
        myMap.addBuilding(chapin);
        myMap.addBuilding(wilson);
        myMap.addBuilding(campusCafe);
        myMap.addBuilding(compass);
        myMap.addBuilding(seelye);
        myMap.addBuilding(sabinReed);
        myMap.addBuilding(burton);
        myMap.addBuilding(wright);
        myMap.addBuilding(josten);
        myMap.addBuilding(collegeHall);

        System.out.println();
        System.out.println(myMap);
        System.out.println();

        System.out.println("====================================");
        System.out.println("DEMONSTRATING OVERRIDDEN showOptions");
        System.out.println("====================================");
        king.showOptions();
        System.out.println();
        neilson.showOptions();
        System.out.println();
        campusCafe.showOptions();
        System.out.println();

        System.out.println("====================================");
        System.out.println("DEMONSTRATING OVERRIDDEN goToFloor");
        System.out.println("====================================");
        neilson.enter();
        neilson.goToFloor(4); // allowed because elevator
        neilson.exit();
        System.out.println();

        chapin.enter();
        chapin.goUp();
        chapin.goUp();
        chapin.exit();
        System.out.println();

        System.out.println("====================================");
        System.out.println("DEMONSTRATING OVERLOADED METHODS");
        System.out.println("====================================");

        // Library overloaded methods
        neilson.addTitle("Jane Eyre");
        neilson.addTitle("Pride and Prejudice", false);
        neilson.checkOut("Jane Eyre");
        neilson.checkOut("Pride and Prejudice", "Daphne");
        neilson.printCollection();
        System.out.println();

        // Cafe overloaded methods
        campusCafe.sellCoffee();
        campusCafe.sellCoffee(16);
        campusCafe.sellCoffee(20, 2, 1);
        System.out.println(campusCafe);
        System.out.println();

        // House overloaded constructors already used above
        // House overloaded moveIn methods demonstrated below
        // These require that your Student class exists in the project
        /*
        Student s1 = new Student("Mina", "33333");
        Student s2 = new Student("Alex", "44444");
        Student s3 = new Student("Jordan", "55555");

        king.moveIn(s1, s2);
        Student[] moreStudents = {s3};
        king.moveIn(moreStudents);
        System.out.println("Residents in King: " + king.nResidents());
        */

        System.out.println("Done with CampusMap demo.");
    }
}