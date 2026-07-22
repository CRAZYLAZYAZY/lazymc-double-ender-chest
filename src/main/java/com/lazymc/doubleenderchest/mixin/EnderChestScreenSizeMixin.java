package com.lazymc.doubleenderchest.mixin;

import net.minecraft.block.EnderChestBlock;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.GenericContainerScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * The block's screen-opening lambda (captured as a private static synthetic method) builds a
 * plain 3-row (27-slot) container to match the vanilla ender chest inventory size. Redirect it
 * to the matching 6-row (54-slot) variant so the UI lines up with EnderChestInventorySizeMixin's
 * doubled backing inventory.
 */
@Mixin(EnderChestBlock.class)
public abstract class EnderChestScreenSizeMixin {
    @Redirect(method = "method_55773", at = @At(value = "INVOKE",
        target = "Lnet/minecraft/screen/GenericContainerScreenHandler;createGeneric9x3(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/inventory/Inventory;)Lnet/minecraft/screen/GenericContainerScreenHandler;"))
    private static GenericContainerScreenHandler doubleenderchest$useSixRows(int syncId, PlayerInventory playerInventory, Inventory enderChestInventory) {
        return GenericContainerScreenHandler.createGeneric9x6(syncId, playerInventory, enderChestInventory);
    }
}
