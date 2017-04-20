package com.lee.x.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import Decoder.BASE64Encoder;


public class KeyPairGenUtil {
    /**
     * 指定加密算法为RSA
     */
    private static final String ALGORITHM = "RSA";
    /**
     * 密钥长度，用来初始化
     */
    private static final int KEYSIZE = 1024;
    /**
     * 指定公钥存放文件
     */
    private static String PUBLIC_KEY_FILE = "public_key.pem";
    /**
     * 指定私钥存放文件
     */
    private static String PRIVATE_KEY_FILE = "private_key.pem";

    public static void main() throws Exception {
        KeyPairGenUtil keys = new KeyPairGenUtil();
        boolean isSuccess = keys.createKey(true);
        if (isSuccess) {
            String plainText = "dsdfdsfds分地方迪马股份大规模发电量圣诞发劳动模范的 ";
            byte[] cipherData = keys.encryptByPublicKey(keys.getPublicKey(), plainText.getBytes());
            String cipher = NewBase64.encode(cipherData);
            System.out.println(cipher);
            byte[] res = keys.decryptBYPrivateKey(keys.getPriveteKey(), NewBase64.decode(cipher));
            String restr = new String(res);
            System.out.println("结果:" + restr);
        } else {
            System.out.println("RSA 公私钥生成失败");
        }

    }

    /**
     * 公钥加密
     *
     * @param publicKey
     * @param plainTextData
     * @return
     * @throws Exception
     */
    public byte[] encryptByPublicKey(RSAPublicKey publicKey, byte[] plainTextData) throws Exception {
        if (publicKey == null) {
            throw new Exception("加密公钥为空, 请设置");
        }
        Cipher cipher = null;
        try {
            // 使用默认RSA
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] output = cipher.doFinal(plainTextData);
            return output;
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此加密算法");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e) {
            throw new Exception("加密公钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("明文长度非法");
        } catch (BadPaddingException e) {
            throw new Exception("明文数据已损坏");
        }
    }


    /**
     * 私钥加密
     *
     * @param privateKey
     * @param plainTextData
     * @return
     * @throws Exception
     */
    public byte[] encryptByPrivateKey(RSAPrivateKey privateKey, byte[] plainTextData) throws Exception {
        if (privateKey == null) {
            throw new Exception("私钥加密为空, 请设置");
        }
        Cipher cipher = null;
        try {
            // 使用默认RSA
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] output = cipher.doFinal(plainTextData);
            return output;
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此加密算法");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e) {
            throw new Exception("加密私钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("明文长度非法");
        } catch (BadPaddingException e) {
            throw new Exception("明文数据已损坏");
        }
    }


