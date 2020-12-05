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
	 * 
	 * @param inFile is the name of the input file.
	 * @param outFile is the name of the output file.
	 */
	public Transcript(String inFile, String outFile) {
		inputFile = new File(inFile);
		outputFile = outFile;	
		grade = new ArrayList<Object>();
		this.readFile();
		
	}// end of Transcript constructor
	
	/**
	 * This is a helper function which generates the list of assessments to be plugged in and copied in the course instance when
	 * being created.
	 * 
	 * @param line
	 * 		Parsed version of the entry input (grade field of transcript class)
	 * @return
	 * 		Returns an ArrayList type Assessment to be used for the instantiation of Course
	 */
	private ArrayList<Assessment> assessmentHelper(String[] line) {
		// Assessments
		ArrayList<Assessment> assessments = new ArrayList<>();
		for(int i = 3; i < line.length - 1; i++) {
			
			int weight = Integer.parseInt(line[i].substring(1, line[i].indexOf('(')));
			char typeOfAssess = line[i].charAt(0);
			Assessment indAssessment = Assessment.getInstance(typeOfAssess, weight);
			assessments.add(indAssessment);
			
		}
		return assessments;
	}
	
	/**
	 * This is a helper function which counts the students and goes over grade (field of transcript), in order to 
	 * recognize and classify students without any replication based on the student numbers provided in the input.
	 * 
	 * @param field
	 * 			The field of transcript class which contains the input/text file in form of an object
	 * @return
	 * 		Returns an ArrayList type Integer containing the student numbers of the students with no replicas
	 */
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
	/**
	 * This is a helper function which returns the grades that the student received for the course which
	 * the entry is via input/line of input. The list returned is parallel with the list containing weights.
	 * 
	 * @param entryDel
	 * 			Entry from the input, an individual line from the field named grade in Transcript class
	 * @return
	 * 		Returns an ArrayList type Double which carries the values/marks received from the assessments
	 */		
	private ArrayList<Double> gradeReturner(String[] entryDel){
		ArrayList<Double> grades = new ArrayList<>();
		for(int i = 3; i < entryDel.length - 1; i++) {
			double grade = Double.parseDouble(entryDel[i].substring(entryDel[i].indexOf('(') + 1, entryDel[i].indexOf(')')));
			grades.add(grade);
		}
		return grades;
	}
	
	/**
	 * This is a helper function which takes in a String array which contains individual assessments, the name of the course and
	 * the information about the student whom this entry is about. That input is parsed in order to return the weights
	 * of the assessments 
	 * This list is also parallel to the list containing the grades 
	 * 
	 * @param entryDel
	 * 			Entry from the input, an individual line from the field named grade in Transcript class
	 * @return
	 * 		Returns an ArrayList type Integer which carries the weights of the assessments
	 */
	private ArrayList<Integer> weightReturner(String[] entryDel){
		ArrayList<Integer> weights = new ArrayList<>();
		
		for(int i = 3; i < entryDel.length - 1; i++) {
			int weight = Integer.parseInt(entryDel[i].substring(1, entryDel[i].indexOf('(')));
			weights.add(weight);
		}
		return weights;
	}
	
	
	/**
	 * Initializes assessments, courses and students based using the grade field which contains
	 * inputs in the form of lines for each course entry for a student. This method builds students
	 * and the courses that they have taken with all the assessment entries and returns an ArrayList 
	 * of the students instantiated during the process
	 * 
	 * @return
	 * 		Returns an ArrayList of type Student that contains all the students with their course entries from 
	 * 		the text file input
	 */
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
					tempStu.addCourse(new Course(courseName, assessmentHelper(entryDel), weightOfCourse));
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
	
	/**
	 * Forms a string/text representation of a report -of all the students 
	 * and the courses they have taken with all their assignments and their GPA's as well
	 * as their individual average for that course.
	 * 
	 * @param students
	 * 			ArrayList of students formed with respect to the input
	 * @throws InvalidTotalException
	 * 			Throws an exception if one of the students has an average or the course evaluation 
	 * 			has an error due to possibly being above the maximum limit of what a student is 
	 * 			statistically able to achieve. This exception is thrown due to the weightedGPA()
	 */
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
	
	/**
	 * Initializes a Student type object by also initializing empty ArrayList
	 * fields
	 */
	public Student() {
		this.courseTaken = new ArrayList<>();
		this.finalGrade = new ArrayList<>();
	}
	
	/**
	 * Initializes a Student type object using the parameters name, studentID and the courses
	 * that the student has taken which is in the form of an ArrayList
	 * 
	 * @param name
	 * 			the name of the student
	 * @param studentID
	 * 			the studentID of the student
	 * @param courses
	 * 			the courses that the student is currently taking
	 * 
	 */
	public Student(String name, String studentID, ArrayList<Course> courses) {
		this.name = name;
		this.studentID = studentID;
		this.courseTaken = new ArrayList<>();
		for(Course course : courses) {
			this.courseTaken.add(new Course(course));
		}
	}	
	
	/**
	 *	Calculates the total average of the assessments based on the weights
	 * and the values of them and adds the final evaluated grade into the finalGrade
	 * ArrayList
	 * 
	 * @param grades
	 * 				grades of the assessments of a course
	 * 			
	 * @param weights
	 * 				weights of the assessments of a course
	 */				
	
	public void addGrade(ArrayList<Double> grades, ArrayList<Integer> weights) {
		double sum = 0; 
		
		for(int i = 0; i < grades.size(); i++) {sum = sum + grades.get(i) * ((double) weights.get(i)/100);}
		
		double temp = Math.round(sum*10);
		double finalValueOfSum = temp/10;

		finalGrade.add(finalValueOfSum);
		
	}
	
	/**
	 * 	Private helper method that evaluates the students GPA based on York University's
	 * standards
	 * 
	 * @param mark
	 * 		The grade that the student has out of 100
	 * @return
	 * 		Returns the GPA representation based on York University's standards which is out of 9
	 * @throws InvalidTotalException
	 * 		Throws an exception if the total mark is more than 100
	 */
	
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
	
	/**
	 * 
	 * 
	 * @return
	 * 		returns the final GPA the student has based on the courses
	 * 		the individual has taken
	 * @throws InvalidTotalException
	 * 		throws exception due to yorkGPA(double mark) method, yorkGPA throws 
	 * 		an exception if the student final grade is more than 100	
	 */
	public double weightedGPA(){

		int i = 0;
		double credits = 0;
		double sumOfGrades = 0;
		for(Course course : this.courseTaken) {
			double credit = course.getCredit();
			credits = credits + credit;
			double finalGradeOfStudent = this.finalGrade.get(i);
			try {
				double GPA = yorkGPA(finalGradeOfStudent);
				sumOfGrades = sumOfGrades + GPA * credit;
			}catch(InvalidTotalException e) {
				System.out.println("Invalid Total");
			}
			
			i++;
		}
		double finalGPAunaltered = sumOfGrades / credits;
		double temp = Math.round(finalGPAunaltered*10);
		double finalGPA = temp/10;
		
		return finalGPA;
	}

	/**
	 * Takes in a course object as a parameter and creates a new copy of that
	 * course and adds that to the courseTaken field which are the courses that the
	 * student has taken
	 * 
	 * @param course
	 * 		The course that the student is taking
	 */
	public void addCourse(Course course){
		courseTaken.add(new Course(course));
	}
	
	/**
	 * Sets the student's name
	 * 
	 * @param name
	 * 		Name of the student
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Sets the student's student ID
	 * 
	 * @param studentNumber
	 * 		The student ID reference for the student
	 */
	public void setStudentNo(int studentNumber) {
		this.studentID = Integer.toString(studentNumber); 
	}
	
	/**
	 * Return the finalGrade ArrayList which are the final grades the student
	 * has received
	 * 
	 * @return
	 * 		Returns the final grades of the student parallel to the courses
	 * 	   the student has taken
	 */
	public ArrayList<Double> getfinalGrade(){
		ArrayList<Double> temp = new ArrayList<>();
		for(Double tempVal : this.finalGrade) {
			temp.add(tempVal);
		}
		return temp;
	}
	
	
	/**
	 * Returns the courses the student has taken, the returned value is a 
	 * new ArrayList of type Course that has copies of the Course objects 
	 * rather than the course itself for data leak
	 * 
	 * @return
	 * 		Returns an ArrayList of courses that the student has taken
	 */
	public ArrayList<Course> getCourseTaken(){
		ArrayList<Course> temp = new ArrayList<>();
		for(Course course : this.courseTaken) {
			temp.add(new Course(course));
		}
		return temp;
	}
	
	/**
	 * Getter for the Student Name
	 * 
	 * @return
	 * 		Returns the name of the student
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Getter for the Student Number
	 * 
	 * @return
	 * 		Returns the student Number of the student
	 */		
	public String getStudentNo() {
		return this.studentID;
	}
	
	
}

