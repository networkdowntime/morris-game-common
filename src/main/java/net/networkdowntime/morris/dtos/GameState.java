package net.networkdowntime.morris.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import net.networkdowntime.morris.Utils;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GameState {
    public int id;

    public String playerOneUsername; // W
    public String playerOneAlgorithmName;
    public boolean playerOneIsAutomated = false;
    public String playerOneAiEndpoint = null; // an API endpoint that can move for player one
    public int playerOneUnplayedPieceCount;
    public int playerOneEndpointFailCount = 0;

    public String playerTwoUsername; // B
    public String playerTwoAlgorithmName;
    public boolean playerTwoIsAutomated = false;
    public String playerTwoAiEndpoint = null; // an API endpoint that can move for player two
    public int playerTwoUnplayedPieceCount;
    public int playerTwoEndpointFailCount = 0;

    public boolean isPlayerOnesTurn = true;
    public Boolean canCapture = null;
    public String currentBoardState;
    public boolean isGameOver = false;

    public String gameCreatedByUsername;
    public Date gameCreateTime; // when the game was first created
    public Date gameStartTime; // when the second player join making it inProgress
    public Date gameCompleteTime;
    public String winnersUsername;
    public int numberOfMovesMade = 0;
    public List<String> moveHistory = new ArrayList<String>();
    public List<Date> moveTimeStamps = new ArrayList<Date>();

    public Set<Line> lines;
    public List<MovePosition> movePositions;

    public void swapPlayers(char playerOneChar, char playerTwoChar) {
        String tempUsername = playerOneUsername;
        String tempAlgorithmName = playerOneAlgorithmName;
        boolean tempIsAutomated = playerOneIsAutomated;
        String tempAiEndpoint = playerOneAiEndpoint;
        int tempUnplayedPieceCount = playerOneUnplayedPieceCount;

        playerOneUsername = playerTwoUsername;
        playerOneAlgorithmName = playerTwoAlgorithmName;
        playerOneIsAutomated = playerTwoIsAutomated;
        playerOneAiEndpoint = playerTwoAiEndpoint;
        playerOneUnplayedPieceCount = playerTwoUnplayedPieceCount;

        playerTwoUsername = tempUsername;
        playerTwoAlgorithmName = tempAlgorithmName;
        playerTwoIsAutomated = tempIsAutomated;
        playerTwoAiEndpoint = tempAiEndpoint;
        playerTwoUnplayedPieceCount = tempUnplayedPieceCount;

        isPlayerOnesTurn = !isPlayerOnesTurn;

        char[] temp = currentBoardState.toCharArray();
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] == playerOneChar) temp[i] = playerTwoChar;
            else if (temp[i] == playerTwoChar) temp[i] = playerOneChar;
        }
        currentBoardState = Utils.swapBoardState(currentBoardState, playerOneChar, playerTwoChar);
    }

    public <T extends GameState> T clone(Class<T> clazz) {
        T retval = this.cloneSmall(clazz);
        retval.playerOneAiEndpoint = playerOneAiEndpoint;
        retval.playerTwoAiEndpoint = playerTwoAiEndpoint;
        return retval;
    }

    public <T extends GameState> T cloneSmall(Class<T> clazz) {
        T retval = null;
        try {
            retval = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        retval.id = id;

        retval.playerOneUsername = playerOneUsername;
        retval.playerOneAlgorithmName = playerOneAlgorithmName;
        retval.playerOneIsAutomated = playerOneIsAutomated;
        retval.playerOneUnplayedPieceCount = playerOneUnplayedPieceCount;
        retval.playerOneEndpointFailCount = playerOneEndpointFailCount;

        retval.playerTwoUsername = playerTwoUsername;
        retval.playerTwoAlgorithmName = playerTwoAlgorithmName;
        retval.playerTwoIsAutomated = playerTwoIsAutomated;
        retval.playerTwoUnplayedPieceCount = playerTwoUnplayedPieceCount;
        retval.playerTwoEndpointFailCount = playerTwoEndpointFailCount;

        retval.isPlayerOnesTurn = isPlayerOnesTurn;
        retval.currentBoardState = currentBoardState;
        retval.isGameOver = isGameOver;

        retval.gameCreatedByUsername = gameCreatedByUsername;
        retval.gameCreateTime = gameCreateTime;
        retval.gameStartTime = gameStartTime;
        retval.gameCompleteTime = gameCompleteTime;
        retval.winnersUsername = winnersUsername;
        retval.numberOfMovesMade = numberOfMovesMade;
        retval.moveHistory = new ArrayList<String>(moveHistory);
        retval.moveTimeStamps = new ArrayList<Date>(moveTimeStamps);
        return retval;
    }

    public GameState populateLinesAndMovePositions(Set<Line> lines, List<MovePosition> movePositions) {
        this.lines = lines;
        this.movePositions = movePositions;
        return this;
    }

}