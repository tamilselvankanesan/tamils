package cmecf.programs;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.HttpsURLConnection;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class MakePayment {
  private BasicCookieStore cs = new BasicCookieStore();
  
  public MakePayment(){
    /*BasicClientCookie cookie = new BasicClientCookie("ECFHELPDESK", "TRUE");
    cookie.setDomain(".uscourts.gov");
    cookie.setPath("/");*/
    
    BasicClientCookie cookie1 = new BasicClientCookie("ecfhelpdesk", "true");
    cookie1.setDomain(".uscourts.gov");
    cookie1.setPath("/");
    
    cs.addCookie(cookie1);
  }

  private static void getCokkies() {

  }

  private void sendFeeSuccess() {
    try {
      URL url = new URL("https://ecf.cm3d.aocms.uscourts.gov/n/baradmission/pages/feeSuccess.jsf");
      HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
      connection.setRequestMethod("POST");
      String parameters = "AgencyTransactionId=ACM3DDC-302651?FeeId=585";

      connection.setDoOutput(true);
      DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
      wr.writeBytes(parameters);
      wr.flush();
      wr.close();

      BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      String inputLine;
      StringBuilder response = new StringBuilder();

      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();

      System.out.println(response.toString());
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
  private void setStandardGetHeaders(HttpGet get){
    get.setHeader("Connection", "keep-alive");
    get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:65.0) Gecko/20100101 Firefox/65.0");
  }
  private void getLoginJsf(){
    System.out.println("####################before getLoginJsf pl####################");
    try {
      String url = "https://pacer.login-test.uscourts.gov/csologin/login.jsf";
      CloseableHttpClient client = HttpClientBuilder.create().setDefaultCookieStore(cs).build();
      HttpGet get = new HttpGet(url);
      setStandardGetHeaders(get);

      get.setHeader("Host", "pacer.login-test.uscourts.gov");
      get.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
      
      HttpResponse response = client.execute(get);
      
      System.out.println("Response Code : " 
                    + response.getStatusLine().getStatusCode());
      printCookies();
      
      String content = EntityUtils.toString(response.getEntity());
      String viewState = getViewState(content);
      
      client.close();
      System.out.println("####################after getLoginJsf pl####################");
      postLoginJsf(viewState);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    
  }
  
  private void postLoginJsf(String viewState){
    try {
      System.out.println("####################before postLoginJsf pl####################");
      RequestConfig requestConfig = RequestConfig.custom().setCircularRedirectsAllowed(true).setCookieSpec(CookieSpecs.STANDARD).build();
      String url = "https://pacer.login-test.uscourts.gov/csologin/login.jsf";
      CloseableHttpClient client = HttpClientBuilder.create().setDefaultCookieStore(cs).setDefaultRequestConfig(requestConfig).build();
      HttpPost post = new HttpPost(url);
      
      post.setHeader("Host", "pacer.login-test.uscourts.gov");
      post.setHeader("Referer", "https://pacer.login-test.uscourts.gov/csologin/login.jsf");
      post.setHeader("Connection", "keep-alive");
      post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:65.0) Gecko/20100101 Firefox/65.0");
      post.setHeader("Pragma", "no-cache");
      post.setHeader("Accept", "application/xml, text/xml, */*; q=0.01");
      post.setHeader("X-Requested-With", "XMLHttpRequest");
      post.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
      post.setHeader("Cache-Control", "no-cache");
      post.setHeader("Faces-Request", "partial/ajax");
      post.setHeader("Accept-Language", "en-US,en;q=0.5");
      
      List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
      urlParameters.add(new BasicNameValuePair("javax.faces.partial.ajax", "true"));
      urlParameters.add(new BasicNameValuePair("javax.faces.source", "loginForm:fbtnLogin"));
      urlParameters.add(new BasicNameValuePair("javax.faces.partial.execute", "@all"));
      urlParameters.add(new BasicNameValuePair("javax.faces.partial.render", "pscLoginPanel loginForm redactionConfirmation popupMsgId"));
      urlParameters.add(new BasicNameValuePair("loginForm:fbtnLogin", "loginForm:fbtnLogin"));
      urlParameters.add(new BasicNameValuePair("loginForm", "loginForm"));
      urlParameters.add(new BasicNameValuePair("loginForm:loginName", "daiblackburn22"));
      urlParameters.add(new BasicNameValuePair("loginForm:password", "Test2013!"));
      urlParameters.add(new BasicNameValuePair("loginForm:courtId_input", "N_CM3DDC"));
      urlParameters.add(new BasicNameValuePair("javax.faces.ViewState", viewState));
      
      post.setEntity(new UrlEncodedFormEntity(urlParameters));
      HttpResponse response = client.execute(post);
      
      System.out.println("Response Code : " 
                    + response.getStatusLine().getStatusCode());
      printCookies();
      
      String content = EntityUtils.toString(response.getEntity());
      System.out.println(content);
      client.close();
      System.out.println("####################after postLoginJsf pl####################");
      getLoginPl();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  private void getLoginPl(){
    try {
      System.out.println("####################before getLogin pl####################");
      String url = "https://ecf.cm3d.aocms.uscourts.gov/cgi-bin/login.pl";
      CloseableHttpClient client = HttpClientBuilder.create().setDefaultCookieStore(cs).build();
      HttpGet get = new HttpGet(url);
      setStandardGetHeaders(get);

      get.setHeader("Host", "ecf.cm3d.aocms.uscourts.gov");
      get.setHeader("Cache-Control","no-cache");
      get.setHeader("Pragma", "no-cache");
      get.setHeader("Upgrade-Insecure-Requests","1");
      get.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
//      get.setHeader("Referer", "https://ecf.cm3d.aocms.uscourts.gov/cgi-bin/login.pl");
      
      HttpResponse response = client.execute(get);
      
      System.out.println("Response Code : " 
                    + response.getStatusLine().getStatusCode());
      addKeyCookie(getKeyCookie(EntityUtils.toString(response.getEntity())));
      printCookies();
//      String viewState = getViewState(content);
//      System.out.println("content is ->...."+content);
      client.close();
      System.out.println("####################after getLogin pl####################");
      getBarAdmissions();
      
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  private void getBarAdmissions(){
    
    try {
      System.out.println("####################before getBarAdmissions pl####################");
      String url = "https://ecf.cm3d.aocms.uscourts.gov/n/baradmission/pages/barAdmission.jsf";
      CloseableHttpClient client = HttpClientBuilder.create().setDefaultCookieStore(cs).build();
      HttpGet get = new HttpGet(url);
      setStandardGetHeaders(get);

      get.setHeader("Host", "ecf.cm3d.aocms.uscourts.gov");
      get.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
//      get.setHeader("Referer", "https://ecf.cm3d.aocms.uscourts.gov/cgi-bin/login.pl");
      
      HttpResponse response = client.execute(get);
      
      System.out.println("Response Code : " 
                    + response.getStatusLine().getStatusCode());
      printCookies();
      
      String content = EntityUtils.toString(response.getEntity());
      String viewState = getViewState(content);
      
      client.close();
      System.out.println("####################after getBarAdmissions pl####################");
//      postLoginJsf(viewState);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  private void addKeyCookie(String value){
    BasicClientCookie cookie = new BasicClientCookie("KEY", value);
    cookie.setDomain("ecf.cm3d.aocms.uscourts.gov");
    cookie.setPath("/");
    cs.addCookie(cookie);
  }
  
  private void printCookies(){
    cs.getCookies().forEach(e -> System.out.println(e.getName()+"= "+e.getValue()));
  }
  
  private static String getViewState(String input){
    Pattern p = Pattern.compile("ViewState:6\"\\svalue=\"(.*?)\"\\sauto");
    Matcher m = p.matcher(input);
    if (m.find()) {
      return m.group(1);
    }
    return "";
  }
  
  private static String getKeyCookie(String input){
    
    Pattern p = Pattern.compile("document.cookie=\"KEY=(.*?);\\s+path");
    Matcher m = p.matcher(input);
    if (m.find()) {
      return m.group(1);
    }
    return "";
  }

  public static void main(String[] args) {
    System.setProperty("javax.net.ssl.trustStore", "C:/Tamil/GIT_Repository/tamil/tamils/Test/jssecacerts");
    System.setProperty("javax.net.ssl.trustStorePassword","changeit");
//    send();
    new MakePayment().getLoginJsf();
//    new MakePayment().getLoginPl();
    /*String c = "id=\"j_id1:javax.faces.ViewState:6\" value=\"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\" autocomplete=\"off\" />";
    Pattern p = Pattern.compile("ViewState:6\"\\svalue=\"(.*?)\"\\sauto");
    Matcher m = p.matcher(c);
    if (m.find()) {
      System.out.println(m.group(1));
    }*/
  }
}
