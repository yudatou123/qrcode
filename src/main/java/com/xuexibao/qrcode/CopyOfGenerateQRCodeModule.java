package com.xuexibao.qrcode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.xuexibao.qrcode.service.GenerateQRCodeService;
import com.xuexibao.qrcode.util.MapUtil;
import com.xuexibao.qrcode.util.Util;

public class CopyOfGenerateQRCodeModule {
	private GenerateQRCodeService generateQRCodeService;
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(CopyOfGenerateQRCodeModule.class);

	private static final int IMAGE_WIDTH = 280;
	private static final int IMAGE_HEIGHT = 280;
	private static final int IMAGE_HALF_WIDTH = IMAGE_WIDTH / 2;
	private static final int FRAME_WIDTH = 2;

	/**
	 * 根据信息产生二维码
	 * 
	 * @param version
	 * @param userId
	 * @param Longitude
	 * @param dimensions
	 */
	@At("/generateQrcode")
	@Ok("jsp:success")
	public void generateQRCode(@Param("name") String name,
			@Param("phoneNum") String phoneNum, @Param("area") String area) {
		if (Util.isEmpty(name) || Util.isEmpty(phoneNum) || Util.isEmpty(area)) {
			return;
		}
		Map<String, String> map = MapUtil.map();
		map.put("name", name);
		map.put("phoneNum", phoneNum);
		map.put("area", area);
		Map<String, String> returnMap = generateQRCodeService.dealInfo(map);
		encodePR(returnMap.get("url"), 300, 300, returnMap.get("userId"));
	}

	/**
	 * 
	 * @param content
	 * @param width
	 * @param height
	 */
	public static void encodePR(String content, int width, int height,
			String userId) {
		// Map<String, String> returnMap = generateQRCodeService.getImagePath();
		StringBuilder sb = new StringBuilder();
		sb.append("d://");
		sb.append(userId);
		sb.append(".png");
		try {
			ImageIO.write(genBarcode(content, width, height, ""), "png",
					new File(sb.toString()));
		} catch (IOException e) {
			logger.error("encodePR(String, int, int)", e);

		} catch (WriterException e) {
			logger.error("encodePR(String, int, int)", e);
		}
	}

	/**
	 * 产生带有图片的二维码缓冲图像
	 * 
	 * @param content
	 * @param width
	 * @param height
	 * @param srcImagePath
	 * @return
	 * @throws WriterException
	 * @throws IOException
	 */
	private static BufferedImage genBarcode(String content, int width,
			int height, String srcImagePath) throws WriterException,
			IOException {
		// 读取源图像
		// BufferedImage scaleImage = scale(srcImagePath, IMAGE_WIDTH,
		// IMAGE_HEIGHT, true);
		int[][] srcPixels = new int[IMAGE_WIDTH][IMAGE_HEIGHT];
		// for (int i = 0; i < scaleImage.getWidth(); i++) {
		// for (int j = 0; j < scaleImage.getHeight(); j++) {
		// srcPixels[i][j] = scaleImage.getRGB(i, j);
		// }
		// }
		Map<EncodeHintType, Object> hint = new HashMap<EncodeHintType, Object>();
		hint.put(EncodeHintType.CHARACTER_SET, "GBK");
		hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		// 生成二维码
		MultiFormatWriter mutiWriter = new MultiFormatWriter();
		BitMatrix matrix = mutiWriter.encode(content, BarcodeFormat.QR_CODE,
				width, height, hint);

		// 二维矩阵转为一维像素数组
		int halfW = matrix.getWidth() / 2;
		int halfH = matrix.getHeight() / 2;
		int[] pixels = new int[width * height];

		for (int y = 0; y < matrix.getHeight(); y++) {
			for (int x = 0; x < matrix.getWidth(); x++) {
				// 读取图片
				// if (x > halfW - IMAGE_HALF_WIDTH
				// && x < halfW + IMAGE_HALF_WIDTH
				// && y > halfH - IMAGE_HALF_WIDTH
				// && y < halfH + IMAGE_HALF_WIDTH) {
				// pixels[y * width + x] = srcPixels[x - halfW
				// + IMAGE_HALF_WIDTH][y - halfH + IMAGE_HALF_WIDTH];
				// }
				// // 在图片四周形成边框
				// else if ((x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH
				// && x < halfW - IMAGE_HALF_WIDTH + FRAME_WIDTH
				// && y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH
				// + IMAGE_HALF_WIDTH + FRAME_WIDTH)
				// || (x > halfW + IMAGE_HALF_WIDTH - FRAME_WIDTH
				// && x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH
				// && y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH
				// + IMAGE_HALF_WIDTH + FRAME_WIDTH)
				// || (x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH
				// && x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH
				// && y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH
				// - IMAGE_HALF_WIDTH + FRAME_WIDTH)
				// || (x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH
				// && x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH
				// && y > halfH + IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH
				// + IMAGE_HALF_WIDTH + FRAME_WIDTH)) {
				// pixels[y * width + x] = 0xfffffff;
				// } else {
				// 此处可以修改二维码的颜色，可以分别制定二维码和背景的颜色；
				pixels[y * width + x] = matrix.get(x, y) ? 0xff000000
						: 0xfffffff;
				// }
			}
		}

		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		image.getRaster().setDataElements(0, 0, width, height, pixels);
		return image;
	}

