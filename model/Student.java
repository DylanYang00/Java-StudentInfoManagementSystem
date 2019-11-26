package model;

public class Student {
    String studentID;
    String name;
    double grade;

    public Student(String studentID, String name, double grade) {
        this.studentID = studentID;
        this.name = name;
        this.grade = grade;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public double getGrade() {
        return grade;
    }
    public void setStudent(String studentID,String name,double grade)
    {
        this.grade=grade;
        this.name=name;
        this.studentID=studentID;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj==this)return true;
        if (obj == null) {
            return false;
        } else if (!(obj instanceof Student)) {
            return false;
        }
        Student student=(Student)obj;
        if (this.grade==student.grade&&this.name==student.name&&this.studentID==student.studentID)
        {
            return true;
        }else return false;
    }

    @Override
    public String toString() {
        return "ID "+studentID+" 姓名 "+name+" 成绩 "+grade;
    }
}
