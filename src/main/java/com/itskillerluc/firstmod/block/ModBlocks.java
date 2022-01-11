package com.itskillerluc.firstmod.block;

import com.itskillerluc.firstmod.block.custom.FirestoneBlock;
import com.itskillerluc.firstmod.block.custom.LightningChargerBlock;
import com.itskillerluc.firstmod.block.custom.magicHerbBlock;
import com.itskillerluc.firstmod.block.custom.LogStrip;
import com.itskillerluc.firstmod.block.custom.plant.CornBlock;
import com.itskillerluc.firstmod.block.custom.trees.MagicalTree;
import com.itskillerluc.firstmod.firstmod;
import com.itskillerluc.firstmod.item.ModItemGroup;
import com.itskillerluc.firstmod.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.trees.OakTree;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.potion.Effects;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, firstmod.MOD_ID);

    public static final RegistryObject<Block> CRYSTAL_ORE = registerBlock("crystal_ore", () -> new Block(AbstractBlock.Properties.of(Material.STONE).harvestLevel(4).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(10f)));
    public static final RegistryObject<Block> CRYSTAL_BLOCK = registerBlock("crystal_block", () -> new Block(AbstractBlock.Properties.of(Material.HEAVY_METAL).harvestLevel(4).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(10f)));
    public static final RegistryObject<Block> FIRESTONE_BLOCK = registerBlock("firestone_block", () -> new FirestoneBlock(AbstractBlock.Properties.of(Material.METAL).harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(7f)));
    public static final RegistryObject<Block> CRYSTAL_STAIRS = registerBlock("crystal_stairs", () -> new StairsBlock(() -> CRYSTAL_BLOCK.get().defaultBlockState(), AbstractBlock.Properties.of(Material.HEAVY_METAL).harvestLevel(4).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CRYSTAL_FENCE = registerBlock("crystal_fence", () -> new FenceBlock(AbstractBlock.Properties.of(Material.METAL).harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(7f)));
    public static final RegistryObject<Block> CRYSTAL_FENCE_GATE = registerBlock("crystal_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.of(Material.METAL).harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(7f)));
    public static final RegistryObject<Block> CRYSTAL_SLAB = registerBlock("crystal_slab", () -> new SlabBlock(AbstractBlock.Properties.of(Material.METAL).harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(7f)));
    public static final RegistryObject<Block> CRYSTAL_BUTTON = registerBlock("crystal_button", () -> new StoneButtonBlock(AbstractBlock.Properties.of(Material.METAL).harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(7f).noCollission()));
    public static final RegistryObject<Block> CRYSTAL_PRESSURE_PLATE = registerBlock("crystal_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, AbstractBlock.Properties.of(Material.METAL).harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(7f)));
    public static final RegistryObject<Block> CRYSTAL_DOOR = registerBlock("crystal_door", () -> new DoorBlock(AbstractBlock.Properties.of(Material.METAL).harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(7f).noOcclusion()));
    public static final RegistryObject<Block> CRYSTAL_TRAPDOOR = registerBlock("crystal_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.of(Material.METAL).harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(7f).noOcclusion()));
    public static final RegistryObject<Block> MAGICHERB = BLOCKS.register("magic_herb_crop", ()-> new magicHerbBlock(AbstractBlock.Properties.copy(Blocks.WHEAT)));

    public static final RegistryObject<Block> MAGICAL_LOG = registerBlock("magical_log", () -> new LogStrip(AbstractBlock.Properties.copy(Blocks.OAK_LOG)));
    public static final RegistryObject<Block> MAGICAL_WOOD = registerBlock("magical_wood", () -> new LogStrip(AbstractBlock.Properties.copy(Blocks.OAK_WOOD)));
    public static final RegistryObject<Block> STRIPPED_MAGICAL_LOG = registerBlock("stripped_magical_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.STRIPPED_OAK_LOG)));
    public static final RegistryObject<Block> STRIPPED_MAGICAL_WOOD = registerBlock("stripped_magical_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));
    public static final RegistryObject<Block> MAGICAL_PLANKS = registerBlock("magical_planks", () -> new Block(AbstractBlock.Properties.copy(Blocks.OAK_PLANKS)));

    public static final RegistryObject<Block> MAGICAL_LEAVES = registerBlock("magical_leaves", () -> new LeavesBlock(AbstractBlock.Properties.of(Material.LEAVES).strength(0.2f).randomTicks().sound(SoundType.CROP).noOcclusion()));
    public static final RegistryObject<Block> MAGICAL_SAPLING = registerBlock("magical_sapling", () -> new SaplingBlock(new MagicalTree(),AbstractBlock.Properties.copy(Blocks.OAK_SAPLING)));

    public static final RegistryObject<Block> MAGICAL_FLOWER = registerBlock("magical_flower", () -> new FlowerBlock(Effects.REGENERATION, 50,AbstractBlock.Properties.copy(Blocks.DANDELION)));

    public static final RegistryObject<Block> LIGHTNING_CHARGER = registerBlock("lightning_charger", () -> new LightningChargerBlock(AbstractBlock.Properties.of(Material.HEAVY_METAL)));
    public static final RegistryObject<Block> CORN = BLOCKS.register("corn_crop", () -> new CornBlock(AbstractBlock.Properties.of(Material.PLANT).sound(SoundType.CROP).noCollission().strength(0).randomTicks()));

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block){
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(ModItemGroup.FIRSTMODGROUP)));
    }


    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
