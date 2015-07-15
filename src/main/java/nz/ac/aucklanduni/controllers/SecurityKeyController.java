package nz.ac.aucklanduni.controllers;

import com.amazonaws.util.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.util.*;

@RestController
public class SecurityKeyController {

    private static final String KEY = "GLOBAL_KEY";

    private static int pswdIterations = 65536;
    private static int keySize = 128;
    private static String algorithm = "AES";
    private static String transformation = "AES/CBC/PKCS5Padding";

    @RequestMapping(value = "/rest/key", method = RequestMethod.POST)
    public String encrypt(@RequestBody String deviceMetadata) {

        String password = "";
        String salt;
        byte[] encryptedKeyBytes;
        byte[] ivBytes;
        List<String> data = new ArrayList<String>();
        Long seed;
        Base64 base64 = new Base64();

        try {

            JSONObject json = new JSONObject(deviceMetadata);
            Iterator<String> keys = json.keys();
            while(keys.hasNext()){
                String key = keys.next();
                String val = json.getString(key);
                data.add(val);
            }

            System.out.println(data.toString());

            // Create random seed from system time
            seed = System.nanoTime();

            // Shuffle the list
            Collections.shuffle(data, new Random(seed));

            StringBuffer sb = new StringBuffer();
            for (String s : data) {
                sb.append(s);
            }
            password = sb.toString();

            // Generate salt based on the seed
            salt = seed.toString();

            // Derive the new key
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            PBEKeySpec spec = new PBEKeySpec(
                    password.toCharArray(),
                    salt.getBytes("UTF-8"),
                    pswdIterations,
                    keySize
            );

            SecretKey secretKey = factory.generateSecret(spec);
            SecretKeySpec secret = new SecretKeySpec(secretKey.getEncoded(), algorithm);

            // Encrypt the key
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.ENCRYPT_MODE, secret);
            AlgorithmParameters params = cipher.getParameters();
            ivBytes = params.getParameterSpec(IvParameterSpec.class).getIV();
            encryptedKeyBytes = cipher.doFinal(KEY.getBytes("UTF-8"));

            return base64.encodeAsString(encryptedKeyBytes)
                    + "_" + base64.encodeAsString(seed.toString().getBytes("UTF-8"))
                    + "_" + base64.encodeAsString(ivBytes);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
