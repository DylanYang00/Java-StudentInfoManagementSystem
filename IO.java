package tool;

import model.Student;
import ui.MainClass;

import javax.swing.filechooser.FileSystemView;
import java.io.*;

/**
 * 默认将IO的txt文件存储到桌面上，并从桌面读取
 */
public class IO {
    public void load(MainClass mainClass)//读取文件
    {
        try {
            //获取桌面路径
            File homeDirectory = FileSystemView.getFileSystemView().getHomeDirectory();
            String path=homeDirectory.getAbsolutePath();
            File file=new File(path+File.pathSeparator+"studentData.txt");
            BufferedReader reader=new BufferedReader(new FileReader(file));
            String temp=null;
            while ((temp=reader.readLine())!=null)
            {
                String[] strArray=temp.split(",");
                String studentID=strArray[0];
                String name=strArray[1];
                double grade=Double.parseDouble(strArray[2]);
                Student student=new Student(studentID,name,grade);
                mainClass.studentArrayList.add(student);
                mainClass.count++;
            }
            reader.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("注意：");
            System.out.println("尚未创建相应的数据保存文件，将在第一次运行后创建"+System.lineSeparator());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
        }

    }
    public void save(MainClass mainClass)//写入文件
    {
        //得到桌面路径
        File homeDirectory = FileSystemView.getFileSystemView().getHomeDirectory();

        String path=homeDirectory.getAbsolutePath();

        File file=new File(path+File.pathSeparator+"studentData.txt");
        StringBuffer stringBuffer=new StringBuffer();
        for (int i = 0; i <mainClass.studentArrayList.size() ; i++) {
            Student student=(Student) mainClass.studentArrayList.get(i);
            stringBuffer.append(student.getStudentID()+","
                    +student.getName()+","
                    +student.getGrade()
                    +System.lineSeparator());
        }
        try {
            FileWriter fileWriter=new FileWriter(file);
            fileWriter.write(stringBuffer.toString());
            fileWriter.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
