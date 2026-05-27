import React, {useState, useEffect} from 'react';
import Form from 'next/form'

export default function Home() {
    const [students, setStudents] = useState([]);
    const [newStudent, setNewStudent] = useState({
        studentId: '',
        name: '',
        age: null,
        gender: '',
    });

    useEffect(async () => {
        try {
            const response = await fetch('http://localhost:8080/all');
            const data = await response.json();
            setStudents(data);
        } catch (error) {
            console.error('Error fetching students:', error);
        }
    }, []);

    const handleAddStudent = async (e) => {
        e.preventDefault();

        try {
            await fetch('http://localhost:8080/add', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(newStudent),
            });

            // Add new student to the local state
            setStudents([...students, newStudent]);
            setNewStudent({
                studentId: '',
                name: '',
                age: null,
                gender: ''
            });
        } catch (error) {
            console.error('Error adding student:', error);
        }
    };

    const handleChange = (field, value) => {
        setNewStudent(prevState => ({
            ...prevState,
            [field]: value
        }));
    };

    return (
        <div>
            <h1>Student Table</h1>
            {/* Form to add new student TODO update to Form*/}
            {/*https://nextjs.org/docs/app/api-reference/components/form*/}
            <form onSubmit={handleAddStudent}>
                <div>
                    <label htmlFor="studentId">Student ID</label>
                    <input
                        type="text"
                        id="studentId"
                        value={newStudent.studentId}
                        onChange={(e) => handleChange('studentId', e.target.value)}
                        placeholder="Enter student ID"
                    />
                </div>
                <div>
                    <label htmlFor="name">Name</label>
                    <input
                        type="text"
                        id="name"
                        value={newStudent.name}
                        onChange={(e) => handleChange('name', e.target.value)}
                        placeholder="Enter student name"
                    />
                </div>

                <div>
                    <label htmlFor="age">Age</label>
                    <input
                        type="number"
                        id="age"
                        value={newStudent.age}
                        onChange={(e) => handleChange('age', e.target.value)}
                        placeholder="Enter student age"
                    />
                </div>

                <div>
                    <label htmlFor="gender">Gender</label>
                    <select
                        id="gender"
                        value={newStudent.gender}
                        onChange={(e) => handleChange('gender', e.target.value)}
                    >
                        <option value="">Select gender</option>
                        <option value="Male">Male</option>
                        <option value="Female">Female</option>
                        <option value="Other">Other</option>
                    </select>
                </div>
                <button type="submit">
                    Add Student
                </button>
            </form>

            {/* Table to display students */}
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Age</th>
                    <th>Gender</th>
                    <th>Graduation Year</th>
                </tr>
                </thead>
                <tbody>
                {students.map((student) => (
                    <tr key={student.id}>
                        <td>{student.studentId}</td>
                        <td>{student.name}</td>
                        <td>{student.age}</td>
                        <td>{student.gender}</td>
                        <td>{student.graduationYear}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}
