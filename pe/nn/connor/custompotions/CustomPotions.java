package pe.nn.connor.custompotions;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;

public class CustomPotions extends JavaPlugin{
	private HashMap<String, PotionEffectType> potionNames;
	
	@Override
	public void onEnable() {
		//Create Potion name list
		potionNames = new HashMap<String, PotionEffectType>();
		//Create automated system for adding new potions
		//This allows any new potion effect to be used prior to an update
		for(PotionEffectType type : PotionEffectType.values()){
			potionNames.put(type.getName(), type);
		}
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if(command.getName().equalsIgnoreCase("custompotions")){
			if(args.length == 1){
				if(args[0].equalsIgnoreCase("help")){
					sender.sendMessage("Custom Potions help");
					sender.sendMessage("This will be changed in the release build");
					sender.sendMessage("");
					sender.sendMessage(label + " give potion:strength:duration (-splash)");
					return true;
				}
			}
		}
		return false;
	}
}
