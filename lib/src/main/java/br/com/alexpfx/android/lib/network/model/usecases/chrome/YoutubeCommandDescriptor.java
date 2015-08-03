package br.com.alexpfx.android.lib.network.model.usecases.chrome;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alexandre on 02/08/15.
 */
public class YoutubeCommandDescriptor implements CommandDescriptor {

    private String video;
    private Map<String, Object> parameters = new HashMap<>();

    public YoutubeCommandDescriptor(String video) {
        this.video = video;
        parameters.put("p1", "v=" + video);
    }

    @Override
    public Map<String, Object> getParameters() {
        return parameters;
    }

}
