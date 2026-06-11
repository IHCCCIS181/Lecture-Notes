package org.example.moodform;

public enum SatisfactionLevel {
    VERY_UNSATISFIED("😞"),
    UNSATISFIED("😕"),
    NEUTRAL("😐"),
    SATISFIED("😊"),
    EXTREMELY_SATISFIED("😍");

    private String emoji;

    SatisfactionLevel(String emoji) {
        this.emoji = emoji;
    }

    public String getEmoji() {
        return this.emoji;
    }

    public static SatisfactionLevel getLevelFromEmoji(String emoji) {
        for (SatisfactionLevel sl : SatisfactionLevel.values()) {
            if (sl.emoji.equals(emoji)) {
                return sl;
            }
        }
        throw new IllegalArgumentException("Invalid emoji for satisfaction level");
    }
}

