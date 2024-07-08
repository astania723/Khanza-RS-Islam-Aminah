/*
 Kontribusi dari Mas Dhiaz Shahab Dari RS Islam Bontang
*/

package bridging;

import javax.crypto.spec.*;

public class ApiBPJSAesKeySpec {
    private SecretKeySpec key;
    private IvParameterSpec iv;
    
    public SecretKeySpec getKey() {
        return key;
    }
    
    /**
     *
     * @param key
     */
    public void setKey(SecretKeySpec key) {
        this.key = key;
    }
    
    /**
     *
     * @return
     */
    public IvParameterSpec getIv() {
        return iv;
    }
    
    /**
     *
     * @param iv
     */
    public void setIv(IvParameterSpec iv) {
        this.iv = iv;
    }
}
