package io.modes;

public class HelpScreenMode extends TextDisplayMode {
    public HelpScreenMode() {
        super(
                "Help Screen",
                "",
                "Commands - ",
                "Arrow Keys: Move (or Attack) in direction",
                "u: (U)se an item from your inventory(equip or consume)",
                "Ctrl-q: (Q)uit the game without saving",
                "Ctrl-s: (S)ave the game and exit to the main menu",
                ">, <: take a staircase or emergency exit",
                "",
                "Special Attacks",
                "",
                "F1 - Cram: Use up all your energy to study hard, ",
                "\t\thealing yourself and damaging your current target.",
                "\t\tBase cost is 50, bonus effects if higher."
        );
    }
}
