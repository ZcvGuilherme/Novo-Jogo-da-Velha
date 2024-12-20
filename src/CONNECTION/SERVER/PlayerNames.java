package CONNECTION.SERVER;

import java.io.Serializable;

public class PlayerNames implements Serializable {
 
	private static final long serialVersionUID = 1L;
	private String hostName;
    private String clientName;

    public PlayerNames(String hostName, String clientName) {
        this.hostName = hostName;
        this.clientName = clientName;
    }

    public String getHostName() {
        return hostName;
    }

    public String getClientName() {
        return clientName;
    }
}
