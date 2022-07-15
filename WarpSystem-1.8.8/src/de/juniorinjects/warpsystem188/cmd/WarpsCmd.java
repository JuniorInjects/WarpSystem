package de.juniorinjects.warpsystem188.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.juniorinjects.warpsystem188.Manager;
import de.juniorinjects.warpsystem188.handler.WarpManager;

public class WarpsCmd implements CommandExecutor {

	Manager m = new Manager();
	WarpManager warpManager = new WarpManager();

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String l, String[] args) {
		if(s instanceof Player) {
			Player p = (Player) s;
			if(args.length == 0) {
				if(warpManager.getWarpsLenght() > 0) {
					p.sendMessage(m.getPrefix() + " §7Es gibt folgende Warps: §e" + warpManager.getWarpsAsString());	
				}else
					p.sendMessage(m.getPrefix() + " §7Aktuell gibt es noch keine Warps.");
			}else
				sendHelp(p);
		}
		return false;
	}
	
	void sendHelp(Player p) {
		p.sendMessage(m.getPrefix() + " §7nutze §a/warps §8//§7um alle Warps zu sehen.");
		p.sendMessage(m.getPrefix() + " §7nutze §a/warp <Warp> §8//§7um dich zu einem Warp zu teleportieren.");
		if(p.hasPermission("warp.set")) {
			p.sendMessage(m.getPrefix() + " §7nutze §a/setwarp <Warp> §8//§7um ein neuen Warp zu erstellen.");
		}else
			p.sendMessage(m.getPrefix() + " §7nutze §c/setwarp <Warp> §8//§7um ein neuen Warp zu erstellen.");
		if(p.hasPermission("warp.del")) {
			p.sendMessage(m.getPrefix() + " §7nutze §a/delwarp <Warp> §8//§7um ein Warp zu löschen.");
		}else
			p.sendMessage(m.getPrefix() + " §7nutze §c/delwarp <Warp> §8//§7um ein Warp zu löschen.");
	}
}