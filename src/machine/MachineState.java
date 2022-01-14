package machine;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum MachineState {
    START_MENU("start"),
    CHOOSING_ACTION("CHOOSING_ACTION"),
    BUYING_COFFEE("buy"),
    CHECKING_REMAINING("remaining"),
    FILLING_SUPPLIES("fill"),
    GIVING_MONEY("take");
    
    public final String name;
    private static final Map<String, MachineState> MACHINE_STATE_MAP;
    
    MachineState(String name) {
        this.name = name;
    }
    
    static {
        Map<String, MachineState> map = new ConcurrentHashMap<>();
        for (MachineState instance :
                MachineState.values()) {
            map.put(instance.name, instance);
        }
        MACHINE_STATE_MAP = Collections.unmodifiableMap(map);
    }

    public static MachineState getInstance(String name) {
        return MACHINE_STATE_MAP.get(name);
    }

    public static boolean hasInstance(String name) {
        return MACHINE_STATE_MAP.containsKey(name);
    }
}
