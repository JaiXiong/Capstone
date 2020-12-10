package io.modes;

public class HelpScreenMode extends TextDisplayMode {
    public HelpScreenMode() {
        super(
                "Help Screen",
                "",
                "Commands - ",
                "Arrow Keys: Move directionally. If a rival is present,",
                "\t\tAttack them for physical damage. (free)",
                "Alt Move: Haskell - make a bad Haskell joke, doing ",
                "\t\tcombined physical and intellectual damage. (15)",
                "Ctrl Move: Null Pointer - Point out a rival's poor code,",
                "\t\tdoing intellectual damage. (5)",
                "u/d: [u]se (or equip) or [d]iscard a carried item. (free)",
                "Ctrl-s/Ctrl-q: Exit the game, (s)aving or (q)uitting.",
                ">, <: take a staircase or emergency exit. (free)",
                "",
                "Special Attacks",
                "F1 - Cram: Use up all your energy to study hard, ",
                "\t\thealing yourself based on the energy used.(50+)",
                "F2 - F4: Locked! Gain levels to find out what these do..."
        );
    }
}
