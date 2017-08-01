package net.networkdowntime.morris;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpClientUtils {
    static final Logger log = LogManager.getLogger(HttpClientUtils.class);
    private static final String USER_AGENT = "Mozilla/5.0";
    private static int TIMEOUT_MILLIS = 120 * 1000;

    public static String doPostJsonReturnText(String apiEndpoint, Object postObject) throws Exception {
        return doPostJsonReturnText(apiEndpoint, postObject, null);
    }

    public static String doPostJsonReturnText(String apiEndpoint, Object postObject, CookieStore cookieStore) throws Exception {
        CloseableHttpClient httpClient = null;
        try {
            HttpContext localContext = null;
            if (cookieStore != null) {
                localContext = new BasicHttpContext();
                localContext.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
            }

            ObjectMapper mapper = new ObjectMapper();
            String jsonGameState = mapper.writeValueAsString(postObject);

            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(TIMEOUT_MILLIS).setConnectTimeout(TIMEOUT_MILLIS).setConnectionRequestTimeout(TIMEOUT_MILLIS).build();

            URI uri = new URI(apiEndpoint);
            HttpPost post = new HttpPost(uri);
            post.setHeader("User-Agent", USER_AGENT);
            post.setHeader("Accept", "*/*");
            post.setHeader("Content-Type", "application/json");
            log.debug("post.getURI(): " + post.getURI());

            post.setConfig(requestConfig);

            StringEntity requestEntity = new StringEntity(jsonGameState, ContentType.APPLICATION_JSON);
            post.setEntity(requestEntity);

            httpClient = HttpClients.createDefault();
            CloseableHttpResponse response;
            if (cookieStore != null) response = httpClient.execute(post, localContext);
            else response = httpClient.execute(post);

            if (response.getStatusLine().getStatusCode() == 200) {
                return Utils.readInputStreamSingleLine(response.getEntity().getContent()).trim();
            } else {
                Exception e = new Exception();
                log.error("ThreadId=" + Thread.currentThread().getId() + "; Recieved a status code of " + response.getStatusLine().getStatusCode() + " from client: " + apiEndpoint, e);
                throw e;
            }
        } catch (IOException | URISyntaxException e) {
            log.error("ThreadId=" + Thread.currentThread().getId() + "; apiEndpoint=" + apiEndpoint, e);
            throw e;
        } finally {
            if (httpClient != null) httpClient.close();
        }
    }

    public static String doPostJsonReturnJson(String apiEndpoint, Object postObject, CookieStore cookieStore) throws Exception {
        CloseableHttpClient httpClient = null;
        try {
            HttpContext localContext = null;
            if (cookieStore != null) {
                localContext = new BasicHttpContext();
                localContext.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
            }

            ObjectMapper mapper = new ObjectMapper();
            String jsonGameState = mapper.writeValueAsString(postObject);

            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(TIMEOUT_MILLIS).setConnectTimeout(TIMEOUT_MILLIS).setConnectionRequestTimeout(TIMEOUT_MILLIS).build();

            URI uri = new URI(apiEndpoint);
            HttpPost post = new HttpPost(uri);
            post.setHeader("User-Agent", USER_AGENT);
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-Type", "application/json");
            log.debug("post.getURI(): " + post.getURI());

            post.setConfig(requestConfig);

            StringEntity requestEntity = new StringEntity(jsonGameState, ContentType.APPLICATION_JSON);
            post.setEntity(requestEntity);

            httpClient = HttpClients.createDefault();
            CloseableHttpResponse response;
            if (cookieStore != null) response = httpClient.execute(post, localContext);
            else response = httpClient.execute(post);

            if (response.getStatusLine().getStatusCode() == 200) {
                return Utils.readInputStreamSingleLine(response.getEntity().getContent()).trim();
            } else {
                Exception e = new Exception();
                log.error("ThreadId=" + Thread.currentThread().getId() + "; Recieved a status code of " + response.getStatusLine().getStatusCode() + " from client: " + apiEndpoint, e);
                throw e;
            }
        } catch (IOException | URISyntaxException e) {
            log.error("ThreadId=" + Thread.currentThread().getId() + "; apiEndpoint=" + apiEndpoint, e);
            throw e;
        } finally {
            if (httpClient != null) httpClient.close();
        }
    }

    public static String doGet(String apiEndpoint, CookieStore cookieStore, ContentType contentType) throws Exception {
        CloseableHttpClient httpClient = null;
        try {
            HttpContext localContext = null;
            if (cookieStore != null) {
                localContext = new BasicHttpContext();
                localContext.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
            }

            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(TIMEOUT_MILLIS).setConnectTimeout(TIMEOUT_MILLIS).setConnectionRequestTimeout(TIMEOUT_MILLIS).build();

            URI uri = new URI(apiEndpoint);
            HttpGet get = new HttpGet(uri);
            get.setHeader("User-Agent", USER_AGENT);
            get.setHeader("Accept", "application/json");
            get.setHeader("Content-Type", contentType.toString());
            log.debug("get.getURI(): " + get.getURI());

            get.setConfig(requestConfig);

            httpClient = HttpClients.createDefault();
            CloseableHttpResponse response;
            if (cookieStore != null) response = httpClient.execute(get, localContext);
            else response = httpClient.execute(get);

            if (response.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(response.getEntity()).trim();
            } else {
                Exception e = new Exception();
                log.error("ThreadId=" + Thread.currentThread().getId() + "; Recieved a status code of " + response.getStatusLine().getStatusCode() + " from client: " + apiEndpoint, e);
                throw e;
            }
        } catch (IOException | URISyntaxException e) {
            log.error("ThreadId=" + Thread.currentThread().getId() + "; apiEndpoint=" + apiEndpoint, e);
            throw e;
        } finally {
            if (httpClient != null) httpClient.close();
        }
    }

}
