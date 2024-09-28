package com.klinbee.badapple.densityfunctions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.world.level.levelgen.DensityFunction;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public record Grid(int xSize, int zSize, int minGridXCoord, int minGridZCoord, int outOfBoundsValue, List<List<DensityFunction>> dfCoordList) implements DensityFunction {
    private static final MapCodec<Grid> MAP_CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(Codec.INT.fieldOf("x_size").forGetter(Grid::xSize), Codec.INT.fieldOf("z_size").forGetter(Grid::zSize), Codec.INT.fieldOf("min_grid_x_coord").forGetter(Grid::minGridXCoord), Codec.INT.fieldOf("min_grid_z_coord").forGetter(Grid::minGridZCoord), Codec.INT.fieldOf("out_of_bounds_value").forGetter(Grid::outOfBoundsValue), DensityFunction.HOLDER_HELPER_CODEC.listOf().listOf().fieldOf("df_coord_list").forGetter(Grid::dfCoordList)).apply(instance, (Grid::new)));
    public static final KeyDispatchDataCodec<Grid> CODEC = KeyDispatchDataCodec.of(MAP_CODEC);

    @Override
    public double compute(DensityFunction.FunctionContext pos) {

        int gridX = Math.floorDiv(pos.blockX(),xSize);
        int gridZ = Math.floorDiv(pos.blockZ(),zSize);

        int shiftedGridX = gridX-minGridXCoord;
        int shiftedGridZ = gridZ-minGridZCoord;

        int maxX = dfCoordList.size();
        int maxZ = dfCoordList.get(0).size();

        // >= because array indices
        if ( shiftedGridX >= maxX || shiftedGridZ >= maxZ || gridX < minGridXCoord || gridZ < minGridZCoord) {
            return outOfBoundsValue;
        }

        DensityFunction currDf = dfCoordList.get(shiftedGridX).get(shiftedGridZ);
        return currDf.compute(pos);
    }

    @Override
    public void fillArray(double @NotNull [] densities, DensityFunction.ContextProvider applier) {
        applier.fillAllDirectly(densities,this);
    }

    @Override
    public @NotNull DensityFunction mapAll(DensityFunction.Visitor visitor) {
        return visitor.apply(new Grid(this.xSize, this.zSize, this.minGridXCoord, this.minGridZCoord, this.outOfBoundsValue, this.dfCoordList));
    }

    public List<List<DensityFunction>> dfCoordList() {
        return dfCoordList;
    }

    public int xSize() {
        return xSize;
    }

    public int zSize() {
        return zSize;
    }

    public int minGridXCoord() {
        return minGridXCoord;
    }

    public int minGridZCoord() {
        return minGridZCoord;
    }

    public int outOfBoundsValue() {
        return outOfBoundsValue;
    }

    @Override
    public double minValue() {
        return 0;
    }

    @Override
    public double maxValue() {
        return 255;
    }

    @Override
    public @NotNull KeyDispatchDataCodec<? extends DensityFunction> codec() {
        return CODEC;
    }
}
