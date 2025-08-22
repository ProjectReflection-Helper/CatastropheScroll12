package catastrophescroll.item;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import catastrophescroll.CatastropheScroll12;
import catastrophescroll.lib.AttrAdder;
import catastrophescroll.lib.CSPlayerLabels;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.Constants;

import java.util.Arrays;
import java.util.List;

public class ItemCatastropheScroll extends Item implements IBauble {
    public ItemCatastropheScroll() {
        this.setRegistryName(CatastropheScroll12.MODID,"catastrophe_scroll");
        this.setTranslationKey("catastrophe_scroll");
        this.setCreativeTab(CatastropheScroll12.TAB);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return BaubleType.AMULET;
    }

    @Override
    public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
        if(player instanceof EntityPlayerMP playerMP)
        {
            CSPlayerLabels.SCROLL_EQUIPPED.addTo(playerMP);
        }
    }

    @Override
    public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
        if(player instanceof EntityPlayerMP playerMP)
        {
            CSPlayerLabels.SCROLL_EQUIPPED.removeFrom(playerMP);
        }
    }

    @Override
    public boolean canUnequip(ItemStack itemstack, EntityLivingBase player) {
        if(player instanceof EntityPlayerMP playerMP)
        {
            return playerMP.capabilities.isCreativeMode;
        }
        return true;
    }
    private static double originAttackModifier(EntityPlayer player)
    {
        if(CSPlayerLabels.ORIGIN.on(player))
        {
            if(CSPlayerLabels.ORIGIN_ETCHED.on(player))
                return +.25;
            else
                return -.5;
        }
        return 0.;
    }
    private static double lifeMaxHealthModifier(EntityPlayer player)
    {
        if(CSPlayerLabels.LIFE.on(player))
        {
            if(CSPlayerLabels.LIFE_ETCHED.on(player)){
                return +.2;
            }else{
                return -.25;
            }
        }
        return 0.;
    }
    private static List<AttrAdder> getAttrs(EntityPlayer player)
    {
        String name="catastrophe_scroll";
        return Arrays.asList(
            AttrAdder.of(name, SharedMonsterAttributes.ATTACK_DAMAGE, Constants.AttributeModifierOperation.MULTIPLY,()->originAttackModifier(player)),
                AttrAdder.of(name, SharedMonsterAttributes.MAX_HEALTH, Constants.AttributeModifierOperation.MULTIPLY,()->lifeMaxHealthModifier(player))
                //TODO reply power
        );
    }
    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {

    }
}
