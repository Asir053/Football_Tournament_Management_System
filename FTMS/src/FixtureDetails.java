/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Asir
 */
public class FixtureDetails {
    private int team1goal,team2goal;
    private String DoM,team1Name,team2Name,duration,MotM;

    public FixtureDetails(String DoM, String team1Name,int team1goal, String team2Name, int team2goal, String duration, String MotM) {
        this.team1goal = team1goal;
        this.team2goal = team2goal;
        this.DoM = DoM;
        this.team1Name = team1Name;
        this.team2Name = team2Name;
        this.duration = duration;
        this.MotM = MotM;
    }

    public int getTeam1goal() {
        return team1goal;
    }

    public int getTeam2goal() {
        return team2goal;
    }

    public String getDoM() {
        return DoM;
    }

    public String getTeam1Name() {
        return team1Name;
    }

    public String getTeam2Name() {
        return team2Name;
    }

    public String getDuration() {
        return duration;
    }

    public String getMotM() {
        return MotM;
    }
   
    
}
