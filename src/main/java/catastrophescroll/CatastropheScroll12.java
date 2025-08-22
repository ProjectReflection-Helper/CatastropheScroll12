package catastrophescroll;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import catastrophescroll.handlers.ModRegistry;
import catastrophescroll.proxy.CommonProxy;
import twelvefold.twelvefoldbooter.api.LateMixinLoader;

@Mod(modid = CatastropheScroll12.MODID, version = CatastropheScroll12.VERSION, name = CatastropheScroll12.NAME, dependencies = "required-after:twelvefoldbooter")
@LateMixinLoader(value = "mixins.catastrophescroll.late.json")
public class CatastropheScroll12 {
    public static final String MODID = "catastrophescroll";
    public static final String VERSION = "1.0.0";
    public static final String NAME = "CatastropheScroll12";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final CreativeTabs TAB=new CreativeTabs("catastrophescroll") {
        @Override
        public ItemStack createIcon() {
            return ModRegistry.catastropheScroll.getDefaultInstance();
        }
    };
	
    @SidedProxy(clientSide = "catastrophescroll.proxy.ClientProxy", serverSide = "catastrophescroll.proxy.CommonProxy")
    public static CommonProxy PROXY;
	
	@Instance(MODID)
	public static CatastropheScroll12 instance;
	
	@Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ModRegistry.init();
        CatastropheScroll12.PROXY.preInit();
    }
}