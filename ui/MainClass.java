package ui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.Student;
import tool.IO;

public class MainClass {



    public ArrayList<Student> studentArrayList=new ArrayList<Student>();
    public int count = 0;

    public MainClass()
    {
        //读取文件
        IO io=new IO();
        io.load(this);

        Scanner scan = new Scanner(System.in);

        printMenu();

        while(true)
        {
            //读取用户输入
            int choice = scan.nextInt();

            if(choice == 5)
            {
                io.save(this);//保存数据到文件
                System.out.println("成功退出系统，欢迎再次光临！");
                break;

            }
            switch(choice)//switch形式
            {
                case 1: addStudent();printMenu(); break;
                case 2: deleteStudent(); break;
                case 3: changeStudent(); break;
                case 4: findStudent(); break;
                default: System.out.println("输入非法"); printMenu(); continue;//这个continue 是continue的while，
            }
        }



    }
    void printMenu()
    {
        //打印菜单
        System.out.println("欢迎...");
        System.out.println("增加学生信息...1");
        System.out.println("删除学生信息...2");
        System.out.println("修改学生信息...3");
        System.out.println("查询学生信息...4");
        System.out.println("退出系统...5");
    }

    void addStudent()//增加学生信息
    {
        if (count == studentArrayList.size())
        {
            System.out.println("当前共有:"+count+"名学生!");
            Scanner scan = new Scanner(System.in);
            System.out.println("请输入学生ID：");
            String studentID = scan.next();

            System.out.println("请输入学生名：");
            String studentName = scan.next();

            System.out.println("请输入成绩：");
            double studentGrade = scan.nextDouble();
            Student student = new Student(studentID,studentName,studentGrade);
            studentArrayList.add(student);
            count++;
            System.out.println("增加成功！");
            printAllStudent();
        }
        else
        {
            System.out.println("学生信息已满！");
        }


    }

    void deleteStudent()//删除学生信息
    {
        Scanner scan = new Scanner(System.in);
        while(true)
        {
            System.out.println("请输入按哪种方法删除学生信息：1、学生ID/2、姓名/3、返回主菜单");
            int choose = scan.nextInt();
            if(choose == 1)
            {
                System.out.println("请输入要删除学生的ID：");

                String id=scan.next();


                    Student studentObject=null;
                    for (Student student:studentArrayList) {
                        if (id.equals(student.getStudentID()))
                        {
                            studentObject=student;
                            break;
                        }
                    }
                    if (studentArrayList.remove(studentObject))
                    {
                        count--;
                        System.out.println("删除成功！");
                        printAllStudent();
                    }
                    else
                    {
                        System.out.println("对不起，删除失败，没有对应值");
                        printAllStudent();
                    }

            }
            else if(choose == 2)
            {
                System.out.println("请输入您要删除的学生姓名：");
                String name = scan.next();




                ArrayList<Student> arrayOfStudents=new ArrayList<Student>();
                for (Student student:studentArrayList) {
                    if (name.equals(student.getName()))
                    {
                       arrayOfStudents.add(student);
                    }
                }
                for (Student student:arrayOfStudents) {
                    if (studentArrayList.remove(student))
                    {
                        count--;
                        System.out.println("删除成功！");
                        printAllStudent();
                    }
                    else
                    {
                        System.out.println("对不起，删除失败,没有对应值");
                        printAllStudent();
                    }
                }

            }
            else if(choose == 3)
            {
                printMenu();
                break; //这个break会跳出当前循环，从而实现跳出当前函数，返回上一级循环，即主菜单。
            }
            else
            {
                System.out.println("输入非法！");
            }
        }
    }

