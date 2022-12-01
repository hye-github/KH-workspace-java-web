import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	public static void printStudents(ArrayList<Student> stds) {
		System.out.println("학번\t이름\t국어\t영어\t수학\t합계\t평균");
		for(Student std : stds) {
			System.out.println(std.getId()+"\t"+
								std.getName()+"\t"+
								std.getKor()+"\t"+
								std.getEng()+"\t"+
								std.getMath()+"\t"+
								std.getSum() + "\t"+
								std.getAvg());
		}
	}
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Manager manager = new Manager();
		

		while(true) {
			System.out.println("<< 학생 관리 시스템 >>");
			System.out.println("1. 신규 정보 등록");
			System.out.println("2. 학생 목록 출력");
			System.out.println("3. 학생 정보 검색 (이름으로)");
			System.out.println("4. 학생 정보 삭제 (학번으로)");
			System.out.println("5. 학생 정보 수정 (학번으로)");
			System.out.println("6. 시스템 종료");
			System.out.print(">> ");
			int menu = Integer.parseInt(sc.nextLine());
			
			if(menu == 1) {
				manager.add(new Student(1001,"Jack",50,80,90));
				manager.add(new Student(1002,"Alice",40,50,100));
				manager.add(new Student(1003,"Tom",30,60,80));
				manager.add(new Student(1004,"Mike",55,70,95));
				manager.add(new Student(1005,"Susan",99,58,38));
			}else if(menu == 2) {
				ArrayList<Student> stds = manager.getStds();
				printStudents(stds);
				
			}else if(menu == 3) {
				System.out.print("검색할 학생의 이름 : ");
				String name = sc.nextLine();
				ArrayList<Student> result = manager.searchByName(name);
				
				if(result.size() == 0) {
					System.out.println(name + " 학생이 존재하지 않습니다.");
				}else {
					printStudents(result);
				}
			}else if(menu == 4) {
				System.out.print("삭제 할 학생의 학번 : ");
				int id = Integer.parseInt(sc.nextLine());
				
				manager.deleteById(id);
				
			}else if(menu == 5) {
				
				System.out.print("수정 할 학생의 학번 : ");
				int id = Integer.parseInt(sc.nextLine());
				
				System.out.print("국 어 : ");
				int kor = Integer.parseInt(sc.nextLine());
				
				System.out.print("영 어 : ");
				int eng = Integer.parseInt(sc.nextLine());
				
				System.out.print("수 학 : ");
				int math = Integer.parseInt(sc.nextLine());
				
				
				Student std = new Student(id, null, kor,eng,math);
				
				manager.updateById(std);
				
				
				
				
			}else if(menu == 6) {
				System.out.println("학생 관리 시스템을 종료합니다.");
				System.exit(0);
			}else {
				System.out.println("메뉴를 다시 확인해주세요.");
			}
		}
	}
}
