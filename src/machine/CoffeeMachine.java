package machine;


public class CoffeeMachine {

    private MachineState machineState;
    private FillingState fillingState;
    private int waterSupplies;
    private int milkSupplies;
    private int beansSupplies;
    private int cupsSupplies;
    private int moneyEarned;


    public CoffeeMachine() {
        fillingState = null;
        machineState = MachineState.START_MENU;
        moneyEarned = 550;
        waterSupplies = 400;
        milkSupplies = 540;
        beansSupplies = 120;
        cupsSupplies = 9;
    }

    private String checkSupplies(CoffeeType coffeeType) {
        if (waterSupplies < coffeeType.water) return "water";
        if (milkSupplies < coffeeType.milk) return "milk";
        if (beansSupplies < coffeeType.beans) return "beans";
        if (cupsSupplies == 0) return "disposable cups";
        return "";
    }

    private void buy(CoffeeType coffeeType) {
        if (waterSupplies >= coffeeType.water && milkSupplies >= coffeeType.milk &&
            beansSupplies >= coffeeType.beans && cupsSupplies > 0)
        {
            waterSupplies -= coffeeType.water;
            milkSupplies -= coffeeType.milk;
            beansSupplies -= coffeeType.beans;
            cupsSupplies--;
            moneyEarned += coffeeType.cost;
        }
    }

    private String status() {
        return "The coffee machine has:\n" +
                waterSupplies + " ml of water\n" +
                milkSupplies + " ml of milk\n" +
                beansSupplies + " g of coffee beans\n" +
                cupsSupplies + " disposable cups\n" +
                "$" + moneyEarned + " of money";
    }

    public void startMenu() {
        if (!machineState.equals(MachineState.START_MENU)) System.out.println();
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        machineState = MachineState.CHOOSING_ACTION;
    }

    public void run(String command) {
        switch (machineState) {
            case CHOOSING_ACTION:
                if (MachineState.hasInstance(command)){
                    System.out.println();
                    machineState = MachineState.getInstance(command);
                    switch (machineState) {
                        case BUYING_COFFEE:
                            System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                            break;

                        case FILLING_SUPPLIES:
                            fillingState = FillingState.WATER;
                            System.out.println("Write how many ml of water you want to add:");
                            break;

                        case GIVING_MONEY:
                            System.out.println("I gave you $" + moneyEarned);
                            moneyEarned = 0;
                            startMenu();
                            break;

                        case CHECKING_REMAINING:
                            System.out.println(status());
                            startMenu();
                            break;
                    }
                }
                break;

            case BUYING_COFFEE:
                if (!"back".equals(command)) {
                    try {
                        int id = Integer.parseInt(command);
                        CoffeeType coffeeType = CoffeeType.getInstance(id);
                        String checkSuppliesResult = checkSupplies(coffeeType);
                        if("".equals(checkSuppliesResult)) {
                            buy(coffeeType);
                            System.out.println("I have enough resources, making you a coffee!");
                        } else {
                            System.out.println("Sorry, not enough " + checkSuppliesResult + "!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(e.getMessage());
                    }
                }
                startMenu();
                break;

            case FILLING_SUPPLIES:
                try {
                    int suppliesAmount = Integer.parseInt(command);
                    switch (fillingState) {
                        case WATER:
                            waterSupplies += suppliesAmount;
                            fillingState = FillingState.MILK;
                            System.out.println("Write how many ml of milk you want to add:");
                            break;

                        case MILK:
                            milkSupplies += suppliesAmount;
                            fillingState = FillingState.BEANS;
                            System.out.println("Write how many grams of coffee beans you want to add:");
                            break;

                        case BEANS:
                            beansSupplies += suppliesAmount;
                            fillingState = FillingState.CUPS;
                            System.out.println("Write how many disposable cups of coffee you want to add: ");
                            break;

                        case CUPS:
                            fillingState = null;
                            cupsSupplies += suppliesAmount;
                            startMenu();
                            break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                }
                break;

        }
    }
}
