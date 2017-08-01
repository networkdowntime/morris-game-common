package net.networkdowntime.morris.dtos;

public class NewGameRequest extends GameRequestBase {
    public String gameBoardName;
    public boolean isPlayerOne;

    @Override
    public String toString() {
        return "NewGameRequest [gameBoardName=" + gameBoardName + ", isPlayerOne=" + isPlayerOne + ", playerName=" + playerName + ", algorithmName=" + algorithmName + ", aiEndpoint=" + aiEndpoint + "]";
    }

}
