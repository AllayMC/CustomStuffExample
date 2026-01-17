package org.allaymc.customstuffexample;

import org.allaymc.api.block.data.BlockStateData;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.creative.CreativeItemCategory;
import org.allaymc.api.math.voxelshape.VoxelShape;
import org.allaymc.api.plugin.Plugin;
import org.allaymc.api.registry.Registries;
import org.allaymc.customstuffexample.block.BlockTestBlockBehavior;
import org.allaymc.server.block.component.BlockStateDataComponentImpl;
import org.allaymc.server.block.type.AllayBlockType;
import org.allaymc.server.block.type.CustomBlockDefinitionGenerator;

import java.util.Map;

public class CustomStuffExample extends Plugin {

    public static BlockType<BlockTestBlockBehavior> TEST_BLOCK;

    @Override
    public void onLoad() {
        var shape = VoxelShape.builder()
                .solid(0, 0, 0, 1, 0.0625, 1)
                .build();
        var material = CustomBlockDefinitionGenerator.MaterialInstance.builder()
                .renderMethod(CustomBlockDefinitionGenerator.RenderMethod.BLEND)
                .texture("test_block")
                .ambientOcclusion(true)
                .faceDimming(true)
                .build();
        TEST_BLOCK = AllayBlockType
                .builder(BlockTestBlockBehavior.class)
                .identifier("cse:test_block")
                .addComponent(BlockStateDataComponentImpl.ofGlobalStatic(BlockStateData.builder()
                        .collisionShape(shape)
                        .shape(shape)
                        .build()
                ))
                .blockDefinitionGenerator(CustomBlockDefinitionGenerator.builder()
                        .materialInstances(Map.of("*", material))
                        .geometry("geometry.test_block")
                        .build()
                )
                .build();

        var group = Registries.CREATIVE_ITEMS
                .getCategory(CreativeItemCategory.Type.CONSTRUCTION)
                .registerGroup("cse:item_group", TEST_BLOCK.getItemType().createItemStack());
        group.registerItem(TEST_BLOCK.getItemType().createItemStack());

        this.pluginLogger.info("CustomStuffExample is loaded!");
    }

    @Override
    public void onEnable() {
        this.pluginLogger.info("CustomStuffExample is enabled!");
    }

    @Override
    public void onDisable() {
        this.pluginLogger.info("CustomStuffExample is disabled!");
    }
}