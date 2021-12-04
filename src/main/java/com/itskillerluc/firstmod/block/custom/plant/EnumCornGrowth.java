package com.itskillerluc.firstmod.block.custom.plant;
import com.itskillerluc.firstmod.firstmod;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;

import java.util.Locale;

public enum EnumCornGrowth implements IStringSerializable{
    BOTTOM0,
    BOTTOM1,
    BOTTOM2,
    TOP0;

    @Override
    public String getSerializedName(){
        return name().toLowerCase(Locale.ENGLISH);
    }
    public ResourceLocation getTextureName()
    {
        return new ResourceLocation(firstmod.MOD_ID, "block/corn/"+getSerializedName());
    }

    @Override
    public String toString()
    {
        return getSerializedName();
    }

    public EnumCornGrowth next()
    {
        switch(this)
        {
            case BOTTOM0:
                return BOTTOM1;
            case BOTTOM1:
                return BOTTOM2;
            case BOTTOM2:
            case TOP0:
            default:
                return this;
        }
    }

    public EnumCornGrowth getMin()
    {
        return TOP0==this?TOP0: BOTTOM0;
    }

    public EnumCornGrowth getMax()
    {
        return TOP0==this?TOP0: BOTTOM2;
    }
}
