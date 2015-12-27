package br.com.alexpfx.supermarket.crawler;

import br.com.alexpfx.supermarket.crawler.jaunt.RibeiraoCrawler;
import br.com.alexpfx.supermarket.crawler.jaunt.UserAgentFactory;
import br.com.alexpfx.supermarket.crawler.model.bo.ProductBo;
import br.com.alexpfx.supermarket.crawler.model.domain.Product;
import br.com.alexpfx.supermarket.crawler.jaunt.CrawlerRunner;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by alexandre on 26/12/2015.
 */
public class MainSpring {



    void test() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:config/spring.xml");
        UserAgentFactory factory = context.getBean(UserAgentFactory.class);
        String startUrl = "https://www.mercadoribeirao.com.br/";
        Thread t = new Thread(new CrawlerRunner(new RibeiraoCrawler(factory.createUserAgent())));
        t.start();
    }

    public static void main(String[] args) {

        new MainSpring().test();
    }

    public static void mainx(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:config/spring.xml");

        ProductBo productBo = (ProductBo) context.getBean(ProductBo.class);
        Product p = new Product();
        p.setUrl("vfasfgaf");
        p.setDescription("fasfasfa");
        productBo.save(p);

        context.close();
    }


}
