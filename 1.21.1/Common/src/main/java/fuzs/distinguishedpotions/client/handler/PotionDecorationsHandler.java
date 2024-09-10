package fuzs.distinguishedpotions.client.handler;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionContents;

import java.util.stream.StreamSupport;

public class PotionDecorationsHandler {
    private static final ChatFormatting[] POTION_AMPLIFIER_COLORS = new ChatFormatting[]{ChatFormatting.AQUA, ChatFormatting.LIGHT_PURPLE, ChatFormatting.GOLD, ChatFormatting.GREEN, ChatFormatting.YELLOW, ChatFormatting.BLUE, ChatFormatting.RED};

    public static boolean renderPotionDecorations(GuiGraphics guiGraphics, ItemStack itemStack, int itemPosX, int itemPosY) {
        Iterable<MobEffectInstance> mobEffects = itemStack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY).getAllEffects();
        int dotCount = StreamSupport.stream(mobEffects.spliterator(), false).mapToInt(MobEffectInstance::getAmplifier).map(
                (int amplifierValue) -> amplifierValue + 1).sum();
        if (dotCount != 0) {
            int startX = itemPosX + 3;
            int startY = itemPosY + 13;
            guiGraphics.pose().pushPose();
            guiGraphics.fill(RenderType.guiOverlay(), startX, startY, startX + 11, startY + 2, 0xFF000000);
            for (int i = 0; i < Math.min(4, dotCount); i++) {
                int color = POTION_AMPLIFIER_COLORS[Math.min((dotCount - i - 1) / 4, POTION_AMPLIFIER_COLORS.length - 1)].getColor();
                guiGraphics.fill(RenderType.guiOverlay(), startX + 3 * i, startY, startX + 3 * i + 2, startY + 2, color | 0xFF000000);
            }
            guiGraphics.pose().popPose();
            return true;
        } else {
            return false;
        }
    }

    public static boolean renderPotionStackSize(GuiGraphics guiGraphics, Font font, ItemStack stack, int itemPosX, int itemPosY) {
        // copied from vanilla ItemRenderer to draw stack count above effect amplifier bar
        if (stack.getCount() != 1) {
            PoseStack poseStack = guiGraphics.pose();
            poseStack.pushPose();
            String string = String.valueOf(stack.getCount());
            poseStack.translate(0.0, 0.0, 200.0F);
            guiGraphics.drawString(font, string, itemPosX + 19 - 2 - font.width(string), itemPosY + 6 + 3, 16777215, true);
            poseStack.popPose();
            return true;
        }
        return false;
    }
}
