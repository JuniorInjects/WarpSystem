package de.juniorinjects.warpsystem188.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.juniorinjects.warpsystem188.Manager;
import de.juniorinjects.warpsystem188.handler.WarpManager;

public class SetwarpCmd implements CommandExecutor {

	Manager m = new Manager();
	WarpManager warpManager = new WarpManager();

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String l, String[] args) {
		if(s instanceof Player) {
			Player p = (Player) s;
			if(p.hasPermission("warp.set")) {
				if(args.length == 1) {
					if(!warpManager.warpExist(args[0])) {
						
						warpManager.addWarp(args[0], p.getLocation());
						String loc = " " + p.getLocation().getX() + " " + p.getLocation().getY() + " " + p.getLocation().getZ();
						p.sendMessage(m.getPrefix() + " §7Der Warp §e" + args[0] + " §7wurde bei §e" + loc + " §7erstellt.");
						
					}else 
						p.sendMessage(m.getPrefix() + " §7Dieser Warp existiert bereits.");
				}else
					sendHelp(p);
			}else
				p.sendMessage(m.getNoPerm());
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