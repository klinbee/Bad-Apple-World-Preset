package com.klinbee.badapple.densityfunctions;

import com.klinbee.badapple.BadAppleWorldCommon;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.world.level.levelgen.DensityFunction;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public record Frame(String frameData, int xSize, int zSize, int xPos, int zPos) implements DensityFunction {
private static final MapCodec<Frame> MAP_CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(Codec.STRING.fieldOf("frame_data").forGetter(Frame::frameData), Codec.INT.fieldOf("x_size").forGetter(Frame::xSize), Codec.INT.fieldOf("z_size").forGetter(Frame::zSize), Codec.INT.fieldOf("x").forGetter(Frame::xPos), Codec.INT.fieldOf("z").forGetter(Frame::zPos)).apply(instance, (Frame::new)));
    public static final KeyDispatchDataCodec<Frame> CODEC = KeyDispatchDataCodec.of(MAP_CODEC);

    public static final Base64.Decoder STRING_DECODER = Base64.getDecoder();

    @Override
    public double compute(FunctionContext pos) {

        int currX = pos.blockX();
        int currZ = pos.blockZ();

        // Caching for getting byte array, because decoding and compression is time consuming but only needs to be done like 1 time per frame.
        byte[] decodedBytes = BadAppleWorldCommon.frameDfCache.get(this);
        if (decodedBytes == null) {
            try {
                decodedBytes = decompress(STRING_DECODER.decode(frameData));
                System.out.println("Getting data, size: " + BadAppleWorldCommon.frameDfCache.size());
            } catch (IOException | DataFormatException e) {
                e.printStackTrace();
            }
            BadAppleWorldCommon.frameDfCache.put(this,decodedBytes);;
        }

        int arrayPos = (currX-xPos) + (currZ-zPos)*(xSize);

        assert decodedBytes != null;
        return decodedBytes[arrayPos] & 0xFF;
    }


    private static byte[] decompress(byte[] compressedData) throws IOException, DataFormatException {
        Inflater inflater = new Inflater();
        inflater.setInput(compressedData);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(compressedData.length);
        byte[] buffer = new byte[1024];

        while (!inflater.finished()) {
            int count = inflater.inflate(buffer);
            outputStream.write(buffer, 0, count);
        }

        outputStream.close();
        return outputStream.toByteArray();
    }

    @Override
    public void fillArray(double @NotNull [] densities, DensityFunction.ContextProvider applier) {
        applier.fillAllDirectly(densities,this);
    }

    @Override
    public @NotNull DensityFunction mapAll(DensityFunction.Visitor visitor) {
        return visitor.apply(new Frame(this.frameData, this.xSize, this.zSize, this.xPos, this.zPos));
    }

    public String frameData() {
        return frameData;
    }

    public int xPos() {
        return xPos;
    }

    public int zPos() {
        return zPos;
    }

    public int xSize() {
        return xSize;
    }

    public int zSize() {
        return zSize;
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
