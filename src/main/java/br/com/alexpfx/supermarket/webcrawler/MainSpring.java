package br.com.alexpfx.supermarket.webcrawler;

import br.com.alexpfx.supermarket.bo.ProductBo;
import br.com.alexpfx.supermarket.domain.Product;
import br.com.alexpfx.supermarket.webcrawler.crawler.impl.RibeiraoCrawler;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by alexandre on 26/12/2015.
 */
public class MainSpring {


    void test() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:config/spring.xml");
        RibeiraoCrawler bean = context.getBean(RibeiraoCrawler.class);
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
