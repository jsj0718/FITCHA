package com.fitcha.model.vo;

public class MovieAndStaffVO {
    private int masId;
    private int movieId;
    private int staffId;
    private String role;
    private String roleName;

    public int getMasId() {
        return masId;
    }

    public void setMasId(int masId) {
        this.masId = masId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
