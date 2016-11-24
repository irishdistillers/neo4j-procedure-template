import org.junit.Rule;
import org.junit.Test;
import org.neo4j.driver.v1.Config;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.harness.junit.Neo4jRule;

/**
 * Created by james.donnelly on 24/11/2016.
 */
public class TestDefaultFunction {

    // This rule starts a Neo4j instance for us
    @Rule
    public Neo4jRule neo4j = new Neo4jRule()

            // This is the Procedure we want to test
            .withFunction( DefaultFunction.class );

    @Test
    public void shouldAllowIndexingAndFindingANode() throws Throwable {
        // In a try-block, to make sure we close the driver after the test
        try (Driver driver = GraphDatabase.driver(neo4j.boltURI(), Config.build().withEncryptionLevel(Config.EncryptionLevel.NONE).toConfig())) {

            Session session = driver.session();
            String slug = session.run("RETURN create.slug('Hello. Wòrld!') AS slug")
                    .single().get( 0 ).asString();

            System.out.println(slug);
            session.close();
        }
    }

}
