package br.com.alexpfx.android.lib.network.model.usecases.chrome;

import br.com.alexpfx.android.lib.network.model.ThreadExecutor;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by alexandre on 02/08/15.
 */
public class CommandExecutorUseCaseImpl implements CommandExecutorUseCase {
    private ThreadExecutor threadExecutor;
    private URL url;
    private Command command;
    private CommandParameters commandParameters;
    private Callback callback;
    private CommandDescriptor commandDescriptor;


    public CommandExecutorUseCaseImpl(ThreadExecutor threadExecutor, URL url) {
        this.threadExecutor = threadExecutor;


        this.url = url;
    }


    @Override
    public void run() {
        Map<String, Object> parameters = commandDescriptor.getParameters();
        try {
            String urlPar = "v=oHg5SJYRHA0";
            byte[] postData = urlPar.getBytes();
            int postLenght = postData.length;


            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setInstanceFollowRedirects(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Content-Length", Integer.toString(postLenght));
            urlConnection.setUseCaches(false);

            DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
            wr.write(postData);
            wr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void execute(CommandDescriptor commandDescriptor, Callback callback) {
        this.commandDescriptor = commandDescriptor;
        this.callback = callback;
        threadExecutor.execute(this);
    }
}
