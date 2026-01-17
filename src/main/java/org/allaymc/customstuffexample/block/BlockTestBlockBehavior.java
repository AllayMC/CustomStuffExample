package org.allaymc.customstuffexample.block;

import org.allaymc.api.component.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * @author daoge_cmd
 */
public class BlockTestBlockBehavior extends BlockBehaviorImpl {
    public BlockTestBlockBehavior(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
