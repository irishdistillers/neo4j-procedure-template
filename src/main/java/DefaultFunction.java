import com.github.slugify.Slugify;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.logging.Log;
import org.neo4j.procedure.Context;
import org.neo4j.procedure.Description;
import org.neo4j.procedure.Name;
import org.neo4j.procedure.UserFunction;

import java.util.UUID;

/**
 * Created by james.donnelly on 24/11/2016.
 */
public class DefaultFunction {

    // This field declares that we need a GraphDatabaseService
    // as context when any procedure in this class is invoked
    @Context
    public GraphDatabaseService db;

    // This gives us a log instance that outputs messages to the
    // standard log, `neo4j.log`
    @Context
    public Log log;

    @UserFunction("create.uuid")
    @Description("creates an UUID (universally unique id)")
    public String uuid() {
        return UUID.randomUUID().toString();
    }

    @UserFunction("create.slug")
    @Description("creates a slug from a text string")
    public String slug( @Name("text") String text ) {

        Slugify slg = new Slugify();
        return slg.slugify(text);
    }
}



