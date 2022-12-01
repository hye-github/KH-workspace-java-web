package homeworks;

import java.util.Scanner;

import classes.Movie;

public class Homework02 {

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
   
   public static double getValidDouble(String ui) {
      Scanner sc = new Scanner(System.in);
      while(true) {
         try {
            System.out.print(ui);
            return Double.parseDouble(sc.nextLine());
         }catch(Exception e) {
            System.out.println("숫자를 입력해야 합니다.");
         }
      }
   }
   

   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      Movie[] movies = new Movie[10];
      int index = 0;
      
      while(true) {
         System.out.println("<< Netflix 영화 프로그램 >>");
         System.out.println("1. 신규 영화 등록");
         System.out.println("2. 영화 목록 출력");
         System.out.println("3. 영화 검색 (제목)");
         System.out.println("4. 프로그램 종료");
         int menu = getValidNum(">> ");
         
         if(menu == 1) {
            
            System.out.print("영화 제목 : ");
            String title = sc.nextLine();
            
            System.out.print("영화 장르 : ");
            String genre = sc.nextLine();
            
            double score = getValidDouble("영화 평점 : ");
            
            Movie mov = new Movie(title,genre,score);
            movies[index++] = mov;
            
         }else if(menu == 2) {
            System.out.println("제목\t장르\t평점");
            for(int i = 0;i < index;i++) {
               System.out.println(movies[i].getTitle()+"\t"+
                              movies[i].getGenre()+"\t"+
                              movies[i].getScore());      
            }
         }else if(menu == 3) {
            System.out.print("검색할 영화의 제목 : ");
            String title = sc.nextLine();
            
            boolean notFound = true;
            for(int i = 0;i < index;i++) {
               if(movies[i].getTitle().equals(title)) {
                  System.out.println(movies[i].getTitle()+"\t"+
                        movies[i].getGenre()+"\t"+
                        movies[i].getScore());
                  notFound = false;
               }
            }
            if(notFound) {System.out.println("\"" + title + "\" 영화를 찾지 못했습니다.");}
            
            
         }else if(menu == 4) {
            System.out.println("프로그램을 종료합니다.");
            System.exit(0);
         }else {
            System.out.println("메뉴를 다시 확인해주세요.");
         }
      }
   }
}


