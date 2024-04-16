package plugin.sample;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChangeMaxHealthCommand implements CommandExecutor {

  // プレイヤーの最大体力を変更するメソッド


  @Override
  // コマンド（プレイヤーの最大HP変更）
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (sender instanceof Player player) {
      // プレイヤーの最大HPを取得して変数に入れる
      AttributeInstance maxHealthAttribute = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
      if (args.length == 1) {
        // コマンド引数が１つで、プレイヤーの最大HPがNULLでなければ最大HPをコマンド引数で受け取った値に変更する
        double newHealth = Double.parseDouble(args[0]);
        if (maxHealthAttribute != null) {
          maxHealthAttribute.setBaseValue(newHealth);
        }
        player.setHealth(newHealth);
      } else {
        // コマンド引数が１つ以外であればメッセージを送信
        player.sendMessage("Nooooooooooooooooo!!");
      }
    }
    return false;
  }
}
