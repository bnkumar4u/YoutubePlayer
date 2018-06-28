package com.bnk.youtubeplayer;

class VideoData {

    String title,videoId,description , thumbnailURL;
    static String nextToken;
    static int totalResults;

    public VideoData(String title, String videoId) {
        this.title = title;
        this.videoId = videoId;
    }

    public VideoData(String title, String videoId, String description, String thumbnailURL) {
        this.title = title;
        this.videoId = videoId;
        this.description = description;
        this.thumbnailURL = thumbnailURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public static String getNextToken() {
        return nextToken;
    }

    public static void setNextToken(String nextToken) {
        VideoData.nextToken = nextToken;
    }

    public static int getTotalResults() {
        return totalResults;
    }

    public static void setTotalResults(int totalResults) {
        VideoData.totalResults = totalResults;
    }
}
