package com.example.demo.common.utils.file;
///**
// * 
// */
//package com.example.demo.common.utils;
//
//import java.awt.Color;
//import java.awt.Graphics2D;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.util.Base64;
//
//import javax.imageio.ImageIO;
//import javax.imageio.stream.ImageOutputStream;
//import com.swetake.util.Qrcode;
//
//import jp.sourceforge.qrcode.data.QRCodeImage;
///**
// * @author LuoYang
// * 实现QRCodeImage接口，
// * 设置解码的图片信息
// * 告诉codeDecoder.decode()将要解析的图片类型
// */
//public class QRCodeImageUtil implements QRCodeImage{
//	
//	BufferedImage bufferedImage;
//
//    public QRCodeImageUtil(BufferedImage bufferedImage){
//        this.bufferedImage=bufferedImage;
//    }
//
//    //宽
//    @Override
//    public int getWidth() {
//        return bufferedImage.getWidth();
//    }
//
//    //高
//    @Override
//    public int getHeight() {
//        return bufferedImage.getHeight();
//    }
//
//    //像素还是颜色
//    @Override
//    public int getPixel(int i, int j) {
//        return bufferedImage.getRGB(i,j);
//    }
//    
//    /**
//     * 生成二维码并转换成base64编码输出
//     * @param content
//     * @return
//     */
//    public static String createQrToBase64(String content){
//    	String base64 = null;
//    	ByteArrayOutputStream baos = null;
//    	ImageOutputStream imageOutputStream = null;
//        try {
//            Qrcode qrcode = new Qrcode();
//            //设置排错率(H L M Q)
//            qrcode.setQrcodeErrorCorrect('M');
//            //N:数字  A：a-Z B：其他字符
//            qrcode.setQrcodeEncodeMode('B');
//            //设置版本（版本至49）
//            qrcode.setQrcodeVersion(7);
//            // 公式 67+12*(版本号-1)
//            int width = 67 + 12*(7-1);
//            int height = 67 + 12*(7-1);
//
//            //定义图片缓冲区（指定图片缓冲区宽和高，以及类型）
//            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
//
//            //定义画板
//            Graphics2D graphics = bufferedImage.createGraphics();
//            graphics.setBackground(Color.WHITE);
//            graphics.setColor(Color.BLACK);
//            //初始化，并指定画板的宽和高
//            graphics.clearRect(0, 0, width, height);
//
//            //定义偏移量
//            int pixoff = 2;
//            //要绘制的内容（字节数组）
//            byte[] contentBytes = content.getBytes("utf-8");
//            //开始绘制:内容长度默认为124（超过124会报错）
//            if (contentBytes.length > 0 && contentBytes.length < 120) {
//                boolean[][] qr = qrcode.calQrcode(contentBytes);
//                for (int i = 0; i < qr.length; i++) {
//                    for (int j = 0; j < qr.length; j++) {
//                        if (qr[j][i]) {
//                            graphics.fillRect(j*3+pixoff, i * 3 + pixoff, 3, 3);
//                        }
//                    }
//                }
//            }
//
//          /*  //生成logo
//            Image img = ImageIO.read(new File("images/logo.jpg"));  // 实例化一个Image对象。
//            graphics.drawImage(img, (width-30)/2, (height-30)/2, 30, 30, null);       // 75,75是距离gs两个边的距离，50,50是中间logo的大小
//           */
//            //收起画板
//            graphics.dispose();
//            bufferedImage.flush();
//            //将图片写入文件
//
//            //字节数组流
//            baos = new ByteArrayOutputStream();
//            //图片输出流
//            imageOutputStream = ImageIO.createImageOutputStream(baos);
//
//            //将图片写入图片流
//            ImageIO.write(bufferedImage, "jpg", imageOutputStream);
//            //将图片转化成base64，web显示
//            //base64 = Base64.encode(baos.toByteArray());
//            base64 = Base64.getEncoder().encodeToString(baos.toByteArray());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally{
//        	if(baos != null){
//				try {
//					baos.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//        	}
//        	if(imageOutputStream != null){
//				try {
//					imageOutputStream.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//        	}
//        }
//        return base64;
//    }
//}
