package br.com.alexpfx.android.lib.network.model.usecases.chrome;

/**
 * Created by alexandre on 02/08/15.
 */
public interface Command<T extends CommandParameters, E extends CommandResult> {

    void execute(T parameters, Callback<E> callback);

    interface Callback<T extends CommandResult> {
        void onResult(T commandResult);
    }


}
