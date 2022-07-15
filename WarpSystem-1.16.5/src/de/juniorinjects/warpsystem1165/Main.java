package de.juniorinjects.warpsystem1165;

import org.bukkit.plugin.java.JavaPlugin;

import de.juniorinjects.warpsystem1165.cmd.DelwarpCmd;
import de.juniorinjects.warpsystem1165.cmd.SetwarpCmd;
import de.juniorinjects.warpsystem1165.cmd.WarpCmd;
import de.juniorinjects.warpsystem1165.cmd.WarpsCmd;

public class Main extends JavaPlugin {
	
	public static Main pl;
	
	@Override
	public void onEnable() {
		pl = this;
		
		getCommand("warps").setExecutor(new WarpsCmd());
		getCommand("warp").setExecutor(new WarpCmd());
		getCommand("setwarp").setExecutor(new SetwarpCmd());
		getCommand("delwarp").setExecutor(new DelwarpCmd());
		
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public static Main getPlugin() {
		return pl;
	}
}