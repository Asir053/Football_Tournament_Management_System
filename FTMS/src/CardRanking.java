/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Asir
 */
public class CardRanking {
    private int PlayerID,TotalCards;
    private String PlayerName,teamName;

    public CardRanking(int PlayerID, String PlayerName, String teamName, int TotalCards) {
        this.PlayerID = PlayerID;
        this.TotalCards = TotalCards;
        this.PlayerName = PlayerName;
        this.teamName = teamName;
    }

    public int getPlayerID() {
        return PlayerID;
    }

    public int getTotalCards() {
        return TotalCards;
    }

    public String getPlayerName() {
        return PlayerName;
    }

    public String getTeamName() {
        return teamName;
    }
    
    
}
