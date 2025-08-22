package catastrophescroll.handlers;

import catastrophescroll.item.ItemCatastropheScroll;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import catastrophescroll.CatastropheScroll12;
import catastrophescroll.potion.PotionExample;
import catastrophescroll.recipe.RecipeExample;

@Mod.EventBusSubscriber(modid = CatastropheScroll12.MODID)
public class ModRegistry {
        public static Item catastropheScroll=new ItemCatastropheScroll();

        public static void init() {

        }

        @SubscribeEvent
        public static void registerItemEvent(RegistryEvent.Register<Item> event) {
                event.getRegistry().registerAll(
                        catastropheScroll
                );
        }

        @SubscribeEvent
        public static void registerRecipeEvent(RegistryEvent.Register<IRecipe> event) {
                event.getRegistry().register(new RecipeExample().setRegistryName(new ResourceLocation(CatastropheScroll12.MODID, "example")));
        }

        @SubscribeEvent
        public static void registerPotionEvent(RegistryEvent.Register<Potion> event) {
                event.getRegistry().register(PotionExample.INSTANCE);
        }

        @SubscribeEvent
        public static void registerPotionTypeEvent(RegistryEvent.Register<PotionType> event) {
        }
}