package me.nylestroke.nylemod.screen

import com.mojang.blaze3d.systems.RenderSystem
import me.nylestroke.nylemod.NylemodExample
import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.client.render.GameRenderer
import net.minecraft.client.render.Shader
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import java.util.function.Supplier

class MythrilBlasterScreen(handler: MythrilBlasterScreenHandler, inventory: PlayerInventory, title: Text)
    : HandledScreen<MythrilBlasterScreenHandler>(handler, inventory, title) {
    protected override fun init() {
        super.init()
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2
    }

    protected override fun drawBackground(matrices: MatrixStack, delta: Float, mouseX: Int, mouseY: Int) {
        RenderSystem.setShader(Supplier<Shader> { GameRenderer.getPositionTexShader() })
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f)
        RenderSystem.setShaderTexture(0, TEXTURE)
        val x: Int = (width - backgroundWidth) / 2
        val y: Int = (height - backgroundHeight) / 2
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight)
        if (handler.isCrafting) {
            drawTexture(matrices, x + 84, y + 22, 176, 14, handler.scaledProgress, 36)
        }
        if (handler.hasFuel()) {
            drawTexture(matrices, x + 18, y + 33 + 14 - handler.scaledFuelProgress, 176,
                    14 - handler.scaledFuelProgress, 14, handler.scaledFuelProgress)
        }
    }

    override fun render(matrices: MatrixStack, mouseX: Int, mouseY: Int, delta: Float) {
        renderBackground(matrices)
        super.render(matrices, mouseX, mouseY, delta)
        drawMouseoverTooltip(matrices, mouseX, mouseY)
    }

    companion object {
        val TEXTURE = Identifier(NylemodExample.MOD_ID, "textures/gui/mythril_blaster_gui.png")
    }
}