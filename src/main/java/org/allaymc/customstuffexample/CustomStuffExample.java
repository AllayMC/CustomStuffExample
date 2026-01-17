package org.allaymc.customstuffexample;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockStateData;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.creative.CreativeItemCategory;
import org.allaymc.api.math.voxelshape.VoxelShape;
import org.allaymc.api.plugin.Plugin;
import org.allaymc.api.registry.Registries;
import org.allaymc.server.block.component.BlockStateDataComponentImpl;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.block.type.AllayBlockType;
import org.allaymc.server.block.type.CustomBlockDefinitionGenerator;

public class CustomStuffExample extends Plugin {

    public static BlockType<BlockBehavior> TEST_BLOCK;

    @Override
    public void onLoad() {
        registerBlockTypes();
        registerCreativeItems();
    }

    protected void registerBlockTypes() {
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
                .builder(BlockBehaviorImpl.class)
                .identifier("cse:test_block")
                .addComponent(BlockStateDataComponentImpl.ofGlobalStatic(BlockStateData.builder()
                        .collisionShape(shape)
                        .shape(shape)
                        .build()
                ))
                .blockDefinitionGenerator(CustomBlockDefinitionGenerator.builder()
                        .materials(CustomBlockDefinitionGenerator.Materials.builder().any(material))
                        .geometry("geometry.test_block")
                        .build()
                )
                .build();
    }

    protected void registerCreativeItems() {
        var group = Registries.CREATIVE_ITEMS
                .getCategory(CreativeItemCategory.Type.CONSTRUCTION)
                .registerGroup("cse:item_group", TEST_BLOCK.getItemType().createItemStack());
        group.registerItem(TEST_BLOCK.getItemType().createItemStack());
    }
}