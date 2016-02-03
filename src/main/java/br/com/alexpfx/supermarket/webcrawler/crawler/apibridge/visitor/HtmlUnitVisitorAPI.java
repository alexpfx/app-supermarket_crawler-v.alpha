package br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.visitor;

import br.com.alexpfx.supermarket.webcrawler.crawler.apibridge.Constants;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.util.List;

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
        HtmlPage p = null;
        try {
            p = webClient.getPage(url);
            System.out.println(p.asText());
            return p.asXml();
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
