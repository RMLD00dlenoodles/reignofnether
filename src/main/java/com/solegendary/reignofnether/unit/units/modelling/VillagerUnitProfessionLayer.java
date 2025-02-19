//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.solegendary.reignofnether.unit.units.modelling;

import com.mojang.blaze3d.vertex.PoseStack;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.io.IOException;
import java.util.Optional;
import net.minecraft.Util;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.VillagerHeadModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.resources.metadata.animation.VillagerMetaDataSection;
import net.minecraft.client.resources.metadata.animation.VillagerMetaDataSection.Hat;
import net.minecraft.core.DefaultedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.npc.VillagerDataHolder;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class VillagerUnitProfessionLayer<T extends LivingEntity & VillagerDataHolder, M extends EntityModel<T> & VillagerHeadModel> extends RenderLayer<T, M> {
    private static final Int2ObjectMap<ResourceLocation> LEVEL_LOCATIONS = (Int2ObjectMap)Util.make(new Int2ObjectOpenHashMap(), (p_117657_) -> {
        p_117657_.put(1, new ResourceLocation("stone"));
        p_117657_.put(2, new ResourceLocation("iron"));
        p_117657_.put(3, new ResourceLocation("gold"));
        p_117657_.put(4, new ResourceLocation("emerald"));
        p_117657_.put(5, new ResourceLocation("diamond"));
    });
    private final Object2ObjectMap<VillagerType, VillagerMetaDataSection.Hat> typeHatCache = new Object2ObjectOpenHashMap();
    private final Object2ObjectMap<VillagerProfession, VillagerMetaDataSection.Hat> professionHatCache = new Object2ObjectOpenHashMap();
    private final ResourceManager resourceManager;
    private final String path;

    public VillagerUnitProfessionLayer(RenderLayerParent<T, M> pRenderer, ResourceManager pResourceManager, String pPath) {
        super(pRenderer);
        this.resourceManager = pResourceManager;
        this.path = pPath;
    }

    public void render(PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, T pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        if (!pLivingEntity.isInvisible()) {
            VillagerData $$10 = ((VillagerDataHolder)pLivingEntity).getVillagerData();
            VillagerType $$11 = $$10.getType();
            VillagerProfession $$12 = $$10.getProfession();
            M $$15 = this.getParentModel();
            ResourceLocation $$16 = this.getResourceLocation("type", Registry.VILLAGER_TYPE.getKey($$11));
            renderColoredCutoutModel($$15, $$16, pMatrixStack, pBuffer, pPackedLight, pLivingEntity, 1.0F, 1.0F, 1.0F);
            if ($$12 != VillagerProfession.NONE && !pLivingEntity.isBaby()) {
                ResourceLocation $$17 = this.getResourceLocation("profession", Registry.VILLAGER_PROFESSION.getKey($$12));
                renderColoredCutoutModel($$15, $$17, pMatrixStack, pBuffer, pPackedLight, pLivingEntity, 1.0F, 1.0F, 1.0F);
                if ($$12 != VillagerProfession.NITWIT) {
                    ResourceLocation $$18 = this.getResourceLocation("profession_level", (ResourceLocation)LEVEL_LOCATIONS.get(Mth.clamp($$10.getLevel(), 1, LEVEL_LOCATIONS.size())));
                    renderColoredCutoutModel($$15, $$18, pMatrixStack, pBuffer, pPackedLight, pLivingEntity, 1.0F, 1.0F, 1.0F);
                }
            }
        }
    }

    private ResourceLocation getResourceLocation(String p_117669_, ResourceLocation p_117670_) {
        return new ResourceLocation(p_117670_.getNamespace(), "textures/entity/" + this.path + "/" + p_117669_ + "/" + p_117670_.getPath() + ".png");
    }
}
