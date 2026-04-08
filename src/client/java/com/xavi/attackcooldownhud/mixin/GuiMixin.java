package com.xavi.attackcooldownhud.mixin;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class GuiMixin {

    @Inject(method = "extractRenderState", at = @At("TAIL"))
    private void onRender(GuiGraphicsExtractor extractor, DeltaTracker deltaTracker, CallbackInfo ci) {
        Minecraft mc = Minecraft.getInstance();

        if (mc.player == null) return;
        if (mc.screen != null) return;

        LocalPlayer player = mc.player;
        float cooldown = player.getAttackStrengthScale(0f);
        int percent = Math.round(cooldown * 100f);

        if (percent >= 100) return;

        String text = percent + "%";
        int textWidth = mc.font.width(text);
        int x = (extractor.guiWidth() - textWidth) / 2;
        int y = extractor.guiHeight() / 2 + 20;

        extractor.text(mc.font, text, x, y, getColor(percent), true);
    }

    private int getColor(int percent) {
        float t = percent / 100f;
        int r, g;
        if (t < 0.5f) {
            r = 255;
            g = (int)(255 * t * 2f);
        } else {
            r = (int)(255 * (1f - (t - 0.5f) * 2f));
            g = 255;
        }
        return (0xFF << 24) | (r << 16) | (g << 8);
    }
}
