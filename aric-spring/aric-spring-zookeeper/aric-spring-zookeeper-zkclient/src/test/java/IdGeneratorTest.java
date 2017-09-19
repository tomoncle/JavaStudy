import com.aric.zkclient.api.nameservice.IdGenerator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/9/19.
 */
public class IdGeneratorTest{

    public static void main(String[] args) throws Exception{

        ExecutorService executorService= Executors.newFixedThreadPool(100);

        for (int i=0;i<20;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        IdGenerator idGenerato=
                                new IdGenerator("localhost:2181","table2");
                        idGenerato.start();
                        System.out.println(idGenerato.generatorPrefix(
                                IdGenerator.RELEASE_NODE_SOURCE.FAST));
                        idGenerato.stop();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        executorService.shutdown();
    }


}
