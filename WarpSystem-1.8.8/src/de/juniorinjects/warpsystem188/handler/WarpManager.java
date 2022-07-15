package de.juniorinjects.warpsystem188.handler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import com.google.common.collect.Lists;

import de.juniorinjects.warpsystem188.Main;
import de.juniorinjects.warpsystem188.Manager;

public class WarpManager implements Listener {

    private static HashMap<UUID, Integer> delay = new HashMap<>();

    private Manager m = new Manager();
    private static FileWriter warpsFile = new FileWriter(Main.getPlugin().getDataFolder().getPath(), "warps.yml");

    public WarpManager(){
        if(!warpsFile.exist()){
            warpsFile.setValue("Warps", "");
            warpsFile.save();
        }
    }

    private void set(String warps){
        warpsFile.setValue("Warps", warps);
        warpsFile.save();
    }

    private String get(){
        return warpsFile.getString("Warps");
    }

    public void addWarp(String name, Location location){
        if(getWarpLocation(name) != null){
            String homes[] = get().split(";");
            StringBuilder newHomes = new StringBuilder();

            for (String aList : homes) {
                newHomes.append(aList).append(";");
            }

            String newHomeString = newHomes.toString();

            for(String s : homes){
                String[] s1 = s.split(":");
                if(s1[0].equalsIgnoreCase(name)){
                    newHomeString = newHomes.toString().replace(s + ";", name + ":" + location.getWorld().getName() + ":" + location.getX() + ":" + location.getY() + ":" + location.getZ() + ":" + location.getYaw() + ":" + location.getPitch() + ";");
                }
            }
            set(newHomeString);
            return;
        }
        String newHomes = get();
        newHomes = newHomes + name + ":" + location.getWorld().getName() + ":" + location.getX() + ":" + location.getY() + ":" + location.getZ() + ":" + location.getYaw() + ":" + location.getPitch() + ";";
        set(newHomes);
    }

    public void delWarp(String name){
        String homes[] = get().split(";");
        StringBuilder newHomes = new StringBuilder();

        for (String aList : homes) {
            newHomes.append(aList).append(";");
        }

        String newHomeString = newHomes.toString();

        for(String s : homes){
            String[] s1 = s.split(":");
            if(s1[0].equalsIgnoreCase(name)){
                newHomeString = newHomes.toString().replace(s + ";", "");
            }

        }

        set(newHomeString);
    }

    public Location getWarpLocation(String name){
    	if(getWarpsLenght() > 0) {
            String[] sLoc;
            try {
                sLoc = getWarpAsString(name).split(":");
            }catch (Exception unused){
                return null;
            }

        	if(Bukkit.getWorld(sLoc[1]) == null) {
        		return null;
        	}
            World world = Bukkit.getWorld(sLoc[1]);
            return new Location(world, Double.valueOf(sLoc[2]), Double.valueOf(sLoc[3]), Double.valueOf(sLoc[4]), Float.valueOf(sLoc[5]), Float.valueOf(sLoc[6]));
    	}
		return null;
    }

    private String getWarpAsString(String name){
        if(getWarpsLenght() > 0){
            String[] locationList = get().split(";");

            List<String> locations = Lists.newArrayList();
            locations.addAll(Arrays.asList(locationList));

            for(String s : locationList){
                String[] loc = s.split(":");
                if(loc[0].equalsIgnoreCase(name)){
                    return s;
                }
            }
            return null;
        }else{
            return "§ckeine";
        }
    }

    public boolean warpExist(String name){
    	return getWarpLocation(name) != null;
    }

    public List<String> getWarpsList(){
        String[] locationList = get().split(";");
        List<String> list = Lists.newArrayList();

        for(String s : locationList){
            String[] s1 = s.split(":");
            list.add(s1[0]);
        }
        return list;
    }

    @SuppressWarnings("unused")
	public Integer getWarpsLenght(){

        if(get().isEmpty()){
            return 0;
        }

        String[] locationList = get().split(";");

        int i = 0;
        for(String s : locationList){
            i++;
        }
        return i;
    }

    public String getWarpsAsString(){
        StringBuilder fin = new StringBuilder();
        for(String s : getWarpsList()){
            fin.append(s).append(", ");
        }
        return fin.toString().substring(0, fin.length()-2);
    }

    public void teleportWithDelay(final Player player, final String name, int seconds){
        delay.put(player.getUniqueId(), seconds*20);
        player.sendMessage(m.getPrefix() + " §7Du wirst in §e" + seconds + " Sekunden §7teleportiert.");

        new BukkitRunnable() {
            @Override
            public void run() {
                    if(delay.containsKey(player.getUniqueId())){
                        if(delay.get(player.getUniqueId()) == 0){
                            delay.remove(player.getUniqueId());
                            player.teleport(getWarpLocation(name));
                            player.sendMessage(m.getPrefix() + " §7Du wurdest zum Warp §e" + name + " §7teleportiert.");
                            cancel();
                            return;
                        }
                        delay.put(player.getUniqueId(), delay.get(player.getUniqueId())-1);
                    }else{
                        player.sendMessage(m.getPrefix() + "§cDer Teleport wurde abgebrochen.");
                        cancel();
                    }
            }
        }.runTaskTimer(Main.getPlugin(), 1, 1);

    }
}