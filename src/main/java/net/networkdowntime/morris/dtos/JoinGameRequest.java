package net.networkdowntime.morris.dtos;

public class JoinGameRequest extends GameRequestBase {
    public int gameId;

    @Override
    public String toString() {
        return "JoinGameRequest [gameId=" + gameId + ", playerName=" + playerName + ", algorithmName=" + algorithmName + ", aiEndpoint=" + aiEndpoint + "]";
    }

}
