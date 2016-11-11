package cz.mikealdo.cache.key;

import org.joda.time.DateTime;

public class CacheKeyDescriptor {
    private DateTime currentTime;
    private String competitionHash;

    public CacheKeyDescriptor(DateTime currentTime, String competitionHash) {
        this.currentTime = currentTime;
        this.competitionHash = competitionHash;
    }

    public DateTime getCurrentTime() {
        return currentTime;
    }

    public String getCompetitionHash() {
        return competitionHash;
    }
}
