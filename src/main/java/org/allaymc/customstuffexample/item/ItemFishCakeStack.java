package org.allaymc.customstuffexample.item;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemEdibleComponent;
import org.allaymc.server.component.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

import java.util.List;

/**
 * @author daoge_cmd
 */
public class ItemFishCakeStack extends ItemStackImpl implements ItemEdibleComponent {
    @Delegate
    private ItemEdibleComponent edibleComponent;

    public ItemFishCakeStack(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
