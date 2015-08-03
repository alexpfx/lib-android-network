package br.com.alexpfx.android.lib.network.model.usecases.chrome;

import br.com.alexpfx.android.lib.network.model.ThreadExecutor;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2Session;
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2SessionException;

import java.util.Map;

/**
 * Created by alexandre on 02/08/15.
 */
public class CommandExecutorUseCaseImpl implements CommandExecutorUseCase {
    private ThreadExecutor threadExecutor;
    private Command command;
    private CommandParameters commandParameters;
    private Callback callback;
    private JSONRPC2Session jsonrpc2Session;
    private CommandDescriptor commandDescriptor;

    public CommandExecutorUseCaseImpl(ThreadExecutor threadExecutor, JSONRPC2Session jsonrpc2Session) {
        this.threadExecutor = threadExecutor;
        this.jsonrpc2Session = jsonrpc2Session;
    }


    @Override
    public void run() {

        Map<String, Object> parameters = commandDescriptor.getParameters();
        JSONRPC2Request r = new JSONRPC2Request("", parameters);
        try {
            jsonrpc2Session.send(r);
            callback.onCommandExecutionSucceful();
        } catch (JSONRPC2SessionException e) {
            callback.onCommandExecutionFailed(e);
        }
    }


    @Override
    public void execute(CommandDescriptor commandDescriptor, Callback callback) {


        this.commandDescriptor = commandDescriptor;
        this.callback = callback;
    }
}
