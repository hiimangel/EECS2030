package Assignment2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.io.*;


/* PLEASE DO NOT MODIFY A SINGLE STATEMENT IN THE TEXT BELOW.
READ THE FOLLOWING CAREFULLY AND FILL IN THE GAPS

I hereby declare that all the work that was required to 
solve the following problem including designing the algorithms
and writing the code below, is solely my own and that I received
no help in creating this solution and I have not discussed my solution 
with anybody. I affirm that I have read and understood
the Senate Policy on Academic honesty at 
https://secretariat-policies.info.yorku.ca/policies/academic-honesty-senate-policy-on/
and I am well aware of the seriousness of the matter and the penalties that I will face as a 
result of committing plagiarism in this assignment.

BY FILLING THE GAPS,YOU ARE SIGNING THE ABOVE STATEMENTS.

Full Name: Alp Baran Sirek
Student Number: 271329251
Course Section: Section E
*/


/**
* This class generates a transcript for each student, whose information is in the text file.
* 
*
*/

public class Transcript {
	public ArrayList<Object> grade = new ArrayList<Object>(); // the ArrayList holding the text file
	private File inputFile;
	private String outputFile;

	
	/**
	 * This the the constructor for Transcript class that 
	 * initializes its instance variables and call readFie private
	 * method to read the file and construct this.grade.
	 * @param inFile is the name of the input file.
	 * @param outFile is the name of the output file.
	 */
	public Transcript(String inFile, String outFile) {
		inputFile = new File(inFile);
		outputFile = outFile;	
		grade = new ArrayList<Object>();
		this.readFile();
		
	}// end of Transcript constructor
	
	private ArrayList<Assessment> assessmentHelper(String[] line) {
		// Assessments
		ArrayList<Assessment> assessments = new ArrayList<>();
		for(int i = 3; i < line.length - 1; i++) {
			// individual assessments is ---entryDel[i]---
			//System.out.println(line[i]);
			
			int weight = Integer.parseInt(line[i].substring(1, line[i].indexOf('(')));
			char typeOfAssess = line[i].charAt(0);
			Assessment indAssessment = Assessment.getInstance(typeOfAssess, weight);
			assessments.add(indAssessment);
			
		}
		return assessments;
	}
	
	
	private ArrayList<Integer> countStudents(ArrayList<Object> field) {
		
		ArrayList<Integer> studentNo = new ArrayList<>();
		for(Object temp : this.grade) {
			String entry = (String) temp;
			String[] entryDel = entry.split(",");
			int studentNumber = Integer.parseInt(entryDel[2]);
			
			if(!studentNo.contains(studentNumber)) {
				studentNo.add(studentNumber);
			}
		}
		return studentNo;
	}
	
	private ArrayList<Double> gradeReturner(String[] entryDel){
		ArrayList<Double> grades = new ArrayList<>();
		for(int i = 3; i < entryDel.length - 1; i++) {
			double grade = Double.parseDouble(entryDel[i].substring(entryDel[i].indexOf('(') + 1, entryDel[i].indexOf(')')));
			grades.add(grade);
		}
		return grades;
	}
	
	private ArrayList<Integer> weightReturner(String[] entryDel){
		ArrayList<Integer> weights = new ArrayList<>();
		
		for(int i = 3; i < entryDel.length - 1; i++) {
			int weight = Integer.parseInt(entryDel[i].substring(1, entryDel[i].indexOf('(')));
			weights.add(weight);
		}
		return weights;
	}
	
	
	
	public ArrayList<Student> buildStudentArray(){
		ArrayList<Student> students = new ArrayList<>();
		
		
		// FIRST FOR LOOP
		for(Integer stuNo : countStudents(grade)) {
			Student tempStu = new Student();
			tempStu.setStudentNo(stuNo);
			
			
			// SECOND FOR LOOP
			for(Object temp : grade) {
				
				String entry = (String) temp;
				String[] entryDel= entry.split(",");
				int studentNumber = Integer.parseInt(entryDel[2]);
				String courseName = entryDel[0];
				int weightOfCourse = Integer.parseInt(entryDel[1]);
				String name = entryDel[entryDel.length - 1];
				
				if(studentNumber == stuNo) {
					// Create course and add the course into the temporary Student
					Course course = new Course(courseName, assessmentHelper(entryDel), weightOfCourse);
					tempStu.addCourse(course);
					// set the name for the student, it will be repetitive which if there is an issue,
					// it will print the same name for different students //<--SELF NOTE--
					tempStu.setName(name);
					//add grade
					ArrayList<Double> grades = gradeReturner(entryDel);
					ArrayList<Integer> weights = weightReturner(entryDel);
					tempStu.addGrade(grades, weights);
				}	
			}
			students.add(tempStu);
		}
		
		return students;
	}
	
	
	void printTranscript(ArrayList<Student> students) throws InvalidTotalException{
		for(Student student : students) {
			System.out.println(student.getName() + '\t' + student.getStudentNo());
			for(int i = 0; i < 20; i++) {
				System.out.print('-');
			}
			System.out.println();
			int k = 0;
			for(Course course : student.getCourseTaken()) {
				System.out.println(course.getCode() + '\t' + student.getfinalGrade().get(k));
				k++;
			}
			for(int i = 0; i < 20; i++) {
				System.out.print('-');
			}
			System.out.println();
			System.out.println("GPA: " + student.weightedGPA());
			System.out.println();
		}
		
		
	}
	
