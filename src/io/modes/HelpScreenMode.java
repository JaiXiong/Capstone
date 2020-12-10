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
                "\t\thealing yourself. Base cost is 50 energy,",
                "\t\tbonus effects if higher.",
                "",
                "Ctrl+Move - Null Pointer: Move in direction. If",
                "\t\tthere's a baddie there, point out their sloppy",
                "\t\terror handling to deal damage. Costs 10 energy.",
                "",
                "Shift+Move - Haskell: Move in direction. If there's",
                "\t\ta baddie there, tell a bad joke about Haskell",
                "\t\tthat deals damage that's stronger with more",
                "\t\tenergy. Base cost is 50 energy."
        );
    }
}
