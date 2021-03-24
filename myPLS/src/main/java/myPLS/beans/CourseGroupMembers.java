package myPLS.beans;

public class CourseGroupMembers {
    private int courseGroupMembersID;
    private int userID;
    private int courseGroupID;

    public int getCourseGroupMembersID() {
        return this.courseGroupMembersID;
    }

    public void setCourseGroupMembersID(int courseGroupMembersID) {
        this.courseGroupMembersID = courseGroupMembersID;
    }

    public int getUserID() {
        return this.userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getCourseGroupID() {
        return this.courseGroupID;
    }

    public void setCourseGroupID(int courseGroupID) {
        this.courseGroupID = courseGroupID;
    }

    public CourseGroupMembers(int courseGroupMembersID, int userID, int courseGroupID) {
        this.courseGroupMembersID = courseGroupMembersID;
        this.userID = userID;
        this.courseGroupID = courseGroupID;
    }
    public CourseGroupMembers(int userID, int courseGroupID) {
        this.userID = userID;
        this.courseGroupID = courseGroupID;
    }
}
