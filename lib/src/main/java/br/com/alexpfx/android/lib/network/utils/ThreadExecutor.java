package br.com.alexpfx.android.lib.network.utils;

import br.com.alexpfx.android.lib.network.model.Interactor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by alexandre on 28/07/15.
 */
public class ThreadExecutor {

    private final int poolSize;
    private ThreadPoolExecutor threadPoolExecutor;


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
