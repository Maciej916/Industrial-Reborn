package com.maciej916.indreb.common.client.renderer;

import com.maciej916.indreb.common.energy.interfaces.IEnergyCore;
import com.maciej916.indreb.common.energy.provider.EnergyNetwork;
import com.maciej916.indreb.common.registries.ModCapabilities;
import com.maciej916.indreb.common.registries.ModItems;
import com.maciej916.indreb.common.util.CapabilityUtil;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.RenderLevelLastEvent;


public class EnergyInfoRenderer {

    public static void render(RenderLevelLastEvent event) {
        LocalPlayer player = Minecraft.getInstance().player;

        assert player != null;
        Item handItem = player.getMainHandItem().getItem();
        if (handItem == ModItems.IE_METER || handItem == ModItems.DEBUG_STICK) {
            highlightNetworks(player, event.getPoseStack());
        }
    }

    private static void highlightNetworks(LocalPlayer player, PoseStack matrixStack) {
        Level level = player.getLevel();

        MultiBufferSource.BufferSource buffer = Minecraft.getInstance().renderBuffers().bufferSource();
        VertexConsumer builder = buffer.getBuffer(ModRenderType.OVERLAY_LINES);

        matrixStack.pushPose();

        Vec3 projectedView = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition();
        matrixStack.translate(-projectedView.x, -projectedView.y, -projectedView.z);

        Matrix4f matrix = matrixStack.last().pose();

        IEnergyCore energyCore = CapabilityUtil.getCapabilityHelper(level, ModCapabilities.ENERGY_CORE).getValue();
        if (energyCore != null) {
            for (EnergyNetwork network: energyCore.getNetworks().getNetworks()) {
                for (BlockPos blockPos: network.getConnections()) {

                    boolean hasUp = network.hasConnection(blockPos.relative(Direction.UP));
                    boolean hasDown = network.hasConnection(blockPos.relative(Direction.DOWN));
                    boolean hasWest = network.hasConnection(blockPos.relative(Direction.WEST));
                    boolean hasEast = network.hasConnection(blockPos.relative(Direction.EAST));
                    boolean hasNorth = network.hasConnection(blockPos.relative(Direction.NORTH));
                    boolean hasSouth = network.hasConnection(blockPos.relative(Direction.SOUTH));

                    createBox(builder, matrix, blockPos, network.r, network.g, network.b, hasUp, hasDown, hasWest, hasEast, hasNorth, hasSouth);
                }
            }
        }

        matrixStack.popPose();

        RenderSystem.disableDepthTest();
        buffer.endBatch(ModRenderType.OVERLAY_LINES);
    }

    private static void createBox(VertexConsumer builder, Matrix4f matrix, BlockPos pos, float r, float g, float b, boolean hasUp, boolean hasDown, boolean hasWest, boolean hasEast, boolean hasNorth, boolean hasSouth) {

        // bottom
        if (!hasDown && !hasWest) {
            createLine(builder, matrix, pos, 0, 0, 0, 0, 0, 1, r, g, b);
        }

        if (!hasDown && !hasEast) {
            createLine(builder, matrix, pos, 1, 0, 0, 1, 0, 1, r, g, b);
        }

        if (!hasDown && !hasNorth) {
            createLine(builder, matrix, pos, 0, 0, 0, 1, 0, 0, r, g, b);
        }

        if (!hasDown && !hasSouth) {
            createLine(builder, matrix, pos, 0, 0, 1, 1, 0, 1, r, g, b);
        }

        // up
        if (!hasUp && !hasWest) {
            createLine(builder, matrix, pos, 0, 1, 0, 0, 1, 1, r, g, b);
        }

        if (!hasUp && !hasEast) {
            createLine(builder, matrix, pos, 1, 1, 0, 1, 1, 1, r, g, b);
        }

        if (!hasUp && !hasNorth) {
            createLine(builder, matrix, pos, 0, 1, 0, 1, 1, 0, r, g, b);
        }

        if (!hasUp && !hasSouth) {
            createLine(builder, matrix, pos, 0, 1, 1, 1, 1, 1, r, g, b);
        }

        // y
        if (!hasNorth && !hasWest) {
            createLine(builder, matrix, pos, 0, 0, 0, 0, 1, 0, r, g, b);
        }

        if (!hasSouth && !hasWest) {
            createLine(builder, matrix, pos, 0, 0, 1, 0, 1, 1, r, g, b);
        }

        if (!hasNorth && !hasEast) {
            createLine(builder, matrix, pos, 1, 0, 0, 1, 1, 0, r, g, b);
        }

        if (!hasSouth && !hasEast) {
            createLine(builder, matrix, pos, 1, 0, 1, 1, 1, 1, r, g, b);
        }
    }

    private static void createLine(VertexConsumer builder, Matrix4f positionMatrix, BlockPos pos, float dx1, float dy1, float dz1, float dx2, float dy2, float dz2, float r, float g, float b) {
        builder.vertex(positionMatrix, pos.getX()+dx1, pos.getY()+dy1, pos.getZ()+dz1)
                .color(r, g, b, 1.0f)
                .endVertex();
        builder.vertex(positionMatrix, pos.getX()+dx2, pos.getY()+dy2, pos.getZ()+dz2)
                .color(r, g, b, 1.0f)
                .endVertex();
    }
}
