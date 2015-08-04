package br.com.alexpfx.android.lib.network.model.usecases.chromecast;

import br.com.alexpfx.android.lib.network.utils.ThreadExecutor;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by alexandre on 02/08/15.
 */
public class CommandExecutorUseCaseImpl implements CommandExecutorUseCase {
    private ThreadExecutor threadExecutor;
    private String baseUrl;
    private CommandDescriptor commandDescriptor;

    private Callback callback;

    public CommandExecutorUseCaseImpl(ThreadExecutor threadExecutor) {
        this.threadExecutor = threadExecutor;
    }


    @Override
    public void run() {

        String urlParameters = commandDescriptor.getParameters();
        byte[] postData = urlParameters.getBytes();
        int postLenght = postData.length;
        URL url = null;
        DataOutputStream wr = null;
        HttpURLConnection urlConnection = null;
        try {
            //TODO: por em outra classe.
            url = new URL(baseUrl + commandDescriptor.getUrlSufix());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setInstanceFollowRedirects(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Content-Length", Integer.toString(postLenght));
            urlConnection.setUseCaches(false);
            wr = new DataOutputStream(urlConnection.getOutputStream());
            wr.write(postData);
            final long responseCode = urlConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                callback.onCommandExecutionSucceful();
            } else {
                notifyError(new RuntimeException("invalid http response"));
            }
        } catch (IOException e) {
            notifyError(e);
        } finally {
            try {
                wr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void notifyError(Throwable e) {
        callback.onCommandExecutionFailed(e);
    }


    @Override
    public void execute(String baseUrl, CommandDescriptor commandDescriptor, Callback callback) {
        this.baseUrl = baseUrl;
        this.commandDescriptor = commandDescriptor;
        this.callback = callback;

        threadExecutor.execute(this);
    }
}
