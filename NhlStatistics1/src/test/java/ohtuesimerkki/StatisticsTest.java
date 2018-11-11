package ohtuesimerkki;

import ohtuesimerkki.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticsTest {
 
  private Reader readerStub = new Reader() {

      public List<Player> getPlayers() {
          ArrayList<Player> players = new ArrayList<Player>();

          players.add(new Player("Semenko", "EDM", 4, 12));
          players.add(new Player("Lemieux", "PIT", 45, 54));
          players.add(new Player("Kurri",   "EDM", 37, 53));
          players.add(new Player("Yzerman", "DET", 42, 56));
          players.add(new Player("Gretzky", "EDM", 35, 89));

          return players;
      }
  };

  private Statistics stats;

  @Before
  public void setUp(){
      // luodaan Statistics-olio joka käyttää "stubia"
      stats = new Statistics(readerStub);
  }
  
  @Test
  public void searchReturnsNullIfPlayerIsNotFound() {
    Player player = stats.search("Laine");

    assertEquals(null, player);
  }

  @Test
  public void searchReturnsPlayerIfIsPresent() {
    Player player = stats.search("Kurri");
    Player player2 = new Player("Kurri", "EDM", 37, 53);

    assertEquals(0, player2.compareTo(player));
    assertEquals(player2.getName(), player.getName());
  }

  @Test
  public void topScorersReturnsPlayersWithMostPoints() {
    List<Player> topScorers = stats.topScorers(3);
    List<Player> realTopScorers = new ArrayList();
    realTopScorers.add(new Player("Gretzky", "EDM", 35, 89));
    realTopScorers.add(new Player("Lemieux", "PIT", 45, 54));
    realTopScorers.add(new Player("Yzerman", "DET", 42, 56));

    for (int i=0; i<3; i++) {
      assertEquals(realTopScorers.get(i).getName(), topScorers.get(i).getName());
    }
  }

  @Test
  public void teamReturnsTeamsPlayers() {
    List<Player> teamsPlayers = stats.team("PIT");
    Player teamsPlayer = new Player("Lemieux", "PIT", 45, 54);

    for (Player player : teamsPlayers) {
      assertEquals(player.getName(), teamsPlayer.getName());
    }
  }
}