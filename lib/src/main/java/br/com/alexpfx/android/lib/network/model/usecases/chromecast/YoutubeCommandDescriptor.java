package br.com.alexpfx.android.lib.network.model.usecases.chromecast;

/**
 * Created by alexandre on 02/08/15.
 */
public class YoutubeCommandDescriptor implements CommandDescriptor {

    private String video;
    private String parameters;

    public YoutubeCommandDescriptor(String video) {
        this.video = video;
        parameters = "v=" + video;
    }


    @Override
    public String getParameters() {
        return parameters;
    }

    @Override
    public String getUrlSufix() {
        return "/apps/YouTube";
    }

}
