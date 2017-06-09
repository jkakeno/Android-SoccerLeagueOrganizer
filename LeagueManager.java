import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Players;
import com.teamtreehouse.model.Team;
//import com.teamtreehouse.model.League;
import com.teamtreehouse.LeagueOrganizer;
import java.util.ArrayList;

public class LeagueManager {

  public static void main(String[] args) {
    Player[] players = Players.load();
    System.out.printf("There are currently %d registered players.%n", players.length);
    LeagueOrganizer organizer = new LeagueOrganizer(players);
    organizer.run();
   
  }

}
