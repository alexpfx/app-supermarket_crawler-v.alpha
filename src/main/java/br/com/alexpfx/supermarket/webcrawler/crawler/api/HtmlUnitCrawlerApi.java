package br.com.alexpfx.supermarket.webcrawler.crawler.api;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;

import java.io.IOException;

/**
 * Created by alexandre on 30/01/2016.
 */
public class HtmlUnitCrawlerApi implements CrawlerApi {
    private WebClient webClient;

    public HtmlUnitCrawlerApi() {
        this.webClient = createWebClient();
    }


    @Override
    public String visit(String url) {
        Page p = null;
        try {
            p = webClient.getPage(url);
            return p.getWebResponse().getContentAsString(CHARSET);
        } catch (IOException e) {
            return "";
        }
    }

    @Override
    public <T> T getDocument(String htmlCode, Class<T> documentType) {
        throw new IllegalArgumentException("not implemented");
    }


    private WebClient createWebClient() {
        WebClient webClient = new WebClient(BrowserVersion.FIREFOX_38);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        webClient.waitForBackgroundJavaScript(TIMEOUT);
        return webClient;
    }

}
