import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import com.phicomm.application.subscriber.util.SecurityUtil;


public class TestMd5 {
	@Test
	public void md51(){
		try {
			System.out.println(SecurityUtil.md5("123456"));
			System.out.println(SecurityUtil.md5HX("123456"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}
