package machine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Launcher
{
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        String action;

        coffeeMachine.startMenu();

        do {
            action = reader.readLine();
            coffeeMachine.run(action);
        } while (!"exit".equals(action));
    }
}
