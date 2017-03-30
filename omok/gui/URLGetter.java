package gui;

import java.net.URL;
/**
 * @author son
 * @since 2005-11-03
 * 
 *   이클래스는 JAR파일 내에 존재하는 이미지 파일의 URL을 얻기 위한 클래스 
 */
public class URLGetter {
	
	private URLGetter() {}
	
	/**
	 * stitc 메소드로서, 객체를 따로 생성할 필요 없이 클래스 이름으로 
	 * 접근이 가능하다. 스트링으로 된 파일이름을 매개변수로 ㅈ넘기면 URL을
	 * 얻을수 있다.
	 * 
	 * @param filename 파일이름.
	 * @return URL 객체를 반환한다.
	 */
	public static URL getResource(String filename) {
		 // jar안에서 읽게한다.
		URL url = ClassLoader.getSystemResource(filename);

		//		 jar파일에서 발견되지 않으면 disk로부터 읽는다.
		if (url == null) { 
			try {
				url = new URL("file", "localhost", filename);
			} catch (Exception urlException) {} // ignore
		}
		return url;
	}
}