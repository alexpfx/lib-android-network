package br.com.alexpfx.android.lib.network.model.usecases.chromecast;

import br.com.alexpfx.android.lib.network.model.Interactor;

/**
 * Created by alexandre on 02/08/15.
 */
public interface CommandExecutorUseCase extends Interactor {
    void execute(String baseUrl, CommandDescriptor commandDescriptor, Callback callback);

    interface Callback {
        void onCommandExecutionSucceful();

        void onCommandExecutionFailed(Throwable throwable);

    }

}
