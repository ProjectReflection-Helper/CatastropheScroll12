package catastrophescroll.handlers;

import catastrophescroll.item.ItemCatastropheScroll;
import catastrophescroll.item.ItemEtching;
import catastrophescroll.lib.CSPlayerLabels;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import catastrophescroll.CatastropheScroll12;

@Mod.EventBusSubscriber(modid = CatastropheScroll12.MODID)
public class ModRegistry {
        public static Item catastropheScroll=new ItemCatastropheScroll();
        public static Item chaoticEtching=new ItemEtching("chaotic_etching", CSPlayerLabels.CHAOS_ETCHED);
        public static Item desireEtching=new ItemEtching("desire_etching", CSPlayerLabels.DESIRE_ETCHED);
        public static Item endEtching=new ItemEtching("end_etching", CSPlayerLabels.END_ETCHED);
        public static Item lifeEtching=new ItemEtching("life_etching", CSPlayerLabels.LIFE_ETCHED);
        public static Item nihilityEtching=new ItemEtching("nihility_etching", CSPlayerLabels.NIHILITY_ETCHED);
        public static Item originEtching=new ItemEtching("origin_etching", CSPlayerLabels.ORIGIN_ETCHED);
        public static Item truthEtching=new ItemEtching("truth_etching", CSPlayerLabels.TRUTH_ETCHED);

        public static void init() {

        }

        @SubscribeEvent
        public static void registerItemEvent(RegistryEvent.Register<Item> event) {
                event.getRegistry().registerAll(
                        catastropheScroll,
                        chaoticEtching,
                        desireEtching,
                        endEtching,
                        lifeEtching,
                        nihilityEtching,
                        originEtching,
                        truthEtching
                );
        }

        @SubscribeEvent
        public static void registerRecipeEvent(RegistryEvent.Register<IRecipe> event) {
        }

        @SubscribeEvent
        public static void registerPotionEvent(RegistryEvent.Register<Potion> event) {
        }

        @SubscribeEvent
        public static void registerPotionTypeEvent(RegistryEvent.Register<PotionType> event) {
        }
}