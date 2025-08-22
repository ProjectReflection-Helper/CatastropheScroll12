package catastrophescroll.handlers;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import catastrophescroll.CatastropheScroll12;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = CatastropheScroll12.MODID, value = Side.CLIENT)
public class ClientModRegistry {

    @SubscribeEvent
    public static void modelRegisterEvent(ModelRegistryEvent event) {
        registerModels(
                ModRegistry.catastropheScroll,
                ModRegistry.truthEtching,
                ModRegistry.originEtching,
                ModRegistry.nihilityEtching,
                ModRegistry.chaoticEtching,
                ModRegistry.lifeEtching,
                ModRegistry.endEtching,
                ModRegistry.desireEtching
        );
    }

    private static void registerModels(Item... values) {
        for(Item entry : values) {
            ModelLoader.setCustomModelResourceLocation(entry, 0, new ModelResourceLocation(Objects.requireNonNull(entry.getRegistryName()), "inventory"));
        }
    }
}