package br.com.alexpfx.android.lib.network.domain;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by alexandre on 28/07/15.
 */
public class ThreadExecutor {

    private ThreadPoolExecutor threadPoolExecutor;

    private final int poolSize;


    public ThreadExecutor(int poolSize) {
        this.poolSize = poolSize;
        this.threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(poolSize);
    }

    public void execute(final Interactor interactor) {
        threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                interactor.run();
            }
        });
    }

}
