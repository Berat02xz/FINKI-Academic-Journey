<?php
// Примерни податоци за листа на студенти
$students = [
    ["name" => "John Doe", "age" => 20, "grades" => [85, 90, 78]],
    ["name" => "Jane Smith", "age" => 22, "grades" => [88, 74, 92]],
    ["name" => "Alex Johnson", "age" => 19, "grades" => [95, 80, 85]],
    ["name" => "Emily Davis", "age" => 21, "grades" => [70, 75, 82]],
    ["name" => "Chris Brown", "age" => 23, "grades" => [60, 66, 71]]
];

// Дел 1: Пресметување на Просечна Оценка
function calculateAverage($grades) {
    return array_sum($grades) / count($grades);
}

// Дел 2: Филтрирање на Студенти по Возраст
function filterByAge($students, $age) {
    return array_filter($students, function($student) use ($age) {
        return $student['age'] > $age;
    });
}

// Дел 3: Голема Буква за Имињата на Студентите
function capitalizeNames(&$students) {
    foreach ($students as &$student) {
        $student['name'] = ucwords(strtolower($student['name']));
    }
}

// Дел 4: Прикажување на Студенти
function displayStudents($students) {
    foreach ($students as $student) {
        $averageGrade = calculateAverage($student['grades']);
        echo "Name: {$student['name']}, Age: {$student['age']}, Average Grade: " . number_format($averageGrade, 2) . "\n";
    }
}

// Дополнителен дел: Сортирај Студенти по Име
function sortByName(&$students) {
    usort($students, function($a, $b) {
        return strcmp($a['name'], $b['name']);
    });
}

// Пример за користење на функциите
capitalizeNames($students);
sortByName($students);
displayStudents($students);

echo "\nФилтрирани студенти постари од 20 години:\n";
$filteredStudents = filterByAge($students, 20);
displayStudents($filteredStudents);
?>
