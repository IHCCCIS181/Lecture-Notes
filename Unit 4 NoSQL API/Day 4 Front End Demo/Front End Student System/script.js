const studentForm = document.querySelector("#studentForm");
const saveButton = document.querySelector("#submitBtn");

saveButton.addEventListener('click', (e)=> addOrUpdateStudent(e))

window.onload = () => {
    fetch("http://localhost:8080/all")
        .then((response) => response.json())
        .then((data) => displayStudents(data))
        .catch((error) => console.error("Error:", error));
};

const displayStudents = (students) => {
    const tbody = document.querySelector("#studentTable tbody");
    tbody.innerHTML = "";

    students.forEach((student) => {
        const tr = document.createElement("tr");
        tr.dataset.id = student.id;

        ["studentId", "name", "age", "gender", "major", "graduationYear"].forEach(
            (key) => {
                const td = document.createElement("td");
                td.textContent = student[key];
                tr.appendChild(td);
            }
        );

        const actionTd = document.createElement("td");
        const editButton = document.createElement("button");
        editButton.textContent = "Edit";
        editButton.addEventListener("click", () => loadStudent(student));
        actionTd.appendChild(editButton);

        const deleteButton = document.createElement("button");
        deleteButton.textContent = "Delete";
        deleteButton.addEventListener("click", () => deleteStudent(student.studentId));
        actionTd.appendChild(deleteButton);

        tr.appendChild(actionTd);
        tbody.appendChild(tr);
    });
};

const loadStudent = (student) => {
    ["studentId", "name", "age", "gender", "major", "graduationYear"].forEach(
        (key) => {
            document.querySelector(`#${key}`).value = student[key];
        }
    );
};

const addOrUpdateStudent = (e) => {
    e.preventDefault();
    const student = {
        studentId: document.querySelector("#studentId").value,
        name: document.querySelector("#name").value,
        age: document.querySelector("#age").value,
        gender: document.querySelector("#gender").value,
    };

    if (student.id) {
        updateStudent(student);
    } else {
        addStudent(student);
    }
};

const addStudent = (student) => {
    fetch("http://localhost:8080/add", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(student),
    })
        .then((response) => response.text())
        .then(() => window.location.reload())
        .catch((error) => console.error("Error:", error));
};

const updateStudent = (student) => {
   // TODO
};

const deleteStudent = (id) => {
    fetch(`http://localhost:8080/delete/${id}`, {
        method: "DELETE",
    })
        .then((response) => response.text())
        .then(() => window.location.reload())
        .catch((error) => console.error("Error:", error));
};
