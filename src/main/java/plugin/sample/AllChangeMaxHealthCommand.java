package plugin.sample;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AllChangeMaxHealthCommand implements CommandExecutor {

  @Override
  // コンソールコマンド（全プレイヤーの最大HP変更）
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (sender instanceof Player player) {
      // コンソール以外で入力された場合はメッセージを表示する
      System.out.println("コンソールで使うコマンドですよー");
    } else {
      for (Player player : sender.getServer().getOnlinePlayers()) {
        // プレイヤーの最大HPを取得して変数に入れる
        AttributeInstance maxHealthAttribute = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        // コマンド引数が１つで、プレイヤーの最大HPがNULLでなければ最大HPをコマンド引数で受け取った値に変更する
        if (args.length == 1) {
          double newHealth = Double.parseDouble(args[0]);
          if (maxHealthAttribute != null) {
            maxHealthAttribute.setBaseValue(newHealth);
          }
          player.setHealth(newHealth);
          System.out.println("プレイヤーのHPが" + args[0] + "にセットされました！");
        } else {
          // コマンド引数が１つ以外であればメッセージを送信
          System.out.println("NOoooooooooooqq");
        }
      }
    }
    return false;
  }
}
