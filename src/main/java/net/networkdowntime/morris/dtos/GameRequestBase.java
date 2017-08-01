package net.networkdowntime.morris.dtos;

public abstract class GameRequestBase {
    public String playerName;
    public String algorithmName;
    public String aiEndpoint;
    public boolean isAutomated;

    @Override
    public String toString() {
        return "GameRequestBase [playerName=" + playerName + ", algorithmName=" + algorithmName + ", aiEndpoint=" + aiEndpoint + ", isAutomated=" + isAutomated + "]";
    }

}
