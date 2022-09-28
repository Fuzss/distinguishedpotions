package fuzs.distinguishedpotions.client.handler;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;

import java.util.List;

public class PotionDecorationsHandler {
    private static final ChatFormatting[] POTION_AMPLIFIER_COLORS = new ChatFormatting[]{ChatFormatting.AQUA, ChatFormatting.LIGHT_PURPLE, ChatFormatting.GOLD, ChatFormatting.GREEN, ChatFormatting.YELLOW, ChatFormatting.BLUE, ChatFormatting.RED};

    public static boolean renderPotionDecorations(ItemStack stack, int itemPosX, int itemPosY) {
        List<MobEffectInstance> mobEffects = PotionUtils.getMobEffects(stack);
        int dotCount = mobEffects.stream().mapToInt(MobEffectInstance::getAmplifier).map(i1 -> i1 + 1).sum();
        if (dotCount == 0) return false;
        RenderSystem.disableDepthTest();
        RenderSystem.disableTexture();
        RenderSystem.disableBlend();
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferBuilder = tesselator.getBuilder();
        fillRect(bufferBuilder, itemPosX + 3, itemPosY + 13, 11, 2, 0, 0, 0, 255);
        for (int i = 0; i < Math.min(4, dotCount); i++) {
            int color = POTION_AMPLIFIER_COLORS[Math.min((dotCount - i - 1) / 4, POTION_AMPLIFIER_COLORS.length - 1)].getColor();
            fillRect(bufferBuilder, itemPosX + 3 + 3 * i, itemPosY + 13, 2, 2, color >> 16 & 0xFF, color >> 8 & 0xFF, color & 0xFF, 0xFF);
        }
        return true;
    }

    private static void fillRect(BufferBuilder renderer, int x, int y, int width, int height, int red, int green, int blue, int alpha) {
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        renderer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
        renderer.vertex(x, y, 0.0).color(red, green, blue, alpha).endVertex();
        renderer.vertex(x, y + height, 0.0).color(red, green, blue, alpha).endVertex();
        renderer.vertex(x + width, y + height, 0.0).color(red, green, blue, alpha).endVertex();
        renderer.vertex(x + width, y, 0.0).color(red, green, blue, alpha).endVertex();
        BufferUploader.drawWithShader(renderer.end());
    }
}
