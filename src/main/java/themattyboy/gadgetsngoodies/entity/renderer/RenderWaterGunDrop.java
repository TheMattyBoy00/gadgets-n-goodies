package themattyboy.gadgetsngoodies.entity.renderer;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import themattyboy.gadgetsngoodies.entity.projectile.EntityWaterGunDrop;

@SideOnly(Side.CLIENT)
public class RenderWaterGunDrop extends Render {
    private static final String __OBFID = "CL_00000978";

    public RenderWaterGunDrop(RenderManager p_i46193_1_) {
        super(p_i46193_1_);
    }

    public void doRender(EntityWaterGunDrop drop, double p_180551_2_, double p_180551_4_, double p_180551_6_, float p_180551_8_, float p_180551_9_) {
        super.doRender(drop, p_180551_2_, p_180551_4_, p_180551_6_, p_180551_8_, p_180551_9_);
    }

    protected ResourceLocation getEntityTexture(EntityWaterGunDrop p_180550_1_) {
        return null;
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        return this.getEntityTexture((EntityWaterGunDrop)entity);
    }

    public void doRender(Entity entity, double x, double y, double z, float p_76986_8_, float partialTicks) {
        this.doRender((EntityWaterGunDrop)entity, x, y, z, p_76986_8_, partialTicks);
    }
}