	public void Build() throws InvalidTotalException{
		printTranscript(buildStudentArray());
	}
	

	/** 
	 * This method reads a text file and add each line as 
	 * an entry of grade ArrayList.
	 * @exception It throws FileNotFoundException if the file is not found.
	 */
	private void readFile() {
		Scanner sc = null; 
		try {
			sc = new Scanner(inputFile);	
			while(sc.hasNextLine()){
				grade.add(sc.nextLine());
	        }      
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			sc.close();
		}		
	} // end of readFile

}

class Student{
	private String studentID;
	public String name;
	private ArrayList<Course> courseTaken;
	private ArrayList<Double> finalGrade;
	
	public Student() {
		this.courseTaken = new ArrayList<>();
		this.finalGrade = new ArrayList<>();
	}
	
	
	public Student(String name, String studentID, ArrayList<Course> courses) {
		this.name = name;
		this.studentID = studentID;
		this.courseTaken = new ArrayList<>(courses);
	}	
	
	public void addGrade(ArrayList<Double> grades, ArrayList<Integer> weights) {
		double sum = 0; 
		
		for(int i = 0; i < grades.size(); i++) {
			sum = sum + grades.get(i) * ((double)weights.get(i)/100);
		}
		//for(Course course : courseTaken) {
		//	System.out.println(course.getCode());
		//}
		// FIND A WAY TO TRANSLATE THIS LONG INTO DOUBLE FOR THE MEMORY KILL ME 
		double temp = Math.round(sum*10);
		double finalValueOfSum = temp/10;
		
		//System.out.println(finalValueOfSum);
		finalGrade.add(finalValueOfSum);
		
	}
	
	private double yorkGPA(double mark) throws InvalidTotalException {
		
		if(mark > 100) {
			throw new InvalidTotalException();
		}
		if(mark < 47) {return 0;}
		if(mark < 50) {return 1.0;}
		if(mark < 55) {return 2.0;}
		if(mark < 60) {return 3.0;}
		if(mark < 65) {return 4.0;}
		if(mark < 70) {return 5.0;}
		if(mark < 75) {return 6.0;}
		if(mark < 80) {return 7.0;}
		if(mark < 90) {return 8.0;}
		else{return 9.0;}
	}
	
	public double weightedGPA() throws InvalidTotalException{
		ArrayList<Double> finalYorkGpa = new ArrayList<>();
		int i = 0;
		double credits = 0;
		double sumOfGrades = 0;
		for(Course course : this.courseTaken) {
			double credit = course.getCredit();
			credits = credits + credit;
			double finalGradeOfStudent = this.finalGrade.get(i);
			double GPA = yorkGPA(finalGradeOfStudent);
			sumOfGrades = sumOfGrades + GPA * credit;
			i++;
		}
		
		double finalGPAunaltered = sumOfGrades / credits;
		double temp = Math.round(finalGPAunaltered*10);
		double finalGPA = temp/10;
		
		
		return finalGPA;
		
	}
	public void addCourse(Course course){
		courseTaken.add(course);
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setStudentNo(int studentNumber) {
		this.studentID = Integer.toString(studentNumber); 
	}
	public ArrayList<Double> getfinalGrade(){
		return this.finalGrade;
	}
	public ArrayList<Course> getCourseTaken(){
		return this.courseTaken;
	}
	public String getName() {
		return this.name;
	}
	public String getStudentNo() {
		return this.studentID;
	}
	
	
}

//end of Class
class Course{
	private String code;
	private ArrayList<Assessment> assessments;
	private double credit;
	
	public Course() {
		this.assessments = new ArrayList<>(); 
	}
		
	
	public Course(String code, ArrayList<Assessment> assessments, double credit) {
		this.code = code;
		this.assessments = new ArrayList<>(assessments);
		this.credit = credit;
		
	}
	
	public Course(Course course) {
		this.assessments = new ArrayList<Assessment>();
		for(Assessment assessment : course.assessments) {
			this.assessments.add(Assessment.getInstance(assessment.getType(), assessment.getWeight()));
		}
		this.code = course.code;
		this.credit = course.credit;
	}
	public ArrayList<Assessment> getAssessments(){
		return new ArrayList<Assessment>(assessments);
	}
	public String getCode() {
		return this.code;
	}
	public double getCredit() {
		return this.credit;
	}
}


class Assessment{
	private char type;
	private int weight;
	
	private Assessment() {
	
	}
	private Assessment(char type, int weight) {
		this.type = type;
		this.weight = type;
	}

	public static Assessment getInstance(char type, int weight) {
		return new Assessment(type, weight);

	}
	public char getType() {
		return this.type;
	}
	public int getWeight() {
		return this.weight;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null) {
			return false;
		}
		if(this.getClass() != obj.getClass()) {
			return false;
		}

		Assessment other = (Assessment) obj;
		
		if(other.getType() != this.type) {
			return false;
		}
		if(other.getWeight() != this.weight) {
			return false;
		}
		
		return true; 
	}
}//end of Assessment


class InvalidTotalException extends Exception{
	
	InvalidTotalException(){}
	
	InvalidTotalException(String message){
		System.out.println(message);
	}
	
}


 // end of Transcript


	

