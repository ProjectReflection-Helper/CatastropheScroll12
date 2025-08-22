package replacememodid;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;
import twelvefold.twelvefoldbooter.api.TwelvefoldRegistryAPI;

import java.util.Map;

@IFMLLoadingPlugin.MCVersion("1.12.2")
public class ReplaceMeModNamePlugin implements IFMLLoadingPlugin {

	public ReplaceMeModNamePlugin() {
		MixinBootstrap.init();
		//False for Vanilla/Coremod mixins, true for regular mod mixins
		TwelvefoldRegistryAPI.enqueueEarlyMixin("mixins.replacememodid.vanilla.json");
		//FermiumRegistryAPI.enqueueMixin(true, "mixins.replacememodid.jei.json", () -> Loader.isModLoaded("jei"));
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