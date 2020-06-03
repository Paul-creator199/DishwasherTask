package dishwasherTask;

public class Dishwasher {
    private Status dishwasherStatus;
    private int numberOfCleanDishes;
    private int numberOfDirtyDishes;
    private int capacity;

    Dishwasher(int capacity, int numberOfCleanDishes, int numberOfDirtyDishes, Status status) {
        if (numberOfCleanDishes != 0) {
            numberOfDirtyDishes = 0;
        } else if (numberOfCleanDishes > capacity || numberOfDirtyDishes > capacity) {
            throw new RuntimeException("You cannot add more dishes.");
        }
        this.capacity = capacity;
        this.numberOfCleanDishes = numberOfCleanDishes;
        this.numberOfDirtyDishes = numberOfDirtyDishes;
        dishwasherStatus = status;
    }

    /**
     * This method gets the status of the dishwasher
     */
    public void getStatus() {
        if (dishwasherStatus == Status.STARTED) {
            System.out.println("The dishwasher is started");
        } else if (dishwasherStatus == Status.STOPPED) {
            System.out.println("The dishwasher is stopped.");
        }
    }

    /**
     * This method checks the content of the dishwasher
     */
    public void getContent() {
        if (numberOfCleanDishes != 0 && numberOfCleanDishes <= capacity) {
            System.out.print("The dishes were washed. Free the dishwasher.");
        } else if (numberOfDirtyDishes < capacity) {
            System.out.print("Add more dishes.");
        } else {
            System.out.print("The dishwasher is full, you cannot add new dishes.");
        }

    }

    /**
     * This method adds the dirty dishes to the dishwasher
     *
     * @param dish should be the name of the dish
     * @throws DishwasherIsStartedException if the dishwasher is already started
     * @throws HasCleanDishesException      if the dishwasher has clean dishes inside
     */
    public void addElement(String dish) throws DishwasherIsStartedException, HasCleanDishesException {
        if (dishwasherStatus == Status.STARTED) {
            throw new DishwasherIsStartedException("The dishwasher is already started.");
        }
        if (numberOfCleanDishes != 0) {
            throw new HasCleanDishesException("The dishwasher has clean dishes inside.");
        }

        for (int i = 0; i <= capacity; i++) {
            System.out.print("The " + dish + " was added.");
            numberOfDirtyDishes++;
        }
    }

    /**
     * This method takes the dishes from the dishwasher
     *
     * @throws DishwasherIsStartedException if the dishwasher is already started
     * @throws DishwasherIsEmptyException   if the dishwasher is empty
     */
    public void takeAllDishes() throws DishwasherIsEmptyException, DishwasherIsStartedException {
        if (dishwasherStatus == Status.STARTED) {
            throw new DishwasherIsStartedException("Stop the dishwasher first.");
        }
        if (numberOfCleanDishes == 0 || numberOfDirtyDishes == 0) {
            throw new DishwasherIsEmptyException("The dishwasher is empty.");
        }
        numberOfCleanDishes = 0;
        numberOfDirtyDishes = 0;

    }

    /**
     * This method starts the dishwasher
     *
     * @throws DishwasherIsStartedException if the dishwasher is already started
     * @throws HasCleanDishesException      if the dishwasher has clean dishes inside
     * @throws DishwasherIsEmptyException   if the dishwasher is empty
     */
    public void startWork() throws DishwasherIsStartedException, HasCleanDishesException,
            DishwasherIsEmptyException {
        if (dishwasherStatus == Status.STARTED) {
            throw new DishwasherIsStartedException("The dishwasher is already started.");
        }
        if (numberOfCleanDishes != 0) {
            throw new HasCleanDishesException("The dishwasher has clean dishes inside.");
        }
        if (numberOfDirtyDishes == 0) {
            throw new DishwasherIsEmptyException("The dishwasher is empty.");
        }

        dishwasherStatus = Status.STARTED;

    }

    /**
     * This method stops the dishwasher
     *
     * @throws DishwasherIsStoppedException if the dishwasher is already stopped
     */
    public void stopWork() throws DishwasherIsStoppedException {
        if (dishwasherStatus == Status.STOPPED) {
            throw new DishwasherIsStoppedException("The dishwasher is already stopped");
        }
        dishwasherStatus = Status.STOPPED;
    }
}

