package net.eabky_dev.codexa.entity.client.renderer;


import net.eabky_dev.codexa.entity.client.model.StarForgerModel;
import net.eabky_dev.codexa.item.StarForger;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class StarForgerRenderer extends GeoItemRenderer<StarForger>
{
    public StarForgerRenderer()
    {
        super(new StarForgerModel());
    }
}