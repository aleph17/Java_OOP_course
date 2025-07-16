package university;
import java.util.logging.Logger;

/**
 * This class represents a university education system.
 * 
 * It manages students and courses.
 *
 */
public class University {

// R1
	private String uniName;
	private String rectorName;
	private String rectorSurname;
	private Student[] students = new Student[1000];
	private int occ=0;
	private final static int START = 10000;
	private int courseOcc = 0;
	private Course[] courses = new Course[50];

	public University(){};
	public University(String name){
		this.uniName = name;
	}

	public String getName(){
		return this.uniName;
	}
	
	/**
	 * Defines the rector for the university
	 * 
	 * @param first first name of the rector
	 * @param last	last name of the rector
	 */
	public void setRector(String first, String last){
		this.rectorName = first;
		this.rectorSurname = last;
	}
	
	/**
	 * Retrieves the rector of the university with the format "First Last"
	 * 
	 * @return name of the rector
	 */
	public String getRector(){
		return rectorName+" "+rectorSurname;
	}
	
// R2
	/**
	 * Enrol a student in the university
	 * The university assigns ID numbers 
	 * progressively from number 10000.
	 * 
	 * @param first first name of the student
	 * @param last last name of the student
	 * 
	 * @return unique ID of the newly enrolled student
	 */
	public int enroll(String first, String last){
		Student newStudent = new Student();
		newStudent.studName = first;
		newStudent.studSurname = last;
		newStudent.id = START+occ;
		students[occ++] = newStudent;
		logger.info("New student enrolled: " + newStudent.id + ", " + first + " " + last );
		return newStudent.id;
	}
	
	/**
	 * Retrieves the information for a given student.
	 * The university assigns IDs progressively starting from 10000
	 * 
	 * @param id the ID of the student
	 * 
	 * @return information about the student
	 */
	public String student(int id){
			return id+" "+students[id-START].studName+" "+students[id-START].studSurname;
	}
	
// R3
	/**
	 * Activates a new course with the given teacher
	 * Course codes are assigned progressively starting from 10.
	 * 
	 * @param title title of the course
	 * @param teacher name of the teacher
	 * 
	 * @return the unique code assigned to the course
	 */
	public int activate(String title, String teacher){
		Course newCourse = new Course();
		newCourse.title = title;
		newCourse.professor = teacher;
		newCourse.code = 10+courseOcc;
		courses[courseOcc++] = newCourse;
		logger.info("New course activated: " + newCourse.code + ", " + title + " " + teacher );
		return newCourse.code;
	}
	
	/**
	 * Retrieve the information for a given course.
	 * 
	 * The course information is formatted as a string containing 
	 * code, title, and teacher separated by commas, 
	 * e.g., {@code "10,Object Oriented Programming,James Gosling"}.
	 * 
	 * @param code unique code of the course
	 * 
	 * @return information about the course
	 */
	public String course(int code){
		return code+","+courses[code-10].title+","+courses[code-10].professor;
	}
	
// R4
	/**
	 * Register a student to attend a course
	 * @param studentID id of the student
	 * @param courseCode id of the course
	 */
	public void register(int studentID, int courseCode){
		// assuming studentID and courseCode are consistent
		//adding the student to course plan
		int i = this.courses[courseCode-10].attOcc;
		this.courses[courseCode-10].attendants[i++] = this.students[studentID-START];
		this.courses[courseCode-10].attOcc++;
		//adding the course to student plan
		int k = this.students[studentID-START].attOcc;
		this.students[studentID-START].attending[k++] = this.courses[courseCode-10];
		this.students[studentID-START].attOcc++;

		logger.info("Student " + studentID + " signed up for course " + courseCode);
	}
	
	/**
	 * Retrieve a list of attendees.
	 * 
	 * The students appear one per row (rows end with `'\n'`) 
	 * and each row is formatted as describe in in method {@link #student}
	 * 
	 * @param courseCode unique id of the course
	 * @return list of attendees separated by "\n"
	 */
	public String listAttendees(int courseCode){
		StringBuffer result = new StringBuffer("");
		for (int i=0; i< this.courses[courseCode-10].attOcc;i++){
			Student s = this.courses[courseCode-10].attendants[i];
			 result.append(s.id+" "+s.studName+" "+s.studSurname+"\n");
		}
		return result.toString();
	}

