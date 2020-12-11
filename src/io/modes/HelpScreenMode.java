package io.modes;


import asset.character.PlayerCharacter;
import engine.Gamestate;

import java.util.ArrayList;

public class HelpScreenMode extends TextDisplayMode {

    private static final int LINES = 16;

    public HelpScreenMode() {
        super(buildHelpScreen());
    }

    private static String[] buildHelpScreen() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("Help Screen");
        strings.add("");
        strings.add("Commands - ");
        strings.add("Arrow Keys: Move directionally. If a rival is present,");
        strings.add("\t\tAttack them for physical damage. (free)");
        strings.add("Alt Move: Haskell - make a bad Haskell joke); doing ");
        strings.add("\t\tcombined physical and intellectual damage. (15)");
        strings.add("Ctrl Move: Null Pointer - Point out a rival's poor code);");
        strings.add("\t\tdoing intellectual damage. (5)");
        strings.add("u/d: [u]se (or equip) or [d]iscard a carried item. (free)");
        strings.add("Ctrl-s/Ctrl-q: Exit the game); (s)aving or (q)uitting.");
        strings.add(">); <: take a staircase or emergency exit. (free)");
        strings.add("");
        strings.add("Special Attacks");
        strings.add("F1 - Cram: Use up all your energy to study hard); ");
        strings.add("\t\thealing yourself based on the energy used.(50+)");
        PlayerCharacter pc = Gamestate.getInstance().getPlayerCharacter();
        if (pc.getLevel() >= 2) {
            strings.add("F2: Rivalry: Expend your own health to damage and");
            strings.add("\t\tsteal your rivals energy for your own. (12.5%)");
            if (pc.getLevel() >= 4) {
                strings.add("F3: Top of the Class: Use some energy to assert your");
                strings.add("\t\tstatus as the best, damaging all others. (25)");
                if (pc.getLevel() >= 8) {
                    strings.add("F4: Invigorating Focus: Use some energy to damage");
                    strings.add("\t\tyour rival, draining its energy and healing yourself. (25%)");
                } else {
                    strings.add("F4: Locked! Level up to gain access to these!");
                }
            } else {
                strings.add("F3-F4: Locked! Level up to gain access to these!");
            }
        } else {
            strings.add("F2-F4: Locked! Level up to gain access to these!");
        }
        return strings.toArray(new String[strings.size()]);
    }
}
