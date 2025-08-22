package catastrophescroll.lib;

import com.github.bsideup.jabel.Desugar;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.EntityPlayer;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.function.DoubleSupplier;
@Desugar
public record AttrAdder(String name, IAttribute attribute, UUID uuid, int op, DoubleSupplier value) {
    public static AttrAdder of(String name,IAttribute id,int op,DoubleSupplier value)
    {
        return new AttrAdder(name,id,UUID.nameUUIDFromBytes(name.getBytes(StandardCharsets.UTF_8)),op,value);
    }
    public void update(EntityPlayer player)
    {
        if(player.world.isRemote)
            return;
        double val=value.getAsDouble();
        var ins=player.getEntityAttribute(attribute);
        var mod=ins.getModifier(uuid);
        if(mod==null ||mod.getOperation()!=op ||mod.getAmount()!=val){
            ins.removeModifier(uuid);
            ins.applyModifier(new AttributeModifier(uuid,name,val,op));
        }
    }
    public void remove(EntityPlayer player)
    {
        if(!player.world.isRemote)
            return;
        player.getEntityAttribute(attribute).removeModifier(uuid);
    }
}
