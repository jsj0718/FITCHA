package com.fitcha.model.vo;

import java.util.Date;

public class DipsVO {
    private int dipsId;
    private String userId;
    private int movieId;
    private Date ddate;
    private String title;
    private String poster;

    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public int getDipsId() {
        return dipsId;
    }

    public void setDipsId(int dipsId) {
        this.dipsId = dipsId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

	public Date getDdate() {
		return ddate;
	}

	public void setDdate(Date ddate) {
		this.ddate = ddate;
	}
    

}
