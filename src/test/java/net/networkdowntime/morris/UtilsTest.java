package net.networkdowntime.morris;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UtilsTest {

    @Test
    public void testHighlightAddWhite() {
        String previousBoard = "BxxWBBWBWWxWBWWWBBxxBxx";
        String currentBoard = "BWxWBBWBWWxWBWWWBBxxBxx";

        String highlight = Utils.highlightMove("", previousBoard, currentBoard, true);
        System.out.println(highlight);
        String[] lines = highlight.split(System.lineSeparator());
        assertEquals(" |                     ", lines[0]);
        assertEquals(" V                     ", lines[1]);
        assertEquals(currentBoard, lines[2]);
    }

    @Test
    public void testHighlightAddBlack() {
        String previousBoard = "BWxWBBWBWWxWBWWWBBxxBxx";
        String currentBoard = "BWxWBBWBWWxWBWWWBBBxBxx";

        String highlight = Utils.highlightMove("", previousBoard, currentBoard, false);
        System.out.println(highlight);
        String[] lines = highlight.split(System.lineSeparator());
        assertEquals("                  |    ", lines[0]);
        assertEquals("                  V    ", lines[1]);
        assertEquals(currentBoard, lines[2]);
    }

    @Test
    public void testHighlightMoveWhite() {
        String previousBoard = "BxxWBBWBWWxWBWWWBBxxBxx";
        String currentBoard = "BWxWBBWBWWxWBWWxBBxxBxx";

        String highlight = Utils.highlightMove("", previousBoard, currentBoard, true);
        System.out.println(highlight);
        String[] lines = highlight.split(System.lineSeparator());
        assertEquals(" |-------------^       ", lines[0]);
        assertEquals(" V             |       ", lines[1]);
        assertEquals(currentBoard, lines[2]);
    }

    @Test
    public void testHighlightMoveBlack() {
        String previousBoard = "BWxWBBWBWWxWBWWWBBxxBxx";
        String currentBoard = "xWxWBBWBWWxWBWWWBBBxBxx";

        String highlight = Utils.highlightMove("", previousBoard, currentBoard, false);
        System.out.println(highlight);
        String[] lines = highlight.split(System.lineSeparator());
        assertEquals("^-----------------|    ", lines[0]);
        assertEquals("|                 V    ", lines[1]);
        assertEquals(currentBoard, lines[2]);
    }

    @Test
    public void testHighlightMoveCaptureWhitesMove1() {
        String previousBoard = "BxxWBBWBWWxWBWWWBBxxBxx";
        String currentBoard = "BWxWxBWBWWxWBWWWBBxxBxx";

        String highlight = Utils.highlightMove("", previousBoard, currentBoard, true);
        System.out.println(highlight);
        String[] lines = highlight.split(System.lineSeparator());
        assertEquals(" |                     ", lines[0]);
        assertEquals(" V  C                  ", lines[1]);
        assertEquals(currentBoard, lines[2]);
    }

    @Test
    public void testHighlightMoveCaptureBlacksMove1() {
        String previousBoard = "BWxWBBWBWWxWBWWWBBxxBxx";
        String currentBoard = "BWxWBBWBWWxWBWWxBBBxBxx";

        String highlight = Utils.highlightMove("", previousBoard, currentBoard, false);
        System.out.println(highlight);
        String[] lines = highlight.split(System.lineSeparator());
        assertEquals("                  |    ", lines[0]);
        assertEquals("               C  V    ", lines[1]);
        assertEquals(currentBoard, lines[2]);
    }

}
