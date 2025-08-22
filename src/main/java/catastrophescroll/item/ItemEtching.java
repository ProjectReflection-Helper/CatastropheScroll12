package catastrophescroll.item;

import catastrophescroll.CatastropheScroll12;
import catastrophescroll.lib.CSPlayerLabels;
import catastrophescroll.lib.PlayerLabel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemEtching extends Item {
    private final PlayerLabel workingLabel;

    public ItemEtching(String registryName,PlayerLabel workingLabel) {
        this.setRegistryName(CatastropheScroll12.MODID,registryName);
        this.setTranslationKey(registryName);
        this.setCreativeTab(CatastropheScroll12.TAB);
        this.setMaxStackSize(1);
        this.workingLabel = workingLabel;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack=playerIn.getHeldItem(handIn);
        EnumActionResult result=EnumActionResult.PASS;
        if(!worldIn.isRemote)
        {
            if(!CSPlayerLabels.SCROLL_EQUIPPED.on(playerIn) || workingLabel.on(playerIn))
            {
                result=EnumActionResult.FAIL;
            }
            else{
                if(!playerIn.capabilities.isCreativeMode){
                    stack.shrink(1);
                }
                if(!CSPlayerLabels.TRUTH.on(playerIn))
                {
                    CSPlayerLabels.TRUTH.addTo(playerIn);
                }
                if(!workingLabel.on(playerIn))
                {
                    workingLabel.addTo(playerIn);
                }
            }
        }
        return ActionResult.newResult(result,stack);
    }
}
