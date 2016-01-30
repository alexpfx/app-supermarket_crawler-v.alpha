package br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.visitor;

import br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.Constants;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;

import java.io.IOException;

/**
 * Created by alexandre on 30/01/2016.
 */
public class HtmlUnitVisitorAPI implements VisitorAPI {
    WebClient webClient;

    public HtmlUnitVisitorAPI() {
        this.webClient = createWebClient();
    }

    @Override
    public String visit(String url) {
        Page p = null;
        try {
            p = webClient.getPage(url);
            return p.getWebResponse().getContentAsString(Constants.CHARSET);
        } catch (IOException e) {
            return "";
        }
    }

    private WebClient createWebClient() {
        WebClient webClient = new WebClient(BrowserVersion.FIREFOX_38);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        webClient.waitForBackgroundJavaScript(Constants.TIMEOUT);
        return webClient;
    }
}
