package common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtil {
	public String encryptSHA256(String str){
    String sha = "";
    try{
       MessageDigest sh = MessageDigest.getInstance("SHA-256");
//       int salt = (int)(Math.random()*100)+1;
//       str += salt;  //salt를 할 경우 db에 salt까지 같이 저장해주어야 한다.
       sh.update(str.getBytes());
       byte byteData[] = sh.digest();
       StringBuffer sb = new StringBuffer();
       for(int i = 0 ; i < byteData.length ; i++){
           sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
       }
       sha = sb.toString();
   }catch(NoSuchAlgorithmException e){
       System.out.println("Encrypt Error - NoSuchAlgorithmException");
       sha = null;
   }
   return sha;
 } 
}
