package io.github.shallowinggg.sms.utils;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import static java.awt.Font.MONOSPACED;

/**
 * @author ding shimin
 */
public final class ImageUtils {

    private static final Color[] COLOR_SPACES = new Color[]{Color.WHITE, Color.CYAN,
            Color.GRAY, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE,
            Color.PINK, Color.YELLOW};

    private static final float[] FONT_FRACTIONS = new float[]{0.3f, .6f, .8f, .9f};
    private static final Color[] FONT_COLORS = new Color[]{Color.BLUE, Color.BLACK, Color.GREEN, Color.BLUE};

    private ImageUtils() {
    }

    /**
     * Build image for the specified content.
     * <p>
     * Note: this function now should only be used to build small content.
     * If you used it to build image for big content, maybe it will produce
     * unexpected behavior.
     *
     * @param width  image width
     * @param height image height
     * @param code   image content
     * @return image bytes
     * @throws IOException if generate image fail
     */
    @Beta
    public static byte[] buildImage(int width,
                                    int height,
                                    String code) throws IOException {
        Preconditions.checkArgument(width > 0, "width must be positive");
        Preconditions.checkArgument(height > 0, "height must be positive");
        Preconditions.checkArgument(StringUtils.isNotBlank(code), "code must not be empty");

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();

        // 先设置背景色为全白
        g2.setPaint(Color.WHITE);
        g2.fillRect(0, 0, width, height);

        // 设置渐变背景
        int gradientNum = 5;
        g2.setPaint(new LinearGradientPaint(0, 0, width, height,
                randomFractions(gradientNum), randomColors(gradientNum)));
        g2.fillRoundRect(0, 0, width, height, 5, 5);

        // 设置文字颜色
        g2.setPaint(new LinearGradientPaint(0, 0, width, height, FONT_FRACTIONS, FONT_COLORS));
        // 设置字体
        int fontSize = Math.min(width / code.length(), height);
        Font font = new Font(MONOSPACED, Font.BOLD, fontSize);
        g2.setFont(font);

        // 绘制字体
        char[] codes = code.toCharArray();
        int codeLen = code.length();
        Stream.iterate(0, v -> v + 1)
                .limit(code.length())
                .forEach(i -> {
                    AffineTransform affine = new AffineTransform();
                    affine.setToRotation(
                            Math.PI / 4 * ThreadLocalRandom.current().nextDouble() * ((i & 0b1) == 0 ? 1 : -1),
                            ((double) width / codeLen) * i + (double) fontSize / 2,
                            (double) height / 2);
                    g2.setTransform(affine);
                    g2.drawChars(codes, i, 1, (width / codeLen) * i, height / 2 + fontSize / 2);
                });
        g2.dispose();

        ByteArrayOutputStream os = new ByteArrayOutputStream(2048);
        ImageIO.write(image, "jpg", os);
        return os.toByteArray();
    }

    private static Color[] randomColors(int size) {
        return ThreadLocalRandom.current()
                .ints(0, COLOR_SPACES.length)
                .limit(size)
                .mapToObj(i -> COLOR_SPACES[i])
                .toArray(Color[]::new);
    }

    private static float[] randomFractions(int size) {
        float[] fractions = new float[size];
        for (int i = 0; i < size; i++) {
            fractions[i] = ThreadLocalRandom.current().nextFloat();
        }
        Arrays.sort(fractions);
        return fractions;
    }
}
