package homeworks;

import java.util.Scanner;

import classes.Student;

public class Homework01 {
   public static int getValidNum(String ui) {
      Scanner sc = new Scanner(System.in);
      while(true) {
         try {
            System.out.print(ui);
            return Integer.parseInt(sc.nextLine());
         }catch(Exception e) {
            System.out.println("숫자를 입력해야 합니다.");
         }
      }
   }
   
   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      Student[] stds = new Student[10];
      int index = 0;

      while(true) {
         System.out.println("<< 학생 관리 시스템 >>");
         System.out.println("1. 신규 정보 등록"); // 이름 국어 영어
         System.out.println("2. 학생 목록 출력");
         System.out.println("3. 프로그램 종료");
         int menu = getValidNum(">> ");

         if(menu == 1) {
            System.out.print("이 름 : ");
            String name = sc.nextLine();

            int kor = getValidNum("국 어 : ");
            int eng = getValidNum("영 어 : ");

            Student std = new Student(name,kor,eng);
            stds[index++] = std;

         }else if(menu == 2) {
            System.out.println("이름\t국어\t영어\t합계\t평균");

            if(index == 0) {
               System.out.println("출력할 내용이 없습니다.");
            }else {
               for(int i = 0;i < index;i++) {
                  System.out.println(stds[i].getName()+"\t"+
                        stds[i].getKor()+"\t"+
                        stds[i].getEng()+"\t"+
                        stds[i].getSum()+"\t"+
                        stds[i].getAvg());
               }
            }
         }else if(menu == 3) {
            System.out.println("프로그램을 종료합니다.");
            System.exit(0);
         }else {
            System.out.println("메뉴를 다시 확인해주세요.");
         }
      }


   }
}