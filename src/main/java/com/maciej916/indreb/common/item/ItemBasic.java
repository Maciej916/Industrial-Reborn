package com.maciej916.indreb.common.item;

import com.maciej916.indreb.common.registries.ModItemGroups;
import net.minecraft.world.item.Item;

public class ItemBasic extends Item {
    public ItemBasic() {
        super(new Item.Properties().tab(ModItemGroups.MAIN_ITEM_GROUP));
    }
}
