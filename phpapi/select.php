<?php
include 'connection.php';
$id = $_GET['id'];
$query = mysqli_query($conn, "SELECT * FROM data WHERE id='$id' ");
$data = array();
$qry_array = array();
$i=0;
$total = mysqli_num_rows($query);
while($row = mysqli_fetch_array($query)){
    $data['id'] = $row['id'];
    $data['gender'] = $row['gender'];
    $data['fullName'] = $row['full_name'];
    $data['age'] = $row['age'];
    $data['email'] = $row['email'];
    $data['profession'] = $row['profession'];
    $data['address'] = $row['address'];
    $qry_array[$i] = $data;
    $i++;
}
if($query){
    $response['success'] = 'true';
    $response['message'] = 'Data Loaded Successfully';
    $response['total'] = $total;
    $response['data'] = $qry_array;
  }else{
    $response['success'] = 'false';
    $response['message'] = 'Data Loading Failed';
  }
  
  echo json_encode($response);
?>