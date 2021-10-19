package de.filtastisch.lobbysystem.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ScoreboardListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        this.createBoard(e.getPlayer());
    }

    public void createBoard(Player p){

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        assert manager != null;
        Scoreboard board = manager.getNewScoreboard();

        Objective obj = board.registerNewObjective("hubBoard-1", "dummy", "§b§lfiltastisch.net");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        List<String> scoreList = new ArrayList<>();
        scoreList.add("§r ");
        scoreList.add("§7Dein Rang");
        if (p.hasPermission("rang.owner")) {
            scoreList.add(" §8└ §4filtastisch");
        } else if (p.hasPermission("rang.admin")) {
            scoreList.add(" §8└ §cAdmin");
        } else if (p.hasPermission("rang.streamer")) {
            scoreList.add(" §8└ §dStreamer");
        } else if (p.hasPermission("rang.vip")) {
            scoreList.add(" §8└ §5VIP");
        } else if (p.hasPermission("rang.moderator")) {
            scoreList.add(" §8└ §9Moderator");
        } else if (p.hasPermission("rang.youtuber")) {
            scoreList.add(" §8└ §dYouTuber");
        } else if (p.hasPermission("rang.spieler")) {
            scoreList.add(" §8└ §aSpieler");
        }
        scoreList.add(" ");
        scoreList.add("§7Twitch");
        scoreList.add(" §8└ §dtwitch.tv/filtastisch");
        scoreList.add("§7Discord");
        scoreList.add(" §8└ §bdc.filtastisch.net");
        scoreList.add("§7YouTube");
        scoreList.add(" §8└ §5yt.filtastisch.net");
        scoreList.add("§7Twitter");
        scoreList.add(" §8└ §3twitter.com/filtastisch");

        Collections.reverse(scoreList);



        for (int i = 0; i < scoreList.size(); i++) {
            Score score = obj.getScore(scoreList.get(i)/*.substring(0, 15)*/);
            score.setScore(i);
        }
        p.setScoreboard(board);
    }

}
