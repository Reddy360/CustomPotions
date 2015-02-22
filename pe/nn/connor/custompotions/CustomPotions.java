package pe.nn.connor.custompotions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
			}else if(args.length >= 2){
				if(args[0].equalsIgnoreCase("give")){
					List<String[]> potions = new ArrayList<String[]>();
					List<String> variables = new ArrayList<String>();
					for(int i = 1; i < args.length; i++){
						String arg = args[i];
						if(arg.contains(":")){
							String[] miniArgs = arg.split(":");
							if(miniArgs.length == 3){
								potions.add(miniArgs);
							}else{
								sender.sendMessage("Malformed potion argument: " + arg);
								sender.sendMessage("Skipping");
							}
						}else if(arg.startsWith("-")){
							variables.add(arg);
						}
					}
					return true;
				}
			}
		}
		return false;
	}
}
