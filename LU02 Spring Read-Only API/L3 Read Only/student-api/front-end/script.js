const endpoint = 'http://localhost:8080/api/students';

const container = document.querySelector('#students');
const form = document.querySelector('form');

const fetchStudents = async () => {
    try {
        const response = await fetch(endpoint);
        const students = await response.json();
        console.log(students);
        renderStudents(students);
    } catch (error) {
        console.error('Error fetching students:', error);
    }
};

fetchStudents();

form.addEventListener('submit', async (event) => {
    event.preventDefault();
    let formData = new FormData(form);
    let name = formData.get('name');
    let id = parseInt(formData.get('id'));
    let students = [];

    if (name) {
        try {
            const response = await fetch(`${endpoint}/name/${name}`);
            students = await response.json();
        } catch (error) {
            console.error('Error fetching student by name:', error);
        }
    }

    if (!isNaN(id)) {
        try {
            const response = await fetch(`${endpoint}/${id}`);
            students = await response.json();
        } catch (error) {
            console.error('Error fetching student by ID:', error);
        }
    }
    if (students) {
        renderStudents(students);
    }
});

const renderStudents = (students) => {
    //TODO handle majors as an array and loop though and build ul
    if (!Array.isArray(students)) {
        students = [students];
    }
    container.innerHTML = students
        .map(
            (student) => `
            <div>
                <h2>${student.name}</h2>
                <p>Age: ${student.age}</p>
                <p>Gender: ${student.gender}</p>
                <p>Graduation Year: ${student.graduationYear}</p>
            </div>
        `,
        )
        .join('');
};
