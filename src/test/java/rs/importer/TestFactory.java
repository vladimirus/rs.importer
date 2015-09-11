package rs.importer;

import rs.importer.model.Link;
import rs.importer.model.Topic;

import java.util.Date;

public class TestFactory {

    private TestFactory() {
        //don't instantiate
    }

    public static Topic aTopic() {
        return  aTopic("t5_2qh33");
    }

    public static Topic aTopic(String id) {
        return aTopic(id, "displayName");
    }

    public static Topic aTopic(String id, String displayName) {
        return Topic.builder()
                .id(id)
                .displayName(displayName)
                .title("title")
                .created(new Date())
                .updated(new Date())
                .nsfw(false)
                .subscribers(1L)
                .description("this is description")
                .type("public")
                .build();
    }

    public static Topic.TopicBuilder topicBuilder() {
        return Topic.builder()
                .id("1")
                .displayName("displayName")
                .title("title")
                .created(new Date())
                .updated(new Date())
                .nsfw(false)
                .subscribers(1L)
                .description("this is description")
                .type("public");
    }

    public static Link aLink(String id) {
        return aLink()
                .id(id)
                .build();
    }

    public static Link.LinkBuilder aLink() {
        return Link.builder()
                .title("title")
                .url("http://url")
                .commentsUrl("http://comments")
                .author("author")
                .topic("topic")
                .topicId("topicId")
                .commentCount(1l)
                .score(1l)
                .created(new Date())
                .self(false)
                .nsfw(false)
                .hidden(false);
    }
}