    void changeStudent()
    {
        Scanner scan = new Scanner(System.in);
        while(true)
        {
            System.out.println("请输入按哪种方法修改学生信息：1、序号/2.姓名/3、返回主菜单");
            int choose = scan.nextInt();
            if(choose == 1)
            {
                System.out.println("请输入要修改第几位学生的信息：");
                int number = scan.nextInt();
                int id = orderFind(number);
                if(id > -1)
                {
                    Student student=studentArrayList.get(id);
                    System.out.println("原ID为："+student.getStudentID()+" 请输入你要修改为什么ID：");
                    String strOfID = scan.next();
                    System.out.println("请输入姓名：");
                    String name = scan.next();
                    System.out.println("请输入成绩：");
                    double grade=0.0;
                    while(true)
                    {
                        try{
                            grade = scan.nextDouble();
                            break;
                        }
                        catch (InputMismatchException e)
                        {
                            System.out.println("您输入了错误的成绩数据，请重新输入！");
                        }
                    }
                    student.setStudent(strOfID,name,grade);
                    System.out.println("修改成功！");
                    printAllStudent();
                }
                else
                {
                    System.out.println("输入错误！");
                }
            }
            else if(choose == 2)
            {
                printAllStudent();
                System.out.println("请输入您要修改的学生姓名：");
                String formerName = scan.next();

                ArrayList<Student> arrayOfStudents=new ArrayList<Student>();
                for (Student student:studentArrayList) {
                    if (formerName.equals(student.getName()))
                    {
                        arrayOfStudents.add(student);
                    }
                }
                System.out.println("以下是查询到的符合该姓名的全部学生：");
                Student student=null;
                for (int i = 0; i <arrayOfStudents.size() ; i++) {
                    student=arrayOfStudents.get(i);
                    System.out.println("序号 "+(i+1)+" ID"+student.getStudentID()+" 姓名 "+student.getName()+" 成绩 "+student.getGrade());
                }
                System.out.println("请问你想修改哪一个？（请输入对应序号）");
               //此处记得写上异常处理语句
                int id=scan.nextInt();
                student=arrayOfStudents.get(id-1);

                System.out.println("请输入新的学生ID：");
                String stuID=scan.next();
                System.out.println("请输入新的学生姓名：");
                String name=scan.next();
                System.out.println("请输入新的学生成绩：");
                double grade=scan.nextDouble();
                student.setStudent(stuID,name,grade);
                System.out.println("修改成功！");
                System.out.println("修改后，该学生信息为："+student);
                System.out.println("修改后全体学生信息：");
                printAllStudent();

            }


            else if(choose == 3)
            {
                printMenu();
                break;
            }
            else
            {
                System.out.println("输入非法！");
            }
        }
    }

    void findStudent()
    {
        Scanner scan = new Scanner(System.in);
        while(true)
        {
            System.out.println("请输入按哪种方法查找图书：1、ID/2、姓名/3、返回主菜单");
            int choose = scan.nextInt();
            if(choose == 1)
            {
                /*System.out.println("请输入要查找第几本书：");
                int number = scan.nextInt();
                int id = orderFind(number);
                if(id > -1)
                {
                    Book book =(Book)booklist.get(id);
                    System.out.println("你要查找的书名为："+book.getBookname()+" 作者："+book.getAuthor()+" 单价："+book.getPrice()+"元/本");
                }
                else
                {
                    System.out.println("输入错误！");
                }*/
                System.out.println("请输入要查找学生的ID：");
                String studentID=scan.next();
                Student studentObject=null;
                for (Student student:studentArrayList) {
                    if (studentID.equals(student.getStudentID()))
                    {
                        studentObject=student;
                        break;
                    }
                }
                if (studentObject==null)
                {
                    System.out.println("对不起，未找到相应学生信息..");
                }else{
                    System.out.println(studentArrayList.get(studentArrayList.indexOf(studentObject)));
                }


            }
            else if(choose == 2)
            {
                /*System.out.println("请输入您要查找的：");
                String name = scan.next();
                int id = nameFind(name);
                if(id > -1)
                {
                    Book book=(Book)booklist.get(id);
//                    System.out.println("查找成功，您查找到的书为第"+(id+1)+"本书！"+"书名为："+booklist[id].getBookname()+" 作者："+booklist[id].getAuthor()+" 单价："+booklist[id].getPrice()+"元/本");
                    System.out.println("查找成功，您查找到的书为第"+(id+1)+"本书！"+"书名为："+book.getBookname()+" 作者："+book.getAuthor()+" 单价："+book.getPrice()+"元/本");
                }*/

                System.out.println("请输入要修改学生的姓名：");
                String name=scan.next();
                Student studentObject=null;
                for (Student student:studentArrayList) {
                    if (name.equals(student.getName()))
                    {
                        studentObject=student;
                        break;
                    }
                }
                if (studentObject==null)
                {
                    System.out.println("对不起，未找到相应学生信息..");
                }else{
                    System.out.println(studentArrayList.get(studentArrayList.indexOf(studentObject)));
                }
            }
            else if(choose == 3)
            {
                printMenu();
                break;
            }
            else
            {
                System.out.println("输入非法！");
            }
        }
    }

    void printAllStudent() //循环打印所有的学生信息
    {
        Student student=null;
        for (int i = 0; i <studentArrayList.size() ; i++) {
            student=studentArrayList.get(i);
            System.out.println("序号 "+(i+1)+" ID"+student.getStudentID()+" 姓名 "+student.getName()+" 成绩 "+student.getGrade());
        }
    }

    int orderFind(int number)	//按序号查找，返回id
    {
        //System.out.println(number);
        if(number <= count)
        {
            int id = number - 1;
            return id;
        }
        else
            return -1;
    }



    public static void main(String[] args) {

        new MainClass();
    }

}

