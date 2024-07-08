package bridging;

import fungsi.*;
import java.io.*;
import java.nio.charset.*;
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
public class ApiKemenkesSisrute {        
    private String Key,Consid,pass;

    /**
     *
     */
    public ApiKemenkesSisrute(){
        try {                    
            pass = koneksiDB.PASSSISRUTE();
            Consid = koneksiDB.IDSISRUTE();
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashInBytes = md.digest(pass.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for (byte b : hashInBytes) {
                sb.append(String.format("%02x", b));
            }
            Key=sb.toString();            
        } catch (Exception ex) {
            System.out.println("Notifikasi : "+ex);
        }
    }

    /**
     *
     * @return
     */
    public String getHmac() {        
        long GetUTCdatetimeAsString = GetUTCdatetimeAsString();        
        String salt = Consid +"&"+String.valueOf(GetUTCdatetimeAsString);
	String generateHmacSHA256Signature = null;
	try {
	    generateHmacSHA256Signature = generateHmacSHA256Signature(salt,Key);
	} catch (GeneralSecurityException e) {
	    // TODO Auto-generated catch block
            System.out.println("Error Signature : "+e);
	    e.printStackTrace();
	}
	return generateHmacSHA256Signature;
    }

    public String generateHmacSHA256Signature(String data, String key)throws GeneralSecurityException {
	byte[] hmacData = null;

	try {
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes("UTF-8"),"HmacSHA256");
	    Mac mac = Mac.getInstance("HmacSHA256");
	    mac.init(secretKey);
	    hmacData = mac.doFinal(data.getBytes("UTF-8"));
	    return new String(Base64.encode(hmacData), "UTF-8");
	} catch (UnsupportedEncodingException e) {
            System.out.println("Error Generate HMac: e");
	    throw new GeneralSecurityException(e);
	}
    }
        
    /**
     *
     * @return
     */
    public long GetUTCdatetimeAsString(){    
        long millis = System.currentTimeMillis();   
        return millis/1000;
    }
    
    public RestTemplate getRest() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContext.getInstance("SSL");
        javax.net.ssl.TrustManager[] trustManagers= {
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {return null;}
                public void checkServerTrusted(X509Certificate[] arg0, String arg1)throws CertificateException {}
                public void checkClientTrusted(X509Certificate[] arg0, String arg1)throws CertificateException {}
            }
        };
        sslContext.init(null,trustManagers , new SecureRandom());
        SSLSocketFactory sslFactory=new SSLSocketFactory(sslContext,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        Scheme scheme=new Scheme("https",443,sslFactory);
        HttpComponentsClientHttpRequestFactory factory=new HttpComponentsClientHttpRequestFactory();
        factory.getHttpClient().getConnectionManager().getSchemeRegistry().register(scheme);
        return new RestTemplate(factory);
    }

}
