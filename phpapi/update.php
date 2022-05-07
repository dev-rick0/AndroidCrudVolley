<?php

include 'connection.php';
$id = $_POST['id'];
$gender = $_POST['gender'];
$fullName = $_POST['fullName'];
$age = $_POST['age'];
$email = $_POST['email'];
$profession = $_POST['profession'];
$address = $_POST['address'];

$query = mysqli_query($conn, "UPDATE data SET gender = '$gender', full_name = '$fullName', age = '$age', email = '$email', profession = '$profession', address = '$address' WHERE id = '$id' ");
if($query){
    $response['success'] = 'true';
    $response['message'] = 'Data Updated Successfully';
  }else{
    $response['success'] = 'false';
    $response['message'] = 'Data Update Failed';
  }
  
  echo json_encode($response);
  ?>