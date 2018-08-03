package idv.jack.connector.jdbc;


import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.source.SourceConnector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCSourceConnector extends SourceConnector {
    private Map<String, String> configProperties;

    @Override
    public void start(Map<String, String> properties) {
        this.configProperties = properties;
    }

    @Override
    public Class<? extends Task> taskClass() {
        return JDBCSourceTask.class;
    }

    @Override
    public List<Map<String, String>> taskConfigs(int maxTasks) {
        List<Map<String, String>> taskConfigs = new ArrayList<Map<String, String>>(1);
        Map<String, String> taskProps = new HashMap<String, String>(this.configProperties);
        taskConfigs.add(taskProps);
        return taskConfigs;
    }

    @Override
    public void stop() {

    }

    @Override
    public ConfigDef config() {
        return new ConfigDef();
    }

    @Override
    public String version() {
        return Version.getVersion();
    }
}