	/**
	 * 把传入的原始图像按高度和宽度进行缩放，生成符合要求的图标
	 * 
	 * @param srcImageFile
	 *            源文件地址
	 * @param height
	 *            目标高度
	 * @param width
	 *            目标宽度
	 * @param hasFiller
	 *            比例不对时是否需要补白：true为补白; false为不补白;
	 * @return
	 * @throws IOException
	 */
	private static BufferedImage scale(String srcImageFile, int height,
			int width, boolean hasFiller) throws IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("scale(String, int, int, boolean) - start"); //$NON-NLS-1$
		}

		double ratio = 0.0; // 缩放比例
		File file = new File(srcImageFile);
		BufferedImage srcImage = ImageIO.read(file);
		Image destImage = srcImage.getScaledInstance(width, height,
				BufferedImage.SCALE_SMOOTH);
		// 计算比例
		if ((srcImage.getHeight() > height) || (srcImage.getWidth() > width)) {
			if (srcImage.getHeight() > srcImage.getWidth()) {
				ratio = (new Integer(height)).doubleValue()
						/ srcImage.getHeight();
			} else {
				ratio = (new Integer(width)).doubleValue()
						/ srcImage.getWidth();
			}
			AffineTransformOp op = new AffineTransformOp(
					AffineTransform.getScaleInstance(ratio, ratio), null);
			destImage = op.filter(srcImage, null);
		}
		if (hasFiller) {// 补白
			BufferedImage image = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D graphic = image.createGraphics();
			graphic.setColor(Color.white);
			graphic.fillRect(0, 0, width, height);
			if (width == destImage.getWidth(null))
				graphic.drawImage(destImage, 0,
						(height - destImage.getHeight(null)) / 2,
						destImage.getWidth(null), destImage.getHeight(null),
						Color.white, null);
			else
				graphic.drawImage(destImage,
						(width - destImage.getWidth(null)) / 2, 0,
						destImage.getWidth(null), destImage.getHeight(null),
						Color.white, null);
			graphic.dispose();
			destImage = image;
		}
		BufferedImage returnBufferedImage = (BufferedImage) destImage;
		if (logger.isDebugEnabled()) {
			logger.debug("scale(String, int, int, boolean) - end"); //$NON-NLS-1$
		}
		return returnBufferedImage;
	}

	/**
	 * 解析二维码
	 * 
	 * @param imagePath
	 * @param charset
	 * @return
	 */
	public static String decodeQRCodeImage(String imagePath) {
		if (logger.isDebugEnabled()) {
			logger.debug("decodeQRCodeImage(String) - start"); //$NON-NLS-1$
		}

		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			logger.error("decodeQRCodeImage(String)", e); //$NON-NLS-1$
			return "";
		}
		if (null == image) {
			return "";
		}
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(
				new BufferedImageLuminanceSource(image)));
		Map<DecodeHintType, String> hints = new HashMap<DecodeHintType, String>();
		hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
		Result result = null;
		try {
			result = new MultiFormatReader().decode(bitmap, hints);
			String returnString = result.getText();
			return returnString;
		} catch (NotFoundException e) {
			logger.error("decodeQRCodeImage(String)", e); //$NON-NLS-1$
			return "";
		}
	}

	public static void main(String[] args) {
		String content = "http://dd.myapp.com/16891/48B1576D2FC4AD55550908F218FECF1A.apk?fsname=com%2Eliveaa%2Eeducation%5F1%2E0%5F1.apk";
		// String content = "http://url.cn/QgyT9S";
		encodePR(content, 1000, 1000, "1");
	}
}
