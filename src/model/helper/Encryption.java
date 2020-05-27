package model.helper;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {
    
    public String getHash(String arg){ 
        String hashValue = null;
        String algorithm = "SHA-512";
        try{
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            byte digestedBytes[] = messageDigest.digest(arg.getBytes("UTF-8"));
            
            StringBuilder stringBuilder = new StringBuilder();
            
            for(byte b : digestedBytes){
                stringBuilder.append(String.format("%02X", 0xFF & b));
            }
            
            hashValue = stringBuilder.toString();
        }
        catch(NoSuchAlgorithmException | UnsupportedEncodingException e){
            System.out.println("FAIL - Encryption ERRO: " + e);
        }
        return hashValue;
    }


        

    
}
