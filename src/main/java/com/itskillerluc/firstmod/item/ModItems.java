package com.itskillerluc.firstmod.item;

import com.itskillerluc.firstmod.block.ModBlocks;
import com.itskillerluc.firstmod.firstmod;
import com.itskillerluc.firstmod.item.custom.Firestone;
import com.itskillerluc.firstmod.item.custom.FirestoneGlint;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    static class enchantmentGlow extends Item{
        public enchantmentGlow(Properties pProperties) {
            super(pProperties);
        }

        @Override
        public boolean isFoil(ItemStack pStack) {
            return true;
        }
    }

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, firstmod.MOD_ID);

    public static final RegistryObject<Item> CRYSTAL = ITEMS.register("crystal",
            () -> new Item(new Item.Properties().tab(ModItemGroup.FIRSTMODGROUP)));
    public static final RegistryObject<Item> FIRESTONE = ITEMS.register("firestone",
            () -> new Firestone(new Item.Properties().tab(ModItemGroup.FIRSTMODGROUP).durability(60)));

    public static final RegistryObject<Item> CHARGED_CRYSTAL = ITEMS.register("charged_crystal",
            () -> new enchantmentGlow(new Item.Properties().tab(ModItemGroup.FIRSTMODGROUP)));
    public static final RegistryObject<Item> CHARGED_FIRESTONE = ITEMS.register("charged_firestone",
            () -> new FirestoneGlint(new Item.Properties().tab(ModItemGroup.FIRSTMODGROUP).durability(150)));

    public static final RegistryObject<Item> CRYSTAL_SWORD = ITEMS.register("crystal_sword",
            () -> new SwordItem(ModItemTier.CRYSTAL, 3, -2.5f, new Item.Properties().tab(ModItemGroup.FIRSTMODGROUP)));
    public static final RegistryObject<Item> CRYSTAL_PICKAXE = ITEMS.register("crystal_pickaxe",
            () -> new PickaxeItem(ModItemTier.CRYSTAL, -2, -2.9f, new Item.Properties().tab(ModItemGroup.FIRSTMODGROUP)));
    public static final RegistryObject<Item> CRYSTAL_AXE = ITEMS.register("crystal_axe",
            () -> new AxeItem(ModItemTier.CRYSTAL, 5, -2.9f, new Item.Properties().tab(ModItemGroup.FIRSTMODGROUP)));
    public static final RegistryObject<Item> CRYSTAL_SHOVEL = ITEMS.register("crystal_shovel",
            () -> new ShovelItem(ModItemTier.CRYSTAL, -2, -2.7f, new Item.Properties().tab(ModItemGroup.FIRSTMODGROUP)));
    public static final RegistryObject<Item> CRYSTAL_HOE = ITEMS.register("crystal_hoe",
            () -> new HoeItem(ModItemTier.CRYSTAL, -2, -2.7f, new Item.Properties().tab(ModItemGroup.FIRSTMODGROUP)));

    public static final RegistryObject<Item> FIRESTONE_SWORD = ITEMS.register("firestone_sword",
            () -> new SwordItem(ModItemTier.FIRESTONE, 3, -2.5f, new Item.Properties().tab(ModItemGroup.FIRSTMODGROUP)));
    public static final RegistryObject<Item> FIRESTONE_PICKAXE = ITEMS.register("firestone_pickaxe",
            () -> new PickaxeItem(ModItemTier.FIRESTONE, -2, -2.9f, new Item.Properties().tab(ModItemGroup.FIRSTMODGROUP)));
    public static final RegistryObject<Item> FIRESTONE_AXE = ITEMS.register("firestone_axe",
            () -> new AxeItem(ModItemTier.FIRESTONE, 5, -2.9f, new Item.Properties().tab(ModItemGroup.FIRSTMODGROUP)));
    public static final RegistryObject<Item> FIRESTONE_SHOVEL = ITEMS.register("firestone_shovel",
            () -> new ShovelItem(ModItemTier.FIRESTONE, -2, -2.7f, new Item.Properties().tab(ModItemGroup.FIRSTMODGROUP)));
    public static final RegistryObject<Item> FIRESTONE_HOE = ITEMS.register("firestone_hoe",
            () -> new HoeItem(ModItemTier.FIRESTONE, -2, -2.7f, new Item.Properties().tab(ModItemGroup.FIRSTMODGROUP)));

    public static final RegistryObject<Item> CRYSTAL_BOOTS = ITEMS.register("crystal_boots",
            () -> new ArmorItem(ModArmorMaterial.CRYSTAL, EquipmentSlotType.FEET, new Item.Properties().tab(ModItemGroup.FIRSTMODGROUP)));
    public static final RegistryObject<Item> CRYSTAL_LEGGINGS = ITEMS.register("crystal_leggings",
            () -> new ArmorItem(ModArmorMaterial.CRYSTAL, EquipmentSlotType.LEGS, new Item.Properties().tab(ModItemGroup.FIRSTMODGROUP)));
    public static final RegistryObject<Item> CRYSTAL_CHESTPLATE = ITEMS.register("crystal_chestplate",
            () -> new ArmorItem(ModArmorMaterial.CRYSTAL, EquipmentSlotType.CHEST, new Item.Properties().tab(ModItemGroup.FIRSTMODGROUP)));
    public static final RegistryObject<Item> CRYSTAL_HELMET = ITEMS.register("crystal_helmet",
            () -> new ArmorItem(ModArmorMaterial.CRYSTAL, EquipmentSlotType.HEAD, new Item.Properties().tab(ModItemGroup.FIRSTMODGROUP)));

    public static final RegistryObject<Item> FIRESTONE_BOOTS = ITEMS.register("firestone_boots",
            () -> new ArmorItem(ModArmorMaterial.FIRESTONE, EquipmentSlotType.FEET, new Item.Properties().tab(ModItemGroup.FIRSTMODGROUP)));
    public static final RegistryObject<Item> FIRESTONE_LEGGINGS = ITEMS.register("firestone_leggings",
            () -> new ArmorItem(ModArmorMaterial.FIRESTONE, EquipmentSlotType.LEGS, new Item.Properties().tab(ModItemGroup.FIRSTMODGROUP)));
    public static final RegistryObject<Item> FIRESTONE_CHESTPLATE = ITEMS.register("firestone_chestplate",
            () -> new ArmorItem(ModArmorMaterial.FIRESTONE, EquipmentSlotType.CHEST, new Item.Properties().tab(ModItemGroup.FIRSTMODGROUP)));
    public static final RegistryObject<Item> FIRESTONE_HELMET = ITEMS.register("firestone_helmet",
            () -> new ArmorItem(ModArmorMaterial.FIRESTONE, EquipmentSlotType.HEAD, new Item.Properties().tab(ModItemGroup.FIRSTMODGROUP)));

    public static final RegistryObject<Item> MAGIC_HERBS = ITEMS.register("magic_herbs",
            () -> new BlockItem(ModBlocks.MAGICHERB.get(), new Item.Properties().food(new Food.Builder().alwaysEat().nutrition(5).saturationMod(1.5f).effect(new EffectInstance(Effects.CONFUSION,1200,5),0.5f).effect(new EffectInstance(Effects.WEAKNESS,1200,2),0.3f).effect(new EffectInstance(Effects.ABSORPTION,3000,2),0.3f).effect(new EffectInstance(Effects.DAMAGE_RESISTANCE,1000,2),0.2f).effect(new EffectInstance(Effects.MOVEMENT_SPEED,2000,3),0.7f).effect(new EffectInstance(Effects.POISON,700,2),0.2f).effect(new EffectInstance(Effects.DAMAGE_BOOST,1500,2),0.4f).effect(new EffectInstance(Effects.BLINDNESS,1200,5),0.4f).effect(new EffectInstance(Effects.NIGHT_VISION,1200,5),0.4f).build()).tab(ModItemGroup.FIRSTMODGROUP)));


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