	/**
	 * Retrieves the study plan for a student.
	 * 
	 * The study plan is reported as a string having
	 * one course per line (i.e. separated by '\n').
	 * The courses are formatted as describe in method {@link #course}
	 * 
	 * @param studentID id of the student
	 * 
	 * @return the list of courses the student is registered for
	 */
	public String studyPlan(int studentID){
		StringBuffer result = new StringBuffer("");
		for(int i=0;i<this.students[studentID-START].attOcc; i++){
			Course c = this.students[studentID-START].attending[i];
			result.append(c.code+","+c.title+","+c.professor+"\n");
		}
		return result.toString();
	}

// R5
	/**
	 * records the grade (integer 0-30) for an exam can 
	 * 
	 * @param studentId the ID of the student
	 * @param courseID	course code 
	 * @param grade		grade ( 0-30)
	 */
	public void exam(int studentId, int courseID, int grade) {
		this.students[studentId-START].takenExams[students[studentId-START].examOcc++]
				= new Exam(this.courses[courseID-10], grade);
		logger.info("ERROR: student " + studentId + " not enrolled in course " + courseID + ": cannot assign a grade.");
	}

	/**
	 * Computes the average grade for a student and formats it as a string
	 * using the following format 
	 * 
	 * {@code "Student STUDENT_ID : AVG_GRADE"}. 
	 * 
	 * If the student has no exam recorded the method
	 * returns {@code "Student STUDENT_ID hasn't taken any exams"}.
	 * 
	 * @param studentId the ID of the student
	 * @return the average grade formatted as a string.
	 */
	public String studentAvg(int studentId) {
		Student s= this.students[studentId-START];
		if(s.examOcc==0)
			return "Student "+ studentId+" hasn't taken any exams";
		else{
			int sum = 0;
			for(int i=0;i<s.examOcc;i++){
				sum+=s.takenExams[i].grade;
			}
			return "Student "+studentId+" : "+(double)(sum/s.examOcc);
		}
	}
	
	/**
	 * Computes the average grades of all students that took the exam for a given course.
	 * 
	 * The format is the following: 
	 * {@code "The average for the course COURSE_TITLE is: COURSE_AVG"}.
	 * 
	 * If no student took the exam for that course it returns {@code "No student has taken the exam in COURSE_TITLE"}.
	 * 
	 * @param courseId	course code 
	 * @return the course average formatted as a string
	 */
	public String courseAvg(int courseId) {
		int sum = 0;
		int count =0;
		Student[] active = this.courses[courseId-10].attendants;
		for(Student s:active){
			if (s!= null){
				for(Exam e:s.takenExams){
					if(e!=null){
						if(e.course.equals(this.courses[courseId-10])){
							sum+=e.grade;
							count++;
						}
					}
				}
			}
		}
		if(count==0)
			return "No student has taken the exam in "+courses[courseId-10].title;
		else{
			return "The average for the course "+courses[courseId-10].title+" is: "+sum/count;
		}
	}
	

// R6
	/**
	 * Retrieve information for the best students to award a price.
	 * 
	 * The students' score is evaluated as the average grade of the exams they've taken. 
	 * To take into account the number of exams taken and not only the grades, 
	 * a special bonus is assigned on top of the average grade: 
	 * the number of taken exams divided by the number of courses the student is enrolled to, multiplied by 10.
	 * The bonus is added to the exam average to compute the student score.
	 * 
	 * The method returns a string with the information about the three students with the highest score. 
	 * The students appear one per row (rows are terminated by a new-line character {@code '\n'}) 
	 * and each one of them is formatted as: {@code "STUDENT_FIRSTNAME STUDENT_LASTNAME : SCORE"}.
	 * 
	 * @return info on the best three students.
	 */
	public String topThreeStudents() {
		Student[] top = new Student[3];
		for(Student s: this.students) {
			if (s != null) {
				if (s.examOcc != 0 && s.attOcc != 0) {
					int sum = 0;
					for (int i = 0; i < s.examOcc; i++) {
						sum += s.takenExams[i].grade;}
					s.points = (int)((sum/ s.examOcc)+ (s.examOcc/s.attOcc)*10);
				}
			}
		}
		for(Student s: this.students){
			if(s!=null)
				insert(s, top);
		}

		StringBuffer result = new StringBuffer();

		for (Student s: top){
			if(s!=null && s.points!=0){
				result.append(s.studName).append(" ").append(s.studSurname).append(" : ").append(s.points).append("\n");
			}
		}
		return result.toString();
	}
	public void insert(Student s, Student[] top){
		for(int i=0;i<top.length;i++){
			if(top[i]==null || s.points>top[i].points){
				for(int j=top.length-1;j>i;j--){
					top[j]=top[j-1];
				}
				top[i]=s;
				break;
			}
		}
	}

// R7
    /**
     * This field points to the logger for the class that can be used
     * throughout the methods to log the activities.
     */
    public static final Logger logger = Logger.getLogger("University");

}
