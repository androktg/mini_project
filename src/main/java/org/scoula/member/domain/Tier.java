package org.scoula.member.domain;

public enum Tier {
    BRONZE("Bronze", 0L,           10_000_000L),
    SILVER("Silver", 10_000_000L,  50_000_000L),
    GOLD("Gold",     50_000_000L,  100_000_000L),
    DIAMOND("Diamond", 100_000_000L, Long.MAX_VALUE);

    private final String name;
    private final long min;   // 이상
    private final long max;   // 미만

    Tier(String name, long min, long max) {
        this.name = name;
        this.min = min;
        this.max = max;
    }

    public String getName() { return name; }

    // 총자산을 받아서 해당 티어를 돌려줌
    public static Tier of(long totalAsset) {
        for (Tier t : values()) {
            if (totalAsset >= t.min && totalAsset < t.max) {
                return t;
            }
        }
        return BRONZE;   // 혹시 모를 경우
    }
}