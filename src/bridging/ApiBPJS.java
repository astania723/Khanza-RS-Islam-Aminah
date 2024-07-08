package bridging;

import fungsi.*;
import java.io.*;
import java.security.*;
import java.security.cert.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import javax.net.ssl.*;
import org.apache.http.conn.scheme.*;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.springframework.http.client.*;
import org.springframework.security.crypto.codec.*;
import org.springframework.web.client.*;

/**
 *
 * @author Kanit SIRS
 */
public class ApiBPJS {        
    private String Key,Consid;
    private String salt;
    private String generateHmacSHA256Signature;
    private byte[] hmacData;
    private Mac mac;
    private long millis;
    private SSLContext sslContext;
    private SSLSocketFactory sslFactory;
    private SecretKeySpec secretKey;
    private Scheme scheme;
    private HttpComponentsClientHttpRequestFactory factory;
    private ApiBPJSAesKeySpec mykey;
    
    /**
     *
     */
    public ApiBPJS(){
        try {
            Key = koneksiDB.SECRETKEYAPIBPJS();
            Consid = koneksiDB.CONSIDAPIBPJS();
        } catch (Exception ex) {
            System.out.println("Notifikasi : "+ex);
        }
    }

    /**
     *
     * @param utc
     * @return
     */
    public String getHmac(String utc) {               
        salt = Consid +"&"+utc;
	generateHmacSHA256Signature = null;
	try {
	    generateHmacSHA256Signature = generateHmacSHA256Signature(salt,Key);
	} catch (GeneralSecurityException e) {
	    // TODO Auto-generated catch block
            System.out.println("Error Signature : "+e);
	    e.printStackTrace();
	}
	return generateHmacSHA256Signature;
    }
    
    /**
     *
     * @param data
     * @param key
     * @return
     * @throws GeneralSecurityException
     */
    public String generateHmacSHA256Signature(String data, String key)throws GeneralSecurityException {
        hmacData = null;
	try {
            secretKey = new SecretKeySpec(key.getBytes("UTF-8"),"HmacSHA256");
	    mac = Mac.getInstance("HmacSHA256");
	    mac.init(secretKey);
	    hmacData = mac.doFinal(data.getBytes("UTF-8"));
	    return new String(Base64.encode(hmacData), "UTF-8");
	} catch (UnsupportedEncodingException e) {
            System.out.println("Error Generate HMac: e");
	    throw new GeneralSecurityException(e);
	}
    }
        
    public long GetUTCdatetimeAsString(){    
        millis = System.currentTimeMillis();   
        return millis/1000;
    }
    
    public String Decrypt(String data,String utc)throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        System.out.println(data);
        mykey = ApiBPJSEnc.generateKey(Consid+Key+utc);
        data=ApiBPJSEnc.decrypt(data, mykey.getKey(), mykey.getIv());
        data=ApiBPJSLZString.decompressFromEncodedURIComponent(data);
        System.out.println(data);
        return data;
    }
    
    /**
     *
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    public RestTemplate getRest() throws NoSuchAlgorithmException, KeyManagementException {
        sslContext = SSLContext.getInstance("SSL");
        TrustManager[] trustManagers= {
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {return null;}
                public void checkServerTrusted(X509Certificate[] arg0, String arg1)throws CertificateException {}
                public void checkClientTrusted(X509Certificate[] arg0, String arg1)throws CertificateException {}
            }
        };
        sslContext.init(null,trustManagers , new SecureRandom());
        sslFactory=new SSLSocketFactory(sslContext,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        scheme=new Scheme("https",443,sslFactory);
        factory=new HttpComponentsClientHttpRequestFactory();
        factory.getHttpClient().getConnectionManager().getSchemeRegistry().register(scheme);
        return new RestTemplate(factory);
    }

}
