/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Asir
 */
public class GoalRanking {
    private int PlayerID,TotalGoals;
    private String PlayerName,teamName;

    public GoalRanking(int PlayerID, String PlayerName, String teamName, int TotalGoals) {
        this.PlayerID = PlayerID;
        this.TotalGoals = TotalGoals;
        this.PlayerName = PlayerName;
        this.teamName = teamName;
    }

    public int getPlayerID() {
        return PlayerID;
    }

    public int getTotalGoals() {
        return TotalGoals;
    }

    public String getPlayerName() {
        return PlayerName;
    }

    public String getTeamName() {
        return teamName;
    }
    
    
}
