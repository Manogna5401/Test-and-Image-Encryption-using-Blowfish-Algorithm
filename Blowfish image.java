import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException; import
javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException; import
javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException; import
javax.crypto.SecretKey;
public class EncryptFile { KeyGenerator
keyGenerator = null; SecretKey
secretKey = null; Cipher cipher = null;
public EncryptFile() { try
{
keyGenerator = KeyGenerator.getInstance("Blowfish");
secretKey = keyGenerator.generateKey();
cipher = Cipher.getInstance("Blowfish");
} catch (NoSuchPaddingException ex) {
System.out.println(ex);
} catch (NoSuchAlgorithmException ex) {
System.out.println(ex);
}
}
public static void main(String[] args) {
String fileToEncrypt = "3.jpg";
String encryptedFile = "encryptedFile.jpg";
String decryptedFile = "decryptedFile.jpg";
String directoryPath = "C:\\Users\\HP\\ISS\\";
EncryptFile encryptFile = new EncryptFile();
long encstart=System.currentTimeMillis();
System.out.println("Starting Encryption...");
encryptFile.encrypt(directoryPath + fileToEncrypt,directoryPath + encryptedFile);
long encend=System.currentTimeMillis();
System.out.println("Encryption completed...");
long diff1=encend-encstart;
System.out.println( diff1 + " milli seconds time for encryption");
long decstart=System.currentTimeMillis();
System.out.println("Starting Decryption...");
encryptFile.decrypt(directoryPath + encryptedFile, directoryPath + decryptedFile);
long decend=System.currentTimeMillis();
System.out.println("Decryption completed...");
long diff2=decend-decstart ;
System.out.println(diff2 + " milli seconds time for decryption");
}
private void encrypt(String srcPath, String destPath) {
File rawFile = new File(srcPath);
File encryptedFile = new File(destPath);
InputStream inStream = null; OutputStream
outStream = null;
try {
cipher.init(Cipher.ENCRYPT_MODE, secretKey);
inStream = new FileInputStream(rawFile);
outStream = new FileOutputStream(encryptedFile);
byte[] buffer = new byte[1024];
int len;
while ((len = inStream.read(buffer)) > 0) {
outStream.write(cipher.update(buffer, 0, len));
outStream.flush();
}
outStream.write(cipher.doFinal());
inStream.close(); outStream.close();
} catch (IllegalBlockSizeException ex) {
System.out.println(ex);
} catch (BadPaddingException ex) {
System.out.println(ex);
} catch (InvalidKeyException ex) {
System.out.println(ex);
} catch (FileNotFoundException ex) {
System.out.println(ex);
} catch (IOException ex) {
System.out.println(ex);
}
}
private void decrypt(String srcPath, String destPath) {
File encryptedFile = new File(srcPath);
File decryptedFile = new File(destPath);
InputStream inStream = null;OutputStream
outStream = null; try {
cipher.init(Cipher.DECRYPT_MODE, secretKey);
inStream = new FileInputStream(encryptedFile);
outStream = new FileOutputStream(decryptedFile); byte[]
buffer = new byte[1024];
int len;
while ((len = inStream.read(buffer)) > 0) {
outStream.write(cipher.update(buffer, 0, len));
outStream.flush();
}
outStream.write(cipher.doFinal()
); inStream.close();
outStream.close();
} catch (IllegalBlockSizeException ex)
{ System.out.println(ex);
} catch (BadPaddingException ex)
{ System.out.println(ex);
} catch (InvalidKeyException ex)
{ System.out.println(ex);
} catch (FileNotFoundException ex)
{ System.out.println(ex);
} catch (IOException ex)
{
System.out.println(ex)
;
}
}
}