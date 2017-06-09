package com.teamtreehouse;

import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Players;
import com.teamtreehouse.model.Team;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.io.*;
import java.util.Comparator;
import java.util.Collections;

public class LeagueOrganizer {
  public Map<String, String> mMenu;
  private BufferedReader mReader;
  private List <Team> teams;
  private List<Player> availablePlayers; 
  private Player[] mPlayers;
  
  public LeagueOrganizer (Player[] players) {
    mPlayers = players;
    mReader = new BufferedReader(new InputStreamReader(System.in));
    mMenu = new HashMap<String, String>();
    teams = new ArrayList <Team>(); 
    availablePlayers = new ArrayList <Player>();
    for (Player player: mPlayers){
      availablePlayers.add(player);
    }
    Collections.sort(availablePlayers);
    mMenu.put("1", "Add a new team to the league");
    mMenu.put("2", "Add a player to a team");
    mMenu.put("3", "Remove a player from a team");
    mMenu.put("4", "Get a height report for a team");
    mMenu.put("5", "Get a League Balance Report");
    mMenu.put("6", "I am a coach and want to print the roster for my team");
    mMenu.put("7", "Exit the program");
  }
  

  private String promptAction() throws IOException {
    System.out.printf("Your options are: %n");
    for (Map.Entry<String, String> option: mMenu.entrySet()){
      System.out.printf("%s - %s %n", option.getKey(), option.getValue());
    }
      System.out.print("What do you want do to:   ");
      String choice = mReader.readLine();
    return choice.trim();  
  }
  
  public void run(){
     String choice = "";
     do {
       try {
         choice = promptAction();
         switch(choice) {
           case "1":
            Team team = promptNewTeam();
            teams.add(team);
            teams.sort( new Comparator<Team>(){
              @Override
              public int compare(Team team1, Team team2){
                if(team1.equals(team2)){
                  return 0;
                }
                return team1.getTeamName().toLowerCase().compareTo(team2.getTeamName().toLowerCase()); 
              }
            });
            System.out.printf("The %s has been added to the league!  %n%n", team);
            break;
           case "2":
            Team team_a = promptTeam();
            Player player = promptPlayer();
            team_a.addPlayer(player);
            availablePlayers.remove(player);  
            System.out.printf("%s %s has been added to the %s %n", 
                              player.getFirstName(),  player.getLastName(), team_a);
            break;
           case "3":
            Team team_b = promptTeam();
            Player teamPlayer = promptTeamPlayer(team_b);
            team_b.removePlayer(teamPlayer);
            System.out.printf("%s %s has been removed from the %s %n", 
                              teamPlayer.getFirstName(),  teamPlayer.getLastName(), team_b);
            break;
           case "4":
            Team team_d = promptTeam();
            for(Map.Entry<String, List<Player>> entry : team_d.byHeight().entrySet()){
              System.out.printf("Height in inches: %s %n There are %s players in that height range. %n %s  %n",
                              entry.getKey(), entry.getValue().size(), entry.getValue());
            }
            break;
           case "5":
            Map <String, List<String>> report = new TreeMap<>();
            for (Team team_e: teams){
              report.put(team_e.getTeamName(),team_e.getTeamExperience());
             }
            for (Map.Entry<String, List<String>> entry: report.entrySet()){
             System.out.printf("%s%n Number of experienced players: %s%n Number of inexperienced players: %s%n %n",
                               entry.getKey(), entry.getValue().get(0), entry.getValue().get(1));
            }
            break;
           case "6":
            Team team_f = promptTeam();
            System.out.printf("Roster for %s: %n", team_f.getTeamName());
           for (Player player_b: team_f.getRoster()){
              System.out.printf("-%s, %s %n", player_b.getLastName(), player_b.getFirstName());
            }
           break;
           case "7":
            System.out.println("Thanks for organizing the league!");
            break;
           default:
            System.out.printf("Unknown choice: %s. Try again. %n%n%n", choice);
         }
       }catch(IOException ioe){
         System.out.println("Problem with input");
         ioe.printStackTrace();
       }
     }while(!choice.equals("7"));
  }
    
  private Team promptNewTeam() throws IOException {
      System.out.print("Enter the team's name:  ");
      String teamName = mReader.readLine();
      System.out.print("Enter the coach's name:  ");
      String coachName = mReader.readLine();
      return new Team (teamName, coachName);
    }
     
		private int promptForIndex(List<String> options) throws IOException {
      int counter =1;
      for (String option : options){
        System.out.printf("%d.)  %s %n", counter, option);
        counter++;
		  }
		  System.out.print("Your choice:   ");
		  String optionAsString = mReader.readLine();
		  int choice = Integer.parseInt(optionAsString.trim());
		  return choice -1;
    }
  
   private Team promptTeam() throws IOException {
     List<String>availableTeams = new ArrayList<>();
     for (Team team :teams ){
      availableTeams.add(team.getTeamName());
     }
     System.out.printf("Select the team you want to work with. The available teams are: %n");
     int index = promptForIndex(availableTeams);
     return teams.get(index);
   }
  
   private Player promptPlayer() throws IOException {
     System.out.println("Available players:");
		 List<String> playersByName = new ArrayList<>();
     for (Player player: availablePlayers){
       playersByName.add(player.toString());
     }  
		 int index = promptForIndex(playersByName);
		 return availablePlayers.get(index);
   }
  
 
  private Player promptTeamPlayer(Team team_c) throws IOException {
    System.out.println("The players in that team are:");
    List<String> teamPlayersByName = new ArrayList<>();
    for (Player player : team_c.getRoster()){
      teamPlayersByName.add(player.toString());
    }
    int index = promptForIndex(teamPlayersByName);
    return team_c.getRoster().get(index);
  }  
  
 }
  
  