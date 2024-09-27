/*
 Kontribusi dari Mas Dhiaz Shahab Dari RS Islam Bontang
 */

package bridging;

import java.nio.charset.*;
import java.security.*;
import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.*;

/**
 *
 * @author dhias
 */
public class ApiBPJSEnc {

    /**
     *
     */
    public static final String ALGORITHM = "AES/CBC/PKCS5Padding";

    public static ApiBPJSAesKeySpec generateKey(String key) 
                throws NoSuchPaddingException, NoSuchAlgorithmException,
                InvalidAlgorithmParameterException, InvalidKeyException,
                BadPaddingException, IllegalBlockSizeException {

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] _hashKey = digest.digest(key.getBytes(StandardCharsets.UTF_8));
        byte[] _hashIv = new byte[16];
        System.arraycopy(_hashKey, 0, _hashIv, 0, 16);

        ApiBPJSAesKeySpec aesKeySpec = new ApiBPJSAesKeySpec();
        SecretKeySpec _key = new SecretKeySpec(_hashKey, "AES");
        IvParameterSpec _iv = new IvParameterSpec(_hashIv);
        aesKeySpec.setKey(_key);
        aesKeySpec.setIv(_iv);
        return aesKeySpec;
    }

    /**
     *
     * @param cipherText
     * @param key
     * @param iv
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static String decrypt(String cipherText, SecretKeySpec key, IvParameterSpec iv) 
                    throws NoSuchPaddingException, NoSuchAlgorithmException,
                InvalidAlgorithmParameterException, InvalidKeyException,
                BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
        return new String(plainText);
    }

    public static String encrypt(String cipherText, SecretKeySpec key, IvParameterSpec iv) 
                    throws NoSuchPaddingException, NoSuchAlgorithmException,
                InvalidAlgorithmParameterException, InvalidKeyException,
                BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        return Base64.getEncoder().encodeToString(cipher.doFinal(cipherText.getBytes(StandardCharsets.UTF_8)));
    }        
}
