package pl.allegro.tech.hermes.test.helper.endpoint;

import pl.allegro.tech.hermes.api.Topic;
import pl.allegro.tech.hermes.api.endpoints.*;
import pl.allegro.tech.hermes.consumers.ConsumerEndpoint;
import pl.allegro.tech.hermes.test.helper.client.Hermes;

import java.util.List;

public class HermesEndpoints {

    private final GroupEndpoint groupEndpoint;

    private final TopicEndpoint topicEndpoint;

    private final TopicWithSchemaEndpoint topicWithSchemaEndpoint;

    private final SubscriptionEndpoint subscriptionEndpoint;

    private final SchemaEndpoint schemaEndpoint;

    private final QueryEndpoint queryEndpoint;

    private final OAuthProviderEndpoint oAuthProviderEndpoint;

    private final ConsumerEndpoint consumerEndpoint;

    private final OwnerEndpoint ownerEndpoint;

    private final BlacklistEndpoint blacklistEndpoint;

    private final MigrationEndpoint migrationEndpoint;

    public HermesEndpoints(Hermes hermes) {
        this.groupEndpoint = hermes.createGroupEndpoint();
        this.topicEndpoint = hermes.createTopicEndpoint();
        this.topicWithSchemaEndpoint = hermes.createTopicWithSchemaEndpoint();
        this.subscriptionEndpoint = hermes.createSubscriptionEndpoint();
        this.schemaEndpoint = hermes.createSchemaEndpoint();
        this.queryEndpoint = hermes.createQueryEndpoint();
        this.blacklistEndpoint = hermes.createBlacklistEndpoint();
        this.oAuthProviderEndpoint = hermes.createOAuthProviderEndpoint();
        this.consumerEndpoint = hermes.createConsumerEndpoint();
        this.ownerEndpoint = hermes.createOwnerEndpoint();
        this.migrationEndpoint = hermes.createMigrationEndpoint();
    }

    public HermesEndpoints(String hermesFrontendUrl, String consumerUrl) {
        this(createHermesFromUrl(hermesFrontendUrl, consumerUrl));
    }

    private static Hermes createHermesFromUrl(String hermesFrontendUrl, String consumerUrl) {
        return new Hermes(hermesFrontendUrl, consumerUrl)
                .withManagementConfig(JerseyClientFactory.createConfig())
                .withPublisherConfig(JerseyClientFactory.createConfig());
    }

    public GroupEndpoint group() {
        return groupEndpoint;
    }

    public TopicEndpoint topic() {
        return topicEndpoint;
    }

    public TopicWithSchemaEndpoint topicWithSchema() {
        return topicWithSchemaEndpoint;
    }

    public SubscriptionEndpoint subscription() {
        return subscriptionEndpoint;
    }

    public SchemaEndpoint schema() {
        return schemaEndpoint;
    }

    public QueryEndpoint query() {
        return queryEndpoint;
    }

    public OwnerEndpoint owner() {
        return ownerEndpoint;
    }

    public MigrationEndpoint migration() {
        return migrationEndpoint;
    }

    public BlacklistEndpoint blacklist() {
        return blacklistEndpoint;
    }

    public List<String> findTopics(Topic topic, boolean tracking) {
        return topicEndpoint.list(topic.getName().getGroupName(), tracking);
    }

    public List<String> findSubscriptions(String group, String topic, boolean tracked) {
        return subscriptionEndpoint.list(group + "." + topic, tracked);
    }

    public OAuthProviderEndpoint oAuthProvider() {
        return oAuthProviderEndpoint;
    }

    public ConsumerEndpoint consumer() {
        return consumerEndpoint;
    }
}

