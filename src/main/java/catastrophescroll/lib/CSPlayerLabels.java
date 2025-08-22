package catastrophescroll.lib;

import catastrophescroll.CatastropheScroll12;
import net.darkhax.gamestages.GameStageHelper;
import net.minecraft.entity.player.EntityPlayer;

public enum CSPlayerLabels implements PlayerLabel{
    SCROLL_EQUIPPED("scroll_equipped"),
    CHAOS("chaos"),
    CHAOS_ETCHED("chaos_etched"),
    ORIGIN("origin"),
    ORIGIN_ETCHED("origin_etched"),
    LIFE("life"),
    LIFE_ETCHED("life_etched"),
    DESIRE("desire"),
    DESIRE_ETCHED("desire_etched"),
    NIHILITY("nihility"),
    NIHILITY_ETCHED("nihility_etched"),
    END("end"),
    END_ETCHED("end_etched"),
    TRUTH("truth"),
    TRUTH_ETCHED("truth_etched")
    ;
    private final String name;

    CSPlayerLabels(String name) {
        this.name = name;
    }

    @Override
    public void addTo(EntityPlayer player) {
        GameStageHelper.addStage(player, CatastropheScroll12.MODID+":"+name);
    }

    @Override
    public void removeFrom(EntityPlayer player) {
        GameStageHelper.removeStage(player, CatastropheScroll12.MODID+":"+name);
    }

    @Override
    public boolean on(EntityPlayer player) {
        return GameStageHelper.hasStage(player, CatastropheScroll12.MODID+":"+name);
    }
}
