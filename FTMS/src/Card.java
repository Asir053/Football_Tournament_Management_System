/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Asir
 */
public class Card {
    private int matchID,playerID;
    private String team1Name,team2Name,playerName,team,cardTypes;

    public Card(int matchID,  String team1Name, String team2Name,int playerID, String playerName, String team, String cardTypes) {
        this.matchID = matchID;
        this.playerID = playerID;
        this.team1Name = team1Name;
        this.team2Name = team2Name;
        this.playerName = playerName;
        this.team = team;
        this.cardTypes = cardTypes;
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

    public String getCardTypes() {
        return cardTypes;
    }
    
    
}
