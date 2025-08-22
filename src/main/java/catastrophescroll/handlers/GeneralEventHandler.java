package catastrophescroll.handlers;

import catastrophescroll.CatastropheScroll12;
import catastrophescroll.lib.CSPlayerLabels;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = CatastropheScroll12.MODID)
public class GeneralEventHandler {
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event)
    {
        if(event.side== Side.SERVER && event.phase== TickEvent.Phase.END)
        {
            if(CSPlayerLabels.SCROLL_EQUIPPED.on(event.player)){
                if(!CSPlayerLabels.CHAOS.on(event.player) ||CSPlayerLabels.ORIGIN.on(event.player)) {
                    for (var slot : EntityEquipmentSlot.values()) {
                        var stack = event.player.getItemStackFromSlot(slot);
                        if (slot.getSlotType() == EntityEquipmentSlot.Type.ARMOR) {
                            if (stack.isItemEnchanted()) {
                                CSPlayerLabels.CHAOS.addTo(event.player);
                            }
                        } else if (stack.getMaxDamage() >= 500) {
                            CSPlayerLabels.ORIGIN.addTo(event.player);
                        }
                    }
                }
                if(!CSPlayerLabels.NIHILITY.on(event.player)) {
                    for (var effect : event.player.getActivePotionEffects()) {
                        if (effect.getPotion().isBeneficial()) {
                            CSPlayerLabels.NIHILITY.addTo(event.player);
                            break;
                        }
                    }
                }
                if(CSPlayerLabels.DESIRE.on(event.player) && !CSPlayerLabels.DESIRE_ETCHED.on(event.player))
                {
                    var world=event.player.world;
                    double radius=event.player.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).getAttributeValue();
                    var aabb=new AxisAlignedBB(event.player.posX-radius,event.player.posY-radius,event.player.posZ-radius,
                            event.player.posX+radius,event.player.posY+radius,event.player.posZ+radius);
                    for(var entity:world.getEntitiesInAABBexcluding(event.player, aabb,entity->entity instanceof EntityLivingBase)){
                        if(entity instanceof EntityLivingBase entityLivingBase)
                        {
                            var entityRadius=entityLivingBase.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).getAttributeValue();
                            if(event.player == world.getNearestAttackablePlayer(entityLivingBase,entityRadius,entityRadius))
                            {
                                entityLivingBase.setRevengeTarget(event.player);
                            }
                        }
                    }
                }
            }

        }
    }
    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event)
    {
        if(event.getEntityLiving() instanceof EntityPlayerMP player)
        {
            float finalDamage=event.getAmount();
            if(CSPlayerLabels.SCROLL_EQUIPPED.on(player)){
                if(!CSPlayerLabels.LIFE.on(player)){
                    CSPlayerLabels.LIFE.addTo(player);
                }
                if(CSPlayerLabels.CHAOS.on(player)){
                    if(!CSPlayerLabels.CHAOS_ETCHED.on(player)){
                        finalDamage*=event.getSource().isExplosion()?1.75f:1.5f;
                    }else
                    {
                        float ratio=1.f-(player.getHealth()/player.getMaxHealth());
                        finalDamage*=1f-(ratio/2f);
                    }
                }
                if(CSPlayerLabels.NIHILITY.on(player) && CSPlayerLabels.NIHILITY_ETCHED.on(player))
                {
                    if(event.getSource()== DamageSource.OUT_OF_WORLD)
                    {
                        finalDamage *= 0.25f;
                    }
                    if(event.getSource().getTrueSource() instanceof EntityLivingBase enemy)
                    {
                        //10秒中毒3
                        enemy.addPotionEffect(new PotionEffect(MobEffects.POISON,20*10,2));
                    }
                }
                if(CSPlayerLabels.TRUTH.on(player) && event.getSource().getTrueSource() instanceof EntityLivingBase)
                {
                    if(!CSPlayerLabels.TRUTH_ETCHED.on(player)){
                        finalDamage=Math.max(finalDamage,player.getMaxHealth()*.3f);
                    }
                    else
                    {
                        finalDamage=Math.min(finalDamage,player.getMaxHealth()*.6f);
                    }
                }

                if(CSPlayerLabels.END.on(player) && !CSPlayerLabels.END_ETCHED.on(player) && finalDamage >= player.getMaxHealth()*.1f)
                {
                    player.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS,20*10,1));
                    player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,20*10,1));
                }
            }
            event.setAmount(finalDamage);
        } else if (event.getSource().getTrueSource() instanceof EntityPlayerMP player) {
            if(CSPlayerLabels.SCROLL_EQUIPPED.on(player)){
                if(!(event.getEntityLiving() instanceof IMob)){
                    if(!CSPlayerLabels.DESIRE.on(player)) {
                        CSPlayerLabels.DESIRE.addTo(player);
                    }
                }
                if(CSPlayerLabels.END.on(player) && CSPlayerLabels.END_ETCHED.on(player)){
                    player.heal((player.getMaxHealth()-player.getHealth())*0.2f);
                }
            }
        }
    }
}
