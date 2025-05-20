public class Elevator {
    private int currentFloor = 1;
    private final int minFloor;
    private final int maxFloor;

    Elevator(int minFloor, int maxFloor) {
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
    }

    public int getCurrentFloor() {
        return this.currentFloor;
    }

    private void moveDown() {
        if (this.currentFloor == 1) {
            this.currentFloor = -1;
        } else {
            --this.currentFloor;
        }

    }

    private void moveUp() {
        if (this.currentFloor == -1) {
            this.currentFloor = 1;
        } else {
            ++this.currentFloor;
        }

    }

    public void move(int floor) {
        if (floor >= this.minFloor && floor <= this.maxFloor) {
            while ( this.currentFloor != floor) {
                if (this.currentFloor > floor) {
                    this.moveDown();
                } else {
                    this.moveUp();
                }
                System.out.println("Current Floor: " + this.currentFloor);
            }
        } else {
            System.out.println("Floor not Exist");
        }
    }
}
