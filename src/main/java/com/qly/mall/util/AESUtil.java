package com.qly.mall.util;

import com.qly.mall.exception.ErrorException;
import com.qly.mall.exception.ErrorNo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Component
public class AESUtil {
    static final Logger logger = LoggerFactory.getLogger(AESUtil.class);

    /**
     * AES加密字符串
     *
     * @param content
     *            需要被加密的字符串
     * @param keys
     *            加密需要的密码
     * @return 密文
     */
    public static byte[] encrypt(String content, String keys) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(keys.getBytes());
            kgen.init(128, secureRandom);
//            kgen.init(128, new SecureRandom(keys.getBytes()));// 利用用户密码作为随机数初始化出
            //加密没关系，SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以解密只要有password就行
            SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥
            byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥，如果此密钥不支持编码，则返回
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化为加密模式的密码器
            byte[] result = cipher.doFinal(byteContent);// 加密
            return result;
        } catch (Exception e) {
            logger.error("加密失败");
            throw new ErrorException(ErrorNo.ENCRYPT_FAIL.code(), ErrorNo.ENCRYPT_FAIL.msg());
        }
    }

    /**
     * 解密AES加密过的字符串
     *
     * @param content
     *            AES加密过过的内容
     * @param password
     *            加密时的密码
     * @return 明文
     */
    public static byte[] decrypt(byte[] content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者
            //windows
//            kgen.init(128, new SecureRandom(password.getBytes()));
            //linux
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥
            byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化为解密模式的密码器
            byte[] result = cipher.doFinal(content);
            System.out.println(result);
            return result; // 明文
        } catch (ErrorException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            logger.error("解密失败");
            e.printStackTrace();
//            throw new ErrorException(ErrorNo.DECRYPT_FAIL.code(), ErrorNo.DECRYPT_FAIL.msg());
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**将二进制转换成16进制
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**将16进制转换为二进制
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }


    public static void main(String[] args) throws UnsupportedEncodingException {
        String key = "qinleyiTestEncodeFancy";
        byte[] encrypt = encrypt("131221sh", key);
        String hexStr = parseByte2HexStr(encrypt);
        System.out.println("1、加密："+hexStr);//0B903ECB3174CE6499953F6C59F08F5A

        byte[] decrypt = parseHexStr2Byte(hexStr);
        byte[] decrypt_ = decrypt(decrypt, key);
        System.out.println("2、解密："+new String(decrypt_,"utf-8"));
    }

}