package plugin.sample;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.World;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {

  private int count; // 基本型int は初期値が0 この指定ではプレイヤー単位ではなくワールド単位でカウントが共通


  @Override
  public void onEnable() {
    Bukkit.getPluginManager().registerEvents(this, this);
    getCommand("maxhealthchange").setExecutor(new MaxHealthChangeCommand());
    getCommand("setlevel").setExecutor(new SetLevelCommand());
  }

  /**
   * プレイヤーがスニークを開始/終了する際に起動されるイベントハンドラ。
   *
   * @param e イベント
   */
  @EventHandler
  public void onPlayerToggleSneak(PlayerToggleSneakEvent e) throws IOException {
    // イベント発生時のプレイヤーやワールドなどの情報を変数に持つ。
    Player player = e.getPlayer();
    World world = player.getWorld();

    // BigInteger型の val を定義
    BigInteger val = new BigInteger(String.valueOf(count));

    System.out.println("====================================");
    System.out.println("現在のカウント : " + count);
    // val が素数であるかの判定 isProbablePrimeメソッドを使用

    List<Color> colorList = List.of(Color.BLUE, Color.ORANGE, Color.WHITE, Color.LIME, Color.GRAY);
    if (val.isProbablePrime(10)) {
      for (Color color : colorList) {
        System.out.println(val + ": は素数です");
        // 花火オブジェクトをプレイヤーのロケーション地点に対して出現させる。
        Firework firework = world.spawn(player.getLocation(), Firework.class);

        // 花火オブジェクトが持つメタ情報を取得。
        FireworkMeta fireworkMeta = firework.getFireworkMeta();

        // メタ情報に対して設定を追加したり、値の上書きを行う。
        // 今回は青色で星型の花火を打ち上げる。
        fireworkMeta.addEffect(
          FireworkEffect.builder()
            .withColor(color)
            .with(Type.CREEPER)
            .withFlicker()
            .build());
        fireworkMeta.setPower(1);
        // 追加した情報で再設定する。
        firework.setFireworkMeta(fireworkMeta);
        Path path = Path.of("firework.txt");
        Files.writeString(path, "たーまや！！", StandardOpenOption.APPEND);
        player.sendMessage(Files.readString(path));

      }
    }
    // BigInteger側の val に対してnextProbablePrimeメソッドを使用
    System.out.println("次の素数は " + val.nextProbablePrime()); // 1より大きい素数の２が出力されます。
    System.out.println("====================================");
    count++;


  }

  @EventHandler
  public void onPlayerBedEnter(PlayerBedEnterEvent e) {
    Player player = e.getPlayer();
    ItemStack[] itemStack = player.getInventory().getContents();
    Arrays.stream(itemStack).filter(
        item -> !Objects.isNull(item) && item.getMaxStackSize() == 64 && item.getAmount() < 64)
      .forEach(item -> item.setAmount(64));

    player.getInventory().setContents(itemStack);


  }

  @EventHandler
  // プレイヤーが参加した際にメッセージを表示するプログラム
  public void onPlayerJoinEvent(PlayerJoinEvent e) {
    // プレイヤーの情報を取得
    Player player = e.getPlayer();
    // プレイヤーの表示名を取得
    String playerName = player.getDisplayName();
    // プレイヤーが参加した際に送るメッセージを設定
    e.setJoinMessage(playerName + "さん、マイクラの世界へようこそ!");
  }

}


