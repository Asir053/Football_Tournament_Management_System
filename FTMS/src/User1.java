/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.math.BigDecimal;
/**
 *
 * @author Asir
 */
class User1 {
    private int teamId,matchesPlayed,matchesWon,matchesLost;
    private String teamName;
    private float totalPoints;

    public User1(int teamId,String teamName,int matchesPlayed,int matchesWon,int matchesLost, float totalPoints) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.matchesPlayed = matchesPlayed;
        this.matchesWon = matchesWon;
        this.matchesLost = matchesLost;       
        this.totalPoints = totalPoints;
    }

    public int getTeamId() {
        return teamId;
    }
    
    public String getTeamName() {
        return teamName;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public int getMatchesWon() {
        return matchesWon;
    }

    public int getMatchesLost() {
        return matchesLost;
    }   

    public float getTotalPoints() {
        return totalPoints;
    }
       
}
