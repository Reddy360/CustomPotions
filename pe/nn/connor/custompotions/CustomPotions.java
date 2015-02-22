package pe.nn.connor.custompotions;

import java.util.HashMap;

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
}
