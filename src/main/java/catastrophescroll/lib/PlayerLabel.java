package catastrophescroll.lib;

import net.minecraft.entity.player.EntityPlayer;

public interface PlayerLabel {
    void addTo(EntityPlayer player);
    void removeFrom(EntityPlayer player);
    boolean on(EntityPlayer player);
}
