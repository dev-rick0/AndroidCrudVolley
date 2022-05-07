<?php
include 'connection.php';

$gender = $_POST['gender'];
$fullName = $_POST['fullName'];
$age = $_POST['age'];
$email = $_POST['email'];
$profession = $_POST['profession'];
$address = $_POST['address'];

$response = array();
$query = mysqli_query($conn, "INSERT INTO data (gender, full_name, age, email, profession, address) VALUES ('$gender', '$fullName', '$age', '$email', '$profession', '$address')");

if ($query){
    $response['success'] = 'true';
    $response['message'] = 'Data inserted Successfully';
} else{
    $response['success'] = 'false';
    $response['message'] = 'Data Insertion Failed';
    }

echo json_encode($response);
?>