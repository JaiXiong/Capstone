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
                ">, <: take a staircase or emergency exit"
        );
    }
}
