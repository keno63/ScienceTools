package phil.plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ScienceTools extends JavaPlugin {
	@Override
	public void onEnable() {
		
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public boolean onCommand(CommandSender sender,
			Command command,
			String label,
			String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (command.getName().equalsIgnoreCase("temperature")) {
				int x_loc = player.getLocation().getBlockX();
				int y_loc = player.getLocation().getBlockY();
				int z_loc = player.getLocation().getBlockZ();
				int time = Math.round(player.getWorld().getTime()) % 24000;
				final String degree_fahrenheit = "\u2109";
				
				//temperature
				Double biome_temp_raw = player.getWorld().getBlockAt(x_loc, y_loc, z_loc).getTemperature();
				Double biome_temp = null;
				//time
				Double temp_change_raw = 15 * (Math.sin(time / 3819.72));
				
				//plains, swamp
				if (biome_temp_raw >= -1.0 && biome_temp_raw < 0.1) {
					biome_temp = 10 + (62 - y_loc) + temp_change_raw;
				}
				else if (biome_temp_raw >= 0.1 && biome_temp_raw < 0.5) {
					biome_temp = (60 + biome_temp_raw * 20) + (62 - y_loc) + temp_change_raw;
				}
				else {
					biome_temp = (80 + biome_temp_raw * 10) + (62 - y_loc) + temp_change_raw * biome_temp_raw;
				}
				
				player.sendMessage(Double.toString(Math.round(biome_temp)) + degree_fahrenheit);
				return true;
			}
		}
		else {
			sender.sendMessage("You are not THE player");
			return false;
		}
		return false;
	}
}
