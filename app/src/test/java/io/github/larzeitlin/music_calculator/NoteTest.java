package io.github.larzeitlin.music_calculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NoteTest {

    @Test
    public void setHz() {
        Note testNote = new Note();

        testNote.setHz(550);
        assertEquals(550, testNote.getHz(), 0.0001);

        testNote.setHz(440.0);
        assertEquals(4, testNote.getOctave(), 0.0001);
        assertEquals("A", testNote.getNoteName());
        assertEquals(0, testNote.getCents());

        testNote.setHz(880.0);
        assertEquals(5, testNote.getOctave(), 0.0001);
        assertEquals("A", testNote.getNoteName());
        assertEquals(0, testNote.getCents());

        testNote.setHz(1479.98);
        assertEquals(6, testNote.getOctave(), 0.0001);
        assertEquals("F#/Gb", testNote.getNoteName());
        assertEquals(0, testNote.getCents());
    }


    @Test
    public void setNote() {
        Note testNote = new Note();

        testNote.setNote("D#/Eb", 2);
        assertEquals(2, testNote.getOctave(), 0.0001);
        assertEquals("D#/Eb", testNote.getNoteName());
        assertEquals(0, testNote.getCents());
        assertEquals(77.78, testNote.getHz(), 0.01);
    }
}