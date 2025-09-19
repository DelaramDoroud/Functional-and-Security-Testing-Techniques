<?php
#####command injection vulnerability
if (isset($_GET['cmd'])) {
    $cmd = $_GET['cmd'];
    system($cmd);
} else {
    echo "No command provided.";
}

####SQL injection vulnerability
$db_host = 'db';
$db_username = 'root';
$db_password = 'password';
$db_name = 'course';

$connection = mysqli_connect($db_host, $db_username, $db_password, $db_name);

sleep(2);

   $name = $_GET['name'];
    $query = "SELECT * FROM Courses WHERE name='$name';";

    $results = mysqli_query($connection, $query);

    while($row = mysqli_fetch_array($results)) {
        print_r($row);
    }

?>
