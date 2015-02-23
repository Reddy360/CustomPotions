package pe.nn.connor.custompotions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CustomPotions extends JavaPlugin{
	private HashMap<String, PotionEffectType> potionNames;
	private static short potionDrinkable = 64;
	private static short potionThrowable = 16390;
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
					sender.sendMessage(label + " give name potion:strength:duration (-splash)");
					return true;
				}
			}else if(args.length >= 3){
				if(args[0].equalsIgnoreCase("give")){
					List<String> variables = new ArrayList<String>();
					Player target = null;
					for(Player player : Bukkit.getOnlinePlayers()){
						if(player.getName().equalsIgnoreCase(args[1])){
							target = player;
						}
					}
					if(target == null){
						sender.sendMessage("Player is not online");
						return true;
					}
					ItemStack item = new ItemStack(Material.POTION, 1);
					PotionMeta meta = (PotionMeta) item.getItemMeta();
					for(int i = 2; i < args.length; i++){
						String arg = args[i];
						if(arg.contains(":")){
							String[] miniArgs = arg.split(":");
							if(miniArgs.length == 3){
								if(potionNames.containsKey(miniArgs[0])){
									PotionEffectType type = potionNames.get(miniArgs[0]);
									int duration = Integer.parseInt(miniArgs[1]) * 20;
									int amplifier = Integer.parseInt(miniArgs[2]);
									meta.addCustomEffect(new PotionEffect(type, duration, amplifier), true);
								}else{
									sender.sendMessage("Unknown potion name: " + miniArgs[0]);
									sender.sendMessage("Ignoring");
								}
							}else{
								sender.sendMessage("Malformed potion argument: " + arg);
								sender.sendMessage("Skipping");
							}
						}else if(arg.startsWith("-")){
							variables.add(arg);
						}
					}
					
					short id = potionDrinkable;
					if(variables.contains("-splash")){
						id = potionThrowable;
					}
					item.setDurability(id);
					item.setItemMeta(meta);
					target.getInventory().addItem(item);
					target.sendMessage("You have been given a Custom Potion :D");
					return true;
				}
			}
		}
		return false;
	}
}
