package catastrophescroll;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;
import twelvefold.twelvefoldbooter.api.TwelvefoldRegistryAPI;

import java.util.Map;

@IFMLLoadingPlugin.MCVersion("1.12.2")
public class CatastropheScroll12Plugin implements IFMLLoadingPlugin {

	public CatastropheScroll12Plugin() {
		MixinBootstrap.init();
		//False for Vanilla/Coremod mixins, true for regular mod mixins
		TwelvefoldRegistryAPI.enqueueEarlyMixin("mixins.catastrophescroll.early.json");
		//FermiumRegistryAPI.enqueueMixin(true, "mixins.catastrophescroll.jei.json", () -> Loader.isModLoaded("jei"));
	}

	@Override
	public String[] getASMTransformerClass()
	{
		return new String[0];
	}
	
	@Override
	public String getModContainerClass()
	{
		return null;
	}
	
	@Override
	public String getSetupClass()
	{
		return null;
	}
	
	@Override
	public void injectData(Map<String, Object> data) { }
	
	@Override
	public String getAccessTransformerClass()
	{
		return null;
	}
}