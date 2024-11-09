package application;

public class FlashCardUserData {
    private final int subjectId;
    private final int topicId;
    private final int flashCardId;

    public FlashCardUserData(int subjectId, int topicId, int flashCardId) {
        this.subjectId = subjectId;
        this.topicId = topicId;
        this.flashCardId = flashCardId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public int getTopicId() {
        return topicId;
    }

    public int getFlashCardId() {
        return flashCardId;
    }
}