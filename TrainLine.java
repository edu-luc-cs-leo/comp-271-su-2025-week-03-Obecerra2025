public class TrainLine {

    private static final String DEFAULT_NAME = "Hogwarts Express";

    /** The name of the train line */
    private String name;
    /** The head station of the train line */
    private Station head;
    /** Missing the last station of the train line so we add this so it could be easier than looping */
    private Station lastStation;
    /** Current number of stations in the line */
    private int numberOfStations;

    /** Basic constructor */
    public TrainLine(String name) {
        this.name = name;
        this.head = null;
        this.lastStation = null;
        this.numberOfStations = 0;
    } // basic constructor

    /** Default constructor */
    public TrainLine() {
        this(DEFAULT_NAME);
    } // default constructor

    /** Accessor for the number of stations */
    public int getNumberOfStations() {
        return this.numberOfStations;
    }

    /**
     * Adds a new station after the last station of a trainline.
     * 
     * @param name String with name of new station to create and add
     */
    public void add(String name) {
        Station newStation = new Station(name);
        if (this.head == null) {
            // No stations exist in this line. Make this new station
            // the head station of the line
            // Set both head and lastStation to the new station
            this.head = newStation;
            this.lastStation = newStation;
        } else {
            // If lastStation works we can add the new station
            if (this.lastStation != null) {
                this.lastStation.setNext(newStation); // connect last to new
                this.lastStation = newStation; // update last
            } else {
                // The line has at least one station (the head station).
                // Find the last station and make its next station the new one.
                Station cursor = this.head;
                while (cursor.hasNext()) {
                    cursor = cursor.getNext(); 
                }
                // Cursor is now at the last station of the line
                cursor.setNext(newStation);
                this.lastStation = newStation;
            }
        }

        this.numberOfStations++;   
    }

    /**
     * Finds how many stations are in a train line
     * 
     * METHOD MADE OBSOLETE BY INTRODUCTING TrainLine.numberOfStations
     */
    public int indexOf(String stationName) {
        // Start at the first station in the line
        Station cursor = this.head;
        // keep track of the current potition
        int index = 0;
        // Go through each station in the train line
        while (cursor != null) {
            // If we find the correct name
            if (cursor.getName().equals(stationName)) {
                return index; // Return its position
            }

            // Move to the next station
            cursor = cursor.getNext();
            index++; // update position
        }

        // If not found return -1
        return -1;
    } // method countStations

    public boolean contains(String stationName) {
        // If index returns anything except -1 means the station is found
        return this.indexOf(stationName) != -1;
    }


    /**
     * String representation of the object
     */
    private static final String EMPTY_TRAIN_LINE = "The train line is empty";
    private static final String TRAIN_LINE_HEADER = "\"%s\" has the following stations: %s";
    private static final String NEXT_ARROW = " --> ";

    public String toString() {
        if (this.head == null) {
            return EMPTY_TRAIN_LINE;
        } else {
            String stations = String.format(TRAIN_LINE_HEADER, this.name, this.head.getName());
            Station cursor = this.head.getNext();
            while (cursor != null) {
                stations = stations + NEXT_ARROW + cursor.getName();
                cursor = cursor.getNext();
            }
            return stations;
        }
    } // method toString
} // class TrainLine
