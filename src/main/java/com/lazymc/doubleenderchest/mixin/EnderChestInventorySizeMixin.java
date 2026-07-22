package com.lazymc.doubleenderchest.mixin;

import net.minecraft.inventory.EnderChestInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

/** EnderChestInventory's constructor calls super(27) (SimpleInventory) - bump it to 54 (6 rows). */
@Mixin(EnderChestInventory.class)
public abstract class EnderChestInventorySizeMixin {
    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/inventory/SimpleInventory;<init>(I)V"))
    private static int doubleenderchest$doubleSize(int originalSize) {
        return originalSize * 2;
    }
}
