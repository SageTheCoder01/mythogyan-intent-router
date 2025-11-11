package org.mythogyan.intent;

import org.springframework.stereotype.Service;
import org.mythogyan.util.TextUtils;

@Service
public class IntentClassifier {

    public Intent classify(String text, String lang) {
        if (text == null || text.isBlank()) return Intent.UNKNOWN;
        String t = TextUtils.normalize(text, lang);

        if (t.matches(".*\\b(avatar|incarnation|अवतार)\\b.*")) return Intent.GET_AVATARS_OF_DEITY;
        if (t.matches(".*\\b(wife|husband|consort|पति|पत्नी)\\b.*")) return Intent.GET_CONSORTS_OF_CHARACTER;
        if (t.matches(".*\\b(teacher|guru|गुरु|शिष्य)\\b.*")) return Intent.GET_TEACHER_OF_CHARACTER;
        if (t.matches(".*\\b(parent|mother|father|माता|पिता|children|बच्चे)\\b.*")) return Intent.GET_PARENTS_OF_CHARACTER;
        if (t.matches(".*\\b(symbolize|represent|प्रतीक)\\b.*")) return Intent.GET_CONCEPTS_OF_CHARACTER;
        if (t.matches(".*\\b(connection|related|संबंध)\\b.*")) return Intent.GET_CONNECTION_BETWEEN_CHARACTERS;
        if (t.matches(".*\\b(who is|who was|tell me about|कौन है|बताओ)\\b.*")) return Intent.GET_CHARACTER_INFO;

        return Intent.UNKNOWN;
    }
}
