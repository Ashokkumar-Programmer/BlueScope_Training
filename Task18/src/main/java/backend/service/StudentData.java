package backend.service;

import java.util.List;
import java.util.Map;

public class StudentData {
    private List<Student> students;
    
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
	public String toString() {
		return "StudentData [students=" + students + "]";
	}


	public static class Student {
        private int id;
        private String name;
        private int age;
        private String gender;
        private List<String> subjects;
        private Address address;
        private Map<String, Integer> grades;
        private boolean graduated;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public List<String> getSubjects() {
            return subjects;
        }

        public void setSubjects(List<String> subjects) {
            this.subjects = subjects;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Map<String, Integer> getGrades() {
            return grades;
        }

        public void setGrades(Map<String, Integer> grades) {
            this.grades = grades;
        }

        public boolean isGraduated() {
            return graduated;
        }

        public void setGraduated(boolean graduated) {
            this.graduated = graduated;
        }

		@Override
		public String toString() {
			return "Student [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + ", subjects="
					+ subjects + ", address=" + address + ", grades=" + grades + ", graduated=" + graduated + "]";
		}
        
        
    }

    public static class Address {
        private String city;
        private String zip;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getZip() {
            return zip;
        }

        public void setZip(String zip) {
            this.zip = zip;
        }

		@Override
		public String toString() {
			return "Address [city=" + city + ", zip=" + zip + "]";
		}
        
    }
}
