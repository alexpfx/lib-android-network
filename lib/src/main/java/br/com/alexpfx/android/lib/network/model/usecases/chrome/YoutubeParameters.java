package br.com.alexpfx.android.lib.network.model.usecases.chrome;

/**
 * Created by alexandre on 02/08/15.
 */
public class YoutubeParameters implements CommandParameters {

    private String url;

    public YoutubeParameters(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
