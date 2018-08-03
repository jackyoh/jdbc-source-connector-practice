package idv.jack.connector.jdbc;

import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.source.SourceTask;

import java.util.List;
import java.util.Map;

public class JDBCSourceTask extends SourceTask {
    private String querySQL;
    private ConnectionProvider connectionProvider;

    @Override
    public void start(Map<String, String> props) {
        String url = props.get(JDBCSourceConnectorConfig.CONNECTION_URL);
        String userName = props.get(JDBCSourceConnectorConfig.CONNECTION_USER);
        String password = props.get(JDBCSourceConnectorConfig.CONNECTION_PASSWORD);
        String querySQL = props.get(JDBCSourceConnectorConfig.QUERY_SQL);
        this.connectionProvider = new ConnectionProvider(url, userName, password);
    }

    @Override
    public List<SourceRecord> poll() throws InterruptedException {
        return null;
    }

    @Override
    public void stop() {
        if (this.connectionProvider != null) {
            this.connectionProvider.close();
        }
    }

    @Override
    public String version() {
        return Version.getVersion();
    }
}
