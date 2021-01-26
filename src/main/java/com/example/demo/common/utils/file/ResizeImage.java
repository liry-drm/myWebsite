package com.example.demo.common.utils.file;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * 缩略图片
 */
public class ResizeImage {
	/**
	 * 生成缩略图片
	 * @param source
	 * @param targetW
	 * @param targetH
	 * @return
	 */
	public static BufferedImage resize(BufferedImage source, int targetW, int targetH) {
		// targetW，targetH分别表示目标长和宽
		int type = source.getType();
		BufferedImage target = null;
		double sx = (double) targetW / source.getWidth();
		double sy = (double) targetH / source.getHeight();
		// 这里想实现在targetW，targetH范围内实现等比缩放。如果不需要等比缩放
		// 则将下面的if else语句注释即可
		if (sx > sy) {
			sx = sy;
			targetW = (int) (sx * source.getWidth());
		} else {
			sy = sx;
			targetH = (int) (sy * source.getHeight());
		}
		if (type == BufferedImage.TYPE_CUSTOM) { // handmade
			ColorModel cm = source.getColorModel();
			WritableRaster raster = cm.createCompatibleWritableRaster(targetW, targetH);
			boolean alphaPremultiplied = cm.isAlphaPremultiplied();
			target = new BufferedImage(cm, raster, alphaPremultiplied, null);
		} else {
            target = new BufferedImage(targetW, targetH, type);
        }
		Graphics2D g = target.createGraphics();
		// smoother than exlax:
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
		g.dispose();
		return target;
	}

	/**
	 * 生成缩略图
	 * @param fromFileStr 源文件路径
	 * @param saveToFileStr 目标路径
	 * @param width 缩略图宽
	 * @param hight 缩略图高
	 * @throws Exception
	 */
	public static void saveImageAsJpg(String fromFileStr, String saveToFileStr, int width, int hight) throws Exception {
		BufferedImage srcImage;
		// String ex =
		// fromFileStr.substring(fromFileStr.indexOf("."),fromFileStr.length());
		String imgType = "JPEG";
		if (fromFileStr.toLowerCase().endsWith(".png")) {
			imgType = "PNG";
		}
		File saveFile = new File(saveToFileStr);
		File fromFile = new File(fromFileStr);
		srcImage = ImageIO.read(fromFile);
		if (width > 0 || hight > 0) {
			srcImage = resize(srcImage, width, hight);
		}
		ImageIO.write(srcImage, imgType, saveFile);

	}
	/**
	 * 图片生成缩略图
	 * @param fromFile 源文件
	 * @param imgType 图片格式 
	 * @param saveToFileStr 目标文件
	 * @param width  宽
	 * @param hight  高
	 * @throws Exception
	 */
	public static void saveImageAsFile(File fromFile, String imgType, String saveToFileStr, int width, int hight)
			throws Exception {
		BufferedImage srcImage;
		if ("png".equalsIgnoreCase(imgType)) {
			imgType = "PNG";
		} else if ("bmp".equalsIgnoreCase(imgType)) {
			imgType = "BMP";
		} else {
			imgType = "JPEG";
		}
		File saveFile = new File(saveToFileStr);
		srcImage = ImageIO.read(fromFile);
		if (width > 0 || hight > 0) {
			srcImage = resize(srcImage, width, hight);
		}
		ImageIO.write(srcImage, imgType, saveFile);

	}
}
