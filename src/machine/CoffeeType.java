package machine;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum CoffeeType {
    ESPRESSO(1, 250, 0, 16, 4),
    LATTE(2, 350, 75, 20, 7),
    CAPPUCCINO(3, 200, 100, 12, 6);

    public final int id ,water, milk, beans, cost;
    private static final Map<Integer, CoffeeType> COFFEE_TYPE_MAP;

    CoffeeType(int id, int water, int milk, int beans, int cost) {
        this.id = id;
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cost = cost;
    }

    static {
        Map<Integer, CoffeeType> map = new ConcurrentHashMap<>();
        for (CoffeeType instance : CoffeeType.values()) {
            map.put(instance.id, instance);
        }
        COFFEE_TYPE_MAP = Collections.unmodifiableMap(map);
    }

    public static CoffeeType getInstance(int id) {
        return COFFEE_TYPE_MAP.get(id);
    }

}