//end of Class
class Course{
	private String code;
	private ArrayList<Assessment> assessments;
	private double credit;
	
	/**
	 * Initializes an empty course with an empty ArrayList for assessments
	 */
	public Course() {
		this.assessments = new ArrayList<>(); 
	}
		
	/**
	 * Initializes a Course instance where the course name/code is instantiated, the assessments are individually copied and 
	 * put in a new ArrayList Assessment type, the credit is defined for the instance at last
	 * 
	 * @param code
	 * 			The name/code of the course
	 * @param assessments
	 * 			ArrayList type Assessment which carries all the assessments of the instantiated course
	 * @param credit
	 * 			The credit of the course
	 */
	public Course(String code, ArrayList<Assessment> assessments, double credit) {
		this.code = code;
		ArrayList<Assessment> temp = new ArrayList<>();
		
		for(Assessment assessment : assessments) {
			temp.add(Assessment.getInstance(assessment.getType(), assessment.getWeight()));
		}
		
		this.assessments = temp;
		this.credit = credit;
		
	}
	/**
	 * The copy constructor of Course, it created a new ArrayList and new Assessment objects for each
	 * assessment in the assessments field of the instance that is wanted to be copied
	 * 
	 * @param course
	 * 			The course instance that is wanted to be copied
	 */
	public Course(Course course) {
		this.assessments = new ArrayList<Assessment>();
		for(Assessment assessment : course.assessments) {
			this.assessments.add(Assessment.getInstance(assessment.getType(), assessment.getWeight()));
		}
		this.code = course.code;
		this.credit = course.credit;
	}
	
