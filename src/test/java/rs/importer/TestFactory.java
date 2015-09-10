package rs.importer;

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
}