    /**
     * 解密私钥
     *
     * @param privateKey
     * @param cipherData
     * @return
     * @throws Exception
     */
    public byte[] decryptBYPrivateKey(RSAPrivateKey privateKey, byte[] cipherData) throws Exception {
        if (privateKey == null) {
            throw new Exception("解密私钥为空, 请设置");
        }
        Cipher cipher = null;
        try {
            // 使用默认RSA
            cipher = Cipher.getInstance("RSA");
            // cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] output = cipher.doFinal(cipherData);
            return output;
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此解密算法");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e) {
            throw new Exception("解密私钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("密文长度非法");
        } catch (BadPaddingException e) {
            throw new Exception("密文数据已损坏");
        }
    }


    /**
     * 公钥解密
     *
     * @param publicKey
     * @param cipherData
     * @return
     * @throws Exception
     */
    public byte[] decryptByPublicKey(RSAPublicKey publicKey, byte[] cipherData) throws Exception {
        if (publicKey == null) {
            throw new Exception("解密公钥为空, 请设置");
        }
        Cipher cipher = null;
        try {
            // 使用默认RSA
            cipher = Cipher.getInstance("RSA");
            // cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] output = cipher.doFinal(cipherData);
            return output;
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此解密算法");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e) {
            throw new Exception("解密公钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("密文长度非法");
        } catch (BadPaddingException e) {
            throw new Exception("密文数据已损坏");
        }
    }


    /**
     * 获得privateKey
     *
     * @return
     * @throws Exception
     */
    public RSAPrivateKey getPriveteKey() throws Exception {
        try {
            byte[] buffer = NewBase64.decode(getPriveteKeyString());
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new Exception("私钥非法");
        } catch (NullPointerException e) {
            throw new Exception("私钥数据为空");
        }
    }

    /**
     * 获得公钥对象
     *
     * @return
     * @throws Exception
     */
    public RSAPublicKey getPublicKey() throws Exception {
        try {
            byte[] buffer = NewBase64.decode(getPublicKeyString());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new Exception("公钥非法");
        } catch (NullPointerException e) {
            throw new Exception("公钥数据为空");
        }
    }


    /**
     * 获得私钥字符串
     *
     * @return
     * @throws IOException
     */
    public String getPriveteKeyString() {

        return "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKfhRBakXl8YXReL" +
                "9WrNOfpadPuFgRqjoILrXvMpuu3YM5QPnl0TQAVx7EH8RgIlJ1oQEpvX6+c7+K0s" +
                "bPMwQQIyd3Bym9YPa9ns7UaUg1ptFYKPYNzIt6qP3/4nvFguDYyrsy6WvgJQf1nx" +
                "glOaFJUSeHSUe0J/4pHBZoUkS81rAgMBAAECgYEAo+bagwCW4mD3AjYytMMJB9Oo" +
                "wqflFA0MeMUzPlRPnEaolLVq95QDvU0sTH/wGcgMU3G9uUlll6yFbQ0ikYta6yVR" +
                "ssZh4ZLrhTddNFij/KHaJWfTlMnimsyq6PIRSluf0lU0ST9CxjtHpqgA8Z7DwZF5" +
                "k7w9GHSoLqTEgAGGpgECQQDUKfnC5NnlUzYCvK5aehekVbqgabCKUPdpqT19JV4Q" +
                "Im5ZZGp4pxnWXQCIqolJfZp2uJairtI/j3BJa6bm5Gi7AkEAypD31p4t0leJFJ9s" +
                "apdKkh3KnT7Bmm0179dpCmF5VDovtrjpKyonqy3M5VLaxKEXljMTLr0OTL3EqF0M" +
                "sP17EQJBAKiv0yhVIrYFyfy1Hf9L8b40d1URk071/zEgYYY+DqLPbWNfxpO1cIKS" +
                "oVkIFm27EvPv4bzpOLtWhZWqpSIRWv8CQDqBzqO1gkSbteM3/Md4tTgo1KnAQ4kH" +
                "/CkvDY7LApE6USe0ltw5y8VccC7YVImwgbCHRgYc4vF1akS6w3ynahECQCQjxZN6" +
                "g43ybSFQVxxCYwaPUs2WcrQe1/E5nfilh04hq15hym+1WIiG2kxWBT3jRG9o2cQL" +
                "V+Z/7hfxNSVnYC0=";

    }

    /**
     * 获取公钥字符串
     *
     * @return
     * @throws IOException
     */
    public String getPublicKeyString() {

        return "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCn4UQWpF5fGF0Xi/VqzTn6WnT7" +
                "hYEao6CC617zKbrt2DOUD55dE0AFcexB/EYCJSdaEBKb1+vnO/itLGzzMEECMndw" +
                "cpvWD2vZ7O1GlINabRWCj2DcyLeqj9/+J7xYLg2Mq7Mulr4CUH9Z8YJTmhSVEnh0" +
                "lHtCf+KRwWaFJEvNawIDAQAB";

    }

    /**
     * key是否已经生成
     *
     * @return
     */
    public boolean keyIsExits() {
        java.io.File file = new java.io.File(PUBLIC_KEY_FILE);
        return file.exists();
    }

    public void delKey() {
        java.io.File file = new java.io.File(PUBLIC_KEY_FILE);
        file.delete();
        java.io.File file1 = new java.io.File(PRIVATE_KEY_FILE);
        file1.delete();
    }

    /**
     * 生成key
     *
     * @return
     */
    public boolean createKey(boolean clear) throws Exception {
        if (clear) {
            delKey();
        }
        boolean isExits = keyIsExits();
        if (isExits)
            return true;
        // /** RSA算法要求有一个可信任的随机数源 */
        // SecureRandom secureRandom = new SecureRandom();
        /** 为RSA算法创建一个KeyPairGenerator对象 */
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);

        /** 利用上面的随机数据源初始化这个KeyPairGenerator对象 */
        // keyPairGenerator.initialize(KEYSIZE, secureRandom);
        keyPairGenerator.initialize(KEYSIZE);

        /** 生成密匙对 */
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        /** 得到公钥 */
        Key publicKey = keyPair.getPublic();

        /** 得到私钥 */
        Key privateKey = keyPair.getPrivate();

        byte[] publicKeyBytes = publicKey.getEncoded();
        byte[] privateKeyBytes = privateKey.getEncoded();

        String publicKeyBase64 = new BASE64Encoder().encode(publicKeyBytes);
        String privateKeyBase64 = new BASE64Encoder().encode(privateKeyBytes);

        OutputStreamWriter oos1 = null;
        OutputStreamWriter oos2 = null;
        try {
            /** 用对象流将生成的密钥写入文件 */
            oos1 = new OutputStreamWriter(new FileOutputStream(PUBLIC_KEY_FILE));
            oos2 = new OutputStreamWriter(new FileOutputStream(PRIVATE_KEY_FILE));
            oos1.write(publicKeyBase64);
            oos2.write(privateKeyBase64);
        } catch (Exception e) {
            throw e;
        } finally {
            /** 清空缓存，关闭文件输出流 */
            oos1.close();
            oos2.close();
        }
        return true;
    }

}
