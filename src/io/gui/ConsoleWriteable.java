package io.gui;

import console.ConsoleGlyph;

/**
 * Any object that might need to be rendered to the console must implement this interface.
 */
public interface ConsoleWriteable {
    ConsoleGlyph getConsoleGlyph();
}