	/**
	 * Getter for assessments of this instance (Course)
	 * 
	 * @return
	 *		Returns a new ArrayList assessment type with the Assessments being individually copied inside
	 */		
	public ArrayList<Assessment> getAssessments(){
		ArrayList<Assessment> temp = new ArrayList<>();
		for(Assessment assessment : this.assessments) {
			temp.add(Assessment.getInstance(assessment.getType(), assessment.getWeight()));
		}
		return temp; 
	}
	
	/**
	 * Getter method for name/code of the Course
	 * 
	 * @return
	 * 		Returns the name/code of the Course
	 */
	public String getCode() {
		return this.code;
	}
	
	/**
	 * Getter for Credit of the Course
	 * 
	 * @return
	 * 		Return the credit of the Course
	 */
	public double getCredit() {
		return this.credit;
	}
	
}//end of Course Class


class Assessment{
	private char type;
	private int weight;
	
	private Assessment() {
	
	}
	/**
	 * Instantiates an Assessment object which only carries the type of it and the
	 * weight of that assessment
	 * 
	 * @param type
	 * 			The type of assessment, practical or exam
	 * @param weight
	 * 			The weight of the assessment with respect to total possible grade for a course instance
	 */
	private Assessment(char type, int weight) {
		this.type = type;
		this.weight = type;
	}

	/**
	 * The public static factory method for Assessment, the actual Constructor is hidden using private but 
	 * this method can be used to instantiate a new instance/object of type Assessment
	 * 
	 * @param type
	 * 			The type of assessment, practical or exam
	 * @param weight
	 * 			The weight of the assessment with respect to total possible grade for a course instance
	 * @return
	 * 		Returns an instance of Assessment, an Assessment object
	 */
	public static Assessment getInstance(char type, int weight) {
		return new Assessment(type, weight);
	}
	
	/**
	 * Getter method for type
	 * 
	 * @return
	 * 		Returns the type of this Assessment
	 */
	public char getType() {
		return this.type;
	}
	
	/**
	 * Getter method for Weight
	 * 
	 * @return
	 * 		Returns the weight for this Assessment
	 */
	public int getWeight() {
		return this.weight;
	}
	
	/**
	 * Indicates whether some other object is "equal to" this one.
	 * @return
	 * 	 	The conditions for the object to be !!different!! are as follows:
	 * 			if this != argument or,
	 * 			if argument != null a
	 * 			if argument's class != instance of this
	 * 			if the fields of this != fields of argument
	 * 		If these conditions fail, this method will return true, if not will return false
	 * 
	 */
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


	

