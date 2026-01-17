package org.allaymc.customstuffexample;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockStateData;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.creative.CreativeItemCategory;
import org.allaymc.api.item.data.ItemData;
import org.allaymc.api.item.data.ItemTags;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.math.voxelshape.VoxelShape;
import org.allaymc.api.plugin.Plugin;
import org.allaymc.api.registry.Registries;
import org.allaymc.customstuffexample.item.ItemFishCakeStack;
import org.allaymc.server.block.component.BlockStateDataComponentImpl;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.block.type.AllayBlockType;
import org.allaymc.server.block.type.CustomBlockDefinitionGenerator;
import org.allaymc.server.item.component.edible.ItemGoldenAppleEdibleComponentImpl;
import org.allaymc.server.item.type.AllayItemType;
import org.allaymc.server.item.type.CustomItemDefinitionGenerator;

import java.util.Set;

public class CustomStuffExample extends Plugin {

    public static ItemType<ItemFishCakeStack> FISH_CAKE;

    public static BlockType<BlockBehavior> TEST_BLOCK;

    @Override
    public void onLoad() {
        registerItemTypes();
        registerBlockTypes();
        registerCreativeItems();
    }

    protected void registerItemTypes() {
        FISH_CAKE = AllayItemType
                .builder(ItemFishCakeStack.class)
                .identifier("cse:fish_cake")
                .itemData(ItemData.DEFAULT)
                .addComponent(ItemGoldenAppleEdibleComponentImpl::new, ItemGoldenAppleEdibleComponentImpl.class)
                .itemDefinitionGenerator(CustomItemDefinitionGenerator.builder()
                        .texture("cse:fish_cake")
                        .displayName("Fish Cake")
                        .allowOffHand(true)
                        .build()
                )
                .setItemTags(Set.of(ItemTags.IS_FOOD))
                // This is important!
                .build(CustomStuffExample.class.getClassLoader());
    }

    protected void registerBlockTypes() {
        var shape = VoxelShape.builder()
                .solid(0, 0, 0, 1, 0.0625, 1)
                .build();
        var material = CustomBlockDefinitionGenerator.MaterialInstance.builder()
                .renderMethod(CustomBlockDefinitionGenerator.RenderMethod.BLEND)
                .texture("cse:test_block")
                .ambientOcclusion(true)
                .faceDimming(true)
                .build();
        TEST_BLOCK = AllayBlockType
                .builder(BlockBehaviorImpl.class)
                .identifier("cse:test_block")
                .addComponent(BlockStateDataComponentImpl.ofGlobalStatic(BlockStateData.builder()
                        .collisionShape(shape)
                        .shape(shape)
                        .build()
                ))
                .blockDefinitionGenerator(CustomBlockDefinitionGenerator.builder()
                        .displayName("Test Block")
                        .materials(CustomBlockDefinitionGenerator.Materials.builder().any(material))
                        .geometry("geometry.test_block")
                        .build()
                )
                .build();
    }

    protected void registerCreativeItems() {
        var group = Registries.CREATIVE_ITEMS
                .getCategory(CreativeItemCategory.Type.CONSTRUCTION)
                .registerGroup("Custom Stuff Example", FISH_CAKE.createItemStack());
        group.registerItem(FISH_CAKE.createItemStack());
        group.registerItem(TEST_BLOCK.getItemType().createItemStack());
    }
}