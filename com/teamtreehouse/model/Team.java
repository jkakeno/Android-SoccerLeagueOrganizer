package com.teamtreehouse.model;

import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Team {
  private Set<Player> mTeam;
  protected String mTeamName;
  protected String mCoachName;
  public static final int MAX_PLAYERS = 11;
  private int teamNumberOfPlayers;

    
  
  public Team (String teamName, String coachName) {
    mTeam = new TreeSet<Player>();
    mTeamName = teamName;
    mCoachName = coachName;
    teamNumberOfPlayers = 0;
  }
  
  public void addPlayer(Player player){
    int newNumberOfPlayers = teamNumberOfPlayers + 1;
    if(newNumberOfPlayers > MAX_PLAYERS){
      throw new IllegalArgumentException("Too many players");
    }
    mTeam.add(player);
    teamNumberOfPlayers = newNumberOfPlayers;   
  }
  
  public void removePlayer(Player player){
    mTeam.remove(player);
    int newNumberOfPlayers = teamNumberOfPlayers -1;
    teamNumberOfPlayers = newNumberOfPlayers;
  }
    
  public boolean containsPlayer (Player player){
    boolean isContained = false;
    if (mTeam.contains(player)){
      isContained = true;
    }
    return isContained;
  }

  
  public String getTeamName(){
    return mTeamName;
  }
  
  	@Override
	public String toString(){
		return String.format("team %s coached by %s ", mTeamName, mCoachName);
	}
  
  public Map <String, List<Player>>byHeight(){
    Map <String, List<Player>>byHeight = new TreeMap<>();
    
    for (Player player:mTeam){
      List<Player> rangePlayers = byHeight.get(player.getHeightRange(player));
      if (rangePlayers == null){
        rangePlayers = new ArrayList<>();
        byHeight.put(player.getHeightRange(player),rangePlayers);
      }
      rangePlayers.add(player);
    }
    return byHeight;
  }
  
  public String getNumberOfExperiencedPlayer(){
    int numberOfExperiencedPlayer = 0;
    for (Player player:mTeam){
      if (player.isPreviousExperience() == true){
        numberOfExperiencedPlayer ++;
      }
    }
    return String.valueOf(numberOfExperiencedPlayer);
  }
 
    public String getNumberOfInexperiencedPlayer(){
    int numberOfInexperiencedPlayer = 0;
    for (Player player:mTeam){
      if (player.isPreviousExperience() == false){
        numberOfInexperiencedPlayer ++;
      }
    }
    return String.valueOf(numberOfInexperiencedPlayer);
    }
  
  public List <String> getTeamExperience() {
    List <String> teamExperience = new ArrayList<>(); 
    teamExperience.add(0, getNumberOfExperiencedPlayer());
    teamExperience.add(1, getNumberOfInexperiencedPlayer());
    return teamExperience;
  }
  
  public List<Player> getRoster(){
    List<Player> roster = new ArrayList<>();
    for (Player player:mTeam){
      roster.add(player);
    }
    return roster;
  }  
}
 