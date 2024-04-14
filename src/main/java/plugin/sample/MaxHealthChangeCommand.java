package plugin.sample;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MaxHealthChangeCommand implements CommandExecutor {

  // プレイヤーの最大体力を変更するメソッド


  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (sender instanceof Player player) {
      AttributeInstance maxHealthAttribute = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
      if (args.length == 1) {
        double newHealth = Double.parseDouble(args[0]);
        if (maxHealthAttribute != null) {
          maxHealthAttribute.setBaseValue(newHealth);
        }
        player.setHealth(newHealth);
      } else {
        player.sendMessage("Nooooooooooooooooo!!");
      }
    }
    return false;
  }
}
