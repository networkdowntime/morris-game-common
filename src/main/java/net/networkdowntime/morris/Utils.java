package net.networkdowntime.morris;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class Utils {
    static final Logger log = LogManager.getLogger(Utils.class);

    public static String getFileAsString(String fileName) throws IOException {
        StringBuilder builder = new StringBuilder();
        ClassLoader classLoader = Utils.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream(fileName);
        if (is == null) {
            File f = new File(fileName);
            if (f.exists() && !f.isDirectory()) {
                is = new FileInputStream(f);
            } else {
                throw new IOException("File " + fileName + " could not be found.");
            }
        }
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        while (br.ready())
            builder.append(br.readLine());
        return builder.toString();
    }

    public static String[] getFile(String fileName) throws IOException {
        List<String> lines = new ArrayList<String>();
        ClassLoader classLoader = Utils.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream(fileName);
        if (is == null) {
            File f = new File(fileName);
            if (f.exists() && !f.isDirectory()) {
                is = new FileInputStream(f);
            } else {
                throw new IOException("File " + fileName + " could not be found.");
            }
        }
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        while (br.ready())
            lines.add(br.readLine());
        return lines.toArray(new String[0]);
    }

    public static String signedInt(int val) {
        if (val > 0) return "+" + val;
        else if (val < 0) return "" + val;
        else return " " + val;
    }

    /**
     * Parses a JSON array of Strings and returns a String[]
     * @param json
     * @return
     * @throws Exception
     */
    public static String[] jsonStringArrayToStringArray(String json) throws Exception {
        // parse the JSON array
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, TypeFactory.defaultInstance().constructArrayType(String.class));
    }

    public static char[][] stringArrayToCharArray(String[] stringArr, boolean requireEqualLengths) throws Exception {
        char[][] charArray = new char[stringArr.length][];
        for (int y = 0; y < stringArr.length; y++) {
            String line = stringArr[y];
            char[] charLine = line.toCharArray();
            if (requireEqualLengths && y > 0 && charLine.length != charArray[y - 1].length) throw new Exception("All lines lengths in board must be equal.  Line[" + y + "] length != Line[" + (y - 1) + "].length");
            charArray[y] = charLine;
        }
        return charArray;
    }

    public static void writeBoardState(String movesFilename, String boardState, boolean doDelete) throws IOException {
        if (movesFilename != null) {
            File file = new File(movesFilename);
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(boardState);
            bw.flush();
            bw.close();
            if (doDelete) file.delete();
        }
    }

    public static String swapBoardState(String boardState, char playerOneChar, char playerTwoChar) {
        char[] temp = boardState.toCharArray();
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] == playerOneChar) temp[i] = playerTwoChar;
            else if (temp[i] == playerTwoChar) temp[i] = playerOneChar;
        }
        return new String(temp);
    }

    public static void tryDeletingFiles(String... fileNames) {
        for (String fileName : fileNames) {
            try {
                File file = new File(fileName);
                file.delete();
            } catch (SecurityException e) {
                log.error("Unable to delete file: " + fileName);
            }
        }
    }

    public static List<String> readInputStream(InputStream inputStream) throws IOException {
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(inputStream));
        ArrayList<String> lines = new ArrayList<String>();
        String line = "";
        while ((line = inputReader.readLine()) != null) {
            lines.add(line);
        }
        return lines;
    }

    public static String readInputStreamSingleLine(InputStream inputStream) throws IOException {
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer retval = new StringBuffer();
        String line = "";
        while ((line = inputReader.readLine()) != null) {
            retval.append(line);
        }
        return retval.toString();
    }

    public static String getWhiteSpacePadding(int paddingSize) {
        StringBuilder padding = new StringBuilder();
        for (int i = 0; i < paddingSize; i++)
            padding.append(" ");
        return padding.toString();
    }

    public static String highlightMove(String description, String previousBoard, String currentBoard, boolean isWhitesTurn) {
        if (description == null) description = "";
        else if (!description.isEmpty() && !description.endsWith(" ")) description = description + " ";

        StringBuilder sb = new StringBuilder();

        char myPieceColor = (isWhitesTurn) ? 'W' : 'B';

        String padding = getWhiteSpacePadding(description.length());

        boolean hasMove = false;
        for (int i = 0; i < currentBoard.length(); i++)
            if (currentBoard.charAt(i) == 'x' && previousBoard.charAt(i) == myPieceColor) hasMove = true;

        char padChar = ' ';
        sb.append(padding);
        for (int i = 0; i < currentBoard.length(); i++)
            if (currentBoard.charAt(i) == 'x' && previousBoard.charAt(i) == myPieceColor) {
                if (hasMove && padChar == ' ') padChar = '-';
                else padChar = ' ';
                sb.append("^");
            } else if (currentBoard.charAt(i) == 'x' && previousBoard.charAt(i) != 'x') sb.append(padChar);
            else if (currentBoard.charAt(i) != previousBoard.charAt(i)) {
                if (hasMove && padChar == ' ') padChar = '-';
                else padChar = ' ';
                sb.append("|");
            } else {
                sb.append(padChar);
            }
        sb.append(System.lineSeparator());

        sb.append(padding);
        for (int i = 0; i < currentBoard.length(); i++)
            if (currentBoard.charAt(i) == 'x' && previousBoard.charAt(i) == myPieceColor) sb.append("|");
            else if (currentBoard.charAt(i) == 'x' && previousBoard.charAt(i) != 'x') sb.append("C");
            else sb.append((currentBoard.charAt(i) != previousBoard.charAt(i)) ? "V" : " ");
        sb.append(System.lineSeparator());

        sb.append(description).append(currentBoard);
        return sb.toString();
    }

}
