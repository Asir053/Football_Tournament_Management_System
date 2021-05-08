/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Asir
 */
public class Goal {
    private int matchID,playerID;
    private String team1Name,team2Name,playerName,team,goalTime;

    public Goal(int matchID,  String team1Name, String team2Name,int playerID, String playerName, String team, String goalTime) {
        this.matchID = matchID;
        this.playerID = playerID;
        this.team1Name = team1Name;
        this.team2Name = team2Name;
        this.playerName = playerName;
        this.team = team;
        this.goalTime = goalTime;
    }

    public int getMatchID() {
        return matchID;
    }

    public int getPlayerID() {
        return playerID;
    }

    public String getTeam1Name() {
        return team1Name;
    }

    public String getTeam2Name() {
        return team2Name;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getTeam() {
        return team;
    }

    public String getGoalTime() {
        return goalTime;
    }
    
    
}
