package net.eabky_dev.codexa.entity.client.renderer;

import net.eabky_dev.codexa.entity.client.model.OtherworldlyLanternBlockItemModel;
import net.eabky_dev.codexa.item.OtherworldlyLanternBlockItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class OtherworldlyLanternItemRenderer extends GeoItemRenderer<OtherworldlyLanternBlockItem>
{
    public OtherworldlyLanternItemRenderer()
    {
        super(new OtherworldlyLanternBlockItemModel());
    }
}
