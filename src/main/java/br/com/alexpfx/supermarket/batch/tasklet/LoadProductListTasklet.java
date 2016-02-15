package br.com.alexpfx.supermarket.batch.tasklet;

import br.com.alexpfx.supermarket.dao.BaseDao;
import br.com.alexpfx.supermarket.domain.Product;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by alexandre on 08/02/2016.
 */
public class LoadProductListTasklet implements Tasklet {

    @Autowired
    private BaseDao<Product> baseDao;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        List<Product> products = baseDao.findAll();
            System.out.println(products.size());

        return RepeatStatus.FINISHED;
    }
}
