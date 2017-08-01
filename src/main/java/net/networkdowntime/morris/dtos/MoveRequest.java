package net.networkdowntime.morris.dtos;

public class MoveRequest {
    public int gameId;
    public String playerName;
    public String boardState;

    @Override
    public String toString() {
        return "MoveRequest [gameId=" + gameId + ", playerName=" + playerName + ", boardState=" + boardState + "]";
    }

}
