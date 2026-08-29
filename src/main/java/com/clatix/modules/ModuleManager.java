package com.clatix.modules;

import com.clatix.modules.modules.combat.*;
import com.clatix.modules.modules.movement.*;
import com.clatix.modules.modules.render.*;
import com.clatix.modules.modules.player.*;
import com.clatix.modules.modules.exploit.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleManager {
    private static ModuleManager instance;
    private List<Module> modules = new ArrayList<>();

    private ModuleManager() {}

    public static ModuleManager getInstance() {
        if (instance == null) {
            instance = new ModuleManager();
        }
        return instance;
    }

    public void init() {
        modules.add(new KillAura());
        modules.add(new CrystalAura());
        modules.add(new AutoCrystal());
        modules.add(new Criticals());
        modules.add(new AutoTrap());
        modules.add(new Surround());
        
        modules.add(new Sprint());
        modules.add(new Speed());
        modules.add(new Flight());
        modules.add(new Step());
        modules.add(new NoFall());
        modules.add(new Jesus());
        modules.add(new Scaffold());
        
        modules.add(new ESP());
        modules.add(new Fullbright());
        modules.add(new Chams());
        modules.add(new Tracers());
        modules.add(new Nametags());
        modules.add(new ViewModel());
        
        modules.add(new AutoEat());
        modules.add(new AutoLog());
        modules.add(new AutoReconnect());
        modules.add(new AntiHunger());
        
        modules.add(new BookDupe());
        modules.add(new ChunkBan());
        modules.add(new Coordinates());
    }

    public List<Module> getModules() { return modules; }
    public List<Module> getModulesByCategory(Module.Category category) {
        return modules.stream()
            .filter(m -> m.getCategory() == category)
            .collect(Collectors.toList());
    }
    public Module getModuleByName(String name) {
        return modules.stream()
            .filter(m -> m.getName().equalsIgnoreCase(name))
            .findFirst()
            .orElse(null);
    }
    public List<Module> getEnabledModules() {
        return modules.stream()
            .filter(Module::isEnabled)
            .collect(Collectors.toList());
    }
}