/**
 * 
 */
package phong.FAA_GUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jsoup.Jsoup;

/**
 * @author Phong Nguyen (vietphong.nguyen@gmail.com)
 *
 */
public class HTML {

	String HTMLCode;
	/**
	 * @param url 
	 * @throws IOException 
	 * 
	 */
	public void HTML4(String urlS) throws IOException {
		String html = Jsoup.connect(urlS).get().html();
	    
		HTMLCode = (html);
	}
	public HTML(String urlS) throws IOException {
		URL url = new URL(urlS);
		Map<String,String> httpHeaders=new HashMap<>();
        httpHeaders.put("Accept", "application/json");
        httpHeaders.put("User-Agent", "myApplication");
        InputStream is = urlToInputStream(url,httpHeaders);
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        String inputLine;
        StringBuilder a = new StringBuilder();
		while ((inputLine = in.readLine()) != null) {
			a.append(inputLine);
			
			// wait 5 seconds
			//System.out.println(inputLine);
		}

		in.close();
		is.close();
		
		HTMLCode = (a.toString());
	}
    
	public void HTML5(String urlS) throws IOException {
		URL url = new URL(urlS);
		HttpURLConnection httpCon;
//	    //set http request headers
//	            httpCon.addRequestProperty("Host", "www.cumhuriyet.com.tr");
//	            httpCon.addRequestProperty("Connection", "keep-alive");
//	            httpCon.addRequestProperty("Cache-Control", "max-age=0");
//	            httpCon.addRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
//	            httpCon.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36");
//	            httpCon.addRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
//	            httpCon.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
//	            //httpCon.addRequestProperty("Cookie", "JSESSIONID=EC0F373FCC023CD3B8B9C1E2E2F7606C; lang=tr; __utma=169322547.1217782332.1386173665.1386173665.1386173665.1; __utmb=169322547.1.10.1386173665; __utmc=169322547; __utmz=169322547.1386173665.1.1.utmcsr=stackoverflow.com|utmccn=(referral)|utmcmd=referral|utmcct=/questions/8616781/how-to-get-a-web-pages-source-code-from-java; __gads=ID=3ab4e50d8713e391:T=1386173664:S=ALNI_Mb8N_wW0xS_wRa68vhR0gTRl8MwFA; scrElm=body");
//	            HttpURLConnection.setFollowRedirects(true);
//	            httpCon.setInstanceFollowRedirects(true);
//	            httpCon.setDoOutput(true);

		BufferedReader in;
		StringBuilder a;
		InputStreamReader IS;
		do {
			httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setUseCaches(false);

			httpCon.setRequestMethod("GET");

			IS = new InputStreamReader(httpCon.getInputStream(), "UTF-8");
			in = new BufferedReader(IS);
			String inputLine;
			a = new StringBuilder();
			while ((inputLine = in.readLine()) != null)
				a.append(inputLine);

			HTMLCode = (a.toString());
			if (HTMLCode.length() > 153 * 20)
				break;
		} while (true);

		in.close();

		httpCon.disconnect();
	}
	
	public void HTML2(String urlS) throws IOException {
		URL urlObject = new URL(urlS);
        URLConnection urlConnection = urlObject.openConnection();
        urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
       
        HTMLCode = toString(urlConnection.getInputStream());
	}

	private String toString(InputStream inputStream) throws UnsupportedEncodingException, IOException {
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8")))
        {
            String inputLine;
            StringBuilder stringBuilder = new StringBuilder();
            while ((inputLine = bufferedReader.readLine()) != null)
            {
                stringBuilder.append(inputLine);
            }

            return stringBuilder.toString();
        }
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s1 = "http://localhost:8983/solr/knowledgeArchitecture.html?query=perform+handoff";
		String s2 = "https://www.youtube.com/watch?v=-UynuFg9g8E";
		String s3 = "https://www.youtube.com/watch?v=aFPPfPXZVgs";
		HTML html;
		try {
			html = new HTML(s3);
			System.out.println(html.HTMLCode);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	public List<StringIntegerPair> getRelatedTopics() {
		// TODO Auto-generated method stub
		return null;
	}

	private InputStream urlToInputStream(URL url, Map<String, String> args) {
	    HttpURLConnection con = null;
	    InputStream inputStream = null;
	    try {
	        con = (HttpURLConnection) url.openConnection();
	        con.setConnectTimeout(15000);
	        con.setReadTimeout(15000);
	        if (args != null) {
	            for (Entry<String, String> e : args.entrySet()) {
	                con.setRequestProperty(e.getKey(), e.getValue());
	            }
	        }
	        con.connect();
	        int responseCode = con.getResponseCode();
	        /* By default the connection will follow redirects. The following
	         * block is only entered if the implementation of HttpURLConnection
	         * does not perform the redirect. The exact behavior depends to 
	         * the actual implementation (e.g. sun.net).
	         * !!! Attention: This block allows the connection to 
	         * switch protocols (e.g. HTTP to HTTPS), which is <b>not</b> 
	         * default behavior. See: https://stackoverflow.com/questions/1884230 
	         * for more info!!!
	         */
	        if (responseCode < 400 && responseCode > 299) {
	            String redirectUrl = con.getHeaderField("Location");
	            try {
	                URL newUrl = new URL(redirectUrl);
	                return urlToInputStream(newUrl, args);
	            } catch (MalformedURLException e) {
	                URL newUrl = new URL(url.getProtocol() + "://" + url.getHost() + redirectUrl);
	                return urlToInputStream(newUrl, args);
	            }
	        }
	        /*!!!!!*/

	        inputStream = con.getInputStream();
	        return inputStream;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	private InputStream getInputStreamFromUrl(URL url, String user, String passwd) throws IOException {
        String encoded = Base64.getEncoder().encodeToString((user + ":" + passwd).getBytes(StandardCharsets.UTF_8));
        Map<String,String> httpHeaders=new HashMap<>();
        httpHeaders.put("Accept", "application/json");
        httpHeaders.put("User-Agent", "myApplication");
        httpHeaders.put("Authorization", "Basic " + encoded);
        return urlToInputStream(url,httpHeaders);
    }
	
}
